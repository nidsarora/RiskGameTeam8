package risk.model;

import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class RiskContinentModel represents the structure of Continent.
 */
public class RiskContinentModel {

	private String name;
	
	private Vector<Integer> territories;
	
	private int value;
	
	RiskTerritoryModel t;

	
	RiskContinentModel(String nm, Vector<Integer> t, int v) {
		name = nm;
		value = v;
		territories = t;
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
	 * Checks if is territory of.
	 *
	 * @param t
	 * @return true, if is territory of
	 */
	public boolean isTerritoryOf(RiskTerritoryModel t) {
		return (territories.contains(t.getId()));
	}

	/**
	 * Checks if is continent captured.
	 *
	 * @return true, if is continent captured
	 */
	public boolean isContinentCaptured(RiskPlayerModel p) {
		Vector<Integer> t1 = new Vector<Integer>();
		Vector<RiskTerritoryModel> t2 = p.getOccupiedTerritories();

		for (int c = 0; c < t2.size(); c++)
			t1.add(t2.elementAt(c).getId());
		for (int i = 0; i < territories.size(); i++) {
			if (!t1.contains(territories.elementAt(i)))
				return false;
		}
		return true;
	}

}
