package manufacturingPlant.models;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class AssemblyLine extends Observable {

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

	public void nextStep() {
		return;
	}

	public void startRun(Product product, int amount) {
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
