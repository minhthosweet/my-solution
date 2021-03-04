package automation.PestRoutes.PageObject.Admin;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;


public class AdminMainPage {

	public String businessContacts = "Business Contacts";
	public String users = "Users";
	public String teams_Departments = "Teams / Departments";
	public String routeTemplates = "Route Templates";
	public String preferences = "Preferences";
	public String billing = "Billing";
	public String userButton = "//div[text()='+ User']";
	public String mergeUserButton = "//div[text()='Merge Users']";
	public String existingUser = "//div[contains(text(),'Automation User')]";
	public String preExistingUser = "//div[contains(text(),'Automation User')]/parent::div/preceding-sibling::div[2]";


	//Action methods
	public void navigateTo(String needTab) {
		Utilities.waitUntileElementIsVisible("//p[text() = '"+needTab+"']");
		Utilities.clickElement("//p[text() = '"+needTab+"']", ElementType.XPath);
	}

	//Setters
	public void setInputValue(String needInputField, String keysToSend) {
		FindElement.elementByAttribute(needInputField, InputType.XPath).sendKeys(keysToSend);
	}

	//Getters
	public String getFieldValue(String needFieldName) {
		Utilities.waitUntileElementIsVisible(needFieldName);
		return Utilities.getElementTextValue(needFieldName, ElementType.XPath);
	}

}
