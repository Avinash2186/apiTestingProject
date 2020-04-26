package utilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener{
	public static Logger logger;

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("==========onTestStart=================");
		//logger.info("Test Started :::: "+result.getName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test case name PASSED::::: "+result.getName());
		//logger.info("Test PASSED :::: "+result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test case name FAILED::::: "+result.getName());
		//logger.info("Test FAILED :::: "+result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("ON START..................");
		/*
		 * logger = Logger.getLogger("API TESTING");
		 * PropertyConfigurator.configure("Log4j.properties");
		 * logger.info("Suite Started.....");
		 */
	}

	@Override
	public void onFinish(ITestContext context) {
			System.out.println("ON FINISH.............");
			//logger.info("Suite Ended.....");
	}

}
