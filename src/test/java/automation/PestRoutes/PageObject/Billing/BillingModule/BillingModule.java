package automation.PestRoutes.PageObject.Billing.BillingModule;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.Utilities;
import static automation.PestRoutes.Utilities.Utilities.waitUntileElementIsVisible;
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
        Utilities.clickElement(needPath, Utilities.ElementType.XPath);
    }

    public CollectionsPage clickCollections() {
        waitUntileElementIsVisible(collectionsSubComponent);
        click(collectionsSubComponent);
        return new CollectionsPage();
    }

    public void navigateTo(String needTab) {
        Utilities.waitUntileElementIsVisible("//p[text() = '"+needTab+"']");
        Utilities.clickElement("//p[text() = '"+needTab+"']", Utilities.ElementType.XPath);
        delay(500);
    }

    public void selectAllICustomerInvoicesForConsolidation(String customerName)
    {
        Utilities.scrollToElement(By.xpath("//*[@id='consolidateInvoicesTable']//span[contains(text(),'" + customerName +"')]"));
        Utilities.jsClickElement("//*[@id='consolidateInvoicesTable']//span[contains(text(),'" + customerName +"')]", Utilities.ElementType.XPath);
        Utilities.delay(1000);
        List<WebElement> consolidateInvoiceList = findElements(By.xpath("//*[@id='consolidateInvoicesTable']//span[contains(text(),'" + customerName +
                       "')]/../following-sibling::div//div[@class = 'col-1']/input"));
        for(WebElement invoice:consolidateInvoiceList)
        {
            delay(500);
            invoice.click();
        }
    }//selectInvoiceForConsolidation()

    public void clickConsolidateInvoices(){
        click(lnkConsolidateInvoices);
        delay(500);
    }//clickConsolidateInvoices();

    public void clickActions(){
        click(dropdwnActions);
        delay(500);
    }//clickActions();

    public void clickConsolidateInvoicesAction(){
        click(actionConsolidateInvoices);
        delay(500);
    }//clickConsolidateInvoicesAction();

    public void clickRefresh(){
        click(btnRefresh);
        delay(500);
    }//clickRefresh();

    public void setDateRange(String dateRangeValue) {
        Utilities.waitUntileElementIsVisible(dateRange,5);
        click(dateRange);
        Utilities.waitUntileElementIsVisible("//div[contains(@style,'block')]//li[text()='" + dateRangeValue + "']",5);
        click(By.xpath("//div[contains(@style,'block')]//li[text()='" + dateRangeValue + "']"));
    }
}
