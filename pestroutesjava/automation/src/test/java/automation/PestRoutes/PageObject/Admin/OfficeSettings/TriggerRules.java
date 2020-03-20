package automation.PestRoutes.PageObject.Admin.OfficeSettings;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class TriggerRules {

	// Filter Objects 
	public String triggerRulesText = "//li[text() = 'Trigger Rules']";
	public String addTriggerButton = "//div[text() = '+ Trigger']";
	public String triggerTypeDropdown = "//select[@name='triggerEventID']";
	public String descriptionInputField = "//form//input[@name='description']";
	public String globalDropdown = "//select[@name='officeID']";
	public String startDateInputField = "//input[@name='startDate']";
	public String endDateInputField = "//input[@name='endDate']";
	public String isActiveDropdown = "//select[@name='active']";

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
	public String addActionButton = "//div[text()='+ Action']";
	public String cancelButton = "//div[@id='triggerRulesTable']//span[text()='cancel']";
	public String saveButton = "//div[@id='triggerRulesTable']//span[text()='save']";
	public String searchTrigger = "//input[@id='triggerSearch']";

	// Action Objects
	public String actionTypeDropDown = "//select[@name = 'eventObserverID']";
	public String messageTypeDropDown = "//select[@data-observeritemtype = 'renewalMessageType']";
	public String ignoreContactPrefsDropDown = "//select[@data-observeritemtype = 'ignoreContactPrefs']";
	public String subjectText = "//input[@data-ruleitemtype='subject']";
	public String renewalLinkDropDown = "//select[@data-observeritemtype='renewalLink']";
	public String additionalActionTypeDropDown = "//div[text()='+ Action']/following-sibling::div/div[2]//select[@name='eventObserverID']";
	public String additionalMessageTypeDropDown = "//div[text()='+ Action']/following-sibling::div/div[2]//select[@data-observeritemtype='renewalMessageType']";
	
	//Trigger Type Objects
	public String sendEmail = "Send Email";
	public String sendSnailMail = "Send Snail Mail";
	public String sendVoice = "Send Voice";
	public String sendSMS = "Send SMS";
	public String webHook = "Webhook";
	
	//Trigger Message Types
	public String customMessage = "Custom Message";
	public String renewalNotice = "Renewal Notice";
	
	//Trigger ignore Contact Preferences Types
	public String ignoreContactPrefsTypes_Yes = "Yes";
	public String ignoreContactPrefsTypes_No = "No";
	
	//Renewal Link Dropdown
	public String renewalLinkDropdown_Include = "Include";
	public String renewalLinkDropdown_Exclude = "Exclude";
	
	//Trigger Actions
	public void navigateToTriggerRules() {
		Utilities.waitUntileElementIsVisible(triggerRulesText);
		Utilities.clickElement(triggerRulesText, ElementType.XPath);
	}

	public void enterSubjectText(String actionSubjectText) {
		FindElement.elementByAttribute(subjectText, InputType.XPath).sendKeys(actionSubjectText);
	}

	public void clickAddTrigerButton() {
		Utilities.clickElement(addTriggerButton, ElementType.XPath);
	}

	// Actions
	public void clickAddActionButton() {
		Utilities.waitUntileElementIsVisible(addActionButton);
		Utilities.clickElement(addActionButton, ElementType.XPath);
	}

	public void selectDropdown(String needObject, String needTextValue) {
		Utilities.selectValueFromDropDownByValue(needObject, needTextValue);
	}

	public void clickEditTrigger(String descriptionName) {
		Utilities.clickElement("//div[text() = '"+descriptionName+"']//parent::div//span[text()='edit']", ElementType.XPath);
	}
	
	public void searchTrigger(String description) {
		Utilities.waitUntileElementIsVisible(searchTrigger);
		FindElement.elementByAttribute(searchTrigger, InputType.XPath).sendKeys(description);
	}

	public void clickSaveButton() {
		Utilities.scrollToElement(saveButton);
		Utilities.waitUntileElementIsVisible(saveButton);
		Utilities.clickElement(saveButton, ElementType.XPath);
	}

	// Setter methods
	public void setDescription(String needDescription) {
		FindElement.elementByAttribute(descriptionInputField, InputType.XPath).sendKeys(needDescription);
	}

	public void setStartDate(String needStartDate) {
		Utilities.waitUntileElementIsVisible(startDateInputField);
		Utilities.clickElement(startDateInputField, ElementType.XPath);
		FindElement.elementByAttribute(startDateInputField, InputType.XPath).clear();
		FindElement.elementByAttribute(startDateInputField, InputType.XPath).sendKeys(needStartDate);
	}

	public void setEndDate(String needEndDate) throws InterruptedException {
		Thread.sleep(3000);
		Utilities.waitUntileElementIsVisible(endDateInputField);
		Utilities.clickElement(endDateInputField, ElementType.XPath);
		FindElement.elementByAttribute(endDateInputField, InputType.XPath).clear();
		FindElement.elementByAttribute(endDateInputField, InputType.XPath).sendKeys(needEndDate);
	}

	public void setDaysBefore_After(String needDays) {
		FindElement.elementByAttribute(daysBefore_AfterInputField, InputType.XPath).sendKeys(needDays);
	}

	public void setMinRenewalAmount(String needAmount) {
		FindElement.elementByAttribute(minRenewalAmountInputField, InputType.XPath).sendKeys(needAmount);
	}

	public void setMaxRenewalAmount(String needAmount) {
		FindElement.elementByAttribute(maxRenewalAmountInputField, InputType.XPath).sendKeys(needAmount);
	}

}
