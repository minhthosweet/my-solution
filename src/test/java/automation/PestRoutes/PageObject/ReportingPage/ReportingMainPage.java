package automation.PestRoutes.PageObject.ReportingPage;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.PageObject.ReportingPage.OfficePage.PaymentsByServiceTypeTab;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.By;

public class ReportingMainPage extends BasePage {
	public String office = "Office";
	public String inventory = "Inventory";
	private By paymentsByServiceType = By.xpath("//ul[@id='oReportsMenu']/li[text()='Payments by Service Type']");

	public void navigateTo(String needTab) {
		Deprecated.clickElement("//p[text() = '"+needTab+"']");
	}

	public PaymentsByServiceTypeTab clickPaymentsByServiceType () {
		Deprecated.scrollToElementJS(Utilities.locate(paymentsByServiceType));
		Utilities.click(paymentsByServiceType);
		return new PaymentsByServiceTypeTab();
	}
}
