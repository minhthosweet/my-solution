package automation.PestRoutes.PageObject.CreateCustomer;

import org.openqa.selenium.Keys;

import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Admin;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class CreateCustomerDIalog {
	// **********Objects**********
	public String dialogTitle = "//span[@id = 'ui-id-11']";
	public String newCustTitle = "//div[@id= 'customerPanel']/h3";
	public String firstNameInputField = "//input[@name= 'fname']";
	public String lastNameInputField = "//input[@name= 'lname']";
	public String cellPhoneInputField = "//input[@name= 'phone1']";
	public String homePhoneInputField = "//input[@name= 'phone2']";
	public String emailAddressInputField = "//input[@name= 'email']";
	public String propertyTypeDropDown = "//select[@name= 'commercialAccount']";
	public String unitTypeDropDown = "//select[@name= 'isMultiUnit']";
	public String selectSourceDropDown = "//select[@name= 'sourceID']";
	public String companyNameInputField = "//input[@name= 'companyName']";
	public String specialHandlingInputField = "//textarea[@name= 'specialScheduling']";
	public String smsCheckBox = "//input[@name= 'smsReminders']";
	public String emailCheckBox = "//input[@name= 'emailReminders']";
	public String voiceCheckBox = "//input[@name= 'phoneReminders']";
	public String genericFlagsDropDown = "//select[@id= 'customerGenericFlags']";
	public String paidInFullCheckBox = "//input[@name= 'paidInFull']";
	public String switchOverCheckBox = "//input[@name= 'switchOver']";
	public String salesRepAPayCheckBox = "//input[@name= 'salesmanAPay']";
	public String pendingCancelCheckBox = "//input[@name= 'pendingCancellation']";
	public String prefersPaperCheckBox = "//input[@name= 'prefersPaper']";
	public String purpleDragonCheckBox = "//input[@name= 'purpleDragon']";
	public String addressInputField = "//input[@name= 'address']";
	public String zipCodeInputField = "//input[@name= 'zip']";
	public String cityInputField = "//input[@name= 'city']";
	public String stateDropDown = "//select[@name= 'state']";
	public String countyDropDown = "//select[@name= 'county']";
	public String countryDropDown = "//select[@name= 'countryID']";
	public String taxPercentageInputField = "//input[@name= 'taxRate']";
	public String divisionDropDown = "//select[@name= 'divisionID']";
	public String info = "//a[text()= 'Info']";
	public String clickSave = "//button[@id='globalCustomerSaveButton']//span[text()='Save']";
	public String clickAddFlag = "//span[text()='Add Flag']";

	// Constructors
	CustomerViewDialog_Admin customerAdmin;

	// **********Functions**********

	/******************************************************************************************************************
	 * Actions Below methods perform click/select on an object
	 */
	// Drop downs
	public void selectProperty(String needPropertyType) {
		Utilities.selectValueFromDropDownByValue(propertyTypeDropDown, needPropertyType);
	}

	public void selectUnit(String needUnit) {
		Utilities.selectValueFromDropDownByValue(unitTypeDropDown, needUnit);
	}

	public void selectSource(String needSource) {
		Utilities.selectValueFromDropDownByValue(selectSourceDropDown, needSource);
	}

	public void selectGenericFlag(String needFlag) {
		Utilities.selectValueFromDropDownByValue(genericFlagsDropDown, needFlag);
	}

	public void selectState(String needState) {
		Utilities.selectValueFromDropDownByValue(stateDropDown, needState);
	}

	public void selectCounty(String needCounty) {
		Utilities.selectValueFromDropDownByValue(countyDropDown, needCounty);
	}

	public void selectCountry(String needCountry) {
		Utilities.selectValueFromDropDownByValue(countryDropDown, needCountry);
	}

	public void selectDivision(String needDivision) {
		Utilities.selectValueFromDropDownByValue(divisionDropDown, needDivision);
	}

	// Check box
	public void clickSmsCheckBox() {
		Utilities.clickElement(smsCheckBox, ElementType.XPath);
	}

	public void clickEmailCheckBox() {
		Utilities.clickElement(emailCheckBox, ElementType.XPath);
	}

	public void clickVoiceCheckBox() {
		Utilities.clickElement(voiceCheckBox, ElementType.XPath);
	}

	public void clickPaidInFullCheckBox() {
		Utilities.clickElement(paidInFullCheckBox, ElementType.XPath);
	}

	public void clickSwitchOverCheckBox() {
		Utilities.clickElement(switchOverCheckBox, ElementType.XPath);
	}

	public void clickSalesRepAPayCheckBox() {
		Utilities.clickElement(salesRepAPayCheckBox, ElementType.XPath);
	}

	public void clickPendingCancelCheckBox() {
		Utilities.clickElement(pendingCancelCheckBox, ElementType.XPath);
		customerAdmin = new CustomerViewDialog_Admin();
		customerAdmin.cancellationCategory(2);
		customerAdmin.cancellationNotes();
		clickAddFlag();
	}

	public void clickPrefersPaperCheckBox() {
		Utilities.clickElement(prefersPaperCheckBox, ElementType.XPath);
	}

	public void clickPurpleDragonCheckBox() {
		Utilities.clickElement(purpleDragonCheckBox, ElementType.XPath);
	}

	/*
	 * Setter methods Below methods insert text in the input fields
	 */
	public void setFirstName(String needFirstName) {
		Utilities.waitUntileElementIsVisible(firstNameInputField);
		FindElement.elementByAttribute(firstNameInputField, InputType.XPath).sendKeys(needFirstName);
	}

	public void setLastName(String needLastName) {
		FindElement.elementByAttribute(lastNameInputField, InputType.XPath).sendKeys(needLastName);
	}

	public void setCellPhone(String needCellPhoneNumber) {
		FindElement.elementByAttribute(cellPhoneInputField, InputType.XPath).sendKeys(needCellPhoneNumber);
	}

	public void setHomePhone(String needHomePhoneNumber) {
		FindElement.elementByAttribute(homePhoneInputField, InputType.XPath).sendKeys(needHomePhoneNumber);
	}

	public void setEmailAddress(String needEmail) {
		FindElement.elementByAttribute(emailAddressInputField, InputType.XPath).sendKeys(needEmail);
	}

	public void setCompanyName(String needCompanyName) {
		FindElement.elementByAttribute(companyNameInputField, InputType.XPath).sendKeys(needCompanyName);
	}

	public void setSpecialHandling(String needSpecialHandlingNotes) {
		FindElement.elementByAttribute(specialHandlingInputField, InputType.XPath).sendKeys(needSpecialHandlingNotes);
	}

	public void setAddress(String needAddress) {
		FindElement.elementByAttribute(addressInputField, InputType.XPath).sendKeys(needAddress);
	}

	public void setZipCode(String needZipCode) {
		FindElement.elementByAttribute(zipCodeInputField, InputType.XPath).sendKeys(needZipCode);
	}

	public void setCity(String needCity) {
		FindElement.elementByAttribute(cityInputField, InputType.XPath).sendKeys(Keys.CONTROL, "a");
		FindElement.elementByAttribute(cityInputField, InputType.XPath).sendKeys(needCity);
	}

	public void setTaxPercentage(String needTaxPercentage) {
		FindElement.elementByAttribute(taxPercentageInputField, InputType.XPath).sendKeys(needTaxPercentage);
	}

	public void clickInfo() {
		Utilities.waitUntileElementIsVisible(info);
		Utilities.clickElement(info, ElementType.XPath);
	}

	public void clickAddFlag() {
		Utilities.clickElement(clickAddFlag, ElementType.XPath);
	}
	
	public void clickSave() {
		Utilities.clickElement(clickSave, ElementType.XPath);
	}

	/*
	 * Getter methods Below methods get text value from an object
	 */
	public String getDialogTitle() {
		return Utilities.getElementTextValue(dialogTitle, ElementType.XPath);
	}

	public String getNewCustTitle() {
		return Utilities.getElementTextValue(newCustTitle, ElementType.XPath);
	}

}
