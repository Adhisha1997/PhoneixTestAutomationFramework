package com.api.test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pojo.LoginCredentials;
import com.utility.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	LoginCredentials loginCredentials;
	
	@BeforeMethod(description = "Contains the payload for the API", groups = {"api","regression","smoke"})
	public void setup() {
		 loginCredentials = new LoginCredentials("iamfd", "password");
	}
	
	@Test(description = "Login for FD user", groups = {"api","regression","smoke"})
	public void loginAPITest() {
		
		
		
	given()
		  .spec(SpecUtil.requestSpec())
		  .spec(SpecUtil.requestSpec(loginCredentials))
		  
   .when()
          .post("login")
   .then()
          .spec(SpecUtil.requestSpecOk())
          .body("message", equalTo("Success"))
          .body("data.token", notNullValue())
          .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginSchema.json"));
          
		  
		
	}

}
