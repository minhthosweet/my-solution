package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Reminders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import automation.PestRoutes.Utilities.*;
import io.cucumber.java.en.When;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Schedules.ScheduleAppt;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.Trigger_Actions;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.ReminderTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.RenewalTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.SubscriptionStatusTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.Scheduling.SchedulingAppointmentDialog;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;

public class CreateTrigger_Reminder extends AppData {
	Header header;
	AdminMainPage adminMainPage;
	TriggerRules triggerAdmin = new TriggerRules();
	ReminderTab reminder;
	RenewalTab renewalTab;
	ARTab ar;
	Trigger_Actions triggerActions;
	SubscriptionStatusTab subscriptionStatus;
	CreateNewCustomer createCustomer;
	ScheduleAppt scheduleAppt;
	SchedulingAppointmentDialog confirmAppt;
	CustomerviewDialog_AppointmentsTab appointmentTab;
	CustomerViewDialog_Header overviewHeader;
	SchedulingTab scheduleTab;

	private String descriptionTrigger = "trigger_reminder_sms_actions_checkIN";
	private String numberOfDays_Before_Reminder = "1";
	public List list = new ArrayList<String>();
	private String daysBefore_Trigger = "1";
	private String serviceType = "Roaches";
	private String editAlertNote_Text = "Sorry, this note is not editable.";
	public String SMSMAppointmentReminderNote = "SMS Appointment Reminder";
	public String VoiceMAppointmentReminderNote = "Voice Appointment Reminder";
	public String EmailMAppointmentReminderNote = "Email Appointment Reminder";

	@Test
	public void createReminderRule() throws Exception {
		createTrigger_Reminder(descriptionTrigger);
		searchTrigger_Reminder(descriptionTrigger);
		emailAction_Reminder(descriptionTrigger);
		searchTrigger_Reminder(descriptionTrigger);
		SMSAction_Reminder(descriptionTrigger);
		searchTrigger_Reminder(descriptionTrigger);
		voiceAction_Reminder(descriptionTrigger);
		searchTrigger_Reminder(descriptionTrigger);
		assertActions_Reminder();
		editTrigger_Reminder_DaysBefore();
		createCustomer();
		scheduleAppointments();
		hitTriggerReminderQuery_daysBefore();
		assertLog();
		validateIfFailureExist();
	}

	// Create Reminder Trigger
	public void createTrigger_Reminder(String descriptionName) throws Exception {
		header = new Header();
		adminMainPage = new AdminMainPage();
		reminder = new ReminderTab();
		renewalTab = new RenewalTab();
		ar = new ARTab();
		triggerActions = new Trigger_Actions();
		subscriptionStatus = new SubscriptionStatusTab();
		header.navigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.clickAddTrigerButton();
		triggerAdmin.setStartDate(GetDate.minusOneWeekToDate(Utilities.currentDate("MM/dd/yyyy")));
		triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
		triggerAdmin.setDescription(descriptionName);
		triggerAdmin.selectDropdown(triggerAdmin.triggerTypeDropdown, triggerAdmin.triggerType_Reminders);
		triggerAdmin.selectDropdown(triggerAdmin.globalType, triggerAdmin.global_SpecificToThisOffice);
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger, reminder.triggerOnCheckIn_whenToTrigger);
		triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger, reminder.triggerDaysBefore_whenToTrigger);
		reminder.setdaysBefore_Reminder(numberOfDays_Before_Reminder);
		triggerAdmin.selectDropdown(renewalTab.propertyTypeDropdown, ar.propertyType_AllProperties);
		triggerAdmin.selectDropdown(renewalTab.multiUnitDropdown, renewalTab.multiUnit_Dropdown_Include);
		triggerAdmin.selectDropdown(reminder.hasInitialServiceDropdown_Reminder,
				reminder.hasInitialService_Any_Reminder);
		triggerAdmin.clickSaveButton();
	}

	// Search Reminder Trigger
	public void searchTrigger_Reminder(String descriptionName) throws InterruptedException {
		header = new Header();
		adminMainPage = new AdminMainPage();
		header.navigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.searchTrigger(descriptionName);
		result(descriptionName, triggerAdmin.getDescriptionText(descriptionName), "Reminder Creation",
				"Reminder Trigger Rule");
		triggerAdmin.clickEditTrigger(descriptionName);
	}

	// Create an Reminder action
	public void emailAction_Reminder(String description) throws InterruptedException {
		triggerActions = new Trigger_Actions();
		searchTrigger_Reminder(description);
		triggerActions.clickAddActionButton();
		//triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendSMSReminder);
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendEmailReminder);
		triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
		triggerAdmin.selectDropdown(triggerActions.emailType_Reminder, triggerActions.standardReminderEmail_Reminder);
		triggerAdmin.selectDropdown(triggerActions.emailType_Reminder, triggerActions.customReminderEmail_Reminder);
		triggerActions.setMessageinAction_Type3(triggerActions.sendEmailReminder, triggerActions.getPlaceHolders());
		//actions.removeAction(actions.sendSMSReminder);
		triggerAdmin.clickSaveButton();
	}

	// Create second Reminder action
	public void SMSAction_Reminder(String description) throws InterruptedException {
		triggerActions = new Trigger_Actions();
		searchTrigger_Reminder(description);
		triggerActions.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendSMSReminder);
		triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
		triggerAdmin.selectDropdown(triggerActions.SMSType_Reminder, triggerActions.standardReminderSMS_Reminder);
		triggerAdmin.selectDropdown(triggerActions.SMSType_Reminder, triggerActions.customSMS_Reminder);
		triggerActions.setMessageinAction_Type1(triggerActions.sendSMSReminder, triggerActions.getPlaceHolders() + " SMS Reminder");
		triggerAdmin.clickSaveButton();
	}

	// Create third Reminder action
	public void voiceAction_Reminder(String description) throws InterruptedException {
		triggerActions = new Trigger_Actions();
		searchTrigger_Reminder(description);
		triggerActions.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendVoiceReminder);
		triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
		triggerAdmin.selectDropdown(triggerActions.voiceType_Reminder, triggerActions.standardReminderVoice_Reminder);
		triggerAdmin.selectDropdown(triggerActions.voiceType_Reminder, triggerActions.preRecordedMessageVoice_Reminder);
		triggerAdmin.selectDropdown(triggerActions.preRecordedMessage_Message_Reminder, "Pest Promotion");
		triggerAdmin.selectDropdown(triggerActions.voiceType_Reminder, triggerActions.standardReminderVoice_Reminder);
		triggerAdmin.selectDropdown(triggerActions.voiceType_Reminder, triggerActions.customReminderVoice_Reminder);
		triggerActions.setMessageinAction_Type1(triggerActions.sendVoiceReminder, triggerActions.getPlaceHolders() + " Voice Reminder");
		triggerAdmin.clickSaveButton();
	}

	// Assert all created actions
	public void assertActions_Reminder() {
		triggerActions = new Trigger_Actions();
		reminder = new ReminderTab();
		result(triggerActions.sendEmailReminder, reminder.getEmailActionTextValue(), "Email Reminder",
				"Reminder Trigger Rule");
		result(triggerActions.sendSMSReminder, reminder.getSMSActionTextValue(), "SMS Reminder", "Reminder Trigger Rule");
		result(triggerActions.sendVoiceReminder, reminder.getVoiceActionTextValue(), "Voice Reminder",
				"Reminder Trigger Rule");

	}

	public void editTrigger_Reminder_DaysBefore() throws Exception {
		reminder = new ReminderTab();
		subscriptionStatus = new SubscriptionStatusTab();
		triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
		reminder.setdaysBefore_Reminder(daysBefore_Trigger);
		triggerAdmin.clickSaveButton();
	}

	public void createCustomer() throws Exception {
		header = new Header();
		header.navigateTo(header.schedulingTab);
		createCustomer = new CreateNewCustomer();
		createCustomer.createCustomerWithAddress();
		createCustomer.validateCreatedCustomerNameAndAddress();

	}

	public void scheduleAppointments() throws IOException, Exception {
		String userID = getData("userID", generalData);
		header = new Header();
		confirmAppt = new SchedulingAppointmentDialog();
		scheduleAppt = new ScheduleAppt();
		scheduleTab = new SchedulingTab();
		overviewHeader = new CustomerViewDialog_Header();
		appointmentTab = new CustomerviewDialog_AppointmentsTab();
		scheduleAppt.addRoute();
		scheduleAppt.addAppointment(userID, serviceType, scheduleAppt.scheduleTime);
		triggerAdmin.selectDropdown(appointmentTab.subscriptionType_schedulinTab,
				appointmentTab.standAloneService_Scheduling);
		confirmAppt.clickScheduleButton();
		header.searchCustomer_History(getData("userID", generalData));
		overviewHeader.navigateTo(overviewHeader.appointmentsTabInDialog);
		appointmentTab.clickScheduledService(serviceType);
		appointmentTab.clickStatusButton();
		appointmentTab.clickSaveAndCompleteButton();
	}

	// trigger Queue query
	@When("I execute the trigger Reminder script")
	public void hitTriggerReminderQuery_daysBefore() {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerReminders.php");
	}

	public void assertLog() throws IOException, Exception {
		header = new Header();
		reminder = new ReminderTab();
		header.searchCustomer_History(getData("userID", generalData));
		overviewHeader = new CustomerViewDialog_Header();
		overviewHeader.navigateTo(overviewHeader.notesTabInDialog);
		overviewHeader.clickCustomerContactsInNotesTab();
		result(editAlertNote_Text, reminder.getAlertText_Notes(), "Edit Note Alert", "Reminder Trigger Rule");
		result(SMSMAppointmentReminderNote, reminder.ConfirmationNote(SMSMAppointmentReminderNote),
				"SMS Notification Affirmative", "Reminder Trigger Rule");
		result(VoiceMAppointmentReminderNote, reminder.ConfirmationNote(VoiceMAppointmentReminderNote),
				"Voice Notification Affirmative", "Reminder Trigger Rule");
		result(EmailMAppointmentReminderNote, reminder.ConfirmationNote(EmailMAppointmentReminderNote),
				"Email Notification Affirmative", "Reminder Trigger Rule");
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
