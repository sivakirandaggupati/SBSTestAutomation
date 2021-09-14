package com.sbstechtest.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.Before;

public class BaseFunctions {
	
	public static WebDriver driver;
	public Properties prop;
	
	public WebDriver initializeDriver() throws IOException
	{

	 prop= new Properties();
	FileInputStream fis=new FileInputStream(Constants.testEnvironmentPath);

	prop.load(fis);
	String browserName=prop.getProperty("browser");

	if(browserName.equals("chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
		driver= new ChromeDriver();
		
	}
	else if (browserName.equals("firefox"))
	{
		System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");
		 driver= new FirefoxDriver();
	}

	driver.manage().timeouts().implicitlyWait(Constants.implicitWait, TimeUnit.SECONDS);

	return driver;

	}

}
