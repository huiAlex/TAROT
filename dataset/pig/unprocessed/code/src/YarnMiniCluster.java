/**
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
package org.apache.pig.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hdfs.MiniDFSCluster;
import org.apache.hadoop.mapreduce.MRJobConfig;
import org.apache.hadoop.mapreduce.v2.MiniMRYarnCluster;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.pig.ExecType;

public abstract class YarnMiniCluster extends MiniGenericCluster {
    protected static final File CONF_DIR = new File("build/classes");
    protected static final File CORE_CONF_FILE = new File(CONF_DIR, "core-site.xml");
    protected static final File HDFS_CONF_FILE = new File(CONF_DIR, "hdfs-site.xml");
    protected static final File MAPRED_CONF_FILE = new File(CONF_DIR, "mapred-site.xml");
    protected static final File YARN_CONF_FILE = new File(CONF_DIR, "yarn-site.xml");


    protected Configuration m_dfs_conf = null;
    protected MiniMRYarnCluster m_mr = null;
    protected Configuration m_mr_conf = null;


    @Override
    protected void setupMiniDfsAndMrClusters() {
        try {
            deleteConfFiles();
            CONF_DIR.mkdirs();

            // Build mini DFS cluster
            Configuration hdfsConf = new Configuration();
            m_dfs = new MiniDFSCluster.Builder(hdfsConf)
                    .numDataNodes(2)
                    .format(true)
                    .racks(null)
                    .build();
            m_fileSys = m_dfs.getFileSystem();
            m_dfs_conf = m_dfs.getConfiguration(0);

            //Create user home directory
            m_fileSys.mkdirs(m_fileSys.getWorkingDirectory());
            // Write core-site.xml
            Configuration core_site = new Configuration(false);
            core_site.set(FileSystem.FS_DEFAULT_NAME_KEY, m_dfs_conf.get(FileSystem.FS_DEFAULT_NAME_KEY));
            core_site.writeXml(new FileOutputStream(CORE_CONF_FILE));

            Configuration hdfs_site = new Configuration(false);
            for (Map.Entry<String, String> conf : m_dfs_conf) {
                if (ArrayUtils.contains(m_dfs_conf.getPropertySources(conf.getKey()), "programatically")) {
                    hdfs_site.set(conf.getKey(), m_dfs_conf.getRaw(conf.getKey()));
                }
            }
            hdfs_site.writeXml(new FileOutputStream(HDFS_CONF_FILE));

            m_dfs_conf.set("yarn.scheduler.capacity.root.queues", "default");
            m_dfs_conf.set("yarn.scheduler.capacity.root.default.capacity", "100");
            m_dfs_conf.set("yarn.scheduler.capacity.maximum-am-resource-percent", "0.1");
            // Build mini YARN cluster
            m_mr = new MiniMRYarnCluster("PigMiniCluster", 2);
            m_mr.init(m_dfs_conf);
            m_mr.start();
            m_mr_conf = m_mr.getConfig();

            File libDir = new File(System.getProperty("ivy.lib.dir", "build/ivy/lib/Pig"));
            File sparkLibDir = new File(System.getProperty("ivy.lib.dir.spark", "build/ivy/lib/Pig/spark"));
            File classesDir = new File(System.getProperty("build.classes", "build/classes"));
            File testClassesDir = new File(System.getProperty("test.build.classes", "test/build/classes"));

            String classpath = libDir.getAbsolutePath() + "/*"
                    + (Util.isSparkExecType(getExecType()) ? File.pathSeparator + sparkLibDir.getAbsolutePath() + "/*" : "")
                    + File.pathSeparator + classesDir.getAbsolutePath()
                    + File.pathSeparator + testClassesDir.getAbsolutePath();

            m_mr_conf.set(YarnConfiguration.YARN_APPLICATION_CLASSPATH, classpath);
            m_mr_conf.setInt(MRJobConfig.IO_SORT_MB, 50);
            m_mr_conf.set(MRJobConfig.MAP_JAVA_OPTS, "-Xmx384m");
            m_mr_conf.set(MRJobConfig.REDUCE_JAVA_OPTS, "-Xmx384m");
            m_mr_conf.setInt(MRJobConfig.MAP_MEMORY_MB, 512);
            m_mr_conf.setInt(MRJobConfig.REDUCE_MEMORY_MB, 512);
            m_mr_conf.set(MRJobConfig.MR_AM_COMMAND_OPTS, "-Xmx384m");
            m_mr_conf.setInt(MRJobConfig.MR_AM_VMEM_MB, 512);

            Configuration mapred_site = new Configuration(false);
            Configuration yarn_site = new Configuration(false);
            for (Map.Entry<String, String> conf : m_mr_conf) {
                if (ArrayUtils.contains(m_mr_conf.getPropertySources(conf.getKey()), "programatically")) {
                    if (conf.getKey().contains("yarn")) {
                        yarn_site.set(conf.getKey(), m_mr_conf.getRaw(conf.getKey()));
                    } else if (!conf.getKey().startsWith("dfs")) {
                        mapred_site.set(conf.getKey(), m_mr_conf.getRaw(conf.getKey()));
                    }
                }
            }

            mapred_site.writeXml(new FileOutputStream(MAPRED_CONF_FILE));
            yarn_site.writeXml(new FileOutputStream(YARN_CONF_FILE));

            m_conf = m_mr_conf;
            System.setProperty("junit.hadoop.conf", CONF_DIR.getPath());
            System.setProperty("hadoop.log.dir", "build/test/logs");
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    protected void deleteConfFiles() {
        if(CORE_CONF_FILE.exists()) {
            CORE_CONF_FILE.delete();
        }
        if(HDFS_CONF_FILE.exists()) {
            HDFS_CONF_FILE.delete();
        }
        if(MAPRED_CONF_FILE.exists()) {
            MAPRED_CONF_FILE.delete();
        }
        if(YARN_CONF_FILE.exists()) {
            YARN_CONF_FILE.delete();
        }
    }

    @Override
    protected void shutdownMiniMrClusters() {
        deleteConfFiles();
        if (m_mr != null) {
            m_mr.stop();
            m_mr = null;
        }
    }
}
