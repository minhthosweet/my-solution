package automation.PestRoutes.PageObject.CustomerOverview.Invoicing;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Legacy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Invoice_Header extends BasePage {
	
	public String cash = "Cash";
	private By cashPayment = By.xpath("//div[@class='wallet']//div[text()='Cash']");
	public String check = "Check";
	private By checkPayment = By.xpath("//div[@class='wallet']//div[text()='Check']");
	public String creditCard = "Card";
	private By cardPayment = By.xpath("//div[@class='wallet']//div[text()='Card']");
	public String achDraft = "ACH";
	public String coupon = "Coupon / Credit";
	private By couponPayment = By.xpath("//div[@class='wallet']//div[text()='Coupon / Credit']");
	public String addPayment = "//div[text()='+ Add Payment']";

	public void navigate(String needTab) {
		try {
		WebElement elm = Legacy.locate(needTab);
			if (elm.isDisplayed()){
				Legacy.clickElement(needTab);
				Legacy.clickElement("//div[contains(@class,'paymentMethod bluGradientBG') and contains(text(),'"+needTab+"')]", true, false);
			}
		} catch(Exception e) {
			Legacy.clickElement("//div[contains(@class,'paymentMethod bluGradientBG') and contains(text(),'"+needTab+"')]", true, false);
		}
	}

	public void navigateTo(String paymentMethodTab) {
		try {
			String strPaymentMethodTab = "//div[contains(@class,'paymentMethod bluGradientBG') and contains(text(),'"+ paymentMethodTab+"')]";

			WebElement elm = Legacy.locate(strPaymentMethodTab);
			if (elm.isDisplayed()){
				Legacy.clickElement(strPaymentMethodTab);
				Legacy.clickElement(strPaymentMethodTab, true, false);

				//Wait to the Invoice Payment  Screen to load
				Thread.sleep(1000);
				}
		} catch(Exception e) {
			System.out.println("******** navigateTo(): Unable to Click Payment Method Tab...");
		}
	}

	public void clickCash() { Utilities.click(cashPayment); }

	public void clickCard() { Utilities.click(cardPayment);}

	public void clickCheck() { Utilities.click(checkPayment);}

	public void clickCouponCredit() { Utilities.click(couponPayment);}

}
