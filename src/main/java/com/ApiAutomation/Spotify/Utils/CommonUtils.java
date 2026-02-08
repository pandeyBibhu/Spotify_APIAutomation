package com.ApiAutomation.Spotify.Utils;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {

	private static final Logger log =
            LoggerFactory.getLogger(CommonUtils.class);
	
	public static Response validateSchema(Response response, String schemaPath) {
         System.out.println("SchemaPath");
		 response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
		 
		 log.info("Schema validation is passed for path::::"+schemaPath);
		 return response;

	}
	

}
