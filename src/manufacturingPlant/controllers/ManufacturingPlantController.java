package manufacturingPlant.controllers;

import manufacturingPlant.models.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class ManufacturingPlantController implements Observer {

	private Map<Product, Integer> products = new HashMap<Product, Integer>();

	//private Map<Product, Integer> queue = new HashMap<Product, Integer>();
	private ArrayList<ProductRun> queue = new ArrayList<ProductRun>();

	/**
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @directed true
	 * @supplierCardinality *
	 */
	private ArrayList<Order> orders = new ArrayList<Order>();

	/**
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @directed true
	 * @supplierCardinality 1..*
	 */
	private ArrayList<AssemblyLine> assemblyLines = new ArrayList<AssemblyLine>();

	private Map<Part, Integer> parts = new HashMap<Part, Integer>();

	public void addOrder(Map<Product, Integer> products) {
		return;
	}

	public void run() {
		for(int i = 0; i < assemblyLines.size() && !queue.isEmpty(); i++) {
			if(assemblyLines.get(i).isIdle()) {
				assemblyLines.get(i).startRun(queue.get(0));
				queue.remove(queue.remove(0));
			}
		}
	}

	public void addProductRun(ProductRun run) {
		if(queue.contains(run)) {
			for(int i = 0; i < queue.size(); i++) {
				if(queue.get(i).equals(run)) {
					queue.get(i).increaseWithAmount(run.getAmount());
				}
			}
		} else {
			queue.add(run);
		}
		
		run();
	}
	
	public void cancelOrder(Order order) {
		for(int i = 0; i < orders.size(); i++) {
			if(orders.get(i).equals(order)) {
				for(Product product : order.getProducts().keySet()) {
					if(products.containsKey(product)) {
						products.put(product, order.getProducts().get(product) + products.get(product));
					} else {
						products.put(product, order.getProducts().get(product));
					}
				}
				
				orders.remove(i);
			}
		}
	}

	public void update(Observable o, Object arg) {
		if(o instanceof AssemblyLine) {
			for(int i = 0; i < assemblyLines.size() && !queue.isEmpty(); i++) {
				if(assemblyLines.get(i).isIdle()) {
					assemblyLines.get(i).startRun(queue.get(0));
					queue.remove(queue.remove(0));
				}
			}
		}
	}
}
