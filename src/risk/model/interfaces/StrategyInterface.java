package risk.model.interfaces;

import risk.model.RiskGameModel;

/**
 * The Interface StrategyInterface.
 */
public interface StrategyInterface {
	
	/**
	 * Take turn.
	 *
	 * @param isTest the is test
	 * @param riskGameModel the risk game model
	 */
	public void takeTurn(boolean isTest, RiskGameModel riskGameModel);
	
	/**
	 * Initial reinforce method of RiskGame.
	 *
	 * @param isTest the is test
	 * @param riskGameModel the risk game model
	 * @param territory the territory
	 */
	public void initialReinforce(boolean isTest, RiskGameModel riskGameModel, int... territory);

	/**
	 * Start turn method of RiskGame.
	 *
	 * @param isTest the is test
	 * @param riskGameModel the risk game model
	 */
	public void startTurn(boolean isTest, RiskGameModel riskGameModel);

	/**
	 * Reinforce method of RiskGame.
	 *
	 * @param isTest the is test
	 * @param riskGameModel the risk game model
	 * @param territory the territory
	 */
	public void reinforce(boolean isTest, RiskGameModel riskGameModel, int... territory);

	/**
	 * Attack method of RiskGame.
	 *
	 * @param isTest the is test
	 * @param riskGameModel the risk game model
	 * @param territory the territory
	 * @return the string
	 */
	public String attack(boolean isTest, RiskGameModel riskGameModel, int... territory);

	/**
	 * Fortify method of RiskGame.
	 *
	 * @param isTest the is test
	 * @param riskGameModel the risk game model
	 * @param territory the territory
	 * @return the string
	 */
	public String fortify(boolean isTest, RiskGameModel riskGameModel, int... territory);
}
