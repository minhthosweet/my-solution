package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Reminders;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AR.AR_Age;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Customers.AppointmentsTab.TestScheduledAppointments;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.ReminderTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_InfoTab;
import automation.PestRoutes.PageObject.DashboardPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus.TriggerOnSave_AppointmentStatus;
import automation.PestRoutes.Utilities.BaseClass;

import static automation.PestRoutes.Utilities.Utilities.*;

public class TriggerDaysBefore_Reminder extends BaseClass {

	CreateTrigger_Reminder createReminder = new CreateTrigger_Reminder();
	TriggerOnSave_AppointmentStatus triggerOnSave_AppintmentStatus;
	DashboardPage userOnDashboard = new DashboardPage();
	AdminMainPage userOnAdminComponent = new AdminMainPage();
	PreferencesPage userOnPreferences = new PreferencesPage();
	TriggerRules userOnTriggerRulesPage = new TriggerRules();
	ReminderTab userSelectsRemindersTrigger = new ReminderTab();
	AR_Age testTrigger = new AR_Age();
	CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
	CustomerViewDialog_InfoTab userOnInfoTab = new CustomerViewDialog_InfoTab();
	CreateNewCustomer testCustomer = new CreateNewCustomer();
	TestScheduledAppointments testAppointment = new TestScheduledAppointments();
	CreateCustomerDialog userCreateNewCustomer = new CreateCustomerDialog();

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
	public void reminder_createAllActions() throws InterruptedException {
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

	@Given("I Set Up {string} Trigger Type That {string} An Appointment")
	public void automateSettingUpRemindersTriggerType(String trigger, String whenToTrigger) {
		userOnAdminComponent = userOnDashboard.goToAdminComponent();
		userOnPreferences = userOnAdminComponent.clickPreferencesSubComponent();
		userOnTriggerRulesPage = userOnPreferences.clickTriggerRules();
		userOnTriggerRulesPage.addActiveTrigger
				(trigger, trigger + " Automation Trigger", currentDate("MM/dd/yy"));
		userSelectsRemindersTrigger.selectWhenToTrigger(whenToTrigger);
		userSelectsRemindersTrigger.typeDaysBefore("0");
		userSelectsRemindersTrigger.typeFlagToInclude(testTrigger.genericFlag);
	}

	@And("I Complete An Action To {string} With {string} Type")
	public void automateSendingCompleteActionForReminders(String action, String type) {
		userSelectsRemindersTrigger.clickAddActionButton();
		userSelectsRemindersTrigger.completeReminderAction(action, type);
		userSelectsRemindersTrigger.clickSaveButton();
	}

	@When("I Add {string} Flag To The Customer Before Scheduling An Appointment")
	public void automateSettingUpCustomerWithFlagAndScheduleAppointment(String flagCode) {
		testCustomer.createCustomerWithBasicInfo();
		userOnInfoTab = sameUser.goToInfoTab();
		userOnInfoTab.selectCustomerGenericFlag(flagCode);
		sameUser.clickSaveButton();
		testAppointment.automateSchedulingAppointment();
	}

	@When("I Add {string} Flag To The Customer Before Canceling An Appointment")
	public void automateSettingUpCustomerWithFlagAndCancelAppointment(String flagCode) {
		testCustomer.createCustomerWithBasicInfo();
		userOnInfoTab = sameUser.goToInfoTab();
		userOnInfoTab.selectCustomerGenericFlag(flagCode);
		sameUser.clickSaveButton();
		testAppointment.automateSchedulingAppointment();
		testAppointment.automateCancellingAppointment();
	}

	@When("I Add {string} Flag To The Customer Before Completing An Appointment")
	public void automateSettingUpCustomerWithFlagAndCompleteAppointment(String flagCode) {
		testCustomer.automateCreatingCustomerWithSubscription();
		userOnInfoTab = sameUser.goToInfoTab();
		userOnInfoTab.selectCustomerGenericFlag(flagCode);
		sameUser.clickSaveButton();
		testAppointment.automateCompletingAnAppointment();
	}
}