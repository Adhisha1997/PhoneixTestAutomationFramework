package com.api.test;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.utility.AuthTokenProvider.*;
import com.utility.ConfigureManager;
import com.utility.SpecUtil;

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

		given().spec(SpecUtil.requestSpec())
				.header(auth_header).when().get("userdetails").then().log()
				.all().spec(SpecUtil.requestSpecOk()).body("message", equalTo("Success"))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailSchema.json"))
				;

	}
}
