package manufacturingPlant.models;

import java.util.ArrayList;

public class AssembledProduct {
	
	/** The serialNumber of this product */
	private int serialNumber = -1;
	/** The stationNumber where this product is on the AssemblyLine. -1 If this product is finished */
	private int stationNumber = 1;
	/** The kind of Product which this AssembledProduct resembles */
	private Product product;
	
	/** ArrayList with all Parts that are already assembled to this product */
	private ArrayList<Part> assembledParts = new ArrayList<Part>();
	/** ArrayList with all Parts yet to be assembled to this product, empty is this AssembledProduct is finished */
	private ArrayList<Part> missingParts = new ArrayList<Part>();
	
	public AssembledProduct(Product type) {
		this.product = type;
		missingParts.addAll(product.getParts());
	}
	
	/**
	 * Returns this products serialnumber
	 * 
	 * @ensure result >= 0 && this.isAssembled() || result == -1 && !this.isAssembled()
	 */
	public int getSerialNumber() {
		return serialNumber;
	}
	
	/**
	 * Sets the SerialNumber of this product if this is not already set
	 * 
	 * @require serialNumber >= 0
	 * @ensure this.getSerialNumber == serialNumber && this.isAssembled() 
	 */
	public void setSerialNumber(int serialNumber) {
		if(this.serialNumber == -1) {
			this.serialNumber = serialNumber;
			stationNumber = -1; // Product is finished, so not on the line anymore
		}
	}

	/**
	 * Get all parts that are already assembled
	 * 
	 * @ensure result != null
	 */
	public ArrayList<Part> getAssembledParts() {
		return assembledParts;
	}
	
	/**
	 * Assembles all parts in <code>part</code> to this product 
	 * 
	 * @require this.getMissingParts().contains(part) && part != null
	 * @ensure result == this.getAssembledParts().contains(part)
	 */
	public void assembleParts(ArrayList<Part> part) {
		assembledParts.addAll(part);
		missingParts.removeAll(part);
	}
	
	/**
	 * Moves this product 1 station further on the AssemblyLine
	 * 
	 * @ensure result >= 0 || this.isAssembled() && result == -1 
	 */
	public void moveStation() {
		stationNumber++;
	}
	
	/**
	 * Returns an arraylist containing all parts that still have to be assembled to this product 
	 * 
	 * @ensure result != null
	 */
	public ArrayList<Part> getMissingParts() {
		return missingParts;
	}
	
	/**
	 * Returns the product(-category) which this AssembledProduct belongs to 
	 * 
	 * @ensure result != null
	 */
	public Product getProduct() {
		return product;
	}
	
	/**
	 * Whether or not this product is finished assembling
	 * 
	 * @ensure result == this.getMissingParts().isEmpty()
	 */
	public boolean isAssembled() {
		return missingParts.isEmpty();
	}
}