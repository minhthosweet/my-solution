package automation.PestRoutes.Controller.Schedules;

import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDIalog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_OverviewTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.RoutePage.RoutePage;
import automation.PestRoutes.PageObject.RoutePage.SchedulingAppointmentDialog;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;

import automation.PestRoutes.PageObject.Scheduling.UnitsTab;

import automation.PestRoutes.Utilities.AssertException;

import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import java.util.List;

import org.openqa.selenium.WebElement;


public class ScheduleAppt extends BaseClass {
	private String routes = "//div[@class = 'route actualRoute route1 ']";
	private String serviceType = "Roach";
	private String serviceAreaProvided = "Exterior Only";
	private String pestTreaded = "Bat";
	private String product = "000 NEW";
	private String applicationMethod = "Direct Spray";
	private String targetIssue = "Bat";
	private String targetArea = "Back Lawn";

	CustomerViewDialog_Header overviewHeader;
	CustomerviewDialog_AppointmentsTab appointmentTab;
	SchedulingTab scheduleDay;
	SchedulingAppointmentDialog confirmAppt;
	RoutePage route;
	Header mainHeader;

	CreateCustomerDIalog customer;
	CustomerViewDialog_OverviewTab customerViewTab;
	UnitsTab unitsTab;

	public List list;


	@Test
	public void createSchedule() throws Exception {

		mainHeader = new Header();
		route = new RoutePage();
		overviewHeader = new CustomerViewDialog_Header();
		scheduleDay = new SchedulingTab();
		confirmAppt = new SchedulingAppointmentDialog();
		appointmentTab = new CustomerviewDialog_AppointmentsTab();
		customer = new CreateCustomerDIalog();
		unitsTab = new UnitsTab();

		changeToMultiUnit();
		addRoute();
		addAppointment();
		addChemicalInUnitTab();
		addChemical();
		verifyChemicalinUnit();
		verifyChemical();
		AssertException.asserFailure(list);

	}

	private void changeToMultiUnit() throws IOException, Exception {
		mainHeader.Search_A_Customer(getData("userID", generalData));
		customer.clickInfo();
		unitsTab.selectUnit("Multi Unit");
		overviewHeader.ClickSaveButton();
		confirmAppt.navigateToUnitTab();
		unitsTab.newUnitClick();
		unitsTab.setupUnit("Harold", "3", "62534");
	}

	private void addRoute() {
		mainHeader.NavigateTo(mainHeader.schedulingTab);
		scheduleDay.clickScheduleDay();
		route.addGroup();
		route.clickButton(route.addRoutesButton);
		route.addRoutesByQuantity("1");
	}

	private void addAppointment() throws Exception {
		mainHeader.Search_A_Customer(getData("userID", generalData));
		
		overviewHeader.ClickScheduleButton();
		int totalCount = Utilities.getElementCount(routes);
		String routesCount = Integer.toString(totalCount);
		System.out.println(routesCount);
		route.scheduleAppointment(routesCount, "08:30");
		confirmAppt.selectServiceType(serviceType);
		confirmAppt.selectInteriorNeededOption(serviceAreaProvided);
		confirmAppt.selectTargetPestsOption(pestTreaded);
	}

	private void addChemicalInUnitTab() {
		unitsTab.clickUnitsScheduleApt();
		unitsTab.AddUnitsSchApt();
		unitsTab.clickDetails();
		confirmAppt.clickAddProduct();
		appointmentTab.chooseProduct(product);
		appointmentTab.chooseApplicationMethod(applicationMethod);
		appointmentTab.chooseTargetIssue(targetIssue);
		appointmentTab.chooseTargetArea(targetArea);
		confirmAppt.clickScheduleButton();

	}

	private void addChemical() throws Exception {
		mainHeader.Search_A_Customer(getData("userID", generalData));
		overviewHeader.NavigateTo(overviewHeader.appointmentsTabInDialog);
		appointmentTab.clickScheduledService(serviceType);
		appointmentTab.clickStatusButton();
		appointmentTab.clickAddProductButton_InCompletingApptDialog();
		appointmentTab.chooseProduct(product);
		appointmentTab.chooseApplicationMethod(applicationMethod);
		appointmentTab.chooseTargetIssue(targetIssue);
		appointmentTab.chooseTargetArea(targetArea);
		appointmentTab.clickSaveAndCompleteButton();

	}

	private void verifyChemicalinUnit() {
		appointmentTab.clickScheduledService(serviceType);
		appointmentTab.clickUnitName();
		String actualUnitArea = appointmentTab.getUnitAreaTreated();
		String actualUnitPest = appointmentTab.getUnitPestsTreated();
		String actualUnitProductUsed = appointmentTab.getUnitChemicalName();
		list = AssertException.result(product, actualUnitProductUsed, "Validate multiUnit product");
		Reporter.status("Product for multiUnit",product, actualUnitProductUsed, "Add Chemicals To An Appointment");
		list = AssertException.result(targetArea, actualUnitArea, "Validate multiUnit target area");
		Reporter.status("target are for multiUnit",targetArea, actualUnitArea, "Add Chemicals To An Appointment");
		list = AssertException.result(targetIssue, actualUnitPest, "Validate multiUnit target issue");
		Reporter.status("target issue for multiUnit",targetIssue, actualUnitPest, "Add Chemicals To An Appointment");

	}

	private void verifyChemical() {
		appointmentTab.clickScheduledService(serviceType);
		String actualProductUsed = appointmentTab.getChemicalName();
		String actualArea = appointmentTab.getTreatedArea();
		String actualPest = appointmentTab.getTreatedPests();



		list = AssertException.result(product, actualProductUsed, "Product Validation");
		Reporter.status("Product ",product, actualProductUsed, "Add Chemicals To An Appointment");
		list = AssertException.result(targetArea, actualArea, "Target Area Validation");
		Reporter.status("Target Area ",targetArea, actualArea, "Add Chemicals To An Appointment");
		Reporter.status("Target Issue ",targetIssue, actualPest, "Add Chemicals To An Appointment");
		list = AssertException.result(targetIssue, actualPest, "Target Issue Validation");

	}

}
