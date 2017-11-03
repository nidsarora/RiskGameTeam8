package risk.model.interfaces;

import risk.model.RiskGameModel;

public interface PhaseViewInterface {	
	public void isChanged();
	public String getTitle();
	public String getContent();
	public String getPhaseInformation();
	public String getObjectType();
	public void setCurrentRiskGameObject(RiskGameModel objCurrentRiskGameContext);
}
