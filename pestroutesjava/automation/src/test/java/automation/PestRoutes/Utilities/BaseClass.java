package automation.PestRoutes.Utilities;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import automation.PestRoutes.Controller.Login.SignIn;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;

public class BaseClass extends AppData {

	WebDriver driver = GetWebDriver.getInstance();
	SignIn signInPage;
	

	// @Parameters("browser")
	@BeforeClass

	public void beforeTest() throws IOException {

		String url = getData("url",generalData);
		String userName = getData("userName", generalData);
		String password = getData("password", generalData);

		driver.get(url);

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		signInPage = new SignIn();
		signInPage.login(userName, password);

	}

	@AfterClass

	public void afterTest() {
		Reporter.flushReport();
		
		driver.close();
	}

}
