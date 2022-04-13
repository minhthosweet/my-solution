package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.PageObject.CustomerPortal.CustomerPortalBasePage;
import automation.PestRoutes.PageObject.CustomerPortal.CustomerPortalSummaryTabPage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Data.*;
import automation.PestRoutes.Utilities.Legacy;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static automation.PestRoutes.Utilities.GetWebDriver.*;
import static automation.PestRoutes.Utilities.Utilities.*;


public class CustomerViewDialog_Admin extends BasePage {
    WebDriverWait wait = new WebDriverWait(driver, 5);
    CustomerPortalBasePage customerPortalBasePage = new CustomerPortalBasePage();

    //Remove Customer
    public String removeButton = "//div[text()='Remove']";
    public String confirmRemoveButton = "//span[text()='Remove Customer?']/ancestor::div//span[text() = 'Confirm Remove']";

    public String accountStatusChange = "//div[@id='completeButton']/span";

    // Cancel Category
    public String clickCancelCatogory = "//select[@id='cancelCategory']";

    // Cancel Customer Options
    public String clickCancelCustomerOptions = "//select[@id='cancelCustomerOptions']";
    public String cancelServiceProps = "Cancel Responsible Service Subscriptions";
    public String reassignToNew = "Reassign Service Properties To New Billing Account";
    public String reassignToSelf = "Reassign Service Properties to Themselves";
    public String cancelNotes = "//textarea[@id='cancelNotes']";
    private String clickFreeze = "//span[text()='Freeze Account']";
    public String customerStatus = "//div[@id='completeButton']/div[@id='SubStatus']";

    // Customer Status
    public String customerStatus_Frozen = "Frozen";
    public String customerStatus_Active = "Active";

    // Links At The Top Near Customer Status
    private By portalLoginLink = By.xpath("//a[@id='portalLoginLink']");

    // Cancellation Options
    public String cancelResponsibleServiceSubscriptions = "Cancel Responsible Service Subscriptions";
    public String reassignServicePropertiesToNewBillingAccount = "Reassign Service Properties To New Billing Account";
    public String reassignServicePropertiestoThemselves = "Reassign Service Properties to Themselves";

    CustomerViewDialog_Header customerCardHeader;

    public void changeAccountStatus_Frozen(String cancelCustomerOption) {

        Legacy.waitVisible(accountStatusChange);
        Legacy.clickElement(accountStatusChange);
        Legacy.clickElement(clickCancelCatogory);
        clickCancellationCategory();
        cancellationCategory(2);
        selectCancelCustomerOptions(cancelCustomerOption);
        cancellationNotes();
        clickFreezeAccount();
    }

    @And("I change customer status")
    public void changeAccountStatus_Active() throws InterruptedException {
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.adminTabInDialog);
        Legacy.waitVisible(accountStatusChange);
        Legacy.clickElement(accountStatusChange);
    }

    public String getAccountStatus() {
        Legacy.scrollToElement("//span[text() = '" + GetDate.currentDate("M/dd/yy h:mm aa").toLowerCase()
                + " CDT']/parent::div/parent::li//h4/parent::div[1]");
        String text = driver
                .findElement(By.xpath("//span[text() = '" + GetDate.currentDate("M/dd/yy h:mm aa").toLowerCase()
                        + " CDT']/parent::div/parent::li//h4/parent::div[1]"))
                .getText();
        return text;
    }

    public void clickCancellationCategory() {
        Legacy.clickElement(clickCancelCatogory);
    }

    public void cancellationCategory(int indexOfCancellationType) {
        selectValueFromDropDownByIndex("//select[@id='cancelCategory']", indexOfCancellationType);
    }

    public static void selectValueFromDropDownByIndex(String needXpath, Integer needIndex) {
        Select dropdown = new Select(driver.findElement(By.xpath(needXpath)));
        dropdown.selectByIndex(needIndex);
    }

    public void selectCancelCustomerOptions(String cancelCustomerOption) {
        Legacy.waitVisible(
                "//select[@id='cancelCustomerOptions']/option[text() = '" + cancelCustomerOption + "']");
        Legacy.clickElement("//select[@id='cancelCustomerOptions']/option[text() = '" + cancelCustomerOption + "']"
        );
    }

    public void cancellationNotes() {
        Legacy.clickElement(cancelNotes);
        Legacy.locate(cancelNotes).sendKeys("Cancelling the customer");
    }

    public void clickFreezeAccount() {
        Legacy.clickElement(clickFreeze);
    }

    public String getCustomerStatus() {
        return Legacy.getElementTextValue(customerStatus);
    }

    public CustomerPortalSummaryTabPage clickPortalLogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(portalLoginLink));
        click(portalLoginLink);
        String customerCardWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String currentWindow : allWindows) {
            if(!customerCardWindow.equals(currentWindow)){
                driver.switchTo().window(currentWindow);
            }
        }
        while(!customerPortalBasePage.isSummaryTabDisplayed()) {
            refreshPage();
        }
        return new CustomerPortalSummaryTabPage();
    }

    public void clickRemoveButton() {
        Utilities.locate(By.xpath(removeButton)).click();
    }

    public void clickConfirmRemoveButton() {
        Legacy.isVisible(confirmRemoveButton);
       Utilities.locate(By.xpath(confirmRemoveButton)).click();
    }
}
