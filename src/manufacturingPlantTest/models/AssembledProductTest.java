package manufacturingPlantTest.models;

import static org.junit.Assert.*;

import manufacturingPlant.models.AssembledProduct;
import manufacturingPlant.models.Product;

import org.junit.Before;
import org.junit.Test;

public class AssembledProductTest extends junit.framework.TestCase{

	private AssembledProduct ap;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAssembledProduct() {
		ap = new AssembledProduct(new Product("test", null));
		assertNotNull(ap);
		assertEquals(ap.getSerialNumber(), -1);
	}

	@Test
	public void testGetSerialNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSerialNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAssembledParts() {
		fail("Not yet implemented");
	}

	@Test
	public void testAssembleParts() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveStation() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMissingParts() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsAssembled() {
		fail("Not yet implemented");
	}

}
