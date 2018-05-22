package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IPatchExecutable;
import org.hl7.fhir.instance.model.api.IIdType;

/**
 * Sample API used by fhir Component whose method signatures are read from File.
 */
public class FhirPatch {

    private final IGenericClient client;

    public FhirPatch(IGenericClient client) {
        this.client = client;
    }

    public MethodOutcome withBodyByUrl(String thePatchBody, String theSearchUrl) {
        return client.patch().withBody(thePatchBody).conditionalByUrl(theSearchUrl).execute();
    }

    public MethodOutcome withBodyById(String thePatchBody, IIdType iId) {
        return client.patch().withBody(thePatchBody).withId(iId).execute();
    }

    public MethodOutcome withBodyById(String thePatchBody, String sId) {
        return client.patch().withBody(thePatchBody).withId(sId).execute();
    }

//
//    /**
//     * Specifies that the update should be performed as a conditional create
//     * against a given search URL.
//     *
//     * @param theSearchUrl
//     *           The search URL to use. The format of this URL should be of the form <code>[ResourceType]?[Parameters]</code>,
//     *           for example: <code>Patient?name=Smith&amp;identifier=13.2.4.11.4%7C847366</code>
//     */
//    IPatchExecutable conditionalByUrl(String theSearchUrl);
//
//    /**
//     * The resource ID to patch
//     */
//    IPatchExecutable withId(IIdType theId);
//
//    /**
//     * The resource ID to patch
//     */
//    IPatchExecutable withId(String theId);


}
