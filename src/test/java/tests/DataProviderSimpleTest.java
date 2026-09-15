package	tests;
import	org.testng.annotations.DataProvider;
import	org.testng.annotations.Test;
public	class	DataProviderSimpleTest {
    //	1.	DataProvider	method	returning	2D	Object	array
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][]{
                {"student", "Password123", true},    //	Row	1:	Valid	User
                {"incorrectUser", "Password123", false},    //	Row	2:	Invalid	Username
                {"student", "wrongPass", false}    //	Row	3:	Invalid	Password
        };
    }

    //	2.	Test	method	linked	to	DataProvider	via	name	attribute
    @Test(dataProvider = "loginData")
    public void testLoginScenarios(String username, String password, boolean expectedSuccess) {
        System.out.println("Testing	Username:	" + username + "	|	Password:	" + password + "	|	Expecting	Success:	" + expectedSuccess);
    }
}