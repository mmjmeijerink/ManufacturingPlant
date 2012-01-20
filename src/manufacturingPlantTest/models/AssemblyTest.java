package manufacturingPlantTest.models;

import manufacturingPlant.models.Assembly;
import manufacturingPlant.models.BoughtPart;
import manufacturingPlant.models.MadePart;

import org.junit.Before;
import org.junit.Test;

public class AssemblyTest extends junit.framework.TestCase{

	private Assembly as;
	private Assembly as1;
	
	@Before
	public void setUp() throws Exception {
		as1 = new BoughtPart("test");
	}

	@Test
	public void testAssembly() {
		as = new BoughtPart("test");
		assertEquals("BoughtPart aangemaakt: ", as != null, as != null);
		as = new MadePart("test");
		assertEquals("MadePart aangemaakt: ", as != null, as != null);
	}

	@Test
	public void testGetName() {
		assertEquals("Naam word goed opgehaald: ", as1.getName(), "test");
	}

}
