package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseBundle;

/**
 * API to search for resources matching a given set of criteria. Searching is a very powerful
 * feature in FHIR with many features for specifying exactly what should be searched for
 * and how it should be returned. See the <a href="http://www.hl7.org/fhir/search.html">specification on search</a>
 * for more information.
 */
public class FhirSearch {

    private final IGenericClient client;

    public FhirSearch(IGenericClient client) {
        this.client = client;
    }


    /**
     * Perform a search directly by URL.
     *
     * @param url The URL to search for. Note that this URL may be complete (e.g. "http://example.com/base/Patient?name=foo") in which case the client's base URL will be ignored. Or it can be relative
     *            (e.g. "Patient?name=foo") in which case the client's base URL will be used.
     */
    public IBaseBundle searchByUrl(String url) {
        return client.search().byUrl(url).execute();
    }

}
