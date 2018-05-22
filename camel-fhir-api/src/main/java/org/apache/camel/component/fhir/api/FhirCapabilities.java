package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseConformance;

/**
 * Sample API used by fhir Component whose method signatures are read from File.
 */
public class FhirCapabilities {

    private final IGenericClient client;

    public FhirCapabilities(IGenericClient client) {
        this.client = client;
    }

    public <T extends IBaseConformance> T ofType(Class<T> theType) {
        return client.capabilities().ofType(theType).execute();
    }

}
