package risk.model;

import java.util.Vector;

/**
 *This class represents the model class of the Continent
 */
public class RiskContinentModel {

	private String name;
	private Vector<Integer> territories;
	private int value;
	RiskTerritoryModel t;

	public RiskContinentModel(String nm, Vector<Integer> t, int v) {
		name = nm;
		value = v;
		territories = t;
	}

	public String getName() {
		return name;
	}

	public Vector<Integer> getTerritories() {
		return territories;
	}

	public int getValue() {
		return value;
	}

	public boolean isTerritoryOf(RiskTerritoryModel t) {
		return (territories.contains(t.getId()));
	}

	public void AddTerritories(Integer i)
	{
		territories.add(i);
	}
	/**
	 *This method finds if the continent has been captured or not.
	 */
	public boolean isContinentCaptured(RiskPlayerModel p) {
		Vector<Integer> t1 = new Vector<Integer>();
		Vector<RiskTerritoryModel> t2 = p.getOccupiedTerritories();
                
	        if (territories.size() == 0)
	             return false;
		for (int c = 0; c < t2.size(); c++)
			t1.add(t2.elementAt(c).getId());
		for (int i = 0; i < territories.size(); i++) {
			if (!t1.contains(territories.elementAt(i)))
				return false;
		}
		return true;
	}

}
