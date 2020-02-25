package automation.PestRoutes.Controller.Invoicing;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class InvoiceImplementation {
	// Values on Invoicing Landing Page
	private String invoiceAccountSummary = "//*[@id=\"billingPanel\"]/div[2]/h3";
	private String lastLetter = "//*[@id=\"billingPanel\"]/div[2]/h3/div/div[1]";
	private String lastEmail = "//*[@id=\"billingPanel\"]/div[2]/h3/div/div[3]";
	private String Payments = "//*[@id=\"billingPanel\"]/div[2]/div[6]/div/h3";

	// Cash tab
	private String PayAmt = "//*[@id=\"singlePaymentForm\"]/div[4]/div[1]/input[1]";
	private String ConfAmt = "//*[@id=\"singlePaymentForm\"]/div[4]/div[1]/input[2]";
	private String custPayNotes = "//*[@id=\"singlePaymentForm\"]/div[4]/div[1]/textarea";
	private String cashPayment = "//*[@id=\"singlePaymentForm\"]/h3";
	private String paymentDetails = "//*[@id=\"singlePaymentForm\"]/div[4]/h3";
	private String paymentAmount = "//*[@id=\"singlePaymentForm\"]/div[4]/div[1]/div[1]";
	private String ConfirmAmount = "//div[text() = 'Payment Amount:']/following-sibling::div";
	private String CustPayNotes = "//div[text() = 'Confirm Amount:']/following-sibling::div";
	private String distDetails = "//*[@id=\"singlePaymentForm\"]/div[5]/h3[1]";
	private String limToCust = "//*[@id=\"singlePaymentForm\"]/div[5]/div[2]/div[4]";
	private String limToSubs = "//div[text() = 'Limited To Customer: ']/following-sibling::div";
	private String app2first = "//div[text() = 'Limited To Subscription: ']/following-sibling::div";
	private String PaymentFlags = "//form[@id=\"singlePaymentForm\"]/div[5]/h3[2]";
	private String OfficePayment = "//form[@id=\"singlePaymentForm\"]/div[5]/div[4]/div[1]";
	private String CollectionPayment = "//*[@id=\"singlePaymentForm\"]/div[5]/div[4]/div[3]";
	private String CollectionAgencypay = "//form[@id=\"singlePaymentForm\"]/div[5]/div[4]/div[4]";
	
	// Payment Result
	private String PaymentResult = "//form[@id=\"singlePaymentForm\"]/div/h3";
	private String CashCharged = "//form[@id=\"singlePaymentForm\"]/div/h2[1]";
	private String AmountCharged = "//form[@id=\"singlePaymentForm\"]/div/h2[2]";

	
	//Invoice Amount
	private String invoiceAmount = "//form[@id=\"newInvoiceParams\"]/input[2]";
	private String Charges = "//h3[contains(@class,'bluGradientBG') and contains(text(),'Charges')]";
	private String TicketInfo = "//h3[contains(@class,'bluGradientBG') and contains(text(),'Ticket Info')]";	
	private String InvoicePayments = "//h3[contains(@class,'bluGradientBG') and contains(text(),'Payments')]";
	private String ChargeAmount = "//*[@id=\"serviceTicket\"]/div[2]/input";
	
	// Getter Methods
	public String getPaymentFlags() {		
		Utilities.waitUntileElementIsVisible(PaymentFlags);
		return Utilities.getElementTextValue(PaymentFlags, ElementType.XPath);
	}



	public String getInvoicePayments() {
		Utilities.waitUntileElementIsVisible(InvoicePayments);
		return Utilities.getElementTextValue(InvoicePayments, ElementType.XPath);
	}
	

	public String getCharges() {
		Utilities.waitUntileElementIsVisible(Charges);
		return Utilities.getElementTextValue(Charges, ElementType.XPath);
	}
	

	public String getTicketInfo() {
		Utilities.waitUntileElementIsVisible(TicketInfo);
		return Utilities.getElementTextValue(TicketInfo, ElementType.XPath);
	}

	public String getOfficePayment() {
		Utilities.waitUntileElementIsVisible(OfficePayment);
		return Utilities.getElementTextValue(OfficePayment, ElementType.XPath);
	}

	public String getCollectionPayment() {
		Utilities.waitUntileElementIsVisible(CollectionPayment);
		return Utilities.getElementTextValue(CollectionPayment, ElementType.XPath);
	}

	public String getCollectionAgencypay() {
		Utilities.waitUntileElementIsVisible(CollectionAgencypay);
		return Utilities.getElementTextValue(CollectionAgencypay, ElementType.XPath);
	}
	
	public String getAmountCharged() {
		Utilities.waitUntileElementIsVisible(AmountCharged);
		return Utilities.getElementTextValue(AmountCharged, ElementType.XPath);
	}

	public String getPaymentResult() {
		Utilities.waitUntileElementIsVisible(PaymentResult);
		return Utilities.getElementTextValue(PaymentResult, ElementType.XPath);
	}

	public String getCashCharged() {
		Utilities.waitUntileElementIsVisible(CashCharged);
		return Utilities.getElementTextValue(CashCharged, ElementType.XPath);
	}

	public String getDistDetails() {
		Utilities.waitUntileElementIsVisible(distDetails);
		return Utilities.getElementTextValue(distDetails, ElementType.XPath);
	}

	public String getLimToCust() {
		Utilities.waitUntileElementIsVisible(limToCust);
		return Utilities.getElementTextValue(limToCust, ElementType.XPath);
	}

	public String getLimToSubs() {
		Utilities.waitUntileElementIsVisible(limToSubs);
		return Utilities.getElementTextValue(limToSubs, ElementType.XPath);

	}

	public String getApp2first() {
		Utilities.waitUntileElementIsVisible(app2first);
		return Utilities.getElementTextValue(app2first, ElementType.XPath);
	}

	public String getPaymentDetails() {
		Utilities.waitUntileElementIsVisible(paymentDetails);
		return Utilities.getElementTextValue(paymentDetails, ElementType.XPath);
	}

	public String getPaymentAmount() {
		Utilities.waitUntileElementIsVisible(paymentAmount);
		return Utilities.getElementTextValue(paymentAmount, ElementType.XPath);
	}

	public String getConfirmAmount() {
		Utilities.waitUntileElementIsVisible(ConfirmAmount);
		return Utilities.getElementTextValue(ConfirmAmount, ElementType.XPath);
	}

	public String getCustPayNotes() {
		Utilities.waitUntileElementIsVisible(CustPayNotes);
		return Utilities.getElementTextValue(CustPayNotes, ElementType.XPath);
	}

	public String getCashPayment() {
		Utilities.waitUntileElementIsVisible(cashPayment);
		return Utilities.getElementTextValue(cashPayment, ElementType.XPath);
	}

	public String getInvoiceAccountSummary() {
		Utilities.waitUntileElementIsVisible(invoiceAccountSummary);
		return Utilities.getElementTextValue(invoiceAccountSummary, ElementType.XPath);
	}

	public String getLastLetter() {
		Utilities.waitUntileElementIsVisible(lastLetter);
		return Utilities.getElementTextValue(lastLetter, ElementType.XPath);
	}

	public String getLastEmail() {
		Utilities.waitUntileElementIsVisible(lastEmail);
		return Utilities.getElementTextValue(lastEmail, ElementType.XPath);
	}

	public String getPayments() {
		Utilities.waitUntileElementIsVisible(Payments);
		return Utilities.getElementTextValue(Payments, ElementType.XPath);
	}

	// Setter
	public String insertPaymentAmount(String amount) {
		Utilities.waitUntileElementIsVisible(PayAmt);
		FindElement.elementByAttribute(PayAmt, InputType.XPath).clear();
		FindElement.elementByAttribute(PayAmt, InputType.XPath).sendKeys(amount);
		Utilities.waitUntileElementIsVisible(ConfAmt);
		FindElement.elementByAttribute(ConfAmt, InputType.XPath).sendKeys(amount);
		Utilities.waitUntileElementIsVisible(custPayNotes);
		FindElement.elementByAttribute(custPayNotes, InputType.XPath).sendKeys("This is just a test");
		return amount;
	}
	public String invoiceAmount(String amount)
	{
		Utilities.waitUntileElementIsVisible(invoiceAmount);
		FindElement.elementByAttribute(invoiceAmount, InputType.XPath).clear();
		FindElement.elementByAttribute(invoiceAmount, InputType.XPath).sendKeys(amount);
		return amount;
		
	}
}
