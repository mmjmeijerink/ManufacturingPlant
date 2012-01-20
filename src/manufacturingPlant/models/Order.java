package manufacturingPlant.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * De klasse Order stelt een order voor binnen het systeem
 * Hieraan wordt een blauwdruk van Product toegevoegd tijdens het aanmaken
 * van een instantie. Daarna kan er begonnen worden met maken van de
 * AssembledProducts, om deze Order te voltooien.
 * 
 * @author Herman Slatman & Mart Meijerink
 *
 */
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
	
	/**
	 * De Constructor van Order vraag om een Map van <Product, Integer> paren.
	 * Dit zijn de Producten die geleverd moeten worden met het bijbehorende aantal.
	 * Deze worden geleverd aan de customer die ingegeven wordt.
	 * @param products Map<Product, Integer> die de te leveren Producten voorstelt
	 * @param customer de klant waarvoor deze Order gemaakt wordt
	 * @require products != null && customer != ""
	 * @ensure this.initialProducts == products && this.products == products
	 * 		   this.customer == customer
	 */
	public Order(Map<Product, Integer> products, String customer) {
		this.initialProducts = products;
		this.products = products;
		this.customer = customer;
		if(products != null){
			for(Product product : products.keySet()) {
				assemblies.put(product, new ArrayList<AssembledProduct>());
			}
		}
	}
	
	/**
	 * Returns the initial products with their initial amounts
	 * @return this.initialProducts
	 */
	public Map<Product, Integer> getInitialProducts() {
		return initialProducts;
	}
	
	/**
	 * Customer of this order
	 * @ensure result != null
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * Returns the products and the amounts still needed to complete this order
	 * @return the products
	 */
	public Map<Product, Integer> getProducts() {
		return products;
	}
	
	/**
	 * Returns the assemblies associated with this order
	 * 
	 * @ensure result != null
	 */
	public Map<Product, ArrayList<AssembledProduct>> getAssemblies() {
		return assemblies;
	}
	
	/**
	 * Geeft aan of deze Order klaar is voor levering
	 * @ensure result == this.finished
	 */
	public boolean isFinished() {
		if(!finished) {
			finished = true;
			if(products != null) {
				for(Product product : products.keySet()) {
					if(!products.get(product).equals(new Integer(0)))
						finished = false;
				}
			}
		}
		
		return finished;
	}
	
	/**
	 * Adds an AssembledProduct to this order to complete this order
	 * @require assembly != null && getInitialProducts().containsKey(assembly) && !this.isFinished() && getProducts().get(assembly) != 0
	 * @ensure old.getProducts().get(assembly) = new.getProducts().get(assembly) + 1
	 */
	public void addAssembledProduct(AssembledProduct assembly) {
		Product product = assembly.getProduct();
		product.removeAssembly(assembly);
		products.put(product, new Integer(products.get(product) - 1));
		assemblies.get(product).add(assembly);
	}
	
	/**
	 * String representation of this instance
	 * 
	 *  @ensure result != null
	 */
	public String toString() {
		return customer;
	}
}
