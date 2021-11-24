package automation.PestRoutes.PageObject;

import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.ReportingPage.ReportingMainPage;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.Utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
    protected static WebDriver driver;


    private By reportingComponent = By.xpath("//div[@id='reportLink']/a[text()='Reporting']");
    private By newCustomerComponent = By.xpath("//div[@id='guestNav']//a[text()='New Customer']");
    private By schedulingComponent = By.xpath("//div[@id='routeLink']/a[text()='Scheduling']");

    public void setWebDriver (WebDriver driver) {
        BasePage.driver = driver;
    }

    protected WebElement find (By locator) { return driver.findElement(locator); }

    protected void type (String text, By locator) {
        find(locator).clear();
        click(locator);
        find(locator).sendKeys(text);
    }

    protected void click (By locator) {
        find(locator).click();
    }

    protected void select (String value, By locator) {
        Select dropDown = new Select(find(locator));
        dropDown.selectByVisibleText(value);
    }

    protected String getText (By locator) { return find(locator).getText(); }

    /*
    The below section is dedicated to FieldRoutes Components.
    (Admin, Sales, Reporting, Billing, Customers, Scheduling, Current Date, New Customer)
    The plan is to only create a handle to the Component when it's time to navigate to the Component.
     */

    public CreateCustomerDialog goToNewCustomerComponent () {
        click(newCustomerComponent);
        return new CreateCustomerDialog ();
    }

    public ReportingMainPage goToReportingComponent () {
        click(reportingComponent);
        return new ReportingMainPage();
    }

    public SchedulingTab goToSchedulingComponent () {
        click(schedulingComponent);
        return new SchedulingTab();
    }
}
