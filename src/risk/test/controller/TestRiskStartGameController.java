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
	RiskStartGameController riskstartgamecontroller;
	
	@Before
	public void before()
	{
		 riskstartgamecontroller=new RiskStartGameController("test");
	}

	@Test
	public void testValidateMapLineInputText() throws ParserConfigurationException, SAXException, IOException 
	{
//		riskstartgamecontroller.initComponents();
		boolean q= riskstartgamecontroller.validateMapLineInputText("India,Asia,China,Japan");
		riskstartgamecontroller.generateChooseMapPanel();
		boolean q1= riskstartgamecontroller.validateMapLineInputText("Indie,Asiw,China,Japan");
		assertEquals(true,q);
		assertEquals(false,q1);
	}
    public void testgetAdjacentCountryInfo() throws ParserConfigurationException, SAXException, IOException
    {
    	riskstartgamecontroller.populatePredefinedTerritoryCoordinatesList();
    	String h=riskstartgamecontroller.getAdjacentCountryInfo("India","Japan","Asia");
    	assertEquals("India,47,76,Asia,Japan",h);
    	
    }


}
