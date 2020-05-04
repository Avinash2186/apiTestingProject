package testCases;


	import org.testng.Assert;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

import allClassesFiles.UsersInfo;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
	import io.restassured.response.ResponseBody;
import utilities.BaseClass;
import utilities.XLUtils;

	import static io.restassured.RestAssured.*;

	import java.io.IOException;
	import java.util.HashMap;
import java.util.List;


public class TC_2_VerifySingleUser_DD extends BaseClass{
		//@Test(dataProvider="UserId")
		public void test_singleUser(String Key,String userId,String emailId) {
			Response response = given()
			.when().
			get("https://reqres.in/api/users/"+userId);
			ResponseBody body = response.getBody();
			System.out.println("response body :::: "+body.asString());			
			JsonPath jsonPathEvaluator = response.jsonPath();	
			String email = jsonPathEvaluator.get("data.email");
			System.out.println("email:::::::;"+email);
			logger.info("Email Id from Response - "+email);
			try {
			Assert.assertTrue(email.equalsIgnoreCase(emailId));
			logger.info("Verified Email in Response - "+email);
			} catch (Exception e) {
				logger.error("Failed Email id :: "+email);
				Assert.assertTrue(false);
			}
			
		}
		 
		@DataProvider(name="UserId")
		String [][] getData() throws IOException
		{
			System.out.println("INside getData function......");
			String path=System.getProperty("user.dir")+"/src/test/java/testData/UserId.xlsx";
			
			int rownum=XLUtils.getRowCount(path, "Users");
			int colcount=XLUtils.getCellCount(path,"Users",1);
			
			String logindata[][]=new String[rownum][colcount];
			
			for(int i=1;i<=rownum;i++)
			{
				for(int j=0;j<colcount;j++)
				{
					logindata[i-1][j]=XLUtils.getCellData(path,"Users", i,j);//1 0
				}
					
			}
			System.out.println("Login data::::"+logindata);
		return logindata;
		}

		 
	}

	
	


