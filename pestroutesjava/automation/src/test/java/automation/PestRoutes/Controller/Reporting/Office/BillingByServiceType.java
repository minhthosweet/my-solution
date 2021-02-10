package automation.PestRoutes.Controller.Reporting.Office;

import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.ReportingPage.OfficePage.BillingByServiceTypeTab;
import automation.PestRoutes.Utilities.BaseClass;
import io.cucumber.java.en.And;
import org.testng.annotations.Test;

public class BillingByServiceType extends BaseClass {
    CreateCustomerDialog createCustomerDIalog;
    BillingByServiceTypeTab billingByServiceType = new BillingByServiceTypeTab();

    @Test
    public void billingByServiceType() {
        navigateToBillingByServiceType();
    }

    @And("I navigate to Billing by Service Type")
    public void navigateToBillingByServiceType() {
        billingByServiceType.navigateToBillingByServiceTypePage();

    }

    @And("I group by customer name")
    public void groupByCustomerName() {
        billingByServiceType.mainGroupBy();
    }

    @And("I search for the billing customer")
    public void searchCustomer() throws Exception {
        createCustomerDIalog = new CreateCustomerDialog();
        billingByServiceType.searchNewCustomer();
    }
}
