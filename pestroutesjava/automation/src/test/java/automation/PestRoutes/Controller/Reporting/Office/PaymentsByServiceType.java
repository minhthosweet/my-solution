package automation.PestRoutes.Controller.Reporting.Office;

import automation.PestRoutes.PageObject.ReportingPage.OfficePage.BillingByServiceTypeTab;
import automation.PestRoutes.PageObject.ReportingPage.OfficePage.PaymentsByServiceTypeTab;
import automation.PestRoutes.Utilities.AssertException;
import io.cucumber.java.en.Then;
import org.testng.annotations.Test;

public class PaymentsByServiceType {

    PaymentsByServiceTypeTab paymentsByServiceTypeTab = new PaymentsByServiceTypeTab();
    BillingByServiceTypeTab billingByServiceTypeTab = new BillingByServiceTypeTab();

    //Author: Aditya
    @Test
    @Then("I validate if all fields are displaying and are enabled in Payments by service type")
    public void validateAllFieldsEnabled() {
        billingByServiceTypeTab.clickAdvancedFilters();
        String[] fields = {billingByServiceTypeTab.dateParams, billingByServiceTypeTab.groupBy, billingByServiceTypeTab.subGroupOne,
                billingByServiceTypeTab.subGroupTwo, billingByServiceTypeTab.asOfDate, paymentsByServiceTypeTab.Coupons, billingByServiceTypeTab.writeOffs,
                billingByServiceTypeTab.aPayType_bbst, billingByServiceTypeTab.aPayStatus_bbst, billingByServiceTypeTab.propType_bbst,
                billingByServiceTypeTab.balance_bbst, billingByServiceTypeTab.balanceAge_bbst, billingByServiceTypeTab.soldDateRange,
                billingByServiceTypeTab.search_bbst,
                billingByServiceTypeTab.filterTypes("invoice_bbst"),
                billingByServiceTypeTab.filterTypes("serviceType_bbst"),
                billingByServiceTypeTab.filterTypes("customerSource_bbst"),
                billingByServiceTypeTab.filterTypes("inclCollections"),
                billingByServiceTypeTab.filterTypes("subSource_bbst"),
                billingByServiceTypeTab.filterTypes("regions_bbst"),
                billingByServiceTypeTab.filterTypes("divisions_bbst"),
                billingByServiceTypeTab.filterTypes("offices_bbst"),
                billingByServiceTypeTab.filterTypes("includeFlags_bbst"),
                billingByServiceTypeTab.filterTypes("excludeFlags_bbst"),
                billingByServiceTypeTab.filterTypes("scheduledBy_bbst"),
                billingByServiceTypeTab.filterTypes("soldByTeam_bbst"),
                billingByServiceTypeTab.filterTypes("soldbySalesRep_bbst")};

        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);

        AssertException.validateFieldEnabled(fields);
    }
}
