package org.apache.camel.component.fhir;

import java.util.List;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.CacheControlDirective;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.api.SummaryEnum;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;
import org.hl7.fhir.instance.model.api.IBaseResource;

/**
 * Component configuration for FHIR component.
 */
@UriParams
public class FhirConfiguration {

   @UriParam
   private String fhirBase;

   @UriParam
   private IGenericClient genericClient;

   @UriParam
   private FhirContext fhirContext;

   @UriParam
   private String fhirVersion;

   @UriParam
   @Metadata(description = "Sets the <code>Cache-Control</code> header value, which advises the server (or any cache in front of it) how to behave in terms of cached requests")
   private CacheControlDirective cacheControlDirective;

   @UriParam
   @Metadata(description ="Request that the server return subsetted resources, containing only the elements specified in the given parameters."
           + " For example: <code>subsetElements(\"name\", \"identifier\")</code> requests that the server only return"
           + " the \"name\" and \"identifier\" fields in the returned resource, and omit any others.")
   private String[] subsetElements;

   @UriParam
   private EncodingEnum encodingEnum;

   @UriParam
   private boolean encodeJson;

   @UriParam
   private boolean encodeXml;

   @UriParam
   @Metadata(description ="Explicitly specify a custom structure type to attempt to use when parsing the response. This"
           + " is useful for invocations where the response is a Bundle/Parameters containing nested resources,"
           + " and you want to use specific custom structures for those nested resources.")
   private Class<? extends IBaseResource> preferedResponseType;

   @UriParam
   @Metadata(description ="Explicitly specify a list of custom structure types to attempt to use (in order from most to"
           + " least preferred) when parsing the response. This"
           + " is useful for invocations where the response is a Bundle/Parameters containing nested resources,"
           + " and you want to use specific custom structures for those nested resources.")
   private List<Class<? extends IBaseResource>> preferedResponseTypes;

   @UriParam
   private boolean prettyPrint;

   @UriParam
   @Metadata(description ="Request that the server modify the response using the <code>_summary</code> param")
   private SummaryEnum summaryEnum;

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

   public CacheControlDirective getCacheControlDirective() {
      return cacheControlDirective;
   }

   public void setCacheControlDirective(CacheControlDirective cacheControlDirective) {
      this.cacheControlDirective = cacheControlDirective;
   }

   public String[] getSubsetElements() {
      return subsetElements;
   }

   public void setSubsetElements(String[] subsetElements) {
      this.subsetElements = subsetElements;
   }

   public EncodingEnum getEncodingEnum() {
      return encodingEnum;
   }

   public void setEncodingEnum(EncodingEnum encodingEnum) {
      this.encodingEnum = encodingEnum;
   }

   public boolean isEncodeJson() {
      return encodeJson;
   }

   public void setEncodeJson(boolean encodeJson) {
      this.encodeJson = encodeJson;
   }

   public boolean isEncodeXml() {
      return encodeXml;
   }

   public void setEncodeXml(boolean encodeXml) {
      this.encodeXml = encodeXml;
   }

   public Class<? extends IBaseResource> getPreferedResponseType() {
      return preferedResponseType;
   }

   public void setPreferedResponseType(Class<? extends IBaseResource> preferedResponseType) {
      this.preferedResponseType = preferedResponseType;
   }

   public List<Class<? extends IBaseResource>> getPreferedResponseTypes() {
      return preferedResponseTypes;
   }

   public void setPreferedResponseTypes(List<Class<? extends IBaseResource>> preferedResponseTypes) {
      this.preferedResponseTypes = preferedResponseTypes;
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
}
