package automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import automation.PestRoutes.Utilities.FindElement.InputType;

public class ReminderTab {

	public String daysBefore_Reminder = "//input[@data-ruleitemtype='reminderDayOffset']";

	// Identifiers for already created actions
	public String emailAction_actual = "//div[text()='Send Email Reminder']";
	public String voiceAction_actual = "//div[text()='Send Voice Reminder']";
	public String SMSAction_actual = "//div[text()='Send SMS Reminder']";

	// Has Initial Service Objects Reminder
	public String hasInitialServiceDropdown_Reminder = "//select[@data-ruleitemtype='isInitialService']";
	public String hasInitialService_Any_Reminder = "Any";
	public String hasInitialService_isInitialService_Reminder = "Is Initial Service";
	public String hasInitialService_isNotInitialService_Reminder = "Is Not Initial Service";

	public void setdaysBefore_Reminder(String numberOfDays) {
		Utilities.waitUntileElementIsVisible(daysBefore_Reminder);
		FindElement.elementByAttribute(daysBefore_Reminder, InputType.XPath).sendKeys(numberOfDays);
	}

	// Getters: get actual text value for action created(used for assertions)
	public String getEmailActionTextValue() {
		return Utilities.getElementTextValue(emailAction_actual, ElementType.XPath);
	}

	public String getSMSActionTextValue() {
		return Utilities.getElementTextValue(SMSAction_actual, ElementType.XPath);
	}

	public String getVoiceActionTextValue() {
		return Utilities.getElementTextValue(voiceAction_actual, ElementType.XPath);
	}
}
