package	stepdefinitions;
import	io.cucumber.java.After;
import	io.cucumber.java.Before;
import	io.cucumber.java.en.Given;
import	io.cucumber.java.en.When;
import	io.cucumber.java.en.Then;
import	io.cucumber.java.en.And;
import	org.openqa.selenium.By;
import	org.openqa.selenium.WebDriver;
import	org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import java.time.Duration;
public	class	LoginStepDefinitions	{
    private	WebDriver	driver;
    //	Hooks	run	before	each	Cucumber	scenario
    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-blink-features=AutomationControlled");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://practicetestautomation.com/practice-test-login/");
        try {
            Thread.sleep(2000); // keep browser open for 2 seconds so you can see ittt
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    @Given("User is on the login page")
    public	void	user_is_on_the_login_page()	{
        driver.get("https://practicetestautomation.com/practice-test-login/");
        System.out.println("Entered URL...");
    }
    @When("User enters username {string} and password {string}")
    public	void	user_enters_username_and_password(String	username,	String	password)	{
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }
    @And("Clicks the login submit button")
    public	void	clicks_the_login_submit_button()	{
        driver.findElement(By.id("submit")).click();
    }
    @Then("User should see the expected page result {string}")
    public	void	user_should_see_the_expected_page_result(String	expectedResult) throws InterruptedException {
        if	(driver.getCurrentUrl().contains("logged-in-successfully"))	{
            String	actualHeader	=	driver.findElement(By.className("post-title")).getText();
            Assert.assertEquals(actualHeader,	expectedResult);
        }	else	{
            Thread.sleep(5000); // keep browser open for 5 seconds so you can see it
            String	actualError	=	driver.findElement(By.id("error")).getText();

            Assert.assertEquals(actualError,	expectedResult);
        }
    }
    //	Hooks	run	after	each	Cucumber	scenario
    @After
    public	void	tearDown()	{
        System.out.println("Browser closing...");
        if (driver != null) {
            driver.quit();
        }
    }
}