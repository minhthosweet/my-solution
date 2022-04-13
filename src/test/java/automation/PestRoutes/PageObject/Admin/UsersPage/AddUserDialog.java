package automation.PestRoutes.PageObject.Admin.UsersPage;

import automation.PestRoutes.Utilities.Legacy;

public class AddUserDialog {
	
	// **********Objects********** //
	public String dialogTitle = "//span[@id = 'ui-id-18']";
	public String firstNameInputField = "//input[@name= 'fname']";
	public String lastNameInputField = "//input[@name= 'lname']";
	public String accountTypeDropDown = "//select[@class='sis-select-gray maxW']";
	public String activateSaveButton = "(//span[text()='Save'])[7]";
	public String deactivateSaveButton = "(//span[text()='Save'])[8]";
	public String cancelButton = "(//span[text()='Cancel'])[9]";
	public String deactivateLink = "//span[text()='De-Activate']";
	public String closeButton = "//span[text()='Edit User']/following-sibling::button[@title='close']";
	

	
	//Action methods
	public void selectValueFromDropDown(String needDropDown, String needValue) {
		Legacy.waitVisible(needDropDown);
		Legacy.selectByText(needDropDown, needValue);
		
	}
	
	public void clickButton(String needButton) {
		Legacy.waitVisible(needButton);
		Legacy.clickElement(needButton);
	}
	
	
	//Setters
	public void setInputValue(String needInputField, String keysToSend) {
		Legacy.locate(needInputField).sendKeys(keysToSend);
	}
	
	//Getters
	public String getFieldValue(String needFieldName) {
		Legacy.waitVisible(needFieldName);
		return Legacy.getElementTextValue(needFieldName);
	}
}
