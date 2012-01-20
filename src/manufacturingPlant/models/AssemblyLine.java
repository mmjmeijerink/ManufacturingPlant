package manufacturingPlant.models;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * De klasse AssemblyLine stelt een assembly line voor in de fabriek
 * van Use Case Industries. Op een AssemblyLine kunnen AssembledProducten
 * gefabriceerd worden. Dit gebeurt door Robots die elk hun eigen PartBin hebben
 * waaruit ze Parts halen om te fabriceren. Dit doen ze in hun eigen 
 * fabricagestap.
 * 
 * @author Herman Slatman & Mart Meijerink
 *
 */
public class AssemblyLine extends Observable {

	/** Whether or not this assemblyline is busy with a product run */
	private boolean isIdle = true;
	private int amountMade;
	
	/**
	 * De Robots die aan deze AssemblyLine staan
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @directed true
	 * @supplierCardinality 0..*
	 */
	private ArrayList<Robot> robots = new ArrayList<Robot>();

	/**
	 * De ProductRun waaraan gewerkt wordt door de Robots
	 * aan deze AssemblyLine
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @directed true
	 * @supplierCardinality 0..1
	 */
	private ProductRun run = null;
	
	private ArrayList<AssembledProduct> productsUnderConstruction = new ArrayList<AssembledProduct>();
	
	/**
	 * Geeft de AssembledProducts waaraan gewerkt worden op deze AssemblyLine
	 * 
	 * @ensure result != null
	 */
	public ArrayList<AssembledProduct> getProductsUnderConstruction() {
		return productsUnderConstruction;
	}
	
	/**
	 * Voegt een nieuw AssembledProduct toe waaraan deze assembly line net is begonnen
	 * 
	 * @ensure getProductsUnderConstruction.contains(newProductUnderConstruction)
	 */
	public void addNewProductUnderConstruction(AssembledProduct newProductUnderConstruction) {
		productsUnderConstruction.add(newProductUnderConstruction);
	}
	
	/**
	 * Geeft de ProductRun waaraan gewerkt wordt op deze AssemblyLine
	 * 
	 * @ensure (result == null && this.isIdle()) || (result != null && !this.isIdle())
	 */
	public ProductRun getProductRun() {
		return run;
	}
	
	/**
	 * Geeft het aantal geproduceerde AssembledProducts terug
	 * @ensure result >= 0
	 */
	public int getAmountMade() {
		return amountMade;
	}
	
	/**
	 * Verhoogt het aantal geproduceerde AssemblyProducts, omdat
	 * er een AssemblyProduct afgerond is
	 * @ensure new.getAmountMade() == old.getAmountMade() + 1
	 */
	public void deliverFinishedProduct() {
		amountMade++;
	}
	
	/**
	 * Geeft aan of deze AssemblyLine op dit moment bezig is met
	 * een ProductRun
	 * @return result = this.getProductRun() == null
	 */
	public boolean isIdle() {
		return isIdle;
	}
	
	/**
	 * Rond de ProductRun op deze AssemblyLine af
	 * @ensure result = (this.isIdle() == true && this.getProductRun() == null)
	 */
	private void finishRun() {
		isIdle = true;
		productsUnderConstruction.clear();
		robots.clear();
		notifyObservers(run);
		this.run = null;
	}
	
	/**
	 * De methode nextStep() zorgt ervoor dat een AssembledProduct
	 * van de ProductRun een plaatsje opschuift op de assemblagelijn
	 * Er wordt tevens gekeken of de ProductRun voltooid is.
	 */
	public void nextStep() {
		for(int i = 0; i < robots.size(); i++) {
			if(robots.get(i).getProductUnderConstruction() != null && !robots.get(i).isLastRobotOfTheLine())
				robots.get(i).getProductUnderConstruction().moveStation();
		}
	}

	/**
	 * De methode startRun zorgt ervoor dat de ProductRun gestart wordt
	 * @param run de ProductRun die gestart moet worden
	 */
	public void startRun(ProductRun run) {
		isIdle = false;
		amountMade = 0;
		this.run = run;
		
		if(run != null) {
			//Maak de benodigde Robots en hun PartBins aan
			Map<Part, Integer> parts = run.getProduct().getPart();
			int stationNumber = 1;
			
			for(Part part : parts.keySet()) {
				Robot robot = new Robot(this, stationNumber);
				robots.add(robot);
				stationNumber++;
				
				ArrayList<Part> partsForBin = new ArrayList<Part>();
				for(int i = 0; i < parts.get(part); i++) {
					partsForBin.add(part);
				}
				
				robot.setPartBin(new PartBin(partsForBin));
			}
		}
		
		while(amountMade != run.getAmount()) {
			nextStep();
		}
		finishRun();
	}

	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
	}

	@Override
	public void notifyObservers(Object arg) {
		super.notifyObservers(arg);
	}	
}
