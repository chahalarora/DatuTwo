package com.datu.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import com.datu.patient.Patient;
import com.datu.patient.TileResponse;
import com.datu.patient.TileResponseEngagement;
import com.datu.result.DisplayTile;

public class PI001_43Test extends RulesBaseTest {
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
	public void succesTest() throws ParseException {
	  System.out.println("Executing PI001_43Test.succesTest");
      TileResponseEngagement te1 = new TileResponseEngagement();
  //    te1.setActionName("AppointmentDate");
  //    te1.setActionValue("2015/04/10");
      te1.setActionValue("Yes");
      
      TileResponse tr1 = new TileResponse();
      tr1.setTileResponseId(57);
      SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
      Date date = format.parse("2015/03/10");
      tr1.setResponseDateTime(date);
      tr1.getTileResponseEngagements().add(te1);
      
      patient.getTileResponses().add(tr1);
      ksession.insert(patient);
      ksession.fireAllRules();
      
      boolean actualResult =  false;
      for(DisplayTile displayTile : patient.getRulesResult().getDisplayTiles()){
        if(displayTile.getTileRange().equals("128-132")){
            actualResult = true;
            break;
        }
      }
      assertEquals("Positive condition", actualResult, true);
	}
}