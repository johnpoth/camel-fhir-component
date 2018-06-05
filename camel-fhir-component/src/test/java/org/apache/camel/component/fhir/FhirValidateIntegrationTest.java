/*
 * Camel Api Route test generated by camel-api-component-maven-plugin
 * Generated on: Mon May 28 16:47:45 CEST 2018
 */
package org.apache.camel.component.fhir;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.fhir.internal.FhirApiCollection;
import org.apache.camel.component.fhir.internal.FhirValidateApiMethod;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.OperationOutcome;
import org.hl7.fhir.dstu3.model.Patient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class for {@link org.apache.camel.component.fhir.api.FhirValidate} APIs.
 * The class source won't be generated again if the generator MOJO finds it under src/test/java.
 */
public class FhirValidateIntegrationTest extends AbstractFhirTestSupport {

    private static final Logger LOG = LoggerFactory.getLogger(FhirValidateIntegrationTest.class);
    private static final String PATH_PREFIX = FhirApiCollection.getCollection().getApiName(FhirValidateApiMethod.class).getName();

    @Test
    public void testResource() throws Exception {
        Patient bobbyHebb      = new Patient().addName(new HumanName().addGiven("Bobby").setFamily("Hebb"));
        // using org.hl7.fhir.instance.model.api.IBaseResource message body for single parameter "resource"
        MethodOutcome result = requestBody("direct://RESOURCE", bobbyHebb);

        assertNotNull("resource result", result);
        LOG.debug("resource: " + result);
        assertNotNull(result.getOperationOutcome());
        assertTrue(((OperationOutcome) result.getOperationOutcome()).getText().getDivAsString().contains("No issues detected during validation"));
    }

    @Test
    public void testResourceAsString() throws Exception {
        Patient bobbyHebb      = new Patient().addName(new HumanName().addGiven("Bobby").setFamily("Hebb"));
        // using org.hl7.fhir.instance.model.api.IBaseResource message body for single parameter "resource"
        MethodOutcome result = requestBody("direct://RESOURCE_AS_STRING", this.fhirContext.newXmlParser().encodeResourceToString(bobbyHebb));

        assertNotNull("resource result", result);
        LOG.debug("resource: " + result);
        assertNotNull(result.getOperationOutcome());
        assertTrue(((OperationOutcome) result.getOperationOutcome()).getText().getDivAsString().contains("No issues detected during validation"));
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                // test route for resource
                from("direct://RESOURCE")
                    .to("fhir://" + PATH_PREFIX + "/resource?inBody=resource");

                // test route for resource
                from("direct://RESOURCE_AS_STRING")
                    .to("fhir://" + PATH_PREFIX + "/resource?inBody=resourceAsString");

            }
        };
    }
}
