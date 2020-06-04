package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.Actions;
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
	Actions actions = new Actions();
	ReminderTab reminder;
	ARTab ar;
	SubscriptionStatusTab subscriptionStatus;
	private String descriptionTrigger = "trigger_appointmentStatus_all_actions";
	public List list = new ArrayList<String>();

	@Test
	public void createAppointmentStatusRule() throws InterruptedException, Exception {
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
		triggerAdmin.selectDropdown(subscriptionStatus.statusChangedTo, subscriptionStatus.statusChangedTo_Complete);
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
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendVoiceMessageType_Action);
		Thread.sleep(3000);
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendSMSMessageType_Action);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_Yes);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		actions.setMessageinAction_Type1(actions.sendSMSMessageType_Action, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Voice Appointment Status action
	public void voiceAction_AppointmentStatus() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendVoiceMessageType_Action);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.preRecordedMessageVoice_Reminder);
//		triggerAdmin.selectDropdown(actions.preRecordedMessage_Message_Reminder, "Pest Promotion");
		triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.newMessage_Voice);
		actions.setMessageinAction_Type1(actions.sendVoiceMessageType_Action, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Email Appointment Status Action
	public void emailAction_AppointmentStatus() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.EmailMessageType_Action);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		actions.setEmailTitle(Utilities.generateRandomString(5));
		actions.setMessageinAction_Type2(actions.EmailMessageType_Action, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create SnailMail Appointment Status Action
	public void snailMailAction_AppointmentStatus() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.snailMailMessageType_Action);
		actions.setMessageinAction_Type2(actions.snailMailMessageType_Action, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Webhook
	public void webhookAction_AppointmentStatus() {
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
	public void sendEmployeeEmail_AppointmentStatus() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendEmployeeEmail_SubscriptionStatus);
		actions.setEmailTitle_SubscriptionStatus(Utilities.generateRandomString(5));
		actions.setEmailAddress_SubscriptionStatus(Utilities.generateRandomString(5) + "@gmail.com");
		actions.setMessageinAction_Type2(actions.sendEmployeeEmail_SubscriptionStatus, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Add Alert
	public void addAlert_AppointmentStatus() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.addAlert_SubscriptionStatus);
		actions.setMessageinAction_Type1(actions.addAlert_SubscriptionStatus, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Add Task
	public void addTask_AppointmentStatus() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.addTask_SubscriptionStatus);
		actions.setDaysTillDueAddTask_SubscriptionStatus(Double.toString(Utilities.generateRandomInteger(1)));
		actions.setMessageinAction_Type1(actions.addTask_SubscriptionStatus, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action Send Employee SMS
	public void sendEmployeeSMS_AppointmentStatus() throws IOException {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendEmployeeSMS_SubscriptionStatus);
		actions.setEmployeePhoneNumber_SubscriptionStatus(actions.sendEmployeeSMS_SubscriptionStatus,
				getData("phoneNumber", generalData));
		actions.setMessageinAction_Type1(actions.sendEmployeeSMS_SubscriptionStatus, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action Send Employee Voice
	public void sendEmployeeVoice_AppointmentStatus() throws IOException {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendEmployeeVoice_SubscriptionStatus);
		actions.setEmployeePhoneNumber_SubscriptionStatus(actions.sendEmployeeVoice_SubscriptionStatus,
				getData("phoneNumber", generalData));
		triggerAdmin.selectDropdown(actions.voiceType_SubscriptionStatus, actions.preRecordedMessageVoice_Reminder);
	//	triggerAdmin.selectDropdown(actions.preRecordedMessage_Message_SubscriptionStatus, "Pest Promotion");
		triggerAdmin.selectDropdown(actions.voiceType_SubscriptionStatus, actions.newMessage_Voice);
		actions.setMessageinAction_Type1(actions.sendEmployeeVoice_SubscriptionStatus, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Assert all created actions
	public void assertActions_AppointmentStatus() {
		ar = new ARTab();
		renewalTab = new RenewalTab();
		subscriptionStatus = new SubscriptionStatusTab();
		result(actions.sendSMSMessageType_Action, ar.getSMSActionTextValue(), "SMS Action",
				"Appointment Status Creation");
		result(actions.sendVoiceMessageType_Action, ar.getVoiceActionTextValue(), "Voice Action",
				"Appointment Status Creation");
		result(actions.EmailMessageType_Action, ar.getEmailActionTextValue(), "Email Action",
				"Appointment Status Creation");
		/*result(actions.webhookMessageType_Action, renewalTab.getWebhookActionTextValue(), "Webhook Action",
				"Appointment Status Creation");*/
		result(actions.snailMailMessageType_Action, ar.getSnailMailActionTextValue(), "Snail Mail Action",
				"Appointment Status Creation");
		result(actions.sendEmployeeEmail_SubscriptionStatus, subscriptionStatus.getSendEmployeeEmailActionTextValue(),
				"Send Employee Email Action", "Appointment Status Creation");
		result(actions.addAlert_SubscriptionStatus, subscriptionStatus.getAddAlertActionTextValue(), "Add Alert Action",
				"Appointment Status Creation");
		result(actions.addTask_SubscriptionStatus, subscriptionStatus.getAddTaskActionTextValue(), "Add Task Action",
				"Appointment Status Creation");
		result(actions.sendEmployeeSMS_SubscriptionStatus, subscriptionStatus.getSendEmploeeSMSActionTextValue(),
				"Send Employee SMS Action", "Appointment Status Creation");
		result(actions.sendEmployeeVoice_SubscriptionStatus, subscriptionStatus.getSendEmployeeVoiceActionTextValue(),
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
		AssertException.asserFailure(list);
	}
}
