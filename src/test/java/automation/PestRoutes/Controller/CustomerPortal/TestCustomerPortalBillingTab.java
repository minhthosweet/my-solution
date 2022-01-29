package automation.PestRoutes.Controller.CustomerPortal;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Customers.AppointmentsTab.TestScheduledAppointments;
import automation.PestRoutes.Controller.Invoicing.InvoicingTab;
import automation.PestRoutes.Controller.Subscriptions.AddSubscription;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.MerchantInfoTab.MarchantInfoPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.CustomerOverview.BillingPage;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerPortal.CustomerPortalBillingTabPage;
import automation.PestRoutes.PageObject.CustomerPortal.CustomerPortalSummaryTabPage;
import automation.PestRoutes.PageObject.DashboardPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

import static automation.PestRoutes.Utilities.Utilities.*;

public class TestCustomerPortalBillingTab {

    DashboardPage userOnDashboard = new DashboardPage();
    CustomerPortalSummaryTabPage userOnCustomerPortalSummaryTab = new CustomerPortalSummaryTabPage();
    CustomerPortalBillingTabPage userOnCustomerPortalBillingTab = new CustomerPortalBillingTabPage();
    AdminMainPage userOnAdminComponent = new AdminMainPage();
    PreferencesPage userOnPreferences = new PreferencesPage();
    MarchantInfoPage userOnMerchantInfo = new MarchantInfoPage();
    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    BillingPage userOnBillingTab = new BillingPage();
    AddSubscription testSubscription = new AddSubscription();
    CreateNewCustomer testCustomer = new CreateNewCustomer();
    InvoicingTab testInvoice = new InvoicingTab();
    TestScheduledAppointments testAppointment = new TestScheduledAppointments();
    String expectedFirstName = testCustomer.customerFirstName;
    String expectedPaymentBalance = testInvoice.invoicePaymentBalance;
    List<String> expectedSubscriptionServiceType = testSubscription.serviceType;
    String expectedCurrentSubscriptionAmount = testAppointment.initialBalance;

    @Then("I Verify First Name In The Welcome Message via Billing Tab")
    public void testFirstNameInWelcomeMessageBillingTab() throws InterruptedException {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        String actualMessage = userOnCustomerPortalBillingTab.getFirstNameFromWelcomeBanner();
        Assert.assertTrue(actualMessage.contains(expectedFirstName),
                "Welcome Message Does Not Contain The Correct First Name" + "\n" +
                        "First Name " + expectedFirstName + " Is Not Located In " + actualMessage);
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify The Responsible Balance via Billing Tab Matches The Invoice Balance")
    public void testResponsibleBalanceIsCorrectOnBillingTab() throws InterruptedException {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        String actualPaymentBalance = userOnCustomerPortalBillingTab.getResponsibleBalance();
        Assert.assertEquals(actualPaymentBalance, expectedPaymentBalance,
                "Actual Responsible Balance: " + actualPaymentBalance +
                        " & Expected Balance: " + expectedPaymentBalance + " Do Not Match");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify The Billing History Section via Billing Tab")
    public void testShowEntriesDropDownValuesInTheBillingTab() throws InterruptedException {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        List<String> actualShowEntryValues = userOnCustomerPortalBillingTab.getValuesFromShowNumberEntriesDropDown();
        List<String> expectedShowEntryValues = Arrays.asList("10", "25", "50", "100");
        Assert.assertTrue(actualShowEntryValues.equals(expectedShowEntryValues),
                "The Show Entries Drop Down Does Not Contain Correct Values");
        Assert.assertEquals(userOnCustomerPortalBillingTab.getNewDate(), "new Date",
                "The newDate Column Name Is Not Correct via Billing History Section");
        Assert.assertEquals(userOnCustomerPortalBillingTab.getDescription(), "Description",
                "The Description Column Name Is Not Correct via Billing History Section");
        Assert.assertEquals(userOnCustomerPortalBillingTab.getAccountInfo(), "Account Info",
                "The Account Info Column Name Is Not Correct via Billing History Section");
        Assert.assertEquals(userOnCustomerPortalBillingTab.getCharge(), "Charge",
                "The Charge Column Name Is Not Correct via Billing History Section");
        Assert.assertEquals(userOnCustomerPortalBillingTab.getBalance(), "Balance",
                "The Balance Column Name Is Not Correct via Billing History Section");
        Assert.assertEquals(userOnCustomerPortalBillingTab.getPayment(), "Payment",
                "The Payment Column Name Is Not Correct via Billing History Section");
        Assert.assertEquals(userOnCustomerPortalBillingTab.getTicketIDNumber(), "#",
                "The # Name Is Not Correct via Billing History Section");
        Assert.assertEquals(userOnCustomerPortalBillingTab.isSearchFieldDisplayed(), true,
                "The Search Field Is Not Displayed On The Billing Tab");
        Assert.assertEquals(userOnCustomerPortalBillingTab.isShowingNumberOfEntriesDisplayed(), true,
                "The Showing # to # of # entries Verbiage Is Not Displayed via Billing Tab");
        Assert.assertEquals(userOnCustomerPortalBillingTab.isPreviousLinkDisplayed(), true,
                "The Previous Link Is Not Displayed via Billing Tab");
        Assert.assertEquals(userOnCustomerPortalBillingTab.isNextLinkDisplayed(), true,
                "The Next Link Is Not Displayed via Billing Tab");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify The Service Type-Amount Is Correct via Current Section")
    public void testServiceTypeIsCorrectInTheCurrentSection() throws InterruptedException {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        String expectedServiceType = expectedSubscriptionServiceType.toString()
                .replace("[", "").replace("]","");
        Assert.assertTrue(userOnCustomerPortalBillingTab.getCurrentSectionText().contains(expectedServiceType),
              "The Current Section Does Not Contain Correct Service Type");
        Assert.assertTrue(userOnCustomerPortalBillingTab.getCurrentSectionText().contains(expectedCurrentSubscriptionAmount),
              "The Current Section Does Not Contain Correct Amount");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify An Error Shows Up When Selecting Pay Total Amount Due Without Selecting a Payment Method")
    public void testTableColumnNamesInTheBillingTab() throws InterruptedException {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        userOnCustomerPortalBillingTab.clickPayNowButton();
        userOnCustomerPortalBillingTab.clickMakePaymentButton();
        String paymentMethodError = userOnCustomerPortalBillingTab.getPaymentMethodErrorMessage();
        Assert.assertTrue(paymentMethodError.contains("Please choose a method of payment"),
                "The Payment Method Error Message Is Not Available Or Not Correct");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify An Error Shows Up When Selecting Pay Another Amount Without Selecting a Payment Method")
    public void testSearchFieldInTheBillingTab() {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        userOnCustomerPortalBillingTab.clickPayNowButton();
        userOnCustomerPortalBillingTab.clickPayAnotherAmount();
        userOnCustomerPortalBillingTab.clickMakePaymentButton();
        String paymentMethodError = userOnCustomerPortalBillingTab.getPaymentMethodErrorMessage();
        Assert.assertTrue(paymentMethodError.contains("Please choose a method of payment"),
                "The Payment Method Error Message Is Not Available Or Not Correct");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify The User Can Update Phone - Make Payment")
    public void testFooterInTheBillingHistorySection() throws InterruptedException {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        Assert.assertEquals(userOnCustomerPortalBillingTab.isUpdatePhoneImageDisplayed(),true,
                "The Update Phone Image Is Not Displayed via Billing Tab");
        Assert.assertEquals(userOnCustomerPortalBillingTab.isMakePaymentImageDisplayed(),true,
                "The Make Payment Image Is Not Displayed via Billing Tab");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify All Of The Required Fields To Pay Total Amount Using A One Time Card For Each {string}")
    public void testAllOfTheRequiredFieldsToPayTotalAmountUsingOneTimeCard(String gateway) {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        userOnCustomerPortalBillingTab.clickPayNowButton();
        userOnCustomerPortalBillingTab.clickUseOneTimeCard();
        userOnCustomerPortalBillingTab.clickMakePaymentButton();
        Assert.assertTrue(userOnCustomerPortalBillingTab.isPayTotalCardNumberErrorDisplayed(gateway),
                "Card Number Does Not Have A Required Field Error Message");
        Assert.assertTrue(userOnCustomerPortalBillingTab.isPayTotalExpirationDateErrorDisplayed(gateway),
                "Expiration Date Does Not Have A Required Field Error Message");
        Assert.assertTrue(userOnCustomerPortalBillingTab.isPayTotalCVVErrorDisplayed(gateway),
                "CVV Does Not Have A Required Field Error Message");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Given("I Set Up The Application For {string}")
    public void automateSettingUpTheApplicationForEachGateway (String gateway) {
        userOnAdminComponent = userOnDashboard.goToAdminComponent();
        userOnAdminComponent.clickPreferencesSubComponent();
        userOnPreferences.clickMerchantInfo();
        userOnMerchantInfo.clickEditForDefaultSettings();
        userOnMerchantInfo.selectCreditCardGateway(gateway);
        userOnMerchantInfo.clickSaveForDefaultSettings();
    }

    @Then("I Verify Paying The Total Amount With A One Time Card Using {string}, {string}, {string}, {string}")
    public void testPayingTheTotalAmountWithOneTimeCard (String gateway, String creditCardNumber, String expirationDate, String cvv) throws InterruptedException {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        userOnCustomerPortalBillingTab.clickPayNowButton();
        userOnCustomerPortalBillingTab.clickUseOneTimeCard();
        userOnCustomerPortalBillingTab.enterNewCardInformation(gateway, creditCardNumber, expirationDate, cvv);
        userOnCustomerPortalBillingTab.clickMakePaymentButton();
        acceptAlert();
        Assert.assertEquals(userOnCustomerPortalBillingTab.isPayNowButtonDisplayed(), true,
                "The Pay Now Button Is Not Displayed");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @When("I Add A Card On File Using {string}, {string}, {string}, {string}")
    public void automateAddingCardOnFile (String gateway, String creditCardNumber, String expirationDate, String cvv) {
        userOnBillingTab = sameUser.goToBillingTab();
        userOnBillingTab.clickAddPaymentMethod();
        userOnBillingTab.clickCreditCardButton();
        userOnBillingTab.enterNewCardInformation(gateway, creditCardNumber, expirationDate, cvv);
    }

    @Then("I Verify Paying The Total Amount With A Card On File")
    public void testPayingTheTotalAmountWithCardOnFile () {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        userOnCustomerPortalBillingTab.clickPayNowButton();
        userOnCustomerPortalBillingTab.clickCardOnFile();
        userOnCustomerPortalBillingTab.clickMakePaymentButton();
        acceptAlert();
        Assert.assertTrue(userOnCustomerPortalBillingTab.isPayNowButtonDisplayed(), "The Pay Now Button Is Not Displayed");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }
}