package manufacturingPlant.models;


/**
 * De klasse ProductRun modelleert een Product Run van
 * Use Case Industries. Bij de Constructor kan een Product opgegeven
 * worden en een aantal. Dit is het aantal Producten dat er van 
 * een Product gemaakt gaan worden in een specifieke ProductRun.
 * 
 * @author Herman Slatman & Mart Meijerink
 *
 */
public class ProductRun {
	
	/**
	 * The product to be made in this run
	 * 
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @directed true
	 * @supplierCardinality 1..*
	 */
	private Product product;
	
	/** Amount of products left to be made in this run */
	private int amount;
	
	/**
	 * De Constructor van ProductRun vraagt om een Product en een
	 * aantal. Deze geven het Product aan dat geproduceerd gaat worden
	 * en hoeveel daarvan geproduceerd worden
	 * @require product != null
	 * @require amount > 0
	 */
	public ProductRun(Product product, int amount) {
		this.product = product;
		this.amount = amount;
	}
	
	/**
	 * The product to be made in this run
	 * @ensure result != null
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * The amount left to be made in this run
	 * @ensure result == amount > 0 || this.isFinished()
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * The amount of products which this run has to make more than the initial amount
	 * @param amount the amount to set
	 * @require (getAmount() + amount) > 0
	 * @ensure result == (old.getAmount() + amount) || 0
	 */
	public int increaseWithAmount(int amount) {
		if(this.amount + amount > 0) {
			this.amount += amount;
		} else {
			this.amount = 0;
		}
		
		return this.amount;
	}

	/**
	 * Whether or not this product run is finished
	 * @ensure result = getAmount() == 0
	 */
	public boolean isFinished() {
		return amount > 0;
	}
}
