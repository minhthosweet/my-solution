package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static automation.PestRoutes.Utilities.Deprecated.scrollToElementJS;

public class AutoPaymentPage extends PreferencesPage {

    private By includeCustomerFlagsMultiDropDown = By.xpath("//label[text()='Include Customer Flags']/parent::div[@class='col-6']//following-sibling::div/div//ul//input");
    private By greenActionButton = By.xpath("//div[text()='+ Action']");
    private By saveTriggerButton = By.xpath("//span[text()='save']");
    private By actionDropDown = By.xpath("//div[@id='observer']//select[@name='eventObserverID']");

    public boolean typeFlagToInclude(String flagCode) {
        List<WebElement> allFlags = Utilities.locateAll(By.xpath("//div[@id='s2id_filterItem9']/ul/li/div"));
        WebElement includeCustomerFlagsMultiField = Utilities.locate(includeCustomerFlagsMultiDropDown);
        for (WebElement flag : allFlags) {
            if (flag.getText().contains(flagCode)) {
                return true;
            }
        }
        Deprecated.scrollToElementJS(includeCustomerFlagsMultiField);
        Deprecated.type(flagCode, includeCustomerFlagsMultiField);
        System.out.println("Customer Flag: " + flagCode);
        return false;
    }

    public void clickAddActionButton() {
        Utilities.isVisible(greenActionButton);
        Deprecated.scrollToElementJS(greenActionButton);
        Utilities.click(greenActionButton);
    }

    public void clickSaveButton() {
        Deprecated.scrollToElementJS(saveTriggerButton);
        Utilities.click(saveTriggerButton);
    }

    public void completeProcessAutoPaymentAction(String action) {
        Utilities.isVisible(actionDropDown);
        Utilities.delay(1000);
        Utilities.selectByText(actionDropDown, action);
    }
}
