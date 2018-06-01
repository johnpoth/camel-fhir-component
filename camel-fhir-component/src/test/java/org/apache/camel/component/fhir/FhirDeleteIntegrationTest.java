/*
 * Camel Api Route test generated by camel-api-component-maven-plugin
 * Generated on: Mon May 28 16:47:45 CEST 2018
 */
package org.apache.camel.component.fhir;

import java.util.HashMap;
import java.util.Map;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.fhir.internal.FhirApiCollection;
import org.apache.camel.component.fhir.internal.FhirDeleteApiMethod;
import org.hl7.fhir.instance.model.api.IBaseOperationOutcome;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class for {@link org.apache.camel.component.fhir.api.FhirDelete} APIs.
 * The class source won't be generated again if the generator MOJO finds it under src/test/java.
 */
public class FhirDeleteIntegrationTest extends AbstractFhirTestSupport {

    private static final Logger LOG = LoggerFactory.getLogger(FhirDeleteIntegrationTest.class);
    private static final String PATH_PREFIX = FhirApiCollection.getCollection().getApiName(FhirDeleteApiMethod.class).getName();

    @Test
    public void testDeleteResource() throws Exception {
        assertTrue(patientExists());
        // using org.hl7.fhir.instance.model.api.IBaseResource message body for single parameter "resource"
        IBaseOperationOutcome result = requestBody("direct://RESOURCE", this.patient);

        assertNotNull("resource result", result);
        assertFalse(patientExists());
        LOG.debug("resource: " + result);
    }

    @Test
    public void testDeleteResourceById() throws Exception {
        assertTrue(patientExists());
        // using org.hl7.fhir.instance.model.api.IIdType message body for single parameter "id"
        IBaseOperationOutcome result = requestBody("direct://RESOURCE_BY_ID", this.patient.getIdElement());

        assertNotNull("resourceById result", result);
        assertFalse(patientExists());
        LOG.debug("resourceById: " + result);
    }

    @Test
    public void testDeleteResourceByStringId() throws Exception {
        assertTrue(patientExists());
        Map<String, Object> headers = new HashMap<>();
        // parameter type is String
        headers.put("CamelFhir.type", "Patient");
        // parameter type is String
        headers.put("CamelFhir.sId", this.patient.getIdElement().getIdPart());
        IBaseOperationOutcome result = requestBodyAndHeaders("direct://RESOURCE_BY_STRING_ID", null, headers);
        assertNotNull("resourceById result", result);
        assertFalse(patientExists());
        LOG.debug("resourceById: " + result);
    }

    @Test
    public void testDeleteResourceConditionalByUrl() throws Exception {
        assertTrue(patientExists());
        IBaseOperationOutcome result = requestBody("direct://RESOURCE_CONDITIONAL_BY_URL", "Patient?given=Vincent&family=Freeman");
        assertNotNull("resourceConditionalByUrl result", result);
        assertFalse(patientExists());
        LOG.debug("resourceConditionalByUrl: " + result);
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                // test route for resource
                from("direct://RESOURCE")
                    .to("fhir://" + PATH_PREFIX + "/resource?inBody=resource");

                // test route for resourceById
                from("direct://RESOURCE_BY_ID")
                    .to("fhir://" + PATH_PREFIX + "/resourceById?inBody=id");

                // test route for resourceById
                from("direct://RESOURCE_BY_STRING_ID")
                    .to("fhir://" + PATH_PREFIX + "/resourceById");

                // test route for resourceConditionalByUrl
                from("direct://RESOURCE_CONDITIONAL_BY_URL")
                    .to("fhir://" + PATH_PREFIX + "/resourceConditionalByUrl?inBody=url");

            }
        };
    }
}
