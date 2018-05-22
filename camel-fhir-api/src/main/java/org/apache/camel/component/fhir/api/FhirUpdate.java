package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IUpdateExecutable;
import ca.uhn.fhir.rest.gclient.IUpdateTyped;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IIdType;

/**
 * Sample API used by fhir Component whose method signatures are read from File.
 */
public class FhirUpdate {

    private final IGenericClient client;

    public FhirUpdate(IGenericClient client) {
        this.client = client;
    }

    public MethodOutcome resource(IBaseResource theResource){
        return client.update().resource(theResource).execute();
    }

//    IUpdateExecutable withId(IIdType theId);
//
//    IUpdateExecutable withId(String theId);
//
//    /**
//     * Specifies that the update should be performed as a conditional create
//     * against a given search URL.
//     *
//     * @param theSearchUrl The search URL to use. The format of this URL should be of the form <code>[ResourceType]?[Parameters]</code>,
//     *                     for example: <code>Patient?name=Smith&amp;identifier=13.2.4.11.4%7C847366</code>
//     * @since HAPI 0.9 / FHIR DSTU 2
//     */
//    IUpdateTyped conditionalByUrl(String theSearchUrl);


}
