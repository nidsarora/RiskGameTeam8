package Risk;

import java.util.Vector;


public class RiskPlayer {
	
	private String name;
	private int index;
	private Vector<RiskTerritory> occupiedTerritories;
	//Cards might be here
	private Vector<RiskCard> cards;
	private int armies;
	//private int territoriesCaptured; //Same as occupiedTerritories.size()
	
	RiskPlayer(String nm, int i) {
		name = nm;
		index = i;
		occupiedTerritories = new Vector<RiskTerritory>();
                cards = new Vector<RiskCard>();
	}
	
	public int getPlayerIndex(){
		return index;
	}
	
	public String getName(){
		return name;
	}
	
	public int getNumberOfArmies(){
		return armies;
	}
	
/*	public Vector getCards(){
		return cardsOwned;
	} */


	public Vector<RiskTerritory> getOccupiedTerritories(){
		return occupiedTerritories;
	}

	public int numOfTerritories(){
		return occupiedTerritories.size();
	}
	
	public void occupyTerritory(RiskTerritory t){
		occupiedTerritories.add(t);
	}
	
	public void looseTerritory(RiskTerritory t){
		occupiedTerritories.remove(t);
		occupiedTerritories.trimToSize();
	}

        public void setCard(RiskCard c){
            cards.add(c);
        }

        public Vector<RiskCard> getCard(){
            return cards;
        }
	
	public void addArmies(int a){
		armies += a;
	}

        public void addArmy(){
            armies++;
        }

        public void looseArmies(int a){
            armies -= a;
        }
        
        public void looseArmy(){
            armies--;
        }
	
	
}
