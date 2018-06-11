package org.apache.camel.component.fhir.api;

import java.util.Map;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IFetchConformanceTyped;
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
    public <T extends IBaseConformance> T ofType(Class<T> type, Map<ExtraParameters, Object> extraParameters) {
        IFetchConformanceTyped<T> fetchConformanceTyped = client.capabilities().ofType(type);
        fetchConformanceTyped = ExtraParameters.process(extraParameters, fetchConformanceTyped);
        return fetchConformanceTyped.execute();
    }

}
