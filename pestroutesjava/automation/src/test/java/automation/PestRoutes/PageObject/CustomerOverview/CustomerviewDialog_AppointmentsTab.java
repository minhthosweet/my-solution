package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class CustomerviewDialog_AppointmentsTab {

	// **********Tab's objects**********
	public String statusButton = "//div[@id='appointmentContainor']//div[@id='SubStatus']";
	public String unitNameObject = "//h3[text() = 'Unit Products']/parent::div//div[@class='left bold']";
	public String productNameObject = "//h3[text() = 'Products Used']/parent::div//div[@class='left bold']";
	public String areaUnitTreated = "//div[contains (text(), 'Treated Areas:')]/following-sibling::div[1]";
	public String pestsUnitTreated = "//div[contains (text(), 'Treated Pests:')]/following-sibling::div[1]";
	public String areaTreated = "//h3[text() = 'Products Used']/parent::div//div[@class='left'][1]";
	public String pestsTreated = "//h3[text() = 'Products Used']/parent::div//div[@class='left'][2]";
	public String addProductButton_InCompletingApptDialog = "//div[text()='+ add product']";
	public String applicationMethodDropdown_InCompletingApptDialog = "//div[@class='chemicalTicketContent']//select[@name = 'applicationMethod']";
	public String targetIssueDropdown_InCompletingApptDialog = "//select[@id='targetedPests0']";
	public String targetAreaDropdown_InCompletingApptDialog = "//select[@id='targetedLocations0']";
	public String cancelButton_InCompletingApptDialog = "//div[@id='completeAppointment']/following-sibling::div//span[text() = 'Cancel']";
	public String saveButton_InCompletingApptDialog = "//div[@id='completeAppointment']/following-sibling::div//span[text() = 'Save']";
	public String saveAndCompleteButton_InCompletingApptDialog = "//span[text() = 'Save and Complete']";
	public String subscriptionType_schedulinTab = "//h3[contains(text(),'Scheduling')]/parent::div//child::select[@name='subscriptionID']";
	public String serviceNotes_Complete = "//textarea[@name='serviceNotes']";

	// Subscription objects
	public String createNewSubscription_Scheduling = "Create New Subscription";
	public String standAloneService_Scheduling = "Stand-Alone Service or Reservice";

	// Unit Products
	public String unitName = "//div[@id='appointmentContainor']//div[@style = 'fonts-size:11px; float:left; margin-left:5px;']";

	// Structure Products Page
	private String structureName = "//div[@id='appointmentContainor']//div[contains (text(), 'Structure:')]";
	public String structureNameObject = "//h3[text() = 'Structure Inspected']/parent::div//div[@class='left bold']";
	public String areaStructureTreated = "//div[contains (text(), 'Target Areas:')]/following-sibling::div[1]";
	public String issuesStrutureTreated = "//div[contains (text(), 'Target Issues:')]/following-sibling::div[1]";
	// **********Functions**********

	/*
	 * Action Methods Below function will click or select objects from drop down
	 */
	public void clickScheduledService(String needServiceName) {
		Utilities.waitUntileElementIsVisible(
				"//span[text()='Pending']/parent::div/preceding-sibling::div[contains (text(), '" + needServiceName
						+ "')]");
		Utilities.clickElement("//span[text()='Pending']/parent::div/preceding-sibling::div[contains (text(), '"
				+ needServiceName + "')]", ElementType.XPath);
	}

	public void clickScheduledStructuredService(String needStructureName) {
		Utilities.clickElement("//ul[@id='structuresMenuList']//span[text()='" + needStructureName + "']",
				ElementType.XPath);
	}

	public void clickSubScheduledStructuredService(String needStructureName, String needSubStructureName) {
		Utilities.clickElement("//ul[@id='structuresMenuList']//span[text()='" + needStructureName + "']",
				ElementType.XPath);
		Utilities.waitUntileElementIsVisible(
				"//ul[@id='structuresMenuList']//span[text() = '" + needSubStructureName + "']");
		Utilities.clickElement("//ul[@id='structuresMenuList']//span[text() = '" + needSubStructureName + "']",
				ElementType.XPath);
	}

	public void clickStatusButton() {
		Utilities.clickElement(statusButton, ElementType.XPath);
	}

	public void clickAddProductButton_InCompletingApptDialog() {
		Utilities.waitUntileElementIsVisible(addProductButton_InCompletingApptDialog);
		Utilities.clickElement(addProductButton_InCompletingApptDialog, ElementType.XPath);
	}

	public void chooseProduct(String needProductName) {
		Utilities.clickElement("//span[text()='" + needProductName + "']", ElementType.XPath);
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
		Utilities.waitUntileElementIsVisible(serviceNotes_Complete);
		Utilities.clickElement(saveAndCompleteButton_InCompletingApptDialog, ElementType.XPath);
	}

	public void clickUnitName() {
		Utilities.waitUntileElementIsVisible(unitName);
		Utilities.clickElement(unitName, ElementType.XPath);
	}

	/*
	 * Getter Methods Below methods get text value of an object
	 */

	public String getUnitChemicalName() {
		Utilities.waitUntileElementIsVisible(unitNameObject);
		return Utilities.getElementTextValue(unitNameObject, ElementType.XPath);
	}

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

	/*
	 * Getter Methods Below methods get text value of an Unit Product object
	 */
	public String getUnitName() {
		Utilities.waitUntileElementIsVisible(unitName);
		return Utilities.getElementTextValue(unitName, ElementType.XPath);
	}

	public String getUnitAreaTreated() {
		Utilities.waitUntileElementIsVisible(areaUnitTreated);
		return Utilities.getElementTextValue(areaUnitTreated, ElementType.XPath);
	}

	public String getUnitPestsTreated() {
		Utilities.waitUntileElementIsVisible(pestsUnitTreated);
		return Utilities.getElementTextValue(pestsUnitTreated, ElementType.XPath);
	}

	// Structure Methods(Structure Products Object
	public String getStructureChemicalName() {
		Utilities.waitUntileElementIsVisible(structureNameObject);
		return Utilities.getElementTextValue(structureNameObject, ElementType.XPath);
	}

	public void clickStructureName() {
		Utilities.waitUntileElementIsVisible(structureName);
		Utilities.clickElement(structureName, ElementType.XPath);
	}

	public String getStructureAreaTreated() {
		Utilities.waitUntileElementIsVisible(areaStructureTreated);
		return Utilities.getElementTextValue(areaStructureTreated, ElementType.XPath);
	}

	public String getStructureIssuesTreated() {
		Utilities.waitUntileElementIsVisible(issuesStrutureTreated);
		return Utilities.getElementTextValue(issuesStrutureTreated, ElementType.XPath);
	}

}
