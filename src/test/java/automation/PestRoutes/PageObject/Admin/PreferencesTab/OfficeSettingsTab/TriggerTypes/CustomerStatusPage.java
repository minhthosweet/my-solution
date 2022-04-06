package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static automation.PestRoutes.Utilities.Deprecated.scrollToElementJS;

public class CustomerStatusPage extends PreferencesPage {

    private By statusChangedToDropDown = By.xpath("//label[text()='Status Changed To']//following::div//select[@name='filterItemValue']");
    private By whenToTriggerDropDown = By.xpath("//label[text()='When to Trigger']//following::div//select[@data-ruleitemtype='triggerWhen']");
    private By includeCustomerFlagsMultiDropDown = By.xpath("//label[text()='Include Customer Flags']/parent::div[@class='col-6']//following-sibling::div/div//ul//input");

    public void selectStatusChangedTo(String changeStatus) {
        Deprecated.scrollToElementJS(statusChangedToDropDown);
        Utilities.selectByText(statusChangedToDropDown, changeStatus);
        System.out.println("Customer Status Changed To " + changeStatus);
    }

    public void selectWhenToTrigger(String whenToTrigger) {
        Deprecated.scrollToElementJS(whenToTriggerDropDown);
        Utilities.selectByText(whenToTriggerDropDown, whenToTrigger);
    }

    public boolean typeIncludeCustomerFlag(String flagCode) {
        List<WebElement> allFlags = Utilities.locateAll(By.xpath("//label[text()='Include Customer Flags']/parent::div[@class='col-6']//following-sibling::div/div//ul//div"));
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
}
