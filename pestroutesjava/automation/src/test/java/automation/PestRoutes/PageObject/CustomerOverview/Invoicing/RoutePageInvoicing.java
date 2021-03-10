package automation.PestRoutes.PageObject.CustomerOverview.Invoicing;

import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class RoutePageInvoicing {

	public String addPayment = "//div[text()='+ Add Payment']";
	public String addNewInvoice = "//li[text()='+ New Invoice']";
	public String accountStatementReport = "Account Statement Report";
	public String accountSummary = "Account Summary";
	public String addTicketItem = "//div[contains(@class,'grayButton full aCenter  serviceTicketButton left') and contains(text(),'Add Ticket Item')]";
	public String clickAddPartialPayment = "//div[contains(@class,'grayButton full aCenter  serviceTicketButton ticketPaymentButton left') and contains(text(),'Add Payment')]";
	private String addAvailableTicket = "//div[@id='availableItems']//li[1]";

	// Setter

	public void clickAddNewInvoice(String needTab) {
		Utilities.clickElement(needTab,ElementType.XPath);

	}

	public void clickAddPayment() {
		Utilities.waitUntileElementIsVisible(addPayment);
		Utilities.clickElement(addPayment, ElementType.XPath);
	}

	public void jsClickAddPayment() {
		Utilities.waitUntileElementIsVisible(addPayment);
		Utilities.jsClickElement(addPayment, ElementType.XPath);
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
