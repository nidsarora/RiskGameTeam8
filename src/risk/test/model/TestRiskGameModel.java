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

/**
 * The Class Test Risk Game Model.
 */
public class TestRiskGameModel {
	
	/** The risk game model. */
	RiskGameModel riskGameModel;

	/**
	 * Before.
	 */
	@Before
	public void before() {
		riskGameModel = new RiskGameModel("test");
	}

	/**
	 * Test occupy territory.
	 */
	@Test
	public void testOccupyTerritory() {

		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1);
		riskplayermodel.setCard(new RiskCardModel(1, 39));
		riskGameModel.setCurPlayer(riskplayermodel);
		RiskTerritoryModel rtm = new RiskTerritoryModel(1, "Northwest_Territory", 2, 155, 70);
		boolean result = riskGameModel.occupyTerritory(rtm);
		assertEquals(false, result);
	}

	/**
	 * Test distribute.
	 */
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

	/**
	 * Test turn bonus.
	 */
	@Test
	public void testTurnBonus() {
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1);
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
		riskplayermodel.setOccupiedTerritories(occupiedTerritories);
		riskGameModel.setCurPlayer(riskplayermodel);
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

	/**
	 * Test bonus territory.
	 */
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

	/**
	 * Test bonus continent.
	 */
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

	/**
	 * Test add player.
	 */
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


    
    /**
     * Test get ownership.
     */
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
	
	/**
	 * Test risk fortifying.
	 */

	@Test
	public void testRiskFortifying() {
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1);
		riskGameModel.setCurPlayer(riskplayermodel);
		RiskTerritoryModel riskterritorymodel = new RiskTerritoryModel(1, "Alaska", 1, 47, 76);
		Vector<Integer> val = new Vector<Integer>();
		val.add(2);
		riskterritorymodel.setPlayer(riskplayermodel);
		RiskTerritoryModel riskterritorymodel1 = new RiskTerritoryModel(2, "Northwest_Territory", 1, 155, 70);
		Vector<Integer> val1 = new Vector<Integer>();
		val.add(1);
		riskterritorymodel1.setPlayer(riskplayermodel);
		riskterritorymodel.setAdjacent(val);
		riskterritorymodel1.setAdjacent(val1);
		riskGameModel.territories = new Vector<RiskTerritoryModel>();
		riskGameModel.territories.addElement(riskterritorymodel);
		riskGameModel.territories.addElement(riskterritorymodel1);
		riskGameModel.aTerritory = riskterritorymodel;
		String fortify = riskGameModel.RiskFortifying(1, true);
		assertEquals("true", fortify);

	}

	
	/**
	 * Test risk not fortifying.
	 */

	@Test
	public void testRiskNotFortifying() {
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1);
		riskGameModel.setCurPlayer(riskplayermodel);
		RiskTerritoryModel riskterritorymodel = new RiskTerritoryModel(1, "India", 1, 47, 76);
		Vector<Integer> val = new Vector<Integer>();
		val.add(2);
		riskterritorymodel.setPlayer(riskplayermodel);
		RiskTerritoryModel riskterritorymodel1 = new RiskTerritoryModel(2, "Japan", 1, 155, 70);
		Vector<Integer> val1 = new Vector<Integer>();
		val.add(1);
		riskterritorymodel1.setPlayer(riskplayermodel);
		riskGameModel.territories = new Vector<RiskTerritoryModel>();
		riskGameModel.territories.addElement(riskterritorymodel);
		riskGameModel.territories.addElement(riskterritorymodel1);
		riskGameModel.aTerritory = riskterritorymodel;
		String nfortify = riskGameModel.RiskFortifying(1, true);
		assertEquals("false", nfortify);

	}

	
	/**
	 * Test risk attack.
	 */

	@Test
	public void testRiskAttack() {
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1);
		RiskPlayerModel riskplayermodel1 = new RiskPlayerModel("player1", 1);
		riskGameModel.setCurPlayer(riskplayermodel);
		RiskTerritoryModel riskterritorymodel = new RiskTerritoryModel(1, "India", 1, 47, 76);
		Vector<Integer> val = new Vector<Integer>();
		val.add(2);
		riskterritorymodel.setPlayer(riskplayermodel);
		RiskTerritoryModel riskterritorymodel1 = new RiskTerritoryModel(2, "Japan", 1, 155, 70);
		Vector<Integer> val1 = new Vector<Integer>();
		val.add(1);
		riskterritorymodel1.setPlayer(riskplayermodel1);
		riskterritorymodel.setAdjacent(val);
		riskterritorymodel1.setAdjacent(val1);
		riskGameModel.territories = new Vector<RiskTerritoryModel>();
		riskGameModel.territories.addElement(riskterritorymodel);
		riskGameModel.territories.addElement(riskterritorymodel1);
		riskterritorymodel.addArmies(7);

		assertEquals("true", riskGameModel.Riskattack(0));

	}

	
	/**
	 * Test not enough armies to attack.
	 */

	@Test
	public void testNOEnoughArmiesToAttack() {
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1);
		RiskPlayerModel riskplayermodel1 = new RiskPlayerModel("player1", 1);
		riskGameModel.setCurPlayer(riskplayermodel);
		RiskTerritoryModel riskterritorymodel = new RiskTerritoryModel(1, "India", 1, 47, 76);
		Vector<Integer> val = new Vector<Integer>();
		val.add(2);
		riskterritorymodel.setPlayer(riskplayermodel);
		RiskTerritoryModel riskterritorymodel1 = new RiskTerritoryModel(2, "Japan", 1, 155, 70);
		Vector<Integer> val1 = new Vector<Integer>();
		val.add(1);
		riskterritorymodel1.setPlayer(riskplayermodel1);
		riskterritorymodel.setAdjacent(val);
		riskterritorymodel1.setAdjacent(val1);
		riskGameModel.territories = new Vector<RiskTerritoryModel>();
		riskGameModel.territories.addElement(riskterritorymodel);
		riskGameModel.territories.addElement(riskterritorymodel1);
		riskplayermodel.addArmies(1);

		assertEquals("Not enough armies to battle, need at least 2", riskGameModel.Riskattack(0));

	}
	
	/**
	 * Test adjacent territory.
	 */
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

	
	/**
	 * Test not adjacent territory.
	 */

	@Test
	public void testNotAdjacentTerritory() {
		RiskTerritoryModel riskterritorymodel = new RiskTerritoryModel(1, "Alaska", 1, 47, 76);
		Vector<Integer> val = new Vector<Integer>();
		val.add(2);
		RiskTerritoryModel riskterritorymodel1 = new RiskTerritoryModel(2, "Northwest_Territory", 1, 155, 70);
		Vector<Integer> val1 = new Vector<Integer>();
		val.add(1);
		assertEquals(false, riskterritorymodel.isAdjacent(riskterritorymodel1));
	}
	
	/**
	 * Test check tags present.
	 */
	@Test
	public void testcheckTagsPresent() {
		riskGameModel.initializeMapVariables();
		// test the baseMap - > go to map and remove some tags [Maps]/[Continents] etc
		assertEquals(true, riskGameModel.checkTagsPresent(riskGameModel.sbBaseMapString.toString()));
		// test the current map - > go to map and remove some tags [Maps]/[Continents]
		// etc
		assertEquals(true, riskGameModel.checkTagsPresent(riskGameModel.sbCurrentMapString.toString()));
	}

	/**
	 * Test check adjacent present all territories.
	 */
	@Test
	public void testcheckAdjacentsPresentAllTerritories() {
		riskGameModel.initializeMapVariables();
		Boolean flag = riskGameModel.checkAdjacentsPresentAllTerritories(riskGameModel.sbCurrentMapString.toString());
		assertEquals(true, flag);
	}

	/**
	 * Test check continents are valid.
	 */
	@Test
	public void testcheckContinentsareValid() {
		// Check is the continents listed is present in the ones defined above
		riskGameModel.initializeMapVariables();
		Boolean flag = riskGameModel.checkContinentsareValid(riskGameModel.sbBaseMapString.toString());
		assertEquals(true, flag);
	}


	
		/**
		 * Test trade card armies count.
		 */
		@Test
	public void testTradeCardArmiesCount()
	{
		
		assertEquals(5,RiskGameModel.fetchTradedArmiesCount());
		assertEquals(10,RiskGameModel.fetchTradedArmiesCount());
		assertEquals(15,RiskGameModel.fetchTradedArmiesCount());
		assertEquals(20,RiskGameModel.fetchTradedArmiesCount());
	}
	
	/**
	 * Test trade cards count.
	 */
	@Test
	public void testTradeCardsCount() {
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1);
		riskGameModel.setCurPlayer(riskplayermodel);
		riskplayermodel.addArmies(5);
		RiskCardModel riskCardModel = new RiskCardModel(0, 1);
		riskplayermodel.setCard(riskCardModel);
		riskGameModel.RiskStartTurn(true);

		assertEquals(1, riskplayermodel.getCard().size());// Card count is 1.so reinforce, can't trade in.

	}
	
	/**
	 * Test capture.
	 */
	@Test
	public void testCapture() {
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1);
		RiskPlayerModel riskplayermodel1 = new RiskPlayerModel("player2", 1);
		RiskGameModel riskGameModel = new RiskGameModel("test");
		riskGameModel.players.add(riskplayermodel);
		riskGameModel.players.add(riskplayermodel1);
		riskGameModel.active = riskplayermodel;
		riskGameModel.defender = riskplayermodel1;
		RiskTerritoryModel aTerritory = new RiskTerritoryModel(1, "India", 1, 47, 76);
		aTerritory.addArmies(5);
		RiskTerritoryModel dTerritory= new RiskTerritoryModel(2,"Japan",1,155,70);
	    dTerritory.addArmies(0);
		riskGameModel.defenseNum=2;
	    riskGameModel.setaTerritory(aTerritory);
	    riskGameModel.setdTerritory(dTerritory);
	    assertEquals(1,riskGameModel.isCaptured());//players.size=1, so he won the game
	}

}
