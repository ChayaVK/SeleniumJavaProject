package	pages;
import	org.openqa.selenium.By;
import	org.openqa.selenium.WebDriver;
public	class	DashboardPage	{
    private	final	WebDriver	driver;
    private	final	By	pageHeader	=	By.className("post-title");
    private	final	By	logoutButton	=	By.linkText("Log out");
    public	DashboardPage(WebDriver	driver)	{
        this.driver	=	driver;
    }
    public	String	getHeaderTitle()	{
        return	driver.findElement(pageHeader).getText();
    }
    public	boolean	isLogoutButtonDisplayed()	{
        return	driver.findElement(logoutButton).isDisplayed();
    }
}