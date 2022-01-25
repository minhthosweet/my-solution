package automation.PestRoutes.PageObject;

import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.ReportingPage.ReportingMainPage;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BasePage {
    protected static WebDriver driver;

    private By reportingComponent = By.xpath("//div[@id='reportLink']/a[text()='Reporting']");
    private By newCustomerComponent = By.xpath("//div[@id='guestNav']//a[text()='New Customer']");
    private By schedulingComponent = By.xpath("//div[@id='routeLink']/a[text()='Scheduling']");
    private By adminComponent = By.xpath("//div[@id='settingsLink']/a[text()='Admin']");
    private By customerSearchField = By.xpath("//input[@id='customerSearch']");
    private By customer = By.xpath("//span[@class='left searchName']");

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
        String selectedOption = option.getText();
        return  selectedOption;
    }

    //Retrieves the text in element's attribute
    protected String getByGetAttribute (By locator, String attributeName) { return find(locator).getAttribute(attributeName); }

    /*
    The below section is dedicated to FieldRoutes Components.
    (Search Customer, Admin, Sales, Reporting, Billing, Customers, Scheduling, Current Date, New Customer)
    The plan is to only create a handle to the Component when it's time to navigate to the Component.
     */

    public CreateCustomerDialog goToNewCustomerComponent () {
        click(newCustomerComponent);
        return new CreateCustomerDialog ();
    }

    public SchedulingTab goToSchedulingComponent () {
        click(schedulingComponent);
        return new SchedulingTab();
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

    public static void delay(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}