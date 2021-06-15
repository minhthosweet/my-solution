package automation.PestRoutes.PageObject.CustomerOverview.Invoicing;

import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class Invoice_Header {
	
	public String cash = "Cash";
	public String check = "Check";
	public String creditCard = "Card";
	public String achDraft = "ACH";
	public String coupon = "Coupon / Credit";
	
	public void navigate(String needTab) {

		Utilities.clickElement("//div[contains(@class,'paymentMethod bluGradientBG') and contains(text(),'"+needTab+"')]",ElementType.XPath, true, false);
	
	}							
	

}
