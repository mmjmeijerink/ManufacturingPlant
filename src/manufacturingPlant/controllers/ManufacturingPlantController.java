package manufacturingPlant.controllers;

import manufacturingPlant.models.*;
import manufacturingPlant.views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class ManufacturingPlantController implements Observer, ActionListener {

	/** ArrayList with all types of products the plant currently can produce */
	private ArrayList<Product> productTypes = new ArrayList<Product>(); 
	/** Map with <Product, ArrayList<AssembledProduct>> mappings modeling which products there are and how many there are in stock */
	private Map<Product, ArrayList<AssembledProduct>> products = new HashMap<Product, ArrayList<AssembledProduct>>();
	/** ArrayList with all ProductRuns that are waiting for an empty AssemblyLine */
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

	/** Map with <Part, ArrayList<Part>> mappings modeling which parts there are and how many there are in stock */
	private Map<Part, ArrayList<Part>> parts = new HashMap<Part, ArrayList<Part>>();

	/** The GUI */
	private MainView view;
	
	/**
	 * Constructor
	 * Starts the factory and makes ten AssemblyLines
	 */
	public ManufacturingPlantController() {
		view = new MainView(this);
		view.log("Manufacturing plant started");
		
		for(int i = 0; i < 10; i++) {
			assemblyLines.add(new AssemblyLine());
		}
	}
	
	/**
	 * Adds an Order and puts all products still in stock which can be used in the order
	 * Then it makes new ProductRuns to match the number of product needed for this order
	 */
	public void addOrder(Map<Product, Integer> products, String customer) {
		Order order = new Order(products, customer);
		orders.add(order);
		
		for(Product product : order.getInitialProducts().keySet()) {
			if(this.products.containsKey(product)) {
				for(AssembledProduct assembly : product.getAssemblies()) {
					if(order.getProducts().get(product) != 0 && !this.products.get(product).isEmpty()) {
						order.addAssembledProduct(assembly);
						this.products.get(product).remove(assembly);
					}
				}
			}
			
			addProductRun(new ProductRun(product, order.getProducts().get(product), order));
		}
		
		view.setOrdersList(orders);
	}

	/**
	 * If there is an idle AssemblyLine and the queue with ProductRuns isn't empty, an new run will be started
	 */
	public void run() {
		view.log("<Looking for a free assembly line>");
		for(int i = 0; i < assemblyLines.size() && !queue.isEmpty(); i++) {
			if(assemblyLines.get(i).isIdle()) {
				assemblyLines.get(i).startRun(queue.get(0));
				view.log("\nStarted new run on assembly line " + i + ".\nProduct: " + queue.get(0).getProduct() + "\nAmount: " + queue.get(0).getAmount());
				queue.remove(queue.remove(0));
			}
		}
	}

	/**
	 * Adds the ProductRun <code>run</code> to the queue
	 */
	public void addProductRun(ProductRun run) {
		queue.add(run);
		
		// Log dit in de GUI
		view.log("\nAdded new product run to the queue.\nProduct: " + run.getProduct() + "\nAmount: " + run.getAmount());
		
		run();
	}
	
	/**
	 * Cancels the ProductRun on the AssemblyLine <code>line</code>
	 * It does this by setting the amount that still has to be made to 0
	 * All products that are still on the line, will be finished and added to the associated order
	 */
	public void cancelProductRun(AssemblyLine line) {
		line.getProductRun().increaseWithAmount(line.getProductRun().getAmount() - line.getAmountMade());
	}
	
	/**
	 * Cancels the Order <code>order</code> and all associated ProductRuns 
	 */
	public void cancelOrder(Order order) {
		//Stop all ProductRuns waiting to be processed that are associated with the order to cancel
		for(ProductRun run : queue) {
			if(run.getOrder().equals(order)) {
				queue.remove(run);
			}
		}
		
		//Let all ProductRuns, associated with the order to cancel, currently being processed stop immediately 
		for(AssemblyLine line : assemblyLines) {
			if(line.getProductRun().getOrder().equals(order)) { 
				cancelProductRun(line);
			}
		}
		
		//Place all products which were already added to the order in stock
		for(Product product : order.getProducts().keySet()) {
			if(products.containsKey(product)) {
				products.get(product).addAll(order.getAssemblies().get(product));
			} else {
				products.put(product, order.getAssemblies().get(product));
			}
		}
		
		orders.remove(order);
		view.removeOrder(order);
	}

	public void update(Observable o, Object arg) {
		if(o instanceof AssemblyLine) {
			run();
		}
	}

	//Voor het toevoegen van Producten tijdens het testen
	public void setProducts(ArrayList<Product> products) {
		this.productTypes = products;
		view.setProducts(products);
	}

	@Override
	// Handelt de acties van de GUI af
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals(view.getAddProductButton().getActionCommand())) {
			view.setProductsOnNewOrder(view.getNewProductValue(), view.getNewAmountValue());
			view.log("\nAdded new product to the order.\nProduct: " + view.getNewProductValue() + "\nAmount: " + view.getNewAmountValue());
			view.resetNewProduct();
		} else if(arg0.getActionCommand().equals(view.getAddOrderButton().getActionCommand())) {
			if(!view.getProductsOnNewOrder().isEmpty() && (!view.getCustomer().isEmpty() || !view.getCustomer().equals(" "))) {
				addOrder(view.getProductsOnNewOrder(), view.getCustomer());
				view.log("\nAdded new order.");
				view.resetNewOrder();
			}
		} else if(arg0.getActionCommand().equals(view.getCancelOrderButton().getActionCommand())) {
			view.log("\nRemoved order of " + view.getSelectedOrder() + ".");
			cancelOrder(view.getSelectedOrder());
		}
	}
}
