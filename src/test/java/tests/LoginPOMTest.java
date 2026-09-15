package	tests;
import	org.openqa.selenium.WebDriver;
import	org.openqa.selenium.chrome.ChromeDriver;
import	org.testng.Assert;
import	org.testng.annotations.AfterMethod;
import	org.testng.annotations.BeforeMethod;
import	org.testng.annotations.Test;
import	pages.DashboardPage;
import	pages.LoginPage;
import	java.time.Duration;
public	class	LoginPOMTest	{
    private	WebDriver	driver;
    @BeforeMethod
    public	void	setUp()	{
        driver	=	new	ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }
    @Test
    public	void	testSuccessfulLogin()	{
        LoginPage	loginPage	=	new	LoginPage(driver);
//	Perform	login	and	receive	the	DashboardPage	object	directly
        DashboardPage	dashboardPage	=	loginPage.login("student",	"Password123");
//	Assertions	stay	in	the	test	class!
        Assert.assertEquals(dashboardPage.getHeaderTitle(),	"Logged In Successfully");
        Assert.assertTrue(dashboardPage.isLogoutButtonDisplayed());
    }
    @AfterMethod
    public	void	tearDown()	{
        if	(driver	!=	null)	{
            driver.quit();
        }
    }
}