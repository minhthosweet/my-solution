package automation.PestRoutes.PageObject.Admin.OfficeSettings;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class Actions {
	// Add Actions
	public String addActionButton = "//div[text()='+ Action']";

	// Action Type Objects
	public String EmailMessageType_Action = "Send Email";
	public String snailMailMessageType_Action = "Send Snail Mail";
	public String freezeCustomersMessageType_Action = "Freeze Customers";
	public String sendVoiceMessageType_Action = "Send Voice";
	public String sendSMSMessageType_Action = "Send SMS";
	public String webHookMessageType_Action = "Webhook";
	public String createInvoicesMessageType_Action = "Create Invoices";
	public String addFlagsMessageType_Action = "Add Flags";
	public String setCollectionsStageMessageType_Action = "Set Collections Stage";
	public String sendTOARMMessageType_Action = "Send to ARM";
	public String webhookMessageType_Action = "Webhook";
	// Reminders
	public String sendEmailReminder = "Send Email Reminder";
	public String sendSMSReminder = "Send SMS Reminder";
	public String sendVoiceReminder = "Send Voice Reminder";

	// Action Filter Objects
	public String actionTypeDropDown = "//select[@name = 'eventObserverID']";

	// Create Invoice Objects
	public String valueTypeDropDown = "//select[@data-observeritemtype = 'valueType']";
	public String value_Action = "//input[@data-ruleitemtype='value']";
	public String serviceType_Action = "//select[@data-observeritemtype = 'serviceID']";

	// SMS Objects
	public String ignoreContactPrefsDropDown = "//select[@data-observeritemtype = 'ignoreContactPrefs']";
	public String ignoreContactPrefsTypes_Yes = "Yes";
	public String ignoreContactPrefsTypes_No = "No";
	public String messageAR_SMSAction = "//option[@selected and text()='Send SMS']/ancestor::div[@class='row']/following-sibling::div//textarea";
	// Reminder SMS Type Objects
	public String SMSType_Reminder = "//label[text()='SMS Type']/following-sibling::div/select[@id='observerItem']";
	public String customSMS_Reminder = "Custom Text Message";
	public String standardReminderSMS_Reminder = "Standard Reminder Text Message";

	// Voice Type
	public String voiceType = "//label[text()='Voice Type']/following-sibling::div";
	public String voiceType_newMessage = "New Message";
	public String voiceType_pre_recorded_Message = "Pre-recorded Message";
	// Voice Type Message
	public String message_VoiceAction = "//label[text()='Voice Type']/parent::div/following-sibling::div/following-sibling::div//textarea";
	// Reminder Voice Type Objects
	public String voiceType_Reminder = "//label[text()='Voice Type']/following-sibling::div/select[@id='observerItem']";
	public String customReminderVoice_Reminder = "Custom Voice Message";
	public String standardReminderVoice_Reminder = "Standard Reminder Voice Message";
	public String newMessageVoice_AR = "New Message";
	public String prerRecordedMessageVoice_Reminder = "Pre-recorded Message";
	public String preRecordedMessage_Message_Reminder = "//option[text()='Pre-recorded Message']/parent::select/parent::div/parent::div/following-sibling::div//select[@id]";

	// Email Objects
	public String email_Title = "//input[@data-observeritemtype='title']";
	public String email_Type = "//input[@data-observeritemtype='title']/parent::div/parent::div/following-sibling::div//select[@id]";
	public String emailType_newEmailMessage = "New Email Message";
	public String emailType_emailStatement = "Email Statement";

	// Webhook Objects
	public String webhook_MethodType = "//select[@data-observeritemtype='method']";
	public String webhookMethod_POST = "HTTP POST";
	public String webhookMethod_GET = "HTTP GET";
	// Webhook message types
	public String URLMessage_Wehbook = "URL";
	public String requestHeaderMessage_Webhook = "Request Header";
	public String requestBodyMessage_Webhook = "Request Body";

	// Message Renewal
	public String messageTypeDropDown = "//select[@data-observeritemtype = 'renewalMessageType']";
	public String renewalLinkDropDown = "//select[@data-observeritemtype='renewalLink']";
	// Renewal Link Dropdown
	public String renewalLinkDropdown_Include = "Include";
	public String renewalLinkDropdown_Exclude = "Exclude";
	public String subjectText = "//input[@data-ruleitemtype='subject']";
	// Reminder Email Type Objects
	public String emailType_Reminder = "//label[text()='Email Type']/following-sibling::div/select[@id='observerItem']";
	public String customReminderEmail_Reminder = "Custom Reminder Email";
	public String standardReminderEmail_Reminder = "Standard Reminder Email";

	// Snail Mail Objects
	public String snailMail_letterType = "//select[@data-observeritemtype='letterType']";
	public String snailMail_letterType_sendLetter = "Send Letter";
	public String snailMail_letterType_sendStatement = "Send Statement";
	public String snailMail_messageType = "//div[text()='+ Action']/following-sibling::div/div[2]//select[@data-observeritemtype='renewalMessageType']";

	// Freeze Customer Objects
	public String freezeCustomers_newMessage = "//div[@id='observer']/parent::div/following-sibling::div//div[@id ='redactor-uuid-1']";

	// Add Flags Objects
	public String addFlags_Flags = "//label[text()='Action:']/parent::div/following-sibling::div//div[@id='s2id_filterItem30']";

	// Set Collections Stage Objects
	public String collectionsStage_Stage = "//label[text()='Action:']/parent::div/following-sibling::div//select[@id='observerItem']";
	public String stage_notSet = "Not Set";
	public String stage_Pending = "Pending";
	public String stage_Sent = "Sent";
	public String stage_sentToARM = "Sent to ARM";

	// Trigger Message Types
	public String customMessage = "Custom Message";
	public String renewalNotice = "Renewal Notice";

	// PlaceHolder Objects
	public String facebookImage = "//a[text()='Terms of Service']";
	public String showPlaceHolders = "//div[text()='Show Placeholders']";
	public String placeHolderItem1 = "//div[@id='customerMessagePlaceholders']/div[2]";
	public String placeHolderItem2 = "//div[@id='customerMessagePlaceholders']/div[3]";
	public String placeHolderItem3 = "//div[@id='customerMessagePlaceholders']/div[4]";
	public String placeHolderItemText;
	public String hidePlaceHolders = "//div[text()='Hide Placeholders']";

	// Actions
	public void clickAddActionButton() {
		Utilities.waitUntileElementIsVisible(addActionButton);
		Utilities.clickElement(addActionButton, ElementType.XPath);
	}

	public void setValue_Action(String valueOfAction) {
		Utilities.waitUntileElementIsVisible(value_Action);
		FindElement.elementByAttribute(value_Action, InputType.XPath).sendKeys(valueOfAction);
	}

	public void setCollectionsStage() {
		Utilities.clickElement(collectionsStage_Stage, ElementType.XPath);
	}

	// Write Messages type 1
	public void setMessageinAction_Type1(String messageType, String placeholderMessage) {
		FindElement
				.elementByAttribute("//option[@selected and text()='" + messageType
						+ "']/ancestor::div[@class='row']/following-sibling::div//textarea", InputType.XPath)
				.sendKeys(placeholderMessage);
	}

	// Write Messages type 2
	public void setMessageinAction_Type2(String messageType, String placeholderMessage) {
		FindElement.elementByAttribute("//option[@selected and text()='" + messageType
				+ "']/parent::select/parent::div/parent::div/parent::div/following-sibling::div//ul/following-sibling::div",
				InputType.XPath).sendKeys(placeholderMessage);
	}

	// Set email title
	public void setEmailTitle(String emailTitle) {
		FindElement.elementByAttribute(email_Title, InputType.XPath).sendKeys(emailTitle);
	}

	// To get placeholders
	public String getPlaceHolders() {
		Utilities.waitUntileElementIsVisible(facebookImage);
		Utilities.scrollToElement(facebookImage);
		Utilities.waitUntileElementIsVisible(showPlaceHolders);
		Utilities.clickElement(showPlaceHolders, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(placeHolderItem1);
		placeHolderItemText = Utilities.getElementTextValue(placeHolderItem1, ElementType.XPath) + " "
				+ Utilities.getElementTextValue(placeHolderItem2, ElementType.XPath) + " "
				+ Utilities.getElementTextValue(placeHolderItem3, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(hidePlaceHolders);
		Utilities.clickElement(hidePlaceHolders, ElementType.XPath);
		return placeHolderItemText;
	}

	// Enter Subject
	public void enterSubjectText(String actionSubjectText) {
		FindElement.elementByAttribute(subjectText, InputType.XPath).sendKeys(actionSubjectText);
	}

	// Message for Webhook
	public void messageInWebhook(String webHookMessageType, String placeHolderMessage) {
		FindElement.elementByAttribute("//label[text()='" + webHookMessageType + "']/following-sibling::div/textarea",
				InputType.XPath).sendKeys(placeHolderMessage);
	}

	// Remove trigger
	public void removeAction(String ActionType) {
		Utilities.clickElement(
				"//option[@selected and text()='" + ActionType
						+ "']/parent::select/parent::div/parent::div/following-sibling::div[text()='Remove']",
				ElementType.XPath);
	}
}
