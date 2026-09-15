package	runner;
import	io.cucumber.testng.AbstractTestNGCucumberTests;
import	io.cucumber.testng.CucumberOptions;
@CucumberOptions(
        features	=	"src/test/resources/features",
        glue	=	{"stepdefinitions"},
        plugin	=	{
                "pretty",
                "html:target/cucumber-reports/cucumber-html-report.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        tags	=	"@Smoke"
)
public	class	TestRunner	extends	AbstractTestNGCucumberTests	{
//	Inherits	TestNG	execution	capabilities	from	AbstractTestNGCucumberTests
}