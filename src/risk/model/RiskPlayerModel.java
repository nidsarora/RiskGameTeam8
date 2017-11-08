package risk.model;

import java.util.Observable;
import java.util.Vector;

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
	private int armiesRecivedByTradingCards; 

	public RiskPlayerModel(String playername, int playerindex) {
		name = playername;
		index = playerindex;
		occupiedTerritories = new Vector<RiskTerritoryModel>();
		cards = new Vector<RiskCardModel>();
	}

	public RiskPlayerModel() {
		// TODO Auto-generated constructor stub
	}

	public int getPlayerIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public int getNumberOfArmies() {
		return armies;
	}

	public void isChanged() {
		setChanged();
		notifyObservers(this);
	}

	public Vector<RiskTerritoryModel> getOccupiedTerritories() {
		return occupiedTerritories;
	}

	public void setOccupiedTerritories(Vector<RiskTerritoryModel> riskterritorymodel) {
		occupiedTerritories = riskterritorymodel;
		isChanged();
	}

	public int numOfTerritories() {
		if (occupiedTerritories == null)
			return 0;
		return occupiedTerritories.size();
	}

	public void occupyTerritory(RiskTerritoryModel riskterritorymodel) {
		if (!occupiedTerritories.contains(riskterritorymodel))
			occupiedTerritories.add(riskterritorymodel);
		isChanged();
	}

	public void looseTerritory(RiskTerritoryModel riskterritorymodel) {
		occupiedTerritories.remove(riskterritorymodel);
		occupiedTerritories.trimToSize();
		isChanged();
	}

	public void setCard(RiskCardModel riskcardmodel) {
		cards.add(riskcardmodel);
		isChanged();
	}

	public Vector<RiskCardModel> getCard() {
		return cards;
	}

	public void addArmies(int army) {
		armies += army;
	}

	public void addArmy() {
		armies++;
	}

	public void looseArmies(int army) {
		armies -= army;
	}

	public void looseArmy() {
		armies--;
	}

	public String getCardExchangeViewContent() {
		StringBuilder sbCardExchangeViewContent = new StringBuilder();
		sbCardExchangeViewContent.append("*************The Card Exchange View************\n");
		sbCardExchangeViewContent.append("Current Player Statistics- \n");

		sbCardExchangeViewContent.append(
				this.getName() + " has " + this.getNumberOfArmies() + " armies and " + this.cards.size() + " cards.\n");

		sbCardExchangeViewContent.append("The following below are the card details - \n");

		if(cards.size() > 0)
			for (RiskCardModel card: this.cards) {
				sbCardExchangeViewContent.append("The card type is " + card.card_type + " and number is + " + card.territory + ".\n");
		}
		else
			sbCardExchangeViewContent.append("Current player has no cards yet!\n");

		sbCardExchangeViewContent.append("Global Statictics- \n");

		for (RiskPlayerModel player : RiskGameModel.players) {
			if (!this.getName().equals(player.getName()))
				sbCardExchangeViewContent.append(player.getName() + " has " + player.getNumberOfArmies() + " armies and "
						+ player.cards.size() + " number of cards.\n");
		}

		return sbCardExchangeViewContent.toString();
	}

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

	public int getArmiesRecivedByTradingCards() {
		return armiesRecivedByTradingCards;
	}

	public void setArmiesRecivedByTradingCards(int armiesRecivedByTradingCards) {
		this.armiesRecivedByTradingCards = armiesRecivedByTradingCards;
	}
}

