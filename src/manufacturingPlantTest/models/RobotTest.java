package manufacturingPlantTest.models;

import manufacturingPlant.models.PartBin;
import manufacturingPlant.models.Robot;

import org.junit.Before;
import org.junit.Test;

public class RobotTest extends junit.framework.TestCase{
	
	private Robot r;
	
	@Before
	public void setUp() throws Exception {
		r = new Robot(null,1);
	}

	@Test
	public void testRobot() {
		assertNotNull(r);
	}

	@Test
	public void testDoStep() {
		fail("Still to be implemented");
	}

	@Test
	public void testSetPartBin() {
		PartBin bin = new PartBin(null);
		r.setPartBin(bin);
		assertEquals(r.getPartBin(),bin);
	}

	@Test
	public void testGetStationNumber() {
		assertEquals(r.getStationNumber(), 1);
	}

	@Test
	public void testIsFirstRobotOfTheLine() {
		assertTrue(r.isFirstRobotOfTheLine());
	}

	@Test
	public void testIsLastRobotOfTheLine() {
		assertFalse(r.isLastRobotOfTheLine());
	}

}
