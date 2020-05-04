package testCasesLaptop;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import laptopClassFiles.Features;
import laptopClassFiles.LaptopBag;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;

public class PostMethodsLaptop {
	
	@Test
	public void testPost_Seriliased() {
		String id = (int) (1000*(Math.random()))+"";
		System.out.println("id:::"+id);
		LaptopBag bag = new LaptopBag();
		bag.setBrandName("Lenevo");
		bag.setId(id);
		bag.setLaptopName("Bing");
		Features features = new Features();
		features.setFeatures(new ArrayList<String>(Arrays.asList("1 GB Ram","6 TB Hard Drive")));
		bag.setFeatures(features);
		
		String res = given()
				.log().all()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.JSON)
		.body(bag)
		.post("http://localhost:8081/laptop-bag/webapi/api/add")
		.thenReturn()
		//.getBody();
		.asString();
		/*.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body("Id", equalTo(Integer.parseInt(id)));*/
		System.out.println("body:::"+res.toString());
		
		XmlPath json = new XmlPath(res);
		System.out.println("ID:::"+json.get("Laptop.Id"));
		Assert.assertEquals((String) json.get("Laptop.Id"), id);
		//Assert.assertEquals(json.get("Laptop.Id"), id);
		
		//System.out.println("response:::"+s);
		/*
		 * JsonPath json = new JsonPath(res);
		 * System.out.println("ID:::"+json.get("Id"));
		 * Assert.assertEquals(json.get("Id"), id);
		 */
		// JSON to JSON ok
		// XML to JSON ok
		
		
		
	}
	
	@Test
	public void testPost_SeriliasedWithCheck() {
		String id = (int) (1000*(Math.random()))+"";
		System.out.println("id:::"+id);
		LaptopBag bag = new LaptopBag();
		bag.setBrandName("Lenevo");
		bag.setId(id);
		bag.setLaptopName("Bing");
		Features features = new Features();
		features.setFeatures(new ArrayList<String>(Arrays.asList("1 GB Ram","6 TB Hard Drive")));
		
		bag.setFeatures(features);
		
		 given()
				.log().all()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.JSON)
		.body(bag)
		.post("http://localhost:8081/laptop-bag/webapi/api/add")
		
		.then().assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body("Laptop.Id", equalTo(id));
		//System.out.println("body:::"+res.toString());
				
		
	}
	
	@Test
	public void testPost_DeSeriliased() {
		String id = (int) (1000*(Math.random()))+"";
		System.out.println("id:::"+id);
		LaptopBag bag = new LaptopBag();
		bag.setBrandName("Lenevo");
		bag.setId(id);
		bag.setLaptopName("Bing");
		Features features = new Features();
		//features.setFeatures(new ArrayList<String>(Arrays.asList("1 GB Ram","6 TB Hard Drive")));
		ArrayList<String> feat = new ArrayList<String>();
		feat.add("1 GB Ram");
		feat.add("6 TB HARD DRIVE");
		features.setFeatures(feat);
		bag.setFeatures(features);
		
		 
		LaptopBag response =  given()
				.log().all()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.JSON)
		.body(bag)
		.post("http://localhost:8081/laptop-bag/webapi/api/add")
		
		.thenReturn()
		.as(LaptopBag.class);
		
		System.out.println("response :::"+response.getBrandName());
		System.out.println("response :::"+response.getId());
		System.out.println("response :::"+response.getLaptopName());
		System.out.println("response feature 1:::"+response.getFeatures().getFeatures().get(0));
		System.out.println("response feature 2:::"+response.getFeatures().getFeatures().get(1));
		
	}


}
