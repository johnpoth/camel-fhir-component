package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.PreferReturnEnum;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.ICreateTyped;
import org.hl7.fhir.instance.model.api.IBaseResource;

/**
 * API for the "create" operation, which creates a new resource instance on the server
 */
public class FhirCreate {

    private final IGenericClient client;

    public FhirCreate(IGenericClient client) {
        this.client = client;
    }

    /**
     * Creates a {@link IBaseResource} on the server
     *
     * @param resource The resource to create
     * @param url The search URL to use. The format of this URL should be of the form <code>[ResourceType]?[Parameters]</code>,
     *                     for example: <code>Patient?name=Smith&amp;identifier=13.2.4.11.4%7C847366</code>, may be null
     * @param preferReturn Add a <code>Prefer</code> header to the request, which requests that the server include
     *                  or suppress the resource body as a part of the result. If a resource is returned by the server
     *                  it will be parsed an accessible to the client via {@link MethodOutcome#getResource()}, may be null
     * @return The {@link MethodOutcome}
     */
    public MethodOutcome resource(IBaseResource resource, String url, PreferReturnEnum preferReturn) {
        ICreateTyped createTyped = client.create().resource(resource);
        createTyped = processOptionalParams(url, preferReturn, createTyped);
        return createTyped.execute();
    }

    /**
     * Creates a {@link IBaseResource} on the server
     *
     * @param resourceAsString The resource to create
     * @param url The search URL to use. The format of this URL should be of the form <code>[ResourceType]?[Parameters]</code>,
     *                     for example: <code>Patient?name=Smith&amp;identifier=13.2.4.11.4%7C847366</code>, may be null
     * @param preferReturn Add a <code>Prefer</code> header to the request, which requests that the server include
     *                  or suppress the resource body as a part of the result. If a resource is returned by the server
     *                  it will be parsed an accessible to the client via {@link MethodOutcome#getResource()}, may be null
     * @return The {@link MethodOutcome}
     */
    public MethodOutcome resource(String resourceAsString, String url, PreferReturnEnum preferReturn) {
        ICreateTyped createTyped = client.create().resource(resourceAsString);
        createTyped = processOptionalParams(url, preferReturn, createTyped);
        return createTyped.execute();
    }

    private ICreateTyped processOptionalParams(String theSearchUrl, PreferReturnEnum theReturn, ICreateTyped createTyped) {
        if (theSearchUrl != null) {
            createTyped = createTyped.conditionalByUrl(theSearchUrl);
        }
        if (theReturn != null) {
            createTyped = createTyped.prefer(theReturn);
        }
        return createTyped;
    }
}
