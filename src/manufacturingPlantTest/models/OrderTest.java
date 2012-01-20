package manufacturingPlantTest.models;

import manufacturingPlant.models.AssembledProduct;
import manufacturingPlant.models.Order;
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
	}
	
	@Test
	public void testGetInitialProducts() {
		assertNull(o.getInitialProducts());
	}

	@Test
	public void testGetCustomer() {
		assertEquals(o.getCustomer(),"Klantnaam");
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
		o.addAssembledProduct(new AssembledProduct(new Product("test", null)));
	}

}
