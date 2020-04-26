package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import utilities.BaseClass;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class TC_3_VerifyHeaders extends BaseClass{
	String userId = "6";
	
	
	@Test(priority = 1)
	public void test_Headers() {
		Response response = given()
		.when().
		get("https://reqres.in/api/users");
		
		System.out.println("response :::: "+response);
		
		Headers headers = response.headers();
		
		for (Header header : headers) {
			System.out.println("header name :: "+header.getName() +" -- -- header value :: "+header.getValue());
		}
		logger.info("Headers Validated......");
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");
		
	}
	 
	
	/*
	 * @Test (priority = 1) public void test_CreateUser() { HashMap data = new
	 * HashMap(); data.put("name", "Avinash"); data.put("job", "Automation Lead");
	 * 
	 * Response res = given() .contentType("application/json") .body(data) .when()
	 * .post("https://reqres.in/api/users") .then() .statusCode(201) .log().body()
	 * .extract().response();
	 * 
	 * System.out.println("=======================================/n \n "); String
	 * responseBodyAsString = res.asString();
	 * System.out.println("responseBodyAsString===>"+responseBodyAsString);
	 * 
	 * }
	 */
	
	/*
	 * @Test(priority = 2) public void test_getNewlyCreatedUser() {
	 * System.out.println("STARTED 2nd TEST CASE updated***********************");
	 * given() .when().get("https://reqres.in/api/users/"+userId)
	 * .then().statusCode(200) .log().body().extract(); }
	 */
	 
}
