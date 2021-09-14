package com.sbstechtest.pageobjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.sbstechtest.helper.GenericFunctions;
import com.sbstechtest.helper.SeleniumFunctions;

public class FrontEndPage extends SeleniumFunctions{
	
public WebDriver driver;
	
	By AudioTitle= By.xpath("//h1[@class='audiotrack__title']");
	By AudioPlayIcon= By.xpath("//span[@class='audiotrack__icon audiotrack__icon--play-pause']");
	By AudioPlayer = By.id("mod-audio-player_module-1");
	By Audio = By.xpath("//audio[@class='audio-player__media']");
	By AudioPlayPauseButton = By.xpath("//div[@data-module='audio-player_module']//button[@data-type='play-pause-button']");
	By AudioMuteButton = By.xpath("//div[@data-module='audio-player_module']//button[@data-type='volume-button']");
	By LanguageToggle = By.xpath("//div[@id='mod-dropdown_module-1'][@class='dropdown dropdown--left']/a");
	By LanguageList = By.xpath("//div[@id='mod-dropdown_module-1'][@class='dropdown dropdown--left dropdown--open']");
	String subscribeItems =  "//ul[@class='podcast-subscribe__list']/li/a[text()='$var0']";
	String AudioStepBackForward = "//div[@data-module='audio-player_module']//button[@aria-label='Step $var0 20 seconds']";
	
	
	public FrontEndPage(WebDriver driver) {
		super(driver);
	}


	public void audioTrackTitleValidation(String title) throws IOException {
		if (title.equalsIgnoreCase("expected")) {
			title = GenericFunctions.readTestDataVariables("AudioTitle");
		}
		String actualTitle = getInnerText("Audio Title",AudioTitle).trim();
		if (title.equals(actualTitle)) {
			logInReport(Status.PASS, "Audio title displayed as expected.");
		}
		else {
			logInReport(Status.FAIL, "Audio title NOT displayed as expected.Expected title: "+title+". Actual title: "+actualTitle);
		}
		
	}

	public void validateSubscribeDropDownItems(String itemName) throws IOException {
		if (isElementPresent(setXpath(subscribeItems, itemName))) {
			logInReport(Status.PASS, itemName+" displayed in Subscribe drop down.");
		}
		else {
			logInReport(Status.FAIL, itemName+" NOT displayed in Subscribe drop down.");
		}
	}

	public void clickAudioIcon() throws IOException {
		click("Audio play icon",AudioPlayIcon);
	}
	
	public void clickAudioPlayPause() throws IOException {
		click("Audio play pause button",AudioPlayPauseButton);
	}
	
	public void clickAudioMute() throws IOException {
		click("Audio mute toggle",AudioMuteButton);
	}


	public void audioPlayerLocation() throws IOException {
		if (explicitWait(AudioPlayer, 10)) {
			logInReport(Status.PASS, "Audio player is  displayed at bottom.");
		}
		else {
			logInReport(Status.FAIL, "Audio player is NOT displayed at bottom.");
		}
	}
	
	public void audioPlayerStatus(String status) throws IOException {
		if (audioPlayingStatus(status,Audio)) {
			logInReport(Status.PASS, "Audio player is "+status+"d.");
		}
		else {
			logInReport(Status.FAIL, "Audio player is NOT"+status+"d.");
		}
		
		
	}


	public String audioPlayingCurrentTime() throws IOException {
		return audioPlayingCurrentTime(Audio);
		
	}

	public void clickFwdRWdAudioButtons(String arg1) throws IOException {
		click("Audio 20s "+arg1+" button",setXpath(AudioStepBackForward, arg1));
	}
	
	public void validateFwdRWdAudioButtons(String arg1) throws IOException {
		float beforeClick = Float.parseFloat(audioPlayingCurrentTime());
		clickFwdRWdAudioButtons(arg1);
		float afterClick = Float.parseFloat(audioPlayingCurrentTime());
		float skippedSeconds = Math.abs(afterClick-beforeClick);
		if (arg1.equalsIgnoreCase("forward")) {
			if (afterClick>beforeClick && skippedSeconds>19.50 && skippedSeconds<20.50) {
				logInReport(Status.PASS, "Audio skipped "+arg1+" by 20s.");
			}
			else {
				logInReport(Status.FAIL, "Audio did not skip "+arg1+" by 20s.Skipped seconds were "+skippedSeconds+". Audio time before clicking forward button "+beforeClick+". After clicking forward button "+afterClick);
			}
		}
		else if (arg1.equalsIgnoreCase("back")) 	{
				if (afterClick<beforeClick && skippedSeconds>19.50 && skippedSeconds<20.50) {
					logInReport(Status.PASS, "Audio skipped "+arg1+" by 20s.");
				}
				else {
					logInReport(Status.FAIL, "Audio did not skip "+arg1+" by 20s.Skipped seconds were "+skippedSeconds+". Audio time before clicking forward button "+beforeClick+". After clicking forward button "+afterClick);
				}
		}
		
	}


	public void clickLanguageToggle() throws IOException {
		click("Language toggle button",LanguageToggle);
	}
	
	public void validateLanguageListDisplay(String condition) throws IOException {
		validateElementDisplayStatus("Langauge List",LanguageList,condition);
	}

}
