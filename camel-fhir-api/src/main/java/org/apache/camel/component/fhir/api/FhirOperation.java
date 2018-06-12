package org.apache.camel.component.fhir.api;

import java.util.Map;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IBaseOn;
import ca.uhn.fhir.rest.gclient.IOperationProcessMsg;
import ca.uhn.fhir.rest.gclient.IOperationUnnamed;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IIdType;

/**
 * Sample API used by fhir Component whose method signatures are read from File.
 */
public class FhirOperation {

    private final IGenericClient client;

    public FhirOperation(IGenericClient client) {
        this.client = client;
    }

    public <R extends IBaseResource> R messageBundle(IBaseBundle theMsgBundle, Class<R> theResponseClass, Map<ExtraParameters, Object> extraParameters) {
        return client.operation().processMessage().setMessageBundle(theMsgBundle).synchronous(theResponseClass).execute();
    }

// extends IBaseOn<IOperationUnnamed>
//    /**
//     * This operation operates on a specific version of a resource
//     */
//    IOperationUnnamed onInstanceVersion(IIdType theId);
//
//    {
//
//        /**
//         * This operation is called <b><a href="https://www.hl7.org/fhir/messaging.html">$process-message</a></b> as defined by the FHIR
//         * specification.<br><br>
//         * Usage :<br>
//         * <code>
//         * <pre>
//         * Bundle response = client
//         * .operation()
//         * .processMessage()
//         * .synchronous(Bundle.class)
//         * .setResponseUrlParam("http://myserver/fhir")
//         * .setMessageBundle(msgBundle)
//         * .execute();
//         *
//         * //if you want to send an async message
//         *
//         * OperationOutcome response = client
//         * .operation()
//         * .processMessage()
//         * .asynchronous(OperationOutcome.class)
//         * .setResponseUrlParam("http://myserver/fhir")
//         * .setMessageBundle(msgBundle)
//         * .execute();
//         *
//         * </pre>
//         * </code>
//         *
//         * @see <a href="https://www.hl7.org/fhir/messaging.html">2.4 Messaging
//         * using FHIR Resources</a>
//         *
//         * @return An interface that defines the operation related to sending
//         * Messages to a Messaging Server
//         */
//        IOperationProcessMsg processMessage();

}
