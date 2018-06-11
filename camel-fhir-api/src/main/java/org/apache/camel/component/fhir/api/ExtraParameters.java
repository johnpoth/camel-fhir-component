package org.apache.camel.component.fhir.api;

import java.util.Map;
import ca.uhn.fhir.rest.gclient.IClientExecutable;

/**
 * Encapsulates a list of extra parameters that are valid for *ALL* Camel FHIR APIs
 */
public enum ExtraParameters {

   /**
    * Will encode the request to JSON
    */
   ENCODE_JSON("encodeJson"),

   /**
    * Will encode the request to XML
    */
   ENCODE_XML("encodeXml");

   private final String param;
   private final String headerName;

   ExtraParameters(String param) {
      this.param = param;
      this.headerName = "CamelFhir." + param;
   }

   public String getParam() {
      return param;
   }

   public String getHeaderName() {
      return headerName;
   }

   static <T extends IClientExecutable<T,Y>, Y> T process(Map<ExtraParameters, Object> extraParameters, T clientExecutable) {
      for (Map.Entry<ExtraParameters, Object> entry : extraParameters.entrySet()) {
         switch(entry.getKey()) {
            case ENCODE_JSON:
               Boolean encode = (Boolean) extraParameters.get(ExtraParameters.ENCODE_JSON);
               if (encode) {
                  clientExecutable = clientExecutable.encodedJson();
               }
               break;
         }
      }
      return clientExecutable;
   }
}
