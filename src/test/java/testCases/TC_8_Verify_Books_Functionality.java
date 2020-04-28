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

public class TC_8_Verify_Books_Functionality extends BooksBaseClass{

    @Test()
    public void testCase1_iAmAnAuthorizedUser() {
    	log.info("iAmAnAuthorizedUser Started");
        AuthorizationRequest credentials = new AuthorizationRequest("TOOLSQA-Test","Test@@123");
        response = request.body(credentials).post("/Account/v1/GenerateToken");
        String jsonString = response.asString();
        token = JsonPath.from(jsonString).get("token");
        log.info("token generated::::"+token);
    }

    @Test()
    public void testCase2_listOfBooksAreAvailable() {
    	log.info("listOfBooksAreAvailable Started");
        response = request.get("/BookStore/v1/Books");
        jsonString = response.asString();
        List<Map<String, String>> books = JsonPath.from(jsonString).get("books");
        bookId = books.get(0).get("isbn");
        log.info("bookId::::"+bookId);
    }

    @Test()
    public void testCase3_addBookInList() {
    	log.info("addBookInList Started");
        AddBooksRequest addBooksRequest = new AddBooksRequest(USER_ID, new ISBN(bookId));
        request.header("Authorization", "Bearer " + token);
                //.header("Content-Type", "application/json");

          response = request.body(addBooksRequest).post("/BookStore/v1/Books");
          System.out.println("response::::"+response);
    }

    @Test()
    public void testCase4_bookIsAdded() {
    	log.info("bookIsAdded Started");
        Assert.assertEquals(201, response.getStatusCode());
        log.info("bookIsAdded::::"+bookId);
    }

    @Test()
    public void testCase5_removeBookFromList() {
    	log.info("removeBookFromList Started");
        RemoveBookRequest removeBookRequest = new RemoveBookRequest(USER_ID, bookId);
        request.header("Authorization", "Bearer " + token);
                //.header("Content-Type", "application/json");

        response = request.body(removeBookRequest).delete("/BookStore/v1/Book");
    }

    @Test()
    public void testCase6_bookIsRemoved(){
    	log.info("bookIsRemoved Started");
        Assert.assertEquals(204, response.getStatusCode());
        request.header("Authorization", "Bearer " + token);
                //.header("Content-Type", "application/json");

        response = request.get("/Account/v1/User/" + USER_ID);
        Assert.assertEquals(200, response.getStatusCode());
        jsonString = response.asString();
        List<Map<String, String>> booksOfUser = JsonPath.from(jsonString).get("books");
        Assert.assertEquals(0, booksOfUser.size());
        log.info("...bookIsRemoved...");

    }
}
