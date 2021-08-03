/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org).
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.serializer.fst;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectOutput;
import org.wso2.carbon.identity.application.authentication.framework.exception.SessionSerializerException;
import org.wso2.carbon.identity.application.authentication.framework.store.SessionSerializer;

import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;


/**
 * Implementation if Session Serializer with FST Serialization library.
 */
public class FSTSessionSerializer implements SessionSerializer {

    private FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

    @Override
    public InputStream serializeSessionObject(Object o) throws SessionSerializerException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FSTObjectOutput out = conf.getObjectOutput(baos);
        try {
            out.writeObject(o, Object.class);
            out.flush();
            baos.close();
        } catch (IOException e) {
            throw new SessionSerializerException("Error while serializing the session object using"
                    + " FST serializer.", e);
        }
        return new ByteArrayInputStream(baos.toByteArray());
    }

    @Override
    public Object deSerializeSessionObject(InputStream inputStream) throws SessionSerializerException {

        try {
            return conf.getObjectInput(inputStream).readObject(Object.class);
        } catch (Exception e) {
            throw new SessionSerializerException("Error while de serializing the session object using"
                    + " FST serializer.", e);
        }
    }
}
