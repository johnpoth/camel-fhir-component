package org.apache.camel.component.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.api.SummaryEnum;
import org.apache.camel.component.fhir.internal.FhirApiName;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;
import org.apache.camel.spi.UriPath;

/**
 * Component configuration for FHIR component.
 */
@UriParams
public class FhirConfiguration {

   @UriPath
   @Metadata(required = "true")
   private FhirApiName apiName;
   @UriPath @Metadata(required = "true")
   private String methodName;

   @UriParam(description = "The FHIR server base URL")
   private String url;

   @UriParam(description ="The FHIR fhirVersion e.g 'DSTU3', 'R4'. See ca.uhn.fhir.context.FhirVersionEnum")
   private String fhirVersion;

   @UriParam(description ="FhirContext is an expensive object to create. To avoid creating multiple instances,"
           + " it can be set directly.")
   private FhirContext fhirContext;

   @UriParam(description ="Pretty print all request")
   private boolean prettyPrint;

   @UriParam(description ="Request that the server modify the response using the <code>_summary</code> param")
   private SummaryEnum summaryEnum;

   @UriParam(description ="Encoding to use for all request")
   private EncodingEnum encodingEnum;

   @UriParam(description ="Force conformance check")
   private boolean forceConformanceCheck;

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getFhirVersion() {
      return fhirVersion;
   }

   public void setFhirVersion(String fhirVersion) {
      this.fhirVersion = fhirVersion;
   }

   public EncodingEnum getEncodingEnum() {
      return encodingEnum;
   }

   public void setEncodingEnum(EncodingEnum encodingEnum) {
      this.encodingEnum = encodingEnum;
   }


   public boolean isPrettyPrint() {
      return prettyPrint;
   }

   public void setPrettyPrint(boolean prettyPrint) {
      this.prettyPrint = prettyPrint;
   }

   public SummaryEnum getSummaryEnum() {
      return summaryEnum;
   }

   public void setSummaryEnum(SummaryEnum summaryEnum) {
      this.summaryEnum = summaryEnum;
   }

   public FhirApiName getApiName() {
      return apiName;
   }

   /**
    * What kind of operation to perform
    */
   public void setApiName(FhirApiName apiName) {
      this.apiName = apiName;
   }

   public String getMethodName() {
      return methodName;
   }

   /**
    * What sub operation to use for the selected operation
    */
   public void setMethodName(String methodName) {
      this.methodName = methodName;
   }

   public FhirContext getFhirContext() {
      return fhirContext;
   }

   public void setFhirContext(FhirContext fhirContext) {
      this.fhirContext = fhirContext;
   }

   public boolean isForceConformanceCheck() {
      return forceConformanceCheck;
   }

   public void setForceConformanceCheck(boolean forceConformanceCheck) {
      this.forceConformanceCheck = forceConformanceCheck;
   }
}
