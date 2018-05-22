package org.apache.camel.component.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.apache.camel.spi.UriParams;

/**
 * Component configuration for fhir component.
 */
@UriParams
public class FhirConfiguration {

   private String fhirBase;
   private IGenericClient genericClient;
   private FhirContext fhirContext;
   private String fhirVersion;

   public String getFhirBase() {
      return fhirBase;
   }

   public void setFhirBase(String fhirBase) {
      this.fhirBase = fhirBase;
   }

   public IGenericClient getGenericClient() {
      return genericClient;
   }

   public void setGenericClient(IGenericClient genericClient) {
      this.genericClient = genericClient;
   }

   public FhirContext getFhirContext() {
      return fhirContext;
   }

   public void setFhirContext(FhirContext fhirContext) {
      this.fhirContext = fhirContext;
   }

   public String getFhirVersion() {
      return fhirVersion;
   }

   public void setFhirVersion(String fhirVersion) {
      this.fhirVersion = fhirVersion;
   }
}
