package com.utility;

import org.hamcrest.Matchers;

import com.pojo.LoginCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	
	public static RequestSpecification requestSpec() {
		
		RequestSpecification req =  new RequestSpecBuilder()
				                    .setBaseUri(ConfigureManager.getProperty("QA"))
				                    .setAccept(ContentType.JSON)
				                    .setContentType(ContentType.JSON)
				                    .log(LogDetail.URI)
				                    .log(LogDetail.HEADERS)
				                    .log(LogDetail.METHOD)
				                    .log(LogDetail.BODY)
				                    .build();
				return req;                       
		 	}
	
	public static RequestSpecification requestSpec(LoginCredentials loginCredentials) {
	RequestSpecification req =  new RequestSpecBuilder()
			                    .setBaseUri(ConfigureManager.getProperty("QA"))
			                    .setAccept(ContentType.JSON)
			                    .setContentType(ContentType.JSON)
			                    .setBody(loginCredentials)
			                    .log(LogDetail.URI)
			                    .log(LogDetail.HEADERS)
			                    .log(LogDetail.METHOD)
			                    .log(LogDetail.BODY)
			                    .build();
			return req;                       
	 	}

	
	public static ResponseSpecification requestSpecOk() {
		
	ResponseSpecification response =new ResponseSpecBuilder()
	 .expectContentType(ContentType.JSON)
	 .expectStatusCode(200)
	 .expectResponseTime(Matchers.lessThan(2000L))
	 .log(LogDetail.ALL)
	 .build();
	
	return response;
			
	
	}
	

	public static ResponseSpecification requestSpec_StatusCodeChange(int statusCode) {
		
		ResponseSpecification response =new ResponseSpecBuilder()
		
		 .expectStatusCode(statusCode)
		 .expectResponseTime(Matchers.lessThan(2000L))
		 .log(LogDetail.ALL)
		 .build();
		
		return response;
				
		
		}
		
}
