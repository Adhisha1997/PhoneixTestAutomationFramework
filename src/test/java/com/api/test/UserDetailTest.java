package com.api.test;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.utility.ConfigureManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class UserDetailTest {

	
	@Test
	
	public void userDetails() throws IOException {
		
		Header auth_header = new Header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwiZmlyc3RfbmFtZSI6ImZkIiwibGFzdF9uYW1lIjoiZmQiLCJsb2dpbl9pZCI6ImlhbWZkIiwibW9iaWxlX251bWJlciI6Ijg4OTk3NzY2NTUiLCJlbWFpbF9pZCI6Im1hcmtAZ21haWwuY29tIiwicGFzc3dvcmQiOiI1ZjRkY2MzYjVhYTc2NWQ2MWQ4MzI3ZGViODgyY2Y5OSIsInJlc2V0X3Bhc3N3b3JkX2RhdGUiOm51bGwsImxvY2tfc3RhdHVzIjowLCJpc19hY3RpdmUiOjEsIm1zdF9yb2xlX2lkIjo1LCJtc3Rfc2VydmljZV9sb2NhdGlvbl9pZCI6MSwiY3JlYXRlZF9hdCI6IjIwMjEtMTEtMDNUMDg6MDY6MjMuMDAwWiIsIm1vZGlmaWVkX2F0IjoiMjAyMS0xMS0wM1QwODowNjoyMy4wMDBaIiwicm9sZV9uYW1lIjoiRnJvbnREZXNrIiwic2VydmljZV9sb2NhdGlvbiI6IlNlcnZpY2UgQ2VudGVyIEEiLCJpYXQiOjE3Nzc0MDk2MDJ9.19h4j7m4284-xyCQMMVfivTUFv-qc-caIzQj23atHBw");
		//ConfigureManager configMang = new ConfigureManager();//Make the method static so no need to create object with class name method can be called
		
		given()
		  .baseUri(ConfigureManager.getProperty("BASEURI"))
		  .accept(ContentType.JSON)
		  .contentType(ContentType.JSON)
		  .header(auth_header)
		  .log().uri()
		  .log().method()
		  .log().headers()
      .when()
          .get("userdetails")
      .then()
          .log().all()
          .statusCode(200)
          .body("message", equalTo("Success"))
          .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailSchema.json"))
          .body("data.id", equalTo(4))
          .time(lessThan(1500L));
	
		  		
		
	}
}
