package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AR;

import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionDueForService.CreateTrigger_SubscriptionDueForService;
import automation.PestRoutes.Controller.Invoicing.InvoicingTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.Then;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AR_daysPastDue  extends AppData {

    SoftAssert softAssert = new SoftAssert();
    CreateTrigger_AR createAR = new CreateTrigger_AR();
    InvoicingTab invoiceTab;
    CreateTrigger_SubscriptionDueForService subscriptionDueForService;
    TriggerRules userOnTriggerRulesPage = new TriggerRules();
    ARTab userSelectsTriggerTypeAR = new ARTab();

    private String description_TriggerDaysPastDue = "TriggerDaysPastDue_AR";
    public String pastDue = "1";

    @Test
    public void daysPastDue_AR() throws Exception {

        // Create trigger and all actions
        createTriggerandActionsDaysPastDue_AR(description_TriggerDaysPastDue, pastDue);

        // Create customer with all communication
        createCustomerWithInvoice();

        // Run the script
        runTriggerEvent();

        // assert Notes
        assertLog();

    }

    public void createTriggerandActionsDaysPastDue_AR(String description, String days) throws Exception {
        createAR.createTrigger_AR( description, "Days Past Due",days);
        createAR.createAllARActions(description_TriggerDaysPastDue);
    }

    public void createCustomerWithInvoice() throws Exception {
        invoiceTab = new InvoicingTab();
        invoiceTab.addNewInvoice(GetDate.minusGenericDayToDate(Utilities.currentDate("MM/dd/yyyy"),31));
    }

    public void runTriggerEvent(){
        createAR.hitTriggerEvent();
    }

    @Then("I assert trigger event logs")
    public void assertLog() throws Exception {
        subscriptionDueForService = new CreateTrigger_SubscriptionDueForService();
        subscriptionDueForService.assertSMSLog();
        subscriptionDueForService.assertEMailLog();
        subscriptionDueForService.assertVoiceLog();
    }

    @Then("I Verify The {string} Service Type Is Not Deleted After Saving The {string} Trigger Type")
    public void testCreateInvoiceServiceTypeIsNotRemovedFromTriggerTypeAR(String expectedServiceType, String triggerType) {
        userOnTriggerRulesPage.editExistingTrigger(triggerType +  " Automation Trigger");
        String actualServiceType = userSelectsTriggerTypeAR.getCreateInvoiceServiceType();
        softAssert.assertEquals(actualServiceType, expectedServiceType,
                "\n Actual Service Type:   " + actualServiceType +
                        "\n Expected Service Type: " + expectedServiceType +
                        "\n The Actual & Expected Service Types Do Not Match \n");
        softAssert.assertAll();
    }
}