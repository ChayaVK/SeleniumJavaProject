package	pages;
import	org.openqa.selenium.By;
import	org.openqa.selenium.WebDriver;
public	class	LoginPage	{
    private	final	WebDriver	driver;
    //	1.	Private	Locators	(Encapsulation)
    private	final	By	usernameInput	=	By.id("username");
    private	final	By	passwordInput	=	By.id("password");
    private	final	By	submitButton	=	By.id("submit");
    private	final	By	errorMessage	=	By.id("error");
    //	Constructor
    public	LoginPage(WebDriver	driver)	{
        this.driver	=	driver;
    }
    //	2.	Action	Methods
    public	void	enterUsername(String	username)	{
        driver.findElement(usernameInput).sendKeys(username);
    }
    public	void	enterPassword(String	password)	{
        driver.findElement(passwordInput).sendKeys(password);
    }
    public	void	clickSubmit()	{
        driver.findElement(submitButton).click();
    }
    //	Helper	method	combining	actions	&	returning	next	page	object	(Fluent	Interface)
    public	DashboardPage	login(String	username,	String	password)	{
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
        return	new	DashboardPage(driver);
    }
    public	String	getErrorMessageText()	{
        return	driver.findElement(errorMessage).getText();
    }
}