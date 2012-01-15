package manufacturingPlant.models;

import java.util.ArrayList;
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
	
	/**
	 * Geeft de ProductRun waaraan gewerkt wordt op deze AssemblyLine
	 * @return this.run
	 * @ensure result == this.run
	 */
	public ProductRun getProductRun() {
		return run;
	}
	
	/**
	 * Geeft het aantal geproduceerde AssembledProducts terug
	 * @return this.amountMade
	 */
	public int getAmountMade() {
		return amountMade;
	}
	
	/**
	 * Verhoogt het aantal geproduceerde AssemblyProducts, omdat
	 * er een AssemblyProduct afgerond is
	 * @ensure new.amountMade == old.amountMade + 1
	 */
	public void deliverFinishedProduct() {
		amountMade++;
	}
	
	/**
	 * Geeft aan of deze AssemblyLine op dit moment bezig is met
	 * een ProductRun
	 * @return this.isIdle = this.run == null
	 */
	public boolean isIdle() {
		return isIdle;
	}
	
	/**
	 * Rond de ProductRun op deze AssemblyLine af
	 * @ensure this.isIdle == true
	 */
	private void finishRun() {
		isIdle = true;
		//Toegevoegd door Herman op 15-01-2012:
		//Aan het einde run op null zetten?
		this.run = null;
		notifyObservers();
	}
	
	/**
	 * De methode nextStep() zorgt ervoor dat een AssembledProduct
	 * van de ProductRun een plaatsje opschuift op de assemblagelijn
	 * Er wordt tevens gekeken of de ProductRun voltooid is.
	 */
	public void nextStep() {
		for(int i = 0; i < robots.size(); i++) {
			if(robots.get(i).getProductUnderConstruction() != null)
				robots.get(i).getProductUnderConstruction().moveStation();
		}
		
		if(amountMade == run.getAmount()) {
			finishRun();
		}
	}

	/**
	 * De methode startRun zorgt ervoor dat de ProductRun gestart wordt
	 * @param run de ProductRun die gestart moet worden
	 */
	public void startRun(ProductRun run) {
		isIdle = false;
		amountMade = 0;
		//Toegevoegd door Herman op 15-01-2012:
		//de run moet wel gezet worden?
		this.run = run;
		return;
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
