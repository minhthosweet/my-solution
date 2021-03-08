package automation.PestRoutes.Controller.Reporting.Office;

import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.ReportingPage.OfficePage.BillingByServiceTypeTab;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.AssertException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.annotations.Test;

public class BillingByServiceType extends AppData {
    CreateCustomerDialog createCustomerDIalog;
    InvoiceImplementation invImplementation;
    BillingByServiceTypeTab billingByServiceType = new BillingByServiceTypeTab();
    CustomerViewDialog_Header dialog;
    Header header;

    public BillingByServiceType() throws Exception {
    }

    @Test
    @And("I navigate to Billing by Service Type")
    public void navigateToBillingByServiceType() {
        billingByServiceType.navigateToBillingByServiceTypePage();
    }

    @And("I group by customer name")
    public void groupByCustomerName() {
        billingByServiceType.mainGroupBy("Customer Name");
    }

    @And("I search for the billing customer")
    public void searchCustomer() throws Exception {
        createCustomerDIalog = new CreateCustomerDialog();
        billingByServiceType.clickRefreshButton();
        billingByServiceType.searchNewCustomer();
    }

    @Then("I validate if all fields are displaying and are enabled in Billing by service type")
    public void validateAllFieldsEnabled() {
        billingByServiceType.clickAdvancedFilters();
        String[] fields = {billingByServiceType.dateParams, billingByServiceType.groupBy, billingByServiceType.subGroupOne,
                billingByServiceType.subGroupTwo, billingByServiceType.asOfDate, billingByServiceType.productionValue, billingByServiceType.writeOffs,
                billingByServiceType.aPayType_bbst, billingByServiceType.aPayStatus_bbst, billingByServiceType.propType_bbst, billingByServiceType.prefersPaper,
                billingByServiceType.balance_bbst, billingByServiceType.balanceAge_bbst, billingByServiceType.soldDateRange,
                billingByServiceType.search_bbst,
                billingByServiceType.filterTypes("invoice_bbst"),
                billingByServiceType.filterTypes("serviceType_bbst"),
                billingByServiceType.filterTypes("customerSource_bbst"),
                billingByServiceType.filterTypes("inclCollections"),
                billingByServiceType.filterTypes("subSource_bbst"),
                billingByServiceType.filterTypes("regions_bbst"),
                billingByServiceType.filterTypes("divisions_bbst"),
                billingByServiceType.filterTypes("offices_bbst"),
                billingByServiceType.filterTypes("includeFlags_bbst"),
                billingByServiceType.filterTypes("excludeFlags_bbst"),
                billingByServiceType.filterTypes("scheduledBy_bbst"),
                billingByServiceType.filterTypes("soldByTeam_bbst"),
                billingByServiceType.filterTypes("soldbySalesRep_bbst")};

        billingByServiceType.click(billingByServiceType.refresh_bbst);
        billingByServiceType.click(billingByServiceType.toggleChart);

        AssertException.validateFieldEnabled(fields);
    }

    @And("I add additional properties to customer")
    public void addAdditionalProperties() throws IOException, InterruptedException {
        billingByServiceType.editCustomerPerFilters();
    }

    @And("I add all the filters")
    public void addAllFilters() throws IOException {
        billingByServiceType.clickAdvancedFilters();
        billingByServiceType.setInvoiceType("Initial Invoice");
        billingByServiceType.setServiceType(getData("serviceDescription", generalData));
        billingByServiceType.setCustomerSource(getData("customerSource", generalData));
        billingByServiceType.setDivisions(getData("division", generalData));
        billingByServiceType.setIncludeFlags(getData("flag", generalData));
        billingByServiceType.setSoldDateRange();
    }

    @And("I validate if the report is linked to the customer card")
    public void validateLink_customerCard() {
        billingByServiceType.clickDescription_reportDetails();
        billingByServiceType.customerDetails();
    }

    @And("I validate billing by service type report")
    public void validateBBSTReport() throws InterruptedException {
        invImplementation = new InvoiceImplementation();
        dialog = new CustomerViewDialog_Header();
        header = new Header();
        header.searchCustomerInOrder("1");
        dialog.navigateTo(dialog.invoicesTabInDialog);
        invImplementation.clickInitialInvoice();
        String subTotalValue = invImplementation.getSubTotalValue();
        String taxValue = invImplementation.getTaxValue();

        result("$" + billingByServiceType.getBilledServiceValue_Report(), subTotalValue, "Sub Total Value Validation",
                "BBST Report Validation");
        result(billingByServiceType.getBilledTaxValue_Report(), taxValue, "Tax Value Validation",
                "BBST Report Validation");
        result(billingByServiceType.getBilledServiceValue_Customer(), subTotalValue, "Sub Total Value Validation",
                "BBST Report Validation");
        result(billingByServiceType.getBilledTaxValue_Customer(), taxValue, "Tax Value Validation",
                "BBST Report Validation");
    }

    @And("I validate the fields generated by billing by service type report")
    public void validateFields_BBSTReport() {
        String[] fields = {billingByServiceType.filterTypes("description_bbstReport"),
                billingByServiceType.filterTypes("services_bbstReport"),
                billingByServiceType.filterTypes("lineItemQuantity_bbstReport"),
                billingByServiceType.filterTypes("totalCollected_bbstReport"),
                billingByServiceType.filterTypes("taxCollected_bbstReport"),
                billingByServiceType.filterTypes("taxInvoiced_bbstReport"),
                billingByServiceType.filterTypes("paymentsCollected_bbstReport"),
                billingByServiceType.filterTypes("billedServices_bbstReport")};

        AssertException.validateFieldEnabled(fields);
    }

    @And("I validate the fields are displayed in individual line items")
    public void validateFields_BBSTLineItem() {
        String[] fields = {billingByServiceType.search_lineItem,
                billingByServiceType.pageNumber_lineItem,
                billingByServiceType.filterTypes("customerID_lineItem"),
                billingByServiceType.filterTypes("customerName_lineItem"),
                billingByServiceType.filterTypes("invoiceID_lineItem"),
                billingByServiceType.filterTypes("date_lineItem"),
                billingByServiceType.filterTypes("serviceDescription_lineItem"),
                billingByServiceType.filterTypes("billingFrequency_lineItem"),
                billingByServiceType.filterTypes("itemsQuantity_lineItem"),
                billingByServiceType.filterTypes("totalCollected_lineItem"),
                billingByServiceType.filterTypes("taxCollected_lineItem"),
                billingByServiceType.filterTypes("tax_lineItem"),
                billingByServiceType.filterTypes("appliedPaymentsBeforeTax_lineItem"),
                billingByServiceType.filterTypes("dateCollected_lineItem"),
                billingByServiceType.filterTypes("billedServices_lineItem"),
                billingByServiceType.filterTypes("first_page"),
                billingByServiceType.filterTypes("previous_page"),
                billingByServiceType.filterTypes("next_page"),
                billingByServiceType.filterTypes("last_page")};

        billingByServiceType.click(billingByServiceType.exportDetailsToCSV_button);
        AssertException.validateFieldEnabled(fields);
    }

    private void result(String expected, String actual, String stepName, String testName) {
        if (AssertException.result(expected, actual, stepName).size() > 0) {
            Utilities.list.add(AssertException.result(expected, actual, stepName));
        }
        Reporter.status(stepName, expected, actual, testName);
    }
}

