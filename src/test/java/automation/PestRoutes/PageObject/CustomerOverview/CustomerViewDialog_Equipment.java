package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.Utilities.Legacy;

public class CustomerViewDialog_Equipment {

    public String addEquipmentButton = "//li[@onclick='addNewEquipment()']";
    public String addDescription = "//input[@name='label']";
    public String addEquipmentType = "//select[@name='equipmentType']";
    public String addEquipmentFlag = "//select[@name='equipmentGenericFlags']";
    public String addDeviceId = "//input[@name='deviceID']";
    public String addApplicationMethod = "//div[@class='col-4']/select[@name='applicationMethod']";
    public String addBarcode = "//input[@name='equipmentCode']";
    public String addEquipmentTargetIssue = "//div[@class='col-4']//select[@name='equipmentTargetIssues']";
    public String addEquipmentTargeArea = "//div[@class='col-4']/select[@name='equipmentTargetArea']";
    public String addNotes = "//textarea[@class='sis-input-gray maxW']";
    public String saveButton = "//div[@onmouseup='saveNewEquipment(this)']";
    public String equipmentAdded = "//span[@class='left full aLeft']";


    public void clickButton(String needButton) {
        Legacy.waitVisible(needButton);
        Legacy.clickElement(needButton);
    }

    public void setItem(String needItem, String needValue) {
        Legacy.locate(needItem).sendKeys(needValue);
    }

    public String getItem() {
        Legacy.waitVisible(equipmentAdded);
        return Legacy.getElementTextValue(equipmentAdded);
    }

    public void selectItemFromDropDown (String needField, String needType) {
        Legacy.selectByText(needField, needType);



    }

}
