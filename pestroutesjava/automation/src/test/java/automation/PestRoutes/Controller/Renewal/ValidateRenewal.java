package automation.PestRoutes.Controller.Renewal;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Schedules.ScheduleAppt;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.Invoicing.Invoice_Header;
import automation.PestRoutes.PageObject.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.PageObject.RoutePage.RoutePage;
import automation.PestRoutes.PageObject.Scheduling.SchedulingAppointmentDialog;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import io.cucumber.java.en.And;

public class ValidateRenewal extends BaseClass {

    CustomerViewDialog_SubscriptionTab subscription = new CustomerViewDialog_SubscriptionTab();
    CustomerViewDialog_Header customerDialogHeader;
    CustomerviewDialog_AppointmentsTab appointmentTab;
    CustomerViewDialog_Header overviewHeader;
    InvoiceImplementation paymentPage;
    Invoice_Header invHeader;
    RoutePageInvoicing invoicing;
    Header header;
    RoutePage route;
    SchedulingTab scheduleDay;
    SchedulingAppointmentDialog confirmAppt;
    ScheduleAppt appt;
    List list = new ArrayList<String>();
    private static DecimalFormat value = new DecimalFormat("0.00");
    public String serviceType;

    {
        try {
            serviceType = getData("serviceDescription", generalData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String currentDate = Utilities.currentDate("M/dd/yyyy");
    String expectedWarning = "Payment amount plus prepayments, is less than renewal amount.";

    @Test
    public void test() throws Exception {
        renewalFieldsValidation();
        createRenewalSubscription();
        //scheduleSubscription("06:30",getData("customerName", generalData));
        completeSchedulesService();
        validateRenewalDate();
        //freezeSubscription();
        addPayment();
        validateActivationOfSubscription();
        AssertException.assertFailure(list);
    }

    @And("I validate if renewal fields display in Subscription tab if I choose renewal as service type")
    public void renewalFieldsValidation() throws Exception {
        header = new Header();
        customerDialogHeader = new CustomerViewDialog_Header();
        customerDialogHeader.navigateTo(customerDialogHeader.subscriptionTabInDialog);
        subscription.clickNewSubscriptionButton();
        subscription.selectServiceType(serviceType);
        WebElement renewalDateField = FindElement.elementByAttribute(subscription.renewalDateField, InputType.XPath);
        if (AssertException.conditionResult(renewalDateField).size() > 0) {
            list.add(AssertException.conditionResult(renewalDateField));
        }
        Reporter.conditionStatus(renewalDateField, "renewal date field", "Renewal in subscription");
        WebElement setRenewalDate = FindElement.elementByAttribute(subscription.setRenewalDateDropdown, InputType.XPath);
        if (AssertException.conditionResult(setRenewalDate).size() > 0) {
            list.add(AssertException.conditionResult(setRenewalDate));
        }
        Reporter.conditionStatus(setRenewalDate, "Set renewal date field", "Renewal in subscription");
        WebElement renewalFrequencyField = FindElement.elementByAttribute(subscription.renewalFrequencyDropdown, InputType.XPath);
        if (AssertException.conditionResult(renewalFrequencyField).size() > 0) {
            list.add(AssertException.conditionResult(renewalFrequencyField));
        }
        Reporter.conditionStatus(renewalFrequencyField, "Renewal date field", "Renewal in subscription");
    }

    @And("I create a renewal subscription")
    public void createRenewalSubscription() throws Exception {
        customerDialogHeader = new CustomerViewDialog_Header();
        customerDialogHeader.navigateTo(customerDialogHeader.subscriptionTabInDialog);
        subscription.clickNewSubscriptionButton();
        subscription.selectServiceType(serviceType);
        subscription.selectSetRenewalDate("On Initial Service Completion");
        subscription.selectRenewalFrequency("Annually");
        customerDialogHeader.clickSaveButton();
    }

    @And("I navigate to scheduling tab")
    public void navigateToSchedulingTab() throws Exception {
        header = new Header();
        scheduleDay = new SchedulingTab();
        header.NavigateTo(header.schedulingTab);
        scheduleDay.addScheduleDateToProperties();
        scheduleDay.clickScheduleDay();
    }

    @And("I navigate to scheduling on same Day")
    public void navigateToSchedulingSameDayTab() throws Exception {
        header = new Header();
        scheduleDay = new SchedulingTab();
        header.NavigateTo(header.schedulingTab);
        scheduleDay.addScheduleDateToProperties();
        scheduleDay.clickScheduleSameDay();
    }

    @And("I navigate to {string} days before on scheduling tab")
    public void navigateToSchedulingPreviousDayTab(String daysBefore) throws Exception {
        header = new Header();
        scheduleDay = new SchedulingTab();
        header.NavigateTo(header.schedulingTab);
        scheduleDay.addScheduleDateToProperties();
        scheduleDay.clickScheduleDaysBefore(daysBefore);
    }

    @And("I schedule an service appointment")
    public void scheduleAnAppointment() throws Exception {
        appt = new ScheduleAppt();
        route = new RoutePage();
        confirmAppt = new SchedulingAppointmentDialog();
        scheduleDay = new SchedulingTab();
        scheduleDay.clickScheduleButton();
        int totalCount = Utilities.getElementCount(appt.routes);
        String routesCount = Integer.toString(totalCount);
        route.scheduleAppointment(routesCount, getData("timeSlot", generalData));
        confirmAppt.selectServiceType(serviceType);
        confirmAppt.selectInteriorNeededOption(appt.serviceAreaProvided);
        confirmAppt.selectTargetPestsOption(appt.pestTreaded);
        confirmAppt.clickScheduleButton();
    }

    @And("I schedule a subscription appointment")
    public void subscriptionAppointment() throws Exception {
        appt = new ScheduleAppt();
        route = new RoutePage();
        confirmAppt = new SchedulingAppointmentDialog();
        scheduleDay = new SchedulingTab();
        scheduleDay.clickScheduleButton();
        int totalCount = Utilities.getElementCount(appt.routes);
        String routesCount = Integer.toString(totalCount);
        route.scheduleAppointment(routesCount, getData("timeSlot", generalData));
        scheduleDay.selectServiceType(getData("quarterly", quarterlyPreferredDayData));
        confirmAppt.clickScheduleButton();
    }

    public void scheduleSubscription(String needTimeSlot) throws Exception {
        header = new Header();
        route = new RoutePage();
        appt = new ScheduleAppt();
        scheduleDay = new SchedulingTab();
        confirmAppt = new SchedulingAppointmentDialog();
        scheduleDay = new SchedulingTab();

        header.NavigateTo(header.schedulingTab);
        scheduleDay.addScheduleDateToProperties();
        scheduleDay.clickScheduleDay();
        route.addGroup();
        route.clickButton(route.addRoutesButton);
        route.addRoutesByQuantity("1");

        scheduleDay.clickScheduleButton();
        int totalCount = Utilities.getElementCount(appt.routes);
        String routesCount = Integer.toString(totalCount);
        System.out.println(routesCount);
        route.scheduleAppointment(routesCount, needTimeSlot);
        confirmAppt.selectServiceType(serviceType);
        confirmAppt.selectInteriorNeededOption(appt.serviceAreaProvided);
        confirmAppt.selectTargetPestsOption(appt.pestTreaded);
        confirmAppt.clickScheduleButton();
    }

    @And("I complete an appointment")
    public void completeSchedulesService() throws Exception {
        header = new Header();
        overviewHeader = new CustomerViewDialog_Header();
        appointmentTab = new CustomerviewDialog_AppointmentsTab();
        overviewHeader.navigateTo(overviewHeader.appointmentsTabInDialog);
        appointmentTab.clickScheduledService(serviceType);
        appointmentTab.clickStatusButton();
        appointmentTab.clickSaveAndCompleteButton();
    }

    @And("I complete a subscription appointment")
    public void completeScheduledSubscriptionService() throws Exception {
        header = new Header();
        overviewHeader = new CustomerViewDialog_Header();
        appointmentTab = new CustomerviewDialog_AppointmentsTab();
        overviewHeader.navigateTo(overviewHeader.appointmentsTabInDialog);
        appointmentTab.clickScheduledService(getData("quarterly", quarterlyPreferredDayData));
        appointmentTab.clickStatusButton();
        appointmentTab.clickSaveAndCompleteButton();
    }

    @And("I cancel the appointment")
    public void cancelAppointment() throws InterruptedException, IOException {
        appointmentTab = new CustomerviewDialog_AppointmentsTab();
        overviewHeader = new CustomerViewDialog_Header();
        overviewHeader.navigateTo(overviewHeader.appointmentsTabInDialog);
        appointmentTab.clickScheduledService(getData("quarterly", quarterlyPreferredDayData));
        appointmentTab.clickCancelAppointmentButton();
        appointmentTab.cancellationNotes();
        appointmentTab.confirmCancellation();
    }

    @And("I reschedule an appointment")
    public void rescheduleSchedulesService() throws Exception {
        route = new RoutePage();
        confirmAppt = new SchedulingAppointmentDialog();
        overviewHeader = new CustomerViewDialog_Header();
        appointmentTab = new CustomerviewDialog_AppointmentsTab();
        overviewHeader.navigateTo(overviewHeader.appointmentsTabInDialog);
        appointmentTab.clickScheduledService(getData("quarterly", quarterlyPreferredDayData));
        appointmentTab.clickEditButton_AppointmentCard();
        appointmentTab.clickRescheduleButton();
        overviewHeader.clickCloseButton();
        int totalCount = Utilities.getElementCount(appt.routes);
        String routesCount = Integer.toString(totalCount);
        System.out.println(routesCount);
        route.scheduleAppointment(routesCount, getData("timeSlot", generalData));
        confirmAppt.clickRescheduleButton();


    }

    @Then("I validate if the renewal date has posted")
    public void validateRenewalDate() throws Exception {
        header = new Header();
        overviewHeader = new CustomerViewDialog_Header();
        overviewHeader.navigateTo(overviewHeader.subscriptionTabInDialog);
        String expectedRenewalDate = GetDate.addOneYearToDate(getData("scheduleDate", generalData));
        String renewalDate = subscription.getRenewalDate();
        result(expectedRenewalDate, renewalDate, "if renewal date is posted", "Subscription Renewal");
    }

    @And("I freeze the subscription")
    public void freezeSubscription() throws InterruptedException {
        overviewHeader = new CustomerViewDialog_Header();
        header = new Header();
        customerDialogHeader = new CustomerViewDialog_Header();
        customerDialogHeader.navigateTo(customerDialogHeader.subscriptionTabInDialog);
        subscription.clickActivateDeActivateButton();
        subscription.selectCancellationCategory("Moved");
        subscription.setCancelSubscriptionNotes("Testing");
        subscription.clickFreezeSubscriptionButton();

    }

    @And("I reactive a frozen subscription")
    public void reActivateSubscription() throws Exception {
        customerDialogHeader = new CustomerViewDialog_Header();
        customerDialogHeader.navigateTo(customerDialogHeader.subscriptionTabInDialog);
        subscription.clickActivateDeActivateButton();
    }

    @And("I get the subscription total")
    public String subscriptionTotal() throws Exception {
        System.out.println(currentDate);
        subscription.setServiceQuote(serviceType, "200");
        Thread.sleep(500);
        subscription.selectBillingFrequency("Renewal");
        Thread.sleep(500);
        subscription.setRenewalDate(currentDate);
        double val = subscription.getRecurringTotal();
        String total = value.format(val);
        System.out.println(total);
        overviewHeader.clickSaveButton();
        return total;
    }

    @And("I pay the subscription")
    public void addPayment() throws Exception {
        invHeader = new Invoice_Header();
        invoicing = new RoutePageInvoicing();
        paymentPage = new InvoiceImplementation();
        overviewHeader = new CustomerViewDialog_Header();
        header = new Header();
        //header.Search_A_Customer(getData("userID", generalData));
        String total = subscriptionTotal();
        overviewHeader.navigateTo(overviewHeader.invoicesTabInDialog);
        invoicing.clickAddPayment();
        System.out.println("clicked");
        for (int i = 0; i < 10; i++) {
            //System.out.println(i);
            try {
                invHeader.navigate(invHeader.cash);
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                    invoicing.jsClickAddPayment();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        paymentPage.setLimitedToSubscription(getData("serviceDescription", generalData));
        Utilities.clickElement(paymentPage.confirmPymtAmtField, ElementType.XPath);
        String paymentWarning = paymentPage.getPaymentWarning();
        result(expectedWarning, paymentWarning, "payment warning to renew subscription", "Subscription Renewal");
        paymentPage.insertPaymentAmount(total, total);
        String expectedRenewalDateAttributeValue = "display: block;";
        String renewalDateAttributeValue = Utilities.getAttributeValue(paymentPage.renewalDateField, "style");
        result(expectedRenewalDateAttributeValue, renewalDateAttributeValue, "if renewal date is checked", "Subscription Renewal");
        paymentPage.clickrecordPayment();
    }

    @Then("I validate if renewal date and account status changed")
    public void validateActivationOfSubscription() throws Exception {
        header = new Header();
        overviewHeader = new CustomerViewDialog_Header();
        String expectedRenewalDate = GetDate.addOneYearToDate(currentDate);
        //header.Search_A_Customer(getData("customerName", generalData));
        overviewHeader.navigateTo(overviewHeader.subscriptionTabInDialog);
        String renewalDate = subscription.getRenewalDate();
        result(expectedRenewalDate, renewalDate, "if renewal date changed after full payment", "Subscription Renewal");
        String expectedStatus = "Active";
        String actualStatus = subscription.getStatusText();
        result(expectedStatus, actualStatus, "Renewal - Validate if status gets active", "Subscription Renewal");
    }

    @SuppressWarnings({"unchecked"})
    private void result(String expected, String actual, String stepName, String testName) {
        if (AssertException.result(expected, actual, stepName).size() > 0) {
            list.add(AssertException.result(expected, actual, stepName));
        }
        Reporter.status(stepName, expected, actual, testName);
    }

}
