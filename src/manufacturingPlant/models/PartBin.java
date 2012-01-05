package manufacturingPlant.models; 

import java.util.ArrayList;

public class PartBin {
	
	/**
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @directed true
	 * @supplierCardinality *
	 */
	private ArrayList<Part> parts;
	
	//private Robot robot;
	
	public PartBin(ArrayList<Part> parts) {
		//this.robot = robot;
		this.parts.addAll(parts);
	}
	
	public ArrayList<Part> getParts() {
		return parts;
	}
}
