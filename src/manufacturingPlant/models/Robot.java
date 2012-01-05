package manufacturingPlant.models;

public class Robot {

	private final int stationNumber;
	private boolean isFirstRobotOfTheLine = false;
	private boolean isLastRobotOfTheLine = false;
	
	private AssembledProduct productUnderConstruction;
	private AssemblyLine line;
	
	/**
	 * @clientCardinality *
	 * @clientNavigability NAVIGABLE
	 * @directed true
	 * @supplierCardinality *
	 */
	private PartBin bin;
	
	public Robot(AssemblyLine line, int stationNumber) {
		this.line = line;
		this.stationNumber = stationNumber;
	}
	
	public void doStep() {
		if(isFirstRobotOfTheLine) {
			if(line.getProductRun().getAmount() < line.getAmountMade()) {
				productUnderConstruction = new AssembledProduct(line.getProductRun().getProduct());
			}
		} else if(isLastRobotOfTheLine) {
			int serialNumber = 0; //Er kunnen honderden manieren bedacht worden om aan een uniek serienummer te komen. Hier hebben we echter geen tijd voor.
			productUnderConstruction.setSerialNumber(serialNumber);
			line.deliverFinishedProduct();
		} else {
			//monteer
			productUnderConstruction.assembleParts(bin.getParts());
		}
	}
	
	public void setPartBin(PartBin bin) {
		this.bin = bin;
	}
	
	public AssembledProduct getProductUnderConstruction() {
		return productUnderConstruction;
	}
	
	public int getStationNumber() {
		return stationNumber;
	}
	
	public boolean isFirstRobotOfTheLine() { 
		return isFirstRobotOfTheLine;
	}
	
	public boolean isLastRobotOfTheLine() { 
		return isLastRobotOfTheLine;
	}
}
