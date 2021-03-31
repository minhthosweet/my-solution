package automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreditCard;

public class CardOnFile {

    public String paymentAmountInputField = "//input[@name='amount']";
    public String confirmAmountInputField = "//input[@name='confirmCashAmount']";
    public String cvvCodeInputField = "//input[@name='creditCardCode']";
    public String customerPaymentNotesInputField = "";
    public String limitedToCustomerDropdown = "";
    public String limitedToSubscriptionInputField = "";
    public String chargeCardButton = "//div[@id='chargeCardButton']";
}
