package risk.model;

import java.io.Serializable;
import java.util.Vector;
import risk.model.strategy.*;

/**
 *This method initializes the first player as the initial player.
 *
 *@author Team8
 */
public class RiskTerritoryModel implements Serializable {
	
	/** The id. */
	protected int id;
	
	/** The x coordinate. */
	private int x_coordinate;
	
	/** The y coordinate. */
	private int y_coordinate;
	
	/** The name. */
	private String name;
	
	/** The player. */
	private RiskPlayerModel player;
	
	/** The armies. */
	private int armies;
	
	/** The continent. */
	private int continent;
	
	/** The adjacents. */
	private Vector<Integer> adjacents;

	/**
	 * Instantiates a new risk territory model.
	 *
	 * @param idvalue the territory id
	 * @param namevalue the territory name
	 * @param continentvalue the continentvalue
	 * @param xlocation the x-coordinate of territory
	 * @param ylocation the y-coordinate of territory
	 */
	public RiskTerritoryModel(int idvalue, String namevalue, int continentvalue, int xlocation, int ylocation) {
		id = idvalue;
		name = namevalue;
		continent = continentvalue;
		armies = 0; // Start Empty
		adjacents = new Vector<Integer>();
		x_coordinate = xlocation;
		y_coordinate = ylocation;
		player = new RiskPlayerModel(null, -1,new Human());
	}

	/**
	 * Sets the player.
	 *
	 * @param playermodel the new player
	 */
	public void setPlayer(RiskPlayerModel playermodel) {
		player = playermodel;
	}


	/**
	 * Sets the adjacent.
	 *
	 * @param adjacent
	 *            the new adjacent
	 */
	public void setAdjacent(Vector<Integer> adjacent) {
		adjacents = adjacent;
	}

	/**
	 * Sets the armies.
	 *
	 * @param army the new armies
	 */
	public void setArmies(int army) {
		armies = army;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x-coordinate
	 */
	public int getX() {
		return x_coordinate;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y-coordinate
	 */
	public int getY() {
		return y_coordinate;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
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
	 * Gets the player.
	 *
	 * @return the player
	 */
	public RiskPlayerModel getPlayer() {
		return player;
	}

	/**
	 * Gets the armies.
	 *
	 * @return the armies
	 */
	public int getArmies() {
		return armies;
	}

	/**
	 * Gets the adjacents.
	 *
	 * @return the adjacents
	 */
	public Vector<Integer> getAdjacents() {
		return adjacents;
	}

	/**
	 * Sets the continent.
	 *
	 * @param continentname the new continent
	 */
	public void setContinent(int continentname) {
		continent = continentname;
	}

	/**
	 * Gets the continent.
	 *
	 * @return the continent
	 */
	public int getContinent() {
		return continent;
	}

	/**
	 * Checks if is adjacent.
	 *
	 * @param riskterritorymodel the riskterritorymodel
	 * @return true, if is adjacent
	 */
	public boolean isAdjacent(RiskTerritoryModel riskterritorymodel) {
		return adjacents.contains(riskterritorymodel.getId());
	}

	/**
	 * Checks if is occupied.
	 *
	 * @return true, if is occupied
	 */
	public boolean isOccupied() {
		if (player.getPlayerIndex() == -1)
			return false;
		return true;
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
	 * Loose army.
	 */
	public void looseArmy() {
		if (armies != 0)
			armies--;
	}

	/**
	 * Loose armies.
	 *
	 * @param army the army
	 */
	public void looseArmies(int army) {
		if (armies - army >= 0)
			armies -= army;
	}

	/**
	 * Prints the adjacents.
	 */
	public void printAdjacents() {
		for (int index = 0; index < adjacents.size(); index++) {
			System.out.println("Print" + adjacents.elementAt(index));
		}

	}
}
