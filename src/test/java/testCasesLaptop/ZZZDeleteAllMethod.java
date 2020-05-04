package testCasesLaptop;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ZZZDeleteAllMethod {

	//@Test
	public void Delete() {
		
	
	String body = given().accept(ContentType.JSON)
	.when()
	.get("http://localhost:8081/laptop-bag/webapi/api/all")
	.thenReturn()
	.body().asString();
	
	JsonPath json = new JsonPath(body);
	List<Integer> listofId = json.getList("Id");
	System.out.println("List of Id :::"+listofId.size());
	for (int id : listofId)
	{
		System.out.println("id -- "+id);
		given().delete("http://localhost:8081/laptop-bag/webapi/api/delete/"+id+"");
	}
			/*
			 * .accept(ContentType.XML) .with() .contentType(ContentType.JSON)
			 */
		//.delete("http://localhost:8081/laptop-bag/webapi/api/delete/155");
	}
}
