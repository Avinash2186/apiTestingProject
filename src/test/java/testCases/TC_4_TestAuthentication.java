package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class TC_4_TestAuthentication {
	String userId = "6";
	
	
	// ToolsQA
	//TestPassword
	
	@Test(priority = 1)
	public void test_AuthenticationError() {
		RestAssured.baseURI = "http://restapi.demoqa.com/authentication";

		Response response = given().when().get("/CheckForAuthentication");

		System.out.println("response body :::: " + response.body().asString());

		JsonPath jsonObject = response.jsonPath();
		System.out.println("Status:::::" + jsonObject.get("Status"));

		Assert.assertEquals(jsonObject.get("Status"), "Invalid or expired Authentication key provided");
		Assert.assertEquals(response.statusCode(),401);
	}
	
	@Test(priority = 2)
	public void test_AuthenticationSuccess() {
		RestAssured.baseURI = "http://restapi.demoqa.com/authentication";

		Response response = given()
				.auth().basic("ToolsQA", "TestPassword")
				.when().get("/CheckForAuthentication");

		System.out.println("response body :::: " + response.body().asString());

		JsonPath jsonObject = response.jsonPath();
		System.out.println("Fault:::::" + jsonObject.get("Fault"));

		Assert.assertEquals(jsonObject.get("Fault"), "Operation completed successfully");

	}
	 

	 
}
