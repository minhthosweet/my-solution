package automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import automation.PestRoutes.Utilities.FindElement.InputType;

public class ReminderTab {

	//Reminder Type
	public String daysBefore_Reminder = "//input[@data-ruleitemtype='reminderDayOffset']";

	// When to trigger Objects
	public String triggerDaysBefore_whenToTrigger = "Trigger Days Before";
	public String triggerOnCheckIn_whenToTrigger = "Trigger on Check-In";

	// Identifiers for already created actions
	public String emailAction_actual = "//div[text()='Send Email Reminder']";
	public String voiceAction_actual = "//div[text()='Send Voice Reminder']";
	public String SMSAction_actual = "//div[text()='Send SMS Reminder']";

	// Has Initial Service Objects Reminder
	public String hasInitialServiceDropdown_Reminder = "//select[@data-ruleitemtype='isInitialService']";
	public String hasInitialService_Any_Reminder = "Any";
	public String hasInitialService_isInitialService_Reminder = "Is Initial Service";
	public String hasInitialService_isNotInitialService_Reminder = "Is Not Initial Service";

	// Assert objects for Notes after Reminder is triggered
	public String editNotes_Alert = "//div[@id='notesDetail']//li";

	public void setdaysBefore_Reminder(String numberOfDays) {
		Utilities.waitUntileElementIsVisible(daysBefore_Reminder);
		FindElement.elementByAttribute(daysBefore_Reminder, InputType.XPath).clear();
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

	public String getAlertText_Notes() {
		Utilities.waitUntileElementIsVisible(editNotes_Alert);
		Utilities.clickElement(editNotes_Alert, ElementType.XPath);
		String editNote_AlertText = Utilities.getAlertText();
		Utilities.acceptAlert();
		return editNote_AlertText;
	}

	public String SMSConfirmationNote() {
		return Utilities.getElementTextValue("//div[@name='contactTypeName']", ElementType.XPath).trim();

	}

	public String getNumberReminderSentTo(String phoneNumber) {
		return Utilities.getElementTextValue("//div[@sentto='" + phoneNumber + "']", ElementType.XPath).trim();
	}
}
