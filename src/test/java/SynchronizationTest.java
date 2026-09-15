import	org.openqa.selenium.By;
import	org.openqa.selenium.NoSuchElementException;
import	org.openqa.selenium.WebDriver;
import	org.openqa.selenium.WebElement;
import	org.openqa.selenium.chrome.ChromeDriver;
import	org.openqa.selenium.support.ui.ExpectedConditions;
import	org.openqa.selenium.support.ui.FluentWait;
import	org.openqa.selenium.support.ui.Wait;
import	org.openqa.selenium.support.ui.WebDriverWait;
import	java.time.Duration;
public	class	SynchronizationTest	{
    public	static	void	main(String[]	args)	{
        WebDriver	driver	=	new	ChromeDriver();
        driver.manage().window().maximize();
//	------------------------------------------------------------
//	1.	IMPLICIT	WAIT	(Set	globally	-	optional/discouraged	if	using	explicit)
//	------------------------------------------------------------
//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://practicetestautomation.com/practice-test-login/");
//	------------------------------------------------------------
//	2.	EXPLICIT	WAIT	(WebDriverWait)
//	------------------------------------------------------------
        WebDriverWait	wait	=	new	WebDriverWait(driver,	Duration.ofSeconds(10));
//	Wait	until	username	field	is	visible,	then	type
        WebElement	usernameInput	=	wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("username"))
        );
        usernameInput.sendKeys("student");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("Password123");

//	Wait	until	submit	button	is	clickable,	then	click
        WebElement	submitBtn	=	wait.until(
                ExpectedConditions.elementToBeClickable(By.id("submit"))
        );


        //	------------------------------------------------------------
//	3.	FLUENT	WAIT	(Custom	Polling	&	Exception	Handling)
//	------------------------------------------------------------
        Wait<WebDriver>	fluentWait	=	new	FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
//	Wait	for	submit	button	to	be	clickable	using	FluentWait
        WebElement	submitBtnFluent	=	fluentWait.until(
                ExpectedConditions.elementToBeClickable(By.id("submit"))
        );
        submitBtnFluent.click();
//	Wait	for	successful	URL	redirect
        wait.until(ExpectedConditions.urlContains("logged-in-successfully"));
        System.out.println("Login	successful,	current	URL:	"	+	driver.getCurrentUrl());
        driver.quit();
    }
}