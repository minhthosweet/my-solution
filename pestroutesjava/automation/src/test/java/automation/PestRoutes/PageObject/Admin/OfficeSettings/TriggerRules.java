package automation.PestRoutes.PageObject.Admin.OfficeSettings;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class TriggerRules {

	// Search Trigger
	public String searchTrigger = "//input[@id='triggerSearch']";

	// Trigger Types
	public String triigerType_AR = "AR";
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
	public String triggerRulesText = "//li[text() = 'Trigger Rules']";
	public String addTriggerButton = "//div[text() = '+ Trigger']";
	public String triggerTypeDropdown = "//select[@name='triggerEventID']";
	public String descriptionInputField = "//form//input[@name='description']";
	public String globalDropdown = "//select[@name='officeID']";
	public String startDateInputField = "//input[@name='startDate']";
	public String endDateInputField = "//input[@name='endDate']";
	public String isActiveDropdown = "//select[@name='active']";

	// Trigger Filter Actions
	public void navigateToTriggerRules() {
		Utilities.waitUntileElementIsVisible(triggerRulesText);
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
		Utilities.selectValueFromDropDownByValue(needObject, needTextValue);
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

}
