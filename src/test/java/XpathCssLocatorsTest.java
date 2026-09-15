import	org.openqa.selenium.By;
import	org.openqa.selenium.WebDriver;
import	org.openqa.selenium.WebElement;
import	org.openqa.selenium.chrome.ChromeDriver;
public	class	XpathCssLocatorsTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");
//	1.	CSS	Selector	using	Attribute	Match
        WebElement usernameInput = driver.findElement(By.cssSelector("input[name='username']"));
        usernameInput.sendKeys("student");
//	2.	XPath	using	Attribute	Match
        WebElement passwordInput = driver.findElement(By.xpath("//input[@type='password']"));
        passwordInput.sendKeys("Password123");
//	3.	XPath	using	Contains	Text
        WebElement submitBtn = driver.findElement(By.xpath("//button[contains(@id,'submit')]"));
        submitBtn.click();
        Thread.sleep(2000);
//	4.	XPath	searching	by	Exact	Visible	Text
        WebElement headerText = driver.findElement(By.xpath("//h1[text()='Logged In Successfully']"));
        System.out.println("Page	Header	Text:	" + headerText.getText());
//	5.	XPath	using	Parent	Traversal
//	Locates	the	Log	out	link,	moves	to	its	parent	container,	then	verifies	text
        WebElement logoutContainer = driver.findElement(By.xpath("//a[contains(text(),'Log out')]"));
        System.out.println("Parent	Container	HTML	Tag:	" + logoutContainer.getTagName());
        driver.quit();
    }
}
