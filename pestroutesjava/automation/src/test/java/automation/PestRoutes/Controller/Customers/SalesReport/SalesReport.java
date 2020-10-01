package automation.PestRoutes.Controller.Customers.SalesReport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import automation.PestRoutes.Utilities.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.testng.annotations.Test;

import automation.PestRoutes.Controller.Admin.Users.AddUser;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Schedules.ScheduleAppt;
import automation.PestRoutes.Controller.Subscriptions.AddSubscription;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.UsersPage.AddUserDialog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_OverviewTab;
import automation.PestRoutes.PageObject.Customers.CustomersMainPage;
import automation.PestRoutes.PageObject.Customers.SalesReport.SalesReportPage;

public class SalesReport extends AppData {

	Header header;
	AddUser addUser;
	AdminMainPage adminPage;
	CreateNewCustomer createNewCustomer;
	AddSubscription addNewSubscription;
	SalesReportPage salesReportPage;
	CustomersMainPage customersMainPage;
	ScheduleAppt scheduleAppt;
	CustomerViewDialog_OverviewTab overview;
	AssertException assertException;

	List list = new ArrayList<String>();
	double customerContractValue;

	@Test
	public void test() throws Exception {
		addUser = new AddUser();
		adminPage = new AdminMainPage();
		createNewCustomer = new CreateNewCustomer();
		addNewSubscription = new AddSubscription();
		scheduleAppt = new ScheduleAppt();
		salesReportPage = new SalesReportPage();
		overview = new CustomerViewDialog_OverviewTab();

		String salesmanName = getData("salesmanName", generalData);
		String subscriptionFlagName = getData("subscriptionFlagName", generalData);
		String accountTypeField = getData("accountTypeField", generalData);


		addUser.createUser(accountTypeField);
		createNewCustomer.createCustomerWithAddress();
		String customerNameInHeader = createNewCustomer.fName + " " + createNewCustomer.lName;
		scheduleAppt.createScheduleWithCustomerName(customerNameInHeader);
		runSoldSubcriptionsReport();
		validateSubscriptionFlagColumn();
		addUser.deactivateUser();

	}

	@And("I run sold subscription report")
	public void runSoldSubcriptionsReport() throws IOException {
		header = new Header();
		salesReportPage = new SalesReportPage();
		customersMainPage = new CustomersMainPage();
		String addtionalColumnName = getData("additionalColumnName", generalData);
		String salesmanName = getData("fiterSalesman", generalData);
		header.NavigateTo(header.customersTab);
		customersMainPage.NavigateTo(customersMainPage.salesReport);
		salesReportPage.selectTodaysDate(salesReportPage.selectToday);
		salesReportPage.selectAdditionalColumns(addtionalColumnName);
		salesReportPage.selectSalesmanFilter(salesmanName);
		salesReportPage.ClickRefreshButton();

	}

	@And("I validate subscription flag column")
	public void validateSubscriptionFlagColumn() throws Exception {
		salesReportPage = new SalesReportPage();
		assertException = new AssertException();
		String currentSubscriptionFlagName = salesReportPage.getCurrentSubscriptionFlagName(getData("serviceDescription", generalData));
		String expectedSubscriptionFlagName = getData("subscriptionFlagName", generalData);
		salesReportPage.subscriptionFlagColumnPresent();
		assertException.result(expectedSubscriptionFlagName, currentSubscriptionFlagName, "validate flags", "validate salesReport");
	}

	@Then("I validate sales report totals")
	public void validateSalesReportTotals() throws Exception {
		addNewSubscription = new AddSubscription();
		salesReportPage = new SalesReportPage();
		assertException = new AssertException();
		double currentReportTotalContractValue = salesReportPage.getSalesReportTotalContractValue();
		String currentReportTotalContractValueConverted = Double.toString(currentReportTotalContractValue);
		double expectedReportTotalContractValue = salesReportPage
				.getSalesReportTotalSingleContractValue(getData("serviceDescription", generalData));
		String expectedReportTotalContractValueConverted = Double.toString(expectedReportTotalContractValue);
		assertException.result(addNewSubscription.newContractValue, currentReportTotalContractValueConverted, "validate contractValue", "validate salesReport" );
		assertException.result(expectedReportTotalContractValueConverted, currentReportTotalContractValueConverted, "validate totalContractValue", "validate salesReport" );
	}

}
