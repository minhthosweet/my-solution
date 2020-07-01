package automation.PestRoutes.Controller.Subscriptions;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDIalog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;

public class AddSubscription extends BaseClass {

	CustomerViewDialog_SubscriptionTab subscription = new CustomerViewDialog_SubscriptionTab();
	CustomerViewDialog_Header customerDialogHeader;
	CreateCustomerDIalog createCustomer;
	Header header;
	ExtentTest test;
	List list = new ArrayList<String>();

	private String ticketItem = "bed";
	private String initialQuote = "120.00";
	private String initialDiscount = "20.00";

	@Test(groups = "Smoke")
	public void validateSubscription() throws Exception {

		startSubscription();
		validatePreferredDayAppt();
		validateInitialInvoice();
		validateRecurringInvoice();
		validateBillingFrequencyByMonthly();
		validateBillingFrequencyByAnnually();
		AssertException.asserFailure(list);

	}

	@When("I start a regular subscription")
	public void startSubscription() throws Exception {
		customerDialogHeader = new CustomerViewDialog_Header();
		header = new Header();
		header.Search_A_Customer(getData("customerName", generalData));
		customerDialogHeader.NavigateTo(customerDialogHeader.subscriptionTabInDialog);
		subscription.clickNewSubscriptionButton();
		subscription.selectServiceType(getData("quarterly", quarterlyPreferredDayData));
		subscription.setCustomDate(getData("customDate", quarterlyPreferredDayData));
	}
	
	public double startSubscriptionWithSalesRep(String needSalesRep, String needSubscriptionFlag) throws Exception {
		customerDialogHeader = new CustomerViewDialog_Header();
		header = new Header();
		customerDialogHeader.NavigateTo(customerDialogHeader.subscriptionTabInDialog);
		subscription.clickNewSubscriptionButton();
		subscription.selectServiceType(getData("quarterly", quarterlyPreferredDayData));
		subscription.setCustomDate(getData("customDate", quarterlyPreferredDayData));
		subscription.selectSalesRep(needSalesRep);
		subscription.selectSubscriptionFlag(needSubscriptionFlag);
		customerDialogHeader.ClickSaveButton();
		double finalContractValue = subscription.getContractValue(getData("quarterly", quarterlyPreferredDayData));
		return finalContractValue;
	}

	@Then("I validate upcoming appointments per each day")
	public void validatePreferredDayAppt() throws Exception {

		String[] prefferedDay = { getData("monday", quarterlyPreferredDayData),
				getData("tuesday", quarterlyPreferredDayData), getData("wednesday", quarterlyPreferredDayData),
				getData("thursday", quarterlyPreferredDayData), getData("friday", quarterlyPreferredDayData),
				getData("saturday", quarterlyPreferredDayData), getData("sunday", quarterlyPreferredDayData) };

		for (int i = 0; i < prefferedDay.length; i++) {
			System.out.println(i);
			subscription.selectPreferredDayOption(prefferedDay[i]);
			String[] actualUpComingDates = { subscription.getUpcomingAppt(subscription.firstUpcomingAppointment),
					subscription.getUpcomingAppt(subscription.secondUpcomingAppointment),
					subscription.getUpcomingAppt(subscription.thirdUpcomingAppointment),
					subscription.getUpcomingAppt(subscription.fourthUpcomingAppointment),
					subscription.getUpcomingAppt(subscription.fifthUpcomingAppointment),
					subscription.getUpcomingAppt(subscription.sixthUpcomingAppointment),
					subscription.getUpcomingAppt(subscription.seventhUpcomingAppointment) };
			String[] expectedUpComingDates = { getData("customDate", quarterlyPreferredDayData),
					getData("first" + prefferedDay[i], quarterlyPreferredDayData),
					getData("second" + prefferedDay[i], quarterlyPreferredDayData),
					getData("third" + prefferedDay[i], quarterlyPreferredDayData),
					getData("fourth" + prefferedDay[i], quarterlyPreferredDayData),
					getData("fifth" + prefferedDay[i], quarterlyPreferredDayData),
					getData("sixth" + prefferedDay[i], quarterlyPreferredDayData) };
			String[] daySlot = { " first slot", " 2nd slot", " 3rd slot", " 4th slot", " 5th slot", "6th slot",
					" 7th slot" };

			for (int j = 0; j < daySlot.length; j++) {
				System.out.println(actualUpComingDates[j]);
				System.out.println(expectedUpComingDates[j]);
				result(expectedUpComingDates[j], actualUpComingDates[j], prefferedDay[i] + daySlot[j], "Subscription");

			}
		}

	}

	@Then("I validate initial invoice")
	public void validateInitialInvoice() {
		subscription.setInitialServiceQuote(initialQuote);
		subscription.setInitialServiceDiscount(initialDiscount);
		subscription.selectAdditionalItem_ToInitialInvoice(ticketItem);
		double finalInitialQuote = Double.parseDouble(initialQuote);
		double finalInitialDiscount = Double.parseDouble(initialDiscount);
		double ticketAmount = subscription.getInitialService_NewTicketItemPrice(ticketItem);
		double actualInitialSubtotal = subscription.getInitialSubTotal();
		double initialTax = subscription.getInitialTax();
		double initialTotal = subscription.getInitialTotal();

		double subTotal = finalInitialQuote + ticketAmount - finalInitialDiscount;
		String expectedSubTotal = Double.toString(subTotal);
		String actual_InitialSubTotal = Double.toString(actualInitialSubtotal);
		Reporter.status("Initial invoice sub total validation ", expectedSubTotal, actual_InitialSubTotal,
				"Subscription");
		double total = subTotal + initialTax;
		String expectedInitialTotal = Double.toString(total);
		String actualInitialTotal = Double.toString(initialTotal);
		result(expectedInitialTotal, actualInitialTotal, "Initial invoice total validation ", "Subscription");
	}

	@Then("I validate recurring invoice")
	public void validateRecurringInvoice() throws Exception {
		subscription.setServiceQuote(getData("quarterly", quarterlyPreferredDayData), initialQuote);
		subscription.selectAdditionalItem_ToRecurringInvoice(ticketItem);
		double serviceAmount = Double.parseDouble(initialQuote);
		double ticketAmount = subscription.getRecurringService_NewTicketItemPrice(ticketItem);
		double actualServiceSubtotal = subscription.getRecurringSubTotal();
		double serviceTax = subscription.getRecurringTax();
		double serviceTotal = subscription.getRecurringTotal();

		double subTotal = serviceAmount + ticketAmount;
		String expectedSubTotal = Double.toString(subTotal);
		String actual_ServiceSubTotal = Double.toString(actualServiceSubtotal);
		Reporter.status("Service invoice sub total validation ", expectedSubTotal, actual_ServiceSubTotal,
				"Subscription");
		double total = subTotal + serviceTax;
		String expectedServiceTotal = Double.toString(total);
		String actualServiceTotal = Double.toString(serviceTotal);
		result(expectedServiceTotal, actualServiceTotal, "Service invoice total validation ", "Subscription");
	}

	@Then("I validate billing frequency by month")
	public void validateBillingFrequencyByMonthly() throws Exception {
		String monthlyFrequecyServiceQuote = "60";
		String monthlyFrequencyItemAmount = "1.00";
		insertServiceQuoteByBillingFrequency("Monthly", monthlyFrequecyServiceQuote, monthlyFrequencyItemAmount);
		double monthlyFrequencyQuote = Double.parseDouble(monthlyFrequecyServiceQuote);
		double monthlyFrequencyItemQuote = Double.parseDouble(monthlyFrequencyItemAmount);
		String expectedCustomProduction = Double.toString((monthlyFrequencyQuote + monthlyFrequencyItemQuote) * 2);
		String actualCustomProduction = subscription.getCustomProductionValue();
		result(expectedCustomProduction, actualCustomProduction, "Monthly Custom production ", "Subscription");
	}

	@Then("I validate billing frequency by annually")
	public void validateBillingFrequencyByAnnually() throws Exception {
		String annuallyFrequecyServiceQuote = "720";
		String annuallyFrequencyItemAmount = "12";
		insertServiceQuoteByBillingFrequency("Annually", annuallyFrequecyServiceQuote, annuallyFrequencyItemAmount);
		double annuallyFrequencyQuote = Double.parseDouble(annuallyFrequecyServiceQuote);
		double annuallyFrequencyItemQuote = Double.parseDouble(annuallyFrequencyItemAmount);
		String expectedCustomProduction = Double
				.toString((annuallyFrequencyQuote / 6) + (annuallyFrequencyItemQuote / 6));
		String actualCustomProduction = subscription.getCustomProductionValue();
		result(expectedCustomProduction, actualCustomProduction, "Annually custom production ", "Subscription");
	}

	private void insertServiceQuoteByBillingFrequency(String needFrequency, String needServiceQuote,
			String needItemAmount) throws Exception {
		subscription.selectBillingFrequency(needFrequency);
		subscription.setServiceQuote(getData("quarterly", quarterlyPreferredDayData), needServiceQuote);
		subscription.setAdditionalItemAmount(ticketItem, needItemAmount);
		Utilities.clickElement(subscription.recurringSubTotalValue, ElementType.XPath);
	}

	@SuppressWarnings("unchecked")
	private void result(String expected, String actual, String stepName, String testName) {
		if (AssertException.result(expected, actual, stepName).size() > 0) {
			list.add(AssertException.result(expected, actual, stepName));
		}
		Reporter.status(stepName, expected, actual, testName);
	}

}
