package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseResource;

/**
 * Sample API used by fhir Component whose method signatures are read from File.
 */
public class FhirValidate {

    private final IGenericClient client;

    public FhirValidate(IGenericClient client) {
        this.client = client;
    }

    public MethodOutcome resource(IBaseResource theResource) {
        return client.validate().resource(theResource).execute();
    }


}
