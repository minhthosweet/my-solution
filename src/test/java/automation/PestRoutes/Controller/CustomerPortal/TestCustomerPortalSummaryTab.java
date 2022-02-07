package automation.PestRoutes.Controller.CustomerPortal;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Customers.AppointmentsTab.TestScheduledAppointments;
import automation.PestRoutes.Controller.Invoicing.InvoicingTab;
import automation.PestRoutes.Controller.Subscriptions.AddSubscription;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Admin;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.CustomerPortal.CustomerPortalSummaryTabPage;
import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.PageObject.RoutePage.RoutePage;
import automation.PestRoutes.PageObject.Scheduling.SchedulingAppointmentDialog;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;
import java.util.List;

import static automation.PestRoutes.Utilities.Utilities.closeTab;
import static automation.PestRoutes.Utilities.Utilities.switchToOldWindowOpened;

public class TestCustomerPortalSummaryTab {

    SoftAssert softAssert = new SoftAssert();
    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    CustomerViewDialog_Admin userOnAdminTab = new CustomerViewDialog_Admin();
    CustomerPortalSummaryTabPage userOnCustomerPortalSummaryTab = new CustomerPortalSummaryTabPage();
    CustomerViewDialog_SubscriptionTab userOnSubscriptionTab = new CustomerViewDialog_SubscriptionTab();
    DashboardPage userOnDashboard = new DashboardPage();
    SchedulingTab userOnSchedulingComponent = new SchedulingTab();
    RoutePage userOnRoutePage = new RoutePage();
    SchedulingAppointmentDialog userOnSchedulingDialog = new SchedulingAppointmentDialog();
    CustomerviewDialog_AppointmentsTab userOnAppointmentsTab = new CustomerviewDialog_AppointmentsTab();
    CreateNewCustomer testCustomer = new CreateNewCustomer();
    AddSubscription testSubscription = new AddSubscription();
    InvoicingTab testInvoice = new InvoicingTab();
    TestScheduledAppointments testAppointment = new TestScheduledAppointments();

    String expectedFirstName = testCustomer.customerFirstName;
    String expectedPropertyAddress = testCustomer.propertyAddress;
    String expectedCityStateZip = testCustomer.cityStateZip;
    String expectedEmailAddress = testCustomer.emailAddress;
    String expectedCustomerAccountID = testCustomer.customerAccountID;
    String expectedServiceNotes = testAppointment.serviceNotes;
    String expectedPaymentBalance = testInvoice.invoicePaymentBalance;
    String expectedTechnician = testAppointment.techName;
    String expectedInvoiceNumber = testAppointment.invoiceNumber;
    List<String> expectedSubscriptionServiceType = testSubscription.serviceType;
    List<String> schedulingSubscription;
    String expectedDate = Utilities.currentDate("MM/dd/yy");

    @When("I Navigate To Customer Portal From Customer Card - Admin Tab")
    public void automateNavigatingFromCustomerCardToCustomerPortal() {
        userOnAdminTab = sameUser.goToAdminTab();
        userOnCustomerPortalSummaryTab = userOnAdminTab.clickPortalLogin();
    }

    @Then("I Verify First Name In The Welcome Message via Summary Tab")
    public void testFirstNameInWelcomeMessageSummaryTab() {
        String actualMessage = userOnCustomerPortalSummaryTab.getFirstNameFromWelcomeBanner();
        softAssert.assertTrue(actualMessage.contains(expectedFirstName),
                "Welcome Message Does Not Contain The Correct First Name");
        softAssert.assertAll();
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify The Property Details Section")
    public void testPropertyDetailsSection() {
        String actualPropertyDetails = userOnCustomerPortalSummaryTab.getPropertyDetails();
        softAssert.assertTrue(actualPropertyDetails.contains(expectedCustomerAccountID),
                "Expected Account ID Is Not Contained In The Property Details Section");
        softAssert.assertTrue(actualPropertyDetails.contains(expectedPropertyAddress),
                "Expected Property Address Is Not Contained In The Property Details Section");
        softAssert.assertTrue(actualPropertyDetails.contains(expectedCityStateZip),
                "Expected City, State, and Zip Is Not Contained In The Property Details Section");
        softAssert.assertTrue(actualPropertyDetails.contains(expectedEmailAddress),
                "Expected Email Address Is Not Contained In The Property Details Section");
        softAssert.assertAll();
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify Share The Love Message")
    public void testShareTheLoveMessage() {
        String actualShareTheLoveMessage = userOnCustomerPortalSummaryTab.getShareTheLoveInformation();
        softAssert.assertTrue(actualShareTheLoveMessage.equalsIgnoreCase(
                "Share The Love"),
                "The Actual And Expected Share The Love Text Do Not Match");
        softAssert.assertAll();
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify Service Type Is Correct In Summary Tab via Service Plan Section")
    public void testServiceTypeIsCorrectInSummaryTab() {
        String actualServiceType = userOnCustomerPortalSummaryTab.getServiceType();
        String expectedServiceType = expectedSubscriptionServiceType.toString();
        softAssert.assertTrue(expectedServiceType.contains(actualServiceType),
                "Service Plan Section Contains " + actualServiceType +
                        " And Does Not Contain A Service Type Labeled " + expectedServiceType);
        softAssert.assertAll();
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @When("I Deactivate-Freeze The Subscription")
    public void automateDeactivatingTheSubscription() {
        userOnSubscriptionTab.clickActivateDeactivateButton();
        userOnSubscriptionTab.clickFreezeSubscriptionButtonOnCancelSubscriptionDialog();
    }

    @Then("I Verify A Frozen Subscription Service Is Not Available via Service Plan Section")
    public void testFrozenSubscriptionDoesNotShowUpInServicePlanSection() {
        softAssert.assertEquals(0, userOnCustomerPortalSummaryTab.numberOfServiceTypes(),
                "The Number Of Service Types Do Not Equal Zero (0)");
        softAssert.assertAll();
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }
  
    @When("I Schedule An Appointment")
    public void automateSchedulingAnAppointment() {
        sameUser.goToAppointmentsTab();
        userOnSchedulingComponent = userOnDashboard.goToSchedulingComponent();
        userOnSchedulingComponent.addScheduleDateToProperties();
        userOnSchedulingComponent.clickScheduleDay();
        userOnRoutePage.selectAvailableAppointment();
        userOnRoutePage.selectCustomer(testCustomer.customerName);
        userOnSchedulingDialog.selectTypeOfService("Automation Renewal");
        userOnSchedulingDialog.selectSubscription("Stand-Alone Service or Reservice");
        schedulingSubscription = userOnSchedulingDialog.getSubscription();
        userOnSchedulingDialog.clickBlueScheduleButton();
    }

    @And("I Cancel The Scheduled Subscription Appointment")
    public void automateCancellingTheScheduledSubscriptionAppointment() {
        sameUser.goToAppointmentsTab();
        userOnAppointmentsTab.clickPendingAppointment("Automation Renewal");
        userOnAppointmentsTab.clickCancelAppointmentButton();
        userOnAppointmentsTab.clickConfirmCancellationButton();
    }

    @Then("I Verify The Cancelled Scheduled Appointment Is Not Displayed via Service Plan Section")
    public void testScheduledAppointmentDoesNotShowUpInServicePlanSection() {
        userOnAdminTab = sameUser.goToAdminTab();
        userOnCustomerPortalSummaryTab = userOnAdminTab.clickPortalLogin();
        softAssert.assertEquals(0, userOnCustomerPortalSummaryTab.numberOfServiceTypes(),
                "The Number Of Service Types Do Not Equal Zero (0)");
        softAssert.assertAll();
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify The Responsible Balance via Summary Tab Matches The Invoice Balance")
    public void testResponsibleBalanceIsCorrectOnSummaryTab() {
        String actualPaymentBalance = userOnCustomerPortalSummaryTab.getResponsibleBalance();
        softAssert.assertEquals(actualPaymentBalance, expectedPaymentBalance,
                "Actual Responsible Balance: " + actualPaymentBalance +
                        " & Expected Balance: " + expectedPaymentBalance + " Do Not Match");
        softAssert.assertAll();
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify The Fields Contain Correct Information")
    public void testEachFieldContainsCorrectInformation() {
        String actualDate = userOnCustomerPortalSummaryTab.getDate();
        String actualTechnician = userOnCustomerPortalSummaryTab.getTechnician();
        String actualInvoiceNumber = userOnCustomerPortalSummaryTab.getInvoiceNumber();
        String actualTotalAmount = String.format("%.2f", new BigDecimal(userOnCustomerPortalSummaryTab.getTotalAmount()));
        String actualBalanceAmount = userOnCustomerPortalSummaryTab.getBalanceAmount();
        String expectedTotalAmount = testAppointment.initialBalance.replace("$", "");
        String expectedBalanceAmount = testAppointment.subStatusAmount.replace("$", "");

        softAssert.assertEquals(actualDate, expectedDate,
                "The Actual Date " + actualDate +
                        " Does Not Match The Expected Date " + expectedDate);
        softAssert.assertEquals(actualTechnician, expectedTechnician,
                "The Actual Technician " + actualTechnician +
                        "Does Not Match The Expected Technician " + expectedTechnician);
        softAssert.assertEquals(actualInvoiceNumber, expectedInvoiceNumber,
                "The Actual Invoice Number " + actualInvoiceNumber +
                        " Is Not The Same As Expected Invoice Number " + expectedInvoiceNumber);
        softAssert.assertEquals(actualTotalAmount, expectedTotalAmount,
                "The Actual Total Amount " + actualTotalAmount +
                        " Is Not The Same As The Expected Total Amount " + expectedTotalAmount);
        softAssert.assertEquals(actualBalanceAmount, expectedBalanceAmount,
                "The Actual Balance Amount " + actualBalanceAmount +
                        " Is Not The Same As The Expected Balance Amount " + expectedBalanceAmount);
        softAssert.assertTrue(userOnCustomerPortalSummaryTab.getMostRecentServiceSection()
                .contains(expectedServiceNotes),
                "The Most Recent Service Section Does Not Contain Correct Service Notes");
        softAssert.assertAll();
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify The Links Are Displayed via Most Recent Service Section")
    public void testLinksInTheMostRecentServiceSection() {
        softAssert.assertEquals(userOnCustomerPortalSummaryTab.isServiceNotificationLinkDisplayed(), true,
                "The Service Notification Link Is Not Displayed via Most Recent Service Section");
        softAssert.assertEquals(userOnCustomerPortalSummaryTab.isInvoiceLinkDisplayed(), true,
                "The Invoice Link Is Not Displayed via Most Recent Service Section");
        softAssert.assertAll();
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }

    @Then("I Verify The Technical Review Area And Star Rating Are Displayed via Most Recent Service Section")
    public void testTechnicalReviewStarRatingInTheMostRecentServiceSection() {
        softAssert.assertEquals(userOnCustomerPortalSummaryTab.isTechnicianReviewTextAreaDisplayed(), true,
                "The Technical Review Text Area Is Not Displayed via Most Recent Service Section");
        softAssert.assertEquals(userOnCustomerPortalSummaryTab.isStarRatingsDisplayed(), true,
                "The Star Ratings Are Not Displayed via Most Recent Service Section");
        softAssert.assertAll();
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
    }
}
