package risk.model.Observable;

import java.io.Serializable;
import java.util.Observable;

import risk.helpers.Utility;
import risk.model.RiskGameModel;
import risk.model.interfaces.PhaseViewInterface;

/**
 * The Class Risk Reinforcement Phase Model.
 */
public class RiskReinforcementPhaseModel extends Observable implements PhaseViewInterface,Serializable {

	/** The current player. */
	private int currentPlayer;
	
	/** The bonus armies recieved. */
	private int bonusArmiesRecieved;
	
	/** The card traded armies recieved. */
	private int cardTradedArmiesRecieved;
	
	/** The object type. */
	private String objectType = "RiskReinforcementPhaseModel";
	
	/** The obj current risk game object. */
	private RiskGameModel objCurrentRiskGameObject;
	
	/** The instance. */
	private static RiskReinforcementPhaseModel instance = new RiskReinforcementPhaseModel();

	/**
	 * Instantiates a new risk reinforcement phase model.
	 */
	private RiskReinforcementPhaseModel() {
	}

	/**
	 * Gets the single instance of RiskReinforcementPhaseModel.
	 *
	 * @return single instance of RiskReinforcementPhaseModel
	 */
	public static RiskReinforcementPhaseModel getInstance() {
		if (instance == null)
			instance = new RiskReinforcementPhaseModel();
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

	/**
	 * Gets the card traded armies recieved.
	 *
	 * @return the card traded armies recieved
	 */
	public int getCardTradedArmiesRecieved() {
		return cardTradedArmiesRecieved;
	}

	/**
	 * Sets the card traded armies recieved.
	 *
	 * @param cardTradedArmiesRecieved the new card traded armies recieved
	 */
	public void setCardTradedArmiesRecieved(int cardTradedArmiesRecieved) {
		this.cardTradedArmiesRecieved = cardTradedArmiesRecieved;
	}

	/**
	 * Gets the bonus armies recieved.
	 *
	 * @return the bonus armies recieved
	 */
	public int getBonusArmiesRecieved() {
		return bonusArmiesRecieved;
	}

	/**
	 * Sets the bonus armies recieved.
	 *
	 * @param bonusArmiesRecieved the new bonus armies recieved
	 */
	public void setBonusArmiesRecieved(int bonusArmiesRecieved) {
		this.bonusArmiesRecieved = bonusArmiesRecieved;
	}

	/**
	 * Gets the current player.
	 *
	 * @return the current player
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Sets the current player.
	 *
	 * @param currentPlayer the new current player
	 */
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Gets the number of armies per players.
	 *
	 * @return the number of armies per players
	 */
	private int getNumberOfArmiesPerPlayers() {
		try {
			return objCurrentRiskGameObject.curPlayer.getNumberOfArmies();
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

	/**
	 * Gets the current risk game object.
	 *
	 * @return the current risk game object
	 */
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

	@Override
	public String toString() {
		StringBuilder sbContentBuilder = new StringBuilder();
		sbContentBuilder.append("\n************" + this.getTitle() + "************\n");
		sbContentBuilder.append("Player: " + objCurrentRiskGameObject.curPlayer.getName() + "\n");
		sbContentBuilder.append(this.getPhaseInformation() + "\n");
		sbContentBuilder.append("Statistics:\n\n");
		sbContentBuilder.append("The startup phase ends.\n");
		sbContentBuilder.append(this.getCurrentRiskGameObject().getArmies() + " armies have been placed.\n");
		sbContentBuilder.append("Player: " + this.getCurrentRiskGameObject().curPlayer.getName() + " recieved "
				+ this.getCurrentRiskGameObject().getCurrentPlayerBonusArmiesRecieved()
				+ " armies as bonus from Continentsand Territories.\n");
		sbContentBuilder.append("Player " + this.getCurrentRiskGameObject().curPlayer.getName() + " recieved " + this.getCurrentRiskGameObject().curPlayer.getArmiesRecivedByTradingCards()
				+ " armies from tading cards.\n");
		sbContentBuilder.append("Player army count:" + getNumberOfArmiesPerPlayers() + "\n");
		return sbContentBuilder.toString();
	}

}
