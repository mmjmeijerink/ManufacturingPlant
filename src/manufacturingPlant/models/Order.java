package manufacturingPlant.models;

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
	 * The products and the current amount still need to complete this order
	 * 
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @directed true
	 * @supplierCardinality 1..*
	 */
	private Map<Product, Integer> products = new HashMap<Product, Integer>();
	
	public Order(Map<Product, Integer> products, String customer) {
		this.initialProducts = products;
		this.products = products;
		this.customer = customer;
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
	 * @require product != null && initialProducts.containsKey(product) && !this.isFinished()
	 */
	public void addProduct(Product product) {
		products.put(product, new Integer(products.get(product) - 1));
	}
}
