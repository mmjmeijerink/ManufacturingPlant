package manufacturingPlant.controllers;

import manufacturingPlant.models.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class ManufacturingPlantController implements Observer {

	private Map<Product, Integer> products = new HashMap<Product, Integer>();

	private Map<Product, Integer> queue = new HashMap<Product, Integer>();

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
		return;
	}

	public void addProductRun(Product product, int amount) {
		return;
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
		// TODO Auto-generated method stub

	}
}
