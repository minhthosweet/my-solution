package automation.PestRoutes.Controller.Invoicing;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Schedules.ScheduleAppt;
import automation.PestRoutes.Controller.Subscriptions.AddSubscription;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.MerchantInfoTab.MarchantInfoPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.OfficeSettingsObjects;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.Billing.BillingModule.BillingModule;
import automation.PestRoutes.PageObject.CustomerOverview.*;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreateNewInvoicePopUp;
import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.RoutePage.RoutePage;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Data.*;
import automation.PestRoutes.Utilities.Legacy;
import automation.PestRoutes.Utilities.Report.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.Invoice_Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreditCard.CardOnFile;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreditCard.CreditCardConfirmationPage;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import static automation.PestRoutes.Utilities.Report.AssertException.result;
import static automation.PestRoutes.Utilities.Utilities.*;
import static java.lang.Double.parseDouble;

public class InvoicingTab extends AppData {

    SoftAssert softAssert = new SoftAssert();
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
    BillingModule billingComponent;
    ScheduleAppt scheduleAppointment;
    CustomerviewDialog_AppointmentsTab appointmentsTab;
    SingleCardPayment cardPayment;
    MarchantInfoPage merchantPage;
    PreferencesPage preferencesPage;
    OfficeSettingsObjects officeSettings;
    BillingPage billingTab = new BillingPage();
    Header userOnHeader = new Header();
    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    CustomerViewDialog_Admin userOnAdminTab = new CustomerViewDialog_Admin();

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
    public static String invoiceSubTotal;
    public static String invoicePaymentBalance;
    public static String applyToFirstInvoiceNum;
    public static String addonInvoiceNum;
    public static String linkedCustomer1FullName = null;
    public static String linkedCustomer2FullName = null;
    public static ArrayList<String> generatedInvoiceIDsList = null;

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
        newInvoice.set(newInvoice.dateField, GetDate.currentDate("MM/dd/yyyy"));
        newInvoice.set(newInvoice.amountInputField, needAmount);
        newInvoice.select(newInvoice.serviceTypeDropdown, "Automation Renewal");
        newInvoice.click(newInvoice.createButton);
    }
    @And("I create standalone service invoice {string} for service {string}")
    public void createStandAloneServiceInvoice(String needAmount, String requestedService){
        newInvoice = new CreateNewInvoicePopUp();
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        invoiceRoutesTab.clickAddNewInvoice(invoiceRoutesTab.addNewInvoice);
        newInvoice.set(newInvoice.dateField, GetDate.currentDate("MM/dd/yyyy"));
        newInvoice.set(newInvoice.amountInputField, needAmount);
        newInvoice.select(newInvoice.serviceTypeDropdown, requestedService);
        newInvoice.click(newInvoice.createButton);
    }

   public void createStandAloneServiceInvoice(String invoiceDate, String needAmount, String requestedService){
        invoiceRoutesTab.clickAddNewInvoice(invoiceRoutesTab.addNewInvoice);
        newInvoice.set(newInvoice.dateField, invoiceDate);
        newInvoice.set(newInvoice.amountInputField, needAmount);
        newInvoice.select(newInvoice.serviceTypeDropdown, requestedService);
        newInvoice.click(newInvoice.createButton);
    }

    public void createStandAloneServiceInvoiceWithAddonAndMarkConsolidate(String invoiceDate, String needAmount, String requestedService, String addonItem){
        invImplementation.createStandAloneServiceInvoice (invoiceDate,needAmount,requestedService);
        Utilities.isPresent(invoiceRoutesTab.invoiceScreenTitle);

        //Mark invoice "Eligible for Consolidation" and add a ticket add-ons
        invImplementation.checkEligibleForConsolidation();
        invoiceRoutesTab.invoiceDetails();
        invoiceRoutesTab.selectAvailableItems(addonItem);
    }//createStandAloneServiceInvoiceWithAddonAndMarkConsolidate()

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
        invImplementation.clickInvoice(getData("serviceDescription", generalData));
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
        result(GetDate.currentDate("MM/dd/yy").replaceAll("0", ""), invImplementation.getInvoiceDate().replaceAll("0", ""), "Invoice Date Validation",
                "Invoice Validation");
        result(GetDate.currentDate("MM/dd/yy").replaceAll("0", ""), invImplementation.getDueDate().replaceAll("0", ""), "Due Date Validation",
                "Invoice Validation");
        if (serviceType.equals("After Agreement Signed")) {
            result("N/A", "N/A", "Appointment Date Validation",
                    "Invoice Validation");
        } else {
            result(GetDate.currentDate("MM/dd/yy").replaceAll("0", ""), invImplementation.getAppointmentDate().replaceAll("0", ""), "Appointment Date Validation",
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
        Legacy.clickElement(invImplementation.responsibleFor);
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
        Legacy.clickElement(invImplementation.responsibleFor);
        result(invImplementation.getBalance("Beginning Balance"), "$0.00", "Responsible Balance Validation",
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
    public void validateInvoiceBalance_AccountStatementReport(String day, String reportType) {
        invImplementation.clickAccountSummary();
        invoiceValue = invImplementation.getAccountBalance();
        generateAccountStatementReport(reportType, day);
        result(invImplementation.getResponsibleBalance("Ending Balance"), invoiceValue, "Balance Validation",
                "Account Statement Report Validation");
        Legacy.clickElement(invImplementation.responsibleFor);
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
        //String taxAmount = Double.toString(subscriptionTab.getRecurringTax());
        String taxAmount = String.format("%.2f", subscriptionTab.getRecurringTax());
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
        result(GetDate.currentDate("MM/dd/yy").replaceAll("0", ""), invImplementation.getInvoiceDate().replaceAll("0", ""), "Invoice Date Validation",
                "Invoice Validation");
        result(GetDate.currentDate("MM/dd/yy").replaceAll("0", ""), invImplementation.getDueDate().replaceAll("0", ""), "Due Date Validation",
                "Invoice Validation");
        result(GetDate.currentDate("MM/dd/yy").replaceAll("0", ""), invImplementation.getAppointmentDate().replaceAll("0", ""), "Appointment Date Validation",
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
        GetWebDriver.switchToNewWindowOpened();
        invImplementation.setAdditionalNotes(needNotes);
        invImplementation.markLetterAsSent();
        result(invoiceBalance.replace("$", ""), invImplementation.getPrintInvoicePaymentBalance().replace("$", ""), "Total Invoice Print Value",
                "Initial Invoice Validation");
        result(invoiceValue.replace("$", ""), invImplementation.getAccountTotalAmountDue().replace("$", ""), "Total Invoice Account Value",
                "Total Invoice Validation");
        result(invoiceCharges.replace("$", ""), invImplementation.getPrintInvoiceMainAmountDue().replace("$", ""), "Total Main Invoice Value",
                "Initial Invoice Validation");
        GetWebDriver.switchToOldWindowOpened();
    }

    //**Author Aarbi
    @Then("I make payment with credit card on file {string}")
    public void makeCardOnFile_CCPayment(String needMessage){
        invoiceHeader = new Invoice_Header();
        CardOnFile cardOnFile = new CardOnFile();
        CreditCardConfirmationPage confirmationPage = new CreditCardConfirmationPage();
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.waitVisible(invoiceRoutesTab.addNewInvoice);
        invImplementation.clickInitialInvoice();
        invoiceRoutesTab.clickAddPayment();
        invoiceHeader.navigate(invoiceHeader.creditCard);
        Legacy.waitVisible(cardOnFile.chargeCardButton);
        String confirmAmount = Legacy.getAttribute(cardOnFile.paymentAmountInputField, "value");
        Legacy.clickElement(cardOnFile.confirmAmountInputField);
        Legacy.locate(cardOnFile.confirmAmountInputField).sendKeys(confirmAmount);
        Legacy.locate(cardOnFile.cvvCodeInputField).sendKeys("123");
        Legacy.clickElement(cardOnFile.chargeCardButton);
        Legacy.waitVisible(confirmationPage.paymentResultTitle);
        String paymentConfirmation = Legacy.getElementTextValue(confirmationPage.confirmationMessage);
        String expectedConfirmation = needMessage;
        result(expectedConfirmation, paymentConfirmation, "Credit Card Confirmation", "Card on file payment");
    }

    //Author: Aditya
    @Then("I make partial payment with credit card on file")
    public void makeCardOnFile_PartialCCPayment() {
        invoiceHeader = new Invoice_Header();
        CardOnFile cardOnFile = new CardOnFile();
        CreditCardConfirmationPage confirmationPage = new CreditCardConfirmationPage();
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.waitVisible(invoiceRoutesTab.addNewInvoice);
        invImplementation.clickInitialInvoice();
        invoiceRoutesTab.clickAddPayment();
        invoiceHeader.navigate(invoiceHeader.creditCard);
        Legacy.waitVisible(cardOnFile.chargeCardButton);
        String paymentAmount = String.format("%.2f", ((parseDouble(Legacy.getAttribute(cardOnFile.paymentAmountInputField, "value"))) / 10));
        Legacy.locate(cardOnFile.paymentAmountInputField).clear();
        Legacy.locate(cardOnFile.paymentAmountInputField).sendKeys(Keys.DELETE);
        Legacy.locate(cardOnFile.paymentAmountInputField).sendKeys(paymentAmount);
        Legacy.highLight(cardOnFile.confirmAmountInputField);
        Legacy.locate(cardOnFile.confirmAmountInputField).sendKeys(paymentAmount);
        Legacy.locate(cardOnFile.cvvCodeInputField).sendKeys("123");
        Legacy.clickElement(cardOnFile.chargeCardButton);
        Legacy.waitVisible(confirmationPage.paymentResultTitle);
        String paymentConfirmation = Legacy.getElementTextValue(confirmationPage.confirmationMessage);
        String expectedConfirmation = "Successfully Charged Credit Card!";
        result(expectedConfirmation, paymentConfirmation, "Credit Card Confirmation", "Card on file payment");
    }

    //**Author Adi
    @Then("I make payment with ACH Account on file")
    public void makePayment_ACHOnFile() {
        invoiceHeader = new Invoice_Header();
        CardOnFile cardOnFile = new CardOnFile();
        achOnFile = new ACHOnFile();
        CreditCardConfirmationPage confirmationPage = new CreditCardConfirmationPage();
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.waitVisible(invoiceRoutesTab.addNewInvoice);
        invImplementation.clickInitialInvoice();
        invoiceRoutesTab.clickAddPayment();
        invoiceHeader.navigate(invoiceHeader.achDraft);
        String confirmAmount = Legacy.getAttribute(cardOnFile.paymentAmountInputField, "value");
        Legacy.clickElement(cardOnFile.confirmAmountInputField);
        Legacy.locate(cardOnFile.confirmAmountInputField).sendKeys(confirmAmount);
        Legacy.clickElement(achOnFile.draftACHButton);
        Legacy.waitVisible(confirmationPage.paymentResultTitle);
        String paymentConfirmation = Legacy.getElementTextValue(confirmationPage.confirmationMessage);
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
        Legacy.waitVisible(invoiceRoutesTab.addNewInvoice);
        invImplementation.clickInitialInvoice();
        Thread.sleep(2000);
        invoiceRoutesTab.clickAddPayment();
        invoiceHeader.navigate(invoiceHeader.achDraft);
        String confirmAmount = Legacy.getAttribute(cardOnFile.paymentAmountInputField, "value");
        Legacy.clickElement(cardOnFile.confirmAmountInputField);
        Legacy.locate(cardOnFile.confirmAmountInputField).sendKeys(confirmAmount);
        Legacy.clickElement(achOnFile.recordExistingButton);
        Legacy.waitVisible(confirmationPage.paymentResultTitle);
        String paymentConfirmation = Legacy.getElementTextValue(confirmationPage.confirmationMessage);
        String expectedConfirmation = "Successfully Charged ACH Account!";
        result(expectedConfirmation, paymentConfirmation, "ACH Confirmation", "ACH on file payment");
    }

    //*** Author: F. White
    @And("I add a payment via pay option {string} in this amount {string}")
    public void addInvoicePayment(String paymentOption, String paymentAmt){
        invoiceHeader = new Invoice_Header();
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.waitVisible(invoiceRoutesTab.addNewInvoice);
        invImplementation.clickInitialInvoice();
        invoiceRoutesTab.clickAddPayment();

        //Make payment based on selected payment option
        switch (paymentOption.toUpperCase(Locale.ROOT)){
            case "CASH":
                invoiceHeader.navigateTo(invoiceHeader.cash);
                invImplementation.insertPaymentAmount(paymentAmt,paymentAmt);
                invImplementation.clickRecordPaymentButton();
                Utilities.waitVisible(invImplementation.paymentResultsScreenTitle, 5);
                String paymentConfirmation = invImplementation.getPaymentConfirmationMessage();
                result(invImplementation.PAYMENT_SUCCESS_MSG_CASH, paymentConfirmation, "Cash Payment of (" +  paymentAmt +") dollars ", "Cash Payment Confirmation");
                break;
            case "CHECK":
                invoiceHeader.navigateTo(invoiceHeader.check);
                invImplementation.insertPaymentAmount(paymentAmt,paymentAmt);
                invImplementation.clickRecordCheckButton();
                Utilities.waitVisible(invImplementation.paymentResultsScreenTitle, 5);
                paymentConfirmation = invImplementation.getPaymentConfirmationMessage();
                result(invImplementation.PAYMENT_SUCCESS_MSG_CHECK, paymentConfirmation, "Check Payment of (" +  paymentAmt +") dollars ", "Check Payment Confirmation");
                break;
            case "COUPON":
                invoiceHeader.navigateTo(invoiceHeader.coupon);
                invImplementation.insertPaymentAmount(paymentAmt,paymentAmt);
                invImplementation.clickRecordCouponButton();
                Utilities.waitVisible(invImplementation.paymentResultsScreenTitle, 5);
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
        Legacy.waitVisible(invoiceRoutesTab.addNewInvoice,5);
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
        Legacy.waitVisible(invoiceRoutesTab.addNewInvoice,5);

        //Load the most recently added invoice
        invImplementation.clickMostRecentInvoice();

        //Validate the generated invoice has an invoice number assigned
        Assert.assertTrue(StringUtils.isNumeric(invImplementation.getMostRecentInvoiceNumber()), "*******The Invoice Number is  Invalid or Missing!");
    }

    @Then("I validate the taxable option on invoice")
    public void validateTaxableOptionOnInvoice(){
        double zeroTaxAmt = 0.0;
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.waitVisible(invoiceRoutesTab.addNewInvoice,5);

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

    @And("I add a subscription with Service Type {string} and an Expiration Date")
    public void addSubscription(String strServiceType) {
        customerCardHeader = new CustomerViewDialog_Header();
        subscriptionTab = new CustomerViewDialog_SubscriptionTab();
        customerCardHeader.navigateTo(customerCardHeader.subscriptionTabInDialog);

        //Add an Annual Subscription with an Expiration Date
        subscriptionTab.clickNewSubscription();
        subscriptionTab.setSubscriptionExpirationDate(GetDate.addOneYearToDate(GetDate.currentDate("MM/dd/yyyy")));
        subscriptionTab.selectRecurringServiceType(strServiceType);
        subscriptionTab.selectInitialInvoice("After Initial Completion");

        //Save the Subscription
        customerCardHeader.clickSaveButton();
    }//addAnnualSubscription()

    @And("I schedule and complete an appointment for subscription with Service Type {string}")
    public void scheduleAndCompleteServiceAppointment(String serviceType){
        customerCardHeader = new CustomerViewDialog_Header();
        createCustomer = new CreateNewCustomer();
        scheduleAppointment = new ScheduleAppt();
        appointmentsTab = new CustomerviewDialog_AppointmentsTab();
        RoutePage routePage = new RoutePage();
        String customerFullName =createCustomer.getCustomerFullName();

        //Today's Schedule
        schedulingTab = userDashboard.goToSchedulingComponent();
        schedulingTab.addScheduleDateToProperties();
        schedulingTab.clickScheduleDay();

         //Schedule The Appointment
        header.searchCustomerWithName(customerFullName);
        subscriptionTab = customerCardHeader.goToSubscriptionTab();

        scheduleAppointment.scheduleAppointmentOnRoute(serviceType);
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
        String expirationDate = "09/29";
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.waitVisible(invoiceRoutesTab.addNewInvoice);

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
        invImplementation.enterNewCardInformation(activeGateway, creditCardNum, expirationDate,"930");

    }//makeCardPaymentToAdvanceExpirationDate()

    @Then("I validate the Expiration Date on the subscription has advanced")
    public void verifyExpirationDatAdvanced() {
        customerCardHeader = new CustomerViewDialog_Header();
        createCustomer = new CreateNewCustomer();
        subscriptionTab = new CustomerViewDialog_SubscriptionTab();

        header.searchCustomer_History(header.convertName(createCustomer.customerName));
        customerCardHeader.goToSubscriptionTab();
        Legacy.isVisible( subscriptionTab.newSubscriptionButton);

        result(nextExpirationDate,subscriptionTab.getSubscriptionExpirationDate(),"Subscription Expiration Date Advancement Validation!!!", "Subscription Expiration Date Validation");
        customerCardHeader.clickCloseButton();
    }//verifyExpirationDatAdvanced()

    @Then("I validate the subscription's status is {string}")
    public void verifySubscriptionStatus(String expectedStatus) {
        customerCardHeader = new CustomerViewDialog_Header();
        createCustomer = new CreateNewCustomer();
        subscriptionTab = new CustomerViewDialog_SubscriptionTab();

        header.searchCustomer_History(header.convertName(createCustomer.customerName));
        customerCardHeader.goToSubscriptionTab();
        Legacy.isVisible( subscriptionTab.newSubscriptionButton);

        String currentStatus = subscriptionTab.getSubscriptionStatus();
        result(currentStatus.toUpperCase(Locale.ROOT),expectedStatus.toUpperCase(Locale.ROOT),"Subscription Status Validation","Subscription Status Validation");
        customerCardHeader.clickCloseButton();
     }//verifyExpirationDatAdvanced()

    @Given("I retrieve the merchant's configured gateway")
    public String retrieveConfiguredGateway() {
        header.navigateTo(header.adminTab);
        admin.navigateTo(admin.preferences);
        merchantPage = new MarchantInfoPage();
        OfficeSettingsObjects officeSettings = new OfficeSettingsObjects();
        Legacy.clickElement(officeSettings.merchantInfo);
        Utilities.waitVisible(officeSettings.lblDefaultVaultSettings,2);

        String configuredGateWay = merchantPage.getDefaultCreditCardGateway();
        activeGateway = configuredGateWay;

        System.out.println("***************** retrieveConfiguredGateway(): " + activeGateway);
        return configuredGateWay;
    }//retrieveConfiguredGateway()

    @When("I Generate A Stand Alone Invoice")
    public void automateGeneratingStandAloneInvoice() {
        RoutePageInvoicing userOnInvoicesTab = new RoutePageInvoicing();
        CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
        CreateNewInvoicePopUp userOnNewInvoicePopUp = new CreateNewInvoicePopUp();
        addSubscription = new AddSubscription();

        sameUser.goToInvoicesTab();
        userOnInvoicesTab.clickNewInvoice();
        userOnNewInvoicePopUp.typeSubTotal(AddSubscription.totalInitialInvoice);
        invoiceSubTotal = userOnNewInvoicePopUp.getSubTotal();
        userOnNewInvoicePopUp.clickCreateButton();
        invoicePaymentBalance = userOnInvoicesTab.getPaymentBalance();
    }

    @When("I Generate A Stand Alone Invoice For Specific {string} Amount")
    public void automateGeneratingStandAloneInvoiceForSpecificAmount(String amount) {
        RoutePageInvoicing userOnInvoicesTab = new RoutePageInvoicing();
        CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
        CreateNewInvoicePopUp userOnNewInvoicePopUp = new CreateNewInvoicePopUp();
        addSubscription = new AddSubscription();

        sameUser.goToInvoicesTab();
        userOnInvoicesTab.clickNewInvoice();
        userOnNewInvoicePopUp.typeSubTotal(amount);
        invoiceSubTotal = userOnNewInvoicePopUp.getSubTotal();
        userOnNewInvoicePopUp.clickCreateButton();
        invoicePaymentBalance = userOnInvoicesTab.getPaymentBalance();
    }

    @When("I Generate A Stand Alone Invoice Using A Past Date")
    public void automateGeneratingStandAloneInvoiceUsingPastDate() {
        RoutePageInvoicing userOnInvoicesTab = new RoutePageInvoicing();
        CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
        CreateNewInvoicePopUp userOnNewInvoicePopUp = new CreateNewInvoicePopUp();
        addSubscription = new AddSubscription();
        String currentDate = GetDate.currentDate("M/dd/yyyy");
        String oneYearAgo = GetDate.minusOneYearToDate(currentDate);

        sameUser.goToInvoicesTab();
        userOnInvoicesTab.clickNewInvoice();
        userOnNewInvoicePopUp.typeInvoiceDate(oneYearAgo);
        userOnNewInvoicePopUp.typeSubTotal(AddSubscription.totalInitialInvoice);
        invoiceSubTotal = userOnNewInvoicePopUp.getSubTotal();
        userOnNewInvoicePopUp.clickCreateButton();
        invoicePaymentBalance = userOnInvoicesTab.getPaymentBalance();
    }

    @When("I Generate A Stand Alone Invoice Using A Future Date")
    public void automateGeneratingStandAloneInvoiceUsingFutureDate() {
        RoutePageInvoicing userOnInvoicesTab = new RoutePageInvoicing();
        CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
        CreateNewInvoicePopUp userOnNewInvoicePopUp = new CreateNewInvoicePopUp();
        addSubscription = new AddSubscription();
        String oneYearInTheFuture = GetDate.addYearstoCurrentYear("M/dd/yyyy", 1);

        sameUser.goToInvoicesTab();
        userOnInvoicesTab.clickNewInvoice();
        userOnNewInvoicePopUp.typeInvoiceDate(oneYearInTheFuture);
        userOnNewInvoicePopUp.typeSubTotal(AddSubscription.totalInitialInvoice);
        invoiceSubTotal = userOnNewInvoicePopUp.getSubTotal();
        userOnNewInvoicePopUp.clickCreateButton();
        invoicePaymentBalance = userOnInvoicesTab.getPaymentBalance();
    }

    @And("I Pay Off The Stand Alone Invoice")
    public void automatePayingOffStandAloneInvoice() {
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
    public void automatePayingOffNonStandAloneInvoice() {
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

    @And("I make full payment via pay option {string} {string} or {string} for balance due")
    public void makeFullCardPayment(String cardPaymentOption, String creditCardNum, String nmiCreditCardNum) throws Exception {
        invoiceHeader = new Invoice_Header();
        customerCardHeader = new CustomerViewDialog_Header();
        cardPayment = new SingleCardPayment();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.waitVisible(invoiceRoutesTab.addNewInvoice);

        //Pay full invoice amount
        invImplementation.clickInitialInvoice();
        invoiceRoutesTab.clickAddPayment();
        invoiceHeader.clickCard();

        String amtDue = invImplementation.getPaymentAmount();
        if (cardPaymentOption.equalsIgnoreCase("SINGLE USE CARD")) {
            if ((activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_BRAINTREE)) ||
                (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_SPREEDLY)) ||
                (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_ELEMENT)) ||
                (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_PESTROUTES_PAYMENTS))) {
                invImplementation.typeConfirmationAmount(amtDue);
                invImplementation.enterNewCardInformation(activeGateway, "4111 1111 1111 1111", "12/29", "145");
            }else if (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_NMI)) {
                invImplementation.typeConfirmationAmount(amtDue);
                invImplementation.enterNewCardInformation(activeGateway, "5412 7501 0905 6250", "12/29", "146");
            }
        }
        else if (cardPaymentOption.equalsIgnoreCase("USE CARD ON FILE")) {
                invImplementation.payWithCardOnFile(invImplementation.getPaymentAmount());
        } else {
            result(cardPaymentOption, "UNKNOWN CARD PAYMENT OPTION", "Card Option Selection", "Card Payment Selection Validation");
        }
    }//makeFullCardPayment()

    @And("I give a full refund")
    public void processRefundOrReversePayment() {
        invoiceHeader = new Invoice_Header();
        customerCardHeader = new CustomerViewDialog_Header();
        cardPayment = new SingleCardPayment();
         customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.waitVisible(invoiceRoutesTab.addNewInvoice);

        //Click the initial Invoice and on a payment line-item
        invImplementation.clickInitialInvoice();
        invImplementation.clickMostRecentPayment();

        //Reverse or Refund Payment
        if (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_PESTROUTES_PAYMENTS)) {
                invImplementation.clickPaymentActionsReverseButton();
                Utilities.waitVisible(invImplementation.lblTitleReverseDialog,1);
             }
        else {
                String paymentTransAmt = invImplementation.getPaymentTransactionAmount();
                invImplementation.clickPaymentActionsRefundButton();
                Utilities.waitVisible(invImplementation.lblTitleRefundDialog, 1);
                invImplementation.setRefundAmount(paymentTransAmt);
            }
        invImplementation.clickReverseRefundContinueButton();
    }//processRefund()

    @Then("I validate the refund processed successfully")
    public void verifyRefundProcessed() {
        String  resultMessage = invImplementation.getRefundReverseResultMessage();

        if (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_PESTROUTES_PAYMENTS))
            result(invImplementation.REVERSE_SUCCESS_MSG, resultMessage, "Payrix Gateway: Payment Reversal Validation", "Payment Reversal Validation");
        else {
                if (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_BRAINTREE))
                   result(invImplementation.REFUND_SUCCESS_MSG_BRAINTREE, resultMessage, "Braintree Gateway: Payment Refund Validation", "Payment Refund Validation");
                else if (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_ELEMENT))
                    result(invImplementation.REFUND_SUCCESS_MSG_ELEMENT, resultMessage, "Element Gateway: Payment Refund Validation", "Payment Refund Validation");
                else if (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_NMI))
                    result(invImplementation.REFUND_SUCCESS_MSG_NMI, resultMessage, "Element Gateway: Payment Refund Validation", "Payment Refund Validation");
                else if (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_SPREEDLY))
                    result(invImplementation.REFUND_SUCCESS_MSG_SPREEDLY, resultMessage, "Element Gateway: Payment Refund Validation", "Payment Refund Validation");
        }
    }//verifyRefundProcessed

    @And("I create multiple standalone service invoices")
    public void createMultipleStandaloneInvoices() {
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.isVisible(invoiceRoutesTab.addNewInvoice);

        //Add 3 StandAlone Invoices
        for (int i = 1; i <=3; i++)
        {
            invImplementation.createStandAloneServiceInvoice(GetDate.currentDate("MM/dd/yyyy"), "125.00", "One-Time Automation");
            Legacy.isPresent(invImplementation.accountSummaryButton);
        }
        invImplementation.clickAccountSummary();
    }//createMultipleStandaloneInvoices()

    @And("I make a full payment via pay option Single Use Card {string} or {string} with \\(Apply To First) selected")
    public void processCardPaymentWithApplyToFirstSelected(String creditCardNumber, String nmiCreditCardNumber) {
        customerCardHeader = new CustomerViewDialog_Header();
        invoiceHeader = new Invoice_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.waitVisible(invoiceRoutesTab.addNewInvoice,2);

        String  cardNum = creditCardNumber;
        if(activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_NMI))
            cardNum= nmiCreditCardNumber;

        //Click "Account Summary" and  add  payment
        invImplementation.clickAccountSummary();
        invImplementation.clickAddPaymentAccountSummary();
        invoiceHeader.navigateTo(invoiceHeader.creditCard );
        Utilities.acceptAlert();
        Legacy.isTextPresent("Card Payment");

        //Limit Payment to a single invoice
        applyToFirstInvoiceNum = invImplementation.getInvoiceNumByIndex(1);
        String payAmt = invImplementation.getInvoiceInitialBalance(applyToFirstInvoiceNum);
        invImplementation.typePaymentAmount(payAmt);
        invImplementation.typeConfirmationAmount(payAmt);

        invImplementation.applyToFirstInvoice(applyToFirstInvoiceNum);

        //Update Billing Address
        invImplementation.typeAddress("10001 Loop Ln.");

        //Charge Credit Card
        invImplementation.enterNewCardInformation(activeGateway,cardNum,"12/29","145");
      }//makePaymentWithApplyToFirstSelected


    @Then("I validate the payment was applied only to the selected invoice")
    public void assertPaymentWasAppliedOnlyToTheSelectedInvoice() {
        customerCardHeader = new CustomerViewDialog_Header();

        header.searchCustomer_History(header.convertName(createCustomer.customerName));
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.isVisible(invoiceRoutesTab.addNewInvoice);

        result("FULLY PAID", invImplementation.getInvoicePaymentBalanceStatus( applyToFirstInvoiceNum.trim() ), "Apply To First Invoice Validation","Apply To First Invoice Validation");
    }

    @And("I create multiple standalone service invoices with addons and marked eligible for consolidation")
    public void generateInvoicesWithAddonsAndMarkConsolidate() {
        customerCardHeader = new CustomerViewDialog_Header();
        invoiceHeader = new Invoice_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.waitVisible(invoiceRoutesTab.addNewInvoice,2);

        //Create multiple invoices with added ticket item and marked  "Eligible for Consolidation"
        createStandAloneServiceInvoiceWithAddonAndMarkConsolidate(GetDate.currentDate("MM/dd/yyyy"),"100.00", "Misc Service", "Animal Removal");
        createStandAloneServiceInvoiceWithAddonAndMarkConsolidate(GetDate.currentDate("MM/dd/yyyy"),"120.00", "Annual Automation Inspection","Animal Removal");
        createStandAloneServiceInvoiceWithAddonAndMarkConsolidate(GetDate.currentDate("MM/dd/yyyy"),"125.00", "Automation Renewal","Animal Removal");
        invImplementation.clickAccountSummary();

    }//generateInvoicesWithAddonsAndMarkConsolidate()

    @Given("I enable Preferences option Eligible for Consolidation in the system")
    public void enableOptionEligibleForConsolidation() {
        header.navigateTo(header.adminTab);
        admin.navigateTo(admin.preferences);
        officeSettings = new OfficeSettingsObjects();
        officeSettings.navigateTo(officeSettings.preferences);

        preferencesPage  = new PreferencesPage();
        Legacy.scrollToElementJS(preferencesPage.billingPreferencesSectionTitle);
        preferencesPage.clickEdit_BillingPreferences();
        preferencesPage.selectUseConsolidatedInvoicing("Yes");
        preferencesPage.clickSave_BillingPreferences();
    }//enableOptionEligibleForConsolidation()

    @And("I add a subscription with Service Type {string} and marked Eligible for Consolidation")
    public void addSubscriptionAnddMarkEligibleForConsolidation(String serviceType) {
        customerCardHeader = new CustomerViewDialog_Header();
        subscriptionTab = new CustomerViewDialog_SubscriptionTab();
        customerCardHeader.navigateTo(customerCardHeader.subscriptionTabInDialog);

        //Create  Subscription
        addSubscription(serviceType);

        //Mark it Eligible for Consolidation
        subscriptionTab.checkEigibleForConsolidation();
        customerCardHeader.clickSaveButton();
    }//addSubscriptionAnddMarkEligibleForConsolidation()

    @And("I schedule and complete multiple appointments for the subscription {string}")
    public void scheduleAndCompleteMultipleAppointmentsForASubscription(String serviceType){
        //Schedule and complete 3 appointments
        for(int i=1; i <=3; i++) {
            scheduleAndCompleteServiceAppointment(serviceType);
            delay(4000);
       }
    }//scheduleAndCompleteMultipleAppointmentsForASubscription()

    @And("I include add-on {string} to a completed service generated invoice marked eligible for consolidation")
    public void addTicketItemAddonToInvoice(String addonItem) {
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);

        delay(2000);
         addonInvoiceNum = invImplementation.getInvoiceNumByIndex(1);

        //Add a ticket item add-ons to the invoice

        Legacy.waitVisible("//*[@id='invoiceGroupListContainer']/ul/li[@ticketid='" + addonInvoiceNum + "']");
        Legacy.clickElement("//*[@id='invoiceGroupListContainer']/ul/li[@ticketid='" + addonInvoiceNum + "']");
        invoiceRoutesTab.clickAddTicketItem();
        invoiceRoutesTab.selectAvailableItems(addonItem);
    }//addTicketItemAddonToInvoice()

    @And("I consolidate all invoices")
    public void consolidateAllInvoices(){
        billingComponent = new BillingModule();
        createCustomer = new CreateNewCustomer();
        customerCardHeader = new CustomerViewDialog_Header();

        customerCardHeader.navigateTo(customerCardHeader.infoTabInDialog);
        String customerName = createCustomer.getCustomerFullName();
        header.navigateTo(header.billingTab);
        billingComponent.clickConsolidateInvoices();

        billingComponent.setDateRange("This Week");
        billingComponent.clickRefresh();

        billingComponent.selectAllICustomerInvoicesForConsolidation(customerName);
        billingComponent.clickActions();
        billingComponent.clickConsolidateInvoicesAction();
        delay(1000);

        createCustomer.searchCustomer();
    }//consolidateAllInvoices()

    @Then("I validate the Consolidated Totals are correct")
    public void validateConsolidatedInvoiceTotals() {
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);

        invImplementation.clickAccountBalanceSummary();
        String accountInitialBalance = invImplementation.getAcctBalancesSummaryBeginningBal();
        String accountRemainingBalance = invImplementation.getAcctBalancesSummaryEndingBal();
        String  addonInvoiceTotal = invImplementation.getInvoiceRemainingBalance(addonInvoiceNum);

        invImplementation.clickConsolidatedInvoices();
        String consolidatedTotalRemainingBalance = invImplementation.getConsolidatedTotalRemainingBalance();
        String consolidatedAddonInvoiceRemainingBalance = invImplementation.getInvoiceRemainingBalance(addonInvoiceNum);

        result(accountRemainingBalance,consolidatedTotalRemainingBalance,"Consolidated Invoices: Remaining Balances Validation","Consolidated Invoices Validation");
        result(addonInvoiceTotal,consolidatedAddonInvoiceRemainingBalance,"Consolidated Invoice With Addon Remaining Balances Validation","Consolidated Invoices Validation");
    }//validateConsolidatedInvoiceTotals()

    @And("I create and link customers,{string} and {string} with first name, last name, email and address")
    public void createLinkedCustomers(String customer1FullName, String customer2FullName) throws Exception {
        customerCardHeader = new CustomerViewDialog_Header();
        CreateNewCustomer customer1 = new CreateNewCustomer();
        CreateNewCustomer customer2 = new CreateNewCustomer();
        CustomerViewDialog_Properties propertiesTab = new CustomerViewDialog_Properties();
        String[] cust1FirstAndLastName = customer1FullName.split(" ");
        String[] cust2FirstAndLastName = customer2FullName.split(" ");


        String customer1Name = customer1.createCustomerWithNameEmailAddrStreetAddrPhNumZipCode(cust1FirstAndLastName[0], cust1FirstAndLastName[1],"tester@test.com", "214-111-1111", "10001 Loop Ln.,Dallas,TX", "75010");
        String customer2Name = customer2.createCustomerWithNameEmailAddrStreetAddrPhNumZipCode(cust2FirstAndLastName[0], cust2FirstAndLastName[1],"tester@test.com", "214-111-2222", "20002 Loop Ln.,Dallas,TX", "75115");

        header.searchCustomer_History(header.convertName(customer1Name));
        Legacy.isVisible(customerCardHeader.adminPageTitle);
        propertiesTab.gotoPropertiesTab();
        propertiesTab.linkThisProperty(customer2Name);
    }//createLinkedCustomers()

    @And("I add a shared credit card to linked properties")
    public void addASharedCreditCardToLinkedProperties() {
        merchantPage = new MarchantInfoPage();
        customerCardHeader = new CustomerViewDialog_Header();

        //Add a credit card to customer1
        header.searchCustomer_History(header.convertName(linkedCustomer1FullName ));
        Legacy.isVisible(customerCardHeader.adminPageTitle);
        customerCardHeader.navigateTo(customerCardHeader.billingTabInDialog);
        billingTab.clickAddPaymentMethod();
        billingTab.clickCreditCardButton();

        if ((activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_BRAINTREE)) ||
            (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_ELEMENT)) ||
            (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_SPREEDLY)) ||
            (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_PESTROUTES_PAYMENTS)))
                 billingTab.enterNewCardInformation(activeGateway,"4111 1111 1111 1111","12/29","145" );
        else if (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_NMI))
            billingTab.enterNewCardInformation(activeGateway,"5412 7501 0905 6250","12/29","146" );

        String ccToken1= (billingTab.getTokenValue(billingTab.ccOptionOnLeft, billingTab.tokenValue)).toLowerCase(Locale.ROOT);

        //Share customer1's credit card with customer2
        header.searchCustomer_History(header.convertName(linkedCustomer2FullName));
        Legacy.isVisible(customerCardHeader.adminPageTitle);
        customerCardHeader.navigateTo(customerCardHeader.billingTabInDialog);
        billingTab.clickAddPaymentMethod();
        String ccToken2= billingTab.shareCustomerCreditCardInfo(linkedCustomer1FullName);

        result(ccToken1,ccToken2,"Credit Card Tokens Match","Shared Credit Card Validation");
    }//addASharedCreditCardToLinkedProperties

    @And("I validate a message is displayed when editing details of a shared credit card by customers")
    public void validateSharedCreditCardCannotBeEdited() {

         //Verify shared credit card cannot be edited by customer1
        header.searchCustomer_History(header.convertName(linkedCustomer1FullName));
        Legacy.isTextPresent("Account Overview");
        customerCardHeader.navigateTo(customerCardHeader.billingTabInDialog);
        billingTab.clickSharedCardOnFile();
        billingTab.clickEditCardDetails();
        String cust1Msg= billingTab.getSharedBillingInfoMsg();

        //Verify shared credit card cannot be edited by customer1
        header.searchCustomer_History(header.convertName(linkedCustomer2FullName));
        customerCardHeader.navigateTo(customerCardHeader.billingTabInDialog);
        billingTab.clickSharedCardOnFile();
        billingTab.clickEditCardDetails();
        String cust2Msg = billingTab.getSharedBillingInfoMsg();

        result(cust1Msg,billingTab.sharedBillingInfoMsg,"(Shared CC): 'Billing Information In Used' Message displayed for Customer1", "'Billing Information In Use' Message  Validation");
        result(cust2Msg,billingTab.sharedBillingInfoMsg,"(Shared CC): 'Billing Information In Used' Message displayed for Customer1", "'Billing Information In Use' Message  Validation");
    }//validateSharedCreditCardCannotBeEdited()

    @Then("I remove the linked properties , {string} and {string}")
    public void removeLinkedProperties(String customer1FullName, String customer2FullName) {
        createCustomer = new CreateNewCustomer();
        customerCardHeader = new CustomerViewDialog_Header();

        //Remove Customers
        createCustomer.removeCustomer(customer2FullName);
        delay(500);
        createCustomer.removeCustomer(customer1FullName);
    }//removeLinkedProperties()

    @And("I create two linked customers with first name, last name, email, address and zip")
    public void  createTwoLinkedCustomers() throws Exception {
        customerCardHeader = new CustomerViewDialog_Header();
        CreateNewCustomer customer1 = new CreateNewCustomer();
        CreateNewCustomer customer2 = new CreateNewCustomer();
        CustomerViewDialog_Properties propertiesTab = new CustomerViewDialog_Properties();

         linkedCustomer1FullName = customer1.createCustomerWithNameEmailAddrStreetAddrPhNumZipCode("75166");
         linkedCustomer2FullName = customer2.createCustomerWithNameEmailAddrStreetAddrPhNumZipCode("75115");

        header.searchCustomer_History(header.convertName(linkedCustomer1FullName));
        Legacy.isVisible(customerCardHeader.overviewPageTitle);
        propertiesTab.gotoPropertiesTab();
        propertiesTab.linkThisProperty(linkedCustomer2FullName);
    }//createTwoLinkedCustomers()

    @And("I add a non-shared credit card to each linked customer")
    public void addNonSharedCreditCardToLinkedCustomers() {
        merchantPage = new MarchantInfoPage();
        customerCardHeader = new CustomerViewDialog_Header();
        String creditCardNumber = "4111 1111 1111 1111";

        if(activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_NMI))
            creditCardNumber = "5412 7501 0905 6250";

        addCreditCardToCustomerAccount(activeGateway,creditCardNumber,linkedCustomer1FullName);
        addCreditCardToCustomerAccount(activeGateway,creditCardNumber,linkedCustomer2FullName);
    }//addNonSharedCreditCardToLinkedCustomers()

    @And("I add a credit card to customer account ")
    public String addCreditCardToCustomerAccount(String gateway, String ccNumber,String customerFullName) {
        merchantPage = new MarchantInfoPage();
        customerCardHeader = new CustomerViewDialog_Header();

        //Add a credit card to customer1
        header.searchCustomer_History(header.convertName(customerFullName));
        Legacy.isVisible(customerCardHeader.overviewPageTitle);
        customerCardHeader.navigateTo(customerCardHeader.billingTabInDialog);
        billingTab.clickAddPaymentMethod();
        billingTab.clickCreditCardButton();

        if (gateway.equalsIgnoreCase(merchantPage.GATEWAY_BRAINTREE) ||
            gateway.equalsIgnoreCase(merchantPage.GATEWAY_ELEMENT) ||
            gateway.equalsIgnoreCase(merchantPage.GATEWAY_SPREEDLY) ||
            gateway.equalsIgnoreCase(merchantPage.GATEWAY_PESTROUTES_PAYMENTS))
            billingTab.enterNewCardInformation(activeGateway, ccNumber, "12/29", "140");
        else if  (gateway.equalsIgnoreCase(merchantPage.GATEWAY_NMI))
            billingTab.enterNewCardInformation(activeGateway,ccNumber,"12/29", "141" );

        String ccToken= (billingTab.getTokenValue(billingTab.ccOptionOnLeft, billingTab.tokenValue)).toLowerCase(Locale.ROOT);

        return ccToken;
    }//addCreditCardToCustomerAccount()

    @And("I update customer's {string} non-shared credit card")
    public String updateNonSharedCreditCardForCustomer(String customerFullName, String gateway,String cardNumber, String  cvv) {

        //Verify credit card can be updated successfully
        header.searchCustomer_History(header.convertName(customerFullName));
        Legacy.isVisible(customerCardHeader.overviewPageTitle);
        customerCardHeader.navigateTo(customerCardHeader.billingTabInDialog);
        billingTab.clickSharedCardOnFile();
        billingTab.clickEditCardDetails();
        String billingInfoMsg = billingTab.getSharedBillingInfoMsg();

        return billingInfoMsg;
    }//validateNonSharedCreditCardCanBeEdited()

    @And("I edit the linked customers' non-shared credit card and validate a <Billing Info Is Used> message is not displayed")
    public void editLinkedCustomersNonSharedCreditCard() {
        String cardNumber = "4111 1111 1111 1111";
        if(activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_NMI))
             cardNumber = "5412 7501 0905 6250";

        String billingInUseMsg1 = updateNonSharedCreditCardForCustomer(linkedCustomer1FullName,activeGateway,cardNumber, "101");
        result("",billingInUseMsg1.toString(),"Customer (" + linkedCustomer1FullName + ") 'Billing Information In Use...' Message Not Displayed Validation", "Message Not Displayed Validation");

        String billingInUseMsg2 = updateNonSharedCreditCardForCustomer(linkedCustomer2FullName,activeGateway,cardNumber, "202");
        result("",billingInUseMsg2.toString(),"Customer (" + linkedCustomer2FullName + ") 'Billing Information In Use...' Message Not Displayed Validation", "Message Not Displayed Validation");
    }//editLinkedCustomersNonSharedCreditCard()

    @Then("I remove linked customers")
    public void removeLinkedCustomers() {
        createCustomer = new CreateNewCustomer();
        customerCardHeader = new CustomerViewDialog_Header();

        //Remove Customers  - Note: The non-master account must be removed first
        createCustomer.removeCustomer(linkedCustomer2FullName);
        createCustomer.removeCustomer(linkedCustomer1FullName);
    }//removeLinkedCustomers()


    @Then("I remove the shared credit card and add a non-shared credit card to a linked customer")
    public void removeSharedCreditCardAndAddANonSharedCreditCard() {
        merchantPage = new MarchantInfoPage();
        customerCardHeader = new CustomerViewDialog_Header();
        String creditCardNumber = "4111 1111 1111 1111";

        if (activeGateway.equalsIgnoreCase(merchantPage.GATEWAY_NMI))
            creditCardNumber = "5412 7501 0905 6250";

        header.searchCustomer_History(header.convertName(linkedCustomer2FullName));
        Legacy.isVisible(customerCardHeader.overviewPageTitle);
        customerCardHeader.navigateTo(customerCardHeader.billingTabInDialog);
        billingTab.clickSharedCardOnFile();
        billingTab.removeCCPaymentMethod();

        //Add a non-shared credit card
        Legacy.isVisible(billingTab.addPaymentMethodButton);
        addCreditCardToCustomerAccount(activeGateway,creditCardNumber,linkedCustomer2FullName);

    }//removeSharedCreditCardAndAddANonSharedCreditCard

    @And("I add a standalone invoice to a linked customer")
    public void addStandaloneInvoiceLinkedCustomer() {
        customerCardHeader = new CustomerViewDialog_Header();

        header.searchCustomer_History(header.convertName(linkedCustomer2FullName));
        Legacy.isVisible(customerCardHeader.overviewPageTitle);
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        createStandAloneServiceInvoice("150.00", "One-Time Automation");
    }//addStandaloneInvoiceLinkedCustomer()

    @And("I make a full {string} payment via {string} screen")
    public void makeFullPaymentViaAccountSummaryScreen(String paymentOption, String paymentScreen) {
        makeFullPaymentWithDistributionDetailsApplied(paymentOption,paymentScreen,"");
    }//makeFullPaymentViaAccountSummaryScreen()

    @And("I give a partial refund payment {string} via {string} screen")
    public void givePartialRefund(String partialRefundAmt, String screenName) {
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.isVisible(invoiceRoutesTab.addNewInvoice);

        invImplementation.processRefundPayment("Partial",partialRefundAmt,screenName,activeGateway);
    }//givePartialRefund()

    @Given("I configure credit card gateway {string}")
    public void configureCCGateway(String gateway) {
        merchantPage = new MarchantInfoPage();
        preferencesPage = new PreferencesPage();

        header.navigateTo(header.adminTab);
        admin.clickPreferencesSubComponent();
        preferencesPage.clickMerchantinfolink();

        merchantPage.clickEditForDefaultSettings();
        merchantPage.selectCreditCardGateway(gateway);
        merchantPage.clickSaveForDefaultSettings();
        Legacy.isTextPresent(merchantPage.HDR_DEFAULT_VAULT_SETTINGS);
    }//configureCCGateway()

    @Then("I validate refund order")
    public void validateRefundOrder() {
        customerCardHeader = new CustomerViewDialog_Header();

        header.searchCustomer_History(header.convertName(createCustomer.customerName));
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.isVisible(invoiceRoutesTab.addNewInvoice);

        generatedInvoiceIDsList = invImplementation.getGeneratedInvoicesNumbers();
        System.out.println("Generated Invoice ID's: " + generatedInvoiceIDsList);

        result("UNPAID",invImplementation.getInvoicePaymentBalanceStatus(generatedInvoiceIDsList.get(0)), "REFUND NEWEST TICKETS FIRST", "REFUND ORDER VALIDATION");
        result("PARTIALLY PAID",invImplementation.getInvoicePaymentBalanceStatus(generatedInvoiceIDsList.get(1)), "REFUND NEWEST TICKETS FIRST", "REFUND ORDER VALIDATION");
        result("FULLY PAID",invImplementation.getInvoicePaymentBalanceStatus(generatedInvoiceIDsList.get(2)), "RREFUND NEWEST TICKETS FIRST", "REFUND ORDER VALIDATION");
    } //validateRefundOrder()

    @And("I make a full {string} payment via {string} screen with payment priority applied")
    public void makeFullPaymentWithPaymentPriorityApplied(String paymentOption, String paymentScreen) {
       //Make payment with "Apply to First" priority applied
        makeFullPaymentWithDistributionDetailsApplied(paymentOption,paymentScreen,invImplementation.DISTRIBUTION_APPLY_TO_FIRST);
    }//makeAFullPaymentWithPaymentPriorityApplied

    @And("I make a full {string} payment via {string} screen with Distribution Details {string} applied")
    public void makeFullPaymentWithDistributionDetailsApplied(String paymentOption, String paymentScreen,String distributionOption) {
        customerCardHeader = new CustomerViewDialog_Header();
        invoiceHeader = new Invoice_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.isVisible(invoiceRoutesTab.addNewInvoice);

        if(paymentScreen.equalsIgnoreCase("Account Summary")) {
            invImplementation.clickAccountSummary();
            invImplementation.clickAddPaymentAccountSummary();
        }
        else if(paymentScreen.equalsIgnoreCase("Invoice"))
        {
            invImplementation.clickInitialInvoice();
            invoiceRoutesTab.clickAddPayment();
        }

        //Select Payment Option
        if (paymentOption.equalsIgnoreCase("CASH"))
            invoiceHeader.clickCash();
        else if (paymentOption.equalsIgnoreCase("CHECK"))
            invoiceHeader.clickCheck();
        else if (paymentOption.equalsIgnoreCase("COUPON"))
            invoiceHeader.clickCouponCredit();

        //Apply Distribution Option
        if(distributionOption.equalsIgnoreCase(invImplementation.DISTRIBUTION_APPLY_TO_FIRST)) {
            generatedInvoiceIDsList = invImplementation.getGeneratedInvoicesNumbers();
            invImplementation.applyToFirstInvoice(generatedInvoiceIDsList.get(1));

            //Add a customer comment so the "Apply to First" section will close
            invImplementation.addCustomerPaymentNote("Applying Distribution Details: 'Apply to First' " +
                    "ticket (ID: " + generatedInvoiceIDsList.get(1)  + ") ");
        }

        invImplementation.makeFullPayment(paymentOption);
    }//makeFullPaymentWithDistributionDetailsApplied()

    @Then("I validate refund order with payment priority applied")
    public void validateRefundOrderWithPaymentPriorityApplied() {
        customerCardHeader = new CustomerViewDialog_Header();

        header.searchCustomer_History(header.convertName(createCustomer.customerName));
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.isVisible(invoiceRoutesTab.addNewInvoice);

        generatedInvoiceIDsList = invImplementation.getGeneratedInvoicesNumbers();
        System.out.println("Generated Invoice ID's: " + generatedInvoiceIDsList);

        result("UNPAID",invImplementation.getInvoicePaymentBalanceStatus(generatedInvoiceIDsList.get(0)), "REFUND WITH PAYMENT PRIORITY APPLIED", "REFUND ORDER VALIDATION");
        result("FULLY PAID",invImplementation.getInvoicePaymentBalanceStatus(generatedInvoiceIDsList.get(1)), "REFUND WITH PAYMENT PRIORITY APPLIED", "REFUND ORDER VALIDATION");
        result("PARTIALLY PAID",invImplementation.getInvoicePaymentBalanceStatus(generatedInvoiceIDsList.get(2)), "REFUND WITH PAYMENT PRIORITY APPLIED", "REFUND ORDER VALIDATION");
    } //validateRefundOrderWithPaymentPriorityApplied()

   @Then("I Verify The Customer Has A Fully Paid Balance After Being Charged via Auto Pay")
   public void testCustomerHasFullyPaidBalanceAfterAutoPay() {
       userOnHeader.searchCustomerWithName(CreateNewCustomer.customerName);
       sameUser.goToInvoicesTab();
       String paymentStatus = invoiceRoutesTab.getFullyPaidStatus();
       softAssert.assertTrue(paymentStatus.contains("FULLY PAID"),
               "\n The Payment Is Not Fully Paid" +
                       "\n Actual Status Is " + paymentStatus);
       sameUser.goToAdminTab();
       userOnAdminTab.clickRemoveButton();
       userOnAdminTab.clickConfirmRemoveButton();
       softAssert.assertAll();
       softAssert.assertAll();
   }

    @Then("I Verify The Customer Has An UNPAID Balance After Card Is Declined via Auto Pay")
    public void testCustomerHasUnPaidBalanceAfterAutoPay() {
        userOnHeader.searchCustomerWithName(CreateNewCustomer.customerName);
        sameUser.goToInvoicesTab();
        String paymentStatus = invoiceRoutesTab.getUnpaidStatus();
        softAssert.assertTrue(paymentStatus.contains("UNPAID"),
                "\n The Payment Is Paid" +
                        "\n Actual Status Is " + paymentStatus);
    }

    @Then("I Verify Only 1 Transaction Declined Message Shows Up In The Invoices Tab")
    public void testCustomerHasOneFailedTransactionMessage() {
        userOnHeader.searchCustomerWithName(CreateNewCustomer.customerName);
        sameUser.goToInvoicesTab();
        int actualNumberOfDeclinedTransactions = invoiceRoutesTab.getNumberOfDeclinedTransactions();
        int expectedNumberOfDeclinedTransactions = 1;
        softAssert.assertEquals(actualNumberOfDeclinedTransactions, expectedNumberOfDeclinedTransactions,
                "\n The Actual & Expected Number Of Transactions Do Not Match" +
                        "\n Actual # Of Transactions:   " + actualNumberOfDeclinedTransactions +
                        "\n Expected # Of Transactions: " + expectedNumberOfDeclinedTransactions + "\n");
        sameUser.goToAdminTab();
        userOnAdminTab.clickRemoveButton();
        userOnAdminTab.clickConfirmRemoveButton();
        softAssert.assertAll();
    }
}