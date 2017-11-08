package risk.helpers;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import risk.model.interfaces.PhaseViewInterface;

/**
 * This class is used to get the path of the resources used in the project.
 */
public final class Utility {

	public static String getImagePath(String imageNameWithExtention) {
		return "/risk/resources/images/" + imageNameWithExtention;
	}

	public static String getMapPath(String mapNameWithExtension) {
		return "/risk/resources/map/" + mapNameWithExtension;
	}

	public static String getMapPathforFile(String mapNameWithExtension) {
		return "src/risk/resources/map/" + mapNameWithExtension;
	}

	public static String getPathforFile(String fileNameWithExtension) {
		return "src/risk/resources/" + fileNameWithExtension;
	}

	public static String getPhaseInformtion(PhaseViewInterface IPhaseViewInterface) {
		String objectType = (IPhaseViewInterface).getObjectType();
		String phaseInformation;
		switch (objectType) {
		case "RiskStartupPhaseModel":
			phaseInformation = "This is the Start up phase. Start the game by choosing the map,number of players and distribution of armies depending upon the number of players accordingly..";
			break;
		case "RiskReinforcementPhaseModel":
			phaseInformation = "Getting additional armies, that you may allocate among your territories.\r\n" + 
					"No. of armies you get :\r\n" + 
					"       -1 army per 3 territories, rounded down, minimum of 3.\r\n" + 
					"       -For complete continent\r\n" + 
					"       -set of Risk Cards cashed in.";
			break;
		case "RiskAttackPhaseModel":
			phaseInformation = "An attack is actually one or more battles which are fought with dice. The object of an attack is to capture a territory.";
			break;
		case "RiskFortifyPhaseModel":
			phaseInformation = "This is the Fortifying phase where we can move any number of armies from one of a pair of neighbouring territories to other.";
			break;
		default:
			phaseInformation = "Who am I?";
			break;
		}
		
		return phaseInformation;
	}
	
	public static String getPhaseTitle(PhaseViewInterface IPhaseViewInterface)
	{
		String objectType = (IPhaseViewInterface).getObjectType();
		String phaseTitle;
		switch (objectType) {
		case "RiskStartupPhaseModel":
			phaseTitle = "*********** Start Up Phase *************";
			break;
		case "RiskAttackStartupEndPhaseModel":
			phaseTitle = "********** Start Up Phase **************";
			break;
		case "RiskReinforcementPhaseModel":
			phaseTitle = "*********** Reinforcement Phase *************";
			break;
		case "RiskAttackPhaseModel":
			phaseTitle = "*********** Atack Phase *************";
			break;
		case "RiskFortifyPhaseModel":
			phaseTitle = "*********** Fortify Phase *************";
			break;
		default:
			phaseTitle = "I am no one";
			break;
		}
		
		return phaseTitle;
	}

	public static void writeLog(String text) {
		try (FileWriter fw = new FileWriter("log\\log.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(text);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
