package automation.PestRoutes.Utilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import automation.PestRoutes.Controller.Login.SignIn;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;

public class BaseClass {

    WebDriver driver;
    SignIn signInPage;

    @BeforeClass

    public void beforeTest() {
        driver = GetWebDriver.getInstance();
        String url = "https://prone.pestroutes.com/";
        driver.get(url);
        signInPage = new SignIn();
    }
}
