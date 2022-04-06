package automation.PestRoutes.PageObject.Admin.PreferencesTab.ServiceRelatedTab;

import automation.PestRoutes.Utilities.Deprecated;

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
		Deprecated.waitVisible(addEquipmentTypeButton);
		Deprecated.clickElement(addEquipmentTypeButton);
	}
	public void selectOptionFromDropdown(String needObject, String needOption) {
		Deprecated.waitVisible(needObject);
		Deprecated.selectByText(needObject, needOption);
	}
	
	/*
	 * Setter methods
	 * Below methods sets values to the input fields for given object
	 */
	public void setID_Prefix(String needData) {
		Deprecated.waitVisible(idPrefixInputField);
		Deprecated.locate(idPrefixInputField).sendKeys(needData);
	}
	public void setDescription(String needData) {
		Deprecated.waitVisible(descriptionInputField);
		Deprecated.locate(descriptionInputField).sendKeys(needData);
	}
	public void setCode(String needData) {
		Deprecated.waitVisible(codeInputField);
		Deprecated.locate(codeInputField).sendKeys(needData);
	}
	
	/*
	 * Getter methods
	 * Below methods get text value of an object
	 */
	public String getIDPrefix() {
		Deprecated.waitVisible(prefix);
		return Deprecated.getElementTextValue(prefix);
	}
	public String getDescription() {
		Deprecated.waitVisible(description);
		return Deprecated.getElementTextValue(description);
	}

}
