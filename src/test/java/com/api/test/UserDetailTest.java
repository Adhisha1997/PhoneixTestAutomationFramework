package com.api.test;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.utility.AuthTokenProvider.*;
import com.utility.ConfigureManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class UserDetailTest {

	@Test

	public void userDetails() throws IOException {

		
		Header auth_header = new Header("Authorization",AuthToken("fd"));
		// ConfigureManager configMang = new ConfigureManager();//Make the method static
		// so no need to create object with class name method can be called

		given().baseUri(ConfigureManager.getProperty("QA")).accept(ContentType.JSON).contentType(ContentType.JSON)
				.header(auth_header).log().uri().log().method().log().headers().when().get("userdetails").then().log()
				.all().statusCode(200).body("message", equalTo("Success"))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailSchema.json"))
				.time(lessThan(1500L));

	}
}
