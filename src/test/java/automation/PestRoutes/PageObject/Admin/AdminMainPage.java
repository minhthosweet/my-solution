package automation.PestRoutes.PageObject.Admin;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.By;


public class AdminMainPage extends BasePage {

	public String businessContacts = "Business Contacts";
	public String users = "Users";
	public String teams_Departments = "Teams / Departments";
	public String routeTemplates = "Route Templates";
	public String preferences = "Preferences";
	private By preferencesSubComponent = By.xpath("//ul[@id='settingsNav']//p[text()='Preferences']");
	public String billing = "Billing";
	public String userButton = "//div[text()='+ User']";
	public String mergeUserButton = "//div[text()='Merge Users']";
	public String existingUser = "//div[contains(text(),'Automation User')]";
	public String preExistingUser = "//div[contains(text(),'Automation User')]/parent::div/preceding-sibling::div[2]";

	//Action methods
	public void navigateTo(String needTab) {
		Deprecated.waitVisible("//p[text() = '"+needTab+"']");
		Deprecated.clickElement("//p[text() = '"+needTab+"']");
		Utilities.delay(500);
	}

	//Setters
	public void setInputValue(String needInputField, String keysToSend) {
		Deprecated.locate(needInputField).sendKeys(keysToSend);
	}

	//Getters
	public String getFieldValue(String needFieldName) {
		Deprecated.waitVisible(needFieldName);
		return Deprecated.getElementTextValue(needFieldName);
	}

	public PreferencesPage clickPreferencesSubComponent(){
		Utilities.click(preferencesSubComponent);
		return new PreferencesPage();
	}
}
