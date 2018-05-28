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
     * @param metaType The type of the meta datatype for the given FHIR model version (should be <code>MetaDt.class</code> or <code>MetaType.class</code>)
     */
    public <T extends IBaseMetaType> T getFromServer(Class<T> metaType) {
        return client.meta().get(metaType).fromServer().execute();
    }

    /**
     * Fetch the current metadata from a specific resource
     */
    public <T extends IBaseMetaType> T getFromResource(Class<T> metaType, IIdType id) {
        return client.meta().get(metaType).fromResource(id).execute();
    }

    /**
     * Fetch the current metadata from a specific type
     */
    public <T extends IBaseMetaType> T getFromType(Class<T> metaType, String resourceType) {
        return client.meta().get(metaType).fromType(resourceType).execute();
    }

    /**
     * Add the elements in the given metadata to the already existing set (do not remove any)
     */
    public <T extends IBaseMetaType> T add(T meta, IIdType id) {
        return client.meta().add().onResource(id).meta(meta).execute();
    }

    /**
     * Delete the elements in the given metadata from the given id
     */
    public <T extends IBaseMetaType> T delete(T meta, IIdType id) {
        return client.meta().delete().onResource(id).meta(meta).execute();
    }
}
