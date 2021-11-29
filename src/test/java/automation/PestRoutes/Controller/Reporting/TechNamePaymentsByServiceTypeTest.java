package automation.PestRoutes.Controller.Reporting;

import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreateNewInvoicePopUp;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.Invoice_Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;

import static automation.PestRoutes.Utilities.Utilities.generateRandomString;
import static automation.PestRoutes.Utilities.Utilities.currentDate;
import static automation.PestRoutes.Utilities.Utilities.transformName;

import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.ReportingPage.OfficePage.PaymentsByServiceTypeTab;
import automation.PestRoutes.PageObject.ReportingPage.ReportingMainPage;
import automation.PestRoutes.PageObject.RoutePage.RoutePage;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static automation.PestRoutes.Utilities.Utilities.generateRandomString;
import static automation.PestRoutes.Utilities.Utilities.transformName;

public class TechNamePaymentsByServiceTypeTest {
    DashboardPage userOnDashboard = new DashboardPage();
    CreateCustomerDialog userCreateNewCustomer = new CreateCustomerDialog();
    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    CustomerViewDialog_SubscriptionTab userOnSubscriptionTab = new CustomerViewDialog_SubscriptionTab();
    RoutePageInvoicing userOnInvoicesTab = new RoutePageInvoicing();
    CreateNewInvoicePopUp userOnNewInvoicePopUp = new CreateNewInvoicePopUp();
    Invoice_Header userSelectsPayment = new Invoice_Header();
    InvoiceImplementation userMakesPayment = new InvoiceImplementation();
    ReportingMainPage userOnReporting = new ReportingMainPage();
    ReportingMainPage userOnReportingComponent = new ReportingMainPage();
    PaymentsByServiceTypeTab userOnPaymentByServiceType = new PaymentsByServiceTypeTab();
    SchedulingTab userOnSchedulingComponent = new SchedulingTab();
    RoutePage userOnRoutePage = new RoutePage();
    Header userOnCustomerHeader = new Header();
    ValidateRenewal userValidates = new ValidateRenewal();
    CustomerviewDialog_AppointmentsTab userOnAppointments = new CustomerviewDialog_AppointmentsTab();
    CustomerviewDialog_AppointmentsTab userOnAppointmentsTab = new CustomerviewDialog_AppointmentsTab();

    String totalInitialInvoice;
    String paymentAmount;
    String customerName;
    String techName;

    @Given("I Create A Customer With A Subscription")
    public void automateCreatingCustomerWithSubscription() throws InterruptedException {
        userCreateNewCustomer = userOnDashboard.goToNewCustomerComponent();
        userCreateNewCustomer.typeFirstName(generateRandomString(3));
        userCreateNewCustomer.typeLastName(generateRandomString(4));
        customerName = userCreateNewCustomer.getCustomerName();
        userCreateNewCustomer.typeZipCode("75093"); // Hard-Coded But Will Fix Later
        sameUser.clickCustomerSaveButton();
        sameUser.goToSubscriptionTab();
        userOnSubscriptionTab.clickNewSubscription();
        userOnSubscriptionTab.selectRecurringServiceType("Automation Renewal"); // Hard-Coded But Will Fix Later
        userCreateNewCustomer.typeZipCode("75093");
        sameUser.clickCustomerSaveButton();
        userOnSubscriptionTab = sameUser.goToSubscriptionTab();
        userOnSubscriptionTab.clickNewSubscription();
        userOnSubscriptionTab.selectRecurringServiceType("Automation Renewal");
        userOnSubscriptionTab.selectCustomDate(currentDate("MM/DD/YYYY"));
        userOnSubscriptionTab.selectInitialInvoice("After Initial Completion");
        userOnSubscriptionTab.selectAdditionalItem_ToInitialInvoice("Best Product");
        sameUser.clickCustomerSaveButton();
        totalInitialInvoice = userOnSubscriptionTab.getInitialInvoiceTotal();
    }

    @When("I Generate A Stand Alone Invoice")
    public void automateGeneratingStandAloneInvoice() throws InterruptedException {
        userOnInvoicesTab = sameUser.goToInvoicesTab();
        userOnInvoicesTab.clickNewInvoice();
        userOnNewInvoicePopUp.typeSubTotal(totalInitialInvoice);
        userOnNewInvoicePopUp.selectServiceType("Automation Renewal");
        userOnNewInvoicePopUp.clickCreateButton();
    }

    @And("I Pay Off The Stand Alone Invoice")
    public void automatePayingOffStandAloneInvoice() throws InterruptedException {
        userOnInvoicesTab.addPayment();
        userSelectsPayment.clickCash();
        paymentAmount = userMakesPayment.getPaymentAmount();
        userMakesPayment.typeConfirmationAmount(paymentAmount);
        userMakesPayment.clickRecordPaymentButton();
        sameUser.clickXButton();
        sameUser.clickSaveChangesButton();
    }

    @Then("I See No Tech Is Displayed On The Payment By Service Type Report")
    public void testNoTechOnPaymentByServiceTypeReport() {
        userOnReporting = userOnDashboard.goToReportingComponent();
        userOnPaymentByServiceType = userOnReporting.clickPaymentsByServiceType();
        userOnReportingComponent = userOnDashboard.goToReportingComponent();
        userOnPaymentByServiceType = userOnReportingComponent.clickPaymentsByServiceType();
        userOnPaymentByServiceType.selectDateFor("Today");
        userOnPaymentByServiceType.selectGroupBy("Technician");
        userOnPaymentByServiceType.clickRefreshButton();
        userOnPaymentByServiceType.clickDescription("No Tech");
        Assert.assertTrue(userOnPaymentByServiceType.getCustomerName(customerName),
                "The Customer Is Not Available After Selecting A Technician");
    }

    @When("I Complete An Appointment")
    public void automateCompletingAnAppointment() throws Exception {
        userOnSchedulingComponent = userOnDashboard.goToSchedulingComponent();
        userOnSchedulingComponent.addScheduleDateToProperties();
        userOnSchedulingComponent.clickScheduleDay();
        userOnRoutePage.addGroup();
        userOnRoutePage.addRoutesByQuantity("1");
        userOnCustomerHeader.searchCustomerWithName(customerName);
        sameUser.goToSubscriptionTab();
        userValidates.scheduleAnAppointment();
        userOnCustomerHeader.searchCustomerWithName(customerName);
        sameUser.goToAppointmentsTab();
        userValidates.completeSchedulesService();
        userOnAppointments.clickStatusButton();
        userOnAppointments.setTechName("Cam Walker"); // Hard-Coded But Will Fix Later
        techName = userOnAppointments.getTechName();
        userOnAppointments.clickSaveAndCompleteButton();
    }

    @And("I Pay Off A Non Stand Alone Invoice")
    public void testPayingOffNonStandAloneInvoice() throws Exception {
        sameUser.goToInvoicesTab();
        userOnSubscriptionTab = sameUser.goToSubscriptionTab();
        userValidates.scheduleAnAppointment();
        userOnCustomerHeader.searchCustomerWithName(customerName);
        userOnAppointmentsTab = sameUser.goToAppointmentsTab();
        userValidates.completeSchedulesService();
        userOnAppointmentsTab.clickStatusButton();
        userOnAppointmentsTab.setTechName("Cam Walker");
        techName = userOnAppointmentsTab.getTechName();
        userOnAppointmentsTab.clickSaveAndCompleteButton();
        userOnAppointments.clickStatusButton();
    }

    @And("I Pay Off A Non Stand Alone Invoice")
    public void automatePayingOffNonStandAloneInvoice() throws InterruptedException {
        userOnInvoicesTab = sameUser.goToInvoicesTab();
        userOnInvoicesTab.addPayment();
        userSelectsPayment.clickCash();
        paymentAmount = userMakesPayment.getPaymentAmount();
        userMakesPayment.typeConfirmationAmount(paymentAmount);
        userMakesPayment.clickRecordPaymentButton();
        sameUser.clickXButton();
        sameUser.clickSaveChangesButton();
    }

    @Then("I See The Correct Technician Is Displayed On The Payment By Service Type Report")
    public void testCorrectTechnicianOnPaymentByServiceTypeReport() {
        userOnReporting = userOnDashboard.goToReportingComponent();
        userOnReporting.clickPaymentsByServiceType();
        userOnReportingComponent = userOnDashboard.goToReportingComponent();
        userOnReportingComponent.clickPaymentsByServiceType();
        userOnPaymentByServiceType.selectDateFor("Today");
        userOnPaymentByServiceType.selectGroupBy("Technician");
        userOnPaymentByServiceType.clickRefreshButton();
        String transformTechName = transformName(techName);
        userOnPaymentByServiceType.clickDescription(transformTechName);
        Assert.assertTrue(userOnPaymentByServiceType.getCustomerName(customerName),
                "The Customer Is Not Available After Selecting A Technician");
    }
}