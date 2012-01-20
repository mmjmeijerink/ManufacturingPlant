package manufacturingPlantTest;

import manufacturingPlantTest.models.*;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AllTests extends TestSuite{

	/**
	 * Main method for executing all test classes listed in the method suite of this class
	 * @param args java.lang.String[]
	 */
	public static void main(String[] args) {
		TestRunner.run(new AllTests());
	}
	/**
	 * Test suite with all classes to be tested
	 * These classes could be located in other packages
	 * @return junit.framework.Test
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(AssembledProductTest.class);
		suite.addTestSuite(AssemblyLineTest.class);	
		suite.addTestSuite(AssemblyTest.class);
		suite.addTestSuite(OrderTest.class);
		suite.addTestSuite(PartBinTest.class);
		suite.addTestSuite(ProductRunTest.class);
		suite.addTestSuite(ProductTest.class);
		suite.addTestSuite(RobotTest.class);
		return suite;
	}
	
}
