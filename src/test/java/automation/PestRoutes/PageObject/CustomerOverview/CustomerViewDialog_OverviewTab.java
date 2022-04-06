package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.By;

public class CustomerViewDialog_OverviewTab extends BasePage {
	public String overviewTab_Address = "//h3[contains (text(),  'Account Overview')]/div";
	public String customerNameInDialogHeader = "//span[@id='ui-id-11']";
	public String customerID_InDialogHeader = "//span[@class='ui-dialog-title']/span[1]";
	public String customerSinceValue = "//div[text()='Customer Since']/following-sibling::div";
	public String contractRemainingValue = "//div[text()='Contract Remaining']/following-sibling::div";
	public String lastCompletedValue = "//div[text()='Last Completed']/following-sibling::div";
	public String nextServiceValue = "//div[text()='Next Service']/following-sibling::div";
	public String recentReServiceValue = "//div[text()='Recent Re-services']/following-sibling::div";
	public String accountBalanceValue = "//div[text()='Account Balance']/following-sibling::div";
	public String accountAgeValue = "//div[text()='Account Age']/following-sibling::div";
	public String aR_TurnOverValue = "//div[text()='A/R Turnover']/following-sibling::div";
	
	/*
	 * Getter methods
	 * Below methods get text value from an object
	 */
	
	public String getCustomerNameFromHeader() {
		return Deprecated.getElementTextValue(customerNameInDialogHeader);
	}
	
	public String getCustomerIDFromHeader() {
		return Deprecated.getElementTextValue(customerID_InDialogHeader).replaceAll("[^a-zA-Z0-9]+", "");
	}
	
	public String getFullAddress() {
		return Deprecated.getElementTextValue(overviewTab_Address);
	}

	public String getCity(){
		String str =  Deprecated.getElementTextValue(overviewTab_Address);
		return str.substring(str.indexOf(" ")+1, str.indexOf(","));
	}

	public boolean getAlert(String alertName) {
		By overviewAlert = By.xpath("//div[@id='overviewPanel']//div[contains(text(),'"+ alertName +"')]");
		if (Utilities.locate(overviewAlert).isDisplayed()) {
			System.out.println("Overview Tab (Alert): " + Utilities.getText(overviewAlert));
			return true;
		}
		return false;
	}
}