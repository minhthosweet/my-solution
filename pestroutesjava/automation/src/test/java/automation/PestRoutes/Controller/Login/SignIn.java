package automation.PestRoutes.Controller.Login;

import java.io.IOException;

import automation.PestRoutes.PageObject.SignInPage.LoginPage;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;
import io.cucumber.java.en.Given;

public class SignIn extends AppData{
	LoginPage login;
//	@Given ("I sign in to pestroutes domain")
	public void login(String needUserName, String needPassword) {
		login = new LoginPage();
		login.setUserName(needUserName);
		login.setPassword(needPassword);
		login.clickLoginButton();
	}
	
	@Given ("I sign in to pestroutes domain")
	public void login() throws Exception {
		String url = getData("pronBetaUrl",generalData);
		String userName = getData("userName", generalData);
		String password = getData("password", generalData);

		GetWebDriver.getInstance().get(url);
		login = new LoginPage();
		login.setUserName(userName);
		login.setPassword(password);
		login.clickLoginButton();
	}

}
