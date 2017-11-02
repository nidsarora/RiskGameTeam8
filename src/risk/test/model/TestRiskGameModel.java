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
	public void before()
	{
		 riskGameModel=new RiskGameModel("test");
	}
	@Test
	public void testOccupyTerritory() {
		
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1);
		rpm.setCard(new RiskCardModel(1, 39));
		riskGameModel.setCurPlayer(rpm);
		RiskTerritoryModel rtm=new RiskTerritoryModel(1,"Northwest_Territory",2,155,70);
		boolean result=riskGameModel.occupyTerritory(rtm);
		assertEquals(false,result);
	}
	@Test
	public void testDistribute() 
	{
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
	public void testTurnBonus() 
	{
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
	public void testBonusTerritory()
	{
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1);
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
		int res = riskGameModel.collectReinforcements();
		assertEquals(4,res);
		
	}
	@Test
	public void testBonusContinent()
	{
		RiskPlayerModel rpm=new RiskPlayerModel("player1",2);
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
		riskGameModel.setCurPlayer(rpm);
		int con_bonus = riskGameModel.collectReinforcementsFromContinent();
		System.out.println(con_bonus);
		
	}
	@Test
	public void testAddplayer() 
	{
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
		riskGameModel.setPlayer(players);
		boolean result=riskGameModel.addPlayer("player8");
		assertEquals(false,result);
	}
	@Test
	public void testNewFormatLoadMap() 
	{
		riskGameModel.loadMap_newformat();
		assertEquals(42,riskGameModel.getTerritories().size());
	}
	@Test
	public void testAdjacentTerritory() 
	{
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
	
	
}
