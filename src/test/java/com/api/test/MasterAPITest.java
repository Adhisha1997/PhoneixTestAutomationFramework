package com.api.test;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.utility.AuthTokenProvider;
import static com.utility.ConfigureManager.*;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class MasterAPITest {

	
	@Test
	public void masterAPI() {
		
		given()
		       .baseUri(getProperty("QA"))
		     
		      .accept(ContentType.JSON)
		       .contentType("")
		       .header("Authorization", AuthTokenProvider.AuthToken("QC"))
		       .log().uri()
		       .log().headers()
		       .log().method()
		       
	   .when()
	          .post("master")
	   .then()
	          .log().all()
	          .statusCode(200)
	          .time(lessThan(1000L))
	          .body("data.mst_oem.size()", equalTo(2))
	          .body("data.mst_model.id", everyItem(notNullValue())) ;
		       
	}
}
