package manufacturingPlant.models;

/**
 * De klasse BoughtPart stelt een Part voor dat gekocht is door
 * Use Case Industries van een andere fabrikant.
 * 
 * @author Herman Slatman & Mart Meijerink
 *
 */
public class BoughtPart extends Assembly implements Part {

	/**
	 * Constructor voor BoughtPart waarin een naam gegeven wordt
	 * @param name Naam van het BoughtPart
	 * @require name != ""
	 * @ensure super.getName == name
	 */
	public BoughtPart(String name) {
		super(name);

	}
}
