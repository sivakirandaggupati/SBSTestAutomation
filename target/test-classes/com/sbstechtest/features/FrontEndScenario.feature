Feature: Radio launch page validation

  This feature file validates functionality of selected webelements and audio player funtionality.

 Front end scenario changes

@OpenBrowser @FrontEnd
Scenario: Invoke test page and validate the audio header
Given User invokes test page
Then Title of audio track should be displayed as "expected"
And Subscribe drop down should display "APPLE PODCASTS" podcast
And Subscribe drop down should display "GOOGLE PODCASTS" podcast

@FrontEnd
Scenario: Validate the audio player launch
Given User clicks on audio icon button
Then Audio player should be launched at the bottom of the screen

@FrontEnd
Scenario: Click and verify player controls
Given User clicks "pause" button for audio 
Then Audio player should "pause" the audio
When User clicks "play" button for audio 
Then Audio player should "play" the audio
When User clicks "mute" button for audio 
Then Audio player should "mute" the audio
When User clicks "unmute" button for audio 
Then Audio player should "unmute" the audio

@FrontEnd
Scenario: Click 20s forward or rewind on the audio player and verify scrub on the progress bar
Given User is on test page
When Clicks 20s "forward" button the audio should step forward by 20s
And Clicks 20s "back" button the audio should step back by 20s

@FrontEnd
Scenario: Verify language toggle button functionality
Given User is on test page
Then Language list should "not display" under language toggle button
When Clicks on toggle button
Then Language list should "display" under language toggle button

@TearDown @FrontEnd
Scenario: Test Teardown
Given Test complete
Then Close the test browser