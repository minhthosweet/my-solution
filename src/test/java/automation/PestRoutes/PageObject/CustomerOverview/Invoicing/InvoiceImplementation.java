package automation.PestRoutes.PageObject.CustomerOverview.Invoicing;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class InvoiceImplementation extends BasePage {

    // Values on Invoicing Landing Page
    public String accountSummaryButton = "//div[@id='billingPanel']//li[contains(text(),'Account Summary')]";
    public String invoiceAccountSummaryClick = "//ul[@id='invoiceGroupListContainer']/ul/li";
    public String initialInvoice = "//span[text()='Initial Balance']";
    public String accountStatementReport = "//li[text()='Account Balance Summary']";
    public String remainingBalanceAmount = "//span[text()='Remaining Balance']/parent::div";

    //Most Recent Invoice's Invoice-Number
    private By mostRecentInvoiceNumber = By.xpath("//ul[@id='invoiceGroupListContainer']//span[text()='Invoice']/parent::div[1]");

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
    private String successfulChargeAmount = "//form[@id=\"singlePaymentForm\"]//h2[@class=\"bold aCenter clr\"]";

    public By paymentResultsScreenTitle = By.xpath("//form[@id='singlePaymentForm']//h3");
    public By paymentConfirmationMsg = By.xpath("//*[@id='singlePaymentForm']/div/h2[1]");

    //Create New Invoice
    private String createNewInvoice_date = "//input[@name='date']";

    //Account Summary Objects
    public String accountBalance = "//div[@id='billingPanel']//div[@id='SubStatus']";
    public String accountBalance1 = "//div[@id='billingPanel']//div[@data-balance]";
    public String printInvoiceAmountDue = "//th[text()='Amount Due']//following-sibling::td[1]";
    public String printInvoiceAmountDue1= "//span[text()='Amount Due:']/parent::th/following-sibling::td/span[2]";

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

    //Print Invoice Objects
    public String additionalNotes ="//div[contains(@id,'additionalNotes')]//following-sibling::textarea";
    public String markLetterSentButton = "//div[@id='markPrintButton']";
    public String printInvoiceDate = "//div[contains(@id,'checkLen')]//following-sibling::div//tr[6]/td";
    public String printInvoiceMainAmounDue = "//th[text()='Amount Due']//following-sibling::td";
    public String printInvoiceMainAmounDue1= "//span[text()='Amount Due:']/ancestor::tr/td/span[2]";

    // Card Elements
    private By addressField = By.xpath("//input[@name='billingAddress1']");
    private By cardNumberField = By.xpath("//input[@id='cardNumber']");
    private By expirationMonthField = By.xpath("//select[@id='ddlExpirationMonth']");
    private By expirationYearField = By.xpath("//select[@id='ddlExpirationYear']");
    private By cvvField = By.xpath("//input[@id='CVV']");
    private By chargeSingleCardButton = By.xpath("//input[@id='chargeSingleCardButton']");
    private By processTransactionButton = By.xpath("//a[@id='submit'] [text()='PROCESS TRANSACTION ']");
    private By limitedToSubscriptionField = By.xpath("//form[@id='singlePaymentForm']//span[text()='Any Subscription']");
    private By checkSubscription = By.xpath("//form[@id='singlePaymentForm']//label[contains(text(),'Subscription')]");
    private By renewalDateCheckBox = By.xpath("//form[@id='singlePaymentForm']//input[@name='updateRenewalDate']");
    private By renewalDate = By.xpath("//form[@id='singlePaymentForm']//input[@name='renewalDate']");
    private By expirationDateCheckBox = By.xpath("//form[@id='singlePaymentForm']//input[@name='updateExpirationDate']");
    private By expirationDateField = By.xpath("//form[@id='singlePaymentForm']//input[@name='expirationDate']");
    private By sendToJobPoolCheckBox = By.xpath("//form[@id='singlePaymentForm']//input[@name='updateCustomDate']");
    private By sendToJobPoolField = By.xpath("//form[@id='singlePaymentForm']//input[@name='customDate']");



    //Payment Details
    private By btnActions = By.xpath("//*[@id='paymentForm']/div[1]/div[2]/div[contains(text(),'Actions')]");
    private By lnkUpdatePayment = By.xpath("//*[@id='modifyPaymentButton']");
    private By inputPaymentTransactionAmount  = By.xpath("//*[@id='paymentForm']//div//input[@name ='originalAmount']");
    private By inputPaymentTransactionDate = By.xpath("//*[@id='paymentForm']//div//input[@name ='paymentDate']");
    private By textareaPaymentNotes = By.xpath("//*[@id='paymentForm']//div//textarea[@name ='customerNotes']");
    private By lnkMostRecentPayment = By.xpath("//div[@id='billingPanel']//div[text()='+ Add Payment']/following-sibling::div/div[1]");
    private By lnkSaveRedistribute = By.xpath("//*[@id='savePaymentDetails']");
    private By lblCustomerPaymentDetails = By.xpath("//*[@id='paymentForm']/div[2]/div[1]/h3[contains(text(), 'Customer Payment Details')]");

    //------------------------------------------------------
    //Constants
    //------------------------------------------------------
    public static  String  nextExpirationDate;
    //Payment Results Messages
    public final String PAYMENT_SUCCESS_MSG_CASH = "Successfully Charged Cash!";
    public final String PAYMENT_SUCCESS_MSG_CHECK = "Successfully Applied Check!";
    public final String PAYMENT_SUCCESS_MSG_CARD = "Successfully Charged Credit Card!";
    public final String PAYMENT_SUCCESS_MSG_ACH = "Successfully Charged ACH Account!";
    public final String PAYMENT_SUCCESS_MSG_COUPON = "Coupon Applied Successfully!";
    public final String PAYMENT_DETAILS_SECTION_TITLE = "Customer Payment Details";

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
        //Optimized For Encapsulation Below via clickRecordPaymentButton() Using A Private Modifier With By Class
    }

    public void clickRecordPaymentButton() {
        click(recordPaymentButton);
    }

    public void invoiceAccountSummaryClick() {
        Utilities.waitUntileElementIsVisible(invoiceAccountSummaryClick);
        Utilities.clickElement(invoiceAccountSummaryClick, ElementType.XPath);
    }

    public void clickInvoice(String needServiceName) {
        Utilities.waitUntileElementIsVisible("//ul[@id='invoiceGroupListContainer']//div[contains(text(),'" + needServiceName + "')]");
        Utilities.clickElement("//ul[@id='invoiceGroupListContainer']//div[contains(text(),'" + needServiceName + "')]", ElementType.XPath);
    }

    public void clickInitialInvoice() {
        delay(100);
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
        Utilities.waitUntileElementIsVisible(lblCustomerPaymentDetails, 5);
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
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getAttributeValue(serviceCostBeforeTax,"value");
    }

    public String getAddOnValue(String addOn){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getAttributeValue("//div[not(@ticketid='0')and@subscriptionid='0']//div[text()='"+addOn+"']/following-sibling::input","value");
    }

    public String getSubTotalValue(){
        Utilities.waitUntileElementIsVisible(subTotalValue);
        return Utilities.getElementTextValue(subTotalValue, ElementType.XPath);
    }

    public String getTaxValue(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(taxValue, ElementType.XPath);
    }

    public String getInitialDiscountValue(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getAttributeValue(initialDiscountValue,"value");
    }

    public String getInvoiceDate(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(invoiceDate, ElementType.XPath);
    }

    public String getDueDate(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(dueDate, ElementType.XPath);
    }

    public String getAppointmentDate(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(appointmentDate, ElementType.XPath);
    }

    public String getRemainingBalanceAmount(){
        Utilities.waitUntileElementIsVisible(remainingBalanceAmount);
        return (Utilities.getElementTextValue(remainingBalanceAmount, ElementType.XPath)).replaceAll("[^0-9.]","");
    }

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

    public void clickCreditMemoButton(){
        Utilities.waitUntileElementIsVisible(invoiceActionButton);
        Utilities.hoverElement(invoiceActionButton, creditMemoButton);
    }

    public void click(String needObject){
        Utilities.waitUntileElementIsVisible(needObject);
        Utilities.clickElement(needObject, ElementType.XPath);
    }

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

    public void clickChargeSingleCardButton() { click(chargeSingleCardButton); }

    public void typeAddress(String address) { type(address, addressField); }

    public void typeCreditCardNumber (String cardNumber) {
        driver.switchTo().frame("elementSingleFrame");
        type(cardNumber, cardNumberField);
    }

    public void selectExpirationDate (String expirationMonth, String expirationYear) {
        selectFromDropDown(expirationMonth, expirationMonthField);
        selectFromDropDown(expirationYear, expirationYearField);
    }

    public void typeCVV (String CVV) {
        type(CVV, cvvField);
    }

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

}
