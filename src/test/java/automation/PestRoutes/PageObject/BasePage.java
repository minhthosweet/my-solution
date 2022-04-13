package automation.PestRoutes.PageObject;

import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Billing.BillingModule.BillingModule;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.Customers.CustomersMainPage;
import automation.PestRoutes.PageObject.ReportingPage.ReportingMainPage;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;

import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Legacy;
import org.openqa.selenium.By;

public class BasePage {
    private By newCustomerComponent = By.xpath("//div[@id='guestNav']//a[text()='New Customer']");
    private By schedulingComponent = By.xpath("//div[@id='routeLink']/a[text()='Scheduling']");
    private By customersComponent = By.xpath("//div[@id='customerLink']/a[text()='Customers']");
    private By billingComponent = By.xpath("//div[@id='billingLink']/a[text()='Billing']");
    private By reportingComponent = By.xpath("//div[@id='reportLink']/a[text()='Reporting']");
    private By adminComponent = By.xpath("//div[@id='settingsLink']/a[text()='Admin']");
    private By customerSearchField = By.xpath("//input[@id='customerSearch']");
    private By customer = By.xpath("//span[@class='left searchName']");
    private By officeDropDown = By.xpath("//select[@id='officeSwitcher']");
    private By tasksFooter = By.xpath("//div[@id='toggleTaskList']/p[text()='Tasks']");
    private By alertsFooter = By.xpath("//div[@id='toggleNotifications']/p[text()='Alerts']");

    /*
    The below section is dedicated to FieldRoutes Components and Footers. They are methods available to all pages.
    (Components = New Customer, Current Date, Scheduling, Customers, Billing, Reporting, Sales, Admin, Search Customer)
    (Footers = History, Tasks, Alerts, Phone, Map Code Wizard, Marketing, News, Guides, Help Wizard, Clock, Logged In As: Office)
     */

    public CreateCustomerDialog goToNewCustomerComponent () {
        Legacy.scrollToElementJS(newCustomerComponent);
        Utilities.click(newCustomerComponent);
        Utilities.delay(3000);
        return new CreateCustomerDialog ();
    }

    public SchedulingTab goToSchedulingComponent () {
        Legacy.scrollToElementJS(schedulingComponent);
        Utilities.click(schedulingComponent);
        Utilities.delay(1000);
        return new SchedulingTab();
    }

    public CustomersMainPage goToCustomersComponent() {
        Legacy.scrollToElementJS(customersComponent);
        Utilities.click(customersComponent);
        Utilities.delay(1000);
        return new CustomersMainPage();
    }

    public BillingModule goToBillingComponent(){
        Legacy.scrollToElementJS(billingComponent);
        Utilities.click(billingComponent);
        Utilities.delay(1000);
        return new BillingModule();
    }

    public ReportingMainPage goToReportingComponent () {
        Legacy.scrollToElementJS(reportingComponent);
        Utilities.click(reportingComponent);
        Utilities.delay(1000);
        return new ReportingMainPage();
    }

    public AdminMainPage goToAdminComponent(){
        Legacy.scrollToElementJS(adminComponent);
        Utilities.click(adminComponent);
        Utilities.delay(1000);
        return new AdminMainPage();
    }

    public void goToCustomerSearchComponent(String customerIDorName) {
        Legacy.scrollToElementJS(customerSearchField);
        Utilities.click(customerSearchField);
        Legacy.type(customerIDorName, customerSearchField);
        Utilities.click(customer);
        Utilities.delay(1000);
    }

    public void goToTasks() {
        Utilities.click(tasksFooter);
    }

    public void goToAlerts() {
        Utilities.click(alertsFooter);
    }

    public void selectOffice(String office) {
        Legacy.jsClickElement(officeDropDown);
        Utilities.locate(By.xpath("//option[text()='" + office + "']")).click();
    }
}