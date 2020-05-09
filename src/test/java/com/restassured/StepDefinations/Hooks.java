/**
 * 
 */
package com.restassured.StepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

/**
 * @author gslab
 *
 */
public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefination step=new StepDefination();
		if(StepDefination.place_id==null) {
			step.add_place_payload_with("Sapre","Arabi","Nagpur");
			step.user_calls_with_Http_request("AddPlaceAPI","POST");
			step.verify_place_id_created_maps_to_using("Sapre", "GetPlaceAPI");
		}
	}
}
