package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseOperationOutcome;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IIdType;

/**
 * Sample API used by fhir Component whose method signatures are read from File.
 */
public class FhirDelete {

    private final IGenericClient client;

    public FhirDelete(IGenericClient client) {
        this.client = client;
    }


//    public IBaseOperationOutcome resourceById(IIdType theId) {
//        return client.delete().resourceById(theId).execute();
//
//    }

    public IBaseOperationOutcome resource(IBaseResource theResource) {
        return client.delete().resource(theResource).execute();
    }

    public IBaseOperationOutcome resourceConditionalByUrl(String theSearchUrl) {
        return client.delete().resourceConditionalByUrl(theSearchUrl).execute();
    }

    //    IDeleteTyped resourceById(String theResourceType, String theLogicalId);
//
//
//    /**
//     * Specifies that the delete should be performed as a conditional delete
//     * against a given search URL.
//     *
//     * @param theSearchUrl The search URL to use. The format of this URL should be of the form <code>[ResourceType]?[Parameters]</code>,
//     *                     for example: <code>Patient?name=Smith&amp;identifier=13.2.4.11.4%7C847366</code>
//     * @since HAPI 0.9 / FHIR DSTU 2
//     */
//    IDeleteTyped resourceConditionalByUrl(String theSearchUrl);
}
