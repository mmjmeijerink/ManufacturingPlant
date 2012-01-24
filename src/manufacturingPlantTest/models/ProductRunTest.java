package manufacturingPlantTest.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import manufacturingPlant.models.BoughtPart;
import manufacturingPlant.models.Order;
import manufacturingPlant.models.Part;
import manufacturingPlant.models.Product;
import manufacturingPlant.models.ProductRun;

import org.junit.Before;
import org.junit.Test;

public class ProductRunTest extends junit.framework.TestCase{

	ProductRun run;
	ArrayList<Product> products = new ArrayList<Product>();
	Order o;
	
	@Before
	public void setUp() throws Exception {
		ArrayList<Part> parts = new ArrayList<Part>();
		parts.add(new BoughtPart("Moer")); // 0
		parts.add(new BoughtPart("Bout")); // 1
		parts.add(new BoughtPart("Staalplaat")); // 2
		parts.add(new BoughtPart("Balk")); // 3
		parts.add(new BoughtPart("Tandwiel")); // 4
		parts.add(new BoughtPart("Wieltjes")); // 5
		parts.add(new BoughtPart("Elektromotor")); // 6
		

		Map<Part, Integer> speelgoedautoParts = new HashMap<Part, Integer>();
		speelgoedautoParts.put(parts.get(0), 5);
		speelgoedautoParts.put(parts.get(1), 5);
		speelgoedautoParts.put(parts.get(2), 7);
		speelgoedautoParts.put(parts.get(3), 15);
		speelgoedautoParts.put(parts.get(4), 5);
		speelgoedautoParts.put(parts.get(5), 4);
		speelgoedautoParts.put(parts.get(6), 1);
		products.add(new Product("Speelgoedauto", speelgoedautoParts));
		
		Map<Product, Integer> map = new HashMap<Product,Integer>();
		map.put(products.get(0),2);

		o = new Order(map,"Testklant");
		
		run = new ProductRun(products.get(0), 2, o);
	}

	@Test
	public void testProductRun() {
		assertNotNull(run);
	}

	@Test
	public void testGetProduct() {
		assertEquals(products.get(0), run.getProduct());
	}

	@Test
	public void testGetAmount() {
		assertEquals(2, run.getAmount());
	}

	@Test
	public void testGetOrder() {
		assertEquals(o, run.getOrder());
	}

	@Test
	public void testIncreaseWithAmount() {
		int aantal = run.getAmount();
		run.increaseWithAmount(100);
		assertEquals(aantal + 100, run.getAmount());
	}

}
