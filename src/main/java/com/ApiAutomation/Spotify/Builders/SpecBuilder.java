package com.ApiAutomation.Spotify.Builders;

import org.apache.http.client.methods.RequestBuilder;

import com.ApiAutomation.Spotify.Authorization.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecBuilder {

	public static RequestSpecification postRequest(Object reqPayload, String userId) {

		return new RequestSpecBuilder().setBaseUri("https://api.spotify.com/")
				.addHeader("Authorization", "Bearer " + TokenManager.getAccessToken())
				.addHeader("Content-type", "application/json").addPathParam("userId", userId).setBody(reqPayload)
				.build();
	}

	public static RequestSpecification getRequest(String playlistId) {
		return new RequestSpecBuilder().setBaseUri("https://api.spotify.com/")
				.addHeader("Authorization", "Bearer " + TokenManager.getAccessToken()).setContentType(ContentType.JSON)
				.addPathParam("playlistId", playlistId).build();
	}
	
	public static RequestSpecification deleteRequest(String playlistId) {
		return new RequestSpecBuilder().setBaseUri("https://api.spotify.com/").addHeader("Authorization", "Bearer "+TokenManager.getAccessToken())
				.setContentType(ContentType.JSON).addPathParam("playlistId", playlistId).build();
	}
}
