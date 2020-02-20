package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class CustomerViewDialog_OverviewTab {
	public String overviewTab_Address = "//h3[contains (text(),  'Account Overview')]/div";
	public String customerNameInDialogHeader = "//span[@id='ui-id-11']";
	public String customerID_InDialogHeader = "//span[@id='ui-id-11']/span[1]";
	
	
	/*
	 * Getter methods
	 * Below methods get text value from an object
	 */
	
	public String getCustomerNameFromHeader() {
		return Utilities.getElementTextValue(customerNameInDialogHeader, ElementType.XPath);
	}
	
	public String getCustomerIDFromHeader() {
		return Utilities.getElementTextValue(customerID_InDialogHeader, ElementType.XPath);
	}

}
