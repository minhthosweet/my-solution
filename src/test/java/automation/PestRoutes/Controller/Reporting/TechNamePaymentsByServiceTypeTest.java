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
    PaymentsByServiceTypeTab userOnPaymentByServiceType = new PaymentsByServiceTypeTab();
    SchedulingTab userOnSchedulingComponent = new SchedulingTab();
    RoutePage userOnRoutePage = new RoutePage();
    Header userOnCustomerHeader = new Header();
    ValidateRenewal userValidates = new ValidateRenewal();
    CustomerviewDialog_AppointmentsTab userOnAppointments = new CustomerviewDialog_AppointmentsTab();

    String totalInitialInvoice;
    String paymentAmount;
    String customerName;
    String techName;

    @Given("I Create A Customer With A Subscription")
    public void testCreatingCustomerWithSubscription() throws InterruptedException {
        userCreateNewCustomer = userOnDashboard.goToNewCustomerComponent();
        userCreateNewCustomer.typeFirstName(generateRandomString(3));
        userCreateNewCustomer.typeLastName(generateRandomString(4));
        customerName = userCreateNewCustomer.getCustomerName();
        userCreateNewCustomer.typeZipCode("75093"); // Hard-Coded But Will Fix Later
        sameUser.clickCustomerSaveButton();
        sameUser.goToSubscriptionTab();
        userOnSubscriptionTab.clickNewSubscription();
        userOnSubscriptionTab.selectRecurringServiceType("Automation Renewal"); // Hard-Coded But Will Fix Later
        sameUser.clickCustomerSaveButton();
        totalInitialInvoice = userOnSubscriptionTab.getInitialInvoiceTotal();
    }

    @When("I Generate A Stand Alone Invoice")
    public void testGeneratingStandAloneInvoice() throws InterruptedException {
        sameUser.goToInvoicesTab();
        userOnInvoicesTab.clickNewInvoice();
        userOnNewInvoicePopUp.typeSubTotal(totalInitialInvoice);
        userOnNewInvoicePopUp.selectServiceType("Automation Renewal"); // Hard-Coded But Will Fix Later
        userOnNewInvoicePopUp.clickCreateButton();
    }

    @And("I Pay Off The Stand Alone Invoice")
    public void testPayingOffStandAloneInvoice() throws InterruptedException {
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
        userOnPaymentByServiceType.selectDateFor("Today");
        userOnPaymentByServiceType.selectGroupBy("Technician");
        userOnPaymentByServiceType.clickRefreshButton();
        userOnPaymentByServiceType.clickDescription("No Tech");
        Assert.assertTrue(userOnPaymentByServiceType.getCustomerName(customerName),
                "The Customer Is Not Available After Selecting A Technician");
    }

    @When("I Complete An Appointment")
    public void testCompletingAnAppointment() throws Exception {
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
    public void testPayingOffNonStandAloneInvoice() throws InterruptedException {
        sameUser.goToInvoicesTab();
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
        userOnPaymentByServiceType.selectDateFor("Today");
        userOnPaymentByServiceType.selectGroupBy("Technician");
        userOnPaymentByServiceType.clickRefreshButton();
        String transformTechName = transformName(techName);
        userOnPaymentByServiceType.clickDescription(transformTechName);
        Assert.assertTrue(userOnPaymentByServiceType.getCustomerName(customerName),
                "The Customer Is Not Available After Selecting A Technician");
    }
}