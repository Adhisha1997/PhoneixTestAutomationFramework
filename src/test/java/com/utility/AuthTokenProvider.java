package com.utility;

import static  io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.pojo.LoginCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {


	public static String AuthToken(String role){
		
	
	LoginCredentials log = null ;
	
	if (role.equalsIgnoreCase("fd")) {
		log =new LoginCredentials("iamfd", "password");
	}
	else if (role.equalsIgnoreCase("sup")) {
		log = new LoginCredentials("iamsup", "password");
	}else if (role.equalsIgnoreCase("eng")) {
		log = new LoginCredentials("iameng", "password");
	}else if (role.equalsIgnoreCase("QC"))  {
		log = new LoginCredentials("iamqc", "password");
	}

	
	
		
	String token=	given()
		   .baseUri(ConfigureManager.getProperty("QA"))
		   .accept(ContentType.JSON)
		   .contentType(ContentType.JSON)
		   .body(log)
		   .log().headers()
		   .log().body()
		   .log().uri()
		   .log().method()
	  .when()
	       .post("login")
	  .then()
	       .statusCode(200)
	       .time(lessThan(1500L))
	       .body("data.token",notNullValue())
	       .log().ifValidationFails()
	       .log().body()
	       .extract()
	       .body()
	       .jsonPath()
	       .getString("data.token");
		System.out.println("////////////////////////////////////////");
		System.out.println(token);
		return token;
	
	}
}
