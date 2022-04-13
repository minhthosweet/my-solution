package automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab;

import automation.PestRoutes.Utilities.Legacy;

public class FormObjects {
	
	public String addFormButton = "//div[text() = '+ Form Template']";
	public String addFlagButton = "//div[text() = '+ Generic Flag']";
	public String addRegionButton = "//div[text() = '+ Region']";
	public String htmlButton = "//div[@id='preferenceHeader']/following-sibling::form//div[text()='html']";
	public String previewButton = "//div[@id='preferenceHeader']/following-sibling::form//div[text()='preview']";
	public String deleteButton = "//div[@id='preferenceHeader']/following-sibling::form//div[text()='delete']";
	public String cancelButton = "//div[@id='preferenceHeader']/following-sibling::form//div[text()='cancel']";
	public static String saveButton = "//div[@id='preferenceHeader']/following-sibling::form//div[text()='save']";
	
	//Form objects
	public String nameField = "//div[@id='preferenceHeader']/following-sibling::form//input[@name='description']";
	public String flagCodeField = "//div[@id='preferenceHeader']/following-sibling::form//input[@name='code']";
	public String flagType = "//div[@id='preferenceHeader']/following-sibling::form//select[@name='type']";
	public String requiredApprovalDropdown = "//div[@id='preferenceHeader']/following-sibling::form//select[@name='requireApproval']";
	public String htmlInputField = "//div[@id='preferenceHeader']/following-sibling::form//textarea[@name = 'content']";
	public String existingFlag = "//div[contains(text(),'Fire')]";

	public void clickButton(String needAttribute) {
		Legacy.waitVisible(needAttribute);
		Legacy.clickElement(needAttribute);
	}
	public void clickEditButton(String needFormName) {
		Legacy.waitVisible("//input[@value = '"+needFormName+"']/following-sibling::div[text() = 'edit']");
		Legacy.clickElement("//input[@value = '"+needFormName+"']/following-sibling::div[text() = 'edit']");
	}
	public void selectValueFromDropdown(String needType, String needValue) {
		Legacy.selectByText(needType, needValue);
	}

	public void setInputField(String needInputField, String needValue) {
		Legacy.waitVisible(needInputField);
		Legacy.locate(needInputField).sendKeys(needValue);
	}


}
