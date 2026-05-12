package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.utility.AuthTokenProvider;
import com.utility.ConfigureManager;
import com.utility.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {

	@Test
	public  void countAPI() {
		
		given()
		       .spec(SpecUtil.requestSpec())
		       .header("Authorization",AuthTokenProvider.AuthToken("fd"))
		     
		.when()
		       .get("dashboard/count")
		.then()
		       .spec(SpecUtil.requestSpecOk())
		       .body("data.size()", equalTo(3))
		       .body("data.count", everyItem(greaterThanOrEqualTo(0)))
		       .body("data.label", everyItem(notNullValue()))
		       .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountSchema.json"));
		
	}
	@Test
	public void negative_MissingAuth() {
		given()
		.spec(SpecUtil.requestSpec())
	.when()
	       .get("dashboard/count")
	.then()
	     .spec(SpecUtil.requestSpec_StatusCodeChange(401));
	       
	}
}
