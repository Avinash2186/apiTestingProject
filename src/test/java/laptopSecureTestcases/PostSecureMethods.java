package laptopSecureTestcases;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import laptopClassFiles.Features;
import laptopClassFiles.LaptopBag;
import testCasesLaptop.SecureBaseClass;

public class PostSecureMethods extends SecureBaseClass{
	
	@Test
		public void testPost_Seriliased_Auth_InsideTest() {
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
					.auth()
					.preemptive()
					.basic("admin","welcome")
					.log().all()
			.accept(ContentType.XML)
			.with()
			.contentType(ContentType.JSON)
			.body(bag)
			.post("/add")
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

}
	
	
	@Test
	public void testPost_Seriliased_Auth_FromBaseClass() {
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
				/*
				 * .auth() .preemptive() .basic("admin","welcome")
				 */
				.log().all()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.JSON)
		.body(bag)
		.post("/add")
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

}
}
