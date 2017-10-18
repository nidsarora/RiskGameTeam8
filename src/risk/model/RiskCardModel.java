/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package risk.model;

// TODO: Auto-generated Javadoc
/**
 * This class represents basic structure of the card.
 */

public class RiskCardModel {

	public String value;
	
	public int territory;

	
	public RiskCardModel(int t, String v) {
		this.value = v;
		this.territory = t;
	}

	/**
	 * Instantiates a new risk card model.
	 */
	public RiskCardModel(int v, int t) {
		this.territory = t;
		switch (v) {
		case 0:
			this.value = "INFANTRY";
			break;
		case 1:
			this.value = "CAVALRY";
			break;
		case 2:
			this.value = "ARTILLERY";
			break;
		default:
			this.value = "WILD";
			break;
		}

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Territory \n" + String.valueOf(territory) + "\n\n Value \n" + String.valueOf(value) + "-"
				+ String.valueOf(territory) + "-" + value;
	}

}
