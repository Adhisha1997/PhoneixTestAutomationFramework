package com.api.test;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import com.pojo.LoginCredentials;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.*;

public class LoginAPITest {
	
	@Test
	public void loginAPITest() {
		
		LoginCredentials loginCredentials = new LoginCredentials("iamfd", "password");
		
	given()
		  .baseUri("http://64.227.160.186:9000/v1")
		  .accept(ContentType.JSON)
		  .contentType(ContentType.JSON)
		  .body(loginCredentials)
		  .log().uri()
		  .log().headers()
		  .log().method()
		  .log().body()
   .when()
          .post("login")
   .then()
          .log().all()
          .statusCode(200)
          .time(lessThan(1500L))
          .body("message", equalTo("Success"))
          .body("data.token", notNullValue())
          .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginSchema.json"));
          
		  
		
	}

}
