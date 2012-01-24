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
	public void testSetPartBin() {
		PartBin bin = new PartBin(null);
		r.setPartBin(bin);
		assertEquals(bin, r.getPartBin());
	}

	@Test
	public void testGetStationNumber() {
		assertEquals(1, r.getStationNumber());
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
