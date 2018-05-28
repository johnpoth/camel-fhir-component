package org.apache.camel.component.fhir;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.builder.RouteBuilder;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.CapabilityStatement;
import org.hl7.fhir.dstu3.model.Enumerations;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.MessageHeader;
import org.hl7.fhir.dstu3.model.Meta;
import org.hl7.fhir.dstu3.model.OperationOutcome;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FhirComponentTest extends AbstractFhirTestSupport {

   private static final Logger LOG = LoggerFactory.getLogger(FhirComponentTest.class);
   private String id;

   @Before
   public void cleanFhirServerState() {
      if (patientExists()) {
         deletePatient();
      }
      createPatient();
   }

   @Test
   public void testRead() {
      Patient patient = getPatient(this.id);
      assertNotNull(patient);
      assertEquals("Freeman", patient.getName().get(0).getFamily());
   }

   @Test
   public void testSearch() {
      final String url = "Patient?given=Vincent&family=Freeman&_format=json";
      Bundle bundle = requestBody("direct://search", url);
      Patient patient = (Patient) bundle.getEntry().get(0).getResource();
      assertNotNull(patient);
      assertEquals("Freeman", patient.getName().get(0).getFamily());
   }


   @Test
   public void testDelete() {
      requestBody("direct://delete", "Patient?given=Vincent&family=Freeman");
      assertFalse(patientExists());
   }

   @Test
   public void testUpdate() throws ParseException {
      Patient patient = getPatient(this.id);
      Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1998-04-29");
      patient.setBirthDate(date);
      requestBody("direct://update", patient);
      patient = getPatient(this.id);
      assertEquals(date, patient.getBirthDate());
   }

   @Test
   @Ignore(value="https://github.com/jamesagnew/hapi-fhir/issues/955")
   public void testPatch() {
      String patch = "[ { \"op\":\"replace\", \"path\":\"/active\", \"value\":true } ]";
      Map<String, Object> headers = new HashMap<>();
      headers.put("CamelFhir.url", "Patient?given=Vincent&family=Freeman");
      requestBodyAndHeaders("direct://patch", patch, headers);
      Patient patient = getPatient(this.id);
      assertTrue(patient.getActive());
   }

   @Test
   public void testTransaction() {
      Patient oscar = new Patient().addName(new HumanName().addGiven("Oscar").setFamily("Peterson"));
      Patient bobbyHebb      = new Patient().addName(new HumanName().addGiven("Bobby").setFamily("Hebb"));
      List<IBaseResource> patients = new ArrayList<>(2);
      patients.add(oscar);
      patients.add(bobbyHebb);
      List<IBaseResource> resources = requestBody("direct://transaction", patients);
      assertTrue(resources.size() == 2 );
      resources.forEach( (resource) -> getPatient(resource.getIdElement().getIdPart()));
   }


   @Test
   public void testValidation() {
      Patient bobbyHebb      = new Patient().addName(new HumanName().addGiven("Bobby").setFamily("Hebb"));
      MethodOutcome outcome = requestBody("direct://validate", bobbyHebb);
      assertNotNull(outcome.getOperationOutcome());
      assertTrue(((OperationOutcome) outcome.getOperationOutcome()).getText().getDivAsString().contains("No issues detected during validation"));
   }

   @Test
   public void testHistory() {
      Map<String, Object> headers = new HashMap<>();
      headers.put("CamelFhir.returnType", Bundle.class);
      headers.put("CamelFhir.count", 1);
      Bundle bundle= requestBodyAndHeaders("direct://history", null, headers);
      assertNotNull(bundle);
      assertEquals(1, bundle.getEntry().size());
   }

   @Test
   public void testLoadPage() {
      final String url = "Patient?";
      Bundle bundle = requestBody("direct://search", url);
      assertNotNull(bundle.getLink(Bundle.LINK_NEXT));
      bundle = requestBody("direct://load-page", bundle);
      assertNotNull(bundle);
   }

   @Test
   public void testMeta() {
      Map<String, Object> headers = new HashMap<>();
      headers.put("CamelFhir.metaType", Meta.class);
      headers.put("CamelFhir.id", new IdType("Patient", this.id));
      Meta meta = requestBodyAndHeaders("direct://meta", null, headers);
      assertEquals(0, meta.getTag().size());
   }

   @Test
   @Ignore
   public void testOperation() {
      deletePatient();
      assertFalse(patientExists());
      Map<String, Object> headers = new HashMap<>();
      Bundle bundle = getMessageBundle(
              "myEvent", "Test Event",
              "MySource", "http://myServer/fhir/", "MyDestination", "http://myDestinationServer/fhir/");
      headers.put("CamelFhir.theMsgBundle", bundle);
      headers.put("CamelFhir.theResponseClass", Bundle.class);

      Bundle resp = requestBodyAndHeaders("direct://operation", null, headers);
      assertNotNull(resp);
   }

   @Test
   public void testCapabilities() {
      Map<String, Object> headers = new HashMap<>();
      headers.put("CamelFhir.type", CapabilityStatement.class);
      CapabilityStatement resp = requestBodyAndHeaders("direct://capabilities", null, headers);
      assertNotNull(resp);
      assertEquals(Enumerations.PublicationStatus.ACTIVE, resp.getStatus());
   }

   private void createPatient() {
      Patient vincentFreeman = new Patient().addName(new HumanName().addGiven("Vincent").setFamily("Freeman"));
      MethodOutcome outcome = requestBody("direct://create", vincentFreeman);
      assertTrue(outcome.getCreated());
      this.id = outcome.getId().getIdPart();
   }

   private void deletePatient() {
      sendBody("direct://delete", "Patient?given=Vincent&family=Freeman");
      assertFalse(patientExists());
   }

   private boolean patientExists() {
      String url = "Patient?given=Vincent&family=Freeman&_format=json";
      Bundle bundle = requestBody("direct://search", url);
      return !bundle.getEntry().isEmpty();
   }

   private Bundle getMessageBundle(String eventCode, String eventDisplay, String sourceName, String sourceEnpoint, String destinationName, String destinationEndpoint) {
        /*
         Init Bundle
         */
      Bundle msgBundle = new Bundle();
      msgBundle.getMeta().setLastUpdated(new Date());
      msgBundle.setType(Bundle.BundleType.MESSAGE); //Document Type
      msgBundle.setId(UUID.randomUUID().toString()); // Random ID
        /*
         Init MessageHeader
         */
      MessageHeader msh = new MessageHeader();
      msh.setId(UUID.randomUUID().toString());
      msh.getEvent().setSystem("http://mybServer/fhir/events");
      msh.getEvent().setCode(eventCode);
      msh.getEvent().setDisplay(eventDisplay);
      msh.getSource().setName(sourceName);
      msh.getSource().setEndpoint(sourceEnpoint);
      msh.getDestinationFirstRep().setName(destinationName);
      msh.getDestinationFirstRep().setEndpoint(destinationEndpoint);
      Bundle.BundleEntryComponent entry = new Bundle.BundleEntryComponent();
      entry.setFullUrl("http://foobar/");
      entry.setResource(msh);
      msgBundle.addEntry(entry);
      return msgBundle;
   }

   private Patient getPatient(String id) {
      Map<String, Object> headers = new HashMap<>();
      headers.put("CamelFhir.resource", Patient.class);
      headers.put("CamelFhir.id", id);
      return requestBodyAndHeaders("direct://read", null, headers);
   }

   @Override
   protected RouteBuilder createRouteBuilder() throws Exception {
      return new RouteBuilder() {
         public void configure() {
            // test routes for read
            from("direct://read").to("fhir://read/resourceWithId");
            from("direct://search").to("fhir://search/searchByUrl?inBody=url");
            from("direct://delete").to("fhir://delete/resourceConditionalByUrl?inBody=url");
            from("direct://create").to("fhir://create/resource?inBody=resource");
            from("direct://update").to("fhir://update/resource?inBody=resource");
            from("direct://patch").to("fhir://patch/patchByUrl?inBody=patchBody");
            from("direct://transaction").to("fhir://transaction/withResources?inBody=resources");
            from("direct://validate").to("fhir://validate/resource?inBody=resource");
            from("direct://history").to("fhir://history/onServer");
            from("direct://load-page").to("fhir://load-page/next?inBody=bundle");
            from("direct://meta").to("fhir://meta/getFromResource");
            from("direct://operation").to("fhir://operation/messageBundle");
            from("direct://capabilities").to("fhir://capabilities/ofType");
         }
      };
   }
}
