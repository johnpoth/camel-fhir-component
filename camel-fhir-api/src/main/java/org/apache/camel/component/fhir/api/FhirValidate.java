package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseResource;

/**
 * API for validating resources
 */
public class FhirValidate {

    private final IGenericClient client;

    public FhirValidate(IGenericClient client) {
        this.client = client;
    }

    public MethodOutcome resource(IBaseResource resource) {
        return client.validate().resource(resource).execute();
    }

    public MethodOutcome resource(String sResource) {
        return client.validate().resource(sResource).execute();
    }
}
