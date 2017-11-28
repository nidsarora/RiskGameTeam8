package risk.model;

import java.io.Serializable;
import java.util.Vector;

/**
 * This class represents the model class of the Continent.
 * 
 * @author Team8
 */
public class RiskContinentModel implements Serializable{

	private String name;
	private Vector<Integer> territories;
	private int value;
	RiskTerritoryModel territorymodel;

	/**
	 * Instantiates a new risk continent model.
	 *
	 * @param nm the continent name
	 * @param t territory 
	 * @param v, 
	 */
	public RiskContinentModel(String nm, Vector<Integer> t, int v) {
		name = nm;
		value = v;
		territories = t;
	}

	/**
	 * Gets the continent name.
	 *
	 * @return the continent name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the territories.
	 *
	 * @return the territories
	 */
	public Vector<Integer> getTerritories() {
		return territories;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Checks if is territory of the continent.
	 *
	 * @param territorymodel the territory model
	 * @return true, if is territory of
	 */
	public boolean isTerritoryOf(RiskTerritoryModel territorymodel) {
		return (territories.contains(territorymodel.getId()));
	}

	/**
	 * Adds the territories.
	 */
	public void AddTerritories(Integer intvalue)
	{
		territories.add(intvalue);
	}
	
	/**
	 * This method finds if the continent has been captured or not.
	 *
	 * @param p risk player
	 * @return true, if is continent captured
	 */
	public boolean isContinentCaptured(RiskPlayerModel p) {
		Vector<Integer> vectorintegers = new Vector<Integer>();
		Vector<RiskTerritoryModel> territorymodel= p.getOccupiedTerritories();
                
	        if (territories.size() == 0)
	             return false;
		for (int c_index = 0; c_index < territorymodel.size(); c_index++)
			vectorintegers.add(territorymodel.elementAt(c_index).getId());
		for (int index = 0; index < territories.size(); index++) {
			if (!vectorintegers.contains(territories.elementAt(index)))
				return false;
		}
		return true;
	}

}
