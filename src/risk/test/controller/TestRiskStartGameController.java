package risk.test.controller;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import risk.controller.RiskController;
import risk.controller.RiskStartGameController;
import risk.model.RiskPlayerModel;

/**
 * The Class Test Risk Start Game Controller.
 */
public class TestRiskStartGameController {
	
	/** The risk start game controller. */
	RiskStartGameController riskstartgamecontroller;
	
	/**
	 * Before.
	 */
	@Before
	public void before() {
		riskstartgamecontroller = new RiskStartGameController("test");
	}

	/**
	 * Test validate map line input text.
	 *
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the SAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testValidateMapLineInputText() throws ParserConfigurationException, SAXException, IOException {
		riskstartgamecontroller.generateChooseMapPanel();
		boolean q = riskstartgamecontroller.validateMapLineInputText("India,Asia,China,Japan");		
		assertEquals(true,q);
	}
	@Test
	public void testInvalidMapLineInputText() throws ParserConfigurationException, SAXException, IOException {
		riskstartgamecontroller.generateChooseMapPanel();		
		boolean q1= riskstartgamecontroller.validateMapLineInputText("China,Japan");
		assertEquals(false,q1);
	}

    /**
     * Test get adjacent country info.
     *
     * @throws ParserConfigurationException the parser configuration exception
     * @throws SAXException the SAX exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
	@Test
    public void testgetAdjacentCountryInfo() throws ParserConfigurationException, SAXException, IOException
    {
    	riskstartgamecontroller.populatePredefinedTerritoryCoordinatesList();
    	//riskstartgamecontroller.getFromPredefinedTerritoryCoordinatesList();
    	String h=riskstartgamecontroller.getAdjacentCountryInfo("India","Japan","Asia");
    	assertEquals("India,47,76,Asia,Japan",h);
    	
    }

    /**
     * Test validate all countries
     */
	@Test
	public void testValidateAllCountriesConnected() {
		HashMap<String, String> countries = new HashMap<String, String>();
		countries.put("aa", "aa,100,100,india,bb,cc");
		countries.put("bb", "bb,100,100,india,aa");
		countries.put("cc", "aa,100,100,india,aa");

		riskstartgamecontroller.copyhmCountryDetails = (HashMap<String, String>) countries.clone();
		assertEquals(true, riskstartgamecontroller.CheckCountriesConnected(countries));
	}
	

}
