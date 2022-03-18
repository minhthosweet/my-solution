package automation.PestRoutes.Controller.Reporting;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Customers.AppointmentsTab.TestScheduledAppointments;
import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.PageObject.ReportingPage.OfficePage.PaymentsByServiceTypeTab;
import automation.PestRoutes.PageObject.ReportingPage.ReportingMainPage;
import io.cucumber.java.en.Then;
import org.testng.asserts.SoftAssert;

import static automation.PestRoutes.Utilities.Utilities.transformName;

public class TestTechNamePaymentsByServiceType {
    SoftAssert softAssert = new SoftAssert();
    DashboardPage userOnDashboard = new DashboardPage();
    ReportingMainPage userOnReportingComponent = new ReportingMainPage();
    PaymentsByServiceTypeTab userOnPaymentByServiceType = new PaymentsByServiceTypeTab();
    TestScheduledAppointments testAppointment = new TestScheduledAppointments();
    CreateNewCustomer testCustomer = new CreateNewCustomer();

    @Then("I See No Tech Is Displayed On The Payment By Service Type Report")
    public void testNoTechOnPaymentByServiceTypeReport() {
        userOnReportingComponent = userOnDashboard.goToReportingComponent();
        userOnPaymentByServiceType = userOnReportingComponent.clickPaymentsByServiceType();
        userOnPaymentByServiceType.selectDateFor("This Week");
        userOnPaymentByServiceType.selectGroupBy("Technician");
        userOnPaymentByServiceType.clickRefreshButton();
        userOnPaymentByServiceType.clickDescription("No Tech");
        softAssert.assertTrue(userOnPaymentByServiceType.getCustomerName(testCustomer.customerName),
                "\n Customer Name Is " + testCustomer.customerName +
                        "\n Customer Is Not Available After Selecting A Technician");
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I See The Correct Technician Is Displayed On The Payment By Service Type Report")
    public void testCorrectTechnicianOnPaymentByServiceTypeReport() {
        userOnReportingComponent = userOnDashboard.goToReportingComponent();
        userOnReportingComponent.clickPaymentsByServiceType();
        userOnPaymentByServiceType.selectDateFor("This Week");
        userOnPaymentByServiceType.selectGroupBy("Technician");
        userOnPaymentByServiceType.clickRefreshButton();
        String transformTechName = transformName(testAppointment.techName);
        userOnPaymentByServiceType.clickDescription(transformTechName);
        softAssert.assertTrue(userOnPaymentByServiceType.getCustomerName(testCustomer.customerName),
                "\n Customer Name Is " + testCustomer.customerName +
                        "\n Customer Is Not Available After Selecting A Technician");
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }
}