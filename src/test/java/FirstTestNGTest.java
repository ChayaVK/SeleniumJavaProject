import	org.openqa.selenium.By;
import	org.openqa.selenium.WebDriver;
import	org.openqa.selenium.WebElement;
import	org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import	org.testng.Assert;
import org.testng.annotations.*;
import	org.testng.asserts.SoftAssert;
import	java.time.Duration;
public	class	FirstTestNGTest	{
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriver getDriver() {
        return driver.get();
    }

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public	void	setUp(@Optional("chrome")	String	browser)	{
        WebDriver webDriver;

        if (browser.equalsIgnoreCase("chrome")) {
            webDriver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {
            webDriver = new FirefoxDriver();

        } else {
            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser
            );
        }

        driver.set(webDriver);

        getDriver().manage().window().maximize();
        getDriver().get("https://practicetestautomation.com/practice-test-login/");
    }
    //	TEST	CASE	1:	Valid	Login	Check	using	Hard	Assertions

    @Test(groups = "smoke",priority	=	1,	description	=	"Verify	valid	user	login	functionality")
    public	void	testValidLogin()	{
        getDriver().findElement(By.id("username")).sendKeys("student");
        getDriver().findElement(By.id("password")).sendKeys("Password123");
        getDriver().findElement(By.id("submit")).click();
        String	currentUrl	=	getDriver().getCurrentUrl();
//	Hard	Assertion:	Stops	test	if	URL	does	not	match
        Assert.assertTrue(currentUrl.contains("logged-in-successfully"),	"URL	verification	failed!");
        WebElement	successHeader	=	getDriver().findElement(By.className("post-title"));
        Assert.assertEquals(successHeader.getText(),	"Logged In Successfully",	"Header	text	mismatch!");
    }
    //	TEST	CASE	2:	Invalid	Login	Check	using	Soft	Assertions
    @Test(groups="regression",priority	=	2,	description	=	"Verify	invalid	password	error	message")
    public	void	testInvalidLogin()	{
        SoftAssert	softAssert	=	new	SoftAssert();
        getDriver().findElement(By.id("username")).sendKeys("student");
        getDriver().findElement(By.id("password")).sendKeys("WrongPassword");
        getDriver().findElement(By.id("submit")).click();
        WebElement	errorElement	=	getDriver().findElement(By.id("error"));
//	Soft	Assertions	continue	execution	even	if	one	check	fails
        softAssert.assertTrue(errorElement.isDisplayed(),	"Error	message	is	not	visible!");
        softAssert.assertEquals(errorElement.getText(),	"Your password is invalid!",	"Error	text	mismatch!");
//	Mandatory	call	to	collapse	all	soft	assertions	and	report	failure	if	any	failed
        softAssert.assertAll();
    }
    //	TEARDOWN:	Runs	AFTER	every	test
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver webDriver = driver.get();

        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }
}