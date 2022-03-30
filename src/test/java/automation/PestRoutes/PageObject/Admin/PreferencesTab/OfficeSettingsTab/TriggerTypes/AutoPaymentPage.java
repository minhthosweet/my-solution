package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static automation.PestRoutes.Utilities.Utilities.elementIsVisible;
import static automation.PestRoutes.Utilities.Utilities.scrollToElementJS;

public class AutoPaymentPage extends PreferencesPage {

    private By includeCustomerFlagsMultiDropDown = By.xpath("//label[text()='Include Customer Flags']/parent::div[@class='col-6']//following-sibling::div/div//ul//input");
    private By greenActionButton = By.xpath("//div[text()='+ Action']");
    private By saveTriggerButton = By.xpath("//span[text()='save']");
    private By actionDropDown = By.xpath("//div[@id='observer']//select[@name='eventObserverID']");

    public boolean typeFlagToInclude(String flagCode) {
        List<WebElement> allFlags = findElements(By.xpath("//div[@id='s2id_filterItem9']/ul/li/div"));
        WebElement includeCustomerFlagsMultiField = find(includeCustomerFlagsMultiDropDown);
        for (WebElement flag : allFlags) {
            if (flag.getText().contains(flagCode)) {
                return true;
            }
        }
        scrollToElementJS(includeCustomerFlagsMultiField);
        type(flagCode, includeCustomerFlagsMultiField);
        System.out.println("Customer Flag: " + flagCode);
        return false;
    }

    public void clickAddActionButton() {
        elementIsVisible(greenActionButton);
        scrollToElementJS(greenActionButton);
        click(greenActionButton);
    }

    public void clickSaveButton() {
        scrollToElementJS(saveTriggerButton);
        click(saveTriggerButton);
    }

    public void completeProcessAutoPaymentAction(String action) {
        elementIsVisible(actionDropDown);
        delay(1000);
        selectFromDropDown(action, actionDropDown);
    }
}
