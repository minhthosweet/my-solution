package automation.PestRoutes.Controller.CustomerPortal;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Customers.AppointmentsTab.TestScheduledAppointments;
import automation.PestRoutes.Controller.Invoicing.InvoicingTab;
import automation.PestRoutes.Controller.Subscriptions.AddSubscription;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Admin;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerPortal.CustomerPortalSummaryTabPage;
import automation.PestRoutes.Utilities.Data.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;
import java.math.BigDecimal;
import java.util.List;
import static automation.PestRoutes.Utilities.Data.AppData.*;
import static automation.PestRoutes.Utilities.GetWebDriver.*;

public class TestCustomerPortalSummaryTab {

    SoftAssert softAssert = new SoftAssert();
    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    CustomerViewDialog_Admin userOnAdminTab = new CustomerViewDialog_Admin();
    CustomerPortalSummaryTabPage userOnCustomerPortalSummaryTab = new CustomerPortalSummaryTabPage();
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
    String expectedDate = GetDate.currentDate("MM/dd/yy");

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
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
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
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify Share The Love Message")
    public void testShareTheLoveMessage() {
        String actualShareTheLoveMessage = userOnCustomerPortalSummaryTab.getShareTheLoveInformation();
        softAssert.assertTrue(actualShareTheLoveMessage.equalsIgnoreCase(
                "Share The Love"),
                "The Actual And Expected Share The Love Text Do Not Match");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify Service Type Is Correct In Summary Tab via Service Plan Section")
    public void testServiceTypeIsCorrectInSummaryTab() {
        String actualServiceType = userOnCustomerPortalSummaryTab.getServiceType();
        String expectedServiceType = expectedSubscriptionServiceType.toString();
        softAssert.assertTrue(expectedServiceType.contains(actualServiceType),
                "Service Plan Section Contains " + actualServiceType +
                        " And Does Not Contain A Service Type Labeled " + expectedServiceType);
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify A Frozen Subscription Service Is Not Available via Service Plan Section")
    public void testFrozenSubscriptionDoesNotShowUpInServicePlanSection() {
        int actualNumberOfServices = userOnCustomerPortalSummaryTab.numberOfServiceTypes();
        int expectedNumberOfServices = 0;
        softAssert.assertEquals(actualNumberOfServices,0,
                "\n Actual Number Of Services:   " + actualNumberOfServices +
                        "\n Expected Number Of Services: " + expectedNumberOfServices +
                        "\n The Actual Number Of Services Should Equal Zero (0) Since The Subscription Was Frozen \n");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify The Cancelled Scheduled Appointment Is Not Displayed via Service Plan Section")
    public void testScheduledAppointmentDoesNotShowUpInServicePlanSection() {
        userOnAdminTab = sameUser.goToAdminTab();
        userOnCustomerPortalSummaryTab = userOnAdminTab.clickPortalLogin();
        int actualNumberOfServices = userOnCustomerPortalSummaryTab.numberOfServiceTypes();
        int expectedNumberOfServices = 0;
        softAssert.assertEquals(actualNumberOfServices,0,
                "\n Actual Number Of Services:   " + actualNumberOfServices +
                        "\n Expected Number Of Services: " + expectedNumberOfServices +
                        "\n The Actual Number Of Services Should Equal Zero (0) Since The Appointment Was Canceled \n");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify The Responsible Balance via Summary Tab Matches The Invoice Balance")
    public void testResponsibleBalanceIsCorrectOnSummaryTab() {
        String actualPaymentBalance = userOnCustomerPortalSummaryTab.getResponsibleBalance();
        softAssert.assertEquals(actualPaymentBalance, expectedPaymentBalance,
                "Actual Responsible Balance: " + actualPaymentBalance +
                        " & Expected Balance: " + expectedPaymentBalance + " Do Not Match");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
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
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify The Links Are Displayed via Most Recent Service Section")
    public void testLinksInTheMostRecentServiceSection() {
        softAssert.assertEquals(userOnCustomerPortalSummaryTab.isServiceNotificationLinkDisplayed(), true,
                "The Service Notification Link Is Not Displayed via Most Recent Service Section");
        softAssert.assertEquals(userOnCustomerPortalSummaryTab.isInvoiceLinkDisplayed(), true,
                "The Invoice Link Is Not Displayed via Most Recent Service Section");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify The Technical Review Area And Star Rating Are Displayed via Most Recent Service Section")
    public void testTechnicalReviewStarRatingInTheMostRecentServiceSection() {
        softAssert.assertEquals(userOnCustomerPortalSummaryTab.isTechnicianReviewTextAreaDisplayed(), true,
                "The Technical Review Text Area Is Not Displayed via Most Recent Service Section");
        softAssert.assertEquals(userOnCustomerPortalSummaryTab.isStarRatingsDisplayed(), true,
                "The Star Ratings Are Not Displayed via Most Recent Service Section");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify The URL Contains The Correct SubDomain")
    public void testCorrectSubdomainInURL() {
        String actualCustomerPortalURL = userOnCustomerPortalSummaryTab.getURL();
        String actualSubDomain = actualCustomerPortalURL.substring(actualCustomerPortalURL.indexOf("/") + 2, actualCustomerPortalURL.indexOf("."));
        String actualPestRoutesURL = getData("url",environment);
        String expectedCustomerPortalURL = actualPestRoutesURL.replace("pestroutes", "pestportals");
        String expectedSubDomain = expectedCustomerPortalURL.substring(expectedCustomerPortalURL.indexOf("/") + 2, expectedCustomerPortalURL.indexOf("."));
        softAssert.assertEquals(actualSubDomain, expectedSubDomain,
                "\n The Actual & Expected Sub Domains Do Not Match" +
                "\n Actual Sub Domain: " + actualSubDomain +
                "\n Expected Sub Domain: " + expectedSubDomain + "\n");
        System.out.println("Actual Sub Domain:   " + actualSubDomain);
        System.out.println("Expected Sub Domain: " + expectedSubDomain);
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify The Facebook {string} Button Is Visible By Returning The Button Name")
    public void testFacebookButtonIsVisibleByReturningButtonName(String recommend_share) {
        String actualFacebookButtonName = userOnCustomerPortalSummaryTab.getFacebookButtonName(recommend_share);
        softAssert.assertTrue(actualFacebookButtonName.contains(recommend_share),
                "\n The Facebook Button Name Is Not Correct " +
                        "\n Actual Button Name: " + actualFacebookButtonName +
                        "\n Expected Button Name: " + recommend_share + "\n");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }
}