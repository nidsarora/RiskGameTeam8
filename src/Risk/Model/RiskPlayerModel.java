package risk.model;

import java.util.Vector;

public class RiskPlayerModel {

	private String name;
	private int index;
	private Vector<RiskTerritoryModel> occupiedTerritories;
	private Vector<RiskCardModel> cards;
	private int armies;
	// private int territoriesCaptured; //Same as occupiedTerritories.size()

	public RiskPlayerModel(String nm, int i) {
		name = nm;
		index = i;
		occupiedTerritories = new Vector<RiskTerritoryModel>();
		cards = new Vector<RiskCardModel>();
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

	/*
	 * public Vector getCards(){ return cardsOwned; }
	 */

	public Vector<RiskTerritoryModel> getOccupiedTerritories() {
		return occupiedTerritories;
	}
	
	public void setOccupiedTerritories(Vector<RiskTerritoryModel> rtm) {
		 occupiedTerritories=rtm;
	}

	public int numOfTerritories() {
		return occupiedTerritories.size();
	}

	public void occupyTerritory(RiskTerritoryModel t) {
		occupiedTerritories.add(t);
	}

	public void looseTerritory(RiskTerritoryModel t) {
		occupiedTerritories.remove(t);
		occupiedTerritories.trimToSize();
	}

	public void setCard(RiskCardModel c) {
		cards.add(c);
	}

	public Vector<RiskCardModel> getCard() {
		return cards;
	}

	public void addArmies(int a) {
		armies += a;
	}

	public void addArmy() {
		armies++;
	}

	public void looseArmies(int a) {
		armies -= a;
	}

	public void looseArmy() {
		armies--;
	}

}
