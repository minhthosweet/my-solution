package automation.PestRoutes.Controller.Login;

import automation.PestRoutes.PageObject.SignInPage.LoginPage;
import automation.PestRoutes.Utilities.Data.AppData;
import automation.PestRoutes.Utilities.GetWebDriver;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SignIn extends AppData{
	LoginPage login;

	public void login(String needUserName, String needPassword) {
		login = new LoginPage();
		login.setUserName(needUserName);
		login.setPassword(needPassword);
		login.clickLoginButton();
	}
	
	@Given ("I sign in to pestroutes domain")
	public void login() throws Exception {
		String url = getData("url",environment);
		String userName = getData("userName", environment);
		String password = getData("password", environment);

		GetWebDriver.getInstance().get(url);
		login = new LoginPage();
		login.setUserName(userName);
		login.setPassword(password);
		login.clickLoginButton();
	}

	@Given ("I clear cache and reload the browser")
	public void browserReload() throws Exception{
		GetWebDriver.reloadBrowser();
	}

	@Given("I Log Into The Application via URL {string}, Username {string}, Password {string}")
	public void logIntoApplication(String url, String username, String password) {
		WebDriver driver;
		driver = new ChromeDriver();
		driver.get(url);
		login.logInToApplication(username, password);
	}

}
