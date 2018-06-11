package org.apache.camel.component.fhir;

import org.apache.camel.util.component.AbstractApiProducer;

import org.apache.camel.component.fhir.internal.FhirApiName;
import org.apache.camel.component.fhir.internal.FhirPropertiesHelper;

/**
 * The FHIR producer.
 */
public class FhirProducer extends AbstractApiProducer<FhirApiName, FhirConfiguration> {

    public FhirProducer(FhirEndpoint endpoint) {
        super(endpoint, FhirPropertiesHelper.getHelper());
    }
}
