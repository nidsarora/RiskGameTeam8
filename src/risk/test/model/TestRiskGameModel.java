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
import risk.model.strategy.Aggressive;
import risk.model.strategy.Benevolent;

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
		riskGameModel = new RiskGameModel("test",true);
	}

	/**
	 * Test occupy territory.
	 */
	@Test
	public void testNotOccupyTerritory() {
                Aggressive aggressive=new Aggressive();
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1, aggressive);
		RiskPlayerModel riskplayermodel1=new RiskPlayerModel("player1",1,aggressive);
		riskplayermodel.addArmies(5);
		riskGameModel.setCurPlayer(riskplayermodel);
		RiskTerritoryModel rtm = new RiskTerritoryModel(2, "Northwest_Territory", 2, 155, 70);
		rtm.setPlayer(riskplayermodel1);
		boolean result = riskGameModel.occupyTerritory(rtm);
		assertEquals(false, result);
	}
	
	/**
	 * Test occupy territory.
	 */
	@Test
	public void testOccupyTerritory() {
		Aggressive aggressive=new Aggressive();
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1,aggressive);
		rpm.addArmies(5);
		riskGameModel.setCurPlayer(rpm);
		RiskTerritoryModel rtm=new RiskTerritoryModel(1,"Northwest_Territory",2,155,70);
		rtm.setPlayer(rpm);
		boolean result=riskGameModel.occupyTerritory(rtm);
		assertEquals(true,result);
	}


	/**
	 * Test distribute.
	 */
	@Test
	public void testDistribute() {
		Aggressive aggressive=new Aggressive();
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1,aggressive);
		RiskPlayerModel riskplayermodel1 = new RiskPlayerModel("player2", 2,aggressive);
		RiskPlayerModel riskplayermodel2 = new RiskPlayerModel("player3", 3,aggressive);
		RiskPlayerModel riskplayermodel3 = new RiskPlayerModel("player4", 4,aggressive);
		RiskPlayerModel riskplayermodel4 = new RiskPlayerModel("player5", 5,aggressive);
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
		Aggressive aggressive=new Aggressive();
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1,aggressive);
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

	/**
	 * Test bonus territory.
	 */
	@Test
	public void testBonusTerritory() {
		Aggressive aggressive=new Aggressive();
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1,aggressive);
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
		Aggressive aggressive=new Aggressive();
		RiskPlayerModel rpm=new RiskPlayerModel("player1",2,aggressive);
		RiskTerritoryModel rtm=new RiskTerritoryModel(1,"Territory1",2,155,70);
		RiskTerritoryModel rtm1=new RiskTerritoryModel(2,"Territory2",2,280,134);
		RiskTerritoryModel rtm2=new RiskTerritoryModel(3,"Territory3",2,280,134);
		RiskTerritoryModel rtm3=new RiskTerritoryModel(4,"Territory4",2,280,134);
		RiskTerritoryModel rtm4=new RiskTerritoryModel(5,"Territory5",2,280,134);
		RiskTerritoryModel rtm5=new RiskTerritoryModel(6,"Territory6",2,280,134);
		RiskTerritoryModel rtm6=new RiskTerritoryModel(7,"Territory7",2,280,134);
		RiskTerritoryModel rtm7=new RiskTerritoryModel(8,"Territory8",2,280,134);
		Vector<RiskTerritoryModel> occupiedTerritories=new Vector<RiskTerritoryModel>();
		occupiedTerritories.add(rtm);
		occupiedTerritories.add(rtm1);
		occupiedTerritories.add(rtm2);
		occupiedTerritories.add(rtm3);
		occupiedTerritories.add(rtm4);
		occupiedTerritories.add(rtm5);
		occupiedTerritories.add(rtm6);
		occupiedTerritories.add(rtm7);
		rpm.setOccupiedTerritories(occupiedTerritories);
		riskGameModel.setCurPlayer(rpm);
		Vector<Integer> val=new Vector<Integer>();
		val.add(1);
		val.add(2);
		val.add(3);
		val.add(4);
		val.add(5);
		val.add(6);
		RiskContinentModel rcm=new RiskContinentModel("Africa",val,3);
		Vector<Integer> val1=new Vector<Integer>();
		val1.add(1);
		val1.add(2);
		val1.add(3);
		val1.add(4);
		val1.add(5);
		val1.add(6);
		val1.add(7);
		RiskContinentModel rcm1=new RiskContinentModel("Europe",val1,5);
		Vector<RiskContinentModel> continents=new Vector<RiskContinentModel>();
		continents.add(rcm);
		continents.add(rcm1);
		riskGameModel.setContinents(continents);
		int con_bonus = riskGameModel.collectReinforcementsFromContinent();
		assertEquals(8,con_bonus);

	}

	/**
	 * Test add player.
	 */
	@Test
	public void testAddplayer() {
		Aggressive aggressive=new Aggressive();
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1,aggressive);
		RiskPlayerModel riskplayermodel1 = new RiskPlayerModel("player2", 2,aggressive);
		RiskPlayerModel riskplayermodel2 = new RiskPlayerModel("player3", 3,aggressive);
		RiskPlayerModel riskplayermodel3 = new RiskPlayerModel("player4", 4,aggressive);
		RiskPlayerModel riskplayermodel4 = new RiskPlayerModel("player5", 5,aggressive);
		Vector<RiskPlayerModel> players = new Vector<RiskPlayerModel>();
		players.add(riskplayermodel);
		players.add(riskplayermodel1);
		players.add(riskplayermodel2);
		players.add(riskplayermodel3);
		players.add(riskplayermodel4);
		riskGameModel.setPlayer(players);
		boolean result=riskGameModel.addPlayer("player5",aggressive);
		assertEquals(true,result);
	
	}
	
	/**
	 * Test addplayer failure.
	 */
	@Test
	public void testAddplayerFailure() {
		Aggressive aggressive=new Aggressive();
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1,aggressive);
		RiskPlayerModel riskplayermodel1 = new RiskPlayerModel("player2", 2,aggressive);
		RiskPlayerModel riskplayermodel2 = new RiskPlayerModel("player3", 3,aggressive);
		RiskPlayerModel riskplayermodel3 = new RiskPlayerModel("player4", 4,aggressive);
		RiskPlayerModel riskplayermodel4 = new RiskPlayerModel("player5", 5,aggressive);
		RiskPlayerModel riskplayermodel5 = new RiskPlayerModel("player5", 5,aggressive);
		RiskPlayerModel riskplayermodel6 = new RiskPlayerModel("player5", 5,aggressive);
		RiskPlayerModel riskplayermodel7 = new RiskPlayerModel("player5", 5,aggressive);
		Vector<RiskPlayerModel> players = new Vector<RiskPlayerModel>();
		players.add(riskplayermodel);
		players.add(riskplayermodel1);
		players.add(riskplayermodel2);
		players.add(riskplayermodel3);
		players.add(riskplayermodel4);
		players.add(riskplayermodel5);
		players.add(riskplayermodel6);
		players.add(riskplayermodel7);
		riskGameModel.setPlayer(players);
		boolean result=riskGameModel.addPlayer("player8",aggressive);
		assertEquals(false,result);
	
	}

    
    /**
     * Test get ownership.
     */
    @Test
	public void testGetOwnership() 
	{
    	Benevolent benevolent=new Benevolent();
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1,benevolent);
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
		Benevolent benevolent=new Benevolent();
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1,benevolent);
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
		Benevolent benevolent=new Benevolent();
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1,benevolent);
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
	 @Test
        public void testSaveGame() {

	riskGameModel.iter = 0;
	riskGameModel.attackNum = 5;
	riskGameModel.defenseNum = 3;
	riskGameModel.saveGame(riskGameModel);
	RiskGameModel riskmo = null;
	try {
	    FileInputStream fileIn = new FileInputStream("SavedRisk\\RiskNonStaticModel.ser");
	    ObjectInputStream in = new ObjectInputStream(fileIn);
	    riskmo = (RiskGameModel) in.readObject();
	    RiskGameModelSerializable riskStaticComponents = (RiskGameModelSerializable) new ObjectInputStream(
		    new FileInputStream("SavedRisk\\RiskStaticModel.ser")).readObject();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	assertEquals(riskGameModel.attackNum, riskmo.attackNum);
	assertEquals(riskGameModel.defenseNum, riskmo.defenseNum);
    }
    @Test
    public void testload(){
	RiskGameModel riskmo = null;
	try {
	    FileInputStream fileIn = new FileInputStream("SavedRisk\\RiskNonStaticModel.ser");
	    ObjectInputStream in = new ObjectInputStream(fileIn);
	    riskmo = (RiskGameModel) in.readObject();
	    RiskGameModelSerializable riskStaticComponents = (RiskGameModelSerializable) new ObjectInputStream(
		    new FileInputStream("SavedRisk\\RiskStaticModel.ser")).readObject();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	riskGameModel.iter = 1;
	riskGameModel.attackNum = 5;
	riskGameModel.defenseNum = 3;
	assertEquals(riskGameModel.attackNum, riskmo.attackNum);
	assertEquals(riskGameModel.defenseNum, riskmo.defenseNum);
	
    }
	
	/**
	 * Test get ownership fails.
	 */
	@Test
	public void testGetOwnershipFails() 
	{
    	Benevolent benevolent=new Benevolent();
		RiskPlayerModel rpm=new RiskPlayerModel("player1",0,benevolent);
		riskGameModel.setCurPlayer(rpm);
		RiskTerritoryModel rttm= new RiskTerritoryModel(9,"Alaska",1,47,76);
		rttm.setPlayer(rpm);
		riskGameModel.territories= new Vector<RiskTerritoryModel>();
	    riskGameModel.territories.addElement(rttm);
		int c=riskGameModel.getOwnership(0);
		assertEquals(0,c);
	}

	
	/**
	 * Test risk attack.
	 */

	@Test
	public void testRiskAttack() {
		Benevolent benevolent=new Benevolent();
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1,benevolent);
		RiskPlayerModel riskplayermodel1 = new RiskPlayerModel("player1", 1,benevolent);
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
		Benevolent benevolent=new Benevolent();
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1,benevolent);
		RiskPlayerModel riskplayermodel1 = new RiskPlayerModel("player1", 1,benevolent);
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
		assertEquals(false, riskGameModel.checkTagsPresent(riskGameModel.sbCurrentMapString.toString()));
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
		Benevolent benevolent=new Benevolent();
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1,benevolent);
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
		Benevolent benevolent=new Benevolent();
		RiskPlayerModel riskplayermodel = new RiskPlayerModel("player1", 1,benevolent);
		RiskPlayerModel riskplayermodel1 = new RiskPlayerModel("player2", 1,benevolent);
		RiskGameModel riskGameModel = new RiskGameModel("test",true);
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
	    assertEquals(9,riskGameModel.isCaptured());//players.size=1, so he won the game
	}
	/**
	 * Testdoes card match current player territory.
	 */
        @Test
	public void testdoesCardMatchCurrentPlayerTerritory() 
	{
		Benevolent benevolent=new Benevolent();
		RiskPlayerModel rpm5=new RiskPlayerModel("player5",1,benevolent);
		RiskTerritoryModel rtm=new RiskTerritoryModel(2,"Northwest_Territory",1,155,70);
		RiskTerritoryModel rtm1=new RiskTerritoryModel(1,"Alaska",1,47,76);
		Vector<RiskTerritoryModel> occupiedTerritories=new Vector<RiskTerritoryModel>();
		occupiedTerritories.add(rtm);
		occupiedTerritories.add(rtm1);
		rpm5.setOccupiedTerritories(occupiedTerritories);
		RiskCardModel rcm = new RiskCardModel(1,1);
			riskGameModel.setRiskCurPlayer(rpm5);
			int tc=riskGameModel.doesCardMatchCurrentPlayerTerritory();
			assertEquals(2,tc);
		}
	 
 	/**
	 * Test card not matching current player territory.
	 */
 	@Test
    	public void testCardNotMatchingCurrentPlayerTerritory() 
    	{
    		Benevolent benevolent=new Benevolent();
    		RiskPlayerModel rpm5=new RiskPlayerModel("player5",1,benevolent);
    		RiskTerritoryModel rtm=new RiskTerritoryModel(3,"Northwest_Territory",1,155,70);
    		RiskTerritoryModel rtm1=new RiskTerritoryModel(4,"Alaska",1,47,76);
    		Vector<RiskTerritoryModel> occupiedTerritories=new Vector<RiskTerritoryModel>();
    		occupiedTerritories.add(rtm);
    		occupiedTerritories.add(rtm1);
    		rpm5.setOccupiedTerritories(occupiedTerritories);
    		RiskCardModel rcm = new RiskCardModel(1,5);
    			riskGameModel.setRiskCurPlayer(rpm5);
    			int tc=riskGameModel.doesCardMatchCurrentPlayerTerritory();
    			boolean result=riskGameModel.doesCardMatchCurrentPlayerTerritory(tc);
    			assertEquals(false,result);
    		}
}
