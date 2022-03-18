package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import static automation.PestRoutes.Utilities.Utilities.*;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ARTab extends PreferencesPage {

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

	// Identifiers for already created actions
	public String SMSAction_actual = "//div[text()='Send SMS']";
	public String createInvoiceAction_actual = "//div[text()='Create Invoices']";
	public String voiceAction_actual = "//div[text()='Send Voice']";
	public String emailAction_actual = "//div[text()='Send Email']";
	public String snailMailAction_actual = "//div[text()='Send Snail Mail']";
	public String collectionsStageAction_actual = "//div[text()='Set Collections Stage']";
	public String ARMAction_actual = "//div[text()='Send to ARM']";
	private By agePastDueDropDown = By.xpath("//label[text()='Age/Past Due']/parent::div/following-sibling::div/select[@name='filterItemValue']");
	private By agePastDueDaysField = By.xpath("//label[text()='Age/Past Due Days']/parent::div/following-sibling::div/input[@name='filterItemValue']");
	private By includeCustomerFlagsMultiDropDown = By.xpath("//label[text()='Include Customer Flags']/parent::div[@class='col-6']//following-sibling::div/div//ul//input");
	private By saveTriggerButton = By.xpath("//span[text()='save']");

	// Action Elements
	private By greenActionButton = By.xpath("//div[text()='+ Action']");
	private By actionDropDown = By.xpath("//div[@id='observer']//select[@name='eventObserverID']");
	private By emailTitleField = By.xpath("//input[@id='observerItem' and @name='observerItemValue']");
	private By emailTypeDropDown = By.xpath("//input[@data-observeritemtype='title']/parent::div/parent::div/following-sibling::div//select[@id='observerItem']");
	private By smsIgnoreContactPrefsDropDown = By.xpath("//select[@id='observerItem' and @data-observeritemtype='ignoreContactPrefs']");
	private By flagsField = By.xpath("//label[text()='Flags']/following::label/following-sibling::input");
	private By voiceTypeDropDown = By.xpath("//select[@name='observerItemValue' and @value='new']");
	private By createInvoicesValueTypeDropDown = By.xpath("//label[contains(text(),'Value Type')]//following::select[@id='observerItem']");
	private By createInvoicesValueField = By.xpath("//input[@id='observerItem' and @name='observerItemValue']");
	private By createInvoicesServiceTypeDropDown = By.xpath("//label[contains(text(),'Value Type')]//following::select[@id='observerItem']//following::select[@id='observerItem']");
	private By freezeCustomersCancellationReasonDropDown = By.xpath("//label[text()='Cancellation Reason']//following::select[@id='observerItem']");
	private By textArea_Email_FreezeCustomer_Message = By.xpath("//textarea[@id='observerItem']/../div");
	private By textArea_SMS_Voice_Message = By.xpath("//textarea[@id='observerItem']");

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

	public String getCreateInvoiceActionTextValue() {
		return Utilities.getElementTextValue(createInvoiceAction_actual, ElementType.XPath);
	}

	public String getSnailMailActionTextValue() {
		return Utilities.getElementTextValue(snailMailAction_actual, ElementType.XPath);
	}

	public String getCollectionsStageActionTextValue() {
		return Utilities.getElementTextValue(collectionsStageAction_actual, ElementType.XPath);
	}

	public String getARMStageActionTextValue() {
		return Utilities.getElementTextValue(ARMAction_actual, ElementType.XPath);
	}

	public void setAge_PastDueDays_InputField(String setDays) {
		FindElement.elementByAttribute(age_PastDueDays_InputField, InputType.XPath).sendKeys(setDays);
	}

	public void setMinimum_Balance(String minimumBalance) {
		Utilities.waitUntileElementIsVisible(minimum_Balance);
		FindElement.elementByAttribute(minimum_Balance, InputType.XPath).sendKeys(minimumBalance);
		;
	}

	public void setMaximum_Balance(String maximumBalance) {
		Utilities.waitUntileElementIsVisible(maximum_Balance);
		FindElement.elementByAttribute(maximum_Balance, InputType.XPath).sendKeys(maximumBalance);
		;
	}

	public void selectAgePastDue(String agePastDue) {
		scrollToElementJS(agePastDueDropDown);
		selectFromDropDown(agePastDue, agePastDueDropDown);
	}

	public void typeAgePastDueDays(String agePastDueDays) {
		type(agePastDueDays, agePastDueDaysField);
	}

	public boolean typeFlagToInclude(String flagCode) {
		List<WebElement> allFlags = findElements(By.xpath("//div[@id='s2id_filterItem9']/ul/li/div"));
		WebElement includeCustomerFlagsMultiField = find(includeCustomerFlagsMultiDropDown);
		for (WebElement flag : allFlags) {
			if (flag.getText().contains(flagCode)) {
				return true;
			}
		}
		scrollToElementJS(includeCustomerFlagsMultiField);
		type(flagCode, includeCustomerFlagsMultiField);
		System.out.println("Customer Flag: " + flagCode);
		return false;
	}

	public void selectAdditionalFilter(String additionalFilter) {
		switch(additionalFilter) {
			case "Minimum Balance":
				find(By.xpath(minimum_Balance)).clear();
				find(By.xpath(maximum_Balance)).clear();
				type("100", find(By.xpath(minimum_Balance)));
				break;
			case "Maximum Balance":
				find(By.xpath(minimum_Balance)).clear();
				find(By.xpath(maximum_Balance)).clear();
				type("1000", find(By.xpath(maximum_Balance)));
				break;
			default:
				find(By.xpath(minimum_Balance)).clear();
				find(By.xpath(maximum_Balance)).clear();
		}
	}

	public void clickAddActionButton() {
		elementIsVisible(greenActionButton);
		click(greenActionButton);
	}

	public void completeActionSendEmail(String emailType) {
		if (emailType.equalsIgnoreCase("Email Statement")) {
			selectFromDropDown(emailType, emailTypeDropDown);
			type("Automation Trigger Rule Test", emailTitleField);
		} else if (emailType.equalsIgnoreCase("New Email Message")){
			selectFromDropDown(emailType, emailTypeDropDown);
			type("Automation Trigger Rule Test", emailTitleField);
			type("Email - AR Trigger Rules Test", textArea_Email_FreezeCustomer_Message);
		}
	}

	public void completeActionSendSMS(String ignoreContactPrefs) {
		waitUntileElementIsVisible(smsIgnoreContactPrefsDropDown);
		selectFromDropDown(ignoreContactPrefs, smsIgnoreContactPrefsDropDown);
		type("SMS - AR Trigger Rules Test", textArea_SMS_Voice_Message);
	}

	public void completeActionSendVoice(String voiceType) {
		String voiceMessage = "//select[@name='observerItemValue' and @data-observeritemtype='recordedMessages']";
		if (voiceType.equalsIgnoreCase("New Message")) {
			selectFromDropDown(voiceType, voiceTypeDropDown);
			type("Voice - AR Trigger Rules Test", textArea_SMS_Voice_Message);
		} else if (voiceType.equalsIgnoreCase("Pre-recorded Message")) {
			selectFromDropDown(voiceType, voiceTypeDropDown);
			selectValueFromDropDownByIndex(voiceMessage, 0);
		}
	}

	public void completeAction(String action, String details) {
		elementIsVisible(actionDropDown);
		delay(1000);
		selectFromDropDown(action, actionDropDown);
		switch(action) {
			case "Add Flags":
				elementIsVisible(flagsField);
				type(details, find(flagsField));
				break;
			case "Create Invoices":
				selectFromDropDown("Fixed", createInvoicesValueTypeDropDown);
				type("38.34", createInvoicesValueField);
				selectFromDropDown(details, createInvoicesServiceTypeDropDown);
				break;
			case "Freeze Customers":
				selectFromDropDown(details, freezeCustomersCancellationReasonDropDown);
				type("Freeze Customers - AR Trigger Rules Test", textArea_Email_FreezeCustomer_Message);
				break;
			case "Send Email":
				completeActionSendEmail(details);
				break;
			case "Send SMS":
				completeActionSendSMS(details);
				break;
			case "Send Voice":
				completeActionSendVoice(details);
				break;
		}
	}

	public void clickSaveButton() {
		scrollToElementJS(saveTriggerButton);
		click(saveTriggerButton);
	}
}