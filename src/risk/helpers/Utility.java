package risk.helpers;

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
			phaseInformation = "I am RiskStartupPhaseModel";
			break;
		case "RiskReinforcementPhaseModel":
			phaseInformation = "I am RiskReinforcementPhaseModel";
			break;
		case "RiskAttackPhaseModel":
			phaseInformation = "I am RiskAttackPhaseModel";
			break;
		case "RiskFortifyPhaseModel":
			phaseInformation = "I am RiskFortifyPhaseModel";
			break;
		default:
			phaseInformation = "I am no one";
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
}
