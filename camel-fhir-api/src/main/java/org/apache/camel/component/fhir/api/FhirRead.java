package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IReadExecutable;
import ca.uhn.fhir.rest.gclient.IReadIfNoneMatch;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IIdType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * API method for "read" operations
 */
public class FhirRead {

    private static final Logger LOG = LoggerFactory.getLogger(FhirRead.class);
    private final IGenericClient client;


    public FhirRead(IGenericClient client) {
        this.client = client;
    }

    public <T extends IBaseResource> T resource(Class<T> resourceType, String sid, String version, Boolean returnNull, T returnResource, Boolean throwError) {
        IReadExecutable<T> readExecutable = client.read().resource(resourceType).withIdAndVersion()withId(sid);
        if (version != null) {
            IReadIfNoneMatch<T> tiReadIfNoneMatch = readExecutable.ifVersionMatches(version);
            if (returnNull != null  && returnNull) {
                return tiReadIfNoneMatch.returnNull().execute();
            } else if (returnResource !=null) {
                return tiReadIfNoneMatch.returnNull().execute();
            } else if (throwError != null) {
                return tiReadIfNoneMatch.throwNotModifiedException().execute();
            }
            LOG.warn("No operation was specified with the If-None-Match header, ignoring");
        }
        return readExecutable.execute();
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
