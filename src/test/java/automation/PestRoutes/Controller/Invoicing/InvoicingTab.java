package automation.PestRoutes.Controller.Invoicing;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Schedules.ScheduleAppt;
import automation.PestRoutes.Controller.Subscriptions.AddSubscription;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.MerchantInfoTab.MarchantInfoPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.OfficeSettingsObjects;
import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreateNewInvoicePopUp;
import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.AppData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.Invoice_Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreditCard.CardOnFile;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreditCard.CreditCardConfirmationPage;

import java.io.IOException;
import java.util.Locale;

import static automation.PestRoutes.Utilities.AssertException.result;
import static java.lang.Double.parseDouble;

public class InvoicingTab extends BasePage{

    InvoiceImplementation invImplementation = new InvoiceImplementation();
    CreateNewInvoicePopUp newInvoice;
    CreateNewCustomer createCustomer;
    RoutePageInvoicing invoiceRoutesTab = new RoutePageInvoicing();
    CustomerViewDialog_Header customerCardHeader;
    Invoice_Header invoiceHeader;
    AddSubscription addSubscription;
    CustomerViewDialog_SubscriptionTab subscriptionTab;
    ACHOnFile achOnFile;
    SchedulingTab schedulingTab;
    DashboardPage userDashboard = new DashboardPage();
    Header header = new Header();
    AdminMainPage admin = new AdminMainPage();
    ScheduleAppt scheduleAppointment;
    CustomerviewDialog_AppointmentsTab appointmentsTab;
    SingleCardPayment cardPayment;
    MarchantInfoPage merchantPage;
    AppData appData = new AppData();
    AddSubscription testSubscription = new AddSubscription();

    private String treatmentAmount = "900";
    private Integer partialPaymentAmount = Integer.parseInt(treatmentAmount) / 2;
    private String successfulPartialCharge = "Successfully Charged Cash!$450.00";
    private String successfulFullCharge = "Successfully Charged Cash!$5,450.00";
    private String invoiceDate = "1";
    public static String invoiceCharges = null;
    public static String invoiceValue = null;
    public static String invoiceBalance = null;
    public static String nextExpirationDate = null;
    public static String activeGateway = null;
    public static String paymentAmount;
    public static String invoicePaymentBalance;

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
        result(initialInvoiceValue, invImplementation.getAccountBalance(), "Total Initial Invoice Value",
                "Invoice Validation");
        invImplementation.clickInvoice(appData.getData("serviceDescription", appData.generalData));
        result(initialInvoiceValue, invImplementation.getChargesBalance(), "Total Initial Invoice Value",
                "Invoice Validation");
        result(initialInvoiceValue, invImplementation.getBalanceInPayments(), "Total Initial Invoice Value",
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
        String taxAmount = String.format("%.2f", (subscriptionTab.getInitialTax()));

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
    public void generateAccountStatementReport(String reportType, String day) {
        invImplementation.clickAccountStatementReport();
        invImplementation.selectDateRange(day);
//        invImplementation.selectReportType(reportType);
        invImplementation.refreshAccountStatementReport();
    }

    @And("I validate the Beginning balance and Ending balance for a day before the invoice was created")
    public void validateBeginningEndingBalance_Yesterday_AccountStatementReport() {
        result(invImplementation.getBalance("Beginning Balance"), "$0.00", "Balance Validation",
                "Account Statement Report Validation");
        result(invImplementation.getBalance("Ending Balance"), "$0.00", "Balance Validation",
                "Account Statement Report Validation");
        Utilities.clickElement(invImplementation.responsibleFor, Utilities.ElementType.XPath);
        result(invImplementation.getBalance("Beginning Balance"), "$0.00", "Responsible Balance Validation",
                "Account Statement Report Validation");
        result(invImplementation.getBalance("Ending Balance"), "$0.00", "Responsible Balance Validation",
                "Account Statement Report Validation");
    }

    @And("Validating beginning balance for invoice created {string} of report type {string}")
    public void validateBeginningBalance_AccountStatementReport(String day, String reportType) {
        generateAccountStatementReport(reportType, day);
        result(invImplementation.getBalance("Beginning Balance"), "$0.00", "Balance Validation",
                "Account Statement Report Validation");
        Utilities.clickElement(invImplementation.responsibleFor, Utilities.ElementType.XPath);
        result(invImplementation.getBalance("Beginning Balance"), "$0.00", "Responsible Balance Validation",
                "Account Statement Report Validation");
    }

    @And("Validating invoice balance for invoice created {string} of report type {string}")
    public void validateEndingBalance_AccountStatementReport(String day, String reportType) throws InterruptedException, IOException {
        invImplementation.clickInitialInvoice();
        invoiceCharges = invImplementation.getChargesBalance();
        invoiceBalance = invImplementation.getBalanceInPayments();
        generateAccountStatementReport(reportType, day);
        result(invImplementation.getInvoiceAmount_accountStatementReport(appData.getData("serviceDescription", appData.generalData)), invoiceCharges, "Invoice Value",
                "Account Statement Report Validation");
        result(invImplementation.getInvoiceBalance_accountStatementReport(appData.getData("serviceDescription", appData.generalData)), invoiceBalance, "Invoice Value",
                "Account Statement Report Validation");
    }

    @And("Validating ending balance for invoice created {string} of report type {string}")
    public void validateInvoiceBalance_AccountStatementReport(String day, String reportType) {
        invImplementation.clickAccountSummary();
        invoiceValue = invImplementation.getAccountBalance();
        generateAccountStatementReport(reportType, day);
        result(invImplementation.getResponsibleBalance("Ending Balance"), invoiceValue, "Balance Validation",
                "Account Statement Report Validation");
        Utilities.clickElement(invImplementation.responsibleFor, Utilities.ElementType.XPath);
        result(invImplementation.getResponsibleBalance("Ending Balance"), invoiceValue, "Responsible Balance Validation",
                "Account Statement Report Validation");
    }

    @And("I validate recurring invoice created on invoice tab")
    public void validateRecurringInvoice() throws InterruptedException {
        addSubscription = new AddSubscription();
        customerCardHeader = new CustomerViewDialog_Header();
        subscriptionTab = new CustomerViewDialog_SubscriptionTab();
        customerCardHeader.navigateTo(customerCardHeader.subscriptionTabInDialog);
        String initialInvoiceValue = subscriptionTab.getInitialInvoiceValue().substring(1);
        String recurringInvoiceValue = subscriptionTab.getRecurringInvoiceValue().substring(1);
        String accountPendingBalance = Double.toString(parseDouble(recurringInvoiceValue) + parseDouble(initialInvoiceValue));
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
    @Then("I make payment with credit card on file {string}")
    public void makeCardOnFile_CCPayment(String needMessage) throws InterruptedException {
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
        Utilities.clickElement(cardOnFile.confirmAmountInputField, Utilities.ElementType.XPath);
        FindElement.elementByAttribute(cardOnFile.confirmAmountInputField, FindElement.InputType.XPath).sendKeys(confirmAmount);
        FindElement.elementByAttribute(cardOnFile.cvvCodeInputField, FindElement.InputType.XPath).sendKeys("123");
        Utilities.clickElement(cardOnFile.chargeCardButton, Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible(confirmationPage.paymentResultTitle);
        String paymentConfirmation = Utilities.getElementTextValue(confirmationPage.confirmationMessage, Utilities.ElementType.XPath);
        String expectedConfirmation = needMessage;
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
        String paymentAmount = String.format("%.2f", ((parseDouble(Utilities.getAttributeValue(cardOnFile.paymentAmountInputField, "value"))) / 10));
        FindElement.elementByAttribute(cardOnFile.paymentAmountInputField, FindElement.InputType.XPath).clear();
        FindElement.elementByAttribute(cardOnFile.paymentAmountInputField, FindElement.InputType.XPath).sendKeys(Keys.DELETE);
        FindElement.elementByAttribute(cardOnFile.paymentAmountInputField, FindElement.InputType.XPath).sendKeys(paymentAmount);
        Utilities.highLight(cardOnFile.confirmAmountInputField);
        FindElement.elementByAttribute(cardOnFile.confirmAmountInputField, FindElement.InputType.XPath).sendKeys(paymentAmount);
        FindElement.elementByAttribute(cardOnFile.cvvCodeInputField, FindElement.InputType.XPath).sendKeys("123");
        Utilities.clickElement(cardOnFile.chargeCardButton, Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible(confirmationPage.paymentResultTitle);
        String paymentConfirmation = Utilities.getElementTextValue(confirmationPage.confirmationMessage, Utilities.ElementType.XPath);
        String expectedConfirmation = "Successfully Charged Credit Card!";
        result(expectedConfirmation, paymentConfirmation, "Credit Card Confirmation", "Card on file payment");
    }

    //**Author Adi
    @Then("I make payment with ACH Account on file")
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
        Utilities.clickElement(cardOnFile.confirmAmountInputField, Utilities.ElementType.XPath);
        FindElement.elementByAttribute(cardOnFile.confirmAmountInputField, FindElement.InputType.XPath).sendKeys(confirmAmount);
        Utilities.clickElement(achOnFile.draftACHButton, Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible(confirmationPage.paymentResultTitle);
        String paymentConfirmation = Utilities.getElementTextValue(confirmationPage.confirmationMessage, Utilities.ElementType.XPath);
        String expectedConfirmation = "Successfully Charged ACH Account!";
        result(expectedConfirmation, paymentConfirmation, "ACH Confirmation", "ACH on file payment");
    }

    //**Author Adi
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
        Thread.sleep(2000);
        invoiceRoutesTab.clickAddPayment();
        invoiceHeader.navigate(invoiceHeader.achDraft);
        String confirmAmount = Utilities.getAttributeValue(cardOnFile.paymentAmountInputField, "value");
        Utilities.clickElement(cardOnFile.confirmAmountInputField, Utilities.ElementType.XPath);
        FindElement.elementByAttribute(cardOnFile.confirmAmountInputField, FindElement.InputType.XPath).sendKeys(confirmAmount);
        Utilities.clickElement(achOnFile.recordExistingButton, Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible(confirmationPage.paymentResultTitle);
        String paymentConfirmation = Utilities.getElementTextValue(confirmationPage.confirmationMessage, Utilities.ElementType.XPath);
        String expectedConfirmation = "Successfully Charged ACH Account!";
        result(expectedConfirmation, paymentConfirmation, "ACH Confirmation", "ACH on file payment");
    }

    //*** Author: F. White
    @And("I add a payment via pay option {string} in this amount {string}")
    public void addInvoicePayment(String paymentOption, String paymentAmt) throws InterruptedException {
        invoiceHeader = new Invoice_Header();
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Utilities.waitUntileElementIsVisible(invoiceRoutesTab.addNewInvoice);
        invImplementation.clickInitialInvoice();
        invoiceRoutesTab.clickAddPayment();

        //Make payment based on selected payment option
        switch (paymentOption.toUpperCase(Locale.ROOT)){
            case "CASH":
                invoiceHeader.navigateTo(invoiceHeader.cash);
                invImplementation.insertPaymentAmount(paymentAmt,paymentAmt);
                invImplementation.clickRecordPaymentButton();
                Utilities.waitUntileElementIsVisible(invImplementation.paymentResultsScreenTitle, 5);
                String paymentConfirmation = invImplementation.getPaymentConfirmationMessage();
                result(invImplementation.PAYMENT_SUCCESS_MSG_CASH, paymentConfirmation, "Cash Payment of (" +  paymentAmt +") dollars ", "Cash Payment Confirmation");
                break;
            case "CHECK":
                invoiceHeader.navigateTo(invoiceHeader.check);
                invImplementation.insertPaymentAmount(paymentAmt,paymentAmt);
                invImplementation.clickRecordCheckButton();
                Utilities.waitUntileElementIsVisible(invImplementation.paymentResultsScreenTitle, 5);
                paymentConfirmation = invImplementation.getPaymentConfirmationMessage();
                result(invImplementation.PAYMENT_SUCCESS_MSG_CHECK, paymentConfirmation, "Check Payment of (" +  paymentAmt +") dollars ", "Check Payment Confirmation");
                break;
            case "COUPON":
                invoiceHeader.navigateTo(invoiceHeader.coupon);
                invImplementation.insertPaymentAmount(paymentAmt,paymentAmt);
                invImplementation.clickRecordCouponButton();
                Utilities.waitUntileElementIsVisible(invImplementation.paymentResultsScreenTitle, 5);
                paymentConfirmation = invImplementation.getPaymentConfirmationMessage();
                result(invImplementation.PAYMENT_SUCCESS_MSG_COUPON, paymentConfirmation, "Coupon Payment of (" +  paymentAmt +") dollars ", "Coupon Payment Confirmation");
                break;
            default:
                Assert.fail("*************** Invalid Payment Option: " + paymentOption);
        }
    }

    //*** Author: F. White
    @Then("I validate notes {string}, transAmt {string}, and transDate {string} fields can be edit on payment")
    public void updatePaymentFields(String paymentNotes, String transactionAmt, String transactionDate) throws InterruptedException {
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Utilities.waitUntileElementIsVisible(invoiceRoutesTab.addNewInvoice,5);
        invImplementation.clickInitialInvoice();
        invImplementation.clickMostRecentPayment();
        invImplementation.loadPaymentDetails();

        //Update the following payment fields on the invoice
        //update Transaction Amount
        invImplementation.setPaymentTransactionAmount(transactionAmt);

        //update Customer Payment Notes
        invImplementation.setPaymentNotes( paymentNotes);

        //update Transaction Amount
        invImplementation.setPaymentTransactionDate(transactionDate);

        //Save updates
        invImplementation.clickSaveRedistribute();

        // Verify updates saved successfully
        Assert.assertEquals(transactionAmt, invImplementation.getPaymentTransactionAmount(), "Cash Payment Update Confirmation");
        Assert.assertEquals(transactionDate, invImplementation.getPaymentTransactionDate(),"Transaction Date Update Confirmation");
        Assert.assertEquals(paymentNotes, invImplementation.getPaymentNotes(),"Customer Payment Notes Update Confirmation");
    }

    @Then("I validate the invoice generates with an invoice number")
    public void validateInvoiceNumber() throws InterruptedException {
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Utilities.waitUntileElementIsVisible(invoiceRoutesTab.addNewInvoice,5);

        //Load the most recently added invoice
        invImplementation.clickMostRecentInvoice();

        //Validate the generated invoice has an invoice number assigned
        Assert.assertTrue(StringUtils.isNumeric(invImplementation.getMostRecentInvoiceNumber()), "*******The Invoice Number is  Invalid or Missing!");
    }

    @Then("I validate the taxable option on invoice")
    public void validateTaxableOptionOnInvoice() throws InterruptedException {
        double zeroTaxAmt = 0.0;
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Utilities.waitUntileElementIsVisible(invoiceRoutesTab.addNewInvoice,5);

        //Load the most recently added invoice
        invImplementation.clickMostRecentInvoice();

        //Validate "Not Taxable" Option
        invImplementation.selectTaxableOption("Not Taxable");
        double displayedTaxAmt = parseDouble(invImplementation.getCalTaxValue());
        Assert.assertTrue((Double.compare(displayedTaxAmt, zeroTaxAmt) == 0 ) , "*************Tax Amount displayed for the 'Not Taxable' option is incorrect!");

        //Validate "Not Taxable" Option
        invImplementation.selectTaxableOption("Taxable");
        displayedTaxAmt = parseDouble(invImplementation.getCalTaxValue());
        Assert.assertTrue((Double.compare(displayedTaxAmt, zeroTaxAmt) > 0 ), "*************Tax Amount displayed for the 'Taxable' option is incorrect!");
    }

    @And("I freeze the subscription in category {string} with reason {string}")
    public void freezeSubscription(String cancellationCategory, String cancellationReason) {
        customerCardHeader = new CustomerViewDialog_Header();
        subscriptionTab = new CustomerViewDialog_SubscriptionTab();

        customerCardHeader.navigateTo(customerCardHeader.subscriptionTabInDialog);
        subscriptionTab.clickActivateDeActivateButton();
        subscriptionTab.selectCancellationCategory(cancellationCategory);
        subscriptionTab.setCancelSubscriptionNotes(cancellationReason);
        subscriptionTab.clickFreezeSubscriptionButton();
        customerCardHeader.clickSaveButton();
    }//freezeSubscription

    @And("I add an annual subscription with Service Type {string} and an Expiration Date")
    public void addAnnualSubscription(String strServiceType) {
        customerCardHeader = new CustomerViewDialog_Header();
        subscriptionTab = new CustomerViewDialog_SubscriptionTab();
        customerCardHeader.navigateTo(customerCardHeader.subscriptionTabInDialog);

        //Add an Annual Subscription with an Expiration Date
        subscriptionTab.clickNewSubscription();
        subscriptionTab.setSubscriptionExpirationDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
        subscriptionTab.selectRecurringServiceType(strServiceType);
        subscriptionTab.selectInitialInvoice("After Initial Completion");

        //Save the Subscription
        customerCardHeader.clickSaveButton();
    }//addAnnualSubscription()


    @And("I schedule and complete an appointment for subscription with Service Type {string}")
    public void scheduleAndCompleteServiceAppointment(String serviceType) {
        customerCardHeader = new CustomerViewDialog_Header();
        createCustomer = new CreateNewCustomer();
        //routePg = new RoutePage();
        scheduleAppointment = new ScheduleAppt();
        appointmentsTab = new CustomerviewDialog_AppointmentsTab();
        String customerFullName =createCustomer.getCustomerFullName();

        //Create a Route on Today's Schedule
        schedulingTab = userDashboard.goToSchedulingComponent();
        schedulingTab.addScheduleDateToProperties();
        schedulingTab.clickScheduleDay();
     //   routePg.addGroup();
      //  routePg.addRoutesByQuantity("1");

        //Schedule The Appointment
        header.searchCustomerWithName(customerFullName);
        subscriptionTab = customerCardHeader.goToSubscriptionTab();
        scheduleAppointment.scheduleAppointmentOnRoute(serviceType,appData.getData("timeSlot", appData.generalData));

        //Complete The Appointment
        header.searchCustomerWithName(customerFullName);
        appointmentsTab = customerCardHeader.goToAppointmentsTab();
        customerCardHeader.navigateTo(customerCardHeader.appointmentsTabInDialog);
        appointmentsTab.clickScheduledService(serviceType);
        appointmentsTab.clickStatusButton();
        appointmentsTab.clickSaveAndCompleteButton();
    }//scheduleAndCompleteServiceAppointment()

    @And("I process an one-time Single Use Card {string} or {string} for payment limited to a subscription")
    public void processSingleUseCardPaymentWithDistribDetails(String creditCardNumber, String nmiCreditCardNumber) {

        //Reload Customer Card and make payment
        customerCardHeader = new CustomerViewDialog_Header();
        createCustomer = new CreateNewCustomer();
        String customerFullName = createCustomer.getCustomerFullName();
        header.searchCustomerWithName(customerFullName);
        String  cardNum = creditCardNumber;

        if(activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_NMI))
            cardNum= nmiCreditCardNumber;

        makeCCPaymentToAdvanceExpirationDateWhenJobPoolIsUnchecked(cardNum);
    }//processSingleUseCardPaymentLimitedToSubscription

    public void makeCCPaymentToAdvanceExpirationDateWhenJobPoolIsUnchecked(String creditCardNum) {
        invoiceHeader = new Invoice_Header();
        customerCardHeader = new CustomerViewDialog_Header();
        cardPayment = new SingleCardPayment();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Utilities.waitUntileElementIsVisible(invoiceRoutesTab.addNewInvoice);

        invImplementation.clickInitialInvoice();
        invoiceRoutesTab.clickAddPayment();
        invoiceHeader.navigateTo(invoiceHeader.creditCard);

        //Enter Full Balance Due
       String paymentAmount = invImplementation.getPaymentAmount();
       invImplementation.typeConfirmationAmount(paymentAmount);

        //Limit Payment to Subscription
        invImplementation.selectLimitedToSubscription();

        //Update Billing Address
        invImplementation.typeAddress("10001 Loop Ln.");

        //Check "Expiration Date" Option
        nextExpirationDate = invImplementation.getExpirationDate();

        //Un-Check the "Send to Job Pool" Option
         invImplementation.uncheckSendToJobPoolBox();

        //charge Single Use Card full amount due
        if (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_BRAINTREE))
            cardPayment.chargeSignleBrainTreeCc(creditCardNum);
        else if (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_ELEMENT))
            cardPayment.chargeSingleElementCc(creditCardNum);
        else if (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_SPREEDLY))
            cardPayment.chargeSignleSpreedlyCc(creditCardNum);
        else if (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_NMI))
            cardPayment.chargeSingleNmiCc(creditCardNum);
        else if (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_PESTROUTES_PAYMENTS))
            cardPayment.chargeSinglePayrixCc(creditCardNum);
    }//makeCardPaymentToAdvanceExpirationDate()

    @Then("I validate the Expiration Date on the subscription has advanced")
    public void verifyExpirationDatAdvanced() {
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.subscriptionTabInDialog);
        Utilities.waitUntileElementIsVisible(subscriptionTab.newSubscriptionButton,5);

        Assert.assertEquals(subscriptionTab.getSubscriptionExpirationDate(),nextExpirationDate,"Subscription Expiration Date Advancement Validation Failed!!! ");
      }//verifyExpirationDatAdvanced()

    @Then("I validate the subscription's status is {string}")
    public void verifySubscriptionStatus(String expectedStatus) {
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.subscriptionTabInDialog);
        Utilities.waitUntileElementIsVisible(subscriptionTab.newSubscriptionButton,5);

        String currentStatus = subscriptionTab.getSubscriptionStatus();
        Assert.assertEquals(currentStatus.toUpperCase(Locale.ROOT),expectedStatus.toUpperCase(Locale.ROOT),"Subscription Status Validation Fail!!!");
    }//verifyExpirationDatAdvanced()

    @Given("I retrieve the merchant's configured gateway")
    public String retrieveConfiguredGateway() {
        header.navigateTo(header.adminTab);
        admin.navigateTo(admin.preferences);
        merchantPage = new MarchantInfoPage();
        OfficeSettingsObjects officeSettings = new OfficeSettingsObjects();
        Utilities.clickElement(officeSettings.merchantInfo, Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible(officeSettings.lblDefaultVaultSettings,2);

        String configuredGateWay = merchantPage.getDefaultCreditCardGateway();
        activeGateway = configuredGateWay;

        System.out.println("***************** retrieveConfiguredGateway(): " + activeGateway);
        return configuredGateWay;
    }//retrieveConfiguredGateway()

    @When("I Generate A Stand Alone Invoice")
    public void automateGeneratingStandAloneInvoice() throws InterruptedException {
        RoutePageInvoicing userOnInvoicesTab = new RoutePageInvoicing();
        CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
        CreateNewInvoicePopUp userOnNewInvoicePopUp = new CreateNewInvoicePopUp();

        sameUser.goToInvoicesTab();
        userOnInvoicesTab.clickNewInvoice();
        userOnNewInvoicePopUp.typeSubTotal(testSubscription.totalInitialInvoice);
        userOnNewInvoicePopUp.selectServiceType("Automation Renewal");
        userOnNewInvoicePopUp.clickCreateButton();
        invoicePaymentBalance = userOnInvoicesTab.getPaymentBalance();
    }

    @And("I Pay Off The Stand Alone Invoice")
    public void automatePayingOffStandAloneInvoice() throws InterruptedException {
        RoutePageInvoicing userOnInvoicesTab = new RoutePageInvoicing();
        CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
        Invoice_Header userSelectsPayment = new Invoice_Header();
        InvoiceImplementation userMakesPayment = new InvoiceImplementation();

        userOnInvoicesTab.addPayment();
        userSelectsPayment.clickCash();
        paymentAmount = userMakesPayment.getPaymentAmount();
        userMakesPayment.typeConfirmationAmount(paymentAmount);
        userMakesPayment.clickRecordPaymentButton();
        sameUser.clickXButton();
        sameUser.clickSaveChangesButton();
    }

    @And("I Pay Off A Non Stand Alone Invoice")
    public void automatePayingOffNonStandAloneInvoice() throws InterruptedException {
        CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
        Invoice_Header userSelectsPayment = new Invoice_Header();
        RoutePageInvoicing userOnInvoicesTab = new RoutePageInvoicing();
        InvoiceImplementation userMakesPayment = new InvoiceImplementation();

        sameUser.goToInvoicesTab();
        userOnInvoicesTab.addPayment();
        userSelectsPayment.clickCash();
        paymentAmount = userMakesPayment.getPaymentAmount();
        userMakesPayment.typeConfirmationAmount(paymentAmount);
        userMakesPayment.clickRecordPaymentButton();
        sameUser.clickXButton();
        sameUser.clickSaveChangesButton();
    }
}