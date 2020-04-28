package testCases;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import allClassesFiles.AddBooksRequest;
import allClassesFiles.AuthorizationRequest;
import allClassesFiles.ISBN;
import allClassesFiles.RemoveBookRequest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.BaseClass;
import utilities.BooksBaseClass;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TC_9_Verify_Books_Validations extends BooksBaseClass{

   
    @Test
    public void testCase1_GetRequest_StatusCode_200() {
    	log.info("listOfBooksAreAvailable Started");
        response = request.get("/BookStore/v1/Books");
        jsonString = response.asString();
        System.out.println("jsonString::::"+jsonString);
        log.info("Status Code :: "+response.getStatusCode());
    }
    
    @Test
    public void testCase2_GetRequest_Validate_BookId_And_Pages() {
    	log.info("listOfBooksAreAvailable Started");
        response = request.get("/BookStore/v1/Books");
        jsonPath = response.jsonPath();
        jsonString = response.asString();
		
		
		  List<Map<String, Object>> books = JsonPath.from(jsonString).get("books");
		  bookId = (String) books.get(0).get("isbn"); 
		  int pages = (int) books.get(0).get("pages");
		  log.info("bookId::::"+bookId); 
		  log.info("pages::::"+pages);
		 
        
			/*
			 * List<Map<String, ?>> books = jsonPath.get("books"); bookId = (String)
			 * books.get(0).get("isbn"); int pages = (int) books.get(0).get("pages");
			 * log.info("bookId::::"+bookId); log.info("pages::::"+pages);
			 */        
    }
    
    @Test
    public void testCase3_GetRequest_StatusCode_200_IntentionalFAiled() {
    	log.info("listOfBooksAreAvailable Started");
        response = request.get("/BookStore/v1/Books");
        jsonString = response.asString();
        System.out.println("jsonString::::"+jsonString);
        log.info("Status Code :: "+response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 401);
    }


}
