/**
 * 
 */
package com.restassured.resources;

import java.util.ArrayList;
import java.util.List;

import com.restassured.pojo.SerializationAddPlace;
import com.restassured.pojo.SerializationLocation;

/**
 * @author gslab
 *
 */
public class TestData {

	public SerializationAddPlace addPlacePayload(String name,String language,String address) {
		SerializationAddPlace serialize=new SerializationAddPlace();
		serialize.setAddress(address);
		serialize.setAccuracy(40);
		serialize.setLanguage(language);
		serialize.setName(name);
		serialize.setPhone_number("(+91) 983 893 3937");
		serialize.setWebsite("http://www.google.com");
		
		//Types
				List<String> list=new ArrayList<String>();
				list.add("Shoe park");
				list.add("Shoe");
				
				serialize.setTypes(list);
				
				//Location
				SerializationLocation l=new SerializationLocation();
				l.setLat(-78.98);
				l.setLng(55.45);
				
				serialize.setLocation(l);
				return serialize;
				 
	}
	
	public String deletePlacePayload(String placeid) {
		return "{\r\n    \"place_id\":\""+placeid+"\"\r\n}\r\n";
	}
}
