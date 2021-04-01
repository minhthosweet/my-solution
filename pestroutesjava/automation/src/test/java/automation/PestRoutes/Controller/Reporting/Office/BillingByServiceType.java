package automation.PestRoutes.Controller.Reporting.Office;

import automation.PestRoutes.Controller.Billings.AccountReceivable;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
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
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static automation.PestRoutes.Utilities.AssertException.result;

public class BillingByServiceType extends AppData {
    CreateCustomerDialog createCustomerDIalog;
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

    public String standAloneInvoiceAmount = "400.00";
    private String customerID = "";
    private String invoiceID = "";
    private String dateOfInvoice = "";
    private String customerName = "";

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
        createCustomerDIalog = new CreateCustomerDialog();
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
        billingByServiceTypeTab.searchNewCustomer(billingByServiceTypeTab.search_bbst, customerName);
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
        billingByServiceTypeTab.click(billingByServiceTypeTab.toggleChart);

        AssertException.validateFieldEnabled(fields);
    }

    //Author: Aditya
    @And("I add additional properties to customer")
    public void addAdditionalProperties() throws IOException, InterruptedException {
        billingByServiceTypeTab.editCustomerPerFilters();
    }

    //Author: Aditya
    @And("I add all the filters")
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
    @And("I validate if the report is linked to the customer card")
    public void validateLink_customerCard() throws Exception {
        billingByServiceTypeTab.clickDescription_reportDetails();
        billingByServiceTypeTab.customerDetails();
    }

    private String subTotalValue = "";
    private String taxValue = "";
    private String totalCollected = "";

    //Author: Aditya
    @And("I validate data generated from billing or payments by service type report")
    public void validateDataInBillingOrPaymentsReport() throws InterruptedException, IOException {
        invImplementation = new InvoiceImplementation();
        customerCardHeader = new CustomerViewDialog_Header();
        creditMemoTab = new CreditMemoTab();
        header = new Header();
        createNewCustomer = new CreateNewCustomer();
        header.searchCustomerWithName(createNewCustomer.getCustomerFullName());
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        if (CucumberBaseClass.scenarioName().equals("Credit memo validation is BST")) {
            billingByServiceTypeTab.click(creditMemoTab.getTicketID());
            creditMemoTab.clickAppliedCharge_invoiceApplications();
        } else {
            invImplementation.clickInitialInvoice();
        }
        subTotalValue = invImplementation.getSubTotalValue();
        taxValue = invImplementation.getTaxValue();
        totalCollected = billingByServiceTypeTab.get(invImplementation.paymentsInPayments);

        if (CucumberBaseClass.scenarioName().equals("Credit memo validation is BST")) {
            result("$-" + totalCollected.substring(1), billingByServiceTypeTab.get(billingByServiceTypeTab.totalCollected_Report), "Total Collected in the report", "BBST Report Validation");
            result(billingByServiceTypeTab.get(billingByServiceTypeTab.billedServices_Report), "-" + (subTotalValue.substring(1)), "Sub Total Value Validation in report",
                    "BBST Report Validation");
            result(billingByServiceTypeTab.get(billingByServiceTypeTab.tax_Report), "$0.00", "Tax Value Validation in report",
                    "BBST Report Validation");
        } else if (CucumberBaseClass.scenarioName().equals("Multi Group By filter validation in BBST")) {
            result(totalCollected, billingByServiceTypeTab.get(billingByServiceTypeTab.totalCollected_Report), "Total Collected in the report", "BBST Report Validation");
            result(billingByServiceTypeTab.getBilledServices_MultiGroupReport(customerID), subTotalValue, "Sub Total Value Validation in report",
                    "BBST Report Validation");
            result(billingByServiceTypeTab.getTaxRate_MultiGroupReport(customerID), taxValue, "Tax Value Validation in report",
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
    @And("I validate line item data in Billing or Payments by service type report")
    public void validateLineItemValues_BillingOrPaymentReport() throws IOException {
        if (CucumberBaseClass.scenarioName().equals("Credit memo validation is BST")) {
            result(Utilities.currentDate("MM-dd-yyyy"), billingByServiceTypeTab.get(billingByServiceTypeTab.paymentDate_lineItem), "Payment Date Validation in Detail Report", "BBST Report Validation");
            result(Utilities.currentDate("MM-dd-YYYY"), billingByServiceTypeTab.get(billingByServiceTypeTab.invoiceDate_lineItem), "Invoice Date Validation", "BBST Report Validation");
            result("$-" + totalCollected.substring(1), billingByServiceTypeTab.get(billingByServiceTypeTab.totalCollected_Customer), "Total Collected in the detail report", "BBST Report Validation");
            result(billingByServiceTypeTab.getBilledServiceValue_Customer(), "$-" + (subTotalValue.substring(1)), "Sub Total Value Validation in detailed report",
                    "BBST Report Validation");
            result(billingByServiceTypeTab.getBilledTaxValue_Customer(), "$0.00", "Tax Value Validation in detailed report",
                    "BBST Report Validation");
        } else if (CucumberBaseClass.scenarioName().equals("Balance Age validation BST with StandAlone Invoices")) {
            String expectedDateOfInvoice = dateOfInvoice.replaceAll("/", "-");
            String actualDateOfInvoice = billingByServiceTypeTab.get(billingByServiceTypeTab.invoiceDate_lineItem);
            if (actualDateOfInvoice.charAt(0) == '0') {
                actualDateOfInvoice = actualDateOfInvoice.substring(1, actualDateOfInvoice.length());
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
        result(billingByServiceTypeTab.getAttributeValue(invImplementation.activeInvoiceOnTheLeft, "ticketid"), billingByServiceTypeTab.get(billingByServiceTypeTab.invoiceID_lineItem), "Invoice ID validation in detail report", "BBST Report Validation");
        result(getData("serviceDescription", generalData), billingByServiceTypeTab.get(billingByServiceTypeTab.serviceType_lineItem), "Service Type in detail report validation", "BBST Report Validation");
    }

    //Author: Aditya
    @And("I validate the fields generated by billing by service type report")
    public void validateFields_BillingServiceReport() {
        String[] fields;
        if (CucumberBaseClass.scenarioName().equals("Multi Group By filter validation in BBST")) {
            fields = new String[]{billingByServiceTypeTab.filterTypes("description_bbstReport_MultiGroup"),
                    billingByServiceTypeTab.filterTypes("services_bbstReport_MultiGroup"),
                    billingByServiceTypeTab.filterTypes("lineItemQuantity_bbstReport_MultiGroup"),
                    billingByServiceTypeTab.filterTypes("totalCollected_bbstReport_MultiGroup"),
                    billingByServiceTypeTab.filterTypes("taxCollected_bbstReport_MultiGroup"),
                    billingByServiceTypeTab.filterTypes("taxInvoiced_bbstReport_MultiGroup"),
                    billingByServiceTypeTab.filterTypes("paymentsCollected_bbstReport_MultiGroup"),
                    billingByServiceTypeTab.filterTypes("billedServices_bbstReport_MultiGroup")};
        } else {
            fields = new String[]{billingByServiceTypeTab.filterTypes("description_bbstReport"),
                    billingByServiceTypeTab.filterTypes("services_bbstReport"),
                    billingByServiceTypeTab.filterTypes("lineItemQuantity_bbstReport"),
                    billingByServiceTypeTab.filterTypes("totalCollected_bbstReport"),
                    billingByServiceTypeTab.filterTypes("taxCollected_bbstReport"),
                    billingByServiceTypeTab.filterTypes("taxInvoiced_bbstReport"),
                    billingByServiceTypeTab.filterTypes("paymentsCollected_bbstReport"),
                    billingByServiceTypeTab.filterTypes("billedServices_bbstReport")};
        }
        AssertException.validateFieldEnabled(fields);
    }

    //Author: Aditya
    @And("I validate the fields are displayed in individual line items")
    public void validateFields_BBSTLineItem() throws InterruptedException {
        createNewCustomer = new CreateNewCustomer();
        header = new Header();

        try {
            String[] fields = {billingByServiceTypeTab.search_lineItem,
                    billingByServiceTypeTab.pageNumber_lineItem,
                    billingByServiceTypeTab.filterTypes("customerID_lineItem"),
                    billingByServiceTypeTab.filterTypes("customerName_lineItem"),
                    billingByServiceTypeTab.filterTypes("invoiceID_lineItem"),
                    billingByServiceTypeTab.filterTypes("date_lineItem"),
                    billingByServiceTypeTab.filterTypes("serviceDescription_lineItem"),
                    billingByServiceTypeTab.filterTypes("billingFrequency_lineItem"),
                    billingByServiceTypeTab.filterTypes("itemsQuantity_lineItem"),
                    billingByServiceTypeTab.filterTypes("totalCollected_lineItem"),
                    billingByServiceTypeTab.filterTypes("taxCollected_lineItem"),
                    billingByServiceTypeTab.filterTypes("tax_lineItem"),
                    billingByServiceTypeTab.filterTypes("appliedPaymentsBeforeTax_lineItem"),
                    billingByServiceTypeTab.filterTypes("dateCollected_lineItem"),
                    billingByServiceTypeTab.filterTypes("billedServices_lineItem"),
                    billingByServiceTypeTab.filterTypes("first_page"),
                    billingByServiceTypeTab.filterTypes("previous_page"),
                    billingByServiceTypeTab.filterTypes("next_page"),
                    billingByServiceTypeTab.filterTypes("last_page"),
                    billingByServiceTypeTab.exportDetailsToCSV_button};
            AssertException.validateFieldEnabled(fields);
        } catch (StaleElementReferenceException e) {
            System.out.println(e);
        } finally {
            if (!CucumberBaseClass.scenarioName().equals("Multi Group By filter validation in BBST")) {
                header.clickAccessHistory();
            }
            createNewCustomer.removeCustomer();
        }
    }

    //Author: Aditya
    @When("I create customer with balance with prefers paper and residential property type")
    public void createCustomerWithBalancePrefersPaper() throws Exception {
        createNewCustomer = new CreateNewCustomer();
        accountReceivable = new AccountReceivable();
        header = new Header();
        customerViewDialog_overviewTab = new CustomerViewDialog_OverviewTab();
        invImplementation = new InvoiceImplementation();
        createNewCustomer.createCustomerWithPrefPaperAndResidentialProperty();
        customerName = createNewCustomer.getCustomerFullName();
        accountReceivable.createStandAloneServiceInvoice(standAloneInvoiceAmount, Utilities.currentDate("MM/dd/yyyy"), getData("serviceDescription", generalData));
        invoiceID = Utilities.getAttributeValue(invImplementation.invoiceAccountSummaryClick, "ticketid");
        customerID = customerViewDialog_overviewTab.getCustomerIDFromHeader();
        createNewCustomer.closeCustomerCard();
        header.searchCustomerWithName(customerName);
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
        if (!CucumberBaseClass.scenarioName().equals("Prefer Paper and Property Type validation PST")) {
            billingByServiceTypeTab.set(billingByServiceTypeTab.prefersPaper, "No");
        }
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
        billingByServiceTypeTab.searchNewCustomer(billingByServiceTypeTab.search_bbst, billingByServiceTypeTab.getCustomerName_CustomerCard_InfoTab());
    }

    //Author: Aditya
    @When("I edit customer to commercial property and not require paper")
    public void editCustomer_notRequirePaper_CommercialProperty() throws IOException, InterruptedException {
        billingByServiceTypeTab.editCustomer_NoPaper_Commercial();
    }

    //Author: Aditya
    @Then("I validate customer with balance age in BST")
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
            customerName = fname + " " + lname;
            Thread.sleep(100);
            createNewCustomer.createACustomer(fname, lname);
            int currentMonth = GetDate.getMonth(Utilities.currentDate("MM/dd/yyyy"));
            int currentYear = GetDate.getYear(Utilities.currentDate("MM/dd/yyyy"));
            dateOfInvoice = GetDate.minusGenericDayToDate(Utilities.currentDate("MM/dd/yyyy"), invoiceDaysPastDue[i]);
            int monthOfInv = GetDate.getMonth(dateOfInvoice);
            int yearOfInv = GetDate.getYear(dateOfInvoice);
            accountReceivable.createStandAloneServiceInvoice("400", dateOfInvoice, getData("serviceDescription", generalData));
            customerCardHeader.navigateTo(customerCardHeader.adminTabInDialog);
            customerViewDialog_admin.changeAccountStatus_Active();
            createNewCustomer.closeCustomerCard();
            officeObjects.navigateToReportType("Billing by Service Type");
            billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, "Customer Name");
            billingByServiceTypeTab.clickAdvancedFilters();
            billingByServiceTypeTab.setType("invoice_bbst", "Stand-Alone Invoices");
            billingByServiceTypeTab.setBalance_bbst("400");
            billingByServiceTypeTab.set(billingByServiceTypeTab.balanceAge_bbst, balanceAge[i]);
            int monthPastDue = currentMonth - monthOfInv;
            int yearsPastDue = currentYear - yearOfInv;
            if (monthPastDue == 0 && yearsPastDue == 0) {
                billingByServiceTypeTab.setDateRange(billingByServiceTypeTab.dateParams, "Last Week");
            } else if (monthPastDue == 1 && yearsPastDue == 0) {
                billingByServiceTypeTab.setDateRange(billingByServiceTypeTab.dateParams, "Last Month");
            } else if (yearsPastDue > 0) {
                billingByServiceTypeTab.setDateRange(billingByServiceTypeTab.dateParams, "Last Year");
            } else if (monthPastDue > 1 && yearsPastDue == 0) {
                billingByServiceTypeTab.setDateRange(billingByServiceTypeTab.dateParams, "This Year");
            }
            billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
            billingByServiceTypeTab.searchNewCustomer(billingByServiceTypeTab.search_bbst, billingByServiceTypeTab.getCustomerName_CustomerCard_InfoTab());
            validateFields_BillingServiceReport();
            validateLink_customerCard();
            validateDataInBillingOrPaymentsReport();
            validateLineItemValues_BillingOrPaymentReport();
            validateFields_BBSTLineItem();
        }
    }

    //Author: Aditya
    @And("I set filter for sold date, scheduler, sale teams, sales reps and service invoice")
    public void validate_soldDate_Scheduler_SaleTeams_SalesReps() {
        officeObjects.navigateToReportType("Billing by Service Type");
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, "Customer Name");
        billingByServiceTypeTab.clickAdvancedFilters();
        billingByServiceTypeTab.setDateRange(billingByServiceTypeTab.soldDateRange, "Today");
        billingByServiceTypeTab.setType("scheduledBy_bbst", "All Office Staff");
        billingByServiceTypeTab.setType("soldByTeam_bbst", "All Office Staff");
        billingByServiceTypeTab.setType("invoice_bbst", "Service Invoices");
        billingByServiceTypeTab.setSalesRep("soldbySalesRep_bbst", "Automation User - Office");
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
    }

    //Author: Aditya
    @And("I search credit memo customer on the BST report")
    public void searchCreditMemo_BST() throws Exception {
        officeObjects.navigateToReportType("Billing by Service Type");
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, "Customer Name");
        billingByServiceTypeTab.clickAdvancedFilters();
        billingByServiceTypeTab.setType("invoice_bbst", "Credit Memo");
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
        billingByServiceTypeTab.searchNewCustomer(billingByServiceTypeTab.search_bbst, customerName);
    }

    //Author: Aditya
    @And("I search customer on the BST report with AutoPay as {string}")
    public void searchCCAutoPay_BST(String needAutoPayType) throws Exception {
        officeObjects.navigateToReportType("Billing by Service Type");
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, "Customer Name");
        billingByServiceTypeTab.clickAdvancedFilters();
        billingByServiceTypeTab.set(billingByServiceTypeTab.aPayType_bbst, needAutoPayType);
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
        billingByServiceTypeTab.searchNewCustomer(billingByServiceTypeTab.search_bbst, customerName);

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
    @And("I set group by and subgroups to Billing Frequency, Customer ID and Invoice")
    public void setAllThreeGroups() {
        officeObjects.navigateToReportType("Billing by Service Type");
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.groupBy, "Billing Frequency");
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.subGroupOne, "Customer ID");
        billingByServiceTypeTab.setGroupFilter(billingByServiceTypeTab.subGroupTwo, "Invoice");
        billingByServiceTypeTab.click(billingByServiceTypeTab.refresh_bbst);
    }

    //Author: Aditya
    @And("I validate Billing by service type report generated from Billing Frequency, Customer ID and Invoice")
    public void validateAllThreeGroups() {
        String customerID_BBSTReport = Utilities.getElementTextValue("//tr//td[text()='" + customerID + "']", Utilities.ElementType.XPath);
        String billingFrequency_BBSTReport = Utilities.getElementTextValue("//tr//td[text()='" + customerID + "']/parent::tr/preceding-sibling::tr[not(contains(@detailvalues,'customerID'))]/td[1]", Utilities.ElementType.XPath);
        String invoiceID_BBSTReport = Utilities.getElementTextValue("//tr//td[text()='" + customerID + "']/parent::tr/following-sibling::tr/td[1]", Utilities.ElementType.XPath);
        try {
            WebElement elm = FindElement.elementByAttribute("//tr//td[text()='" + customerID + "']", FindElement.InputType.XPath);
            if (elm.isDisplayed()) {
                result(customerID, customerID_BBSTReport, "Customer ID validation", "BBST Report Validation");
                result("After Service", billingFrequency_BBSTReport, "Billing Frequency Validation", "BBST Report Validation");
                result(invoiceID, invoiceID_BBSTReport, "Invoice ID validation", "BBST Report Validation");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println(customerID + " is not visible on Billing by Service Type report. " + e);
        }
    }

    //Author: Aditya
    @Then("I search customer in billing frequency line item")
    public void searchCustomerBillingFrequencyLineItem() throws Exception {
        billingByServiceTypeTab.click("//tr//td[text()='" + customerID + "']");
        billingByServiceTypeTab.searchNewCustomer(billingByServiceTypeTab.search_lineItem, customerID);
    }

}

