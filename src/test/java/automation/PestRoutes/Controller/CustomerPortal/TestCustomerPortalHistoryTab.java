package automation.PestRoutes.Controller.CustomerPortal;

import automation.PestRoutes.Controller.Customers.AppointmentsTab.TestScheduledAppointments;
import automation.PestRoutes.Controller.Reporting.TestTechNamePaymentsByServiceType;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.CustomerPortal.CustomerPortalHistoryTabPage;
import automation.PestRoutes.PageObject.CustomerPortal.CustomerPortalSummaryTabPage;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.List;

public class TestCustomerPortalHistoryTab {

    CustomerPortalSummaryTabPage userOnCustomerPortalSummaryTab = new CustomerPortalSummaryTabPage();
    CustomerPortalHistoryTabPage userOnCustomerPortalHistoryTab = new CustomerPortalHistoryTabPage();
    TestTechNamePaymentsByServiceType test = new TestTechNamePaymentsByServiceType();
    String expectedPaymentBalance = test.invoicePaymentBalance;
    String expectedFirstName = test.customerFirstName;
    String expectedAppointmentTabID = test.appointmentTabID;
    List<String> expectedSubscriptionServiceType = test.serviceType;

    @Then("I Verify First Name In The Welcome Message via History Tab")
    public void testFirstNameInWelcomeMessageHistoryTab(){
        userOnCustomerPortalHistoryTab = userOnCustomerPortalSummaryTab.goToHistoryTab();
        String actualMessage = userOnCustomerPortalHistoryTab.getFirstNameFromWelcomeBanner();
        Assert.assertTrue(actualMessage.contains(expectedFirstName),
                "Welcome Message Does Not Contain The Correct First Name");
    }

    @Then("I Verify The Responsible Balance via History Tab Matches The Invoice Balance")
    public void testResponsibleBalanceIsCorrectOnHistoryTab(){
        userOnCustomerPortalHistoryTab = userOnCustomerPortalSummaryTab.goToHistoryTab();
        String actualPaymentBalance = userOnCustomerPortalHistoryTab.getResponsibleBalance();
        Assert.assertEquals(actualPaymentBalance, expectedPaymentBalance,
                "Actual Responsible Balance: " + actualPaymentBalance +
                        " & Expected Balance: " + expectedPaymentBalance + " Do Not Match");
    }

    @Then("I Verify View Details Button Directs A User To The Billing Tab")
    public void testViewDetailsButtonDirectsUserToTheBillingTab() {
        userOnCustomerPortalHistoryTab = userOnCustomerPortalSummaryTab.goToHistoryTab();
        userOnCustomerPortalHistoryTab.clickViewDetailsButton();
        Assert.assertTrue(userOnCustomerPortalHistoryTab.isBillingTabActive(),
                "The Billing Tab Is Not Active After Clicking The View Details Button");
    }

    @Then("I Verify Service Type Is Correct In History Tab via Service Plan Section")
    public void testServiceTypeIsCorrectInHistoryTab() {
        userOnCustomerPortalHistoryTab = userOnCustomerPortalSummaryTab.goToHistoryTab();
        String actualServiceType = userOnCustomerPortalHistoryTab.getServiceType();
        String expectedServiceType = expectedSubscriptionServiceType.toString();
        Assert.assertTrue(expectedServiceType.contains(actualServiceType),
                "Service Plan Section Contains " + actualServiceType +
                        " And Does Not Contain A Service Type Labeled " + expectedServiceType);
    }

    @Then("I Verify The Service History Section Contains Accurate Information For Completed Appointment")
    public void testServiceHistorySectionContainsAccurateAppointmentInformation(){
        userOnCustomerPortalHistoryTab = userOnCustomerPortalSummaryTab.goToHistoryTab();
        String actualAppointmentID = userOnCustomerPortalHistoryTab.getAppointmentID();
        Assert.assertTrue(actualAppointmentID.contains(expectedAppointmentTabID),
                "The History Tab Does Not Contain Correct Appointment via Service History Section");
    }
}
