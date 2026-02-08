package com.ApiAutomation.Spotify.Authorization;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.Instant;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TokenManager {

	private static Response generateToken() {
		String refreshToken = "AQC_iKhfPmzxX1hxbr_oR8acSn5K2WUOu3MUOsYdoCZeWGW5omWAQ4K-4h7jNAlq5e1VtKywIkmAE3LfkKRzHSF78iuICiRCHgMWv7Wm03lGawXuvmijgixWbr3pWL6kUSE";
		Map<String, String> formParam = new HashMap<String, String>();
		formParam.put("grant_type", "refresh_token");
		formParam.put("client_id", "052052720a9640848b3ecd43751afaf2");
		formParam.put("refresh_token", refreshToken);
		formParam.put("client_secret", "6bc327b0dfe742d9bfe7506364eae7c3");

		Response response = given().baseUri("https://accounts.spotify.com").log().all()
				.header("Content-type", "application/x-www-form-urlencoded").formParams(formParam).when()
				.post("/api/token").then().log().all().extract().response();

		return response;

	}

	private static String accessToken;
	private static Instant expiryTime;

	public synchronized static String getAccessToken() {

		try {
			if (accessToken == null || Instant.now().isAfter(expiryTime)) {
				System.out.println("===========Renewing the token=================");
				Response response = generateToken();

				JsonPath jsonPath = new JsonPath(response.asString());
				accessToken = response.path("access_token");
				int expiryTimeinSeconds = response.path("expires_in");

				expiryTime = Instant.now().plus(expiryTimeinSeconds);
				System.out.println("Current Time::" + Instant.now() + " expiryTime::" + expiryTime);

			} else {
				System.out.println("============ReUsing the token yet!!!!!!==================");
			}
		} catch (Exception e) {
			throw new RuntimeException("Abort!!!  Unable to extract the token");
		}

		return accessToken;

	}

}
