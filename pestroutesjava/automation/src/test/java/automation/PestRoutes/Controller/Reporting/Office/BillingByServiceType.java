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
    BillingByServiceTypeTab billingByServiceType = new BillingByServiceTypeTab();
    CustomerViewDialog_Header customerCardHeader;
    Header header;
    CreateNewCustomer createNewCustomer;
    CustomerViewDialog_OverviewTab customerViewDialog_overviewTab;
    CustomerViewDialog_Admin customerViewDialog_admin;
    AccountReceivable accountReceivable;
    CreditMemoTab creditMemoTab;

    public String standAloneInvoiceAmount = "400.00";
    private String customerID = "";
    private String invoiceID = "";
    private String dateOfInvoice = "";
    private String customerName = "";

    //Author: Aditya
    @Test
    @And("I navigate to Billing by Service Type")
    public void navigateToBillingByServiceType() {
        billingByServiceType.navigateToBillingByServiceTypePage();
    }

    public BillingByServiceType() {
    }

    //Author: Aditya
    @And("I group by customer name")
    public void groupByCustomerName() {
        billingByServiceType.setGroupFilter(billingByServiceType.groupBy, "Customer Name");
    }

    //Author: Aditya
    @And("I search for the billing customer")
    public void searchCustomer() throws Exception {
        createCustomerDIalog = new CreateCustomerDialog();
        billingByServiceType.click(billingByServiceType.refresh_bbst);
        billingByServiceType.searchNewCustomer(billingByServiceType.search_bbst, billingByServiceType.getCustomerName_CustomerCard_InfoTab());
    }

    //Author: Aditya
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

    //Author: Aditya
    @And("I add additional properties to customer")
    public void addAdditionalProperties() throws IOException, InterruptedException {
        billingByServiceType.editCustomerPerFilters();
    }

    //Author: Aditya
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

    //Author: Aditya
    @And("I validate if the report is linked to the customer card")
    public void validateLink_customerCard() throws Exception {
        billingByServiceType.clickDescription_reportDetails();
        billingByServiceType.customerDetails();
    }

    private String subTotalValue = "";
    private String taxValue = "";
    private String totalCollected = "";

    //Author: Aditya
    @And("I validate billing by service type report")
    public void validateBBSTReport() throws InterruptedException, IOException {
        invImplementation = new InvoiceImplementation();
        customerCardHeader = new CustomerViewDialog_Header();
        creditMemoTab = new CreditMemoTab();
        header = new Header();
        header.searchCustomerWithName(customerName);
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        if (CucumberBaseClass.scenarioName().equals("Credit memo validation is BST")) {
            billingByServiceType.click(creditMemoTab.getTicketID());
            creditMemoTab.clickAppliedCharge_invoiceApplications();
        } else {
            invImplementation.clickInitialInvoice();
        }
        subTotalValue = invImplementation.getSubTotalValue();
        taxValue = invImplementation.getTaxValue();
        totalCollected = billingByServiceType.get(invImplementation.paymentsInPayments);

        if (CucumberBaseClass.scenarioName().equals("Credit memo validation is BST")) {
            result("$-" + totalCollected.substring(1), billingByServiceType.get(billingByServiceType.totalCollected_Report), "Total Collected in the report", "BBST Report Validation");
            result(billingByServiceType.get(billingByServiceType.billedServices_Report), "-" + (subTotalValue.substring(1)), "Sub Total Value Validation in report",
                    "BBST Report Validation");
            result(billingByServiceType.get(billingByServiceType.tax_Report), "$0.00", "Tax Value Validation in report",
                    "BBST Report Validation");
        } else if (CucumberBaseClass.scenarioName().equals("Multi Group By filter validation in BBST")) {
            result(totalCollected, billingByServiceType.get(billingByServiceType.totalCollected_Report), "Total Collected in the report", "BBST Report Validation");
            result(billingByServiceType.getBilledServices_MultiGroupReport(customerID), subTotalValue, "Sub Total Value Validation in report",
                    "BBST Report Validation");
            result(billingByServiceType.getTaxRate_MultiGroupReport(customerID), taxValue, "Tax Value Validation in report",
                    "BBST Report Validation");
        } else {
            result(totalCollected, billingByServiceType.get(billingByServiceType.totalCollected_Report), "Total Collected in the report", "BBST Report Validation");
            result("$" + billingByServiceType.get(billingByServiceType.billedServices_Report), subTotalValue, "Sub Total Value Validation in report",
                    "BBST Report Validation");
            result(billingByServiceType.get(billingByServiceType.tax_Report), taxValue, "Tax Value Validation in report",
                    "BBST Report Validation");
        }
    }

    //Author: Aditya
    @And("I validate line item values in Billing by service type report")
    public void validateLineItemValues_BBSTReport() throws IOException {
        if (CucumberBaseClass.scenarioName().equals("Credit memo validation is BST")) {
            result(Utilities.currentDate("MM-dd-yyyy"), billingByServiceType.get(billingByServiceType.paymentDate_lineItem), "Payment Date Validation in Detail Report", "BBST Report Validation");
            result(Utilities.currentDate("MM-dd-YYYY"), billingByServiceType.get(billingByServiceType.invoiceDate_lineItem), "Invoice Date Validation", "BBST Report Validation");
            result("$-" + totalCollected.substring(1), billingByServiceType.get(billingByServiceType.totalCollected_Customer), "Total Collected in the detail report", "BBST Report Validation");
            result(billingByServiceType.getBilledServiceValue_Customer(), "$-" + (subTotalValue.substring(1)), "Sub Total Value Validation in detailed report",
                    "BBST Report Validation");
            result(billingByServiceType.getBilledTaxValue_Customer(), "$0.00", "Tax Value Validation in detailed report",
                    "BBST Report Validation");
        } else if (CucumberBaseClass.scenarioName().equals("Balance Age validation BST with StandAlone Invoices")) {
            String expectedDateOfInvoice = dateOfInvoice.replaceAll("/", "-");
            String actualDateOfInvoice = billingByServiceType.get(billingByServiceType.invoiceDate_lineItem);
            if (actualDateOfInvoice.charAt(0) == '0') {
                actualDateOfInvoice = actualDateOfInvoice.substring(1, actualDateOfInvoice.length());
            }
            result(expectedDateOfInvoice, actualDateOfInvoice, "Invoice Date Validation", "BBST Report Validation");
        } else {
            result(totalCollected, billingByServiceType.get(billingByServiceType.totalCollected_Customer), "Total Collected in the detail report", "BBST Report Validation");
            result(billingByServiceType.getBilledServiceValue_Customer(), subTotalValue, "Sub Total Value Validation in detailed report",
                    "BBST Report Validation");
            result(billingByServiceType.getBilledTaxValue_Customer(), taxValue, "Tax Value Validation in detailed report",
                    "BBST Report Validation");
            result(Utilities.currentDate("MM-dd-YYYY"), billingByServiceType.get(billingByServiceType.invoiceDate_lineItem), "Invoice Date Validation", "BBST Report Validation");
        }
        result(billingByServiceType.getAttributeValue(invImplementation.activeInvoiceOnTheLeft, "ticketid"), billingByServiceType.get(billingByServiceType.invoiceID_lineItem), "Invoice ID validation in detail report", "BBST Report Validation");
        result(getData("serviceDescription", generalData), billingByServiceType.get(billingByServiceType.serviceType_lineItem), "Service Type in detail report validation", "BBST Report Validation");
    }

    //Author: Aditya
    @And("I validate the fields generated by billing by service type report")
    public void validateFields_BBSTReport() {
        String[] fields;
        if (CucumberBaseClass.scenarioName().equals("Multi Group By filter validation in BBST")) {
            fields = new String[]{billingByServiceType.filterTypes("description_bbstReport_MultiGroup"),
                    billingByServiceType.filterTypes("services_bbstReport_MultiGroup"),
                    billingByServiceType.filterTypes("lineItemQuantity_bbstReport_MultiGroup"),
                    billingByServiceType.filterTypes("totalCollected_bbstReport_MultiGroup"),
                    billingByServiceType.filterTypes("taxCollected_bbstReport_MultiGroup"),
                    billingByServiceType.filterTypes("taxInvoiced_bbstReport_MultiGroup"),
                    billingByServiceType.filterTypes("paymentsCollected_bbstReport_MultiGroup"),
                    billingByServiceType.filterTypes("billedServices_bbstReport_MultiGroup")};
        } else {
            fields = new String[]{billingByServiceType.filterTypes("description_bbstReport"),
                    billingByServiceType.filterTypes("services_bbstReport"),
                    billingByServiceType.filterTypes("lineItemQuantity_bbstReport"),
                    billingByServiceType.filterTypes("totalCollected_bbstReport"),
                    billingByServiceType.filterTypes("taxCollected_bbstReport"),
                    billingByServiceType.filterTypes("taxInvoiced_bbstReport"),
                    billingByServiceType.filterTypes("paymentsCollected_bbstReport"),
                    billingByServiceType.filterTypes("billedServices_bbstReport")};
        }
        AssertException.validateFieldEnabled(fields);
    }

    //Author: Aditya
    @And("I validate the fields are displayed in individual line items")
    public void validateFields_BBSTLineItem() throws InterruptedException {
        createNewCustomer = new CreateNewCustomer();
        header = new Header();
        
        try {
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
                    billingByServiceType.filterTypes("last_page"),
                    billingByServiceType.exportDetailsToCSV_button};
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
    @And("I search for customer with pref paper and residential property in BST")
    public void filterByPrefPaperAndResidentialProperty() throws Exception {
        billingByServiceType.navigateToBillingByServiceTypePage();
        billingByServiceType.setGroupFilter(billingByServiceType.groupBy, "Customer Name");
        billingByServiceType.clickAdvancedFilters();
        billingByServiceType.set(billingByServiceType.propType_bbst, "Residential Only");
        billingByServiceType.set(billingByServiceType.prefersPaper, "Yes");
        billingByServiceType.click(billingByServiceType.refresh_bbst);
        billingByServiceType.searchNewCustomer(billingByServiceType.search_bbst, billingByServiceType.getCustomerName_CustomerCard_InfoTab());
    }

    //Author: Aditya
    @And("I search for customer without pref paper and commercial property in BST")
    public void filterByNotPrefPaperAndCommercialProperty() throws Exception {
        billingByServiceType.navigateToBillingByServiceTypePage();
        billingByServiceType.setGroupFilter(billingByServiceType.groupBy, "Customer Name");
        billingByServiceType.clickAdvancedFilters();
        billingByServiceType.set(billingByServiceType.propType_bbst, "Commercial Only");
        billingByServiceType.set(billingByServiceType.prefersPaper, "No");
        billingByServiceType.click(billingByServiceType.refresh_bbst);
        billingByServiceType.searchNewCustomer(billingByServiceType.search_bbst, billingByServiceType.getCustomerName_CustomerCard_InfoTab());
    }

    //Author: Aditya
    @When("I edit customer to commercial property and not require paper")
    public void editCustomer_notRequirePaper_CommercialProperty() throws IOException, InterruptedException {
        billingByServiceType.editCustomer_NoPaper_Commercial();
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
            billingByServiceType.navigateToBillingByServiceTypePage();
            billingByServiceType.setGroupFilter(billingByServiceType.groupBy, "Customer Name");
            billingByServiceType.clickAdvancedFilters();
            billingByServiceType.setType("invoice_bbst", "Stand-Alone Invoices");
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
            billingByServiceType.searchNewCustomer(billingByServiceType.search_bbst, billingByServiceType.getCustomerName_CustomerCard_InfoTab());
            validateFields_BBSTReport();
            validateLink_customerCard();
            validateBBSTReport();
            validateLineItemValues_BBSTReport();
            validateFields_BBSTLineItem();
        }
    }

    //Author: Aditya
    @And("I set filter for sold date, scheduler, sale teams, sales reps and service invoice")
    public void validate_soldDate_Scheduler_SaleTeams_SalesReps() {
        billingByServiceType.navigateToBillingByServiceTypePage();
        billingByServiceType.setGroupFilter(billingByServiceType.groupBy, "Customer Name");
        billingByServiceType.clickAdvancedFilters();
        billingByServiceType.setDateRange(billingByServiceType.soldDateRange, "Today");
        billingByServiceType.setType("scheduledBy_bbst", "All Office Staff");
        billingByServiceType.setType("soldByTeam_bbst", "All Office Staff");
        billingByServiceType.setType("invoice_bbst", "Service Invoices");
        billingByServiceType.setSalesRep("soldbySalesRep_bbst", "Automation User - Office");
        billingByServiceType.click(billingByServiceType.refresh_bbst);
    }

    //Author: Aditya
    @And("I search credit memo customer on the BST report")
    public void searchCreditMemo_BST() throws Exception {
        billingByServiceType.navigateToBillingByServiceTypePage();
        billingByServiceType.setGroupFilter(billingByServiceType.groupBy, "Customer Name");
        billingByServiceType.clickAdvancedFilters();
        billingByServiceType.setType("invoice_bbst", "Credit Memo");
        billingByServiceType.click(billingByServiceType.refresh_bbst);
        billingByServiceType.searchNewCustomer(billingByServiceType.search_bbst, billingByServiceType.getCustomerName_CustomerCard_InfoTab());
    }

    //Author: Aditya
    @And("I search standalone write off customer on the BST report")
    public void validateStandAloneWriteOffCustomer_BST() throws Exception {
        billingByServiceType.navigateToBillingByServiceTypePage();
        billingByServiceType.setGroupFilter(billingByServiceType.groupBy, "Customer Name");
        billingByServiceType.clickAdvancedFilters();
        billingByServiceType.setType("invoice_bbst", "Stand-Alone Invoices");
        billingByServiceType.set(billingByServiceType.writeOffs, "Yes");
        billingByServiceType.click(billingByServiceType.refresh_bbst);
        billingByServiceType.searchNewCustomer(billingByServiceType.search_bbst, billingByServiceType.getCustomerName_CustomerCard_InfoTab());
    }

    //Author: Aditya
    @And("I set group by and subgroups to Billing Frequency, Customer ID and Invoice")
    public void setAllThreeGroups() {
        billingByServiceType.navigateToBillingByServiceTypePage();
        billingByServiceType.setGroupFilter(billingByServiceType.groupBy, "Billing Frequency");
        billingByServiceType.setGroupFilter(billingByServiceType.subGroupOne, "Customer ID");
        billingByServiceType.setGroupFilter(billingByServiceType.subGroupTwo, "Invoice");
        billingByServiceType.click(billingByServiceType.refresh_bbst);
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
        billingByServiceType.click("//tr//td[text()='" + customerID + "']");
        billingByServiceType.searchNewCustomer(billingByServiceType.search_lineItem, customerID);
    }

}

