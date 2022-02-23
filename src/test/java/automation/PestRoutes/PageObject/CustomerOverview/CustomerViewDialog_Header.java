package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.PageObject.Leads.LeadsPage;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import io.cucumber.java.en.And;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static automation.PestRoutes.Utilities.Utilities.*;

public class CustomerViewDialog_Header extends BasePage {

    public String overviewTabInDialog = "overviewTab";
    public String infoTabInDialog = "infoTab";
    public String subscriptionTabInDialog = "subscriptionTab";
    public String leadsTabInDialog = "leadsTab";
    public String billingTabInDialog = "billingTab";
    public String notesTabInDialog = "notesTab";
    public String documentsTabInDIalog = "documentsTab";
    public String appointmentsTabInDialog = "appointmentsTab";
    public String invoicesTabInDialog = "invoicesTab";
    public String propertiesTabInDialog = "linkedPropertiesTab";
    public String unitsTabInDialog = "unitsTab";
    public String equipmentTabInDialog = "equipmentsTab";
    public String adminTabInDialog = "adminTab";
    public String structuresTabInDialog = "structuresTab";
    public String tranferButtonInDialog = "//span[text()='Transfer']";
    public String closeButton = "//div[@id= 'customerWindow']/following-sibling::div//span[text()='Close']";
    public String saveButton = "//div[@id= 'customerWindow']/following-sibling::div//span[text()='Save']";
    public String closeXButton = "//span[@id= 'ui-id-11']/parent::div/button/span";
    private By xButton = By.xpath("//span[@id='ui-id-11']/parent::div/button[@role='button']");
    public String discardChange = "//span[text()='Discard Changes']";
    public String saveAnyways = "//span[text()='Save Anyways']";
    private By saveChangesButton = By.xpath("//span[text()='Save Changes']");
    private By loadThisCustomerButton = By.xpath("//div[text()='Load This Customer Instead']");
    private By scheduleButton = By.xpath("//div[@id= 'customerWindow']/following-sibling::div//span[text()='Schedule']");

    //******************** TABS ********************
    private By infoTab = By.xpath("//li[@name='infoTab']/a[text()='Info']");
    private By subscriptionTab = By.xpath("//li[@name='subscriptionTab']/a[text()='Subscription']");
    private By leadsTab = By.xpath("//a[text()='Leads']");
    private By billingTab = By.xpath("//li[@name='billingTab']//a[text()='Billing']");
    private By notesTab = By.xpath("//li[@name='notesTab']/a[text()='Notes']");
    private By appointmentsTab = By.xpath("//a[text()='Appointments']");
    private By invoicesTab = By.xpath("//a[text()='Invoices']");
    private By adminTab = By.xpath("//li[@name='adminTab']//a[text()='Admin']");

    //Notes tab objects
    public String customerContacts_Notes = "//li[text()='Customer Contacts']";
    WebDriverWait wait = new WebDriverWait(driver, 55);

    public void clickCustomerContactsInNotesTab() {
        Utilities.waitUntileElementIsVisible(customerContacts_Notes);
        Utilities.clickElement(customerContacts_Notes, ElementType.XPath);
    }

    public void navigateTo(String chooseTabFromConst) {
        delay(800);
        Utilities.waitUntileElementIsVisible("//li[@name= '" + chooseTabFromConst + "']");
        Utilities.clickElement("//li[@name = '" + chooseTabFromConst + "']", ElementType.XPath, true, false);
        delay(100);
    }

    public void ClickTranferButton() {
        Utilities.clickElement(tranferButtonInDialog, ElementType.XPath);
    }

    public void clickSaveButton() {
        Utilities.clickElement(saveButton, ElementType.XPath);
    }

    public void clickCustomerCardScheduleButton (){
        click(scheduleButton);
    }

    public void clickCloseButton() throws InterruptedException {
        Thread.sleep(500);
        Utilities.waitUntileElementIsVisible(closeButton);
        Utilities.clickElement(closeButton, ElementType.XPath);
        clickSaveChangesButton();
    }

    public void Click_X_Button() {
        Utilities.clickElement(closeXButton, ElementType.XPath);
    }

    public void clickXButton() {
        delay(500);
        click(xButton);
    }

    //Author Aarbi
    @And("If discard changes dialog displays, I discard unsaved changes")
    public void discardChanges() {
        try{
            if (Utilities.elementIsVisible(discardChange)) {
                Utilities.clickElement(discardChange, ElementType.XPath);
                Thread.sleep(500);
            }
        }catch (Exception e){

        }
    }

    public void saveAnyways() {
        if (Utilities.elementIsVisible(saveAnyways)) {
            Utilities.clickElement(saveAnyways, ElementType.XPath);
        }
    }

    public void clickSaveChangesButton () {
        if (elementIsVisible(saveChangesButton)){
            click(saveChangesButton);
            delay(500);
        }
    }

    public void clickDiscardChangesButton () {
        if (elementIsVisible(discardChange)){
            find(By.xpath(discardChange)).click();
            delay(3000);
        }
    }

    public void switchToCustomerCard() {
        driver.switchTo().defaultContent();
    }

    public CustomerViewDialog_InfoTab goToInfoTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(infoTab));
        click(infoTab);
        return new CustomerViewDialog_InfoTab();
    }

    public CustomerViewDialog_SubscriptionTab goToSubscriptionTab () {
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionTab));
        click(subscriptionTab);
        return new CustomerViewDialog_SubscriptionTab();
    }

    public LeadsPage goToLeadsTab () {
        wait.until(ExpectedConditions.visibilityOfElementLocated(leadsTab));
        click(leadsTab);
        return new LeadsPage();
    }

    public BillingPage goToBillingTab(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingTab));
        click(billingTab);
        return new BillingPage();
    }

    public CustomerViewDialog_Notes goToNotesTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(notesTab));
        click(notesTab);
        return new CustomerViewDialog_Notes();
    }

    public CustomerviewDialog_AppointmentsTab goToAppointmentsTab () {
        wait.until(ExpectedConditions.visibilityOfElementLocated(appointmentsTab));
        click(appointmentsTab);
        return new CustomerviewDialog_AppointmentsTab();
    }

    public RoutePageInvoicing goToInvoicesTab () {
        delay(3000);
        click(invoicesTab);
        return new RoutePageInvoicing();
    }

    public CustomerViewDialog_Admin goToAdminTab() {
        delay(3000);
        click(adminTab);
        return new CustomerViewDialog_Admin();
    }
}
