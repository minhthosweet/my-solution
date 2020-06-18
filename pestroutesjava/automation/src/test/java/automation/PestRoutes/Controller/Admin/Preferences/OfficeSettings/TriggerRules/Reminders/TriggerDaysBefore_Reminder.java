package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Reminders;

import org.testng.annotations.Test;
import automation.PestRoutes.Utilities.BaseClass;

public class TriggerDaysBefore_Reminder extends BaseClass {

	CreateTrigger_Reminder createReminder = new CreateTrigger_Reminder();

	private String description_TriggerBeforeDays = "TriggerBeforeDays_Reminder";

	@Test
	public void createReminderRule() throws Exception {

		// Create trigger
		createTriggerDaysBefore_Reminder(description_TriggerBeforeDays);

		// Create Actions
		Reminder_createAllActions();
	}

	// Create Trigger
	public void createTriggerDaysBefore_Reminder(String description) throws Exception {
		createReminder.createReminderRule();
	}
	
	//Create All Actions
	public void Reminder_createAllActions() {
		createReminder.emailAction_Reminder();
		createReminder.SMSAction_Reminder();
		createReminder.voiceAction_Reminder();
	}
}
