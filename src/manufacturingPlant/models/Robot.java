package manufacturingPlant.models;


/**
 * De klasse Robot modelleert een Robot.
 * Deze Robot kan op een AssemblyLine geplaatst worden tijdens
 * het aanmaken van een instantie. Hieraan is ook de positie op
 * de AssemblyLine verbonden. De Robot kan een stap in het
 * fabricageproces uitvoeren.
 * 
 * @author Herman Slatman & Mart Meijerink
 *
 */
public class Robot {

	/** De positie op de AssemblyLine */
	private final int stationNumber;
	/** Geeft aan of deze robot de eerste is op de lijn */
	private boolean isFirstRobotOfTheLine = false;
	/** Geeft aan of deze robot de laatste op de lijn is */
	private boolean isLastRobotOfTheLine = false;
	
	/** Het product dat op dit moment wordt geassembleerd op de AssemblyLine waar deze Robot aan staat */
	private AssembledProduct productUnderConstruction;
	/** De AssemblyLine waarop deze Robot staat */
	private AssemblyLine line;
	
	/**
	 * @clientCardinality *
	 * @clientNavigability NAVIGABLE
	 * @directed true
	 * @supplierCardinality *
	 */
	private PartBin bin; //De PartBin waaruit deze Robot Parts mag halen
	
	/**
	 * De Constructor voor Robot
	 * @param line de AssemblyLine waarop deze Robot moet staan 
	 * @param stationNumber de positie op de AssemblyLine waarop deze robot moet staan
	 * @require line != null && 0 >= stationNumber
	 * @ensure this.line == line && this.stationNumber == stationNumber
	 */
	public Robot(AssemblyLine line, int stationNumber) {
		this.line = line;
		this.stationNumber = stationNumber;
		
		if(stationNumber == 1) {
			isFirstRobotOfTheLine = true;
		}
		if(line != null){
			if(line.getProductRun() != null){
				if(stationNumber == this.line.getProductRun().getProduct().getPart().size()) {
					isLastRobotOfTheLine = true;
				}
			}
		}
	}
	
	/**
	 * De methode doStep() checkt eerst of deze Robot de eerste of
	 * de laatste Robot van de AssemblyLine is, en voert hierop een
	 * specifieke actie uit. In het geval van de eerste Robot op de 
	 * lijn is dit het aanmaken van een nieuw AssembledProduct dat
	 * gemaakt gaat worden. In het geval van de laatste Robot op de 
	 * lijn is dit het setten van het serialNumber op het product.
	 * Als dit niet het geval is wordt er een fabricagestap 
	 * uitgevoerd
	 */
	public void doStep() {
		if(isFirstRobotOfTheLine) {
			if(line.getProductRun().getAmount() < line.getAmountMade()) {
				productUnderConstruction = new AssembledProduct(line.getProductRun().getProduct());
			}
		} else if(isLastRobotOfTheLine) {
			int serialNumber = makeSerialNumber(); //Er kunnen vele manieren bedacht worden om aan een uniek serienummer te komen. Hier hebben we echter geen tijd voor.
			productUnderConstruction.setSerialNumber(serialNumber);
			line.deliverFinishedProduct();
		} else {
			//monteer
			productUnderConstruction.assembleParts(bin.getParts());
		}
	}
	
	private int makeSerialNumber() {
		String res = "1";
		for(int i = 0; i < 15; i++){
			int k = (int)(Math.random() * 10.0);
			res += Integer.toString(k);
		}
		return Integer.parseInt(res);
	}

	/**
	 * De methode setPartBin wijst een PartBin toe aan deze Robot
	 * @param bin de PartBin waaruit deze Robot Parts mag halen
	 * @require bin != null
	 * @ensure this.bin == bin
	 */
	public void setPartBin(PartBin bin) {
		this.bin = bin;
	}
	
	/**
	 * De methode getProductUnderConstruction() geeft het 
	 * AssembledProduct terug waaraan door deze Robot wordt
	 * gewerkt
	 * @return this.productUnderConstruction
	 */
	public AssembledProduct getProductUnderConstruction() {
		return productUnderConstruction;
	}
	
	/**
	 * Geeft het stationsnummer terug waarop deze Robot staat
	 * @return this.stationNumber
	 */
	public int getStationNumber() {
		return stationNumber;
	}
	
	/**
	 * Geeft aan of deze Robot de eerste op de AssemblyLine is
	 * @return this.isFirstRobotOfTheLine
	 */
	public boolean isFirstRobotOfTheLine() { 
		return isFirstRobotOfTheLine;
	}
	
	/**
	 * Geeft aan of deze Robot de laatste op de AssemblyLine is
	 * @return this.isLastRobotOfTheLine
	 * @ensure result == isLastRobotOfTheLine
	 */
	public boolean isLastRobotOfTheLine() { 
		return isLastRobotOfTheLine;
	}
}
