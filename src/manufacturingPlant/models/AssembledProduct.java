package manufacturingPlant.models;

import java.util.ArrayList;

public class AssembledProduct {
	
	private int serialNumber;
	private int stationNumber = 0;
	private Product product;
	
	private ArrayList<Part> assembledParts = new ArrayList<Part>();
	private ArrayList<Part> missingParts = new ArrayList<Part>();
	
	public AssembledProduct(Product type) {
		this.product = type;
		missingParts.addAll(product.getParts());
	}
	
	public int getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public ArrayList<Part> getAssembledParts() {
		return assembledParts;
	}
	
	/**
	 * 
	 * @require getMissingParts().contains(part)
	 * @ensure getAssembledParts().contains(part)
	 */
	public void assembleParts(ArrayList<Part> part) {
		assembledParts.addAll(part);
		missingParts.removeAll(part);
	}
	
	public void moveStation() {
		stationNumber++;
	}
	
	public ArrayList<Part> getMissingParts() {
		return missingParts;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public boolean isAssembled() {
		return missingParts.isEmpty();
	}
}