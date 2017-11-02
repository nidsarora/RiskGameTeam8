package risk.test.controller;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import risk.controller.RiskController;
import risk.controller.RiskStartGameController;
import risk.model.RiskPlayerModel;



public class TestRiskStartGameController {
	RiskStartGameController rsgc;
	
	@Before
	public void before()
	{
		 rsgc=new RiskStartGameController("test");
	}

	@Test
	public void testmapEditTextInsertCoordinates() 
	{
		rsgc.initializeMapVariables();
		String result =rsgc.mapEditTextInsertCoordinates("Alaska,North_America,Northwest_Territory,Alberta,Kamchatka");
		assertEquals("Alaska,47,76,North_America,Northwest_Territory,Alberta,Kamchatka",result);
	}
	@Test
	public void testValidateMapLineInputText() throws ParserConfigurationException, SAXException, IOException 
	{
		rsgc.initComponents();
		boolean q= rsgc.validateMapLineInputText("India,Asia,China,Japan");
		rsgc.generateChooseMapPanel();
		boolean q1= rsgc.validateMapLineInputText("Indie,Asiw,China,Japan");
		assertEquals(true,q);
		assertEquals(false,q1);
	}
	/*@Test
	public void testValidateInvalidMapInput() throws ParserConfigurationException, SAXException, IOException 
	{
		rsgc.initComponents();
		boolean s = rsgc.validateMapLineInputText("Montreal,Toronto,Ontario,Ottawa");
	    rsgc.generateChooseMapPanel();
		assertEquals(false,s);
	
	}*/
	
	@Test
	public void testFetchCoordinates() 
	{
		rsgc.initializeMapVariables();
		String result=rsgc.fetchCoordinates("Alaska");
		assertEquals(",47,76,",result);
		
	}

}
