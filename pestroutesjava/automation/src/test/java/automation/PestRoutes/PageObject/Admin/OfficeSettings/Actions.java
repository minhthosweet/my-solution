package automation.PestRoutes.PageObject.Admin.OfficeSettings;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class Actions {
	public String addActionButton = "//div[text()='+ Action']";
	public String additionalActionTypeDropDown = "//div[text()='+ Action']/following-sibling::div/div[2]//select[@name='eventObserverID']";

	// Action Type Objects
	// Renewal
	public String sendEmail = "Send Email";
	public String sendSnailMail = "Send Snail Mail";
	public String sendVoice = "Send Voice";
	public String sendSMS = "Send SMS";
	public String webHook = "Webhook";

	// AR
	public String createInvoices = "Create Invoices";
	public String freezeCustomers = "Freeze Customers";
	public String addFlags = "Add Flags";
	public String setCollectionsStage = "Set Collections Stage";
	public String sendTOARM = "Send to ARM";

	// Action Filter Objects
	public String actionTypeDropDown = "//select[@name = 'eventObserverID']";
	public String saveButton = "//div[@id='triggerRulesTable']//span[text()='save']";

	// Create Invoice Objects
	public String valueTypeDropDown = "//select[@data-observeritemtype = 'valueType']";
	public String value_Action = "//input[@data-ruleitemtype='value']";
	public String serviceType_Action = "//select[@data-observeritemtype = 'serviceID']";

	// SMS Objects
	public String ignoreContactPrefsDropDown = "//select[@data-observeritemtype = 'ignoreContactPrefs']";
	public String ignoreContactPrefsTypes_Yes = "Yes";
	public String ignoreContactPrefsTypes_No = "No";
	public String message_SMSAction = "//option[@selected and text()='Send SMS']/ancestor::div[@class='row']/following-sibling::div//textarea";

	// Voice Type
	public String voiceType = "//label[text()='Voice Type']/following-sibling::div";
	public String voiceType_newMessage = "New Message";
	public String voiceType_pre_recorded_Message = "Pre-recorded Message";
	// Voice Type Message
	public String message_VoiceAction = "//label[text()='Voice Type']/parent::div/following-sibling::div/following-sibling::div//textarea";

	// Email Objects
	public String email_Title = "//select[@data-observeritemtype='recordedMessages']";
	public String email_Type = "//select[@data-observeritemtype='messageType']";
	public String emailType_newEmailMessage = "New Email Message";
	public String emailType_emailStatement = "Email Statement";
	public String messageTypeDropDown = "//select[@data-observeritemtype = 'renewalMessageType']";
	public String renewalLinkDropDown = "//select[@data-observeritemtype='renewalLink']";
	// Renewal Link Dropdown
	public String renewalLinkDropdown_Include = "Include";
	public String renewalLinkDropdown_Exclude = "Exclude";
	public String subjectText = "//input[@data-ruleitemtype='subject']";

	// Snail Mail Objects
	public String snailMail_letterType = "//select[@data-observeritemtype='letterType']";
	public String snailMail_letterType_sendLetter = "Send Letter";
	public String snailMail_letterType_sendStatement = "Send Statement";
	public String snailMail_newMessage = "//select[@data-observeritemtype='letterType']/parent::div/parent::div/following-sibling::div//div[@id='redactor-uuid-0']";
	public String snailMail_messageType = "//div[text()='+ Action']/following-sibling::div/div[2]//select[@data-observeritemtype='renewalMessageType']";

	// Freeze Customer Objects
	public String freezeCustomers_newMessage = "//div[@id='observer']/parent::div/following-sibling::div//div[@id ='redactor-uuid-1']";

	// Add Flags Objects
	public String addFlags_Flags = "//label[text()='Action:']/parent::div/following-sibling::div//div[@id='s2id_filterItem30']";

	// Set Collections Stage Objects
	public String setCollectionsStage_Stage = "//label[text()='Action:']/parent::div/following-sibling::div//select[@id='observerItem']";
	public String stage_notSet = "Not Set";
	public String stage_Pending = "Pending";
	public String stage_Sent = "Sent";
	public String stage_sentToARM = "Sent to ARM";

	// Trigger Message Types
	public String customMessage = "Custom Message";
	public String renewalNotice = "Renewal Notice";

	// PlaceHolder Objects
	public String showPlaceHolders = "//div[text()='Show Placeholders']";
	public String placeHolderItem1 = "//div[@id='customerMessagePlaceholders']/div[2]";
	public String placeHolderItem2 = "//div[@id='customerMessagePlaceholders']/div[3]";
	public String placeHolderItem3 = "//div[@id='customerMessagePlaceholders']/div[4]";
	public String placeHolderItemText = null;

	// Actions
	public void clickAddActionButton() {
		Utilities.waitUntileElementIsVisible(addActionButton);
		Utilities.clickElement(addActionButton, ElementType.XPath);
	}

	public void setValue_Action(String valueOfAction) {
		Utilities.waitUntileElementIsVisible(value_Action);
		FindElement.elementByAttribute(value_Action, InputType.XPath).sendKeys(valueOfAction);
	}

	public void setMessageinAction(String placeholderMessage) {
		FindElement.elementByAttribute(message_SMSAction, InputType.XPath).sendKeys(placeholderMessage);
	}

	public void clickSaveButton() {
		Utilities.scrollToElement(saveButton);
		Utilities.waitUntileElementIsVisible(saveButton);
		Utilities.clickElement(saveButton, ElementType.XPath);
	}

	public String getPlaceHolders() {
		Utilities.scrollToElement(showPlaceHolders);
		Utilities.clickElement(showPlaceHolders, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(placeHolderItem1);
		placeHolderItemText = Utilities.getElementTextValue(placeHolderItem1, ElementType.XPath) + " "
				+ Utilities.getElementTextValue(placeHolderItem2, ElementType.XPath) + " "
				+ Utilities.getElementTextValue(placeHolderItem3, ElementType.XPath);
		return placeHolderItemText;
	}

	public void enterSubjectText(String actionSubjectText) {
		FindElement.elementByAttribute(subjectText, InputType.XPath).sendKeys(actionSubjectText);
	}

}
