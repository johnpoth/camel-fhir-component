/*
 * Camel Api Route test generated by camel-api-component-maven-plugin
 * Generated on: Mon May 28 16:47:45 CEST 2018
 */
package org.apache.camel.component.fhir;

import java.util.HashMap;
import java.util.Map;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.fhir.internal.FhirApiCollection;
import org.apache.camel.component.fhir.internal.FhirUpdateApiMethod;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class for {@link org.apache.camel.component.fhir.api.FhirUpdate} APIs.
 * TODO Move the file to src/test/java, populate parameter values, and remove @Ignore annotations.
 * The class source won't be generated again if the generator MOJO finds it under src/test/java.
 */
public class FhirUpdateIntegrationTest extends AbstractFhirTestSupport {

    private static final Logger LOG = LoggerFactory.getLogger(FhirUpdateIntegrationTest.class);
    private static final String PATH_PREFIX = FhirApiCollection.getCollection().getApiName(FhirUpdateApiMethod.class).getName();

    // TODO provide parameter values for resource
    @Ignore
    @Test
    public void testResource() throws Exception {
        final Map<String, Object> headers = new HashMap<String, Object>();
        // parameter type is org.hl7.fhir.instance.model.api.IBaseResource
        headers.put("CamelFhir.resource", null);
        // parameter type is org.hl7.fhir.instance.model.api.IIdType
        headers.put("CamelFhir.id", null);
        // parameter type is String
        headers.put("CamelFhir.sId", null);
        // parameter type is String
        headers.put("CamelFhir.url", null);
        // parameter type is ca.uhn.fhir.rest.api.PreferReturnEnum
        headers.put("CamelFhir.preferReturn", null);

        final ca.uhn.fhir.rest.api.MethodOutcome result = requestBodyAndHeaders("direct://RESOURCE", null, headers);

        assertNotNull("resource result", result);
        LOG.debug("resource: " + result);
    }

    // TODO provide parameter values for resource
    @Ignore
    @Test
    public void testResource_1() throws Exception {
        final Map<String, Object> headers = new HashMap<String, Object>();
        // parameter type is String
        headers.put("CamelFhir.sResource", null);
        // parameter type is org.hl7.fhir.instance.model.api.IIdType
        headers.put("CamelFhir.id", null);
        // parameter type is String
        headers.put("CamelFhir.sId", null);
        // parameter type is String
        headers.put("CamelFhir.url", null);
        // parameter type is ca.uhn.fhir.rest.api.PreferReturnEnum
        headers.put("CamelFhir.preferReturn", null);

        final ca.uhn.fhir.rest.api.MethodOutcome result = requestBodyAndHeaders("direct://RESOURCE_1", null, headers);

        assertNotNull("resource result", result);
        LOG.debug("resource: " + result);
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                // test route for resource
                from("direct://RESOURCE")
                    .to("fhir://" + PATH_PREFIX + "/resource");

                // test route for resource
                from("direct://RESOURCE_1")
                    .to("fhir://" + PATH_PREFIX + "/resource");

            }
        };
    }
}
