package org.apache.camel.component.fhir;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.util.component.AbstractApiComponent;

import org.apache.camel.component.fhir.internal.FhirApiCollection;
import org.apache.camel.component.fhir.internal.FhirApiName;

/**
 * Represents the component that manages {@link FhirEndpoint}.
 */
public class FhirComponent extends AbstractApiComponent<FhirApiName, FhirConfiguration, FhirApiCollection> {

    public FhirComponent() {
        super(FhirEndpoint.class, FhirApiName.class, FhirApiCollection.getCollection());
    }

    public FhirComponent(CamelContext context) {
        super(context, FhirEndpoint.class, FhirApiName.class, FhirApiCollection.getCollection());
    }

    @Override
    protected FhirApiName getApiName(String apiNameStr) throws IllegalArgumentException {
        return FhirApiName.fromValue(apiNameStr);
    }

    @Override
    protected Endpoint createEndpoint(String uri, String methodName, FhirApiName apiName,
                                      FhirConfiguration endpointConfiguration) {
        FhirEndpoint endpoint = new FhirEndpoint(uri, this, apiName, methodName, endpointConfiguration);
        endpoint.setName(methodName);
        return endpoint;
    }

    /**
     * To use the shared configuration
     */
    @Override
    public void setConfiguration(FhirConfiguration configuration) {
        super.setConfiguration(configuration);
    }

}
