package risk.model.Observable;

import java.io.Serializable;
import java.util.Observable;

import risk.helpers.Utility;
import risk.model.RiskGameModel;
import risk.model.interfaces.PhaseViewInterface;

/**
 * The Class Risk Attack Phase Model.
 */
public class RiskAttackPhaseModel extends Observable implements PhaseViewInterface,Serializable {

	/** RiskAttackPhaseModel object. */
	private String objectType = "RiskAttackPhaseModel";

	/** The object of current risk game. */
	private RiskGameModel objCurrentRiskGameObject;

	/** The instance. */
	private static RiskAttackPhaseModel instance = new RiskAttackPhaseModel();

	/**
	 * Instantiates a new risk attack phase model.
	 */
	private RiskAttackPhaseModel() {
	}

	/**
	 * Gets the single instance of Risk Attack Phase Model.
	 *
	 * @return single instance of Risk Attack Phase Model
	 */
	public static RiskAttackPhaseModel getInstance() {
		if (instance == null)
			instance = new RiskAttackPhaseModel();
		return instance;
	}

	/**
	 * Gets the phase information
	 *
	 * @return String Phase Information
	 */
	public String getPhaseInformation() {
		return Utility.getPhaseInformtion(this);
	}
	/**
	 * Gets the phase information
	 *
	 * @return String get object type
	 */
	@Override
	public String getObjectType() {
		return objectType;
	}

	@Override
	public String getTitle() {
		return Utility.getPhaseTitle(this);
	}

	@Override
	public void isChanged() {
		setChanged();
		notifyObservers(this);
	}

	@Override
	public String getContent() {
		return this.toString();
	}

	@Override
	public void setCurrentRiskGameObject(RiskGameModel objCurrentRiskGameContext) {
		this.setObjCurrentRiskGameObject(objCurrentRiskGameContext);
	}

	/**
	 * Sets the obj current risk game object.
	 *
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

				((this.objCurrentRiskGameObject.aTerritory != null)
						? this.objCurrentRiskGameObject.aTerritory.getName() + " with "
								+ this.objCurrentRiskGameObject.aTerritory.getArmies() + " armies "
						: "")

				+ " attacks " +

				((this.objCurrentRiskGameObject.defenseTerritory != null)
						? this.objCurrentRiskGameObject.defenseTerritory.getName() + " with "
								+ this.objCurrentRiskGameObject.defenseTerritory.getArmies() + " armies "
						: "")

		);

		sbContentBuilder.append("\n\n");
		if (this.objCurrentRiskGameObject.getState() == this.objCurrentRiskGameObject.ATTACK_PHASE) {
			sbContentBuilder.append("The war has began!\n");
			sbContentBuilder.append("Attacking with " + this.objCurrentRiskGameObject.attackNum
					+ " dices.\n Defending with " + this.objCurrentRiskGameObject.defenseNum + " dices");
			sbContentBuilder.append("\n\n");

			if (this.objCurrentRiskGameObject.getDefenceDieArray() != null
					&& this.objCurrentRiskGameObject.getDefenceDieArray().length > 0
					&& this.objCurrentRiskGameObject.getAttackDieArray() != null
					&& this.objCurrentRiskGameObject.getAttackDieArray().length > 0)
				for (int defence = 0; defence < this.objCurrentRiskGameObject.getDefenceDieArray().length; defence++) {
					try {
						sbContentBuilder.append(
								defence + 1 + ". It was " + this.objCurrentRiskGameObject.getAttackDieArray()[defence]
										+ " vs " + this.objCurrentRiskGameObject.getDefenceDieArray()[defence] + ".");
						if (this.objCurrentRiskGameObject.getAttackDieArray()[defence] > this.objCurrentRiskGameObject
								.getDefenceDieArray()[defence])
							sbContentBuilder.append("Attacker Won this round! Count -"
									+ this.objCurrentRiskGameObject.aTerritory.getArmies() + "\n");
						else
							sbContentBuilder.append("Defender Won this round! Count -"
									+ this.objCurrentRiskGameObject.defenseTerritory.getArmies() + "\n");
					}

					catch (Exception e) {
						System.out.println(defence + " " + this.objCurrentRiskGameObject.getAttackDieArray().length
								+ this.objCurrentRiskGameObject.getDefenceDieArray().length);
					}
				}
			if (this.objCurrentRiskGameObject.defenseTerritory.getArmies() == 0) {
				sbContentBuilder.append("Results are out!\n\n");
				sbContentBuilder
						.append("Attack successfully prevailed.\n" + this.objCurrentRiskGameObject.aTerritory.getName()
								+ " defeated " + this.objCurrentRiskGameObject.defenseTerritory.getName() + ".");

				sbContentBuilder.append("Attack count - " + this.objCurrentRiskGameObject.aTerritory.getArmies()
						+ " defence count - " + this.objCurrentRiskGameObject.defenseTerritory.getArmies());
			}
		}
		sbContentBuilder.append("\n\n");

		if (this.objCurrentRiskGameObject.getState() == RiskGameModel.CAPTURE) {

			sbContentBuilder.append(this.objCurrentRiskGameObject.defenseNum + " armies moved from "
					+ this.objCurrentRiskGameObject.aTerritory.getName() + " to "
					+ this.objCurrentRiskGameObject.defenseTerritory.getName() + "\n");
		}

		if (this.objCurrentRiskGameObject.getState() == RiskGameModel.DEFEATED) {

			sbContentBuilder.append(this.objCurrentRiskGameObject.defenseTerritory.getName()
					+ " won!! They defended themseleves successfully from "
					+ this.objCurrentRiskGameObject.aTerritory.getName() + "\n");
		}

		return sbContentBuilder.toString();
	}
}
