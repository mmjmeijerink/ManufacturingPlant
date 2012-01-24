package manufacturingPlantTest.models;

import java.util.ArrayList;

import manufacturingPlant.models.MadePart;
import manufacturingPlant.models.Part;
import manufacturingPlant.models.PartBin;

import org.junit.Before;
import org.junit.Test;

public class PartBinTest extends junit.framework.TestCase{

	private PartBin bin;
	private ArrayList<Part> parts = new ArrayList<Part>();
	
	@Before
	public void setUp() throws Exception {
		parts.add(new MadePart("test"));
		parts.add(new MadePart("test"));
		parts.add(new MadePart("test"));
		parts.add(new MadePart("test"));	
	}

	@Test
	public void testPartBin() {
		bin = new PartBin(parts);
		assertNotNull(bin);
	}

	@Test
	public void testGetParts() {
		bin = new PartBin(parts);
		assertNotNull(bin.getParts());
		assertEquals("Parts zijn hetzelfde: ", parts, bin.getParts());
		assertTrue(bin.getParts().containsAll(parts));
	}

}
