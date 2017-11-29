package risk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RiskTournamentModel {
	public List<RiskGameModel> tournamentGameList;
	public static int tournamentGameMaxTurnCount;
	public List<String> tournamentGameMapNameList;
	public int tournamentGameCount;
	public int gamePerMapCount;
	public Vector<RiskPlayerModel> tournamentPlayers;

	public RiskTournamentModel(int GamePerMapCount, Vector<RiskPlayerModel> players, int GameTurnCount,
			ArrayList<String> mapNameList) {
		this.gamePerMapCount = GamePerMapCount;
		this.tournamentPlayers = players;
		tournamentGameMaxTurnCount = GameTurnCount;
		this.tournamentGameMapNameList = mapNameList;
	}

	public void initializeTournament() {
		RiskGameModel.isTournamentMode = true;
		setTournamentGameCount();
		getTournamentGameList();
		//setTournamentGamesMapName();		
	}
	
	public List<RiskGameModel> setTournamentGamesMapName() {
		for (int gameIndex = 0; gameIndex < this.tournamentGameMapNameList.size(); gameIndex++)
			tournamentGameList.get(gameIndex).currentTournamentGameMapName = tournamentGameMapNameList.get(gameIndex);
		return this.tournamentGameList;
	}

	public void setTournamentGameCount() {
		this.tournamentGameCount = tournamentGameMapNameList.size();
	}

	public List<RiskGameModel> getTournamentGameList() {
		int gameCount = 1;
		if (tournamentGameList == null)
			tournamentGameList = new ArrayList<RiskGameModel>();
		while (gameCount <= tournamentGameCount) {
			RiskGameModel.players = this.tournamentPlayers;
			tournamentGameList.add(new RiskGameModel(tournamentGameMapNameList.get(gameCount -1)));
			gameCount++;
		}
		return tournamentGameList;
	}

	public void startTournament() {		
		for (RiskGameModel game : this.tournamentGameList) {
			game.curPlayer.takeTurn(game);			
		}
	}
	
	public void printTournamentResult() {
		Object[][] resultArray = new Object[tournamentGameMapNameList.size()/gamePerMapCount + 1][gamePerMapCount + 1];
		for(int rowIndex =1; rowIndex < resultArray.length; rowIndex++) {
			resultArray[rowIndex][0] = tournamentGameMapNameList.get(rowIndex * 4);
		}
		
		for(int colIndex =1; colIndex < resultArray[0].length; colIndex++) {
			resultArray[0][colIndex] = "Game " + colIndex;
		}
		
		for(Object[] rows:resultArray) {
			
		}
		
		
	}

}
