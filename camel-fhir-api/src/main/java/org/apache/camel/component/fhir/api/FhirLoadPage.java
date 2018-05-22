package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseBundle;

/**
 * Sample API used by fhir Component whose method signatures are read from File.
 */
public class FhirLoadPage {

    private final IGenericClient client;

    public FhirLoadPage(IGenericClient client) {
        this.client = client;
    }

    public <T extends IBaseBundle> T next(T theBundle) {
        return client.loadPage().next(theBundle).execute();
    }

//    /**
//     * Load the next page of results using the link with relation "next" in the bundle. This
//     * method accepts a DSTU2 Bundle resource
//     *
//     * @since 1.1
//     */
//    <T extends IBaseBundle> IGetPageTyped<T> next(T theBundle);
//
//    /**
//     * Load the previous page of results using the link with relation "prev" in the bundle. This
//     * method accepts a DSTU2+ Bundle resource
//     *
//     * @since 1.1
//     */
//    <T extends IBaseBundle> IGetPageTyped<T> previous(T theBundle);
//
//    /**
//     * Load a page of results using the a given URL and return a DSTU1 Atom bundle
//     */
//    IGetPageUntyped byUrl(String thePageUrl);

}
