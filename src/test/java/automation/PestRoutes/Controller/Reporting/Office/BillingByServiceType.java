package automation.PestRoutes.Controller.Reporting.Office;

import automation.PestRoutes.Controller.Billings.AccountReceivable;
import automation.PestRoutes.Controller.Billings.Billing;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Invoicing.InvoicingTab;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Admin;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_OverviewTab;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreditMemoTab;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.ReportingPage.OfficePage.BillingByServiceTypeTab;

import java.io.IOException;
import java.util.Locale;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static automation.PestRoutes.Utilities.AssertException.result;

public class BillingByServiceType extends AppData {
    CreateCustomerDialog createCustomerDialog;
    InvoiceImplementation invImplementation;
    BillingByServiceTypeTab billingByServiceTypeTab = new BillingByServiceTypeTab();
    CustomerViewDialog_Header customerCardHeader;
    Header header;
    CreateNewCustomer createNewCustomer;
    CustomerViewDialog_OverviewTab customerViewDialog_overviewTab;
    CustomerViewDialog_Admin customerViewDialog_admin;
    AccountReceivable accountReceivable;
    CreditMemoTab creditMemoTab;
    OfficeObjects officeObjects = new OfficeObjects();
    Billing billing;
    InvoicingTab invoicingTab;

    public String standAloneInvoiceAmount = String.valueOf((int)Math.floor(Math.random()*900+100));
    private String customerID_BST = "";
    private String invoiceID = "";
    private String dateOfInvoice = "";
    private String customerName_BST = "";

    private String subTotalValue = "";
    private String taxValue = "";
    private String totalCollected = "";

    //Author: Aditya
    public BillingByServiceType() {
    }

    //Author: Aditya
    @Test
    @And("I group by {string}")
    public void groupByCustomerName(String needFilter) {
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, needFilter);
    }

    //Author: Aditya
    @And("I search for the billing customer")
    public void searchCustomer() {
        createCustomerDialog = new CreateCustomerDialog();
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
        billingByServiceTypeTab.searchNewCustomer(billingByServiceTypeTab.search_bbst, customerName_BST);
    }

    //Author: Aditya
    @Then("I validate if all fields are displaying and are enabled in Billing by service type")
    public void validateAllFieldsEnabled() {
        billingByServiceTypeTab.clickAdvancedFilters();
        String[] fields = {billingByServiceTypeTab.dateParams, billingByServiceTypeTab.groupBy, billingByServiceTypeTab.subGroupOne,
                billingByServiceTypeTab.subGroupTwo, billingByServiceTypeTab.asOfDate, billingByServiceTypeTab.productionValue, billingByServiceTypeTab.writeOffs,
                billingByServiceTypeTab.aPayType_bbst, billingByServiceTypeTab.aPayStatus_bbst, billingByServiceTypeTab.propType_bbst, billingByServiceTypeTab.prefersPaper,
                billingByServiceTypeTab.balance_bbst, billingByServiceTypeTab.balanceAge_bbst, billingByServiceTypeTab.soldDateRange,
                billingByServiceTypeTab.search_bbst,
                billingByServiceTypeTab.filterTypes_BST("invoice_bbst"),
                billingByServiceTypeTab.filterTypes_BST("serviceType_bbst"),
                billingByServiceTypeTab.filterTypes_BST("customerSource_bbst"),
                billingByServiceTypeTab.filterTypes_BST("inclCollections"),
                billingByServiceTypeTab.filterTypes_BST("subSource_bbst"),
                billingByServiceTypeTab.filterTypes_BST("regions_bbst"),
                billingByServiceTypeTab.filterTypes_BST("divisions_bbst"),
                billingByServiceTypeTab.filterTypes_BST("offices_bbst"),
                billingByServiceTypeTab.filterTypes_BST("includeFlags_bbst"),
                billingByServiceTypeTab.filterTypes_BST("excludeFlags_bbst"),
                billingByServiceTypeTab.filterTypes_BST("scheduledBy_bbst"),
                billingByServiceTypeTab.filterTypes_BST("soldByTeam_bbst"),
                billingByServiceTypeTab.filterTypes_BST("soldbySalesRep_bbst")};

        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
        billingByServiceTypeTab.click(billingByServiceTypeTab.toggleChart);

        AssertException.validateFieldEnabled(fields);
    }

    //Author: Aditya
    @And("I add properties invoice type, service type, customer source, divisions, include flags and date range to customer")
    public void addAdditionalProperties() throws IOException, InterruptedException {
        billingByServiceTypeTab.editCustomerPerFilters();
    }

    //Author : Aditya
    @And("I get customer name and customer ID details for Billing by service type report")
    public void updateCustomerIDAndCustomerNameDetails() throws InterruptedException {
        createNewCustomer = new CreateNewCustomer();
        customerViewDialog_overviewTab = new CustomerViewDialog_OverviewTab();
        customerName_BST = createNewCustomer.getCustomerFullName();
        customerID_BST = customerViewDialog_overviewTab.getCustomerIDFromHeader();
    }

    //Author: Aditya
    @And("I add filters invoice type, service type, customer source, divisions, include flags and date range")
    public void addAllFilters() throws IOException {
        billingByServiceTypeTab.clickAdvancedFilters();
        billingByServiceTypeTab.setType("invoice_bbst", "Initial Invoice");
        billingByServiceTypeTab.setType("serviceType_bbst", getData("serviceDescription", generalData));
        billingByServiceTypeTab.setType("customerSource_bbst", getData("customerSource", generalData));
        billingByServiceTypeTab.setType("divisions_bbst", getData("division", generalData));
        billingByServiceTypeTab.setType("includeFlags_bbst", getData("flag", generalData));
        billingByServiceTypeTab.setDateRange(billingByServiceTypeTab.dateParams, "Today");
    }

    //Author: Aditya
    @And("I validate if the billing by service type report is linked to the customer card")
    public void validateLink_customerCard_BST() throws Exception {
        if (!Utilities.isPresent("//tr[@detailvalues]//td[text()='" + customerName_BST + "']")){
            billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
        }
        billingByServiceTypeTab.clickDescription_reportDetails(customerName_BST);
        billingByServiceTypeTab.customerDetails(customerName_BST);
    }

    //Author: Aditya
    @And("I validate data generated from billing by service type report")
    public void validateDataInBillingReport() throws InterruptedException {
        invImplementation = new InvoiceImplementation();
        customerCardHeader = new CustomerViewDialog_Header();
        creditMemoTab = new CreditMemoTab();
        header = new Header();
        customerViewDialog_overviewTab = new CustomerViewDialog_OverviewTab();
        header.searchCustomerWithName(customerName_BST);
        customerID_BST = customerViewDialog_overviewTab.getCustomerIDFromHeader();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        if (CucumberBaseClass.scenarioName().equals("Credit memo validation is BST")) {
            billingByServiceTypeTab.click(creditMemoTab.getTicketID());
            creditMemoTab.clickAppliedCharge_invoiceApplications();
        } else {
            invImplementation.clickInitialInvoice();
        }
        subTotalValue = invImplementation.getSubTotalValue();
        taxValue = invImplementation.getTaxValue();
        System.out.println(taxValue);
        totalCollected = billingByServiceTypeTab.get(invImplementation.paymentsInPayments);

        if (CucumberBaseClass.scenarioName().equals("Credit memo validation is BST")) {
            result("$-" + totalCollected.substring(1), billingByServiceTypeTab.get(billingByServiceTypeTab.totalCollected_Report), "Total Collected in the report", "BBST Report Validation");
            result(billingByServiceTypeTab.get(billingByServiceTypeTab.billedServices_Report), "-" + (subTotalValue.substring(1)), "Sub Total Value Validation in report",
                    "BBST Report Validation");
            result("$-" + taxValue.substring(1), billingByServiceTypeTab.get(billingByServiceTypeTab.tax_Report), "Tax Value Validation in report",
                    "BBST Report Validation");
//            result(billingByServiceTypeTab.get(billingByServiceTypeTab.tax_Report), "$0.00", "Tax Value Validation in report",
//                    "BBST Report Validation");
        } else if (CucumberBaseClass.scenarioName().equals("Multi Group By filter validation in BST")) {
            result(totalCollected, billingByServiceTypeTab.getBilledServices_MultiGroupReport(customerID_BST), "Total Collected in the report", "BBST Report Validation");
            result(billingByServiceTypeTab.getTaxRate_MultiGroupReport(customerID_BST), taxValue, "Tax Value Validation in report",
                    "BBST Report Validation");
        } else {
            result(totalCollected, billingByServiceTypeTab.get(billingByServiceTypeTab.totalCollected_Report), "Total Collected in the report", "BBST Report Validation");
            result("$" + billingByServiceTypeTab.get(billingByServiceTypeTab.billedServices_Report), subTotalValue, "Sub Total Value Validation in report",
                    "BBST Report Validation");
            result(billingByServiceTypeTab.get(billingByServiceTypeTab.tax_Report), taxValue, "Tax Value Validation in report",
                    "BBST Report Validation");
        }
    }

    //Author: Aditya
    @And("I validate line item data in Billing by service type report")
    public void validateLineItemValues_BillingReport() throws IOException {
        if (CucumberBaseClass.scenarioName().equals("Credit memo validation is BST")) {
            result(Utilities.currentDate("MM-dd-yyyy"), billingByServiceTypeTab.get(billingByServiceTypeTab.paymentDate_lineItem), "Payment Date Validation in Detail Report", "BBST Report Validation");
            result(Utilities.currentDate("MM-dd-YYYY"), billingByServiceTypeTab.get(billingByServiceTypeTab.invoiceDate_lineItem), "Invoice Date Validation", "BBST Report Validation");
            result("$-" + totalCollected.substring(1), billingByServiceTypeTab.get(billingByServiceTypeTab.totalCollected_Customer), "Total Collected in the detail report", "BBST Report Validation");
            result(billingByServiceTypeTab.getBilledServiceValue_Customer(), "$-" + (subTotalValue.substring(1)), "Sub Total Value Validation in detailed report",
                    "BBST Report Validation");
            result("$-" + taxValue.substring(1), billingByServiceTypeTab.getBilledTaxValue_Customer(), "Tax Value Validation in report",
                    "BBST Report Validation");
//            result(billingByServiceTypeTab.getBilledTaxValue_Customer(), "$0.00", "Tax Value Validation in detailed report",
//                    "BBST Report Validation");
        } else if (CucumberBaseClass.scenarioName().equals("Balance Age validation BST with StandAlone Invoices")) {
            String expectedDateOfInvoice = dateOfInvoice.replaceAll("/", "-");
            String actualDateOfInvoice = billingByServiceTypeTab.get(billingByServiceTypeTab.invoiceDate_lineItem);
            if (actualDateOfInvoice.charAt(0) == '0') {
                actualDateOfInvoice = actualDateOfInvoice.substring(1);
            }
            result(expectedDateOfInvoice, actualDateOfInvoice, "Invoice Date Validation", "BBST Report Validation");
        } else {
            result(totalCollected, billingByServiceTypeTab.get(billingByServiceTypeTab.totalCollected_Customer), "Total Collected in the detail report", "BBST Report Validation");
            result(billingByServiceTypeTab.getBilledServiceValue_Customer(), subTotalValue, "Sub Total Value Validation in detailed report",
                    "BBST Report Validation");
            result(billingByServiceTypeTab.getBilledTaxValue_Customer(), taxValue, "Tax Value Validation in detailed report",
                    "BBST Report Validation");
            result(Utilities.currentDate("MM-dd-YYYY"), billingByServiceTypeTab.get(billingByServiceTypeTab.invoiceDate_lineItem), "Invoice Date Validation", "BBST Report Validation");
        }
//        result(billingByServiceTypeTab.getAttributeValue
//                (invImplementation.activeInvoiceOnTheLeft, "ticketid"), billingByServiceTypeTab.get
//                (billingByServiceTypeTab.invoiceID_lineItem), "Invoice ID validation in detail report", "BBST Report Validation");
        result(getData("serviceDescription", generalData), billingByServiceTypeTab.get(billingByServiceTypeTab.serviceType_lineItem), "Service Type in detail report validation", "BBST Report Validation");
        result(customerID_BST, billingByServiceTypeTab.get(billingByServiceTypeTab.customerID_lineItem), "Customer ID Validation", "BBST Report Validation");
        result(customerName_BST, billingByServiceTypeTab.get(billingByServiceTypeTab.customerName_lineItem), "Customer Name Validation", "BBST Report Validation");
    }

    //Author: Aditya
    @And("I validate the fields generated in multi group by billing by service type report")
    public void validateFields_BillingServiceReport_MultiGroup() {
        String[] fields;

        fields = new String[]{billingByServiceTypeTab.filterTypes_BST("description_bbstReport_MultiGroup"),
                billingByServiceTypeTab.filterTypes_BST("services_bbstReport_MultiGroup"),
                billingByServiceTypeTab.filterTypes_BST("lineItemQuantity_bbstReport_MultiGroup"),
                billingByServiceTypeTab.filterTypes_BST("totalCollected_bbstReport_MultiGroup"),
                billingByServiceTypeTab.filterTypes_BST("taxCollected_bbstReport_MultiGroup"),
                billingByServiceTypeTab.filterTypes_BST("taxInvoiced_bbstReport_MultiGroup"),
                billingByServiceTypeTab.filterTypes_BST("paymentsCollected_bbstReport_MultiGroup"),
                billingByServiceTypeTab.filterTypes_BST("billedServices_bbstReport_MultiGroup")};
        AssertException.validateFieldEnabled(fields);
    }

    //Author: Aditya
    @And("I validate the fields generated by billing by service type report")
    public void validateFields_BillingServiceReport() {
        String[] fields;

        fields = new String[]{billingByServiceTypeTab.filterTypes_BST("description_bbstReport"),
                billingByServiceTypeTab.filterTypes_BST("services_bbstReport"),
                billingByServiceTypeTab.filterTypes_BST("lineItemQuantity_bbstReport"),
                billingByServiceTypeTab.filterTypes_BST("totalCollected_bbstReport"),
                billingByServiceTypeTab.filterTypes_BST("taxCollected_bbstReport"),
                billingByServiceTypeTab.filterTypes_BST("taxInvoiced_bbstReport"),
                billingByServiceTypeTab.filterTypes_BST("paymentsCollected_bbstReport"),
                billingByServiceTypeTab.filterTypes_BST("billedServices_bbstReport")};

        AssertException.validateFieldEnabled(fields);
    }

    //Author: Aditya
    @And("I validate the fields are displayed in individual line items in Billing by Service Type")
    public void validateFields_BSTLineItem() throws InterruptedException {
        createNewCustomer = new CreateNewCustomer();
        header = new Header();

        try {
            String[] fields = {billingByServiceTypeTab.search_lineItem,
                    billingByServiceTypeTab.pageNumber_lineItem,
                    billingByServiceTypeTab.filterTypes_BST("customerID_lineItem"),
                    billingByServiceTypeTab.filterTypes_BST("customerName_lineItem"),
                    billingByServiceTypeTab.filterTypes_BST("invoiceID_lineItem"),
                    billingByServiceTypeTab.filterTypes_BST("date_lineItem"),
                    billingByServiceTypeTab.filterTypes_BST("serviceDescription_lineItem"),
                    billingByServiceTypeTab.filterTypes_BST("billingFrequency_lineItem"),
                    billingByServiceTypeTab.filterTypes_BST("itemsQuantity_lineItem"),
                    billingByServiceTypeTab.filterTypes_BST("totalCollected_lineItem"),
                    billingByServiceTypeTab.filterTypes_BST("taxCollected_lineItem"),
                    billingByServiceTypeTab.filterTypes_BST("tax_lineItem"),
                    billingByServiceTypeTab.filterTypes_BST("appliedPaymentsBeforeTax_lineItem"),
                    billingByServiceTypeTab.filterTypes_BST("dateCollected_lineItem"),
                    billingByServiceTypeTab.filterTypes_BST("billedServices_lineItem"),
                    billingByServiceTypeTab.filterTypes_BST("first_page"),
                    billingByServiceTypeTab.filterTypes_BST("previous_page"),
                    billingByServiceTypeTab.filterTypes_BST("next_page"),
                    billingByServiceTypeTab.filterTypes_BST("last_page"),
                    billingByServiceTypeTab.exportDetailsToCSV_button};
            AssertException.validateFieldEnabled(fields);
        } catch (StaleElementReferenceException e) {
            System.out.println("Fields not visible");
        } finally {
//            if (!CucumberBaseClass.scenarioName().equals("Multi Group By filter validation in BBST")) {
//                header.clickAccessHistory();
//            }
            createNewCustomer.removeCustomer();
        }
    }

    //Author: Aditya
    @When("I create customer with balance with prefers paper and residential property type")
    public void createCustomerWithBalancePrefersPaper() throws Exception {
        createNewCustomer = new CreateNewCustomer();
        header = new Header();
        customerViewDialog_overviewTab = new CustomerViewDialog_OverviewTab();
        invImplementation = new InvoiceImplementation();
        createNewCustomer.createCustomerWithPrefPaperAndResidentialProperty();
        customerName_BST = createNewCustomer.getCustomerFullName();
        createStandAloneInvoice();
        invoiceID = Utilities.getAttributeValue(invImplementation.invoiceAccountSummaryClick, "ticketid");
        customerID_BST = customerViewDialog_overviewTab.getCustomerIDFromHeader();
        createNewCustomer.closeCustomerCard();
        header.searchCustomerWithName(customerName_BST);
    }

    @And("I create a standalone invoice for current date")
    public void createStandAloneInvoice() throws IOException, InterruptedException {
        accountReceivable = new AccountReceivable();
//        accountReceivable.createStandAloneServiceInvoice
//                (standAloneInvoiceAmount, Utilities.currentDate("MM/dd/yyyy"),
//                        getData("serviceDescription", generalData));
        accountReceivable.createStandAloneServiceInvoice
                ("400", Utilities.currentDate("MM/dd/yyyy"),
                        getData("serviceDescription", generalData));
    }

    //Author: Aditya
    @And("I search for customer with pref paper and residential property in {string}")
    public void filterByPrefPaperAndResidentialProperty(String reportType) throws Exception {
        officeObjects.navigateToReportType(reportType);
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, "Customer Name");
        billingByServiceTypeTab.clickAdvancedFilters();
        if (!CucumberBaseClass.scenarioName().equals("Prefer Paper and Property Type validation PST")) {
            billingByServiceTypeTab.set(billingByServiceTypeTab.prefersPaper, "Yes");
        }
        billingByServiceTypeTab.set(billingByServiceTypeTab.propType_bbst, "Residential Only");
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
        billingByServiceTypeTab.searchNewCustomer(billingByServiceTypeTab.search_bbst, billingByServiceTypeTab.getCustomerName_CustomerCard_InfoTab());
    }

    //Author: Aditya
    @And("I search for customer without pref paper and commercial property in {string}")
    public void filterByNotPrefPaperAndCommercialProperty(String reportType) throws Exception {
        officeObjects.navigateToReportType(reportType);
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, "Customer Name");
        billingByServiceTypeTab.clickAdvancedFilters();
        billingByServiceTypeTab.set(billingByServiceTypeTab.propType_bbst, "Commercial Only");
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
        if (!CucumberBaseClass.scenarioName().equals("Prefer Paper and Property Type validation PST")) {
            billingByServiceTypeTab.set(billingByServiceTypeTab.prefersPaper, "No");
        }
        billingByServiceTypeTab.searchNewCustomer(billingByServiceTypeTab.search_bbst, billingByServiceTypeTab.getCustomerName_CustomerCard_InfoTab());
    }

    //Author: Aditya
    @When("I edit customer to commercial property and not require paper")
    public void editCustomer_notRequirePaper_CommercialProperty() throws IOException, InterruptedException {
        billingByServiceTypeTab.editCustomer_NoPaper_Commercial();
    }

    //Author: Aditya
    @Then("I validate customer with balance age in Billing by Service Type")
    public void validateBalanceAge() throws Exception {
        createNewCustomer = new CreateNewCustomer();
        customerCardHeader = new CustomerViewDialog_Header();
        customerViewDialog_admin = new CustomerViewDialog_Admin();
        accountReceivable = new AccountReceivable();
        String[] balanceAge = {"7+ Days Old", "30+ Days Old (Past Due)", "90+ Days Old (Way, Way Past Due)"};
        int[] invoiceDaysPastDue = {7, 30, 90};
        for (int i = 0; i < balanceAge.length; i++) {
            String fname = Utilities.generateRandomString(7).toLowerCase(Locale.ROOT);
            String lname = Utilities.generateRandomString(6).toLowerCase(Locale.ROOT);
            customerName_BST = fname + " " + lname;
            Thread.sleep(100);
            createNewCustomer.createACustomer(fname, lname);
            int currentMonth = GetDate.getMonth(Utilities.currentDate("MM/dd/yyyy"));
            int currentYear = GetDate.getYear(Utilities.currentDate("MM/dd/yyyy"));
            dateOfInvoice = GetDate.minusGenericDayToDate(Utilities.currentDate("MM/dd/yyyy"), invoiceDaysPastDue[i]);
            int monthOfInv = GetDate.getMonth(dateOfInvoice);
            int yearOfInv = GetDate.getYear(dateOfInvoice);
            String amount = "400";
            accountReceivable.createStandAloneServiceInvoice(amount, dateOfInvoice, getData("serviceDescription", generalData));
            customerCardHeader.navigateTo(customerCardHeader.adminTabInDialog);
            customerViewDialog_admin.changeAccountStatus_Active();
            if (CucumberBaseClass.scenarioName().equals("Balance Age validation PST with StandAlone Invoices")) {
                billing = new Billing();
                invoicingTab = new InvoicingTab();
                billing.addPaymentCC("4111111111111111", "5412750109056250");
                invoicingTab.makeCardOnFile_PartialCCPayment();
            }
            createNewCustomer.closeCustomerCard();
            officeObjects.navigateToReportType("Billing by Service Type");
            billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, "Customer Name");
            billingByServiceTypeTab.clickAdvancedFilters();
            billingByServiceTypeTab.setType("invoice_bbst", "Stand-Alone Invoices");
            billingByServiceTypeTab.setBalance_bbst(amount);
            billingByServiceTypeTab.set(billingByServiceTypeTab.balanceAge_bbst, balanceAge[i]);
            int monthPastDue = currentMonth - monthOfInv;
            int yearsPastDue = currentYear - yearOfInv;
            if (!CucumberBaseClass.scenarioName().equals("Balance Age validation PST with StandAlone Invoices")) {
                if (monthPastDue == 0 && yearsPastDue == 0) {
                    billingByServiceTypeTab.setDateRange(billingByServiceTypeTab.dateParams, "Last Week");
                } else if (monthPastDue == 1 && yearsPastDue == 0) {
                    billingByServiceTypeTab.setDateRange(billingByServiceTypeTab.dateParams, "Last Month");
                } else if (yearsPastDue > 0) {
                    billingByServiceTypeTab.setDateRange(billingByServiceTypeTab.dateParams, "Last Year");
                } else if (monthPastDue > 1 && yearsPastDue == 0) {
                    billingByServiceTypeTab.setDateRange(billingByServiceTypeTab.dateParams, "This Year");
                }
            }
            billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
            billingByServiceTypeTab.searchNewCustomer(billingByServiceTypeTab.search_bbst, billingByServiceTypeTab.getCustomerName_CustomerCard_InfoTab());
            validateFields_BillingServiceReport();
            validateLink_customerCard_BST();
            validateDataInBillingReport();
            validateLineItemValues_BillingReport();
            validateFields_BSTLineItem();

        }
    }

    //Author: Aditya
    @And("I set filter for sold date, scheduler, sale teams, sales reps and service invoice in {string}")
    public void set_soldDate_Scheduler_SaleTeams_SalesReps(String needReportType) {
        officeObjects.navigateToReportType(needReportType);
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, "Customer Name");
        billingByServiceTypeTab.clickAdvancedFilters();
        billingByServiceTypeTab.setDateRange(billingByServiceTypeTab.soldDateRange, "Today");
        billingByServiceTypeTab.setType("scheduledBy_bbst", "All Office Staff");
        billingByServiceTypeTab.setType("soldByTeam_bbst", "All Office Staff");
        billingByServiceTypeTab.setType("invoice_bbst", "Service Invoices");
        billingByServiceTypeTab.setSalesRep("soldbySalesRep_bbst", "Automation User - Office");
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
    }

    //Author:  Aditya
    @And("I set technician as group by in {string}")
    public void setTechnicianGroupBy(String needReportType) {
        officeObjects.navigateToReportType(needReportType);
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, "Technician");
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
    }

    //Author: Aditya
    @And("I search credit memo customer on {string}")
    public void searchCreditMemo_BST(String needReportType) {
        officeObjects.navigateToReportType(needReportType);
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, "Customer Name");
        billingByServiceTypeTab.clickAdvancedFilters();
        billingByServiceTypeTab.setType("invoice_bbst", "Credit Memo");
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
        billingByServiceTypeTab.searchNewCustomer(billingByServiceTypeTab.search_bbst, customerName_BST);
    }

    //Author: Aditya
    @And("I search customer on the {string} with AutoPay as {string} and Payment Status as {string}")
    public void searchSuccessfulPaymentAutoPay_BST(String needReportType, String needAutoPayType, String needPaymentStatus) {
        officeObjects.navigateToReportType(needReportType);
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, "Customer Name");
        billingByServiceTypeTab.clickAdvancedFilters();
        billingByServiceTypeTab.set(billingByServiceTypeTab.aPayType_bbst, needAutoPayType);
        billingByServiceTypeTab.set(billingByServiceTypeTab.aPayStatus_bbst, needPaymentStatus);
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
        billingByServiceTypeTab.searchNewCustomer(billingByServiceTypeTab.search_bbst, customerName_BST);

    }

    //Author: Aditya
    @And("I search customer on the {string} with AutoPay as {string}")
    public void searchCustomerAutoPay_BST(String needReportType, String needAutoPayType) {
        officeObjects.navigateToReportType(needReportType);
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, "Customer Name");
        billingByServiceTypeTab.clickAdvancedFilters();
        billingByServiceTypeTab.set(billingByServiceTypeTab.aPayType_bbst, needAutoPayType);
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
        billingByServiceTypeTab.searchNewCustomer(billingByServiceTypeTab.search_bbst, customerName_BST);

    }

    //Author: Aditya
    @And("I search standalone write off customer on the BST report")
    public void validateStandAloneWriteOffCustomer_BST() throws Exception {
        officeObjects.navigateToReportType("Billing by Service Type");
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, "Customer Name");
        billingByServiceTypeTab.clickAdvancedFilters();
        billingByServiceTypeTab.setType("invoice_bbst", "Stand-Alone Invoices");
        billingByServiceTypeTab.set(billingByServiceTypeTab.writeOffs, "Yes");
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
        billingByServiceTypeTab.searchNewCustomer(billingByServiceTypeTab.search_bbst, billingByServiceTypeTab.getCustomerName_CustomerCard_InfoTab());
    }

    //Author: Aditya
    @And("I set group by and subgroups to Billing Frequency, Customer ID and Invoice in {string}")
    public void setAllThreeGroups(String needReportType) {
        officeObjects.navigateToReportType(needReportType);
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, "Billing Frequency");
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.subGroupOne, "Customer ID");
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.subGroupTwo, "Invoice");
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
    }

    //Author: Aditya
    @And("I validate Billing or Payment by service type report generated from Billing Frequency, Customer ID and Invoice")
    public void validateAllThreeGroups() {
        String customerID_BBSTReport = Utilities.getElementTextValue("//tr//td[text()='" + customerID_BST + "']", Utilities.ElementType.XPath);
        String billingFrequency_BBSTReport = Utilities.getElementTextValue("//tr//td[text()='" + customerID_BST + "']/parent::tr/preceding-sibling::tr[not(contains(@detailvalues,'customerID'))]/td[1]", Utilities.ElementType.XPath);
        String invoiceID_BBSTReport = Utilities.getElementTextValue("//tr//td[text()='" + customerID_BST + "']/parent::tr/following-sibling::tr/td[1]", Utilities.ElementType.XPath);
        try {
            WebElement elm = FindElement.elementByAttribute("//tr//td[text()='" + customerID_BST + "']", FindElement.InputType.XPath);
            if (elm.isDisplayed()) {
                result(customerID_BST, customerID_BBSTReport, "Customer ID validation", "Report Validation");
                if (CucumberBaseClass.scenarioName().equals("Multi Group By filter validation in PST")) {
                    result("Billed After Service", billingFrequency_BBSTReport, "Billing Frequency Validation", "Report Validation");
                } else {
                    result("After Service", billingFrequency_BBSTReport, "Billing Frequency Validation", "Report Validation");
                }
                result(invoiceID, invoiceID_BBSTReport, "Invoice ID validation", "Report Validation");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println(customerID_BST + " is not visible on Billing by Service Type report. " + e);
        }
    }

    //Author: Aditya
    @Then("I search customer in billing frequency line item")
    public void searchCustomerBillingFrequencyLineItem() throws InterruptedException {
        billingByServiceTypeTab.click("//tr//td[text()='" + customerID_BST + "']");
        billingByServiceTypeTab.searchNewCustomer(billingByServiceTypeTab.search_lineItem, customerID_BST);
        FindElement.elementByAttribute(billingByServiceTypeTab.search_lineItem, FindElement.InputType.XPath).sendKeys(Keys.ENTER);
        Thread.sleep(500);
    }

    //Author: Aditya
    @And("I search {string} invoice on {string}")
    public void validateStandAloneWriteOffCustomer_BST(String needInvoiceType, String needReportType) throws
            Exception {
        officeObjects.navigateToReportType(needReportType);
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, "Customer Name");
        billingByServiceTypeTab.clickAdvancedFilters();
        billingByServiceTypeTab.setType("invoice_bbst", needInvoiceType);
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
        billingByServiceTypeTab.searchNewCustomer(billingByServiceTypeTab.search_bbst, billingByServiceTypeTab.getCustomerName_CustomerCard_InfoTab());
    }

    //Author: Aditya
    @Then("I click technician {string} assigned to the invoice")
    public void clickTechAssigned(String technicianName) {
        billingByServiceTypeTab.clickDescription_reportDetails(technicianName);
    }

}

