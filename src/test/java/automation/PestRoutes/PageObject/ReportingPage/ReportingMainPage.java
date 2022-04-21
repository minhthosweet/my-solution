package automation.PestRoutes.PageObject.ReportingPage;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.PageObject.ReportingPage.OfficePage.CustomerRestorePageObjects;
import automation.PestRoutes.PageObject.ReportingPage.OfficePage.PaymentsByServiceTypeTab;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Legacy;
import org.openqa.selenium.By;

public class ReportingMainPage extends BasePage {
	public String office = "Office";
	public String inventory = "Inventory";
	private By paymentsByServiceType = By.xpath("//ul[@id='oReportsMenu']/li[text()='Payments by Service Type']");
	private By lnkCustomerRestore = By.xpath("//ul[@id='oReportsMenu']/li[text()='Customer Restore']");

	public void navigateTo(String needTab) {
		Legacy.clickElement("//p[text() = '"+needTab+"']");
	}

	public PaymentsByServiceTypeTab clickPaymentsByServiceType() {
		Legacy.scrollToElementJS(Utilities.locate(paymentsByServiceType));
		Utilities.click(paymentsByServiceType);
		return new PaymentsByServiceTypeTab();
	}

	public CustomerRestorePageObjects clickCustomerRestore() {
		Legacy.scrollToElementJS(Utilities.locate(lnkCustomerRestore));
		Utilities.click(lnkCustomerRestore);
		return new CustomerRestorePageObjects();
	}//clickCustomerRestore()

}
