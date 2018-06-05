package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.client.api.IGenericClient;
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
     * @param resource the {@link IBaseResource} to delete
     * @return the {@link IBaseOperationOutcome}
     */
    public IBaseOperationOutcome resource(IBaseResource resource) {
        return client.delete().resource(resource).execute();
    }

    /**
     * * Deletes the given resource by {@link IIdType}
     *
     * @param id the {@link IIdType} referencing the resource
     * @return the {@link IBaseOperationOutcome}
     */
    public IBaseOperationOutcome resourceById(IIdType id) {
        return client.delete().resourceById(id).execute();
    }

    /**
     * Deletes the resource by resource type e.g "Patient" and it's id
     * @param type the resource type e.g "Patient"
     * @param stringId it's id
     * @return the {@link IBaseOperationOutcome}
     */
    public IBaseOperationOutcome resourceById(String type, String stringId) {
        return client.delete().resourceById(type, stringId).execute();
    }

    /**
     * Specifies that the delete should be performed as a conditional delete against a given search URL.
     * @param url The search URL to use. The format of this URL should be of the form
     *                     <code>[ResourceType]?[Parameters]</code>,
     *                     for example: <code>Patient?name=Smith&amp;identifier=13.2.4.11.4%7C847366</code>
     * @return the {@link IBaseOperationOutcome}
     */
    public IBaseOperationOutcome resourceConditionalByUrl(String url) {
        return client.delete().resourceConditionalByUrl(url).execute();
    }

}
