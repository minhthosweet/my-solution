package automation.PestRoutes.Controller.Renewal;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
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
import automation.PestRoutes.PageObject.RoutePage.SchedulingAppointmentDialog;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;

public class ValidateRenewal extends BaseClass{
	
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
	private String serviceType = "Pest Renewal";
	String currentDate = Utilities.currentDate("M/dd/yyyy");
	String expectedWarning = "Payment amount plus prepayments, is less than renewal amount.";
	@Test
	public void test() throws Exception {
		renewalFieldsValidation();
		createRenewalSubscription();
		scheduleSubscription("9:30");
		completeSchedulesService();
		validateRenewalDate();
		freezeSubscription();
		AssertException.asserFailure(list);
	}
	
	public void renewalFieldsValidation() throws Exception {
		header = new Header();
		customerDialogHeader = new CustomerViewDialog_Header();
		header.Search_A_Customer(getData("customerName", generalData));
		customerDialogHeader.NavigateTo(customerDialogHeader.subscriptionTabInDialog);
		subscription.clickNewSubscriptionButton();
		subscription.selectServiceType(serviceType);
		WebElement renewalDateField = FindElement.elementByAttribute(subscription.renewalDateField, InputType.XPath);
		list.add(AssertException.conditionResult(renewalDateField));
		Reporter.conditionStatus(renewalDateField, "renewal date field", "Renewal in subscription");
		WebElement setRenewalDate = FindElement.elementByAttribute(subscription.setRenewalDateDropdown, InputType.XPath);
		list.add(AssertException.conditionResult(setRenewalDate));
		Reporter.conditionStatus(setRenewalDate, "Set renewal date field", "Renewal in subscription");
		WebElement renewalFrequencyField = FindElement.elementByAttribute(subscription.renewalFrequencyDropdown, InputType.XPath);
		list.add(AssertException.conditionResult(renewalFrequencyField));
		Reporter.conditionStatus(renewalFrequencyField, "Renewal date field", "Renewal in subscription");

		
	}
	
	public void createRenewalSubscription() throws Exception {
		appt = new ScheduleAppt();
		header = new Header();
		scheduleDay = new SchedulingTab();
		route = new RoutePage();
		overviewHeader = new CustomerViewDialog_Header();
		appointmentTab = new CustomerviewDialog_AppointmentsTab();
		confirmAppt = new SchedulingAppointmentDialog();
		customerDialogHeader = new CustomerViewDialog_Header();
		subscription.selectSetRenewalDate("On Initial Service Completion");
		subscription.selectRenewalFrequency("Annually");
		customerDialogHeader.ClickSaveButton();
	}
	public void scheduleSubscription(String needTimeSlot) throws Exception {
		header.NavigateTo(header.schedulingTab);
		scheduleDay.addScheduleDateToProperties();
		scheduleDay.clickScheduleDay();
		route.addGroup();
		route.clickButton(route.addRoutesButton);
		route.addRoutesByQuantity("1");
		
		header.Search_A_Customer(getData("customerName", generalData));
		overviewHeader.ClickScheduleButton();
		int totalCount = Utilities.getElementCount(appt.routes);
		String routesCount = Integer.toString(totalCount);
		System.out.println(routesCount);
		route.scheduleAppointment(routesCount, needTimeSlot);
		confirmAppt.selectServiceType(serviceType);
		confirmAppt.selectInteriorNeededOption(appt.serviceAreaProvided);
		confirmAppt.selectTargetPestsOption(appt.pestTreaded);
		confirmAppt.clickScheduleButton();
	}
	public void completeSchedulesService() throws Exception {
		header.Search_A_Customer(getData("customerName", generalData));
		overviewHeader.NavigateTo(overviewHeader.appointmentsTabInDialog);
		appointmentTab.clickScheduledService(serviceType);
		appointmentTab.clickStatusButton();
		appointmentTab.clickSaveAndCompleteButton();
	}
	public void validateRenewalDate() throws Exception {
		header.Search_A_Customer(getData("userID", generalData));
		overviewHeader.NavigateTo(overviewHeader.subscriptionTabInDialog);
		String expectedRenewalDate = GetDate.addOneYearToDate(getData("scheduleDate", generalData));
		String renewalDate = subscription.getRenewalDate();
		Reporter.status("if renewal date is posted", expectedRenewalDate, renewalDate, "Renewal in subscription");
		list.add(AssertException.result(expectedRenewalDate, renewalDate, "Renewal in subscription"));
		
	}
	
	public String freezeSubscription() throws Exception {
		invoicing = new RoutePageInvoicing();
		invHeader = new Invoice_Header();
		paymentPage = new InvoiceImplementation();
		overviewHeader = new CustomerViewDialog_Header();
		header = new Header();
		System.out.println(currentDate);
		subscription.setServiceQuote(serviceType, "200");
		subscription.setRenewalDate(currentDate);
		subscription.selectBillingFrequency("Renewal");
		double val = subscription.getRecurringTotal();
		String total = value.format(val);
		System.out.println(total);
		overviewHeader.ClickSaveButton();
		subscription.clickDeActivateButton();
		subscription.selectCancellationCategory("Moved");
		subscription.setCancelSubscriptionNotes("Testing");
		subscription.clickFreezeSubscriptionButton();
		return total;
	}
	public void addPayment() throws Exception {
		String total = freezeSubscription();
		overviewHeader.NavigateTo(overviewHeader.invoicesTabInDialog);
		invoicing.clickAddPayment();
		invHeader.navigate(invHeader.cash);
		paymentPage.setLimitedToSubscription();
		String paymentWarning = paymentPage.getPaymentWarning();
		list.add(AssertException.result(expectedWarning, paymentWarning, "Validate warning"));
		Reporter.status("payment warning to renew subscription", expectedWarning, paymentWarning, "Renewal in subscription");
		paymentPage.insertPaymentAmount(total, total);
		String expectedRenewalDateAttributeValue = "display: block;";
		String renewalDateAttributeValue = Utilities.getAttributeValue(paymentPage.renewalDateField, "style");
		list.add(AssertException.result(expectedRenewalDateAttributeValue, renewalDateAttributeValue, "Validate warning"));
		Reporter.status("if renewal date is checked", expectedRenewalDateAttributeValue, renewalDateAttributeValue, "Renewal in subscription");
		paymentPage.clickrecordPayment();
	}
	public void validateActivationOfSubscription() throws Exception {
		String expectedRenewalDate = GetDate.addOneYearToDate(currentDate);
		header.Search_A_Customer(getData("customerName", generalData));
		overviewHeader.NavigateTo(overviewHeader.subscriptionTabInDialog);
		String renewalDate = subscription.getRenewalDate();
		list.add(AssertException.result(expectedRenewalDate, renewalDate, "Validate renewal"));
		Reporter.status("if renewal changed after full payment", expectedRenewalDate, renewalDate, "Renewal in subscription");
		String expectedStatus = "Active";
		String actualStatus = subscription.getStatusText();
		list.add(AssertException.result(expectedStatus, actualStatus, "Validate subscription status"));
		Reporter.status("if status gets activate", expectedStatus, actualStatus, "Renewal in subscription");
		
	}

}
