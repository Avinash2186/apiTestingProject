package testCasesLaptop;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class SGetMethods {
	
	@Test
	public void testGet_Body_NotNull() {
		
		 given()
		.accept(ContentType.JSON)
		.when()
		.get("http://localhost:8081/laptop-bag/webapi/api/all")
		.then()
		.assertThat()
		//.statusCode(HttpStatus.SC_OK)
		.body("$", is(notNullValue()));	
	}
	
	
	@Test
	public void testGet_Response_AsString_JSON() {
		
		Response res =  given()
		.accept(ContentType.JSON)
		.when()
		.get("http://localhost:8081/laptop-bag/webapi/api/all")
		.thenReturn();
		
		System.out.println("response::::"+res);
		System.out.println("response as String ::::"+res.asString());
		System.out.println("response body :::: "+res.getBody());
		System.out.println("response body as String  :::: "+res.getBody().asString());
		
	}
	
	@Test
public void testGet_Response_AsString_XML() {
		
		Response res =  given()
		.accept(ContentType.XML)
		.when()
		.get("http://localhost:8081/laptop-bag/webapi/api/all")
		.thenReturn();
		
		System.out.println("response::::"+res);
		System.out.println("response as String ::::"+res.asString());
		System.out.println("response body :::: "+res.getBody());
		System.out.println("response body as String  :::: "+res.getBody().asString());
		
	}

}
