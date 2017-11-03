package risk.model.Observable;

import java.util.Observable;

import risk.helpers.Utility;
import risk.model.RiskGameModel;
import risk.model.interfaces.PhaseViewInterface;

public class RiskStartupEndPhaseModel extends Observable implements PhaseViewInterface {
	private String title;
	private String objectType = "RiskAttackStartupEndPhaseModel";
	private RiskGameModel currentRiskGameObject;

	private static RiskStartupEndPhaseModel instance = new RiskStartupEndPhaseModel();

	private RiskStartupEndPhaseModel() {
	}

	public static RiskStartupEndPhaseModel getInstance() {
		if (instance == null)
			instance = new RiskStartupEndPhaseModel();
		return instance;
	}

	@Override
	public String getContent() {
		// TODO Auto-generated method stub
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

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhaseInformation() {
		return Utility.getPhaseInformtion(this);
	}

	private int getNumberOfArmiesPerPlayers() {
		try {
			return currentRiskGameObject.curPlayer.getNumberOfArmies();
		} catch (NullPointerException e) {
			return 0;
		}
	}

	private int getNumberOfPlayers() {
		try {
			return currentRiskGameObject.getPlayers().size();
		} catch (NullPointerException e) {
			return 0;
		}
	}

	@Override
	public String toString() {
		StringBuilder sbContentBuilder = new StringBuilder();
		sbContentBuilder.append("************" + this.getTitle() + "************\n");
		sbContentBuilder.append(this.getPhaseInformation() + "\n");
		sbContentBuilder.append("Statistics:\n\n");
		sbContentBuilder.append("The startup phase ends.\n");
		sbContentBuilder.append(this.getCurrentRiskGameObject().getArmies() + " armies have been placed.");
		return sbContentBuilder.toString();
	}

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
}
