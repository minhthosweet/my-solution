package automation.PestRoutes.Utilities;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import automation.PestRoutes.Controller.Login.SignIn;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;

public class BaseClass extends AppData {

	WebDriver driver;
	SignIn signInPage;
	
	

	// @Parameters("browser")
	@BeforeSuite

	public void beforeTest() throws IOException {
		driver = GetWebDriver.getInstance();
		String url = getData("url",environment);
		String userName = getData("userName", environment);
		String password = getData("password", environment);

		driver.get(url);
		signInPage = new SignIn();
		signInPage.login(userName, password);

	}

	@AfterSuite(alwaysRun = true)

	public void afterTest() {
		Reporter.flushReport();
		//driver.close();
		
		
	}
}
