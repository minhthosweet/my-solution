package automation.PestRoutes.PageObject.CustomerPortal;

import automation.PestRoutes.PageObject.BasePage;
import static automation.PestRoutes.Utilities.Utilities.*;

import org.openqa.selenium.By;

public class CustomerPortalBasePage extends BasePage {

    private By welcomeBanner = By.xpath("//div[@id='welcomeBarInner']//following-sibling::div[contains(text(),' Welcome ')]");
    private By responsibleBalance = By.xpath("//h3[text()='Responsible Balance']/following::div[contains(text(),'$')]");
    private By summaryTab = By.xpath("//div[@id='myNavbar']//a[text()='Summary']");
    private By historyTab = By.xpath("//div[@id='myNavbar']//a[text()='History']");
    private By billingTab = By.xpath("//div[@id='myNavbar']//a[text()='Billing']");
    private By activeTab = By.xpath("//div[@id='myNavbar']//a[contains(@class,'active')]");

    public String getFirstNameFromWelcomeBanner(){
        return getText(welcomeBanner);
    }

    public String getResponsibleBalance(){
        delay(1000);
        scrollToElementJS(responsibleBalance);
        return getText(responsibleBalance);
    }

    public CustomerPortalHistoryTabPage goToHistoryTab(){
        click(historyTab);
        while(!isHistoryTabActive()) {
            refreshPage();
        }
        return new CustomerPortalHistoryTabPage();
    }

    public CustomerPortalBillingTabPage goToBillingTab() {
        click(billingTab);
        while(!isBillingTabActive()) {
            refreshPage();
        }
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

    public boolean isHistoryTabActive(){
        return isTabActive(historyTab);
    }

    public boolean isSummaryTabDisplayed() {
        return elementIsVisible(summaryTab);
    }
}
