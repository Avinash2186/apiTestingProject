package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import allClassesFiles.UsersInfo;
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
				
				List<UsersInfo> users = jsonPathEvaluator.getList("data",UsersInfo.class);
				System.out.println("users size::::::;"+users.size());
				// Iterate over the list and print individual book item
				for(UsersInfo user : users)
				{
					System.out.println("User id : " + user.getId() + " "+"User email : " + user.getEmail());
					System.out.println();
					if(user.getId().equalsIgnoreCase("2"))
						Assert.assertTrue(user.getEmail().contains("janet"));
					else 
						System.out.println("Other than Janet.....");
						
				}
			}			
}
