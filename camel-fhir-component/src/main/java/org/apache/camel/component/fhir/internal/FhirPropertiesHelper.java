package org.apache.camel.component.fhir.internal;

import org.apache.camel.util.component.ApiMethodPropertiesHelper;

import org.apache.camel.component.fhir.FhirConfiguration;

/**
 * Singleton {@link ApiMethodPropertiesHelper} for fhir component.
 */
public final class FhirPropertiesHelper extends ApiMethodPropertiesHelper<FhirConfiguration> {

    private static FhirPropertiesHelper helper;

    private FhirPropertiesHelper() {
        super(FhirConfiguration.class, FhirConstants.PROPERTY_PREFIX);
    }

    public static synchronized FhirPropertiesHelper getHelper() {
        if (helper == null) {
            helper = new FhirPropertiesHelper();
        }
        return helper;
    }
}
