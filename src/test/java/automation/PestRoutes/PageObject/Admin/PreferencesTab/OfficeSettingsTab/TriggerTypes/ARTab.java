package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.Utilities.*;

import static automation.PestRoutes.Utilities.Utilities.*;

import automation.PestRoutes.Utilities.Deprecated;
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
	private By createInvoicesServiceTypeDropDown = By.xpath("//select[@data-observeritemtype='serviceID']");
	private By freezeCustomersCancellationReasonDropDown = By.xpath("//label[text()='Cancellation Reason']//following::select[@id='observerItem']");
	private By textArea_Email_FreezeCustomer_Message = By.xpath("//textarea[@id='observerItem']/../div");
	private By textArea_SMS_Voice_Message = By.xpath("//textarea[@id='observerItem']");

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

	public String getCreateInvoiceActionTextValue() {
		return Deprecated.getElementTextValue(createInvoiceAction_actual);
	}

	public String getSnailMailActionTextValue() {
		return Deprecated.getElementTextValue(snailMailAction_actual);
	}

	public String getCollectionsStageActionTextValue() {
		return Deprecated.getElementTextValue(collectionsStageAction_actual);
	}

	public String getARMStageActionTextValue() {
		return Deprecated.getElementTextValue(ARMAction_actual);
	}

	public void setAge_PastDueDays_InputField(String setDays) {
		Deprecated.locate(age_PastDueDays_InputField).sendKeys(setDays);
	}

	public void setMinimum_Balance(String minimumBalance) {
		Deprecated.waitVisible(minimum_Balance);
		Deprecated.locate(minimum_Balance).sendKeys(minimumBalance);
		;
	}

	public void setMaximum_Balance(String maximumBalance) {
		Deprecated.waitVisible(maximum_Balance);
		Deprecated.locate(maximum_Balance).sendKeys(maximumBalance);
		;
	}

	public void selectAgePastDue(String agePastDue) {
		Deprecated.scrollToElementJS(agePastDueDropDown);
		Utilities.selectByText(agePastDueDropDown, agePastDue);
	}

	public void typeAgePastDueDays(String agePastDueDays) {
		Deprecated.type(agePastDueDays, agePastDueDaysField);
	}

	public boolean typeFlagToInclude(String flagCode) {
		List<WebElement> allFlags = locateAll(By.xpath("//div[@id='s2id_filterItem11']/ul/li/div"));
		WebElement includeCustomerFlagsMultiField = Utilities.locate(includeCustomerFlagsMultiDropDown);
		for (WebElement flag : allFlags) {
			if (flag.getText().contains(flagCode)) {
				return true;
			}
		}
		Deprecated.scrollToElementJS(includeCustomerFlagsMultiField);
		Deprecated.type(flagCode, includeCustomerFlagsMultiField);
		System.out.println("Customer Flag: " + flagCode);
		return false;
	}

	public void selectAdditionalFilter(String additionalFilter) {
		switch(additionalFilter) {
			case "Minimum Balance":
				Utilities.locate(By.xpath(minimum_Balance)).clear();
				Utilities.locate(By.xpath(maximum_Balance)).clear();
				Deprecated.type("100", Utilities.locate(By.xpath(minimum_Balance)));
				break;
			case "Maximum Balance":
				Utilities.locate(By.xpath(minimum_Balance)).clear();
				Utilities.locate(By.xpath(maximum_Balance)).clear();
				Deprecated.type("1000", Utilities.locate(By.xpath(maximum_Balance)));
				break;
			default:
				Utilities.locate(By.xpath(minimum_Balance)).clear();
				Utilities.locate(By.xpath(maximum_Balance)).clear();
		}
	}

	public void clickAddActionButton() {
		isVisible(greenActionButton);
		Deprecated.scrollToElementJS(greenActionButton);
		click(greenActionButton);
	}

	public void completeActionSendEmail(String emailType) {
		if (emailType.equalsIgnoreCase("Email Statement")) {
			Utilities.selectByText(emailTypeDropDown, emailType);
			Deprecated.type("Automation Trigger Rule Test", emailTitleField);
		} else if (emailType.equalsIgnoreCase("New Email Message")){
			Utilities.selectByText(emailTypeDropDown, emailType);
			Deprecated.type("Automation Trigger Rule Test", emailTitleField);
			Deprecated.type("Email - AR Trigger Rules Test", textArea_Email_FreezeCustomer_Message);
		}
	}

	public void completeActionSendSMS(String ignoreContactPrefs) {
		waitVisible(smsIgnoreContactPrefsDropDown);
		Utilities.selectByText(smsIgnoreContactPrefsDropDown, ignoreContactPrefs);
		Deprecated.type("SMS - AR Trigger Rules Test", textArea_SMS_Voice_Message);
	}

	public void completeActionSendVoice(String voiceType) {
		String voiceMessage = "//select[@name='observerItemValue' and @data-observeritemtype='recordedMessages']";
		if (voiceType.equalsIgnoreCase("New Message")) {
			Utilities.selectByText(voiceTypeDropDown, voiceType);
			Deprecated.type("Voice - AR Trigger Rules Test", textArea_SMS_Voice_Message);
		} else if (voiceType.equalsIgnoreCase("Pre-recorded Message")) {
			Utilities.selectByText(voiceTypeDropDown, voiceType);
			Deprecated.selectByIndex(voiceMessage, 0);
		}
	}

	public void completeAction(String action, String details) {
		isVisible(actionDropDown);
		delay(1000);
		Utilities.selectByText(actionDropDown, action);
		switch(action) {
			case "Add Flags":
				isVisible(flagsField);
				Deprecated.type(details, Utilities.locate(flagsField));
				break;
			case "Create Invoices":
				Utilities.selectByText(createInvoicesValueTypeDropDown, "Fixed");
				Deprecated.type("38.34", createInvoicesValueField);
				Utilities.selectByText(createInvoicesServiceTypeDropDown, details);
				break;
			case "Freeze Customers":
				Utilities.selectByText(freezeCustomersCancellationReasonDropDown, details);
				Deprecated.type("Freeze Customers - AR Trigger Rules Test", textArea_Email_FreezeCustomer_Message);
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
		Deprecated.scrollToElementJS(saveTriggerButton);
		click(saveTriggerButton);
	}

	public String getCreateInvoiceServiceType() {
		String serviceType = getFirstSelected(createInvoicesServiceTypeDropDown);
		System.out.println("Service Type: " + serviceType);
		return serviceType;
	}
}