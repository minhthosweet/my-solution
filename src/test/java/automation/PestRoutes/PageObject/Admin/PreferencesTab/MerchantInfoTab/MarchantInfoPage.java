package automation.PestRoutes.PageObject.Admin.PreferencesTab.MerchantInfoTab;

import automation.PestRoutes.Utilities.Utilities;

public class MarchantInfoPage {

    //**Default vault settings
    public String defaultSettingsEditButton = "//form[@name='defaultSettings']/div[text()='edit']";
    public String defaultSettingsSaveButton = "//form[@name='defaultSettings']/div[text()='save']";
    public String defaultPaymentGatewayValue = "//select[@name='paymentGateway']/preceding-sibling::span[1]";
    public String ccGatewaysDropdown = "//select[@name='paymentGateway']";
    public String achGatewaysDropdown = "//select[@name='achGateway']";


    public void select(String needDropdown, String needText){
        Utilities.selectValueFromDropDownByValue(needDropdown, needText);
    }
    public void click(String needElement){
        Utilities.clickElement(needElement, Utilities.ElementType.XPath);
    }

}
