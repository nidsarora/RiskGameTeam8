package risk.model.strategy;

import risk.model.RiskGameModel;
import risk.model.RiskTerritoryModel;
import risk.model.interfaces.StrategyInterface;

public class Human implements StrategyInterface {
	@Override
	public String attack(int territory, RiskGameModel riskGameModel) {
		if(riskGameModel.getState() == riskGameModel.ATTACKING)
			return RiskAttacking(territory,riskGameModel);
		if(riskGameModel.getState() == riskGameModel.ATTACK)
			return Riskattack(territory,riskGameModel);		
		
		return "";
	}
	
	@Override
	public String reinforce(int territory, RiskGameModel riskGameModel) {
		if (territory != -1) // if not a country
			if (riskGameModel.getOwnership(territory) == riskGameModel.curPlayer.getPlayerIndex()) // if owned
			{
				riskGameModel.occupyTerritory(riskGameModel.territories.elementAt(territory)); // occupy
				riskGameModel.notifyPhaseViewChange();
				return "true";
			}
		return "";		
	}
	@Override
	public String fortify(int territory, RiskGameModel riskGameModel) {
		// TODO Auto-generated method stub
		if(riskGameModel.getState() == riskGameModel.FORTIFYING)
			return RiskFortifying(territory,riskGameModel);
		if(riskGameModel.getState() == riskGameModel.FORTIFY)
			return RiskFortify(territory,riskGameModel);		
		
		return "";
	}
	
	
	/**
	 * this function is for Risk attacking.
	 *
	 * @param country
	 *            the country
	 * @return the string
	 */
	public String RiskAttacking(int country,RiskGameModel riskGameModel) {
		if (country != -1) {// not a country
			RiskTerritoryModel d = riskGameModel.territories.elementAt(country); // defending
			// territory

			System.out.println(riskGameModel.aTerritory.getAdjacents().size());

			for (int index : riskGameModel.aTerritory.getAdjacents()) {
				System.out.println(riskGameModel.territories.get(index));
			}

			if (riskGameModel.getOwnership(country) == riskGameModel.curPlayer.getPlayerIndex())
				return "You own that territory.";
			if (riskGameModel.aTerritory.isAdjacent(d)) {// if its adjacent...
				riskGameModel.setState(riskGameModel.ATTACK_PHASE);
				riskGameModel.defenseTerritory = d;
				riskGameModel.defender = d.getPlayer();
			} else
				// if its not adjacent
				System.out.println("That territory is not adjacent, try again.");

			return "That territory is not adjacent, try again.";

		}
		riskGameModel.notifyPhaseViewChange();
		return "";

	}
	
	/**
	 * This function is about Risk attack.
	 *
	 * @param country
	 *            the country
	 * @return the string
	 */
	public String Riskattack(int country,RiskGameModel riskGameModel) {
		if (country != -1)// if not a country
			if (riskGameModel.getOwnership(country) == riskGameModel.curPlayer.getPlayerIndex()) {
				if (riskGameModel.territories.elementAt(country).getArmies() < 2)
					return "Not enough armies to battle, need at least 2";
				else {
					riskGameModel.setState(riskGameModel.ATTACKING);
					riskGameModel.aTerritory = riskGameModel.territories.elementAt(country);
					return "true";
				}
			} // end is curPlayers country
		riskGameModel.notifyPhaseViewChange();
		return "";
	}
	

	/**
	 * this function is for Risk start turn.
	 *
	 * @return the string
	 */
	public String RiskStartTurn(boolean isTest,RiskGameModel riskGameModel) {
		riskGameModel.currentPlayerBonusArmiesRecieved = riskGameModel.turnBonus();
		riskGameModel.curPlayer.addArmies(riskGameModel.currentPlayerBonusArmiesRecieved);
		if (!isTest)
			riskGameModel.notifyPhaseViewChange();
		// recive turn bonus
		if (riskGameModel.curPlayer.getCard().size() > 5) {
			riskGameModel.setState(riskGameModel.TRADE_CARDS);
			return "tradecards";
		} else {
			riskGameModel.setState(riskGameModel.REINFORCE);
			return "reinforce";
		}
	}
	
	
	/**
	 * this function is for Risk trade cards.
	 *
	 * @param country
	 *            the country
	 * @return the string
	 */
	public String RiskTradeCards(int country,RiskGameModel riskGameModel) {
		if (country != -1) // if not a country
			if (riskGameModel.getOwnership(country) == riskGameModel.curPlayer.getPlayerIndex()) // if
			// owned
			{
				riskGameModel.occupyTerritory(riskGameModel.territories.elementAt(country)); // occupy
				riskGameModel.notifyPhaseViewChange();
				riskGameModel.setState(riskGameModel.REINFORCE);
				return "true";
			}
		return "";
	}
	
	
	/**
	 * this function is for Risk fortify.
	 *
	 * @param country
	 *            the country
	 * @return the string
	 */
	public String RiskFortify(int country,RiskGameModel riskGameModel) {
		if (country != -1) {
			if (riskGameModel.getOwnership(country) == riskGameModel.curPlayer.getPlayerIndex()) {
				riskGameModel.setState(riskGameModel.FORTIFYING);
				riskGameModel.aTerritory = riskGameModel.territories.elementAt(country);
				riskGameModel.notifyPhaseViewChange(); // get the first country to
												// fotify
				return "true";
			}
		}
		return "false";
	}
	
	public String RiskFortifying(int country, RiskGameModel riskGameModel) {
		if (country != -1) {// not a country
			riskGameModel.defenseTerritory = riskGameModel.territories.elementAt(country); // move to
			// territory
			if (riskGameModel.getOwnership(country) == riskGameModel.curPlayer.getPlayerIndex())
				if (riskGameModel.aTerritory.isAdjacent(riskGameModel.defenseTerritory)) {// if its
					// adjacent...

//					if (flag)        //Commented 21 Nov - Does not seem to be neccessary - paul
//						return "true";
					riskGameModel.notifyPhaseViewChange();
					riskGameModel.setState(riskGameModel.FORTIFY_PHASE);
					return "true";
				} else
					return "false";
		} // end if a county
		return "";
	}


}
