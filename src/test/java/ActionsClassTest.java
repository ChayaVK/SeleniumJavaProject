import	org.openqa.selenium.By;
import	org.openqa.selenium.Keys;
import	org.openqa.selenium.WebDriver;
import	org.openqa.selenium.WebElement;
import	org.openqa.selenium.chrome.ChromeDriver;
import	org.openqa.selenium.interactions.Actions;
import	org.openqa.selenium.support.ui.ExpectedConditions;
import	org.openqa.selenium.support.ui.WebDriverWait;
import	java.time.Duration;
public	class	ActionsClassTest	{
    public	static	void	main(String[]	args)	{
        WebDriver	driver	=	new	ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait	wait	=	new	WebDriverWait(driver,	Duration.ofSeconds(10));
//	Initialize	the	Actions	builder
        Actions	actions	=	new	Actions(driver);
//	---	1.	MOUSE	HOVER	EXAMPLE	--
        driver.get("https://the-internet.herokuapp.com/hovers");
//	Hover	over	the	first	user	profile	avatar
        WebElement	firstAvatar	=	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@alt='User Avatar'])[1]")));
        actions.moveToElement(firstAvatar).perform();
//	Verify	sub-menu	profile	link	appears	after	hover
        WebElement	profileLink	=	driver.findElement(By.xpath("//a[@href='/users/1']"));
        System.out.println("Hover	text	visible:	"	+	profileLink.isDisplayed());
//	---	2.	DRAG	AND	DROP	EXAMPLE	--
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        WebElement	columnA	=	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("column-a")));
        WebElement	columnB	=	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("column-b")));
//	Perform	drag	and	drop	from	column	A	to	column	B
        actions.dragAndDrop(columnA,	columnB).perform();
        System.out.println("Drag	and	drop	action	completed.");
//	---	3.	KEYBOARD	MODIFIER	&	UPPERCASE	TYPING	--
        driver.get("https://practicetestautomation.com/practice-test-login/");
        WebElement	usernameInput	=	driver.findElement(By.id("username"));
//	Hold	down	SHIFT	key	while	typing	to	force	UPPERCASE	text,	then	press	TAB
        actions.moveToElement(usernameInput)
                .click()
                .keyDown(Keys.SHIFT)
                .sendKeys("student_in_caps")
                .keyUp(Keys.SHIFT)
                .sendKeys(Keys.TAB)
                .perform();
        driver.quit();
    }
}