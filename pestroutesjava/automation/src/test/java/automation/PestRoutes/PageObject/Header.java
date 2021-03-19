package automation.PestRoutes.PageObject;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;;

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
	

	public void searchCustomer_History(String needCustomerFullName){
		Utilities.waitUntileElementIsVisible(ACCESS_HISTORY);
		Utilities.jsClickElement(ACCESS_HISTORY, ElementType.XPath);
		Utilities.clickElement("//span[text()='" + needCustomerFullName + "']", ElementType.XPath);
	}

	@When("I search the number {string} customer in History tab")
	public void searchCustomerInOrder(String customerNumber){
		clickAccessHistory();
		Utilities.clickElement("//h3[text()='Customer Access History']/following-sibling::div//li["+customerNumber+"]//span["+customerNumber+"]", ElementType.XPath);
	}
	//**Author Aarbi
	@And("I search customer with name {string}")
	public void searchCustomerWithName(String needCustomerName) throws InterruptedException {
		clickAccessHistory();
		String name = convertName(needCustomerName);
		Utilities.clickElement("//span[text()='"+name+"']", ElementType.XPath);
	}

	public void clickAccessHistory(){
		Utilities.waitUntileElementIsVisible(ACCESS_HISTORY);
		Utilities.jsClickElement(ACCESS_HISTORY, ElementType.XPath);
	}

	public void searchCustomer_SearchField(String customerDetails){
		Utilities.jsClickElement(SearchField, ElementType.XPath);
		FindElement.elementByAttribute(SearchField, FindElement.InputType.XPath).sendKeys(customerDetails);
	}
	//**Author Aarbi
	public static String convertName(String name) {
		String firstName = name.substring(0, name.indexOf(" "));
		String lastName = name.substring(name.indexOf(" ") + 1);
		String cName =  lastName + ", " +firstName;
		return cName;
	}
}