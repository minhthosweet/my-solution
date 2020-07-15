package automation.PestRoutes.PageObject.Admin.OfficeSettings;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class TriggerRules {

	// Search Trigger
	public String searchTrigger = "//input[@id='triggerSearch']";

	// Trigger Types
	public String triggerType_AR = "AR";
	public String triggerType_Renewal = "Renewal";
	public String triggerType_Reminders = "Reminders";
	public String triggerType_SubscriptionStatus = "Subscription Status";
	public String triggerType_CustomerStatus = "Customer Status";
	public String triggerType_AppointmentStatus = "Appointment Status";
	public String triggerType_SubscriptionDueforService = "Subscription Due for Service";

	// Global Types
	public String globalType = "//select[@name='officeID']";
	public String global_SpecificToThisOffice = "Specific To This Office";
	public String global_AvailableToAllOffices = "Available To All Offices";

	// Global Types
	public String activeType = "//select[@name='active']";
	public String activeType_Active = "Active";
	public String activeType_NotActive = "Not Active";

	// Filter Objects
	public String officeSettingsText = "//div[@id='OfficeLogo']//preceding-sibling::h2";
	public String triggerRulesText = "//li[text() = 'Trigger Rules']";
	public String addTriggerButton = "//div[text() = '+ Trigger']";
	public String triggerTypeDropdown = "//select[@name='triggerEventID']";
	public String descriptionInputField = "//form//input[@name='description']";
	public String globalDropdown = "//select[@name='officeID']";
	public String startDateInputField = "//input[@name='startDate']";
	public String endDateInputField = "//input[@name='endDate']";
	public String isActiveDropdown = "//select[@name='active']";
	public String daysAfterChange = "//input[@data-ruleitemtype='triggerWhenValue']";

	// Save Button
	public String saveButton = "//div[@id='triggerRulesTable']//span[text()='save']";

	public void clickSaveButton() {
		Utilities.scrollToElement(addTriggerButton);
		Utilities.waitUntileElementIsVisible(saveButton);
		Utilities.clickElement(saveButton, ElementType.XPath);
	}

	// Trigger Filter Actions
	public void navigateToTriggerRules() {
		Utilities.waitUntileElementIsVisible(officeSettingsText);
		Utilities.clickElement(triggerRulesText, ElementType.XPath);
	}

	public void clickAddTrigerButton() {
		Utilities.clickElement(addTriggerButton, ElementType.XPath);
	}

	public void clickEditTrigger(String descriptionName) {
		Utilities.clickElement("//div[text() = '" + descriptionName + "']//parent::div//span[text()='edit']",
				ElementType.XPath);
	}

	public void searchTrigger(String description) {
		Utilities.waitUntileElementIsVisible(searchTrigger);
		FindElement.elementByAttribute(searchTrigger, InputType.XPath).sendKeys(description);
	}

	public void selectDropdown(String needObject, String needTextValue) {
		Utilities.waitUntileElementIsVisible(needObject);
		Utilities.selectValueFromDropDownByValue(needObject, needTextValue);
	}

	// Get Description text value from Landing page
	public String getDescriptionText(String descriptionSet) {
		return Utilities.getElementTextValue(
				"//div[@id= 'triggerRulesHeader']/following-sibling::div//div[text()='" + descriptionSet + "']",
				ElementType.XPath);
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
	
	public void setDaysAfterChange(String days) {
		Utilities.waitUntileElementIsVisible(daysAfterChange);
		FindElement.elementByAttribute(daysAfterChange, InputType.XPath).sendKeys(days);
	}
	
	// trigger Queue query
	public void hitTriggerQueue() {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerQueue.php");
	}

}
