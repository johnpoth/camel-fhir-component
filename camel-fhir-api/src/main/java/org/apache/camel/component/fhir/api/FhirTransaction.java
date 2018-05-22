package org.apache.camel.component.fhir.api;

import java.util.List;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseResource;

/**
 * Sample API used by fhir Component whose method signatures are read from File.
 */
public class FhirTransaction {

    private final IGenericClient client;

    public FhirTransaction(IGenericClient client) {
        this.client = client;
    }

    /**
     * Use a list of resources as the transaction input
     */
    public List<IBaseResource> withResources(List<? extends IBaseResource> theResource) {
        return client.transaction().withResources(theResource).execute();
    }


}
