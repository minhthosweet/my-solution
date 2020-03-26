package automation.PestRoutes.Utilities;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Controller.Login.SignIn;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;

public class BaseClass extends AppData {

	WebDriver driver = GetWebDriver.getInstance();
	SignIn signInPage;
	
	

	// @Parameters("browser")
	@BeforeSuite

	public void beforeTest() throws IOException {

		String url = getData("pronBetaUrl",generalData);
		String userName = getData("userName", generalData);
		String password = getData("password", generalData);

		driver.get(url);

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		signInPage = new SignIn();
		signInPage.login(userName, password);

	}

	@AfterSuite(alwaysRun = true)

	public void afterTest() {
		Reporter.flushReport();
		//driver.close();
		
		
	}
}
