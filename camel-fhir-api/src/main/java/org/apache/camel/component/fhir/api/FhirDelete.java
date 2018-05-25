package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IDeleteTyped;
import org.hl7.fhir.instance.model.api.IBaseOperationOutcome;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IIdType;

/**
 * API for the "delete" operation, which performs a logical delete on a server resource.
 */
public class FhirDelete {

    private final IGenericClient client;

    public FhirDelete(IGenericClient client) {
        this.client = client;
    }

    /**
     * Deletes the given resource
     *
     * @param theResource the {@link IBaseResource} to delete
     * @return the {@link IBaseOperationOutcome}
     */
    public IBaseOperationOutcome resource(IBaseResource theResource) {
        return client.delete().resource(theResource).execute();
    }

    /**
     * * Deletes the given resource by {@link IIdType}
     *
     * @param resourceId the {@link IIdType} referencing the resource
     * @return the {@link IBaseOperationOutcome}
     */
    public IBaseOperationOutcome resourceById(IIdType resourceId) {
        return client.delete().resourceById(resourceId).execute();
    }

    /**
     * Deletes the resource by resource type e.g "Patient" and it's id
     * @param theResourceType the resource type e.g "Patient"
     * @param theLogicalId it's id
     * @return the {@link IBaseOperationOutcome}
     */
    public IBaseOperationOutcome resourceById(String theResourceType, String theLogicalId) {
        return client.delete().resourceById(theResourceType, theLogicalId).execute();
    }

    /**
     * Specifies that the delete should be performed as a conditional delete against a given search URL.
     * @param theSearchUrl The search URL to use. The format of this URL should be of the form
     *                     <code>[ResourceType]?[Parameters]</code>,
     *                     for example: <code>Patient?name=Smith&amp;identifier=13.2.4.11.4%7C847366</code>
     * @return the {@link IBaseOperationOutcome}
     */
    public IBaseOperationOutcome resourceConditionalByUrl(String theSearchUrl) {
        return client.delete().resourceConditionalByUrl(theSearchUrl).execute();
    }

}
