package automation.PestRoutes.Controller.Customers.AppointmentsTab;

import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.PageObject.RoutePage.RoutePage;
import automation.PestRoutes.PageObject.Scheduling.SchedulingAppointmentDialog;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.util.List;

import static automation.PestRoutes.Utilities.Utilities.dragCustomerCard;
import static automation.PestRoutes.Utilities.Utilities.generateRandomString;

public class TestScheduledAppointments {
    DashboardPage userOnDashboard = new DashboardPage();
    CreateCustomerDialog userCreateNewCustomer = new CreateCustomerDialog();
    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    SchedulingTab userOnSchedulingComponent = new SchedulingTab();
    RoutePage userOnRoutePage = new RoutePage();
    SchedulingAppointmentDialog userOnSchedulingDialog = new SchedulingAppointmentDialog();
    CustomerviewDialog_AppointmentsTab userOnAppointmentsTab = new CustomerviewDialog_AppointmentsTab();

    String customerName;
    List<String> schedulingSubscription;

    @Given("I Open The Appointments Tab")
    public void automateAccessingRoutesPageFromAppointmentsTab() throws Exception {
        userOnDashboard.goToNewCustomerComponent();
        userCreateNewCustomer.typeFirstName(generateRandomString(3));
        userCreateNewCustomer.typeLastName(generateRandomString(4));
        customerName = userCreateNewCustomer.getCustomerName();
        userCreateNewCustomer.typeZipCode("75093");
        sameUser.clickCustomerSaveButton();
        sameUser.goToAppointmentsTab();
    }

    @When("I Schedule An Appointment From The Routes Page")
    public void automateSchedulingAppointment() throws Exception {
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

    @And("I Cancel The Scheduled Appointment")
    public void automateCancellingAppointment() throws InterruptedException {
        userOnRoutePage.goToCustomerSearchComponent(customerName);
        sameUser.goToAppointmentsTab();
        userOnAppointmentsTab.clickPendingAppointment("Automation Renewal");
        userOnAppointmentsTab.clickCancelAppointmentButton();
        userOnAppointmentsTab.clickConfirmCancellationButton();
    }

    @But("I Schedule Another Appointment From The Routes Page")
    public void automateSchedulingAnotherAppointment() throws InterruptedException {
        dragCustomerCard(200, 0);
        userOnRoutePage.selectAvailableAppointment();
        userOnRoutePage.selectCustomer(customerName);
        userOnSchedulingDialog.selectTypeOfService("Automation Renewal");
        userOnSchedulingDialog.selectSubscription("Stand-Alone Service or Reservice");
        schedulingSubscription = userOnSchedulingDialog.getSubscription();
        userOnSchedulingDialog.clickBlueScheduleButton();
    }


    @Then("I See The Correct Appointment Information On The Appointments Tab")
    public void testCorrectAppointmentInformation() throws InterruptedException {
        sameUser.goToAppointmentsTab();
        userOnAppointmentsTab.clickPendingAppointment("Automation Renewal");
        List<String> expectedSubscription = schedulingSubscription;
        String actualSubscription = userOnAppointmentsTab.getAppointmentsTabSubscription();
        Assert.assertTrue(expectedSubscription.contains(actualSubscription),
                "The Expected and Actual Subscription Do Not Contain The Same Value");
    }
}
