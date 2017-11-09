package risk.test.model;

import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import risk.model.RiskCardModel;
import risk.model.RiskContinentModel;
import risk.model.RiskGameModel;
import risk.model.RiskPlayerModel;
import risk.model.RiskTerritoryModel;

public class TestRiskGameModel {
	RiskGameModel riskGameModel;

	@Before
	public void before() {
		riskGameModel = new RiskGameModel("test");
	}

	@Test
	public void testOccupyTerritory() {

		RiskPlayerModel rpm = new RiskPlayerModel("player1", 1);
		rpm.setCard(new RiskCardModel(1, 39));
		riskGameModel.setCurPlayer(rpm);
		RiskTerritoryModel rtm = new RiskTerritoryModel(1, "Northwest_Territory", 2, 155, 70);
		boolean result = riskGameModel.occupyTerritory(rtm);
		assertEquals(false, result);
	}

	@Test
	public void testDistribute() {
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1);
		RiskPlayerModel riskplayermodel1 = new RiskPlayerModel("player2", 2);
		RiskPlayerModel riskplayermodel2 = new RiskPlayerModel("player3", 3);
		RiskPlayerModel riskplayermodel3 = new RiskPlayerModel("player4", 4);
		RiskPlayerModel riskplayermodel4 = new RiskPlayerModel("player5", 5);
		riskGameModel.setCurPlayer(riskplayermodel);
		Vector<RiskPlayerModel> players = new Vector<RiskPlayerModel>();
		players.add(riskplayermodel);
		players.add(riskplayermodel);
		players.add(riskplayermodel2);
		players.add(riskplayermodel3);
		players.add(riskplayermodel4);
		riskGameModel.setPlayer(players);
		riskGameModel.distubuteArmies();
		int result = riskGameModel.getArmies();
		assertEquals(25, result);
	}

	@Test
	public void testTurnBonus() {
		RiskPlayerModel rpm = new RiskPlayerModel("player1", 1);
		// riskGameModel.loadMap();
		RiskTerritoryModel riskterritorymodel = new RiskTerritoryModel(1, "Territory1", 2, 155, 70);
		RiskTerritoryModel riskterritorymodel1 = new RiskTerritoryModel(2, "Territory2", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel2 = new RiskTerritoryModel(3, "Territory3", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel3 = new RiskTerritoryModel(4, "Territory4", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel4 = new RiskTerritoryModel(5, "Territory5", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel5 = new RiskTerritoryModel(6, "Territory6", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel6 = new RiskTerritoryModel(7, "Territory7", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel7 = new RiskTerritoryModel(8, "Territory8", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel8 = new RiskTerritoryModel(9, "Territory9", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel9 = new RiskTerritoryModel(10, "Territory10", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel10 = new RiskTerritoryModel(11, "Territory11", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel11 = new RiskTerritoryModel(12, "Territory12", 2, 280, 134);
		Vector<RiskTerritoryModel> occupiedTerritories = new Vector<RiskTerritoryModel>();
		occupiedTerritories.add(riskterritorymodel);
		occupiedTerritories.add(riskterritorymodel1);
		occupiedTerritories.add(riskterritorymodel2);
		occupiedTerritories.add(riskterritorymodel3);
		occupiedTerritories.add(riskterritorymodel4);
		occupiedTerritories.add(riskterritorymodel);
		occupiedTerritories.add(riskterritorymodel6);
		occupiedTerritories.add(riskterritorymodel7);
		occupiedTerritories.add(riskterritorymodel8);
		occupiedTerritories.add(riskterritorymodel9);
		occupiedTerritories.add(riskterritorymodel10);
		occupiedTerritories.add(riskterritorymodel11);
		rpm.setOccupiedTerritories(occupiedTerritories);
		riskGameModel.setCurPlayer(rpm);
		Vector<Integer> val = new Vector<Integer>();
		val.add(1);
		val.add(2);
		RiskContinentModel riskcontinentmodel = new RiskContinentModel("Africa", val, 3);
		Vector<Integer> val1 = new Vector<Integer>();
		val1.add(3);
		val1.add(4);
		RiskContinentModel riskcontinentmodel1 = new RiskContinentModel("Europe", val1, 5);
		Vector<RiskContinentModel> continents = new Vector<RiskContinentModel>();
		continents.add(riskcontinentmodel);
		continents.add(riskcontinentmodel1);
		riskGameModel.setContinents(continents);
		int result = riskGameModel.turnBonus();
		assertEquals(12, result);
	}

	@Test
	public void testBonusTerritory() {
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1);
		RiskTerritoryModel riskterritorymodel = new RiskTerritoryModel(1, "Territory1", 2, 155, 70);
		RiskTerritoryModel riskterritorymodel1 = new RiskTerritoryModel(2, "Territory2", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel2 = new RiskTerritoryModel(3, "Territory3", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel3 = new RiskTerritoryModel(4, "Territory4", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel4 = new RiskTerritoryModel(5, "Territory5", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel5 = new RiskTerritoryModel(6, "Territory6", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel6 = new RiskTerritoryModel(7, "Territory7", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel7 = new RiskTerritoryModel(8, "Territory8", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel8 = new RiskTerritoryModel(9, "Territory9", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel9 = new RiskTerritoryModel(10, "Territory10", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel10 = new RiskTerritoryModel(11, "Territory11", 2, 280, 134);
		RiskTerritoryModel riskterritorymodel11 = new RiskTerritoryModel(12, "Territory12", 2, 280, 134);
		Vector<RiskTerritoryModel> occupiedTerritories = new Vector<RiskTerritoryModel>();
		occupiedTerritories.add(riskterritorymodel);
		occupiedTerritories.add(riskterritorymodel1);
		occupiedTerritories.add(riskterritorymodel2);
		occupiedTerritories.add(riskterritorymodel3);
		occupiedTerritories.add(riskterritorymodel4);
		occupiedTerritories.add(riskterritorymodel5);
		occupiedTerritories.add(riskterritorymodel6);
		occupiedTerritories.add(riskterritorymodel7);
		occupiedTerritories.add(riskterritorymodel8);
		occupiedTerritories.add(riskterritorymodel9);
		occupiedTerritories.add(riskterritorymodel10);
		occupiedTerritories.add(riskterritorymodel11);
		riskplayermodel.setOccupiedTerritories(occupiedTerritories);
		riskGameModel.setCurPlayer(riskplayermodel);
		int res = riskGameModel.collectReinforcements();
		assertEquals(4, res);

	}

	@Test
	public void testBonusContinent() {
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 2);
		Vector<Integer> val = new Vector<Integer>();
		val.add(1);
		val.add(2);
		RiskContinentModel riskcontinentmodel = new RiskContinentModel("Africa", val, 3);
		Vector<Integer> val1 = new Vector<Integer>();
		val1.add(3);
		val1.add(4);
		RiskContinentModel riskcontinentmodel1 = new RiskContinentModel("Europe", val1, 5);
		Vector<RiskContinentModel> continents = new Vector<RiskContinentModel>();
		continents.add(riskcontinentmodel);
		continents.add(riskcontinentmodel1);
		riskGameModel.setContinents(continents);
		riskGameModel.setCurPlayer(riskplayermodel);
		int conbonus = riskGameModel.collectReinforcementsFromContinent();
		System.out.println(conbonus);

	}

	@Test
	public void testAddplayer() {
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1);
		RiskPlayerModel riskplayermodel1 = new RiskPlayerModel("player2", 2);
		RiskPlayerModel riskplayermodel2 = new RiskPlayerModel("player3", 3);
		RiskPlayerModel riskplayermodel3 = new RiskPlayerModel("player4", 4);
		RiskPlayerModel riskplayermodel4 = new RiskPlayerModel("player5", 5);
		RiskPlayerModel riskplayermodel5 = new RiskPlayerModel("player6", 6);
		RiskPlayerModel riskplayermodel6 = new RiskPlayerModel("player7", 7);
		Vector<RiskPlayerModel> players = new Vector<RiskPlayerModel>();
		players.add(riskplayermodel);
		players.add(riskplayermodel1);
		players.add(riskplayermodel2);
		players.add(riskplayermodel3);
		players.add(riskplayermodel4);
		players.add(riskplayermodel5);
		players.add(riskplayermodel6);
		riskGameModel.setPlayer(players);
	}
    @Test
	public void testGetOwnership() 
	{
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1);
		riskGameModel.setCurPlayer(rpm);
		RiskTerritoryModel rttm= new RiskTerritoryModel(1,"Alaska",1,47,76);
		rttm.setPlayer(rpm);
		riskGameModel.territories= new Vector<RiskTerritoryModel>();
	    riskGameModel.territories.addElement(rttm);
		int c=riskGameModel.getOwnership(0);
		assertEquals(1,c);
	}
	@Test
	public void testRiskFortifying() {
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1);
		riskGameModel.setCurPlayer(rpm);
		RiskTerritoryModel rttm= new RiskTerritoryModel(1,"Alaska",1,47,76);
		Vector<Integer> val=new Vector<Integer>();
	    val.add(2);
	    rttm.setPlayer(rpm);
	    RiskTerritoryModel rttm1= new RiskTerritoryModel(2,"Northwest_Territory",1,155,70);
	    Vector<Integer> val1=new Vector<Integer>();
	    val.add(1);
	    rttm1.setPlayer(rpm);
	    rttm.setAdjacent(val);
	    rttm1.setAdjacent(val1);
	    riskGameModel.territories= new Vector<RiskTerritoryModel>();
	    riskGameModel.territories.addElement(rttm);
	    riskGameModel.territories.addElement(rttm1);
	    riskGameModel.aTerritory = rttm;
	    String d = riskGameModel.RiskFortifying(1);
	    assertEquals("",d);
	    
	}
	@Test
	public void testRiskNotFortifying() {
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1);
		riskGameModel.setCurPlayer(rpm);
		RiskTerritoryModel rttm= new RiskTerritoryModel(1,"India",1,47,76);
		Vector<Integer> val=new Vector<Integer>();
	    val.add(2);
	    rttm.setPlayer(rpm);
	    RiskTerritoryModel rttm1= new RiskTerritoryModel(2,"Japan",1,155,70);
	    Vector<Integer> val1=new Vector<Integer>();
	    val.add(1);
	    rttm1.setPlayer(rpm);
	    riskGameModel.territories= new Vector<RiskTerritoryModel>();
	    riskGameModel.territories.addElement(rttm);
	    riskGameModel.territories.addElement(rttm1);
	    riskGameModel.aTerritory = rttm;
	    String d = riskGameModel.RiskFortifying(1);
	    assertEquals("",d);
	    
	}
	@Test
	public void testRiskAttack() {
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1);
		RiskPlayerModel rpm1=new RiskPlayerModel("player1",1);
		riskGameModel.setCurPlayer(rpm);
		RiskTerritoryModel rttm= new RiskTerritoryModel(1,"India",1,47,76);
		Vector<Integer> val=new Vector<Integer>();
	    val.add(2);
	    rttm.setPlayer(rpm);
	    RiskTerritoryModel rttm1= new RiskTerritoryModel(2,"Japan",1,155,70);
	    Vector<Integer> val1=new Vector<Integer>();
	    val.add(1);
	    rttm1.setPlayer(rpm1);
	    rttm.setAdjacent(val);
	    rttm1.setAdjacent(val1);
	    riskGameModel.territories= new Vector<RiskTerritoryModel>();
	    riskGameModel.territories.addElement(rttm);
	    riskGameModel.territories.addElement(rttm1);
		rttm.addArmies(7);
		
		assertEquals("true",riskGameModel.Riskattack(0));
		
	}
	@Test
	public void testNOEnoughArmiesToAttack() {
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1);
		RiskPlayerModel rpm1=new RiskPlayerModel("player1",1);
		riskGameModel.setCurPlayer(rpm);
		RiskTerritoryModel rttm= new RiskTerritoryModel(1,"India",1,47,76);
		Vector<Integer> val=new Vector<Integer>();
	    val.add(2);
	    rttm.setPlayer(rpm);
	    RiskTerritoryModel rttm1= new RiskTerritoryModel(2,"Japan",1,155,70);
	    Vector<Integer> val1=new Vector<Integer>();
	    val.add(1);
	    rttm1.setPlayer(rpm1);
	    rttm.setAdjacent(val);
	    rttm1.setAdjacent(val1);
	    riskGameModel.territories= new Vector<RiskTerritoryModel>();
	    riskGameModel.territories.addElement(rttm);
	    riskGameModel.territories.addElement(rttm1);
		rpm.addArmies(1);
		String e=riskGameModel.Riskattack(0);
		assertEquals("Not enough armies to battle, need at least 2",e);

	}
	
	@Test
	public void testAdjacentTerritory() {
		RiskTerritoryModel riskterritorymodel = new RiskTerritoryModel(1, "Alaska", 1, 47, 76);
		Vector<Integer> val = new Vector<Integer>();
		val.add(2);
		RiskTerritoryModel riskterritorymodel1 = new RiskTerritoryModel(2, "Northwest_Territory", 1, 155, 70);
		Vector<Integer> val1 = new Vector<Integer>();
		val.add(1);
		riskterritorymodel.setAdjacent(val);
		riskterritorymodel1.setAdjacent(val1);
		assertEquals(true, riskterritorymodel.isAdjacent(riskterritorymodel1));
	}
	@Test
	public void testNotAdjacentTerritory() 
	{
		RiskTerritoryModel rttm= new RiskTerritoryModel(1,"Alaska",1,47,76);
		Vector<Integer> val=new Vector<Integer>();
	    val.add(2);
	    RiskTerritoryModel rttm1= new RiskTerritoryModel(2,"Northwest_Territory",1,155,70);
	    Vector<Integer> val1=new Vector<Integer>();
	    val.add(1);
		assertEquals(false,rttm.isAdjacent(rttm1));
	}
	@Test
	public void testcheckTagsPresent() {
		riskGameModel.initializeMapVariables();
		// test the baseMap - > go to map and remove some tags [Maps]/[Continents] etc
		assertEquals(true, riskGameModel.checkTagsPresent(riskGameModel.sbBaseMapString.toString()));
		// test the current map - > go to map and remove some tags [Maps]/[Continents]
		// etc
		assertEquals(false, riskGameModel.checkTagsPresent(riskGameModel.sbCurrentMapString.toString()));
	}

	@Test
	public void testcheckAdjacentsPresentAllTerritories() {
		riskGameModel.initializeMapVariables();
		Boolean flag = riskGameModel.checkAdjacentsPresentAllTerritories(riskGameModel.sbCurrentMapString.toString());
		assertEquals(true, flag);
	}

	@Test
	public void testcheckContinentsareValid() {
		// Check is the continents listed is present in the ones defined above
		riskGameModel.initializeMapVariables();
		Boolean flag = riskGameModel.checkContinentsareValid(riskGameModel.sbBaseMapString.toString());
		assertEquals(true, flag);
	}
	
		@Test
	public void testTradeCardArmiesCount()
	{
		
		assertEquals(5,RiskGameModel.fetchTradedArmiesCount());
		assertEquals(10,RiskGameModel.fetchTradedArmiesCount());
		assertEquals(15,RiskGameModel.fetchTradedArmiesCount());
		assertEquals(20,RiskGameModel.fetchTradedArmiesCount());
	}
	@Test
	public void testTradeCardsCount() {
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1);
		riskGameModel.setCurPlayer(rpm);
		rpm.addArmies(5);
		RiskCardModel rcm = new RiskCardModel(0,1);
		rpm.setCard(rcm);
		assertEquals(1,riskGameModel.RiskStartTurn());//Card count is 1.so reinforce, can't trade in.
		
	}
	@Test
	public void testCapture() {
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1);
		RiskPlayerModel rpm1=new RiskPlayerModel("player2",1);
		RiskGameModel  riskGameModel=new RiskGameModel("test");
		riskGameModel.players.add(rpm);
		riskGameModel.players.add(rpm1);
		riskGameModel.active=rpm;
		riskGameModel.defender=rpm1;
		RiskTerritoryModel aTerritory= new RiskTerritoryModel(1,"India",1,47,76);
		aTerritory.addArmies(5);
		RiskTerritoryModel dTerritory= new RiskTerritoryModel(2,"Japan",1,155,70);
	    dTerritory.addArmies(0);
		riskGameModel.defenseNum=2;
	    riskGameModel.setaTerritory(aTerritory);
	    riskGameModel.setdTerritory(dTerritory);
	    assertEquals(1,riskGameModel.isCaptured());//players.size=1, so he won the game
	}

}
