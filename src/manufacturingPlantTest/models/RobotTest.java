package manufacturingPlantTest.models;

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
		fail("Not yet implemented");
	}

	@Test
	public void testSetPartBin() {
		fail("Not yet implemented");
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
