package automation.PestRoutes.Controller.Invoicing;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Subscriptions.AddSubscription;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.Invoicing.Invoice_Header;
import automation.PestRoutes.PageObject.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.Utilities.Reporter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InvoicingTab extends AppData {

	InvoiceImplementation invImplementation  = new InvoiceImplementation();
	CreateNewCustomer createCustomer;
	RoutePageInvoicing invoiceRoutesTab = new RoutePageInvoicing();
	CustomerViewDialog_Header header;
	Invoice_Header invoiceHeader;
	AddSubscription addSubscription;
	CustomerViewDialog_SubscriptionTab subscriptionTab;

	private String treatmentAmount = "900";
	private Integer partialPaymentAmount = Integer.parseInt(treatmentAmount) / 2;
	private String successfulPartialCharge = "Successfully Charged Cash!$450.00";
	private String successfulFullCharge = "Successfully Charged Cash!$5,450.00";
	List list = new ArrayList<String>();
	private String invoiceDate = "1";

	@Test
	public void CustomerInvoicing() throws Exception {

		// Implementation
		addNewInvoice(invoiceDate);
		InitialCost();
		assertInitialCharge();
		reducingBalance();
		assertPartialCharge(partialPaymentAmount, partialPaymentAmount);
		reducingBalance();
		assertFullCharge();
	}

	// Add a new invoice to the customer
	public void addNewInvoice(String date) throws Exception {
		createCustomer = new CreateNewCustomer();
		createCustomer.createCustomerWithEmail();
		createCustomer.searchCustomer();
		header = new CustomerViewDialog_Header();
		header.navigateTo(header.invoicesTabInDialog);
		invoiceRoutesTab.clickAddPayment();
		invoiceRoutesTab.clickAddNewInvoice(invoiceRoutesTab.addNewInvoice);
		invImplementation.newInvoiceDetails(treatmentAmount,date);
		invoiceRoutesTab.invoiceDetails();
		invoiceRoutesTab.selectAvailableItems();

	}

	// Calculates initial cost
	private Integer InitialCost() {
		return invImplementation.getInvoiceCost();
	}

	// Keeps checking balance
	private void reducingBalance() {
		invoiceHeader = new Invoice_Header();
		invoiceRoutesTab.clickAddPartialPayments();
		invoiceHeader.navigate(invoiceHeader.cash);
	}

	// Initial Assert - UNPAID
	private void assertInitialCharge() {
		Reporter.status(" Treatment Amount",Integer.toString(InitialCost() + Integer.parseInt(treatmentAmount)),
				Integer.toString(invImplementation.getTotalValueCharges()), "Invoicing");
		Reporter.status("",Integer.toString(InitialCost() + Integer.parseInt(treatmentAmount)),
				Integer.toString(invImplementation.getTotalValuePayments()), "Values match");
		Reporter.status("","UNPAID", invImplementation.checkPaymentStatus(), "The payment status is UNPAID");
	}

	// Assert - Partial payment
	private void assertPartialCharge(Integer pAmount, Integer cAmount) {
		invImplementation.insertPaymentAmount(Integer.toString(pAmount), Integer.toString(cAmount));
		invImplementation.clickrecordPayment();
		Reporter.status("",successfulPartialCharge, invImplementation.getSuccessfulChargeAmount(), "Charged successfully");
		Reporter.status("","PARTIALLY PAID", invImplementation.checkPaymentStatus(),
				"The payment status is PARTIALLY PAID");
		invImplementation.InvoiceAccountSummaryClick();
	}

	// Assert - Full Payment
	private void assertFullCharge() {
		invImplementation.insertPaymentAmount(Integer.toString(invImplementation.getPaymentBalance()),
				Integer.toString(invImplementation.getPaymentBalance()));
		invImplementation.clickrecordPayment();
		Reporter.status("",successfulFullCharge, invImplementation.getSuccessfulChargeAmount(), "Charged successfully");
		Reporter.status("","FULLY PAID", invImplementation.checkPaymentStatus(), "The payment status is FULLY PAID");
		invImplementation.InvoiceAccountSummaryClick();
	}

	@And("I validate invoice")
	public void validateInvoice() throws IOException, InterruptedException {
		addSubscription = new AddSubscription();
		header = new CustomerViewDialog_Header();
		subscriptionTab = new CustomerViewDialog_SubscriptionTab();;
		header.navigateTo(header.subscriptionTabInDialog);
		String initialInvoiceValue = subscriptionTab.getInitialInvoiceValue();
		header.navigateTo(header.invoicesTabInDialog);
		result(initialInvoiceValue, "$" + invImplementation.getAccountBalance(), "Total Initial Invoice Value",
				"Invoice Validation");
		invImplementation.clickInvoice(getData("serviceDescription", generalData));
		result(initialInvoiceValue,"$" +  invImplementation.getChargesBalance(), "Total Initial Invoice Value",
				"Invoice Validation");
		result(initialInvoiceValue, "$" + invImplementation.getPaymentsBalance(), "Total Initial Invoice Value",
				"Invoice Validation");
	}

	@And("I validate initial invoice created on invoice tab")
	public void validateInitialInvoice() throws InterruptedException {
		addSubscription = new AddSubscription();
		header = new CustomerViewDialog_Header();
		subscriptionTab = new CustomerViewDialog_SubscriptionTab();;
		header.navigateTo(header.subscriptionTabInDialog);
		String initialInvoiceValue = subscriptionTab.getInitialInvoiceValue();
		String initialSubTotal = Double.toString(subscriptionTab.getInitialSubTotal());
		String taxAmount = Double.toString(subscriptionTab.getInitialTax());

		header.navigateTo(header.invoicesTabInDialog);
		result(initialInvoiceValue, invImplementation.getAccountBalance(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		invImplementation.clickInitialInvoice();

		result(addSubscription.initialQuote,invImplementation.getServiceCostBeforeTax(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		result(Double.toString(subscriptionTab.getInitialService_NewTicketItemPrice(addSubscription.ticketItem)),invImplementation.getAddOnValue(addSubscription.ticketItem), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		result("$" + initialSubTotal,invImplementation.getSubTotalValue(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		result("$" + taxAmount,invImplementation.getTaxValue(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		result("-" + addSubscription.initialDiscount,invImplementation.getInitialDiscountValue(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		result(initialInvoiceValue,invImplementation.getChargesBalance(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		result(initialInvoiceValue, invImplementation.getPaymentsBalance(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		result(Utilities.currentDateWithZeroDelimiterOnDate("MM/dd/yy"), invImplementation.getInvoiceDate(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		result(Utilities.currentDateWithZeroDelimiterOnDate("MM/dd/yy"), invImplementation.getDueDate(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		result(Utilities.currentDateWithZeroDelimiterOnDate("MM/dd/yy"), invImplementation.getAppointmentDate(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		}

	@And("I validate recurring invoice created on invoice tab")
	public void validateRecurringInvoice() throws InterruptedException {
		addSubscription = new AddSubscription();
		header = new CustomerViewDialog_Header();
		subscriptionTab = new CustomerViewDialog_SubscriptionTab();;
		header.navigateTo(header.subscriptionTabInDialog);
		String initialInvoiceValue = subscriptionTab.getInitialInvoiceValue().substring(1,subscriptionTab.getInitialInvoiceValue().length());
		String recurringInvoiceValue = subscriptionTab.getRecurringInvoiceValue().substring(1,subscriptionTab.getRecurringInvoiceValue().length());
		String accountPendingBalance = Double.toString(Double.parseDouble(recurringInvoiceValue) + Double.parseDouble(initialInvoiceValue));
		String recurringSubTotal = Double.toString(subscriptionTab.getRecurringSubTotal());
		String taxAmount = Double.toString(subscriptionTab.getRecurringTax());
		header.navigateTo(header.invoicesTabInDialog);
		result("$" + accountPendingBalance, invImplementation.getAccountBalance(), "Total Invoice Value",
				"Invoice Validation");
		invImplementation.clickRecurringInvoice(recurringInvoiceValue);

		result(addSubscription.recurringQuote,invImplementation.getServiceCostBeforeTax(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		result(Double.toString(subscriptionTab.getInitialService_NewTicketItemPrice(addSubscription.ticketItem)),invImplementation.getAddOnValue(addSubscription.ticketItem), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		result("$" + recurringSubTotal,invImplementation.getSubTotalValue(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		result("$" + taxAmount,invImplementation.getTaxValue(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		result("$" + recurringInvoiceValue, invImplementation.getChargesBalance(), "Total Initial Invoice Value",
				"Invoice Validation");
		result("$" + recurringInvoiceValue, invImplementation.getPaymentsBalance(), "Total Initial Invoice Value",
				"Invoice Validation");
		result(Utilities.currentDateWithZeroDelimiterOnDate("MM/dd/yy"), invImplementation.getInvoiceDate(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		result(Utilities.currentDateWithZeroDelimiterOnDate("MM/dd/yy"), invImplementation.getDueDate(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		result(Utilities.currentDateWithZeroDelimiterOnDate("MM/dd/yy"), invImplementation.getAppointmentDate(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
	}

	@And("I validate initial invoice created on invoice tab from custom schedule")
	public void validateInitialInvoice_customSchedule() throws InterruptedException {
		addSubscription = new AddSubscription();
		header = new CustomerViewDialog_Header();
		subscriptionTab = new CustomerViewDialog_SubscriptionTab();;
		header.navigateTo(header.subscriptionTabInDialog);
		String iniitialIvoiceValueWithoutTax = subscriptionTab.getInitialInvoiceAmountWithoutTax_CustomSchedule();
		String initialInvoiceValue = "$"+subscriptionTab.getInitialInvoiceTotalAmount_CustomerSchedule();
		header.navigateTo(header.invoicesTabInDialog);
		result(initialInvoiceValue,invImplementation.getAccountBalance(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		invImplementation.clickInitialInvoice();
		result(initialInvoiceValue,invImplementation.getChargesBalance(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
		result(initialInvoiceValue, invImplementation.getPaymentsBalance(), "Total Initial Invoice Value",
				"Initial Invoice Validation");
	}

	private void result(String expected, String actual, String stepName, String testName) {
		if (AssertException.result(expected, actual, stepName).size() > 0) {
			list.add(AssertException.result(expected, actual, stepName));
		}
		Reporter.status(stepName, expected, actual, testName);
	}

	public void validateIfFailureExist() {
		AssertException.assertFailure(list);
	}
}
