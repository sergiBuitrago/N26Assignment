package APIRestSpring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import APIRestSpring.Transaction;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
 
public class TestPost {
 
	@Test
	public void PostTransaction200()
	{   
		RestAssured.baseURI ="http://localhost:8080/APIRest/";
		
		RequestSpecification request = RestAssured.given();
		
		Gson gson = new Gson();
		long currentTime = System.currentTimeMillis();
		Transaction example = new Transaction(currentTime,12.3);

		request.body(gson.toJson(example));
		Response response = request.post("/transaction");
	 
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
//		String successCode = response.jsonPath().get("SuccessCode");
//		assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
 
	}
	@Test
	public void PostTransaction204()
	{   
		RestAssured.baseURI ="http://localhost:8080/APIRest/";
		RequestSpecification request = RestAssured.given();
	 
		Gson gson = new Gson();
		long currentTime = System.currentTimeMillis();
		Transaction example = new Transaction(currentTime-61000,12.3);
		
		
	
		request.body(gson.toJson(example));
		Response response = request.post("/transaction");
	 
		int statusCode = response.getStatusCode();
		System.out.println("response code =>  " + statusCode);
		System.out.println("Current Timestamp=>  " + currentTime);
		System.out.println("60sec Timestamp=>  " + (currentTime-60000));
		System.out.println("Transaction Timestamp=>  " + example.getTimestamp());
		assertEquals(statusCode, 204);
//		String successCode = response.jsonPath().get("SuccessCode");
//		assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
 
	}
}
