package automation.PestRoutes.PageObject.CustomerOverview;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class CustomerviewDialog_AppointmentsTab {
	
	//**********Tab's objects**********
	public String statusButton = "//div[@id='appointmentContainor']//div[@id='SubStatus']";
	public String productNameObject = "//div[@class = 'full left']/div[1]";
	public String areaTreated = "//div[contains (text(), 'Treated Areas:')]/following-sibling::div[1]";
	public String pestsTreated = "//div[contains (text(), 'Treated Pests:')]/following-sibling::div[1]";
	public String addProductButton_InCompletingApptDialog = "//div[text()='+ add product']";
	public String applicationMethodDropdown_InCompletingApptDialog = "//div[@class='chemicalTicketContent']//select[@name = 'applicationMethod']";
	public String targetIssueDropdown_InCompletingApptDialog = "//select[@id='targetedPests0']";
	public String targetAreaDropdown_InCompletingApptDialog = "//select[@id='targetedLocations0']";
	public String cancelButton_InCompletingApptDialog = "//div[@id='completeAppointment']/following-sibling::div//span[text() = 'Cancel']";
	public String saveButton_InCompletingApptDialog = "//div[@id='completeAppointment']/following-sibling::div//span[text() = 'Save']";
	public String saveAndCompleteButton_InCompletingApptDialog = "//span[text() = 'Save and Complete']";
	
	
	
	
	
	//**********Functions**********
	
	/*
	 * Action Methods
	 * Below function will click or select objects from drop down
	 */
	public void clickScheduledService(String needServiceName) {
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        System.out.println(dateFormat.format(date));
		Utilities.waitUntileElementIsVisible("//span[text()='Pending']/parent::div/preceding-sibling::div[contains (text(), '"+needServiceName+"')]");
		Utilities.clickElement
		("//span[text()='Pending']/parent::div/preceding-sibling::div[contains (text(), '"+needServiceName+"')]", ElementType.XPath);
	}
	
	public void clickStatusButton() {
		Utilities.clickElement(statusButton, ElementType.XPath);
	}
	
	public void clickAddProductButton_InCompletingApptDialog() {
		Utilities.waitUntileElementIsVisible(addProductButton_InCompletingApptDialog);
		Utilities.clickElement(addProductButton_InCompletingApptDialog, ElementType.XPath);
	}
	
	public void chooseProduct(String needProductName) {
		Utilities.clickElement("//span[text()='"+needProductName+"']", ElementType.XPath);
	}
	
	public void chooseApplicationMethod(String needMethod) {
		Utilities.scrollToElement(applicationMethodDropdown_InCompletingApptDialog);
		Utilities.selectValueFromDropDownByValue(applicationMethodDropdown_InCompletingApptDialog, needMethod);
	}
	
	public void chooseTargetIssue(String needOption) {
		Utilities.selectValueFromDropDownByValue(targetIssueDropdown_InCompletingApptDialog, needOption);
	}
	
	public void chooseTargetArea(String needOption) {
		Utilities.selectValueFromDropDownByValue(targetAreaDropdown_InCompletingApptDialog, needOption);
	}
	
	public void clickCancelButton() {
		Utilities.clickElement(cancelButton_InCompletingApptDialog, ElementType.XPath);
	}
	
	public void clickSaveButton() {
		Utilities.clickElement(saveButton_InCompletingApptDialog, ElementType.XPath);
	}
	
	public void clickSaveAndCompleteButton() {
		Utilities.clickElement(saveAndCompleteButton_InCompletingApptDialog, ElementType.XPath);
	}
	
	/*
	 * Getter Methods
	 * Below methods get text value of an object
	 */
	public String getChemicalName() {
		Utilities.waitUntileElementIsVisible(productNameObject);
		return Utilities.getElementTextValue(productNameObject, ElementType.XPath);
	}
	
	public String getTreatedArea() {
		Utilities.waitUntileElementIsVisible(areaTreated);
		return Utilities.getElementTextValue(areaTreated, ElementType.XPath);
	}
	
	public String getTreatedPests() {
		Utilities.waitUntileElementIsVisible(pestsTreated);
		return Utilities.getElementTextValue(pestsTreated, ElementType.XPath);
	}

}
