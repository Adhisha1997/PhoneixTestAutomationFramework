package com.api.test;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pojo.Customer;
import com.pojo.CustomerAddress;
import com.pojo.CustomerProduct;
import com.pojo.Payload;
import com.pojo.Problems;
import com.utility.AuthTokenProvider;
import com.utility.ConfigureManager;
import com.utility.DateTimeUtil;
import com.utility.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.http.Header;

public class CreateJob {

	Payload payload;
	Header header ;
	
	@BeforeMethod(description = "Contains the paylod", groups = {"api","regression","smoke"})
	public void setup() {
		Customer customer = new Customer("QA", "Tester", "7777859698", "", "qatester@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("1 D", "Street", "Oxford Road", "New", "Oxford", "400095", "UK", "OxfordShire");
		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.timeAndDate(10), "268404202539889","268404202539889", "268404202539889",DateTimeUtil.timeAndDate(5),3,3);
		Problems problems = new Problems(2, "QA");
		List<Problems> problemsArray= new ArrayList<Problems>();
		problemsArray.add(problems);
		 payload = new Payload(0, 2, 1, 2, customer, customerAddress, customerProduct, problemsArray);
		
		 header = new Header("Authorization", AuthTokenProvider.AuthToken("fd"));
	}
	
	
	@Test(description = "Post request for creating Jobs", groups = {"api","regression","smoke"})
	public void CreateJobAPI() {
		
		
		
		given()
		  .spec(SpecUtil.requestSpec())
		  .body(payload)
		  .header(header)
		.when()
		  .post("/job/create")
		.then()
		  .spec(SpecUtil.requestSpecOk());
	}
	
}
