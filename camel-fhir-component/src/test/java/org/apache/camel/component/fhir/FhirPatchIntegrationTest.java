/*
 * Camel Api Route test generated by camel-api-component-maven-plugin
 * Generated on: Mon May 28 16:47:45 CEST 2018
 */
package org.apache.camel.component.fhir;

import java.util.HashMap;
import java.util.Map;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.fhir.internal.FhirApiCollection;
import org.apache.camel.component.fhir.internal.FhirPatchApiMethod;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.instance.model.api.IIdType;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class for {@link org.apache.camel.component.fhir.api.FhirPatch} APIs.
 * The class source won't be generated again if the generator MOJO finds it under src/test/java.
 */
public class FhirPatchIntegrationTest extends AbstractFhirTestSupport {

    private static final Logger LOG = LoggerFactory.getLogger(FhirPatchIntegrationTest.class);
    private static final String PATH_PREFIX = FhirApiCollection.getCollection().getApiName(FhirPatchApiMethod.class).getName();
    private static final String PATCH = "[ { \"op\":\"replace\", \"path\":\"/active\", \"value\":true } ]";

    @Test
    public void testPatchById() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is String
        headers.put("CamelFhir.patchBody", PATCH);
        // parameter type is org.hl7.fhir.instance.model.api.IIdType
        headers.put("CamelFhir.id", this.patient.getIdElement());
        // parameter type is ca.uhn.fhir.rest.api.PreferReturnEnum
        headers.put("CamelFhir.preferReturn", null);

        MethodOutcome result = requestBodyAndHeaders("direct://PATCH_BY_ID", null, headers);
        assertNotNull("patchById result", result);
        assertActive(result);
    }

    @Test
    public void testPatchByStringId() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is String
        headers.put("CamelFhir.patchBody", PATCH);
        // parameter type is String
        headers.put("CamelFhir.stringId", this.patient.getId());
        // parameter type is ca.uhn.fhir.rest.api.PreferReturnEnum
        headers.put("CamelFhir.preferReturn", null);

        MethodOutcome result = requestBodyAndHeaders("direct://PATCH_BY_SID", null, headers);
        assertActive(result);
    }

    @Test
    @Ignore(value="https://github.com/jamesagnew/hapi-fhir/issues/955")
    public void testPatchByUrl() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is String
        headers.put("CamelFhir.patchBody", PATCH);
        // parameter type is String
        headers.put("CamelFhir.url", "Patient?given=Vincent&family=Freeman");
        // parameter type is ca.uhn.fhir.rest.api.PreferReturnEnum
        headers.put("CamelFhir.preferReturn", null);

        MethodOutcome result = requestBodyAndHeaders("direct://PATCH_BY_URL", null, headers);

        assertNotNull("patchByUrl result", result);
        LOG.debug("patchByUrl: " + result);
        assertActive(result);
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                // test route for patchById
                from("direct://PATCH_BY_ID")
                    .to("fhir://" + PATH_PREFIX + "/patchById");

                // test route for patchBySId
                from("direct://PATCH_BY_SID")
                    .to("fhir://" + PATH_PREFIX + "/patchById");

                // test route for patchByUrl
                from("direct://PATCH_BY_URL")
                    .to("fhir://" + PATH_PREFIX + "/patchByUrl");

            }
        };
    }

    private void assertActive(MethodOutcome result) {
        LOG.debug("result: " + result);
        IIdType id = result.getId();

        Patient patient = fhirClient.read().resource(Patient.class).withId(id).preferResponseType(Patient.class).execute();
        System.out.print(fhirContext.newJsonParser().encodeResourceToString(patient));
        assertTrue(patient.getActive());
    }
}
