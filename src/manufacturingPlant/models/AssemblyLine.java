package manufacturingPlant.models;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class AssemblyLine extends Observable {

	/** Whether or not this assemblyline is busy with a product run */
	private boolean isIdle = true;
	private int amountMade;
	
	/**
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @directed true
	 * @supplierCardinality 0..*
	 */
	private ArrayList<Robot> robots = new ArrayList<Robot>();

	/**
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @directed true
	 * @supplierCardinality 0..1
	 */
	private ProductRun run = null;
	
	public ProductRun getProductRun() {
		return run;
	}
	
	public int getAmountMade() {
		return amountMade;
	}
	
	public void deliverFinishedProduct() {
		amountMade++;
	}
	
	public boolean isIdle() {
		return isIdle;
	}
	
	private void finishRun() {
		isIdle = true;
		notifyObservers();
	}
	
	public void nextStep() {
		for(int i = 0; i < robots.size(); i++) {
			if(robots.get(i).getProductUnderConstruction() != null)
				robots.get(i).getProductUnderConstruction().moveStation();
		}
		
		if(amountMade == run.getAmount()) {
			finishRun();
		}
	}

	public void startRun(ProductRun run) {
		isIdle = false;
		amountMade = 0;
		return;
	}

	@Override
	public synchronized void addObserver(Observer o) {
		// TODO Auto-generated method stub
		super.addObserver(o);
	}

	@Override
	public void notifyObservers(Object arg) {
		// TODO Auto-generated method stub
		super.notifyObservers(arg);
	}	
}
