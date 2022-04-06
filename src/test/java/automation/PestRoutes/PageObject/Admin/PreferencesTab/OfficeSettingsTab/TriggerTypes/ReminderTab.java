package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.Utilities.*;

import static automation.PestRoutes.Utilities.Utilities.*;

import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

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

	// Custom Messages For Email, SMS, and Voice Reminders
	private final String CUSTOM_EMAIL_MESSAGE = "Hi {{fname}}! We will be servicing your home this {{serviceDate}}. \n Email - Reminder Trigger Rules Test";
	private final String CUSTOM_SMS_MESSAGE = "Hi {{fname}}! We will be servicing your home this {{serviceDate}}. \n SMS - Reminder Trigger Rules Test";
	private final String CUSTOM_VOICE_MESSAGE = "Hi {{fname}}! We will be servicing your home this {{serviceDate}}. \n Voice - Reminder Trigger Rules Test";

	public void setdaysBefore_Reminder(String numberOfDays) {
		Deprecated.waitVisible(daysBefore_Reminder);
		Deprecated.locate(daysBefore_Reminder).clear();
		Deprecated.locate(daysBefore_Reminder).sendKeys(numberOfDays);
	}

	// Getters: get actual text value for action created(used for assertions)
	public String getEmailActionTextValue() {
		return Deprecated.getElementTextValue(emailAction_actual);
	}

	public String getSMSActionTextValue() {
		return Deprecated.getElementTextValue(SMSAction_actual);
	}

	public String getVoiceActionTextValue() {
		return Deprecated.getElementTextValue(voiceAction_actual);
	}

	public String getAlertText_Notes() {
		Deprecated.waitVisible(editNotes_Alert);
		Deprecated.clickElement(editNotes_Alert);
		String editNote_AlertText = Utilities.getAlertText();
		Utilities.acceptAlert();
		return editNote_AlertText;
	}

	public String ConfirmationNote(String affirmation) {
		return Deprecated.getElementTextValue("//div[contains(text(),'" + affirmation + "')]");

	}

	public String getEmailValue() {
		return Deprecated.getElementTextValue(emailValue);
	}

	public String getVoiceText() {
		return Deprecated.getElementTextValue(voiceValue);
	}

	public String getSnailMailValue() {
		return Deprecated.getElementTextValue(snailMailValue);
	}

	public String getEmployeeEMailValue(String employeeEmail) {
		return Deprecated.getElementTextValue("//div[@sentto='" + employeeEmail + "']");
	}

	public String getAlertValue() {
		return Deprecated.getElementTextValue(alertLogValue);
	}

	public String getTaskValue() {
		return Deprecated.getElementTextValue(taskLogValue);
	}

	public String customerNameinTask(String customerNameTask) {
		Deprecated.scrollToElement("//td[@customerid]//p[text()='" + customerNameTask + "']");
		return Deprecated.getElementTextValue("//td[@customerid]//p[text()='" + customerNameTask + "']"
        );
	}

	public String getEmployeeVoiceValue() {
		return Deprecated.getElementTextValue(employeeVoiceValue).substring(0, 41);
	}

	public String getRemovedPaymentValue() {
		return Deprecated.getElementTextValue(removePaymentValue).substring(0, 41);
	}

	public String getCCInfoBilling() {
		return Deprecated.getElementTextValue(CCInfo);
	}

	public void selectWhenToTrigger(String whenToTrigger) {
		Utilities.selectByText(whenToTriggerDropDown, whenToTrigger);
	}

	public void typeDaysBefore(String numberOfDays) {
		Deprecated.scrollToElementJS(daysBeforeField);
		Deprecated.type(numberOfDays, daysBeforeField);
	}

	public boolean typeFlagToInclude(String flagCode) {
		List<WebElement> allFlags = locateAll(By.xpath("//div[@id='s2id_filterItem19']/ul/li/div"));
		WebElement includeCustomerFlagsMultiField = Utilities.locate(includeCustomerFlagsMultiDropDown);
		for (WebElement flag : allFlags) {
			if (flag.getText().contains(flagCode)) {
				System.out.println("Customer Flag: " + flag.getText());
				return true;
			}
		}
		Deprecated.scrollToElementJS(includeCustomerFlagsMultiField);
		Deprecated.type(flagCode, includeCustomerFlagsMultiField);
		System.out.println("Customer Flag: " + flagCode);
		return false;
	}

	public void clickAddActionButton() {
		Utilities.isVisible(greenActionButton);
		Deprecated.scrollToElementJS(greenActionButton);
		click(greenActionButton);
	}

	public void completeSendEmailAction(String emailType) {
		if (emailType.equalsIgnoreCase("Standard Reminder Email")) {
			Utilities.isVisible(emailTypeDropDown);
			delay(1000);
			Utilities.selectByText(emailTypeDropDown, emailType);
		} else if (emailType.equalsIgnoreCase("Custom Reminder Email")){
			Utilities.isVisible(emailTypeDropDown);
			delay(1000);
			Utilities.selectByText(emailTypeDropDown, emailType);
			Deprecated.type(CUSTOM_EMAIL_MESSAGE, emailTextAreaMessage);
		}
	}

	public void completeSendSMSAction(String smsType) {
		if (smsType.equalsIgnoreCase("Standard Reminder Text Message")) {
			Utilities.isVisible(smsTypeDropDown);
			delay(1000);
			Utilities.selectByText(smsTypeDropDown, smsType);
		} else if (smsType.equalsIgnoreCase("Custom Text Message")){
			Utilities.isVisible(smsTypeDropDown);
			delay(1000);
			Utilities.selectByText(smsTypeDropDown, smsType);
			Deprecated.type(CUSTOM_SMS_MESSAGE, textAreaMessageVoiceSMS);
		}
	}

	public void completeSendVoiceAction(String voiceType) {
		String voiceMessage = "//select[@name='observerItemValue' and @data-observeritemtype='recordedMessages']";
		if (voiceType.equalsIgnoreCase("Standard Reminder Voice Message")) {
			Utilities.isVisible(voiceTypeDropDown);
			delay(1000);
			Utilities.selectByText(voiceTypeDropDown, voiceType);
		} else if (voiceType.equalsIgnoreCase("Custom Voice Message")){
			Utilities.isVisible(voiceTypeDropDown);
			delay(1000);
			Utilities.selectByText(voiceTypeDropDown, voiceType);
			Deprecated.type(CUSTOM_VOICE_MESSAGE, textAreaMessageVoiceSMS);
		} else if (voiceType.equalsIgnoreCase("Pre-recorded Message")) {
			Utilities.isVisible(voiceTypeDropDown);
			delay(1000);
			Utilities.selectByText(voiceTypeDropDown, voiceType);
			Deprecated.selectByIndex(voiceMessage, 0);
		}
	}

	public void completeReminderAction(String action, String type) {
		Utilities.isVisible(actionDropDown);
		delay(1000);
		Utilities.selectByText(actionDropDown, action);
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
		Deprecated.scrollToElementJS(saveTriggerButton);
		click(saveTriggerButton);
	}
}