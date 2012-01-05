package manufacturingPlant.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order {

	/** The initial set of products and the amounts for this order */
	private final Map<Product, Integer> initialProducts;
	
	/** Whether or not this order is finished */
	private boolean finished = false;
	
	/** Buyer of this order */
	private String customer;
	
	/**
	 * The products (types) and the current amount still need to complete this order
	 * 
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @directed true
	 * @supplierCardinality 1..*
	 */
	private Map<Product, Integer> products = new HashMap<Product, Integer>();
	
	/**
	 * The real products that are going to be delivered 
	 */
	private Map<Product, ArrayList<AssembledProduct>> assemblies = new HashMap<Product, ArrayList<AssembledProduct>>();
	
	public Order(Map<Product, Integer> products, String customer) {
		this.initialProducts = products;
		this.products = products;
		this.customer = customer;
		
		for(Product product : products.keySet()) {
			assemblies.put(product, new ArrayList<AssembledProduct>());
		}
	}
	
	/**
	 * Returns the initial products with their initial amounts
	 * 
	 * @return the initialProducts
	 */
	public Map<Product, Integer> getInitialProducts() {
		return initialProducts;
	}
	
	/**
	 * Customer of this order
	 * 
	 * @ensure result != null
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * Returns the products and the amounts still needed to complete this order
	 * 
	 * @return the products
	 */
	public Map<Product, Integer> getProducts() {
		return products;
	}
	
	/**
	 * 
	 * @ensure 
	 */
	public boolean isFinished() {
		if(!finished) {
			finished = true;
			
			for(Product product : products.keySet()) {
				if(!products.get(product).equals(new Integer(0)))
					finished = false;
			}
		}
		
		return finished;
	}
	
	/**
	 * Adds a product to this order to complete this order
	 * 
	 * @require product != null && getInitialProducts().containsKey(product) && !this.isFinished() && getProducts().get(product) != 0
	 * @ensure old.getProducts().get(product) = new.getProducts().get(product) + 1
	 */
	public void addAssembledProduct(AssembledProduct assembly) {
		Product product = assembly.getProduct();
		products.put(product, new Integer(products.get(product) - 1));
		assemblies.get(product).add(assembly);
	}
}
