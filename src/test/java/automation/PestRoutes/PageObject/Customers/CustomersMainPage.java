package automation.PestRoutes.PageObject.Customers;

import automation.PestRoutes.Utilities.Deprecated;

public class CustomersMainPage {
	
	public String customerReport = "Customer Reports";
	public String emailDashboard = "Email Dashboard";
	public String salesReport = "Sales Report";
	public String auditSales = "Audit Sales";
	public String leads = "Leads";
	private String actions = "//div[@id='customerReportTableActions']";
	
	public void navigateTo(String needReportType) {
		Deprecated.waitVisible(actions);
		Deprecated.clickElement("//ul[@id='customerNav']//p[text()='"+needReportType+"']");
	}
}
