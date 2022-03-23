package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import static automation.PestRoutes.Utilities.Utilities.*;
import automation.PestRoutes.Utilities.FindElement.InputType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static automation.PestRoutes.Utilities.Utilities.elementIsVisible;

public class ReminderTab extends PreferencesPage {

	// Reminder Type
	public String daysBefore_Reminder = "//input[@data-ruleitemtype='reminderDayOffset']";

	// When to trigger Objects
	public String triggerDaysBefore_whenToTrigger = "Trigger Days Before";
	private By whenToTriggerDropDown = By.xpath("//label[text()='When To Trigger']//following::select[@name='filterItemValue']");
	public String triggerOnCheckIn_whenToTrigger = "Trigger on Check-In";
	private By daysBeforeField = By.xpath("//label[text()='Days Before']//following::input[@name='filterItemValue']");
	private By includeCustomerFlagsMultiDropDown = By.xpath("//label[text()='Include Customer Flags']/parent::div[@class='col-6']//following-sibling::div/div//ul//input");
	private By greenActionButton = By.xpath("//div[text()='+ Action']");
	private By saveTriggerButton = By.xpath("//span[text()='save']");
	private By actionDropDown = By.xpath("//div[@id='observer']//select[@name='eventObserverID']");
	private By textAreaMessageVoiceSMS = By.xpath("//textarea[@name='observerItemValue']");
	private By emailTextAreaMessage = By.xpath("//textarea[@name='observerItemValue']/preceding::div[1]");
	private By emailTypeDropDown = By.xpath("//label[text()='Email Type']//following::select[@name='observerItemValue']");
	private By smsTypeDropDown = By.xpath("//label[text()='SMS Type']//following::select[@name='observerItemValue']");
	private By voiceTypeDropDown = By.xpath("//label[text()='Voice Type']//following::select[@name='observerItemValue']");

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

	public void selectWhenToTrigger(String whenToTrigger) {
		selectFromDropDown(whenToTrigger, whenToTriggerDropDown);
	}

	public void typeDaysBefore(String numberOfDays) {
		scrollToElementJS(daysBeforeField);
		type(numberOfDays, daysBeforeField);
	}

	public boolean typeFlagToInclude(String flagCode) {
		List<WebElement> allFlags = findElements(By.xpath("//div[@id='s2id_filterItem19']/ul/li/div"));
		WebElement includeCustomerFlagsMultiField = find(includeCustomerFlagsMultiDropDown);
		for (WebElement flag : allFlags) {
			if (flag.getText().contains(flagCode)) {
				System.out.println("Customer Flag: " + flag.getText());
				return true;
			}
		}
		scrollToElementJS(includeCustomerFlagsMultiField);
		type(flagCode, includeCustomerFlagsMultiField);
		System.out.println("Customer Flag: " + flagCode);
		return false;
	}

	public void clickAddActionButton() {
		elementIsVisible(greenActionButton);
		click(greenActionButton);
	}

	public void completeSendEmailAction(String emailType) {
		if (emailType.equalsIgnoreCase("Standard Reminder Email")) {
			elementIsVisible(emailTypeDropDown);
			delay(1000);
			selectFromDropDown(emailType, emailTypeDropDown);
		} else if (emailType.equalsIgnoreCase("Custom Reminder Email")){
			elementIsVisible(emailTypeDropDown);
			delay(1000);
			selectFromDropDown(emailType, emailTypeDropDown);
			type("Email - Reminder Trigger Rules Test", emailTextAreaMessage);
		}
	}

	public void completeSendSMSAction(String smsType) {
		if (smsType.equalsIgnoreCase("Standard Reminder Text Message")) {
			elementIsVisible(smsTypeDropDown);
			delay(1000);
			selectFromDropDown(smsType, smsTypeDropDown);
		} else if (smsType.equalsIgnoreCase("Custom Text Message")){
			elementIsVisible(smsTypeDropDown);
			delay(1000);
			selectFromDropDown(smsType, smsTypeDropDown);
			type("SMS - Reminder Trigger Rules Test", textAreaMessageVoiceSMS);
		}
	}

	public void completeSendVoiceAction(String voiceType) {
		String voiceMessage = "//select[@name='observerItemValue' and @data-observeritemtype='recordedMessages']";
		if (voiceType.equalsIgnoreCase("Standard Reminder Voice Message")) {
			elementIsVisible(voiceTypeDropDown);
			delay(1000);
			selectFromDropDown(voiceType, voiceTypeDropDown);
		} else if (voiceType.equalsIgnoreCase("Custom Voice Message")){
			elementIsVisible(voiceTypeDropDown);
			delay(1000);
			selectFromDropDown(voiceType, voiceTypeDropDown);
			type("Voice - Reminder Trigger Rules Test", textAreaMessageVoiceSMS);
		} else if (voiceType.equalsIgnoreCase("Pre-recorded Message")) {
			elementIsVisible(voiceTypeDropDown);
			delay(1000);
			selectFromDropDown(voiceType, voiceTypeDropDown);
			selectValueFromDropDownByIndex(voiceMessage, 0);
		}
	}

	public void completeReminderAction(String action, String type) {
		elementIsVisible(actionDropDown);
		delay(1000);
		selectFromDropDown(action, actionDropDown);
		switch (action) {
			case "Send Email Reminder":
				completeSendEmailAction(type);
				break;
			case "Send SMS Reminder":
				completeSendSMSAction(type);
				break;
			case "Send Voice Reminder":
				completeSendVoiceAction(type);
				break;
		}
	}

	public void clickSaveButton() {
		scrollToElementJS(saveTriggerButton);
		click(saveTriggerButton);
	}
}