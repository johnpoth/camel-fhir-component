package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseBundle;

/**
 * Sample API used by fhir Component whose method signatures are read from File.
 */
public class FhirSearch {

    private final IGenericClient client;

    public FhirSearch(IGenericClient client) {
        this.client = client;
    }

    public IBaseBundle searchByUrl(String url) {
        return client.search().byUrl(url).execute();
    }

}
