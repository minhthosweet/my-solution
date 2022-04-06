package automation.PestRoutes.PageObject.Customers;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.PageObject.Customers.CustomerReportsTab.CustomerReportsPage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.By;

import static automation.PestRoutes.Utilities.Utilities.*;

public class CustomersMainPage extends BasePage {
	
	public String customerReport = "Customer Reports";
	public String emailDashboard = "Email Dashboard";
	public String salesReport = "Sales Report";
	public String auditSales = "Audit Sales";
	public String leads = "Leads";
	private String actions = "//div[@id='customerReportTableActions']";
	private By customerReportsSubComponent = By.xpath("//ul[@id='customerNav']//p[text()='Customer Reports']");
	
	public void navigateTo(String needReportType) {
		Deprecated.waitVisible(actions);
		Deprecated.clickElement("//ul[@id='customerNav']//p[text()='"+needReportType+"']");
	}

	public CustomerReportsPage goToCustomerReports() {
		click(customerReportsSubComponent);
		delay(3000);
		return new CustomerReportsPage();
	}
}