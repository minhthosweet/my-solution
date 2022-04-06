package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Leads.LeadsPage;
import automation.PestRoutes.PageObject.Search.SearchBox;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Data.*;
import automation.PestRoutes.Utilities.Deprecated;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import static automation.PestRoutes.Utilities.GetWebDriver.*;
import static automation.PestRoutes.Utilities.Deprecated.isVisible;
import org.openqa.selenium.WebElement;
import java.util.List;

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
    public String saveAnyways = "//div[text()='Save Anyways']";
    public String adminPageTitle ="//*[@id='accountManagement']//div/h3[contains(text(),'Customer Number')]";
    public String overviewPageTitle ="//*[@id='overviewPanel']/h3[contains(text(),'Account Overview')]";

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
    private By customerIDStatus = By.xpath("//span[@class='customerTitleSpan']");

    //Notes tab objects
    public String customerContacts_Notes = "//li[text()='Customer Contacts']";
    WebDriverWait wait = new WebDriverWait(driver, 55);

    public void clickCustomerContactsInNotesTab() {
        Deprecated.waitVisible(customerContacts_Notes);
        Deprecated.clickElement(customerContacts_Notes);
    }

    public void navigateTo(String chooseTabFromConst) {
        delay(3000);
        Deprecated.waitVisible("//li[@name= '" + chooseTabFromConst + "']");
        Deprecated.clickElement("//li[@name = '" + chooseTabFromConst + "']", true, false);
        delay(100);
    }

    public void ClickTranferButton() {
        Deprecated.clickElement(tranferButtonInDialog);
    }

    public void clickSaveButton() {
        Deprecated.clickElement(saveButton);
    }

    public void clickCustomerCardScheduleButton (){
        click(scheduleButton);
    }

    public void clickCloseButton(){
        try {
            Thread.sleep(500);
        }catch(InterruptedException e){e.printStackTrace();}
        Deprecated.waitVisible(closeButton);
        Deprecated.clickElement(closeButton);
        clickSaveChangesButton();
    }

    public void Click_X_Button() {
        Deprecated.clickElement(closeXButton);
    }

    public void clickXButton() {
        delay(500);
        click(xButton);
    }

    public void clickClose() {
        delay(3000);
        click(By.xpath(closeButton));
        clickSaveChangesButton();
    }

    //Author Aarbi
    @And("If discard changes dialog displays, I discard unsaved changes")
    public void discardChanges() {
        try{
            if (Deprecated.isVisible(discardChange)) {
                Deprecated.clickElement(discardChange);
                Thread.sleep(500);
            }
        }catch (Exception e){

        }
    }

    @And("If Remove Customer dialog displays, I confirm the remove")
    public void confirmRemoveCustomer() {
        try{
             if (Deprecated.isVisible("//span[text()='Remove Customer?']/ancestor::div//span[text() = 'Confirm Remove']")) {
                Deprecated.clickElement("//span[text()='Remove Customer?']/ancestor::div//span[text() = 'Confirm Remove']");
                Thread.sleep(1000);
            }
        }catch (Exception e){
        }
    }
    @And("If confirm customer removal")
    public void confirmCustomerRemoval() {
       List<WebElement> elementList = locateAll(By.xpath("//span[text()='Remove Customer?']/ancestor::div//span[text() = 'Confirm Remove']"));
       for(WebElement elem : elementList )
           if(elem.isDisplayed())
                elem.click();
    }//removeCustomerConfirmation()

    public void saveAnyways() {
        if (Deprecated.isVisible(saveAnyways)) {
            Deprecated.clickElement(saveAnyways);
        }
    }

    public void clickSaveChangesButton () {
        if (Utilities.isVisible(saveChangesButton)){
            click(saveChangesButton);
            delay(500);
        }
    }

    public void clickDiscardChangesButton () {
        if (isVisible(discardChange)){
            Utilities.locate(By.xpath(discardChange)).click();
            delay(3000);
        }
    }

    public void switchToCustomerCard() {
        driver.switchTo().defaultContent();
    }

    public CustomerViewDialog_InfoTab goToInfoTab() {
        acceptAlert();
        isPresent(infoTab);
        delay(3000);
        click(infoTab);
        return new CustomerViewDialog_InfoTab();
    }

    public CustomerViewDialog_SubscriptionTab goToSubscriptionTab () {
        isPresent(subscriptionTab);
        delay(3000);
        click(subscriptionTab);
        return new CustomerViewDialog_SubscriptionTab();
    }

    public LeadsPage goToLeadsTab () {
        isPresent(leadsTab);
        delay(3000);
        click(leadsTab);
        return new LeadsPage();
    }

    public BillingPage goToBillingTab(){
        isPresent(billingTab);
        delay(3000);
        click(billingTab);
        return new BillingPage();
    }

    public CustomerViewDialog_Notes goToNotesTab() {
        isPresent(notesTab);
        delay(3000);
        click(notesTab);
        return new CustomerViewDialog_Notes();
    }

    public CustomerviewDialog_AppointmentsTab goToAppointmentsTab () {
        isPresent(appointmentsTab);
        delay(3000);
        click(appointmentsTab);
        return new CustomerviewDialog_AppointmentsTab();
    }

    public RoutePageInvoicing goToInvoicesTab () {
        isPresent(invoicesTab);
        delay(3000);
        click(invoicesTab);
        return new RoutePageInvoicing();
    }

    public CustomerViewDialog_Admin goToAdminTab() {
        isPresent(adminTab);
        delay(3000);
        click(adminTab);
        return new CustomerViewDialog_Admin();
    }

    public boolean checkCustomerExist(String firstName, String lastName) {
        Header header = new Header();
        SearchBox searchBox = new SearchBox();
        boolean boolflag = false;
        header.searchCustomer_SearchField(firstName + " " + lastName);
        try {
            if (searchBox.containsInAutoCompleteSearch(firstName + " " + lastName).contains(firstName + " " + lastName)) {
                System.out.println("******** Customer found: " + firstName + " " + lastName);
                boolflag= true;
              }
        } catch (Exception e) {}
        return boolflag;
    }//checkCustomerExist()

    public String getCustomerID() {
        delay(1000);
        String customerIDNumber = getText(customerIDStatus);
        String customerID = GetData.removeFirstAndLastCharacter(customerIDNumber);
        System.out.println("Customer ID: " + customerID);
        return customerID;
    }

    public boolean isCustomerLoaded(String customerName) {
        try{
        if (Utilities.locate(By.xpath("//span[contains(@id,'id') and contains(text(),'" + customerName + "')]")).isDisplayed())
            return true;
        else
            return false;
        }catch(Exception e)
        {
            return false;
        }
    }//isCustomerLoaded()
}
