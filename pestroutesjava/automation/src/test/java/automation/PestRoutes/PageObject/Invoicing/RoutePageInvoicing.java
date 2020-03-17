package automation.PestRoutes.PageObject.Invoicing;

import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class RoutePageInvoicing {

	public String addPayment = "//h3[text() = 'Payments']/following-sibling::div";
	public String addNewInvoice = "+ New Invoice";
	public String accountStatementReport = "Account Statement Report";
	public String accountSummary = "Account Summary";
	public String addTicketItem = "//div[contains(@class,'grayButton full aCenter  serviceTicketButton left') and contains(text(),'Add Ticket Item')]";
	public String clickAddPartialPayment = "//div[contains(@class,'grayButton full aCenter  serviceTicketButton ticketPaymentButton left') and contains(text(),'Add Payment')]";
	private String addAvailableTicket = "//span[contains(@class,'productDescription') and contains(text(),'Candy Party')]";

	// Setter

	public void clickAddNewInvoice(String needTab) {
		Utilities.clickElement("//li[contains(@class,'shortItem') and contains(text(),'" + needTab + "')]",
				ElementType.XPath);

	}

	public void clickAddPayment() {
		Utilities.waitUntileElementIsVisible(addPayment);
		Utilities.clickElement(addPayment, ElementType.XPath);
	}

	public void invoiceDetails() {
		Utilities.waitUntileElementIsVisible(addTicketItem);
		Utilities.clickElement(addTicketItem, ElementType.XPath);

	}

	public void clickAddPartialPayments() {
		Utilities.waitUntileElementIsVisible(clickAddPartialPayment);
		Utilities.clickElement(clickAddPartialPayment, ElementType.XPath);
	}

	public void selectAvailableItems() {
		Utilities.waitUntileElementIsVisible(addAvailableTicket);
		Utilities.clickElement(addAvailableTicket, ElementType.XPath);
	}
}
