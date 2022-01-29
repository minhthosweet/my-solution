package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import static automation.PestRoutes.Utilities.Utilities.*;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
	private By includeCustomerFlagsMultiDropDown = By.xpath("//label[text()='Include Customer Flags']//following::input");
	private By greenActionButton = By.xpath("//div[text()='+ Action']");
	private By actionDropDown = By.xpath("//div[@id='observer']//select[@name='eventObserverID']");
	private By emailTitleField = By.xpath("//input[@id='observerItem' and @name='observerItemValue']");
	private By emailTypeDropDown = By.xpath("//input[@data-observeritemtype='title']/parent::div/parent::div/following-sibling::div//select[@id='observerItem']");
	private By saveTriggerButton = By.xpath("//span[text()='save']");

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
		selectFromDropDown(agePastDue, agePastDueDropDown);
	}

	public void typeAgePastDueDays(String agePastDueDays) {
		type(agePastDueDays, agePastDueDaysField);
	}

	public void typeFlagToInclude(String flagCode) {
		// On Next Commitment
		// Write find statement to retrieve the size of x then remove
		// Write if statement to remove all values by clicking the x then type new flagcode
		WebElement includeCustomerFlagsMultiField = find(includeCustomerFlagsMultiDropDown);
		type(flagCode, includeCustomerFlagsMultiField);
	}

	public void clickAddActionButton() {
		elementIsVisible(greenActionButton);
		click(greenActionButton);
	}

	public void selectAction(String action) {
		elementIsVisible(actionDropDown);
		selectFromDropDown(action, actionDropDown);
	}

	public void typeEmailTitle(String emailTitle) {
		type(emailTitle, emailTitleField);
	}

	public void selectEmailType(String emailType) {
		selectFromDropDown(emailType, emailTypeDropDown);
	}

	public void clickSaveButton() {
		click(saveTriggerButton);
	}
}
