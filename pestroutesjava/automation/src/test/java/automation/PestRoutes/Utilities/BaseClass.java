package automation.PestRoutes.Utilities;

import java.io.IOException;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import automation.PestRoutes.Controller.Login.SignIn;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;

public class BaseClass {

    WebDriver driver;
    SignIn signInPage;

    // @Parameters("browser")
    @BeforeClass

    public void beforeTest() throws IOException {
        driver = GetWebDriver.getInstance();
        String url = "https://prone.pestroutes.com/";
//        String url = getData("url", environment);
//        String userName = getData("userName", environment);
//        String password = getData("password", environment);
        driver.get(url);
        signInPage = new SignIn();
        //signInPage.login(userName, password);
    }

    @AfterClass
    public void afterTest() {

        Reporter.flushReport();
        driver.close();
    }

    @AfterTest
    public void captureFailedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","scrrr");
        }
    }
}
