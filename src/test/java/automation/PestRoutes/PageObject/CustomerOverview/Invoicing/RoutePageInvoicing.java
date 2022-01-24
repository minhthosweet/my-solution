package automation.PestRoutes.PageObject.CustomerOverview.Invoicing;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RoutePageInvoicing extends BasePage {

	WebDriverWait wait = new WebDriverWait(driver, 5);

	public String addPayment = "//div[text()='+ Add Payment']";
	private By addInvoicePayment = By.xpath("//div[text()='+ Add Payment']");
	public String addNewInvoice = "//li[text()='+ New Invoice']";
	private By newInvoice = By.xpath("//li[text()='+ New Invoice']");
	private By invoiceNumber = By.xpath("//ul[@id='invoiceGroupListContainer']//span[text()='Invoice']/..");
	public String addTicketItem = "//div[contains(@class,'grayButton full aCenter  serviceTicketButton left') and contains(text(),'Add Ticket Item')]";
	public String clickAddPartialPayment = "//div[contains(@class,'grayButton full aCenter  serviceTicketButton ticketPaymentButton left') and contains(text(),'Add Payment')]";
	private String addAvailableTicket = "//div[@id='availableItems']//li[1]";
	private By backToAccountSummaryButton = By.xpath("//div[@id='billingPanel']//div[text()='Back to Account Summary']");
	private By fullyPaidPaymentStatus = By.xpath("//ul[@id='invoiceGroupListContainer']//div[contains(text(),'FULLY PAID')]");
	private By serviceChargeField = By.xpath("//div[@id='invoiceDetails']//input[@name='serviceCharge']");
	private By initialDiscountField = By.xpath("//div[@id='invoiceDetails']//div[text()='Initial Discount']/following-sibling::input[@name='amount']");
	private By paymentBalanceField = By.xpath("//div[@id='invoiceDetails']//div[text()='Balance']/following-sibling::div");
	private By recentMemo = By.xpath("//div[@id='billingPanel']//div[contains(text(), 'applied')]");
	private By initialBalance = By.xpath("//ul[@id='invoiceGroupListContainer']//span[text()='Initial Balance']/..");
	private By subStatusAmount = By.xpath("//div[@class='statusBox']//div[@class='subInactive']");

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

	public void addPayment() {
		delay(3000);
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

	public boolean clickBackToAccountSummaryButton () {
		if (driver.findElements(backToAccountSummaryButton).size() > 0) {
			click(backToAccountSummaryButton);
			return true;
		} else {
			return false;
		}
	}

	public void clickFullyPaidPaymentStatus() throws InterruptedException {
		Thread.sleep(3000);
		click(fullyPaidPaymentStatus);
	}

	public void typeServiceChargeAmount(String serviceCharge){
		type(serviceCharge, serviceChargeField);
	}

	public void typeInitialDiscount(String initialDiscount){
		type(initialDiscount, initialDiscountField);
	}

	public String getPaymentBalance(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBalanceField));
		return getText(paymentBalanceField);
	}

	public String getInvoiceNumber(){
		String invoiceNum = getText(invoiceNumber);
		String accountInvoiceNumber = invoiceNum.replaceAll(
					"\\s.*", "");
		return accountInvoiceNumber;
	}

	public String getInitialBalance(){
		String initialBalanceAmount = getText(initialBalance);
		String balanceAmount = initialBalanceAmount.replaceAll(
					"\\s.*", "");
		return balanceAmount;
	}

	public String getSubStatusAmount(){
		return getText(subStatusAmount);
	}

	public String getRecentMemo(){
		return getText(recentMemo);
	}
}
