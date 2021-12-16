package automation.PestRoutes.Controller.CustomerPortal;

import automation.PestRoutes.Controller.Reporting.TestTechNamePaymentsByServiceType;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Admin;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.PageObject.CustomerPortal.CustomerPortalSummaryTabPage;
import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.PageObject.RoutePage.RoutePage;
import automation.PestRoutes.PageObject.Scheduling.SchedulingAppointmentDialog;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class TestCustomerPortalSummaryTab {
    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    CustomerViewDialog_Admin userOnAdminTab = new CustomerViewDialog_Admin();
    CustomerPortalSummaryTabPage userOnCustomerPortalSummaryTab = new CustomerPortalSummaryTabPage();
    TestTechNamePaymentsByServiceType test = new TestTechNamePaymentsByServiceType();
    CustomerViewDialog_SubscriptionTab userOnSubscriptionTab = new CustomerViewDialog_SubscriptionTab();
    DashboardPage userOnDashboard = new DashboardPage();
    SchedulingTab userOnSchedulingComponent = new SchedulingTab();
    RoutePage userOnRoutePage = new RoutePage();
    SchedulingAppointmentDialog userOnSchedulingDialog = new SchedulingAppointmentDialog();
    CustomerviewDialog_AppointmentsTab userOnAppointmentsTab = new CustomerviewDialog_AppointmentsTab();
    RoutePageInvoicing userOnInvoicesTab = new RoutePageInvoicing();

    String expectedFirstName = test.customerFirstName;
    String customerName = test.customerFullName;
    String expectedPropertyAddress = test.propertyAddress;
    String expectedCityStateZip = test.cityStateZip;
    String expectedEmailAddress = test.emailAddress;
    String expectedCustomerAccountID = test.customerAccountID;
    String expectedServiceNotes = test.serviceNotes;
    String expectedPaymentBalance = test.invoicePaymentBalance;
    String expectedTechnician = test.techName;
    String expectedInvoiceNumber = test.invoiceNumber;
    List<String> expectedSubscriptionServiceType = test.serviceType;
    List<String> schedulingSubscription;
    String expectedDate = Utilities.currentDate("MM/dd/yy");

    @When("I Navigate To Customer Portal From Customer Card - Admin Tab")
    public void automateNavigatingFromCustomerCardToCustomerPortal() throws InterruptedException {
        sameUser.goToAdminTab();
        userOnCustomerPortalSummaryTab = userOnAdminTab.clickPortalLogin();
    }

    @Then("I Verify First Name In The Welcome Message")
    public void testFirstNameInWelcomeMessage(){
        String actualMessage = userOnCustomerPortalSummaryTab.getFirstNameFromWelcomeBanner();
        Assert.assertTrue(actualMessage.contains(expectedFirstName),
                "Welcome Message Does Not Contain The Correct First Name");
    }

    @Then("I Verify The Property Details Section")
    public void testPropertyDetailsSection() {
        String actualPropertyDetails = userOnCustomerPortalSummaryTab.getPropertyDetails();

        Assert.assertTrue(actualPropertyDetails.contains(expectedCustomerAccountID),
                "Expected Account ID Is Not Contained In The Property Details Section");
        Assert.assertTrue(actualPropertyDetails.contains(expectedPropertyAddress),
                "Expected Property Address Is Not Contained In The Property Details Section");
        Assert.assertTrue(actualPropertyDetails.contains(expectedCityStateZip),
                "Expected City, State, and Zip Is Not Contained In The Property Details Section");
        Assert.assertTrue(actualPropertyDetails.contains(expectedEmailAddress),
                "Expected Email Address Is Not Contained In The Property Details Section");
    }

    @Then("I Verify Share The Love Message")
    public void testShareTheLoveMessage() {
        String actualShareTheLoveMessage = userOnCustomerPortalSummaryTab.getShareTheLoveInformation();
        Assert.assertTrue(actualShareTheLoveMessage.equalsIgnoreCase(
                "Share The Love"),
                "The Actual And Expected Share The Love Text Do Not Match");
    }

    @Then("I Verify Service Type Is Correct via Service Plan Section")
    public void testServiceTypeIsCorrectInSummaryTab() {
        String actualServiceType = userOnCustomerPortalSummaryTab.getServiceType();
        String expectedServiceType = expectedSubscriptionServiceType.toString();
        Assert.assertTrue(expectedServiceType.contains(actualServiceType),
                "Service Plan Section Contains " + actualServiceType +
                        " And Does Not Contain A Service Type Labeled " + expectedServiceType);
    }

    @When("I Deactivate-Freeze The Subscription")
    public void automateDeactivatingTheSubscription() throws InterruptedException {
        userOnSubscriptionTab.clickActivateDeactivateButton();
        userOnSubscriptionTab.clickFreezeSubscriptionButtonOnCancelSubscriptionDialog();
    }

    @Then("I Verify A Frozen Subscription Service Is Not Available via Service Plan Section")
    public void testFrozenSubscriptionDoesNotShowUpInServicePlanSection(){
        Assert.assertEquals(0, userOnCustomerPortalSummaryTab.numberOfServiceTypes(),
                "The Number Of Service Types Do Not Equal Zero (0)");
    }
    @When("I Schedule An Appointment")
    public void automateSchedulingAnAppointment() throws Exception {
        sameUser.goToAppointmentsTab();
        userOnSchedulingComponent = userOnDashboard.goToSchedulingComponent();
        userOnSchedulingComponent.addScheduleDateToProperties();
        userOnSchedulingComponent.clickScheduleDay();
        userOnRoutePage.addGroup();
        userOnRoutePage.addRoutesByQuantity("1");
        userOnRoutePage.selectAvailableAppointment();
        userOnRoutePage.selectCustomer(customerName);
        userOnSchedulingDialog.selectTypeOfService("Automation Renewal");
        userOnSchedulingDialog.selectSubscription("Stand-Alone Service or Reservice");
        schedulingSubscription = userOnSchedulingDialog.getSubscription();
        userOnSchedulingDialog.clickBlueScheduleButton();
    }

    @And("I Cancel The Scheduled Subscription Appointment")
    public void automateCancellingTheScheduledSubscriptionAppointment() throws InterruptedException {
        sameUser.goToAppointmentsTab();
        userOnAppointmentsTab.clickPendingAppointment("Automation Renewal");
        userOnAppointmentsTab.clickCancelAppointmentButton();
        userOnAppointmentsTab.clickConfirmCancellationButton();
    }

    @Then("I Verify The Cancelled Scheduled Appointment Is Not Displayed via Service Plan Section")
    public void testScheduledAppointmentDoesNotShowUpInServicePlanSection() throws InterruptedException {
        sameUser.goToAdminTab();
        userOnCustomerPortalSummaryTab = userOnAdminTab.clickPortalLogin();
        Assert.assertEquals(0, userOnCustomerPortalSummaryTab.numberOfServiceTypes(),
                "The Number Of Service Types Do Not Equal Zero (0)");
    }

    @Then("I Verify The Responsible Balance Matches The Invoice Balance")
    public void testResponsibleBalanceIsCorrect(){
        String actualPaymentBalance = userOnCustomerPortalSummaryTab.getResponsibleBalance();
        Assert.assertEquals(actualPaymentBalance, expectedPaymentBalance,
                "Actual Responsible Balance: " + actualPaymentBalance +
                        " & Expected Balance: " + expectedPaymentBalance + " Do Not Match");
    }

    @Then("I Verify The Fields Contain Correct Information")
    public void testEachFieldContainsCorrectInformation() {
        String actualDate = userOnCustomerPortalSummaryTab.getDate();
        String actualTechnician = userOnCustomerPortalSummaryTab.getTechnician();
        String actualInvoiceNumber = userOnCustomerPortalSummaryTab.getInvoiceNumber();
        String actualTotalAmount = userOnCustomerPortalSummaryTab.getTotalAmount();
        String actualBalanceAmount = userOnCustomerPortalSummaryTab.getBalanceAmount();
        String expectedTotalAmount = test.initialBalance.replace("$", "");
        String expectedBalanceAmount = test.subStatusAmount.replace("$", "");

        Assert.assertEquals(actualDate, expectedDate,
                "The Actual Date " + actualDate +
                        " Does Not Match The Expected Date " + expectedDate);
        Assert.assertEquals(actualTechnician, expectedTechnician,
                "The Actual Technician " + actualTechnician +
                        "Does Not Match The Expected Technician " + expectedTechnician);
        Assert.assertEquals(actualInvoiceNumber, expectedInvoiceNumber,
                "The Actual Invoice Number " + actualInvoiceNumber +
                        " Is Not The Same As Expected Invoice Number " + expectedInvoiceNumber);
        Assert.assertEquals(actualTotalAmount, expectedTotalAmount,
                "The Actual Total Amount " + actualTotalAmount +
                        " Is Not The Same As The Expected Total Amount " + expectedTotalAmount);
        Assert.assertEquals(actualBalanceAmount, expectedBalanceAmount,
                "The Actual Balance Amount " + actualBalanceAmount +
                        " Is Not The Same As The Expected Balance Amount " + expectedBalanceAmount);
        Assert.assertTrue(userOnCustomerPortalSummaryTab.getMostRecentServiceSection()
                .contains(expectedServiceNotes),
                "The Most Recent Service Section Does Not Contain Correct Service Notes");
    }

    @Then("I Verify The Links Are Displayed via Most Recent Service Section")
    public void testLinksInTheMostRecentServiceSection(){
        Assert.assertEquals(userOnCustomerPortalSummaryTab.isServiceNotificationLinkDisplayed(), true,
                "The Service Notification Link Is Not Displayed via Most Recent Service Section");
        Assert.assertEquals(userOnCustomerPortalSummaryTab.isInvoiceLinkDisplayed(), true,
                "The Invoice Link Is Not Displayed via Most Recent Service Section");
    }

    @Then("I Verify The Technical Review Area And Star Rating Are Displayed via Most Recent Service Section")
    public void testTechnicalReviewStarRatingInTheMostRecentServiceSection(){
        Assert.assertEquals(userOnCustomerPortalSummaryTab.isTechnicianReviewTextAreaDisplayed(), true,
                "The Technical Review Text Area Is Not Displayed via Most Recent Service Section");
        Assert.assertEquals(userOnCustomerPortalSummaryTab.isStarRatingsDisplayed(), true,
                "The Star Ratings Are Not Displayed via Most Recent Service Section");
    }
}
