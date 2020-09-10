package automation.PestRoutes.PageObject.Invoicing;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class InvoiceImplementation {

    // Values on Invoicing Landing Page
    private String invoiceAccountSummaryClick = "//ul[@id=\"invoiceGroupListContainer\"]/ul/li";
    public String initialInvoice = "//span[text()='Initial Balance']";

    // Invoice Amount
    private String newInvoice = "//form[@id=\"newInvoiceParams\"]//input[@type=\"number\"]";
    private String serviceSelect = "//form[@id=\"newInvoiceParams\"]//select[@name = \"serviceID\"]";
    private String service = "//form[@id=\"newInvoiceParams\"]/select/option[21]";
    private String create = "//span[contains(@class,'ui-button-text') and contains(text(),'Create')]";

    // Assertion Invoice Values
    private String invoiceCost = "//div[@id=\"serviceTicket\"]//div[contains (text(), '@ $5,000.00')]";
    private String totalValueCharges = "//div[@id=\"invoiceDetails\"]//div[@class = 'ticketTotal totalBoxValue']";
    private String totalPaymentCharges = "//div[@id=\"invoiceDetails\"]//div[@class=\"ticketTotal totalBoxValue balanceBox\"]";

    // Payment Status
    //Do not have any other XPath for initialPaymentStatus
    private String initialPaymentStatus = "//ul[@id=\"invoiceGroupListContainer\"]/ul/li[1]/div[2]/div[2]";
    private String paymentBalance = "//form[@id = \"singlePaymentForm\"]//div[@id = \"SubStatus\"]";

    //Distribution Details
    public String limitedToCustomer = "//span[text()='Any Customer']";
    public String limitedToSubscription = "//span[text()='Any Subscription']";
    public String applyToFirst = "//span[text()='Any Invoice']";
    public String paymentWarning = "//div[text()='Prepayment Amount: ']/following-sibling::div[2]";
    public String renewalDateField = "//div[text()='Renewal Date: ']/following-sibling::input[@name = 'renewalDate']";
    // Cash tab
    private String paymentAmountField = "//div[text() = 'Payment Amount:']/following-sibling::input[@name = 'amount']";
    public String confirmPymtAmtField = "//input[@name = 'confirmCashAmount']";
    private String custPaymentNotes = "//textarea[@name = \"customerNotes2\"]";
    private String recordPayment = "//form[@id=\"singlePaymentForm\"]//div[contains (text(), 'Record Payment')]";

    // Payment Result
    private String successfulCharge = "//h2[contains(@class,'bold aCenter clr font24') and contains(text(),'Successfully Charged Cash!')]";
    private String successfulChargeAmount = "//form[@id=\"singlePaymentForm\"]//h2[@class=\"bold aCenter clr\"]";

    //Create New Invoice
    private String createNewInvoice_date = "//input[@name='date']";

    //Account Summary Objects
    public String accountBalance = "//div[text()='Email']/parent::div/following-sibling::div[1]//div";

    //Initial Invoice Objects
    public String invoiceDate = "//div[text()='Invoice Date']/following-sibling::div[1]";
    public String chargesBalance = "//div[@data-isinitial='1']//following-sibling::div/child::div[text()='Total']/following-sibling::div[1]";
    public String paymentsBalance = "//div[text()='Balance']/following-sibling::div";

    // Getter Methods
    public int getInvoiceCost() {
        Utilities.waitUntileElementIsVisible(invoiceCost);
        return Utilities.removeSpecialChars(invoiceCost);
    }

    public Integer getTotalValueCharges() {
        Utilities.waitUntileElementIsVisible(totalValueCharges);
        return Utilities.removeSpecialChars(totalValueCharges);
    }

    public Integer getTotalValuePayments() {
        Utilities.waitUntileElementIsVisible(totalPaymentCharges);
        return Utilities.removeSpecialChars(totalPaymentCharges);
    }

    public String checkPaymentStatus() {
        Utilities.waitUntileElementIsVisible(initialPaymentStatus);
        return Utilities.getElementTextValue(initialPaymentStatus, ElementType.XPath);

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

    // Setter

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
        FindElement.elementByAttribute(paymentAmountField, InputType.XPath).sendKeys(pAmount);
        Utilities.waitUntileElementIsVisible(confirmPymtAmtField);
        FindElement.elementByAttribute(confirmPymtAmtField, InputType.XPath).sendKeys(cAmount);
        Utilities.waitUntileElementIsVisible(custPaymentNotes);
        FindElement.elementByAttribute(custPaymentNotes, InputType.XPath).sendKeys("This is just a test");

    }

    public void setLimitedToSubscription(String needServiceName) {
        Utilities.clickElement(limitedToSubscription, ElementType.XPath);
        Utilities.clickElement("//label[contains (text(), '" + needServiceName + " Subscription Invoices')]", ElementType.XPath);
    }

    public void clickrecordPayment() {
        Utilities.waitUntileElementIsVisible(recordPayment);
        Utilities.clickElement(recordPayment, ElementType.XPath);
    }

    public void InvoiceAccountSummaryClick() {
        Utilities.waitUntileElementIsVisible(invoiceAccountSummaryClick);
        Utilities.clickElement(invoiceAccountSummaryClick, ElementType.XPath);
    }

    public void clickInvoice(String needServiceName) {
        Utilities.waitUntileElementIsVisible("//ul[@id='invoiceGroupListContainer']//div[contains(text(),'" + needServiceName + "')]");
        Utilities.clickElement("//ul[@id='invoiceGroupListContainer']//div[contains(text(),'" + needServiceName + "')]", ElementType.XPath);
    }

    public void clickInitialInvoice(){
        Utilities.waitUntileElementIsVisible(initialInvoice);
        Utilities.clickElement(initialInvoice, ElementType.XPath);
    }

    public String getAccountBalance(){
        Utilities.waitUntileElementIsVisible(accountBalance);
        return Utilities.getElementTextValue(accountBalance, ElementType.XPath);
    }

    public String getChargesBalance(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(chargesBalance, ElementType.XPath);
    }

    public String getPaymentsBalance(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(paymentsBalance, ElementType.XPath);
    }
}
