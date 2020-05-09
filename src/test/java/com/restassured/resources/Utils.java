/**
 * 
 */
package com.restassured.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author gslab
 *
 */
public class Utils {
	
	static RequestSpecification req;
	JsonPath js;
	
	public RequestSpecification requestSpecification() throws IOException {
		if(req==null) {
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		req=new RequestSpecBuilder().
				setBaseUri(getGlobalValue("baseURI"))
				.addQueryParam("key","qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON)
				.build();
		return req;
		}
		return req;
	}
	
	public static String getGlobalValue(String value) throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/test/java/com/restassured/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(value);
	}

	public String getJsonPath(Response response,String key) {
		String respString=response.asString();
		js=new JsonPath(respString);
		return js.get(key).toString();
	}
}
