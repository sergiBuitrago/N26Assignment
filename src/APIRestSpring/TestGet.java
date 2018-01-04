package APIRestSpring;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.gson.Gson;
 
public class TestGet {
 
	@Test
	public void GetStadistics()
	{   
		
		RestAssured.baseURI ="http://localhost:8080/APIRest/";
		
		RequestSpecification request = RestAssured.given();
		
		Gson gson = new Gson();
		long currentTime = System.currentTimeMillis();
		Transaction example = new Transaction(currentTime,12.3);
		request.body(gson.toJson(example));
		request.post("/transaction");
		
		currentTime = System.currentTimeMillis();
		example = new Transaction(currentTime,15);
		request.body(gson.toJson(example));
		request.post("/transaction");
		
		currentTime = System.currentTimeMillis();
		example = new Transaction(currentTime,2.7);
		request.body(gson.toJson(example));
		request.post("/transaction");

		Response responseGET = request.request(Method.GET, "/stadistics");
		String responseBody = responseGET.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
		Stadistics stadistics = gson.fromJson(responseBody, Stadistics.class);
		assertEquals(stadistics.count, 3);
		assertEquals(stadistics.max, 15);
		assertEquals(stadistics.min, 2.7);
		assertEquals(stadistics.sum, 30);
		assertEquals(stadistics.avg, 10);
 
	}
	@Test
	public void GetStadistics2()
	{   
		
		RestAssured.baseURI ="http://localhost:8080/APIRest/";
		
		RequestSpecification request = RestAssured.given();
		
		Gson gson = new Gson();
		long currentTime = System.currentTimeMillis();
		Transaction example = new Transaction((currentTime-62000),12.3);
		request.body(gson.toJson(example));
		request.post("/transaction");
		
		currentTime = System.currentTimeMillis();
		example = new Transaction(currentTime,15);
		request.body(gson.toJson(example));
		request.post("/transaction");
		
		currentTime = System.currentTimeMillis();
		example = new Transaction(currentTime,5);
		request.body(gson.toJson(example));
		request.post("/transaction");

		Response responseGET = request.request(Method.GET, "/stadistics");
		String responseBody = responseGET.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
		Stadistics stadistics = gson.fromJson(responseBody, Stadistics.class);
		assertEquals(stadistics.count, 2);
		assertEquals(stadistics.max, 15);
		assertEquals(stadistics.min, 5);
		assertEquals(stadistics.sum, 20);
		assertEquals(stadistics.avg, 10);
 
	}
 
}