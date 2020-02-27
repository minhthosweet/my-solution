package automation.PestRoutes.PageObject;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;;

public class Header {
	
	public String newCustomerTab = "New Customer";
	public String calender = "//div[@id='guestNav']/div[2]/input";
	public String date = "//a[@class= 'ui-state-default ui-state-highlight']/parent::td/following-sibling::td[2]";
	public String schedulingTab = "Scheduling";
	public String customersTab = "Customers";
	public String billingTab = "Billing";
	public String reportingTab = "Reporting";
	public String salesTab = "Sales";
	public String adminTab = "Admin";
	public String SearchField = "//input[@id='customerSearch']";
	public String FirstSearchResult = "//ul[@id='ui-id-15']/li[1]";
	public String invoices = "Invoices";
	public String addPayment = "//h3[text() = 'Payments']/following-sibling::div";
	public String Cash = "//div[@class='wallet']//*[text()='Cash']\n";
	public String LimitedToCustomer = "//*[@id=\"singlePaymentForm\"]/div[5]/div[2]/div[2]/p/span";
	public String avalcust = "//*[@id=\"singlePaymentForm\"]/div[5]/div[2]/div[2]/div/ul/li/ul/li/label";
	public String officepaymentclick = "//*[@id=\"singlePaymentForm\"]/div[5]/div[4]/input[1]";
	public String CollPaymentclick = "//*[@id=\"singlePaymentForm\"]/div[5]/div[4]/input[2]";
	public String CollaAgencyPaymentClick = "//*[@id=\"singlePaymentForm\"]/div[5]/div[4]/input[3]";
	public String RecordPayment = "//form[@id=\"singlePaymentForm\"]/div[4]/div[1]/div[5]";
	public String AccountSummary = "//div[@id=\"billingPanel\"]/div[1]/ul[1]/li[1]";
	
	//date selected
	
	public String prev = "//span[contains(@class,'ui-icon ui-icon-circle-triangle-w') and contains(text(),'Prev')]";
	public String dateSelected = "//span[contains(@class,'ui-datepicker-month') and contains(text(),'January')]";
	public void NavigateTo(String chooseTabFromConst) {
		Utilities.clickElement("//a[text() = '"+chooseTabFromConst+"']", ElementType.XPath);
	}
	
	public void Search_A_Customer(String needCustomerFullName) throws Exception {
		Thread.sleep(3000);
		Utilities.waitUntileElementIsVisible(SearchField);
		FindElement.elementByAttribute(SearchField, InputType.XPath).sendKeys(needCustomerFullName);
		Thread.sleep(2000);
		Utilities.clickElement(FirstSearchResult, ElementType.XPath);
	}

	public void clickElementLanding() throws InterruptedException {
		Utilities.clickElement(addPayment, ElementType.XPath);
		Utilities.clickElement(Cash, ElementType.XPath);

	}

	public void clickElementCash() throws InterruptedException {
		Utilities.clickElement(LimitedToCustomer, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(avalcust);
		Utilities.clickElement(avalcust, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(officepaymentclick);
		Utilities.clickElement(officepaymentclick, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(CollPaymentclick);
		Utilities.clickElement(CollPaymentclick, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(CollaAgencyPaymentClick);
		Utilities.clickElement(CollaAgencyPaymentClick, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(RecordPayment);
		Utilities.clickElement(RecordPayment, ElementType.XPath);
	}

	public void clickElementAccountSummary() {
		Utilities.clickElement(AccountSummary, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(AccountSummary);
	}

	public String currentDateandTime() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		DateFormat TimeFormat = new SimpleDateFormat("hh:mmaa");
		Date time = new Date();
		String time1 = TimeFormat.format(time);
		return date1 + " @ " + time1 + " CST ";

	}
}
