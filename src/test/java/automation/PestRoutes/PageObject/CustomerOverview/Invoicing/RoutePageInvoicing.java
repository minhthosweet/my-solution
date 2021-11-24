package automation.PestRoutes.PageObject.CustomerOverview.Invoicing;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import org.openqa.selenium.By;

public class RoutePageInvoicing extends BasePage {

	public String addPayment = "//div[text()='+ Add Payment']";
	private By addInvoicePayment = By.xpath("//div[text()='+ Add Payment']");
	public String addNewInvoice = "//li[text()='+ New Invoice']";
	private By newInvoice = By.xpath("//li[text()='+ New Invoice']");
	public String addTicketItem = "//div[contains(@class,'grayButton full aCenter  serviceTicketButton left') and contains(text(),'Add Ticket Item')]";
	public String clickAddPartialPayment = "//div[contains(@class,'grayButton full aCenter  serviceTicketButton ticketPaymentButton left') and contains(text(),'Add Payment')]";
	private String addAvailableTicket = "//div[@id='availableItems']//li[1]";

	// Setter

	public void clickAddNewInvoice(String needTab) {
		Utilities.clickElement(needTab,ElementType.XPath);
		//Optimized For Encapsulation Below via clickNewInvoice() Using A Private Modifier With By Class
	}

	public void clickNewInvoice() throws InterruptedException {
		Thread.sleep(3000);
		click(newInvoice);
	}

	public void clickAddPayment() {
		Utilities.waitUntileElementIsVisible(addPayment);
		Utilities.clickElement(addPayment, ElementType.XPath);
	}

	public void addPayment() throws InterruptedException {
		Thread.sleep(3000);
		click(addInvoicePayment);
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
