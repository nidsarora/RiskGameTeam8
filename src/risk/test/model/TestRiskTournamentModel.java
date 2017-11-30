/**
 * 
 */
package risk.test.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.junit.Test;

import risk.controller.RiskController;
import risk.model.RiskGameModel;
import risk.model.RiskPlayerModel;
import risk.model.RiskTournamentModel;
import risk.model.strategy.Benevolent;

/**
 * @author Vino
 *
 */
public class TestRiskTournamentModel {

	@Test
	public void testTournamentGameCount() {
		RiskGameModel riskGameModel=new RiskGameModel("test", true);
		List<RiskGameModel> riskGameModels=new ArrayList<>(3);
		riskGameModels.add(null);
		Benevolent benevolent=new Benevolent();
		RiskController rc= new RiskController("test");
		RiskPlayerModel riskplayermodel4=new RiskPlayerModel("player5",5,benevolent);
		Vector<RiskPlayerModel> players = new Vector<RiskPlayerModel>();
		players.add(riskplayermodel4);
		riskGameModel.setPlayer(players);
		ArrayList<String> mapname=new ArrayList<>();
		mapname.add("3DCliff");
		RiskTournamentModel rtmm= new RiskTournamentModel(2, 2,players,3,mapname);
		rtmm.setTestTournamentGameCount(3);
	}

}
