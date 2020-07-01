package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus;

import java.io.IOException;

import org.testng.annotations.Test;

import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Trigger_SubscriptionDueForService;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
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
	private String descriptionSMS = "SMS_Customer";

	@Test
	public void triggerOnSave_AppointmentStatus() throws Exception {
		 createSMSTrigger();
		 editTrigger_triggerOnSave_CustomerStatus("Any");
		 customerStatus_SMSAction();
		 createNewCustomerWithPhoneNumber();
		 hitTriggerQueue();
		 assertSMSlog();
		 editTrigger_triggerOnSave_CustomerStatus("Frozen");
		 createNewCustomerWithPhoneNumberFrozenStatus();
		 hitTriggerQueue();
		 assertSMSlog();
		 editTrigger_triggerOnSave_CustomerStatus("Active");
		 createNewCustomerWithPhoneNumberActiveStatus();
		 hitTriggerQueue();
		 assertSMSlog();
		 editTrigger_triggerOnSave_CustomerStatus("Pending Cancel");
		 createNewCustomerWithPhoneNumberPendingCancelStatus();
		 hitTriggerQueue();
		 assertSMSlog();
		 editTrigger_triggerOnSave_CustomerStatus("Created");
		 createNewCustomerWithPhoneNumber();
		 hitTriggerQueue();
		 assertSMSlog();
	}

	// Create Trigger
	public void createSMSTrigger() throws Exception {
		createCustomerStatus.createTrigger_CustomerStatus(descriptionSMS);
	}

	// Edit Trigger Status
	public void editTrigger_triggerOnSave_CustomerStatus(String statusChange) throws Exception {
		subscriptionStatus = new SubscriptionStatusTab();
		createCustomerStatus.searchTrigger_appointmentStatus(descriptionSMS);
		triggerAdmin.clickEditTrigger(descriptionSMS);
		triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger, subscriptionStatus.whenToTrigger_triggerOnSave);
		triggerAdmin.selectDropdown(subscriptionStatus.statusChangedTo, statusChange);
		triggerAdmin.clickSaveButton();
	}

	// Set SMS Customer Status
	public void customerStatus_SMSAction() throws InterruptedException {
		createCustomerStatus.searchTrigger_appointmentStatus(descriptionSMS);
		createCustomerStatus.SMSAction_CustomerStatus();
	}

	// Hit trigger Queue
	public void hitTriggerQueue() {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerQueue.php");
	}

	// assert SMS Log
	public void assertSMSlog() throws IOException, Exception {
		subscriptionDueForService = new Trigger_SubscriptionDueForService();
		subscriptionDueForService.assertLog();
	}

	// Create customer with SMS
	public void createNewCustomerWithPhoneNumber() throws Exception {
		newCustomer = new CreateNewCustomer();
		newCustomer.createCustomerWithoutAddress();
	}

	// Create customer with SMS Frozen Status
	public void createNewCustomerWithPhoneNumberFrozenStatus() throws Exception {
		newCustomer = new CreateNewCustomer();
		customerAdmin = new CustomerViewDialog_Admin();
		overviewHeader = new CustomerViewDialog_Header();
		newCustomer.createCustomerWithoutAddress();
		overviewHeader.NavigateTo(overviewHeader.adminTabInDialog);
		if (customerAdmin.getCustomerStatus() == customerAdmin.customerStatus_Frozen) {
			customerAdmin.changeAccountStatus_Frozen(customerAdmin.reassignServicePropertiesToNewBillingAccount);
		}
	}

	// Create customer with SMS Active Status
	public void createNewCustomerWithPhoneNumberActiveStatus() throws Exception {
		newCustomer = new CreateNewCustomer();
		customerAdmin = new CustomerViewDialog_Admin();
		overviewHeader = new CustomerViewDialog_Header();
		newCustomer.createCustomerWithoutAddress();
		overviewHeader.NavigateTo(overviewHeader.adminTabInDialog);
		if (customerAdmin.getCustomerStatus() != customerAdmin.customerStatus_Frozen) {
			customerAdmin.changeAccountStatus_Active();
		}
	}

	// Create customer with SMS Pending Cancel
	public void createNewCustomerWithPhoneNumberPendingCancelStatus() throws Exception {
		newCustomer = new CreateNewCustomer();
		overviewHeader = new CustomerViewDialog_Header();
		customer = new CreateCustomerDIalog();
		newCustomer.createCustomerWithoutAddress();
		overviewHeader.NavigateTo(overviewHeader.infoTabInDialog);
		customer.clickPendingCancelCheckBox();
	}

}
