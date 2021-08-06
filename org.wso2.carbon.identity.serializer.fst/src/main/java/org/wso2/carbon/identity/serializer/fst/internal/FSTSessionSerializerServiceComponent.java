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

package org.wso2.carbon.identity.serializer.fst.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.wso2.carbon.identity.application.authentication.framework.store.SessionSerializer;
import org.wso2.carbon.identity.serializer.fst.FSTSessionSerializer;

/**
 * FST Serialization service component.
 */
@Component(
        name = "identity.serializer.fst",
        immediate = true
)
public class FSTSessionSerializerServiceComponent {

    private static final Log log = LogFactory.getLog(FSTSessionSerializerServiceComponent.class);

    @Activate
    protected void activate(ComponentContext ctxt) {

        try {
            BundleContext bundleContext = ctxt.getBundleContext();
            bundleContext.registerService(SessionSerializer.class.getName() ,new FSTSessionSerializer(),null);
            if (log.isDebugEnabled()) {
                log.debug("FST Session Serializer is activated.");
            }
        } catch (Throwable e) {
            log.error("Error occurred while activating the FST Session Serializer. Hence the performance of session "
                    + "persistence will be impaired.", e);
        }
    }

    @Deactivate
    protected void deactivate(ComponentContext ctxt) {

        if (log.isDebugEnabled()) {
            log.debug("Default Session Serializer is deactivated.");
        }
    }
}
