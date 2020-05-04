package laptopSecureTestcases;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetMethodWithSecure {

	@Test
	public void testGetWithAuth_SC_UNAUTHORIZED() {
		expect()
		.log()
		.all().statusCode(HttpStatus.SC_UNAUTHORIZED)
		.when()
		.get("http://localhost:8081/laptop-bag/webapi/secure/all");
		}
	
	@Test
	public void testGetWithAuth_WithHEader_BASICAUTH() {
		given()
		.header("Authorization","Basic YWRtaW46d2VsY29tZQ==")
		.when()
		.get("http://localhost:8081/laptop-bag/webapi/secure/all")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
		}
	
	@Test
	public void testGetWith_AuthMethod_FailedExpected_BasicAuTh_Challenged() { // server send credentials only when asked
		given()
		.auth()
		.basic("admin","welcome")
		.when()
		.get("http://localhost:8081/laptop-bag/webapi/secure/all")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
		}
	
	@Test
	public void testGetWith_AuthMethod_Preemptive() {
		given()
		.auth()
		.preemptive()
		.basic("admin","welcome")
		.when()
		.get("http://localhost:8081/laptop-bag/webapi/secure/all")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
		}
	
	@Test
	public void testGetWith_SSL() { // server send credentials only when asked
		given()
		.relaxedHTTPSValidation()
				/*
				 * .auth() .basic("admin","welcome")
				 */
		.when()
		.get("http://localhost:8081/laptop-bag/webapi/sslres/all")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
		}
	
}
