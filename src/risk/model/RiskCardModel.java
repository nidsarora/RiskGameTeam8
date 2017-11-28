
package risk.model;

import java.io.Serializable;

/**
 * This class represents the model of Card used in Risk Game.
 * 
 * @author Team8
 */

public class RiskCardModel implements Serializable{

	public String card_type;
	public int territory;

	/**
	 * Instantiates a new risk card model.
	 *
	 * @param r_territory_number the risk territory number
	 * @param r_card_type risk card type
	 */
	public RiskCardModel(int r_territory_number, String r_card_type) {
		this.territory = r_territory_number;
		this.card_type = r_card_type;		
	}

	/**
	 * Instantiates a new risk card model.
	 */
	public RiskCardModel(int r_territory_number, int r_int_card_type) {
		this.territory = r_territory_number;
		switch (r_int_card_type) {
		case 0:
			this.card_type = "INFANTRY";
			break;
		case 1:
			this.card_type = "CAVALRY";
			break;
		case 2:
			this.card_type = "ARTILLERY";
			break;
		default:
			this.card_type = "WILD";
			break;
		}

	}

	@Override
	public String toString() {
		return "Territory \n" + String.valueOf(territory) + "\n\n Value \n" + String.valueOf(card_type) + "-"
				+ String.valueOf(territory) + "-" + card_type;
	}

}
