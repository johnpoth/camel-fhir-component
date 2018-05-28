package org.apache.camel.component.fhir.api;

import java.util.List;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.instance.model.api.IBaseResource;

/**
 * API for sending a transaction (collection of resources) to the server to be executed as a single unit.
 */
public class FhirTransaction {

    private final IGenericClient client;

    public FhirTransaction(IGenericClient client) {
        this.client = client;
    }

    /**
     * Use a list of resources as the transaction input
     */
    public List<IBaseResource> withResources(List<IBaseResource> resources) {
        return client.transaction().withResources(resources).execute();
    }

    /**
     * Use the given Bundle resource as the transaction input
     */
    public IBaseBundle withBundle(IBaseBundle bundle) {
        return client.transaction().withBundle(bundle).execute();
    }

    /**
     * Use the given raw text (should be a Bundle resource) as the transaction input
     */
    public String withBundle(String sBundle) {
        return client.transaction().withBundle(sBundle).execute();
    }
}
