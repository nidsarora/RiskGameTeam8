package risk.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import risk.test.controller.TestRiskController;
import risk.test.controller.TestRiskStartGameController;
import risk.test.model.TestRiskGameModel;

/**
 * The Class RiskGameTestSuite having all the classes tested.
 */
@RunWith(Suite.class)
@SuiteClasses({TestRiskController.class,TestRiskStartGameController.class, TestRiskGameModel.class, TestRiskTournamentModel.class})

public class RiskGameTestSuite {

	

}
