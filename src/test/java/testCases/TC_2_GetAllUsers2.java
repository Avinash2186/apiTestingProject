package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class TC_2_GetAllUsers2 {
	String userId = "6";
	
	
	@Test(priority = 3)
	public void test_getAllUsers_Page1() {
		Response response = given()
		.when().
		get("https://reqres.in/api/users");
		
		System.out.println("response :::: "+response);
		
		ResponseBody body = response.getBody();
		
		System.out.println("body ::::: "+body);
		
		System.out.println("body as string ::::: "+body.asString());
		
		System.out.println("response jsonpath:::"+response.jsonPath().get("per_page"));
		
		System.out.println("response Headers :::: "+response.getHeaders());
		System.out.println("Content type :::: "+response.getHeader("Content-Type"));
		
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
