package automation.PestRoutes.Controller.Subscriptions;

import automation.PestRoutes.PageObject.CustomerOverview.BillingPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.Utilities.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static automation.PestRoutes.Utilities.AssertException.result;

public class AddSubscription extends AppData {

	CustomerViewDialog_SubscriptionTab subscription = new CustomerViewDialog_SubscriptionTab();
	CustomerViewDialog_Header customerDialogHeader;
	Header header;

	public String ticketItem = "bed";
	public String initialQuote = "120.00";
	public Double recurringQuote = 240.00;
	public String initialDiscount = "20.00";
	private String customDateInCustomSchedule = Utilities.getCurrentDate();
	public static String newContractValue = null;
	public String initialInvoiceValue;

	@Test(groups = "Smoke")
	public void validateSubscription() throws Exception {

		startSubscription();
		validatePreferredDayAppt();
		validateInitialInvoice();
		validateRecurringInvoice();
		validateBillingFrequencyByMonthly();
		validateBillingFrequencyByAnnually();

	}

	@When("I start a regular subscription")
	public void startSubscription() throws Exception {
		customerDialogHeader = new CustomerViewDialog_Header();
		header = new Header();
		customerDialogHeader.navigateTo(customerDialogHeader.subscriptionTabInDialog);
		subscription.clickNewSubscriptionButton();
		subscription.selectServiceType(getData("quarterly", quarterlyPreferredDayData));
		subscription.selectServiceFrequency("Alternate Monthly");
		subscription.setCustomDate(getData("customDate", quarterlyPreferredDayData));
		subscription.clickButton(subscription.standardProductionButton);
	}

	@And("I create a subscription with Sales Rep assigned {string} and {string}")
	public void startSubscriptionWithSalesRep(String needSalesmanName, String needSubscriptionFlagName) throws Exception {
		customerDialogHeader = new CustomerViewDialog_Header();
		header = new Header();
		customerDialogHeader.navigateTo(customerDialogHeader.subscriptionTabInDialog);
		subscription.clickNewSubscriptionButton();
		subscription.selectServiceType(getData("serviceDescription", generalData));
		subscription.setCustomDate(getData("customDate", quarterlyPreferredDayData));
		subscription.selectSalesRep(needSalesmanName);
		subscription.selectSalesRep2(needSalesmanName);
		subscription.selectSalesRep3(needSalesmanName);
		subscription.selectSubscriptionFlag(needSubscriptionFlagName);
		customerDialogHeader.clickSaveButton();
		newContractValue = getContractValue();
	}

	@And("I create a dynamic subscription with Sales Rep assigned {string} and {string} and {string}")
	public void startSubscriptionWithSalesRep(String needSalesmanName, String needSubscriptionFlagName, String needPreferredDay) throws Exception {
		String preferredTech = getData("userFirstName", generalData) + " " + getData("userLastName", generalData);
		customerDialogHeader = new CustomerViewDialog_Header();
		header = new Header();
		customerDialogHeader.navigateTo(customerDialogHeader.subscriptionTabInDialog);
		subscription.clickNewSubscriptionButton();
		subscription.selectServiceType(getData("serviceDescription", generalData));
		subscription.selectSalesRep(needSalesmanName);
		subscription.selectSubscriptionFlag(needSubscriptionFlagName);
		subscription.selectPreferTechOption(preferredTech);
		subscription.selectPreferredDayOption(needPreferredDay);
		subscription.selectRoutineRegionOption(getData("region", generalData));
		customerDialogHeader.clickSaveButton();
	}

	@And("I create a subscription of type {string}")
	public void createSubscription(String initialInvoiceType) throws Exception {
		customerDialogHeader = new CustomerViewDialog_Header();
		header = new Header();
		customerDialogHeader.navigateTo(customerDialogHeader.subscriptionTabInDialog);
		subscription.clickNewSubscriptionButton();
		subscription.selectServiceType(getData("serviceDescription", generalData));
		subscription.setCustomDate(getData("customDate", quarterlyPreferredDayData));
		subscription.setInitialInvoiceType(initialInvoiceType);
		subscription.setInitialServiceQuote(initialQuote);
		subscription.setInitialServiceDiscount(initialDiscount);
		subscription.selectAdditionalItem_ToInitialInvoice(ticketItem);
		customerDialogHeader.clickSaveButton();
		initialInvoiceValue = subscription.getInitialInvoiceValue();
	}

	public String getContractValue() throws Exception {
		double contractValue = subscription.getContractValue(getData("serviceDescription", generalData));
		String contractValueConverted = Double.toString(contractValue);
		return contractValueConverted;
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

	@Then("I validate initial invoice template values")
	public void validateInitialInvoice() throws InterruptedException {
		subscription.setInitialServiceQuote(initialQuote);
		subscription.setInitialServiceDiscount(initialDiscount);
		//subscription.selectAdditionalItem_ToInitialInvoice(ticketItem);
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

	@Then("I validate recurring invoice template values")
	public void validateRecurringInvoice() throws Exception {
		subscription.setServiceQuote(getData("serviceDescription", generalData), Double.toString(recurringQuote));
		subscription.selectAdditionalItem_ToRecurringInvoice(ticketItem);
		double ticketAmount = subscription.getRecurringService_NewTicketItemPrice(ticketItem);
		double actualServiceSubtotal = subscription.getRecurringSubTotal();
		double serviceTax = subscription.getRecurringTax();
		double serviceTotal = subscription.getRecurringTotal();
		double subTotal = recurringQuote + ticketAmount;
		String expectedSubTotal = Double.toString(subTotal);
		String actual_ServiceSubTotal = Double.toString(actualServiceSubtotal);
		Reporter.status("Service invoice sub total validation ", expectedSubTotal, actual_ServiceSubTotal,
				"Subscription");
		double total = subTotal + serviceTax;
		String expectedServiceTotal = Double.toString(total);
		String actualServiceTotal = Double.toString(serviceTotal);
		result(expectedServiceTotal, actualServiceTotal, "Service invoice total validation ", "Subscription");
		customerDialogHeader.clickSaveButton();
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

	public void insertServiceQuoteByBillingFrequency(String needFrequency, String needServiceQuote, String needItemAmount) throws Exception {
		subscription.selectBillingFrequency(needFrequency);
		subscription.setServiceQuote(getData("quarterly", quarterlyPreferredDayData), needServiceQuote);
		subscription.setAdditionalItemAmount(ticketItem, needItemAmount);
		subscription.clickRecurringSubTotalValue();
	}

	@Then("I validate initial Billing date")
	public void validateInitialBillingDate(){
		result(subscription.getCurrentDate(),subscription.getBilling_initialBillingDate(),"Initial Billing Date","Subscription");
	}

	@And("I create a custom schedule subscription of type {string}")
	public void createCustomScheduleSubscription(String initialInvoiceType) throws Exception {
		customerDialogHeader = new CustomerViewDialog_Header();
		header = new Header();
		customerDialogHeader.navigateTo(customerDialogHeader.subscriptionTabInDialog);
		subscription.clickNewSubscriptionButton();
		subscription.selectServiceType(getData("serviceDescription", generalData));
		subscription.setCustomDate(getData("customDate", quarterlyPreferredDayData));
		subscription.setInitialInvoiceType(initialInvoiceType);
		customerDialogHeader.clickSaveButton();
		subscription.customInitialBilling_alertCondition();
	}

	@And("I set today as custom billing date")
	public void setCurrentDateAsCustomBillingDate_InitialAppointment() throws InterruptedException {
		customerDialogHeader = new CustomerViewDialog_Header();
		subscription.clickEditCustomInitialScheduleButton();
		subscription.clickSpecificDateButton_initialCustomSchedule();
		subscription.selectCurrentDate_CustomSchedule();
		subscription.setAmount_CustomSchedule();
		subscription.clickFinishEditingSchedule();
		customerDialogHeader.clickSaveButton();
	}

	@And("I calculate the Initial Invoice amount for the Custom Schedule")
	public void calculateInitialInvoiceTotal_customSchedule() throws InterruptedException {
		subscription.getInitialInvoiceTotalAmount_CustomerSchedule();
	}

	@And("I run the billing queue script")
	public void runBillingQueueScript() throws Exception{
		Thread.sleep(700);
		Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/billingQueue.php?debug=1");
	}

	@And("I add a custom frequency recurring service")
	public void customFrequencyRecurringService() throws InterruptedException {
		subscription.clearCustomDate();
		subscription.selectServiceFrequency("Custom Schedule");
		subscription.clickEditCustomRecurringScheduleButton();
		subscription.clickSpecificDateButton_recurringCustomSchedule();
		subscription.selectCurrentDateSpecificDate_recurringCustomSchedule(customDateInCustomSchedule);
		subscription.clickDayOfTheWeekButton_recurringCustomSchedule();
		subscription.selectDayOfTheWeek("Third", "Tuesday");
		subscription.clickFinishEditingSchedule();
		customerDialogHeader.clickSaveButton();
	}

	@Then("I validate upcoming appointments for custom recurring appointments")
	public void validateCustomSchedule_RecurringAppt() throws Exception {

		result(subscription.getUpcomingAppt(subscription.firstUpcomingAppointment).replaceAll("0",""), subscription.getUpcomingAppointment_specificDate(customDateInCustomSchedule, 0).replaceAll("0",""), "first appointment", "Subscription");
		result(subscription.getUpcomingAppt(subscription.thirdUpcomingAppointment).replaceAll("0",""), subscription.getUpcomingAppointment_specificDate(customDateInCustomSchedule, 1).replaceAll("0",""), "third appointment", "Subscription");
		result(subscription.getUpcomingAppt(subscription.fifthUpcomingAppointment).replaceAll("0",""), subscription.getUpcomingAppointment_specificDate(customDateInCustomSchedule, 2).replaceAll("0",""), "fifth appointment", "Subscription");
		result(subscription.getUpcomingAppt(subscription.seventhUpcomingAppointment).replaceAll("0",""), subscription.getUpcomingAppointment_specificDate(customDateInCustomSchedule, 3).replaceAll("0",""), "fifth appointment", "Subscription");

		result(subscription.getUpcomingAppt(subscription.secondUpcomingAppointment).replaceAll("0",""), subscription.getUpcomingAppointment_specificDay_everyYear(1).replaceAll("0",""), "second appointment", "Subscription");
		result(subscription.getUpcomingAppt(subscription.fourthUpcomingAppointment).replaceAll("0",""), subscription.getUpcomingAppointment_specificDay_everyYear(2).replaceAll("0",""), "fourth appointment", "Subscription");
		result(subscription.getUpcomingAppt(subscription.sixthUpcomingAppointment).replaceAll("0",""), subscription.getUpcomingAppointment_specificDay_everyYear(3).replaceAll("0",""), "sixth appointment", "Subscription");
		result(subscription.getUpcomingAppt(subscription.eighthUpcomingAppointment).replaceAll("0",""), subscription.getUpcomingAppointment_specificDay_everyYear(4).replaceAll("0",""), "eighth appointment", "Subscription");
	}

	@When("I add auto pay with cc or ach in subscription billing options and merge customer accounts {string}")
	public void addCustomerOnAutoPay(String needMergeCustomerName) throws InterruptedException {
		customerDialogHeader = new CustomerViewDialog_Header();
		customerDialogHeader.navigateTo(customerDialogHeader.subscriptionTabInDialog);
		subscription.setAutoPayProfileDropdown();
		customerDialogHeader.clickSaveButton();
		Utilities.clickElement(subscription.billingAccountField, Utilities.ElementType.XPath);
		try {
			WebElement elm = FindElement.elementByAttribute(subscription.proceedAndTransferButton, FindElement.InputType.XPath);
			if (elm.isDisplayed()){
				Utilities.clickElement(subscription.proceedAndTransferButton, Utilities.ElementType.XPath);
			}
		} catch(Exception e) {
			System.out.println("Transfer Funds dialog not displayed");
		}
		subscription.setMergeBillingAccountField(needMergeCustomerName);
		Utilities.clickElement(subscription.mergeBillingAccountSelectButton, Utilities.ElementType.XPath);
		customerDialogHeader.clickSaveButton();
	}
}
