package automation.PestRoutes.PageObject.SignInPage;

import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class LoginPage {
	
	//**********Objects**********
	public String UserName = "//input[@id = 'inputUser']";
	public String Password = "//input[@id = 'inputPassword']";
	public String LoginButton = "//input[@type='submit']";
	
	
	//**********Functions**********
	
	// Action method
	public void clickLoginButton() {
		Utilities.clickElement(LoginButton, ElementType.XPath);
	}
	
	// Setter methods
	public void setUserName(String needUserName) {
		FindElement.elementByAttribute(UserName, InputType.XPath).sendKeys(needUserName);
	}
	
	public void setPassword(String needPassword) {
		FindElement.elementByAttribute(Password, InputType.XPath).sendKeys(needPassword);
	}

}
