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
import automation.PestRoutes.Utilities.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

import static automation.PestRoutes.Utilities.Utilities.*;

public class TestCustomerPortalBillingTab {

    SoftAssert softAssert = new SoftAssert();
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
    public void testFirstNameInWelcomeMessageBillingTab() {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        String actualMessage = userOnCustomerPortalBillingTab.getFirstNameFromWelcomeBanner();
        softAssert.assertTrue(actualMessage.contains(expectedFirstName),
                "Welcome Message Does Not Contain The Correct First Name" + "\n" +
                        "First Name " + expectedFirstName + " Is Not Located In " + actualMessage);
        GetWebDriver.closeTab();
        GetWebDriver.switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify The Responsible Balance via Billing Tab Matches The Invoice Balance")
    public void testResponsibleBalanceIsCorrectOnBillingTab() {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        String actualPaymentBalance = userOnCustomerPortalBillingTab.getResponsibleBalance();
        softAssert.assertEquals(actualPaymentBalance, expectedPaymentBalance,
                "Actual Responsible Balance: " + actualPaymentBalance +
                        " & Expected Balance: " + expectedPaymentBalance + " Do Not Match");
        GetWebDriver.closeTab();
        GetWebDriver.switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify The Billing History Section via Billing Tab")
    public void testShowEntriesDropDownValuesInTheBillingTab() throws InterruptedException {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        List<String> actualShowEntryValues = userOnCustomerPortalBillingTab.getValuesFromShowNumberEntriesDropDown();
        List<String> expectedShowEntryValues = Arrays.asList("10", "25", "50", "100");
        softAssert.assertTrue(actualShowEntryValues.equals(expectedShowEntryValues),
                "The Show Entries Drop Down Does Not Contain Correct Values");
        softAssert.assertEquals(userOnCustomerPortalBillingTab.getNewDate(), "new Date",
                "The newDate Column Name Is Not Correct via Billing History Section");
        softAssert.assertEquals(userOnCustomerPortalBillingTab.getDescription(), "Description",
                "The Description Column Name Is Not Correct via Billing History Section");
        softAssert.assertEquals(userOnCustomerPortalBillingTab.getAccountInfo(), "Account Info",
                "The Account Info Column Name Is Not Correct via Billing History Section");
        softAssert.assertEquals(userOnCustomerPortalBillingTab.getCharge(), "Charge",
                "The Charge Column Name Is Not Correct via Billing History Section");
        softAssert.assertEquals(userOnCustomerPortalBillingTab.getBalance(), "Balance",
                "The Balance Column Name Is Not Correct via Billing History Section");
        softAssert.assertEquals(userOnCustomerPortalBillingTab.getPayment(), "Payment",
                "The Payment Column Name Is Not Correct via Billing History Section");
        softAssert.assertEquals(userOnCustomerPortalBillingTab.getTicketIDNumber(), "#",
                "The # Name Is Not Correct via Billing History Section");
        softAssert.assertEquals(userOnCustomerPortalBillingTab.isSearchFieldDisplayed(), true,
                "The Search Field Is Not Displayed On The Billing Tab");
        softAssert.assertEquals(userOnCustomerPortalBillingTab.isShowingNumberOfEntriesDisplayed(), true,
                "The Showing # to # of # entries Verbiage Is Not Displayed via Billing Tab");
        softAssert.assertEquals(userOnCustomerPortalBillingTab.isPreviousLinkDisplayed(), true,
                "The Previous Link Is Not Displayed via Billing Tab");
        softAssert.assertEquals(userOnCustomerPortalBillingTab.isNextLinkDisplayed(), true,
                "The Next Link Is Not Displayed via Billing Tab");
        GetWebDriver.closeTab();
        GetWebDriver.switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify The Service Type-Amount Is Correct via Current Section")
    public void testServiceTypeIsCorrectInTheCurrentSection() throws InterruptedException {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        String expectedServiceType = expectedSubscriptionServiceType.toString()
                .replace("[", "").replace("]","");
        softAssert.assertTrue(userOnCustomerPortalBillingTab.getCurrentSectionText().contains(expectedServiceType),
              "The Current Section Does Not Contain Correct Service Type");
        softAssert.assertTrue(userOnCustomerPortalBillingTab.getCurrentSectionText().contains(expectedCurrentSubscriptionAmount),
              "The Current Section Does Not Contain Correct Amount");
        GetWebDriver.closeTab();
        GetWebDriver.switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify An Error Shows Up When Selecting Pay Total Amount Due Without Selecting a Payment Method")
    public void testTableColumnNamesInTheBillingTab() {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        userOnCustomerPortalBillingTab.clickPayNowButton();
        userOnCustomerPortalBillingTab.clickMakePaymentButton();
        String paymentMethodError = userOnCustomerPortalBillingTab.getPaymentMethodErrorMessage();
        softAssert.assertTrue(paymentMethodError.contains("Please choose a method of payment"),
                "The Payment Method Error Message Is Not Available Or Not Correct");
        GetWebDriver.closeTab();
        GetWebDriver.switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify An Error Shows Up When Selecting Pay Another Amount Without Selecting a Payment Method")
    public void testSearchFieldInTheBillingTab() {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        userOnCustomerPortalBillingTab.clickPayNowButton();
        userOnCustomerPortalBillingTab.clickPayAnotherAmount();
        userOnCustomerPortalBillingTab.clickMakePaymentButton();
        String paymentMethodError = userOnCustomerPortalBillingTab.getPaymentMethodErrorMessage();
        softAssert.assertTrue(paymentMethodError.contains("Please choose a method of payment"),
                "The Payment Method Error Message Is Not Available Or Not Correct");
        GetWebDriver.closeTab();
        GetWebDriver.switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify The Billing Tab Has An Image-Link To Make A Payment")
    public void testBillingTabHasMakePaymentImageLink() {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        softAssert.assertTrue(userOnCustomerPortalBillingTab.isMakePaymentImageDisplayed(),
                "The Make Payment Image Is Not Displayed via Billing Tab");
        GetWebDriver.closeTab();
        GetWebDriver.switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify All Of The Required Fields To Pay Total Amount Using A One Time Card For Each {string}")
    public void testAllOfTheRequiredFieldsToPayTotalAmountUsingOneTimeCard(String gateway) {
        userOnCustomerPortalBillingTab = userOnCustomerPortalSummaryTab.goToBillingTab();
        userOnCustomerPortalBillingTab.clickPayNowButton();
        userOnCustomerPortalBillingTab.clickUseOneTimeCard();
        userOnCustomerPortalBillingTab.clickMakePaymentButton();
        softAssert.assertTrue(userOnCustomerPortalBillingTab.isPayTotalCardNumberErrorDisplayed(gateway),
                "Card Number Does Not Have A Required Field Error Message");
        softAssert.assertTrue(userOnCustomerPortalBillingTab.isPayTotalExpirationDateErrorDisplayed(gateway),
                "Expiration Date Does Not Have A Required Field Error Message");
        softAssert.assertTrue(userOnCustomerPortalBillingTab.isPayTotalCVVErrorDisplayed(gateway),
                "CVV Does Not Have A Required Field Error Message");
        GetWebDriver.closeTab();
        GetWebDriver.switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Given("I Set Up The Merchant Info For Credit Card {string}")
    public void automateSettingUpTheMerchantInfoForCreditCardGateway (String gateway) {
        userOnAdminComponent = userOnDashboard.goToAdminComponent();
        userOnAdminComponent.clickPreferencesSubComponent();
        userOnPreferences.clickMerchantInfo();
        userOnMerchantInfo.clickEditForDefaultSettings();
        userOnMerchantInfo.selectCreditCardGateway(gateway);
        userOnMerchantInfo.clickSaveForDefaultSettings();
    }

    @Given("I Set Up The Merchant Info For ACH {string}")
    public void automateSettingUpTheMerchantInfoForACHGateway (String gateway) {
        userOnAdminComponent = userOnDashboard.goToAdminComponent();
        userOnAdminComponent.clickPreferencesSubComponent();
        userOnPreferences.clickMerchantInfo();
        userOnMerchantInfo.clickEditForDefaultSettings();
        userOnMerchantInfo.selectACHGateway(gateway);
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
        String actualPaymentBalance = userOnCustomerPortalBillingTab.getResponsibleBalance();
        softAssert.assertEquals(actualPaymentBalance, "$0.00",
                "Actual Responsible Balance: " + actualPaymentBalance +
                        " & Expected Balance: " + "$0.00" + " Do Not Match");
        GetWebDriver.closeTab();
        GetWebDriver.switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
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
        String actualPaymentBalance = userOnCustomerPortalBillingTab.getResponsibleBalance();
        softAssert.assertEquals(actualPaymentBalance, "$0.00",
                "Actual Responsible Balance: " + actualPaymentBalance +
                        " & Expected Balance: " + "$0.00" + " Do Not Match");
        GetWebDriver.closeTab();
        GetWebDriver.switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }
}