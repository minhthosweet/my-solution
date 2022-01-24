package automation.PestRoutes.Controller.Renewal;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.Invoice_Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.Utilities.Utilities;
import static automation.PestRoutes.Utilities.Utilities.currentDate;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static automation.PestRoutes.Utilities.GetDate.addOneYearToDate;
import static automation.PestRoutes.Utilities.Utilities.acceptAlert;

public class TestOneTimePayment {

    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    RoutePageInvoicing userOnInvoicesTab = new RoutePageInvoicing();
    Invoice_Header userSelectsPayment = new Invoice_Header();
    InvoiceImplementation userMakesPayment = new InvoiceImplementation();
    CustomerViewDialog_SubscriptionTab userOnSubscriptionTab = new CustomerViewDialog_SubscriptionTab();
    CreateNewCustomer testCustomer = new CreateNewCustomer();



    @When("I Process A One Time Single Use Card Payment On A Renewal Subscription Using {string}, {string}, {string}, {string}")
    public void automateProcessingSingleUseCardPaymentOnARenewalSubscription(String gateway, String creditCardNumber, String expirationDate, String cvv) {
        String address = testCustomer.propertyAddress;
        String paymentAmount;

        userOnInvoicesTab = sameUser.goToInvoicesTab();
        userOnInvoicesTab.addPayment();
        userSelectsPayment.clickCard();
        paymentAmount = userMakesPayment.getPaymentAmount();
        userMakesPayment.typeConfirmationAmount(paymentAmount);
        userMakesPayment.selectLimitedToSubscription();
        userMakesPayment.typeAddress(address);
        userMakesPayment.enterNewCardInformation(gateway, creditCardNumber, expirationDate, cvv);
        acceptAlert();
    }

    @Then("I See The Subscription Renewal Date Move Forward After Making Single Use Card Payment")
    public void testSubscriptionRenewalDateTask() {
        String actualSubscriptionRenewalDate;
        String expectedSubscriptionRenewalDate = addOneYearToDate(currentDate("MM/dd/yyyy"));

        userOnInvoicesTab.clickBackToAccountSummaryButton();
        userOnSubscriptionTab = sameUser.goToSubscriptionTab();
        userOnSubscriptionTab.clickActiveSubscription();
        actualSubscriptionRenewalDate = userOnSubscriptionTab.getSubscriptionRenewalDate();
        Assert.assertEquals(actualSubscriptionRenewalDate, expectedSubscriptionRenewalDate,
                "\n Actual Renewal Date: " + actualSubscriptionRenewalDate +
                        "\n Expected Renewal Date: " + expectedSubscriptionRenewalDate +
                        "\n The Subscription Actual Renewal Date & Expected Renewal Date Does Not Match");
        testCustomer.removeCustomer();
    }
}
