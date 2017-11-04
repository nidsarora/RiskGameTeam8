package risk.model;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

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
import risk.view.RiskPhaseViewObserver;

/**
 * This class consists the business logic of the entire game.It consists of all
 * the phases of the game. It calculates and assigns the armies to the players
 * as per the territories occupied and the continents occupied.It also has the
 * logic for loading and parsing the map from the map file.
 */
public class RiskGameModel {

	// Game States
	public static final int NEW_GAME = 0;
	public static final int INITIAL_REINFORCE = 1;
	public static final int ACTIVE_TURN = 2;
	public static final int TURN_BONUS = 3;
	public static final int REINFORCE = 4;
	public static final int TRADE_CARDS = 5;
	public static final int START_TURN = 6;
	public static final int ATTACK = 7;
	public static final int ATTACKING = 8;
	public static final int ATTACK_PHASE = 9;
	public static final int BATTLING = 10;
	public static final int CAPTURE = 11;
	public static final int FORTIFY = 12;
	public static final int FORTIFYING = 13;
	public static final int FORTIFY_PHASE = 14;
	public static final int DEFEATED = 15;
	public static int GAME_TRADE_CARD_PHASE_COUNT = 0;

	int armies;

	public static final int GAME_OVER = 99;

	static public Vector<RiskTerritoryModel> territories = new Vector<RiskTerritoryModel>();
	public Vector<RiskContinentModel> continents = new Vector<RiskContinentModel>();
	static public Vector<RiskPlayerModel> players = new Vector<RiskPlayerModel>();
	public Vector<RiskCardModel> deck = new Vector<RiskCardModel>();
	public RiskPlayerModel curPlayer;
	public RiskPlayerModel defender;
	public RiskPlayerModel active;

	static private int gameState;
	public RiskTerritoryModel aTerritory;
	public RiskTerritoryModel dTerritory;
	public int defenseNum = 0;
	public int attackNum = 0;
	public int iter = 0;
	public boolean drawn;
	private Integer[] attackDieArray;
	private Integer[] defenceDieArray;
	private RiskStartupPhaseModel riskStartupPhaseModelObservable;
	private RiskReinforcementPhaseModel riskRiskReinforcementPhaseModelObservable;
	private RiskAttackPhaseModel riskAttackPhaseModelObservable;
	private RiskFortifyPhaseModel riskFortifyPhaseModelObservable;
	private RiskStartupEndPhaseModel riskStartupEndPhaseModelObservable;
	private int currentPlayerBonusArmiesRecieved;

	private RiskPhaseViewObserver riskPhaseViewObserver;

	public RiskTerritoryModel getaTerritory() {
		return aTerritory;
	}

	public void setaTerritory(RiskTerritoryModel test) {
		aTerritory = test;
	}

	public Vector<RiskTerritoryModel> getTerritories() {
		return territories;
	}

	public void setTerritories(Vector<RiskTerritoryModel> test) {
		territories = test;
	}

	public Vector<RiskContinentModel> getContinents() {
		return continents;
	}

	public int getCurrentPlayerBonusArmiesRecieved() {
		return currentPlayerBonusArmiesRecieved;
	}

	public void setContinents(Vector<RiskContinentModel> test) {
		continents = test;
	}

	public int getArmies() {
		return armies;
	}

	public void setArmies(int test) {
		armies = test;
	}

	public RiskPlayerModel getCurPlayer() {
		return curPlayer;
	}

	public void notifyPhaseViewChange() {
		// System.out.println(this.getState());

		if ((this.getState() == NEW_GAME) || (this.getState() == INITIAL_REINFORCE)) // 0|1
		{
			RiskStartupPhaseModel objRiskStartupPhaseModel = this.getRiskStartupPhaseModelObservable();
			objRiskStartupPhaseModel.setCurrentRiskGameObject(this);
			this.setRiskStartupPhaseModelObservable(objRiskStartupPhaseModel);
			this.getRiskStartupPhaseModelObservable().isChanged();
			// System.out.println("START_TURN");
		}

		if (this.getState() == START_TURN) // 6
		{
			RiskStartupEndPhaseModel objRiskStartupEndPhaseModel = this.getRiskStartupEndPhaseModelObservable();
			objRiskStartupEndPhaseModel.setCurrentRiskGameObject(this);
			this.setRiskStartupEndPhaseModelObservable(objRiskStartupEndPhaseModel);
			this.getRiskStartupEndPhaseModelObservable().isChanged();
			System.out.println("START_TURN");
		}

		if (this.getState() == REINFORCE) {
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
			System.out.println("ATTACK_PHASE");
		}
		if (this.getState() == FORTIFYING || this.getState() == FORTIFY_PHASE) {
			RiskFortifyPhaseModel objRiskFortifyPhaseModel = this.getRiskFortifyPhaseModelObservable();
			objRiskFortifyPhaseModel.setCurrentRiskGameObject(this);
			this.setRiskFortifyPhaseModelObservable(objRiskFortifyPhaseModel);
			this.getRiskFortifyPhaseModelObservable().isChanged();
			System.out.println("FORTIFY_PHASE");
		}

	}

	public void setCurPlayer(RiskPlayerModel test) {
		curPlayer = test;
	}

	public Vector<RiskPlayerModel> getPlayer() {
		return players;
	}

	public void setPlayer(Vector<RiskPlayerModel> test) {
		players = test;
	}

	public RiskGameModel(String test) {

	}

	public RiskGameModel() {

		// Setup Board
		gameState = NEW_GAME;

		initalPlayer();
		loadMap_newformat();
		initializeDeck();
		distubuteArmies();

	}

	/**
	 * This method initializes adds the players.
	 */
	static public boolean addPlayer(String nm) {
		int size = players.size();
		if (size > 6)
			return false;
		RiskPlayerModel p = new RiskPlayerModel(nm, size);
		players.add(p);
		return true;
	}

	static public boolean removeAllPlayer() {
		players.clear();
		return true;
	}

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
	public void nextPlayer() {
		if (curPlayer == players.lastElement()) {
			curPlayer = players.elementAt(0);
			iter = 0;
		} else
			curPlayer = players.elementAt(++iter);
	}

	/**
	 * This method removes the players from the vector list.
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
		// int armies = 0;
		if (numOfPlayers == 3)
			armies = 15;
		else if (numOfPlayers == 4)
			armies = 30;
		else if (numOfPlayers == 5)
			armies = 25;
		else if (numOfPlayers == 6)
			armies = 20;

		for (int i = 0; i < numOfPlayers; i++)
			players.elementAt(i).addArmies(armies);
	}

	public void initializeDeck() {
		for (int i = 0; i < territories.size(); i++)
			deck.add(new RiskCardModel(i % 3, i));
	}

	public void drawCard(RiskPlayerModel p) {
		Random draw = new Random();
		System.out.println(deck.size());
		int card = draw.nextInt(deck.size());

		RiskCardModel c = deck.elementAt(card);
		deck.remove(deck.elementAt(card));
		deck.trimToSize();
		p.setCard(c);
	}

	/**
	 * This method calculates the bonus.
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
	 */
	public int collectReinforcementsFromContinent() {
		int continentBonus = 0;
		int numOfCont = continents.size();
		// # of continents = 6
		for (int i = 0; i < numOfCont; i++) {
			boolean captured = continents.elementAt(i).isContinentCaptured(curPlayer);
			if (captured)
				continentBonus += continents.elementAt(i).getValue();
			System.out.println("Bonus " + continentBonus + " for " + continents.elementAt(i).getName());

		}
		return continentBonus;
	}

	public void loadMap() {

		boolean done = false;
		String next;
		String name;
		int value;
		int id;
		int continent;
		int x;
		int y;
		Vector<Integer> adjacents;
		Vector<Integer> contains;
		InputStream file = RiskGameModel.class.getResourceAsStream(Utility.getImagePath("MapFileNew.txt"));
		Scanner mapfile = new Scanner(file);

		while (mapfile.hasNextLine()) {
			done = false;
			next = mapfile.nextLine();

			if (next.equals("{continents}")) {
				next = mapfile.next();

				do {
					value = Integer.parseInt(next);
					name = mapfile.next().replace("_", " ");
					contains = new Vector<Integer>();
					next = mapfile.next();
					while (!next.equals(";")) {
						contains.add(Integer.parseInt(next));
						next = mapfile.next();
					}

					continents.add(new RiskContinentModel(name, contains, value));
					next = mapfile.next();

					if (next.equals(";;"))
						done = true;
				} while (done == false);
			} // end if continents

			if (next.equals("{countries}")) {
				next = mapfile.next();

				do {
					id = Integer.parseInt(next);
					name = mapfile.next().replace("_", " ");
					continent = Integer.parseInt(mapfile.next());
					x = Integer.parseInt(mapfile.next());
					y = Integer.parseInt(mapfile.next());

					territories.add(new RiskTerritoryModel(id, name, continent, x, y));
					// System.out.println(id + " " + name + " " + continent);
					next = mapfile.next();
					if (next.equals(";;"))
						done = true;

				} while (done == false);

			} // end if countries

			if (next.equals("{adjacents}")) {
				String c = mapfile.next();
				do {
					adjacents = new Vector<Integer>();
					next = mapfile.next();
					while (!next.equals(";")) {
						adjacents.add(Integer.parseInt(next));
						next = mapfile.next();
					}
					territories.elementAt(Integer.parseInt(c) - 1).setAdjacent(adjacents);
					c = mapfile.next();
					if (c.equals(";;"))
						done = true;
				} while (done == false);

			} // end if adjacents

		} // end while

		// } catch(FileNotFoundException e){e.printStackTrace();} //end try read
		// file

	}// end loadmap

	/**
	 * This method loads the map by parsing it.
	 */
	public void loadMap_newformat() {
		boolean done = false;
		String next;
		String name;
		int value;
		int id;
		int continent;
		int x;
		int y;
		Vector<Integer> adjacents;
		Vector<Integer> contains;

		try {

			InputStream file = RiskGameModel.class.getResourceAsStream(Utility.getMapPath("CurrentGameMap.map"));
			Scanner mapfile = new Scanner(file);

			while (mapfile.hasNextLine()) {
				done = false;
				next = mapfile.nextLine();

				if (next.equals("[Continents]")) {
					next = mapfile.nextLine();

					do {

						name = next.split("=")[0];
						value = Integer.parseInt((next.split("=")[1]).trim());
						contains = new Vector<Integer>();
						continents.add(new RiskContinentModel(name, contains, value));
						next = mapfile.nextLine();

						if (next.equals(""))
							done = true;
					} while (done == false);
				} // end if continents

				if (next.equals("[Territories]")) {
					next = mapfile.nextLine();
					int i = 0;
					do {
						if (!(next.equals("-") || next.equals("") || next.equals("[Adjacents]"))) {
							id = i++;
							name = next.split(",")[0];
							x = Integer.parseInt(next.split(",")[1]);
							y = Integer.parseInt(next.split(",")[2]);
							continent = -1;
							for (int k = 0; k < continents.size(); k++) {
								if (continents.elementAt(k).getName().equals(next.split(",")[3]))
									continent = k;
							}

							territories.add(new RiskTerritoryModel(id, name, continent, x, y));
							// System.out.println(id + " " + name + " " +
							// continent);

							for (int z = 0; z < continents.size(); z++) {
								if (continents.elementAt(z).getName().equals(next.split(",")[3])) {
									continents.elementAt(z).AddTerritories(id);
								}
							}

						}
						// System.out.println(i);
						next = mapfile.nextLine();
						if (next.equals(";;"))
							done = true;
					} while (done == false);

				} // end if countries

			} // end while

			// @SuppressWarnings("resource")
			InputStream file1 = RiskGameModel.class.getResourceAsStream(Utility.getMapPath("CurrentGameMap.map"));
			Scanner mapfile1 = new Scanner(file1);
			while (mapfile1.hasNextLine()) {

				next = mapfile1.nextLine();
				if (next.equals("[Territories]")) {

					boolean Notendfile = true;
					do {
						next = mapfile1.nextLine();

						if (next.equals(";;"))
							Notendfile = false;
						else if (!(next.equals("-") || next.equals("") || next.equals("[Adjacents]"))) {
							String[] all = next.split(",");
							String c = all[0];

							adjacents = new Vector<Integer>();

							for (int j = 4; j < all.length; j++) {
								for (int z = 0; z < territories.size(); z++) {
									if (territories.elementAt(z).getName().equals(all[j])) {
										adjacents.add(territories.elementAt(z).getId());
									}
								}
							}

							for (RiskTerritoryModel t : territories) {
								if (t.getName().equals(c))
									t.setAdjacent(adjacents);
							}
						}
					} while (Notendfile);

				} // end if adjacents
			}
			file.close();
			file1.close();
			mapfile.close();
			mapfile1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method sets up the gamePhaseSetup in the beginning.
	 */
	public void gamePhaseSetup(int x, int y) {

		int country = getMapLocation(x, y);
		int i = 0; // num of occupied countries

		if (getState() == INITIAL_REINFORCE) {
			if (country != -1) {
				if (getOwnership(country) == curPlayer.getPlayerIndex()) {
					occupyTerritory(territories.elementAt(country));
					nextPlayer();
				}
			}

			for (int c = 0; c < players.size(); c++) {
				if (players.elementAt(c).getNumberOfArmies() == 0)
					i++;
			}

			if (i == players.size()) {
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
				for (int c = 0; c < num; c++) {
					if (getOwnership(c) != -1) // if country owned
						i++; // count number of owned countries
				} // end for loop
			} // end if country clicked on

			if (i == num) {
				// startup

				setState(INITIAL_REINFORCE);
				System.out.println("status " + getState());
			}
		} // end if NEW_GAME

	}

	/**
	 * This method activates the game phase to fortify.
	 */
	public String gamePhaseActive(int x, int y) {

		int country = getMapLocation(x, y);

		if (getState() == FORTIFYING) {
			if (country != -1) {// not a country
				dTerritory = territories.elementAt(country); // move to
				// territory
				if (getOwnership(country) == curPlayer.getPlayerIndex())
					if (aTerritory.isAdjacent(dTerritory)) {// if its
						// adjacent...
						this.notifyPhaseViewChange(); // 2nd country to fortify
						setState(FORTIFY_PHASE);
					}
			} // end if a county

		} // end fortifying

		if (getState() == FORTIFY) {
			if (country != -1) {
				if (getOwnership(country) == curPlayer.getPlayerIndex()) {
					setState(FORTIFYING);
					aTerritory = territories.elementAt(country);
					this.notifyPhaseViewChange(); // get the first country to fotify
				}
			}

		} // end forty

		if (getState() == ATTACK_PHASE) {

			// engageBattle();

		}

		if (getState() == ATTACKING) { // PLayer click the 2nd country to defend With
			if (country != -1) {// not a country
				RiskTerritoryModel d = territories.elementAt(country); // defending
				// territory

				System.out.println(aTerritory.getAdjacents().size());

				for (int i : aTerritory.getAdjacents()) {
					System.out.println(territories.get(i));
				}

				if (getOwnership(country) == curPlayer.getPlayerIndex())
					return "You own that territory.";
				if (aTerritory.isAdjacent(d)) {// if its adjacent...
					setState(ATTACK_PHASE);
					dTerritory = d;
					defender = d.getPlayer();
				} else
					// if its not adjacent
					System.out.println("That territory is not adjacent, try again.");
				return "That territory is not adjacent, try again.";
			}
			this.notifyPhaseViewChange();
		}

		if (getState() == ATTACK) {
			if (country != -1)// if not a country
				if (getOwnership(country) == curPlayer.getPlayerIndex()) {
					if (territories.elementAt(country).getArmies() < 2)
						return "Not enough armies to battle, need at least 2";
					else {
						setState(ATTACKING);
						aTerritory = territories.elementAt(country); // first country to attack
					}
				} // end is curPlayers country
			this.notifyPhaseViewChange();
		} // end attack with

		if (getState() == TRADE_CARDS) {
			if (country != -1) // if not a country
				if (getOwnership(country) == curPlayer.getPlayerIndex()) // if
					// owned
					occupyTerritory(territories.elementAt(country)); // occupy
		}

		if (getState() == REINFORCE) {

			if (country != -1) // if not a country
				if (getOwnership(country) == curPlayer.getPlayerIndex()) // if
					// owned
					occupyTerritory(territories.elementAt(country)); // occupy

		}

		if (getState() == START_TURN) {
			currentPlayerBonusArmiesRecieved = turnBonus();
			curPlayer.addArmies(currentPlayerBonusArmiesRecieved); // recive turn bonus
			this.notifyPhaseViewChange();

			if (curPlayer.getCard().size() > 5)
				setState(TRADE_CARDS);
			else
				setState(REINFORCE);

			System.out.println("status " + getState());
		}

		return "";
	}

	public void engageBattle() {

		attackDieArray = new Integer[attackNum]; // 3 or 2 or 1
		defenceDieArray = new Integer[defenseNum];// 2 or 1
		Random attDice = new Random();

		// get value for each roll
		for (int i = 0; i < attackNum; i++)
			attackDieArray[i] = attDice.nextInt(6) + 1;
		for (int i = 0; i < defenseNum; i++)
			defenceDieArray[i] = attDice.nextInt(6) + 1;
		Arrays.sort(attackDieArray, Collections.reverseOrder());
		Arrays.sort(defenceDieArray, Collections.reverseOrder());

		if (attackNum == 1) {
			System.out.println(attackDieArray[0] + " vs " + defenceDieArray[0]);
			if (attackDieArray[0] > defenceDieArray[0])
				dTerritory.looseArmy();
			else
				aTerritory.looseArmy();
		}
		if (attackNum > 1) { // attacking with more than 1
			System.out.println(attackDieArray[0] + " vs " + defenceDieArray[0]);
			if (attackDieArray[0] > defenceDieArray[0])
				dTerritory.looseArmy();
			else
				aTerritory.looseArmy();
			if (defenseNum == 2) {
				System.out.print(attackDieArray[1] + " vs " + defenceDieArray[1]);
				if (attackDieArray[1] > defenceDieArray[1])
					dTerritory.looseArmy();
				else
					aTerritory.looseArmy();
			} // if defending with two
		}
		notifyPhaseViewChange();
		if (dTerritory.getArmies() == 0) {
			setState(CAPTURE);
			dTerritory.setPlayer(curPlayer);
		}
		if (aTerritory.getArmies() == 0) {
			setState(DEFEATED);
			// setState(ACTIVE_TURN);
		}

		active = curPlayer;

	}

	public void capture() {
		int armies = defenseNum;
		RiskTerritoryModel d = dTerritory;
		RiskTerritoryModel a = aTerritory;
		defender.looseTerritory(d);
		active.occupyTerritory(d);

		if (defender.getOccupiedTerritories().size() == 0) {
			System.out.println(defender.getName() + " lost the game.");
			removePlayer(defender);
			if (players.size() == 1) {
				System.out.print(active.getName() + " has won the game");
			}
		}

		a.looseArmies(armies);
		d.addArmies(armies);

		// Draw a card
		if (drawn == false) {
			drawCard(curPlayer);
			System.out.println("RiskCardModel " + getCountryName(curPlayer.getCard().firstElement().territory));
			drawn = true;
		}

		setState(ACTIVE_TURN);
		// Reset battle variables
		defenseNum = 0;
		attackNum = 0;
		dTerritory = null;
		aTerritory = null;

	}

	public int[] drawMap(int i) {
		int out[] = new int[2];
		out[0] = territories.elementAt(i).getX();
		out[1] = territories.elementAt(i).getY();
		return out;
	}

	public int[] fillDrawMap(int i, int p) {
		int loc[] = new int[2];
		if (territories.elementAt(i).getPlayer().getPlayerIndex() == p)
			loc = drawMap(i);
		return loc;
	}

	public int getOwnership(int i) {
		return territories.elementAt(i).getPlayer().getPlayerIndex();

	}

	public int numOfTerroitories() {
		int num = territories.size();
		return num;
	}

	public int getMapLocation(int x, int y) {
		int x1;
		int y1;
		int size = 30;
		for (int i = 0; i < territories.size(); i++) {
			x1 = territories.elementAt(i).getX();
			y1 = territories.elementAt(i).getY();
			if (Math.abs(x1 - x) <= size || Math.abs(x1 - x) <= size) {
				if (Math.abs(y1 - y) <= size || Math.abs(y1 - y) <= size) {
					return i;
				} // end if y
			} // end if x
		} // end for
		return -1;

	}

	/**
	 * This method checks if the territory is occupied by the current player or not.
	 */
	public boolean occupyTerritory(RiskTerritoryModel t) {
		// Make sure there are availble armies
		if (curPlayer.getNumberOfArmies() > 0) { // Checks if the territory is
			// occupied by the current
			// player.
			if (t.getPlayer() == curPlayer) {
				t.setPlayer(curPlayer);
				t.addArmies(1);
				curPlayer.looseArmy();
				return true;
			} // end if
		} // end if availble
		else
			System.out.println(curPlayer.getName() + " has no more Armies.");
		return false;
	} // end occupyTerritory

	public void initialOccupyTerritories(int id) {

		if (curPlayer.getNumberOfArmies() > 0) {
			RiskTerritoryModel t = territories.elementAt(id);
			t.setPlayer(curPlayer);
			curPlayer.occupyTerritory(t);
			t.addArmy();
			curPlayer.looseArmy();
			nextPlayer();
		} else
			System.out.println(curPlayer.getName() + " has no more Armies.");

	}

	public String getCountryName(int id) {
		if (id == -1)
			return "Nowhere";
		return territories.elementAt(id).getName();
	}

	public String getPlayerName(int id) {
		return territories.elementAt(id).getPlayer().getName();
	}

	public RiskPlayerModel getCurrentPlayer() {
		return curPlayer;
	}

	public Vector<RiskPlayerModel> getPlayers() {
		return players;
	}

	public RiskTerritoryModel getTerritoryAt(int i) {
		// if (i >= 0)
		return territories.elementAt(i);
	}

	public int numOfArmiesOnTerritory(int i) {
		return territories.elementAt(i).getArmies();
	}

	public int getState() {
		return gameState;
	}

	public void setState(int state) {
		gameState = state;
	}

	public void setAttack(int num) {
		attackNum = num;
	}

	public void setDefend(int num) {
		defenseNum = num;
	}

	/**
	 * @return the riskStartupPhaseModelObservable
	 */
	public RiskStartupPhaseModel getRiskStartupPhaseModelObservable() {
		return riskStartupPhaseModelObservable;
	}

	/**
	 * @param riskStartupPhaseModelObservable
	 *            the riskStartupPhaseModelObservable to set
	 */
	public void setRiskStartupPhaseModelObservable(RiskStartupPhaseModel riskStartupPhaseModelObservable) {
		this.riskStartupPhaseModelObservable = riskStartupPhaseModelObservable;
	}

	/**
	 * @return the riskStartupPhaseModelObservable
	 */
	public RiskStartupEndPhaseModel getRiskStartupEndPhaseModelObservable() {
		return this.riskStartupEndPhaseModelObservable;
	}

	/**
	 * @param riskStartupPhaseModelObservable
	 *            the riskStartupPhaseModelObservable to set
	 */
	public void setRiskStartupEndPhaseModelObservable(RiskStartupEndPhaseModel objriskStartupEndPhaseModelObservable) {
		this.riskStartupEndPhaseModelObservable = objriskStartupEndPhaseModelObservable;
	}

	/**
	 * @return the riskRiskReinforcementPhaseModelObservable
	 */
	public RiskReinforcementPhaseModel getRiskRiskReinforcementPhaseModelObservable() {
		return riskRiskReinforcementPhaseModelObservable;
	}

	/**
	 * @param riskRiskReinforcementPhaseModelObservable
	 *            the riskRiskReinforcementPhaseModelObservable to set
	 */
	public void setRiskRiskReinforcementPhaseModelObservable(
			RiskReinforcementPhaseModel riskRiskReinforcementPhaseModelObservable) {
		this.riskRiskReinforcementPhaseModelObservable = riskRiskReinforcementPhaseModelObservable;
	}

	/**
	 * @return the riskAttackPhaseModelObservable
	 */
	public RiskAttackPhaseModel getRiskAttackPhaseModelObservable() {
		return riskAttackPhaseModelObservable;
	}

	/**
	 * @param riskAttackPhaseModelObservable
	 *            the riskAttackPhaseModelObservable to set
	 */
	public void setRiskAttackPhaseModelObservable(RiskAttackPhaseModel riskAttackPhaseModelObservable) {
		this.riskAttackPhaseModelObservable = riskAttackPhaseModelObservable;
	}

	/**
	 * @return the riskFortifyPhaseModelObservable
	 */
	public RiskFortifyPhaseModel getRiskFortifyPhaseModelObservable() {
		return riskFortifyPhaseModelObservable;
	}

	/**
	 * @param riskFortifyPhaseModelObservable
	 *            the riskFortifyPhaseModelObservable to set
	 */
	public void setRiskFortifyPhaseModelObservable(RiskFortifyPhaseModel riskFortifyPhaseModelObservable) {
		this.riskFortifyPhaseModelObservable = riskFortifyPhaseModelObservable;
	}

	/**
	 * @return the riskPhaseViewObserver
	 */
	public RiskPhaseViewObserver getRiskPhaseViewObserver() {
		return riskPhaseViewObserver;
	}

	/**
	 * @param riskPhaseViewObserver
	 *            the riskPhaseViewObserver to set
	 */
	public void setRiskPhaseViewObserver(RiskPhaseViewObserver riskPhaseViewObserver) {
		this.riskPhaseViewObserver = riskPhaseViewObserver;
	}

	public Integer[] getDefenceDieArray() {
		return defenceDieArray;
	}

	public void setDefenceDieArray(Integer[] defenceDieArray) {
		this.defenceDieArray = defenceDieArray;
	}

	public Integer[] getAttackDieArray() {
		return attackDieArray;
	}

	public void setAttackDieArray(Integer[] attackDieArray) {
		this.attackDieArray = attackDieArray;
	}

}
