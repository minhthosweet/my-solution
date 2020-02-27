package automation.PestRoutes.PageObject.Invoicing;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class InvoiceImplementation {
	// Values on Invoicing Landing Page
	private String invoiceAccountSummaryClick = "//ul[@id=\"invoiceGroupListContainer\"]/ul/li";

	// Invoice Amount
	private String newInvoice = "//form[@id=\"newInvoiceParams\"]/input[2]";
	private String serviceSelect = "//form[@id=\"newInvoiceParams\"]/select";
	private String service = "//form[@id=\"newInvoiceParams\"]/select/option[21]";
	private String create = "//span[contains(@class,'ui-button-text') and contains(text(),'Create')]";

	// Assertion Invoice Values
	private String invoiceCost = "//div[@id=\"serviceTicket\"]/div[3]/div[4]/div";
	private String totalValueCharges = "//div[@id=\"invoiceDetails\"]/div[2]/div/div/div[2]/div[2]/div[6]";
	private String totalPaymentCharges = "//div[@id=\"invoiceDetails\"]/div[3]/div/div[2]/div[6]";

	// Payment Status
	private String initialPaymentStatus = "//ul[@id=\"invoiceGroupListContainer\"]/ul/li/div[2]/div[2]";
	private String paymentBalance = "//div[contains(@id,'SubStatus') and contains(text(),'$5,450.00')]";

	// Cash tab
	private String partialPaymentAmount = "//*[@id=\"singlePaymentForm\"]/div[4]/div[1]/input[1]";
	private String confirmPartialPymtAmt = "//form[@id=\"singlePaymentForm\"]/div[4]/div[1]/input[2]";
	private String custPaymentNotes = "//form[@id=\"singlePaymentForm\"]/div[4]/div[1]/textarea";
	private String recordPayment = "//form[@id=\"singlePaymentForm\"]/div[4]/div[1]/div[5]";

	// Payment Result
	private String successfulCharge = "//h2[contains(@class,'bold aCenter clr font24') and contains(text(),'Successfully Charged Cash!')]";
	private String successfulChargeAmount = "//form[@id=\"singlePaymentForm\"]/div/h2[2]";

	// Getter Methods

	public int getInvoiceCost() {
		Utilities.waitUntileElementIsVisible(invoiceCost);
		return Utilities.removeSpecialChars(invoiceCost);
	}

	public Integer getTotalValueCharges() {
		Utilities.waitUntileElementIsVisible(totalValueCharges);
		return Utilities.removeSpecialChars(totalValueCharges);
	}

	public Integer getTotalValuePayments() {
		Utilities.waitUntileElementIsVisible(totalPaymentCharges);
		return Utilities.removeSpecialChars(totalPaymentCharges);
	}

	public String checkPaymentStatus() {
		Utilities.waitUntileElementIsVisible(initialPaymentStatus);
		return Utilities.getElementTextValue(initialPaymentStatus, ElementType.XPath);

	}

	public String getSuccessfulChargeAmount() {
		String successfulChargeText = Utilities.getElementTextValue(successfulCharge, ElementType.XPath)
				+ Utilities.getElementTextValue(successfulChargeAmount, ElementType.XPath);
		System.out.println("Charge is successful " + successfulChargeText);
		return successfulChargeText;
	}

	public int getPaymentBalance() {
		return Utilities.removeSpecialChars(paymentBalance);
	}

	// Setter

	public void newInvoiceDetails(String amount) {
		Utilities.clickElement(newInvoice, ElementType.XPath);
		FindElement.elementByAttribute(newInvoice, InputType.XPath).clear();
		FindElement.elementByAttribute(newInvoice, InputType.XPath).sendKeys(amount);
		Utilities.waitUntileElementIsVisible(serviceSelect);
		Utilities.clickElement(serviceSelect, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(service);
		Utilities.clickElement(service, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(create);
		Utilities.clickElement(create, ElementType.XPath);
	}

	public void insertPaymentAmount(String pAmount, String cAmount) {
		Utilities.waitUntileElementIsVisible(partialPaymentAmount);
		FindElement.elementByAttribute(partialPaymentAmount, InputType.XPath).clear();
		FindElement.elementByAttribute(partialPaymentAmount, InputType.XPath).sendKeys(pAmount);
		Utilities.waitUntileElementIsVisible(confirmPartialPymtAmt);
		FindElement.elementByAttribute(confirmPartialPymtAmt, InputType.XPath).sendKeys(cAmount);
		Utilities.waitUntileElementIsVisible(custPaymentNotes);
		FindElement.elementByAttribute(custPaymentNotes, InputType.XPath).sendKeys("This is just a test");

	}

	public void clickrecordPayment() {
		Utilities.waitUntileElementIsVisible(recordPayment);
		Utilities.clickElement(recordPayment, ElementType.XPath);
	}

	public void InvoiceAccountSummaryClick() {
		Utilities.waitUntileElementIsVisible(invoiceAccountSummaryClick);
		Utilities.clickElement(invoiceAccountSummaryClick, ElementType.XPath);
	}

}
