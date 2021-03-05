package automation.PestRoutes.Controller.CustomerCreation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import automation.PestRoutes.Utilities.*;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_OverviewTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Admin;

public class AccountStatus extends AppData {
	static ExtentTest test;
	CreateCustomerDialog customer = new CreateCustomerDialog();
	CustomerViewDialog_Header dialog = new CustomerViewDialog_Header();
	CustomerViewDialog_OverviewTab overview = new CustomerViewDialog_OverviewTab();
	Header header = new Header();
	CustomerViewDialog_Admin statusChange = new CustomerViewDialog_Admin();
	List list = new ArrayList<String>();

	@Test
	public void validateAccountStatus() throws Exception {
		createCustomer();
		validateActiveStatus();
		validateFrozenStatus();
		AssertException.assertFailure(list);
	}

	// Creates customer
	public void createCustomer() throws Exception {
		String expectedAlert = "Required: You must fill in the customer's last name or company name!";
		String fName = Utilities.generateRandomString(7);
		String lName = Utilities.generateRandomString(6);
		header.navigateTo(header.newCustomerTab);
		customer.setFirstName(fName);
		customer.selectUnit("Multi Unit");
		dialog.clickSaveButton();
		String alert = Utilities.getAlertText();
		list.add(AssertException.result(expectedAlert, alert, "Validate required field"));
		Utilities.acceptAlert();
		customer.setLastName(lName);
		dialog.clickSaveButton();
		Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
		String customerNameInHeader = overview.getCustomerNameFromHeader();
		System.out.println("Customer Name found is " + customerNameInHeader);
		list.add(AssertException.result(fName, customerNameInHeader, "Validate Customer Creation"));
		String newId = overview.getCustomerIDFromHeader();
		System.out.println(newId);
		addData("userID", newId, generalData);
	}

	// Change the account to Active and assert
	private void validateActiveStatus() throws Exception {
		header.searchCustomer_History(getData("userID", generalData));
		dialog.navigateTo(dialog.adminTabInDialog);
		statusChange.changeAccountStatus_Active();
		statusChange.getAccountStatus();
		String expectedStatus = "status: [Frozen] was changed to [Active]";
		String actualStatus = statusChange.getAccountStatus().substring(16, 57);
		result(expectedStatus, actualStatus, "Activate Status ", "Account Status validation");
	}

	// Change the account to Frozen and assert
	private void validateFrozenStatus() throws IOException, Exception {
		header.searchCustomer_History(getData("userID", generalData));
		dialog.navigateTo(dialog.adminTabInDialog);
		statusChange.changeAccountStatus_Frozen(statusChange.cancelServiceProps);
		statusChange.getAccountStatus();
		String expectedStatus = "status: [Active] was changed to [Frozen]";
		String actualStatus = statusChange.getAccountStatus().substring(16, 57);
		result(expectedStatus, actualStatus, "Frozen Status ", "Account Status validation");
	}
	
	@SuppressWarnings("unchecked")
	private void result(String expected, String actual, String stepName, String testName) {
		if(AssertException.result(expected, actual, stepName).size()>0) {
			list.add(AssertException.result(expected, actual, stepName));
		}
		Reporter.status(stepName,expected, actual, testName);
	}

}