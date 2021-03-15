package automation.PestRoutes.Controller.Reporting.Office;

import automation.PestRoutes.Controller.Billings.AccountReceivable;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Admin;
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

import automation.PestRoutes.Utilities.GetDate;
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
    CustomerViewDialog_Admin customerViewDialog_admin;
    AccountReceivable accountReceivable;

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
        billingByServiceType.click(billingByServiceType.refresh_bbst);
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
        billingByServiceType.setType("invoice_bbst", "Initial Invoice");
        billingByServiceType.setType("serviceType_bbst", getData("serviceDescription", generalData));
        billingByServiceType.setType("customerSource_bbst", getData("customerSource", generalData));
        billingByServiceType.setType("divisions_bbst", getData("division", generalData));
        billingByServiceType.setType("includeFlags_bbst", getData("flag", generalData));
        billingByServiceType.setDateRange(billingByServiceType.dateParams, "Today");
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
        accountReceivable = new AccountReceivable();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        createNewCustomer.createCustomerWithPrefPaperAndResidentialProperty();
        customerCardHeader.navigateTo(customerCardHeader.infoTabInDialog);
        accountReceivable.createStandAloneServiceInvoice(amount, Utilities.currentDate("MM/dd/yyyy"), "AA Petst1");
        createNewCustomer.closeCustomerCard();
    }

    @And("I search for customer with pref paper and residential property in BST")
    public void filterByPrefPaperAndResidentialProperty() throws Exception {
        billingByServiceType.navigateToBillingByServiceTypePage();
        billingByServiceType.mainGroupBy("Customer Name");
        billingByServiceType.clickAdvancedFilters();
        billingByServiceType.set(billingByServiceType.propType_bbst, "Residential Only");
        billingByServiceType.set(billingByServiceType.prefersPaper, "Yes");
        billingByServiceType.click(billingByServiceType.refresh_bbst);
        billingByServiceType.searchNewCustomer();
    }

    @And("I search for customer without pref paper and commercial property in BST")
    public void filterByNotPrefPaperAndCommercialProperty() throws Exception {
        billingByServiceType.navigateToBillingByServiceTypePage();
        billingByServiceType.mainGroupBy("Customer Name");
        billingByServiceType.clickAdvancedFilters();
        billingByServiceType.set(billingByServiceType.propType_bbst, "Commercial Only");
        billingByServiceType.set(billingByServiceType.prefersPaper, "No");
        billingByServiceType.click(billingByServiceType.refresh_bbst);
        billingByServiceType.searchNewCustomer();
    }

    @When("I edit customer to commercial property and not require paper")
    public void editCustomer_notRequirePaper_CommercialProperty() throws IOException, InterruptedException {
        billingByServiceType.editCustomer_NoPaper_Commercial();
    }

    @Then("I validate customer with balance age in BST")
    public void validateBalanceAge() throws Exception {
        createNewCustomer = new CreateNewCustomer();
        customerCardHeader = new CustomerViewDialog_Header();
        customerViewDialog_admin = new CustomerViewDialog_Admin();
        accountReceivable = new AccountReceivable();
        String[] balanceAge = {"7+ Days Old", "30+ Days Old (Past Due)", "90+ Days Old (Way, Way Past Due)"};
        int[] invoiceDaysPastDue = {7, 30, 90};
        for (int i = 0; i < balanceAge.length; i++) {
            createNewCustomer.createCustomerWithAddress();
            int currentMonth = GetDate.getMonth(Utilities.currentDate("MM/dd/yyyy"));
            int currentYear = GetDate.getYear(Utilities.currentDate("MM/dd/yyyy"));
            String dateOfInvoice = GetDate.minusGenericDayToDate(Utilities.currentDate("MM/dd/yyyy"), invoiceDaysPastDue[i]);
            int monthOfInv = GetDate.getMonth(dateOfInvoice);
            int yearOfInv = GetDate.getYear(dateOfInvoice);
            accountReceivable.createStandAloneServiceInvoice("400", dateOfInvoice, "AA Petst1");
            customerCardHeader.navigateTo(customerCardHeader.adminTabInDialog);
            customerViewDialog_admin.changeAccountStatus_Active();
            createNewCustomer.closeCustomerCard();
            billingByServiceType.navigateToBillingByServiceTypePage();
            billingByServiceType.mainGroupBy("Customer Name");
            billingByServiceType.clickAdvancedFilters();
            billingByServiceType.setBalance_bbst("400");
            billingByServiceType.set(billingByServiceType.balanceAge_bbst, balanceAge[i]);
            int monthPastDue = currentMonth - monthOfInv;
            int yearsPastDue = currentYear - yearOfInv;
            if (monthPastDue == 0 && yearsPastDue == 0) {
                billingByServiceType.setDateRange(billingByServiceType.dateParams, "Last Week");
            } else if (monthPastDue == 1 && yearsPastDue == 0) {
                billingByServiceType.setDateRange(billingByServiceType.dateParams, "Last Month");
            } else if (yearsPastDue > 0) {
                billingByServiceType.setDateRange(billingByServiceType.dateParams, "Last Year");
            } else if (monthPastDue > 1 && yearsPastDue == 0) {
                billingByServiceType.setDateRange(billingByServiceType.dateParams, "This Year");
            }
            billingByServiceType.click(billingByServiceType.refresh_bbst);
            billingByServiceType.searchNewCustomer();
            validateFields_BBSTReport();
            validateLink_customerCard();
            validateBBSTReport();
            validateFields_BBSTLineItem();
        }
    }

    @And("I set filter for sold date, scheduler, sale teams and sales reps")
    public void validate_soldDate_Scheduler_SaleTeams_SalesReps() {
        billingByServiceType.navigateToBillingByServiceTypePage();
        billingByServiceType.mainGroupBy("Customer Name");
        billingByServiceType.clickAdvancedFilters();
        billingByServiceType.setDateRange(billingByServiceType.soldDateRange, "Today");
        billingByServiceType.setType("scheduledBy_bbst", "All Office Staff");
        billingByServiceType.setType("soldByTeam_bbst", "All Office Staff");
        billingByServiceType.setSalesRep("soldbySalesRep_bbst", "Automation User - Office");
        billingByServiceType.click(billingByServiceType.refresh_bbst);
    }
}

