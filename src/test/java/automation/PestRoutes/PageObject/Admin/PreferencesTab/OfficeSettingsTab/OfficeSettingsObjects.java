package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.Legacy;
import automation.PestRoutes.Utilities.Utilities;
import org.openqa.selenium.By;

public class OfficeSettingsObjects extends BasePage {

    public String officeInfo = "//li[text() = 'Office Info']";
    public String accessControlProfiles = "//li[text() = 'Access Control Profiles']";
    public String billingLetterTemplates = "//li[text() = 'Billing Letter Templates']";
    public String paymentType = "//li[text() = 'Commission Deduction Types']";
    public String commissionProfiles = "//li[text() = 'Commission Profiles']";
    public String gLAccounts = "//li[text() = 'GL Accounts']";
    public String merchantInfo = "//li[text() = 'Merchant Info']";
    public String preferencesBody = "//li[text() = 'Preferences']";
    public By preferences = By.xpath("//*[@id='preferencesMenu']//li[text() = 'Preferences']");
    public String socialNetwork = "//li[text() = 'Social Network']";
    public String clockCategories = "//li[text() = 'Time Clock Categories']";
    public String timeClockSettings = "//li[text() = 'Time Clock Settings']";
    public String triggerRules = "//li[text() = 'Trigger Rules']";
    public String vendors = "//li[text() = 'Vendors']";
    public String taskCategories = "//li[text() = 'Task Categories']";

    public String authorizedContactsTitle = "//*[@id='AuthorizedContacts']//h2[contains(text(),'Authorized Contacts')]";
    public String invoiceAddressTitle = "//*[@id='officeLocation']//h2[contains(text(),'Invoice Address')]";

    public By lblDefaultVaultSettings = By.xpath("//*[@id='merchantInfo']//h2[contains(text(),'Default Vault Settings')]");
    public By lblPageTitle = By.xpath("//*[@id='newPreferenceBody']//h2[contains(text(),'Office Settings')]");

    public By lnkAddressEdit = By.xpath("//*[@id='officeLocation']//div[text()='edit']");
    public By lnkAddressCancel = By.xpath("//*[@id='officeLocation']//div[text()='cancel']");
    public By lnkAddressSave = By.xpath("//*[@id='officeLocation']//div[text()='save']");

    public By physicalStreetAddr = By.xpath("//*[@id='officeLocation']//input[@name ='address']");
    public By physicalCity = By.xpath("//*[@id='officeLocation']//input[@name ='city']");
    public By physicalState = By.xpath("//*[@id='officeLocation']//select[@name='state']");
    public By physicalZipCode = By.xpath("//*[@id='officeLocation']//input[@name ='zip']");
    public By physicalCountry = By.xpath("//*[@id='officeLocation']//select[@name='country']");


    public void navigateTo(By subComponent)
    {
        Utilities.isVisible(subComponent);
        Utilities.click(subComponent);
        Utilities.delay(500);
    }

    public void clickOfficeInfo(){
        Utilities.click(By.xpath(officeInfo));
    }//clickOfficeInfo()

    public void clickAddressEdit(){
        Utilities.click(lnkAddressEdit);
    }//clickAddressEdit()

    public void clickAddressSave(){
        Utilities.click(lnkAddressSave);
    }//clickAddressSave()

    public void clickAddressCancel(){
        Utilities.click(lnkAddressCancel);
    }//clickAddressCancel()

    /*************************** Setters ***************************/
    public void setPhysicalStreetAddr(String streetAddress){
        Legacy.type(streetAddress,physicalStreetAddr);
    }//setPhysicalStreetAddr()

    public void setPhysicalCity(String cityName){
        Legacy.type(cityName,physicalCity);
    }//setPhysicalCity()

    public void setPhysicalState(String stateName){
        Utilities.selectByText(physicalState, stateName);
    }//setPhysicalState()

    public void setPhysicalZipCode(String zipCode){
        Legacy.type(zipCode,physicalZipCode);
    }//setPhysicalZip()

    public void setPhysicalCountry(String optionCountry){
        Utilities.selectByText(physicalCountry, optionCountry);
    }//setPhysicalCountry()

    /*************************** Getters ***************************/
    public String getPhysicalZipCode(){
    // Utilities.scrollToBottomElementJS(invoiceAddressTitle);
        //click(By.xpath(invoiceAddressTitle));
        Legacy.scrollToElementJS(authorizedContactsTitle);
        Utilities.click(lnkAddressEdit);
        Legacy.scrollToElementJS(physicalZipCode);
        return Utilities.getText(physicalZipCode);
    }//getPhysicalZipCode

}
