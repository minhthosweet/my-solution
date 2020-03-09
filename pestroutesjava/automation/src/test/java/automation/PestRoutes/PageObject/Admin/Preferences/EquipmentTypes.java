package automation.PestRoutes.PageObject.Admin.Preferences;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class EquipmentTypes {
	//Equipment types main objects
	public String requiredDropdown = "//select[@name = 'required']";
	public String searchStatusDropdown = "//label[text() = 'Status']/following-sibling::select[@name = 'status']";
	public String addEquipmentTypeButton = "//div[text() = '+ Equipment Type']";
	//Equipment types data objects
	public String prefix = "//div[@id='preferenceHeader']/following-sibling::li[1]//div[@class='left prefNewDescription']";
	public String description = "//div[@id='preferenceHeader']/following-sibling::li[1]//div[@class='grayInput oneQuarter left prefDisplay searchable']";
	//Add equipment objects
	public String idPrefixInputField = "//div[@id='preferenceHeader']/following-sibling::form//input[@placeholder = 'ID Prefix']";
	public String descriptionInputField = "//div[@id='preferenceHeader']/following-sibling::form//input[@placeholder = 'Description']";
	public String requiredToCompleteDropdown = "//div[@id='preferenceHeader']/following-sibling::form//select[@placeholder = 'Required To Complete']";
	public String statusDropdown = "//div[@id='preferenceHeader']/following-sibling::form//select[@placeholder = 'Status']";
	public String defaultDentriconBaitDropdown = "//div[@id='preferenceHeader']/following-sibling::form//select[@placeholder = 'Default Bait']";
	public String barcodeRequiredDropdown = "//div[@id='preferenceHeader']/following-sibling::form//select[@placeholder = 'Barcode Required']";
	public String codeInputField = "//div[@id='preferenceHeader']/following-sibling::form//input[@placeholder = 'Code']";
	public String applicationMethodDropdown = "//div[@id='preferenceHeader']/following-sibling::form//select[@name= 'applicationMethod']";
	public String targerIssueDropdown = "//div[@id='preferenceHeader']/following-sibling::form//select[@placeholder= 'Target Issues']";
	
	
	/*
	 * Action methods
	 * Below methods clicks or select an object
	 */
	public void clickAddEquipmentButton() {
		Utilities.waitUntileElementIsVisible(addEquipmentTypeButton);
		Utilities.clickElement(addEquipmentTypeButton, ElementType.XPath);
	}
	public void selectOptionFromDropdown(String needObject, String needOption) {
		Utilities.waitUntileElementIsVisible(needOption);
		Utilities.selectValueFromDropDownByValue(needObject, needOption);
	}
	
	/*
	 * Setter methods
	 * Below methods sets values to the input fields for given object
	 */
	public void setID_Prefix(String needData) {
		Utilities.waitUntileElementIsVisible(idPrefixInputField);
		FindElement.elementByAttribute(idPrefixInputField, InputType.XPath).sendKeys(needData);
	}
	public void setDescription(String needData) {
		Utilities.waitUntileElementIsVisible(descriptionInputField);
		FindElement.elementByAttribute(descriptionInputField, InputType.XPath).sendKeys(needData);
	}
	public void setCode(String needData) {
		Utilities.waitUntileElementIsVisible(codeInputField);
		FindElement.elementByAttribute(codeInputField, InputType.XPath).sendKeys(needData);
	}
	
	/*
	 * Getter methods
	 * Below methods get text value of an object
	 */
	public String getIDPrefix() {
		Utilities.waitUntileElementIsVisible(prefix);
		return Utilities.getElementTextValue(prefix, ElementType.XPath);
	}
	public String getDescription() {
		Utilities.waitUntileElementIsVisible(description);
		return Utilities.getElementTextValue(description, ElementType.XPath);
	}

}
