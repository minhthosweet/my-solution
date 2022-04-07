package automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab;

import automation.PestRoutes.PageObject.BasePage;
import static automation.PestRoutes.Utilities.Utilities.*;

import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.*;

public class CustomerCommunicationPageObjects extends BasePage {
    //--------------------------------------------------------
    // --  DEFINE PAGE OBJECTS
    //--------------------------------------------------------
    //Customer Preferences Link
    private By lnkPreferences = By.xpath("//*[@id='settingsNav']//li[@for='preferencesBox']/div/p[contains(text(),'Preferences')]");
    public By menulnkCustomerPreferences = By.xpath("//h2[@id='customer']");
    public By lnkCustomerCommunication = By.xpath("//*[@id='preferencesMenu']/div//li[@preferenceboard='customerCommunication']");

    //************** Start of: Customer Preferences Section ********************
    private By lblCustomerPreferencesSection = By.xpath("//*[@id='newPreferenceBody']//div//h3[contains(text(),'Customer Preferences')]");
    private By lnkEdit_CustomerPreferences = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'edit')]");
    private By lnkSave_CustomerPreferences = By.xpath("//*[@id='enclosureCustomerPreferences']");
    private By lnkCancel_CustomerPreferences = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'cancel')]");

    //Default SMS
    private By lblDefaultSMS = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Default SMS')]");
   // private By dropdwnDefaultSMS = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Default SMS')]//select");

    private By dropdwnDefaultSMS = By.xpath("//*[@id='customerPreferences']//select[@name='defaultSMS']");
    //*[@id='customerPreferences']//select[@name='defaultSMS']

    //Default Voice
    private By lblDefaultVoice = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Default Voice')]");
    private By dropdwnDefaultVoice = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Default Voice')]//select");

    //Default Email
    private By lblDefaultEmail = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Default Email')]");
    private By dropdwnDefaultEmail = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Default Email')]//select");

    //Portal Access
    private By lblPortalAccess = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Portal Access')]");
    private By dropdwnPortalAccess = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Portal Access')]//select");

    //Portal File Uploads
    private By lblPortalFileUploads = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Portal File Uploads')]");
    private By dropdwnPortalFileUploads = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Portal File Uploads')]//select");

    //Portal Links Auto Login
    private By lblPortalLinksAutoLogin = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Portal Links Auto Login')]");
    private By dropdwnPortalLinksAutoLogin = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Portal Links Auto Login')]//select");

    //Enable Online Payments
    private By lblEnableOnlinePayments = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Enable Online Payments')]");
    private By dropdwnEnableOnlinePayments = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Enable Online Payments')]//select");

    //Attach Invoice to Email
    private By lblAttachInvoiceToEmail = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Attach Invoice to Email')]");
    private By dropdwnAttachInvoiceToEmail = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Attach Invoice to Email')]//select");

    //Send Recurring Billing Email
    private By lblSendRecurringBillingEmail = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Send Recurring Billing Email')]");
    private By dropdwnSendRecurringBillingEmail = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Send Recurring Billing Email')]//select");

    //Show Invoice on Service Notification/Follow-up
    private By lblShowInvoiceOnServiceNotification_Followup= By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Show Invoice on Service Notification/Follow-up')]");
    private By dropdwnShowInvoiceOnServiceNotification_Followup = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Show Invoice on Service Notification/Follow-up')]//select");

    //Voice Call Secretary
    private By lblVoiceCallSecretary = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Voice Call Secretary')]");
    private By dropdwnVoiceCallSecretary = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Voice Call Secretary')]//select");

    //Email Payment Receipts
    private By lblEmailPaymentReceipts = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Email Payment Receipts')]");
    private By dropdwnEmailPaymentReceipts = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Email Payment Receipts')]//select");

    //SMS Reply Auto Response
    private By lblSMSReplyAutoResponse = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'SMS Reply Auto Response')]");
    private By dropdwnSMSReplyAutoResponse = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'SMS Reply Auto Response')]//select");

     //Include Appointment Notes on Service
    private By lblIncludeAppointmentNotesOnServiceNotification = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Include Appointment Notes on Service Notification)]");
    private By dropdwnIncludeAppointmentNotesOnServiceNotification= By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Include Appointment Notes on Service Notification')]//select");

    //Service Followup Email
    private By lblServiceFollowupEmail_CustPref = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Service Followup Email')]");
    private By dropdwnServiceFollowupEmail_CustPref = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Service Followup Email')]//select");

    //Voice Reminder Confirmation
    private By lblVoiceReminderConfirmation = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Voice Reminder Confirmation')]");
    private By dropdwnVoiceReminderConfirmation = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Voice Reminder Confirmation')]//select");

    //Show Service Notification On Follow-Up
    private By lblShowServiceNotificationOnFollowUp = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Show Service Notification On Follow-Up')]");
    private By dropdwnhowServiceNotificationOnFollowUp = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Show Service Notification On Follow-Up')]//select");

    //Voip service
    private By lblVoipService = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Voip service')]");
    private By dropdwnVoipService = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Voip service')]//select");

    //Voip Host URL
    private By lblVoipHostURL = By.xpath("//*[@id='customerPreferences']//div[contains(text(),'Voip Host URL')]");
    private By inputVoipHostURL = By.xpath("//*[@id='customerPreferences']//input[@name='voipHost']");

    //************** End of: Customer Preferences Section ********************

    //************** Start of: Delivery Settings (SMS, Voice and Email) ********************
    //Section Title, buttons: Edit, Save, Cancel
    private By lblDeliverySettingsSection = By.xpath("//*[@id='newPreferenceBody']//h3[contains(text(),'Delivery Settings (SMS, Voice and Email)')]");
    private By lnkEdit_DeliverySettings = By.xpath("//*[@id='newPreferenceBody']//h3[contains(text(),'Delivery Settings (SMS, Voice and Email)')]/parent::div//form/div[contains(text(),'edit')]");
    private By lnkSave_DeliverySettings = By.xpath("//*[@id='enclosureSMSWindow']");
    private By lnkCancel_DeliverySettings = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureSMSWindow']//following-sibling::div[contains(text(),'cancel')]");

    //SMS Window
    private By lblSMSWindow = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'SMS Window')]");
    private By inputSMSWindow_StartAndEndTimes = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'SMS Window')]//following-sibling::div//input[1]");
    private By smsStartWindowField = By.xpath("//span[text()='SMS Window:']//following::input");
    private By smsEndWindowField = By.xpath("//span[text()='SMS Window:']//following::input//following::input[@name='communicationPreferenceContent[]']");

    //Voice Window
    private By lblVoiceWindow = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Voice Window')]");
    private By inputVoiceWindow_StartAndEndTimes = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Voice Window')]//following-sibling::div//input[1]");

    //Email Window
    private By lblEmailWindow = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Email Window')]");
    private By inputEmailWindow_StartAndEndTimes = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Email Window')]//following-sibling::div//input[1]");

    //Max SMS Per Minute
    private By lblMaxSMSPerMinute = By.xpath(" //*[@id='newPreferenceBody']//div[contains(text(),'Max SMS Per Minute')]");
    private By inputMaxSMSPerMinute= By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Max SMS Per Minute')]//following-sibling::input[starts-with(@name,'communicationPreferenceContent')]");

    //Max Voice Per Minute
    private By lblMaxVoicePerMinute = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Max Voice Per Minute')]");
    private By inputMaxVoicePerMinute= By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Max Voice Per Minute')]//following-sibling::input[starts-with(@name,'communicationPreferenceContent')]");

    //Max Email Per Minute
    private By lblMaxEmailPerMinute = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Max Email Per Minute')]");
    private By inputMaxEmailPerMinute= By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Max Email Per Minute')]//following-sibling::input[starts-with(@name,'communicationPreferenceContent')]");


    //Blocked Days for Sending Text Triggers
    private By lblBlockedDaysForSendingTextTriggers = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Blocked Days for Sending Text Triggers')]");
    private By blockedDaysForTextsLbls = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Blocked Days for Sending Text Triggers')]//div");
    private By blockedDaysForTextsChkboxs = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Blocked Days for Sending Text Triggers')]//div/input[@type='checkbox']");

    private By chkboxBlockedDaysForSendingTextTriggers_Sun = By.xpath("//*[@id='newPreferenceBody']//input[@name='sunTextBlocked']");
    private By chkboxBlockedDaysForSendingTextTriggers_Mon = By.xpath("//*[@id='newPreferenceBody']//input[@name='monTextBlocked']");
    private By chkboxBlockedDaysForSendingTextTriggers_Tue = By.xpath("//*[@id='newPreferenceBody']//input[@name='tueTextBlocked']");
    private By chkboxBlockedDaysForSendingTextTriggers_Wed = By.xpath("//*[@id='newPreferenceBody']//input[@name='wedTextBlocked']");
    private By chkboxBlockedDaysForSendingTextTriggers_Thu = By.xpath("//*[@id='newPreferenceBody']//input[@name='thuTextBlocked']");
    private By chkboxBlockedDaysForSendingTextTriggers_Fri = By.xpath("//*[@id='newPreferenceBody']//input[@name='friTextBlocked']");
    private By chkboxBlockedDaysForSendingTextTriggers_Sat = By.xpath("//*[@id='newPreferenceBody']//input[@name='satTextBlocked']");

    //Blocked Days for Sending Phone Triggers
    private By lblBlockedDaysForSendingPhoneTriggers = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Blocked Days for Sending Phone Triggers')]");
    private By blockedDaysForPhoneLbls = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Blocked Days for Sending Phone Triggers')]//div");
    private By blockedDaysForPhoneChkboxs = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Blocked Days for Sending Phone Triggers')]//div/input[@type='checkbox']");

    private By chkboxBlockedDaysForSendingPhoneTriggers_Sun = By.xpath("//*[@id='newPreferenceBody']//input[@name='sunPhoneBlocked']");
    private By chkboxBlockedDaysForSendingPhoneTriggers_Mon = By.xpath("//*[@id='newPreferenceBody']//input[@name='monPhoneBlocked']");
    private By chkboxBlockedDaysForSendingPhoneTriggers_Tue = By.xpath("//*[@id='newPreferenceBody']//input[@name='tuePhoneBlocked']");
    private By chkboxBlockedDaysForSendingPhoneTriggers_Wed = By.xpath("//*[@id='newPreferenceBody']//input[@name='wedPhoneBlocked']");
    private By chkboxBlockedDaysForSendingPhoneTriggers_Thu = By.xpath("//*[@id='newPreferenceBody']//input[@name='thuPhoneBlocked']");
    private By chkboxBlockedDaysForSendingPhoneTriggers_Fri = By.xpath("//*[@id='newPreferenceBody']//input[@name='friPhoneBlocked']");
    private By chkboxBlockedDaysForSendingPhoneTriggers_Sat = By.xpath("//*[@id='newPreferenceBody']//input[@name='satPhoneBlocked']");

    //********************* End of Delivery Settings (SMS, Voice and Email) ********************
    //Enhanced Answering Machine Detection
    private By lblEnhancedAnsweringMachineDetection = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Enhanced Answering Machine Detection')]");
    private By lnkEdit_EnhancedAnsweringMachineDetection = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureAnsweringMachineDetection']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_EnhancedAnsweringMachineDetection = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureAnsweringMachineDetection']");
    private By lnkCancel_EnhancedAnsweringMachineDetection = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureAnsweringMachineDetection']//following-sibling::div[contains(text(),'cancel')]");
    private By dropdwnEnhancedAnsweringMachineDetection = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Enhanced Answering Machine Detection')]//following-sibling::div/select");

    //Account Statement Tear Sheet
    private By lblAccountStatementTearSheet = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Account Statement Tear Sheet')]");
    private By lnkEdit_AccountStatementTearSheet = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureSnailPerforation']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_AccountStatementTearSheet = By.xpath("//*[@id='enclosureSnailPerforation']");
    private By lnkCancel_AccountStatementTearSheet = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureSnailPerforation']//following-sibling::div[contains(text(),'cancel')]");
    private By dropdwnAccountStatementTearSheet = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Account Statement Tear Sheet')]//following-sibling::div/select");

    //Accounts Receivable Hold Time
    private By lblAccountsReceivableHoldTime = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Accounts Receivable Hold Time')]");
    private By lnkEdit_AccountsReceivableHoldTime = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureHoldTime']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_AccountsReceivableHoldTime = By.xpath("//*[@id='enclosureHoldTime']");
    private By lnkCancel_AccountsReceivableHoldTime = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureHoldTime']//following-sibling::div[contains(text(),'cancel')]");

    //Residential Customers
    private By lblResidentialCustomers = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Residential Customers')]");
    private By inputResidentialCustomers = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Residential Customers')]//input[starts-with(@name,'communicationPreferenceContent')]");

    //Commercial Customers
    private By lblCommercialCustomers = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Commercial Customers')]");
    private By inputCommercialCustomers = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Commercial Customers')]//input[starts-with(@name,'communicationPreferenceContent')]");

    //SMS Appointment Reminder Template
    private By lblSMSAppointmentReminderTemplate = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'SMS Appointment Reminder Template')]");
    private By lnkEdit_SMSAppointmentReminderTemplate = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureSmsTemplate']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_SMSAppointmentReminderTemplate = By.xpath("//*[@id='enclosureSmsTemplate']");
    private By lnkCancel_SMSAppointmentReminderTemplate = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureSmsTemplate']//following-sibling::div[contains(text(),'cancel')]");
    private By textareaSMSAppointmentReminderTemplate = By.xpath("//*[@id='newPreferenceBody']//textarea[@name='smsTemplate']");

    //SMS Reply Auto Response [Confirmed Appointment]
    private By lblSMSReplyAutoResponse_ConfirmAppt = By.xpath("//*[@id='newPreferenceBody']/div/div/div[1]/div[9]/form/div[4]/span[1]/text()')]");
    private By lnkEdit_SMSReplyAutoResponse_ConfirmAppt = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureSmsReplyAutoResponseConfirm']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_SMSReplyAutoResponse_ConfirmAppt = By.xpath("//*[@id='enclosureSmsReplyAutoResponseConfirm']");
    private By lnkCancel_SMSReplyAutoResponse_ConfirmAppt = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureSmsReplyAutoResponseConfirm']//following-sibling::div[contains(text(),'cancel')]");
    private By textareaSMSReplyAutoResponse_ConfirmAppt = By.xpath("//*[@id='newPreferenceBody']/div/div/div[1]/div[9]/form/div[5]/textarea");

    //SMS Reply Auto Response [Default]
    private By lblSMSReplyAutoResponse_Default = By.xpath("//*[@id='newPreferenceBody']/div/div/div[1]/div[10]/form/div[4]/span[1]/text()");
    private By lnkEdit_SMSReplyAutoResponse_Default  = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureSmsReplyAutoResponseDefault']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_SMSReplyAutoResponse_Default  = By.xpath("//*[@id='enclosureSmsReplyAutoResponseDefault']");
    private By lnkCancel_SMSReplyAutoResponse_Default  = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureSmsReplyAutoResponseDefault']//following-sibling::div[contains(text(),'cancel')]");
    private By textareaSMSReplyAutoResponse_Default  = By.xpath("//*[@id='newPreferenceBody']/div/div/div[1]/div[10]/form/div[5]/textarea");

    //Invoice Footer Text
    private By lblInvoiceFooterText = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Invoice Footer Text')]");
    private By lnkEdit_InvoiceFooterText = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureInvoiceFooter']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_InvoiceFooterText = By.xpath("//*[@id='enclosureInvoiceFooter']");
    private By lnkCancel_InvoiceFooterText = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureInvoiceFooter']//following-sibling::div[contains(text(),'cancel')]");
    private By rtextareaInvoiceFooterText = By.xpath("//span[contains(text(),'Invoice Footer Text')]//parent::div/..//div[starts-with(@id,'redactor-uuid')]");

    //Statement and Invoice Footer Text
    private By lblStatementAndInvoiceFooterText = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Statement and Invoice Footer Text')]");
    private By lnkEdit_StatementAndInvoiceFooterText = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureLatePaymentStatement']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_StatementAndInvoiceFooterText = By.xpath("//*[@id='enclosureLatePaymentStatement']");
    private By lnkCancel_StatementAndInvoiceFooterText = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureLatePaymentStatement']//following-sibling::div[contains(text(),'cancel')]");
    private By rtextareaStatementAndInvoiceFooterText = By.xpath("//span[contains(text(),'Statement and Invoice Footer Text')]//parent::div/..//div[starts-with(@id,'redactor-uuid')]");

    //Email Appointment Reminder Template
    private By lblEmailAppointmentReminderTemplate = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Email Appointment Reminder Template')]");
    private By lnkEdit_EmailAppointmentReminderTemplate = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureEmailReminderTemplate']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_EmailAppointmentReminderTemplate = By.xpath("//*[@id='enclosureEmailReminderTemplate']");
    private By lnkCancel_EmailAppointmentReminderTemplate = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureEmailReminderTemplate']//following-sibling::div[contains(text(),'cancel')]");
    private By rtextareaEmailAppointmentReminderTemplate = By.xpath("//span[contains(text(),'Email Appointment Reminder Template')]//parent::div/..//div[starts-with(@id,'redactor-uuid')]");

    //Require Electronic Consent Agreement
    private By lblRequireElectronicConsentAgreement = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Require Electronic Consent Agreement')]");
    private By lnkEdit_RequireElectronicConsentAgreement = By.xpath("//*[@id='newPreferenceBody']//div[@id='requireElectronicConsent']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_RequireElectronicConsentAgreement = By.xpath("//*[@id='requireElectronicConsent']");
    private By lnkCancel_RequireElectronicConsentAgreement = By.xpath("//*[@id='newPreferenceBody']//div[@id='requireElectronicConsent']//following-sibling::div[contains(text(),'cancel')]");

    private By chkboxRequireElectronicConsentAgreement = By.xpath("//*[@id='newPreferenceBody']//input[@name='requireElectronicConsent' and @type='checkbox']");
    private By rtextareaElectronicConsentContent = By.xpath("//div[contains(text(),'Require Electronic Consent Agreement')]//parent::div/..//div[starts-with(@id,'redactor-uuid')]");

    //Agreement Signature Email Subject
    private By lblAgreementSignatureEmailSubject = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Agreement Signature Email Subject')]");
    private By lnkEdit_AgreementSignatureEmailSubject = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureAgreementSignatureSubject']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_AgreementSignatureEmailSubject = By.xpath("//*[@id='enclosureAgreementSignatureSubject']");
    private By lnkCancel_AgreementSignatureEmailSubject = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureAgreementSignatureSubject']//following-sibling::div[contains(text(),'cancel')]");
    private By textareaAgreementSignatureEmailSubject = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Agreement Signature Email Subject')]//parent::div/..//div/textarea[starts-with(@name,'communicationPreferenceContent')]");

    //Agreement Signature Email Body
    private By lblAgreementSignatureEmailBody = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Agreement Signature Email Body')]");
    private By lnkEdit_AgreementSignatureEmailBody = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureAgreementSignatureBody']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_AgreementSignatureEmailBody = By.xpath("//*[@id='enclosureAgreementSignatureBody']");
    private By lnkCancel_AgreementSignatureEmailBody = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureAgreementSignatureBody']//following-sibling::div[contains(text(),'cancel')]");
    private By rtextareaAgreementSignatureEmailBody = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Agreement Signature Email Body')]//parent::div/..//div[starts-with(@id,'redactor-uuid')]");

    //Allow Contracts via SMS Text
    private By lblAllowContractsViaSMSText = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Allow Contracts via SMS Text')]");
    private By lnkEdit_AllowContractsViaSMSText = By.xpath("//*[@id='newPreferenceBody']//div[@id='allowContractsViaSms']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_AllowContractsViaSMSText = By.xpath("//*[@id='allowContractsViaSms']");
    private By lnkCancel_AllowContractsViaSMSText = By.xpath("//*[@id='newPreferenceBody']//div[@id='allowContractsViaSms']//following-sibling::div[contains(text(),'cancel')]");

    private By dropdwnAllowContractsViaSMSText = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Allow Contracts via SMS Text')]//parent::form//div/select[starts-with(@name,'communicationPreferenceContent')]");
    private By lblAgreementSignatureSMSTextBody = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Agreement Signature SMS Text Body')]");
    private By textareaAgreementSignatureSMSTextBody = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Agreement Signature SMS Text Body')]//parent::div/..//div/textarea[starts-with(@name,'communicationPreferenceContent')]");

    //Service Follow-up Email
    private By lblServiceFollowupEmail = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Service Follow-up Email')]");
    private By lnkEdit_ServiceFollowupEmail = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureFollowupTemplate']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_ServiceFollowupEmail = By.xpath("//*[@id='enclosureFollowupTemplate']");
    private By lnkCancel_ServiceFollowupEmail = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureFollowupTemplate']//following-sibling::div[contains(text(),'cancel')]");
    private By rtextareaServiceFollowupEmail = By.xpath("//span[contains(text(),'Service Follow-up Email')]//parent::div/..//div[starts-with(@id,'redactor-uuid')]");

    //Service Notifications Notes / Caution Statements
    private By lblServiceNotificationsNotes_CautionStatements = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Service Notifications Notes / Caution Statements')]");
    private By lnkEdit_ServiceNotificationsNotes_CautionStatements = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureCautionStatements']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_ServiceNotificationsNotes_CautionStatements = By.xpath("//*[@id='enclosureCautionStatements']");
    private By lnkCancel_ServiceNotificationsNotes_CautionStatements = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureCautionStatements']//following-sibling::div[contains(text(),'cancel')]");
    private By rtextareaServiceNotificationsNotes_CautionStatements = By.xpath("//span[contains(text(),'Service Notifications Notes / Caution Statements')]//parent::div/..//div[starts-with(@id,'redactor-uuid')]");

    //Custom Review Header
    private By lblCustomReviewHeader = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Custom Review Header')]");
    private By lnkEdit_CustomReviewHeader = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureCustomReviewHeader']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_CustomReviewHeader = By.xpath("//*[@id='enclosureCustomReviewHeader']");
    private By lnkCancel_CustomReviewHeader = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureCustomReviewHeader']//following-sibling::div[contains(text(),'cancel')]");
    private By textareaCustomReviewHeader = By.xpath("//*[@id='newPreferenceBody']//div//textarea[@name ='customReviewHeader']");

    //Custom Review Message
    private By lblCustomReviewMessage = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Custom Review Message')]");
    private By lnkEdit_CustomReviewMessage = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureReviewMessage']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_CustomReviewMessage = By.xpath("//*[@id='enclosureReviewMessage']");
    private By lnkCancel_CustomReviewMessage = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureReviewMessage']//following-sibling::div[contains(text(),'cancel')]");
    private By rtextareaCustomReviewMessage = By.xpath("//span[contains(text(),'Custom Review Message')]//parent::div/..//div[starts-with(@id,'redactor-uuid')]");

    //Review link option and Custom Review Link
    private By lblReviewlinkOption = By.xpath("//*[@id='newPreferenceBody']//div[contains(text(),'Review link option')]");
    private By lnkEdit_ReviewlinkOption = By.xpath("//*[@id='newPreferenceBody']//div[@id='displayCustomReviewLink']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_ReviewlinkOption = By.xpath("//*[@id='displayCustomReviewLink']");
    private By lnkCancel_ReviewlinkOption = By.xpath("//*[@id='newPreferenceBody']//div[@id='displayCustomReviewLink']//following-sibling::div[contains(text(),'cancel')]");
    private By dropdwnReviewlinkOption = By.xpath("//*[@id='newPreferenceBody']//select[@name='displayCustomReviewLink']");
    private By inputCustomReviewLink = By.xpath("//*[@id='reviewLink']");

    //Custom Email Blurb
    private By lblCustomEmailBlurb = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Custom Email Blurb')]");
    private By lnkEdit_CustomEmailBlurb = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureEmailBlurb']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_CustomEmailBlurb = By.xpath("//*[@id='enclosureEmailBlurb']");
    private By lnkCancel_CustomEmailBlurb = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureEmailBlurb']//following-sibling::div[contains(text(),'cancel')]");
    private By rtextareaCustomEmailBlurb = By.xpath("//span[contains(text(),'Custom Email Blurb')]//parent::div/..//div[starts-with(@id,'redactor-uuid')]");

    //Customer Portal Sign Agreement Welcome Message
    private By lblCustomerPortalSignAgreementWelcomeMessage = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Customer Portal Sign Agreement Welcome Message')]");
    private By lnkEdit_CustomerPortalSignAgreementWelcomeMessage = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosurePortalWelcomeMessage']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_CustomerPortalSignAgreementWelcomeMessage = By.xpath("//*[@id='enclosurePortalWelcomeMessage']");
    private By lnkCancel_CustomerPortalSignAgreementWelcomeMessage = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosurePortalWelcomeMessage']//following-sibling::div[contains(text(),'cancel')]");
    private By rtextareaCustomerPortalSignAgreementWelcomeMessage= By.xpath("//span[contains(text(),'Customer Portal Sign Agreement Welcome Message')]//parent::div/..//div[starts-with(@id,'redactor-uuid')]");

    //Customer Portal Sign Form Welcome Message
    private By lblCustomerPortalSignFormWelcomeMessage = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'Customer Portal Sign Form Welcome Message')]");
    private By lnkEdit_CustomerPortalSignFormWelcomeMessage = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosurePortalWelcomeMessageForm']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_CustomerPortalSignFormWelcomeMessage = By.xpath("//*[@id='enclosurePortalWelcomeMessageForm']");
    private By lnkCancel_CustomerPortalSignFormWelcomeMessage = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosurePortalWelcomeMessageForm']//following-sibling::div[contains(text(),'cancel')]");
    private By rtextareaCustomerPortalSignFormWelcomeMessage = By.xpath("//span[contains(text(),'Customer Portal Sign Form Welcome Message')]//parent::div/..//div[starts-with(@id,'redactor-uuid')]");

    //SalesRoutes and Customer Portal Contract Agreement Message
    private By lblSalesRoutesAndCustomerPortalContractAgreementMessage = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'SalesRoutes and Customer Portal Contract Agreement Message')]");
    private By lnkEdit_SalesRoutesAndCustomerPortalContractAgreementMessage = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureContractAgreementLabel']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_SalesRoutesAndCustomerPortalContractAgreementMessage = By.xpath("//*[@id='enclosureContractAgreementLabel']");
    private By lnkCancel_SalesRoutesAndCustomerPortalContractAgreementMessage = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureContractAgreementLabel']//following-sibling::div[contains(text(),'cancel')]");
    private By rtextareaSalesRoutesAndCustomerPortalContractAgreementMessage = By.xpath("//span[contains(text(),'SalesRoutes and Customer Portal Contract Agreement Message')]//parent::div/..//div[starts-with(@id,'redactor-uuid')]");

    //SalesRoutes and Customer Portal Auto Payment Message
    private By lblSalesRoutesAndCustomerPortalAutoPaymentMessage = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'SalesRoutes and Customer Portal Auto Payment Message')]");
    private By lnkEdit_SalesRoutesAndCustomerPortalAutoPaymentMessage = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureApayInitialLabel']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_SalesRoutesAndCustomerPortalAutoPaymentMessage = By.xpath("//*[@id='enclosureApayInitialLabel']");
    private By lnkCancel_SalesRoutesAndCustomerPortalAutoPaymentMessage = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureApayInitialLabel']//following-sibling::div[contains(text(),'cancel')]");
    private By rtextareaSalesRoutesAndCustomerPortalAutoPaymentMessage = By.xpath("//span[contains(text(),'SalesRoutes and Customer Portal Auto Payment Message')]//parent::div/..//div[starts-with(@id,'redactor-uuid')]");

    //CA WDO Pre Findings and Recommendations Legal
    private By lblCAWDOPreFindingsAndRecommendationsLegal = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'CA WDO Pre Findings and Recommendations Legal')]");
    private By lnkEdit_CAWDOPreFindingsAndRecommendationsLegal = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureTermiteTerms']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_CAWDOPreFindingsAndRecommendationsLegal = By.xpath("//*[@id='enclosureTermiteTerms']");
    private By lnkCancel_CAWDOPreFindingsAndRecommendationsLegal = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureTermiteTerms']//following-sibling::div[contains(text(),'cancel')]");
    private By rtextareaCAWDOPreFindingsAndRecommendationsLegal = By.xpath("//span[contains(text(),'CA WDO Pre Findings and Recommendations Legal')]//parent::div/..//div[starts-with(@id,'redactor-uuid')]");

    //CA WDO Post Findings and Recommendations Legal
    private By lblCAWDOPostFindingsAndRecommendationsLegal = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'CA WDO Post Findings and Recommendations Legal')]");
    private By lnkEdit_CAWDOPostFindingsAndRecommendationsLegal = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureTermiteNotices']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_CAWDOPostFindingsAndRecommendationsLegal = By.xpath("//*[@id='enclosureTermiteNotices']");
    private By lnkCancel_CAWDOPostFindingsAndRecommendationsLegal = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureTermiteNotices']//following-sibling::div[contains(text(),'cancel')]");
    private By rtextareaCAWDOPostFindingsAndRecommendationsLegal = By.xpath("//span[contains(text(),'CA WDO Post Findings and Recommendations Legal')]//parent::div/..//div[starts-with(@id,'redactor-uuid')]");

    //CA WDO Work Authorization Legal
    private By lblCAWDOWorkAuthorizationLegal = By.xpath("//*[@id='newPreferenceBody']//span[contains(text(),'CA WDO Work Authorization Legal')]");
    private By lnkEdit_CAWDOWorkAuthorizationLegal = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureWorkAuthLegal']//preceding-sibling::div[contains(text(),'edit')]");
    private By lnkSave_CAWDOWorkAuthorizationLegal = By.xpath("//*[@id='enclosureWorkAuthLegal']");
    private By lnkCancel_CAWDOWorkAuthorizationLegal = By.xpath("//*[@id='newPreferenceBody']//div[@id='enclosureWorkAuthLegal']//following-sibling::div[contains(text(),'cancel')]");
    private By rtextareaCAWDOWorkAuthorizationLegal = By.xpath("//span[contains(text(),'CA WDO Work Authorization Legal')]//parent::div/..//div[starts-with(@id,'redactor-uuid')]");

    //--------------------------------------------------------
    // -- CONSTANTS & VARIABLES
    // --------------------------------------------------------
    public final String PREFERENCES_PAGE_TITLE = "Office Info";

    //TestData HashMap Keys
    protected final String keyDefaultSMS = "Default SMS";
    protected final String keyDefaultVoice = "Default Voice";
    protected final String keyDefaultEmail = "Default Email";
    protected final String keyPortalAccess = "Portal Access";
    protected final String keyPortalFileUploads = "Portal File Uploads";
    protected final String keyPortalLinksAutoLogin = "Portal Links Auto Login";
    protected final String keyEnableOnlinePayments = "Enable Online Payments";
    protected final String keyAttachInvoiceToEmail = "Attach Invoice to Email";
    protected final String keySendRecurringBillingEmail = "Send Recurring Billing Email";
    protected final String keyShowInvoiceOnServiceNotificationFollowup = "Show Invoice on Service Notification/Follow-up";
    protected final String keyVoiceCallSecretary = "Voice Call Secretary";
    protected final String keyEmailPaymentReceipts = "Email Payment Receipts";
    protected final String keySMSReplyAutoResponse = "SMS Reply Auto Response";
    protected final String keyIncludeAppointmentNotesOnServiceNotification = "Include Appointment Notes on Service Notification";
    protected final String keyServiceFollowupEmail_CustomPref = "Service Followup Email";
    protected final String keyVoiceReminderConfirmation = "Voice Reminder Confirmation";
    protected final String keyShowServiceNotificationOnFollowUp = "Show Service Notification On Follow-Up";
    protected final String keyVoipService = "Voip service";
    protected final String keyVoipHostURL= "Voip Host URL";
    protected final String keyEmailWindow_sTime = "Email Window_sTime";
    protected final String keyEmailWindow_eTime= "Email Window_eTime";
    protected final String keyVoiceWindow_sTime = "Voice Window_sTime";
    protected final String keyVoiceWindow_eTime = "Voice Window_eTime";
    protected final String keySMSWindow_sTime = "SMS Window_sTime";
    protected final String keySMSWindow_eTime = "SMS Window_eTime";
    protected final String keyMaxEmailPerMinute = "Max Email Per Minute";
    protected final String keyMaxVoicePerMinute = "Max Voice Per Minute";
    protected final String keyMaxSMSPerMinute = "Max SMS Per Minute";
    protected final String keyBlockedDaysForSendingTextTriggers = "Blocked Days for Sending Text Triggers";
    protected final String keyBlockedDaysForSendingPhoneTriggers = "Blocked Days for Sending Phone Triggers";
    protected final String keyEnhancedAnsweringMachineDetection = "Enhanced Answering Machine Detection";
    protected final String keyAccountStatementTearSheet = "Account Statement Tear Sheet";
    protected final String keyResidentialCustomers = "Residential Customers";
    protected final String keyCommercialCustomers = "Commercial Customers";
    protected final String keySMSAppointmentReminderTemplate = "SMS Appointment Reminder Template";
    protected final String keySMSReplyAutoResponseConfirmedAppt = "SMS Reply Auto Response_Confirmed Appointment";
    protected final String keySMSReplyAutoResponseDefault = "SMS Reply Auto Response_Default";
    protected final String keyInvoiceFooterText = "Invoice Footer Text";
    protected final String keyStatementAndInvoiceFooterText = "Statement and Invoice Footer Text";
    protected final String keyEmailAppointmentReminderTemplate = "Email Appointment Reminder Template";
    protected final String keyRequireElectronicConsentAgreement = "Require Electronic Consent Agreement";
    protected final String keyElectronicConsentContent = "Electronic Consent Content";
    protected final String keyAgreementSignatureEmailSubject = "Agreement Signature Email Subject";
    protected final String keyAgreementSignatureEmailBody = "Agreement Signature Email Body";
    protected final String keyAllowContractsViaSMSText = "Allow Contracts via SMS Text";
    protected final String keyAgreementSignatureSMSTextBody = "Agreement Signature SMS Text Body";
    protected final String keyServiceFollowupEmail = "Service Follow-up Email";
    protected final String keyServiceNotificationsNotesCautionStatements = "Service Notifications Notes / Caution Statements";
    protected final String keyCustomReviewHeader = "Custom Review Header";
    protected final String keyCustomReviewMessage = "Custom Review Message";
    protected final String keyReviewlinkOption = "Review link option";
    protected final String keyCustomReviewLink = "Custom Review Link";
    protected final String keyCustomEmailBlurb ="Custom Email Blurb";
    protected final String keyCustomerPortalSignAgreementWelcomeMessage ="Customer Portal Sign Agreement Welcome Message";
    protected final String keyCustomerPortalSignFormWelcomeMessage ="Customer Portal Sign Form Welcome Message";
    protected final String keySalesRoutesAndCustomerPortalContractAgreementMessage ="SalesRoutes and Customer Portal Contract Agreement Message";
    protected final String keySalesRoutesAndCustomerPortalAutoPaymentMessage ="SalesRoutes and Customer Portal Auto Payment Message";
    protected final String keyCAWDOPreFindingsAndRecommendationsLegal = "CA WDO Pre Findings and Recommendations Legal";
    protected final String keyCAWDOPostFindingsAndRecommendationsLegal = "CA WDO Post Findings and Recommendations Legal";
    protected final String keyCAWDOWorkAuthorizationLegal = "CA WDO Work Authorization Legal";

    //Field Types on the page
    public final String DROP_DOWN = "DROP DOWN";
    public final String TEXTAREA = "TEXTAREA";
    public final String INPUT = "INPUT";
    public final String RTEXTAREA = "RTEXTAREA";

    //--------------------------------------------------------
    // -- METHODS
    //--------------------------------------------------------
    public void loadCustomCommunciationTab() throws InterruptedException {
        //Load Preferences Page
        Utilities.click(lnkPreferences);

        //Verify Page is loaded and navigate to the "Customer Communication" Screen
        if( Deprecated.isTextPresent(PREFERENCES_PAGE_TITLE)) {
            //Close the Default Displayed Menu Section (Office Settings) on initial load
            By lnkOfficeSettings = By.xpath("//h2[@id='office']");
            Utilities.click(lnkOfficeSettings);

            //Allow menu to close
            Thread.sleep(2000);

            //Click the (Customer Preferences > Communication) links in the Left Navigation Menu
            Utilities.click(menulnkCustomerPreferences);
            Utilities.click(lnkCustomerCommunication);
        }
        else
        {
           Assert.fail("Customer Communication' Screen Page DID NOT LOAD");
        }
    }//loadCustomCommunciationScreen()

    public void updateAllFields(HashMap<String, String> testData) throws Exception {
        //Update "Customer Preferences" Section
        updateCustomerPreferencesSection(testData);

        //Update "Delivery Settings (SMS, Voice and Email)" Section
        updateDeliverySettingsSection(testData);

        //Enhanced Answering Machine Detection
        updateField(String.valueOf(testData.get(keyEnhancedAnsweringMachineDetection).toString()),dropdwnEnhancedAnsweringMachineDetection,DROP_DOWN,lnkEdit_EnhancedAnsweringMachineDetection);
        saveField(lnkSave_EnhancedAnsweringMachineDetection);

        //Account Statement Tear Sheet
        updateField(String.valueOf(testData.get(keyAccountStatementTearSheet).toString()),dropdwnAccountStatementTearSheet,DROP_DOWN,lnkEdit_AccountStatementTearSheet);
        saveField(lnkSave_AccountStatementTearSheet);
        Utilities.acceptAlert();

        //Accounts Receivable Hold Time - Residential Customer
        updateField(String.valueOf(testData.get(keyResidentialCustomers).toString()),inputResidentialCustomers,INPUT,lnkEdit_AccountsReceivableHoldTime);
        saveField(lnkSave_AccountsReceivableHoldTime);

        //Accounts Receivable Hold Time - Commercial Customers
        updateField(String.valueOf(testData.get(keyCommercialCustomers).toString()),inputCommercialCustomers,INPUT,lnkEdit_AccountsReceivableHoldTime);
        saveField(lnkSave_AccountsReceivableHoldTime);

        //SMS Appointment Reminder Template
        updateField(String.valueOf(testData.get(keySMSAppointmentReminderTemplate).toString()),textareaSMSAppointmentReminderTemplate,TEXTAREA,lnkEdit_SMSAppointmentReminderTemplate);
        saveField(lnkSave_SMSAppointmentReminderTemplate);

        //SMS Reply Auto Response [Confirmed Appointment]
        updateField(String.valueOf(testData.get(keySMSReplyAutoResponseConfirmedAppt).toString()),textareaSMSReplyAutoResponse_ConfirmAppt,TEXTAREA,lnkEdit_SMSReplyAutoResponse_ConfirmAppt);
        saveField(lnkSave_SMSReplyAutoResponse_ConfirmAppt);

        //SMS Reply Auto Response [Default]
        updateField(String.valueOf(testData.get(keySMSReplyAutoResponseDefault).toString()),textareaSMSReplyAutoResponse_Default,TEXTAREA,lnkEdit_SMSReplyAutoResponse_Default);
        saveField(lnkSave_SMSReplyAutoResponse_Default);

        //Invoice Footer Text
        updateField(String.valueOf(testData.get(keyInvoiceFooterText).toString()),rtextareaInvoiceFooterText,RTEXTAREA,lnkEdit_InvoiceFooterText);
        saveField(lnkSave_InvoiceFooterText);

        //Statement and Invoice Footer Text
        updateField(String.valueOf(testData.get(keyStatementAndInvoiceFooterText).toString()),rtextareaStatementAndInvoiceFooterText,RTEXTAREA,lnkEdit_StatementAndInvoiceFooterText);
        saveField(lnkSave_StatementAndInvoiceFooterText);

        //Email Appointment Reminder Template
        updateField(String.valueOf(testData.get(keyEmailAppointmentReminderTemplate).toString()),rtextareaEmailAppointmentReminderTemplate,RTEXTAREA,lnkEdit_EmailAppointmentReminderTemplate);
        saveField(lnkSave_EmailAppointmentReminderTemplate);

        //Require Electronic Consent Agreement -  Electronic Consent Content
        Deprecated.scrollToElementJS(lnkEdit_RequireElectronicConsentAgreement); //(Note: Navigated to the previous field to make sure the desired field is displayed)
        Utilities.click(lnkEdit_RequireElectronicConsentAgreement);
        Utilities.checkBox(chkboxRequireElectronicConsentAgreement);
        Deprecated.type(String.valueOf(testData.get(keyElectronicConsentContent).toString()),rtextareaElectronicConsentContent);
        saveField(lnkSave_RequireElectronicConsentAgreement);
        //updateField(String.valueOf(testData.get(keyElectronicConsentContent).toString()),rtextareaElectronicConsentContent,RTEXTAREA,lnkEdit_InvoiceFooterText);

        //Agreement Signature Email Subject
        updateField(String.valueOf(testData.get(keyAgreementSignatureEmailSubject).toString()),textareaAgreementSignatureEmailSubject,TEXTAREA,lnkEdit_AgreementSignatureEmailSubject);
        saveField(lnkSave_AgreementSignatureEmailSubject);

        //Agreement Signature Email Body
        updateField(String.valueOf(testData.get(keyAgreementSignatureEmailBody).toString()),rtextareaAgreementSignatureEmailBody,RTEXTAREA,lnkEdit_AgreementSignatureEmailBody);
        saveField(lnkSave_AgreementSignatureEmailBody);

        //Allow Contracts via SMS Text - dropdown
        updateField(String.valueOf(testData.get(keyAllowContractsViaSMSText).toString()),dropdwnAllowContractsViaSMSText,DROP_DOWN,lnkEdit_AllowContractsViaSMSText);
        saveField(lnkSave_AllowContractsViaSMSText);

        //Agreement Signature SMS Text Body
        updateField(String.valueOf(testData.get(keyAgreementSignatureSMSTextBody).toString()),textareaAgreementSignatureSMSTextBody,TEXTAREA,lnkEdit_AllowContractsViaSMSText);
        saveField(lnkSave_AllowContractsViaSMSText);

        //Service Follow-up Email
        updateField(String.valueOf(testData.get(keyServiceFollowupEmail).toString()),rtextareaServiceFollowupEmail,RTEXTAREA,lnkEdit_ServiceFollowupEmail);
        saveField(lnkSave_ServiceFollowupEmail);


        //Service Notifications Notes / Caution Statements
        updateField(String.valueOf(testData.get(keyServiceNotificationsNotesCautionStatements).toString()),rtextareaServiceNotificationsNotes_CautionStatements,RTEXTAREA,lnkEdit_ServiceNotificationsNotes_CautionStatements);
        saveField(lnkSave_ServiceNotificationsNotes_CautionStatements);

        //Custom Review Header
        updateField(String.valueOf(testData.get(keyCustomReviewHeader).toString()),textareaCustomReviewHeader,TEXTAREA,lnkEdit_CustomReviewHeader);
        saveField(lnkSave_CustomReviewHeader);

        //Custom Review Message
        updateField(String.valueOf(testData.get(keyCustomReviewMessage).toString()),rtextareaCustomReviewMessage,RTEXTAREA,lnkEdit_CustomReviewMessage);
        saveField(lnkSave_CustomReviewMessage);

        //Review link option and Custom Review Link
        updateField(String.valueOf(testData.get(keyReviewlinkOption).toString()),dropdwnReviewlinkOption,DROP_DOWN,lnkEdit_ReviewlinkOption);
        saveField(lnkSave_ReviewlinkOption);

        updateField(String.valueOf(testData.get(keyCustomReviewLink).toString()),inputCustomReviewLink,INPUT,null);
        saveField(lnkSave_ReviewlinkOption);

        //Custom Email Blurb
        updateField(String.valueOf(testData.get(keyCustomEmailBlurb).toString()),rtextareaCustomEmailBlurb,RTEXTAREA,lnkEdit_CustomEmailBlurb);
        saveField(lnkSave_CustomEmailBlurb);

        //Customer Portal Sign Agreement Welcome Message
        updateField(String.valueOf(testData.get(keyCustomerPortalSignAgreementWelcomeMessage).toString()),rtextareaCustomerPortalSignAgreementWelcomeMessage,RTEXTAREA,lnkEdit_CustomerPortalSignAgreementWelcomeMessage);
        saveField(lnkSave_CustomerPortalSignAgreementWelcomeMessage);

        //Customer Portal Sign Form Welcome Message
        updateField(String.valueOf(testData.get(keyCustomerPortalSignFormWelcomeMessage).toString()),rtextareaCustomerPortalSignFormWelcomeMessage,RTEXTAREA,lnkEdit_CustomerPortalSignFormWelcomeMessage);
        saveField(lnkSave_CustomerPortalSignFormWelcomeMessage);

        //SalesRoutes and Customer Portal Contract Agreement Message
        updateField(String.valueOf(testData.get(keySalesRoutesAndCustomerPortalContractAgreementMessage).toString()),rtextareaSalesRoutesAndCustomerPortalContractAgreementMessage,RTEXTAREA,lnkEdit_SalesRoutesAndCustomerPortalContractAgreementMessage);
        saveField(lnkSave_SalesRoutesAndCustomerPortalContractAgreementMessage);

        //SalesRoutes and Customer Portal Auto Payment Message
        updateField(String.valueOf(testData.get(keySalesRoutesAndCustomerPortalAutoPaymentMessage).toString()),rtextareaSalesRoutesAndCustomerPortalAutoPaymentMessage,RTEXTAREA,lnkEdit_SalesRoutesAndCustomerPortalAutoPaymentMessage);
        saveField(lnkSave_SalesRoutesAndCustomerPortalAutoPaymentMessage);

/* Comment out CA WDO- fields because they are not configured in all offices
        //CA WDO Pre Findings and Recommendations Legal
        updateField(String.valueOf(testData.get(keyCAWDOPreFindingsAndRecommendationsLegal).toString()),rtextareaCAWDOPreFindingsAndRecommendationsLegal,RTEXTAREA,lnkEdit_CAWDOPreFindingsAndRecommendationsLegal);
        saveField(lnkSave_CAWDOPreFindingsAndRecommendationsLegal);

        //CA WDO Post Findings and Recommendations Legal
        updateField(String.valueOf(testData.get(keyCAWDOPostFindingsAndRecommendationsLegal).toString()),rtextareaCAWDOPostFindingsAndRecommendationsLegal,RTEXTAREA,lnkEdit_CAWDOPostFindingsAndRecommendationsLegal);
        saveField(lnkSave_CAWDOPostFindingsAndRecommendationsLegal);

       //CA WDO Work Authorization Legal
       updateField(String.valueOf(testData.get(keyCAWDOWorkAuthorizationLegal).toString()),rtextareaCAWDOWorkAuthorizationLegal,TEXTAREA,lnkEdit_CAWDOWorkAuthorizationLegal);
*/       saveField(lnkSave_CAWDOWorkAuthorizationLegal);

    }//updateAllFields()

    public void updateTimeWindow(By timeRange, String startTime, String endTime, By editBtn) throws Exception{
        List<WebElement> windowTimes= Utilities.locateAll(timeRange);
        try {
            //Scroll to and click Edit
            Deprecated.scrollToElementJS(editBtn);
            Utilities.click(editBtn);

            //Enter Start Time
            Deprecated.type(startTime, windowTimes.get(0));
            //Enter Start Time
            Deprecated.type(endTime, windowTimes.get(1));
        }catch (Exception exp){}

    }//updateTimeWindow()

    public void updateCustomerPreferencesSection(HashMap<String, String> testData) throws Exception {
        //Default SMS
        updateField(String.valueOf(testData.get(keyDefaultSMS).toString()),dropdwnDefaultSMS,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Default Voice
        updateField(String.valueOf(testData.get(keyDefaultVoice).toString()),dropdwnDefaultVoice,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Default Email
        updateField(String.valueOf(testData.get(keyDefaultEmail).toString()),dropdwnDefaultEmail,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Portal Access
        updateField(String.valueOf(testData.get(keyPortalAccess).toString()),dropdwnPortalAccess,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Portal File Uploads
        updateField(String.valueOf(testData.get(keyPortalFileUploads).toString()),dropdwnPortalFileUploads,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Portal Links Auto Login
        updateField(String.valueOf(testData.get(keyPortalLinksAutoLogin).toString()),dropdwnPortalLinksAutoLogin,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Enable Online Payments
        updateField(String.valueOf(testData.get(keyEnableOnlinePayments).toString()),dropdwnEnableOnlinePayments,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Attach Invoice to Email
        updateField(String.valueOf(testData.get(keyAttachInvoiceToEmail).toString()),dropdwnAttachInvoiceToEmail,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Send Recurring Billing Email
        updateField(String.valueOf(testData.get(keySendRecurringBillingEmail).toString()),dropdwnSendRecurringBillingEmail,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Show Invoice on Service Notification/Follow-up
        updateField(String.valueOf(testData.get(keyShowInvoiceOnServiceNotificationFollowup).toString()),dropdwnShowInvoiceOnServiceNotification_Followup,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Voice Call Secretary
        updateField(String.valueOf(testData.get(keyVoiceCallSecretary).toString()),dropdwnVoiceCallSecretary,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Email Payment Receipts
        updateField(String.valueOf(testData.get(keyEmailPaymentReceipts).toString()),dropdwnEmailPaymentReceipts,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //SMS Reply Auto Response
        updateField(String.valueOf(testData.get(keySMSReplyAutoResponse).toString()),dropdwnSMSReplyAutoResponse,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Include Appointment Notes on Service Notification
        updateField(String.valueOf(testData.get(keyIncludeAppointmentNotesOnServiceNotification).toString()),dropdwnIncludeAppointmentNotesOnServiceNotification,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Service Followup Email
        updateField(String.valueOf(testData.get(keyServiceFollowupEmail_CustomPref).toString()),dropdwnServiceFollowupEmail_CustPref,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Voice Reminder Confirmation
        updateField(String.valueOf(testData.get(keyVoiceReminderConfirmation).toString()),dropdwnVoiceReminderConfirmation,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Show Service Notification On Follow-Up
        updateField(String.valueOf(testData.get(keyShowServiceNotificationOnFollowUp).toString()),dropdwnShowInvoiceOnServiceNotification_Followup,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Voip service
        updateField(String.valueOf(testData.get(keyVoipService).toString()),dropdwnVoipService,DROP_DOWN,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

        //Voip Host URL
        updateField(String.valueOf(testData.get(keyVoipHostURL).toString()),inputVoipHostURL,INPUT,lnkEdit_CustomerPreferences);
        saveField(lnkSave_CustomerPreferences);

    }//updateCustomerPreferencesSection()

    public void updateDeliverySettingsSection(HashMap<String, String> testData) throws Exception {
        //Scroll to the "Delivery Settings (SMS, Voice and Email)" Section
        Deprecated.scrollToElementJS(lblDeliverySettingsSection);

        //SMS Window
        updateTimeWindow(inputSMSWindow_StartAndEndTimes,String.valueOf(testData.get(keySMSWindow_sTime).toString()),
                String.valueOf(testData.get(keySMSWindow_eTime).toString()),lnkEdit_DeliverySettings);
        saveField(lnkSave_DeliverySettings);

        //Voice Window
        updateTimeWindow(inputVoiceWindow_StartAndEndTimes,String.valueOf(testData.get(keyVoiceWindow_sTime).toString()),
                String.valueOf(testData.get(keyVoiceWindow_eTime).toString()),lnkEdit_DeliverySettings);
        saveField(lnkSave_DeliverySettings);

        //Email Window
        updateTimeWindow(inputEmailWindow_StartAndEndTimes,String.valueOf(testData.get(keyEmailWindow_sTime).toString()),
                String.valueOf(testData.get(keyEmailWindow_eTime).toString()),lnkEdit_DeliverySettings);
        saveField(lnkSave_DeliverySettings);

        //Max SMS Per Minute
        updateTimeWindow(inputMaxSMSPerMinute,String.valueOf(testData.get(keyMaxSMSPerMinute).toString()),
                String.valueOf(testData.get(keyMaxSMSPerMinute).toString()),lnkEdit_DeliverySettings);
        saveField(lnkSave_DeliverySettings);

        //Max Voice Per Minute
        updateTimeWindow(inputMaxVoicePerMinute,String.valueOf(testData.get(keyMaxVoicePerMinute).toString()),
                String.valueOf(testData.get(keyMaxVoicePerMinute).toString()),lnkEdit_DeliverySettings);
        saveField(lnkSave_DeliverySettings);

        //Max Email Per Minute
        updateTimeWindow(inputMaxEmailPerMinute,String.valueOf(testData.get(keyMaxEmailPerMinute).toString()),
                String.valueOf(testData.get(keyMaxEmailPerMinute).toString()),lnkEdit_DeliverySettings);
        saveField(lnkSave_DeliverySettings);

        //Blocked Days for Sending Text Triggers
        blockDays(blockedDaysForTextsLbls, blockedDaysForTextsChkboxs, keyBlockedDaysForSendingTextTriggers,String.valueOf(testData.get(keyBlockedDaysForSendingTextTriggers).toString()),lnkEdit_DeliverySettings);
        saveField(lnkSave_DeliverySettings);

        //Blocked Days for Sending Phone Triggers
        blockDays(blockedDaysForPhoneLbls,blockedDaysForPhoneChkboxs,keyBlockedDaysForSendingPhoneTriggers, String.valueOf(testData.get(keyBlockedDaysForSendingPhoneTriggers).toString()),lnkEdit_DeliverySettings);
        saveField(lnkSave_DeliverySettings);
    } //updateDeliverySettingsSection()

    public boolean updateField (String value,By webElmField, String fieldType, By editBtn) {
        boolean boolUpdateFlag = false;

        //Scroll to and click Edit Button]
        if (editBtn != null) {
            Deprecated.scrollToElementJS(editBtn);
            Utilities.click(editBtn);
        }


        //Locate field and update
        try {
            switch (fieldType) {
                case DROP_DOWN:
                    Utilities.selectByText(webElmField, value);
                    boolUpdateFlag = true;
                break;

                case INPUT:
                case TEXTAREA:
                case RTEXTAREA:
                    //System.out.println("*******Input Data: " + value);
                    Deprecated.type(value,webElmField);
                    boolUpdateFlag = true;
                break;

                default:
                    System.out.println("**********updateField(): ERROR: Field Type (" + fieldType + ") does not exist." );
            }
        }catch(Exception exp){
            exp.printStackTrace();
        }

        return boolUpdateFlag;
    }//updateField()

    public void blockDays (By daysToBlockLbls, By daysToBlockChks ,String channel, String days, By editBtn) {
        String[] blockDayArray = days.split(",");
        List<WebElement> blockedDayLbls = Utilities.locateAll(daysToBlockLbls);
        List<WebElement> blockedDayChkboxs = Utilities.locateAll(daysToBlockChks);
        String  xpathDayStr = "";

        //Scroll to and click Edit Button
        Deprecated.scrollToElementJS(lblDeliverySettingsSection);
        Utilities.click(editBtn);

        //Check blocked day...
        for ( String strDay : blockDayArray)
        {
            for(WebElement dayElm : blockedDayLbls){
                int indx=0;
                if (dayElm.getText().equalsIgnoreCase(strDay))
                {
                    switch (channel) {
                        case keyBlockedDaysForSendingTextTriggers:
                        xpathDayStr = "//*[@id='newPreferenceBody']//input[@name='" + strDay.toLowerCase() + "TextBlocked']";
                        break;

                        case keyBlockedDaysForSendingPhoneTriggers:
                            xpathDayStr = "//*[@id='newPreferenceBody']//input[@name='" + strDay.toLowerCase() + "PhoneBlocked']";
                        break;
                    }
                    if(!Deprecated.isChecked(xpathDayStr))
                        Deprecated.clickElement(xpathDayStr);
                    break;
                }
             }
        }
     }//blockDays()

    public boolean saveField(By saveBtn) {
        try {
            Deprecated.scrollToElementJS(saveBtn);
            Utilities.click(saveBtn);

            //Wait to Save Process Completes
            Thread.sleep(2000);
            return true;
        }catch(Exception exp){
            exp.printStackTrace();
            return false;
        }
    }//saveField()

    public  void verifySaveProcess(HashMap<String, String> testData) {
        int startTimeIndx = 0;
        int endTimeIndx = 1;
        String strByValue = "value";
        List<WebElement>  smsTimeWindow = Utilities.locateAll(inputSMSWindow_StartAndEndTimes);
        List<WebElement>  voiceTimeWindow = Utilities.locateAll(inputVoiceWindow_StartAndEndTimes);
        List<WebElement>  emailTimeWindow = Utilities.locateAll(inputEmailWindow_StartAndEndTimes);
        String genericErrorMsg = " The Saved value did not match the entered value...";

        try {
            Deprecated.scrollToElementJS(lblCustomerPreferencesSection);

            //******************** Customer Preferences *******************
            //Open the Customer Preferences Section
            Utilities.click(lnkEdit_CustomerPreferences);

            //Verify All Fields were saved successfully
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnDefaultSMS),String.valueOf(testData.get(keyDefaultSMS).toString()), "(" + keyDefaultSMS + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnDefaultVoice),String.valueOf(testData.get(keyDefaultVoice).toString()), "(" + keyDefaultVoice + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnDefaultEmail),String.valueOf(testData.get(keyDefaultEmail).toString()), "(" + keyDefaultEmail + "): "+ genericErrorMsg);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnPortalAccess),String.valueOf(testData.get(keyPortalAccess).toString()), "(" + keyPortalAccess + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnPortalFileUploads),String.valueOf(testData.get(keyPortalFileUploads).toString()), "(" + keyPortalFileUploads + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnPortalLinksAutoLogin),String.valueOf(testData.get(keyPortalLinksAutoLogin).toString()), "(" + keyPortalLinksAutoLogin + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnEnableOnlinePayments),String.valueOf(testData.get(keyEnableOnlinePayments).toString()), "(" + keyEnableOnlinePayments + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnAttachInvoiceToEmail),String.valueOf(testData.get(keyAttachInvoiceToEmail).toString()), "(" + keyAttachInvoiceToEmail + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnShowInvoiceOnServiceNotification_Followup),String.valueOf(testData.get(keyShowInvoiceOnServiceNotificationFollowup).toString()), "(" + keyShowInvoiceOnServiceNotificationFollowup + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnVoiceCallSecretary),String.valueOf(testData.get(keyVoiceCallSecretary).toString()), "(" + keyVoiceCallSecretary + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnEmailPaymentReceipts),String.valueOf(testData.get(keyEmailPaymentReceipts).toString()), "(" + keyEmailPaymentReceipts + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnSMSReplyAutoResponse),String.valueOf(testData.get(keySMSReplyAutoResponse).toString()), "(" + keySMSReplyAutoResponse + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnIncludeAppointmentNotesOnServiceNotification),String.valueOf(testData.get(keyIncludeAppointmentNotesOnServiceNotification).toString()), "(" + keyIncludeAppointmentNotesOnServiceNotification + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnServiceFollowupEmail_CustPref),String.valueOf(testData.get(keyServiceFollowupEmail_CustomPref).toString()), "(" + keyServiceFollowupEmail_CustomPref + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnVoiceReminderConfirmation),String.valueOf(testData.get(keyVoiceReminderConfirmation).toString()), "(" + keyVoiceReminderConfirmation + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnShowInvoiceOnServiceNotification_Followup),String.valueOf(testData.get(keyShowInvoiceOnServiceNotificationFollowup).toString()), "(" + keyShowInvoiceOnServiceNotificationFollowup + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnVoipService),String.valueOf(testData.get(keyVoipService).toString()), "(" + keyVoipService + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getAttribute(inputVoipHostURL,strByValue),String.valueOf(testData.get(keyVoipHostURL).toString()), "(" + keyVoipHostURL + "): " + genericErrorMsg);

            //******************** Delivery Settings (SMS, Voice and Email) *******************
            //Scroll to and open the Delivery Settings (SMS, Voice and Email) Section
            navigateToAndClickBtn(lblDeliverySettingsSection, lnkEdit_DeliverySettings);

            Assert.assertEquals(smsTimeWindow.get(startTimeIndx).getAttribute(strByValue).toString(),String.valueOf(testData.get(keySMSWindow_sTime).toString()), "(" + keySMSWindow_sTime + "): " + genericErrorMsg);
            Assert.assertEquals(smsTimeWindow.get(endTimeIndx).getAttribute(strByValue).toString(),String.valueOf(testData.get(keySMSWindow_eTime).toString()), "(" + keySMSWindow_eTime + "): " + genericErrorMsg);
            Assert.assertEquals(voiceTimeWindow.get(startTimeIndx).getAttribute(strByValue).toString(),String.valueOf(testData.get(keyVoiceWindow_sTime).toString()), "(" + keyVoiceWindow_sTime + "): " + genericErrorMsg);
            Assert.assertEquals(voiceTimeWindow.get(endTimeIndx).getAttribute(strByValue).toString(),String.valueOf(testData.get(keyVoiceWindow_eTime).toString()), "(" + keyVoiceWindow_eTime + "): " + genericErrorMsg);
            Assert.assertEquals(emailTimeWindow.get(startTimeIndx).getAttribute(strByValue).toString(),String.valueOf(testData.get(keyEmailWindow_sTime).toString()), "(" + keyEmailWindow_sTime + "): " + genericErrorMsg);
            Assert.assertEquals(emailTimeWindow.get(endTimeIndx).getAttribute(strByValue).toString(),String.valueOf(testData.get(keyEmailWindow_eTime).toString()), "(" + keyEmailWindow_eTime + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getAttribute(inputMaxSMSPerMinute,strByValue).toString(),String.valueOf(testData.get(keyMaxSMSPerMinute).toString()), "(" + keyMaxSMSPerMinute + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getAttribute(inputMaxVoicePerMinute,strByValue).toString(),String.valueOf(testData.get(keyMaxVoicePerMinute).toString()), "(" + keyMaxVoicePerMinute + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getAttribute(inputMaxEmailPerMinute,strByValue).toString(),String.valueOf(testData.get(keyMaxEmailPerMinute).toString()), "(" + keyMaxEmailPerMinute + "): " + genericErrorMsg);
            savedBlockedWindowCheck(keyBlockedDaysForSendingTextTriggers,String.valueOf(testData.get(keyBlockedDaysForSendingTextTriggers).toString()));
            savedBlockedWindowCheck(keyBlockedDaysForSendingPhoneTriggers,String.valueOf(testData.get(keyBlockedDaysForSendingPhoneTriggers).toString()));

            //Enhanced Answering Machine Detection
            navigateToAndClickBtn(lnkEdit_EnhancedAnsweringMachineDetection, lnkEdit_EnhancedAnsweringMachineDetection);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnEnhancedAnsweringMachineDetection),String.valueOf(testData.get(keyEnhancedAnsweringMachineDetection).toString()), "(" + keyEnhancedAnsweringMachineDetection + "): " + genericErrorMsg);

            //Account Statement Tear Sheet
            navigateToAndClickBtn(lnkEdit_AccountStatementTearSheet, lnkEdit_AccountStatementTearSheet);
            Assert.assertEquals( Utilities.getFirstSelected(dropdwnAccountStatementTearSheet),String.valueOf(testData.get(keyAccountStatementTearSheet).toString()), "(" + keyAccountStatementTearSheet + "): " + genericErrorMsg);

            //Accounts Receivable Hold Time - Residential Customers , Commercial Customers
            navigateToAndClickBtn(lnkEdit_AccountsReceivableHoldTime, lnkEdit_AccountsReceivableHoldTime);
            Assert.assertEquals( Utilities.getAttribute(inputResidentialCustomers,strByValue),String.valueOf(testData.get(keyResidentialCustomers).toString()), "(" + keyResidentialCustomers + "): " + genericErrorMsg);
            Assert.assertEquals( Utilities.getAttribute(inputCommercialCustomers,strByValue),String.valueOf(testData.get(keyCommercialCustomers).toString()), "(" + keyCommercialCustomers + "): " + genericErrorMsg);

            //SMS Appointment Reminder Template
            navigateToAndClickBtn(lnkEdit_SMSAppointmentReminderTemplate, lnkEdit_SMSAppointmentReminderTemplate);
            Assert.assertEquals(Utilities.getAttribute(textareaSMSAppointmentReminderTemplate,strByValue),String.valueOf(testData.get(keySMSAppointmentReminderTemplate).toString()), "(" + keySMSAppointmentReminderTemplate + "): " + genericErrorMsg);

            //SMS Reply Auto Response [Confirmed Appointment]
            navigateToAndClickBtn(lnkEdit_SMSReplyAutoResponse_ConfirmAppt, lnkEdit_SMSReplyAutoResponse_ConfirmAppt);
            Assert.assertEquals(Utilities.getAttribute(textareaSMSReplyAutoResponse_ConfirmAppt,strByValue),String.valueOf(testData.get(keySMSReplyAutoResponseConfirmedAppt).toString()), "(" + keySMSReplyAutoResponseConfirmedAppt + "): " + genericErrorMsg);

            //SMS Reply Auto Response [Default]
            navigateToAndClickBtn(lnkEdit_SMSReplyAutoResponse_Default, lnkEdit_SMSReplyAutoResponse_Default);
            Assert.assertEquals(Utilities.getAttribute(textareaSMSReplyAutoResponse_Default,strByValue),String.valueOf(testData.get(keySMSReplyAutoResponseDefault).toString()), "(" + keySMSReplyAutoResponseDefault + "): " + genericErrorMsg);

            //Invoice Footer Text
            navigateToAndClickBtn(lblInvoiceFooterText, lnkEdit_InvoiceFooterText);
            Assert.assertEquals(Utilities.jsGetText(rtextareaInvoiceFooterText).trim(),String.valueOf(testData.get(keyInvoiceFooterText).toString()), "(" + keyInvoiceFooterText + "): " + genericErrorMsg);

            //Statement and Invoice Footer Text (Note: Navigated to the previous field to make sure the desired field is displayed)
            navigateToAndClickBtn(lblInvoiceFooterText, lnkEdit_StatementAndInvoiceFooterText);
            Assert.assertEquals(Utilities.jsGetText(rtextareaStatementAndInvoiceFooterText).trim(),String.valueOf(testData.get(keyStatementAndInvoiceFooterText).toString()), "(" + keyStatementAndInvoiceFooterText + "): " + genericErrorMsg);

            //Email Appointment Reminder Template (Note: Navigated to the previous field to make sure the desired field is displayed)
            navigateToAndClickBtn(lblStatementAndInvoiceFooterText, lnkEdit_EmailAppointmentReminderTemplate);
            Assert.assertEquals(Utilities.jsGetText(rtextareaEmailAppointmentReminderTemplate).trim(),String.valueOf(testData.get(keyEmailAppointmentReminderTemplate).toString()), "(" + keyEmailAppointmentReminderTemplate + "): " + genericErrorMsg);

            //Require Electronic Consent Agreement
            Utilities.click(lnkEdit_RequireElectronicConsentAgreement);
            Utilities.checkBox(chkboxRequireElectronicConsentAgreement);
            Assert.assertEquals(Utilities.jsGetText(rtextareaElectronicConsentContent).trim(),String.valueOf(testData.get(keyElectronicConsentContent).toString()), "(" + keyElectronicConsentContent + "): " + genericErrorMsg);

            //Agreement Signature Email Subject
            navigateToAndClickBtn(lblRequireElectronicConsentAgreement, lnkEdit_AgreementSignatureEmailSubject);
            Assert.assertEquals(Utilities.getAttribute(textareaAgreementSignatureEmailSubject,strByValue),String.valueOf(testData.get(keyAgreementSignatureEmailSubject).toString()), "(" + keyAgreementSignatureEmailSubject + "): " + genericErrorMsg);

           //Agreement Signature Email Body
           navigateToAndClickBtn(lblAgreementSignatureEmailBody, lnkEdit_AgreementSignatureEmailBody);
           Assert.assertEquals(Utilities.jsGetText(rtextareaAgreementSignatureEmailBody).trim(),String.valueOf(testData.get(keyAgreementSignatureEmailBody).toString()), "(" + keyEmailAppointmentReminderTemplate + "): " + genericErrorMsg);

           //Allow Contracts via SMS Text - dropdown , Agreement Signature SMS Text Body
           navigateToAndClickBtn(lblAgreementSignatureEmailBody, lnkEdit_AllowContractsViaSMSText);
           Assert.assertEquals(Utilities.getFirstSelected(dropdwnAllowContractsViaSMSText),String.valueOf(testData.get(keyAllowContractsViaSMSText).toString()), "(" + keyAllowContractsViaSMSText + "): " + genericErrorMsg);
           Assert.assertEquals(Utilities.getAttribute(textareaAgreementSignatureSMSTextBody,strByValue), String.valueOf(testData.get(keyAgreementSignatureSMSTextBody).toString()),"(" + keyAgreementSignatureSMSTextBody + "): " + genericErrorMsg);

            //Service Follow-up Email
            navigateToAndClickBtn(lblServiceFollowupEmail, lnkEdit_ServiceFollowupEmail);
            Assert.assertEquals(Utilities.jsGetText(rtextareaServiceFollowupEmail).trim(),String.valueOf(testData.get(keyServiceFollowupEmail).toString().trim()), "(" + keyServiceFollowupEmail + "): " + genericErrorMsg);

            //Service Notifications Notes / Caution Statements (Note: Navigated to the previous field to make sure the desired field is displayed)
            navigateToAndClickBtn(lblAllowContractsViaSMSText, lnkEdit_ServiceNotificationsNotes_CautionStatements);
            Assert.assertEquals(Utilities.jsGetText(rtextareaServiceNotificationsNotes_CautionStatements).trim(),String.valueOf(testData.get(keyServiceNotificationsNotesCautionStatements).toString().trim()), "(" + keyServiceNotificationsNotesCautionStatements + "): " + genericErrorMsg);

            //Custom Review Header  (Note: Navigated to the previous field to make sure the desired field is displayed)
            navigateToAndClickBtn(lblServiceNotificationsNotes_CautionStatements, lnkEdit_CustomReviewHeader);
            Assert.assertEquals(Utilities.getAttribute(textareaCustomReviewHeader,strByValue), String.valueOf(testData.get(keyCustomReviewHeader).toString()),"(" + keyCustomReviewHeader + "): " + genericErrorMsg);

            //Custom Review Message (Note: Navigated to the previous field to make sure the desired field is displayed)
            navigateToAndClickBtn(lblCustomReviewHeader, lnkEdit_CustomReviewMessage);
            Assert.assertEquals(Utilities.jsGetText(rtextareaCustomReviewMessage).trim(), String.valueOf(testData.get(keyCustomReviewMessage).toString()),"(" + keyCustomReviewMessage + "): " + genericErrorMsg);

            //Review link option, Custom Review Link (Note: Navigated to the previous field to make sure the desired field is displayed)
            navigateToAndClickBtn(lblCustomReviewHeader, lnkEdit_ReviewlinkOption);
            Assert.assertEquals(Utilities.getFirstSelected(dropdwnReviewlinkOption),String.valueOf(testData.get(keyReviewlinkOption).toString()), "(" + keyReviewlinkOption + "): " + genericErrorMsg);
            Assert.assertEquals(Utilities.getAttribute(inputCustomReviewLink, strByValue), String.valueOf(testData.get(keyCustomReviewLink).toString()),"(" + keyCustomReviewLink + "): " + genericErrorMsg);

            //Custom Email Blurb (Note: Navigated to the previous field to make sure the desired field is displayed)
            navigateToAndClickBtn(lblReviewlinkOption, lnkEdit_CustomEmailBlurb);
            Assert.assertEquals(Utilities.jsGetText(rtextareaCustomEmailBlurb).trim(), String.valueOf(testData.get(keyCustomEmailBlurb).toString()),"(" + keyCustomEmailBlurb + "): " + genericErrorMsg);

            //Customer Portal Sign Agreement Welcome Message (Note: Navigated to the previous field to make sure the desired field is displayed)
            navigateToAndClickBtn(lblCustomEmailBlurb, lnkEdit_CustomerPortalSignAgreementWelcomeMessage);
            Assert.assertEquals(Utilities.jsGetText(rtextareaCustomerPortalSignAgreementWelcomeMessage).trim(), String.valueOf(testData.get(keyCustomerPortalSignAgreementWelcomeMessage).toString()),"(" + keyCustomerPortalSignAgreementWelcomeMessage + "): " + genericErrorMsg);

            //Customer Portal Sign Form Welcome Message (Note: Navigated to the previous field to make sure the desired field is displayed)
            navigateToAndClickBtn(lblCustomerPortalSignAgreementWelcomeMessage, lnkEdit_CustomerPortalSignFormWelcomeMessage);
            Assert.assertEquals(Utilities.jsGetText(rtextareaCustomerPortalSignFormWelcomeMessage).trim(), String.valueOf(testData.get(keyCustomerPortalSignFormWelcomeMessage).toString()),"(" + keyCustomerPortalSignFormWelcomeMessage + "): " + genericErrorMsg);

            //SalesRoutes and Customer Portal Contract Agreement Message (Note: Navigated to the previous field to make sure the desired field is displayed)
            navigateToAndClickBtn(lblCustomerPortalSignFormWelcomeMessage, lnkEdit_SalesRoutesAndCustomerPortalContractAgreementMessage);
            Assert.assertEquals(Utilities.jsGetText(rtextareaSalesRoutesAndCustomerPortalContractAgreementMessage).trim(), String.valueOf(testData.get(keySalesRoutesAndCustomerPortalContractAgreementMessage).toString()),"(" + keySalesRoutesAndCustomerPortalContractAgreementMessage + "): " + genericErrorMsg);

            //SalesRoutes and Customer Portal Auto Payment Message (Note: Navigated to the previous field to make sure the desired field is displayed)
            navigateToAndClickBtn(lblCustomerPortalSignFormWelcomeMessage, lnkEdit_SalesRoutesAndCustomerPortalAutoPaymentMessage);
            Assert.assertEquals(Utilities.jsGetText(rtextareaSalesRoutesAndCustomerPortalAutoPaymentMessage).trim(), String.valueOf(testData.get(keySalesRoutesAndCustomerPortalAutoPaymentMessage).toString()),"(" + keySalesRoutesAndCustomerPortalAutoPaymentMessage + "): " + genericErrorMsg);

/* Comment out CA WDO- fields because they are not configured in offices
            //CA WDO Pre Findings and Recommendations Legal (Note: Navigated to the previous field to make sure the desired field is displayed)
            navigateToAndClickBtn(lblSalesRoutesAndCustomerPortalAutoPaymentMessage, lnkEdit_CAWDOPreFindingsAndRecommendationsLegal);
            Assert.assertEquals(Utilities.getInnerText(rtextareaCAWDOPreFindingsAndRecommendationsLegal).trim(), String.valueOf(testData.get(keyCAWDOPreFindingsAndRecommendationsLegal).toString()),"(" + keyCAWDOPreFindingsAndRecommendationsLegal + "): " + genericErrorMsg);

            //CA WDO Post Findings and Recommendations Legal (Note: Navigated to the previous field to make sure the desired field is displayed)
            navigateToAndClickBtn(lblCAWDOPreFindingsAndRecommendationsLegal, lnkEdit_CAWDOPostFindingsAndRecommendationsLegal);
            Assert.assertEquals(Utilities.getInnerText(rtextareaCAWDOPostFindingsAndRecommendationsLegal).trim(), String.valueOf(testData.get(keyCAWDOPostFindingsAndRecommendationsLegal).toString()),"(" + keyCAWDOPostFindingsAndRecommendationsLegal + "): " + genericErrorMsg);

            //CA WDO Work Authorization Legal (Note: Navigated to the previous field to make sure the desired field is displayed)
            navigateToAndClickBtn(lblCAWDOPostFindingsAndRecommendationsLegal, lnkEdit_CAWDOWorkAuthorizationLegal);
            Assert.assertEquals(Utilities.getInnerText(rtextareaCAWDOWorkAuthorizationLegal).trim(), String.valueOf(testData.get(keyCAWDOWorkAuthorizationLegal).toString()),"(" + keyCAWDOWorkAuthorizationLegal + "): " + genericErrorMsg);
*/
        } catch(Exception exp) {
             System.out.println("********************* SAVE ERROR: All fields were NOT saved successfully!");
            exp.printStackTrace();
            }
    }//verifySaveProcess()

    protected void navigateToAndClickBtn(By scrollToElem, By btn) throws InterruptedException {
        Deprecated.scrollToElementJS(scrollToElem);
        Utilities.click(btn);
    }//navigateToAndClickBtn()

    protected void savedBlockedWindowCheck(String windowSection, String savedBlockedDays) throws InterruptedException {
        String[] savedBlockedDaysList = savedBlockedDays.split(",");
        By dayCheckbox;

        for(String day : savedBlockedDaysList)
        {
            if(windowSection.equalsIgnoreCase(keyBlockedDaysForSendingTextTriggers))
                dayCheckbox = By.xpath("//*[@id='newPreferenceBody']//input[@name='" + day.toLowerCase() + "TextBlocked']");
            else if(windowSection.equalsIgnoreCase(keyBlockedDaysForSendingPhoneTriggers))
                dayCheckbox = By.xpath("//*[@id='newPreferenceBody']//input[@name='" + day.toLowerCase() + "PhoneBlocked']");
            else
                dayCheckbox = null;
            Assert.assertEquals(Utilities.isChecked(dayCheckbox),true,"(" + windowSection + "): The checked day WAS NOT save successfully (" + day + ") ...");
         }
    }//savedBlockedWindowCheck()

    public void typeSMS_StartWindowTime(String smsStartTime) {
        Deprecated.type(smsStartTime, smsStartWindowField);
    }

    public void typeSMS_EndWindowTime(String smsEndTime) {
        Deprecated.type(smsEndTime, smsEndWindowField);
    }

    public void clickDeliverySettingsEditButton() {
        //delay(2000); // Needed for StagingDemo but not my subdomain. StagingDemo Did Not scrollToElementJS
        jsScrollTo(lnkEdit_DeliverySettings);
        click(lnkEdit_DeliverySettings);
    }

    public void clickDeliverySettingsSaveButton() {
        jsScrollTo(lnkSave_DeliverySettings);
        click(lnkSave_DeliverySettings);
    }

} //CustomerCommunicationPageObjects()
