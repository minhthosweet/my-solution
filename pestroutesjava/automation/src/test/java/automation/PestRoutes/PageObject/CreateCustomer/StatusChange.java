package automation.PestRoutes.PageObject.CreateCustomer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class StatusChange {

	static WebDriver driver = GetWebDriver.getInstance();;
	public String accountStatusChange = "//div[@id='completeButton']/span";

	// Cancel Category
	public String clickCancelCatogory = "//select[@id='cancelCategory']";

	// Cancellation Category
	public String selectCancellationReason = "Select Cancellation Reason";
	public String cannotAffordServicesssx = "Cannot afford servicesssx";
	public String collectionsAccount = "Collections Account";
	public String costumerIsAOneTime = "Customer is a one time";
	public String CXLOther = "CXL - Other";
	public String CXLSales = "CXL-Sales";
	public String doesNotNeedServiceAnymore = "Does not need service anymore";
	public String foundABetterCompany = "Found a better company";
	public String medical = "Medical";
	public String militaryPrders = "Military Orders";
	public String moved = "Moved";
	public String myDog = "my dog";
	public String needsTermites = "Needs Termites";
	public String neverReceivedInitial = "Never Received Initial";
	public String noContact = "No Contact";
	public String noMoreBugs = "No More Bugs";
	public String officeMIstake = "Office Mistake";
	public String oneTimeInspectionOnly = "One Time Inspection Only";
	public String oneTimeOnly = "One Time Only";
	public String oneTimeService = "One-Time Service";
	public String refusedRenewal = "Refused Renewal";
	public String soldHouse = "Sold House";
	public String stillSeeingBugs = "Still Seeing Bugs";
	public String stolenByADifferentCompany = "Stolen by a different comapny";
	public String techError = "Tech Error";
	public String tragicAccident = "Tragic Accident";
	public String trnasferredBranches = "Transferred Branches";
	public String unhappyWithService = "Unhappy With Service";

	// Cancel Customer Options
	public String clickCancelCustomerOptions = "//select[@id='cancelCustomerOptions']";
	public String cancelServiceProps = "Cancel Responsible Service Subscriptions";
	public String reassignToNew = "Reassign Service Properties To New Billing Account";
	public String reassignToSelf = "Reassign Service Properties to Themselves";
	public String cancelNotes = "//textarea[@id='cancelNotes']";
	private String clickFreeze = "//span[text()='Freeze Account']";

	public void changeAccountStatus_Frozen(String selectCancellationCatrgory, String cancelCustomerOption) {
		
			Utilities.waitUntileElementIsVisible(accountStatusChange);
			Utilities.clickElement(accountStatusChange, ElementType.XPath);
			Utilities.clickElement(clickCancelCatogory, ElementType.XPath);
			clickCancellationCategory();
			cancellationCategory(selectCancellationCatrgory);
			selectCancelCustomerOptions(cancelCustomerOption);
			cancellationNotes();
			clickFreezeAccount();
		} 
	public void changeAccountStatus_Active() {
			Utilities.waitUntileElementIsVisible(accountStatusChange);
			Utilities.clickElement(accountStatusChange, ElementType.XPath);
		}
	

	public String getAccountStatus() {
		Utilities.scrollToElement(
				"//span[text() = '" + currentDateIs() + " CDT']/parent::div/parent::li//h4/parent::div[1]");
		String text = driver
				.findElement(By.xpath(
						"//span[text() = '" + currentDateIs() + " CDT']/parent::div/parent::li//h4/parent::div[1]"))
				.getText();
		return text;
	}

	public void clickCancellationCategory() {
		Utilities.clickElement(clickCancelCatogory, ElementType.XPath);
	}

	public void cancellationCategory(String selectCancellationCatrgory) {
		Utilities.waitUntileElementIsVisible(
				"//select[@id='cancelCategory']/option[text() = '" + selectCancellationCatrgory + "']");
		Utilities.clickElement("//select[@id='cancelCategory']/option[text() = '" + selectCancellationCatrgory + "']",
				ElementType.XPath);
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

	public String currentDateIs() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("M/dd/yy h:mm aa");
		String strDate = dateFormat.format(date).toLowerCase();
		System.out.println(strDate);
		return strDate;

	}

	public String getActualDate() {
		Utilities.scrollToElement(
				"//span[text() = '" + PDTDate() + " CDT']/parent::div/parent::li//h4/parent::div[1]");
		String text = driver
				.findElement(By.xpath(
						"//span[text() = '" + PDTDate() + " CDT']/parent::div/parent::li//h4/parent::div[1]"))
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
