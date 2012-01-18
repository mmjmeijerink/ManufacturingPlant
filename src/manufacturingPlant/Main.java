package manufacturingPlant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import manufacturingPlant.controllers.ManufacturingPlantController;
import manufacturingPlant.models.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ManufacturingPlantController controller = new ManufacturingPlantController();
		
		ArrayList<Part> parts = new ArrayList<Part>();
		parts.add(new BoughtPart("Moer")); // 0
		parts.add(new BoughtPart("Bout")); // 1
		parts.add(new BoughtPart("Staalplaat")); // 2
		parts.add(new BoughtPart("Balk")); // 3
		parts.add(new BoughtPart("Tandwiel")); // 4
		parts.add(new BoughtPart("Wieltjes")); // 5
		parts.add(new BoughtPart("Elektromotor")); // 6
		
		ArrayList<Product> products = new ArrayList<Product>();
		Map<Part, Integer> speelgoedautoParts = new HashMap<Part, Integer>();
		speelgoedautoParts.put(parts.get(0), 5);
		speelgoedautoParts.put(parts.get(1), 5);
		speelgoedautoParts.put(parts.get(2), 7);
		speelgoedautoParts.put(parts.get(3), 15);
		speelgoedautoParts.put(parts.get(4), 5);
		speelgoedautoParts.put(parts.get(5), 4);
		speelgoedautoParts.put(parts.get(6), 1);
		products.add(new Product("Speelgoedauto", speelgoedautoParts));
		
		Map<Part, Integer> speelgoedvliegtuigjeParts = new HashMap<Part, Integer>();
		speelgoedvliegtuigjeParts.put(parts.get(0), 50);
		speelgoedvliegtuigjeParts.put(parts.get(1), 50);
		speelgoedvliegtuigjeParts.put(parts.get(2), 30);
		speelgoedvliegtuigjeParts.put(parts.get(3), 100);
		speelgoedvliegtuigjeParts.put(parts.get(4), 50);
		speelgoedvliegtuigjeParts.put(parts.get(5), 3);
		speelgoedvliegtuigjeParts.put(parts.get(6), 3);
		products.add(new Product("Speelgoedvliegtuigje", speelgoedvliegtuigjeParts));
		
		controller.setProducts(products);
	}

}