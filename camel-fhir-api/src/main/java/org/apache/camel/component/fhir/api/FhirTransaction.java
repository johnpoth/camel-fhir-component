package org.apache.camel.component.fhir.api;

import java.util.List;
import java.util.Map;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.ITransactionTyped;
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
     * @param extraParameters see {@link ExtraParameters} for a full list of parameters that can be passed, may be NULL
     */
    public List<IBaseResource> withResources(List<IBaseResource> resources, Map<ExtraParameters, Object> extraParameters) {
        ITransactionTyped<List<IBaseResource>> transactionTyped = client.transaction().withResources(resources);
        ExtraParameters.process(extraParameters, transactionTyped);
        return transactionTyped.execute();
    }

    /**
     * Use the given Bundle resource as the transaction input
     * @param extraParameters see {@link ExtraParameters} for a full list of parameters that can be passed, may be NULL
     */
    public IBaseBundle withBundle(IBaseBundle bundle, Map<ExtraParameters, Object> extraParameters) {
        ITransactionTyped<IBaseBundle> transactionTyped = client.transaction().withBundle(bundle);
        ExtraParameters.process(extraParameters, transactionTyped);
        return transactionTyped.execute();
    }

    /**
     * Use the given raw text (should be a Bundle resource) as the transaction input
     * @param extraParameters see {@link ExtraParameters} for a full list of parameters that can be passed, may be NULL
     */
    public String withBundle(String stringBundle, Map<ExtraParameters, Object> extraParameters) {
        ITransactionTyped<String> transactionTyped = client.transaction().withBundle(stringBundle);
        ExtraParameters.process(extraParameters, transactionTyped);
        return transactionTyped.execute();
    }
}