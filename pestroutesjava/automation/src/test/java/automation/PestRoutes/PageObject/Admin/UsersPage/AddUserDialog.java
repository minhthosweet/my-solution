package automation.PestRoutes.PageObject.Admin.UsersPage;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

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
	

	
	//Action methods
	public void selectValueFromDropDown(String needDropDown, String needValue) {
		Utilities.waitUntileElementIsVisible(needDropDown);
		Utilities.selectValueFromDropDownByValue(needDropDown, needValue);
		
	}
	
	public void clickButton(String needButton) {
		Utilities.waitUntileElementIsVisible(needButton);
		Utilities.clickElement(needButton, ElementType.XPath);
	}
	
	
	//Setters
	public void setInputValue(String needInputField, String keysToSend) {
		FindElement.elementByAttribute(needInputField, InputType.XPath).sendKeys(keysToSend);
	}
	
	//Getters
	public String getFieldValue(String needFieldName) {
		Utilities.waitUntileElementIsVisible(needFieldName);
		return Utilities.getElementTextValue(needFieldName, ElementType.XPath);
	}
}
