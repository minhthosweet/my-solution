package automation.PestRoutes.PageObject.Customers.SalesReport;

import java.io.IOException;

import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class SalesReportPage extends BaseClass{
	
	public String selectToday = "//div[@class='daterangepicker dropdown-menu ltr openscenter show-calendar']//li[text()='Today'] ";
	public String subscriptionFlagColumn = "//th[text()='Subscription Flags'] ";
	public String currentSubscriptionFlagName = "//td[text()='Fire']";
	String selectDateField = "//input[@name='soldCustomersReportDateFilter']";
	String additionColumns = "//select[@name='extraFields']";
	String filterBySalesman = "//select[@name='soldSalesmen']";
	String filterBySalesman2 = "//select[@name='soldSalesmen2']";
	String refreshButton = "//div[text()='Refresh']";
	String exportButton = "//div[text()='Export to Excel']";
	String salesReportTotalContractValue = "//div[@class='salesReportTotalsContainer']/preceding-sibling::h3[1]";

	


	public void selectTodaysDate(String needDate) {
		Utilities.waitUntileElementIsVisible(selectDateField);
		Utilities.clickElement(selectDateField, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(selectToday);
		Utilities.clickElement(needDate, ElementType.XPath);
	}
	
	public void selectAdditionalColumns(String needColumnName) {
		Utilities.selectValueFromDropDownByValue(additionColumns, needColumnName);
		
	}
	
	public void selectSalesmanFilter(String needSalesman) {
		Utilities.selectValueFromDropDownByValue(filterBySalesman, needSalesman);
	}
	
	public void ClickRefreshButton() {
		Utilities.clickElement(refreshButton, ElementType.XPath);
	}

	
	public void subscriptionFlagColumnPresent() {
		Utilities.waitUntileElementIsVisible(subscriptionFlagColumn);
	}
	
	public String getCurrentSubscriptionFlagName(String chooseNameFromConst) {
		String elm = Utilities.getElementTextValue("//td[contains(text(),'"+chooseNameFromConst+"')]/following-sibling::td[13]", ElementType.XPath);
		return elm;
	}

	public double getSalesReportTotalSingleContractValue(String chooseNameFromConst) {
		String elm = Utilities.getElementTextValue("//td[contains(text(),'"+chooseNameFromConst+"')]/following-sibling::td[12]", ElementType.XPath);
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
