Feature: API Backend Test

    This feature file retreives the mp3 audio clip links from API Test end point and validates response code

    This is a git folder change.
	
	  This is a git1 folder change.
	  
	  Merge changes

@API
Scenario: Invoke test page and validate the audio header
Given I get response of "APITest" end point
Then I get all "archiveAudio" mp3 audio files
And I validate response is processed successfully

@TearDown
Scenario: Invoke test page and validate the audio header
Given Test complete
