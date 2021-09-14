package com.sbstechtest.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.Status;

import junit.framework.Assert;

public class GenericFunctions {
	
	public static String readEnvVariables(String token) throws IOException {
		Properties prop;
		prop= new Properties();
		FileInputStream File =new FileInputStream(Constants.testEnvironmentPath);

		prop.load(File);
		return prop.getProperty(token);
	}
	
	public static String readTestDataVariables(String token) throws IOException {
		Properties prop;
		prop= new Properties();
		FileInputStream File =new FileInputStream(Constants.testDataPath);

		prop.load(File);
		return prop.getProperty(token);
	}
	

	public static String getMethodName() {
	    return Thread.currentThread().getStackTrace()[2].getMethodName();
	} 
	
	public static String getClassName() {
	    return Thread.currentThread().getStackTrace()[2].getClassName();
	}
	
	public static void logInReport(Status statusType, String description) throws IOException {
        GenerateExtentReports.logDef.log(statusType, description);
    }
    
    public static void logInStackTraceReport(String methodName, String description) throws IOException {
    	logInReport(Status.FAIL, methodName);
    	logInReport(Status.INFO, description);
    	Assert.assertTrue("Stack trace error", false);
    }
	

}
