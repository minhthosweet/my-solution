package automation.PestRoutes.Controller.Reporting.Office;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_InfoTab;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreateNewInvoicePopUp;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.ReportingPage.OfficePage.BillingByServiceTypeTab;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.AssertException;

import java.io.IOException;

import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.annotations.Test;

import static automation.PestRoutes.Utilities.AssertException.result;

public class BillingByServiceType extends AppData {
    CreateCustomerDialog createCustomerDIalog;
    InvoiceImplementation invImplementation;
    BillingByServiceTypeTab billingByServiceType = new BillingByServiceTypeTab();
    CustomerViewDialog_Header customerCardHeader;
    Header header;
    CreateNewCustomer createNewCustomer;
    RoutePageInvoicing invoice;
    CreateNewInvoicePopUp newInvoice;
    CustomerViewDialog_InfoTab customerViewDialog_infoTab;

    @Test
    @And("I navigate to Billing by Service Type")
    public void navigateToBillingByServiceType() {
        billingByServiceType.navigateToBillingByServiceTypePage();
    }

    public BillingByServiceType() throws Exception {
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
    public void validateLink_customerCard() throws Exception {
        billingByServiceType.clickDescription_reportDetails();
        billingByServiceType.customerDetails();
    }

    @And("I validate billing by service type report")
    public void validateBBSTReport() throws InterruptedException {
        invImplementation = new InvoiceImplementation();
        customerCardHeader = new CustomerViewDialog_Header();
        header = new Header();
        header.searchCustomerInOrder("1");
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
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

    @When("I create customer with balance with prefers paper and residential property type")
    public void createCustomerWithBalancePrefersPaper() throws Exception {
        String amount = "400";
        createNewCustomer = new CreateNewCustomer();
        customerCardHeader = new CustomerViewDialog_Header();
        invoice = new RoutePageInvoicing();
        newInvoice = new CreateNewInvoicePopUp();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        createNewCustomer.createCustomerWithPrefPaperAndResidentialProperty();
        customerCardHeader.navigateTo(customerCardHeader.infoTabInDialog);
        String customerName = customerViewDialog_infoTab.getLastName() + " " + customerViewDialog_infoTab.getFirstName();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        invoice = new RoutePageInvoicing();
        invoice.clickAddNewInvoice(invoice.addNewInvoice);
        newInvoice.set(newInvoice.dateField, Utilities.currentDate("MM/dd/yyyy"));
        newInvoice.set(newInvoice.amountInputField, amount);
        newInvoice.select(newInvoice.serviceTypeDropdown, "AA Petst1");
        newInvoice.click(newInvoice.createButton);
        createNewCustomer.closeCustomerCard();
    }

    @And("I search for customer with pref paper and residential property in BST")
    public void filterByPrefPaperAndProperty() throws Exception {
        billingByServiceType.navigateToBillingByServiceTypePage();
        billingByServiceType.mainGroupBy("Customer Name");
        billingByServiceType.clickAdvancedFilters();
        billingByServiceType.setPropType("Residential Only");
        billingByServiceType.setPrefersPaper("Yes");
        billingByServiceType.clickRefreshButton();
        billingByServiceType.searchNewCustomer();
    }

}

