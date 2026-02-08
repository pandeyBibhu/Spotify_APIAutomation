package com.ApiAutomation.Spotify.tests;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertThat;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ApiAutomation.Spotify.Authorization.TokenManager;
import com.ApiAutomation.Spotify.Builders.SpecBuilder;
import com.ApiAutomation.Spotify.Pojo.CreatePlaylistRequestPayload;
import com.ApiAutomation.Spotify.Pojo.PlaylistResponse;
import com.ApiAutomation.Spotify.Utils.CommonUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PlayList {
	String userId = "";
	String playlistId = "";
//	String accessToken = "BQARd2rro8pDGJO_TWWfH7_3bGRQo1RQ88mbkBpmNd7MS-jJXQVpW6RRQYvOBLjOjVpjfmvbQNBdfau-E1PUlXfVqqpZFQTuvXBOJoAQeFA3Tyj_NISDhRROtajItbiuq5scIH1o5MQghfcAWPhTMWCQLJgZLF_IfQYyIErQ6aRu4k5uECUxVgiBZXhiHSXSrFG1MKnrsPzk7hHz_aDhE7WDr7SbrAAAAyaWwK1MBoxlKDMucB1CFK0VmtUKxt4--boaOPWheqttTQUvDQM-iA0Ne9chTN7wuHqOoeHwBF0bK1NKXgUa3OAXoDg5";
	CreatePlaylistRequestPayload reqPayload = new CreatePlaylistRequestPayload();

	@BeforeTest
	public void setup() {
		RestAssured.baseURI = "https://api.spotify.com/";
	}

	@Test
	@Description("This test attempts to log into the website using a login and a password. Fails if any error happens.\n\nNote that this test does not test 2-Factor Authentication.")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("John Doe")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
	public void getUserId() {

		Response response = given().log().all().header("Authorization", "Bearer " + TokenManager.getAccessToken())
				.when().get("/v1/me").then().log().all().extract().response();

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

		JsonPath path = new JsonPath(response.asString());
		userId = path.getString("id");
		System.out.println("userId:::" + userId);
	}

	@Test(dependsOnMethods = "getUserId")
	public void createPlayList() {
//		CreatePlaylistRequestPayload reqPayload = new CreatePlaylistRequestPayload();

		reqPayload.setName("BibhuPlayList1");
		reqPayload.setDescription("BolloywoodPlaylist");
		reqPayload.setIsPublic(false);

		Response response = given().log().all().spec(SpecBuilder.postRequest(reqPayload, userId)).when()
				.post("/v1/users/{userId}/playlists").then().log().all().extract().response();

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);

		JsonPath path = new JsonPath(response.asString());
		playlistId = path.getString("id");
		System.out.println("PlaylistId::" + playlistId);
	}

	@Test(dependsOnMethods = "createPlayList")
	public void getPlaylist() {
		Response response = given().log().all().spec(SpecBuilder.getRequest(playlistId)).when()
				.get("v1/playlists/{playlistId}").then().statusCode(200).log().all().extract().response();
		/* Validating response body and comparing json schema */
		CommonUtils.validateSchema(response, "schemas/getPlaylistSchema.json");
		/* validating Pojo with response body for business logic */

		PlaylistResponse playlistResponse = response.as(PlaylistResponse.class);
		Assert.assertEquals(playlistResponse.getDescription(), reqPayload.getDescription());
		Assert.assertEquals(playlistResponse.getName(), reqPayload.getName());

	}

	@Test(dependsOnMethods = "getPlaylist")
	public void deletePlaylist() {
		Response response = given().log().all().spec(SpecBuilder.deleteRequest(playlistId)).when()
				.delete("v1/playlists/{playlistId}/followers").then().log().all().extract().response();

		Assert.assertEquals(response.getStatusCode(), 200);

	}

}
