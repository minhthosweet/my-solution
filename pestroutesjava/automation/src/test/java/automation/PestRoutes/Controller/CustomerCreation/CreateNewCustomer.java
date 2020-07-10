package automation.PestRoutes.Controller.CustomerCreation;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDIalog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_OverviewTab;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateNewCustomer extends BaseClass {
	static ExtentTest test;
	CreateCustomerDIalog customer;
	CustomerViewDialog_Header dialog;
	CustomerViewDialog_OverviewTab overview;
	Header header;
	List list = new ArrayList<String>();

	public String fName = Utilities.generateRandomString(7);
	public String lName = Utilities.generateRandomString(6);
	String expectedAlert = "Required: You must fill in the customer's last name or company name!";
	public String streetAddress = "4500 W Eldorado Pkwy STE 3200";
	String city = "McKinney";
	String zipcode = "75070";
	public String email = "test@gmail.com";

	@Test
	public void createCustomer() throws Exception {
		createCustomerWithOutRequiredField();
		validateRequiredFieldError();
		createCustomerWithAddress();
		validateCreatedCustomerNameAndAddress();
		validateIfFailureExist();

	}

	@When("I create customer without required last name field")
	public void createCustomerWithOutRequiredField() {
		String fName = Utilities.generateRandomString(7);
		dialog = new CustomerViewDialog_Header();
		customer = new CreateCustomerDIalog();
		overview = new CustomerViewDialog_OverviewTab();
		header = new Header();
		header.NavigateTo(header.newCustomerTab);
		customer.setFirstName(fName);
		customer.selectUnit("Multi Unit");
		dialog.ClickSaveButton();
	}

	@Then("I validate alert")
	public void validateRequiredFieldError() {
		String alert = Utilities.getAlertText();
		Utilities.acceptAlert();
		result(expectedAlert, alert, "required field while creating customer ", "Customer creation");
	}

	@When("I create customer with first name, last name and address")
	public void createCustomerWithAddress() throws Exception {
		dialog = new CustomerViewDialog_Header();
		customer = new CreateCustomerDIalog();
		overview = new CustomerViewDialog_OverviewTab();
		header = new Header();
		header.NavigateTo(header.newCustomerTab);
		customer.setFirstName(fName);
		customer.setLastName(lName);
		customer.selectUnit("Multi Unit");
		customer.setAddress(streetAddress);
		customer.setZipCode(zipcode);
		customer.setCity(city);
		customer.setCellPhone(getData("phoneNumber",generalData));
		customer.clickSmsCheckBox();
		customer.clickEmailCheckBox();
		customer.clickVoiceCheckBox();
		dialog.ClickSaveButton();
		alertCondition();
		captureUserIdAndFullName();
	}
	
	@And("I search customer")
	public void searchCustomer() throws Exception {
		header = new Header();
		header.Search_A_Customer(fName + " " + lName);
	}
	
	@When("I create customer with first name, last name, email and address")
	public void createCustomerWithEmail() throws Exception {
		dialog = new CustomerViewDialog_Header();
		customer = new CreateCustomerDIalog();
		overview = new CustomerViewDialog_OverviewTab();
		header = new Header();
		header.NavigateTo(header.newCustomerTab);
		customer.setFirstName(fName);
		customer.setLastName(lName);
		customer.setEmailAddress(email);
		customer.selectUnit("Multi Unit");
		customer.setAddress(streetAddress);
		customer.setZipCode(zipcode);
		customer.setCellPhone(getData("phoneNumber",generalData));
		customer.clickSmsCheckBox();
		dialog.ClickSaveButton();
		alertCondition();
		captureUserIdAndFullName();
	}

	@When("I create customer with first name and last name")
	public void createCustomerWithoutAddress() throws Exception {

		dialog = new CustomerViewDialog_Header();
		customer = new CreateCustomerDIalog();
		overview = new CustomerViewDialog_OverviewTab();
		header = new Header();
		header.NavigateTo(header.newCustomerTab);
		customer.setFirstName(fName);
		customer.setLastName(lName);
		customer.selectUnit("Multi Unit");
		customer.setCellPhone(getData("phoneNumber",generalData));
		customer.clickSmsCheckBox();
		dialog.ClickSaveButton();
		alertCondition();
		captureUserIdAndFullName();
	}

	@Then("I validate if customer name and address match in overview tab")
	public void validateCreatedCustomerNameAndAddress() {
		String expectedAddress = streetAddress + " " + city + ", TX " + zipcode;
		Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
		String customerNameInHeader = overview.getCustomerNameFromHeader();
		System.out.println("Customer Name found is " + customerNameInHeader);
		String actualAddress = overview.getAddress();
		result(fName, customerNameInHeader, "Created customer name ", "Customer creation");
		result(expectedAddress, actualAddress, "Created customer address", "Customer creation");
	}

	@Then("I validate if customer name match in overview tab")
	public void validateCreatedCustomerName() {
		Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
		String customerNameInHeader = overview.getCustomerNameFromHeader();
		result(fName, customerNameInHeader, "Created customer name ", "Customer creation");
	}

	@And("I validate if there are errors exist in the list")
	public void validateIfFailureExist() {
		AssertException.asserFailure(list);
	}

	@SuppressWarnings("unchecked")
	private void result(String expected, String actual, String stepName, String testName) {
		if (AssertException.result(expected, actual, stepName).size() > 0) {
			list.add(AssertException.result(expected, actual, stepName));
		}
		Reporter.status(stepName, expected, actual, testName);
	}

	private void alertCondition() throws Exception {
		int i = 0;
		while (i++ < 5) {
			try {
				Alert alert = Utilities.alertPopUp();
				String actionAlert = Utilities.getAlertText();
				String expected = "Action Required!";
				if (actionAlert.contains(expected)) {
					alert.accept();
					Utilities.clickElement("//div[text()='Save Anyways']", ElementType.XPath);
				}
				break;
			} catch (NoAlertPresentException e) {
				Thread.sleep(500);
				continue;
			}
		}
	}

	private void captureUserIdAndFullName() throws Exception {
		String id = overview.getCustomerIDFromHeader();
		String newId = id.replaceAll("[^a-zA-Z0-9]+", "");
		addData("userID", newId, generalData);
		addData("customerName", fName + " " + lName, generalData);
	}

}
