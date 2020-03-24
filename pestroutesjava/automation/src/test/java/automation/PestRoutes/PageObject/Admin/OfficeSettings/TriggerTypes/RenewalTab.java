package automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;

public class RenewalTab {
	// Renewal objects
	public String before_AfterDropdown = "//label[text()='Before/After']/ancestor::div[@class='col-6']/following-sibling::div/select[@name='filterItemValue']";
	public String daysBefore_AfterInputField = "//label[text()='Days Before/After']/ancestor::div[@class='col-6']/following-sibling::div/input[@name='filterItemValue']";
	public String accountStatusDropdown = "//label[text()='Account Status']/parent::div[@class='col-6']/following-sibling::div/select[@name='filterItemValue']";
	public String subscriptionStatusDropdown = "//label[text()='Subscription Status']/parent::div[@class='col-6']/following-sibling::div/select[@name='filterItemValue']";
	public String multiUnitDropdown = "//label[text()='Multi Unit']/parent::div[@class='col-6']/following-sibling::div/select[@name='filterItemValue']";
	public String propertyTypeDropdown = "//label[text()='Property Type']/parent::div[@class='col-6']/following-sibling::div/select[@name='filterItemValue']";
	public String includeServiceTypeDropdown = "//label[text()='Include Service Types']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String excludeServiceTypeDropdown = "//label[text()='Exclude Service Types']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String includeCustomerFlagsDropdown = "//label[text()='Include Customer Flags']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String excludeCustomerFlagsDropdown = "//label[text()='Exclude Customer Flags']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String includeSubscriptionFlagsDropdown = "//label[text()='Include Subscription Flags']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String excludeSubscriptionFlagsDropdown = "//label[text()='Exclude Subscription Flags']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String includeDivisionDropdown = "//label[text()='Include Divisions']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String excludeDivisionDropdown = "//label[text()='Exclude Divisions']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String includeRegionsDropdown = "//label[text()='Include Regions']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String excludeRegionsDropdown = "//label[text()='Exclude Regions']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String hasInitialServiceDropdown = "//label[text()='Has Initial Service']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String hasEmailDropdown = "//label[text()='Has Email']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String prefersPaperDropdown = "//label[text()='Prefers Paper']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String minRenewalAmountInputField = "//label[text()='Min Renewal Amount']/parent::div[@class='col-6']/following-sibling::div//input[@name='filterItemValue']";
	public String maxRenewalAmountInputField = "//label[text()='Max Renewal Amount']/parent::div[@class='col-6']/following-sibling::div//input[@name='filterItemValue']";
	public String cancelButton = "//div[@id='triggerRulesTable']//span[text()='cancel']";

	// Multi Unit DropDown Objects
	public String multiUnit_Dropdown_Include = "Include Multi Unit Properties";
	public String multiUnit_Dropdown_Exclude = "Exclude Multi Unit Properties";
	public String multiUnit_Dropdown_Only = "Only Multi Unit Properties";

	public void setDaysBefore_After(String needDays) {
		FindElement.elementByAttribute(daysBefore_AfterInputField, InputType.XPath).sendKeys(needDays);
	}

	public void setMinRenewalAmount(String needAmount) {
		FindElement.elementByAttribute(minRenewalAmountInputField, InputType.XPath).sendKeys(needAmount);
	}

	public void setMaxRenewalAmount(String needAmount) {
		Utilities.waitUntileElementIsVisible(maxRenewalAmountInputField);
		FindElement.elementByAttribute(maxRenewalAmountInputField, InputType.XPath).sendKeys(needAmount);
	}

}
