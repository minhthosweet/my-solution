package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Trigger_SubscriptionDueForService;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.TriggerOnSave_CustomerStatus;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.SubscriptionStatusTab;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Utilities;

public class TriggerOnSave_AppointmentStatus extends BaseClass {
	CreateTrigger_AppointmentStatus createAppointmentStatus = new CreateTrigger_AppointmentStatus();
	TriggerRules triggerAdmin = new TriggerRules();
	SubscriptionStatusTab subscriptionStatus;
	Header header;
	CreateNewCustomer createCustomer;
	ValidateRenewal validateRenewal;
	Trigger_SubscriptionDueForService subscriptionDueForService;
	TriggerOnSave_CustomerStatus customerStatus;

	private String descriptionSMS = "SMS_Appointment";
	public List list = new ArrayList<String>();

	@Test
	public void triggerOnSave_AppointmentStatus() throws Exception {
		createSMSTrigger();
		editTrigger_triggerOnSave_AppointmentStatus("Any");
		appointmentStatus_SMSAction();
		createCutomerWithSubscription();
		scheduleappointment();
		hitTriggerQueue();

	}

	// Create Trigger
	public void createSMSTrigger() throws Exception {
		createAppointmentStatus.createTrigger_AppointmentStatus(descriptionSMS);
	}

	// Edit Trigger
	public void editTrigger_triggerOnSave_AppointmentStatus(String statusChange) throws Exception {
		customerStatus = new TriggerOnSave_CustomerStatus();
		customerStatus.editTrigger_triggerOnSave_CustomerStatus(statusChange);
	}

	// Set SMS Appointment Status
	public void appointmentStatus_SMSAction() throws InterruptedException {
		createAppointmentStatus.searchTrigger_appointmentStatus(descriptionSMS);
		createAppointmentStatus.SMSAction_AppointmentStatus();
	}

	// Create customer with Renewal Subscription
	public void createCutomerWithSubscription() throws Exception {
		subscriptionDueForService = new Trigger_SubscriptionDueForService();
		subscriptionDueForService.createSubscription_beforeDueDate();

	}

	// Schedule an appointment
	public void scheduleappointment() throws Exception {
		validateRenewal = new ValidateRenewal();
		validateRenewal.scheduleSubscription("11:00");
	}

	// Hit trigger Queue
	public void hitTriggerQueue() {
		triggerAdmin.hitTriggerQueue();
	}

	// Hit trigger AppointmentStatus
	public void hitTriggerAppointmentStatus() {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerAppointmentStatus.php");
	}

	// assert SMS Log
	public void assertSMSlog() throws IOException, Exception {
		subscriptionDueForService = new Trigger_SubscriptionDueForService();
		subscriptionDueForService.assertSMSLog();
	}

}
