package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseConformance;

/**
 * Fetch the capability statement for the server
 */
public class FhirCapabilities {

    private final IGenericClient client;

    public FhirCapabilities(IGenericClient client) {
        this.client = client;
    }

    /**
     * Retrieve the conformance statement using the given model type
     */
    public <T extends IBaseConformance> T ofType(Class<T> theType) {
        return client.capabilities().ofType(theType).execute();
    }

}
