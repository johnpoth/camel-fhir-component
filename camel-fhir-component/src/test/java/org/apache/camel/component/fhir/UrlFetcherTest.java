package org.apache.camel.component.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.Patient;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("Helper class to generate search URLs based on HAPI-FHIR's search API")
public class UrlFetcherTest {

   @Test
   public void getUrlTest(){
      // Create a client to talk to your favorite test server
      FhirContext ctx = FhirContext.forDstu3();
      IGenericClient client = ctx.newRestfulGenericClient("http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3");
      // URL will be logged in console, see log4j2.properties
      client.registerInterceptor(new LoggingInterceptor(true));

      client.search().forResource("Patient").where(Patient.IDENTIFIER.exactly().identifier("this/is/my/id")).execute();
   }
}
