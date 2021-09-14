package com.sbstechtest.helper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class SeleniumFunctions {
	
public WebDriver driver;
private WebDriverWait explicitWait;
private static final String IS_PRESENT = " is present.";
private static final String IS_NOT_PRESENT = " is NOT present.";

public SeleniumFunctions(WebDriver driver) {
	this.driver=driver;
	explicitWait = new WebDriverWait(driver, 30);
}



	public void invokeTestApplication(String token) throws IOException {

		String testUrl = GenericFunctions.readEnvVariables(token);
		if (testUrl== null) {
			testUrl=token;
		}
		openUrl(testUrl);
	}
	
	 /** Pauses execution for not more than 2 sec */
    public void sleep(int millisec) {
        if (millisec > 2000) {
            millisec = 2000;
        }
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Waits for visibility of locator for time length specified
     *
     * @throws IOException
     */
    protected boolean explicitWait(By locator, int waitSecs) {
        boolean eleFound = true;
        try {
            WebDriverWait softWait;
            softWait = new WebDriverWait(driver, waitSecs);
            softWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            eleFound = false;
        }
        return eleFound;
    }

    /** Open page with given URL */
    protected void openUrl(String url) {
        driver.get(url);
    }

    /** Maximize the browser */
    protected void maximizeBrowser() {
        driver.manage().window().maximize();
    }

    /** Find element using given locator */
    protected WebElement find(String fieldname, By locator) throws IOException {
        waitForVisibilityOf(locator, fieldname);
        return driver.findElement(locator);
    }

    /** Click on element with given locator when its visible 
     * @throws IOException */
    protected void click(String fieldname, By locator) throws IOException   {
    	try {
        find(fieldname, locator).click();
    	} catch (Exception e) {
    		logInStackTraceReport(GenericFunctions.getClassName()+":"+GenericFunctions.getMethodName(),e.toString());
		}
    }

    /** Type given text into element with given locator 
     * @throws IOException */
    protected void type(String fieldname, String text, By locator) throws IOException {
    	try {
    		find(fieldname, locator).sendKeys(text);
        	} catch (Exception e) {
        		logInStackTraceReport(GenericFunctions.getClassName()+":"+GenericFunctions.getMethodName(),e.toString());
    		}
    }

    /** Return inner text of given locator */
    protected String getInnerText(String fieldname, By locator) throws IOException {
    	String innerText = null;
    	try {
    		innerText = find(fieldname, locator).getText();
        	} catch (Exception e) {
        		logInStackTraceReport(GenericFunctions.getClassName()+":"+GenericFunctions.getMethodName(),e.toString());
    		}
    	return innerText;
    }
        

    
    public boolean waitForVisibilityOf(By locator, String fieldname) throws IOException {
        boolean isDisplayed = explicitWait(locator, 3);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: \"end\",inline: \"center\",behavior: \"smooth\"});", driver.findElement(locator));
            isDisplayed = driver.findElement(locator).isDisplayed();


        } catch (Exception e) {
            logInReport(Status.FAIL, fieldname + " field not displayed.");
            logInReport(Status.INFO, e.toString());
        }
        Assert.assertTrue(fieldname + " element missing.", isDisplayed);


        return isDisplayed;
    }



	public void waitForElementToBeClickable(By locator, String fieldname) throws IOException {
        boolean isEnabled = explicitWait(locator, 3);
        try {
        explicitWait(locator,5);
            explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
            isEnabled = true;
        } catch (Exception e) {
            logInReport(Status.FAIL, fieldname + " field not clickable.");
            logInReport(Status.INFO, e.toString());
        }
        Assert.assertTrue(fieldname + " element not clickable.", isEnabled);
    }

    public By setXpath(String xpathExp, Object... args) {
        for (int text = 0; text < args.length; text++) {
            xpathExp = xpathExp.replace("$var" + text, args[text].toString());
        }

        return By.xpath(xpathExp);
    }

    public boolean isElementPresent(By locator) {
        boolean present;
        try {
            driver.findElement(locator);
            present = true;
        } catch (NoSuchElementException e) {
            present = false;
        }
        return present;
    }

    public boolean switchTab(String tabUrl) {
        boolean newTab = false;
        sleep(2000);
        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
            if (driver.getCurrentUrl().contains(tabUrl)) {
                newTab = true;
                break;
            }

        }
        return newTab;
    }

    public boolean validateElementDisplayed(By locator) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        boolean exists;
        try {
            exists = driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            exists = false;
        }
        //driver.manage().timeouts().implicitlyWait(BaseUtils.getImplicitWait(), TimeUnit.SECONDS);
        return exists;
    }


    public void validateElementDisplayStatus(String eleDesc, By locator, String status) throws IOException {
        if (status.toUpperCase().contains("NOT")) {
            if (validateElementDisplayed(locator)) {
                logInReport(Status.FAIL, eleDesc + IS_PRESENT);
            } else {
                logInReport(Status.PASS, eleDesc + IS_NOT_PRESENT);
            }
        } else if (validateElementDisplayed(locator)) {
            logInReport(Status.PASS, eleDesc + IS_PRESENT);
        } else {
            logInReport(Status.FAIL, eleDesc + IS_NOT_PRESENT);
        }
    }

    public void validateInnerTextValue(String eleDesc, By locator, String value) throws IOException {
        String actualValue = getInnerText(eleDesc, locator);
        if (value.equalsIgnoreCase("blank")) {
            if (actualValue.equals("")) {
                logInReport(Status.PASS, eleDesc + " is blank");
            } else {
                logInReport(Status.FAIL, eleDesc + " is NOT blank");
            }
        } else if (actualValue.equals(value)) {
            logInReport(Status.PASS, eleDesc + " text validated as expected. ");
        } else {
            logInReport(Status.FAIL, eleDesc + " text does NOT match expected value- " + value + ".The actual text displayed is " + actualValue);
        }
    }
    
    /** Return inner text of given locator */
    protected String getAttribute(String fieldname, By locator, String attributeName) throws IOException {
        return find(fieldname, locator).getAttribute(attributeName);
    }
    
    public void logInReport(Status statusType, String description) throws IOException {
    	GenericFunctions.logInReport(statusType, description);
    }
    
    public void logInStackTraceReport(String methodName, String description) throws IOException {
    	GenericFunctions.logInStackTraceReport(methodName, description);
    }
    
    
    public boolean audioPlayingStatus(String status, By locator) throws IOException {
    	boolean audioStatus;
    	if (status.toUpperCase().contains("PAUSE")) {
    		audioStatus = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].paused;", driver.findElement(locator));
    	}
    	else if (status.toUpperCase().contains("PLAY")) {
    		audioStatus = !(Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].paused;", driver.findElement(locator));
    	}
    	else if (status.toUpperCase().equals("MUTE")) {
    		audioStatus = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].muted;", driver.findElement(locator));
    	}
    	else if (status.toUpperCase().contains("UNMUTE")) {
    		audioStatus = !(Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].muted;", driver.findElement(locator));
    	}
    	else {
    		audioStatus = false;
    		logInReport(Status.FAIL, status +" does not match any audio status.");
    	}
    	return audioStatus;
    }
    
    public String audioPlayingCurrentTime(By locator) throws IOException {
         return ((JavascriptExecutor) driver).executeScript("return arguments[0].currentTime;", driver.findElement(locator)).toString();
    }
    
    public void getcoordinates(By locator) throws IOException {
    
    WebElement Image = driver.findElement(locator);
    //Get element width.
    int ImageWidth = Image.getSize().getWidth();
    int xcor = Image.getLocation().getX();
    System.out.println("Image width Is "+ImageWidth+" pixels");
    System.out.println("Image width Is "+xcor+" pixels");
    System.out.println(getAttribute("Some element", locator, "offsetTop"));

    //Get element height.
    int ImageHeight = Image.getSize().getHeight();  
    int ycor = Image.getLocation().getY();
    System.out.println("Image height Is "+ImageHeight+" pixels");
    System.out.println("Image width Is "+ycor+" pixels");
    }
}


