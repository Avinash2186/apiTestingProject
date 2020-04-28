package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listener implements ITestListener{
	//public static Logger logger;

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	/*
	 * public String filename=
	 * "C:\\Users\\avina\\gitProjects\\apiTestingProject\\TestReport_2020_04_26\\TestReport.xlsx";
	 * public String sheetname= "Sheet1"; public static int rowCounter;
	 */
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("==========onTestStart=================");
		/*
		 * //rowCounter=XLUtils.getRowCount(filename, sheetname); } catch (IOException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * System.out.println("rowCounter::::"+rowCounter);
		 * //logger.info("Test Started :::: "+result.getName()); try { int cellCounter =
		 * XLUtils.getCellCount(filename, sheetname, rowCounter);
		 * System.out.println("cellCounter::::"+cellCounter); } catch (IOException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test case name PASSED::::: "+result.getName());
		logger=extent.createTest(result.getName()); // create new entry in th report
		logger.log(Status.PASS,MarkupHelper.createLabel(result.getName(),ExtentColor.GREEN)); 
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test case name FAILED::::: "+result.getName());
		//logger.info("Test FAILED :::: "+result.getName());
		logger=extent.createTest(result.getName()); // create new entry in th report
		logger.log(Status.FAIL,MarkupHelper.createLabel(result.getName(),ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger=extent.createTest(result.getName()); // create new entry in th report
		logger.log(Status.SKIP,MarkupHelper.createLabel(result.getName(),ExtentColor.ORANGE));
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("ON START..................");
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";
		
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+repName);//specify location of the report
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		
		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","pavan");
		
		htmlReporter.config().setDocumentTitle("InetBanking Test Project"); // Tile of report
		htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
		
	}

	@Override
	public void onFinish(ITestContext context) {
			System.out.println("ON FINISH.............");
			extent.flush();
	}

}
