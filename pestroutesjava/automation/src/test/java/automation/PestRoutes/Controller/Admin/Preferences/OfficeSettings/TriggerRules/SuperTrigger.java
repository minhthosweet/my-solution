package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules;

import org.testng.annotations.Test;

import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus.CreateTrigger_AppointmentStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.CreateTrigger_CustomerStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Reminders.CreateTrigger_Reminder;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionStatus.CreateTrigger_SubscriptionStatus;
import automation.PestRoutes.Utilities.BaseClass;

public class SuperTrigger extends BaseClass {
	CreateTrigger_Reminder Reminder_trigger = new CreateTrigger_Reminder();
	Trigger_AR AR_trigger = new Trigger_AR();
	CreateTrigger_AppointmentStatus AppointmentStatus_trigger = new CreateTrigger_AppointmentStatus();
	CreateTrigger_CustomerStatus CustomerStatus_trigger = new CreateTrigger_CustomerStatus();
	Trigger_Renewal Renewal_trigger = new Trigger_Renewal();
	Trigger_SubscriptionDueForService SubscriptionDueForService_trigger = new Trigger_SubscriptionDueForService();
	CreateTrigger_SubscriptionStatus SubscriptionStatus_trigger = new CreateTrigger_SubscriptionStatus();

	@Test
	public void createTrigger() throws Exception {
		Reminder_trigger.createReminderRule();
		AR_trigger.createRenewalRule();
		AppointmentStatus_trigger.createAppointmentStatusRule();
		CustomerStatus_trigger.createCustomerStatusRule();
		Renewal_trigger.createRenewalRule();
		SubscriptionDueForService_trigger.createSubscriptionDueForService();
		SubscriptionStatus_trigger.createSubscriptionStatusRule();
	}

}