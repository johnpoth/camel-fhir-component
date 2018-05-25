package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.PreferReturnEnum;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IPatchExecutable;
import org.hl7.fhir.instance.model.api.IIdType;

/**
 * API for the "patch" operation, which performs a logical patch on a server resource
 */
public class FhirPatch {

    private final IGenericClient client;

    public FhirPatch(IGenericClient client) {
        this.client = client;
    }

    /**
     * Specifies that the update should be performed as a conditional create
     * against a given search URL.
     *
     * @param url The search URL to use. The format of this URL should be of the form <code>[ResourceType]?[Parameters]</code>,
     *                     for example: <code>Patient?name=Smith&amp;identifier=13.2.4.11.4%7C847366</code>
     * @param patchBody The body of the patch document serialized in either XML or JSON which conforms to
     *                     http://jsonpatch.com/ or http://tools.ietf.org/html/rfc5261
     * @param preferReturnEnum Add a <code>Prefer</code> header to the request, which requests that the server include
     *                         or suppress the resource body as a part of the result. If a resource is returned by the server
     *                         it will be parsed an accessible to the client via {@link MethodOutcome#getResource()}
     *
     * @return
     */
    public MethodOutcome patchByUrl(String patchBody, String url, PreferReturnEnum preferReturnEnum) {
        IPatchExecutable patchExecutable = client.patch().withBody(patchBody).conditionalByUrl(url);
        if (preferReturnEnum != null) {
            patchExecutable = patchExecutable.prefer(preferReturnEnum);
        }
        return patchExecutable.execute();
    }

    /**
     * Applies the patch to the given resource ID
     *
     * @param patchBody The body of the patch document serialized in either XML or JSON which conforms to
     *                     http://jsonpatch.com/ or http://tools.ietf.org/html/rfc5261
     * @param iId The resource ID to patch
     * @param preferReturnEnum Add a <code>Prefer</code> header to the request, which requests that the server include
     *                         or suppress the resource body as a part of the result. If a resource is returned by the server
     *                         it will be parsed an accessible to the client via {@link MethodOutcome#getResource()}
     * @return
     */
    public MethodOutcome patchById(String patchBody, IIdType iId, PreferReturnEnum preferReturnEnum) {
        IPatchExecutable patchExecutable = client.patch().withBody(patchBody).withId(iId);
        if (preferReturnEnum != null) {
            patchExecutable = patchExecutable.prefer(preferReturnEnum);
        }
        return patchExecutable.execute();
    }

    /**
     * Applies the patch to the given resource ID
     *
     * @param patchBody The body of the patch document serialized in either XML or JSON which conforms to
     *                     http://jsonpatch.com/ or http://tools.ietf.org/html/rfc5261
     * @param sId The resource ID to patch
     * @param preferReturnEnum Add a <code>Prefer</code> header to the request, which requests that the server include
     *                         or suppress the resource body as a part of the result. If a resource is returned by the server
     *                         it will be parsed an accessible to the client via {@link MethodOutcome#getResource()}
     * @return
     */
    public MethodOutcome patchBySId(String patchBody, String sId, PreferReturnEnum preferReturnEnum) {
        IPatchExecutable patchExecutable = client.patch().withBody(patchBody).withId(sId);
        if (preferReturnEnum != null) {
            patchExecutable = patchExecutable.prefer(preferReturnEnum);
        }
        return patchExecutable.execute();
    }

}
