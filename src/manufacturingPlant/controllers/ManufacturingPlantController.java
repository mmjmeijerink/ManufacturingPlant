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
			AssemblyLine line = new AssemblyLine();
			line.addObserver(this);
			assemblyLines.add(line);
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
		for(int i = 0; i < assemblyLines.size() && !queue.isEmpty(); i++) {
			if(assemblyLines.get(i).isIdle()) {
				// Log in de GUI
				view.log("\n---------------------------------------------------------------");
				view.log("Started new run on assembly line " + i + ".\nProduct: " + queue.get(0).getProduct() + "\nAmount: " + queue.get(0).getAmount());
				view.log("---------------------------------------------------------------");
				
				assemblyLines.get(i).startRun(queue.get(0));
				
				queue.remove(queue.remove(0));
				
			}
		}
	}

	/**
	 * Adds the ProductRun <code>run</code> to the queue
	 */
	public void addProductRun(ProductRun run) {
		// Log dit in de GUI
		view.log("\n---------------------------------------------------------------");
		view.log("Added new product run to the queue.\nProduct: " + run.getProduct() + "\nAmount: " + run.getAmount());
		view.log("---------------------------------------------------------------");
		
		queue.add(run);
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
			if(line.getProductRun() != null && line.getProductRun().getOrder().equals(order)) {
				cancelProductRun(line);
			}
		}
		
		//Place all assemblies which were already added to the order in stock
		for(Product product : order.getProducts().keySet()) {
			product.newAssembledProduct(order.getAssemblies().get(product));
			
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
			// Log in de GUI
			view.log("\n---------------------------------------------------------------");
			view.log("Product run for Order: " + ((ProductRun) arg).getOrder().toString() + " finished.\nProduct: " + ((ProductRun) arg).getProduct() + "\nAmount: " + ((ProductRun) arg).getAmount());
			view.log("---------------------------------------------------------------");
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
			if(view.getNewAmountValue() > 0){
				view.setProductsOnNewOrder(view.getNewProductValue(), view.getNewAmountValue());
				// Log in de GUI
				view.log("\n---------------------------------------------------------------");
				view.log("Added new product to the order.\nProduct: " + view.getNewProductValue() + "\nAmount: " + view.getNewAmountValue());
				view.log("---------------------------------------------------------------");
			} else {
				view.log("\n---------------------------------------------------------------");
				view.log("Use an appropiate number of products to add to the order!");
				view.log("---------------------------------------------------------------");
			}
			view.resetNewProduct();
		} else if(arg0.getActionCommand().equals(view.getAddOrderButton().getActionCommand())) {
			if(!view.getProductsOnNewOrder().isEmpty()) {
				if(view.getCustomer().isEmpty() || view.getCustomer().equals(" ")){
					view.log("\n---------------------------------------------------------------");
					view.log("Fill in a customer name for the order!");
					view.log("---------------------------------------------------------------");
				} else {
					// Log in de GUI
					view.log("\n---------------------------------------------------------------");
					view.log("Added new order.");
					view.log("---------------------------------------------------------------");
					addOrder(view.getProductsOnNewOrder(), view.getCustomer());
					view.resetNewOrder();
				}
			}
		} else if(arg0.getActionCommand().equals(view.getCancelOrderButton().getActionCommand())) {
			// Log in de GUI
			view.log("\n---------------------------------------------------------------");
			view.log("Removed order of " + view.getSelectedOrder() + ".");
			view.log("---------------------------------------------------------------");
			cancelOrder(view.getSelectedOrder());
		}
	}
}
