package org.apache.camel.component.fhir.api;

import java.util.Map;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IGetPageTyped;
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
     * @param extraParameters see {@link ExtraParameters} for a full list of parameters that can be passed, may be NULL
     */
    public <T extends IBaseBundle> T next(T bundle, Map<ExtraParameters, Object> extraParameters) {
        IGetPageTyped<T> getPageTyped = client.loadPage().next(bundle);
        ExtraParameters.process(extraParameters, getPageTyped);
        return getPageTyped.execute();
    }

    /**
     * Load the previous page of results using the link with relation "prev" in the bundle. This
     * method accepts a DSTU2+ Bundle resource
     * @param extraParameters see {@link ExtraParameters} for a full list of parameters that can be passed, may be NULL
     */
    public <T extends IBaseBundle> T previous(T bundle, Map<ExtraParameters, Object> extraParameters) {
        IGetPageTyped<T> getPageTyped = client.loadPage().previous(bundle);
        ExtraParameters.process(extraParameters, getPageTyped);
        return getPageTyped.execute();
    }

    /**
     * Load a page of results using the given URL and bundle type and return a DSTU1 Atom bundle
     * @param extraParameters see {@link ExtraParameters} for a full list of parameters that can be passed, may be NULL
     */
    public <T extends IBaseBundle> T byUrl(String url, Class<T> returnType, Map<ExtraParameters, Object> extraParameters) {
        IGetPageTyped<T> getPageTyped = client.loadPage().byUrl(url).andReturnBundle(returnType);
        ExtraParameters.process(extraParameters, getPageTyped);
        return getPageTyped.execute();
    }


}
