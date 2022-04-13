package automation.PestRoutes.PageObject.SignInPage;

import automation.PestRoutes.Utilities.Legacy;

public class LoginPage {
	
	//**********Objects**********
	public String UserName = "//input[@id = 'inputUser']";
	public String Password = "//input[@id = 'inputPassword']";
	public String LoginButton = "//input[@type='submit']";
	
	
	//**********Functions**********
	
	// Action method
	public void clickLoginButton() {
		Legacy.clickElement(LoginButton);
	}
	
	// Setter methods
	public void setUserName(String needUserName) {
		Legacy.locate(UserName).sendKeys(needUserName);
	}
	
	public void setPassword(String needPassword) {
		Legacy.locate(Password).sendKeys(needPassword);
	}

}
