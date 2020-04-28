package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	public static Logger logger;

	
	@BeforeClass
	public static void setUp() {
		System.out.println("BEFORE CLASS....setup");
		  logger = Logger.getLogger("API TESTING");
		  PropertyConfigurator.configure("Log4j.properties");
	}
	
	@BeforeSuite
	public static void createDirectory() {
		System.out.println("inside create dirctory.......BEFORE SUITE....");
		String date = new SimpleDateFormat("yyyy_MM_dd").format(new Date());//time stamp		
		String directoryName = System.getProperty("user.dir")+"/TestReport_"+date;//specify location of the report
		
        File file = new File(directoryName);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
		System.out.println("Directory::::"+directoryName);
       

	}

}
