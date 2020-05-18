package testCases;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.BaseClass;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class TC_1_GetAllUsers extends BaseClass{
	String userId = "6";
	
	@Test(priority = 3)
	public void test_getAllUsers_Page1() {
		given()
		.when().get("https://reqres.in/api/users")
		.then().statusCode(200);
		//logger.info("Status 200 Check OK.........");
		//.log().body();	
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
		.body("isEmpty()", Matchers.is(false))//.statusCode(200)

		.log().body()
		.extract().response();
		
		System.out.println("=======================================/n \n ");
		String responseBodyAsString = res.asString();
		//System.out.println("responseBodyAsString===>"+responseBodyAsString);
		JsonPath path = res.jsonPath();
		userId = path.getString("id");
		//res.getStatusCode()
		//logger.info("User Created. cna Check Status Code equals 201.. USer Id  ......."+userId);
	}
	
	@Test(priority = 2)
		public void test_getNewlyCreatedUser() {
		System.out.println("STARTED 2nd TEST CASE updated**** to get newly created user*******************"+userId);
			given()
			.log().all()
			.when().get("https://reqres.in/api/users/"+userId)
			.then()
			.body("isEmpty()", Matchers.is(true))//.statusCode(200)
			.log().all().extract();
				
		}

}
