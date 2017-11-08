package risk.model.Observable;

import java.util.Observable;

import risk.helpers.Utility;
import risk.model.RiskGameModel;
import risk.model.interfaces.PhaseViewInterface;

public class RiskAttackPhaseModel extends Observable implements PhaseViewInterface {

	private String objectType = "RiskAttackPhaseModel";
	private RiskGameModel objCurrentRiskGameObject;
	private static RiskAttackPhaseModel instance = new RiskAttackPhaseModel();

	private RiskAttackPhaseModel() {
	}

	public static RiskAttackPhaseModel getInstance() {
		if (instance == null)
			instance = new RiskAttackPhaseModel();
		return instance;
	}

	public String getPhaseInformation() {
		return Utility.getPhaseInformtion(this);
	}

	@Override
	public String getObjectType() {
		// TODO Auto-generated method stub
		return objectType;
	}

	@Override
	public String getTitle() {
		return Utility.getPhaseTitle(this);
	}

	@Override
	public void isChanged() {
		// specify that my state was changed
		setChanged();
		// notify all attached Observers of a change
		notifyObservers(this);
	}

	@Override
	public String getContent() {
		return this.toString();
	}

	
	//
	// private String annouceResults() {
	// // TODO Auto-generated method stub
	// if(this.objCurrentRiskGameObject.getDefenceDieArray() !=null &&
	// this.objCurrentRiskGameObject.getAttackDieArray() != null)
	// {
	// int countDefenceWins = 0;
	// for(int match = 0; match <
	// this.objCurrentRiskGameObject.getDefenceDieArray().length ; match ++)
	// {
	// if(this.objCurrentRiskGameObject.getDefenceDieArray()[match] <
	// this.objCurrentRiskGameObject.getAttackDieArray()[match])
	// countDefenceWins--;
	// else
	// countDefenceWins++;
	// }
	//
	// return (countDefenceWins >= 0 ? "Defence" : "Attack");
	// }
	// return "No one won!";
	// }

	@Override
	public void setCurrentRiskGameObject(RiskGameModel objCurrentRiskGameContext) {
		this.setObjCurrentRiskGameObject(objCurrentRiskGameContext);
	}

	/**
	 * @param objCurrentRiskGameObject
	 *            the objCurrentRiskGameObject to set
	 */
	public void setObjCurrentRiskGameObject(RiskGameModel objCurrentRiskGameObject) {
		this.objCurrentRiskGameObject = objCurrentRiskGameObject;
	}
	
	@Override
	public String toString() {
		StringBuilder sbContentBuilder = new StringBuilder();
		sbContentBuilder.append("************" + getTitle() + "************\n");
		sbContentBuilder.append(this.getPhaseInformation() + "\n");
		sbContentBuilder.append("Player: " + objCurrentRiskGameObject.curPlayer.getName() + "\n");
		sbContentBuilder.append("Statistics:\n\n");

		sbContentBuilder.append("Territory " +

				((this.objCurrentRiskGameObject.aTerritory != null) ? this.objCurrentRiskGameObject.aTerritory.getName()
						: "")

				+ " attacks " +

				((this.objCurrentRiskGameObject.dTerritory != null) ? this.objCurrentRiskGameObject.dTerritory.getName()
						: "")

		);

		sbContentBuilder.append("\n\n");
		if (this.objCurrentRiskGameObject.getState() == this.objCurrentRiskGameObject.ATTACK_PHASE) {
			sbContentBuilder.append("The war has began!\n");
			sbContentBuilder.append("Attacking with " + this.objCurrentRiskGameObject.attackNum
					+ " dices.\n Defending with " + this.objCurrentRiskGameObject.defenseNum + " dices");
			sbContentBuilder.append("\n\n");

			if (this.objCurrentRiskGameObject.getDefenceDieArray() != null
					&& this.objCurrentRiskGameObject.getDefenceDieArray().length > 0)
				for (int defence = 0; defence <= this.objCurrentRiskGameObject.getDefenceDieArray().length
						- 1; defence++) {
					sbContentBuilder.append(
							defence + 1 + ". It was " + this.objCurrentRiskGameObject.getAttackDieArray()[defence]
									+ " vs " + this.objCurrentRiskGameObject.getDefenceDieArray()[defence] + ".");
					if (this.objCurrentRiskGameObject.getAttackDieArray()[defence] > this.objCurrentRiskGameObject
							.getDefenceDieArray()[defence])
						sbContentBuilder.append("Attacker Won this round!\n");
					else
						sbContentBuilder.append("Defender Won this round!\n");
				}

			if (this.objCurrentRiskGameObject.dTerritory.getArmies() == 0) {
				sbContentBuilder.append("Results are out!\n\n");
				sbContentBuilder
						.append("Attack successfully prevailed.\n" + this.objCurrentRiskGameObject.aTerritory.getName()
								+ " defeated " + this.objCurrentRiskGameObject.dTerritory.getName() + ".");
			}
		}
		sbContentBuilder.append("\n\n");

		if (this.objCurrentRiskGameObject.getState() == RiskGameModel.CAPTURE) {

			sbContentBuilder.append(this.objCurrentRiskGameObject.defenseNum + " armies moved from "
					+ this.objCurrentRiskGameObject.aTerritory.getName() + " to "
					+ this.objCurrentRiskGameObject.dTerritory.getName() +"\n");
		}

		if (this.objCurrentRiskGameObject.getState() == RiskGameModel.DEFEATED) {

			sbContentBuilder.append(this.objCurrentRiskGameObject.dTerritory.getName()
					+ " won!! They defended themseleves successfully from "
					+ this.objCurrentRiskGameObject.aTerritory.getName() +"\n");
		}

		return sbContentBuilder.toString();
	}
}
