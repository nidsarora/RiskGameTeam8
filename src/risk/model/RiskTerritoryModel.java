package risk.model;

import java.util.Vector;

/**
 *This method initializes the first player as the initial player.
 */
public class RiskTerritoryModel {
	protected int id;
	private int x_coordinate;
	private int y_coordinate;
	private String name;
	private RiskPlayerModel player;
	private int armies;
	private int continent;
	private Vector<Integer> adjacents;

	public RiskTerritoryModel(int idvalue, String namevalue, int continentvalue, int xlocation, int ylocation) {
		id = idvalue;
		name = namevalue;
		continent = continentvalue;
		armies = 0; // Start Empty
		adjacents = new Vector<Integer>();
		x_coordinate = xlocation;
		y_coordinate = ylocation;
		player = new RiskPlayerModel(null, -1);
	}

	public void setPlayer(RiskPlayerModel playermodel) {
		player = playermodel;
	}

	public void setAdjacent(Vector<Integer> adjacent) {
		adjacents = adjacent;
	}

	public void setArmies(int army) {
		armies = army;
	}

	public int getX() {
		return x_coordinate;
	}

	public int getY() {
		return y_coordinate;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public RiskPlayerModel getPlayer() {
		return player;
	}

	public int getArmies() {
		return armies;
	}

	public Vector<Integer> getAdjacents() {
		return adjacents;
	}

	public void setContinent(int continent) {
		continent = continent;
	}

	public int getContinent() {
		return continent;
	}

	public boolean isAdjacent(RiskTerritoryModel riskterritorymodel) {
		return adjacents.contains(riskterritorymodel.getId());
	}

	public boolean isOccupied() {
		if (player.getPlayerIndex() == -1)
			return false;
		return true;
	}

	public void addArmies(int army) {
		armies += army;
	}


	public void addArmy() {
		armies++;
	}

	public void looseArmy() {
		armies--;
	}

	public void looseArmies(int army) {
		armies -= army;
	}

	public void printAdjacents() {
		for (int index = 0; index < adjacents.size(); index++) {
			System.out.println("Print" + adjacents.elementAt(index));
		}

	}
}
