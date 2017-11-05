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
import risk.model.RiskContinentModel;
import risk.model.RiskGameModel;
import risk.model.RiskPlayerModel;
import risk.model.RiskTerritoryModel;

public class RiskGameTest {

	
	
	@Test
	public void testBonus() {
		RiskGameModel riskGameModel=new RiskGameModel("test");
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1);
		//riskGameModel.loadMap();
		RiskTerritoryModel rtm=new RiskTerritoryModel(1,"Territory1",2,155,70);
		RiskTerritoryModel rtm1=new RiskTerritoryModel(2,"Territory2",2,280,134);
		RiskTerritoryModel rtm2=new RiskTerritoryModel(3,"Territory3",2,280,134);
		RiskTerritoryModel rtm3=new RiskTerritoryModel(4,"Territory4",2,280,134);
		RiskTerritoryModel rtm4=new RiskTerritoryModel(5,"Territory5",2,280,134);
		RiskTerritoryModel rtm5=new RiskTerritoryModel(6,"Territory6",2,280,134);
		RiskTerritoryModel rtm6=new RiskTerritoryModel(7,"Territory7",2,280,134);
		RiskTerritoryModel rtm7=new RiskTerritoryModel(8,"Territory8",2,280,134);
		RiskTerritoryModel rtm8=new RiskTerritoryModel(9,"Territory9",2,280,134);
		RiskTerritoryModel rtm9=new RiskTerritoryModel(10,"Territory10",2,280,134);
		RiskTerritoryModel rtm10=new RiskTerritoryModel(11,"Territory11",2,280,134);
		RiskTerritoryModel rtm11=new RiskTerritoryModel(12,"Territory12",2,280,134);
		Vector<RiskTerritoryModel> occupiedTerritories=new Vector<RiskTerritoryModel>();
		occupiedTerritories.add(rtm);
		occupiedTerritories.add(rtm1);
		occupiedTerritories.add(rtm2);
		occupiedTerritories.add(rtm3);
		occupiedTerritories.add(rtm4);
		occupiedTerritories.add(rtm5);
		occupiedTerritories.add(rtm6);
		occupiedTerritories.add(rtm7);
		occupiedTerritories.add(rtm8);
		occupiedTerritories.add(rtm9);
		occupiedTerritories.add(rtm10);
		occupiedTerritories.add(rtm11);
		rpm.setOccupiedTerritories(occupiedTerritories);
		riskGameModel.setCurPlayer(rpm);
		Vector<Integer> val=new Vector<Integer>();
		val.add(1);
		val.add(2);
		RiskContinentModel rcm=new RiskContinentModel("Africa",val,3);
		Vector<Integer> val1=new Vector<Integer>();
		val1.add(3);
		val1.add(4);
		RiskContinentModel rcm1=new RiskContinentModel("Europe",val1,5);
		Vector<RiskContinentModel> continents=new Vector<RiskContinentModel>();
		continents.add(rcm);
		continents.add(rcm1);
		riskGameModel.setContinents(continents);
		int result =riskGameModel.turnBonus();
		assertEquals(12,result);
	} 
	
	@Test
	public void testDistribute() {
		RiskGameModel riskGameModel=new RiskGameModel("test");
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1);
		RiskPlayerModel rpm1=new RiskPlayerModel("player2",2);
		RiskPlayerModel rpm2=new RiskPlayerModel("player3",3);
		RiskPlayerModel rpm3=new RiskPlayerModel("player4",4);
		RiskPlayerModel rpm4=new RiskPlayerModel("player5",5);
		riskGameModel.setCurPlayer(rpm);
		Vector<RiskPlayerModel> players=new Vector<RiskPlayerModel>();
		players.add(rpm);
		players.add(rpm1);
		players.add(rpm2);
		players.add(rpm3);
		players.add(rpm4);
		riskGameModel.setPlayer(players);
		riskGameModel.distubuteArmies();
		int result=riskGameModel.getArmies();
		assertEquals(25,result);
	}
	
	@Test
	public void testOccupyTerritory() {
		RiskGameModel riskGameModel=new RiskGameModel("test");
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1);
		rpm.setCard(new RiskCardModel(1, 39));
		riskGameModel.setCurPlayer(rpm);
		RiskTerritoryModel rtm=new RiskTerritoryModel(1,"Northwest_Territory",2,155,70);
		boolean result=riskGameModel.occupyTerritory(rtm);
		assertEquals(false,result);
	}
	
	@Test
	public void testGeneratePanel() {
		RiskController rc= new RiskController("test");
		RiskPlayerModel rpm4=new RiskPlayerModel("player5",5);
		rc.setRiskCurPlayer(rpm4);
		rc.GenerateCardPanel();
		int result=rc.getCountTradeCards();
		assertEquals(0,result);
		
 	}
	@Test
	public void testValidateMapLineInputText() throws ParserConfigurationException, SAXException, IOException {
		RiskStartGameController rsgc= new RiskStartGameController();
		rsgc.initComponents();
		boolean q= rsgc.validateMapLineInputText("India,Asia,China,Japan");
		rsgc.generateChooseMapPanel();
		boolean q1= rsgc.validateMapLineInputText("Indie,Asiw,China,Japan");
		assertEquals(true,q);
		assertEquals(false,q1);
		
		
	}
	@Test
	public void testAdjacentTerritory() {
		RiskGameModel rgmm= new RiskGameModel("test");
		RiskTerritoryModel rttm= new RiskTerritoryModel(1,"Alaska",1,47,76);
		Vector<Integer> val=new Vector<Integer>();
	    val.add(2);
	    RiskTerritoryModel rttm1= new RiskTerritoryModel(2,"Northwest_Territory",1,155,70);
	    Vector<Integer> val1=new Vector<Integer>();
	    val.add(1);
	    rttm.setAdjacent(val);
	    rttm1.setAdjacent(val1);
		assertEquals(true,rttm.isAdjacent(rttm1));
	}
	@Test
	public void testFetchCoordinates() {
		RiskStartGameController rsgc=new RiskStartGameController("test");
		rsgc.initializeMapVariables();
		String result=rsgc.fetchCoordinates("Alaska");
		assertEquals(",47,76,",result);
		
	}
	}
	

