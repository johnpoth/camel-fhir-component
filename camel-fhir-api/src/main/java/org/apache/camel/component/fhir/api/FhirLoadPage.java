package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseBundle;

/**
 * API that Loads the previous/next bundle of resources from a paged set, using the link specified in the "link type=next" tag within the atom bundle.
 */
public class FhirLoadPage {

    private final IGenericClient client;

    public FhirLoadPage(IGenericClient client) {
       this.client = client;
    }

    /**
     * Load the next page of results using the link with relation "next" in the bundle. This
     * method accepts a DSTU2 Bundle resource
     */
    public <T extends IBaseBundle> T next(T theBundle) {
        return client.loadPage().next(theBundle).execute();
    }

    /**
     * Load the previous page of results using the link with relation "prev" in the bundle. This
     * method accepts a DSTU2+ Bundle resource
     */
    public <T extends IBaseBundle> T previous(T theBundle) {
        return client.loadPage().previous(theBundle).execute();
    }

    /**
     * Load a page of results using the given URL and bundle type and return a DSTU1 Atom bundle
     */
    public <T extends IBaseBundle> T byUrl(String thePageUrl, Class<T> theBundleType) {
        return client.loadPage().byUrl(thePageUrl).andReturnBundle(theBundleType).execute();
    }


}
