package org.apache.camel.component.fhir;

import org.apache.camel.Processor;
import org.apache.camel.util.component.AbstractApiConsumer;

import org.apache.camel.component.fhir.internal.FhirApiName;

/**
 * The FHIR consumer.
 */
public class FhirConsumer extends AbstractApiConsumer<FhirApiName, FhirConfiguration> {

    public FhirConsumer(FhirEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
    }

}
