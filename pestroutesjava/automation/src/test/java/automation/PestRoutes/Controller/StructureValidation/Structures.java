package automation.PestRoutes.Controller.StructureValidation;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
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
import automation.PestRoutes.PageObject.Structure.StructuresTab;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;

public class Structures extends BaseClass {
	WebDriver driver = GetWebDriver.getInstance();
	CreateCustomerDIalog customer = new CreateCustomerDIalog();
	CustomerViewDialog_Header dialog = new CustomerViewDialog_Header();
	CustomerViewDialog_OverviewTab overview = new CustomerViewDialog_OverviewTab();
	Header header = new Header();
	StructuresTab structures = new StructuresTab();
	RoutePage route = new RoutePage();
	SchedulingAppointmentDialog confirmAppt = new SchedulingAppointmentDialog();
	SchedulingTab scheduleDay = new SchedulingTab();
	UnitsTab unitsTab = new UnitsTab();
	CustomerviewDialog_AppointmentsTab appointmentTab = new CustomerviewDialog_AppointmentsTab();
	public List list = null;

	private String mainStructureName = Utilities.generateRandomString(5);
	private String routes = "//div[@class = 'route actualRoute route1 ']";
	private String serviceType = "Roach";
	private String serviceAreaProvided = "Exterior Only";
	private String pestTreaded = "Bat";
	private String product = "UP-STAR";
	private String applicationMethod = "Direct Spray";
	private String targetIssue = "Bat";
	private String fName = Utilities.generateRandomString(7);
	private String lName = Utilities.generateRandomString(6);
	private String subUnit = Utilities.generateRandomString(5);
	private String subSubUnit = Utilities.generateRandomString(5);

	@Test

	public void validateStructures() throws Exception {

		createStructure();
		addRoute();
		addAppointment();
		addChemicalMainStructureTab();
		addChemicalSubStructureTab();
		verifyChemicalinUnit();
		verifySubChemicalinUnit();

	}

	public void createStructure() throws Exception {

		header.NavigateTo(header.newCustomerTab);
		customer.setFirstName(fName);
		customer.setLastName(lName);
		unitsTab.selectUnit("Structures");
		dialog.ClickSaveButton();
		Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
		String customerNameInHeader = overview.getCustomerNameFromHeader();
		System.out.println("Customer Name found is " + customerNameInHeader);
		list = AssertException.result(fName, customerNameInHeader, "Validate Customer Creation");
		Reporter.status("Created customer ", customerNameInHeader, fName, "Customer creation");
		String id = overview.getCustomerIDFromHeader();
		String newId = id.replaceAll("[^a-zA-Z0-9]+", "");
		System.out.println(newId);
		addData("strutureUID", newId, generalData);
		AssertException.asserFailure(list);
		header.Search_A_Customer(newId);
		dialog.NavigateTo(dialog.structuresTabInDialog);
		structures.setMainStructure(mainStructureName);
		structures.setSubStructure(subUnit);
		structures.setSubStructure(subSubUnit);

	}

	private void addRoute() {
		header.NavigateTo(header.schedulingTab);
		scheduleDay.clickScheduleDay();
		route.addGroup();
		route.clickButton(route.addRoutesButton);
		route.addRoutesByQuantity("1");
	}

	private void addAppointment() throws Exception {
		header.Search_A_Customer(getData("strutureUID", generalData));
		dialog.Click_X_Button();
		int totalCount = Utilities.getElementCount(routes);
		String routesCount = Integer.toString(totalCount);
		System.out.println(routesCount);
		route.scheduleAppointment(routesCount, "08:30");
		structures.clickExistingCustomer(fName + " " + lName, fName);
		confirmAppt.selectServiceType(serviceType);
		confirmAppt.selectInteriorNeededOption(serviceAreaProvided);
		confirmAppt.selectTargetPestsOption(pestTreaded);
		confirmAppt.clickScheduleButton();
	}

	private void addChemicalMainStructureTab() throws InterruptedException {
		structures.clickAppointmentCard();
		structures.clickStructuresTabApt();
		structures.clickDetailsButtonMainStructure(mainStructureName);
		structures.clickAddProductMainStructure();
		appointmentTab.chooseProduct(product);
		appointmentTab.chooseApplicationMethod(applicationMethod);
		appointmentTab.chooseTargetIssue(targetIssue);
		appointmentTab.clickSaveButton();
	}

	private void addChemicalSubStructureTab() throws InterruptedException {
		structures.clickAppointmentCard();
		structures.clickStructuresTabApt();
		structures.clickDetailsButtonSubStructure(subUnit);
		structures.clickAddProductSubStructure();
		appointmentTab.chooseProduct(product);
		appointmentTab.chooseApplicationMethod(applicationMethod);
		appointmentTab.chooseTargetIssue(targetIssue);
		appointmentTab.clickSaveButton();
	}

	private void verifyChemicalinUnit() throws IOException, Exception {
		header.Search_A_Customer(fName + " " + lName);
		dialog.NavigateTo(dialog.structuresTabInDialog);
		appointmentTab.clickScheduledStructuredService(mainStructureName);
		structures.clickProductsAptTab();
		structures.getChemicalNameStructure(product);
		appointmentTab.clickStructureName();
		String actualStructureArea = appointmentTab.getStructureAreaTreated();
		String actualStructureIssues = appointmentTab.getStructureIssuesTreated();
		String actualStructureProductUsed = appointmentTab.getStructureChemicalName();
		list = AssertException.result(product, actualStructureArea, "Validate Structure product");
		Reporter.status("Chemical for Structure", product, actualStructureArea, "Add Chemicals To An Appointment");
		list = AssertException.result(product, actualStructureProductUsed, "Validate Structure product");
		Reporter.status("Product for Structure", product, actualStructureProductUsed,
				"Add Chemicals To An Appointment");
		list = AssertException.result(targetIssue, actualStructureIssues, "Validate Struture target issue");
		Reporter.status("Target issue for Structure", targetIssue, actualStructureIssues,
				"Add Chemicals To An Appointment");
	}

	private void verifySubChemicalinUnit() throws IOException, Exception {
		header.Search_A_Customer(fName + " " + lName);
		dialog.NavigateTo(dialog.structuresTabInDialog);
		appointmentTab.clickSubScheduledStructuredService(mainStructureName, subUnit);
		structures.clickProductsAptTab();
		structures.getChemicalNameStructure(product);
		appointmentTab.clickStructureName();
		String actualStructureArea = appointmentTab.getStructureAreaTreated();
		String actualStructureIssues = appointmentTab.getStructureIssuesTreated();
		String actualStructureProductUsed = appointmentTab.getStructureChemicalName();
		list = AssertException.result(product, actualStructureArea, "Validate Structure product");
		Reporter.status("Chemical for Structure", product, actualStructureArea, "Add Chemicals To An Appointment");
		list = AssertException.result(product, actualStructureProductUsed, "Validate Structure product");
		Reporter.status("Product for Structure", product, actualStructureProductUsed,
				"Add Chemicals To An Appointment");
		list = AssertException.result(targetIssue, actualStructureIssues, "Validate Struture target issue");
		Reporter.status("Target issue for Structure", targetIssue, actualStructureIssues,
				"Add Chemicals To An Appointment");
	}

}