package automation.PestRoutes.PageObject;

import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Billing.BillingModule.BillingModule;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.ReportingPage.ReportingMainPage;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import static automation.PestRoutes.Utilities.Utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BasePage {
    protected static WebDriver driver;

    private By newCustomerComponent = By.xpath("//div[@id='guestNav']//a[text()='New Customer']");
    private By schedulingComponent = By.xpath("//div[@id='routeLink']/a[text()='Scheduling']");
    private By billingComponent = By.xpath("//div[@id='billingLink']/a[text()='Billing']");
    private By reportingComponent = By.xpath("//div[@id='reportLink']/a[text()='Reporting']");
    private By adminComponent = By.xpath("//div[@id='settingsLink']/a[text()='Admin']");
    private By customerSearchField = By.xpath("//input[@id='customerSearch']");
    private By customer = By.xpath("//span[@class='left searchName']");
    private By officeDropDown = By.xpath("//select[@id='officeSwitcher']");
    private By tasksFooter = By.xpath("//div[@id='toggleTaskList']/p[text()='Tasks']");
    private By alertsFooter = By.xpath("//div[@id='toggleNotifications']/p[text()='Alerts']");

    public void setWebDriver (WebDriver driver) {
        BasePage.driver = driver;
    }

    protected WebElement find (By locator) {
        return driver.findElement(locator);
    }

    protected List<WebElement> findElements (By locator) {
        List<WebElement> elemList = driver.findElements(locator);
        return elemList;
    }

    protected void type (String text, By locator) {
        click(locator);
        find(locator).sendKeys(Keys.CONTROL, "a");
        find(locator).sendKeys(text);
    }

    protected void type (String text, WebElement elem) {
        elem.sendKeys(Keys.CONTROL, "a");
        elem.sendKeys(text);
        elem.sendKeys(Keys.ENTER);
    }

    protected void type (String text, By locator, String keyControl) {
        click(locator);
        find(locator).sendKeys(Keys.CONTROL, "a");
        find(locator).sendKeys(text);
        find(locator).sendKeys(Keys.CONTROL, keyControl);
    }

    protected void click (By locator) {
        find(locator).click();
    }

    protected void selectFromDropDown(String value, By locator) {
        Select findDropDown = new Select(find(locator));
        findDropDown.selectByVisibleText(value);
    }

    protected String getText (By locator) {
        return find(locator).getText();
    }

    protected String getSelectedOptionFromDropDown(By locator) {
        Select findDropDown = new Select(find(locator));
        WebElement option = findDropDown.getFirstSelectedOption();
        return option.getText();
    }

    //Retrieves the text in element's attribute
    protected String getByGetAttribute (By locator, String attributeName) { return find(locator).getAttribute(attributeName); }

    public static void delay(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    The below section is dedicated to FieldRoutes Components and Footers. They are methods available to all pages.
    (Components = New Customer, Current Date, Scheduling, Customers, Billing, Reporting, Sales, Admin, Search Customer)
    (Footers = History, Tasks, Alerts, Phone, Map Code Wizard, Marketing, News, Guides, Help Wizard, Clock, Logged In As: Office)
     */

    public CreateCustomerDialog goToNewCustomerComponent () {
        click(newCustomerComponent);
        return new CreateCustomerDialog ();
    }

    public SchedulingTab goToSchedulingComponent () {
        click(schedulingComponent);
        return new SchedulingTab();
    }

    public BillingModule goToBillingComponent(){
        click(billingComponent);
        return new BillingModule();
    }

    public ReportingMainPage goToReportingComponent () {
        click(reportingComponent);
        return new ReportingMainPage();
    }

    public AdminMainPage goToAdminComponent(){
        click(adminComponent);
        return new AdminMainPage();
    }

    public void goToCustomerSearchComponent(String customerIDorName) {
        click(customerSearchField);
        type(customerIDorName, customerSearchField);
        click(customer);
    }

    public void goToTasks() {
        click(tasksFooter);
    }

    public void goToAlerts() {
        click(alertsFooter);
    }

    public void selectOffice(String office) {
        jsClickElement(officeDropDown);
        find(By.xpath("//option[text()='" + office + "']")).click();
    }
}