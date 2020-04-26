package utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	public static Logger logger;

	
	@BeforeClass
	public static void setUp() {
		
		  logger = Logger.getLogger("API TESTING");
		  PropertyConfigurator.configure("Log4j.properties");
	}

}
