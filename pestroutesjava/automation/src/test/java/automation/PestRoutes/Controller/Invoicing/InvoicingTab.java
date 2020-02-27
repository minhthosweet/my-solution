package automation.PestRoutes.Controller.Invoicing;

import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.Invoicing.Invoice_Header;
import automation.PestRoutes.PageObject.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;

public class InvoicingTab extends BaseClass {

	Header mainHeader;
	InvoiceImplementation invImplementation;

	RoutePageInvoicing invoiceRoutesTab;
	CustomerViewDialog_Header routesTab;
	Invoice_Header invoiceHeader;
	private String treatmentAmount = "900";
	private Integer partialPaymentAmount = Integer.parseInt(treatmentAmount) / 2;
	private String successfulPartialCharge = "Successfully Charged Cash!$450.00";
	private String successfulFullCharge = "Successfully Charged Cash!$5,450.00";

	@Test
	public void CustomerInvoicing() throws Exception {
		// Login
		mainHeader = new Header();

		// Object Creation
		invImplementation = new InvoiceImplementation();
		routesTab = new CustomerViewDialog_Header();
		invoiceRoutesTab = new RoutePageInvoicing();
		invoiceHeader = new Invoice_Header();

		// Methods
		searchCustomer();
		addNewInvoice();
		InitialCost();
		assertInitialCharge();
		reducingBalance();
		assertPartialCharge(partialPaymentAmount, partialPaymentAmount);
		reducingBalance();
		assertFullCharge();
	}

	// Searches for a customer
	private void searchCustomer() throws Exception {
		mainHeader.Search_A_Customer(getData("userID", generalData));
	}

	// Add a new invoice to the customer
	private void addNewInvoice() throws InterruptedException {
		routesTab.NavigateTo(routesTab.invoicesTabInDialog);
		invoiceRoutesTab.clickAddPayment();
		invoiceRoutesTab.clickAddNewInvoice(invoiceRoutesTab.addNewInvoice);
		invImplementation.newInvoiceDetails(treatmentAmount);
		invoiceRoutesTab.invoiceDetails();
		invoiceRoutesTab.selectAvailableItems();

	}

	// Calculates initial cost
	private Integer InitialCost() {
		return invImplementation.getInvoiceCost();
	}

	// Keeps checking balance
	private void reducingBalance() {
		invoiceRoutesTab.clickAddPartialPayments();
		invoiceHeader.navigate(invoiceHeader.cash);
	}

	// Initial Assert - UNPAID
	private void assertInitialCharge() {
		Reporter.status(Integer.toString(InitialCost() + Integer.parseInt(treatmentAmount)),
				Integer.toString(invImplementation.getTotalValueCharges()), "Values match");
		Reporter.status(Integer.toString(InitialCost() + Integer.parseInt(treatmentAmount)),
				Integer.toString(invImplementation.getTotalValuePayments()), "Values match");
		Reporter.status("UNPAID", invImplementation.checkPaymentStatus(), "The payment status is UNPAID");
	}

	// Assert - Partial payment
	private void assertPartialCharge(Integer pAmount, Integer cAmount) {
		invImplementation.insertPaymentAmount(Integer.toString(pAmount), Integer.toString(cAmount));
		invImplementation.clickrecordPayment();
		Reporter.status(successfulPartialCharge, invImplementation.getSuccessfulChargeAmount(), "Charged successfully");
		Reporter.status("PARTIALLY PAID", invImplementation.checkPaymentStatus(),
				"The payment status is PARTIALLY PAID");
		invImplementation.InvoiceAccountSummaryClick();
	}

	// Assert - Full Payment
	private void assertFullCharge() {
		invImplementation.insertPaymentAmount(Integer.toString(invImplementation.getPaymentBalance()),
				Integer.toString(invImplementation.getPaymentBalance()));
		invImplementation.clickrecordPayment();
		Reporter.status(successfulFullCharge, invImplementation.getSuccessfulChargeAmount(), "Charged successfully");
		Reporter.status("FULLY PAID", invImplementation.checkPaymentStatus(), "The payment status is FULLY PAID");
		invImplementation.InvoiceAccountSummaryClick();
	}

}
