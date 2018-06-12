package org.apache.camel.component.fhir.api;

import java.util.Map;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IValidateUntyped;
import org.hl7.fhir.instance.model.api.IBaseResource;

/**
 * API for validating resources
 */
public class FhirValidate {

    private final IGenericClient client;

    public FhirValidate(IGenericClient client) {
        this.client = client;
    }

    public MethodOutcome resource(IBaseResource resource, Map<ExtraParameters, Object> extraParameters) {
        IValidateUntyped validateUntyped = client.validate().resource(resource);
        ExtraParameters.process(extraParameters, validateUntyped);
        return validateUntyped.execute();
    }

    public MethodOutcome resource(String resourceAsString, Map<ExtraParameters, Object> extraParameters) {
        IValidateUntyped validateUntyped = client.validate().resource(resourceAsString);
        ExtraParameters.process(extraParameters, validateUntyped);
        return validateUntyped.execute();
    }
}
