package automation.PestRoutes.PageObject.Leads;

import org.openqa.selenium.Keys;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class LeadsPage {
	public String statusDropdown = "//select[@id='leadStatusAction']";
	
	//********************Sales info objects********************
	public String convertToLeadButton = "//div[text()='Convert Lead']";
	public String deleteButton = "//div[@id='deleteCustomerContactButton']";
	public String leadStatusButton = "//select[@id='leadStatusAction']";
	public String newButton = "//div[text()='+ New']";
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
	public String followUpDropdown = "//h3[text()='Service Info']/following-sibling::select[@name='followupDelay']";
	public String customStartDateInputField = "//h3[text()='Service Info']/following-sibling::input[@name='customDate']";
	public String frequencyDropdown = "//h3[text()='Service Info']/following-sibling::select[@name='frequency']";
	public String seasonalDropdown = "//h3[text()='Service Info']/following-sibling::div[@class='regularSchedulingOptions']/select";
	public String billingDropdown = "//h3[text()='Service Info']/following-sibling::select[@name='billingFrequency']";
	
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
	public String recurringTicketItemValue = "//div[@class='left half']//div[text()='bed']/following-sibling::input";
	public String recurringSubTotalValue = "//div[@class='right half']//div[text()='Sub Total']/following-sibling::div[1]";
	public String recurringTaxValue = "//div[@class='right half']//div[text()='Tax']/following-sibling::div[1]";
	public String recurringTotalValue = "//div[@class='right half']//div[text()='Total']/following-sibling::div[1]";
	
	//********************Lead Notes objects********************
	public String addLeadsNotesButton = "//div[@id='addLeadNoteButton']";
	public String notesInputField = "//div[@id='leadNotesSection']//textarea";
	public String saveNotesButton = "//div[@id='saveLeadNoteButton']";
	
	/*
	 * Action methods
	 * Below methods clicks an element/select value from dropdown
	 */
	public void clickButton(String needButton) {
		Utilities.waitUntileElementIsVisible(needButton);
		Utilities.clickElement(needButton, ElementType.XPath);
	}
	public void selectValueFromDropdown(String needDropDown, String needValue) {
		Utilities.waitUntileElementIsVisible(needDropDown);
		Utilities.selectValueFromDropDownByValue(needDropDown, needValue);
	}
	
	public void selectAdditionalItem(String needButton, String needItem) {
		Utilities.waitUntileElementIsVisible(needButton);
		Utilities.clickElement(needButton, ElementType.XPath);
		Utilities.waitUntileElementIsVisible("//span[text()=  '"+needItem+"']");
		Utilities.clickElement("//span[text()=  '"+needItem+"']", ElementType.XPath);
	}
	
	/*
	 * Setter method
	 * Below method sets value to given input field
	 */
	public void setInputField(String needInputField, String needValue) {
		Utilities.waitUntileElementIsVisible(needInputField);
		FindElement.elementByAttribute(needInputField, InputType.XPath).sendKeys(Keys.CONTROL,"a");
		FindElement.elementByAttribute(needInputField, InputType.XPath).sendKeys(needValue);
	}
	
	/*
	 * Getter method
	 * Below method gets the text value of an element
	 */
	public String getValueOfAnElement(String needElement) {
		Utilities.waitUntileElementIsVisible(needElement);
		return Utilities.getElementTextValue(needElement, ElementType.XPath);
	}
	public String getValueOfAnElementByAttribute(String needElement) {
		Utilities.waitUntileElementIsVisible(needElement);
		return Utilities.getAttributeValue(needElement, "value");
	}
}
