package risk.model;

import java.io.StreamTokenizer;
import java.util.Observable;
import java.util.Vector;

import risk.model.interfaces.StrategyInterface;

/**
 * This class represents the model class of the Player. Player has army name,
 * index, territories he occupies, and cards that he owns.
 * 
 * @author Team8
 */
public class RiskPlayerModel extends Observable {

	private String name;
	private int index;
	private Vector<RiskTerritoryModel> occupiedTerritories;
	private Vector<RiskCardModel> cards;
	private int armies;
	private StrategyInterface strategy;

	/** The armies received by trading cards. */
	private int armiesRecivedByTradingCards;

	/**
	 * Instantiates a new risk player model.
	 *
	 * @param playername,
	 *            player name
	 * @param playerindex,
	 *            player index
	 */
	public RiskPlayerModel(String playername, int playerindex) {
		name = playername;
		index = playerindex;
		occupiedTerritories = new Vector<RiskTerritoryModel>();
		cards = new Vector<RiskCardModel>();
	}

	/**
	 * Instantiates a new risk player model.
	 */
	public RiskPlayerModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the player index.
	 *
	 * @return the player index
	 */
	public int getPlayerIndex() {
		return index;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the number of armies.
	 *
	 * @return the number of armies
	 */
	public int getNumberOfArmies() {
		return armies;
	}

	/**
	 * Checks if is changed.
	 */
	public void isChanged() {
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the occupied territories.
	 *
	 * @return the occupied territories
	 */
	public Vector<RiskTerritoryModel> getOccupiedTerritories() {
		return occupiedTerritories;
	}

	/**
	 * Sets the occupied territories.
	 *
	 * @param riskterritorymodel
	 *            the new occupied territories
	 */
	public void setOccupiedTerritories(Vector<RiskTerritoryModel> riskterritorymodel) {
		occupiedTerritories = riskterritorymodel;
		isChanged();
	}

	/**
	 * Number of territories.
	 *
	 */
	public int numOfTerritories() {
		if (occupiedTerritories == null)
			return 0;
		return occupiedTerritories.size();
	}

	/**
	 * Occupy territory.
	 */
	public void occupyTerritory(RiskTerritoryModel riskterritorymodel) {
		if (!occupiedTerritories.contains(riskterritorymodel))
			occupiedTerritories.add(riskterritorymodel);
		isChanged();
	}

	/**
	 * Loose territory.
	 *
	 */
	public void looseTerritory(RiskTerritoryModel riskterritorymodel) {
		occupiedTerritories.remove(riskterritorymodel);
		occupiedTerritories.trimToSize();
		isChanged();
	}

	/**
	 * Sets the card.
	 *
	 */
	public void setCard(RiskCardModel riskcardmodel) {
		cards.add(riskcardmodel);
		isChanged();
	}

	/**
	 * Gets the card.
	 *
	 * @return the card
	 */
	public Vector<RiskCardModel> getCard() {
		return cards;
	}

	/**
	 * Adds the armies.
	 *
	 * @param army
	 *            the army
	 */
	public void addArmies(int army) {
		armies += army;
	}

	/**
	 * Adds the army.
	 */
	public void addArmy() {
		armies++;
	}

	/**
	 * Loose armies.
	 *
	 * @param army
	 *            the army
	 */
	public void looseArmies(int army) {
		armies -= army;
	}

	/**
	 * Loose army.
	 */
	public void looseArmy() {
		armies--;
	}

	/**
	 * Gets the card exchange view content.
	 *
	 * @return the card exchange view content
	 */
	public String getCardExchangeViewContent() {
		StringBuilder sbCardExchangeViewContent = new StringBuilder();
		sbCardExchangeViewContent.append("*************The Card Exchange View************\n");
		sbCardExchangeViewContent.append("Current Player Statistics- \n");

		sbCardExchangeViewContent.append(
				this.getName() + " has " + this.getNumberOfArmies() + " armies and " + this.cards.size() + " cards.\n");

		sbCardExchangeViewContent.append("The following below are the card details - \n");

		if (cards.size() > 0)
			for (RiskCardModel card : this.cards) {
				sbCardExchangeViewContent
						.append("The card type is " + card.card_type + " and number is + " + card.territory + ".\n");
			}
		else
			sbCardExchangeViewContent.append("Current player has no cards yet!\n");

		sbCardExchangeViewContent.append("Global Statictics- \n");

		for (RiskPlayerModel player : RiskGameModel.players) {
			if (!this.getName().equals(player.getName()))
				sbCardExchangeViewContent.append(player.getName() + " has " + player.getNumberOfArmies()
						+ " armies and " + player.cards.size() + " number of cards.\n");
		}

		return sbCardExchangeViewContent.toString();
	}

	/**
	 * Gets the player domination view content.
	 *
	 * @return the player domination view content
	 */
	public String getPlayerDominationViewContent() {
		StringBuilder sbPlayerDominationViewContent = new StringBuilder();
		sbPlayerDominationViewContent.append("**********Player Domination View**********\n");
		sbPlayerDominationViewContent.append("Statistics\n\n");
		sbPlayerDominationViewContent
				.append("Current Player: " + this.getName() + ":" + this.numOfTerritories() + " territories: ");
		for (int territoryCount = 1; territoryCount <= this.numOfTerritories() / 5 + 1; territoryCount++)
			sbPlayerDominationViewContent.append("*");
		sbPlayerDominationViewContent.append("\n");

		for (RiskPlayerModel player : RiskGameModel.players) {
			if (!this.getName().equals(player.getName())) {
				sbPlayerDominationViewContent.append("\n");
				sbPlayerDominationViewContent
						.append("Player: " + player.getName() + ":" + player.numOfTerritories() + " territories: ");
				for (int territoryCount = 1; territoryCount <= player.numOfTerritories() / 5 + 1; territoryCount++)
					sbPlayerDominationViewContent.append(player.numOfTerritories() != 0 ? "*" : "");

			}
		}

		return sbPlayerDominationViewContent.toString();
	}

	/**
	 * Gets the armies received by trading cards.
	 *
	 * @return the armies received by trading cards
	 */
	public int getArmiesRecivedByTradingCards() {
		return armiesRecivedByTradingCards;
	}

	/**
	 * Sets the armies received by trading cards.
	 *
	 * @param armiesRecivedByTradingCards,
	 *            the new armies received by trading cards
	 */
	public void setArmiesRecivedByTradingCards(int armiesRecivedByTradingCards) {
		this.armiesRecivedByTradingCards = armiesRecivedByTradingCards;
	}

	public StrategyInterface getStrategy() {
		return strategy;
	}

	public void setStrategy(StrategyInterface strategy) {
		this.strategy = strategy;
	}

	public Boolean isValidAttack() {
		return true;
	}

	public void attack() {
		if (isValidAttack())
			this.strategy.attack();
	}
}
