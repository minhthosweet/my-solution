package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.Trigger_Actions;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ReminderTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.RenewalTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.SubscriptionStatusTab;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;

public class CreateTrigger_AppointmentStatus extends BaseClass {
	Header header;
	AdminMainPage adminMainPage;
	TriggerRules triggerAdmin = new TriggerRules();
	RenewalTab renewalTab;
	Trigger_Actions triggerActions = new Trigger_Actions();
	ReminderTab reminder;
	ARTab ar;
	SubscriptionStatusTab subscriptionStatus;
	private String descriptionTrigger = "trigger_appointmentStatus_all_actions";
	public List list = new ArrayList<String>();

	@Test
	public void createAppointmentStatusRule() throws Exception {
		createTrigger_AppointmentStatus(descriptionTrigger);
		searchTrigger_appointmentStatus(descriptionTrigger);
		SMSAction_AppointmentStatus();
		searchTrigger_appointmentStatus(descriptionTrigger);
		voiceAction_AppointmentStatus();
		searchTrigger_appointmentStatus(descriptionTrigger);
		emailAction_AppointmentStatus();
		searchTrigger_appointmentStatus(descriptionTrigger);
		snailMailAction_AppointmentStatus();
		/*searchTrigger_appointmentStatus(descriptionTrigger);
		webhookAction_AppointmentStatus();*/
		searchTrigger_appointmentStatus(descriptionTrigger);
		sendEmployeeEmail_AppointmentStatus();
		searchTrigger_appointmentStatus(descriptionTrigger);
		addAlert_AppointmentStatus();
		searchTrigger_appointmentStatus(descriptionTrigger);
		addTask_AppointmentStatus();
		searchTrigger_appointmentStatus(descriptionTrigger);
		sendEmployeeSMS_AppointmentStatus();
		searchTrigger_appointmentStatus(descriptionTrigger);
		sendEmployeeVoice_AppointmentStatus();
		searchTrigger_appointmentStatus(descriptionTrigger);
		assertActions_AppointmentStatus();
		validateIfFailureExist();
	}

	public void createTrigger_AppointmentStatus(String descriptionName) throws Exception {
		header = new Header();
		adminMainPage = new AdminMainPage();
		renewalTab = new RenewalTab();
		subscriptionStatus = new SubscriptionStatusTab();
		ar = new ARTab();
		reminder = new ReminderTab();
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.clickAddTrigerButton();
		triggerAdmin.setStartDate(Utilities.currentDate("MM/dd/yyyy"));
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_NotActive);
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
		triggerAdmin.setDescription(descriptionName);
		triggerAdmin.selectDropdown(triggerAdmin.triggerTypeDropdown, triggerAdmin.triggerType_AppointmentStatus);
		triggerAdmin.selectDropdown(triggerAdmin.globalType, triggerAdmin.global_SpecificToThisOffice);
		triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger,
				subscriptionStatus.whenToTrigger_triggerAfterTime);
		triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger, subscriptionStatus.whenToTrigger_triggerOnSave);
		triggerAdmin.selectDropdown(subscriptionStatus.statusChangedTo, subscriptionStatus.statusChangedTo_Pending);
		triggerAdmin.selectDropdown(subscriptionStatus.statusChangedTo, subscriptionStatus.statusChangedTo_Any);
		triggerAdmin.selectDropdown(reminder.hasInitialServiceDropdown_Reminder,
				reminder.hasInitialService_Any_Reminder);
		triggerAdmin.clickSaveButton();
	}

	// Search Appointment Status Trigger
	public void searchTrigger_appointmentStatus(String descriptionName) {
		header = new Header();
		adminMainPage = new AdminMainPage();
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.searchTrigger(descriptionName);
		result(descriptionName, triggerAdmin.getDescriptionText(descriptionName), "Search Customer",
				"Appointment Status Creation");
		triggerAdmin.clickEditTrigger(descriptionName);
	}

	// Create a SMS action
	public void SMSAction_AppointmentStatus() throws InterruptedException {
		triggerActions.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendVoiceMessageType_Action);
		Thread.sleep(3000);
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendSMSMessageType_Action);
		triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_Yes);
		triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
		triggerActions.setMessageinAction_Type1(triggerActions.sendSMSMessageType_Action, triggerActions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Voice Appointment Status action
	public void voiceAction_AppointmentStatus() {
		triggerActions.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendVoiceMessageType_Action);
		triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
		triggerAdmin.selectDropdown(triggerActions.voiceType_Reminder, triggerActions.preRecordedMessageVoice_Reminder);
//		triggerAdmin.selectDropdown(actions.preRecordedMessage_Message_Reminder, "Pest Promotion");
		triggerAdmin.selectDropdown(triggerActions.voiceType_Reminder, triggerActions.newMessage_Voice);
		triggerActions.setMessageinAction_Type1(triggerActions.sendVoiceMessageType_Action, triggerActions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Email Appointment Status Action
	public void emailAction_AppointmentStatus() {
		triggerActions.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.EmailMessageType_Action);
		triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
		triggerActions.setEmailTitle(Utilities.generateRandomString(5));
		triggerActions.setMessageinAction_Type2(triggerActions.EmailMessageType_Action, triggerActions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create SnailMail Appointment Status Action
	public void snailMailAction_AppointmentStatus() {
		triggerActions.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.snailMailMessageType_Action);
		triggerActions.setMessageinAction_Type2(triggerActions.snailMailMessageType_Action, triggerActions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Webhook
	public void webhookAction_AppointmentStatus() {
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
	public void sendEmployeeEmail_AppointmentStatus() {
		triggerActions.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendEmployeeEmail_SubscriptionStatus);
		triggerActions.setEmailTitle_SubscriptionStatus(Utilities.generateRandomString(5));
		triggerActions.setEmailAddress_SubscriptionStatus(Utilities.generateRandomString(5) + "@gmail.com");
		triggerActions.setMessageinAction_Type2(triggerActions.sendEmployeeEmail_SubscriptionStatus, triggerActions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Add Alert
	public void addAlert_AppointmentStatus() {
		triggerActions.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.addAlert_SubscriptionStatus);
		triggerActions.setMessageinAction_Type1(triggerActions.addAlert_SubscriptionStatus, triggerActions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Add Task
	public void addTask_AppointmentStatus() {
		triggerActions.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.addTask_SubscriptionStatus);
		triggerActions.setDaysTillDueAddTask_SubscriptionStatus(Double.toString(Utilities.generateRandomInteger(1)));
		triggerActions.setMessageinAction_Type1(triggerActions.addTask_SubscriptionStatus, triggerActions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action Send Employee SMS
	public void sendEmployeeSMS_AppointmentStatus() throws IOException {
		triggerActions.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendEmployeeSMS_SubscriptionStatus);
		triggerActions.setEmployeePhoneNumber_SubscriptionStatus(triggerActions.sendEmployeeSMS_SubscriptionStatus,
				getData("phoneNumber", generalData));
		triggerActions.setMessageinAction_Type1(triggerActions.sendEmployeeSMS_SubscriptionStatus, triggerActions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action Send Employee Voice
	public void sendEmployeeVoice_AppointmentStatus() throws IOException {
		triggerActions.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendEmployeeVoice_SubscriptionStatus);
		triggerActions.setEmployeePhoneNumber_SubscriptionStatus(triggerActions.sendEmployeeVoice_SubscriptionStatus,
				getData("phoneNumber", generalData));
		triggerAdmin.selectDropdown(triggerActions.voiceType_SubscriptionStatus, triggerActions.preRecordedMessageVoice_Reminder);
	//	triggerAdmin.selectDropdown(actions.preRecordedMessage_Message_SubscriptionStatus, "Pest Promotion");
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
		/*result(actions.webhookMessageType_Action, renewalTab.getWebhookActionTextValue(), "Webhook Action",
				"Appointment Status Creation");*/
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
		if (AssertException.result(expected, actual, stepName).size() > 0) {
			list.add(AssertException.result(expected, actual, stepName));
		}
		Reporter.status(stepName, expected, actual, testName);
	}

	public void validateIfFailureExist() {
		AssertException.assertFailure(list);
	}
}
