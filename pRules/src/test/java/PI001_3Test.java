package java;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import com.datu.patient.CollectionResponse;
import com.datu.patient.Demographics;
import com.datu.patient.Patient;
import com.datu.result.DisplayCollection;

import static org.junit.Assert.assertEquals;

//Collect patient's initial information using MyInformation Survey
public class PI001_3Test extends RulesBaseTest {
  private StatefulKnowledgeSession ksession = null;
  private Patient patient;
  
	@Before
    public void setUp() {
        ksession = initializeKnowledgeSession();
        patient = new Patient();
    }
	
	@After
	public void tearDown() {
        if(ksession != null){
          ksession.dispose();
        }
    }
	
	@Test
	public void collectionResponseWithout12AndGenderMale() {
	  System.out.println("Executing PI001_3Test.collectionResponseWithout12AndGenderMale");
	  CollectionResponse c1 = new CollectionResponse();
      c1.setCollectionId(10);
      patient.getCollectionResponses().add(c1);
      Demographics demographics = new Demographics();
      demographics.setGenderCode("M");
      patient.setDemographics(demographics);
      ksession.insert(patient);
      ksession.fireAllRules();
      //List<DisplayCollection> dCollections = patient.getRulesResult().getDisplayCollections();
      boolean actualResult =  false;
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 12){
            actualResult = true;
            break;
        }
      }
      assertEquals("Positive condition", actualResult, true);
	}
	
	@Test
    public void collectionResponseWithout12NoDemographics() {
	  System.out.println("Executing PI001_3Test.collectionResponseWithout12NoDemographics");
      CollectionResponse c1 = new CollectionResponse();
      c1.setCollectionId(10);
      
      CollectionResponse c2 = new CollectionResponse();
      c2.setCollectionId(13);
      
      patient.getCollectionResponses().add(c1);
      patient.getCollectionResponses().add(c2);
      ksession.insert(patient);
      ksession.fireAllRules();
      //List<DisplayCollection> dCollections = patient.getRulesResult().getDisplayCollections();
      boolean actualResult =  false;
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 12){
            actualResult = true;
            break;
        }
      }
      assertEquals("Positive condition", actualResult, false);
    }
	
	@Test
    public void conditionResponseWith12AndGenderMale() {
	  System.out.println("Executing PI001_3Test.conditionResponseWith12AndGenderMale");
      CollectionResponse c1 = new CollectionResponse();
      c1.setCollectionId(12);
      
      Demographics demographics = new Demographics();
      demographics.setGenderCode("M");
      patient.setDemographics(demographics);
      patient.getCollectionResponses().add(c1);
      ksession.insert(patient);
      ksession.fireAllRules();
      boolean actualResult =  false;
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 12){
            actualResult = true;
            break;
        }
      }
      assertEquals("Positive condition", actualResult, false);
    }
	
	@Test
    public void conditionResponseWith12AndGenderFemale() {
	  System.out.println("Executing PI001_3Test.conditionResponseWith12AndGenderFemale");
      CollectionResponse c1 = new CollectionResponse();
      c1.setCollectionId(10);
      CollectionResponse c2 = new CollectionResponse();
      c2.setCollectionId(12);
      patient.getCollectionResponses().add(c1);
      patient.getCollectionResponses().add(c2);
      
      Demographics demographics = new Demographics();
      demographics.setGenderCode("F");
      patient.setDemographics(demographics);
      
      ksession.insert(patient);
      ksession.fireAllRules();
      boolean actualResult =  true;
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 12){
            actualResult = true;
            break;
        }
      }
      assertEquals("Positive condition", actualResult, true);
    }
}