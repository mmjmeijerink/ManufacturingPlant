package manufacturingPlant.models;

import manufacturingPlant.models.Assembly;

/**
 * De klasse MadePart stelt een Part voor die door Use Case Industries
 * geproduceerd is in de fabriek. Deze klasse implementeert daarmee
 * tevens de klasse Assembly, omdat het MadePart in de fabriek 
 * geproduceerd wordt.
 * 
 * @author Herman Slatman & Mart Meijerink
 *
 */
public class MadePart extends Assembly implements Part {

	/**
	 * Constructor van MadePart waarin een naam meegeven kan worden
	 * @param name Naam voor dit MadePart
	 * @require name != ""
	 * @ensure super.getName() == name
	 */
	public MadePart(String name) {
		super(name);
	}
}
