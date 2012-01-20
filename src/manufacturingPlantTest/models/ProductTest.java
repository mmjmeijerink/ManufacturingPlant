package manufacturingPlantTest.models;

import manufacturingPlant.models.Product;

import org.junit.Before;
import org.junit.Test;

public class ProductTest extends junit.framework.TestCase{

	Product p;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testProduct() {
		p = new Product("test", null);
		assertNotNull(p);
	}

	@Test
	public void testGetAssemblies() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveAssembly() {
		fail("Not yet implemented");
	}

	@Test
	public void testNewAssembledProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetParts() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPart() {
		fail("Not yet implemented");
	}

}
