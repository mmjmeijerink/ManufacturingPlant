package manufacturingPlant.models; 

import java.util.ArrayList;


/**
 * De klasse PartBin bevat Parts die door een Robot gebruikt
 * kunnen worden om een AssembledProduct in elkaar te zetten
 * 
 * @author Herman Slatman & Mart Meijerink
 *
 */
public class PartBin {
	
	/**
	 * ArrayList<Part> die de Parts opslaat die in deze PartBin zitten
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @directed true
	 * @supplierCardinality *
	 */
	private ArrayList<Part> parts;
	
	//private Robot robot;
	
	/**
	 * De Constructor van de PartBin voegt Parts toe
	 * @param parts ArrayList<Part> die de Parts voor deze PartBin bevat
	 * @require parts != null
	 * @ensure this.parts.containsAll(parts)
	 */
	public PartBin(ArrayList<Part> parts) {
		//this.robot = robot;
		this.parts.addAll(parts);
	}
	
	/**
	 * Geeft de Parts in deze PartBin terug
	 * @return this.parts
	 * @ensure this.parts != null
	 */
	public ArrayList<Part> getParts() {
		return parts;
	}
}
