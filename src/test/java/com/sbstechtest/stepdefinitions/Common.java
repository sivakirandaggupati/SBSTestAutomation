package com.sbstechtest.stepdefinitions;

import com.aventstack.extentreports.GherkinKeyword;
import com.sbstechtest.helper.GenerateExtentReports;
import com.sbstechtest.pageobjects.FrontEndPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Common extends GenerateExtentReports{
	

	
	@Given("^Test complete$")
	public void test_complete() throws Throwable {
		logDef=stepDef.createNode(new GherkinKeyword("Given"), "Test complete.");
	}
	
	@Then("^Close the test browser$")
	public void close_test_browser() throws Throwable {
		logDef=stepDef.createNode(new GherkinKeyword("Then"), "Close the test browser.");
	}

	@Given("^User is on test page$")
	public void user_is_on_test_page() throws Throwable {
		logDef=stepDef.createNode(new GherkinKeyword("Then"), "User is on test page.");
	}

}