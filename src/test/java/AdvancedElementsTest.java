import	org.openqa.selenium.*;
import	org.openqa.selenium.chrome.ChromeDriver;
import	org.openqa.selenium.support.ui.ExpectedConditions;
import	org.openqa.selenium.support.ui.Select;
import	org.openqa.selenium.support.ui.WebDriverWait;
import	java.time.Duration;
import	java.util.Set;
public	class	AdvancedElementsTest	{
    public	static	void	main(String[]	args)	throws	InterruptedException	{
        WebDriver	driver	=	new	ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait	wait	=	new	WebDriverWait(driver,	Duration.ofSeconds(10));
//	---	1.	DROPDOWN	EXAMPLE	--
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement	dropdownElement	=	driver.findElement(By.id("dropdown"));
        Select	select	=	new	Select(dropdownElement);
        select.selectByVisibleText("Option 2");
        System.out.println("Selected	Option:	"	+	select.getFirstSelectedOption().getText());
//	---	2.	ALERT	EXAMPLE	--
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Alert	alert	=	wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert	text:	"	+	alert.getText());
        alert.accept();	//	Clicks	OK
        Thread.sleep(5000);
//	---	3.	IFRAME	EXAMPLE	--
        driver.get("https://the-internet.herokuapp.com/iframe");
//	Switch	into	frame	using	frame	ID
        driver.switchTo().frame("mce_0_ifr");

        WebElement editor = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("tinymce"))
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].focus(); arguments[0].innerText = ''; arguments[0].textContent = '';",
                editor
        );
        editor.sendKeys("Hello inside iframe!");

        driver.switchTo().defaultContent();
//	---	4.	MULTIPLE	WINDOWS	/	TABS	--
        driver.get("https://the-internet.herokuapp.com/windows");
        String	parentWindowHandle	=	driver.getWindowHandle();
        driver.findElement(By.linkText("Click Here")).click();	//	Opens	new	tab
        Set<String>	windowHandles	=	driver.getWindowHandles();
        for	(String	handle	:	windowHandles)	{
            if	(!handle.equals(parentWindowHandle))	{
                driver.switchTo().window(handle);
                break;
            }
        }
        System.out.println("New	Tab	Title:	"	+	driver.getTitle());
        driver.close();	//	Close	new	tab
        driver.switchTo().window(parentWindowHandle);	//	Switch	back	to	original	window
        driver.quit();
    }
}
