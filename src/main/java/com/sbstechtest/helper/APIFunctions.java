package com.sbstechtest.helper;
//Test1
import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class APIFunctions {
	
	private static Response response;
	private static String jsonString;
	
	public String FetchAPIResponseUsingUrl(String endPoint) throws IOException {
		try {
			RestAssured.baseURI = GenericFunctions.readEnvVariables(endPoint+"_baseurl");
			RequestSpecification request = RestAssured.given();
			response = request.get(GenericFunctions.readEnvVariables(endPoint+"_suffix"));
			jsonString = response.asString();
			
		} catch (Exception e) {
			GenericFunctions.logInStackTraceReport(GenericFunctions.getClassName()+":"+GenericFunctions.getMethodName(),e.toString());
		}
		return jsonString;
	}
	
	public int getResponseCode() throws IOException {
		return response.getStatusCode();
	}
	
	public List getattributeValues(String attribute) throws IOException {
		List<Map<String, String>> attValueList = null;
		try {
		attValueList = JsonPath.from(jsonString).get("archiveAudio");
		}  catch (Exception e) {
			GenericFunctions.logInStackTraceReport(GenericFunctions.getClassName()+":"+GenericFunctions.getMethodName(),e.toString());
		}
		
		return attValueList;
	}
	
}
