package manufacturingPlantTest.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import manufacturingPlant.models.AssemblyLine;
import manufacturingPlant.models.BoughtPart;
import manufacturingPlant.models.Order;
import manufacturingPlant.models.Part;
import manufacturingPlant.models.Product;
import manufacturingPlant.models.ProductRun;

import org.junit.Before;
import org.junit.Test;

public class AssemblyLineTest extends junit.framework.TestCase{

	private AssemblyLine l;
	
	@Before
	public void setUp() throws Exception {
		l = new AssemblyLine();
	}
	
	@Test
	public void testInitial() {
		assertNotNull(l);
		assertNull(l.getProductRun());
		assertEquals(0, l.getAmountMade());
		assertTrue(l.isIdle());
	}

	@Test
	public void testGetAmountMade() {
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
		
		Map<Product, Integer> map = new HashMap<Product,Integer>();
		map.put(products.get(0),2);

		Order o = new Order(map,"Testklant");
		
		ProductRun run = new ProductRun(products.get(0), 2, o);
		l.startRun(run);
		assertEquals(2, l.getAmountMade());
	}
}
