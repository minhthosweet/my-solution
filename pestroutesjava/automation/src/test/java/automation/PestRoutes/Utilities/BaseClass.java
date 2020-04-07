package automation.PestRoutes.Utilities;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import automation.PestRoutes.Controller.Login.SignIn;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;

public class BaseClass extends AppData {

	WebDriver driver = GetWebDriver.getInstance();
	SignIn signInPage;
	
	

	// @Parameters("browser")
	//@BeforeSuite

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

	//@AfterSuite(alwaysRun = true)

	public void afterTest() {
		Reporter.flushReport();
		driver.close();
		
		
	}
}
