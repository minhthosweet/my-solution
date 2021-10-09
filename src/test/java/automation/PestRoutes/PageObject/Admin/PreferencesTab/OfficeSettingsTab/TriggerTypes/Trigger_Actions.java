package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class Trigger_Actions {
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
    // Subscription Status
    public String sendEmployeeEmail_SubscriptionStatus = "Send Employee Email";
    public String addAlert_SubscriptionStatus = "Add Alert";
    public String addTask_SubscriptionStatus = "Add Task";
    public String sendEmployeeSMS_SubscriptionStatus = "Send Employee SMS";
    public String sendEmployeeVoice_SubscriptionStatus = "Send Employee Voice";
    public String removePaymentProfile_SubscriptionStatus = "Remove Payment Profile";

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

    // Voice Type Objects
    public String voiceType = "//label[text()='Voice Type']/following-sibling::div";
    public String voiceType_newMessage = "New Message";
    public String voiceType_pre_recorded_Message = "Pre-recorded Message";
    // Voice Type Message
    public String message_VoiceAction = "//label[text()='Voice Type']/parent::div/following-sibling::div/following-sibling::div//textarea";
    public String voiceType_Reminder = "//label[text()='Voice Type']/following-sibling::div/select[@id='observerItem']";
    public String voiceType_SubscriptionStatus = "//option[@selected and text()='Send Employee Voice']/parent::select/parent::div/parent::div/parent::div/following::div/following::div//select[@data-observeritemtype='messageType']";
    public String preRecordedMessage_Message_SubscriptionStatus = "//option[@selected and text()='Send Employee Voice']/parent::select/parent::div/parent::div/parent::div/following::div//select[@data-observeritemtype='recordedMessages']";
    public String customReminderVoice_Reminder = "Custom Voice Message";
    public String standardReminderVoice_Reminder = "Standard Reminder Voice Message";
    public String newMessage_Voice = "New Message";
    public String preRecordedMessageVoice_Reminder = "Pre-recorded Message";
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

    // Send Employee Email Objects
    public String emailTitlesendEmployeeEmail_SubscriptionStatus = "//option[@selected and text()='Send Employee Email']/ancestor::div[@class='row']/following-sibling::div//input[@data-ruleitemtype='title']";
    public String emailsSendEmployeeEmail_SubscriptionStatus = "//input[@data-ruleitemtype='sendToEmails']";

    // Add Task Objects
    public String daysTillDue_addTask = "//label[text()='Days Till Due']/following-sibling::div//input[@data-observeritemtype='daysDue']";

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

    // Remove Payment Profile Objects
    public String paymentType = "//select[@data-observeritemtype='paymentType']";
    public String CCandACH = "Credit Card and Bank Account";
    public String ACHOnly = "Bank Account Only";
    public String CCOnly = "Credit Card Only";

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
        FindElement
                .elementByAttribute("//option[@selected and text()='" + messageType
                        + "']/parent::select/parent::div/parent::div/parent::div/following-sibling::div//ul/following-sibling::div[@role]",
                InputType.XPath).sendKeys(placeholderMessage);
    }

    // Write Messages type 3
    public void setMessageinAction_Type3(String messageType, String placeholderMessage) {
        FindElement
                .elementByAttribute("//option[text()='" + messageType
                                + "']/parent::select/parent::div/parent::div/parent::div/following-sibling::div//ul/following-sibling::div[@role]",
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

    // Set email title in send employee email for Subscription Status trigger rule
    public void setEmailTitle_SubscriptionStatus(String emailTitle) {
        FindElement.elementByAttribute(emailTitlesendEmployeeEmail_SubscriptionStatus, InputType.XPath)
                .sendKeys(emailTitle);
    }

    // Set email address in send employee email for Subscription Status trigger rule
    public void setEmailAddress_SubscriptionStatus(String emailAddress) {
        FindElement.elementByAttribute(emailsSendEmployeeEmail_SubscriptionStatus, InputType.XPath)
                .sendKeys(emailAddress);
    }

    // Set phone number in Send Employee SMS/Voice
    public void setEmployeePhoneNumber_SubscriptionStatus(String actionType, String phoneNumber) {
        FindElement.elementByAttribute(
                "//option[@selected and text()='" + actionType
                        + "']//ancestor::div/following-sibling::div//input[@data-ruleitemtype='sendToPhones']",
                InputType.XPath).sendKeys(phoneNumber);
    }

    // Set days till due in add task for Subscription Status trigger rule
    public void setDaysTillDueAddTask_SubscriptionStatus(String days) {
        FindElement.elementByAttribute(daysTillDue_addTask, InputType.XPath).sendKeys(days);
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

    // Payment Type in Remove Payment Profile
    public void paymentType_removePaymentProfile(String paymentOption) {
        Utilities.clickElement(paymentType, ElementType.XPath);
        Utilities.waitUntileElementIsVisible("//select[@data-observeritemtype='paymentType']//option[text()='" + paymentOption + "']");
        Utilities.clickElement("//select[@data-observeritemtype='paymentType']//option[text()='" + paymentOption + "']", ElementType.XPath);
    }

    // Remove trigger
    public void removeAction(String ActionType) {
        Utilities.clickElement(
                "//option[@selected and text()='" + ActionType
                        + "']/parent::select/parent::div/parent::div/following-sibling::div[text()='Remove']",
                ElementType.XPath);
    }
}
