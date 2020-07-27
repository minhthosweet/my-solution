package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Reminders;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus.TriggerOnSave_AppointmentStatus;
import automation.PestRoutes.Utilities.BaseClass;

public class TriggerDaysBefore_Reminder extends BaseClass {

	CreateTrigger_Reminder createReminder = new CreateTrigger_Reminder();
	TriggerOnSave_AppointmentStatus triggerOnSave_AppintmentStatus;

	private String description_TriggerBeforeDays = "TriggerBeforeDays_Reminder";

	@Test
	public void createReminderRule() throws Exception {

		// Create trigger
		createTriggerDaysBefore_Reminder(description_TriggerBeforeDays);

		// Create Actions
		createReminder.searchTrigger_Reminder(description_TriggerBeforeDays);
		reminder_createAllActions();

		// Create customer
		createCustomerWithAppointment();

		// hit trigger
		hitTriggerQueue();

		// Assert logs
		assertlog();
	}

	// Create Trigger
	public void createTriggerDaysBefore_Reminder(String description) throws Exception {
		createReminder.createTrigger_Reminder(description_TriggerBeforeDays);
	}

	// Create All Actions
	public void reminder_createAllActions() {
		createReminder.emailAction_Reminder(description_TriggerBeforeDays);
		createReminder.SMSAction_Reminder(description_TriggerBeforeDays);
		createReminder.voiceAction_Reminder(description_TriggerBeforeDays);
	}

	// Create Customer with Appointment
	public void createCustomerWithAppointment() throws Exception {
		triggerOnSave_AppintmentStatus = new TriggerOnSave_AppointmentStatus();
		triggerOnSave_AppintmentStatus.createCutomerWithSubscription();
		triggerOnSave_AppintmentStatus.scheduleappointment();
	}

	// Hit trigger
	public void hitTriggerQueue() {
		createReminder.hitTriggerReminderQuery_daysBefore();
	}

	public void assertlog() throws Exception {
		createReminder.assertLog();
	}
}
