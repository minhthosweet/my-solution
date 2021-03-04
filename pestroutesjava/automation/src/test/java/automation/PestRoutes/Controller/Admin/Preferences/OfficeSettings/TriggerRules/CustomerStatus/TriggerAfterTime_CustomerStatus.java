package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus;

import java.io.IOException;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.SubscriptionStatusTab;
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
        customerStatus_CreateAllActions(description_TriggerAfterTime);

        // Trigger on Save for Created customer
        editTrigger_triggerAfterTime_CustomerStatus("Created", description_TriggerAfterTime);
        createNewCustomerWithPhoneEmailBilling_Created();
        hitTriggerCustomerStatus();
        assertFrozen_allActions();

        // Trigger on Save for Active customer
        editTrigger_triggerAfterTime_CustomerStatus("Active", description_TriggerAfterTime);
        createNewCustomerWithPhoneEmailBilling_Active();
        hitTriggerCustomerStatus();
        assertFrozen_allActions();

        // Trigger on Save for Pending Cancel customer
        editTrigger_triggerAfterTime_CustomerStatus("Pending Cancel", description_TriggerAfterTime);
        createNewCustomerWithPhoneEmailBilling_PendingCancel();
        hitTriggerCustomerStatus();
        assertFrozen_allActions();

        // Trigger on Save for Frozen customer
        editTrigger_triggerAfterTime_CustomerStatus("Frozen", description_TriggerAfterTime);
        customerStatus_RemovePaymentProfileAction(description_TriggerAfterTime);
        createNewCustomerWithPhoneEmailBilling_Frozen();
        hitTriggerCustomerStatus();
        assertFrozen_allActions();
        assertFrozen_RemovePayment();

    }

    public void createTriggerAfterTime_CustomerStatus(String description) throws Exception {
        triggerOnSave.createTriggerOnSave_CustomerStatus(description);
    }

    @Then("I edit the trigger status on trigger after time {string} of type {string}")
    public void editTrigger_triggerAfterTime_CustomerStatus(String statusChange, String description) {
        subscriptionStatus = new SubscriptionStatusTab();
        triggerAdmin = new TriggerRules();
        createCustomerStatus = new CreateTrigger_CustomerStatus();
        createCustomerStatus.searchTrigger_appointmentStatus(description);
        triggerAdmin.clickEditTrigger(description);
        triggerAdmin.selectDropdown(subscriptionStatus.statusChangedTo, statusChange);
        triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger, subscriptionStatus.whenToTrigger_triggerAfterTime);
        triggerAdmin.setDaysAfterChange(days_After_Change);
        triggerAdmin.clickSaveButton();
    }

    public void customerStatus_CreateAllActions(String description) throws InterruptedException, IOException {
        triggerOnSave.customerStatus_addAllAction(description);
    }

    public void customerStatus_RemovePaymentProfileAction(String description) {
        triggerOnSave.customerStatus_removePaymentProfileAction(description);
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

    // Run script
    @When("I execute the trigger customer status script")
    public void hitTriggerCustomerStatus() {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerCustomerStatus.php");
    }

    public void assertFrozen_allActions() throws IOException, Exception {
        triggerOnSave.assertlog();
    }

    public void assertFrozen_RemovePayment() throws IOException, Exception {
        triggerOnSave.assertRemovePaymentlog();
    }
}
