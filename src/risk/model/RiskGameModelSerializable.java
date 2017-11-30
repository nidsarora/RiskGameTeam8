
package risk.model;


import java.io.Serializable;

import java.util.Vector;

import risk.model.RiskPlayerModel;
import risk.model.RiskTerritoryModel;


/**
 * This class consists the business logic of the entire game.It consists of all
 * the phases of the game. It calculates and assigns the armies to the players
 * as per the territories occupied and the continents occupied.It also has the
 * logic for loading and parsing the map from the map file.
 * 
 * @author Team8
 */
public class RiskGameModelSerializable implements Serializable {

	/** The Constant NEW_GAME. */
	// Game States
	public  int NEW_GAME = 0;

	/** The Constant INITIAL_REINFORCE. */
	public  int INITIAL_REINFORCE = 1;

	/** The Constant ACTIVE_TURN. */
	public  int ACTIVE_TURN = 2;

	/** The Constant TURN_BONUS. */
	public  int TURN_BONUS = 3;

	/** The Constant REINFORCE. */
	public  int REINFORCE = 4;
	
	/** The Constant TRADE_CARDS. */
	public  int TRADE_CARDS = 5;

	/** The Constant START_TURN. */
	public  int START_TURN = 6;

	/** The Constant ATTACK. */
	public  int ATTACK = 7;

	/** The Constant ATTACKING. */
	public  int ATTACKING = 8;

	/** The Constant ATTACK_PHASE. */
	public  int ATTACK_PHASE = 9;

	/** The Constant BATTLING. */
	public  int BATTLING = 10;

	/** The Constant CAPTURE. */
	public  int CAPTURE = 11;

	/** The Constant FORTIFY. */
	public  int FORTIFY = 12;

	/** The Constant FORTIFYING. */
	public  int FORTIFYING = 13;

	/** The Constant FORTIFY_PHASE. */
	public int FORTIFY_PHASE = 14;

	/** The Constant DEFEATED. */
	public  int DEFEATED = 15;

	/** The game trade card phase count. */
	public int GAME_TRADE_CARD_PHASE_COUNT = 0;

	/** The Constant END_GAME. */
	public  int END_GAME = 100;

	/** The Constant GAME_OVER. */
	public  int GAME_OVER = 99;

	/** The territories. */
	public Vector<RiskTerritoryModel> territories = new Vector<RiskTerritoryModel>();


	/** The players. */
	public Vector<RiskPlayerModel> players = new Vector<RiskPlayerModel>();

	/** The game state. */
	public int gameState;
	public Boolean isTournamentMode;

}
