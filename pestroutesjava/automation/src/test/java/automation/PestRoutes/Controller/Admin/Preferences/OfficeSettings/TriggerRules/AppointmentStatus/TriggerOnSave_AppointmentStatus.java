package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Trigger_SubscriptionDueForService;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.SubscriptionStatusTab;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;

public class TriggerOnSave_AppointmentStatus extends BaseClass {
	CreateTrigger_AppointmentStatus createAppointmentStatus;
	TriggerRules triggerAdmin = new TriggerRules();
	SubscriptionStatusTab subscriptionStatus;
	Header header;
	CreateNewCustomer createCustomer;
	ValidateRenewal validateRenewal;
	Trigger_SubscriptionDueForService subscriptionDueForService;

	private String descriptionSMS = "SMS_Appointment";
	public List list = new ArrayList<String>();

	@Test
	public void triggerOnSave_AppointmentStatus() throws Exception {
		createTrigger();
		editTrigger_triggerOnSave_AnyAppointmentStatus();
		appointmentStatus_SMS();
		createCutomerWithSubscription();
		scheduleappointment();

	}

	public void createTrigger() throws Exception {
		createAppointmentStatus = new CreateTrigger_AppointmentStatus();
		createAppointmentStatus.createTrigger_AppointmentStatus(descriptionSMS);
	}

	public void editTrigger_triggerOnSave_AnyAppointmentStatus() throws Exception {
		subscriptionStatus = new SubscriptionStatusTab();
		createAppointmentStatus = new CreateTrigger_AppointmentStatus();
		createAppointmentStatus.searchTrigger_appointmentStatus(descriptionSMS);
		triggerAdmin.clickEditTrigger(descriptionSMS);
		triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger, subscriptionStatus.whenToTrigger_triggerOnSave);
		triggerAdmin.selectDropdown(subscriptionStatus.statusChangedTo, subscriptionStatus.statusChangedTo_Any);
		triggerAdmin.clickSaveButton();
	}

	// Set Appointment Status
	public void appointmentStatus_SMS() throws InterruptedException {
		createAppointmentStatus = new CreateTrigger_AppointmentStatus();
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
		Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerQueue.php");
	}

	// Hit trigger AppointmentStatus
	public void hitTriggerAppointmentStatus() {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerAppointmentStatus.php");
	}

	// assert SMS Log
	public void assertSMSlog() throws IOException, Exception {
		subscriptionDueForService = new Trigger_SubscriptionDueForService();
		subscriptionDueForService.assertLog();
	}

}
