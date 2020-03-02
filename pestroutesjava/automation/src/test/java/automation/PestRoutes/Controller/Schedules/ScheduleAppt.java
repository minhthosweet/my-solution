package automation.PestRoutes.Controller.Schedules;

import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.RoutePage.RoutePage;
import automation.PestRoutes.PageObject.RoutePage.SchedulingAppointmentDialog;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;

import static org.testng.Assert.assertTrue;

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
	public List list = null;

	@Test
	public void createSchedule() throws Exception {

		mainHeader = new Header();
		route = new RoutePage();
		overviewHeader = new CustomerViewDialog_Header();
		scheduleDay = new SchedulingTab();
		confirmAppt = new SchedulingAppointmentDialog();
		appointmentTab = new CustomerviewDialog_AppointmentsTab();

		addRoute();
		addAppointment();
		addChemical();
		verifyChemical();
		AssertException.asserFailure(list);

	}

	private void addRoute() {
		mainHeader.NavigateTo(mainHeader.schedulingTab);
		scheduleDay.clickScheduleDay();
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
		try {
			WebElement unitTab = FindElement.elementByAttribute(confirmAppt.unitsTab, InputType.XPath);
			if (unitTab.isDisplayed()) {
				addChemicalInUnitTab();
			}
		} catch (Exception e) {
			System.out.println("Exception is == " + e.getMessage());
		}
		confirmAppt.clickScheduleButton();
	}

	private void addChemicalInUnitTab() {
		confirmAppt.navigateToUnitTab();
		confirmAppt.selectUnit();
		confirmAppt.clickDetailLink();
		confirmAppt.clickAddProduct();
		appointmentTab.chooseProduct(product);
		appointmentTab.chooseApplicationMethod(applicationMethod);
		appointmentTab.chooseTargetIssue(targetIssue);
		appointmentTab.chooseTargetArea(targetArea);
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
