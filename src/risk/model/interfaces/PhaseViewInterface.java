package risk.model.interfaces;

import risk.model.RiskGameModel;

/**
 * The Interface PhaseViewInterface.
 */
public interface PhaseViewInterface {	
	
	/**
	 * Checks if phase is changed.
	 */
	public void isChanged();
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle();
	
	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent();
	
	/**
	 * Gets the phase information.
	 *
	 * @return the phase information
	 */
	public String getPhaseInformation();
	
	/**
	 * Gets the object type.
	 *
	 * @return the object type
	 */
	public String getObjectType();
	
	/**
	 * Sets the current risk game object.
	 *
	 * @param objCurrentRiskGameContext the new current risk game object
	 */
	public void setCurrentRiskGameObject(RiskGameModel objCurrentRiskGameContext);
}
