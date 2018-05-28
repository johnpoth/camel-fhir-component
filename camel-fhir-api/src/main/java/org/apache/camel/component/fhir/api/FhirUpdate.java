package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.PreferReturnEnum;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IUpdateExecutable;
import ca.uhn.fhir.rest.gclient.IUpdateTyped;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IIdType;

/**
 * API for the "update" operation, which performs a logical delete on a server resource
 */
public class FhirUpdate {

    private final IGenericClient client;

    public FhirUpdate(IGenericClient client) {
        this.client = client;
    }

    public MethodOutcome resource(IBaseResource resource, IIdType id, String sId, String url, PreferReturnEnum preferReturn){
        IUpdateTyped updateTyped = client.update().resource(resource);
        return processOptionalParams(id, sId, url, preferReturn, updateTyped);
    }

    public MethodOutcome resource(String sResource, IIdType id, String sId, String url, PreferReturnEnum preferReturn){
        IUpdateTyped updateTyped = client.update().resource(sResource);
        return processOptionalParams(id, sId, url, preferReturn, updateTyped);
    }

    private MethodOutcome processOptionalParams(IIdType id, String sId, String url, PreferReturnEnum preferReturn, IUpdateTyped updateTyped) {
        if(url != null) {
            updateTyped = updateTyped.conditionalByUrl(url);
            if (preferReturn != null) {
                return updateTyped.prefer(preferReturn).execute();
            }
        }
        else if (id != null) {
            IUpdateExecutable updateExecutable = updateTyped.withId(id);
            if (preferReturn != null) {
               return updateExecutable.prefer(preferReturn).execute();
            }
        }
        else if (sId !=null){
            IUpdateExecutable updateExecutable = updateTyped.withId(id);
            if (preferReturn != null) {
                return updateExecutable.prefer(preferReturn).execute();
            }
        }
        return updateTyped.execute();
    }
}
