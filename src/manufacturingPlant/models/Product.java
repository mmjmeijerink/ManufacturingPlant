package manufacturingPlant.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * De klasse Product stelt een Product voor. Product moet hier 
 * gezien worden als een soort blauwdruk van het product dat
 * uiteindelijk gemaakt wordt: AssembledProduct.
 * 
 * @author Herman Slatman & Mart Meijerink
 *
 */
public class Product extends Assembly {

	/**
	 * Een ArrayList die alle AssembledProducts van dit
	 * 'blauwdruk' Product er gemaakt zijn.
	 * @clientCardinality 1
	 * @directed true
	 * @link composition
	 * @supplierCardinality *
	 */
	private ArrayList<AssembledProduct> assemblies;
	
	/**
	 * Een Map waarin <Part, Integer> paren opgeslagen worden.
	 * Dit zijn de Parts waaruit het product bestaat. Per Part
	 * wordt aangegeven hoeveel er hiervan aanwezig zijn
	 */
	private Map<Part, Integer> parts = new HashMap<Part, Integer>();
	
	/**
	 * De Constructor van Product kan een naam geven aan een nieuw
	 * product, en geeft aan uit welke (en hoeveel) Parts het
	 * Product bestaat
	 * @param name de Name voor dit product
	 * @param parts de Map van <Part, Integer> paren die aangeeft
	 * 				waaruit dit Product bestaat
	 * @require name != "" && parts != null
	 * @ensure super.getName() == name && this.parts.equals(parts)
	 */
	public Product(String name, Map<Part, Integer> parts) {
		super(name);
		this.parts.putAll(parts);
	}
	
	/**
	 * Geeft alle AssembledProducts terug van dit Product
	 * @return this.assemblies
	 */
	public ArrayList<AssembledProduct> getAssemblies() {
		return assemblies;
	}
	
	/**
	 * Voegt een nieuw AssembledProduct toe van dit Product
	 * @param assembly het AssembledProduct dat geproduceerd werd
	 * @require assembly != null && assembly instanceof AssembledProduct
	 * @ensure this.getAssemblies().contains(assembly)
	 */
	public void newAssembledProduct(AssembledProduct assembly) {
			assemblies.add(assembly);
	}
	
	/**
	 * Geeft de Parts die benodigd zijn voor dit Product
	 * @return ArrayList<Part> met alle Parts (met goede aantal)
	 * @ensure result != null
	 */
	public ArrayList<Part> getParts() {
		ArrayList<Part> result = new ArrayList<Part>();
		for(Part part : parts.keySet()) {
			for(int i = 0; i < parts.get(part).intValue(); i++) {
				result.add(part);
			}
		}
		
		return result;
	}
	
	/**
	 * Geeft de mapping van parts die in dit Product zitten met als key het aantal per part
	 * 
	 * @ensure result != null
	 */
	public Map<Part, Integer> getPart() {
		return parts;
	}
}
