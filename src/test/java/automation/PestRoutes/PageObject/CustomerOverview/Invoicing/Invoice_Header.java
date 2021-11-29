package automation.PestRoutes.PageObject.CustomerOverview.Invoicing;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Invoice_Header extends BasePage {
	
	public String cash = "Cash";
	private By cashPayment = By.xpath("//div[@class='wallet']//div[text()='Cash']");
	public String check = "Check";
	public String creditCard = "Card";
	private By cardPayment = By.xpath("//div[@class='wallet']//div[text()='Card']");
	public String achDraft = "ACH";
	public String coupon = "Coupon / Credit";
	public String addPayment = "//div[text()='+ Add Payment']";
	
	public void navigate(String needTab) {
		try {
		WebElement elm = FindElement.elementByAttribute(needTab, FindElement.InputType.XPath);
			if (elm.isDisplayed()){
				Utilities.clickElement(needTab, ElementType.XPath);
				Utilities.clickElement("//div[contains(@class,'paymentMethod bluGradientBG') and contains(text(),'"+needTab+"')]",ElementType.XPath, true, false);
			}
		} catch(Exception e) {
			Utilities.clickElement("//div[contains(@class,'paymentMethod bluGradientBG') and contains(text(),'"+needTab+"')]",ElementType.XPath, true, false);
		}
	}

	public void clickCash() { click(cashPayment); }

	public void clickCard() { click(cardPayment);}
}
