package risk.test.controller;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import risk.controller.RiskController;
import risk.model.RiskCardModel;
import risk.model.RiskPlayerModel;
import risk.model.RiskTerritoryModel;

public class TestRiskController {
	RiskController rc;
	
	@Before
	public void before()
	{
	    rc= new RiskController("test");
	}

	@Test
	public void testGeneratePanel() 
	{
		RiskPlayerModel rpm4=new RiskPlayerModel("player5",5);
		rc.setRiskCurPlayer(rpm4);
		rc.GenerateCardPanel();
		int result=rc.getCountTradeCards();
		assertEquals(0,result);
		
 	}
//	@Test
//	public void testdoesCardMatchCurrentPlayerTerritory() 
//	{
//		RiskPlayerModel rpm5=new RiskPlayerModel("player5",1);
//		RiskTerritoryModel rtm=new RiskTerritoryModel(1,"Northwest_Territory",1,155,70);
//		RiskTerritoryModel rtm1=new RiskTerritoryModel(2,"Alaska",1,47,76);
//		Vector<RiskTerritoryModel> occupiedTerritories=new Vector<RiskTerritoryModel>();
//		occupiedTerritories.add(rtm);
//		occupiedTerritories.add(rtm1);
//		rpm5.setOccupiedTerritories(occupiedTerritories);
//		rpm5.setCard(new RiskCardModel(1, 39));
//		rc.setRiskCurPlayer(rpm5);
//		rc.doesCardMatchCurrentPlayerTerritory();
//		int tc=rc.getcountMatchingCards();
//		assertEquals(0,tc);
//	}
}
