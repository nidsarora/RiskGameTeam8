package risk.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.rmi.CORBA.Util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import risk.helpers.Utility;

public class RiskTournamentModel implements Serializable {
	public List<RiskGameModel> tournamentGameList;
	public static int tournamentGameMaxTurnCount;
	public List<String> tournamentGameMapNameList;
	public int tournamentGameCount;
	public int gamePerMapCount;
	public Vector<RiskPlayerModel> tournamentPlayers = new Vector<RiskPlayerModel>(); 
	public int mapCount;

	public RiskTournamentModel(int tournamentMapCount, int GamePerMapCount, Vector<RiskPlayerModel> players,
			int GameTurnCount, ArrayList<String> mapNameList) {
		this.mapCount = tournamentMapCount;
		this.gamePerMapCount = GamePerMapCount;
		tournamentPlayers = players;
		tournamentGameMaxTurnCount = GameTurnCount;
		this.tournamentGameMapNameList = mapNameList;
	}

	public void initializeTournament() {
		RiskGameModel.isTournamentMode = true;
		setTournamentGameCount();
		getTournamentGameList();
		setTournamentGamesMapName();
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
			RiskGameModel.players = new Vector<RiskPlayerModel>();
			Vector<RiskPlayerModel> tclone = (Vector<RiskPlayerModel>) tournamentPlayers.clone();
			RiskGameModel.players =  tclone;
			RiskGameModel.territories = new Vector<RiskTerritoryModel>();
			tournamentGameList.add(new RiskGameModel(tournamentGameMapNameList.get(gameCount - 1)));
			gameCount++;
		}
		return tournamentGameList;
	}

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

	public void printTournamentResult() {
//		 final String FILE_NAME = "/tmp/MyFirstExcel.xlsx";
//		
//		 XSSFWorkbook workbook = new XSSFWorkbook();
//		 XSSFSheet sheet = workbook.createSheet("Java Books");
//		
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
//		 int rowCount = 0;
//		
//		 for (Object[] aBook : resultArray) {
//		 Row row = sheet.createRow(++rowCount);
//		
//		 int columnCount = 0;
//		
//		 for (Object field : aBook) {
//		 Cell cell = row.createCell(++columnCount);
//		 if (field instanceof String) {
//		 cell.setCellValue((String) field);
//		 } else if (field instanceof Integer) {
//		 cell.setCellValue((Integer) field);
//		 }
//		 }
//		
//		 }
//		
//		 try {
//		 FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
//		 workbook.write(outputStream);
//		 outputStream.close();
//		 } catch (FileNotFoundException e) {
//		 e.printStackTrace();
//		 } catch (IOException e) {
//		 e.printStackTrace();
//		 }

		System.out.println("Done");
	}

}
