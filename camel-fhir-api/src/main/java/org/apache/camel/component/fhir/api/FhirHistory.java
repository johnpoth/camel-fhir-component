package org.apache.camel.component.fhir.api;

import java.util.Date;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IHistoryTyped;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.instance.model.api.IPrimitiveType;

/**
 * Sample API used by fhir Component whose method signatures are read from File.
 */
public class FhirHistory {

    private final IGenericClient client;

    public FhirHistory(IGenericClient client) {
        this.client = client;
    }

    public <T extends IBaseBundle> T onServer(Class<T> theType, Integer count, Date theCutoff, IPrimitiveType<Date> cutoff) {
        IHistoryTyped<T> tiHistoryTyped = client.history().onServer().andReturnBundle(theType);
        if ( count != null) {
            tiHistoryTyped = tiHistoryTyped.count(count);
        }
        if (theCutoff != null) {
            tiHistoryTyped = tiHistoryTyped.since(theCutoff);
        }
        if (cutoff != null){
          tiHistoryTyped = tiHistoryTyped.since(cutoff);
        }
        return tiHistoryTyped.execute();
    }
//
//    /**
//     * Perform the operation across all versions of all resources of all types on the server
//     */
//    T onServer();
//
//    /**
//     * Perform the operation across all versions of all resources of the given type on the server
//     */
//    T onType(Class<? extends IBaseResource> theResourceType);
//
//    /**
//     * Perform the operation across all versions of a specific resource (by ID and type) on the server.
//     * Note that <code>theId</code> must be populated with both a resource type and a resource ID at
//     * a minimum.
//     *
//     * @throws IllegalArgumentException If <code>theId</code> does not contain at least a resource type and ID
//     */
//    T onInstance(IIdType theId);

}
