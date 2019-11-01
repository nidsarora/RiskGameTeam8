package risk.test.controller;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import risk.controller.RiskController;
import risk.model.RiskCardModel;
import risk.model.RiskGameModel;
import risk.model.RiskPlayerModel;
import risk.model.RiskTerritoryModel;
import risk.model.strategy.Aggressive;
import risk.model.strategy.Benevolent;

/**
 * The Class TestRiskController.
 */
public class TestRiskController {
	
	/** The riskcontroller. */
	RiskController rc;
	
	/**
	 * Before method.
	 */
	@Before
	public void before()
	{
	    rc= new RiskController("test");
	}

	/**
	 * Test case for Test generate panel function.
	 */
	@Test
	public void testGeneratePanel() 
	{
		Benevolent benevolent=new Benevolent();
		RiskPlayerModel riskplayermodel4=new RiskPlayerModel("player5",5,benevolent);
		rc.setRiskCurPlayer(riskplayermodel4);
		rc.GenerateCardPanel();
		int result=rc.getCountTradeCards();
		assertEquals(0,result);
		
 	}
	
	
	
	/**
	 * Test fortify armies count.
	 */
	@Test
	public void testFortifyArmiesCount() {
		RiskTerritoryModel aTerritory= new RiskTerritoryModel(1,"Alaska",1,47,76);
		aTerritory.addArmies(5);
		RiskTerritoryModel dTerritory= new RiskTerritoryModel(2,"Northwest_Territory",1,155,70);
		dTerritory.addArmies(10);
		RiskGameModel  riskGameModel=new RiskGameModel("test",true);
	    riskGameModel.setaTerritory(aTerritory);
	    riskGameModel.setdTerritory(dTerritory);
	    riskGameModel.setState(14);
	    riskGameModel.defenseNum=3;
	    rc.setRisk(riskGameModel);
	    rc.fortifyPhase(490, 350);
	    int dcount=rc.getRisk().getdTerritories().getArmies();
	    assertEquals(13,dcount);
	    int acount=rc.getRisk().getaTerritory().getArmies();
	    assertEquals(2,acount);
	}
	
	/**
	 * Test attacker dice.
	 */
	@Test
	public void testAttackerDice() {
		Aggressive aggressive=new Aggressive();
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1,aggressive);
		RiskPlayerModel rpm1=new RiskPlayerModel("player1",1,aggressive);
		RiskGameModel  riskGameModel=new RiskGameModel("test",true);
		riskGameModel.setCurPlayer(rpm);
		riskGameModel.active=rpm;
		riskGameModel.defender=rpm1;
		RiskTerritoryModel aTerritory= new RiskTerritoryModel(1,"India",1,47,76);
		aTerritory.addArmies(5);
		Vector<Integer> val=new Vector<Integer>();
	    val.add(2);
	    aTerritory.setPlayer(rpm);
	    RiskTerritoryModel dTerritory= new RiskTerritoryModel(2,"Japan",1,155,70);
	    dTerritory.addArmies(15);
	    Vector<Integer> val1=new Vector<Integer>();
	    val.add(1);
	    dTerritory.setPlayer(rpm1);
	    riskGameModel.setaTerritory(aTerritory);
	    riskGameModel.setdTerritory(dTerritory);
	    riskGameModel.defenseNum=3;
	    riskGameModel.setState(9);
	    rc.setRisk(riskGameModel);
	    rc.attackPhase(490, 260);
        int attackNum=riskGameModel.getAttack();	   
	    assertEquals(2,attackNum);
	    
	    
	}
	
	/**
	 * Test defend winner.
	 */
	@Test
	public void testDefendWinner() {
		Aggressive aggressive=new Aggressive();
		RiskPlayerModel rpm=new RiskPlayerModel("player1",1,aggressive);
		RiskPlayerModel rpm1=new RiskPlayerModel("player1",1,aggressive);
		RiskGameModel  riskGameModel=new RiskGameModel("test",true);
		riskGameModel.active=rpm1;
		riskGameModel.defender=rpm1;
		RiskTerritoryModel aTerritory= new RiskTerritoryModel(1,"India",1,47,76);
		aTerritory.addArmies(5);
		Vector<Integer> val=new Vector<Integer>();
	    val.add(2);
	    aTerritory.setPlayer(rpm);
	    RiskTerritoryModel dTerritory= new RiskTerritoryModel(2,"Japan",1,155,70);
	    dTerritory.addArmies(15);
	    Vector<Integer> val1=new Vector<Integer>();
	    val.add(1);
	    dTerritory.setPlayer(rpm1);
	    riskGameModel.setaTerritory(aTerritory);
	    riskGameModel.setdTerritory(dTerritory);
	    riskGameModel.setState(9);
	    riskGameModel.attackNum=2;
	    rc.setRisk(riskGameModel);
	    rc.attackPhase(490, 260);
        int defenseNum=riskGameModel.getDefend();	   
	    assertEquals(1,defenseNum);
	    
	    
	}
}
