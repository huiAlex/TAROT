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

package org.apache.pig.backend.hadoop.executionengine.spark;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.mapred.JobConf;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Pulled class from Hive on Spark
 */
public class KryoSerializer {
    private static final Log LOG = LogFactory.getLog(KryoSerializer.class);

    public static byte[] serializeJobConf(JobConf jobConf) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            jobConf.write(new DataOutputStream(out));
        } catch (IOException e) {
            LOG.error("Error serializing job configuration", e);
            return null;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                LOG.error("Error closing output stream", e);
            }
        }

        return out.toByteArray();

    }

    public static JobConf deserializeJobConf(byte[] buffer) {
        JobConf conf = new JobConf();
        try {
            conf.readFields(new DataInputStream(new ByteArrayInputStream(buffer)));
        } catch (IOException e) {
            LOG.error("Error de-serializing job configuration");
            return null;
        }
        return conf;
    }

}
