package automation.PestRoutes.Controller.Renewal;

import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.Invoice_Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static automation.PestRoutes.Utilities.Utilities.acceptAlert;

public class TestOneTimePayment {

    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    RoutePageInvoicing userOnInvoicesTab = new RoutePageInvoicing();
    Invoice_Header userSelectsPayment = new Invoice_Header();
    InvoiceImplementation userMakesPayment = new InvoiceImplementation();
    CustomerViewDialog_SubscriptionTab userOnSubscriptionTab = new CustomerViewDialog_SubscriptionTab();

    String paymentAmount;
    String invoiceRenewalDate;
    String subscriptionRenewalDate;

    @When("I Process A One Time Single Use Card Payment On A Renewal Subscription")
    public void automateProcessingSingleUseCardPaymentOnARenewalSubscription() throws InterruptedException {
        userOnInvoicesTab = sameUser.goToInvoicesTab();
        userOnInvoicesTab.addPayment();
        userSelectsPayment.clickCard();
        paymentAmount = userMakesPayment.getPaymentAmount();
        userMakesPayment.typeConfirmationAmount(paymentAmount);
        userMakesPayment.selectLimitedToSubscription();
        userMakesPayment.typeAddress("1234 Testers Boulevard");
        invoiceRenewalDate = userMakesPayment.getRenewalDate();
        userMakesPayment.clickChargeSingleCardButton();
        acceptAlert();
        userMakesPayment.typeCreditCardNumber("4242424242424242");
        userMakesPayment.selectExpirationDate("12", "2034");
        userMakesPayment.typeCVV("123");
        userMakesPayment.clickProcessTransactionButton();
        acceptAlert();
        sameUser.switchToCustomerCard();
    }

    @Then("I See All Of The Renewal Tasks")
    public void iSeeAllOfTheRenewalTasks() throws InterruptedException {
        userOnInvoicesTab.clickBackToAccountSummaryButton();
        Assert.assertTrue(userOnInvoicesTab.getSuccessApprovedNote().contains("Success"),
                "The Payment Transaction Was Not A Success" );
        userOnSubscriptionTab = sameUser.goToSubscriptionTab();
        userOnSubscriptionTab.clickActiveSubscription();
        subscriptionRenewalDate = userOnSubscriptionTab.getSubscriptionRenewalDate();
        Assert.assertEquals(subscriptionRenewalDate, invoiceRenewalDate,
                "The Renewal Date Is Not Correct On The Subscription Tab");
    }
}
