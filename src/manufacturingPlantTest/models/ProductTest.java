package manufacturingPlantTest.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import manufacturingPlant.models.BoughtPart;
import manufacturingPlant.models.Part;
import manufacturingPlant.models.Product;

import org.junit.Before;
import org.junit.Test;

public class ProductTest extends junit.framework.TestCase{

	Product p;
	Map<Part, Integer> speelgoedautoParts;
	ArrayList<Part> parts;
	
	BoughtPart moer = new BoughtPart("Moer");
	BoughtPart bout = new BoughtPart("Bout");
	BoughtPart staalplaat = new BoughtPart("Staalplaat");
	BoughtPart balk = new BoughtPart("Balk");
	BoughtPart tandwiel = new BoughtPart("Tandwiel");
	BoughtPart wieltjes = new BoughtPart("Wieltjes");
	BoughtPart elektromotor = new BoughtPart("Elektromoter");
	
	@Before
	public void setUp(){
		parts = new ArrayList<Part>();
		parts.add(new BoughtPart("Moer")); // 0
		parts.add(new BoughtPart("Bout")); // 1
		parts.add(new BoughtPart("Staalplaat")); // 2
		parts.add(new BoughtPart("Balk")); // 3
		parts.add(new BoughtPart("Tandwiel")); // 4
		parts.add(new BoughtPart("Wieltjes")); // 5
		parts.add(new BoughtPart("Elektromotor")); // 6
		
		speelgoedautoParts = new HashMap<Part, Integer>();
		speelgoedautoParts.put(parts.get(0), 5);
		speelgoedautoParts.put(parts.get(1), 5);
		speelgoedautoParts.put(parts.get(2), 7);
		speelgoedautoParts.put(parts.get(3), 15);
		speelgoedautoParts.put(parts.get(4), 5);
		speelgoedautoParts.put(parts.get(5), 4);
		speelgoedautoParts.put(parts.get(6), 1);
		
		p = new Product("Speelgoedauto", speelgoedautoParts);
	}

	@Test
	public void testProduct() {
		assertNotNull(p);
		assertEquals(speelgoedautoParts, p.getPart());
		
	}

	@Test
	public void testGetAssemblies() {
		assertTrue(p.getAssemblies().isEmpty());
	}

	@Test
	public void testGetParts() {
		assertNotNull(p.getParts());
		ArrayList<Part> pieces = new ArrayList<Part>();
		ArrayList<Part> pieces2 = new ArrayList<Part>();
		for(int i = 0; i < 5; i++){
			pieces.add(parts.get(0));
			pieces2.add(moer);
		}
		for(int i = 0; i < 5; i++){
			pieces.add(parts.get(1));
			pieces2.add(bout);
		}
		for(int i = 0; i < 7; i++){
			pieces.add(parts.get(2));
			pieces2.add(staalplaat);
		}
		for(int i = 0; i < 15; i++){
			pieces.add(parts.get(3));
			pieces2.add(balk);
		}
		for(int i = 0; i < 5; i++){
			pieces.add(parts.get(4));
			pieces2.add(tandwiel);
		}
		for(int i = 0; i < 4; i++){
			pieces.add(parts.get(5));
			pieces2.add(wieltjes);
		}
		pieces.add(parts.get(6));
		pieces2.add(elektromotor);
		assertEquals(pieces.size(), p.getParts().size());
		for(int i = 0; i < pieces.size(); i++){
			assertEquals(pieces.get(i).getClass(), p.getParts().get(i).getClass());
		}
	}

	@Test
	public void testGetPart() {
		assertEquals(speelgoedautoParts, p.getPart());
	}

}
