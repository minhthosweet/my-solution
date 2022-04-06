package automation.PestRoutes.PageObject.SignInPage;

import automation.PestRoutes.Utilities.Deprecated;

public class LoginPage {
	
	//**********Objects**********
	public String UserName = "//input[@id = 'inputUser']";
	public String Password = "//input[@id = 'inputPassword']";
	public String LoginButton = "//input[@type='submit']";
	
	
	//**********Functions**********
	
	// Action method
	public void clickLoginButton() {
		Deprecated.clickElement(LoginButton);
	}
	
	// Setter methods
	public void setUserName(String needUserName) {
		Deprecated.locate(UserName).sendKeys(needUserName);
	}
	
	public void setPassword(String needPassword) {
		Deprecated.locate(Password).sendKeys(needPassword);
	}

}
