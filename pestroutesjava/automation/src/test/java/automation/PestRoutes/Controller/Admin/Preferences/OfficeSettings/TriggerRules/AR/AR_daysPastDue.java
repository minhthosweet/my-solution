package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AR;

import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionDueForService.CreateTrigger_SubscriptionDueForService;
import automation.PestRoutes.Controller.Invoicing.InvoicingTab;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.Then;
import org.testng.annotations.Test;

public class AR_daysPastDue  extends BaseClass {

    CreateTrigger_AR createAR = new CreateTrigger_AR();
    InvoicingTab invoiceTab;
    CreateTrigger_SubscriptionDueForService subscriptionDueForService;

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

}
