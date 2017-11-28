package risk.model.interfaces;

import risk.model.RiskGameModel;

public interface StrategyInterface {
	
	public void takeTurn(boolean isTest, RiskGameModel riskGameModel);
	
	public void initialReinforce(boolean isTest, RiskGameModel riskGameModel, int... territory);

	public void startTurn(boolean isTest, RiskGameModel riskGameModel);

	public void reinforce(boolean isTest, RiskGameModel riskGameModel, int... territory);

	public String attack(boolean isTest, RiskGameModel riskGameModel, int... territory);

	public String fortify(boolean isTest, RiskGameModel riskGameModel, int... territory);
}
