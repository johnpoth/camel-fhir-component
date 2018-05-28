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

    public IBaseResource  resourceWithId(Class<IBaseResource> resource, String id, String ifVersionMatches, Boolean returnNull, IBaseResource returnResource, Boolean throwError) {
        IReadExecutable<IBaseResource> readExecutable = client.read().resource(resource).withId(id);
        return processOptionalParams(ifVersionMatches, returnNull, returnResource, throwError, readExecutable);
    }

    public IBaseResource resourceWithId(Class<IBaseResource> resource, Long lId, String ifVersionMatches, Boolean returnNull, IBaseResource returnResource, Boolean throwError) {
        IReadExecutable<IBaseResource> readExecutable = client.read().resource(resource).withId(lId);
        return processOptionalParams(ifVersionMatches, returnNull, returnResource, throwError, readExecutable);
    }

    public IBaseResource resourceWithId(Class<IBaseResource> resource, IIdType idType, String ifVersionMatches, Boolean returnNull, IBaseResource returnResource, Boolean throwError) {
        IReadExecutable<IBaseResource> readExecutable = client.read().resource(resource).withId(idType);
        return processOptionalParams(ifVersionMatches, returnNull, returnResource, throwError, readExecutable);
    }

    public IBaseResource resourceWithIdAndVersion(Class<IBaseResource> resource, String id, String version, String ifVersionMatches, Boolean returnNull, IBaseResource returnResource, Boolean throwError) {
        IReadExecutable<IBaseResource> readExecutable = client.read().resource(resource).withIdAndVersion(id, version);
        return processOptionalParams(version, returnNull, returnResource, throwError, readExecutable);
    }

    public IBaseResource resourceWithUrl(Class<IBaseResource> resource, String url, String ifVersionMatches, Boolean returnNull, IBaseResource returnResource, Boolean throwError) {
        IReadExecutable<IBaseResource> readExecutable = client.read().resource(resource).withUrl(url);
        return processOptionalParams(ifVersionMatches, returnNull, returnResource, throwError, readExecutable);
    }

    public IBaseResource resourceWithUrl(Class<IBaseResource> resource, IIdType iUrl, String ifVersionMatches, Boolean returnNull, IBaseResource returnResource, Boolean throwError) {
        IReadExecutable<IBaseResource> readExecutable = client.read().resource(resource).withUrl(iUrl);
        return processOptionalParams(ifVersionMatches, returnNull, returnResource, throwError, readExecutable);
    }

    public IBaseResource resourceWithId(String resource, String id, String ifVersionMatches, Boolean returnNull, IBaseResource returnResource, Boolean throwError) {
        IReadExecutable<IBaseResource> readExecutable = client.read().resource(resource).withId(id);
        return processOptionalParams(ifVersionMatches, returnNull, returnResource, throwError, readExecutable);
    }

    public IBaseResource resourceWithId(String resourceClass, Long lId, String ifVersionMatches, Boolean returnNull, IBaseResource returnResource, Boolean throwError) {
        IReadExecutable<IBaseResource> readExecutable = client.read().resource(resourceClass).withId(lId);
        return processOptionalParams(ifVersionMatches, returnNull, returnResource, throwError, readExecutable);
    }

    public IBaseResource resourceWithId(String resourceClass, IIdType idType, String ifVersionMatches, Boolean returnNull, IBaseResource returnResource, Boolean throwError) {
        IReadExecutable<IBaseResource> readExecutable = client.read().resource(resourceClass).withId(idType);
        return processOptionalParams(ifVersionMatches, returnNull, returnResource, throwError, readExecutable);
    }

    public IBaseResource resourceWithIdAndVersion(String resourceClass, String id, String version, String ifVersionMatches, Boolean returnNull, IBaseResource returnResource, Boolean throwError) {
        IReadExecutable<IBaseResource> readExecutable = client.read().resource(resourceClass).withIdAndVersion(id, version);
        return processOptionalParams(version, returnNull, returnResource, throwError, readExecutable);
    }

    public IBaseResource resourceWithUrl(String resourceClass, String url, String ifVersionMatches, Boolean returnNull, IBaseResource returnResource, Boolean throwError) {
        IReadExecutable<IBaseResource> readExecutable = client.read().resource(resourceClass).withUrl(url);
        return processOptionalParams(ifVersionMatches, returnNull, returnResource, throwError, readExecutable);
    }

    public IBaseResource resourceWithUrl(String resourceClass, IIdType iUrl, String ifVersionMatches, Boolean returnNull, IBaseResource returnResource, Boolean throwError) {
        IReadExecutable<IBaseResource> readExecutable = client.read().resource(resourceClass).withUrl(iUrl);
        return processOptionalParams(ifVersionMatches, returnNull, returnResource, throwError, readExecutable);
    }

    private IBaseResource processOptionalParams(String ifVersionMatches, Boolean returnNull, IBaseResource returnResource, Boolean throwError, IReadExecutable<IBaseResource> readExecutable) {
        if (ifVersionMatches != null) {
            IReadIfNoneMatch<IBaseResource> tiReadIfNoneMatch = readExecutable.ifVersionMatches(ifVersionMatches);
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
}
