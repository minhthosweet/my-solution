package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AR;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import automation.PestRoutes.Controller.Admin.Preferences.ServiceRelated.Service;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.Actions;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.RenewalTab;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;

public class CreateTrigger_AR extends BaseClass {

    Header header;
    AdminMainPage adminMainPage;
    TriggerRules triggerAdmin = new TriggerRules();
    RenewalTab renewalTab;
    Actions actions = new Actions();
    ARTab ar;

    private String descriptionTrigger = "trigger_ar_all_actions";
    private String age_PastDueDropDownValue = "Age";
    private String age_pastDueDays_InputField_Value = Double.toString(Utilities.generateRandomInteger(1));
    private String minimum_Balance = "0";
    private String maximum_Balance = "10000";
    private String valueType_DropDownValue = "Percentage";
    private String value_createInvoice_Action = Double.toString(Utilities.generateRandomInteger(2));
    private String serviceType_createInvoice_Action = "Misc Service";
    public List list = new ArrayList<String>();

    @Test
    public void createRenewalRule() throws Exception {
        createTrigger_AR(descriptionTrigger, age_PastDueDropDownValue, age_pastDueDays_InputField_Value);
        createAllARActions(descriptionTrigger);
        searchTrigger_AR(descriptionTrigger);
        assertActions_Reminder();
        searchTrigger_AR(descriptionTrigger);
        validateIfFailureExist();
    }

    // Create AR Trigger
    public void createTrigger_AR(String descriptionName, String arType, String daysPastDue) throws InterruptedException, Exception {
        header = new Header();
        adminMainPage = new AdminMainPage();
        renewalTab = new RenewalTab();
        ar = new ARTab();
        header.NavigateTo(header.adminTab);
        adminMainPage.navigateTo(adminMainPage.preferences);
        triggerAdmin.navigateToTriggerRules();
        triggerAdmin.clickAddTrigerButton();
        triggerAdmin.setStartDate(Utilities.currentDate("MM/dd/yyyy"));
        triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
        triggerAdmin.setDescription(descriptionName);
        triggerAdmin.selectDropdown(triggerAdmin.triggerTypeDropdown, triggerAdmin.triggerType_AR);
        triggerAdmin.selectDropdown(triggerAdmin.globalType, triggerAdmin.global_SpecificToThisOffice);
        triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
        triggerAdmin.selectDropdown(ar.age_PastDueDropDown, arType);
        ar.setAge_PastDueDays_InputField(daysPastDue);
        ar.setMinimum_Balance(minimum_Balance);
        ar.setMaximum_Balance(maximum_Balance);
        triggerAdmin.selectDropdown(renewalTab.multiUnitDropdown, renewalTab.multiUnit_Dropdown_Include);
        triggerAdmin.selectDropdown(renewalTab.propertyTypeDropdown, ar.propertyType_AllProperties);
        triggerAdmin.clickSaveButton();
    }

    // Search AR Trigger
    public void searchTrigger_AR(String descriptionName) {
        header = new Header();
        adminMainPage = new AdminMainPage();
        header.NavigateTo(header.adminTab);
        adminMainPage.navigateTo(adminMainPage.preferences);
        triggerAdmin.navigateToTriggerRules();
        triggerAdmin.searchTrigger(descriptionName);
        result(descriptionName, triggerAdmin.getDescriptionText(descriptionName), "AR Trigger Rule",
                "AR creation");
        triggerAdmin.clickEditTrigger(descriptionName);
    }

    // Create an SMS action
    public void SMSAction_AR() {
        actions.clickAddActionButton();
        triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendSMSMessageType_Action);
        triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
        actions.setMessageinAction_Type1(actions.sendSMSMessageType_Action, actions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Invoice action
    public void createInvoiceAction_AR() {
        actions.clickAddActionButton();
        triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.createInvoicesMessageType_Action);
        triggerAdmin.selectDropdown(actions.valueTypeDropDown, valueType_DropDownValue);
        actions.setValue_Action(value_createInvoice_Action);
        triggerAdmin.selectDropdown(actions.serviceType_Action, serviceType_createInvoice_Action);
        triggerAdmin.clickSaveButton();
    }

    // Create Voice AR action
    public void voiceAction_AR() {
        actions.clickAddActionButton();
        triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendVoiceMessageType_Action);
        triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
        triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.preRecordedMessageVoice_Reminder);
        triggerAdmin.selectDropdown(actions.preRecordedMessage_Message_Reminder, "Pest Promotion");
        triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.newMessage_Voice);
        actions.setMessageinAction_Type1(actions.sendVoiceMessageType_Action, actions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Email AR Action
    public void emailAction_AR() {
        actions.clickAddActionButton();
        triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.EmailMessageType_Action);
        triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
        actions.setEmailTitle(Utilities.generateRandomString(5));
        triggerAdmin.selectDropdown(actions.email_Type, actions.emailType_emailStatement);
        triggerAdmin.selectDropdown(actions.email_Type, actions.emailType_newEmailMessage);
        actions.setMessageinAction_Type2(actions.EmailMessageType_Action, actions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create SnailMail AR Action
    public void snailMailAction_AR() {
        actions.clickAddActionButton();
        triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.snailMailMessageType_Action);
        triggerAdmin.selectDropdown(actions.snailMail_letterType, actions.snailMail_letterType_sendLetter);
        triggerAdmin.selectDropdown(actions.snailMail_letterType, actions.snailMail_letterType_sendStatement);
        actions.setMessageinAction_Type2(actions.snailMailMessageType_Action, actions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create Freeze AR Action
    public void freezeCustomersAction_AR() {
        actions.clickAddActionButton();
        triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.freezeCustomersMessageType_Action);
        triggerAdmin.selectDropdown(actions.snailMail_letterType, actions.snailMail_letterType_sendLetter);
        triggerAdmin.selectDropdown(actions.snailMail_letterType, actions.snailMail_letterType_sendStatement);
        actions.setMessageinAction_Type2(actions.freezeCustomersMessageType_Action, actions.getPlaceHolders());
        triggerAdmin.clickSaveButton();
    }

    // Create add flags AR Action
    public void addFlagsAction_AR() {
        actions.clickAddActionButton();
        triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.addFlagsMessageType_Action);
        triggerAdmin.clickSaveButton();
    }

    // Create set collections stage AR Action
    public void setCollectionsStageAction_AR() {
        actions.clickAddActionButton();
        triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.setCollectionsStageMessageType_Action);
        actions.setCollectionsStage();
        Utilities.clickElement(actions.collectionsStage_Stage, Utilities.ElementType.XPath);
        triggerAdmin.selectDropdown(actions.collectionsStage_Stage, actions.stage_Sent);
        triggerAdmin.clickSaveButton();
    }

    // Create Send to ARM AR Action
    public void sendToARMAction_AR() {
        actions.clickAddActionButton();
        triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendTOARMMessageType_Action);
        triggerAdmin.clickSaveButton();
    }

    // Assert all created actions
    public void assertActions_Reminder() {
        actions = new Actions();
        ar = new ARTab();
        result(actions.EmailMessageType_Action, ar.getEmailActionTextValue(), "Email Action", "AR Trigger Rule");
        result(actions.sendSMSMessageType_Action, ar.getSMSActionTextValue(), "SMS Action", "AR Trigger Rule");
        result(actions.sendVoiceMessageType_Action, ar.getVoiceActionTextValue(), "Voice Action", "AR Trigger Rule");
        result(actions.createInvoicesMessageType_Action, ar.getCreateInvoiceActionTextValue(), "Create Invoice Action",
                "AR Trigger Rule");
        result(actions.snailMailMessageType_Action, ar.getSnailMailActionTextValue(), "Snail Mail Action",
                "AR Trigger Rule");
        result(actions.setCollectionsStageMessageType_Action, ar.getCollectionsStageActionTextValue(),
                "Collections Stage Action", "AR Trigger Rule");
        result(actions.sendTOARMMessageType_Action, ar.getARMStageActionTextValue(), "ARM Action", "AR Trigger Rule");
    }

    public void createAllARActions(String description)  {
        searchTrigger_AR(description);
        SMSAction_AR();
        searchTrigger_AR(description);
        createInvoiceAction_AR();
        searchTrigger_AR(description);
        voiceAction_AR();
        searchTrigger_AR(description);
        emailAction_AR();
        searchTrigger_AR(description);
        snailMailAction_AR();
        searchTrigger_AR(description);
        setCollectionsStageAction_AR();
		/*
		Add custom requires a custom flag. Method available for later use for Dev
		addFlagsAction_AR(); searchTrigger_AR();
		searchTrigger_AR(description);
		sendToARMAction_AR();
		*/
    }

    @SuppressWarnings("unchecked")
    private void result(String expected, String actual, String stepName, String testName) {
        if (AssertException.result(expected, actual, stepName).size() > 0) {
            list.add(AssertException.result(expected, actual, stepName));
        }
        Reporter.status(stepName, expected, actual, testName);
    }

    public void validateIfFailureExist() {
        AssertException.asserFailure(list);
    }

    // trigger Queue query
    public void hitTriggerEvent() {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerEvents.php");
    }
}
