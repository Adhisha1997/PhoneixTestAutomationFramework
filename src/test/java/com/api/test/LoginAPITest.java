package com.api.test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import static com.pojo.LoginCredentials.*;

import com.pojo.LoginCredentials;
import com.utility.ConfigureManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	public void loginAPITest() {
		
		//LoginCredentials loginCredentials = new LoginCredentials("iamfd", "password");
		
	given()
		  .baseUri(ConfigureManager.getProperty("QA"))
		  .accept(ContentType.JSON)
		  .contentType(ContentType.JSON)
		  .body(new LoginCredentials("iamfd", "password"))
		  .log().uri()
		  .log().headers()
		  .log().method()
		  .log().body()
   .when()
          .post("login")
   .then()
          .log().all()
          .statusCode(200)
         // .time(lessThan(1800L))
          .body("message", equalTo("Success"))
          .body("data.token", notNullValue())
          .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginSchema.json"));
          
		  
		
	}

}
