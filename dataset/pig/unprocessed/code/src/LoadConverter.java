/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pig.backend.hadoop.executionengine.spark.converter;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import scala.Function1;
import scala.Tuple2;
import scala.runtime.AbstractFunction1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.pig.LoadFunc;
import org.apache.pig.PigConstants;
import org.apache.pig.backend.hadoop.executionengine.mapReduceLayer.MRConfiguration;
import org.apache.pig.backend.hadoop.executionengine.mapReduceLayer.PigInputFormat;
import org.apache.pig.backend.hadoop.executionengine.mapReduceLayer.PigMapReduce;
import org.apache.pig.backend.hadoop.executionengine.physicalLayer.PhysicalOperator;
import org.apache.pig.backend.hadoop.executionengine.physicalLayer.plans.PhysicalPlan;
import org.apache.pig.backend.hadoop.executionengine.physicalLayer.relationalOperators.POLoad;
import org.apache.pig.backend.hadoop.executionengine.physicalLayer.relationalOperators.POMergeJoin;
import org.apache.pig.backend.hadoop.executionengine.spark.SparkEngineConf;
import org.apache.pig.backend.hadoop.executionengine.spark.SparkUtil;
import org.apache.pig.backend.hadoop.executionengine.spark.running.PigInputFormatSpark;
import org.apache.pig.data.Tuple;
import org.apache.pig.impl.PigContext;
import org.apache.pig.impl.io.FileSpec;
import org.apache.pig.impl.plan.OperatorKey;
import org.apache.pig.impl.util.ObjectSerializer;
import org.apache.pig.impl.util.UDFContext;
import org.apache.pig.tools.pigstats.spark.SparkCounters;
import org.apache.pig.tools.pigstats.spark.SparkPigStatusReporter;
import org.apache.pig.tools.pigstats.spark.SparkStatsUtil;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.rdd.RDD;

import com.google.common.collect.Lists;

/**
 * Converter that loads data via POLoad and converts it to RRD&lt;Tuple>. Abuses
 * the interface a bit in that there is no input RRD to convert in this case.
 * Instead input is the source path of the POLoad.
 */
@SuppressWarnings({ "serial" })
public class LoadConverter implements RDDConverter<Tuple, Tuple, POLoad> {
    private static Log LOG = LogFactory.getLog(LoadConverter.class);

    private PigContext pigContext;
    private PhysicalPlan physicalPlan;
    private SparkContext sparkContext;
    private JobConf jobConf;
    private SparkEngineConf sparkEngineConf;

    public LoadConverter(PigContext pigContext, PhysicalPlan physicalPlan,
            SparkContext sparkContext, JobConf jobConf, SparkEngineConf sparkEngineConf) {
        this.pigContext = pigContext;
        this.physicalPlan = physicalPlan;
        this.sparkContext = sparkContext;
        this.jobConf = jobConf;
        this.sparkEngineConf = sparkEngineConf;
    }

    @Override
    public RDD<Tuple> convert(List<RDD<Tuple>> predecessorRdds, POLoad op)
            throws IOException {
        configureLoader(physicalPlan, op, jobConf);

        // Set the input directory for input formats that are backed by a
        // filesystem. (Does not apply to HBase, for example).
        jobConf.set("mapreduce.input.fileinputformat.inputdir",
                op.getLFile().getFileName());

        // internally set pig.noSplitCombination as true ONLY for
        // the POLoad operator which has POMergeJoin successor.
        if (hasMergeJoinSuccessor(op)) {
            jobConf.set("pig.noSplitCombination", "true");
        }


        //serialize the UDFContext#udfConfs in jobConf
        UDFContext.getUDFContext().serialize(jobConf);

        //SparkContext.newAPIHadoop will broadcast the jobConf to other worker nodes.
        //Later in PigInputFormatSpark#createRecordReader, jobConf will be used to
        //initialize PigContext,UDFContext and SchemaTupleBackend.
        RDD<Tuple2<Text, Tuple>> hadoopRDD = sparkContext.newAPIHadoopRDD(
                jobConf, PigInputFormatSpark.class, Text.class, Tuple.class);

        registerUdfFiles();

        ToTupleFunction ttf = new ToTupleFunction(sparkEngineConf);

        //create SparkCounter and set it for ToTupleFunction
        boolean disableCounter = jobConf.getBoolean("pig.disable.counter", false);
        if (!op.isTmpLoad() && !disableCounter) {
            String counterName = SparkStatsUtil.getCounterName(op);
            SparkPigStatusReporter counterReporter = SparkPigStatusReporter.getInstance();
            if (counterReporter.getCounters() != null) {
                counterReporter.getCounters().createCounter(
                        SparkStatsUtil.SPARK_INPUT_COUNTER_GROUP,
                        counterName);
            }

            ttf.setDisableCounter(disableCounter);
            ttf.setCounterGroupName(SparkStatsUtil.SPARK_INPUT_COUNTER_GROUP);
            ttf.setCounterName(counterName);
            ttf.setSparkCounters(SparkPigStatusReporter.getInstance().getCounters());
        }

        // map to get just RDD<Tuple>
        return hadoopRDD.map(ttf,
                SparkUtil.getManifest(Tuple.class));
    }

    private void registerUdfFiles() throws MalformedURLException{
        Map<String, File> scriptFiles = pigContext.getScriptFiles();
        for (Map.Entry<String, File> scriptFile : scriptFiles.entrySet()) {
            File script = scriptFile.getValue();
            if (script.exists()) {
                sparkContext.addFile(script.toURI().toURL().toExternalForm());
            }
        }
    }

    private static class ToTupleFunction extends
            AbstractFunction1<Tuple2<Text, Tuple>, Tuple> implements
            Function1<Tuple2<Text, Tuple>, Tuple>, Serializable {

        private String counterGroupName;
        private String counterName;
        private SparkCounters sparkCounters;
        private boolean disableCounter;
        private SparkEngineConf sparkEngineConf;
        private boolean initialized;

        public ToTupleFunction(SparkEngineConf sparkEngineConf){
               this.sparkEngineConf = sparkEngineConf;
        }

        @Override
        public Tuple apply(Tuple2<Text, Tuple> v1) {
            if (!initialized) {
                long partitionId = TaskContext.get().partitionId();
                Configuration jobConf = PigMapReduce.sJobConfInternal.get();
                jobConf.set(PigConstants.TASK_INDEX, Long.toString(partitionId));
                jobConf.set(MRConfiguration.TASK_ID, Long.toString(partitionId));
                initialized = true;
            }
            if (sparkCounters != null && disableCounter == false) {
                sparkCounters.increment(counterGroupName, counterName, 1L);
            }
            return v1._2();
        }

        public void setCounterGroupName(String counterGroupName) {
            this.counterGroupName = counterGroupName;
        }

        public void setCounterName(String counterName) {
            this.counterName = counterName;
        }

        public void setSparkCounters(SparkCounters sparkCounters) {
            this.sparkCounters = sparkCounters;
        }

        public void setDisableCounter(boolean disableCounter) {
            this.disableCounter = disableCounter;
        }
    }

    /**
     * stolen from JobControlCompiler TODO: refactor it to share this
     *
     * @param physicalPlan
     * @param poLoad
     * @param jobConf
     * @return
     * @throws java.io.IOException
     */
    private static JobConf configureLoader(PhysicalPlan physicalPlan,
            POLoad poLoad, JobConf jobConf) throws IOException {

        Job job = new Job(jobConf);
        LoadFunc loadFunc = poLoad.getLoadFunc();

        loadFunc.setLocation(poLoad.getLFile().getFileName(), job);

        // stolen from JobControlCompiler
        ArrayList<FileSpec> pigInputs = new ArrayList<FileSpec>();
        // Store the inp filespecs
        pigInputs.add(poLoad.getLFile());

        ArrayList<List<OperatorKey>> inpTargets = Lists.newArrayList();
        ArrayList<String> inpSignatures = Lists.newArrayList();
        ArrayList<Long> inpLimits = Lists.newArrayList();
        // Store the target operators for tuples read
        // from this input
        List<PhysicalOperator> loadSuccessors = physicalPlan
                .getSuccessors(poLoad);
        List<OperatorKey> loadSuccessorsKeys = Lists.newArrayList();
        if (loadSuccessors != null) {
            for (PhysicalOperator loadSuccessor : loadSuccessors) {
                loadSuccessorsKeys.add(loadSuccessor.getOperatorKey());
            }
        }
        inpTargets.add(loadSuccessorsKeys);
        inpSignatures.add(poLoad.getSignature());
        inpLimits.add(poLoad.getLimit());

        jobConf.set(PigInputFormat.PIG_INPUTS, ObjectSerializer.serialize(pigInputs));
        jobConf.set(PigInputFormat.PIG_INPUT_TARGETS, ObjectSerializer.serialize(inpTargets));
        jobConf.set(PigInputFormat.PIG_INPUT_SIGNATURES,
                ObjectSerializer.serialize(inpSignatures));
        jobConf.set(PigInputFormat.PIG_INPUT_LIMITS, ObjectSerializer.serialize(inpLimits));
        return jobConf;
    }

    private static boolean hasMergeJoinSuccessor(PhysicalOperator op) {
        if (op == null || op.getParentPlan() == null) {
            return false;
        }
        List<PhysicalOperator> successors = op.getParentPlan().getSuccessors(op);
        if (successors == null ) {
            return false;
        }
        for (PhysicalOperator successor : successors){
            if (successor instanceof POMergeJoin){
                return true;
            }
            if (hasMergeJoinSuccessor(successor)){
                return true;
            }
        }
        return false;
    }

}
