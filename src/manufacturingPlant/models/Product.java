import java.util.ArrayList;

public class Product extends Assembly {

	/**
	 * @clientCardinality 1
	 * @directed true
	 * @link composition
	 * @supplierCardinality *
	 */
	private ArrayList<Assembly> assemblies;
}
