import	org.openqa.selenium.By;
import	org.openqa.selenium.WebDriver;
import	org.openqa.selenium.WebElement;
import	org.openqa.selenium.chrome.ChromeDriver;
public	class	LocatorsTest	{
    public	static	void	main(String[]	args)	throws	InterruptedException	{
        WebDriver	driver	=	new	ChromeDriver();
        driver.manage().window().maximize();
//	1.	Navigate	to	a	standard	practice	login	page
        driver.get("https://practicetestautomation.com/practice-test-login/");
//	2.	Locate	Username	input	by	ID	and	type	text
        WebElement	usernameInput	=	driver.findElement(By.id("username"));
        usernameInput.sendKeys("student");
//	3.	Locate	Password	input	by	Name	and	type	text
        WebElement	passwordInput	=	driver.findElement(By.name("password"));
        passwordInput.sendKeys("Password123");
//	4.	Locate	Submit	button	by	ID	or	Class	Name	and	click
        WebElement	submitButton	=	driver.findElement(By.id("submit"));
        submitButton.click();
        Thread.sleep(2000);	//	Temporary	pause	to	visually	inspect	the	login
//	5.	Verify	successful	login	by	inspecting	the	URL	&	Success	Message
        String	currentUrl	=	driver.getCurrentUrl();
        System.out.println("Current	URL	after	login:	"	+	currentUrl);
//	Find	success	message	by	Class	Name
        WebElement	successMessage	=	driver.findElement(By.className("has-text-align-center"));
        System.out.println("Message	on	page:	"	+	successMessage.getText());
//	6.	Locate	Log	out	link	using	LinkText	and	click
        WebElement	logoutLink	=	driver.findElement(By.xpath("//a[contains(text(),'Log out')]"));
        logoutLink.click();
//	Close	driver
        driver.quit();
    }
}