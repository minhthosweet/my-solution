package automation.PestRoutes.Controller.Renewal;

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
	Header header;
	RoutePage route;
	SchedulingTab scheduleDay;
	SchedulingAppointmentDialog confirmAppt;
	ScheduleAppt appt;
	List list = new ArrayList<String>();
	private String serviceType = "Pest Renewal";
	@Test
	public void test() throws Exception {
		renewalFieldsValidation();
		validateSubscriptionWithRenewal("11:30");
		AssertException.asserFailure(list);
	}
	
	public void renewalFieldsValidation() throws Exception {
		header = new Header();
		customerDialogHeader = new CustomerViewDialog_Header();
		header.Search_A_Customer(getData("userID", generalData));
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
	
	public void validateSubscriptionWithRenewal(String needTimeSlot) throws Exception {
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
		
		header.NavigateTo(header.schedulingTab);
		scheduleDay.addScheduleDateToProperties();
		scheduleDay.clickScheduleDay();
		route.addGroup();
		route.clickButton(route.addRoutesButton);
		route.addRoutesByQuantity("1");
		
		header.Search_A_Customer(getData("userID", generalData));
		overviewHeader.ClickScheduleButton();
		int totalCount = Utilities.getElementCount(appt.routes);
		String routesCount = Integer.toString(totalCount);
		System.out.println(routesCount);
		route.scheduleAppointment(routesCount, needTimeSlot);
		confirmAppt.selectServiceType(serviceType);
		confirmAppt.selectInteriorNeededOption(appt.serviceAreaProvided);
		confirmAppt.selectTargetPestsOption(appt.pestTreaded);
		confirmAppt.clickScheduleButton();
		header.Search_A_Customer(getData("userID", generalData));
		overviewHeader.NavigateTo(overviewHeader.appointmentsTabInDialog);
		appointmentTab.clickScheduledService(serviceType);
		appointmentTab.clickStatusButton();
		appointmentTab.clickSaveAndCompleteButton();
		header.Search_A_Customer(getData("userID", generalData));
		overviewHeader.NavigateTo(overviewHeader.subscriptionTabInDialog);
		String expectedRenewalDate = GetDate.addOneYearToDate(getData("scheduleDate", generalData));
		String renewalDate = subscription.getRenewalDate();
		Reporter.status("if renewal date is posted", expectedRenewalDate, renewalDate, "Renewal in subscription");
		list.add(AssertException.result(expectedRenewalDate, renewalDate, "Renewal in subscription"));
		
	}

}
