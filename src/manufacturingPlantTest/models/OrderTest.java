package manufacturingPlantTest.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import manufacturingPlant.models.AssembledProduct;
import manufacturingPlant.models.BoughtPart;
import manufacturingPlant.models.Order;
import manufacturingPlant.models.Part;
import manufacturingPlant.models.Product;

import org.junit.Before;
import org.junit.Test;

public class OrderTest extends junit.framework.TestCase{

	private Order o;
	
	@Before
	public void setUp() throws Exception {
		o = new Order(null, "Klantnaam");
	}

	@Test
	public void testOrder() {
		assertNotNull(o);
		assertEquals("Klantnaam", o.getCustomer());
		assertNull(o.getProducts());
		assertTrue(o.getAssemblies().isEmpty());
		assertTrue(o.isFinished());
	}
	
	@Test
	public void testGetInitialProducts() {
		assertNull(o.getInitialProducts());
	}

	@Test
	public void testGetProducts() {
		assertNull(o.getProducts());
	}

	@Test
	public void testGetAssemblies() {
		assertTrue(o.getAssemblies().isEmpty());
	}

	@Test
	public void testIsFinished() {
		assertTrue(o.isFinished());
	}

	@Test
	public void testAddAssembledProduct() {
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
		o = new Order(map,"TEST");
		
		o.addAssembledProduct(new AssembledProduct(products.get(0)));
		assertFalse(o.getAssemblies().isEmpty());
		assertEquals(1, o.getAssemblies().size());
		assertFalse(o.isFinished());
		
		o.addAssembledProduct(new AssembledProduct(products.get(0)));
		assertFalse(o.getAssemblies().isEmpty());
		assertEquals(1, o.getAssemblies().size());
		assertTrue(o.isFinished());
		
	}

}
