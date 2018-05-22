package org.apache.camel.component.fhir.api;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseMetaType;
import org.hl7.fhir.instance.model.api.IIdType;

/**
 * Sample API used by fhir Component whose method signatures are read from File.
 */
public class FhirMeta {

    private final IGenericClient client;

    public FhirMeta(IGenericClient client) {
        this.client = client;
    }

    public <T extends IBaseMetaType> T getFromServer(Class<T> theMetaType) {
        return client.meta().get(theMetaType).fromServer().execute();
    }

    public <T extends IBaseMetaType> T getFromResource(Class<T> theMetaType, IIdType theId) {
        return client.meta().get(theMetaType).fromResource(theId).execute();
    }


//    /**
//     * Fetch the current metadata
//     *
//     * @param theMetaType The type of the meta datatype for the given FHIR model version (should be <code>MetaDt.class</code> or <code>MetaType.class</code>)
//     */
//    <T extends IBaseMetaType> IMetaGetUnsourced<T> get(Class<T> theMetaType);
//
//    /**
//     * Add the elements in the given metadata to the already existing set (do not remove any)
//     */
//    IMetaAddOrDeleteUnsourced add();
//
//    /**
//     * Delete the elements in the given metadata to the
//     */
//    IMetaAddOrDeleteUnsourced delete();

}
