package automation.PestRoutes.PageObject.Billing.BillingModule;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;

import static automation.PestRoutes.Utilities.Legacy.waitVisible;

import automation.PestRoutes.Utilities.Legacy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class BillingModule extends BasePage {

    private By autoPayBalancesSubComponent = By.xpath("//p[text()='AutoPayBalances']");
    private By accountsWithCreditSubComponent = By.xpath("//p[text()='AccountsWithCredits']");
    private By collectionsSubComponent = By.xpath("//p[text()='Collections']");
    private By paymentHistorySubComponent = By.xpath("//p[text()='PaymentHistory']");
    private By paymentBatchesSubComponent = By.xpath("//p[text()='PaymentBatches']");

    // Below objects are the header menu of Billing module
    public String autoPayBalances = "//p[text() = 'AutoPay Balances']";
    public String accountsReceivable = "//p[text() = 'Accounts Receivable']";
    public String accountsWithCredit = "//p[text() = 'Accounts With Credits']";
    public String collections = "//p[text() = 'Collections']";
    public String paymentHistory = "//p[text() = 'Payment History']";
    public String paymentBatches = "//p[text() = 'Payment Batches']";
    public String consolidateInvoices = "//*[@id='billingNav']/li//p[contains(text(),'Consolidate Invoices')]";
    private By lnkConsolidateInvoices = By.xpath("//*[@id='billingNav']/li//p[contains(text(),'Consolidate Invoices')]");

    //******* Actions Objects **************
    private By dropdwnActions = By.xpath("//*[@id='consolidateInvoicesTableActions']");
    private By actionConsolidateInvoices = By.xpath("//*[@id='consolidateInvoicesAction']");
    private By dateRange = By.xpath("//input[@name='dateRange-consolidateInvoicesFilterParams']");
    private By btnRefresh = By.xpath("//*[@id='consolidateInvoicesFilterWrapper']//div[contains(text(),'Refresh')]");

    public void navigate(String needPath){
        Legacy.clickElement(needPath);
    }

    public CollectionsPage clickCollections() {
        Utilities.waitVisible(collectionsSubComponent);
        Utilities.click(collectionsSubComponent);
        return new CollectionsPage();
    }

    public void navigateTo(String needTab) {
        Legacy.waitVisible("//p[text() = '"+needTab+"']");
        Legacy.clickElement("//p[text() = '"+needTab+"']");
        Utilities.delay(500);
    }

    public void selectAllICustomerInvoicesForConsolidation(String customerName)
    {
        Legacy.scrollToElement(By.xpath("//*[@id='consolidateInvoicesTable']//span[contains(text(),'" + customerName +"')]"));
        Legacy.jsClickElement("//*[@id='consolidateInvoicesTable']//span[contains(text(),'" + customerName +"')]");
        Utilities.delay(1000);
        List<WebElement> consolidateInvoiceList = Utilities.locateAll(By.xpath("//*[@id='consolidateInvoicesTable']//span[contains(text(),'" + customerName +
                       "')]/../following-sibling::div//div[@class = 'col-1']/input"));
        for(WebElement invoice:consolidateInvoiceList)
        {
            Utilities.delay(500);
            invoice.click();
        }
    }//selectInvoiceForConsolidation()

    public void clickConsolidateInvoices(){
        Utilities.click(lnkConsolidateInvoices);
        Utilities.delay(500);
    }//clickConsolidateInvoices();

    public void clickActions(){
        Utilities.click(dropdwnActions);
        Utilities.delay(500);
    }//clickActions();

    public void clickConsolidateInvoicesAction(){
        Utilities.click(actionConsolidateInvoices);
        Utilities.delay(500);
    }//clickConsolidateInvoicesAction();

    public void clickRefresh(){
        Utilities.click(btnRefresh);
        Utilities.delay(500);
    }//clickRefresh();

    public void setDateRange(String dateRangeValue) {
        Utilities.waitVisible(dateRange,5);
        Utilities.click(dateRange);
        Legacy.waitVisible("//div[contains(@style,'block')]//li[text()='" + dateRangeValue + "']",5);
        Utilities.click(By.xpath("//div[contains(@style,'block')]//li[text()='" + dateRangeValue + "']"));
    }
}
