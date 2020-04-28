package testCases;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC_7_Spotify_GetTrack {
	public static String trackId="5O932cZmzOZGOGZz9RHx20";
	public static String token="BQDTucwJ7enErcbxgb_SGQFci0k9Meupmf1-50dNttyBYfPxFrQoEp59knSZ-LautQ-vh1yhsxPKpGckz7Dem6ob4zPAQ4riyEeLvap1UPNcIfYzz0kT5ivjHrEnlJl_Ol2bYp1Dy_l_m_kAYIWjjh27DXRkscXxAE0";
	
	@Test(priority = 1)
	public void test_getTrackDetails() {
		Response res = given()
		.header("Authorization","Bearer " + token)
		.header("Content-Type", "application/json")
		.log().all()
		.when().get("https://api.spotify.com/v1/tracks/"+trackId);
		/*.then().statusCode(200)
		.log().all();*/
		
		JsonPath path = res.jsonPath();
		String songName = path.get("name");
        System.out.println("Name of Song is  :::"+songName);
		
        List<Map<String, ?>> markets = path.get("available_markets");
        System.out.println("size of data is :::"+markets.size());
        System.out.println("first Market name :::"+markets.get(0));
        
        
        //List<Map<String, ?>> artist = path.get("artists");
        List<Map<String, ?>> artists = path.get("artists.findAll { artists -> artists.type == 'artist'}");
        System.out.println("size of data is :::"+artists.size());
        System.out.println("Name of Artist :::"+artists.get(0).get("name"));
        
        
        
        
        
		
	}

}
