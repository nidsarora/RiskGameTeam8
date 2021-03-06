package risk.model.Observable;

import java.io.Serializable;
import java.util.Observable;

import risk.helpers.Utility;
import risk.model.RiskGameModel;
import risk.model.interfaces.PhaseViewInterface;

/**
 * The Class Risk Fortify Phase Model.
 */
public class RiskFortifyPhaseModel extends Observable implements PhaseViewInterface,Serializable {

	/** The origin territory. */
	private String originTerritory;
	
	/** The destination territory. */
	private String destinationTerritory;
	
	/** The origin territory pre fortify army count. */
	private String originTerritoryPreFortifyArmyCount;
	
	/** The origin territoryost fortify army count. */
	private String originTerritoryostFortifyArmyCount;
	
	/** The destination territory pre fortify army count. */
	private String destinationTerritoryPreFortifyArmyCount;
	
	/** The destination territory post fortify army count. */
	private String destinationTerritoryPostFortifyArmyCount;
	
	/** The object type. */
	private String objectType = "RiskFortifyPhaseModel";
	
	/** The obj current risk game object. */
	private RiskGameModel objCurrentRiskGameObject;
	
	/** The instance. */
	private static RiskFortifyPhaseModel instance = new RiskFortifyPhaseModel();

	/**
	 * Instantiates a new risk fortify phase model.
	 */
	private RiskFortifyPhaseModel() {
	}

	/**
	 * Gets the single instance of Risk Fortify Phase Model.
	 *
	 * @return single instance of Risk Fortify Phase Model
	 */
	public static RiskFortifyPhaseModel getInstance() {
		if (instance == null)
			instance = new RiskFortifyPhaseModel();
		return instance;
	}

	@Override
	public String getContent() {
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
		return null;
	}

	@Override
	public String getObjectType() {
		return objectType;
	}

	@Override
	public String getTitle() {
		return Utility.getPhaseTitle(this);
	}

	/**
	 * Gets the origin territory.
	 *
	 * @return the origin Territory
	 */
	public String getOriginTerritory() {
		return originTerritory;
	}

	/**
	 * Sets the origin territory.
	 *
	 * @param originTerritory the origin Territory to set
	 */
	public void setOriginTerritory(String originTerritory) {
		this.originTerritory = originTerritory;
	}

	/**
	 * Gets the destination territory.
	 *
	 * @return the destinationTerritory
	 */
	public String getDestinationTerritory() {
		return destinationTerritory;
	}

	/**
	 * Sets the destination territory.
	 *
	 * @param destinationTerritory the destinationTerritory to set
	 */
	public void setDestinationTerritory(String destinationTerritory) {
		this.destinationTerritory = destinationTerritory;
	}

	/**
	 * Gets the origin territory pre fortify army count.
	 *
	 * @return the originTerritoryPreFortifyArmyCount
	 */
	public String getOriginTerritoryPreFortifyArmyCount() {
		return originTerritoryPreFortifyArmyCount;
	}

	/**
	 * Sets the origin territory pre fortify army count.
	 *
	 * @param originTerritoryPreFortifyArmyCount the origin Territory Pre Fortify Army Count to set
	 */
	public void setOriginTerritoryPreFortifyArmyCount(String originTerritoryPreFortifyArmyCount) {
		this.originTerritoryPreFortifyArmyCount = originTerritoryPreFortifyArmyCount;
	}

	/**
	 * Gets the origin territoryost fortify army count.
	 *
	 * @return the originTerritoryostFortifyArmyCount
	 */
	public String getOriginTerritoryostFortifyArmyCount() {
		return originTerritoryostFortifyArmyCount;
	}

	/**
	 * Sets the origin territoryost fortify army count.
	 *
	 * @param originTerritoryostFortifyArmyCount the originTerritoryostFortifyArmyCount to set
	 */
	public void setOriginTerritoryostFortifyArmyCount(String originTerritoryostFortifyArmyCount) {
		this.originTerritoryostFortifyArmyCount = originTerritoryostFortifyArmyCount;
	}

	/**
	 * Gets the destination territory pre fortify army count.
	 *
	 * @return the destinationTerritoryPreFortifyArmyCount
	 */
	public String getDestinationTerritoryPreFortifyArmyCount() {
		return destinationTerritoryPreFortifyArmyCount;
	}

	/**
	 * Sets the destination territory pre fortify army count.
	 *
	 * @param destinationTerritoryPreFortifyArmyCount the destinationTerritoryPreFortifyArmyCount to set
	 */
	public void setDestinationTerritoryPreFortifyArmyCount(String destinationTerritoryPreFortifyArmyCount) {
		this.destinationTerritoryPreFortifyArmyCount = destinationTerritoryPreFortifyArmyCount;
	}

	/**
	 * Gets the destination territory post fortify army count.
	 *
	 * @return the destinationTerritoryPostFortifyArmyCount
	 */
	public String getDestinationTerritoryPostFortifyArmyCount() {
		return destinationTerritoryPostFortifyArmyCount;
	}

	/**
	 * Sets the destination territory post fortify army count.
	 *
	 * @param destinationTerritoryPostFortifyArmyCount the destination Territory Post Fortify Army Count to set
	 */
	public void setDestinationTerritoryPostFortifyArmyCount(String destinationTerritoryPostFortifyArmyCount) {
		this.destinationTerritoryPostFortifyArmyCount = destinationTerritoryPostFortifyArmyCount;
	}

	@Override
	public void setCurrentRiskGameObject(RiskGameModel objCurrentRiskGameContext) {
		this.setObjCurrentRiskGameObject(objCurrentRiskGameContext);
	}

	/**
	 * Sets the obj current risk game object.
	 *
	 * @param objCurrentRiskGameObject the obj Current Risk Game Object to set
	 */
	public void setObjCurrentRiskGameObject(RiskGameModel objCurrentRiskGameObject) {
		this.objCurrentRiskGameObject = objCurrentRiskGameObject;
	}

	@Override
	public String toString() {
		StringBuilder sbContentBuilder = new StringBuilder();

		sbContentBuilder.append("\n************" + getTitle() + "************\n");
		sbContentBuilder.append(this.getPhaseInformation() + "\n");
		sbContentBuilder.append("Statistics:\n\n");
		// }


		sbContentBuilder.append(""

				+ ((this.objCurrentRiskGameObject.aTerritory != null
						&& this.objCurrentRiskGameObject.defenseTerritory != null)
								? "Fortification from territory: " + objCurrentRiskGameObject.aTerritory.getName()
										+ " to " + objCurrentRiskGameObject.defenseTerritory.getName() + "\n"
								: ""));
		// }
		if (this.objCurrentRiskGameObject.getState() == this.objCurrentRiskGameObject.FORTIFY_PHASE
				&& ((this.objCurrentRiskGameObject.aTerritory != null
						&& this.objCurrentRiskGameObject.defenseTerritory != null))) {
			sbContentBuilder.append("So " + this.objCurrentRiskGameObject.defenseNum + " armies moved from "
					+ this.objCurrentRiskGameObject.aTerritory.getName() + " to "
					+ this.objCurrentRiskGameObject.defenseTerritory.getName() + ".\n");
		}
		return sbContentBuilder.toString();
	}
}
