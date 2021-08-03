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

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wso2.carbon.identity.application.authentication.framework.exception.SessionSerializerException;

import java.io.InputStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * FST Serialization Test Class.
 */
public class FSTSessionSerializerTest {

    private Employee employee;
    private Employee manager;

    @BeforeMethod
    public void setUp() {
        employee = new Employee();
        employee.setId(42L);
        employee.setName("Fabio");
        employee.setSalary(1234.5);
        employee.setBytes(new byte[100]);
        manager = new Employee();
        manager.setName("Cabio");
        employee.setManager(manager);
        Employee[] team = {new Employee(), new Employee()};
        employee.setTeam(team);
    }

    @Test
    public void testSerializeSessionObject() throws SessionSerializerException {

        FSTSessionSerializer fstSessionSerializer = new FSTSessionSerializer();
        InputStream serialized = fstSessionSerializer.serializeSessionObject(employee);
        Object deserialized = fstSessionSerializer.deSerializeSessionObject(serialized);
        assertTrue(deserialized instanceof Employee);
        assertEquals(employee.getName(), ((Employee) deserialized).getName());
        assertEquals(employee.getManager().getName(), ((Employee) deserialized).getManager().getName());
        assertEquals(employee.getTeam().length, ((Employee) deserialized).getTeam().length);
    }
}
