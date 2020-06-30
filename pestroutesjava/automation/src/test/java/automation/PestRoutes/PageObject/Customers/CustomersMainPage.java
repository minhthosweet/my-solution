package automation.PestRoutes.PageObject.Customers;

import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class CustomersMainPage {
	
	public String customerReport = "customerReport";
	public String emailDashboard = "emailStats";
	public String salesReport = "soldCustomers";
	public String auditSales = "auditSales";
	public String leads = "leads";
	private String actions = "//div[@id='customerReportTableActions']";
	
	public void NavigateTo(String chooseTabFromConst) {
		Utilities.waitUntileElementIsVisible(actions);
		Utilities.clickElement("//li[@for = '"+chooseTabFromConst+"']", ElementType.XPath);
	}
}
