package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import automation.PestRoutes.Utilities.*;
import io.cucumber.java.en.And;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.Trigger_Actions;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.RenewalTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.SubscriptionStatusTab;

public class CreateTrigger_SubscriptionStatus extends AppData {
    Header header;
    AdminMainPage adminMainPage;
    TriggerRules triggerAdmin = new TriggerRules();
    RenewalTab renewalTab;
    Trigger_Actions triggerActions = new Trigger_Actions();
    ARTab ar;
    SubscriptionStatusTab subscriptionStatus;
    private String descriptionTrigger = "trigger_subscriptionStatus_all_actions";
    public List list = new ArrayList<String>();
    public String days_After_Change = "1";

    @Test
    public void createSubscriptionStatusRule() throws InterruptedException, Exception {
        createTrigger_SubscriptionStatus(descriptionTrigger);
        searchTrigger_subscriptionStatus(descriptionTrigger);
        validateTrigger(descriptionTrigger);
        SMSAction_SubscriptionStatus();
        searchTrigger_subscriptionStatus(descriptionTrigger);
        voiceAction_SubscriptionStatus();
        searchTrigger_subscriptionStatus(descriptionTrigger);
        emailAction_SubscriptionStatus();
        searchTrigger_subscriptionStatus(descriptionTrigger);
        snailMailAction_SubscriptionStatus();
		/*searchTrigger_subscriptionStatus(descriptionTrigger);
		webhookAction_SubscriptionStatus();*/
        searchTrigger_subscriptionStatus(descriptionTrigger);
        sendEmployeeEmail_SubscriptionStatus();
        searchTrigger_subscriptionStatus(descriptionTrigger);
        addAlert_SubscriptionStatus();
        searchTrigger_subscriptionStatus(descriptionTrigger);
        addTask_SubscriptionStatus();
        searchTrigger_subscriptionStatus(descriptionTrigger);
        sendEmployeeSMS_SubscriptionStatus();
        searchTrigger_subscriptionStatus(descriptionTrigger);
        sendEmployeeVoice_SubscriptionStatus();
        searchTrigger_subscriptionStatus(descriptionTrigger);
        assertActions_SubscriptionStatus();
        validateIfFailureExist();
    }

    public void createTrigger_SubscriptionStatus(String description) throws Exception {
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
        triggerAdmin.setDescription(description);
        triggerAdmin.selectDropdown(triggerAdmin.triggerTypeDropdown, triggerAdmin.triggerType_SubscriptionStatus);
        triggerAdmin.selectDropdown(triggerAdmin.globalType, triggerAdmin.global_SpecificToThisOffice);
        triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
        triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger,
                subscriptionStatus.whenToTrigger_triggerAfterTime);
        triggerAdmin.setDaysAfterChange(days_After_Change);
        triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger, subscriptionStatus.whenToTrigger_triggerOnSave);
        triggerAdmin.selectDropdown(renewalTab.multiUnitDropdown, renewalTab.multiUnit_Dropdown_Include);
        triggerAdmin.selectDropdown(renewalTab.hasInitialService_Renewal, renewalTab.hasInitialService_Any_Renewal);
        triggerAdmin.selectDropdown(subscriptionStatus.statusChangedTo, subscriptionStatus.statusChangedTo_Frozen);
        triggerAdmin.selectDropdown(subscriptionStatus.statusChangedTo, subscriptionStatus.statusChangedTo_Any);
        triggerAdmin.selectDropdown(renewalTab.propertyTypeDropdown, ar.propertyType_AllProperties);
        triggerAdmin.clickSaveButton();
    }

    // Search Subscription Status Trigger
    @And("I search for the trigger {string}")
    public void searchTrigger_subscriptionStatus(String description) throws InterruptedException {
        header = new Header();
        adminMainPage = new AdminMainPage();
        header.navigateTo(header.adminTab);
        adminMainPage.navigateTo(adminMainPage.preferences);
        triggerAdmin.navigateToTriggerRules();
        triggerAdmin.searchTrigger(description);
    }

    //Validate created trigger
    @And("I validate the new trigger {string}")
    public void validateTrigger(String descriptionName) throws InterruptedException {
        searchTrigger_subscriptionStatus(descriptionName);
        triggerAdmin.clickEditTrigger(descriptionName);
        result(descriptionName, triggerAdmin.getDescriptionText(descriptionName), "Search Customer",
                "Subscription Status Creation");
    }

    // Create a SMS action
    public void SMSAction_SubscriptionStatus() throws InterruptedException {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendVoiceMessageType_Action);
        Thread.sleep(3000);
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendSMSMessageType_Action);
        triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_Yes);
        triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
        triggerActions.setMessageinAction_Type1(triggerActions.sendSMSMessageType_Action, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Voice Subscription Status action
    public void voiceAction_SubscriptionStatus() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendVoiceMessageType_Action);
        triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
        triggerAdmin.selectDropdown(triggerActions.voiceType_Reminder, triggerActions.preRecordedMessageVoice_Reminder);
        triggerAdmin.selectDropdown(triggerActions.preRecordedMessage_Message_Reminder, "Pest Promotion");
        triggerAdmin.selectDropdown(triggerActions.voiceType_Reminder, triggerActions.newMessage_Voice);
        triggerActions.setMessageinAction_Type1(triggerActions.sendVoiceMessageType_Action, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Email Subscription Status Action
    public void emailAction_SubscriptionStatus() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.EmailMessageType_Action);
        triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
        triggerActions.setEmailTitle(Utilities.generateRandomString(5));
        triggerActions.setMessageinAction_Type2(triggerActions.EmailMessageType_Action, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create SnailMail Subscription Status Action
    public void snailMailAction_SubscriptionStatus() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.snailMailMessageType_Action);
        triggerActions.setMessageinAction_Type2(triggerActions.snailMailMessageType_Action, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Action with Webhook
    public void webhookAction_SubscriptionStatus() {
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
    public void sendEmployeeEmail_SubscriptionStatus() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendEmployeeEmail_SubscriptionStatus);
        triggerActions.setEmailTitle_SubscriptionStatus(Utilities.generateRandomString(5));
        triggerActions.setEmailAddress_SubscriptionStatus(Utilities.generateRandomString(5) + "@gmail.com");
        triggerActions.setMessageinAction_Type2(triggerActions.sendEmployeeEmail_SubscriptionStatus, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Action with Add Alert
    public void addAlert_SubscriptionStatus() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.addAlert_SubscriptionStatus);
        triggerActions.setMessageinAction_Type1(triggerActions.addAlert_SubscriptionStatus, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Action with Add Task
    public void addTask_SubscriptionStatus() {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.addTask_SubscriptionStatus);
        triggerActions.setDaysTillDueAddTask_SubscriptionStatus(Double.toString(Utilities.generateRandomInteger(1)));
        triggerActions.setMessageinAction_Type1(triggerActions.addTask_SubscriptionStatus, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Action Send Employee SMS
    public void sendEmployeeSMS_SubscriptionStatus() throws IOException {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendEmployeeSMS_SubscriptionStatus);
        triggerActions.setEmployeePhoneNumber_SubscriptionStatus(triggerActions.sendEmployeeSMS_SubscriptionStatus,
                getData("phoneNumber", generalData));
        triggerActions.setMessageinAction_Type1(triggerActions.sendEmployeeSMS_SubscriptionStatus, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Action Send Employee Voice
    public void sendEmployeeVoice_SubscriptionStatus() throws IOException {
        triggerActions.clickAddActionButton();
        triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendEmployeeVoice_SubscriptionStatus);
        triggerActions.setEmployeePhoneNumber_SubscriptionStatus(triggerActions.sendEmployeeVoice_SubscriptionStatus,
                getData("phoneNumber", generalData));
        triggerAdmin.selectDropdown(triggerActions.voiceType_SubscriptionStatus, triggerActions.preRecordedMessageVoice_Reminder);
        triggerAdmin.selectDropdown(triggerActions.preRecordedMessage_Message_SubscriptionStatus, "Pest Promotion");
        triggerAdmin.selectDropdown(triggerActions.voiceType_SubscriptionStatus, triggerActions.newMessage_Voice);
        triggerActions.setMessageinAction_Type1(triggerActions.sendEmployeeVoice_SubscriptionStatus, triggerActions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Assert all created actions
    public void assertActions_SubscriptionStatus() {
        ar = new ARTab();
        renewalTab = new RenewalTab();
        subscriptionStatus = new SubscriptionStatusTab();
        result(triggerActions.sendSMSMessageType_Action, ar.getSMSActionTextValue(), "SMS Action",
                "Subscription Status Creation");
        result(triggerActions.sendVoiceMessageType_Action, ar.getVoiceActionTextValue(), "Voice Action",
                "Subscription Status Creation");
        result(triggerActions.EmailMessageType_Action, ar.getEmailActionTextValue(), "Email Action",
                "Subscription Status Creation");
        result(triggerActions.webhookMessageType_Action, renewalTab.getWebhookActionTextValue(), "Webhook Action",
                "Subscription Status Creation");
        result(triggerActions.snailMailMessageType_Action, ar.getSnailMailActionTextValue(), "Snail Mail Action",
                "Subscription Status Creation");
        result(triggerActions.sendEmployeeEmail_SubscriptionStatus, subscriptionStatus.getSendEmployeeEmailActionTextValue(),
                "Send Employee Email Action", "Subscription Status Creation");
        result(triggerActions.addAlert_SubscriptionStatus, subscriptionStatus.getAddAlertActionTextValue(), "Add Alert Action",
                "Subscription Status Creation");
        result(triggerActions.addTask_SubscriptionStatus, subscriptionStatus.getAddTaskActionTextValue(), "Add Task Action",
                "Subscription Status Creation");
        result(triggerActions.sendEmployeeSMS_SubscriptionStatus, subscriptionStatus.getSendEmploeeSMSActionTextValue(),
                "Send Employee SMS Action", "Subscription Status Creation");
        result(triggerActions.sendEmployeeVoice_SubscriptionStatus, subscriptionStatus.getSendEmployeeVoiceActionTextValue(),
                "Send Employee Voice Action", "Subscription Status Creation");

    }

    @SuppressWarnings("unchecked")
    private void result(String expected, String actual, String stepName, String testName) {
        if (AssertException.result(expected, actual, stepName).size() > 0) {
            list.add(AssertException.result(expected, actual, stepName));
        }
        Reporter.status(stepName, expected, actual, testName);
    }

    public void validateIfFailureExist() {
        AssertException.assertFailure(list);
    }
}
