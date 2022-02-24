package automation.PestRoutes.Controller.Renewal;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.Invoice_Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import static automation.PestRoutes.Utilities.Utilities.*;

public class TestOneTimePayment {

    SoftAssert softAssert = new SoftAssert();
    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    RoutePageInvoicing userOnInvoicesTab = new RoutePageInvoicing();
    Invoice_Header userSelectsPayment = new Invoice_Header();
    InvoiceImplementation userMakesPayment = new InvoiceImplementation();
    CustomerViewDialog_SubscriptionTab userOnSubscriptionTab = new CustomerViewDialog_SubscriptionTab();
    CreateNewCustomer testCustomer = new CreateNewCustomer();

    String paymentAmount;
    String invoiceRenewalDate;

    @When("I Process A One Time Single Use Card Payment On A Renewal Subscription Using {string}, {string}, {string}, {string}")
    public void automateProcessingSingleUseCardPaymentOnARenewalSubscription(String gateway, String creditCardNumber, String expirationDate, String cvv) {
        String address = testCustomer.propertyAddress;
        userOnInvoicesTab = sameUser.goToInvoicesTab();
        userOnInvoicesTab.addPayment();
        userSelectsPayment.clickCard();
        paymentAmount = userMakesPayment.getPaymentAmount();
        userMakesPayment.typeConfirmationAmount(paymentAmount);
        userMakesPayment.selectLimitedToSubscription();
        userMakesPayment.typeAddress(address);
        invoiceRenewalDate = userMakesPayment.getRenewalDate();
        userMakesPayment.enterNewCardInformation(gateway, creditCardNumber, expirationDate, cvv);
        acceptAlert();
    }

    @Then("I See The Subscription Renewal Date Move Forward After Making Single Use Card Payment For Each {string}")
    public void testSubscriptionRenewalDateTask(String gateway) {
        acceptAlert();
        userOnInvoicesTab.clickBackToAccountSummaryButton();
        userOnSubscriptionTab = sameUser.goToSubscriptionTab();
        userOnSubscriptionTab.clickActiveSubscription();
        String subscriptionRenewalDate = userOnSubscriptionTab.getSubscriptionRenewalDate();
        System.out.println(gateway + " Subscription");
        System.out.println("\t Expected Renewal Date: " + invoiceRenewalDate);
        System.out.println("\t Actual Renewal Date:   " + subscriptionRenewalDate);
        softAssert.assertEquals(subscriptionRenewalDate, invoiceRenewalDate,
                "\n Actual Renewal Date:   " + subscriptionRenewalDate +
                        "\n Expected Renewal Date: " + invoiceRenewalDate +
                        "\n The Subscription Actual Renewal Date & Expected Renewal Date Does Not Match \n");
        softAssert.assertAll();
        testCustomer.removeCustomer();
    }
}
