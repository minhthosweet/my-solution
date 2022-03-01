package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab;

import automation.PestRoutes.PageObject.BasePage;
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

    public By lblDefaultVaultSettings = By.xpath("//*[@id='merchantInfo']//h2[contains(text(),'Default Vault Settings')]");

    public void navigateTo(By subComponent)
    {
        Utilities.elementIsVisible(subComponent);
        click(subComponent);
        delay(500);
    }
}
