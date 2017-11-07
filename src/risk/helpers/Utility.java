package risk.helpers;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import risk.model.interfaces.PhaseViewInterface;
import java.util.Date;
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
			phaseInformation = "This is the Start up phase which is about placing armies etc etc...";
			break;
		case "RiskReinforcementPhaseModel":
			phaseInformation = "This is the Reinforcement phase which is about reinforcing etc etc...";
			break;
		case "RiskAttackPhaseModel":
			phaseInformation = "This is the Attack phase which is about attacking etc etc...";
			break;
		case "RiskFortifyPhaseModel":
			phaseInformation = "This is the Fortifying phase which is about fortiying etc etc...";
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
			out.println(new Date().toString() + ":" + text);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
