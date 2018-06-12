package org.apache.camel.component.fhir.api;

import java.util.Map;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.PreferReturnEnum;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IUpdateExecutable;
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

    public MethodOutcome resource(IBaseResource resource, IIdType id, PreferReturnEnum preferReturn, Map<ExtraParameters, Object> extraParameters){
        IUpdateExecutable updateExecutable = client.update().resource(resource).withId(id);
        ExtraParameters.process(extraParameters, updateExecutable);
        return processOptionalParam(preferReturn, updateExecutable);
    }

    public MethodOutcome resource(String resourceAsString, IIdType id, PreferReturnEnum preferReturn, Map<ExtraParameters, Object> extraParameters){
        IUpdateExecutable updateExecutable = client.update().resource(resourceAsString).withId(id);
        ExtraParameters.process(extraParameters, updateExecutable);
        return processOptionalParam(preferReturn, updateExecutable);
    }

    public MethodOutcome resource(IBaseResource resource, String stringId, PreferReturnEnum preferReturn, Map<ExtraParameters, Object> extraParameters){
        IUpdateExecutable updateExecutable = client.update().resource(resource).withId(stringId);
        ExtraParameters.process(extraParameters, updateExecutable);
        return processOptionalParam(preferReturn, updateExecutable);
    }

    public MethodOutcome resource(String resourceAsString, String stringId, PreferReturnEnum preferReturn, Map<ExtraParameters, Object> extraParameters){
        IUpdateExecutable updateExecutable = client.update().resource(resourceAsString).withId(stringId);
        ExtraParameters.process(extraParameters, updateExecutable);
        return processOptionalParam(preferReturn, updateExecutable);
    }

    public MethodOutcome resourceBySearchUrl(IBaseResource resource, String url, PreferReturnEnum preferReturn, Map<ExtraParameters, Object> extraParameters){
        IUpdateExecutable updateExecutable = client.update().resource(resource).conditionalByUrl(url);
        ExtraParameters.process(extraParameters, updateExecutable);
        return processOptionalParam(preferReturn, updateExecutable);
    }

    public MethodOutcome resourceBySearchUrl(String resourceAsString, String url, PreferReturnEnum preferReturn, Map<ExtraParameters, Object> extraParameters){
        IUpdateExecutable updateExecutable = client.update().resource(resourceAsString).conditionalByUrl(url);
        ExtraParameters.process(extraParameters, updateExecutable);
        return processOptionalParam(preferReturn, updateExecutable);
    }

    private MethodOutcome processOptionalParam(PreferReturnEnum preferReturn, IUpdateExecutable updateExecutable) {
        if (preferReturn != null) {
            return updateExecutable.prefer(preferReturn).execute();
        }
        return updateExecutable.execute();
    }
}
