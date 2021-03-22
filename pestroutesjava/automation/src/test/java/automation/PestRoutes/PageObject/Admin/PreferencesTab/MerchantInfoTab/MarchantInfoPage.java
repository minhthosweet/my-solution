package automation.PestRoutes.PageObject.Admin.PreferencesTab.MerchantInfoTab;

public class MarchantInfoPage {
    //**Left Nav in preferences
    public String merchantInfoText = "//li[text()='Merchant Info']";

    //**Default vault settings
    public String ccGatewaysDropdown = "//select[@name='paymentGateway']";
    public String achGatewaysDropdown = "//select[@name='achGateway']";
    public String defaultVaultEditButton = "//form[@name='defaultSettings']//div[text()='edit']";
    public String defaultVaultSaveButton = "//form[@name='defaultSettings']//div[text()='save']";

}
