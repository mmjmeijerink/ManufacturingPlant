package manufacturingPlant.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Product extends Assembly {

	/**
	 * @clientCardinality 1
	 * @directed true
	 * @link composition
	 * @supplierCardinality *
	 */
	private ArrayList<AssembledProduct> assemblies;
	
	private Map<Part, Integer> parts = new HashMap<Part, Integer>();
	
	public Product(String name, Map<Part, Integer> parts) {
		super(name);
		this.parts.putAll(parts);
	}
	
	public ArrayList<AssembledProduct> getAssemblies() {
		return assemblies;
	}
	
	public void newAssembledProduct(AssembledProduct assembly) {
			assemblies.add(assembly);
	}
	
	public ArrayList<Part> getParts() {
		ArrayList<Part> result = new ArrayList<Part>();
		for(Part part : parts.keySet()) {
			for(int i = 0; i < parts.get(part).intValue(); i++) {
				result.add(part);
			}
		}
		
		return result;
	}
}
