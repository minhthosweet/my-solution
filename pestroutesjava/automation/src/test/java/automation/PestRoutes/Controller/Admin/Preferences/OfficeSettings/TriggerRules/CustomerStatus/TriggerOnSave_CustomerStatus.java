package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus;

import java.io.IOException;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Trigger_SubscriptionDueForService;
import automation.PestRoutes.Controller.Billings.Billing;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.SubscriptionStatusTab;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDIalog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Admin;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Utilities;

public class TriggerOnSave_CustomerStatus extends BaseClass {

	CreateTrigger_CustomerStatus createCustomerStatus = new CreateTrigger_CustomerStatus();
	TriggerRules triggerAdmin = new TriggerRules();
	SubscriptionStatusTab subscriptionStatus;
	CreateNewCustomer newCustomer;
	Trigger_SubscriptionDueForService subscriptionDueForService;
	CustomerViewDialog_Admin customerAdmin;
	CustomerViewDialog_Header overviewHeader;
	CreateCustomerDIalog customer;
	Billing billing;
	Header header;

	private String description_TriggerOnSave = "SMS_Customer";

	@Test
	public void triggerOnSave_AppointmentStatus() throws Exception {

		// Create trigger
		createTriggerOnSave_CustomerStatus();
		
		// Create Actions
		customerStatus_SMSAction();
		customerStatus_EmailAction();
		customerStatus_VoiceAction();
		customerStatus_SnailMailAction();
		customerStatus_employeeEmailAction();
		customerStatus_alertAction();
		customerStatus_taskAction();
		customerStatus_employeeVoiceAction();
		customerStatus_removePaymentProfileAction();

		// Trigger on Save for Frozen customer
		editTrigger_triggerOnSave_CustomerStatus("Frozen");
		createNewCustomerwithPhoneEmailBilling_Frozen();
		hitTriggerQueue();
		assertSMSlog();
		assertEmaillog();
		assertVoicelog();
		assertSnailMaillog();
		assertEmployeeEmaillog();
		assertAlertlog();
		assertTasklog();
		assertEmployeeVoicelog();
		assertRemovePaymentlog();

		// Trigger on Save for Created customer
		editTrigger_triggerOnSave_CustomerStatus("Created");
		createNewCustomerwithPhoneEmailBilling_Created();
		hitTriggerQueue();
		assertSMSlog();
		assertEmaillog();
		assertVoicelog();
		assertSnailMaillog();
		assertEmployeeEmaillog();
		assertAlertlog();
		assertTasklog();
		assertEmployeeVoicelog();

		// Trigger on Save for Active customer
		editTrigger_triggerOnSave_CustomerStatus("Active");
		createNewCustomerwithPhoneEmailBilling_Active();
		hitTriggerQueue();
		assertSMSlog();
		assertEmaillog();
		assertVoicelog();
		assertSnailMaillog();
		assertEmployeeEmaillog();
		assertAlertlog();
		assertTasklog();
		assertEmployeeVoicelog();

		// Trigger on Save for Pending Cancel customer
		editTrigger_triggerOnSave_CustomerStatus("Pending Cancel");
		createNewCustomerwithPhoneEmailBilling_PendingCancel();
		hitTriggerQueue();
		assertSMSlog();
		assertEmaillog();
		assertVoicelog();
		assertSnailMaillog();
		assertEmployeeEmaillog();
		assertAlertlog();
		assertTasklog();
		assertEmployeeVoicelog();
	}

	// Create Trigger
	public void createTriggerOnSave_CustomerStatus() throws Exception {
		createCustomerStatus.createTrigger_CustomerStatus(description_TriggerOnSave);
	}

	// Edit Trigger Status
	public void editTrigger_triggerOnSave_CustomerStatus(String statusChange) throws Exception {
		subscriptionStatus = new SubscriptionStatusTab();
		createCustomerStatus.searchTrigger_appointmentStatus(description_TriggerOnSave);
		triggerAdmin.clickEditTrigger(description_TriggerOnSave);
		triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger, subscriptionStatus.whenToTrigger_triggerOnSave);
		triggerAdmin.selectDropdown(subscriptionStatus.statusChangedTo, statusChange);
		triggerAdmin.clickSaveButton();
	}

	// Set SMS Action Customer Status
	public void customerStatus_SMSAction() throws InterruptedException {
		createCustomerStatus.searchTrigger_appointmentStatus(description_TriggerOnSave);
		createCustomerStatus.SMSAction_CustomerStatus();
	}

	// Set Email Action Customer Status
	public void customerStatus_EmailAction() throws InterruptedException {
		createCustomerStatus.searchTrigger_appointmentStatus(description_TriggerOnSave);
		createCustomerStatus.emailAction_CustomerStatus();
	}

	// Set Voice Action Customer Status
	public void customerStatus_VoiceAction() throws InterruptedException {
		createCustomerStatus.searchTrigger_appointmentStatus(description_TriggerOnSave);
		createCustomerStatus.voiceAction_CustomerStatus();
	}

	// Set SnailMail Action Customer Status
	public void customerStatus_SnailMailAction() throws InterruptedException {
		createCustomerStatus.searchTrigger_appointmentStatus(description_TriggerOnSave);
		createCustomerStatus.snailMailAction_CustomerStatus();
	}

	// Set Employee Mail Action Customer Status
	public void customerStatus_employeeEmailAction() throws InterruptedException {
		createCustomerStatus.searchTrigger_appointmentStatus(description_TriggerOnSave);
		createCustomerStatus.sendEmployeeEmail_CustomerStatus();
	}

	// Set Alert Action Customer Status
	public void customerStatus_alertAction() throws InterruptedException {
		createCustomerStatus.searchTrigger_appointmentStatus(description_TriggerOnSave);
		createCustomerStatus.addAlert_CustomerStatus();
	}

	// Set Task Action Customer Status
	public void customerStatus_taskAction() throws InterruptedException {
		createCustomerStatus.searchTrigger_appointmentStatus(description_TriggerOnSave);
		createCustomerStatus.addTask_CustomerStatus();
	}

	// Set Employee Voice Action Customer Status
	public void customerStatus_employeeVoiceAction() throws InterruptedException, IOException {
		createCustomerStatus.searchTrigger_appointmentStatus(description_TriggerOnSave);
		createCustomerStatus.sendEmployeeVoice_CustomerStatus();
	}

	// Set Remove Payment Profile Action Customer Status
	public void customerStatus_removePaymentProfileAction() throws InterruptedException, IOException {
		subscriptionDueForService = new Trigger_SubscriptionDueForService();
		createCustomerStatus.searchTrigger_appointmentStatus(description_TriggerOnSave);
		subscriptionDueForService.removePaymentProfile_subscriptionForService();
	}

	// Hit trigger Queue
	public void hitTriggerQueue() {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerQueue.php");
	}

	// assert SMS Log
	public void assertSMSlog() throws IOException, Exception {
		subscriptionDueForService = new Trigger_SubscriptionDueForService();
		subscriptionDueForService.assertSMSLog();
	}

	// assert Email Log
	public void assertEmaillog() throws IOException, Exception {
		subscriptionDueForService = new Trigger_SubscriptionDueForService();
		subscriptionDueForService.assertEMailLog();
	}

	// assert Email Log
	public void assertVoicelog() throws IOException, Exception {
		subscriptionDueForService = new Trigger_SubscriptionDueForService();
		subscriptionDueForService.assertVoiceLog();
	}

	// assert Snail Mail Log
	public void assertSnailMaillog() throws IOException, Exception {
		subscriptionDueForService = new Trigger_SubscriptionDueForService();
		subscriptionDueForService.assertSnailMailLog();
	}

	// assert Employee EMail Log
	public void assertEmployeeEmaillog() throws IOException, Exception {
		subscriptionDueForService = new Trigger_SubscriptionDueForService();
		subscriptionDueForService.assertEmployeeEMailLog();
	}

	// assert Alert Log
	public void assertAlertlog() throws IOException, Exception {
		subscriptionDueForService = new Trigger_SubscriptionDueForService();
		subscriptionDueForService.assertAlertLog();
	}

	// assert Task Log
	public void assertTasklog() throws IOException, Exception {
		subscriptionDueForService = new Trigger_SubscriptionDueForService();
		subscriptionDueForService.assertTaskLog();
	}

	// assert Employee Voice Log
	public void assertEmployeeVoicelog() throws IOException, Exception {
		subscriptionDueForService = new Trigger_SubscriptionDueForService();
		subscriptionDueForService.assertEmplopeeVoiceLog();
	}

	// assert Remove Payment Log
	public void assertRemovePaymentlog() throws IOException, Exception {
		subscriptionDueForService = new Trigger_SubscriptionDueForService();
		subscriptionDueForService.assertRemovePaymentLog();
	}

	// Create customer with Frozen Status
	public void createNewCustomerwithPhoneEmailBilling_Frozen() throws Exception {
		newCustomer = new CreateNewCustomer();
		customerAdmin = new CustomerViewDialog_Admin();
		overviewHeader = new CustomerViewDialog_Header();
		billing = new Billing();
		newCustomer.createCustomerWithEmail();
		overviewHeader.NavigateTo(overviewHeader.billingTabInDialog);
		billing.navigateToCC();
		billing.addCC();
		billing.navigateToBankAccount();
		billing.addBankAccount();
		overviewHeader.NavigateTo(overviewHeader.adminTabInDialog);
		customerAdmin.changeAccountStatus_Active();
		header = new Header();
		header.Search_A_Customer(getData("customerName", generalData));
		overviewHeader.NavigateTo(overviewHeader.adminTabInDialog);
		customerAdmin.changeAccountStatus_Frozen(customerAdmin.reassignServicePropertiesToNewBillingAccount);
	}

	// Create customer with Active Status
	public void createNewCustomerwithPhoneEmailBilling_Active() throws Exception {
		newCustomer = new CreateNewCustomer();
		customerAdmin = new CustomerViewDialog_Admin();
		overviewHeader = new CustomerViewDialog_Header();
		newCustomer.createCustomerWithEmail();
		overviewHeader.NavigateTo(overviewHeader.billingTabInDialog);
		overviewHeader.NavigateTo(overviewHeader.adminTabInDialog);
		customerAdmin.changeAccountStatus_Active();
	}

	// Create customer with Created Status
	public void createNewCustomerwithPhoneEmailBilling_Created() throws Exception {
		newCustomer = new CreateNewCustomer();
		newCustomer.createCustomerWithEmail();
	}

	// Create customer with Created Status
	public void createNewCustomerwithPhoneEmailBilling_PendingCancel() throws Exception {
		newCustomer = new CreateNewCustomer();
		header = new Header();
		overviewHeader = new CustomerViewDialog_Header();
		customer = new CreateCustomerDIalog();
		newCustomer.createCustomerWithEmail();
		header.Search_A_Customer(getData("customerName", generalData));
		overviewHeader.NavigateTo(overviewHeader.infoTabInDialog);
		customer.clickPendingCancelCheckBox();
	}
}
