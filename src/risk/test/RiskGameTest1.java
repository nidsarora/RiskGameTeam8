package risk.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import risk.controller.RiskController;
import risk.controller.RiskStartGameController;
import risk.model.RiskCardModel;
import risk.model.RiskGameModel;
import risk.model.RiskPlayerModel;
import risk.model.RiskTerritoryModel;
public class RiskGameTest1 {

	@Test
	public void testmapEditTextInsertCoordinates() {
		RiskStartGameController rsgc=new RiskStartGameController("test");
		rsgc.initializeMapVariables();
		String result =rsgc.mapEditTextInsertCoordinates("Alaska,North_America,Northwest_Territory,Alberta,Kamchatka");
		assertEquals("Alaska,47,76,North_America,Northwest_Territory,Alberta,Kamchatka",result);
	}

	@Test
	public void testAddplayer() {
		RiskGameModel rgm=new RiskGameModel("test");
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1);
		RiskPlayerModel rpm1=new RiskPlayerModel("player2",2);
		RiskPlayerModel rpm2=new RiskPlayerModel("player3",3);
		RiskPlayerModel rpm3=new RiskPlayerModel("player4",4);
		RiskPlayerModel rpm4=new RiskPlayerModel("player5",5);
		RiskPlayerModel rpm5=new RiskPlayerModel("player6",6);
		RiskPlayerModel rpm6=new RiskPlayerModel("player7",7);
		Vector<RiskPlayerModel> players=new Vector<RiskPlayerModel>();
		players.add(rpm);
		players.add(rpm1);
		players.add(rpm2);
		players.add(rpm3);
		players.add(rpm4);
		players.add(rpm5);
		players.add(rpm6);
		rgm.setPlayer(players);
		boolean result=rgm.addPlayer("player8");
		assertEquals(false,result);
	}
	@Test
	public void testNewFormatLoadMap() {
		RiskGameModel rgm=new RiskGameModel("test");
		rgm.loadMap_newformat();
		assertEquals(42,rgm.getTerritories().size());
	}
	
}
