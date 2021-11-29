package automation.PestRoutes.PageObject.ReportingPage;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.PageObject.ReportingPage.OfficePage.PaymentsByServiceTypeTab;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import org.openqa.selenium.By;

public class ReportingMainPage extends BasePage {
	public String office = "Office";
	public String inventory = "Inventory";
	private By paymentsByServiceType = By.xpath("//ul[@id='oReportsMenu']/li[text()='Payments by Service Type']");

	public void navigateTo(String needTab) {
		Utilities.clickElement("//p[text() = '"+needTab+"']", ElementType.XPath);
	}

	public PaymentsByServiceTypeTab clickPaymentsByServiceType () {
		Utilities.scrollToElementJS(find(paymentsByServiceType));
		click(paymentsByServiceType);
		return new PaymentsByServiceTypeTab();
	}
}
