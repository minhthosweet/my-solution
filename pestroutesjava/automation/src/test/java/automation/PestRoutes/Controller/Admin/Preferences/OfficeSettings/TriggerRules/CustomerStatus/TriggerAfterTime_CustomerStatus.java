package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus;

import java.io.IOException;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.SubscriptionStatusTab;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Utilities;

// need to run query before the trigger
// update customers set dateCancelled = '[previous day]' where customerID = [Customer ID];

public class TriggerAfterTime_CustomerStatus extends BaseClass {

	TriggerOnSave_CustomerStatus triggerOnSave = new TriggerOnSave_CustomerStatus();
	SubscriptionStatusTab subscriptionStatus;
	TriggerRules triggerAdmin;
	CreateTrigger_CustomerStatus createCustomerStatus;
	
	private String description_TriggerAfterTime = "TriggerAfterTime_CustomerStatus";
	private String days_After_Change = "1";
	
	@Test
	public void triggerAfterTime_CustomerStatus() throws Exception {
		// Create trigger
		createTriggerAfterTime_CustomerStatus(description_TriggerAfterTime);

		// Create Actions
		customerStatus_CreateAllActions();

		// Trigger on Save for Frozen customer
		editTrigger_triggerAfterTime_CustomerStatus("Frozen");
		createNewCustomerWithPhoneEmailBilling_Frozen();
		hitTriggerQueue();
		assertFrozen_allActions();
		assertFrozen_RemovePayment();
		
		// Trigger on Save for Created customer
		editTrigger_triggerAfterTime_CustomerStatus("Created");
		createNewCustomerWithPhoneEmailBilling_Created();
		hitTriggerQueue();
		assertFrozen_allActions();
		
		// Trigger on Save for Active customer
		editTrigger_triggerAfterTime_CustomerStatus("Active");
		createNewCustomerWithPhoneEmailBilling_Active();
		hitTriggerQueue();
		assertFrozen_allActions();
		
		// Trigger on Save for Pending Cancel customer
		editTrigger_triggerAfterTime_CustomerStatus("Pending Cancel");
		createNewCustomerWithPhoneEmailBilling_PendingCancel();
		hitTriggerQueue();
		assertFrozen_allActions();

	}

	public void createTriggerAfterTime_CustomerStatus(String description) throws Exception {
		triggerOnSave.createTriggerOnSave_CustomerStatus(description);
	}
	
	public void editTrigger_triggerAfterTime_CustomerStatus(String statusChange) {
		subscriptionStatus = new SubscriptionStatusTab();
		triggerAdmin = new TriggerRules();
		createCustomerStatus = new CreateTrigger_CustomerStatus();
		createCustomerStatus.searchTrigger_appointmentStatus(description_TriggerAfterTime);
		triggerAdmin.clickEditTrigger(description_TriggerAfterTime);
		triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger, subscriptionStatus.whenToTrigger_triggerAfterTime);
		triggerAdmin.setDaysAfterChange(days_After_Change);
		triggerAdmin.selectDropdown(subscriptionStatus.statusChangedTo, statusChange);
		triggerAdmin.clickSaveButton();
	}
	
	public void customerStatus_CreateAllActions() throws InterruptedException, IOException {
		triggerOnSave.customerStatus_SMSAction();
		triggerOnSave.customerStatus_EmailAction();
		triggerOnSave.customerStatus_VoiceAction();
		triggerOnSave.customerStatus_SnailMailAction();
		triggerOnSave.customerStatus_employeeEmailAction();
		triggerOnSave.customerStatus_alertAction();
		triggerOnSave.customerStatus_taskAction();
		triggerOnSave.customerStatus_employeeVoiceAction();
		triggerOnSave.customerStatus_removePaymentProfileAction();
	}
	
	public void createNewCustomerWithPhoneEmailBilling_Frozen() throws Exception {
		triggerOnSave.createNewCustomerwithPhoneEmailBilling_Frozen();
	}
	
	public void createNewCustomerWithPhoneEmailBilling_Created() throws Exception {
		triggerOnSave.createNewCustomerwithPhoneEmailBilling_Created();
	}
	
	public void createNewCustomerWithPhoneEmailBilling_Active() throws Exception {
		triggerOnSave.createNewCustomerwithPhoneEmailBilling_Active();
	}
	
	public void createNewCustomerWithPhoneEmailBilling_PendingCancel() throws Exception {
		triggerOnSave.createNewCustomerwithPhoneEmailBilling_PendingCancel();
	}
	
	public void hitTriggerQueue() {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerCustomerStatus.php");
	}
	
	public void assertFrozen_allActions() throws IOException, Exception {
		triggerOnSave.assertSMSlog();
		triggerOnSave.assertEmaillog();
		triggerOnSave.assertVoicelog();
		triggerOnSave.assertSnailMaillog();
		triggerOnSave.assertEmployeeEmaillog();
		triggerOnSave.assertAlertlog();
		triggerOnSave.assertTasklog();
		triggerOnSave.assertEmployeeVoicelog();
		
	}	
	
	public void assertFrozen_RemovePayment() throws IOException, Exception{
		triggerOnSave.assertRemovePaymentlog();
	}
}
