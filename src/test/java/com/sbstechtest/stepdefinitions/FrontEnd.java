package com.sbstechtest.stepdefinitions;

import com.aventstack.extentreports.GherkinKeyword;
import com.sbstechtest.helper.GenerateExtentReports;
import com.sbstechtest.pageobjects.FrontEndPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FrontEnd extends GenerateExtentReports{
	
	FrontEndPage sbsTestPage = new FrontEndPage(driver);

	@Given("^User invokes test page$")
	public void user_invokes_test_page() throws Throwable {
		logDef=stepDef.createNode(new GherkinKeyword("Given"), "User invokes test page.");
		sbsTestPage.invokeTestApplication("testUrl");
	}
	

	@Then("^Title of audio track should be displayed as \"([^\"]*)\"$")
	public void audio_title_track_should_be_displayed_as(String arg1) throws Throwable {
		logDef=stepDef.createNode(new GherkinKeyword("Then"), "Title of audio track should be displayed as "+arg1+".");
		sbsTestPage.audioTrackTitleValidation(arg1);
	}

	@Then("^Subscribe drop down should display \"([^\"]*)\" podcast$")
	public void subscribe_drop_down_should_display_podcast(String arg1) throws Throwable {
		logDef=stepDef.createNode(new GherkinKeyword("Then"), "Subscribe drop down should display "+arg1+" podcast.");
		sbsTestPage.validateSubscribeDropDownItems(arg1);
	}

	@Given("^User clicks on audio icon button$")
	public void user_clicks_play_button_for_audio() throws Throwable {
		logDef=stepDef.createNode(new GherkinKeyword("Given"), "User clicks on audio icon button.");
		sbsTestPage.clickAudioIcon();
	}

	@Then("^Audio player should be launched at the bottom of the screen$")
	public void audio_player_should_be_launched_at_the_bottom_of_the_screen() throws Throwable {
		logDef=stepDef.createNode(new GherkinKeyword("Then"), "Audio player should be launched at the bottom of the screen.");
		sbsTestPage.audioPlayerLocation();
	}

	@Given("^User clicks \"([^\"]*)\" button for audio$")
	public void user_clicks_button_for_audio(String arg1) throws Throwable {
		logDef=stepDef.createNode(new GherkinKeyword("Then"), "User clicks "+arg1+" button for audio.");
		if (arg1.toLowerCase().contains("mute")) {
			sbsTestPage.clickAudioMute();
		}
		else {
			sbsTestPage.clickAudioPlayPause();
		}
	}

	@Then("^Audio player should \"([^\"]*)\" the audio$")
	public void audio_player_should_the_audio(String arg1) throws Throwable {
		logDef=stepDef.createNode(new GherkinKeyword("Then"), "Audio player should "+arg1+" the audio.");
		sbsTestPage.audioPlayerStatus(arg1);
	}
	
	@Then("^Clicks 20s \"([^\"]*)\" button the audio should step [fb][ao][cr][kw][a]?[r]?[d]? by 20s$")  
	public void audio_clip_should_skip_to_s(String arg1) throws Throwable {
		logDef=stepDef.createNode(new GherkinKeyword("Then"), "Audio clip should skip to 20s "+arg1+".");
		sbsTestPage.validateFwdRWdAudioButtons(arg1);
	}

	@Then("^Language list should \"([^\"]*)\" under language toggle button$")
	public void language_list_should_not_display_under_language_toggle_button(String arg1) throws Throwable {
		logDef=stepDef.createNode(new GherkinKeyword("Then"), "Language list should "+arg1+" under language toggle button.");
		sbsTestPage.validateLanguageListDisplay(arg1);
	}

	@When("^Clicks on toggle button$")
	public void clicks_on_toggle_button() throws Throwable {
		logDef=stepDef.createNode(new GherkinKeyword("Then"), "Clicks on toggle button.");
		sbsTestPage.clickLanguageToggle();
	}
}