package org.apache.camel.component.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("Helper class to generate search URL based on HAPI-FHIR's search API")
public class UrlFetcherTest {

   @Test
   public void getUrlTest(){
      // Create a client to talk to the HeathIntersections server
      FhirContext ctx = FhirContext.forDstu3();
      IGenericClient client = ctx.newRestfulGenericClient("http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3");
      client.registerInterceptor(new LoggingInterceptor(true));

      client.search().forResource("Patient").count(2).execute();
   }
}
