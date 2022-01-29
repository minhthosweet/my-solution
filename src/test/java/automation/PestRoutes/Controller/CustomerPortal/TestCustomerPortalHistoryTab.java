package automation.PestRoutes.Controller.CustomerPortal;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Customers.AppointmentsTab.TestScheduledAppointments;
import automation.PestRoutes.Controller.Invoicing.InvoicingTab;
import automation.PestRoutes.Controller.Subscriptions.AddSubscription;
import automation.PestRoutes.PageObject.CustomerPortal.CustomerPortalHistoryTabPage;
import automation.PestRoutes.PageObject.CustomerPortal.CustomerPortalSummaryTabPage;
import io.cucumber.java.en.Then;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static automation.PestRoutes.Utilities.Utilities.closeTab;
import static automation.PestRoutes.Utilities.Utilities.switchToOldWindowOpened;

public class TestCustomerPortalHistoryTab {

    SoftAssert softAssert = new SoftAssert();
    CustomerPortalSummaryTabPage userOnCustomerPortalSummaryTab = new CustomerPortalSummaryTabPage();
    CustomerPortalHistoryTabPage userOnCustomerPortalHistoryTab = new CustomerPortalHistoryTabPage();
    TestScheduledAppointments testAppointment = new TestScheduledAppointments();
    InvoicingTab testInvoice = new InvoicingTab();
    CreateNewCustomer testCustomer = new CreateNewCustomer();
    AddSubscription testSubscription = new AddSubscription();
    String expectedPaymentBalance = testInvoice.invoicePaymentBalance;
    String expectedFirstName = testCustomer.customerFirstName;
    String expectedAppointmentTabID = testAppointment.appointmentTabID;
    List<String> expectedSubscriptionServiceType = testSubscription.serviceType;

    @Then("I Verify First Name In The Welcome Message via History Tab")
    public void testFirstNameInWelcomeMessageHistoryTab() {
        userOnCustomerPortalHistoryTab = userOnCustomerPortalSummaryTab.goToHistoryTab();
        String actualMessage = userOnCustomerPortalHistoryTab.getFirstNameFromWelcomeBanner();
        softAssert.assertTrue(actualMessage.contains(expectedFirstName),
                "Welcome Message Does Not Contain The Correct First Name");
        softAssert.assertAll();
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify The Responsible Balance via History Tab Matches The Invoice Balance")
    public void testResponsibleBalanceIsCorrectOnHistoryTab(){
        userOnCustomerPortalHistoryTab = userOnCustomerPortalSummaryTab.goToHistoryTab();
        String actualPaymentBalance = userOnCustomerPortalHistoryTab.getResponsibleBalance();
        softAssert.assertEquals(actualPaymentBalance, expectedPaymentBalance,
                "Actual Responsible Balance: " + actualPaymentBalance +
                        " & Expected Balance: " + expectedPaymentBalance + " Do Not Match");
        softAssert.assertAll();
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify View Details Button Directs A User To The Billing Tab")
    public void testViewDetailsButtonDirectsUserToTheBillingTab() {
        userOnCustomerPortalHistoryTab = userOnCustomerPortalSummaryTab.goToHistoryTab();
        userOnCustomerPortalHistoryTab.clickViewDetailsButton();
        softAssert.assertTrue(userOnCustomerPortalHistoryTab.isBillingTabActive(),
                "The Billing Tab Is Not Active After Clicking The View Details Button");
        softAssert.assertAll();
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify Service Type Is Correct In History Tab via Service Plan Section")
    public void testServiceTypeIsCorrectInHistoryTab() {
        userOnCustomerPortalHistoryTab = userOnCustomerPortalSummaryTab.goToHistoryTab();
        String actualServiceType = userOnCustomerPortalHistoryTab.getServiceType();
        String expectedServiceType = expectedSubscriptionServiceType.toString();
        softAssert.assertTrue(expectedServiceType.contains(actualServiceType),
                "Service Plan Section Contains " + actualServiceType +
                        " And Does Not Contain A Service Type Labeled " + expectedServiceType);
        softAssert.assertAll();
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify The Service History Section Contains Accurate Information For Completed Appointment")
    public void testServiceHistorySectionContainsAccurateAppointmentInformation() {
        userOnCustomerPortalHistoryTab = userOnCustomerPortalSummaryTab.goToHistoryTab();
        String actualAppointmentID = userOnCustomerPortalHistoryTab.getAppointmentID();
        softAssert.assertTrue(actualAppointmentID.contains(expectedAppointmentTabID),
                "The History Tab Does Not Contain Correct Appointment via Service History Section");
        softAssert.assertAll();
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }
}
