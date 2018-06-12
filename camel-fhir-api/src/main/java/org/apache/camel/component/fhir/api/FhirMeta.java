package org.apache.camel.component.fhir.api;

import java.util.Map;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IClientExecutable;
import ca.uhn.fhir.rest.gclient.IMeta;
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
     * @param extraParameters see {@link ExtraParameters} for a full list of parameters that can be passed, may be NULL
     */
    public <T extends IBaseMetaType> T getFromServer(Class<T> metaType, Map<ExtraParameters, Object> extraParameters) {
        IClientExecutable<IClientExecutable<?, T>, T> clientExecutable = client.meta().get(metaType).fromServer();
        ExtraParameters.process(extraParameters, clientExecutable);
        return clientExecutable.execute();
    }

    /**
     * Fetch the current metadata from a specific resource
     * @param extraParameters see {@link ExtraParameters} for a full list of parameters that can be passed, may be NULL
     */
    public <T extends IBaseMetaType> T getFromResource(Class<T> metaType, IIdType id, Map<ExtraParameters, Object> extraParameters) {
        IClientExecutable<IClientExecutable<?, T>, T> clientExecutable = client.meta().get(metaType).fromResource(id);
        ExtraParameters.process(extraParameters, clientExecutable);
        return clientExecutable.execute();
    }

    /**
     * Fetch the current metadata from a specific type
     * @param extraParameters see {@link ExtraParameters} for a full list of parameters that can be passed, may be NULL
     */
    public <T extends IBaseMetaType> T getFromType(Class<T> metaType, String resourceType, Map<ExtraParameters, Object> extraParameters) {
        IClientExecutable<IClientExecutable<?, T>, T> clientExecutable = client.meta().get(metaType).fromType(resourceType);
        ExtraParameters.process(extraParameters, clientExecutable);
        return clientExecutable.execute();
    }

    /**
     * Add the elements in the given metadata to the already existing set (do not remove any)
     * @param extraParameters see {@link ExtraParameters} for a full list of parameters that can be passed, may be NULL
     */
    public <T extends IBaseMetaType> T add(T meta, IIdType id, Map<ExtraParameters, Object> extraParameters) {
        IClientExecutable<IClientExecutable<?, T>, T> clientExecutable = client.meta().add().onResource(id).meta(meta);
        ExtraParameters.process(extraParameters, clientExecutable);
        return clientExecutable.execute();
    }

    /**
     * Delete the elements in the given metadata from the given id
     * @param extraParameters see {@link ExtraParameters} for a full list of parameters that can be passed, may be NULL
     */
    public <T extends IBaseMetaType> T delete(T meta, IIdType id, Map<ExtraParameters, Object> extraParameters) {
        IClientExecutable<IClientExecutable<?, T>, T> clientExecutable = client.meta().delete().onResource(id).meta(meta);
        ExtraParameters.process(extraParameters, clientExecutable);
        return clientExecutable.execute();
    }
}
