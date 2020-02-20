package automation.PestRoutes.Controller.Login;

import automation.PestRoutes.PageObject.SignInPage.LoginPage;

public class SignIn {
	LoginPage login;
	
	public void login(String needUserName, String needPassword) {
		login = new LoginPage();
		login.setUserName(needUserName);
		login.setPassword(needPassword);
		login.clickLoginButton();
	}

}
