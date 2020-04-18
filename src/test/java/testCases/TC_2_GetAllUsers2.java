package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class TC_2_GetAllUsers2 {
	String userId = "6";
	
	@Test(priority = 3)
	public void test_getAllUsers_Page1() {
		given()
		.when().get("https://reqres.in/api/users")
		.then().statusCode(200)
		.log().body();	
	}
	
	@Test (priority = 1)
	public void test_CreateUser() {
		HashMap data = new HashMap();
		data.put("name", "Avinash");
		data.put("job", "Automation Lead");
		
		Response res = given()
		.contentType("application/json")
		.body(data)
		.when()
		.post("https://reqres.in/api/users")
		.then()
		.statusCode(201)
		.log().body()
		.extract().response();
		
		System.out.println("=======================================/n \n ");
		String responseBodyAsString = res.asString();
		System.out.println("responseBodyAsString===>"+responseBodyAsString);
		
	}
	
	@Test(priority = 2)
		public void test_getNewlyCreatedUser() {
		System.out.println("STARTED 2nd TEST CASE updated***********************");
			given()
			.when().get("https://reqres.in/api/users/"+userId)
			.then().statusCode(200)
			.log().body().extract();	
		}

}
