package automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreditCard;

public class CreditCardConfirmationPage {

    public String paymentResultTitle = "//form[@id='singlePaymentForm']//h3";
    public String confirmationMessage = "//form[@id='singlePaymentForm']//h2[1]";
    public String singleCardPaymentResult = "//div[@id='billingPanel']//h2[1]";
}
