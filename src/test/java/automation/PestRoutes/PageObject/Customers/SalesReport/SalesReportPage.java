package automation.PestRoutes.PageObject.Customers.SalesReport;

import automation.PestRoutes.Utilities.Legacy;

public class SalesReportPage{
	
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
		Legacy.waitVisible(selectDateField);
		Legacy.clickElement(selectDateField);
		Legacy.waitVisible(selectToday);
		Legacy.clickElement(needDate);
	}
	
	public void selectAdditionalColumns(String needColumnName) {
		Legacy.selectByText(additionColumns, needColumnName);
	}

	public void selectIncludeOffices(String needOffice) {
		Legacy.selectByText(includeOffices, needOffice);
	}

	
	public void selectSalesmanFilter(String filterBySalesman, String needSalesman) {
		Legacy.selectByText(filterBySalesman, needSalesman);
	}

	public void selectFilter(String needFilterType, String needValue) {
		Legacy.selectByText(needFilterType, needValue);
	}

	public void clickFilter(String needFilterType, String needValue) {
		Legacy.selectByText(needFilterType, needValue);
	}
	
	public void ClickRefreshButton() {
		Legacy.waitVisible(refreshButton);
		Legacy.clickElement(refreshButton);
	}

	
	public void subscriptionFlagColumnPresent() {
		Legacy.waitVisible(subscriptionFlagColumn);
	}
	
	public String getCurrentSubscriptionFlagName(String chooseNameFromConst) {
		String elm = Legacy.getElementTextValue("//td[contains(text(),'"+chooseNameFromConst+"')]/following-sibling::td[12]");
		return elm;
	}

	public double getSalesReportTotalSingleContractValue(String chooseNameFromConst) {
		String elm = Legacy.getElementTextValue("//td[contains(text(),'"+chooseNameFromConst+"')]/following-sibling::td[11]");
		String newElm = elm.replaceAll("[^\\\\.0123456789]", "");
		double attributeValue = Double.parseDouble(newElm);
		return attributeValue;		
	}
	
	public double getSalesReportTotalContractValue() {
		String elm = Legacy.getElementTextValue(salesReportTotalContractValue);
		String newElm = elm.replaceAll("[^\\\\.0123456789]", "");
		double attributeValue = Double.parseDouble(newElm);
		return attributeValue;
	}
	
	
	
}
