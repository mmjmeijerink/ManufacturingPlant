package manufacturingPlant.models;

public class ProductRun {
	
	/**
	 * The product to be made in this run
	 * 
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @directed true
	 * @supplierCardinality 1..*
	 */
	private Assembly product;
	
	/** Amount of products left to be made in this run */
	int amount;
	
	/**
	 * @require product != null
	 * @require amount > 0
	 */
	public ProductRun(Product product, int amount) {
		this.product = product;
		this.amount = amount;
	}
	
	/**
	 * The product to be made in this run
	 * 
	 * @ensure result != null
	 */
	public Assembly getProduct() {
		return product;
	}

	/**
	 * The amount left to be made in this run
	 * 
	 * @ensure result == amount > 0 || this.isFinished()
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * The amount of products which this run has to make more than the initial amount
	 * 
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
