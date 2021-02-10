package automation.PestRoutes.PageObject;

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
	public String ACCESS_HISTORY = "//div[@id='toggleAccessHistory']";

	
	public void navigateTo(String chooseTabFromConst) {
		Utilities.waitUntileElementIsVisible("//a[text() = '"+chooseTabFromConst+"']");
		Utilities.clickElement("//a[text() = '"+chooseTabFromConst+"']", ElementType.XPath);
	}
	
	public void searchCustomer(String needCustomerFullName){
		Utilities.jsClickElement(ACCESS_HISTORY, ElementType.XPath);
		Utilities.clickElement("//span[text()='" + needCustomerFullName + "']", ElementType.XPath);
	}

	public void searchCustomerInOrder(String customerNumber){
		Utilities.jsClickElement(ACCESS_HISTORY, ElementType.XPath);
		Utilities.clickElement("//h3[text()='Customer Access History']/following-sibling::div//li["+customerNumber+"]//span["+customerNumber+"]", ElementType.XPath);
	}
}