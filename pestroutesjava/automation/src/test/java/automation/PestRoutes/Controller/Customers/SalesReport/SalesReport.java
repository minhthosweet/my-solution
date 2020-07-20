package automation.PestRoutes.Controller.Customers.SalesReport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Utilities;

public class SalesReport extends BaseClass {

	Header header;
	AddUser addUser;
	AdminMainPage adminPage;
	CreateNewCustomer createNewCustomer;
	AddSubscription addNewSubscription;
	SalesReportPage salesReportPage;
	CustomersMainPage customersMainPage;
	ScheduleAppt scheduleAppt;
	CustomerViewDialog_OverviewTab overview;

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
		double customerContractValue = addNewSubscription.startSubscriptionWithSalesRep(salesmanName,subscriptionFlagName);
		scheduleAppt.createScheduleWithCustomerName(customerNameInHeader);
		runSoldSubcriptionsReport();
		validateSubscriptionFlagColumn();
		validateSalesReportTotals(customerContractValue);
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
	public void validateSubscriptionFlagColumn() throws IOException {
		salesReportPage = new SalesReportPage();
		String currentSubscriptionFlagName = salesReportPage.getCurrentSubscriptionFlagName(getData("serviceDescription", generalData));
		String expectedSubscriptionFlagName = getData("subscriptionFlagName", generalData);
		salesReportPage.subscriptionFlagColumnPresent();
		if (expectedSubscriptionFlagName == currentSubscriptionFlagName) {
			Assert.assertTrue(true);
		}
	}

	@Then("I validate sales report totals")
	public void validateSalesReportTotals(double customerContractValue) throws IOException {
		salesReportPage = new SalesReportPage();
		double currentReportTotalContractValue = salesReportPage.getSalesReportTotalContractValue();
		double expectedReportTotalContractValue = salesReportPage
				.getSalesReportTotalSingleContractValue(getData("serviceDescription", generalData));
		if (customerContractValue == currentReportTotalContractValue) {
			if (expectedReportTotalContractValue == currentReportTotalContractValue) {
				Assert.assertTrue(true);
			}

		}

	}

}
