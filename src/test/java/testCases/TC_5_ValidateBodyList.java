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
import java.util.List;

public class TC_5_ValidateBodyList {
	String userId = "6";
	
	
	// ToolsQA
	//TestPassword
	
	@Test(priority = 1)
	public void test_ResponseBody() {
		Response response = given()
				.when().
				get("https://reqres.in/api/users");
				
				System.out.println("response :::: "+response.body().asString());
				
				JsonPath jsonPathEvaluator = response.jsonPath();
				
				List<String> allBooks = jsonPathEvaluator.getList("data.email");
				System.out.println("allbooks size::::::;"+allBooks.size());
				// Iterate over the list and print individual book item
				for(String book : allBooks)
				{
					System.out.println("Book: " + book);
				}
			}			
}
