/**
 * 
 */
package com.restassured.StepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.restassured.pojo.SerializationAddPlace;
import com.restassured.pojo.SerializationLocation;
import com.restassured.resources.APIResources;
import com.restassured.resources.TestData;
import com.restassured.resources.Utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * @author gslab
 *
 */
public class StepDefination extends Utils{
	
	RequestSpecification req_spec;
	ResponseSpecification res_spec;
	Response response;
	TestData data=new TestData();
	static String place_id;

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String langugae,String address) throws IOException  {
				req_spec=given().spec(requestSpecification())
								.body(data.addPlacePayload(name,langugae,address)).log().all();
	}

	@When("user calls {string} with {string} Http request")
	public void user_calls_with_Http_request(String resource, String httpmethod) {
		
		APIResources resourceapi=APIResources.valueOf(resource);
		System.out.println(APIResources.valueOf(resource));
		
		res_spec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(httpmethod.equalsIgnoreCase("POST"))
			response=req_spec.when().post(resourceapi.getResource());
		else if(httpmethod.equalsIgnoreCase("GET"))
			response=req_spec.when().get(resourceapi.getResource());	
	}

	@Then("API call success with statuscode {int}")
	public void api_call_success_with_statuscode(Integer int1) {
	    Assert.assertEquals(response.getStatusCode(),200);
		
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String value) {
		/*
		 * String resp=response.asString(); JsonPath js=new JsonPath(resp);
		 * System.out.println(js.get(keyvalue));
		 */
	    Assert.assertEquals(getJsonPath(response, keyvalue),value);
	    System.out.println(keyvalue);
		System.out.println(value);
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
			//get place_id from response as json
		place_id=getJsonPath(response,"place_id");
			//set newly fetched place_id in query param. for get request
		req_spec=given().spec(requestSpecification()).queryParam("place_id",place_id);
			//by below method we get response and response store in response variable
		user_calls_with_Http_request(resource,"GET");
			//get name value from response
		String actualname=getJsonPath(response,"name");
		Assert.assertEquals(actualname,expectedname);
	}
	
	@Given("Deleteplace payload")
	public void deleteplace_payload() throws IOException {
	    req_spec=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
}
