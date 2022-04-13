package automation.PestRoutes.PageObject.Billing.BillingModule;

import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Legacy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CollectionsPage extends BillingModule {

    private By advancedFiltersButton = By.xpath("//div[@id='autoPayFilterWrapper']//div[text()='Advanced Filters']");
    private By includeFlagsField = By.xpath("//label[text()='Incl. Flags']//following::li//input");
    private By refreshButton = By.xpath("//div[@id='autoPayFilterWrapper']//div[text()=' Refresh ']");
    private By nameColumnValues = By.xpath("//table[@id='collectionBatch']/tbody//td[2]");
    private By stageColumnValue = By.xpath("//table[@id='collectionBatch']/tbody//td[14]");

    public void clickAdvancedFilters() {
        Utilities.click(advancedFiltersButton);
    }

    public void typeFlagToInclude(String flag) {
        Legacy.type(flag, includeFlagsField);
    }

    public void clickRefreshButton() {
        Utilities.click(refreshButton);
    }

    public String getStageValue(String customerName) {
        List<WebElement> listOfNameValues = Utilities.locateAll(nameColumnValues);
        for (WebElement name : listOfNameValues) {
            if (name.getText().equalsIgnoreCase(customerName)) {
                return Utilities.getText(stageColumnValue);
            }
        }
        return Utilities.getText(stageColumnValue);
    }
}
