package automation.PestRoutes.PageObject.CustomerOverview;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class CustomerViewDialog_Admin {

	static WebDriver driver = GetWebDriver.getInstance();;
	public String accountStatusChange = "//div[@id='completeButton']/span";

	// Cancel Category
	public String clickCancelCatogory = "//select[@id='cancelCategory']";

	// Cancel Customer Options
	public String clickCancelCustomerOptions = "//select[@id='cancelCustomerOptions']";
	public String cancelServiceProps = "Cancel Responsible Service Subscriptions";
	public String reassignToNew = "Reassign Service Properties To New Billing Account";
	public String reassignToSelf = "Reassign Service Properties to Themselves";
	public String cancelNotes = "//textarea[@id='cancelNotes']";
	private String clickFreeze = "//span[text()='Freeze Account']";

	public void changeAccountStatus_Frozen(String cancelCustomerOption) {

		Utilities.waitUntileElementIsVisible(accountStatusChange);
		Utilities.clickElement(accountStatusChange, ElementType.XPath);
		Utilities.clickElement(clickCancelCatogory, ElementType.XPath);
		clickCancellationCategory();
		cancellationCategory(2);
		selectCancelCustomerOptions(cancelCustomerOption);
		cancellationNotes();
		clickFreezeAccount();
	}

	public void changeAccountStatus_Active() {
		Utilities.waitUntileElementIsVisible(accountStatusChange);
		Utilities.clickElement(accountStatusChange, ElementType.XPath);
	}

	public String getAccountStatus() {
		Utilities.scrollToElement("//span[text() = '" + Utilities.currentDate("M/dd/yy h:mm aa").toLowerCase()
				+ " CDT']/parent::div/parent::li//h4/parent::div[1]");
		String text = driver
				.findElement(By.xpath("//span[text() = '" + Utilities.currentDate("M/dd/yy h:mm aa").toLowerCase()
						+ " CDT']/parent::div/parent::li//h4/parent::div[1]"))
				.getText();
		return text;
	}

	public void clickCancellationCategory() {
		Utilities.clickElement(clickCancelCatogory, ElementType.XPath);
	}

	public void cancellationCategory(int indexOfCancellationType) {
		selectValueFromDropDownByIndex("//select[@id='cancelCategory']", indexOfCancellationType);
	}

	public static void selectValueFromDropDownByIndex(String needXpath, Integer needIndex) {
		Select dropdown = new Select(driver.findElement(By.xpath(needXpath)));
		dropdown.selectByIndex(needIndex);
	}

	public void selectCancelCustomerOptions(String cancelCustomerOption) {
		Utilities.waitUntileElementIsVisible(
				"//select[@id='cancelCustomerOptions']/option[text() = '" + cancelCustomerOption + "']");
		Utilities.clickElement("//select[@id='cancelCustomerOptions']/option[text() = '" + cancelCustomerOption + "']",
				ElementType.XPath);
	}

	public void cancellationNotes() {
		Utilities.clickElement(cancelNotes, ElementType.XPath);
		FindElement.elementByAttribute(cancelNotes, InputType.XPath).sendKeys("Cancelling the customer");
	}

	public void clickFreezeAccount() {
		Utilities.clickElement(clickFreeze, ElementType.XPath);
	}

	public String getActualDate() {
		Utilities.scrollToElement("//span[text() = '" + PDTDate() + " CDT']/parent::div/parent::li//h4/parent::div[1]");
		String text = driver
				.findElement(
						By.xpath("//span[text() = '" + PDTDate() + " CDT']/parent::div/parent::li//h4/parent::div[1]"))
				.getText();
		return text;
	}

	public String PDTDate() {
		Date date = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("PST"));
		String strDate = formatter.format(date).toLowerCase();
		return strDate;
	}
}
