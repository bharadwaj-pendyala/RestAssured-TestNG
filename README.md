# API Automation Template

Kindly Naviagate to src/main/resources/testsettings.properties file to Edit Dynamic Test Data. 

Generate API Token https://api.slack.com/custom-integrations/legacy-tokens.

TestData Required to be feeded:

- token
- newChannelName
- renameChannelTo
- archiveChannelID(E.g. CPN1ZMCEB) - Channel ID could be obtained from URL after clicking on channel.

Slack Workspace used for testing is: http://testplivoapi.slack.com

For successful execution please follow below steps:

1. Create a new slack workspace.
2. Assuming you are logged in as an admin - Generate an legacy API token with link provided above for the same workspace.
3. Feed the token and dynamic data as per requirements.
4. Trigger testng.xml file in root folder.
