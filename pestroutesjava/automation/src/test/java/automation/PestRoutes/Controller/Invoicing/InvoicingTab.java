package automation.PestRoutes.Controller.Invoicing;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Utilities;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.Invoicing.Invoice_Header;
import automation.PestRoutes.PageObject.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;

public class InvoicingTab extends BaseClass {

	InvoiceImplementation invImplementation  = new InvoiceImplementation();;
	CreateNewCustomer createCustomer;
	RoutePageInvoicing invoiceRoutesTab = new RoutePageInvoicing();;
	CustomerViewDialog_Header routesTab;
	Invoice_Header invoiceHeader;

	private String treatmentAmount = "900";
	private Integer partialPaymentAmount = Integer.parseInt(treatmentAmount) / 2;
	private String successfulPartialCharge = "Successfully Charged Cash!$450.00";
	private String successfulFullCharge = "Successfully Charged Cash!$5,450.00";

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
		routesTab = new CustomerViewDialog_Header();
		routesTab.NavigateTo(routesTab.invoicesTabInDialog);
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

}
