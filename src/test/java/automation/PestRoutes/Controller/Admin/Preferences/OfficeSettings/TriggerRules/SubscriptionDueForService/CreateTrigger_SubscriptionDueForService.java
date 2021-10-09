package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionDueForService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.CreateTrigger_CustomerStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionStatus.CreateTrigger_SubscriptionStatus;
import automation.PestRoutes.Utilities.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.Footer;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.Trigger_Actions;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.ReminderTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.RenewalTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.SubscriptionDueForServiceTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.SubscriptionStatusTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;

public class CreateTrigger_SubscriptionDueForService extends AppData {
    Header header;
    AdminMainPage adminMainPage;
    TriggerRules triggerAdmin = new TriggerRules();
    RenewalTab renewalTab;
    Trigger_Actions triggerActions = new Trigger_Actions();
    ARTab ar;
    SubscriptionStatusTab subscriptionStatus;
    SubscriptionDueForServiceTab subscriptionDueForService;
    CreateNewCustomer createCustomer;
    CustomerViewDialog_Header overviewHeader;
    ReminderTab reminder;
    Footer footer;
    CreateTrigger_SubscriptionStatus createTrigger_subscriptionStatus;
    CreateTrigger_CustomerStatus createCustomerStatus;

    public List list = new ArrayList<String>();

    private String descriptionTrigger = "trigger_subscriptionDueForService_all_actions";
    private String days_before_afterDueDate_InputField_Value = "1";
    private String SMSMAppointmentSubscriptionNote = "Sent to: ";
    private String employeeEmailValue = Utilities.generateRandomString(5) + "@gmail.com";
    private String alertTextinNotes = "Alert - Customer Status (UnRead)";
    private String taskTextinNotes = "Task - Customer Status (Pending)";
    private String employeeVoiceinNotes = "help unable to send message [bad phone number]";
    private String removePaymentinNotes = "Removed Payment Method from Trigger: Card";
    private String CCInfoBillingTab = "No Payment Info";

    @Test
    public void createSubscriptionDueForService() throws Exception {
        createTrigger_SubscriptionDueForService(descriptionTrigger);
        searchTrigger_subscriptionDueForService(descriptionTrigger);
        SMSAction_SubscriptionDueForService();
        searchTrigger_subscriptionDueForService(descriptionTrigger);
        voiceAction_SubscriptionDueForService();
        searchTrigger_subscriptionDueForService(descriptionTrigger);
        emailAction_SubscriptionDueForService();
        searchTrigger_subscriptionDueForService(descriptionTrigger);
        snailMailAction_SubscriptionDueForService();
        /*
         * searchTrigger_subscriptionDueForService(descriptionTrigger);
         * webhookAction_SubscriptionDueForService();
         */
        searchTrigger_subscriptionDueForService(descriptionTrigger);
        sendEmployeeEmail_SubscriptionDueForService();
        searchTrigger_subscriptionDueForService(descriptionTrigger);
        addAlert_SubscriptionDueForService();
        searchTrigger_subscriptionDueForService(descriptionTrigger);
        addTask_SubscriptionDueForService();
        searchTrigger_subscriptionDueForService(descriptionTrigger);
        sendEmployeeSMS_SubscriptionDueForService();
        searchTrigger_subscriptionDueForService(descriptionTrigger);
        sendEmployeeVoice_SubscriptionDueForService();
        searchTrigger_subscriptionDueForService(descriptionTrigger);
        removePaymentProfile_subscriptionForService();
        searchTrigger_subscriptionDueForService(descriptionTrigger);
        assertActions_SubscriptionDueForService();
        validateIfFailureExist();
    }

    public void createTrigger_SubscriptionDueForService(String description) throws Exception {
        createTrigger_subscriptionStatus = new CreateTrigger_SubscriptionStatus();
        header = new Header();
        adminMainPage = new AdminMainPage();
        renewalTab = new RenewalTab();
        subscriptionStatus = new SubscriptionStatusTab();
        ar = new ARTab();
        subscriptionDueForService = new SubscriptionDueForServiceTab();
        header.navigateTo(header.adminTab);
        adminMainPage.navigateTo(adminMainPage.preferences);
        triggerAdmin.navigateToTriggerRules();
        triggerAdmin.clickAddTrigerButton();
        triggerAdmin.setStartDate(Utilities.currentDate("MM/dd/yyyy"));
        triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
        triggerAdmin.setDescription(description);
        triggerAdmin.selectDropdown(triggerAdmin.triggerTypeDropdown,
                triggerAdmin.triggerType_SubscriptionDueforService);
        triggerAdmin.selectDropdown(triggerAdmin.globalType, triggerAdmin.global_SpecificToThisOffice);
        triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
        triggerAdmin.selectDropdown(subscriptionDueForService.before_afterDueDate,
                subscriptionDueForService.afterDueDate_dueDateType);
        triggerAdmin.selectDropdown(renewalTab.multiUnitDropdown, renewalTab.multiUnit_Dropdown_Include);
        triggerAdmin.selectDropdown(renewalTab.hasInitialService_Renewal, renewalTab.hasInitialService_Any_Renewal);
        subscriptionDueForService.setdays_before_afterDueDate_InputField(days_before_afterDueDate_InputField_Value);
        triggerAdmin.selectDropdown(renewalTab.propertyTypeDropdown, ar.propertyType_CommercialOnly);
        triggerAdmin.selectDropdown(renewalTab.propertyTypeDropdown, ar.propertyType_AllProperties);
        triggerAdmin.selectDropdown(subscriptionDueForService.before_afterDueDate,
                subscriptionDueForService.beforeDueDate_dueDateType);
        triggerAdmin.clickSaveButton();
    }

    // Search Subscription Due For Service Trigger
    public void searchTrigger_subscriptionDueForService(String description) throws InterruptedException {
        createCustomerStatus = new CreateTrigger_CustomerStatus();
        createCustomerStatus.searchTrigger_appointmentStatus(description);
    }

    // Edit Trigger
    public void editTrigger_afterDueDate_subscriptionDueForService(String description) {
        subscriptionDueForService = new SubscriptionDueForServiceTab();
        triggerAdmin.clickEditTrigger(description);
        triggerAdmin.selectDropdown(subscriptionDueForService.before_afterDueDate,
                subscriptionDueForService.afterDueDate_dueDateType);
    }

    // Create a SMS action
    public void SMSAction_SubscriptionDueForService() throws InterruptedException {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendVoiceMessageType_Action);
        Thread.sleep(3000);
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendSMSMessageType_Action);
        triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_Yes);
        triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
        triggerActions.setMessageinAction_Type1(triggerActions.sendSMSMessageType_Action, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Voice Subscription Due For Service action
    public void voiceAction_SubscriptionDueForService() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendVoiceMessageType_Action);
        triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
        triggerAdmin.selectDropdown(triggerActions.voiceType_Reminder, triggerActions.preRecordedMessageVoice_Reminder);
        triggerAdmin.selectDropdown(triggerActions.voiceType_Reminder, triggerActions.newMessage_Voice);
        triggerActions.setMessageinAction_Type1(triggerActions.sendVoiceMessageType_Action, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Email Subscription Due For Service Action
    public void emailAction_SubscriptionDueForService() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.EmailMessageType_Action);
        triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
        triggerActions.setEmailTitle(Utilities.generateRandomString(5));
        triggerActions.setMessageinAction_Type2(triggerActions.EmailMessageType_Action, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create SnailMail Subscription Due For Service Action
    public void snailMailAction_SubscriptionDueForService() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.snailMailMessageType_Action);
        triggerActions.setMessageinAction_Type2(triggerActions.snailMailMessageType_Action, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Action with Webhook
    public void webhookAction_SubscriptionDueForService() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.webhookMessageType_Action);
        triggerAdmin.selectDropdown(triggerActions.webhook_MethodType, triggerActions.webhookMethod_GET);
        triggerAdmin.selectDropdown(triggerActions.webhook_MethodType, triggerActions.webhookMethod_POST);
        triggerActions.messageInWebhook(triggerActions.URLMessage_Wehbook, triggerActions.getPlaceHolders());
        triggerActions.messageInWebhook(triggerActions.requestHeaderMessage_Webhook, triggerActions.getPlaceHolders());
        triggerActions.messageInWebhook(triggerActions.requestBodyMessage_Webhook, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Action with Send Employee Email
    public void sendEmployeeEmail_SubscriptionDueForService() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendEmployeeEmail_SubscriptionStatus);
        triggerActions.setEmailTitle_SubscriptionStatus(Utilities.generateRandomString(5));
        triggerActions.setEmailAddress_SubscriptionStatus(employeeEmailValue);
        triggerActions.setMessageinAction_Type2(triggerActions.sendEmployeeEmail_SubscriptionStatus, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Action with Add Alert
    public void addAlert_SubscriptionDueForService() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.addAlert_SubscriptionStatus);
        triggerActions.setMessageinAction_Type1(triggerActions.addAlert_SubscriptionStatus, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Action with Add Task
    public void addTask_SubscriptionDueForService() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.addTask_SubscriptionStatus);
        triggerActions.setDaysTillDueAddTask_SubscriptionStatus(Double.toString(Utilities.generateRandomInteger(1)));
        triggerActions.setMessageinAction_Type1(triggerActions.addTask_SubscriptionStatus, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Action Send Employee SMS
    public void sendEmployeeSMS_SubscriptionDueForService() throws IOException {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendEmployeeSMS_SubscriptionStatus);
        triggerActions.setEmployeePhoneNumber_SubscriptionStatus(triggerActions.sendEmployeeSMS_SubscriptionStatus,
                getData("phoneNumber", generalData));
        triggerActions.setMessageinAction_Type1(triggerActions.sendEmployeeSMS_SubscriptionStatus, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Action Send Employee Voice
    public void sendEmployeeVoice_SubscriptionDueForService() throws IOException {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendEmployeeVoice_SubscriptionStatus);
        triggerActions.setEmployeePhoneNumber_SubscriptionStatus(triggerActions.sendEmployeeVoice_SubscriptionStatus,
                getData("phoneNumber", generalData));
        triggerAdmin.selectDropdown(triggerActions.voiceType_SubscriptionStatus, triggerActions.preRecordedMessageVoice_Reminder);
        triggerAdmin.selectDropdown(triggerActions.voiceType_SubscriptionStatus, triggerActions.newMessage_Voice);
        triggerActions.setMessageinAction_Type1(triggerActions.sendEmployeeVoice_SubscriptionStatus, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create active for Remove Payment Profile Action
    public void removePaymentProfile_subscriptionForService() {
        triggerActions.clickAddActionButton();
        try {
            WebElement elm = triggerAdmin.getDescription("Remove Payment Profile");
            if (elm.isDisplayed()) {
                triggerAdmin.clickSaveButton();
            }
        } catch (Exception e) {
            triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.removePaymentProfile_SubscriptionStatus);
            triggerActions.paymentType_removePaymentProfile(triggerActions.CCandACH);
            triggerAdmin.clickSaveButton();
        }
    }

    // Assert all created actions
    public void assertActions_SubscriptionDueForService() {
        ar = new ARTab();
        renewalTab = new RenewalTab();
        subscriptionStatus = new SubscriptionStatusTab();
        result(triggerActions.sendSMSMessageType_Action, ar.getSMSActionTextValue(), "SMS Action",
                "Subscription Due For Service Creation");
        result(triggerActions.sendVoiceMessageType_Action, ar.getVoiceActionTextValue(), "Voice Action",
                "Subscription Due For Service Creation");
        result(triggerActions.EmailMessageType_Action, ar.getEmailActionTextValue(), "Email Action",
                "Subscription Due For Service Creation");
        result(triggerActions.snailMailMessageType_Action, ar.getSnailMailActionTextValue(), "Snail Mail Action",
                "Subscription Due For Service Creation");
        result(triggerActions.sendEmployeeEmail_SubscriptionStatus, subscriptionStatus.getSendEmployeeEmailActionTextValue(),
                "Send Employee Email Action", "Subscription Due For Service Creation");
        result(triggerActions.addAlert_SubscriptionStatus, subscriptionStatus.getAddAlertActionTextValue(), "Add Alert Action",
                "Subscription Due For Service Creation");
        result(triggerActions.addTask_SubscriptionStatus, subscriptionStatus.getAddTaskActionTextValue(), "Add Task Action",
                "Subscription Due For Service Creation");
        result(triggerActions.sendEmployeeSMS_SubscriptionStatus, subscriptionStatus.getSendEmploeeSMSActionTextValue(),
                "Send Employee SMS Action", "Subscription Due For Service Creation");
        result(triggerActions.sendEmployeeVoice_SubscriptionStatus, subscriptionStatus.getSendEmployeeVoiceActionTextValue(),
                "Send Employee Voice Action", "Subscription Due For Service Creation");

    }

    // Hit the Script
    public void hitTriggerSubscriptionDueForService_Query() {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerServiceDue.php");
    }

    // Navigate to customer and validate the SMS log
    public void assertSMSLog() throws IOException, Exception {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/");
        header = new Header();
        reminder = new ReminderTab();
        header.searchCustomer_History(getData("customerName", generalData));
        overviewHeader = new CustomerViewDialog_Header();
        overviewHeader.navigateTo(overviewHeader.notesTabInDialog);
        result(SMSMAppointmentSubscriptionNote + getData("phoneNumber", generalData),
                reminder.ConfirmationNote(getData("phoneNumber", generalData)), "SMS Notification Affirmative",
                "Subscription Due For Service Creation");
    }

    // Navigate to customer and validate the Email log
    public void assertEMailLog() throws IOException, Exception {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/");
        header = new Header();
        reminder = new ReminderTab();
        createCustomer = new CreateNewCustomer();
        header.searchCustomer_History(getData("customerName", generalData));
        overviewHeader = new CustomerViewDialog_Header();
        overviewHeader.navigateTo(overviewHeader.notesTabInDialog);
        result("Sent to: " + createCustomer.email, reminder.getEmailValue(), "Email Notification Affirmative",
                "Subscription Due For Service Creation");
    }

    // Navigate to customer and validate the Voice log
    public void assertVoiceLog() throws IOException, Exception {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/");
        header = new Header();
        reminder = new ReminderTab();
        header.searchCustomer_History(getData("customerName", generalData));
        overviewHeader = new CustomerViewDialog_Header();
        overviewHeader.navigateTo(overviewHeader.notesTabInDialog);
        result(SMSMAppointmentSubscriptionNote + getData("phoneNumber", generalData), reminder.getVoiceText(),
                "Voice Notification Affirmative", "Subscription Due For Service Creation");
    }

    // Navigate to customer and validate the Snail Mail log
    public void assertSnailMailLog() throws IOException, Exception {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/");
        header = new Header();
        reminder = new ReminderTab();
        createCustomer = new CreateNewCustomer();
        header.searchCustomer_History(getData("customerName", generalData));
        overviewHeader = new CustomerViewDialog_Header();
        overviewHeader.navigateTo(overviewHeader.notesTabInDialog);
        result("Sent to: " + createCustomer.streetAddress, reminder.getSnailMailValue(),
                "Snail Mail Notification Affirmative", "Subscription Due For Service Creation");
    }

    // Navigate to customer and validate the Employee EMmail log
    public void assertEmployeeEMailLog() throws IOException, Exception {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/");
        header = new Header();
        reminder = new ReminderTab();
        createCustomer = new CreateNewCustomer();
        header.searchCustomer_History(getData("customerName", generalData));
        overviewHeader = new CustomerViewDialog_Header();
        overviewHeader.navigateTo(overviewHeader.notesTabInDialog);
        result("Sent to: " + employeeEmailValue, reminder.getEmployeeEMailValue(employeeEmailValue),
                "Employee EMail Notification Affirmative", "Subscription Due For Service Creation");
    }

    // Navigate to customer and validate the Alert log
    public void assertAlertLog() throws IOException, Exception {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/");
        header = new Header();
        reminder = new ReminderTab();
        createCustomer = new CreateNewCustomer();
        header.searchCustomer_History(getData("customerName", generalData));
        overviewHeader = new CustomerViewDialog_Header();
        overviewHeader.navigateTo(overviewHeader.notesTabInDialog);
        result(alertTextinNotes, reminder.getAlertValue(), "Alert Notification Affirmative",
                "Subscription Due For Service Creation");
    }

    // Navigate to customer and validate the Task log
    public void assertTaskLog() throws IOException, Exception {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/");
        header = new Header();
        reminder = new ReminderTab();
        createCustomer = new CreateNewCustomer();
        footer = new Footer();
        header.searchCustomer_History(getData("customerName", generalData));
        overviewHeader = new CustomerViewDialog_Header();
        overviewHeader.navigateTo(overviewHeader.notesTabInDialog);
        result(taskTextinNotes, reminder.getTaskValue(), "Task Notification Affirmative",
                "Subscription Due For Service Creation");
        header.navigateTo(footer.tasks);
        result(getData("customerName", generalData), reminder.customerNameinTask(getData("customerName", generalData)),
                "Task Notification Affirmative", "Subscription Due For Service Creation");
    }

    // Navigate to customer and validate the Employee Voice log
    public void assertEmplopeeVoiceLog() throws IOException, Exception {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/");
        header = new Header();
        reminder = new ReminderTab();
        createCustomer = new CreateNewCustomer();
        header.searchCustomer_History(getData("customerName", generalData));
        overviewHeader = new CustomerViewDialog_Header();
        overviewHeader.navigateTo(overviewHeader.notesTabInDialog);
        result(employeeVoiceinNotes, reminder.getEmployeeVoiceValue(), "Employee Voice Notification Affirmative",
                "Subscription Due For Service Creation");
    }

    // Navigate to customer and validate the Employee Voice log
    public void assertRemovePaymentLog() throws IOException, Exception {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/");
        header = new Header();
        reminder = new ReminderTab();
        createCustomer = new CreateNewCustomer();
        header.searchCustomer_History(getData("customerName", generalData));
        overviewHeader = new CustomerViewDialog_Header();
        overviewHeader.navigateTo(overviewHeader.notesTabInDialog);
        result(removePaymentinNotes, reminder.getRemovedPaymentValue(), "Remove Payment Notification Affirmative",
                "Subscription Due For Service Creation");
        overviewHeader.navigateTo(overviewHeader.billingTabInDialog);
        result(CCInfoBillingTab, reminder.getCCInfoBilling(), "Remove Payment Notification Affirmative",
                "Subscription Due For Service Creation");
    }

    @SuppressWarnings("unchecked")
    public void result(String expected, String actual, String stepName, String testName) {
        if (AssertException.hardAssert(expected, actual, stepName).size() > 0) {
            list.add(AssertException.hardAssert(expected, actual, stepName));
        }
        Reporter.status(stepName, expected, actual, testName);
    }

    public void validateIfFailureExist() {
        AssertException.assertFailure(list);
    }
}
