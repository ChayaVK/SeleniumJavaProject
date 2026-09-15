import	org.openqa.selenium.WebDriver;
import	org.openqa.selenium.chrome.ChromeDriver;
public	class BrowserLaunch {
    public	static	void	main(String[]	args) throws InterruptedException {
        WebDriver	driver	=	new	ChromeDriver();
        driver.get("https://google.com");
        driver.manage().window().maximize();
        System.out.println("Page	1	Title:	"	+	driver.getTitle());
        System.out.println("Current	URL:	"	+	driver.getCurrentUrl());
        //Navigation	Controls	(Back,	Forward,	Refresh)
        driver.navigate().to("https://www.bing.com");
        System.out.println("Page	2	Title:	"	+	driver.getTitle());
        Thread.sleep(1000);	//	Pausing	for	1	second	just	to	visually	watch	the	browser
        driver.navigate().back();	//	Goes	back	to	Google
        Thread.sleep(1000);
        driver.navigate().forward();	//	Goes	forward	to	Bing
        Thread.sleep(1000);
        driver.navigate().refresh();	//	Reloads	current	page
//	4.	Close	the	browser
        driver.quit();
    }
}