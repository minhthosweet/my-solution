package automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import automation.PestRoutes.Utilities.FindElement.InputType;

public class ReminderTab {

	// Reminder Type
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

	// get Email value in Notes
	public String emailValue = "//li[1][@contactid='SENTEMAIL']//div[@sentto]";

	// get Voice Value in Notes
	public String voiceValue = "//div[contains(text(),'Voice Message')]//preceding-sibling::div[@sentto]";

	// get Snail Mail Value in Notes
	public String snailMailValue = "//div[contains(text(),'Queued Snail Mail')]//following-sibling::div[@sentto]";

	// get Alert Value in Notes
	public String alertLogValue = "//div[contains(text(),'Alert')]";

	// get Task Value in Notes
	public String taskLogValue = "//div[contains(text(),'Task - Customer Status')]";

	// get Employee Voice Value in Notes
	public String employeeVoiceValue = "//div[contains(text(),'unable to send message [bad phone number]')]";

	// get Removed Payment Value in Notes
	public String removePaymentValue = "//div[text()='Removed Payment Method from Trigger: Card']";

	// get Credit Card Info in Billing
	public String CCInfo = "//li[@class='gatewayCC']//div[text()='No Payment Info']";

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

	public String ConfirmationNote(String affirmation) {
		return Utilities.getElementTextValue("//div[contains(text(),'" + affirmation + "')]", ElementType.XPath);

	}

	public String getEmailValue() {
		return Utilities.getElementTextValue(emailValue, ElementType.XPath);
	}

	public String getVoiceText() {
		return Utilities.getElementTextValue(voiceValue, ElementType.XPath);
	}

	public String getSnailMailValue() {
		return Utilities.getElementTextValue(snailMailValue, ElementType.XPath);
	}

	public String getEmployeeEMailValue(String employeeEmail) {
		return Utilities.getElementTextValue("//div[@sentto='" + employeeEmail + "']", ElementType.XPath);
	}

	public String getAlertValue() {
		return Utilities.getElementTextValue(alertLogValue, ElementType.XPath);
	}

	public String getTaskValue() {
		return Utilities.getElementTextValue(taskLogValue, ElementType.XPath);
	}

	public String customerNameinTask(String customerNameTask) {
		Utilities.scrollToElement("//td[@customerid]//p[text()='" + customerNameTask + "']");
		return Utilities.getElementTextValue("//td[@customerid]//p[text()='" + customerNameTask + "']",
				ElementType.XPath);
	}

	public String getEmployeeVoiceValue() {
		return Utilities.getElementTextValue(employeeVoiceValue, ElementType.XPath).substring(0, 41);
	}

	public String getRemovedPaymentValue() {
		return Utilities.getElementTextValue(removePaymentValue, ElementType.XPath).substring(0, 41);
	}

	public String getCCInfoBilling() {
		return Utilities.getElementTextValue(CCInfo, ElementType.XPath);
	}
}
