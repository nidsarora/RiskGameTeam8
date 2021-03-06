package risk.model.Observable;

import java.io.Serializable;
import java.util.Observable;

import risk.helpers.Utility;
import risk.model.RiskGameModel;
import risk.model.interfaces.PhaseViewInterface;

/**
 * The Class Risk Startup End Phase Model.
 */
public class RiskStartupEndPhaseModel extends Observable implements PhaseViewInterface,Serializable {
	
	/** The title. */
	private String title;
	
	/** The object type. */
	private String objectType = "RiskAttackStartupEndPhaseModel";
	
	/** The current risk game object. */
	private RiskGameModel currentRiskGameObject;

	/** The instance. */
	private static RiskStartupEndPhaseModel instance = new RiskStartupEndPhaseModel();

	/**
	 * Instantiates a new risk startup end phase model.
	 */
	private RiskStartupEndPhaseModel() {
	}

	/**
	 * Gets the single instance of Risk Startup End Phase Model.
	 *
	 * @return single instance of RiskStartupEndPhaseModel
	 */
	public static RiskStartupEndPhaseModel getInstance() {
		if (instance == null)
			instance = new RiskStartupEndPhaseModel();
		return instance;
	}

	@Override
	public String getContent() {
		return this.toString();
	}

	@Override
	public void isChanged() {
		// specify that my state was changed
		setChanged();
		// notify all attached Observers of a change
		notifyObservers(this);
	}

	public String getTitle() {
		return Utility.getPhaseTitle(this);
	}


	/**
	 * Sets the title.
	 *
	 * @param title
	 *            sets new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhaseInformation() {
		return Utility.getPhaseInformtion(this);
	}

	/**
	 * Gets the current risk game object.
	 *
	 * @return the current risk game object
	 */
	public RiskGameModel getCurrentRiskGameObject() {
		return currentRiskGameObject;
	}

	public void setCurrentRiskGameObject(RiskGameModel currentRiskGameObject) {
		this.currentRiskGameObject = currentRiskGameObject;
	}

	@Override
	public String getObjectType() {
		return objectType;
	}

	@Override
	public String toString() {
		StringBuilder sbContentBuilder = new StringBuilder();
		sbContentBuilder.append("\n************" + this.getTitle() + "************\n");
		sbContentBuilder.append(this.getPhaseInformation() + "\n");
		sbContentBuilder.append("Statistics:\n\n");
		sbContentBuilder.append("The startup phase ends.\n");
		sbContentBuilder.append(this.getCurrentRiskGameObject().getArmies() + " armies have been placed.\n");
		sbContentBuilder.append("Player: "+ this.getCurrentRiskGameObject().curPlayer.getName() + " recieved " + this.currentRiskGameObject.getCurrentPlayerBonusArmiesRecieved()
				+ " armies as bonus from Continents.\n");
		sbContentBuilder.append("Player recieved " + 0 + " armies from tading cards.\n");
		return sbContentBuilder.toString();
	}
}
