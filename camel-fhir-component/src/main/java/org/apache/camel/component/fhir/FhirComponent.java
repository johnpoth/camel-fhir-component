package org.apache.camel.component.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.FhirVersionEnum;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.api.SummaryEnum;
import ca.uhn.fhir.rest.client.apache.GZipContentInterceptor;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.BasicAuthInterceptor;
import ca.uhn.fhir.rest.client.interceptor.BearerTokenAuthInterceptor;
import ca.uhn.fhir.rest.client.interceptor.CookieInterceptor;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.spi.Metadata;
import org.apache.camel.util.component.AbstractApiComponent;

import org.apache.camel.component.fhir.internal.FhirApiCollection;
import org.apache.camel.component.fhir.internal.FhirApiName;

/**
 * Represents the component that manages {@link FhirEndpoint}.
 */
public class FhirComponent extends AbstractApiComponent<FhirApiName, FhirConfiguration, FhirApiCollection> {


    @Metadata(label = "advanced")
    private IGenericClient client;

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
                    client = createClient(this.configuration);
                }
            }
            result = client;
        } else {
            result = createClient(endpointConfiguration);
        }
        return result;
    }

    private IGenericClient createClient(FhirConfiguration endpointConfiguration) {
        FhirContext fhirContext = getFhirContext(endpointConfiguration);
        IGenericClient genericClient = fhirContext.newRestfulGenericClient(endpointConfiguration.getUrl());
        genericClient.setPrettyPrint(endpointConfiguration.isPrettyPrint());
        String encoding = endpointConfiguration.getEncoding();
        String summary = endpointConfiguration.getSummary();

        if (encoding != null)
            genericClient.setEncoding(EncodingEnum.valueOf(encoding));
        if (summary != null)
            genericClient.setSummary(SummaryEnum.valueOf(summary));
        if (endpointConfiguration.isForceConformanceCheck())
            genericClient.forceConformanceCheck();

        registerClientInterceptors(genericClient, endpointConfiguration);
        return genericClient;
    }

    private void registerClientInterceptors(IGenericClient genericClient, FhirConfiguration endpointConfiguration) {
        String username = endpointConfiguration.getUsername();
        String password = endpointConfiguration.getPassword();
        String accessToken = endpointConfiguration.getAccessToken();
        String sessionCookie = endpointConfiguration.getSessionCookie();
        if (username != null)
            genericClient.registerInterceptor(new BasicAuthInterceptor(username, password));
        if (accessToken != null)
            genericClient.registerInterceptor(new BearerTokenAuthInterceptor(accessToken));
        if (endpointConfiguration.isLog())
            genericClient.registerInterceptor(new LoggingInterceptor(true));
        if (endpointConfiguration.isCompress())
            genericClient.registerInterceptor(new GZipContentInterceptor());
        if (sessionCookie != null)
            genericClient.registerInterceptor(new CookieInterceptor(sessionCookie));
    }

    private FhirContext getFhirContext(FhirConfiguration endpointConfiguration) {
        FhirContext context = endpointConfiguration.getFhirContext();
        if (context != null)
            return context;
        if (endpointConfiguration.getUrl() == null || endpointConfiguration.getFhirVersion() == null)
            throw new RuntimeCamelException("The FHIR URL and version must be set!");

        FhirVersionEnum version = FhirVersionEnum.valueOf(endpointConfiguration.getFhirVersion());
        return new FhirContext(version);
    }
}

