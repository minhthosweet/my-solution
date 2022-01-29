package automation.PestRoutes.Controller.Customers.AppointmentsTab;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.RoutePage.RoutePage;
import automation.PestRoutes.PageObject.Scheduling.SchedulingAppointmentDialog;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import io.cucumber.java.en.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static automation.PestRoutes.Utilities.Utilities.dragCustomerCard;

public class TestScheduledAppointments {

    SoftAssert softAssert = new SoftAssert();
    DashboardPage userOnDashboard = new DashboardPage();
    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    SchedulingTab userOnSchedulingComponent = new SchedulingTab();
    RoutePage userOnRoutePage = new RoutePage();
    SchedulingAppointmentDialog userOnSchedulingDialog = new SchedulingAppointmentDialog();
    CustomerviewDialog_AppointmentsTab userOnAppointmentsTab = new CustomerviewDialog_AppointmentsTab();
    CustomerViewDialog_SubscriptionTab userOnSubscriptionTab = new CustomerViewDialog_SubscriptionTab();
    RoutePageInvoicing userOnInvoicesTab = new RoutePageInvoicing();
    Header userOnCustomerHeader = new Header();
    ValidateRenewal userValidates = new ValidateRenewal();
    CreateNewCustomer testCustomer = new CreateNewCustomer();

    List<String> schedulingSubscription;
    public static String techName;
    public static String serviceNotes;
    public static String appointmentTabID;
    public static String invoiceNumber;
    public static String initialBalance;
    public static String subStatusAmount;

    @Given("I Open The Appointments Tab")
    public void automateAccessingRoutesPageFromAppointmentsTab() {
        testCustomer.createCustomerWithBasicInfo();
        sameUser.goToAppointmentsTab();
    }

    @When("I Schedule An Appointment From The Routes Page")
    public void automateSchedulingAppointment() {
        userOnSchedulingComponent = userOnDashboard.goToSchedulingComponent();
        userOnSchedulingComponent.addScheduleDateToProperties();
        userOnSchedulingComponent.clickScheduleDay();
        userOnRoutePage.addGroup();
        userOnRoutePage.addRoutesByQuantity("1");
        userOnRoutePage.selectAvailableAppointment();
        userOnRoutePage.selectCustomer(testCustomer.customerName);
        userOnSchedulingDialog.selectTypeOfService("Automation Renewal");
        userOnSchedulingDialog.selectSubscription("Stand-Alone Service or Reservice");
        schedulingSubscription = userOnSchedulingDialog.getSubscription();
        userOnSchedulingDialog.clickBlueScheduleButton();
    }

    @And("I Cancel The Scheduled Appointment")
    public void automateCancellingAppointment() {
        userOnRoutePage.goToCustomerSearchComponent(testCustomer.customerName);
        sameUser.goToAppointmentsTab();
        userOnAppointmentsTab.clickPendingAppointment("Automation Renewal");
        userOnAppointmentsTab.clickCancelAppointmentButton();
        userOnAppointmentsTab.clickConfirmCancellationButton();
    }

    @But("I Schedule Another Appointment From The Routes Page")
    public void automateSchedulingAnotherAppointment() {
        dragCustomerCard(200, 0);
        userOnRoutePage.selectAvailableAppointment();
        userOnRoutePage.selectCustomer(testCustomer.customerName);
        userOnSchedulingDialog.selectTypeOfService("Automation Renewal");
        userOnSchedulingDialog.selectSubscription("Stand-Alone Service or Reservice");
        schedulingSubscription = userOnSchedulingDialog.getSubscription();
        userOnSchedulingDialog.clickBlueScheduleButton();
    }

    @Then("I See The Correct Appointment Information On The Appointments Tab")
    public void testCorrectAppointmentInformation() {
        sameUser.goToAppointmentsTab();
        userOnAppointmentsTab.clickPendingAppointment("Automation Renewal");
        List<String> expectedSubscription = schedulingSubscription;
        String actualSubscription = userOnAppointmentsTab.getAppointmentsTabSubscription();
        softAssert.assertTrue(expectedSubscription.contains(actualSubscription),
                "\n Expected Subscription: " + expectedSubscription +
                        "\n Actual Subscription: " + actualSubscription +
                        "\n The Expected Subscription Is Not Contained In The Actual Subscription");
        softAssert.assertAll();
        testCustomer.removeCustomer();
    }

    @When("I Complete An Appointment")
    public void automateCompletingAnAppointment() {
        userOnSchedulingComponent = userOnDashboard.goToSchedulingComponent();
        userOnSchedulingComponent.addScheduleDateToProperties();
        userOnSchedulingComponent.clickScheduleDay();
        userOnRoutePage.addGroup();
        userOnRoutePage.addRoutesByQuantity("1");
        userOnCustomerHeader.searchCustomerWithName(testCustomer.customerName);
        userOnSubscriptionTab = sameUser.goToSubscriptionTab();
        userValidates.scheduleAnAppointment();
        userOnCustomerHeader.searchCustomerWithName(testCustomer.customerName);
        userOnAppointmentsTab = sameUser.goToAppointmentsTab();
        userValidates.completeSchedulesService();
        userOnAppointmentsTab.clickStatusButton();
        userOnAppointmentsTab.typeServiceNotes("Automation Test");
        userOnAppointmentsTab.setTechName("Cam Walker");
        techName = userOnAppointmentsTab.getTechName();
        userOnAppointmentsTab.clickSaveAndCompleteButton();
        serviceNotes = userOnAppointmentsTab.getServiceNotes();
        appointmentTabID = userOnAppointmentsTab.getAppointmentTabID();
        userOnCustomerHeader.searchCustomerWithName(testCustomer.customerName);
        sameUser.goToInvoicesTab();
        invoiceNumber = userOnInvoicesTab.getInvoiceNumber();
        initialBalance = userOnInvoicesTab.getInitialBalance();
        subStatusAmount = userOnInvoicesTab.getSubStatusAmount();
    }
}