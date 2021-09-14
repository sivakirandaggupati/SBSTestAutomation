package com.sbstechtest.stepdefinitions;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.sbstechtest.helper.APIFunctions;
import com.sbstechtest.helper.GenerateExtentReports;
import com.sbstechtest.helper.GenericFunctions;
import com.sbstechtest.pageobjects.FrontEndPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class BackEnd extends GenerateExtentReports{
	

	APIFunctions apifunctions = new APIFunctions();
	private static String jsonString;
	

	@Given("I get response of \"([^\"]*)\" end point")
	public void  get_JSON_response_from_end_point(String endPoint) throws Throwable {
		logDef=stepDef.createNode(new GherkinKeyword("Given"), "I get response of "+endPoint+" end point.");		
	    apifunctions.FetchAPIResponseUsingUrl(endPoint);
	}
	
	@And("I validate response is processed successfully")
	public void  validate_response_code() throws Throwable {
		logDef=stepDef.createNode(new GherkinKeyword("Given"), "I validate response is processed successfully.");
		if(apifunctions.getResponseCode()==200) {
			logDef.log(Status.PASS,"Request processed succssfully." );
		}
		else {
			logDef.log(Status.FAIL,("Request NOT processed successfully") );
		}
		
	}
	
	@And("I get all \"([^\"]*)\" mp3 audio files")
	public void  get_response_value(String attribute) throws Throwable {
		logDef=stepDef.createNode(new GherkinKeyword("Given"), "I get all "+attribute+" mp3 audio files.");
		List<Map<String, String>> attValueList  =  apifunctions.getattributeValues(attribute);
		for (Map<String, String> item: attValueList) {
			logDef.log(Status.INFO,item.get("mp3") );
		}		
		
	}
	
}