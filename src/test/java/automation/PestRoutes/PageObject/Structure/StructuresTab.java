package automation.PestRoutes.PageObject.Structure;

import automation.PestRoutes.Utilities.Legacy;

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
        Legacy.scrollToElement("//div[@id='structureDetails']//div[contains (text(),'" + checmicalName + "')]");
        Legacy.clickElement("//div[@id='structureDetails']//div[contains (text(),'" + checmicalName + "')]"
        );
    }

    public void setMainStructure(String mainStructuresName) {
        Legacy.clickElement(addMainStructure);
        Legacy.waitVisible(structureName);
        Legacy.locate(structureName).sendKeys(mainStructuresName);
        Legacy.clickElement(newStructureSave);
    }

    public void setSubStructure(String subStructuresName) {
        Legacy.clickElement(addSubStructure);
        Legacy.waitVisible(structureName);
        Legacy.locate(structureName).sendKeys(subStructuresName);
        Legacy.waitVisible(newStructureSave);
        Legacy.clickElement(newStructureSave);
        Legacy.clickElement("//ul[@id='structuresMenuList']//span[text()='" + subStructuresName + "']"
        );
    }

    // Details Button for Main Structure and SubStructure
    public void clickDetailsButtonMainStructure(String mainStructure) {
        Legacy.waitVisible(
                "//div[@id='apptStructuresPanel']//div[2]//h3[contains (text(),'" + mainStructure + "')]");
        Legacy.clickElement(
                "//div[@id='apptStructuresPanel']//div[2]//h3[contains (text(),'" + mainStructure + "')]"
        );
    }

    public void clickDetailsButtonSubStructure(String subStructure) {
        Legacy.waitVisible(
                "//div[@id='apptStructuresPanel']//div[3]//h3[contains (text(),'" + subStructure + "')]");
        Legacy.clickElement("//div[@id='apptStructuresPanel']//div[3]//h3[contains (text(),'" + subStructure + "')]"
        );
    }

    // Add Product click for Structures and SubStructures
    public void clickAddProductMainStructure() {
        Legacy.waitVisible(clickAddProduct_mainStructure);
        Legacy.clickElement(clickAddProduct_mainStructure);
    }

    public void clickAddProductSubStructure() {
        Legacy.waitVisible(clickAddProduct_subStructure);
        Legacy.clickElement(clickAddProduct_subStructure);
    }

    // Click Appointment card on Schedule Card
    public void clickStructuresTabApt() {
        Legacy.clickElement(structuresTab);
    }

    public void clickProductsAptTab() {
        Legacy.clickElement(clickProducts);
    }

    public void clickExistingCustomer(String custID, String firstName) {
        Legacy.clickElement(clickExistingCustomer);
        Legacy.locate(clickExistingCustomer).sendKeys(custID);
        Legacy.waitVisible("//span[contains (text(), '" + firstName + "')]");
        Legacy.clickElement("//span[contains (text(), '" + firstName + "')]");
    }

}
