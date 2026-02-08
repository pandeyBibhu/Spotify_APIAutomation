package com.ApiAutomation.Spotify.tests;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ApiAutomation.Spotify.Authorization.TokenManager;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;


public class UsersInformation {
	
	@BeforeTest
	public void setup() {
		RestAssured.baseURI="https://api.spotify.com/";
	}
	
	@Test
	public void getUserId() {
		String accessToken="BQARd2rro8pDGJO_TWWfH7_3bGRQo1RQ88mbkBpmNd7MS-jJXQVpW6RRQYvOBLjOjVpjfmvbQNBdfau-E1PUlXfVqqpZFQTuvXBOJoAQeFA3Tyj_NISDhRROtajItbiuq5scIH1o5MQghfcAWPhTMWCQLJgZLF_IfQYyIErQ6aRu4k5uECUxVgiBZXhiHSXSrFG1MKnrsPzk7hHz_aDhE7WDr7SbrAAAAyaWwK1MBoxlKDMucB1CFK0VmtUKxt4--boaOPWheqttTQUvDQM-iA0Ne9chTN7wuHqOoeHwBF0bK1NKXgUa3OAXoDg5";
		
		Response response=given().log().all()
				          .header("Authorization","Bearer "+TokenManager.getAccessToken())
				          .when().get("/v1/me")
				          .then().log().all().extract().response();
		
		int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode,200);
		
		JsonPath path=new JsonPath(response.asString());
		String userId=path.getString("id");
		System.out.println("userId:::"+userId);
	}

}
