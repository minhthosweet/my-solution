package automation.PestRoutes.PageObject.CustomerOverview.Invoicing;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import org.openqa.selenium.WebElement;

import javax.swing.text.Element;

public class Invoice_Header {
	
	public String cash = "Cash";
	public String check = "Check";
	public String creditCard = "Card";
	public String achDraft = "ACH";
	public String coupon = "Coupon / Credit";
	public String addPayment = "//div[text()='+ Add Payment']";
	
	public void navigate(String needTab) {
		try {
		WebElement elm = FindElement.elementByAttribute(addPayment, FindElement.InputType.XPath);
			if (elm.isDisplayed()){
				Utilities.clickElement(addPayment, ElementType.XPath);
				Utilities.clickElement("//div[contains(@class,'paymentMethod bluGradientBG') and contains(text(),'"+needTab+"')]",ElementType.XPath, true, false);
			}
		} catch(Exception e) {
			Utilities.clickElement("//div[contains(@class,'paymentMethod bluGradientBG') and contains(text(),'"+needTab+"')]",ElementType.XPath, true, false);
		}
	}
}
