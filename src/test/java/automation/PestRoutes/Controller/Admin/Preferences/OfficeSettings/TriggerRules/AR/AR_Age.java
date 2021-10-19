package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AR;

import automation.PestRoutes.Controller.Invoicing.InvoicingTab;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.When;
import org.testng.annotations.Test;

public class AR_Age extends AppData {

    CreateTrigger_AR createAR = new CreateTrigger_AR();
    InvoicingTab invoiceTab;
    AR_daysPastDue ar_daysPastDue;

    private String description_TriggerAge = "TriggerAge_AR";
    public String age = "1";

    @Test
    public void daysPastDue_AR() throws Exception {

        // Create trigger and all actions
        createTriggerandActionsAge_AR(description_TriggerAge, age);

        // Create customer with all communication
        createCustomerWithInvoice();

        // Run the script
        runTriggerEvent();

        // assert Notes
        assertLog();
    }

    public void createTriggerandActionsAge_AR(String description, String days) throws Exception {
        createAR.createTrigger_AR(description, "Days Past Due", days);
        createAR.createAllARActions(description);
    }

    @When("I create customer with Invoice")
    public void createCustomerWithInvoice() throws Exception {
        invoiceTab = new InvoicingTab();
        invoiceTab.addNewInvoice(GetDate.minusOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
    }

    @When("I execute the trigger event script")
    public void runTriggerEvent() {
        createAR.hitTriggerEvent();
    }

    public void assertLog() throws Exception {
        ar_daysPastDue = new AR_daysPastDue();
        ar_daysPastDue.assertLog();
    }
}