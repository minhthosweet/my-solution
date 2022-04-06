package automation.PestRoutes.PageObject.Structure;

import automation.PestRoutes.Utilities.Deprecated;

public class StructuresTab {

    // Structures tab in Customer Card
    private String addMainStructure = "//li[@id='addMainStructure']//div[text()='Add Main Structure ']";
    private String structureName = "//input[@id='newStructureDescription']";
    private String addSubStructure = "//div[@id='addDeleteStructureContainer']//span[text()='Add']";

    // the only XPath that can find this Save button
    private String newStructureSave = "//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-front no-close ui-dialog-buttons ui-draggable']//span[text()='Save']";

    // Structures tab in Appointment Card
    private String structuresTab = "//ul[@id='appointmentTabs']//a[text()='Structures']";

    // Click actions Products
    private String clickProducts = "//span[@id='chemicalsHeader']/i";
    private String clickExistingCustomer = "//div[@id='chooseCustomerDialog']/input[@name='customer']";
    private String clickAddProduct_mainStructure = "//div[@id='apptStructuresPanel']//div[2]//div[text()='+ add product']";
    private String clickAddProduct_subStructure = "//div[@id='apptStructuresPanel']//div[3]//div[text()='+ add product']";

    // Getter Methods
    public void getChemicalNameStructure(String checmicalName) {
        Deprecated.scrollToElement("//div[@id='structureDetails']//div[contains (text(),'" + checmicalName + "')]");
        Deprecated.clickElement("//div[@id='structureDetails']//div[contains (text(),'" + checmicalName + "')]"
        );
    }

    public void setMainStructure(String mainStructuresName) {
        Deprecated.clickElement(addMainStructure);
        Deprecated.waitVisible(structureName);
        Deprecated.locate(structureName).sendKeys(mainStructuresName);
        Deprecated.clickElement(newStructureSave);
    }

    public void setSubStructure(String subStructuresName) {
        Deprecated.clickElement(addSubStructure);
        Deprecated.waitVisible(structureName);
        Deprecated.locate(structureName).sendKeys(subStructuresName);
        Deprecated.waitVisible(newStructureSave);
        Deprecated.clickElement(newStructureSave);
        Deprecated.clickElement("//ul[@id='structuresMenuList']//span[text()='" + subStructuresName + "']"
        );
    }

    // Details Button for Main Structure and SubStructure
    public void clickDetailsButtonMainStructure(String mainStructure) {
        Deprecated.waitVisible(
                "//div[@id='apptStructuresPanel']//div[2]//h3[contains (text(),'" + mainStructure + "')]");
        Deprecated.clickElement(
                "//div[@id='apptStructuresPanel']//div[2]//h3[contains (text(),'" + mainStructure + "')]"
        );
    }

    public void clickDetailsButtonSubStructure(String subStructure) {
        Deprecated.waitVisible(
                "//div[@id='apptStructuresPanel']//div[3]//h3[contains (text(),'" + subStructure + "')]");
        Deprecated.clickElement("//div[@id='apptStructuresPanel']//div[3]//h3[contains (text(),'" + subStructure + "')]"
        );
    }

    // Add Product click for Structures and SubStructures
    public void clickAddProductMainStructure() {
        Deprecated.waitVisible(clickAddProduct_mainStructure);
        Deprecated.clickElement(clickAddProduct_mainStructure);
    }

    public void clickAddProductSubStructure() {
        Deprecated.waitVisible(clickAddProduct_subStructure);
        Deprecated.clickElement(clickAddProduct_subStructure);
    }

    // Click Appointment card on Schedule Card
    public void clickStructuresTabApt() {
        Deprecated.clickElement(structuresTab);
    }

    public void clickProductsAptTab() {
        Deprecated.clickElement(clickProducts);
    }

    public void clickExistingCustomer(String custID, String firstName) {
        Deprecated.clickElement(clickExistingCustomer);
        Deprecated.locate(clickExistingCustomer).sendKeys(custID);
        Deprecated.waitVisible("//span[contains (text(), '" + firstName + "')]");
        Deprecated.clickElement("//span[contains (text(), '" + firstName + "')]");
    }

}
