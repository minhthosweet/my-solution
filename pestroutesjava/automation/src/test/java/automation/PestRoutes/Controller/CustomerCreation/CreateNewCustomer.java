package automation.PestRoutes.Controller.CustomerCreation;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDIalog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_OverviewTab;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;

public class CreateNewCustomer extends BaseClass {
	static ExtentTest test;
	CreateCustomerDIalog customer;
	CustomerViewDialog_Header dialog;
	CustomerViewDialog_OverviewTab overview;
	Header header;
	List list = new ArrayList<String>();

	
	@Test(groups = "createCustomer")
	public void CreateCustomer() throws Exception {

		String expectedAlert = "Required: You must fill in the customer's last name or company name!";
		String streetAddress = "4500 W Eldorado Pkwy STE 3200";
		String city = "Wylie";
		String zipcode = "75098";
		String expectedAddress = streetAddress + " " + city +", TX " + zipcode;
		String fName = Utilities.generateRandomString(7);
		String lName = Utilities.generateRandomString(6);
		dialog = new CustomerViewDialog_Header();
		customer = new CreateCustomerDIalog();
		overview = new CustomerViewDialog_OverviewTab();
		header = new Header();
		header.NavigateTo(header.newCustomerTab);
		customer.setFirstName(fName);
		customer.selectUnit("Multi Unit");
		dialog.ClickSaveButton();
		String alert = Utilities.getAlertText();
		result(expectedAlert, alert, "required field while creating customer ", "Customer creation");
		Utilities.acceptAlert();
		customer.setLastName(lName);
		customer.setAddress(streetAddress);
		customer.setZipCode(zipcode);
		customer.setCity(city);
		dialog.ClickSaveButton();
		Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
		String customerNameInHeader = overview.getCustomerNameFromHeader();
		System.out.println("Customer Name found is " + customerNameInHeader);
		String actualAddress = overview.getAddress();
		result(fName, customerNameInHeader, "Created customer ", "Customer creation");
		result(expectedAddress, actualAddress, "customer address", "Customer creation");
		String id = overview.getCustomerIDFromHeader();
		String newId = id.replaceAll("[^a-zA-Z0-9]+", "");
		System.out.println(newId);
		addData("userID", newId, generalData);
		addData("customerName", fName + " " + lName, generalData);
		AssertException.asserFailure(list);
	}
	@SuppressWarnings("unchecked")
	private void result(String expected, String actual, String stepName, String testName) {
		if(AssertException.result(expected, actual, stepName).size()>0) {
			list.add(AssertException.result(expected, actual, stepName));
		}
		Reporter.status(stepName,expected, actual, testName);
	}

}
