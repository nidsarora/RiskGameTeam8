
package risk.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JPanel;

import risk.controller.RiskController;
import risk.controller.RiskMapPanelViewController;
import risk.controller.RiskPlayerPanelViewController;
import risk.controller.RiskStartGameController;
import risk.helpers.Utility;
import risk.model.RiskCardModel;
import risk.model.RiskContinentModel;
import risk.model.RiskPlayerModel;
import risk.model.RiskTerritoryModel;
import risk.model.Observable.RiskAttackPhaseModel;
import risk.model.Observable.RiskStartupEndPhaseModel;
import risk.model.Observable.RiskFortifyPhaseModel;
import risk.model.Observable.RiskReinforcementPhaseModel;
import risk.model.Observable.RiskStartupEndPhaseModel;
import risk.model.Observable.RiskStartupPhaseModel;
import risk.model.interfaces.StrategyInterface;
import risk.view.RiskCardExchangeViewObserver;
import risk.view.RiskPhaseViewObserver;
import risk.view.RiskPlayerDominationViewObserver;

/**
 * This class consists the business logic of the entire game.It consists of all
 * the phases of the game. It calculates and assigns the armies to the players
 * as per the territories occupied and the continents occupied.It also has the
 * logic for loading and parsing the map from the map file.
 * 
 * @author Team8
 */
public class RiskGameModel {

	/** The Constant NEW_GAME. */
	// Game States
	public static final int NEW_GAME = 0;

	/** The Constant INITIAL_REINFORCE. */
	public static final int INITIAL_REINFORCE = 1;

	/** The Constant ACTIVE_TURN. */
	public static final int ACTIVE_TURN = 2;

	/** The Constant TURN_BONUS. */
	public static final int TURN_BONUS = 3;

	/** The Constant REINFORCE. */
	public static final int REINFORCE = 4;

	/** The Constant REINFORCE. */
	private Boolean isGameMapValid = false;

	/** The Constant REINFORCE. */
	public StringBuilder sbBaseMapString;
	/** The Constant REINFORCE. */
	public StringBuilder sbCurrentMapString;

	/** The Constant TRADE_CARDS. */
	public static final int TRADE_CARDS = 5;

	/** The Constant START_TURN. */
	public static final int START_TURN = 6;

	/** The Constant ATTACK. */
	public static final int ATTACK = 7;

	/** The Constant ATTACKING. */
	public static final int ATTACKING = 8;

	/** The Constant ATTACK_PHASE. */
	public static final int ATTACK_PHASE = 9;

	/** The Constant BATTLING. */
	public static final int BATTLING = 10;

	/** The Constant CAPTURE. */
	public static final int CAPTURE = 11;

	/** The Constant FORTIFY. */
	public static final int FORTIFY = 12;

	/** The Constant FORTIFYING. */
	public static final int FORTIFYING = 13;

	/** The Constant FORTIFY_PHASE. */
	public static final int FORTIFY_PHASE = 14;

	/** The Constant DEFEATED. */
	public static final int DEFEATED = 15;

	/** The game trade card phase count. */
	public static int GAME_TRADE_CARD_PHASE_COUNT = 0;

	/** The Constant END_GAME. */
	public static final int END_GAME = 100;

	/** The armies. */
	int armies;

	/** The Constant GAME_OVER. */
	public static final int GAME_OVER = 99;

	/** The territories. */
	static public Vector<RiskTerritoryModel> territories = new Vector<RiskTerritoryModel>();

	/** The continents. */
	public Vector<RiskContinentModel> continents = new Vector<RiskContinentModel>();

	/** The players. */
	static public Vector<RiskPlayerModel> players = new Vector<RiskPlayerModel>();

	/** The deck. */
	public Vector<RiskCardModel> deck = new Vector<RiskCardModel>();

	/** The cur player. */
	public RiskPlayerModel curPlayer;

	/** The defender. */
	public RiskPlayerModel defender;

	public JPanel mainPanel;

	public JPanel subPanel;

	/** The active. */
	public RiskPlayerModel active;

	/** The game state. */
	static private int gameState;

	/** The a territory. */
	public RiskTerritoryModel aTerritory;

	/** The defense territory. */
	public RiskTerritoryModel defenseTerritory;

	/** The defense num. */
	public int defenseNum = 0;

	/** The attack num. */
	public int attackNum = 0;

	public int numberofAttack = 0;

	/** The iter. */
	public int iter = 0;

	/** The drawn. */
	public boolean drawn;

	/** The attack die array. */
	private Integer[] attackDieArray;

	/** The defense die array. */
	private Integer[] defenceDieArray;

	/** The risk startup phase model observable. */
	private RiskStartupPhaseModel riskStartupPhaseModelObservable;

	/** The risk risk reinforcement phase model observable. */
	private RiskReinforcementPhaseModel riskRiskReinforcementPhaseModelObservable;

	/** The risk attack phase model observable. */
	private RiskAttackPhaseModel riskAttackPhaseModelObservable;

	/** The risk fortify phase model observable. */
	private RiskFortifyPhaseModel riskFortifyPhaseModelObservable;

	/** The risk startup end phase model observable. */
	private RiskStartupEndPhaseModel riskStartupEndPhaseModelObservable;

	/** The risk player domination view observer. */
	private RiskPlayerDominationViewObserver riskPlayerDominationViewObserver;

	/** The current player bonus armies received. */
	public int currentPlayerBonusArmiesRecieved;

	/** The is base map edited. */
	private Boolean isBaseMapEdited;

	/** The risk phase view observer. */
	private RiskPhaseViewObserver riskPhaseViewObserver;

	/** The attack dice. */
	public Integer[] attackdice;

	public int xCoordinate;

	public int yCoordinate;
	
	public List<RiskCardModel> lstTradedCards = new ArrayList<RiskCardModel>(); 

	/**
	 * Instantiates a new risk game model.
	 *
	 * @param test
	 *            the test
	 */
	public RiskGameModel(String test) {
	}

	/**
	 * Instantiates a new risk game model.
	 */
	public RiskGameModel() {
		setIsBaseMapEdited(RiskController.isBaseMapEdited);
		gameState = INITIAL_REINFORCE;
		initalPlayer();
		initializePlayerDominationView();
		initializeCardExchangeView();
		initializeMapVariables();
		ValidateLoadMap();
		initializeDeck();
		distubuteArmies();
		assignTerritories();// TODO - Auto distribution of armies - set the current player phase to
							// reinforcement
	}

	public Boolean anyPlayerHasArmies() {
		for (RiskPlayerModel player : this.players) {
			if (player.getNumberOfArmies() > 0)
				return true;
		}
		return false;
	}

	
	/**
	 * This method checks if the TradeCardSet is valid or not i.e. if the number of
	 * cards is 3, and then checks if all 3 are either the same or all three are of
	 * different types or one of the three is a wild card
	 *
	 * @return the boolean
	 */
	public Boolean isTradedCardSetValid() {

		if (lstTradedCards.size() == 3) {

			if (!isAWildCardSet()) {
				// If all the cards are same
				if (lstTradedCards.get(0).card_type.equals(lstTradedCards.get(1).card_type)
						&& lstTradedCards.get(1).card_type.equals(lstTradedCards.get(2).card_type)) {
					return true;
				}

				// all 3 are different
				else if (!lstTradedCards.get(0).card_type.equals(lstTradedCards.get(1).card_type)
						&& !lstTradedCards.get(1).card_type.equals(lstTradedCards.get(2).card_type)
						&& !lstTradedCards.get(0).card_type.equals(lstTradedCards.get(2).card_type)) {
					Utility.writeLog("card traded successfully");
					return true;
				}
			} else {
				if (isValidCountWildCard()) {
					Utility.writeLog("card traded successfully");
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * This method checks if the TradeCardSet is a WildCardSet or not.
	 *
	 * @return the boolean
	 */
	public Boolean isAWildCardSet() {
		for (RiskCardModel card : lstTradedCards) {
			if (card.card_type.equals("WILD"))
				return true;
		}
		return false;
	}
	
	/**
	 * Does card match current player territory.
	 */
	public int doesCardMatchCurrentPlayerTerritory() {
		int countMatchingCards = 0;

		for (RiskCardModel card : this.curPlayer.getCard()) {
			for (RiskTerritoryModel territory : this.curPlayer.getOccupiedTerritories()) {
				if (territory.getId() == card.territory)
					countMatchingCards++;
			}
		}

		return countMatchingCards;
	}

	/**
	 * Checks if is valid count wild card.
	 *
	 * @return the boolean
	 */
	public Boolean isValidCountWildCard() {
		int count = 0;
		for (RiskCardModel card : lstTradedCards) {
			if (card.card_type.equals("WILD")) {
				count++;
			}
		}
		return (count > 1 ? false : true);
	}

	private void assignTerritories() {
		int territoryId = -1;
		while ((territoryId = getUnOccupiedTerritory()) != -1) {

			occupyTerritoryByPlayer(territoryId, curPlayer);
			// if ((territoryId = getUnOccupiedTerritory()) != -1) {
			// occupyTerritoryByPlayer(territoryId, curPlayer);
			// } else {
			// occupyTerritoryByPlayer(getRandomOccupiedTerritoryByPlayer(curPlayer),
			// curPlayer);
			// }

			nextPlayer(true);
		}
	}

	private int getRandomOccupiedTerritoryByPlayer(RiskPlayerModel riskPlayer) {
		return riskPlayer.getOccupiedTerritories()
				.get(new Random().nextInt(riskPlayer.getOccupiedTerritories().size())).id;
	}

	private Boolean armiesExhaustedForAllPlayers() {
		for (RiskPlayerModel player : players) {
			if (player.getNumberOfArmies() > 0)
				return false;
		}
		return true;
	}

	public void occupyTerritoryByPlayer(int territoryId, RiskPlayerModel riskPlayer) {
		RiskTerritoryModel riskterritorymodel = territories.elementAt(territoryId);
		riskterritorymodel.setPlayer(riskPlayer);
		riskPlayer.occupyTerritory(riskterritorymodel);
		riskterritorymodel.addArmy();
		riskPlayer.looseArmy();
	}

	public int getUnOccupiedTerritory() {
		for (int territory = 0; territory < territories.size(); territory++) {
			if (!territories.get(territory).isOccupied())
				return territory;
		}
		return -1;
	}

	/**
	 * Validate load map.
	 */
	private void ValidateLoadMap() {
		if (isGameMapValid()) {
			loadGameMap();
			this.setIsGameMapValid(true);
		} else {
			this.setIsGameMapValid(false);
		}
	}

	/**
	 * Checks if is game map valid.
	 *
	 * @return true, if is game map valid
	 */
	private boolean isGameMapValid() {

		return isMapFormatValid();
	}

	/**
	 * Gets the attacker territory.
	 *
	 * @return the attacker territory
	 */
	public RiskTerritoryModel getaTerritory() {
		return aTerritory;
	}

	/**
	 * Sets the attacker territory.
	 *
	 * @param test
	 *            the new attacker territory
	 */
	public void setaTerritory(RiskTerritoryModel test) {
		aTerritory = test;
	}

	/**
	 * Gets the defender territories.
	 *
	 * @return the defender territories
	 */
	public RiskTerritoryModel getdTerritories() {
		return defenseTerritory;
	}

	/**
	 * Sets the defender territory.
	 *
	 * @param test
	 *            the new defender territory
	 */
	public void setdTerritory(RiskTerritoryModel test) {
		defenseTerritory = test;
	}

	/**
	 * Gets the territories.
	 *
	 * @return the territories
	 */
	public Vector<RiskTerritoryModel> getTerritories() {
		return territories;
	}

	/**
	 * Sets the territories.
	 *
	 * @param test
	 *            the new territories
	 */
	public void setTerritories(Vector<RiskTerritoryModel> test) {
		territories = test;
	}

	/**
	 * Gets the continents.
	 *
	 * @return the continents
	 */
	public Vector<RiskContinentModel> getContinents() {
		return continents;
	}

	/**
	 * Gets the current player bonus armies received.
	 *
	 * @return the current player bonus armies received
	 */
	public int getCurrentPlayerBonusArmiesRecieved() {
		return currentPlayerBonusArmiesRecieved;
	}

	/**
	 * Sets the continents.
	 *
	 * @param test
	 *            the new continents
	 */
	public void setContinents(Vector<RiskContinentModel> test) {
		continents = test;
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
	 * Sets the armies.
	 *
	 * @param test
	 *            the new armies
	 * 
	 */
	public void setArmies(int test) {
		armies = test;
	}

	/**
	 * Gets the current player.
	 *
	 * @return the current player
	 */
	public RiskPlayerModel getCurPlayer() {
		return curPlayer;
	}

	/**
	 * Notify phase view change.
	 * 
	 * @throws InterruptedException
	 */
	public void notifyPhaseViewChange() {
		// System.out.println(this.getState());

		if ((this.getState() == NEW_GAME) || (this.getState() == INITIAL_REINFORCE)) // 0|1
		{
			RiskStartupPhaseModel objRiskStartupPhaseModel = this.getRiskStartupPhaseModelObservable();
			objRiskStartupPhaseModel.setCurrentRiskGameObject(this);
			this.setRiskStartupPhaseModelObservable(objRiskStartupPhaseModel);
			this.getRiskStartupPhaseModelObservable().isChanged();
		}

		if (this.getState() == REINFORCE || this.getState() == START_TURN) {
			RiskReinforcementPhaseModel objRiskReinforcementPhaseModel = this
					.getRiskRiskReinforcementPhaseModelObservable();
			objRiskReinforcementPhaseModel.setCurrentRiskGameObject(this);
			this.setRiskRiskReinforcementPhaseModelObservable(objRiskReinforcementPhaseModel);
			this.getRiskRiskReinforcementPhaseModelObservable().isChanged();
		}

		if (this.getState() == ATTACK || this.getState() == ATTACKING || this.getState() == ATTACK_PHASE
				|| this.getState() == CAPTURE || this.getState() == DEFEATED) // 8|9|11
		{
			RiskAttackPhaseModel objRiskAttackPhaseModel = this.getRiskAttackPhaseModelObservable();
			objRiskAttackPhaseModel.setCurrentRiskGameObject(this);
			this.setRiskAttackPhaseModelObservable(objRiskAttackPhaseModel);
			this.getRiskAttackPhaseModelObservable().isChanged();
		}
		if (this.getState() == FORTIFY || this.getState() == FORTIFYING || this.getState() == FORTIFY_PHASE) {
			RiskFortifyPhaseModel objRiskFortifyPhaseModel = this.getRiskFortifyPhaseModelObservable();
			objRiskFortifyPhaseModel.setCurrentRiskGameObject(this);
			this.setRiskFortifyPhaseModelObservable(objRiskFortifyPhaseModel);
			this.getRiskFortifyPhaseModelObservable().isChanged();
			System.out.println("FORTIFY_PHASE");
		}
		// try {
		if (this.mainPanel != null && this.subPanel != null) {
			this.mainPanel.repaint();
			this.subPanel.repaint();
		}
		// Thread.sleep(0);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	/**
	 * Sets the current player.
	 *
	 * @param test
	 *            the new current player
	 * 
	 */
	public void setCurPlayer(RiskPlayerModel test) {
		curPlayer = test;

	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Vector<RiskPlayerModel> getPlayer() {
		return players;
	}

	/**
	 * Sets the player.
	 *
	 * @param test
	 *            the new player
	 * 
	 */
	public void setPlayer(Vector<RiskPlayerModel> test) {
		players = test;
	}

	/**
	 * Initialize card exchange view.
	 */
	private void initializeCardExchangeView() {
		RiskCardExchangeViewObserver riskCardExchangeViewObserver = RiskCardExchangeViewObserver.getInstance();
		for (RiskPlayerModel player : RiskGameModel.players)
			player.addObserver(riskCardExchangeViewObserver);
		riskCardExchangeViewObserver.generateCardExchangeView();
	}

	/**
	 * Initialize player domination view.
	 */
	private void initializePlayerDominationView() {
		RiskPlayerDominationViewObserver riskPlayerDominationViewObserver = RiskPlayerDominationViewObserver
				.getInstance();
		for (RiskPlayerModel player : RiskGameModel.players)
			player.addObserver(riskPlayerDominationViewObserver);
		riskPlayerDominationViewObserver.generatePhaseView();
	}

	/**
	 * This method initializes adds the players.
	 *
	 * @param nm
	 *            the player name
	 * 
	 * @return true, if successful
	 */
	static public boolean addPlayer(String nm, StrategyInterface IStrategy) {
		int size = players.size();
		if (size > 6)
			return false;
		RiskPlayerModel p = new RiskPlayerModel(nm, size, IStrategy);
		players.add(p);
		return true;
	}

	/**
	 * Removes the all player.
	 *
	 * @return true, if successful
	 */
	static public boolean removeAllPlayer() {
		players.clear();
		return true;
	}

	/**
	 * Fetch traded armies count.
	 *
	 */
	public static int fetchTradedArmiesCount() {
		GAME_TRADE_CARD_PHASE_COUNT++;
		return GAME_TRADE_CARD_PHASE_COUNT * 5;
	}

	/**
	 * This method initializes the first player as the initial player.
	 */
	public void initalPlayer() {
		curPlayer = players.elementAt(0);
	}

	/**
	 * This method finds the next player in the loop.
	 */
	public void nextPlayer(Boolean... isInitialization) {
		if (curPlayer == players.lastElement()) {
			curPlayer = players.elementAt(0);
			iter = 0;
		} else
			curPlayer = players.elementAt(++iter);

		if (isInitialization != null && isInitialization.length == 0)
			curPlayer.takeTurn(this);
	}

	/**
	 * This method removes the players from the vector list.
	 *
	 * @param p
	 *            risk game player
	 * 
	 */
	public void removePlayer(RiskPlayerModel p) {
		players.remove(p);
		players.trimToSize();
		iter--;

	}

	/**
	 * This method initializes the number of armies as per the number of players.
	 */
	public void distubuteArmies() {
		int numOfPlayers = players.size();
		if (numOfPlayers == 3)
			armies = 15;
		else if (numOfPlayers == 4)
			armies = 30;
		else if (numOfPlayers == 5)
			armies = 25;
		else if (numOfPlayers == 6)
			armies = 20;

		for (int index = 0; index < numOfPlayers; index++)
			players.elementAt(index).addArmies(armies);
		Utility.writeLog(armies + " armies added to each player");

	}

	/**
	 * Initialize deck.
	 */
	public void initializeDeck() {
		for (int index = 0; index < territories.size(); index++)
			deck.add(new RiskCardModel(index, index % 3));
		Random wildIndex = new Random();
		if (deck.size() > 0)
			for (int wildCardCount = 1; wildCardCount <= 2; wildCardCount++)
				deck.add(new RiskCardModel(wildIndex.nextInt(deck.size()), -1));
	}

	/**
	 * Draw card.
	 *
	 * @param player
	 *            risk game player
	 * 
	 */
	public void drawCard(RiskPlayerModel player) {
		Random draw = new Random();
		System.out.println(deck.size());
		int card = draw.nextInt(deck.size());

		RiskCardModel riskcardmodel = deck.elementAt(card);
		deck.remove(deck.elementAt(card));
		deck.trimToSize();
		player.setCard(riskcardmodel);
	}

	/**
	 * This method calculates the bonus.
	 *
	 * @return the int
	 */
	public int turnBonus() {
		int bonus = 0;
		bonus += collectReinforcements();
		System.out.println("Bonus " + bonus);
		bonus += collectReinforcementsFromContinent();

		return bonus;
	}

	/**
	 * This method calculates the reinforcement as per the number of territories
	 * current player owns. Min of 3 and max of numTerritory/3
	 *
	 * @return the int
	 */
	public int collectReinforcements() {
		// count how many territories owned by curPlayer
		int territoryAmount = curPlayer.numOfTerritories();
		double bonus;
		if (territoryAmount < 9)
			bonus = 3;
		else
			bonus = Math.floor(territoryAmount / 3);
		return (int) bonus;
	}

	/**
	 * This method calculates the reinforcement from Continent as every continent
	 * has a different control value.
	 *
	 * @return the int
	 */
	public int collectReinforcementsFromContinent() {
		int continentBonus = 0;
		int numOfCont = continents.size();
		// # of continents = 6
		for (int index = 0; index < numOfCont; index++) {
			boolean captured = continents.elementAt(index).isContinentCaptured(curPlayer);
			if (captured)
				continentBonus += continents.elementAt(index).getValue();
			System.out.println("Bonus " + continentBonus + " for " + continents.elementAt(index).getName());

		}
		return continentBonus;
	}

	/**
	 * Checks if is map format valid.
	 *
	 * @return the boolean
	 */
	private Boolean isMapFormatValid() {
		if (this.getIsBaseMapEdited())
			return checkTagsPresent(sbCurrentMapString.toString())
					&& checkAdjacentsPresentAllTerritories(sbCurrentMapString.toString())
					&& checkContinentsareValid(sbCurrentMapString.toString());
		else
			return checkTagsPresent(sbBaseMapString.toString())
					&& checkAdjacentsPresentAllTerritories(sbBaseMapString.toString())
					&& checkContinentsareValid(sbBaseMapString.toString());
	}

	/**
	 * Check tags present.
	 *
	 * @param mapText
	 *            the map text
	 * @return the boolean
	 */
	public Boolean checkTagsPresent(String mapText) {
		Boolean flag = ((mapText.indexOf("[Map]") != -1) && (mapText.indexOf("[Territories]") != -1)
				&& (mapText.indexOf("[Continents]") != -1) && (mapText.indexOf(";;") != -1)); // != -1 && );
		return flag;
	}

	/**
	 * Check adjacents present all territories.
	 *
	 * @param mapText
	 *            the map text
	 * @return the boolean
	 */
	public Boolean checkAdjacentsPresentAllTerritories(String mapText) {
		Boolean reachedTerritory = false;
		Boolean isAdjacentValid = true;
		for (String mapTextLine : mapText.split("\n")) {
			if (reachedTerritory == true) {
				if (!mapTextLine.equals("") && !mapTextLine.trim().equals(";;") && !mapTextLine.trim().equals("-")
						&& mapTextLine.split(",").length <= 4) // aa,0,1,con,
					return isAdjacentValid = false;
			}
			if (mapTextLine.equals("[Territories]"))
				reachedTerritory = true;
		}
		return isAdjacentValid;
	}

	/**
	 * Check continents are valid.
	 *
	 * @param mapText
	 *            the map text
	 * @return the boolean
	 */
	public Boolean checkContinentsareValid(String mapText) {
		Boolean reachedContinent = false;
		Boolean reachedTerritories = false;
		Boolean isContinentValid = true;
		List<String> mapContinents = new ArrayList<String>();
		for (String mapTextLine : mapText.split("\n")) {

			if (mapTextLine.equals("[Territories]"))
				reachedTerritories = true;

			if (reachedContinent && !reachedTerritories) {
				mapContinents.add(mapTextLine.split("=")[0]);
			}
			if (!mapTextLine.trim().equals("[Territories]") && !mapTextLine.trim().equals("-")
					&& !mapTextLine.trim().equals("") && !mapTextLine.trim().equals(";;") && reachedTerritories) {
				if (!mapContinents.contains(mapTextLine.split(",")[3]))
					return false;
			}
			if (mapTextLine.equals("[Continents]"))
				reachedContinent = true;
		}
		return isContinentValid;
	}

	/**
	 * Initialize map variables.
	 */
	public void initializeMapVariables() {
		sbBaseMapString = new StringBuilder();
		sbCurrentMapString = new StringBuilder();

		BufferedReader brEarthMapReader = new BufferedReader(new InputStreamReader(
				RiskStartGameController.class.getResourceAsStream(Utility.getMapPath("BaseEarthMap.map"))));
		BufferedReader brCurrentMapReader = new BufferedReader(new InputStreamReader(
				RiskStartGameController.class.getResourceAsStream(Utility.getMapPath("CurrentGameMap.map"))));
		String baseMapLine, currentMapLine;
		try {
			while ((baseMapLine = brEarthMapReader.readLine()) != null)
				sbBaseMapString.append(baseMapLine + "\n");

			while ((currentMapLine = brCurrentMapReader.readLine()) != null)
				sbCurrentMapString.append(currentMapLine + "\n");

			brCurrentMapReader.close();
			brEarthMapReader.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * This method loads the map by parsing it.
	 */
	public void loadGameMap() {
		boolean done = false;
		String nextLine;
		String regionName = "";
		int continentValue = 0;
		int territoryId;
		int territoriesContinent = 0;
		int x_coordinate = 0;
		int y_coordinate = 0;
		Vector<Integer> adjacents;
		Vector<Integer> contains;
		this.isBaseMapEdited = RiskController.isBaseMapEdited;
		try {
			InputStream fileLoadContinentTerritory;
			if (this.isBaseMapEdited)
				fileLoadContinentTerritory = RiskGameModel.class
						.getResourceAsStream(Utility.getMapPath("CurrentGameMap.map"));
			else
				fileLoadContinentTerritory = RiskGameModel.class
						.getResourceAsStream(Utility.getMapPath("BaseEarthMap.map"));

			Scanner fileLoadContinentTerritoryScanner = new Scanner(fileLoadContinentTerritory);

			while (fileLoadContinentTerritoryScanner.hasNextLine()) {
				done = false;
				nextLine = fileLoadContinentTerritoryScanner.nextLine();

				if (nextLine.equals("[Continents]")) {
					nextLine = fileLoadContinentTerritoryScanner.nextLine();

					do {

						if (nextLine.split("=").length > 1) {
							regionName = nextLine.split("=")[0];
							continentValue = Integer.parseInt((nextLine.split("=")[1]).trim());
						}
						contains = new Vector<Integer>();
						continents.add(new RiskContinentModel(regionName, contains, continentValue));
						nextLine = fileLoadContinentTerritoryScanner.nextLine();

						if (nextLine.equals(""))
							done = true;
					} while (done == false);
				} // end if continents

				if (nextLine.equals("[Territories]")) {
					nextLine = fileLoadContinentTerritoryScanner.nextLine();
					int index = 0;
					do {
						if (!(nextLine.equals("-") || nextLine.equals("") || nextLine.equals("[Adjacents]"))) {
							territoryId = index++;
							regionName = nextLine.split(",")[0];
							x_coordinate = Integer.parseInt(nextLine.split(",")[1]);
							y_coordinate = Integer.parseInt(nextLine.split(",")[2]);
							territoriesContinent = -1;
							for (int k = 0; k < continents.size(); k++) {
								if (continents.elementAt(k).getName().equals(nextLine.split(",")[3]))
									territoriesContinent = k;
							}

							territories.add(new RiskTerritoryModel(territoryId, regionName, territoriesContinent,
									x_coordinate, y_coordinate));

							for (int z = 0; z < continents.size(); z++) {
								if (continents.elementAt(z).getName().equals(nextLine.split(",")[3])) {
									continents.elementAt(z).AddTerritories(territoryId);
								}
							}

						}
						// System.out.println(index);
						nextLine = fileLoadContinentTerritoryScanner.nextLine();
						if (nextLine.equals(";;"))
							done = true;
					} while (done == false);

				} // end if countries

			} // end while

			InputStream fileLoadAdjacents;
			if (this.isBaseMapEdited)
				fileLoadAdjacents = RiskGameModel.class.getResourceAsStream(Utility.getMapPath("CurrentGameMap.map"));
			else
				fileLoadAdjacents = RiskGameModel.class.getResourceAsStream(Utility.getMapPath("BaseEarthMap.map"));

			Scanner fileLoadAdjacentsScanner = new Scanner(fileLoadAdjacents);
			while (fileLoadAdjacentsScanner.hasNextLine()) {

				nextLine = fileLoadAdjacentsScanner.nextLine();
				if (nextLine.equals("[Territories]")) {

					boolean Notendfile = true;
					do {
						nextLine = fileLoadAdjacentsScanner.nextLine();

						if (nextLine.equals(";;"))
							Notendfile = false;
						else if (!(nextLine.equals("-") || nextLine.equals("") || nextLine.equals("[Adjacents]"))) {
							String[] compenents = nextLine.split(",");
							String currentTerritory = compenents[0];

							adjacents = new Vector<Integer>();

							for (int j = 4; j < compenents.length; j++) {
								for (int z = 0; z < territories.size(); z++) {
									if (territories.elementAt(z).getName().equals(compenents[j])) {
										adjacents.add(territories.elementAt(z).getId());
									}
								}
							}

							for (RiskTerritoryModel territory : territories) {
								if (territory.getName().equals(currentTerritory))
									territory.setAdjacent(adjacents);
							}
						}
					} while (Notendfile);

				} // end if adjacents
			}
			fileLoadContinentTerritory.close();
			fileLoadAdjacents.close();
			fileLoadContinentTerritoryScanner.close();
			fileLoadAdjacentsScanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method sets up the gamePhaseSetup in the beginning.
	 *
	 * @param xcoordinate
	 *            the x-coordinate of country position
	 * @param ycoordinate
	 *            the y-coordinate of country position
	 */
	public void gamePhaseSetup(int xcoordinate, int ycoordinate) {

		int country = getMapLocation(xcoordinate, ycoordinate);
		int index = 0; // num of occupied countries

		if (getState() == INITIAL_REINFORCE) {
			if (country != -1) {
				if (getOwnership(country) == curPlayer.getPlayerIndex()) {
					occupyTerritory(territories.elementAt(country));
					nextPlayer();
				}
			}

			for (int riskcardmodel = 0; riskcardmodel < players.size(); riskcardmodel++) {
				if (players.elementAt(riskcardmodel).getNumberOfArmies() == 0)
					index++;
			}

			if (index == players.size()) {
				setState(START_TURN);
				System.out.println("status " + getState());
			}

			// startup
		} // end if INITIAL_REINFORCE

		if (getState() == NEW_GAME) {
			int num = numOfTerroitories();

			if (country != -1) { // if a country was clicked on
				if (getOwnership(country) == -1) // if country not owned
					initialOccupyTerritories(country); // current player now
				// owns
				// How many countries are already owned?
				for (int riskcardmodel = 0; riskcardmodel < num; riskcardmodel++) {
					if (getOwnership(riskcardmodel) != -1) // if country owned
						index++; // count number of owned countries
				} // end for loop
			} // end if country clicked on

			if (index == num) {
				// startup

				setState(INITIAL_REINFORCE);
				System.out.println("status " + getState());
			}
		} // end if NEW_GAME

	}

	/**
	 * This method activates the game phase to fortify.
	 *
	 * @param x
	 *            the x-coordinate of country position
	 * @param y
	 *            the y-coordinate of country position
	 * @return the string
	 */
	public String gamePhaseActive(int x, int y) {

		int country = getMapLocation(x, y);

		// if (getState() == FORTIFYING) {
		// RiskFortifying(country, false);
		// } // end fortifying
		//
		// if (getState() == FORTIFY) {
		// RiskFortify(country);
		// } // end forty

		if (getState() == FORTIFY || getState() == FORTIFYING || getState() == FORTIFY_PHASE) {
			return this.curPlayer.fortify(country, this);
		}

		// if (getState() == ATTACK_PHASE) {
		// // engageBattle(); // always commented
		// }

		// if (getState() == ATTACKING) {
		// RiskAttacking(country);
		// }
		//
		// if (getState() == ATTACK) {
		// Riskattack(country);
		// } // end attack with

		if (getState() == ATTACKING || getState() == ATTACK || getState() == ATTACK_PHASE || getState() == CAPTURE) {
			return this.curPlayer.attack(country, this);
		}

		// if (getState() == TRADE_CARDS) {
		// RiskTradeCards(country);
		// }

		if (getState() == TRADE_CARDS) {
			this.curPlayer.tradeCard(this);
		}

		// if (getState() == REINFORCE) {
		// RiskReinforce(country);
		// }

		if (getState() == REINFORCE) {
			return this.curPlayer.reinforce(country, this);
		}

		// if (getState() == START_TURN) {
		// RiskStartTurn(false);
		// }

		if (getState() == START_TURN) {
			return this.curPlayer.startTurn(this);
		}

		return "";
	}

	/**
	 * this function is for Risk fortifying.
	 *
	 * @param country
	 *            the country
	 * @return the string
	 */
	public String RiskFortifying(int country, boolean flag) {
		if (country != -1) {// not a country
			defenseTerritory = territories.elementAt(country); // move to
			// territory
			if (getOwnership(country) == curPlayer.getPlayerIndex())
				if (aTerritory.isAdjacent(defenseTerritory)) {// if its
					// adjacent...

					if (flag)
						return "true";
					this.notifyPhaseViewChange();
					setState(FORTIFY_PHASE);
					return "true";
				} else
					return "false";
		} // end if a county
		return "";
	}

	/**
	 * this function is for Risk trade cards.
	 *
	 * @param country
	 *            the country
	 * @return the string
	 */
	public String RiskTradeCards(int country) {
		if (country != -1) // if not a country
			if (getOwnership(country) == curPlayer.getPlayerIndex()) // if
			// owned
			{
				occupyTerritory(territories.elementAt(country)); // occupy
				this.notifyPhaseViewChange();
				setState(REINFORCE);
				return "true";
			}
		return "";
	}

	/**
	 * this function is for Risk start turn.
	 *
	 * @return the string
	 */
	public String RiskStartTurn(boolean isTest) {
		currentPlayerBonusArmiesRecieved = turnBonus();
		curPlayer.addArmies(currentPlayerBonusArmiesRecieved);
		if (!isTest)
			this.notifyPhaseViewChange();
		// recive turn bonus
		if (curPlayer.getCard().size() > 5) {
			setState(TRADE_CARDS);
			return "tradecards";
		} else {
			setState(REINFORCE);
			return "reinforce";
		}
	}

	/**
	 * this function is for Risk reinforce.
	 *
	 * @param country
	 *            the country
	 * @return the string
	 */
	public String RiskReinforce(int country) {
		if (country != -1) // if not a country
			if (getOwnership(country) == curPlayer.getPlayerIndex()) // if owned
			{
				occupyTerritory(territories.elementAt(country)); // occupy
				this.notifyPhaseViewChange();
				return "true";
			}
		return "";
	}

	/**
	 * this function is for Risk fortify.
	 *
	 * @param country
	 *            the country
	 * @return the string
	 */
	public String RiskFortify(int country) {
		if (country != -1) {
			if (getOwnership(country) == curPlayer.getPlayerIndex()) {
				setState(FORTIFYING);
				aTerritory = territories.elementAt(country);
				this.notifyPhaseViewChange(); // get the first country to
				// fotify
				return "true";
			}
		}
		return "false";
	}

	/**
	 * this function is for Risk attacking.
	 *
	 * @param country
	 *            the country
	 * @return the string
	 */
	public String RiskAttacking(int country) {
		if (country != -1) {// not a country
			RiskTerritoryModel d = territories.elementAt(country); // defending
			// territory

			System.out.println(aTerritory.getAdjacents().size());

			for (int index : aTerritory.getAdjacents()) {
				System.out.println(territories.get(index));
			}

			if (getOwnership(country) == curPlayer.getPlayerIndex())
				return "You own that territory.";
			if (aTerritory.isAdjacent(d)) {// if its adjacent...
				setState(ATTACK_PHASE);
				defenseTerritory = d;
				defender = d.getPlayer();
			} else
				// if its not adjacent
				System.out.println("That territory is not adjacent, try again.");

			return "That territory is not adjacent, try again.";

		}
		this.notifyPhaseViewChange();
		return "";

	}

	/**
	 * This function is about Risk attack.
	 *
	 * @param country
	 *            the country
	 * @return the string
	 */
	public String Riskattack(int country) {
		if (country != -1)// if not a country
			if (getOwnership(country) == curPlayer.getPlayerIndex()) {
				if (territories.elementAt(country).getArmies() < 2)
					return "Not enough armies to battle, need at least 2";
				else {
					setState(ATTACKING);
					aTerritory = territories.elementAt(country);
					return "true";
				}
			} // end is curPlayers country
		this.notifyPhaseViewChange();
		return "";
	}

	/**
	 * This function is about the details of the battle.
	 */
	public void engageBattle() {

		attackDieArray = new Integer[attackNum]; // 3 or 2 or 1
		defenceDieArray = new Integer[defenseNum];// 2 or 1
		Random attDice = new Random();

		// get value for each roll
		for (int index = 0; index < attackNum; index++)
			attackDieArray[index] = attDice.nextInt(6) + 1;
		for (int index = 0; index < defenseNum; index++)
			defenceDieArray[index] = attDice.nextInt(6) + 1;
		Arrays.sort(attackDieArray, Collections.reverseOrder());
		Arrays.sort(defenceDieArray, Collections.reverseOrder());

		attackdice = attackDieArray;

		if (attackNum == 1) {
			System.out.println(attackDieArray[0] + " vs " + defenceDieArray[0]);
			Utility.writeLog("Round 1 - Attacker - " + attackDieArray[0] + " Vs Defender " + defenceDieArray[0]);
			if (attackDieArray[0] > defenceDieArray[0]) {
				defenseTerritory.looseArmy();
				Utility.writeLog(
						"Defender lost round and one army. Current army count + " + defenseTerritory.getArmies());
			} else {
				aTerritory.looseArmy();
				Utility.writeLog("Attacker lost round and one army. Current army count + " + aTerritory.getArmies());
			}
		}
		if (attackNum > 1) { // attacking with more than 1
			System.out.println(attackDieArray[0] + " vs " + defenceDieArray[0]);
			Utility.writeLog("Round 1 - Attacker - " + attackDieArray[0] + " Vs Defender " + defenceDieArray[0]);
			if (attackDieArray[0] > defenceDieArray[0]) {
				defenseTerritory.looseArmy();
				Utility.writeLog(
						"Defender lost round and one army. Current army count + " + defenseTerritory.getArmies());
			} else {
				aTerritory.looseArmy();
				Utility.writeLog("Attacker lost round and one army. Current army count + " + aTerritory.getArmies());
			}

			if (defenseNum == 2) {
				Utility.writeLog("Round 2 - Attacker - " + attackDieArray[1] + " Vs Defender " + defenceDieArray[1]);
				System.out.print(attackDieArray[1] + " vs " + defenceDieArray[1]);
				if (attackDieArray[1] > defenceDieArray[1]) {
					Utility.writeLog(
							"Defender lost round and one army. Current army count + " + defenseTerritory.getArmies());
					defenseTerritory.looseArmy();
				} else {
					Utility.writeLog(
							"Attacker lost round and one army. Current army count + " + aTerritory.getArmies());
					aTerritory.looseArmy();
				}
			} // if defending with two
		}
		notifyPhaseViewChange();
		if (defenseTerritory.getArmies() == 0) {
			Utility.writeLog("Defender Lost!!");
			setState(CAPTURE);
			notifyPhaseViewChange();
			defenseTerritory.setPlayer(curPlayer);
		}
		if (aTerritory.getArmies() == 1) {
			Utility.writeLog("Attacker Lost!!");
			setState(DEFEATED);
			this.notifyPhaseViewChange(); // show defeat in phase view
			// setState(ACTIVE_TURN);
		}

		active = curPlayer;

	}

	/**
	 * Checks if is captured.
	 *
	 */
	public int isCaptured() {

		if (defender.getOccupiedTerritories().size() == 0) {
			System.out.println(defender.getName() + " lost the game.");
			removePlayer(defender);
			if (players.size() == 1) {
				System.out.print(active.getName() + " has won the game");
			}

		}
		return players.size();
	}

	/**
	 * Capture.
	 *
	 * @return true, if successful
	 */
	public boolean capture() {
		int armies = defenseNum;
		RiskTerritoryModel d = defenseTerritory;
		RiskTerritoryModel a = aTerritory;
		defender.looseTerritory(d);
		active.occupyTerritory(d);
		boolean flag = false;

		if (defender.getOccupiedTerritories().size() == 0) {
			Utility.writeLog(defender.getName() + " lost the game.");
			removePlayer(defender);
			if (players.size() == 1) {
				Utility.writeLog(active.getName() + " has won the game");
				flag = true;
			}
		}

		a.looseArmies(armies);
		d.addArmies(armies);

		// Draw a card
		if (drawn == false) {
			drawCard(curPlayer);
			//Utility.writeLog("RiskCardModel " + getCountryName(curPlayer.getCard().firstElement().territory));
			drawn = true;
		}

		setState(ACTIVE_TURN);
		// Reset battle variables
		defenseNum = 0;
		attackNum = 0;
		defenseTerritory = null;
		aTerritory = null;

		return flag;
	}

	/**
	 * Draw map.
	 *
	 * @param index
	 *            the country index
	 * 
	 * @return the int[]
	 */
	public int[] drawMap(int index) {
		int out[] = new int[2];
		out[0] = territories.elementAt(index).getX();
		out[1] = territories.elementAt(index).getY();
		return out;
	}

	/**
	 * Fill draw map.
	 *
	 * @param index
	 *            the country index
	 * 
	 * @param p
	 *            risk game player
	 * 
	 * @return the int[]
	 */
	public int[] fillDrawMap(int index, int p) {
		int loc[] = new int[2];
		if (territories.elementAt(index).getPlayer().getPlayerIndex() == p)
			loc = drawMap(index);
		return loc;
	}

	/**
	 * Gets the ownership.
	 *
	 * @param index
	 *            the country index
	 * 
	 * @return the ownership
	 */
	public int getOwnership(int index) {
		return territories.elementAt(index).getPlayer().getPlayerIndex();

	}

	/**
	 * Number of territories.
	 *
	 */
	public int numOfTerroitories() {
		int num = territories.size();
		return num;
	}

	/**
	 * Gets the map location.
	 *
	 * @param xcoordinate
	 *            the x-coordinate of territory
	 * 
	 * @param ycoordinate
	 *            the y-coordinate of territory
	 * 
	 * @return the map location
	 */
	public int getMapLocation(int xcoordinate, int ycoordinate) {
		int x1coordinate;
		int y1coordinate;
		int size = 30;
		for (int index = 0; index < territories.size(); index++) {
			x1coordinate = territories.elementAt(index).getX();
			y1coordinate = territories.elementAt(index).getY();
			if (Math.abs(x1coordinate - xcoordinate) <= size || Math.abs(x1coordinate - xcoordinate) <= size) {
				if (Math.abs(y1coordinate - ycoordinate) <= size || Math.abs(y1coordinate - ycoordinate) <= size) {
					return index;
				} // end if y
			} // end if x
		} // end for
		return -1;

	}

	/**
	 * This method checks if the territory is occupied by the current player or not.
	 *
	 * @param riskterritorymodel
	 *            the risk territory model
	 * 
	 * @return true, if successful
	 */
	public boolean occupyTerritory(RiskTerritoryModel riskterritorymodel) {
		// Make sure there are availble armies
		if (curPlayer.getNumberOfArmies() > 0) { // Checks if the territory is
			// occupied by the current
			// player.
			if (riskterritorymodel.getPlayer() == curPlayer) {
				riskterritorymodel.setPlayer(curPlayer);
				riskterritorymodel.addArmies(1);
				curPlayer.looseArmy();
				curPlayer.occupyTerritory(riskterritorymodel);
				return true;
			} // end if
		} // end if availble
		else
			System.out.println(curPlayer.getName() + " has no more Armies.");
		return false;
	} // end occupyTerritory

	/**
	 * Initial occupy territories.
	 *
	 * @param id
	 *            the territory id
	 * 
	 */
	public void initialOccupyTerritories(int id) {

		if (curPlayer.getNumberOfArmies() > 0) {
			occupyTerritoryByPlayer(id, curPlayer);
		} else
			System.out.println(curPlayer.getName() + " has no more Armies.");

	}

	/**
	 * Gets the country name.
	 *
	 * @param id
	 *            the id
	 * @return the country name
	 */
	public String getCountryName(int id) {
		if (id == -1)
			return "Nowhere";
		return territories.elementAt(id).getName();
	}

	/**
	 * Gets the player name.
	 *
	 * @param id
	 *            the id
	 * @return the player name
	 */
	public String getPlayerName(int id) {
		return territories.elementAt(id).getPlayer().getName();
	}

	/**
	 * Gets the current player.
	 *
	 * @return the current player
	 */
	public RiskPlayerModel getCurrentPlayer() {
		return curPlayer;
	}

	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public Vector<RiskPlayerModel> getPlayers() {
		return players;
	}

	/**
	 * Gets the territory at.
	 *
	 * @param index
	 *            the index
	 * @return the territory at
	 */
	public RiskTerritoryModel getTerritoryAt(int index) {
		return territories.elementAt(index);
	}

	/**
	 * Number of armies on territory.
	 *
	 * @param index
	 *            the index
	 * @return the int
	 */
	public int numOfArmiesOnTerritory(int index) {
		return territories.elementAt(index).getArmies();
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public int getState() {
		return gameState;
	}

	/**
	 * Sets the state.
	 *
	 * @param state
	 *            the new state
	 */
	public void setState(int state) {
		gameState = state;
	}

	/**
	 * Gets the attack.
	 *
	 * @return the attack
	 */
	public int getAttack() {
		return attackNum;
	}

	/**
	 * Sets the attack.
	 *
	 * @param num
	 *            the new attack
	 */
	public void setAttack(int num) {
		attackNum = num;
	}

	/**
	 * Gets the defend.
	 *
	 * @return the defend
	 */
	public int getDefend() {
		return defenseNum;
	}

	/**
	 * Sets the defend.
	 *
	 * @param num
	 *            the new defend
	 */
	public void setDefend(int num) {
		defenseNum = num;
	}

	/**
	 * Gets the risk startup phase model observable.
	 *
	 * @return the riskStartupPhaseModelObservable
	 */
	public RiskStartupPhaseModel getRiskStartupPhaseModelObservable() {
		return riskStartupPhaseModelObservable;
	}

	/**
	 * Sets the risk startup phase model observable.
	 *
	 * @param riskStartupPhaseModelObservable
	 *            the riskStartupPhaseModelObservable to set
	 */
	public void setRiskStartupPhaseModelObservable(RiskStartupPhaseModel riskStartupPhaseModelObservable) {
		this.riskStartupPhaseModelObservable = riskStartupPhaseModelObservable;
	}

	/**
	 * Gets the risk startup end phase model observable.
	 *
	 * @return the riskStartupPhaseModelObservable
	 */
	public RiskStartupEndPhaseModel getRiskStartupEndPhaseModelObservable() {
		return this.riskStartupEndPhaseModelObservable;
	}

	/**
	 * Sets the risk startup end phase model observable.
	 *
	 * @param objriskStartupEndPhaseModelObservable
	 *            the new risk startup end phase model observable
	 */
	public void setRiskStartupEndPhaseModelObservable(RiskStartupEndPhaseModel objriskStartupEndPhaseModelObservable) {
		this.riskStartupEndPhaseModelObservable = objriskStartupEndPhaseModelObservable;
	}

	/**
	 * Gets the risk risk reinforcement phase model observable.
	 *
	 * @return the riskRiskReinforcementPhaseModelObservable
	 */
	public RiskReinforcementPhaseModel getRiskRiskReinforcementPhaseModelObservable() {
		return riskRiskReinforcementPhaseModelObservable;
	}

	/**
	 * Sets the risk risk reinforcement phase model observable.
	 *
	 * @param riskRiskReinforcementPhaseModelObservable
	 *            the riskRiskReinforcementPhaseModelObservable to set
	 */
	public void setRiskRiskReinforcementPhaseModelObservable(
			RiskReinforcementPhaseModel riskRiskReinforcementPhaseModelObservable) {
		this.riskRiskReinforcementPhaseModelObservable = riskRiskReinforcementPhaseModelObservable;
	}

	/**
	 * Gets the risk attack phase model observable.
	 *
	 * @return the riskAttackPhaseModelObservable
	 */
	public RiskAttackPhaseModel getRiskAttackPhaseModelObservable() {
		return riskAttackPhaseModelObservable;
	}

	/**
	 * Sets the risk attack phase model observable.
	 *
	 * @param riskAttackPhaseModelObservable
	 *            the riskAttackPhaseModelObservable to set
	 */
	public void setRiskAttackPhaseModelObservable(RiskAttackPhaseModel riskAttackPhaseModelObservable) {
		this.riskAttackPhaseModelObservable = riskAttackPhaseModelObservable;
	}

	/**
	 * Gets the risk fortify phase model observable.
	 *
	 * @return the riskFortifyPhaseModelObservable
	 */
	public RiskFortifyPhaseModel getRiskFortifyPhaseModelObservable() {
		return riskFortifyPhaseModelObservable;
	}

	/**
	 * Sets the risk fortify phase model observable.
	 *
	 * @param riskFortifyPhaseModelObservable
	 *            the riskFortifyPhaseModelObservable to set
	 */
	public void setRiskFortifyPhaseModelObservable(RiskFortifyPhaseModel riskFortifyPhaseModelObservable) {
		this.riskFortifyPhaseModelObservable = riskFortifyPhaseModelObservable;
	}

	/**
	 * Gets the risk phase view observer.
	 *
	 * @return the riskPhaseViewObserver
	 */
	public RiskPhaseViewObserver getRiskPhaseViewObserver() {
		return riskPhaseViewObserver;
	}

	/**
	 * Sets the risk phase view observer.
	 *
	 * @param riskPhaseViewObserver
	 *            the riskPhaseViewObserver to set
	 */
	public void setRiskPhaseViewObserver(RiskPhaseViewObserver riskPhaseViewObserver) {
		this.riskPhaseViewObserver = riskPhaseViewObserver;
	}

	/**
	 * Gets the defence die array.
	 *
	 * @return the defence die array
	 */
	public Integer[] getDefenceDieArray() {
		return defenceDieArray;
	}

	/**
	 * Sets the defence die array.
	 *
	 * @param defenceDieArray
	 *            the new defence die array
	 */
	public void setDefenceDieArray(Integer[] defenceDieArray) {
		this.defenceDieArray = defenceDieArray;
	}

	/**
	 * Gets the attack die array.
	 *
	 * @return the attack die array
	 */
	public Integer[] getAttackDieArray() {
		return attackDieArray;
	}

	/**
	 * Sets the attack die array.
	 *
	 * @param attackDieArray
	 *            the new attack die array
	 */
	public void setAttackDieArray(Integer[] attackDieArray) {
		this.attackDieArray = attackDieArray;
	}

	/**
	 * Gets the risk player domination view observer.
	 *
	 * @return the risk player domination view observer
	 */
	public RiskPlayerDominationViewObserver getRiskPlayerDominationViewObserver() {
		return riskPlayerDominationViewObserver;
	}

	/**
	 * Sets the risk player domination view observer.
	 *
	 * @param riskPlayerDominationViewObserver
	 *            the new risk player domination view observer
	 */
	public void setRiskPlayerDominationViewObserver(RiskPlayerDominationViewObserver riskPlayerDominationViewObserver) {
		this.riskPlayerDominationViewObserver = riskPlayerDominationViewObserver;
	}

	/**
	 * Gets the checks if is base map edited.
	 *
	 * @return the checks if is base map edited
	 */
	public Boolean getIsBaseMapEdited() {
		return isBaseMapEdited;
	}

	/**
	 * Sets the checks if is base map edited.
	 *
	 * @param isBaseMapEdited
	 *            the new checks if is base map edited
	 */
	public void setIsBaseMapEdited(Boolean isBaseMapEdited) {
		this.isBaseMapEdited = isBaseMapEdited;
	}

	/**
	 * Gets the checks if is game map valid.
	 *
	 * @return the checks if is game map valid
	 */
	public Boolean getIsGameMapValid() {
		return isGameMapValid;
	}

	/**
	 * Sets the checks if is game map valid.
	 *
	 * @param isGameMapValid
	 *            the new checks if is game map valid
	 */
	public void setIsGameMapValid(Boolean isGameMapValid) {
		this.isGameMapValid = isGameMapValid;
	}

}
