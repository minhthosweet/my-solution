package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Reminders;

import automation.PestRoutes.Utilities.Utilities;
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

        // hit trigger
        hitTriggerQueue();
    }

    // Create Trigger
    public void createTriggerDaysBefore_Reminder(String description) throws Exception {
        createReminder.createTrigger_Reminder(description_TriggerBeforeDays);
    }

    //Create All Actions
    public void Reminder_createAllActions() {
        createReminder.emailAction_Reminder();
        createReminder.SMSAction_Reminder();
        createReminder.voiceAction_Reminder();
    }


    public void hitTriggerQueue() {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerReminders.php");
    }
}
