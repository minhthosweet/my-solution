package automation.PestRoutes.PageObject.Leads;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Legacy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static automation.PestRoutes.Utilities.GetWebDriver.*;

public class LeadsPage extends BasePage {
	public String statusDropdown = "//select[@id='leadStatusAction']";
	
	//********************Sales info objects********************
	public String convertToLeadButton = "//div[text()='Convert Lead']";
	public String deleteButton = "//div[@id='deleteCustomerContactButton']";
	public String leadStatusButton = "//select[@id='leadStatusAction']";
	public String newButton = "//div[text()='+ New']";
	private By newQuoteButton = By.xpath("//div[text()='+ New']");
	public String assignToDropdown = "//h3[text()='Sales Info']/following-sibling::select[@name='assignedTo']";
	public String salesRep2Dropdown = 
			"//h3[text()='Sales Info']/following-sibling::select[@name='assignedTo']"
			+ "/following-sibling::select[@name='creditTo3']";
	public String dateAssignedInputField = "//h3[text()='Sales Info']/following-sibling::input[@name='dateAssigned']";
	public String valueInputField = "//h3[text()='Sales Info']/following-sibling::input[@name='value']";
	public String sourceDropdown = 
			"//h3[text()='Sales Info']/following-sibling::select[@name='assignedTo']"
			+ "/following-sibling::select[@name='sourceID']";
	public String contractDropdown = 
			"//h3[text()='Sales Info']/following-sibling::select[@name='assignedTo']"
			+ "/following-sibling::select[@name='agreementLength']";
	public String reasonLostDropdown = 
			"//h3[text()='Sales Info']/following-sibling::select[@name='assignedTo']"
			+ "/following-sibling::select[@name='lostReason']";
	
	//********************Service info objects********************
	public String serviceTypeDropdown = "//h3[text()='Service Info']/following-sibling::select[@name='serviceID']";
	private By serviceTypeDropDown = By.xpath("//select[@name='serviceID']");
	public String followUpDropdown = "//h3[text()='Service Info']/following-sibling::select[@name='followupDelay']";
	public String customStartDateInputField = "//h3[text()='Service Info']/following-sibling::input[@name='customDate']";
	public String frequencyDropdown = "//h3[text()='Service Info']/following-sibling::select[@name='frequency']";
	private By frequencyDropDown = By.xpath("//select[@name='frequency']");
	public String seasonalDropdown = "//h3[text()='Service Info']/following-sibling::div[@class='regularSchedulingOptions']/select";
	public String billingDropdown = "//h3[text()='Service Info']/following-sibling::select[@name='billingFrequency']";
	private By billingDropDown = By.xpath("//select[@name='billingFrequency']");
	
	//********************Initial Invoice Template objects********************
	public String addInitialInvoiceTicketItemButton = "//div[@class='left half']//div[text()='+ Add Ticket Item']";
	public String initialQuoteInputField = "//div[@class='left half']//input[@name='serviceCharge']";
	public String initialDiscountInputField = "//div[@class='left half']//div[text()='Initial Discount']"
			+ "/following-sibling::input[@name='amount']";
	public String initialTicketItemValue = "//div[@class='left half']//div[text()='bed']/following-sibling::input";
	public String initialSubTotalValue = "//div[@class='left half']//div[text()='Sub Total']/following-sibling::div[1]";
	public String initialTaxValue = "//div[@class='left half']//div[text()='Tax']/following-sibling::div[1]";
	public String initialTotalValue = "//div[@class='left half']//div[text()='Total']/following-sibling::div[1]";
	
	//********************Recurring Invoice Template objects********************
	public String addRecurringInvoiceTicketItemButton = "//div[@class='right half']//div[text()='+ Add Ticket Item']";
	public String recurringInvoiceInputField = "//div[@class='right half']//input[@name='serviceCharge']";
	private By recurringServiceTypeAmountField = By.xpath("//div[@class='right half']//input[@name='serviceCharge']");
	public String recurringTicketItemValue = "//div[@class='left half']//div[text()='bed']/following-sibling::input";
	public String recurringSubTotalValue = "//div[@class='right half']//div[text()='Sub Total']/following-sibling::div[1]";
	public String recurringTaxValue = "//div[@class='right half']//div[text()='Tax']/following-sibling::div[1]";
	public String recurringTotalValue = "//div[@class='right half']//div[text()='Total']/following-sibling::div[1]";

	private By standardProductionButton = By.xpath("//div[@id='recurringServices']//span[text()='Standard Production']");
	private By customProductionField = By.xpath("//div[@id='recurringServices']//input[@name='productionValue']");

	//********************Lead Notes objects********************
	public String addLeadsNotesButton = "//div[@id='addLeadNoteButton']";
	public String notesInputField = "//div[@id='leadNotesSection']//textarea";
	public String saveNotesButton = "//div[@id='saveLeadNoteButton']";

	WebDriverWait wait = new WebDriverWait(driver, 5);
	
	/*
	 * Action methods
	 * Below methods clicks an element/select value from dropdown
	 */
	public void clickButton(String needButton) {
		Legacy.waitVisible(needButton);
		Legacy.clickElement(needButton);
	}
	public void selectValueFromDropdown(String needDropDown, String needValue) {
		Legacy.waitVisible(needDropDown);
		Legacy.selectByText(needDropDown, needValue);
	}
	
	public void selectAdditionalItem(String needButton, String needItem) {
		Legacy.waitVisible(needButton);
		Legacy.clickElement(needButton);
		Legacy.waitVisible("//span[text()=  '"+needItem+"']");
		Legacy.clickElement("//span[text()=  '"+needItem+"']");
	}
	
	/*
	 * Setter method
	 * Below method sets value to given input field
	 */
	public void setInputField(String needInputField, String needValue) {
		Legacy.waitVisible(needInputField);
		Legacy.clearField(needInputField);
		Legacy.highLight(needInputField);
		Legacy.locate(needInputField).sendKeys(needValue);
	}
	
	/*
	 * Getter method
	 * Below method gets the text value of an element
	 */
	public String getValueOfAnElement(String needElement) {
		Legacy.waitVisible(needElement);
		return Legacy.getElementTextValue(needElement);
	}
	public String getValueOfAnElementByAttribute(String needElement) {
		Legacy.waitVisible(needElement);
		return Legacy.getAttribute(needElement, "value");
	}

	public void clickNewQuote() {
		Utilities.click(newQuoteButton);
	}

	public void selectServiceType(String serviceType) {
		Utilities.selectByText(serviceTypeDropDown, serviceType);
	}

	public void selectFrequency(String frequency) {
		Utilities.selectByText(frequencyDropDown, frequency);
	}

	public void selectBilling(String billing) {
		Utilities.selectByText(billingDropDown, billing);
	}

	public void typeRecurringServiceTypeAmount(String serviceTypeAmount) {
		Utilities.delay(1000);
		Utilities.type(recurringServiceTypeAmountField, serviceTypeAmount);
	}

	public String getRecurringServiceTypeAmount() {
		String serviceTypeAmount = Utilities.locate(recurringServiceTypeAmountField).getAttribute("value");
		return serviceTypeAmount;
	}

	public String getRecurringCustomProduction() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(standardProductionButton));
		Utilities.click(standardProductionButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(customProductionField));
		return Utilities.locate(customProductionField).getAttribute("value");
	}
}
