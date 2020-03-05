package automation.PestRoutes.PageObject.Structure;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class StructuresTab {

	// Structures tab in Customer Card
	private String addMainStructure = "//li[@id=\"addMainStructure\"]//div[text()='Add Main Structure ']";
	private String structureName = "//input[@id=\"newStructureDescription\"]";
	private String addSubStructure = "//div[@id=\"addDeleteStructureContainer\"]//span[text()='Add']";

	// the only XPath that can find this Save button
	private String newStructureSave = "//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-front no-close ui-dialog-buttons ui-draggable']//span[text()='Save']";

	// Structures tab in Appointment Card
	private String structuresTab = "//ul[@id=\"appointmentTabs\"]//a[text()='Structures']";

	// click appointment card
	private String clickScheduledCustomer = "//div[@id=\"routesView\"]//div[@class=\"appointmentCustomer bBox pending\"]";
	private String clickAppointmentCard = "//div[@id=\"appointmentOptions\"]//p[text()='Appointment Card']";

	// Click actions Products
	private String clickProducts = "//span[@id=\"chemicalsHeader\"]/i";
	private String clickExistingCustomer = "//div[@id=\"chooseCustomerDialog\"]/input[@name=\"customer\"]";
	private String clickAddProduct_mainStructure = "//div[@id=\"apptStructuresPanel\"]//div[2]//div[text()='+ add product']";
	private String clickAddProduct_subStructure = "//div[@id=\"apptStructuresPanel\"]//div[3]//div[text()='+ add product']";

	// Getter Methods
	public void getChemicalNameStructure(String checmicalName) {
		Utilities.scrollToElement("//div[@id=\"structureDetails\"]//div[contains (text(),'" + checmicalName + "')]");
		Utilities.clickElement("//div[@id=\"structureDetails\"]//div[contains (text(),'" + checmicalName + "')]",
				ElementType.XPath);
	}

	public void setMainStructure(String mainStructuresName) {
		Utilities.clickElement(addMainStructure, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(structureName);
		FindElement.elementByAttribute(structureName, InputType.XPath).sendKeys(mainStructuresName);
		Utilities.clickElement(newStructureSave, ElementType.XPath);
	}

	public void setSubStructure(String subStructuresName) {
		Utilities.clickElement(addSubStructure, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(structureName);
		FindElement.elementByAttribute(structureName, InputType.XPath).sendKeys(subStructuresName);
		Utilities.waitUntileElementIsVisible(newStructureSave);
		Utilities.clickElement(newStructureSave, ElementType.XPath);
		Utilities.clickElement("//ul[@id=\"structuresMenuList\"]//span[text()='" + subStructuresName + "']",
				ElementType.XPath);
	}

	// Details Button for Main Structure and SubStructure
	public void clickDetailsButtonMainStructure(String mainStructure) {
		Utilities.waitUntileElementIsVisible(
				"//div[@id=\"apptStructuresPanel\"]//div[2]//h3[contains (text(),'" + mainStructure + "')]");
		Utilities.clickElement(
				"//div[@id=\"apptStructuresPanel\"]//div[2]//h3[contains (text(),'" + mainStructure + "')]",
				ElementType.XPath);
	}

	public void clickDetailsButtonSubStructure(String subStructure) {
		Utilities.waitUntileElementIsVisible(
				"//div[@id=\"apptStructuresPanel\"]//div[3]//h3[contains (text(),'" + subStructure + "')]");
		Utilities.clickElement(
				"//div[@id=\"apptStructuresPanel\"]//div[3]//h3[contains (text(),'" + subStructure + "')]",
				ElementType.XPath);
	}

	// Add Product click for Structures and SubStructures
	public void clickAddProductMainStructure() {
		Utilities.waitUntileElementIsVisible(clickAddProduct_mainStructure);
		Utilities.clickElement(clickAddProduct_mainStructure, ElementType.XPath);
	}

	public void clickAddProductSubStructure() {
		Utilities.waitUntileElementIsVisible(clickAddProduct_subStructure);
		Utilities.clickElement(clickAddProduct_subStructure, ElementType.XPath);
	}

	//Click Appointment card on Schedule Card
	public void clickStructuresTabApt() {
		Utilities.clickElement(structuresTab, ElementType.XPath);
	}

	public void clickAppointmentCard() throws InterruptedException {
		Utilities.waitUntileElementIsVisible(clickScheduledCustomer);
		Utilities.clickElement(clickScheduledCustomer, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(clickAppointmentCard);
		Utilities.scrollToElement(clickAppointmentCard);
		Utilities.clickElement(clickAppointmentCard, ElementType.XPath);
	}

	public void clickProductsAptTab() {
		Utilities.clickElement(clickProducts, ElementType.XPath);
	}

	public void clickExistingCustomer(String custID, String firstName) {
		Utilities.clickElement(clickExistingCustomer, ElementType.XPath);
		FindElement.elementByAttribute(clickExistingCustomer, InputType.XPath).sendKeys(custID);
		Utilities.waitUntileElementIsVisible("//span[contains (text(), '" + firstName + "')]");
		Utilities.clickElement("//span[contains (text(), '" + firstName + "')]", ElementType.XPath);
	}

}
