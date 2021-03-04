package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import automation.PestRoutes.Utilities.*;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.Trigger_Actions;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.RenewalTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.SubscriptionStatusTab;

public class CreateTrigger_CustomerStatus extends AppData {
    Header header;
    AdminMainPage adminMainPage;
    TriggerRules triggerAdmin = new TriggerRules();
    RenewalTab renewalTab;
    Trigger_Actions triggerActions = new Trigger_Actions();
    ARTab ar;
    SubscriptionStatusTab subscriptionStatus;
    private String descriptionTrigger = "trigger_customerStatus_all_actions";
    public List list = new ArrayList<String>();

    @Test
    public void createCustomerStatusRule() throws Exception {
        createTrigger_CustomerStatus(descriptionTrigger);
        searchTrigger_appointmentStatus(descriptionTrigger);
        SMSAction_CustomerStatus();
        searchTrigger_appointmentStatus(descriptionTrigger);
        voiceAction_CustomerStatus();
        searchTrigger_appointmentStatus(descriptionTrigger);
        emailAction_CustomerStatus();
        searchTrigger_appointmentStatus(descriptionTrigger);
        snailMailAction_CustomerStatus();
        searchTrigger_appointmentStatus(descriptionTrigger);
		/*webhookAction_CustomerStatus();
		searchTrigger_appointmentStatus(descriptionName);*/
        sendEmployeeEmail_CustomerStatus();
        searchTrigger_appointmentStatus(descriptionTrigger);
        addAlert_CustomerStatus();
        searchTrigger_appointmentStatus(descriptionTrigger);
        addTask_CustomerStatus();
        searchTrigger_appointmentStatus(descriptionTrigger);
        sendEmployeeSMS_CustomerStatus();
        searchTrigger_appointmentStatus(descriptionTrigger);
        sendEmployeeVoice_CustomerStatus();
        searchTrigger_appointmentStatus(descriptionTrigger);
        assertActions_AppointmentStatus();
        validateIfFailureExist();
    }

    // Create a Customer Status Trigger
    public void createTrigger_CustomerStatus(String descriptionName) throws InterruptedException, Exception {
        header = new Header();
        adminMainPage = new AdminMainPage();
        renewalTab = new RenewalTab();
        subscriptionStatus = new SubscriptionStatusTab();
        ar = new ARTab();
        header.navigateTo(header.adminTab);
        adminMainPage.navigateTo(adminMainPage.preferences);
        triggerAdmin.navigateToTriggerRules();
        triggerAdmin.clickAddTrigerButton();
        triggerAdmin.setStartDate(Utilities.currentDate("MM/dd/yyyy"));
        triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
        triggerAdmin.setDescription(descriptionName);
        triggerAdmin.selectDropdown(triggerAdmin.triggerTypeDropdown, triggerAdmin.triggerType_CustomerStatus);
        triggerAdmin.selectDropdown(triggerAdmin.globalType, triggerAdmin.global_SpecificToThisOffice);
        triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_NotActive);
        triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger,
                subscriptionStatus.whenToTrigger_triggerAfterTime);
        triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
        triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger, subscriptionStatus.whenToTrigger_triggerOnSave);
        triggerAdmin.selectDropdown(renewalTab.multiUnitDropdown, renewalTab.multiUnit_Dropdown_Include);
        triggerAdmin.selectDropdown(renewalTab.propertyTypeDropdown, ar.propertyType_AllProperties);
        triggerAdmin.clickSaveButton();
    }

    // Search Customer Status Trigger
    public void searchTrigger_appointmentStatus(String descriptionName) {
        header = new Header();
        adminMainPage = new AdminMainPage();
        header.navigateTo(header.adminTab);
        adminMainPage.navigateTo(adminMainPage.preferences);
        triggerAdmin.navigateToTriggerRules();
        triggerAdmin.searchTrigger(descriptionName);
        result(descriptionName, triggerAdmin.getDescriptionText(descriptionName), "Search Customer",
                "Appointment Status Creation");
        triggerAdmin.clickEditTrigger(descriptionName);
    }

    // Create a SMS action
    public void SMSAction_CustomerStatus() throws InterruptedException {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendVoiceMessageType_Action);
        Thread.sleep(3000);
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendSMSMessageType_Action);
        triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_Yes);
        triggerActions.setMessageinAction_Type1(triggerActions.sendSMSMessageType_Action, triggerActions.getPlaceHolders());
        triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
        triggerAdmin.clickSaveButton();
    }

    // Create Voice Customer Status action
    public void voiceAction_CustomerStatus() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendVoiceMessageType_Action);
        triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
        triggerAdmin.selectDropdown(triggerActions.voiceType_Reminder, triggerActions.preRecordedMessageVoice_Reminder);
        triggerAdmin.selectDropdown(triggerActions.voiceType_Reminder, triggerActions.newMessage_Voice);
        triggerActions.setMessageinAction_Type1(triggerActions.sendVoiceMessageType_Action, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Email Customer Status Action
    public void emailAction_CustomerStatus() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.EmailMessageType_Action);
        triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
        triggerActions.setEmailTitle(Utilities.generateRandomString(5));
        triggerActions.setMessageinAction_Type2(triggerActions.EmailMessageType_Action, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create SnailMail Customer Status Action
    public void snailMailAction_CustomerStatus() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.snailMailMessageType_Action);
        triggerActions.setMessageinAction_Type2(triggerActions.snailMailMessageType_Action, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Action with Webhook
    public void webhookAction_CustomerStatus() {
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
    public void sendEmployeeEmail_CustomerStatus() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendEmployeeEmail_SubscriptionStatus);
        triggerActions.setEmailTitle_SubscriptionStatus(Utilities.generateRandomString(5));
        triggerActions.setEmailAddress_SubscriptionStatus(Utilities.generateRandomString(5) + "@gmail.com");
        triggerActions.setMessageinAction_Type2(triggerActions.sendEmployeeEmail_SubscriptionStatus, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Action with Add Alert
    public void addAlert_CustomerStatus() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.addAlert_SubscriptionStatus);
        triggerActions.setMessageinAction_Type1(triggerActions.addAlert_SubscriptionStatus, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Action with Add Task
    public void addTask_CustomerStatus() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.addTask_SubscriptionStatus);
        triggerActions.setDaysTillDueAddTask_SubscriptionStatus(Double.toString(Utilities.generateRandomInteger(1)));
        triggerActions.setMessageinAction_Type1(triggerActions.addTask_SubscriptionStatus, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Action Send Employee SMS
    public void sendEmployeeSMS_CustomerStatus() throws IOException {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendEmployeeSMS_SubscriptionStatus);
        triggerActions.setEmployeePhoneNumber_SubscriptionStatus(triggerActions.sendEmployeeSMS_SubscriptionStatus, getData("phoneNumber", generalData));
        triggerActions.setMessageinAction_Type1(triggerActions.sendEmployeeSMS_SubscriptionStatus, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Action Send Employee Voice
    public void sendEmployeeVoice_CustomerStatus() throws IOException {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendEmployeeVoice_SubscriptionStatus);
        triggerActions.setEmployeePhoneNumber_SubscriptionStatus(triggerActions.sendEmployeeVoice_SubscriptionStatus, getData("phoneNumber", generalData));
        triggerAdmin.selectDropdown(triggerActions.voiceType_SubscriptionStatus, triggerActions.preRecordedMessageVoice_Reminder);
        triggerAdmin.selectDropdown(triggerActions.voiceType_SubscriptionStatus, triggerActions.newMessage_Voice);
        triggerActions.setMessageinAction_Type1(triggerActions.sendEmployeeVoice_SubscriptionStatus, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Assert all created actions
    public void assertActions_AppointmentStatus() {
        ar = new ARTab();
        renewalTab = new RenewalTab();
        subscriptionStatus = new SubscriptionStatusTab();
        result(triggerActions.sendSMSMessageType_Action, ar.getSMSActionTextValue(), "SMS Action",
                "Appointment Status Creation");
        result(triggerActions.sendVoiceMessageType_Action, ar.getVoiceActionTextValue(), "Voice Action",
                "Appointment Status Creation");
        result(triggerActions.EmailMessageType_Action, ar.getEmailActionTextValue(), "Email Action",
                "Appointment Status Creation");
        result(triggerActions.webhookMessageType_Action, renewalTab.getWebhookActionTextValue(), "Webhook Action",
                "Appointment Status Creation");
        result(triggerActions.snailMailMessageType_Action, ar.getSnailMailActionTextValue(), "Snail Mail Action",
                "Appointment Status Creation");
        result(triggerActions.sendEmployeeEmail_SubscriptionStatus, subscriptionStatus.getSendEmployeeEmailActionTextValue(),
                "Send Employee Email Action", "Appointment Status Creation");
        result(triggerActions.addAlert_SubscriptionStatus, subscriptionStatus.getAddAlertActionTextValue(), "Add Alert Action",
                "Appointment Status Creation");
        result(triggerActions.addTask_SubscriptionStatus, subscriptionStatus.getAddTaskActionTextValue(), "Add Task Action",
                "Appointment Status Creation");
        result(triggerActions.sendEmployeeSMS_SubscriptionStatus, subscriptionStatus.getSendEmploeeSMSActionTextValue(),
                "Send Employee SMS Action", "Appointment Status Creation");
        result(triggerActions.sendEmployeeVoice_SubscriptionStatus, subscriptionStatus.getSendEmployeeVoiceActionTextValue(),
                "Send Employee Voice Action", "Appointment Status Creation");

    }

    @SuppressWarnings("unchecked")
    private void result(String expected, String actual, String stepName, String testName) {
        if (AssertException.hardAssert(expected, actual, stepName).size() > 0) {
            list.add(AssertException.hardAssert(expected, actual, stepName));
        }
        Reporter.status(stepName, expected, actual, testName);
    }

    public void validateIfFailureExist() {
        AssertException.assertFailure(list);
    }
}
