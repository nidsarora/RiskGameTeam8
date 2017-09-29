package Risk;

import java.util.Vector;


public class RiskPlayer {
	
	private String name;
	private int index;
	private Vector<Territory> occupiedTerritories;
	//RiskCards might be here
	private Vector<RiskCard> RiskCards;
	private int armies;
	//private int territoriesCaptured; //Same as occupiedTerritories.size()
	
	RiskPlayer(String nm, int i) {
		name = nm;
		index = i;
		occupiedTerritories = new Vector<Territory>();
                RiskCards = new Vector<RiskCard>();
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
	
/*	public Vector getRiskCards(){
		return RiskCardsOwned;
	} */


	public Vector<Territory> getOccupiedTerritories(){
		return occupiedTerritories;
	}

	public int numOfTerritories(){
		return occupiedTerritories.size();
	}
	
	public void occupyTerritory(Territory t){
		occupiedTerritories.add(t);
	}
	
	public void looseTerritory(Territory t){
		occupiedTerritories.remove(t);
		occupiedTerritories.trimToSize();
	}

        public void setRiskCard(RiskCard c){
            RiskCards.add(c);
        }

        public Vector<RiskCard> getRiskCard(){
            return RiskCards;
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
