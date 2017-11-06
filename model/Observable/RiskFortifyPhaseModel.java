package risk.model.Observable;

import java.util.Observable;

import risk.helpers.Utility;
import risk.model.RiskGameModel;
import risk.model.interfaces.PhaseViewInterface;

public class RiskFortifyPhaseModel extends Observable implements PhaseViewInterface {

	private String originTerritory;
	private String destinationTerritory;
	private String originTerritoryPreFortifyArmyCount;
	private String originTerritoryostFortifyArmyCount;
	private String destinationTerritoryPreFortifyArmyCount;
	private String destinationTerritoryPostFortifyArmyCount;
	private String objectType = "RiskFortifyPhaseModel";
	private RiskGameModel objCurrentRiskGameObject;
	private static RiskFortifyPhaseModel instance = new RiskFortifyPhaseModel();

	private RiskFortifyPhaseModel() {
	}

	public static RiskFortifyPhaseModel getInstance() {
		if (instance == null)
			instance = new RiskFortifyPhaseModel();
		return instance;
	}

	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return this.toString();
	}

	@Override
	public void isChanged() {
		// specify that my state was changed
		setChanged();
		// notify all attached Observers of a change
		notifyObservers(this);
	}

	@Override
	public String getPhaseInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getObjectType() {
		// TODO Auto-generated method stub
		return objectType;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return Utility.getPhaseTitle(this);
	}

	/**
	 * @return the originTerritory
	 */
	public String getOriginTerritory() {
		return originTerritory;
	}

	/**
	 * @param originTerritory
	 *            the originTerritory to set
	 */
	public void setOriginTerritory(String originTerritory) {
		this.originTerritory = originTerritory;
	}

	/**
	 * @return the destinationTerritory
	 */
	public String getDestinationTerritory() {
		return destinationTerritory;
	}

	/**
	 * @param destinationTerritory
	 *            the destinationTerritory to set
	 */
	public void setDestinationTerritory(String destinationTerritory) {
		this.destinationTerritory = destinationTerritory;
	}

	/**
	 * @return the originTerritoryPreFortifyArmyCount
	 */
	public String getOriginTerritoryPreFortifyArmyCount() {
		return originTerritoryPreFortifyArmyCount;
	}

	/**
	 * @param originTerritoryPreFortifyArmyCount
	 *            the originTerritoryPreFortifyArmyCount to set
	 */
	public void setOriginTerritoryPreFortifyArmyCount(String originTerritoryPreFortifyArmyCount) {
		this.originTerritoryPreFortifyArmyCount = originTerritoryPreFortifyArmyCount;
	}

	/**
	 * @return the originTerritoryostFortifyArmyCount
	 */
	public String getOriginTerritoryostFortifyArmyCount() {
		return originTerritoryostFortifyArmyCount;
	}

	/**
	 * @param originTerritoryostFortifyArmyCount
	 *            the originTerritoryostFortifyArmyCount to set
	 */
	public void setOriginTerritoryostFortifyArmyCount(String originTerritoryostFortifyArmyCount) {
		this.originTerritoryostFortifyArmyCount = originTerritoryostFortifyArmyCount;
	}

	/**
	 * @return the destinationTerritoryPreFortifyArmyCount
	 */
	public String getDestinationTerritoryPreFortifyArmyCount() {
		return destinationTerritoryPreFortifyArmyCount;
	}

	/**
	 * @param destinationTerritoryPreFortifyArmyCount
	 *            the destinationTerritoryPreFortifyArmyCount to set
	 */
	public void setDestinationTerritoryPreFortifyArmyCount(String destinationTerritoryPreFortifyArmyCount) {
		this.destinationTerritoryPreFortifyArmyCount = destinationTerritoryPreFortifyArmyCount;
	}

	/**
	 * @return the destinationTerritoryPostFortifyArmyCount
	 */
	public String getDestinationTerritoryPostFortifyArmyCount() {
		return destinationTerritoryPostFortifyArmyCount;
	}

	/**
	 * @param destinationTerritoryPostFortifyArmyCount
	 *            the destinationTerritoryPostFortifyArmyCount to set
	 */
	public void setDestinationTerritoryPostFortifyArmyCount(String destinationTerritoryPostFortifyArmyCount) {
		this.destinationTerritoryPostFortifyArmyCount = destinationTerritoryPostFortifyArmyCount;
	}

	@Override
	public void setCurrentRiskGameObject(RiskGameModel objCurrentRiskGameContext) {
		this.setObjCurrentRiskGameObject(objCurrentRiskGameContext);
	}

	/**
	 * @param objCurrentRiskGameObject
	 *            the objCurrentRiskGameObject to set
	 */
	public void setObjCurrentRiskGameObject(RiskGameModel objCurrentRiskGameObject) {
		this.objCurrentRiskGameObject = objCurrentRiskGameObject;
	}

	@Override
	public String toString() {
		StringBuilder sbContentBuilder = new StringBuilder();

		// if (this.objCurrentRiskGameObject.getState() ==
		// this.objCurrentRiskGameObject.FORTIFY) {
		sbContentBuilder.append("\n************" + getTitle() + "************\n");
		sbContentBuilder.append(this.getPhaseInformation() + "\n");
		sbContentBuilder.append("Statistics:\n\n");
		// }

		// if (this.objCurrentRiskGameObject.getState() ==
		// this.objCurrentRiskGameObject.FORTIFYING) {

		sbContentBuilder.append(""

				+ ((this.objCurrentRiskGameObject.aTerritory != null
						&& this.objCurrentRiskGameObject.dTerritory != null)
								? "Fortification from territory: " + objCurrentRiskGameObject.aTerritory.getName()
										+ " to " + objCurrentRiskGameObject.dTerritory.getName() + "\n"
								: ""));
		// }
		if (this.objCurrentRiskGameObject.getState() == this.objCurrentRiskGameObject.FORTIFY_PHASE
				&& ((this.objCurrentRiskGameObject.aTerritory != null
						&& this.objCurrentRiskGameObject.dTerritory != null))) {
			sbContentBuilder.append("So " + this.objCurrentRiskGameObject.defenseNum + " armies moved from "
					+ this.objCurrentRiskGameObject.aTerritory.getName() + " to "
					+ this.objCurrentRiskGameObject.dTerritory.getName() + ".\n");
		}
		return sbContentBuilder.toString();
	}
}
