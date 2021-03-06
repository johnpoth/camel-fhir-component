/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.fhir;

import java.util.HashMap;
import java.util.Map;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.fhir.internal.FhirApiCollection;
import org.apache.camel.component.fhir.internal.FhirOperationApiMethod;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class for {@link org.apache.camel.component.fhir.api.FhirOperation} APIs.
 * The class source won't be generated again if the generator MOJO finds it under src/test/java.
 */
public class FhirOperationIntegrationTest extends AbstractFhirTestSupport {

    private static final Logger LOG = LoggerFactory.getLogger(FhirOperationIntegrationTest.class);
    private static final String PATH_PREFIX = FhirApiCollection.getCollection().getApiName(FhirOperationApiMethod.class).getName();

    // TODO provide parameter values for messageBundle
    @Ignore
    @Test
    public void testMessageBundle() throws Exception {
        final Map<String, Object> headers = new HashMap<String, Object>();
        // parameter type is org.hl7.fhir.instance.model.api.IBaseBundle
        headers.put("CamelFhir.theMsgBundle", null);
        // parameter type is Class
        headers.put("CamelFhir.theResponseClass", null);

        final org.hl7.fhir.instance.model.api.IBaseResource result = requestBodyAndHeaders("direct://MESSAGEBUNDLE", null, headers);

        assertNotNull("messageBundle result", result);
        LOG.debug("messageBundle: " + result);
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                // test route for messageBundle
                from("direct://MESSAGEBUNDLE")
                    .to("fhir://" + PATH_PREFIX + "/messageBundle");

            }
        };
    }
}
