package risk.model.Observable;

import java.util.Observable;

import risk.helpers.Utility;
import risk.model.RiskGameModel;
import risk.model.interfaces.PhaseViewInterface;

public class RiskReinforcementPhaseModel extends Observable implements PhaseViewInterface {

	private int currentPlayer;
	private int bonusArmiesRecieved;
	private int cardTradedArmiesRecieved;
	private String objectType = "RiskReinforcementPhaseModel";
	private RiskGameModel objCurrentRiskGameObject;
	private static RiskReinforcementPhaseModel instance = new RiskReinforcementPhaseModel();

	private RiskReinforcementPhaseModel() {
	}

	public static RiskReinforcementPhaseModel getInstance() {
		if (instance == null)
			instance = new RiskReinforcementPhaseModel();
		return instance;
	}

	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return this.toString();
	}

	@Override
	public void isChanged() {
		// TODO Auto-generated method stub
		// specify that my state was changed
		setChanged();
		// notify all attached Observers of a change
		notifyObservers(this);
	}

	public int getCardTradedArmiesRecieved() {
		return cardTradedArmiesRecieved;
	}

	public void setCardTradedArmiesRecieved(int cardTradedArmiesRecieved) {
		this.cardTradedArmiesRecieved = cardTradedArmiesRecieved;
	}

	public int getBonusArmiesRecieved() {
		return bonusArmiesRecieved;
	}

	public void setBonusArmiesRecieved(int bonusArmiesRecieved) {
		this.bonusArmiesRecieved = bonusArmiesRecieved;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	@Override
	public String toString() {
		StringBuilder sbContentBuilder = new StringBuilder();
		sbContentBuilder.append("************" +this.getTitle() + "************\n");
		sbContentBuilder.append("Player: " + objCurrentRiskGameObject.curPlayer.getName() +"\n");
		sbContentBuilder.append(this.getPhaseInformation() + "\n");
		sbContentBuilder.append("Statistics:\n\n");
		sbContentBuilder.append("Player army count:" + getNumberOfArmiesPerPlayers() + "\n");
		return sbContentBuilder.toString();
	}

	private int getNumberOfArmiesPerPlayers() {
		try {
			return objCurrentRiskGameObject.curPlayer.getNumberOfArmies();
		} catch (NullPointerException e) {
			return 0;
		}
	}

	private int getNumberOfPlayers() {
		try {
			return objCurrentRiskGameObject.getPlayers().size();
		} catch (NullPointerException e) {
			return 0;
		}
	}

	@Override
	public String getPhaseInformation() {
		return Utility.getPhaseInformtion(this);
	}

	public String getObjectType() {
		return objectType;
	}

	public RiskGameModel getCurrentRiskGameObject() {
		return objCurrentRiskGameObject;
	}

	public void setCurrentRiskGameObject(RiskGameModel currentRiskGameObject) {
		this.objCurrentRiskGameObject = currentRiskGameObject;
	}

	@Override
	public String getTitle() {
		return Utility.getPhaseTitle(this);
	}

}
