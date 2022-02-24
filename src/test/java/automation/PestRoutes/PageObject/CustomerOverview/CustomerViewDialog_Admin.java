package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.PageObject.CustomerPortal.CustomerPortalBasePage;
import automation.PestRoutes.PageObject.CustomerPortal.CustomerPortalSummaryTabPage;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static automation.PestRoutes.Utilities.Utilities.refreshPage;
import static automation.PestRoutes.Utilities.Utilities.*;


public class CustomerViewDialog_Admin extends BasePage {

    static WebDriver driver = GetWebDriver.getInstance();
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

        Utilities.waitUntileElementIsVisible(accountStatusChange);
        Utilities.clickElement(accountStatusChange, ElementType.XPath);
        Utilities.clickElement(clickCancelCatogory, ElementType.XPath);
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
        Utilities.waitUntileElementIsVisible(accountStatusChange);
        Utilities.clickElement(accountStatusChange, ElementType.XPath);
    }

    public String getAccountStatus() {
        Utilities.scrollToElement("//span[text() = '" + Utilities.currentDate("M/dd/yy h:mm aa").toLowerCase()
                + " CDT']/parent::div/parent::li//h4/parent::div[1]");
        String text = driver
                .findElement(By.xpath("//span[text() = '" + Utilities.currentDate("M/dd/yy h:mm aa").toLowerCase()
                        + " CDT']/parent::div/parent::li//h4/parent::div[1]"))
                .getText();
        return text;
    }

    public void clickCancellationCategory() {
        Utilities.clickElement(clickCancelCatogory, ElementType.XPath);
    }

    public void cancellationCategory(int indexOfCancellationType) {
        selectValueFromDropDownByIndex("//select[@id='cancelCategory']", indexOfCancellationType);
    }

    public static void selectValueFromDropDownByIndex(String needXpath, Integer needIndex) {
        Select dropdown = new Select(driver.findElement(By.xpath(needXpath)));
        dropdown.selectByIndex(needIndex);
    }

    public void selectCancelCustomerOptions(String cancelCustomerOption) {
        Utilities.waitUntileElementIsVisible(
                "//select[@id='cancelCustomerOptions']/option[text() = '" + cancelCustomerOption + "']");
        Utilities.clickElement("//select[@id='cancelCustomerOptions']/option[text() = '" + cancelCustomerOption + "']",
                ElementType.XPath);
    }

    public void cancellationNotes() {
        Utilities.clickElement(cancelNotes, ElementType.XPath);
        FindElement.elementByAttribute(cancelNotes, InputType.XPath).sendKeys("Cancelling the customer");
    }

    public void clickFreezeAccount() {
        Utilities.clickElement(clickFreeze, ElementType.XPath);
    }

    public String getCustomerStatus() {
        return Utilities.getElementTextValue(customerStatus, ElementType.XPath);
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
        find(By.xpath(removeButton)).click();
    }

    public void clickConfirmRemoveButton() {
        elementIsVisible(confirmRemoveButton);
        find(By.xpath(confirmRemoveButton)).click();
    }
}
