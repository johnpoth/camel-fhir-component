package org.apache.camel.component.fhir;

import ca.uhn.fhir.context.FhirContext;
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
   @UriPath
   @Metadata(required = "true")
   private String methodName;

   @UriParam(description = "The FHIR server base URL")
   private String url;

   @UriParam(description ="The FHIR Version to use", enums = "DSTU2, DSTU2_HL7ORG, DSTU2_1, DSTU3, R4")
   private String fhirVersion;

   @UriParam(description ="FhirContext is an expensive object to create. To avoid creating multiple instances,"
           + " it can be set directly.")
   private FhirContext fhirContext;

   @UriParam(description ="Pretty print all request")
   private boolean prettyPrint;

   @UriParam(description ="Request that the server modify the response using the <code>_summary</code> param", enums = "TRUE, FALSE, TEXT, DATA, COUNT")
   private String summary;

   @UriParam(description ="Encoding to use for all request", enums = "JSON, XML")
   private String encoding;

   @UriParam(description ="Force conformance check")
   private boolean forceConformanceCheck;

   @UriParam(description ="Username to use for basic authentication", label = "security", secret = true)
   private String username;

   @UriParam(description ="Username to use for basic authentication", label = "security", secret = true)
   private String password;

   @UriParam(description ="OAuth access token", label = "security", secret = true)
   private String accessToken;

   @UriParam(description ="Compresses outgoing (POST/PUT) contents to the GZIP format", label = "advanced")
   private boolean compress;

   @UriParam(description ="Will log every requests and responses")
   private boolean log;

   @UriParam(description ="HTTP session cookie to add to every request")
   private String sessionCookie;

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

   public String getEncoding() {
      return encoding;
   }

   public void setEncoding(String encoding) {
      this.encoding = encoding;
   }


   public boolean isPrettyPrint() {
      return prettyPrint;
   }

   public void setPrettyPrint(boolean prettyPrint) {
      this.prettyPrint = prettyPrint;
   }

   public String getSummary() {
      return summary;
   }

   public void setSummary(String summary) {
      this.summary = summary;
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

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getAccessToken() {
      return accessToken;
   }

   public void setAccessToken(String accessToken) {
      this.accessToken = accessToken;
   }

   public boolean isLog() {
      return log;
   }

   public void setLog(boolean log) {
      this.log = log;
   }

   public boolean isCompress() {
      return compress;
   }

   public void setCompress(boolean compress) {
      this.compress = compress;
   }

   public String getSessionCookie() {
      return sessionCookie;
   }

   public void setSessionCookie(String sessionCookie) {
      this.sessionCookie = sessionCookie;
   }
}
