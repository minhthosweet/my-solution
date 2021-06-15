package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.Reporter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionDueForService.CreateTrigger_SubscriptionDueForService;
import automation.PestRoutes.Controller.Billings.Billing;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.SubscriptionStatusTab;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Admin;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;

public class TriggerOnSave_CustomerStatus extends AppData {

    CreateTrigger_CustomerStatus createCustomerStatus = new CreateTrigger_CustomerStatus();
    TriggerRules triggerAdmin;
    SubscriptionStatusTab subscriptionStatus;
    CreateNewCustomer newCustomer;
    CreateTrigger_SubscriptionDueForService subscriptionDueForService = new CreateTrigger_SubscriptionDueForService();
    CustomerViewDialog_Admin customerAdmin;
    CustomerViewDialog_Header overviewHeader;
    CreateCustomerDialog customer;
    Billing billing;
    Header header;

    private String description_TriggerOnSave = "TriggerOnSave_CustomerStatus";
    public List list = new ArrayList<String>();

    @Test
    public void triggerOnSave_CustomerStatus() throws Exception {

        // Create trigger
        createTriggerOnSave_CustomerStatus(description_TriggerOnSave);

        // Create Actions
        customerStatus_addAllAction(description_TriggerOnSave);

        // Trigger on Save for Created customer
        editTrigger_triggerOnSave_CustomerStatus("Created", description_TriggerOnSave);
        createNewCustomerwithPhoneEmailBilling_Created();
        hitTriggerQueue();
        assertlog();

        // Trigger on Save for Active customer
        editTrigger_triggerOnSave_CustomerStatus("Active", description_TriggerOnSave);
        createNewCustomerwithPhoneEmailBilling_Active();
        hitTriggerQueue();
        assertlog();

        // Trigger on Save for Pending Cancel customer
        editTrigger_triggerOnSave_CustomerStatus("Pending Cancel", description_TriggerOnSave);
        createNewCustomerwithPhoneEmailBilling_PendingCancel();
        hitTriggerQueue();
        assertlog();

        // Trigger on Save for Frozen customer
        customerStatus_removePaymentProfileAction(description_TriggerOnSave);
        editTrigger_triggerOnSave_CustomerStatus("Frozen", description_TriggerOnSave);
        customerStatus_removePaymentProfileAction(description_TriggerOnSave);
        createNewCustomerwithPhoneEmailBilling_Frozen();
        hitTriggerQueue();
        assertlog();
        assertRemovePaymentlog();
    }

    // Create Trigger
    public void createTriggerOnSave_CustomerStatus(String description) throws Exception {
        createCustomerStatus.createTrigger_CustomerStatus(description);
    }

    // Edit Trigger Status
    @Then("I edit the trigger status on trigger on save {string} of type {string}")
    public void editTrigger_triggerOnSave_CustomerStatus(String statusChange, String description) throws InterruptedException {
        subscriptionStatus = new SubscriptionStatusTab();
        triggerAdmin = new TriggerRules();
        createCustomerStatus.searchTrigger_appointmentStatus(description);
        triggerAdmin.clickEditTrigger(description);
        triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger, subscriptionStatus.whenToTrigger_triggerOnSave);
        triggerAdmin.selectDropdown(subscriptionStatus.statusChangedTo, statusChange);
        triggerAdmin.clickSaveButton();
        triggerAdmin.searchTrigger(description);
        result(description, triggerAdmin.getDescriptionText(description), "Search Customer",
                "Subscription Status Creation");
    }

    // Set SMS Action Customer Status
    public void customerStatus_addAllAction(String description) throws InterruptedException, IOException {
        createCustomerStatus.searchTrigger_appointmentStatus(description);
        createCustomerStatus.SMSAction_CustomerStatus();
        createCustomerStatus.searchTrigger_appointmentStatus(description);
        createCustomerStatus.emailAction_CustomerStatus();
        createCustomerStatus.searchTrigger_appointmentStatus(description);
        createCustomerStatus.voiceAction_CustomerStatus();
        createCustomerStatus.searchTrigger_appointmentStatus(description);
        createCustomerStatus.snailMailAction_CustomerStatus();
        createCustomerStatus.searchTrigger_appointmentStatus(description);
        createCustomerStatus.sendEmployeeEmail_CustomerStatus();
        createCustomerStatus.searchTrigger_appointmentStatus(description);
        createCustomerStatus.addAlert_CustomerStatus();
        createCustomerStatus.searchTrigger_appointmentStatus(description);
        createCustomerStatus.addTask_CustomerStatus();
        createCustomerStatus.searchTrigger_appointmentStatus(description);
        createCustomerStatus.sendEmployeeVoice_CustomerStatus();
        createCustomerStatus.searchTrigger_appointmentStatus(description);
        createCustomerStatus.sendEmployeeSMS_CustomerStatus();

    }

    @And("I add remove payment profile action {string}")
    public void customerStatus_removePaymentProfileAction(String description) throws InterruptedException {
        createCustomerStatus.searchTrigger_appointmentStatus(description);
        subscriptionDueForService.removePaymentProfile_subscriptionForService();
    }

    // Hit trigger Queue
    public void hitTriggerQueue() {
        triggerAdmin = new TriggerRules();
        triggerAdmin.hitTriggerQueue();
    }

    // assert Log on Notes
    @Then("I assert all the logs")
    public void assertlog() throws Exception {
        subscriptionDueForService.assertSMSLog();
        subscriptionDueForService.assertEMailLog();
        subscriptionDueForService.assertVoiceLog();
        subscriptionDueForService.assertSnailMailLog();
        subscriptionDueForService.assertEmployeeEMailLog();
        subscriptionDueForService.assertAlertLog();
        subscriptionDueForService.assertTaskLog();
        subscriptionDueForService.assertEmplopeeVoiceLog();
    }

    // assert Remove Payment Log
    @And("I assert Remove Payment log")
    public void assertRemovePaymentlog() throws Exception {
        subscriptionDueForService.assertRemovePaymentLog();
    }

    // Create customer with Frozen Status
    @When("I create frozen customer with first name, last name, email and address")
    public void createNewCustomerwithPhoneEmailBilling_Frozen() throws Exception {
        newCustomer = new CreateNewCustomer();
        customerAdmin = new CustomerViewDialog_Admin();
        overviewHeader = new CustomerViewDialog_Header();
        billing = new Billing();
        newCustomer.createCustomerWithEmail();
//        overviewHeader.navigateTo(overviewHeader.billingTabInDialog);
//        billing.navigateToCC();
        billing.addPaymentCC("4111111111111111", "5412750109056250");
//        billing.navigateToBankAccount();
        billing.addBankAccount();
        overviewHeader.navigateTo(overviewHeader.adminTabInDialog);
        customerAdmin.changeAccountStatus_Active();
        header = new Header();
        header.searchCustomer_History(getData("customerName", generalData));
        overviewHeader.navigateTo(overviewHeader.adminTabInDialog);
        customerAdmin.changeAccountStatus_Frozen(customerAdmin.reassignServicePropertiesToNewBillingAccount);
    }

    // Create customer with Active Status
    @When("I create active customer with first name, last name, email and address")
    public void createNewCustomerwithPhoneEmailBilling_Active() throws Exception {
        newCustomer = new CreateNewCustomer();
        customerAdmin = new CustomerViewDialog_Admin();
        overviewHeader = new CustomerViewDialog_Header();
        newCustomer.createCustomerWithEmail();
        overviewHeader.navigateTo(overviewHeader.billingTabInDialog);
        overviewHeader.navigateTo(overviewHeader.adminTabInDialog);
        customerAdmin.changeAccountStatus_Active();
    }

    // Create customer with Created Status
    public void createNewCustomerwithPhoneEmailBilling_Created() throws Exception {
        newCustomer = new CreateNewCustomer();
        newCustomer.createCustomerWithEmail();
    }

    // Create customer with Created Status
    @When("I create pending cancel customer with first name, last name, email and address")
    public void createNewCustomerwithPhoneEmailBilling_PendingCancel() throws Exception {
        newCustomer = new CreateNewCustomer();
        header = new Header();
        overviewHeader = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        newCustomer.createCustomerWithEmail();
        header.searchCustomer_History(getData("customerName", generalData));
        overviewHeader.navigateTo(overviewHeader.infoTabInDialog);
        customer.clickPendingCancelCheckBox();
    }

    private void result(String expected, String actual, String stepName, String testName) {
        if (AssertException.result(expected, actual, stepName).size() > 0) {
            list.add(AssertException.result(expected, actual, stepName));
        }
        Reporter.status(stepName, expected, actual, testName);
    }

    public void validateIfFailureExist() {
        AssertException.assertFailure(list);
    }
}

