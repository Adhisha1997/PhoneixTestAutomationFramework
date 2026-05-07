package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.utility.AuthTokenProvider;
import com.utility.ConfigureManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {

	@Test
	public  void countAPI() {
		
		given()
		       .baseUri(ConfigureManager.getProperty("QA"))
		       .accept(ContentType.JSON)
		       .contentType(ContentType.JSON)
		       .header("Authorization",AuthTokenProvider.AuthToken("fd"))
		       .log().uri()
		       .log().headers()
		       .log().body()
		       .log().method()
		.when()
		       .get("dashboard/count")
		.then()
		       .statusCode(200)
		       .log().all()
		       .time(lessThan(1000L))
		       .body("data.size()", equalTo(3))
		       .body("data.count", everyItem(greaterThanOrEqualTo(0)))
		       .body("data.label", everyItem(notNullValue()))
		       .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountSchema.json"));
		
	}
	@Test
	public void negative_MissingAuth() {
		given()
	       .baseUri(ConfigureManager.getProperty("QA"))
	       .accept(ContentType.JSON)
	       .contentType(ContentType.JSON)
	       .log().headers()
	       .log().body()
	       .log().method()
	.when()
	       .get("dashboard/count")
	.then()
	       .statusCode(401)
	       .log().all();
	       
	}
}
