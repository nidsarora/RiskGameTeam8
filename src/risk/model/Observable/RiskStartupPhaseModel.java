
package risk.model.Observable;

import java.io.Serializable;
import java.util.Observable;

import risk.helpers.Utility;
import risk.model.RiskGameModel;
import risk.model.interfaces.PhaseViewInterface;

/**
 * The Class Risk Startup Phase Model.
 */
public class RiskStartupPhaseModel extends Observable implements PhaseViewInterface ,Serializable{

	/** The title. */
	private String title;
	
	/** The object type. */
	private String objectType = "RiskStartupPhaseModel";
	
	/** The current risk game object. */
	private RiskGameModel currentRiskGameObject;

	/** The instance. */
	private static RiskStartupPhaseModel instance = new RiskStartupPhaseModel();

	/**
	 * Instantiates a new risk startup phase model.
	 */
	private RiskStartupPhaseModel() {
	}

	/**
	 * Gets the single instance of RiskStartupPhaseModel.
	 *
	 * @return single instance of RiskStartupPhaseModel
	 */
	public static RiskStartupPhaseModel getInstance() {
		if (instance == null)
			instance = new RiskStartupPhaseModel();
		return instance;
	}

	@Override
	public String getContent() {
		return this.toString();
	}

	@Override
	public void isChanged() {
		setChanged();
		notifyObservers(this);
	}

	public String getTitle() {
		return Utility.getPhaseTitle(this);
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhaseInformation() {
		return Utility.getPhaseInformtion(this);
	}

	/**
	 * Gets the number of armies per players.
	 *
	 * @return the number of armies per players
	 */
	private int getNumberOfArmiesPerPlayers() {
		try {
			return currentRiskGameObject.curPlayer.getNumberOfArmies();
		} catch (NullPointerException e) {
			return 0;
		}
	}

	/**
	 * Gets the number of players.
	 *
	 * @return the number of players
	 */
	private int getNumberOfPlayers() {
		try {
			return currentRiskGameObject.getPlayers().size();
		} catch (NullPointerException e) {
			return 0;
		}
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
		if(this.getCurrentRiskGameObject().getIsGameMapValid()) {
			 		sbContentBuilder.append("************" + this.getTitle() + "************\n");
			 		sbContentBuilder.append(this.getPhaseInformation() + "\n");
			 		sbContentBuilder.append("Statistics:\n\n");
			 		sbContentBuilder.append("Number of players:" + this.getNumberOfPlayers() + "\n");
			 		sbContentBuilder.append("Number of armies per player:" + this.getCurrentRiskGameObject().getArmies() + "\n");
			 	}else
			 		{
			 			sbContentBuilder.append("************  invalid map file ************\n");
			 	}
		return sbContentBuilder.toString();
	}
}

