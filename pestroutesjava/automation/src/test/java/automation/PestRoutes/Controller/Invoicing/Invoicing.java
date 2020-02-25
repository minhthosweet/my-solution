package automation.PestRoutes.Controller.Invoicing;

import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class Invoicing extends BaseClass {

	Header mainHeader;
	InvoiceImplementation invImplementation;

	private String AccountSummary = "Account Summary";
	private String lastLetter = "Last Letter";
	private String lastEmail = "Last Email";
	private String payments = "Payments";

	// Values in Cash
	private String cashpayment = "Cash Payment";
	private String paymentDetails = "Payment Details";
	private String paymentAmount = "Payment Amount";
	private String ConfirmAmount = "Confirm Amount";
	private String CustPayNotes = "Customer Payment Notes";
	private String distDetails = "Distribution Details";
	private String limToCust = "Limited To Customer: ";
	private String limToSubs = "Limited To Subscription: ";
	private String app2first = "Apply To First: ";
	private String PaymentFlags = "Payment Flags";
	private String OfficePayment = "Office Payment: ";
	private String CollectionPayment = "Collection Payment: ";
	private String CollectionAgencypay = "Collections Agency Payment: ";

	// Variables in payment confirmation
	private String PaymentResult = "Payment Result ";
	private String CashCharged = "Successfully Charged Cash!";

	// Variables in Account Summary
	private String paymentDate = "02/21/2020 @ 02:26pm CST ";

	// New Invoice
	private String newInvoice = "//li[contains(@class,'createNewInvoice(20316);') and contains(text(),'+ New Invoice')]";
	private String serviceSelect = "//form[@id=\"newInvoiceParams\"]/select";
	private String service = "//select[@id=\"newInvoiceParams\"]/select/option[21]";
	private String create = "//span[contains(@class,'ui-button-text') and contains(text(),'Create')]";
	private String Charges = "Charges";
	private String TicketInfo = "TicketInfo";
	private String InvoicePayments = "Payments";
	
	@Test
	public void CustomerInvoicing() throws Exception {
		// Login
		mainHeader = new Header();

		// Getter & Setter
		invImplementation = new InvoiceImplementation();

		searchCustomer();
		assertInvoices();
		paymentOptions();
		newInvoice();
		manageInvoices();

		System.out.println("Customer Invoicing TC passed");
	}

	private void searchCustomer() throws Exception {
		mainHeader.Search_A_Customer(getData("userID", generalData));
	}

	private void assertInvoices() {
		mainHeader.NavigateTo(mainHeader.invoices);
		Reporter.status(AccountSummary, invImplementation.getInvoiceAccountSummary(), "Title Matched");
		System.out.println("Title Matched");
		Reporter.status(lastLetter, invImplementation.getLastLetter(), "Last Letter passed");
		System.out.println("Last Letter TC passed");
		Reporter.status(lastEmail, invImplementation.getLastEmail(), "Last Email passed");
		System.out.println("Last Email TC passed");
		Reporter.status(payments, invImplementation.getPayments(), "Payments passed");
		System.out.println("Payments TC passed");

	}

	private void paymentOptions() throws InterruptedException {
		mainHeader.clickElementLanding();
		String amount = invImplementation.insertPaymentAmount("900.00");

		Reporter.status(cashpayment, invImplementation.getCashPayment(), "Cash Payment Visible");

		Reporter.status(paymentDetails, invImplementation.getPaymentDetails(), "Payment Details Visible");

		Reporter.status(paymentAmount, invImplementation.getPaymentAmount(), "Payment Amount Visible");

		Reporter.status(ConfirmAmount, invImplementation.getConfirmAmount(), "Confirm Amount Visible");

		Reporter.status(CustPayNotes, invImplementation.getCustPayNotes(), "Customer Pay Notes Visible");

		Reporter.status(distDetails, invImplementation.getDistDetails(), "Distribution Details Visible");

		Reporter.status(limToCust, invImplementation.getLimToCust(), "Limited to Customer Visible");

		Reporter.status(limToSubs, invImplementation.getLimToSubs(), "Limited to Subscription Visible");

		Reporter.status(app2first, invImplementation.getApp2first(), "Apply to First Visible");

		Reporter.status(PaymentFlags, invImplementation.getPaymentFlags(), "Paymemt Flags Visible");

		Reporter.status(OfficePayment, invImplementation.getOfficePayment(), "Office Paymemt Visible");

		Reporter.status(CollectionPayment, invImplementation.getCollectionPayment(), "Collection Payment Visible");

		Reporter.status(CollectionAgencypay, invImplementation.getCollectionAgencypay(),
				"Collection Agency Payment Visible");

		mainHeader.clickElementCash();
		Reporter.status("$" + amount, invImplementation.getAmountCharged(), "Amount Charge Visible");

		Reporter.status(PaymentResult, invImplementation.getPaymentResult(), "Payment Result Visisble");

		Reporter.status(CashCharged, invImplementation.getCashCharged(), "Cash Charged Visible");

		Reporter.status(mainHeader.currentDateandTime(), paymentDate, "Date and Matches");

	}

	public void newInvoice() {
		Utilities.clickElement(newInvoice, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(invImplementation.invoiceAmount("222"));
		Utilities.waitUntileElementIsVisible(serviceSelect);
		Utilities.clickElement(serviceSelect, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(service);
		Utilities.clickElement(service, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(create);
		Utilities.clickElement(create, ElementType.XPath);
	}
	
	public void manageInvoices() {
		Reporter.status(Charges, invImplementation.getCharges(), "Charges tab visible on the UI");
		Reporter.status(TicketInfo, invImplementation.getTicketInfo(), "Ticket Info TC passed");
		Reporter.status(InvoicePayments, invImplementation.getInvoicePayments(), "Payments visible on the UI");
	}

}
