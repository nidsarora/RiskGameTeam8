package risk.model.strategy;

import risk.model.RiskGameModel;
import risk.model.interfaces.StrategyInterface;

/**
 * The Class Benevolent.
 */
public class Benevolent implements StrategyInterface {
	
	@Override
	public void startTurn(boolean isTest, RiskGameModel riskGameModel) {
		
		return;
	}

		@Override
	public void reinforce(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		
		return;
	}

		@Override
	public void attack(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		
		return;
	}

		@Override
	public void fortify(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		
		return;
	}

		@Override
	public void initialReinforce(boolean isTest, RiskGameModel riskGameModel, int... territory) {
		return;
	}

		@Override
	public void takeTurn(boolean isTest, RiskGameModel riskGameModel) {
		return;
	}

}
