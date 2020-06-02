package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules;

import org.testng.annotations.Test;

import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus.CreateTrigger_AppointmentStatus;
import automation.PestRoutes.Utilities.BaseClass;

public class SuperTrigger extends BaseClass {
	Trigger_Reminder Reminder_trigger = new Trigger_Reminder();
	Trigger_AR AR_trigger = new Trigger_AR();
	CreateTrigger_AppointmentStatus AppointmentStatus_trigger = new CreateTrigger_AppointmentStatus();
	Trigger_CustomerStatus CustomerStatus_trigger = new Trigger_CustomerStatus();
	Trigger_Renewal Renewal_trigger = new Trigger_Renewal();
	Trigger_SubscriptionDueForService SubscriptionDueForService_trigger = new Trigger_SubscriptionDueForService();
	Trigger_SubscriptionStatus SubscriptionStatus_trigger = new Trigger_SubscriptionStatus();

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