package automation.PestRoutes.PageObject.CustomerOverview.Invoicing;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.MerchantInfoTab.MarchantInfoPage;
import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import automation.PestRoutes.Utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static automation.PestRoutes.Utilities.AssertException.result;
import static automation.PestRoutes.Utilities.Utilities.*;
import static automation.PestRoutes.Utilities.Utilities.switchToIframeByXpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class InvoiceImplementation extends BasePage {

    // Values on Invoicing Landing Page
    public String accountSummaryButton = "//div[@id='billingPanel']//li[contains(text(),'Account Summary')]";
    public String invoiceAccountSummaryClick = "//ul[@id='invoiceGroupListContainer']/ul/li";
    public String initialInvoice = "//span[text()='Initial Balance']";
    public String accountStatementReport = "//li[text()='Account Balance Summary']";
    public By lnkAccountBalanceSummary = By.xpath("//li[text()='Account Balance Summary']");
    public String remainingBalanceAmount = "//span[text()='Remaining Balance']/parent::div";

    private By lnkConsolidatedInvoices =  By.xpath("//*[@id='billingPanel']//li[contains(text(),'Consolidated Invoices')]");

    //Most Recent Invoice's Invoice-Number
    private By mostRecentInvoiceNumber = By.xpath("//ul[@id='invoiceGroupListContainer']//span[text()='Invoice']/parent::div[1]");
    private By  invoicesList = By.xpath("//ul[@id='invoiceGroupListContainer']//span[text()='Invoice']/parent::div");

     // Invoice Amount
    private String newInvoice = "//form[@id=\"newInvoiceParams\"]//input[@type=\"number\"]";
    private String serviceSelect = "//form[@id=\"newInvoiceParams\"]//select[@name = \"serviceID\"]";
    private String service = "//form[@id=\"newInvoiceParams\"]/select/option[21]";
    private String create = "//span[contains(@class,'ui-button-text') and contains(text(),'Create')]";

    // Assertion Invoice Values
    private String invoiceCost = "//div[@id=\"serviceTicket\"]//div[contains (text(), '@ $5,000.00')]";
    private String totalValueCharges = "//div[@id=\"invoiceDetails\"]//div[@class = 'ticketTotal totalBoxValue']";

    // Payment Status
    //Do not have any other XPath for initialPaymentStatus
    private String initialPaymentStatus = "//li[@ticketid]//div[@class='payment-status paid']";
    private String paymentBalance = "//form[@id = 'singlePaymentForm']//div[@id = 'SubStatus']";

    //Distribution Details
    public String limitedToCustomer = "//span[text()='Any Customer']";
    public String limitedToSubscription = "//span[text()='Any Subscription']";
    public String applyToFirst = "//span[text()='Any Invoice']";
    public String paymentWarning = "//div[text()='Prepayment Amount: ']/following-sibling::div[2]";
    public String renewalDateField = "//div[text()='Renewal Date: ']/following-sibling::input[@name = 'renewalDate']";
    public String renewalDateCheckbox = "//div[text()='Renewal Date: ']/following-sibling::input[@name = 'updateRenewalDate']";

    // Cash tab
    private String paymentAmountField = "//div[text() = 'Payment Amount:']/following-sibling::input[@name = 'amount']";
    private By paymentAmtField = By.xpath("//form[@id='singlePaymentForm']//input[@name='amount']");
    public String confirmPymtAmtField = "//input[@name = 'confirmCashAmount']";
    private By confirmAmountField = By.xpath("//input[@name = 'confirmCashAmount']");
    private String custPaymentNotes = "//textarea[@name = \"customerNotes2\"]";
    private String recordPayment = "//form[@id=\"singlePaymentForm\"]//div[contains (text(), 'Record Payment')]";
    private By recordPaymentButton = By.xpath("//form[@id='singlePaymentForm']//div[text()='Record Payment']");

    //Check Tab
    private By recordCheckButton = By.xpath("//form[@id='singlePaymentForm']//div[text()='Record Check']");

    //Coupon/Credit Tab
    private By recordCouponButton = By.xpath("//form[@id='singlePaymentForm']//div[text()='Record Coupon']");

    // Payment Result
    private String successfulCharge = "//h2[contains(@class,'bold aCenter clr font24') and contains(text(),'Successfully Charged Cash!')]";
    private By paymentSuccessMessage = By.xpath("//h2[contains(@class,'bold aCenter clr font24') and contains(text(),'Successfully Charged')]");

    private String successfulChargeAmount = "//form[@id=\"singlePaymentForm\"]//h2[@class=\"bold aCenter clr\"]";


    public By paymentResultsScreenTitle = By.xpath("//form[@id='singlePaymentForm']//h3[contains(text(),'Payment Result')]");
    public By paymentConfirmationMsg = By.xpath("//*[@id='singlePaymentForm']/div/h2[1]");
    public By singleUseCardPaymentResultsScreenTitle = By.xpath("//*[@id='billingPanel']//div/h3[contains(text(),'Payment Result')]");
    public By singleUseCardPaymentConfirmationMsg = By.xpath("//*[@id='billingPanel']//div/h2[1]");

    //Create New Invoice
    private String createNewInvoice_date = "//input[@name='date']";

    //Account Summary Objects
    public String accountBalance = "//div[@id='billingPanel']//div[@id='SubStatus']";
    public String accountBalance1 = "//div[@id='billingPanel']//div[@data-balance]";
    public String printInvoiceAmountDue = "//th[text()='Amount Due']//following-sibling::td[1]";
    public String printInvoiceAmountDue1= "//span[text()='Amount Due:']/parent::th/following-sibling::td/span[2]";

    private By lnkAddPaymentAcctSummary = By.xpath("//*[@id='billingPanel']//div[contains(text(),'+ Add Payment')]");

    //Initial Invoice Objects
    public String printInvoicePaymentBalance = "//th[text()='Invoice Total']//following-sibling::td[1]";
    public String printInvoicePaymentBalance1 = "//span[text()='Invoice Total']/parent::th/following-sibling::td/span[2]";

    //Active Invoice Objects
    public String activeInvoiceOnTheLeft = "//li[@class='listItem appleMenuActive']";
    public String inactiveInvoiceOnTheLeft = "//li[@class='listItem']";
    public String paymentsBalance = "//div[text()='Balance']/following-sibling::div";

    // Charges Objects
    public String serviceCostBeforeTax  = "//div[not(@ticketid='0')and@subscriptionid='0']//input[@name='serviceCharge']";
    public String subTotalValue = "//div[not(@ticketid='0')and@subscriptionid='0']//following-sibling::div//div[text()='Sub Total']/following-sibling::div[1]";
    public String taxValue = "//div[not(@ticketid='0')and@subscriptionid='0']//following-sibling::div//div[text()='Tax']/following-sibling::div[1]";
    private By calTaxValue = By.xpath("//div[not(@ticketid='0')and@subscriptionid='0']//following-sibling::div//div[text()='Tax']/following-sibling::div[1]");
    public String chargesTotalValue = "//div[not(@ticketid='0')and@subscriptionid='0']//following-sibling::div//div[text()='Total']/following-sibling::div[1]";
    public String initialDiscountValue = "//div[@subscriptionid='0']//div[text()='Initial Discount']/following-sibling::input[@name='amount']";
    private By drpdwnTaxable = By.xpath("//*[@id='serviceTicket']/div[2]/select[2]");

    // Payments Objects
    public String paymentsInPayments = "//div[text()='Payments']/following-sibling::div[1]";
    public String balanceInPayments = "//div[text()='Balance']/following-sibling::div";

    //Ticket Info Objects
    public String invoiceDate = "//div[text()='Invoice Date']/following-sibling::div[1]";
    public String dueDate = "//div[text()='Due Date']/following-sibling::div[1]";
    public String appointmentDate = "//div[text()='Appointment Date']/following-sibling::div[1]";

    // Account Statement Report Objects
    public String dateRange = "//input[@name='dateRange-accountStatementFilterParams']";
    public String reportType = "//select[@name='filterType']";
    public String thisAccount = "//label[@for='accountBalanceSummaryTransactionBID']";
    public String responsibleFor = "//label[@for='accountBalanceSummaryTransactionCID']";
    public String refreshButton = "//button[@onclick='runAccountStatementReport()']";
    public String scrollLeftButton = "//div[@id='accountStatementReportScrollButtons']//div[contains(@class,'scrollLeft')]//i";
    public String scrollRightButton = "//div[@id='accountStatementReportScrollButtons']//div[contains(@class,'scrollRight')]//i";
    public String invoiceActionButton = "//div[@id='billingPanel']//div[@class='toggleActions right tableButton']";
    public String statementActionButton = "//div[@id='conditionsActionsBtn']";
    public String statementPrintButton = "//div[text()='Print']";
    private By backToAccountSummaryButton= By.xpath("//form[@id='singlePaymentForm']//div[text()='Back to Account Summary']");

    // Invoice Action Objects
    public String invoicePrintButton = "//div[@id='printInvoiceButton']";
    public String creditMemoButton = "//div[@id='creditMemoButton']";

    //Payments
    public String addPaymentButton = "//div[text() = '+ Add Payment']";
    private By btnCardOnFile = By.xpath("//*[@id='singlePaymentCardOptions']/label[@for = 'cardOnFile']");
    private By bthChargeCard = By. xpath("//*[@id='chargeCardButton']");

    //Print Invoice Objects
    public String additionalNotes ="//div[contains(@id,'additionalNotes')]//following-sibling::textarea";
    public String markLetterSentButton = "//div[@id='markPrintButton']";
    public String printInvoiceDate = "//div[contains(@id,'checkLen')]//following-sibling::div//tr[6]/td";
    public String printInvoiceMainAmounDue = "//th[text()='Amount Due']//following-sibling::td";
    public String printInvoiceMainAmounDue1= "//span[text()='Amount Due:']/ancestor::tr/td/span[2]";

    // Card Elements
    private By addressField = By.xpath("//input[@name='billingAddress1']");
    private By braintreeCardNumberField = By.xpath("//input[@id='credit-card-number']");
    private By braintreeExpirationDateField = By.xpath("//input[@id='expiration']");
    private By payWithCard = By.xpath("//div[@id='dropin-container']//div[@aria-label='Paying with Card']");
    private By elementCardNumberField = By.xpath("//input[@id='cardNumber']");
    private By elementExpirationMonth = By.xpath("//select[@id='ddlExpirationMonth']");
    private By elementExpirationYear = By.xpath("//select[@id='ddlExpirationYear']");
    private By elementCVVField = By.xpath("//input[@id='CVV']");
    private By elementProcessTransactionButton = By.xpath("//a[@id='submit']");
    private By nmiCardNumberField = By.xpath("//input[@id='cc-number']");
    private By nmiExpirationDateField = By.xpath("//input[@id='cc-exp']");
    private By nmiCVVField = By.xpath("//input[@id='cc-cvv']");
    private By submitPaymentButton = By.xpath("//button[@id='submit-payment']");
    private By spreedlyCardNumber = By.xpath("//input[@id='card_number']");
    private By spreedlyExpirationMonth = By.xpath("//select[@name='expMonth']");
    private By spreedlyExpirationYear = By.xpath("//select[@name='expYear']");
    private By spreedlyCVVField = By.xpath("//input[@id='cvv']");
    private By pestRoutesPaymentsCardNumber = By.xpath("//input[@id='payment_number']");
    private By pestRoutesPaymentsExpirationDate = By.xpath("//input[@id='expiration']");
    private By pestRoutesPaymentsCVVField = By.xpath("//input[@id='payment_cvv']");

    private By chargeSingleCardButton = By.xpath("//input[@id='chargeSingleCardButton']");
    private By nmiChargeSingleCardButton = By.xpath("//div[text()='Charge Single Card']");


    private By processTransactionButton = By.xpath("//a[@id='submit'] [text()='PROCESS TRANSACTION ']");
    private By limitedToSubscriptionField = By.xpath("//form[@id='singlePaymentForm']//span[text()='Any Subscription']");

    private By applyToFirstField = By.xpath("//*[@id='singlePaymentForm']//div[contains(text(),'Apply To First')]/following-sibling::div");
    private By applyToFirstInvoiceOptions= By.xpath("//*[@id='singlePaymentForm']//div[contains(text(),'Apply To First')]/following-sibling::div/div/ul/li/ul/li");
    private By selectedApplyToFirstOption = By.xpath("//*[@id='singlePaymentForm']//div[contains(text(),'Apply To First')]/following-sibling::div/p");

    private By checkSubscription = By.xpath("//form[@id='singlePaymentForm']//label[contains(text(),'Subscription')]");
    private By renewalDateCheckBox = By.xpath("//form[@id='singlePaymentForm']//input[@name='updateRenewalDate']");
    private By renewalDate = By.xpath("//form[@id='singlePaymentForm']//input[@name='renewalDate']");
    private By expirationDateCheckBox = By.xpath("//form[@id='singlePaymentForm']//input[@name='updateExpirationDate']");
    private By expirationDateField = By.xpath("//form[@id='singlePaymentForm']//input[@name='expirationDate']");
    private By paymentFlagsSection = By.xpath("//h3[text()='Payment Flags']");
    private By sendToJobPoolCheckBox = By.xpath("//form[@id='singlePaymentForm']//input[@name='updateCustomDate']");
    private By sendToJobPoolField = By.xpath("//form[@id='singlePaymentForm']//input[@name='customDate']");

    //Payment Details
    private By btnActions = By.xpath("//*[@id='paymentForm']//div[contains(text(),'Actions')]");
    private By lnkUpdatePayment = By.xpath("//*[@id='modifyPaymentButton']");
    private By inputPaymentTransactionAmount  = By.xpath("//*[@id='paymentForm']//div//input[@name ='originalAmount']");
    private By inputPaymentTransactionDate = By.xpath("//*[@id='paymentForm']//div//input[@name ='paymentDate']");
    private By textareaPaymentNotes = By.xpath("//*[@id='paymentForm']//div//textarea[@name ='customerNotes']");
    private By lnkMostRecentPayment = By.xpath("//div[@id='billingPanel']//div[text()='+ Add Payment']/following-sibling::div/div[1]");
    private By lnkSaveRedistribute = By.xpath("//*[@id='savePaymentDetails']");
    private By lblCustomerPaymentDetails = By.xpath("//*[@id='paymentForm']/div[2]/div[1]/h3[contains(text(), 'Customer Payment Details')]");

    //Payment Actions Options
    private By btnActionsRefund = By.xpath("//*[@id='refundPaymentButton']");
    private By btnActionsReverse = By.xpath("//*[@id='reversePaymentButton']");

    //Reverse/Refund Dialog
    public By lblTitleRefundResult = By.xpath("//*[@id='billingPanel']//div/h3[contains(text(),'Refund Result')]");
    public By lblTitleReverseResult = By.xpath("//*[@id='billingPanel']//div/h3[contains(text(),'Reverse Result')]");
    public By lblTitleRefundDialog = By.xpath("//*[@id='refundProcessBox']/p[contains(text(),'Refund amount')]");
    public By lblTitleReverseDialog = By.xpath("//*[@id='refundProcessBox']/p[contains(text(),'Reversing a payment')]");
    private By inputRefund = By.xpath("//*[@id='refundProcessBox']//input[@id='refundAmountInput']");
    private By btnContinue_ReverseRefund = By.xpath("//*[@id='refundProcessBox']/following-sibling::div//div//button/span[contains(text(),'Continue')]");
    private By btnCancel_ReverseRefund = By.xpath("//*[@id='refundProcessBox']/following-sibling::div//div//button/span[contains(text(),'Cancel')]");
    private By textRefundResultMessage = By.xpath("//*[@id='billingPanel']//div[contains(text(),'Result')]//following-sibling::div[1]");
    private By textRefundResultAmount = By.xpath("//*[@id='billingPanel']//div[contains(text(),'Amount')]//following-sibling::div[1]");

    private By  acctBalanceSummaryBeginningBal = By.xpath("//span[contains(text(),'Beginning Balance')]/../../following-sibling::div/div[contains(@class,'half left accountBalanceSummaryBalanceNumbers ')]/span");
    private By  acctBalanceSummaryEndingBal = By.xpath("//span[contains(text(),'Ending Balance')]/../../following-sibling::div//div[contains(@class, 'half right accountBalanceSummaryBalanceNumbers')]/span");

    private By chkboxEligibleForConsolidation = By.xpath("//*[@id='eligibleForConsolidation']");


    //------------------------------------------------------
    //Constants
    //------------------------------------------------------
    public static  String  nextExpirationDate;

    //Payment Results Messages
    public final String PAYMENT_RESULT_SCREEN_TITLE = "Payment Result";
    public final String PAYMENT_SUCCESS_MSG_CASH = "Successfully Charged Cash!";
    public final String PAYMENT_SUCCESS_MSG_CHECK = "Successfully Applied Check!";
    public final String PAYMENT_SUCCESS_MSG_CARD = "Successfully Charged Credit Card!";
    public final String PAYMENT_SUCCESS_MSG_ACH = "Successfully Charged ACH Account!";
    public final String PAYMENT_SUCCESS_MSG_COUPON = "Coupon Applied Successfully!";
    public final String PAYMENT_DETAILS_SECTION_TITLE = "Customer Payment Details";
    public final String REFUND_RESULT_SCREEN_TITLE = "Refund Result";
    public final String REVERSE_RESULT_SCREEN_TITLE = "Reverse Result";
    public final String REFUND_SUCCESS_MSG= "Successfully Refunded";
    public final String REFUND_SUCCESS_MSG_BRAINTREE = "Refund Successfully Issued";
    public final String REFUND_SUCCESS_MSG_ELEMENT = "Approved";
    public final String REFUND_SUCCESS_MSG_NMI = "SUCCESS";
    public final String REFUND_SUCCESS_MSG_SPREEDLY = "succeeded";
    public final String REVERSE_SUCCESS_MSG = "Marked payment for Reversal";
    public final String DISTRIBUTION_LIMITED_TO_CUSTOMER = "Limited To Customer";
    public final String DISTRIBUTION_LIMITED_TO_SUBSCRIPTION = "Limited To Subscription";
    public final String DISTRIBUTION_APPLY_TO_FIRST = "Apply To First";

    //------------------------------------------------------
    // Methods
    //------------------------------------------------------
    public String checkPaymentStatus() {
        Utilities.waitUntileElementIsVisible(initialPaymentStatus);
        return Utilities.getElementTextValue(initialPaymentStatus, ElementType.XPath);
    }

    public void newInvoiceDetails(String amount, String date) {
        Utilities.waitUntileElementIsVisible(createNewInvoice_date);
        FindElement.elementByAttribute(createNewInvoice_date, InputType.XPath).clear();
        FindElement.elementByAttribute(createNewInvoice_date, InputType.XPath).sendKeys(date);
        Utilities.clickElement(newInvoice, ElementType.XPath);
        FindElement.elementByAttribute(newInvoice, InputType.XPath).clear();
        FindElement.elementByAttribute(newInvoice, InputType.XPath).sendKeys(amount);
        Utilities.waitUntileElementIsVisible(serviceSelect);
        Utilities.clickElement(serviceSelect, ElementType.XPath);
        Utilities.waitUntileElementIsVisible(service);
        Utilities.clickElement(service, ElementType.XPath);
        Utilities.waitUntileElementIsVisible(create);
        Utilities.clickElement(create, ElementType.XPath);
    }

    public void insertPaymentAmount(String pAmount, String cAmount) {
        Utilities.waitUntileElementIsVisible(paymentAmountField);
        FindElement.elementByAttribute(paymentAmountField, InputType.XPath).clear();
        Utilities.clickElement(paymentAmountField, ElementType.XPath);
        FindElement.elementByAttribute(paymentAmountField, InputType.XPath).sendKeys(pAmount);
        Utilities.waitUntileElementIsVisible(confirmPymtAmtField);
        Utilities.clickElement(confirmPymtAmtField, ElementType.XPath);
        FindElement.elementByAttribute(confirmPymtAmtField, InputType.XPath).sendKeys(cAmount);
        Utilities.waitUntileElementIsVisible(custPaymentNotes);
        Utilities.clickElement(custPaymentNotes, ElementType.XPath);
        FindElement.elementByAttribute(custPaymentNotes, InputType.XPath).sendKeys("This is just a test");
    }

    public String getPaymentAmount() {
        return find(paymentAmtField).getAttribute("value");
    }

    public void typePaymentAmount(String paymentAmount) {
        type(paymentAmount, paymentAmtField);
    }

    public void typeConfirmationAmount(String confirmationAmount)  {
        type(confirmationAmount, confirmAmountField);
    }

    public void clickRecordPayment() {
        Utilities.waitUntileElementIsVisible(recordPayment);
        Utilities.clickElement(recordPayment, ElementType.XPath);
    }

    public void clickRecordPaymentButton() {
        click(recordPaymentButton);
    }

    public void invoiceAccountSummaryClick() {
        Utilities.waitUntileElementIsVisible(invoiceAccountSummaryClick);
        Utilities.clickElement(invoiceAccountSummaryClick, ElementType.XPath);
    }

    public void clickInvoice(String needServiceName) {
        delay(3000);
        Utilities.waitUntileElementIsVisible("//ul[@id='invoiceGroupListContainer']//div[contains(text(),'" + needServiceName + "')]");
        Utilities.clickElement("//ul[@id='invoiceGroupListContainer']//div[contains(text(),'" + needServiceName + "')]", ElementType.XPath);
    }

    public void clickInitialInvoice() {
        delay(3000);
        Utilities.waitUntileElementIsVisible(initialInvoice);
        Utilities.clickElement(initialInvoice, ElementType.XPath);
    }

    public void doubleClickRenewalDateCheckBox() {
        Utilities.clickElement(renewalDateCheckbox, ElementType.XPath);
    }

    public void clickRecurringInvoice(String recurringInvoiceTotal){
        Utilities.waitUntileElementIsVisible("//ul[@id='invoiceGroupListContainer']//span[text()='Remaining Balance']/parent::div[contains(text(),'"+recurringInvoiceTotal+"')]");
        Utilities.clickElement("//ul[@id='invoiceGroupListContainer']//span[text()='Remaining Balance']/parent::div[contains(text(),'"+recurringInvoiceTotal+"')]", ElementType.XPath);
    }

    public void clickAccountStatementReport(){
        Utilities.waitUntileElementIsVisible(accountStatementReport);
        Utilities.clickElement(accountStatementReport, ElementType.XPath);
    }

    public void clickAccountSummary(){
        Utilities.waitUntileElementIsVisible(accountSummaryButton);
        Utilities.clickElement(accountSummaryButton, ElementType.XPath);
    }

    public void selectDateRange(String day){
        Utilities.waitUntileElementIsVisible(dateRange);
        Utilities.clickElement(dateRange, ElementType.XPath);
        Utilities.waitUntileElementIsVisible( "//div[contains(@style,'block')]//li[text()='"+day+"']");
        Utilities.clickElement( "//div[contains(@style,'block')]//li[text()='"+day+"']", ElementType.XPath);
    }

    public void selectReportType(String needReportType){
        Utilities.waitUntileElementIsVisible(reportType);
        Utilities.selectValueFromDropDownByValue(reportType, needReportType);
    }

    public void scrollRight(){
        Utilities.waitUntileElementIsVisible(scrollRightButton);
        Utilities.clickElement(scrollRightButton, ElementType.XPath);
    }

    public void refreshAccountStatementReport(){
        Utilities.waitUntileElementIsVisible(refreshButton);
        Utilities.clickElement(refreshButton, ElementType.XPath);
    }

    public void printInvoice(){
        Utilities.waitUntileElementIsVisible(invoiceActionButton);
        Utilities.hoverElement(invoiceActionButton, invoicePrintButton);
    }

    public void printAccountStatement(){
        Utilities.waitUntileElementIsVisible(accountStatementReport);
        Utilities.clickElement(accountStatementReport, ElementType.XPath);
        Utilities.hoverElement(statementActionButton, statementPrintButton);
    }

    public void markLetterAsSent(){
        Utilities.waitUntileElementIsVisible(markLetterSentButton);
        Utilities.clickElement(markLetterSentButton, ElementType.XPath);
    }

     public void clickMostRecentPayment(){
            click(lnkMostRecentPayment);
     }//clickMostRecentPayment();

    public void clickSaveRedistribute() throws InterruptedException {
        click(lnkSaveRedistribute);

        //Wait until Save Process completes
        Thread.sleep(1000);
    }//clickSaveRedistribute();

    public void clickRecordCheckButton() {
        click(recordCheckButton);
    }//clickRecordCheckButton()

    public void clickRecordCouponButton() {
        click(recordCouponButton);
    }//clickRecordCouponButton()

    public void clickMostRecentInvoice(){
        click(mostRecentInvoiceNumber);
    }//clickMostRecentInvoice();

    public void selectTaxableOption(String taxableOption){
        selectFromDropDown(taxableOption, drpdwnTaxable);
    }//selectTaxableOption()

    public void loadPaymentDetails() {
        //Click "Actions" button
        click(btnActions);

        //Click "Update Payment" link
        click(lnkUpdatePayment);
         //Wait Until Payment Details Are Loaded
        Utilities.waitUntileElementIsVisible(lblCustomerPaymentDetails, 2);
    }//loadPaymentDetails()

    public void checkExpirationDateBox() throws Exception { Utilities.checkBox(expirationDateCheckBox);}
    public void uncheckExpirationDateBox() throws Exception { Utilities.uncheckBox(expirationDateCheckBox);}

    public void checkSendToJobPoolBox() throws Exception { Utilities.checkBox(sendToJobPoolCheckBox);}
    public void uncheckSendToJobPoolBox() { Utilities.uncheckBox(sendToJobPoolCheckBox);}


    // Getters
    //--------------------------------------------------------
    public int getInvoiceCost() {
        Utilities.waitUntileElementIsVisible(invoiceCost);
        return Utilities.removeSpecialChars(invoiceCost);
    }

    public Integer getTotalValueCharges() {
        Utilities.waitUntileElementIsVisible(totalValueCharges);
        return Utilities.removeSpecialChars(totalValueCharges);
    }

    public Integer getTotalValuePayments() {
        Utilities.waitUntileElementIsVisible(chargesTotalValue);
        return Utilities.removeSpecialChars(chargesTotalValue);
    }

    public String getInvoiceDate_accountStatementReport(String needServiceType){
        Utilities.waitUntileElementIsVisible("//td[contains(text(),'"+needServiceType+"')]/following-sibling::td[text()][2]");
        return Utilities.getElementTextValue("//td[contains(text(),'"+needServiceType+"')]/following-sibling::td[text()][2]", ElementType.XPath);
    }

    public String getInvoiceAmount_accountStatementReport(String needServiceType){
        Utilities.waitUntileElementIsVisible("//div[@id='accountStatementReportTableWrapper']//div[5]//div[4]");
        return Utilities.getElementTextValue("//div[@id='accountStatementReportTableWrapper']//div[5]//div[4]", ElementType.XPath);
    }

    public String getInvoiceBalance_accountStatementReport(String needServiceType){
        Utilities.waitUntileElementIsVisible("//div[@id='accountStatementReportTableWrapper']//div[5]//div[4]");
        return Utilities.getElementTextValue("//div[@id='accountStatementReportTableWrapper']//div[5]//div[4]", ElementType.XPath);
    }

    public String getBalance(String balanceType){
        Utilities.waitUntileElementIsVisible("//span[contains(text(),'"+balanceType+"')]/parent::div/parent::div/following-sibling::div//div[@class='half left accountBalanceSummaryBalanceNumbers ']//span");
        return Utilities.getElementTextValue("//span[contains(text(),'"+balanceType+"')]/parent::div/parent::div/following-sibling::div//div[@class='half left accountBalanceSummaryBalanceNumbers ']//span", ElementType.XPath);
    }

    public String getResponsibleBalance(String balanceType){
        Utilities.waitUntileElementIsVisible("//span[contains(text(),'"+balanceType+"')]/parent::div/parent::div/following-sibling::div//div[@class='half right accountBalanceSummaryBalanceNumbers ']//span");
        return Utilities.getElementTextValue("//span[contains(text(),'"+balanceType+"')]/parent::div/parent::div/following-sibling::div//div[@class='half right accountBalanceSummaryBalanceNumbers ']//span", ElementType.XPath);
    }

    public String getSuccessfulChargeAmount() {
        String successfulChargeText = Utilities.getElementTextValue(successfulCharge, ElementType.XPath)
                + Utilities.getElementTextValue(successfulChargeAmount, ElementType.XPath);
        System.out.println("Charge is successful " + successfulChargeText);
        return successfulChargeText;
    }

    public int getPaymentBalance() {
        return Utilities.removeSpecialChars(paymentBalance);
    }

    public String getPaymentWarning() {
        return Utilities.getElementTextValue(paymentWarning, ElementType.XPath);
    }

    public String getAccountBalance(){
        try {
            Utilities.waitUntileElementIsVisible(accountBalance);
            return Utilities.getElementTextValue(accountBalance, ElementType.XPath);
        } catch (Exception e) {
            Utilities.waitUntileElementIsVisible(accountBalance1);
            return Utilities.getElementTextValue(accountBalance1, ElementType.XPath);
        }
    }

    public String getAccountTotalAmountDue(){
        try {
            Utilities.waitUntileElementIsVisible(printInvoiceAmountDue);
            return Utilities.getElementTextValue(printInvoiceAmountDue, ElementType.XPath);
        } catch (Exception e) {
            Utilities.waitUntileElementIsVisible(printInvoiceAmountDue1);
            return Utilities.getElementTextValue(printInvoiceAmountDue1, ElementType.XPath);
        }
    }

    public String getChargesBalance(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(chargesTotalValue, ElementType.XPath);
    }

    public String getPrintInvoiceMainAmountDue() {
        try {
            Utilities.waitUntileElementIsVisible(printInvoiceDate);
            return Utilities.getElementTextValue(printInvoiceMainAmounDue, ElementType.XPath);
        } catch(Exception e){
            Utilities.waitUntileElementIsVisible(printInvoiceMainAmounDue1);
            return Utilities.getElementTextValue(printInvoiceMainAmounDue1, ElementType.XPath);
        }
    }

    public String getChargesBalance_customSchedule(String initialAmountWithoutTax){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue("//div[text()='Sub Total']/following-sibling::div[contains(text(),'"+initialAmountWithoutTax+"')]/following-sibling::div[4]", ElementType.XPath);
    }

    public String getBalanceInPayments(){
        delay(1000);
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(balanceInPayments, ElementType.XPath);
    }

    public String getPrintInvoicePaymentBalance() {
        try {
            Utilities.waitUntileElementIsVisible(printInvoicePaymentBalance);
            return Utilities.getElementTextValue(printInvoicePaymentBalance, ElementType.XPath);
        } catch (Exception e) {
            Utilities.waitUntileElementIsVisible(printInvoicePaymentBalance1);
            return Utilities.getElementTextValue(printInvoicePaymentBalance1, ElementType.XPath);
        }
    }

    public String getServiceCostBeforeTax(){
        delay(1000);
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getAttributeValue(serviceCostBeforeTax,"value");
    }

    public String getAddOnValue(String addOn){
        delay(1000);
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getAttributeValue("//div[not(@ticketid='0')and@subscriptionid='0']//div[text()='"+addOn+"']/following-sibling::input","value");
    }

    public String getSubTotalValue(){
        delay(3000);
        Utilities.waitUntileElementIsVisible(subTotalValue);
        return Utilities.getElementTextValue(subTotalValue, ElementType.XPath);
    }

    public String getTaxValue(){
        delay(1000);
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(taxValue, ElementType.XPath);
    }

    public String getInitialDiscountValue(){
        delay(1000);
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getAttributeValue(initialDiscountValue,"value");
    }

    public String getInvoiceDate(){
        delay(1000);
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(invoiceDate, ElementType.XPath);
    }

    public String getDueDate(){
        delay(1000);
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(dueDate, ElementType.XPath);
    }

    public String getAppointmentDate(){
        delay(1000);
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(appointmentDate, ElementType.XPath);
    }

    public String getRemainingBalanceAmount(){
        delay(1000);
        Utilities.waitUntileElementIsVisible(remainingBalanceAmount);
        return (Utilities.getElementTextValue(remainingBalanceAmount, ElementType.XPath)).replaceAll("[^0-9.]","");
    }

    public String getSingleUseCardPaymentConfirmMsg(){
        return getText(singleUseCardPaymentConfirmationMsg);
    }//getSingleUseCardPaymentConfirmMsg()

    //Note: Used by the following payment methods: Card-on-file, Cash, Check, ACH
    public String getPaymentConfirmationMessage(){
        return getText(paymentConfirmationMsg);
    }//getPaymentConfirmationMessage()

    public String getPaymentTransactionAmount(){
        return getByGetAttribute(inputPaymentTransactionAmount, "value");
    }//getPaymentTransactionAmount()

    public String getPaymentTransactionDate(){
        return getByGetAttribute(inputPaymentTransactionDate, "value");
    }//getPaymentTransactionDate()

    public String getPaymentNotes(){
        return getByGetAttribute(textareaPaymentNotes, "value");
    }//getPaymentTransactionDate()

    public String getMostRecentInvoiceNumber(){
        return getText(mostRecentInvoiceNumber).replaceAll("[^0-9.]","");
    }//getInvoiceNumber()

    public String getCalTaxValue(){
         return getText(calTaxValue).replaceAll("[^0-9.]","");
    }//getInvoiceNumber()

    public String getRefundDialogTitle(){
       return getText(lblTitleRefundDialog);
    }//getRefundDialogTitle()

    public String getRefundReverseResultMessage()
    {
        return getText(textRefundResultMessage);
    }//getRefundResultMessage()

    public String getRefundReverseResultAmount()
    {
        return getText(textRefundResultAmount);
    }//getRefundReverseResultAmount()

    public String getPaymentSuccessMessage()
    {
        return getText(paymentSuccessMessage);
    }//getPaymentSuccessMessage()

    public String getRefundResultScreenTitle()
    {
        return getText(lblTitleRefundResult);
    }

    public String getAcctBalancesSummaryBeginningBal()
    {
        return getText(acctBalanceSummaryBeginningBal);
    }//getAcctBalancesSummaryBeginningBal()

    public String getAcctBalancesSummaryEndingBal()
    {
        return getText(acctBalanceSummaryEndingBal);
    }//getAcctBalancesSummaryEndingBal()

    public void clickCreditMemoButton(){
        Utilities.waitUntileElementIsVisible(invoiceActionButton);
        Utilities.hoverElement(invoiceActionButton, creditMemoButton);
    }

    public void click(String needObject){
        Utilities.waitUntileElementIsVisible(needObject);
        Utilities.clickElement(needObject, ElementType.XPath);
    }

    public void clickPaymentActionsRefundButton(){
        Utilities.waitUntileElementIsVisible(btnActions);
        Utilities.hoverElement(btnActions,btnActionsRefund);
    }//clickPaymentActionsRefundButton()

    public void clickPaymentActionsReverseButton(){
        Utilities.waitUntileElementIsVisible(btnActions);
        Utilities.hoverElement(btnActions,btnActionsReverse);
    }//clickPaymentActionsReverseButton()


    public void clickReverseRefundContinueButton() {
        click(btnContinue_ReverseRefund);
    }//clickReverseRefundContinueButton()

    public void clickConsolidatedInvoices(){
        delay(1000);
        click(lnkConsolidatedInvoices);
    }//clickConsolidatedInvoices()

    public void clickAddPaymentAccountSummary() {
        click(lnkAddPaymentAcctSummary);
    }//clickAddPaymentAccountSummary()

    public void checkEligibleForConsolidation()
    { Utilities.checkBox(chkboxEligibleForConsolidation);}

    public void clickAccountBalanceSummary(){
        click(lnkAccountBalanceSummary);
    }//clickAccountBalanceSummary()


    // Setters
    //--------------------------------------------------------------
    public void setAdditionalNotes(String needNotes){
        try {
            WebElement elm = FindElement.elementByAttribute(additionalNotes, InputType.XPath);
            Utilities.scrollToElementJS(additionalNotes);
            Utilities.waitUntileElementIsVisible(additionalNotes);
            Utilities.clickElement(additionalNotes, ElementType.XPath);
            FindElement.elementByAttribute(additionalNotes, InputType.XPath).sendKeys(needNotes);
        } catch (Exception e) {
            Utilities.scrollToElementJS(printInvoicePaymentBalance1);
            System.out.println("Unable to find additional notes section");
        }
    }

    public void setLimitedToSubscription(String needServiceName) {
        Utilities.clickElement(limitedToSubscription, ElementType.XPath);
        Utilities.clickElement("//label[contains (text(), '" + needServiceName + " Subscription Invoices')]", ElementType.XPath);
    }

    public void clickChargeSingleCardButton() {
        click(chargeSingleCardButton);
    }

    public void clickNMIChargeSingleCardButton() {
        click(nmiChargeSingleCardButton);
    }
    public void typeAddress(String address) { type(address, addressField); }

   public void clickProcessTransactionButton(){ click(processTransactionButton); }

    public void selectLimitedToSubscription() {
        click(limitedToSubscriptionField);
        click(checkSubscription);
    }

    public String getRenewalDate() {
        click(renewalDateCheckBox);
        return find(renewalDate).getAttribute("value");
    }

    public void typeExpirationDate(String expirationDate) {
        click(expirationDateCheckBox);
        type(expirationDate, expirationDateField);
        find(expirationDateField).sendKeys(Keys.ENTER);
    }

    public String getExpirationDate() {
        if (!Utilities.isChecked(expirationDateCheckBox)) {
            click(expirationDateCheckBox);
        }
        return find(expirationDateField).getAttribute("value");
    }//getExpirationDate()

    public String getSendToJobPoolDate() {
        if (!Utilities.isChecked(sendToJobPoolCheckBox)) {
            click(sendToJobPoolCheckBox);
        }
        return find(sendToJobPoolField).getAttribute("value");
    }//getSendToJobPoolDate()

    public void clickBackToAccountSummaryButton(){
        click(backToAccountSummaryButton);
    }

    public void setPaymentTransactionAmount(String transAmt){
        type(transAmt, inputPaymentTransactionAmount);
    }//setPaymentTransactionAmount()

    public void setPaymentTransactionDate(String transDate){
       type(transDate, inputPaymentTransactionDate, "ENTER");
    }//setPaymentTransactionDate()

    public void setPaymentNotes(String paymentNotes){
        type(paymentNotes, textareaPaymentNotes);
    }//setPaymentNotes()

    public void enterBraintreeNewCardInformation(String cardNumber, String expirationDate) {
        click(payWithCard);
        switchToIframeByXpath("braintree-hosted-field-number");
        type(cardNumber, braintreeCardNumberField);
        driver.switchTo().defaultContent();
        switchToIframeByXpath("braintree-hosted-field-expirationDate");
        type(expirationDate, braintreeExpirationDateField);
        driver.switchTo().defaultContent();
        delay(1000);
        clickChargeSingleCardButton();

        delay(3000);
        acceptAlert();
    }

    public void enterElementNewCardInformation(String cardNumber, String expirationDate, String cvv){
        clickChargeSingleCardButton();
        switchToIframeByXpath( "elementSingleFrame");
        type(cardNumber, elementCardNumberField);
        String[] separateMonthYear = expirationDate.split("/");
        String month = separateMonthYear[0];
        String year = separateMonthYear[1];
        selectFromDropDown(month, elementExpirationMonth);
        selectFromDropDown("20"+ year, elementExpirationYear);
        type(cvv, elementCVVField);
        click(elementProcessTransactionButton);

        delay(3000);
        acceptAlert();

        driver.switchTo().defaultContent();

        delay(1000);
        acceptAlert(); //Accept successful payment alert
    }

    public void enterNMINewCardInformation(String cardNumber, String expirationDate, String cvv) {
        clickNMIChargeSingleCardButton();
        switchToIframeByXpath("CollectJSIframe");
        type(cardNumber, nmiCardNumberField);
        String[] separateMonthYear = expirationDate.split("/");
        String month = separateMonthYear[0];
        String year = separateMonthYear[1];
        String expirationMonthYear = month + "/20" + year;
        type(expirationMonthYear, nmiExpirationDateField);
        type(cvv, nmiCVVField);
        click(submitPaymentButton);
        driver.switchTo().defaultContent();

        acceptAlert(); //Accept successful payment alert
    }

    public void enterSpreedlyNewCardInformation(String cardNumber, String expirationDate, String cvv) {
        WebElement iFrameCardNumber = find(By.xpath("//iframe[contains(@id,'spreedly-number-frame')]"));
        driver.switchTo().frame(iFrameCardNumber);
        scrollToElementJS(find(spreedlyCardNumber));
        type(cardNumber, spreedlyCardNumber);
        driver.switchTo().defaultContent();
        String[] separateMonthYear = expirationDate.split("/");
        String month = separateMonthYear[0];
        String year = separateMonthYear[1];
        Select findDropDown = new Select(find(spreedlyExpirationMonth));
        findDropDown.selectByValue(month);
        selectFromDropDown("20"+ year, spreedlyExpirationYear);
        WebElement iFrameCVV = find(By.xpath("//iframe[contains(@id,'spreedly-cvv-frame')]"));
        driver.switchTo().frame(iFrameCVV);
        type(cvv, spreedlyCVVField);
        driver.switchTo().defaultContent();
        clickChargeSingleCardButton();

        delay(3000);
        acceptAlert(); //Accept successful payment alert
    }

    public void enterPestRoutesPaymentsNewCardInformation(String cardNumber, String expirationDate, String cvv) {
        switchToIframeByXpath("payrixSingleChargeIFrame");
        switchToIframeByXpath("payFields-iframe-number");
        type(cardNumber, pestRoutesPaymentsCardNumber);
        driver.switchTo().defaultContent();
        switchToIframeByXpath("payrixSingleChargeIFrame");
        switchToIframeByXpath("payFields-iframe-expiration");
        type(expirationDate, pestRoutesPaymentsExpirationDate);
        driver.switchTo().defaultContent();
        switchToIframeByXpath("payrixSingleChargeIFrame");
        switchToIframeByXpath("payFields-iframe-cvv");
        type(cvv, pestRoutesPaymentsCVVField);
        driver.switchTo().defaultContent();
        clickChargeSingleCardButton();

        delay(3000);
        acceptAlert(); //Accept successful payment alert
    }

    public void  enterNewCardInformation(String gateway, String cardNumber, String expirationDate, String cvv) {
        switch(gateway) {
            case "Braintree":
                  enterBraintreeNewCardInformation(cardNumber, expirationDate);
                break;
            case "Element":
                  enterElementNewCardInformation(cardNumber, expirationDate, cvv);
                break;
            case "NMI":
                  enterNMINewCardInformation(cardNumber, expirationDate, cvv);
                break;
            case "Spreedly":
                 enterSpreedlyNewCardInformation(cardNumber, expirationDate, cvv);
                break;
            case "PestRoutes Payments":
                 enterPestRoutesPaymentsNewCardInformation(cardNumber, expirationDate, cvv);
                break;
            default:
        }
        delay(3000);
    }

    public void setRefundAmount(String refundAmt)
    {
      type(refundAmt, inputRefund);
    }//setRefundAmount()

    public String getSelectedApplyToFirstOption() {
        return getText(selectedApplyToFirstOption);
    }//getSelectedApplyToFirstOption()

    public void applyToFirstInvoice(String invoiceNum)
    {
        click (applyToFirstField);
        List<WebElement>  invoiceLabels = findElements(By.xpath("//*[@id='singlePaymentForm']//div[contains(text(),'Apply To First')]/following-sibling::div/div/ul/li/ul/li/label"));
        List<WebElement> generatedInvoicesOptions = findElements(applyToFirstInvoiceOptions);

          for(WebElement invoiceLabel: invoiceLabels )
            {
                  if (invoiceLabel.getText().contains(invoiceNum)){
                    generatedInvoicesOptions.get(invoiceLabels.indexOf(invoiceLabel)).click();
                    break;
                  }
            }
    }//applyToFirstInvoice()

    public String getInvoiceNumByIndex(Integer invoiceIndex)
    {
       String strInvoiceNum = null;
       List<WebElement> generatedInvoices = driver.findElements(invoicesList);

        if (generatedInvoices.size() > 0) {
            strInvoiceNum = generatedInvoices.get(invoiceIndex - 1).getText().trim();
            strInvoiceNum = strInvoiceNum.replaceAll("\\s.*", "");
        }
       return strInvoiceNum;
    }//getInvoiceNumByIndex()

    public String getInvoicePaymentByIndex(Integer invoiceIndex)
    {
        String strInvoiceNum = null;
        List<WebElement> generatedInvoices = findElements(invoicesList);

        if (generatedInvoices.size() > 0) {
            strInvoiceNum = generatedInvoices.get(invoiceIndex - 1).getText().trim();
            strInvoiceNum = strInvoiceNum.replaceAll("\\s.*", "");
        }
        return strInvoiceNum;
    }//getInvoicePaymentByIndex()

    public List<WebElement> getGeneratedInvoices()
    {
         List<WebElement> generatedInvoices = findElements(invoicesList);
         return generatedInvoices;
    }//getGeneratedInvoices()

    public ArrayList<String> getGeneratedInvoicesNumbers()
    {
        ArrayList<String> generatedInvoiceNums = new ArrayList<>();
        List<WebElement> generatedInvoices = findElements(invoicesList);
        for(WebElement invoice: generatedInvoices)
        {
            generatedInvoiceNums.add(invoice.getText().trim().replaceAll("\\s.*", ""));
        }
        generatedInvoiceNums.sort(Collections.reverseOrder());
        return generatedInvoiceNums;
    }//getGeneratedInvoicesNumbers()

    public String getInvoiceInitialBalance(String invoiceNum){
        String initialBalAmt = getText(By.xpath("//ul[@id='invoiceGroupListContainer']//div[contains(text(), '" + invoiceNum +
                "')]/following-sibling::div/span[text()='Initial Balance']//.."));
        String initialBalanceAmount = initialBalAmt.replaceAll("[^0-9.]","");

        return initialBalanceAmount;
    }//getInvoiceInitialBalance()

    public String getInvoiceRemainingBalance(String invoiceNum){
        String remainingBalAmt = getText(By.xpath("//ul[@id='invoiceGroupListContainer']//div[contains(text(), '" + invoiceNum +
                "')]/following-sibling::div/span[text()='Remaining Balance']//.."));
        String remainingBalanceAmount = remainingBalAmt.replaceAll("[^0-9.]","");

        return remainingBalanceAmount;
    }//getInvoiceRemainingBalance()

    public String getInvoicePaymentBalanceStatus(String invoiceNum){
        Utilities.delay(3000);
        Utilities.scrollToElement(By.xpath("//*[@id='invoiceGroupListContainer']/ul/li[@ticketid='" + invoiceNum +
                "']//div[contains(@class, 'payment-status')]"));
        String invoiceBalStatus= getText(By.xpath("//*[@id='invoiceGroupListContainer']/ul/li[@ticketid='" + invoiceNum +
                                                  "']//div[contains(@class, 'payment-status')]"));
        return invoiceBalStatus;
    }//getInvoicePaymentBalanceStatus()

    public String getConsolidatedTotalInitialBalance(){
      delay(500);
      return getText(By.xpath("//*[@id='consolidatedInvoiceDetails']//div[@class='col-2']//span[contains(text()," +
                                "'Initial Balance')]/following-sibling::span"));
    }//getConsolidatedTotalInitialBalance()

    public String getConsolidatedTotalRemainingBalance(){
        delay(500);
        return getText(By.xpath("//*[@id='consolidatedInvoiceDetails']//div[@class='col-2']//span[contains(text()," +
                                    "'Remaining Balance')]/following-sibling::span"));
    }//getConsolidatedTotalRemainingBalance()

    public String getConsolidatedInvoiceInitialBalance(String consolidatedInvoiceNum){
        delay(500);
        return getText(By.xpath("//*[@id='consolidatedInvoiceDetails']//span[contains(text(),'Initial Balance')]" +
                "/following-sibling::span/../..//div[@class='left half']/span[contains(text(),'" + consolidatedInvoiceNum + "')] "));
    }//getConsolidatedInvoiceInitialBalance()

    public String getConsolidatedInvoiceRemainingBalance(String consolidatedInvoiceNum){
        delay(500);
        return getText(By.xpath("//*[@id='consolidatedInvoiceDetails']//span[contains(text(),'Remaining Balance')]" +
                "/following-sibling::span/../..//div[@class='left half']/span[contains(text(),'" + consolidatedInvoiceNum + "')] "));
    }//getConsolidatedInvoiceRemainingBalance()

    public void clickCardOnFile()
    {
       click(btnCardOnFile);
    }//clickCardOnFile()

    public void clickChargeCardButton()
    {
        click(bthChargeCard);
    }//clickCardOnFile()

    public String payWithCardOnFile(String paymentAmt)
    {
       clickCardOnFile();
       typeConfirmationAmount(paymentAmt);
       clickChargeCardButton();

       Utilities.elementIsVisible( paymentConfirmationMsg);
       String paymentStatusMsg = getPaymentConfirmationMessage();
       return paymentStatusMsg;
    }//payWithCardOnFile()

    public void createStandAloneServiceInvoice(String invoiceDate, String needAmount, String requestedService){
        CreateNewInvoicePopUp newInvoice = new CreateNewInvoicePopUp();
        RoutePageInvoicing invoiceRoutesTab = new RoutePageInvoicing();
        invoiceRoutesTab.clickAddNewInvoice(invoiceRoutesTab.addNewInvoice);

        newInvoice.set(newInvoice.dateField, invoiceDate);
        newInvoice.set(newInvoice.amountInputField, needAmount);
        newInvoice.select(newInvoice.serviceTypeDropdown, requestedService);
        newInvoice.click(newInvoice.createButton);
    }//createStandAloneServiceInvoice()

    public String makeFullPayment(String paymentOption) {
        Invoice_Header invoiceHeader = new Invoice_Header();
        String paymentConfirmation ="";
        String amtDue= "0";

        //Make payment based on selected payment option
        switch (paymentOption.toUpperCase(Locale.ROOT)) {
            case "CASH":
                amtDue = getPaymentAmount();
                typeConfirmationAmount(amtDue);
                clickRecordPaymentButton();

                if (Utilities.isPresent(paymentResultsScreenTitle))
                    paymentConfirmation = getPaymentConfirmationMessage();
                break;
            case "CHECK":
                amtDue = getPaymentAmount();
                typeConfirmationAmount(amtDue);
                clickRecordCheckButton();

                if (Utilities.isPresent(paymentResultsScreenTitle))
                    paymentConfirmation = getPaymentConfirmationMessage();
                break;
            case "COUPON":
                amtDue = getPaymentAmount();
                typeConfirmationAmount(amtDue);
                clickRecordCouponButton();

                if (Utilities.isPresent(paymentResultsScreenTitle))
                    paymentConfirmation = getPaymentConfirmationMessage();
                break;
            default:
                 paymentConfirmation = "INVALID PAYMENT OPTION";
        }
        return paymentConfirmation;
    }//makeFullPayment()

    public String processRefundPayment(String refundTypeFull, String screenName , String gateway) {
       return processRefundPayment("FULL","",screenName,gateway);
    }//processRefundPayment()

    public String  processRefundPayment(String refundTypeFullOrPartial,String paymentAmount,String screenName,String gateway) {
        CustomerViewDialog_Header customerCardHeader = new CustomerViewDialog_Header();
        RoutePageInvoicing invoiceRoutesTab = new RoutePageInvoicing();
        MarchantInfoPage merchantPage = new MarchantInfoPage();
        String refundStatusMessage = "";

        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Utilities.elementIsVisible(invoiceRoutesTab.addNewInvoice);
        clickAccountSummary();

        if(screenName.equalsIgnoreCase("Account Summary")) {
          //Click first line item below "Add Payment" link
            clickMostRecentPayment();
        }
        else if(screenName.equalsIgnoreCase("Invoice"))
        {
            //Click the initial Invoice and on a payment line-item
            clickInitialInvoice(); //Note: This is a fully or partially paid invoice
            clickMostRecentPayment();
        }

        String fullPaymentAmt = getPaymentTransactionAmount();
        //Reverse or Refund Payment
        if (gateway.equalsIgnoreCase(merchantPage.GATEWAY_PESTROUTES_PAYMENTS)) {
            clickPaymentActionsReverseButton();
            Utilities.waitUntileElementIsVisible(lblTitleReverseDialog,1);
        }
        else {
                clickPaymentActionsRefundButton();
                Utilities.waitUntileElementIsVisible(lblTitleRefundDialog, 1);

                if (refundTypeFullOrPartial.equalsIgnoreCase("PARTIAL"))
                    setRefundAmount(paymentAmount);
                else
                    setRefundAmount(fullPaymentAmt);
        }

        clickReverseRefundContinueButton();
        if (isPresent(lblTitleRefundResult) || isPresent(lblTitleReverseResult) )
            refundStatusMessage = getRefundReverseResultMessage();

        return refundStatusMessage;
    }//processRefund()

    public void addCustomerPaymentNote(String comments)
    {
        click(By.xpath(custPaymentNotes));
        type(comments, By.xpath(custPaymentNotes)) ;
    }//addCustomerPaymentNote()
}
