package utilities;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BooksBaseClass {
	protected static Logger log;
	protected static final String USER_ID = "9b5f49ab-eea9-45f4-9d66-bcf56a531b85";
	protected static final String BASE_URL = "http://bookstore.toolsqa.com";
	protected static String token;
	protected static Response response;
	protected static String jsonString;
	protected static String bookId;
	protected static RequestSpecification request=null;
	protected static JsonPath jsonPath;


	
	@BeforeClass
	public static void setUp() { // @Optional(BASE_URL) String BASE_URL
		System.out.println("BEFORE CLASS....setup:::"+BASE_URL);
		System.out.println("baseUrl from cmd :::"+System.getProperty("baseUrl"));
		if(System.getProperty("baseUrl")==null) {
			baseURI = BASE_URL;
		} 	
		else 
		{
			baseURI = System.getProperty("baseUrl");
		}	 		
		request = given();
		request.header("Content-Type", "application/json");
		log = Logger.getLogger("API TESTING");
		PropertyConfigurator.configure("Log4j.properties");
	}
}
