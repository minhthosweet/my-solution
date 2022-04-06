package automation.PestRoutes.PageObject.ReportingPage.OfficePage;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class CustomerRestorePageObjects extends BasePage {
    //--------------------------------------------------------
    // --  DEFINE PAGE OBJECTS
    //--------------------------------------------------------
    private WebDriver incognitoBrowser;

    private By lnkCustomerRestore = By.xpath("//*[@id='oReportsMenu']/li[contains(text(),'Customer Restore')]");
    private By inputSearch = By.xpath("//*[@id='customerRestoreReportTable_filter']//input[@type='search']");
    private By btnRefresh =  By.xpath("//*[@id='refreshOfficeReport']");

    private By lblCustomerRestoreDialog = By.xpath("//*[contains(@class,'ui-dialog-titlebar')]/span[text()='Customer Restore']");
    private By msgCustomerRestoreDialog = By.xpath("//*[@id='divCustomerRestoreReportDialog']/h4");
    private By btnNo = By.xpath("//*[@id='reportsPage']//button/span[text() ='No']");
    private By btnYes= By.xpath("//*[@id='reportsPage']//button/span[text() ='Yes']");
    private By colCustomerName= By.xpath("//*[@id='customerRestoreReportTable']/thead/tr//th[contains(text(), 'Customer Name')]");

    public String colCustomerNameStr = "//*[@id='customerRestoreReportTable']/thead/tr//th[contains(text(), 'Customer Name')]";
    public String btnRefreshStr = "//*[@id='refreshOfficeReport']";

    //--------------------------------------------------------
    // -- CONSTANTS & VARIABLES
    // --------------------------------------------------------


    //--------------------------------------------------------
    // --  METHODS: Setters, Getters, Click
    //--------------------------------------------------------
    public void enterSearchValue(String searchValue){

        Utilities.click(inputSearch);
        Deprecated.type(searchValue,inputSearch);
        Utilities.locate(inputSearch).sendKeys(Keys.ENTER);
    }//enterSearchValue()

    public void clickCustomerRestoreLink(){
        Deprecated.scrollToElementJS(lnkCustomerRestore);
        Utilities.click(lnkCustomerRestore);
    }//clickCustomerRestore()

    public void clickRefreshBtn(){
        Utilities.click(btnRefresh);
    }//clickRefreshBtn()

    public void clickYesBtn(){
        Utilities.click(btnYes);
    }//clickYesBtn()

    public void clickNoBtn(){
        Utilities.click(btnNo);
    }//clickNoBtn()

    //--------------------------------------------------------
    // --  METHODS: Others
    //--------------------------------------------------------
    public void restoreCustomer(String customerFullName){
        enterSearchValue(customerFullName);
        Utilities.delay(500);
        Utilities.click(By.xpath("//*[@id='customerRestoreReportTable']/tbody/tr//button[contains(@onclick, '" + customerFullName + "')]"));
        Utilities.waitVisible(lblCustomerRestoreDialog,3);
    }//restoreCustomer()

}//CustomerRestoreTab



