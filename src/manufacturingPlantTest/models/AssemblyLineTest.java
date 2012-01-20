package manufacturingPlantTest.models;

import static org.junit.Assert.*;

import manufacturingPlant.models.AssemblyLine;

import org.junit.Before;
import org.junit.Test;

public class AssemblyLineTest extends junit.framework.TestCase{

	private AssemblyLine l;
	
	@Before
	public void setUp() throws Exception {
		l = new AssemblyLine();
	}

	@Test
	public void testAssemblyLine() {
		assertNotNull(l);
	}
	
	@Test
	public void testInitial() {
		assertNotNull(l);
		assertNull(l.getProductRun());
		assertEquals(l.getAmountMade(),0);
		assertTrue(l.isIdle());
	}
	
	@Test
	public void testGetProductRun() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAmountMade() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeliverFinishedProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsIdle() {
		fail("Not yet implemented");
	}

	@Test
	public void testNextStep() {
		fail("Not yet implemented");
	}

	@Test
	public void testStartRun() {
		fail("Not yet implemented");
	}

}
