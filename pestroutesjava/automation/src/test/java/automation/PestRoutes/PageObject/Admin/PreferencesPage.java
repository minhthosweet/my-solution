package automation.PestRoutes.PageObject.Admin;

import org.openqa.selenium.WebElement;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class PreferencesPage {
	//Admin page objects
	public String serviceRelatedNav = "//h2[@id='service']";
	
	//Equipment types objects
	public String equipmentTypesText = "//li[text() = 'Equipment Types']";
	
	
	
	//Service types objects
	public String serviceTypesText = "//li[text() = 'Service Types']";
	public String searchInputField = "//input[@placeholder = 'Search...']";
	public String addNewServiceButton = "//div[text() = '+ Service Type']";
	public String services_DescriptionInputField = "//div[@id='preferenceHeader']/following-sibling::form//input[@placeholder='Description (Required)']";
	public String services_CategoryInputField = "//div[@id='preferenceHeader']/following-sibling::form//input[@placeholder='Category']";
	public String services_AbbreviationInputField = "//div[@id='preferenceHeader']/following-sibling::form//input[@placeholder='Abbreviation']";
	public String globalDropDown = "//div[@id='preferenceHeader']/following-sibling::form//select[@placeholder='Global']";
	public String visibilityDropDown = "//div[@id='preferenceHeader']/following-sibling::form//select[@placeholder='Visibility']";
	public String appointmentFrequencyDropDown = "//select[@name='frequency']";
	public String defaultFollowUpDelayDropDown = "//select[@name='defaultFollowupDelay']";
	public String defaultAppointmentLengthInputField = "//input[@name='defaultLength']";
	public String defaultContractLengthDropDown = "//select[@name='defaultContractLength']";
	public String minimumContractLengthDropDown = "//select[@name='minimumContractLength']";
	public String displayRenewalDateDropDown = "//select[@name='displayRenewalDate']";
	public String renewalFrequencyDropDown = "//select[@name='defaultRenewalFrequency']";
	public String setRenewalDateDropDown = "//select[@name='defaultSetRenewalDateOn']";
	public String defaultServiceChargeInputField = "//input[@name='defaultCharge']";
	public String serviceCommissionInputField = "//input[@name='serviceCommissionRate']";
	public String minimumInitialChargeInputField = "//input[@name='minimumInitialCharge']";
	public String recurring_OneTimeServiceDropDown = "//select[@name='regularService']";
	public String cancelButton = "//div[@id='preferenceHeader']/following-sibling::form//div[text()='cancel']";
	public String saveButton = "//div[@id='preferenceHeader']/following-sibling::form//div[text()='save']";
	
	
	/*
	 * Actions
	 * Below methods perform actions such as selecting from drop drown or click an object
	 */
	public void navigateTo(String needMenuArea, String needPage) {
		Utilities.scrollToElement(needMenuArea);
		Utilities.clickElement(needMenuArea, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(needPage);
		Utilities.clickElement(needPage, ElementType.XPath);
	}
	public void navigateToServiceTypePage() {
		Utilities.scrollToElement(serviceRelatedNav);
		Utilities.clickElement(serviceRelatedNav, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(serviceTypesText);
		Utilities.clickElement(serviceTypesText, ElementType.XPath);
	}
	public void clickEditButton(String needDescription) {
		Utilities.clickElement("//div[contains (text(), '"+needDescription+"')]/ancestor::form//div[text()='edit']", ElementType.XPath);
	}
	public void clickAddServiceButton() {
		Utilities.waitUntileElementIsVisible(addNewServiceButton);
		Utilities.clickElement(addNewServiceButton, ElementType.XPath);
	}
	public void clickSave() {
		Utilities.clickElement(saveButton, ElementType.XPath);
	}
	public void clickCancel() {
		Utilities.clickElement(cancelButton, ElementType.XPath);
	}
	public void selectFromDropdown(String needXpath, String needValue) {
		Utilities.selectValueFromDropDownByValue(needXpath, needValue);
	}
	
	/*
	 * Setter methods
	 */
	
	public void setSearch(String needText) {
		Utilities.waitUntileElementIsVisible(searchInputField);
		FindElement.elementByAttribute(searchInputField, InputType.XPath).sendKeys(needText);
	}
	public void setDescription(String needDescription) {
		Utilities.waitUntileElementIsVisible(services_DescriptionInputField);
		FindElement.elementByAttribute(services_DescriptionInputField, InputType.XPath).sendKeys(needDescription);
	}
	public void setCategory(String needCategory) {
		Utilities.waitUntileElementIsVisible(services_CategoryInputField);
		FindElement.elementByAttribute(services_CategoryInputField, InputType.XPath).sendKeys(needCategory);
	}
	public void setAbbreviation(String needAbbreviation) {
		Utilities.waitUntileElementIsVisible(services_AbbreviationInputField);
		FindElement.elementByAttribute(services_AbbreviationInputField, InputType.XPath).sendKeys(needAbbreviation);
	}
	public void setAppointLegnth(String needMinutes) {
		FindElement.elementByAttribute(defaultAppointmentLengthInputField, InputType.XPath).sendKeys(needMinutes);
	}
	public void setServiceCharge(String needAmount) {
		FindElement.elementByAttribute(defaultServiceChargeInputField, InputType.XPath).sendKeys(needAmount);
	}
	public void setServiceCommission(String needAmount) {
		FindElement.elementByAttribute(serviceCommissionInputField, InputType.XPath).sendKeys(needAmount);
	}
	public void setMinInitialCharge(String needAmount) {
		FindElement.elementByAttribute(minimumInitialChargeInputField, InputType.XPath).sendKeys(needAmount);
	}
	
	/*
	 * Getter methods
	 */
	
	public WebElement getDescription(String needText) {
		return FindElement.elementByAttribute("//div[contains (text(), '"+needText+"')]", InputType.XPath);
	}


}
