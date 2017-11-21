package risk.model.strategy;

import risk.helpers.Utility;
import risk.model.RiskGameModel;
import risk.model.RiskTerritoryModel;
import risk.model.interfaces.StrategyInterface;

public class Human implements StrategyInterface {
	
	/**
	 * this function is for Risk start turn.
	 *
	 * @return the string
	 */
	@Override
	public String startTurn(boolean isTest,RiskGameModel riskGameModel) {
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
	

	
	@Override
	public String attack(boolean isTest,int territory, RiskGameModel riskGameModel) {
		if(riskGameModel.getState() == RiskGameModel.ATTACKING)
			return RiskAttacking(territory,riskGameModel);
		if(riskGameModel.getState() == RiskGameModel.ATTACK)
			return Riskattack(territory,riskGameModel);		
		if(riskGameModel.getState() == RiskGameModel.ATTACK_PHASE)
			return RiskAttackPhase(territory, riskGameModel.xCoordinate, riskGameModel.yCoordinate, riskGameModel);		
		
		
		return "";
	}
	
	@Override
	public String reinforce(boolean isTest,int territory, RiskGameModel riskGameModel) {
		if (territory != -1) // if not a country
			if (riskGameModel.getOwnership(territory) == riskGameModel.curPlayer.getPlayerIndex()) // if owned
			{
				riskGameModel.occupyTerritory(RiskGameModel.territories.elementAt(territory)); // occupy
				riskGameModel.notifyPhaseViewChange();
				return "true";
			}
		return "";		
	}
	@Override
	public String fortify(boolean isTest,int territory, RiskGameModel riskGameModel) {
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
	
	public String RiskAttackPhase(int territory,int x_coordinate,int y_coordinate, RiskGameModel risk) {
		// jInternalFrame1.setVisible(true);
					int attackArmies = risk.aTerritory.getArmies();
					int defenseArmies = risk.defenseTerritory.getArmies();
					int numofatt = 0;
					// If attackers turn
					if (risk.active == risk.curPlayer) {
						if (attackArmies > 3) {
							if (y_coordinate > 250 && y_coordinate < 280) {// if in
																			// y_coordinate
																			// coord
								if (x_coordinate > 420 && x_coordinate < 460) // If dice
																				// one
									numofatt = 1;
								if (x_coordinate > 480 && x_coordinate < 520) // if dice
																				// two
									numofatt = 2;
								if (x_coordinate > 540 && x_coordinate < 580)
									numofatt = 3;
							} // in y_coordinate coord
						} // if attcking with 3
						if (attackArmies == 3) {// if attakking with two
							if (y_coordinate > 250 && y_coordinate < 280) {
								if (x_coordinate > 460 && x_coordinate < 500)
									numofatt = 1;
								if (x_coordinate > 510 && x_coordinate < 550)
									numofatt = 2;
							} // in y_coordinate coord
						} // end if can attack with two

						if (attackArmies == 2) {// can only attack with one
							if (y_coordinate > 250 && y_coordinate < 280) {
								if (x_coordinate > 480 && x_coordinate < 520)
									numofatt = 1;
							} // in y_coordinate
						} // end only attack with one

						if (numofatt != 0) {// change player is num is selected
							risk.active = risk.defender;
							risk.setAttack(numofatt);
							Utility.writeLog(risk.getCurrentPlayer().getName() + " has " + attackArmies + " armies");
							Utility.writeLog(risk.getCurrentPlayer().getName() + " attacking with " + numofatt + " armies");
						}

					} // end attackers turn

					// If defenders turn
					else if (risk.active == risk.defender) {
						if (defenseArmies > 1 && risk.attackNum > 1) {
							if (y_coordinate > 250 && y_coordinate < 280) {
								if (x_coordinate > 460 && x_coordinate < 500)
									numofatt = 1;
								if (x_coordinate > 510 && x_coordinate < 550)
									numofatt = 2;
							}
						} else {
							if (y_coordinate > 250 && y_coordinate < 280) {
								if (x_coordinate > 480 && x_coordinate < 520)
									numofatt = 1;
							}
						}

						risk.notifyPhaseViewChange();
						if (numofatt > 0) {
							risk.setDefend(numofatt);
							risk.engageBattle();
//							if (defenseArmies - risk.defenseTerritory.getArmies() == 1) {
//								statusLabel.setText(risk.curPlayer.getName() + " has destroyed an army");
//								Utility.writeLog(risk.curPlayer.getName() + " has destroyed an army");
//							} else if (defenseArmies - risk.defenseTerritory.getArmies() == 2) {
//								statusLabel.setText(risk.curPlayer.getName() + " has destroyed two armies");
//								Utility.writeLog(risk.curPlayer.getName() + " has destroyed two armies");
//							} else if (attackArmies - risk.aTerritory.getArmies() == 1) {
//								statusLabel.setText(risk.curPlayer.getName() + " has lost an army");
//								Utility.writeLog(risk.curPlayer.getName() + " has lost an army");
//							} else if (attackArmies - risk.aTerritory.getArmies() == 2) {
//								statusLabel.setText(risk.curPlayer.getName() + " has lost two armies");
//								Utility.writeLog(risk.curPlayer.getName() + " has lost two armies");
//							}
//
//							if (risk.aTerritory.getArmies() == 1) {
//								risk.setState(RiskGameModel.ACTIVE_TURN);
//								statusLabel.setText(risk.curPlayer.getName() + " has lost the battle");
//								AttackButton.setText("Attack");
//								FortifyButton.setVisible(true);
//								EndButton.setVisible(true);
//								risk.defenseNum = 0;
//								risk.attackNum = 0;
//								risk.defenseTerritory = null;
//								risk.aTerritory = null;
//							}
						}

					} /// end if defenders turn
					return String.valueOf(numofatt); // Print details in risk model 
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
