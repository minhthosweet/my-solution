package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.Actions;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.RenewalTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.SubscriptionStatusTab;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;

public class CreateTrigger_SubscriptionStatus extends BaseClass {
	Header header;
	AdminMainPage adminMainPage;
	TriggerRules triggerAdmin = new TriggerRules();
	RenewalTab renewalTab;
	Actions actions = new Actions();
	ARTab ar;
	SubscriptionStatusTab subscriptionStatus;
	private String descriptionTrigger = "trigger_subscriptionStatus_all_actions";
	public List list = new ArrayList<String>();
	public String days_After_Change = "1";

	@Test
	public void createSubscriptionStatusRule() throws InterruptedException, Exception {
		createTrigger_SubscriptionStatus(descriptionTrigger);
		searchTrigger_subscriptionStatus(descriptionTrigger);
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

	public void createTrigger_SubscriptionStatus(String descriptionName) throws Exception {
		header = new Header();
		adminMainPage = new AdminMainPage();
		renewalTab = new RenewalTab();
		subscriptionStatus = new SubscriptionStatusTab();
		ar = new ARTab();
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.clickAddTrigerButton();
		triggerAdmin.setStartDate(Utilities.currentDate("MM/dd/yyyy"));
		triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
		triggerAdmin.setDescription(descriptionName);
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
	public void searchTrigger_subscriptionStatus(String descriptionName) {
		header = new Header();
		adminMainPage = new AdminMainPage();
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.searchTrigger(descriptionName);
		result(descriptionName, triggerAdmin.getDescriptionText(descriptionName), "Search Customer",
				"Subscription Status Creation");
		triggerAdmin.clickEditTrigger(descriptionName);
	}

	// Create a SMS action
	public void SMSAction_SubscriptionStatus() throws InterruptedException {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendVoiceMessageType_Action);
		Thread.sleep(3000);
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendSMSMessageType_Action);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_Yes);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		actions.setMessageinAction_Type1(actions.sendSMSMessageType_Action, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Voice Subscription Status action
	public void voiceAction_SubscriptionStatus() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendVoiceMessageType_Action);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.preRecordedMessageVoice_Reminder);
		triggerAdmin.selectDropdown(actions.preRecordedMessage_Message_Reminder, "Pest Promotion");
		triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.newMessage_Voice);
		actions.setMessageinAction_Type1(actions.sendVoiceMessageType_Action, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Email Subscription Status Action
	public void emailAction_SubscriptionStatus() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.EmailMessageType_Action);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		actions.setEmailTitle(Utilities.generateRandomString(5));
		actions.setMessageinAction_Type2(actions.EmailMessageType_Action, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create SnailMail Subscription Status Action
	public void snailMailAction_SubscriptionStatus() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.snailMailMessageType_Action);
		actions.setMessageinAction_Type2(actions.snailMailMessageType_Action, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Webhook
	public void webhookAction_SubscriptionStatus() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.webhookMessageType_Action);
		triggerAdmin.selectDropdown(actions.webhook_MethodType, actions.webhookMethod_GET);
		triggerAdmin.selectDropdown(actions.webhook_MethodType, actions.webhookMethod_POST);
		actions.messageInWebhook(actions.URLMessage_Wehbook, actions.getPlaceHolders());
		actions.messageInWebhook(actions.requestHeaderMessage_Webhook, actions.getPlaceHolders());
		actions.messageInWebhook(actions.requestBodyMessage_Webhook, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Send Employee Email
	public void sendEmployeeEmail_SubscriptionStatus() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendEmployeeEmail_SubscriptionStatus);
		actions.setEmailTitle_SubscriptionStatus(Utilities.generateRandomString(5));
		actions.setEmailAddress_SubscriptionStatus(Utilities.generateRandomString(5) + "@gmail.com");
		actions.setMessageinAction_Type2(actions.sendEmployeeEmail_SubscriptionStatus, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Add Alert
	public void addAlert_SubscriptionStatus() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.addAlert_SubscriptionStatus);
		actions.setMessageinAction_Type1(actions.addAlert_SubscriptionStatus, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Add Task
	public void addTask_SubscriptionStatus() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.addTask_SubscriptionStatus);
		actions.setDaysTillDueAddTask_SubscriptionStatus(Double.toString(Utilities.generateRandomInteger(1)));
		actions.setMessageinAction_Type1(actions.addTask_SubscriptionStatus, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action Send Employee SMS
	public void sendEmployeeSMS_SubscriptionStatus() throws IOException {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendEmployeeSMS_SubscriptionStatus);
		actions.setEmployeePhoneNumber_SubscriptionStatus(actions.sendEmployeeSMS_SubscriptionStatus,
				getData("phoneNumber", generalData));
		actions.setMessageinAction_Type1(actions.sendEmployeeSMS_SubscriptionStatus, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action Send Employee Voice
	public void sendEmployeeVoice_SubscriptionStatus() throws IOException {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendEmployeeVoice_SubscriptionStatus);
		actions.setEmployeePhoneNumber_SubscriptionStatus(actions.sendEmployeeVoice_SubscriptionStatus,
				getData("phoneNumber", generalData));
		triggerAdmin.selectDropdown(actions.voiceType_SubscriptionStatus, actions.preRecordedMessageVoice_Reminder);
		triggerAdmin.selectDropdown(actions.preRecordedMessage_Message_SubscriptionStatus, "Pest Promotion");
		triggerAdmin.selectDropdown(actions.voiceType_SubscriptionStatus, actions.newMessage_Voice);
		actions.setMessageinAction_Type1(actions.sendEmployeeVoice_SubscriptionStatus, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Assert all created actions
	public void assertActions_SubscriptionStatus() {
		ar = new ARTab();
		renewalTab = new RenewalTab();
		subscriptionStatus = new SubscriptionStatusTab();
		result(actions.sendSMSMessageType_Action, ar.getSMSActionTextValue(), "SMS Action",
				"Subscription Status Creation");
		result(actions.sendVoiceMessageType_Action, ar.getVoiceActionTextValue(), "Voice Action",
				"Subscription Status Creation");
		result(actions.EmailMessageType_Action, ar.getEmailActionTextValue(), "Email Action",
				"Subscription Status Creation");
		result(actions.webhookMessageType_Action, renewalTab.getWebhookActionTextValue(), "Webhook Action",
				"Subscription Status Creation");
		result(actions.snailMailMessageType_Action, ar.getSnailMailActionTextValue(), "Snail Mail Action",
				"Subscription Status Creation");
		result(actions.sendEmployeeEmail_SubscriptionStatus, subscriptionStatus.getSendEmployeeEmailActionTextValue(),
				"Send Employee Email Action", "Subscription Status Creation");
		result(actions.addAlert_SubscriptionStatus, subscriptionStatus.getAddAlertActionTextValue(), "Add Alert Action",
				"Subscription Status Creation");
		result(actions.addTask_SubscriptionStatus, subscriptionStatus.getAddTaskActionTextValue(), "Add Task Action",
				"Subscription Status Creation");
		result(actions.sendEmployeeSMS_SubscriptionStatus, subscriptionStatus.getSendEmploeeSMSActionTextValue(),
				"Send Employee SMS Action", "Subscription Status Creation");
		result(actions.sendEmployeeVoice_SubscriptionStatus, subscriptionStatus.getSendEmployeeVoiceActionTextValue(),
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
		AssertException.asserFailure(list);
	}
}
