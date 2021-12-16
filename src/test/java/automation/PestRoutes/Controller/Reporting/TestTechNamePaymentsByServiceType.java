package automation.PestRoutes.Controller.Reporting;

import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.BillingPage;
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

import java.util.List;

import static automation.PestRoutes.Utilities.Utilities.*;

public class TestTechNamePaymentsByServiceType {
    DashboardPage userOnDashboard = new DashboardPage();
    CreateCustomerDialog userCreateNewCustomer = new CreateCustomerDialog();
    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    CustomerViewDialog_SubscriptionTab userOnSubscriptionTab = new CustomerViewDialog_SubscriptionTab();
    RoutePageInvoicing userOnInvoicesTab = new RoutePageInvoicing();
    CreateNewInvoicePopUp userOnNewInvoicePopUp = new CreateNewInvoicePopUp();
    Invoice_Header userSelectsPayment = new Invoice_Header();
    InvoiceImplementation userMakesPayment = new InvoiceImplementation();
    ReportingMainPage userOnReportingComponent = new ReportingMainPage();
    PaymentsByServiceTypeTab userOnPaymentByServiceType = new PaymentsByServiceTypeTab();
    SchedulingTab userOnSchedulingComponent = new SchedulingTab();
    RoutePage userOnRoutePage = new RoutePage();
    Header userOnCustomerHeader = new Header();
    ValidateRenewal userValidates = new ValidateRenewal();
    CustomerviewDialog_AppointmentsTab userOnAppointmentsTab = new CustomerviewDialog_AppointmentsTab();
    BillingPage userOnBillingTab = new BillingPage();

    public static String paymentAmount;
    public static String customerFullName;
    public static String customerFirstName;
    public static String propertyAddress;
    public static String cityStateZip;
    public static String emailAddress;
    public static String customerAccountID;
    public static List<String> serviceType;
    public static String invoicePaymentBalance;
    public static String totalInitialInvoice;
    public static String techName;
    public static String serviceNotes;
    public static String invoiceNumber;
    public static String initialBalance;
    public static String subStatusAmount;

    @Given("I Create A Customer With A Subscription")
    public void automateCreatingCustomerWithSubscription() throws InterruptedException {
        userCreateNewCustomer = userOnDashboard.goToNewCustomerComponent();
        userCreateNewCustomer.typeFirstName(generateRandomString(3));
        customerFirstName = userCreateNewCustomer.getCustomerFirstName();
        userCreateNewCustomer.typeLastName(generateRandomString(4));
        customerFullName = userCreateNewCustomer.getCustomerFullName();
        userCreateNewCustomer.typePropertyAddress("1234 " + generateRandomString(7)); // Hard Coded But Plan To Change Later
        propertyAddress = userCreateNewCustomer.getPropertyAddress();
        userCreateNewCustomer.typeCity("Plano"); // Hard Coded But Plan To Change Later
        userCreateNewCustomer.typeZipCode("75093"); // Hard Coded But Plan To Change Later
        cityStateZip = userCreateNewCustomer.getCityStateZip();
        userCreateNewCustomer.typeEmailAddress("TestEmail@FieldRoutes.com"); // Hard Coded But Plan To Change Later
        emailAddress = userCreateNewCustomer.getEmailAddress();
        sameUser.clickCustomerSaveButton();
        sameUser.goToBillingTab();
        customerAccountID = userOnBillingTab.getCustomerAccountID();
        userOnSubscriptionTab = sameUser.goToSubscriptionTab();
        userOnSubscriptionTab.clickNewSubscription();
        userOnSubscriptionTab.selectRecurringServiceType("Automation Renewal");
        serviceType = userOnSubscriptionTab.getRecurringServiceType();
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
        userOnNewInvoicePopUp.selectServiceType("Automation Renewal"); // Hard Coded But Plan To Change Later
        userOnNewInvoicePopUp.clickCreateButton();
        invoicePaymentBalance = userOnInvoicesTab.getPaymentBalance();
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
        userOnReportingComponent = userOnDashboard.goToReportingComponent();
        userOnPaymentByServiceType = userOnReportingComponent.clickPaymentsByServiceType();
        userOnPaymentByServiceType.selectDateFor("Today");
        userOnPaymentByServiceType.selectGroupBy("Technician");
        userOnPaymentByServiceType.clickRefreshButton();
        userOnPaymentByServiceType.clickDescription("No Tech");
        Assert.assertTrue(userOnPaymentByServiceType.getCustomerName(customerFullName),
                "The Customer Is Not Available After Selecting A Technician");
    }

    @When("I Complete An Appointment")
    public void automateCompletingAnAppointment() throws Exception {
        userOnSchedulingComponent = userOnDashboard.goToSchedulingComponent();
        userOnSchedulingComponent.addScheduleDateToProperties();
        userOnSchedulingComponent.clickScheduleDay();
        userOnRoutePage.addGroup();
        userOnRoutePage.addRoutesByQuantity("1");
        userOnCustomerHeader.searchCustomerWithName(customerFullName);
        userOnSubscriptionTab = sameUser.goToSubscriptionTab();
        userValidates.scheduleAnAppointment();
        userOnCustomerHeader.searchCustomerWithName(customerFullName);
        userOnAppointmentsTab = sameUser.goToAppointmentsTab();
        userValidates.completeSchedulesService();
        userOnAppointmentsTab.clickStatusButton();
        userOnAppointmentsTab.typeServiceNotes("Automation Test"); // Hard Coded But Plan To Change Later
        userOnAppointmentsTab.setTechName("Cam Walker"); // Hard Coded But Plan To Change Later
        techName = userOnAppointmentsTab.getTechName();
        userOnAppointmentsTab.clickSaveAndCompleteButton();
        serviceNotes = userOnAppointmentsTab.getServiceNotes();
        userOnAppointmentsTab.goToCustomerSearchComponent(customerFullName);
        sameUser.goToInvoicesTab();
        invoiceNumber = userOnInvoicesTab.getInvoiceNumber();
        initialBalance = userOnInvoicesTab.getInitialBalance();
        subStatusAmount = userOnInvoicesTab.getSubStatusAmount();
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
        userOnReportingComponent = userOnDashboard.goToReportingComponent();
        userOnReportingComponent.clickPaymentsByServiceType();
        userOnPaymentByServiceType.selectDateFor("Today");
        userOnPaymentByServiceType.selectGroupBy("Technician");
        userOnPaymentByServiceType.clickRefreshButton();
        String transformTechName = transformName(techName);
        userOnPaymentByServiceType.clickDescription(transformTechName);
        Assert.assertTrue(userOnPaymentByServiceType.getCustomerName(customerFullName),
                "The Customer Is Not Available After Selecting A Technician");
    }
}
