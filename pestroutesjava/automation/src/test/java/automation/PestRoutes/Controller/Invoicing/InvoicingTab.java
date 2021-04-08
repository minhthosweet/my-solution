package automation.PestRoutes.Controller.Invoicing;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Subscriptions.AddSubscription;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreateNewInvoicePopUp;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.Invoice_Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreditCard.CardOnFile;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreditCard.CreditCardConfirmationPage;
import automation.PestRoutes.Utilities.Reporter;

import java.io.IOException;

import static automation.PestRoutes.Utilities.AssertException.result;

public class InvoicingTab extends AppData {

    InvoiceImplementation invImplementation = new InvoiceImplementation();
    CreateNewInvoicePopUp newInvoice;
    CreateNewCustomer createCustomer;
    RoutePageInvoicing invoiceRoutesTab = new RoutePageInvoicing();
    CustomerViewDialog_Header customerCardHeader;
    Invoice_Header invoiceHeader;
    AddSubscription addSubscription;
    CustomerViewDialog_SubscriptionTab subscriptionTab;
    ACHOnFile achOnFile;

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

    //**Author Aarbi**
    @And("I create standalone service invoice {string}")
    public void createStandAloneServiceInvoice_CurrentDate(String needAmount) throws InterruptedException {
        newInvoice = new CreateNewInvoicePopUp();
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        invoiceRoutesTab.clickAddNewInvoice(invoiceRoutesTab.addNewInvoice);
        newInvoice.set(newInvoice.dateField, Utilities.currentDate("MM/dd/yyyy"));
        newInvoice.set(newInvoice.amountInputField, needAmount);
        newInvoice.select(newInvoice.serviceTypeDropdown, "Automation Renewal");
        newInvoice.click(newInvoice.createButton);
    }

    // Add a new invoice to the customer
    public void addNewInvoice(String date) throws Exception {
        createCustomer = new CreateNewCustomer();
        createCustomer.createCustomerWithEmail();
        createCustomer.searchCustomer();
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
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
        invImplementation.clickRecordPayment();
        Reporter.status("", successfulPartialCharge, invImplementation.getSuccessfulChargeAmount(), "Charged successfully");
        Reporter.status("", "PARTIALLY PAID", invImplementation.checkPaymentStatus(),
                "The payment status is PARTIALLY PAID");
        invImplementation.invoiceAccountSummaryClick();
    }

    // Assert - Full Payment
    private void assertFullCharge() {
        invImplementation.insertPaymentAmount(Integer.toString(invImplementation.getPaymentBalance()),
                Integer.toString(invImplementation.getPaymentBalance()));
        invImplementation.clickRecordPayment();
        Reporter.status("", successfulFullCharge, invImplementation.getSuccessfulChargeAmount(), "Charged successfully");
        Reporter.status("", "FULLY PAID", invImplementation.checkPaymentStatus(), "The payment status is FULLY PAID");
        invImplementation.invoiceAccountSummaryClick();
    }

    @When("I navigate to Invoice Tab")
    public void navigateToInvoiceTab() throws InterruptedException {
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
    }

    @And("I validate invoice")
    public void validateInvoice() throws IOException, InterruptedException {
        addSubscription = new AddSubscription();
        customerCardHeader = new CustomerViewDialog_Header();
        subscriptionTab = new CustomerViewDialog_SubscriptionTab();
        customerCardHeader.navigateTo(customerCardHeader.subscriptionTabInDialog);
        String initialInvoiceValue = subscriptionTab.getInitialInvoiceValue();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        result(initialInvoiceValue, "$" + invImplementation.getAccountBalance(), "Total Initial Invoice Value",
                "Invoice Validation");
        invImplementation.clickInvoice(getData("serviceDescription", generalData));
        result(initialInvoiceValue, "$" + invImplementation.getChargesBalance(), "Total Initial Invoice Value",
                "Invoice Validation");
        result(initialInvoiceValue, "$" + invImplementation.getBalanceInPayments(), "Total Initial Invoice Value",
                "Invoice Validation");
    }

    @And("I validate initial invoice created on invoice tab")
    public void validateInitialInvoice() throws InterruptedException {
        addSubscription = new AddSubscription();
        customerCardHeader = new CustomerViewDialog_Header();
        subscriptionTab = new CustomerViewDialog_SubscriptionTab();
        customerCardHeader.navigateTo(customerCardHeader.subscriptionTabInDialog);
        String serviceType = subscriptionTab.getServiceType();
        String initialInvoiceValue = subscriptionTab.getInitialInvoiceValue();
        String initialSubTotal = Double.toString(subscriptionTab.getInitialSubTotal());
        String taxAmount = Double.toString(subscriptionTab.getInitialTax());

        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
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
        result(initialInvoiceValue, invImplementation.getBalanceInPayments(), "Total Invoice Value in Payments Validation",
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
        invoiceBalance = invImplementation.getBalanceInPayments();
        generateAccountStatementReport(reportType, day);
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
        customerCardHeader = new CustomerViewDialog_Header();
        subscriptionTab = new CustomerViewDialog_SubscriptionTab();
        customerCardHeader.navigateTo(customerCardHeader.subscriptionTabInDialog);
        String initialInvoiceValue = subscriptionTab.getInitialInvoiceValue().substring(1, subscriptionTab.getInitialInvoiceValue().length());
        String recurringInvoiceValue = subscriptionTab.getRecurringInvoiceValue().substring(1, subscriptionTab.getRecurringInvoiceValue().length());
        String accountPendingBalance = Double.toString(Double.parseDouble(recurringInvoiceValue) + Double.parseDouble(initialInvoiceValue));
        String recurringSubTotal = Double.toString(subscriptionTab.getRecurringSubTotal());
        String taxAmount = Double.toString(subscriptionTab.getRecurringTax());
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        result("$" + accountPendingBalance, invImplementation.getAccountBalance(), "Total Invoice Value Validation",
                "Invoice Validation");
        invImplementation.clickRecurringInvoice(recurringInvoiceValue);

        result(Double.toString(addSubscription.recurringQuote), invImplementation.getServiceCostBeforeTax().substring(0, invImplementation.getServiceCostBeforeTax().length() - 1), "Service Cost Before Tax Validation",
                "Invoice Validation");
        result(Double.toString(subscriptionTab.getInitialService_NewTicketItemPrice(addSubscription.ticketItem)), invImplementation.getAddOnValue(addSubscription.ticketItem), "Item Cost Validation",
                "Invoice Validation");
        result("$" + recurringSubTotal, invImplementation.getSubTotalValue(), "Sub Total Value Validation",
                "Invoice Validation");
        result("$" + taxAmount, invImplementation.getTaxValue(), "Tax Value Validation",
                "Invoice Validation");
        result("$" + recurringInvoiceValue, invImplementation.getChargesBalance(), "Total Invoice Value in Charges Validation",
                "Invoice Validation");
        result("$" + recurringInvoiceValue, invImplementation.getBalanceInPayments(), "Total Invoice Value in Payments Validation",
                "Invoice Validation");
        result(Utilities.currentDate("MM/dd/yy").replaceAll("0", ""), invImplementation.getInvoiceDate().replaceAll("0", ""), "Invoice Date Validation",
                "Invoice Validation");
        result(Utilities.currentDate("MM/dd/yy").replaceAll("0", ""), invImplementation.getDueDate().replaceAll("0", ""), "Due Date Validation",
                "Invoice Validation");
        result(Utilities.currentDate("MM/dd/yy").replaceAll("0", ""), invImplementation.getAppointmentDate().replaceAll("0", ""), "Appointment Date Validation",
                "Invoice Validation");
    }

    @And("I validate initial invoice created on invoice tab from custom schedule")
    public void validateInitialInvoice_customSchedule() throws InterruptedException {
        addSubscription = new AddSubscription();
        customerCardHeader = new CustomerViewDialog_Header();
        subscriptionTab = new CustomerViewDialog_SubscriptionTab();
        customerCardHeader.navigateTo(customerCardHeader.subscriptionTabInDialog);
        String initialInvoiceValueWithoutTax = subscriptionTab.getInitialInvoiceAmountWithoutTax_CustomSchedule();
        String initialInvoiceValue = "$" + subscriptionTab.getInitialInvoiceTotalAmount_CustomerSchedule();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        result(initialInvoiceValue, invImplementation.getAccountBalance(), "Total Initial Invoice Value",
                "Initial Invoice Validation");
        invImplementation.clickInitialInvoice();
        result(initialInvoiceValue, invImplementation.getChargesBalance(), "Total Initial Invoice Value",
                "Initial Invoice Validation");
        result(initialInvoiceValue, invImplementation.getBalanceInPayments(), "Total Initial Invoice Value",
                "Initial Invoice Validation");
    }

    @And("I print report and validate totals and enter notes {string}")
    public void printAndValidateReport(String needNotes) throws InterruptedException {
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        invImplementation.clickInitialInvoice();
        invImplementation.printInvoice();
        Utilities.switchToNewWindowOpened();
        invImplementation.setAdditionalNotes(needNotes);
        invImplementation.markLetterAsSent();
        result(invoiceBalance.replace("$", ""), invImplementation.getPrintInvoicePaymentBalance().replace("$", ""), "Total Invoice Print Value",
                "Initial Invoice Validation");
        result(invoiceValue.replace("$", ""), invImplementation.getAccountTotalAmountDue().replace("$", ""), "Total Invoice Account Value",
                "Total Invoice Validation");
        result(invoiceCharges.replace("$", ""), invImplementation.getPrintInvoiceMainAmountDue().replace("$", ""), "Total Main Invoice Value",
                "Initial Invoice Validation");
        Utilities.switchToOldWindowOpened();
    }

    //**Author Aarbi
    @Then("I make payment with credit card on file")
    public void makeCardOnFile_CCPayment() throws InterruptedException {
        invoiceHeader = new Invoice_Header();
        CardOnFile cardOnFile = new CardOnFile();
        CreditCardConfirmationPage confirmationPage = new CreditCardConfirmationPage();
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Utilities.waitUntileElementIsVisible(invoiceRoutesTab.addNewInvoice);
        invImplementation.clickInitialInvoice();
        invoiceRoutesTab.clickAddPayment();
        invoiceHeader.navigate(invoiceHeader.creditCard);
        Utilities.waitUntileElementIsVisible(cardOnFile.chargeCardButton);
        String confirmAmount = Utilities.getAttributeValue(cardOnFile.paymentAmountInputField, "value");
        FindElement.elementByAttribute(cardOnFile.confirmAmountInputField, FindElement.InputType.XPath).sendKeys(confirmAmount);
        FindElement.elementByAttribute(cardOnFile.cvvCodeInputField, FindElement.InputType.XPath).sendKeys("123");
        Utilities.clickElement(cardOnFile.chargeCardButton, Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible(confirmationPage.paymentResultTitle);
        String paymentConfirmation = Utilities.getElementTextValue(confirmationPage.confirmationMessage, Utilities.ElementType.XPath);
        String expectedConfirmation = "Successfully Charged Credit Card!";
        result(expectedConfirmation, paymentConfirmation, "Credit Card Confirmation", "Card on file payment");
    }

    //Author: Aditya
    @Then("I make partial payment with credit card on file")
    public void makeCardOnFile_PartialCCPayment() throws InterruptedException {
        invoiceHeader = new Invoice_Header();
        CardOnFile cardOnFile = new CardOnFile();
        CreditCardConfirmationPage confirmationPage = new CreditCardConfirmationPage();
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Utilities.waitUntileElementIsVisible(invoiceRoutesTab.addNewInvoice);
        invImplementation.clickInitialInvoice();
        invoiceRoutesTab.clickAddPayment();
        invoiceHeader.navigate(invoiceHeader.creditCard);
        Utilities.waitUntileElementIsVisible(cardOnFile.chargeCardButton);
        String paymentAmount = String.valueOf((int) (Double.parseDouble(Utilities.getAttributeValue(cardOnFile.paymentAmountInputField, "value"))) / 10);
        FindElement.elementByAttribute(cardOnFile.paymentAmountInputField, FindElement.InputType.XPath).clear();
        FindElement.elementByAttribute(cardOnFile.paymentAmountInputField, FindElement.InputType.XPath).sendKeys(paymentAmount);
        FindElement.elementByAttribute(cardOnFile.confirmAmountInputField, FindElement.InputType.XPath).sendKeys(paymentAmount);
        FindElement.elementByAttribute(cardOnFile.cvvCodeInputField, FindElement.InputType.XPath).sendKeys("123");
        Utilities.clickElement(cardOnFile.chargeCardButton, Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible(confirmationPage.paymentResultTitle);
        String paymentConfirmation = Utilities.getElementTextValue(confirmationPage.confirmationMessage, Utilities.ElementType.XPath);
        String expectedConfirmation = "Successfully Charged Credit Card!";
        result(expectedConfirmation, paymentConfirmation, "Credit Card Confirmation", "Card on file payment");
    }

    //**Author Adi
    @Then("I make payment with Use Account on file")
    public void makePayment_ACHOnFile() throws InterruptedException {
        invoiceHeader = new Invoice_Header();
        CardOnFile cardOnFile = new CardOnFile();
        achOnFile = new ACHOnFile();
        CreditCardConfirmationPage confirmationPage = new CreditCardConfirmationPage();
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Utilities.waitUntileElementIsVisible(invoiceRoutesTab.addNewInvoice);
        invImplementation.clickInitialInvoice();
        invoiceRoutesTab.clickAddPayment();
        invoiceHeader.navigate(invoiceHeader.achDraft);
        String confirmAmount = Utilities.getAttributeValue(cardOnFile.paymentAmountInputField, "value");
        FindElement.elementByAttribute(cardOnFile.confirmAmountInputField, FindElement.InputType.XPath).sendKeys(confirmAmount);
        Utilities.clickElement(achOnFile.draftACHButton, Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible(confirmationPage.paymentResultTitle);
        String paymentConfirmation = Utilities.getElementTextValue(confirmationPage.confirmationMessage, Utilities.ElementType.XPath);
        String expectedConfirmation = "Successfully Charged ACH Account!";
        result(expectedConfirmation, paymentConfirmation, "ACH Confirmation", "ACH on file payment");
    }

    @Then("I make payment with Existing Transaction ACH on file")
    public void makePayment_ExistingTransactionACHOnFile() throws InterruptedException {
        invoiceHeader = new Invoice_Header();
        CardOnFile cardOnFile = new CardOnFile();
        achOnFile = new ACHOnFile();
        CreditCardConfirmationPage confirmationPage = new CreditCardConfirmationPage();
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Utilities.waitUntileElementIsVisible(invoiceRoutesTab.addNewInvoice);
        invImplementation.clickInitialInvoice();
        invoiceRoutesTab.clickAddPayment();
        invoiceHeader.navigate(invoiceHeader.achDraft);
        String confirmAmount = Utilities.getAttributeValue(cardOnFile.paymentAmountInputField, "value");
        FindElement.elementByAttribute(cardOnFile.confirmAmountInputField, FindElement.InputType.XPath).sendKeys(confirmAmount);
        Utilities.clickElement(achOnFile.recordExistingButton, Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible(confirmationPage.paymentResultTitle);
        String paymentConfirmation = Utilities.getElementTextValue(confirmationPage.confirmationMessage, Utilities.ElementType.XPath);
        String expectedConfirmation = "Successfully Charged ACH Account!";
        result(expectedConfirmation, paymentConfirmation, "ACH Confirmation", "ACH on file payment");
    }
}
