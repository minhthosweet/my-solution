package automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;

public class ARTab {

	// AR Filter Objects
	public String age_PastDueDropDown = "//label[text()='Age/Past Due']/ancestor::div[@class='col-6']/following-sibling::div/select[@name='filterItemValue']";
	public String age_PastDueDays_InputField = "//label[text()='Age/Past Due Days']/ancestor::div[@class='col-6']/following-sibling::div/input[@name='filterItemValue']";
	public String minimum_Balance = "//label[text()='Minimum Balance']/parent::div[@class='col-6']/following-sibling::div/input[@name='filterItemValue']";
	public String maximum_Balance = "//label[text()='Maximum Balance']/parent::div[@class='col-6']/following-sibling::div/input[@name='filterItemValue']";
	public String customer_Status = "//label[text()='Customer Status']/parent::div[@class='col-6']/following-sibling::div//ul";
	public String includeCollectionStagesDropDown = "//label[text()='Include Collections Stages']/parent::div[@class='col-6']/following-sibling::div//input";

	// Property Type Objects
	public String propertyType_AllProperties = "All Properties";
	public String propertyType_ResidentialOnly = "Residential Only";
	public String propertyType_CommercialOnly = "Commercial Only";

	public void setAge_PastDueDays_InputField(String setDays) {
		FindElement.elementByAttribute(age_PastDueDays_InputField, InputType.XPath).sendKeys(setDays);
	}

	public void setMinimum_Balance(String minimumBalance) {
		Utilities.waitUntileElementIsVisible(minimum_Balance);
		FindElement.elementByAttribute(minimum_Balance, InputType.XPath).sendKeys(minimumBalance);;
	}

	public void setMaximum_Balance(String maximumBalance) {
		Utilities.waitUntileElementIsVisible(maximum_Balance);
		FindElement.elementByAttribute(maximum_Balance, InputType.XPath).sendKeys(maximumBalance);;
	}

}
