package org.apache.camel.component.fhir;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.component.fhir.internal.FhirApiCollection;
import org.apache.camel.component.fhir.internal.FhirApiName;
import org.apache.camel.component.fhir.internal.FhirHelper;
import org.apache.camel.spi.Metadata;
import org.apache.camel.util.component.AbstractApiComponent;

/**
 * Represents the component that manages {@link FhirEndpoint}.
 */
public class FhirComponent extends AbstractApiComponent<FhirApiName, FhirConfiguration, FhirApiCollection> {

    @Metadata(label = "advanced")
    private IGenericClient client;
    // TODO: allow to inject client factory, FhirContext?

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
        endpointConfiguration.setApiName(apiName);
        endpointConfiguration.setMethodName(methodName);
        return new FhirEndpoint(uri, this, apiName, methodName, endpointConfiguration);
    }

    public IGenericClient getClient(FhirConfiguration endpointConfiguration) {
        final IGenericClient result;
        if (endpointConfiguration.equals(this.configuration)) {
            synchronized (this) {
                if (client == null) {
                    client = FhirHelper.createClient(this.configuration, getCamelContext());
                }
            }
            result = client;
        } else {
            result = FhirHelper.createClient(endpointConfiguration, getCamelContext());
        }
        return result;
    }
}

