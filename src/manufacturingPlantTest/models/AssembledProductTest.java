package manufacturingPlantTest.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import manufacturingPlant.models.AssembledProduct;
import manufacturingPlant.models.BoughtPart;
import manufacturingPlant.models.Part;
import manufacturingPlant.models.Product;

import org.junit.Before;
import org.junit.Test;

public class AssembledProductTest extends junit.framework.TestCase{

	private AssembledProduct ap;
	ArrayList<Part> parts;
	ArrayList<Product> products;
	Map<Part, Integer> speelgoedautoParts;
	
	@Before
	public void setUp() throws Exception {
		ap = new AssembledProduct(new Product("test", null));
		
		parts = new ArrayList<Part>();
		parts.add(new BoughtPart("Moer")); // 0
		parts.add(new BoughtPart("Bout")); // 1
		parts.add(new BoughtPart("Staalplaat")); // 2
		parts.add(new BoughtPart("Balk")); // 3
		parts.add(new BoughtPart("Tandwiel")); // 4
		parts.add(new BoughtPart("Wieltjes")); // 5
		parts.add(new BoughtPart("Elektromotor")); // 6
		
		products = new ArrayList<Product>();
		speelgoedautoParts = new HashMap<Part, Integer>();
		speelgoedautoParts.put(parts.get(0), 5);
		speelgoedautoParts.put(parts.get(1), 5);
		speelgoedautoParts.put(parts.get(2), 7);
		speelgoedautoParts.put(parts.get(3), 15);
		speelgoedautoParts.put(parts.get(4), 5);
		speelgoedautoParts.put(parts.get(5), 4);
		speelgoedautoParts.put(parts.get(6), 1);
		products.add(new Product("Speelgoedauto", speelgoedautoParts));
	}

	@Test
	public void testAssembledProduct() {
		assertNotNull(ap);
		assertEquals(-1, ap.getSerialNumber());
		assertEquals(new ArrayList<Part>(), ap.getAssembledParts());
	}

	@Test
	public void testSetSerialNumber() {
		ap.setSerialNumber(10001000);
		assertEquals(10001000, ap.getSerialNumber());
	}

	@Test
	public void testAssembleParts() {
		ap = new AssembledProduct((Product)products.get(0));
		assertNotNull(ap);
		ap.assembleParts(parts);
		assertFalse(ap.getAssembledParts().isEmpty());
		assertEquals(parts, ap.getAssembledParts());
	}
	
	@Test
	public void testGetAssembledParts() {
		assertTrue(ap.getAssembledParts().isEmpty());
		ap.assembleParts(parts);
		assertFalse(ap.getAssembledParts().isEmpty());
		assertEquals(parts, ap.getAssembledParts());
	}

	@Test
	public void testMoveStation() {
		int nr = ap.getStationNumber();
		ap.moveStation();
		assertEquals(nr+1, ap.getStationNumber());
	}

	@Test
	public void testGetMissingParts() {
		ap = new AssembledProduct((Product)products.get(0));
		assertNotNull(ap);
		ap.assembleParts(parts);
		assertFalse(ap.getAssembledParts().isEmpty());
		assertNotNull(ap.getMissingParts());
		assertTrue(ap.getMissingParts().isEmpty());
		
		//Half assembleren
		AssembledProduct prod = new AssembledProduct((Product)products.get(0));
		ArrayList<Part >parts2 = new ArrayList<Part>();
		parts2.add(new BoughtPart("Moer")); // 0
		parts2.add(new BoughtPart("Bout")); // 1
		parts2.add(new BoughtPart("Staalplaat")); // 2
		prod.assembleParts(parts2);
		assertFalse(prod.getAssembledParts().isEmpty());
		assertNotNull(prod.getMissingParts());
		assertFalse(prod.getMissingParts().isEmpty());
	}

	@Test
	public void testGetProduct() {
		ap = new AssembledProduct((Product)products.get(0));
		assertEquals(ap.getProduct(),(Product)products.get(0));
	}

	@Test
	public void testIsAssembled() {
		AssembledProduct prod = new AssembledProduct((Product)products.get(0));
		assertFalse(prod.isAssembled());
		ArrayList<Part >parts2 = new ArrayList<Part>();
		parts2.add(new BoughtPart("Moer")); // 0
		parts2.add(new BoughtPart("Bout")); // 1
		parts2.add(new BoughtPart("Staalplaat")); // 2
		prod.assembleParts(parts2);
		assertFalse(prod.isAssembled());
		
		prod = new AssembledProduct((Product)products.get(0));
		prod.assembleParts(parts);
		assertTrue(prod.isAssembled());
	}

}
