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
	public String accountStatementReport = "Account Statement Report";
	public String accountSummary = "Account Summary";
	public String addTicketItem = "//div[contains(@class,'grayButton full aCenter  serviceTicketButton left') and contains(text(),'Add Ticket Item')]";
	public String clickAddPartialPayment = "//div[contains(@class,'grayButton full aCenter  serviceTicketButton ticketPaymentButton left') and contains(text(),'Add Payment')]";
	private String addAvailableTicket = "//div[@id='availableItems']//li[1]";
	private By paymentStatusField = By.xpath("//ul[@id='invoiceGroupListContainer']/ul/li/div[2]/div[2]");
	private By invoicesTab = By.xpath("//li[@name='invoicesTab']");
	private By successApprovedNote = By.xpath("//div[@id='billingPanel']//div[text()='Success! Approved']");
	private By backToAccountSummaryButton = By.xpath("//div[@id='billingPanel']//div[text()='Back to Account Summary']");

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
		//This Method Was Not Consistent
		//Sometimes the + Add Payment Click And Sometimes It Did Not Click
		//Optimized For Encapsulation Below via addPayment() Using A Private Modifier With By Class
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

	public String getSuccessApprovedNote () {
		return getText(successApprovedNote);
	}

	public Boolean clickBackToAccountSummaryButton () {
		if (driver.findElements(backToAccountSummaryButton).size() > 0) {
			click(backToAccountSummaryButton);
			return true;
		}
		else {
			return false;
		}
	}
}
