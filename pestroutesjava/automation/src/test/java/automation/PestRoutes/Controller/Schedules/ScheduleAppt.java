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
import java.io.IOException;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class ScheduleAppt extends BaseClass {
	public String routes = "//div[@class = 'route actualRoute route1 ']";
	private String serviceType = "Roach";
	public String serviceAreaProvided = "Exterior Only";
	public String pestTreaded = "Bat";
	private String product = "000 NEW";
	private String applicationMethod = "Direct Spray";
	private String targetIssue = "Bat";
	private String targetArea = "Back Lawn";
	public String scheduleTime = "08:30";

	CustomerViewDialog_Header overviewHeader = new CustomerViewDialog_Header();
	CustomerviewDialog_AppointmentsTab appointmentTab = new CustomerviewDialog_AppointmentsTab();
	SchedulingTab scheduleDay = new SchedulingTab();
	SchedulingAppointmentDialog confirmAppt = new SchedulingAppointmentDialog();
	RoutePage route = new RoutePage();
	Header header = new Header();
	CreateCustomerDIalog customer = new CreateCustomerDIalog();
	UnitsTab unitsTab = new UnitsTab();
	
	

	public List list;

	@Test
	public void createSchedule() throws Exception {
		String userID = getData("userID", generalData);

		changeToMultiUnit(userID);
		addRoute();
		addAppointment(userID, serviceType, scheduleTime);
		addChemicalInUnitTab();
		addChemical(userID);
		verifyChemicalinUnit();
		verifyChemical();
		AssertException.asserFailure(list);

	}
	
	public void createScheduleWithCustomerName(String customerNameInHeader) throws Exception {
		
		changeToMultiUnit(customerNameInHeader);
		addRoute();
		addAppointment(customerNameInHeader, serviceType, scheduleTime);
		addChemicalInUnitTab();
		addChemical(customerNameInHeader);
		verifyChemicalinUnit();
		verifyChemical();
		AssertException.asserFailure(list);

	}

	public void changeToMultiUnit(String userID) throws IOException, Exception {
		header.Search_A_Customer(userID);	
		overviewHeader.NavigateTo(overviewHeader.infoTabInDialog);
		customer.clickInfo();
		unitsTab.selectUnit("Multi Unit");
		overviewHeader.ClickSaveButton();
		confirmAppt.navigateToUnitTab();
		unitsTab.newUnitClick();
		unitsTab.setupUnit("Harold", "3", "62534");
	}

	public void addRoute() throws Exception {
		header.NavigateTo(header.schedulingTab);
		scheduleDay.addScheduleDateToProperties();
		scheduleDay.clickScheduleDay();
		route.addGroup();
		route.clickButton(route.addRoutesButton);
		route.addRoutesByQuantity("1");
	}

	public void addAppointment(String needUserID, String needServieType, String needTimeSlot) throws Exception {
		header.Search_A_Customer(needUserID);

		overviewHeader.ClickScheduleButton();
		int totalCount = Utilities.getElementCount(routes);
		String routesCount = Integer.toString(totalCount);
		System.out.println(routesCount);
		route.scheduleAppointment(routesCount, needTimeSlot);
		confirmAppt.selectServiceType(needServieType);
		confirmAppt.selectInteriorNeededOption(serviceAreaProvided);
		confirmAppt.selectTargetPestsOption(pestTreaded);
	}

	public void addChemicalInUnitTab() {
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

	public void addChemical(String userID) throws Exception {
		header.Search_A_Customer(userID);	
		overviewHeader.NavigateTo(overviewHeader.appointmentsTabInDialog);
		appointmentTab.clickScheduledService(serviceType);
		appointmentTab.clickStatusButton();
		appointmentTab.clickAddProductButton_InCompletingApptDialog();
		appointmentTab.chooseProduct(product);
		appointmentTab.chooseApplicationMethod(applicationMethod);
		appointmentTab.chooseTargetIssue(targetIssue);
		appointmentTab.chooseTargetArea(targetArea);
		appointmentTab.chooseInteriorServiced(getData("interiorServiced", generalData));
		appointmentTab.clickSaveAndCompleteButton();

	}

	public void verifyChemicalinUnit() {
		appointmentTab.clickScheduledService(serviceType);
		appointmentTab.clickUnitName();
		String actualUnitArea = appointmentTab.getUnitAreaTreated();
		String actualUnitPest = appointmentTab.getUnitPestsTreated();
		String actualUnitProductUsed = appointmentTab.getUnitChemicalName();
		list = AssertException.result(product, actualUnitProductUsed, "Validate multiUnit product");
		Reporter.status("Product for multiUnit", product, actualUnitProductUsed, "Add Chemicals To An Appointment");
		list = AssertException.result(targetArea, actualUnitArea, "Validate multiUnit target area");
		Reporter.status("target are for multiUnit", targetArea, actualUnitArea, "Add Chemicals To An Appointment");
		list = AssertException.result(targetIssue, actualUnitPest, "Validate multiUnit target issue");
		Reporter.status("target issue for multiUnit", targetIssue, actualUnitPest, "Add Chemicals To An Appointment");

	}

	public void verifyChemical() {
		appointmentTab.clickScheduledService(serviceType);
		String actualProductUsed = appointmentTab.getChemicalName();
		String actualArea = appointmentTab.getTreatedArea();
		String actualPest = appointmentTab.getTreatedPests();
		list = AssertException.result(product, actualProductUsed, "Product Validation");
		Reporter.status("Product ", product, actualProductUsed, "Add Chemicals To An Appointment");
		list = AssertException.result(targetArea, actualArea, "Target Area Validation");
		Reporter.status("Target Area ", targetArea, actualArea, "Add Chemicals To An Appointment");
		Reporter.status("Target Issue ", targetIssue, actualPest, "Add Chemicals To An Appointment");
		list = AssertException.result(targetIssue, actualPest, "Target Issue Validation");

	}

}
