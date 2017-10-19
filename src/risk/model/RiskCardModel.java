/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package risk.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class RiskCardModel {

	public String card_type;
	public int territory;

	public RiskCardModel(int r_territory_number, String r_card_type) {
		this.territory = r_territory_number;
		this.card_type = r_card_type;		
	}

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
