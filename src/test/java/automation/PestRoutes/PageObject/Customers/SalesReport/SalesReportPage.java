package automation.PestRoutes.PageObject.Customers.SalesReport;

import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class SalesReportPage extends BaseClass{
	
	public String selectToday = "//div[@class='daterangepicker dropdown-menu ltr openscenter show-calendar']//li[text()='Today'] ";
	public String subscriptionFlagColumn = "//th[text()='Subscription Flags'] ";
	public String currentSubscriptionFlagName = "//td[text()='Fire']";
	public String selectDateField = "//input[@name='soldCustomersReportDateFilter']";
	public String selectEmployeeType = "//select[@name='employeeTypes']";
	public String selectStatus = "//select[@name='status']";
	public String subscriptionStatus = "//select[@name='subscriptionStatus']";
	public String additionColumns = "//select[@name='extraFields']";
	public String includeOffices = "//select[@id='soldCustomers-officeIDs']";
	public String includeRoamingReps = "//select[@name='includeRoamingRepSales']";
	public String filterEmployeeStatus = "//select[@name='employeeStatus']";
	public String filterBySalesman = "//select[@name='soldSalesmen']";
	public String filterBySalesman2 = "//select[@name='soldSalesmen2']";
	public String filterBySalesman3 = "//select[@name='soldSalesmen3']";
	public String filterTeams = "//select[@name='soldTeams']";
	public String includeServiceTypes = "//select[@id='soldCustomers-serviceTypes']";
	public String excludeServiceTypes = "//select[@name='excludeServiceTypes']";
	public String includeCustomerFlags = "//select[@name='includeCustomerFlags']";
	public String excludeCustomerFlags = "//select[@name='excludeCustomerFlags']";
	public String includeCustomerSources = "//select[@name='includeCustomerSources']";
	public String excludeCustomerSources = "//select[@name='excludeCustomerSources']";
	public String includeServiceTypeCategories = "//select[@name='serviceTypeCategories']";
	public String excludeServiceTypeCategories = "//select[@name='excludeServiceTypeCategories']";
	public String SearchField = "//div[@id='soldCustomers']//input[@type='search']";

	String refreshButton = "//div[@onmouseup='loadSoldCustomers();']";
	String exportButton = "//div[text()='Export to Excel']";
	String salesReportTotalContractValue = "//div[contains(text(),'Total Contract Value')]/preceding-sibling::h3";

	public void selectTodaysDate(String needDate) {
		Utilities.waitUntileElementIsVisible(selectDateField);
		Utilities.clickElement(selectDateField, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(selectToday);
		Utilities.clickElement(needDate, ElementType.XPath);
	}
	
	public void selectAdditionalColumns(String needColumnName) {
		Utilities.selectValueFromDropDownByValue(additionColumns, needColumnName);
	}

	public void selectIncludeOffices(String needOffice) {
		Utilities.selectValueFromDropDownByValue(includeOffices, needOffice);
	}

	
	public void selectSalesmanFilter(String filterBySalesman, String needSalesman) {
		Utilities.selectValueFromDropDownByValue(filterBySalesman, needSalesman);
	}

	public void selectFilter(String needFilterType, String needValue) {
		Utilities.selectValueFromDropDownByValue(needFilterType, needValue);
	}

	public void clickFilter(String needFilterType, String needValue) {
		Utilities.selectValueFromDropDownByValue(needFilterType, needValue);
	}
	
	public void ClickRefreshButton() {
		Utilities.waitUntileElementIsVisible(refreshButton);
		Utilities.clickElement(refreshButton, ElementType.XPath);
	}

	
	public void subscriptionFlagColumnPresent() {
		Utilities.waitUntileElementIsVisible(subscriptionFlagColumn);
	}
	
	public String getCurrentSubscriptionFlagName(String chooseNameFromConst) {
		String elm = Utilities.getElementTextValue("//td[contains(text(),'"+chooseNameFromConst+"')]/following-sibling::td[12]", ElementType.XPath);
		return elm;
	}

	public double getSalesReportTotalSingleContractValue(String chooseNameFromConst) {
		String elm = Utilities.getElementTextValue("//td[contains(text(),'"+chooseNameFromConst+"')]/following-sibling::td[11]", ElementType.XPath);
		String newElm = elm.replaceAll("[^\\\\.0123456789]", "");
		double attributeValue = Double.parseDouble(newElm);
		return attributeValue;		
	}
	
	public double getSalesReportTotalContractValue() {
		String elm = Utilities.getElementTextValue(salesReportTotalContractValue, ElementType.XPath);
		String newElm = elm.replaceAll("[^\\\\.0123456789]", "");
		double attributeValue = Double.parseDouble(newElm);
		return attributeValue;
	}
	
	
	
}
