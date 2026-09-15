Feature:User Login Functionality
@Smoke
Scenario Outline:Verify	user	login	with	valid	and	invalid	credentials
Given User is on the login page
When User enters username "<username>" and password "<password>"
And Clicks the login submit button
Then User should see the expected page result "<expectedResult>"
Examples:
|	username	|	password	|	expectedResult	|
|	student	|	Password123	|	Logged In Successfully	|
|	invalidUser	|	Password123	|	Your username is invalid!|