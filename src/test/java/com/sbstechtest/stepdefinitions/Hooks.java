package com.sbstechtest.stepdefinitions;

import java.io.IOException;

import org.junit.Assume;

import com.aventstack.extentreports.Status;
import com.sbstechtest.helper.GenerateExtentReports;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends GenerateExtentReports{
	
	private static boolean isNewTest = true;
    private static boolean isEndTest, skipFlag;

    @Before(order = 1)
    public void application(Scenario scenario) throws IOException {

        if (extent == null) {
            GenerateExtentReports.extentReport();
        }

        String str = scenario.getId();
        String[] arrOfStr = str.split(";", 2);
        if (isNewTest) {
            isNewTest = false;
            scenarioDef = extent.createTest(arrOfStr[0].toUpperCase());
        

        if (driver != null) 
            driver.quit();
            skipFlag = false;
        }
    }

    @Before(value = "@TearDown",order = 2)
    public void resetNewTestStatus(Scenario scenario) throws IOException {
        isEndTest = true;
        if (driver != null) {
            driver.quit();
        }
        skipFlag=false;
    }


    @Before(order = 3)
    public void creatingNode(Scenario scenario) throws IOException {

        stepDef=scenarioDef.createNode(scenario.getName());

        if(skipFlag) {
        	stepDef.log(Status.SKIP, "Scenario skipped due to above failure.");
            Assume.assumeTrue(false);
        }
    }

	@Before(value="@OpenBrowser", order=4)
	public void openDriver() throws IOException
	{
	  initializeDriver();
	}

  @After(order = 2)
  public void skipOnFailure(Scenario scenario) throws IOException {
      if (scenario.isFailed()) {
          skipFlag = true;
          if (logDef == null) {
        	  logDef.log(Status.FAIL, "Failed in this scenario. Check the first step definition for mapping.");
          } 
      }
      if (isEndTest) {
          skipFlag = false;
      }
  }

  @After(value = "@TearDown",order = 1)
  public void teardown(Scenario scenario) {
      isNewTest = true;
      if (driver != null) {
          driver.quit();
      }
      GenerateExtentReports.flushReport();
  }

}
