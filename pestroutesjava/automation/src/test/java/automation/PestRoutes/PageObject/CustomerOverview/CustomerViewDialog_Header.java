package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class CustomerViewDialog_Header {
    public String overviewTabInDialog = "overviewTab";
    public String infoTabInDialog = "infoTab";
    public String subscriptionTabInDialog = "subscriptionTab";
    public String leadsTabInDialog = "leadsTab";
    public String billingTabInDialog = "billingTab";
    public String notesTabInDialog = "notesTab";
    public String documentsTabInDIalog = "documentsTab";
    public String appointmentsTabInDialog = "appointmentsTab";
    public String invoicesTabInDialog = "invoicesTab";
    public String propertiesTabInDialog = "linkedPropertiesTab";
    public String unitsTabInDialog = "unitsTab";
    public String equipmentTabInDialog = "equipmentsTab";
    public String adminTabInDialog = "adminTab";
    public String structuresTabInDialog = "structuresTab";
    public String tranferButtonInDialog = "//span[text()='Transfer']";
    public String closeButton = "//div[@id= 'customerWindow']/following-sibling::div//span[text()='Close']";
    public String saveButton = "//div[@id= 'customerWindow']/following-sibling::div//span[text()='Save']";
    public String closeXButton = "//span[@id= 'ui-id-11']/parent::div/button/span";
    public String discardChange = "//span[text()='Discard Changes']";
    public String saveAnyways = "//span[text()='Save Anyways']";

    //Notes tab objects
    public String customerContacts_Notes = "//li[text()='Customer Contacts']";

    public void clickCustomerContactsInNotesTab() {
        Utilities.waitUntileElementIsVisible(customerContacts_Notes);
        Utilities.clickElement(customerContacts_Notes, ElementType.XPath);
    }

    public void navigateTo(String chooseTabFromConst) throws InterruptedException {
        Thread.sleep(800);
        Utilities.waitUntileElementIsVisible("//li[@name= '" + chooseTabFromConst + "']");
        Utilities.clickElement("//li[@name = '" + chooseTabFromConst + "']", ElementType.XPath, true, false);
    }

    public void ClickTranferButton() {
        Utilities.clickElement(tranferButtonInDialog, ElementType.XPath);
    }

    public void clickSaveButton() throws InterruptedException {
        Utilities.clickElement(saveButton, ElementType.XPath);
        Thread.sleep(400);
    }

    public void clickCloseButton() {
        Utilities.waitUntileElementIsVisible(closeButton);
        Utilities.clickElement(closeButton, ElementType.XPath);
    }

    public void Click_X_Button() {
        Utilities.clickElement(closeXButton, ElementType.XPath);
    }

    public void discardChanges() {
        if (Utilities.elementIsVisible(discardChange)) {
            Utilities.clickElement(discardChange, ElementType.XPath);
        }
    }

    public void saveAnyways() {
        if (Utilities.elementIsVisible(saveAnyways)) {
            Utilities.clickElement(saveAnyways, ElementType.XPath);
        }
    }
}
