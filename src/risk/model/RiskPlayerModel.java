package risk.model;

import java.util.Vector;

/**
 * The Class RiskPlayerModel represents the occupied 
 */
public class RiskPlayerModel {

	private String name;
	private int index;
	
	/** The occupied territories. */
	private Vector<RiskTerritoryModel> occupiedTerritories;
	
	private Vector<RiskCardModel> cards;
	
	private int armies;
	// private int territoriesCaptured; //Same as occupiedTerritories.size()

	/**
	 * Instantiates a new risk player model.
	 *
	 * @param nm
	 * @param i 
	 */
	RiskPlayerModel(String nm, int i) {
		name = nm;
		index = i;
		occupiedTerritories = new Vector<RiskTerritoryModel>();
		cards = new Vector<RiskCardModel>();
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

	/*
	 * public Vector getCards(){ return cardsOwned; }
	 */

	/**
	 * Gets the occupied territories.
	 *
	 * @return the occupied territories
	 */
	public Vector<RiskTerritoryModel> getOccupiedTerritories() {
		return occupiedTerritories;
	}

	/**
	 * Number of territories.
	 *
	 * @return the number of territories
	 */
	public int numOfTerritories() {
		return occupiedTerritories.size();
	}

	/**
	 * Occupy territory.
	 *
	 * @param t the t
	 */
	public void occupyTerritory(RiskTerritoryModel t) {
		occupiedTerritories.add(t);
	}

	/**
	 * Loose territory.
	 *
	 * @param t the t
	 */
	public void looseTerritory(RiskTerritoryModel t) {
		occupiedTerritories.remove(t);
		occupiedTerritories.trimToSize();
	}

	/**
	 * Sets the card.
	 *
	 * @param c the new card
	 */
	public void setCard(RiskCardModel c) {
		cards.add(c);
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
	 * @param a the a
	 */
	public void addArmies(int a) {
		armies += a;
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
	 * @param a the a
	 */
	public void looseArmies(int a) {
		armies -= a;
	}

	/**
	 * Loose army.
	 */
	public void looseArmy() {
		armies--;
	}

}
