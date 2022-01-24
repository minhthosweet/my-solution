package automation.PestRoutes.PageObject.CustomerPortal;

import automation.PestRoutes.PageObject.BasePage;
import static automation.PestRoutes.Utilities.Utilities.*;

import org.openqa.selenium.By;

public class CustomerPortalBasePage extends BasePage {

    private By welcomeBanner = By.xpath("//div[@id='welcomeBarInner']//following-sibling::div[contains(text(),' Welcome ')]");
    private By responsibleBalance = By.xpath("//h3[text()='Responsible Balance']/following::div[contains(text(),'$')]");
    private By historyTab = By.xpath("//div[@id='myNavbar']//a[text()='History']");
    private By billingTab = By.xpath("//div[@id='myNavbar']//a[text()='Billing']");
    private By activeTab = By.xpath("//div[@id='myNavbar']//a[contains(@class,'active')]");

    public String getFirstNameFromWelcomeBanner(){
        return getText(welcomeBanner);
    }

    public String getResponsibleBalance(){
        return getText(responsibleBalance);
    }

    public CustomerPortalHistoryTabPage goToHistoryTab(){
        click(historyTab);
        delay(2000);
        refreshPage();
        delay(2000);
        return new CustomerPortalHistoryTabPage();
    }

    public CustomerPortalBillingTabPage goToBillingTab() {
        click(billingTab);
        delay(2000);
        refreshPage();
        delay(2000);
        return new CustomerPortalBillingTabPage();
    }

    private boolean isTabActive(By locator){
        if (find(activeTab).getText().equals(find(locator).getText())) {
            return true;
        }
        return false;
    }

    public boolean isBillingTabActive(){
        return isTabActive(billingTab);
    }
}
