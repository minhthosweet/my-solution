package automation.PestRoutes.Controller.Invoicing;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Subscriptions.AddSubscription;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.Invoice_Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.Utilities.Reporter;

import java.io.IOException;

public class InvoicingTab extends AppData {

    InvoiceImplementation invImplementation = new InvoiceImplementation();
    CreateNewCustomer createCustomer;
    RoutePageInvoicing invoiceRoutesTab = new RoutePageInvoicing();
    CustomerViewDialog_Header header;
    Invoice_Header invoiceHeader;
    AddSubscription addSubscription;
    CustomerViewDialog_SubscriptionTab subscriptionTab;

    private String treatmentAmount = "900";
    private Integer partialPaymentAmount = Integer.parseInt(treatmentAmount) / 2;
    private String successfulPartialCharge = "Successfully Charged Cash!$450.00";
    private String successfulFullCharge = "Successfully Charged Cash!$5,450.00";
    private String invoiceDate = "1";
    public static String invoiceCharges = null;
    public static String invoiceValue = null;
    public static String invoiceBalance = null;

    @Test
    public void CustomerInvoicing() throws Exception {

        // Implementation
        addNewInvoice(invoiceDate);
        InitialCost();
        assertInitialCharge();
        reducingBalance();
        assertPartialCharge(partialPaymentAmount, partialPaymentAmount);
        reducingBalance();
        assertFullCharge();
    }

    // Add a new invoice to the customer
    public void addNewInvoice(String date) throws Exception {
        createCustomer = new CreateNewCustomer();
        createCustomer.createCustomerWithEmail();
        createCustomer.searchCustomer();
        header = new CustomerViewDialog_Header();
        header.navigateTo(header.invoicesTabInDialog);
        invoiceRoutesTab.clickAddPayment();
        invoiceRoutesTab.clickAddNewInvoice(invoiceRoutesTab.addNewInvoice);
        invImplementation.newInvoiceDetails(treatmentAmount, date);
        invoiceRoutesTab.invoiceDetails();
        invoiceRoutesTab.selectAvailableItems();

    }

    // Calculates initial cost
    private Integer InitialCost() {
        return invImplementation.getInvoiceCost();
    }

    // Keeps checking balance
    private void reducingBalance() {
        invoiceHeader = new Invoice_Header();
        invoiceRoutesTab.clickAddPartialPayments();
        invoiceHeader.navigate(invoiceHeader.cash);
    }

    // Initial Assert - UNPAID
    private void assertInitialCharge() {
        Reporter.status(" Treatment Amount", Integer.toString(InitialCost() + Integer.parseInt(treatmentAmount)),
                Integer.toString(invImplementation.getTotalValueCharges()), "Invoicing");
        Reporter.status("", Integer.toString(InitialCost() + Integer.parseInt(treatmentAmount)),
                Integer.toString(invImplementation.getTotalValuePayments()), "Values match");
        Reporter.status("", "UNPAID", invImplementation.checkPaymentStatus(), "The payment status is UNPAID");
    }

    // Assert - Partial payment
    private void assertPartialCharge(Integer pAmount, Integer cAmount) {
        invImplementation.insertPaymentAmount(Integer.toString(pAmount), Integer.toString(cAmount));
        invImplementation.clickrecordPayment();
        Reporter.status("", successfulPartialCharge, invImplementation.getSuccessfulChargeAmount(), "Charged successfully");
        Reporter.status("", "PARTIALLY PAID", invImplementation.checkPaymentStatus(),
                "The payment status is PARTIALLY PAID");
        invImplementation.invoiceAccountSummaryClick();
    }

    // Assert - Full Payment
    private void assertFullCharge() {
        invImplementation.insertPaymentAmount(Integer.toString(invImplementation.getPaymentBalance()),
                Integer.toString(invImplementation.getPaymentBalance()));
        invImplementation.clickrecordPayment();
        Reporter.status("", successfulFullCharge, invImplementation.getSuccessfulChargeAmount(), "Charged successfully");
        Reporter.status("", "FULLY PAID", invImplementation.checkPaymentStatus(), "The payment status is FULLY PAID");
        invImplementation.invoiceAccountSummaryClick();
    }

    @When("I navigate to Invoice Tab")
    public void navigateToInvoiceTab() throws InterruptedException {
        header = new CustomerViewDialog_Header();
        header.navigateTo(header.invoicesTabInDialog);
    }

    @And("I validate invoice")
    public void validateInvoice() throws IOException, InterruptedException {
        addSubscription = new AddSubscription();
        header = new CustomerViewDialog_Header();
        subscriptionTab = new CustomerViewDialog_SubscriptionTab();
        header.navigateTo(header.subscriptionTabInDialog);
        String initialInvoiceValue = subscriptionTab.getInitialInvoiceValue();
        header.navigateTo(header.invoicesTabInDialog);
        result(initialInvoiceValue, "$" + invImplementation.getAccountBalance(), "Total Initial Invoice Value",
                "Invoice Validation");
        invImplementation.clickInvoice(getData("serviceDescription", generalData));
        result(initialInvoiceValue, "$" + invImplementation.getChargesBalance(), "Total Initial Invoice Value",
                "Invoice Validation");
        result(initialInvoiceValue, "$" + invImplementation.getPaymentsBalance(), "Total Initial Invoice Value",
                "Invoice Validation");
    }

    @And("I validate initial invoice created on invoice tab")
    public void validateInitialInvoice() throws InterruptedException {
        addSubscription = new AddSubscription();
        header = new CustomerViewDialog_Header();
        subscriptionTab = new CustomerViewDialog_SubscriptionTab();
        header.navigateTo(header.subscriptionTabInDialog);
        String serviceType = subscriptionTab.getServiceType();
        String initialInvoiceValue = subscriptionTab.getInitialInvoiceValue();
        String initialSubTotal = Double.toString(subscriptionTab.getInitialSubTotal());
        String taxAmount = Double.toString(subscriptionTab.getInitialTax());

        header.navigateTo(header.invoicesTabInDialog);
        result(initialInvoiceValue, invImplementation.getAccountBalance(), "Total Account Balance Validation",
                "Invoice Validation");
        invImplementation.clickInitialInvoice();

        result(addSubscription.initialQuote, invImplementation.getServiceCostBeforeTax(), "Service Cost Validation",
                "Invoice Validation");
        result(Double.toString(subscriptionTab.getInitialService_NewTicketItemPrice(addSubscription.ticketItem)), invImplementation.getAddOnValue(addSubscription.ticketItem), "Add On Value Validation",
                "Invoice Validation");
        result("$" + initialSubTotal, invImplementation.getSubTotalValue(), "Sub Total Value Validation",
                "Invoice Validation");
        result("$" + taxAmount, invImplementation.getTaxValue(), "Tax Value Validation",
                "Invoice Validation");
        result("-" + addSubscription.initialDiscount, invImplementation.getInitialDiscountValue(), "Discount Value Validation",
                "Invoice Validation");
        result(initialInvoiceValue, invImplementation.getChargesBalance(), "Total Invoice Value in Charges Validation",
                "Invoice Validation");
        result(initialInvoiceValue, invImplementation.getPaymentsBalance(), "Total Invoice Value in Payments Validation",
                "Invoice Validation");
        result(Utilities.currentDate("MM/dd/yy").replaceAll("0", ""), invImplementation.getInvoiceDate().replaceAll("0", ""), "Invoice Date Validation",
                "Invoice Validation");
        result(Utilities.currentDate("MM/dd/yy").replaceAll("0", ""), invImplementation.getDueDate().replaceAll("0", ""), "Due Date Validation",
                "Invoice Validation");
        if (serviceType.equals("After Agreement Signed")) {
            result("N/A", "N/A", "Appointment Date Validation",
                    "Invoice Validation");
        } else {
            result(Utilities.currentDate("MM/dd/yy").replaceAll("0", ""), invImplementation.getAppointmentDate().replaceAll("0", ""), "Appointment Date Validation",
                    "Invoice Validation");
        }
    }

    @And("I generate Account Statement Report of report type {string} for {string}")
    public void generateAccountStatementReport(String reportType, String day) throws InterruptedException {
        invImplementation.clickAccountStatementReport();
        invImplementation.selectDateRange(day);
        invImplementation.selectReportType(reportType);
        invImplementation.refreshAccountStatementReport();
    }

    @And("I validate the Beginning balance and Ending balance for a day before the invoice was created")
    public void validateBeginningEndingBalance_Yesterday_AccountStatementReport() {
        result(invImplementation.getBalance("Beginning Balance"), "$0.00", "Balance Validation",
                "Account Statement Report Validation");
        result(invImplementation.getResponsibleBalance("Beginning Balance"), "$0.00", "Responsible Balance Validation",
                "Account Statement Report Validation");
        result(invImplementation.getBalance("Ending Balance"), "$0.00", "Balance Validation",
                "Account Statement Report Validation");
        result(invImplementation.getResponsibleBalance("Ending Balance"), "$0.00", "Responsible Balance Validation",
                "Account Statement Report Validation");
    }

    @And("Validating beginning balance for invoice created {string} of report type {string}")
    public void validateBeginningBalance_AccountStatementReport(String day, String reportType) throws InterruptedException {
        generateAccountStatementReport(reportType, day);
        result(invImplementation.getBalance("Beginning Balance"), "$0.00", "Balance Validation",
                "Account Statement Report Validation");
        result(invImplementation.getResponsibleBalance("Beginning Balance"), "$0.00", "Responsible Balance Validation",
                "Account Statement Report Validation");
    }

    @And("Validating invoice balance for invoice created {string} of report type {string}")
    public void validateEndingBalance_AccountStatementReport(String day, String reportType) throws InterruptedException, IOException {
        invImplementation.clickInitialInvoice();
        invoiceCharges = invImplementation.getChargesBalance();
        invoiceBalance = invImplementation.getPaymentsBalance();
        generateAccountStatementReport(reportType, day);
        result(invImplementation.getInvoiceDate_accountStatementReport(getData("serviceDescription", generalData)), Utilities.currentDate("MM/dd/YY").replaceAll("0", ""), "Invoice Date",
                "Account Statement Report Validation");
        result(invImplementation.getInvoiceAmount_accountStatementReport(getData("serviceDescription", generalData)), invoiceCharges, "Invoice Value",
                "Account Statement Report Validation");
        result(invImplementation.getInvoiceBalance_accountStatementReport(getData("serviceDescription", generalData)), invoiceBalance, "Invoice Value",
                "Account Statement Report Validation");
    }

    @And("Validating ending balance for invoice created {string} of report type {string}")
    public void validateInvoiceBalance_AccountStatementReport(String day, String reportType) throws InterruptedException {
        invImplementation.clickAccountSummary();
        invoiceValue = invImplementation.getAccountBalance();
        generateAccountStatementReport(reportType, day);
        result(invImplementation.getBalance("Ending Balance"), invoiceValue, "Balance Validation",
                "Account Statement Report Validation");
        result(invImplementation.getResponsibleBalance("Ending Balance"), invoiceValue, "Responsible Balance Validation",
                "Account Statement Report Validation");
    }

    @And("I validate recurring invoice created on invoice tab")
    public void validateRecurringInvoice() throws InterruptedException {
        addSubscription = new AddSubscription();
        header = new CustomerViewDialog_Header();
        subscriptionTab = new CustomerViewDialog_SubscriptionTab();
        header.navigateTo(header.subscriptionTabInDialog);
        String initialInvoiceValue = subscriptionTab.getInitialInvoiceValue().substring(1, subscriptionTab.getInitialInvoiceValue().length());
        String recurringInvoiceValue = subscriptionTab.getRecurringInvoiceValue().substring(1, subscriptionTab.getRecurringInvoiceValue().length());
        String accountPendingBalance = Double.toString(Double.parseDouble(recurringInvoiceValue) + Double.parseDouble(initialInvoiceValue));
        String recurringSubTotal = Double.toString(subscriptionTab.getRecurringSubTotal());
        String taxAmount = Double.toString(subscriptionTab.getRecurringTax());
        header.navigateTo(header.invoicesTabInDialog);
        result("$" + accountPendingBalance, invImplementation.getAccountBalance(), "Total Invoice Value Validation",
                "Invoice Validation");
        invImplementation.clickRecurringInvoice(recurringInvoiceValue);

        result(Double.toString(addSubscription.recurringQuote), invImplementation.getServiceCostBeforeTax(), "Service Cost Before Tax Validation",
                "Invoice Validation");
        result(Double.toString(subscriptionTab.getInitialService_NewTicketItemPrice(addSubscription.ticketItem)), invImplementation.getAddOnValue(addSubscription.ticketItem), "Item Cost Validation",
                "Invoice Validation");
        result("$" + recurringSubTotal, invImplementation.getSubTotalValue(), "Sub Total Value Validation",
                "Invoice Validation");
        result("$" + taxAmount, invImplementation.getTaxValue(), "Tax Value Validation",
                "Invoice Validation");
        result("$" + recurringInvoiceValue, invImplementation.getChargesBalance(), "Total Invoice Value in Charges Validation",
                "Invoice Validation");
        result("$" + recurringInvoiceValue, invImplementation.getPaymentsBalance(), "Total Invoice Value in Payments Validation",
                "Invoice Validation");
        result(Utilities.currentDateWithZeroDelimiterOnDate("MM/dd/yy"), invImplementation.getInvoiceDate(), "Invoice Date Validation",
                "Invoice Validation");
        result(Utilities.currentDateWithZeroDelimiterOnDate("MM/dd/yy"), invImplementation.getDueDate(), "Due Date Validation",
                "Invoice Validation");
        result(Utilities.currentDateWithZeroDelimiterOnDate("MM/dd/yy"), invImplementation.getAppointmentDate(), "Appointment Date Validation",
                "Invoice Validation");
    }

    @And("I validate initial invoice created on invoice tab from custom schedule")
    public void validateInitialInvoice_customSchedule() throws InterruptedException {
        addSubscription = new AddSubscription();
        header = new CustomerViewDialog_Header();
        subscriptionTab = new CustomerViewDialog_SubscriptionTab();
        header.navigateTo(header.subscriptionTabInDialog);
        String iniitialIvoiceValueWithoutTax = subscriptionTab.getInitialInvoiceAmountWithoutTax_CustomSchedule();
        String initialInvoiceValue = "$" + subscriptionTab.getInitialInvoiceTotalAmount_CustomerSchedule();
        header.navigateTo(header.invoicesTabInDialog);
        result(initialInvoiceValue, invImplementation.getAccountBalance(), "Total Initial Invoice Value",
                "Initial Invoice Validation");
        invImplementation.clickInitialInvoice();
        result(initialInvoiceValue, invImplementation.getChargesBalance(), "Total Initial Invoice Value",
                "Initial Invoice Validation");
        result(initialInvoiceValue, invImplementation.getPaymentsBalance(), "Total Initial Invoice Value",
                "Initial Invoice Validation");
    }

    @And("I print report and validate totals and enter notes {string}")
    public void printAndValidateReport(String needNotes) throws InterruptedException {
        header = new CustomerViewDialog_Header();
        header.navigateTo(header.invoicesTabInDialog);
        invImplementation.clickInitialInvoice();
        invImplementation.printInvoice();
        Utilities.switchToNewWindowOpened();
        invImplementation.setAdditionalNotes(needNotes);
        invImplementation.markLetterAsSent();
        result(invoiceBalance, invImplementation.getPrintInvoicePaymentBalance(), "Total Invoice Value",
                "Initial Invoice Validation");
        result(invoiceValue, invImplementation.getAccountTotalAmountDue(), "Total Invoice Value",
                "Total Invoice Validation");
        result(invoiceCharges, invImplementation.getPrintInvoiceMainAmountDue(), "Total Invoice Value",
                "Initial Invoice Validation");
        Utilities.switchToOldWindowOpened();
    }

    private void result(String expected, String actual, String stepName, String testName) {
        if (AssertException.result(expected, actual, stepName).size() > 0) {
            Utilities.list.add(AssertException.result(expected, actual, stepName));
        }
        Reporter.status(stepName, expected, actual, testName);
    }

}
