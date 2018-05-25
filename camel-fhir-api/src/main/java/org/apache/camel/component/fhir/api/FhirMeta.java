package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseMetaType;
import org.hl7.fhir.instance.model.api.IIdType;

/**
 * API for the "meta" operations, which can be used to get, add and remove tags and other
 * Meta elements from a resource or across the server.
 */
public class FhirMeta {

    private final IGenericClient client;

    public FhirMeta(IGenericClient client) {
        this.client = client;
    }

    /**
     * Fetch the current metadata from the whole Server
     *
     * @param theMetaType The type of the meta datatype for the given FHIR model version (should be <code>MetaDt.class</code> or <code>MetaType.class</code>)
     */
    public <T extends IBaseMetaType> T getFromServer(Class<T> theMetaType) {
        return client.meta().get(theMetaType).fromServer().execute();
    }

    /**
     * Fetch the current metadata from a specific resource
     */
    public <T extends IBaseMetaType> T getFromResource(Class<T> theMetaType, IIdType theId) {
        return client.meta().get(theMetaType).fromResource(theId).execute();
    }

    /**
     * Fetch the current metadata from a specific type
     */
    public <T extends IBaseMetaType> T getFromType(Class<T> theMetaType, String theResourceName) {
        return client.meta().get(theMetaType).fromType(theResourceName).execute();
    }

    /**
     * Add the elements in the given metadata to the already existing set (do not remove any)
     */
    public <T extends IBaseMetaType> T add(T theMeta, IIdType theId) {
        return client.meta().add().onResource(theId).meta(theMeta).execute();
    }

    /**
     * Delete the elements in the given metadata from the given id
     */
    public <T extends IBaseMetaType> T delete(T theMeta, IIdType theId) {
        return client.meta().delete().onResource(theId).meta(theMeta).execute();
    }
}
