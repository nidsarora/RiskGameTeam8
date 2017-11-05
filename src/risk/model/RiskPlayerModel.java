package risk.model;

import java.util.Observable;
import java.util.Vector;

/**
 * This class represents the model class of the Player. Player has a name,
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
	private RiskGameModel currentRiskGameModel;

	public RiskPlayerModel(String nm, int i) {
		name = nm;
		index = i;
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

	public void setOccupiedTerritories(Vector<RiskTerritoryModel> rtm) {
		occupiedTerritories = rtm;
		isChanged();
	}

	public int numOfTerritories() {
		if (occupiedTerritories == null)
			return 0;
		return occupiedTerritories.size();
	}

	public void occupyTerritory(RiskTerritoryModel t, RiskGameModel risk) {
		occupiedTerritories.add(t);
		currentRiskGameModel = risk;
		isChanged();
	}

	public void looseTerritory(RiskTerritoryModel t, RiskGameModel risk) {
		occupiedTerritories.remove(t);
		occupiedTerritories.trimToSize();
		currentRiskGameModel = risk;
		isChanged();
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

	@Override
	public String toString() {
		StringBuilder sbPlayerDominationViewContent = new StringBuilder();
		sbPlayerDominationViewContent.append("**********Player Domination View**********\n");
		sbPlayerDominationViewContent.append("Statistics\n\n");
		sbPlayerDominationViewContent
				.append("Current Player: " + this.getName() + ":" + this.numOfTerritories() + " territories: ");
		for (int territoryCount = 1; territoryCount <= this.numOfTerritories() / 5 + 1; territoryCount++)
			sbPlayerDominationViewContent.append("*");
		sbPlayerDominationViewContent.append("\n");
	
		for (RiskPlayerModel player : this.currentRiskGameModel.players) {
			if (!this.getName().equals(player.getName())) {
				sbPlayerDominationViewContent.append("\n");
				sbPlayerDominationViewContent
						.append("Player: " + player.getName() + ":" + player.numOfTerritories() + " territories: ");
				for (int territoryCount = 1; territoryCount <= player.numOfTerritories()/5 + 1; territoryCount++)
					sbPlayerDominationViewContent.append(player.numOfTerritories() != 0 ? "*" : "");

			}
		}

		return sbPlayerDominationViewContent.toString();
	}

	public void setCurrentRiskGameModel(RiskGameModel currentRiskGameModel) {
		this.currentRiskGameModel = currentRiskGameModel;
	}

}
