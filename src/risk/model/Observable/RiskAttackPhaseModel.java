package risk.model.Observable;

import java.util.Observable;

import risk.helpers.Utility;
import risk.model.RiskGameModel;
import risk.model.interfaces.PhaseViewInterface;

public class RiskAttackPhaseModel extends Observable implements PhaseViewInterface {

	private String objectType = "RiskAttackPhaseModel";
	private RiskGameModel objCurrentRiskGameObject;
	private static RiskAttackPhaseModel instance = new RiskAttackPhaseModel();

	private RiskAttackPhaseModel() {
	}

	public static RiskAttackPhaseModel getInstance() {
		if (instance == null)
			instance = new RiskAttackPhaseModel();
		return instance;
	}

	public String getPhaseInformation() {
		return Utility.getPhaseInformtion(this);
	}

	@Override
	public String getObjectType() {
		// TODO Auto-generated method stub
		return objectType;
	}

	@Override
	public String getTitle() {
		return Utility.getPhaseTitle(this);
	}

	@Override
	public void isChanged() {
		// specify that my state was changed
		setChanged();
		// notify all attached Observers of a change
		notifyObservers(this);
	}

	@Override
	public String getContent() {
		return this.toString();
	}

	@Override
	public String toString() {
		StringBuilder sbContentBuilder = new StringBuilder();
		sbContentBuilder.append("************" + getTitle() + "************\n");
		sbContentBuilder.append(this.getPhaseInformation() + "\n");
		sbContentBuilder.append("Player: " + objCurrentRiskGameObject.curPlayer.getName() + "\n");
		sbContentBuilder.append("Statistics:\n\n");
		
		sbContentBuilder.append("Territory " + 
		
				((this.objCurrentRiskGameObject.aTerritory !=null) ? this.objCurrentRiskGameObject.aTerritory.getName(): "") 
		
		+ " attacks " + 
		
				((this.objCurrentRiskGameObject.dTerritory !=null) ? this.objCurrentRiskGameObject.dTerritory.getName(): "") 
				
				);
		
		return sbContentBuilder.toString();
	}

	@Override
	public void setCurrentRiskGameObject(RiskGameModel objCurrentRiskGameContext) {
		this.setObjCurrentRiskGameObject(objCurrentRiskGameContext);
	}

	/**
	 * @param objCurrentRiskGameObject the objCurrentRiskGameObject to set
	 */
	public void setObjCurrentRiskGameObject(RiskGameModel objCurrentRiskGameObject) {
		this.objCurrentRiskGameObject = objCurrentRiskGameObject;
	}
}
