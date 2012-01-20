package manufacturingPlantTest.models;

import manufacturingPlant.models.AssemblyLine;
import manufacturingPlant.models.Robot;

import org.junit.Before;
import org.junit.Test;

public class RobotTest extends junit.framework.TestCase{
	
	@Before
	public void setUp() throws Exception {}

	@Test
	public void testRobot() {
		assertNotNull(new Robot(null,1));
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
	public void testGetProductUnderConstruction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStationNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsFirstRobotOfTheLine() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsLastRobotOfTheLine() {
		fail("Not yet implemented");
	}

}
