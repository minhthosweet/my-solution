package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Reminders;

import java.io.IOException;

import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ReminderTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.SubscriptionStatusTab;
import automation.PestRoutes.Utilities.BaseClass;

//This TC requires FieldRoutes Intervention
public class TriggerOnCheckIn_Reminder extends BaseClass {
	CreateTrigger_Reminder createReminder = new CreateTrigger_Reminder();
	TriggerRules triggerAdmin;
	SubscriptionStatusTab subscriptionStatus;
	ReminderTab reminder;
	TriggerDaysBefore_Reminder triggerDaysBefore_Reminder;

	private String description_TriggerOnCheckIn = "TriggerOnCheckIn_Reminder";

	@Test
	public void createReminderRule() throws Exception {

		// Create Trigger
		createTriggerOnCheckIn_Reminder(description_TriggerOnCheckIn);

		// Create all actions
		reminder_createAllActions();

		// Create customer with Appointment
		createCustomerPlusAppointment();

		// hit trigger
		hitTriggerReminderQuery_daysBefore();

		// assert log
		assertLog();
	}

	// Create Trigger
	public void createTriggerOnCheckIn_Reminder(String description) throws Exception {
		triggerAdmin = new TriggerRules();
		subscriptionStatus = new SubscriptionStatusTab();
		reminder = new ReminderTab();
		createReminder.createTrigger_Reminder(description);
		createReminder.searchTrigger_Reminder(description);
		triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger, reminder.triggerOnCheckIn_whenToTrigger);
	}

	public void reminder_createAllActions() {
		triggerDaysBefore_Reminder = new TriggerDaysBefore_Reminder();
		triggerDaysBefore_Reminder.reminder_createAllActions();
	}

	public void createCustomerPlusAppointment() throws Exception {
		triggerDaysBefore_Reminder = new TriggerDaysBefore_Reminder();
		triggerDaysBefore_Reminder.createCustomerWithAppointment();
	}

	public void hitTriggerReminderQuery_daysBefore() {
		triggerAdmin = new TriggerRules();
		triggerAdmin.hitTriggerQueue();
	}

	public void assertLog() throws IOException, Exception {
		createReminder.assertLog();
	}
}
