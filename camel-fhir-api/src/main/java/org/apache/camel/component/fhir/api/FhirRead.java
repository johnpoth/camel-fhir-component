package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IIdType;

/**
 * Sample API used by fhir Component whose method signatures are read from File.
 */
public class FhirRead {

    private final IGenericClient client;

    public FhirRead(IGenericClient client) {
        this.client = client;
    }

    public <T extends IBaseResource> T resource(Class<T> resourceType, String sid) {
        return client.read().resource(resourceType).withId(sid).execute();
    }

    public <T extends IBaseResource> T resource(Class<T> resourceType, String sid, String theVersion) {
        return null;
    }

    public <T extends IBaseResource> T resource(Class<T> resourceType, Long lid) {
        return null;
    }

    public <T extends IBaseResource> T resource(Class<T> resourceType, IIdType id) {
        return null;
    }

    public <T extends IBaseResource> T resourceUrl(Class<T> resourceType, IIdType iUrl) {
        return null;
    }

    public <T extends IBaseResource> T resourceUrl(Class<T> resourceType, String url) {
        return null;
    }

}
