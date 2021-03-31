package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.MerchantInfo;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.MerchantInfoTab.MarchantInfoPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.OfficeSettingsObjects;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddOrEditMerchantInfo {
    MarchantInfoPage merchant = new MarchantInfoPage();

    //***Author Aarbi
    @When("I navigate to merchant Info")
    public void navigateToMerchantInfo(){
        Header header = new Header();
        AdminMainPage admin = new AdminMainPage();
        OfficeSettingsObjects officeSettings = new OfficeSettingsObjects();
        header.navigateTo(header.adminTab);
        admin.navigateTo(admin.preferences);
        Utilities.clickElement(officeSettings.merchantInfo, Utilities.ElementType.XPath);
    }

    //***Author Aarbi
    @Then("I set cc gateway {string}")
    public void editCreditCardGateway_DefaultSettings(String needGatewayType){
        Utilities.waitUntileElementIsVisible(merchant.defaultSettingsEditButton);
        merchant.click(merchant.defaultSettingsEditButton);
        merchant.select(merchant.ccGatewaysDropdown, needGatewayType);
        merchant.click(merchant.defaultSettingsSaveButton);
    }
    //***Author Aarbi
    @Then("I set ACH gateway {string}")
    public void editACHGateway_DefaultSettings(String needGatewayType){
        Utilities.waitUntileElementIsVisible(merchant.defaultSettingsEditButton);
        merchant.click(merchant.defaultSettingsEditButton);
        merchant.select(merchant.achGatewaysDropdown, needGatewayType);
        merchant.click(merchant.defaultSettingsSaveButton);
    }
}
