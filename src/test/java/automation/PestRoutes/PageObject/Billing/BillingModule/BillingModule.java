package automation.PestRoutes.PageObject.Billing.BillingModule;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.Utilities;
import static automation.PestRoutes.Utilities.Utilities.waitUntileElementIsVisible;
import org.openqa.selenium.By;

public class BillingModule extends BasePage {

    // Below objects are the header menu of Billing module

    private By autoPayBalancesSubComponent = By.xpath("//p[text()='AutoPayBalances']");
    public String accountsReceivable = "//p[text()='AccountsReceivable']";
    private By accountsWithCreditSubComponent = By.xpath("//p[text()='AccountsWithCredits']");
    private By collectionsSubComponent = By.xpath("//p[text()='Collections']");
    private By paymentHistorySubComponent = By.xpath("//p[text()='PaymentHistory']");
    private By paymentBatchesSubComponent = By.xpath("//p[text()='PaymentBatches']");

    public void navigate(String needPath){
        Utilities.clickElement(needPath, Utilities.ElementType.XPath);
    }

    public CollectionsPage clickCollections() {
        waitUntileElementIsVisible(collectionsSubComponent);
        click(collectionsSubComponent);
        return new CollectionsPage();
    }
}
