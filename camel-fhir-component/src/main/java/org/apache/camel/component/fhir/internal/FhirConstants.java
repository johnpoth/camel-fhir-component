package org.apache.camel.component.fhir.internal;

/**
 * Constants for fhir component.
 */
public interface FhirConstants {

    // suffix for parameters when passed as exchange header properties
    String PROPERTY_PREFIX = "CamelFhir.";

    // thread profile name for this component
    String THREAD_PROFILE_NAME = "CamelFhir";
}
