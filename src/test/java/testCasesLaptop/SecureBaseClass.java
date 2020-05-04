package testCasesLaptop;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;

public class SecureBaseClass {
	
	@BeforeClass
	public void setUp() {
		baseURI = "http://localhost:8081/";
		basePath="laptop-bag/webapi/secure";
		port=8081;
		authentication = preemptive().basic("admin", "welcome");
	}

}
