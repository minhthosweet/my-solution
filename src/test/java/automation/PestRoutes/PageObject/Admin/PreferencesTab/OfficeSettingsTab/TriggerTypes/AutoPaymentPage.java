package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;

import automation.PestRoutes.Utilities.Deprecated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static automation.PestRoutes.Utilities.Deprecated.scrollToElementJS;
import static automation.PestRoutes.Utilities.Utilities.*;

public class AutoPaymentPage extends PreferencesPage {

    private By includeCustomerFlagsMultiDropDown = By.xpath("//label[text()='Include Customer Flags']/parent::div[@class='col-6']//following-sibling::div/div//ul//input");
    private By greenActionButton = By.xpath("//div[text()='+ Action']");
    private By saveTriggerButton = By.xpath("//span[text()='save']");
    private By actionDropDown = By.xpath("//div[@id='observer']//select[@name='eventObserverID']");

    public boolean typeFlagToInclude(String flagCode) {
        List<WebElement> allFlags = locateAll(By.xpath("//div[contains(@id,'s2id_filterItem')]/ul/li/div"));
        WebElement includeCustomerFlagsMultiField = locate(includeCustomerFlagsMultiDropDown);
        for (WebElement flag : allFlags) {
            if (flag.getText().contains(flagCode)) {
                return true;
            }
        }
        scrollToElementJS(includeCustomerFlagsMultiField);
        Deprecated.type(flagCode, includeCustomerFlagsMultiField);
        System.out.println("Customer Flag: " + flagCode);
        return false;
    }

    public void clickAddActionButton() {
        isVisible(greenActionButton);
        scrollToElementJS(greenActionButton);
        click(greenActionButton);
    }

    public void clickSaveButton() {
        scrollToElementJS(saveTriggerButton);
        click(saveTriggerButton);
    }

    public void completeProcessAutoPaymentAction(String action) {
        isVisible(actionDropDown);
        delay(1000);
        selectByText(actionDropDown, action);
    }
}
