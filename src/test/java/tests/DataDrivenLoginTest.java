package	tests;
import org.openqa.selenium.By;
import	org.openqa.selenium.WebDriver;
import	org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import	org.testng.Assert;
import org.testng.annotations.*;
import	pages.LoginPage;
import	java.time.Duration;
public	class	DataDrivenLoginTest	{
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriver getDriver() {
        return driver.get();
    }
    
    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        WebDriver webDriver;

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            webDriver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            webDriver = new FirefoxDriver();

        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.set(webDriver);
        getDriver().get("https://practicetestautomation.com/practice-test-login/");
    }
    //	DataProvider	supplying	credentials	&	expected	status
    @DataProvider(name	=	"invalidCredentials",	parallel	=	true)
    public	Object[][]	getInvalidCredentials()	{
        return	new	Object[][]	{
                {"incorrectUser",	"Password123",	"Your username is invalid!"},
                {"student",	"incorrectPassword",	"Your password is invalid!"}
        };
    }
    @Test(dataProvider	=	"invalidCredentials")
    public	void	testInvalidLoginScenarios(String	username,	String	password,	String	expectedErrorMessage)	{
        LoginPage	loginPage	=	new	LoginPage(getDriver());
//	Execute	login	attempt	with	current	row	data
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickSubmit();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
//	Verify	dynamic	error	message	output
        String	actualError	=	loginPage.getErrorMessageText();
        Assert.assertEquals(actualError,	expectedErrorMessage,	"Error	message	text	mismatched	for	user:	"	+	username);
    }
    @AfterMethod
    public	void	tearDown()	{
        if	(getDriver()	!=	null)	{
            getDriver().quit();
        }
    }
}