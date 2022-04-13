package automation.PestRoutes.PageObject.CustomerOverview.Invoicing;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static automation.PestRoutes.Utilities.GetWebDriver.*;
import static automation.PestRoutes.Utilities.Utilities.*;

public class RoutePageInvoicing extends BasePage {

	public String addPayment = "//div[text()='+ Add Payment']";
	private By addInvoicePayment = By.xpath("//div[text()='+ Add Payment']");
	public String addNewInvoice = "//li[text()='+ New Invoice']";
	private By newInvoice = By.xpath("//li[text()='+ New Invoice']");
	private By invoiceNumber = By.xpath("//ul[@id='invoiceGroupListContainer']//span[text()='Invoice']/..");
	public String addTicketItem = "//div[contains(@class,'grayButton full aCenter  serviceTicketButton left') and contains(text(),'Add Ticket Item')]";
	private By lnkAddTicketItem =  By.xpath("//div[contains(text(),'+ Add Ticket Item')]/parent::div[@id='serviceTicket' and @displaycontext='ticketDisplay']");

	public String clickAddPartialPayment = "//div[contains(@class,'grayButton full aCenter  serviceTicketButton ticketPaymentButton left') and contains(text(),'Add Payment')]";
	private String addAvailableTicket = "//div[@id='availableItems']//li[1]";
	private By backToAccountSummaryButton = By.xpath("//div[@id='billingPanel']//div[text()='Back to Account Summary']");
	private By fullyPaidPaymentStatus = By.xpath("//ul[@id='invoiceGroupListContainer']//div[contains(text(),'FULLY PAID')]");
	private By unpaidPaymentStatus = By.xpath("//ul[@id='invoiceGroupListContainer']//div[contains(text(),'UNPAID')]");
	private By daysToPayField = By.xpath("//div[@id='invoiceDetails']//div[text()='Days to Pay']/following::div");
	private By serviceChargeField = By.xpath("//div[@id='invoiceDetails']//input[@name='serviceCharge']");
	private By initialDiscountField = By.xpath("//div[@id='invoiceDetails']//div[text()='Initial Discount']/following-sibling::input[@name='amount']");
	private By paymentBalanceField = By.xpath("//div[@id='invoiceDetails']//div[text()='Balance']/following-sibling::div");
	private By recentMemo = By.xpath("//div[@id='billingPanel']//div[contains(text(), 'applied')]");
	private By initialBalance = By.xpath("//ul[@id='invoiceGroupListContainer']//span[text()='Initial Balance']/..");
	private By subStatusAmount = By.xpath("//div[@class='statusBox']//div[@class='subInactive']");
    public By  invoiceScreenTitle = By.xpath("//*[@id='billingPanel']//h3[contains(text(), 'Invoices')]");

	// Setter

	public void clickAddNewInvoice(String needTab) {
		Deprecated.clickElement(needTab);
	}

	public void clickNewInvoice() {
		Utilities.delay(3000);
		Utilities.click(newInvoice);
	}

	public void clickAddPayment() {
		Deprecated.waitVisible(addPayment);
		Deprecated.clickElement(addPayment);
	}

	public void addPayment() {
		Utilities.delay(3000);
		Utilities.click(addInvoicePayment);
	}

	public void jsClickAddPayment() {
		Deprecated.waitVisible(addPayment);
		Deprecated.jsClickElement(addPayment);
	}

	public void invoiceDetails() {
		Deprecated.waitVisible(addTicketItem);
		Deprecated.clickElement(addTicketItem);
	}

	public void clickAddTicketItem(){
		Utilities.isPresent(lnkAddTicketItem);
		Utilities.click(lnkAddTicketItem);
	}

	public void clickAddPartialPayments() {
		Deprecated.waitVisible(clickAddPartialPayment);
		Deprecated.clickElement(clickAddPartialPayment);
	}

	public void selectAvailableItems() {
		Deprecated.waitVisible(addAvailableTicket);
		Deprecated.clickElement(addAvailableTicket);
	}

	public void selectAvailableItems( String  addOnTicketItem) {
		String strXpath = "//*[@id='availableItems']/li//span[contains(text(),'" + addOnTicketItem + "')]";
		Deprecated.waitVisible(strXpath);
		Deprecated.clickElement(strXpath);
	}//selectAvailableItems()

	public boolean clickBackToAccountSummaryButton () {
		if (driver.findElements(backToAccountSummaryButton).size() > 0) {
			Utilities.click(backToAccountSummaryButton);
			return true;
		} else {
			return false;
		}
	}

	public void clickFullyPaidPaymentStatus() {
		Utilities.delay(3000);
		Utilities.click(fullyPaidPaymentStatus);
	}

	public void clickUnpaidPaymentStatus() {
		Utilities.delay(3000);
		Utilities.click(unpaidPaymentStatus);
	}

	public String getDaysToPay() {
		String invoiceDaysToPay = getText(daysToPayField);
		System.out.println("Days To Pay:   " + invoiceDaysToPay);
		return invoiceDaysToPay;
	}

	public String inverseDaysToPay(String days) {
		// Check To See If The Invoice Days To Pay Value Is Correct
		// Days To Pay via Invoice Should Be Inverse Of Days Past Due via Customer Report (CR)
		// For Example, If Days Past Due Is 34 Then Days To Pay Is -34

		if (days.contains("-")) {
			return days.replace("-", "");
		} else {
			return "-" + days;
		}
	}

	public void typeServiceChargeAmount(String serviceCharge){
		Deprecated.type(serviceCharge, serviceChargeField);
	}

	public void typeInitialDiscount(String initialDiscount){
		Deprecated.type(initialDiscount, initialDiscountField);
	}

	public String getPaymentBalance(){
		Utilities.delay(1000);
		return getText(paymentBalanceField);
	}

	public String getInvoiceNumber(){
		Utilities.delay(1000);
		String invoiceNum = getText(invoiceNumber);
		String accountInvoiceNumber = invoiceNum.replaceAll(
				"\\s.*", "");
		return accountInvoiceNumber;
	}

	public String getInitialBalance(){
		Utilities.delay(1000);
		String initialBalanceAmount = getText(initialBalance);
		String balanceAmount = initialBalanceAmount.replaceAll(
				"\\s.*", "");
		return balanceAmount;
	}


	public String getSubStatusAmount(){
		Utilities.delay(1000);
		return getText(subStatusAmount);
	}

	public String getRecentMemo(){
		return getText(recentMemo);
	}

	public String getFullyPaidStatus() {
		String paymentStatus = getText(fullyPaidPaymentStatus);
		System.out.println("Payment Status: " + paymentStatus);
		return paymentStatus;
	}

	public String getUnpaidStatus() {
		String paymentStatus = getText(unpaidPaymentStatus);
		System.out.println("Payment Status: " + paymentStatus);
		return paymentStatus;
	}

	public int getNumberOfDeclinedTransactions() {
		List<WebElement> declinedTransactions = locateAll(By.xpath("//div[@id='billingPanel']//div[contains(text(),'declined')]"));
		int numberOfDeclinedTransactions = declinedTransactions.size();
		System.out.println("# of Declined Transactions: " + numberOfDeclinedTransactions);
		return numberOfDeclinedTransactions;
	}
}
