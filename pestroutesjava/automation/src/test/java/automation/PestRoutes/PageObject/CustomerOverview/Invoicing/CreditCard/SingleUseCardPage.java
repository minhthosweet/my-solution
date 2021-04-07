package automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreditCard;

public class SingleUseCardPage {

    public String paymentAmountField = "//input[@name='amount']";
    public String confirmAmountField = "//input[@name='confirmCashAmount']";
    public String notes = "//div[text()='Customer Payment Notes: ']";
    public String chargeSingleCard = "//input[@id='chargeSingleCardButton']";
    //Braintree
    public String payingWithCardButton = "//div[@aria-label='Paying with Card']";
    public String brainTreeCcIframe = "braintree-hosted-field-number";
    public String brainTreeExpIframe = "braintree-hosted-field-expirationDate";
    public String ccNumberField = "//input[@id='credit-card-number']";
    public String ccExpField = "//input[@id='expiration']";

    //Element
    public String elementIframe = "elementSingleFrame";
    public String elementCcInputField = "//input[@name='cardNumber']";
    public String elementExpMonthDropdown = "//select[@id='ddlExpirationMonth']";
    public String elementExpYearDropdown = "//select[@id='ddlExpirationYear']";
    public String elementCvvInputField = "//input[@id='CVV']";
    public String elementProcessTransactionButton = "//a[@id='submit']";

    //Spreedly
    public String spreedlyOneTimeCcNumberIframe = "//div[@id='spreedly-onetime-number']/iframe";
    public String spreedlyOneTimeCvvIframe = "//div[@id='spreedly-onetime-cvv']/iframe";
    public String spreedlyOneTimeCardNumberInputField = "//input[@name='card_number']";
    public String spreedlyOneTimeCvvInputField = "//input[@id='cvv']";
    public String spreedlyOneTimeExpMonthDropdown = "//select[@name='expMonth']";
    public String spreedlyOneTimeExpYearDropdown = "//select[@name='expYear']";

    //NMI
    public String nmiChargeSingleCardButton = "//div[@id='renderSecureCardFormButtonNMI']";
    public String nmiIframe = "CollectJSIframe";
    public String nmiCcNumberInputField = "//input[@id='cc-number']";
    public String nmiCcExpInputField = "//input[@id='cc-exp']";
    public String nmiCvvInputField = "//input[@id='cc-cvv']";
    public String nmiSubmitPaymentButton = "//button[@id='submit-payment']";

    //PestRoutes payment
    public String payrixCcIframe = "payFields-iframe-number";
    public String payrixExpIframe = "payFields-iframe-expiration";
    public String payrixCvvIframe = "payFields-iframe-cvv";
    public String payrixCcNumberInputField = "//input[@id='payment_number']";
    public String payrixExpInputField = "//input[@id='expiration']";
    public String payrixCvvInputField = "//input[@id='payment_cvv']";

}
