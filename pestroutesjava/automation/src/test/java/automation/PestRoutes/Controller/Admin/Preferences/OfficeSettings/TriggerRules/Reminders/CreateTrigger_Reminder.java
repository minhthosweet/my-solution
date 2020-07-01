package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Reminders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Schedules.ScheduleAppt;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.Actions;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ReminderTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.RenewalTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.SubscriptionStatusTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.RoutePage.SchedulingAppointmentDialog;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;

public class CreateTrigger_Reminder extends BaseClass {
	Header header;
	AdminMainPage adminMainPage;
	TriggerRules triggerAdmin = new TriggerRules();
	ReminderTab reminder;
	RenewalTab renewalTab;
	ARTab ar;
	Actions actions;
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
		emailAction_Reminder();
		searchTrigger_Reminder(descriptionTrigger);
		SMSAction_Reminder();
		searchTrigger_Reminder(descriptionTrigger);
		voiceAction_Reminder();
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
		actions = new Actions();
		subscriptionStatus = new SubscriptionStatusTab();
		header.NavigateTo(header.adminTab);
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
	public void searchTrigger_Reminder(String descriptionName) {
		header = new Header();
		adminMainPage = new AdminMainPage();
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.searchTrigger(descriptionName);
		result(descriptionName, triggerAdmin.getDescriptionText(descriptionName), "Reminder Creation",
				"Reminder Trigger Rule");
		triggerAdmin.clickEditTrigger(descriptionName);
	}

	// Create an Reminder action
	public void emailAction_Reminder() {
		actions = new Actions();
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendSMSReminder);
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendEmailReminder);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		triggerAdmin.selectDropdown(actions.emailType_Reminder, actions.standardReminderEmail_Reminder);
		triggerAdmin.selectDropdown(actions.emailType_Reminder, actions.customReminderEmail_Reminder);
		actions.setMessageinAction_Type2(actions.sendEmailReminder, actions.getPlaceHolders() + " Email Reminder");
		actions.removeAction(actions.sendSMSReminder);
		triggerAdmin.clickSaveButton();
	}

	// Create second Reminder action
	public void SMSAction_Reminder() {
		actions = new Actions();
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendSMSReminder);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		triggerAdmin.selectDropdown(actions.SMSType_Reminder, actions.standardReminderSMS_Reminder);
		triggerAdmin.selectDropdown(actions.SMSType_Reminder, actions.customSMS_Reminder);
		actions.setMessageinAction_Type1(actions.sendSMSReminder, actions.getPlaceHolders() + " SMS Reminder");
		triggerAdmin.clickSaveButton();
	}

	// Create third Reminder action
	public void voiceAction_Reminder() {
		actions = new Actions();
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendVoiceReminder);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.standardReminderVoice_Reminder);
		triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.preRecordedMessageVoice_Reminder);
		triggerAdmin.selectDropdown(actions.preRecordedMessage_Message_Reminder, "Pest Promotion");
		triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.standardReminderVoice_Reminder);
		triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.customReminderVoice_Reminder);
		actions.setMessageinAction_Type1(actions.sendVoiceReminder, actions.getPlaceHolders() + " Voice Reminder");
		triggerAdmin.clickSaveButton();
	}

	// Assert all created actions
	public void assertActions_Reminder() {
		actions = new Actions();
		reminder = new ReminderTab();
		result(actions.sendEmailReminder, reminder.getEmailActionTextValue(), "Email Reminder",
				"Reminder Trigger Rule");
		result(actions.sendSMSReminder, reminder.getSMSActionTextValue(), "SMS Reminder", "Reminder Trigger Rule");
		result(actions.sendVoiceReminder, reminder.getVoiceActionTextValue(), "Voice Reminder",
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
		header.NavigateTo(header.schedulingTab);
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
		header.Search_A_Customer(getData("userID", generalData));
		overviewHeader.NavigateTo(overviewHeader.appointmentsTabInDialog);
		appointmentTab.clickScheduledService(serviceType);
		appointmentTab.clickStatusButton();
		appointmentTab.clickSaveAndCompleteButton();
	}

	public void hitTriggerReminderQuery_daysBefore() {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerReminders.php");
	}

	public void assertLog() throws IOException, Exception {
		header = new Header();
		reminder = new ReminderTab();
		header.Search_A_Customer(getData("userID", generalData));
		overviewHeader = new CustomerViewDialog_Header();
		overviewHeader.NavigateTo(overviewHeader.notesTabInDialog);
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
		AssertException.asserFailure(list);
	}
}
