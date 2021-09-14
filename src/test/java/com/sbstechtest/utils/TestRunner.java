package com.sbstechtest.utils;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/com/sbstechtest/features",
		glue = {"com/sbstechtest/stepdefinitions"}
		//tags = {"@API"}
		)
public class TestRunner {
	
}
