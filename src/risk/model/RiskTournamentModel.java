package risk.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.rmi.CORBA.Util;


import risk.helpers.Utility;
/**
 * The Class RiskTournamentModel.
 */
public class RiskTournamentModel implements Serializable {
	
	/** The tournament game list. */
	public List<RiskGameModel> tournamentGameList;
	
	/** The tournament game max turn count. */
	public static int tournamentGameMaxTurnCount;
	
	/** The tournament game map name list. */
	public List<String> tournamentGameMapNameList;
	
	/** The tournament game count. */
	public int tournamentGameCount;
	
	/** The game per map count. */
	public int gamePerMapCount;
	
	/** The tournament players. */
	public Vector<RiskPlayerModel> tournamentPlayers = new Vector<RiskPlayerModel>(); 
	
	/** The map count. */
	public int mapCount;

	/**
	 * Instantiates a new risk tournament model.
	 *
	 * @param tournamentMapCount the tournament map count
	 * @param GamePerMapCount the game per map count
	 * @param players the players
	 * @param GameTurnCount the game turn count
	 * @param mapNameList the map name list
	 */
	public RiskTournamentModel(int tournamentMapCount, int GamePerMapCount, Vector<RiskPlayerModel> players,
			int GameTurnCount, ArrayList<String> mapNameList) {
		this.mapCount = tournamentMapCount;
		this.gamePerMapCount = GamePerMapCount;
		this.tournamentPlayers = players;
		tournamentGameMaxTurnCount = GameTurnCount;
		this.tournamentGameMapNameList = mapNameList;
	}

	/**
	 * Initialize tournament.
	 */
	public void initializeTournament() {
		RiskGameModel.isTournamentMode = true;
		setTournamentGameCount();
		getTournamentGameList();
		setTournamentGamesMapName();
	}

	/**
	 * Sets the tournament games map name.
	 *
	 * @return the list
	 */
	public List<RiskGameModel> setTournamentGamesMapName() {
		for (int gameIndex = 0; gameIndex < this.tournamentGameMapNameList.size(); gameIndex++)
			tournamentGameList.get(gameIndex).currentTournamentGameMapName = tournamentGameMapNameList.get(gameIndex);
		return this.tournamentGameList;
	}

	/**
	 * Sets the tournament game count.
	 */
	public void setTournamentGameCount() {
		this.tournamentGameCount = tournamentGameMapNameList.size();
	}

	/**
	 * Gets the tournament game list.
	 *
	 * @return the tournament game list
	 */
	public List<RiskGameModel> setTestTournamentGameCount(int count){
	    int gameCount = 1;
		if (tournamentGameList == null)
			tournamentGameList = new ArrayList<RiskGameModel>();
		while (gameCount <= tournamentGameCount) {
			RiskGameModel.players = new Vector<RiskPlayerModel>();
			Vector<RiskPlayerModel> tclone = (Vector<RiskPlayerModel>) tournamentPlayers.clone();
			RiskGameModel.players =  tclone;
			RiskGameModel.territories = new Vector<RiskTerritoryModel>();
			tournamentGameList.add(new RiskGameModel());
			gameCount++;
		}
		return tournamentGameList;
	}
	public List<RiskGameModel> getTournamentGameList() {
		int gameCount = 1;
		if (tournamentGameList == null)
			tournamentGameList = new ArrayList<RiskGameModel>();
		while (gameCount <= tournamentGameCount) {
			RiskGameModel.players = new Vector<RiskPlayerModel>();
			Vector<RiskPlayerModel> tclone = (Vector<RiskPlayerModel>) tournamentPlayers.clone();
			RiskGameModel.players =  tclone;
			RiskGameModel.territories = new Vector<RiskTerritoryModel>();
			tournamentGameList.add(new RiskGameModel(tournamentGameMapNameList.get(gameCount - 1)));
			gameCount++;
		}
		return tournamentGameList;
	}

	/**
	 * Start tournament.
	 */
	public void startTournament() {
		int gameIndex = 1;
		for (RiskGameModel game : this.tournamentGameList) {			
			RiskGameModel.players = game.getplayersInstantiable();
			RiskGameModel.territories = game.getTerritoriesInstantiable();
			game.setState(RiskGameModel.INITIAL_REINFORCE);
			Utility.writeLog("Game" + (gameIndex++) + " played on " + game.currentTournamentGameMapName);
			game.curPlayer.takeTurn(game);
		}
	}

	/**
	 * Prints the tournament result.
	 */
	public void printTournamentResult() {		
		Utility.writeLog("********Tournament Results*******");
		Object[][] resultArray = new Object[tournamentGameMapNameList.size() / gamePerMapCount + 1][gamePerMapCount
				+ 1];
		for (int rowIndex = 1; rowIndex < resultArray.length; rowIndex++) {
			resultArray[rowIndex][0] = tournamentGameMapNameList
					.get((rowIndex - 1) * tournamentGameMapNameList.size() / this.mapCount);
		}

		for (int colIndex = 1; colIndex < resultArray[0].length; colIndex++) {
			resultArray[0][colIndex] = "Game " + colIndex;
		}

		for (int j = 1; j < resultArray.length; j++) {
			for (int i = 1; i < resultArray[j].length; i++) {
				for (int gameIndex = 0; gameIndex < tournamentGameList.size(); gameIndex++) {
					if (tournamentGameList.get(gameIndex).currentTournamentGameMapName
							.equals(String.valueOf(resultArray[j][0]))) {
						if (tournamentGameList.get(gameIndex).winner != null)
							resultArray[j][i++] = tournamentGameList.get(gameIndex).winner.getStrategy().getClass();
						else
							resultArray[j][i++] = "Draw";
					}
				}
			}
		}
		StringBuilder sbTournamentResults = new StringBuilder();
		for (int outerIndex = 0; outerIndex < resultArray.length; outerIndex++) {
			for (int innerIndex = 0; innerIndex < resultArray[outerIndex].length; innerIndex++) {
				sbTournamentResults.append(
						(resultArray[outerIndex][innerIndex] == null ? " " : resultArray[outerIndex][innerIndex])
								+ " ");
			}
			sbTournamentResults.append("\n");
		}
		Utility.writeLog(sbTournamentResults.toString());

		System.out.println("Done");
	}

}
