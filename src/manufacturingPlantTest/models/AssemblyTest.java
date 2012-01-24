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
		assertNotNull(as);
		as = new MadePart("test");
		assertNotNull(as);
	}

	@Test
	public void testGetName() {
		assertEquals("Naam word goed opgehaald: ", "test", as1.getName() );
	}

}
