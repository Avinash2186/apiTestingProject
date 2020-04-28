package testCases;


	import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.Matcher;
import org.testng.Assert;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import allClassesFiles.UsersInfo;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import static io.restassured.path.json.JsonPath.from;
import io.restassured.response.Response;
	import io.restassured.response.ResponseBody;
import utilities.BaseClass;
import utilities.XLUtils;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


	import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TC_6_VerifySlowResponse extends BaseClass{
	public String filename="C:\\Users\\avina\\gitProjects\\apiTestingProject\\TestReport_2020_04_26\\TestReport.xlsx";
	public String sheetname= "Sheet1";
	public static Response response =null;
	
		@Test(priority = 1)
		public void test_slowResponse() throws IOException {
			response = given()
			.when().
			get("https://reqres.in/api/users");
			String responseAsString = response.asString();
			logger.info("Verified Slow response is nt an Issue....... Taking 3 Seconds....");
			// take out only all firstNames
			List<String> firstNames = from(responseAsString).getList("data.first_name");
			for(String f : firstNames) {
				System.out.println("first name ::: "+f);
			}
			
			// take out only  firstNames where id less than 4
						List<String> firstNamesList = from(responseAsString).getList("data.findAll {it.id < 4 }.first_name");
						for(String f : firstNamesList) {
							System.out.println("first name with id less than 4 ::: "+f);
						}
			
			JsonPath path = response.jsonPath();
	        List<Map<String, ?>> data = path.get("data.findAll { data -> data.id > 2 && data.id < 5}");
	        System.out.println("size of data is :::"+data.size());
	        //assertThat(data.size(), equalTo(1));
	        Assert.assertEquals(data.size() , 2);
			System.out.println("data with Id as 1 :::"+data.get(0).get("email"));
			System.out.println("data with Id as 2 :::"+data.get(1).get("email"));

			List<String> listOfEmails = path.getList("data.email");
			
			for (String e : listOfEmails) {
				System.out.println("email::: "+e);
			}
			
			/*
			 * System.out.println("XLUtils.getRowCount(filename, sheetname);===="+XLUtils.
			 * getRowCount(filename, sheetname));
			 * System.out.println("XLUtils.gecellCount(filename, sheetname);===="+XLUtils.
			 * getCellCount(filename, sheetname,0));
			 */
			try {
				XLUtils.setCellData(filename, sheetname, 0, 7 , "PASSED");
				
				/*
				 * File src = new File(filename); FileInputStream fis = new
				 * FileInputStream(src); XSSFWorkbook wb = new XSSFWorkbook(fis); XSSFSheet
				 * sheet1 = wb.getSheetAt(0);
				 * sheet1.getRow(0).createCell(5).setCellValue("PASSED"); FileOutputStream fout
				 * = new FileOutputStream(src); wb.write(fout); wb.close();
				 * 
				 */
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("UNable to set vaue............"+e.getMessage());
			}
			
		}
		
		
		@Test()
		public void Verify_EmailId_Of_Records_InBetween_Second_And_Fifth() throws IOException {
			response = given()
			.when().
			get("https://reqres.in/api/users");
			JsonPath path = response.jsonPath();
	        List<Map<String, ?>> data = path.get("data.findAll { data -> data.id > 2 && data.id < 5}");
	        System.out.println("size of data is :::"+data.size());
	        //assertThat(data.size(), equalTo(1));
	        Assert.assertEquals(data.size() , 2);
			System.out.println("data with Id as 1 :::"+data.get(0).get("email"));
			System.out.println("data with Id as 2 :::"+data.get(1).get("email"));
		}
		
		@Test()
		public void Verify_FirstName_LastName_Where_Id_Is_3() throws IOException {
			response = given()
			.when().
			get("https://reqres.in/api/users");
			JsonPath path = response.jsonPath();
	        List<Map<String, ?>> data = path.get("data.findAll { data -> data.id == 3}");
	        System.out.println("size of data is :::"+data.size());
	        //assertThat(data.size(), equalTo(1));
	        Assert.assertEquals(data.size() , 1);
			System.out.println("FirstName with Id as 3 :::"+data.get(0).get("first_name"));
			System.out.println("LastNAme with Id as 3 :::"+data.get(0).get("last_name"));
		}
		
		
		@Test()
		public void Verify_FirstName_LastName_Where_Id_Is_3_With_Assert() throws IOException {
			SoftAssert softAssert = new SoftAssert();
			
			response = given()
					.when().
					get("https://reqres.in/api/users");
					JsonPath path = response.jsonPath();
					List<Map<String, ?>> data = path.get("data");
			        System.out.println("size of data is :::"+data.size());
			
			
				
				softAssert.assertEquals(data.size(), 2);
				softAssert.assertEquals(data.get(0).get("first_name"), "Georgey");
				softAssert.assertEquals(data.get(0).get("first_name"), "George");
			
				softAssert.assertAll();
			
			//assertThat(,equalTo("George"));
			
		}

		 
	}

	
	


