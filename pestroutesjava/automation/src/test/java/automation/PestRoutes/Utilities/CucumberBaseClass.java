package automation.PestRoutes.Utilities;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.SignInPage.LoginPage;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.openqa.selenium.*;

import java.util.Collection;

public class CucumberBaseClass extends AppData {

    static WebDriver driver = GetWebDriver.getInstance();
    static LoginPage login;
    static CustomerViewDialog_Header customerViewDialog_header;
    static CreateNewCustomer createNewCustomer;
    static CustomerviewDialog_AppointmentsTab customerviewDialog_appointmentsTab;
    static Scenario scenario;

    @Before(order = 1)
    public void beforeTest() {
        driver = GetWebDriver.getInstance();
    }

    @Before(order = 2)
    public static void login() throws Exception {
        try {
            int count = 0;
            if (count == 0) {
                String url = getData("url", environment);
                String userName = getData("userName", environment);
                String password = getData("password", environment);

                GetWebDriver.getInstance().get(url);
                login = new LoginPage();
                login.setUserName(userName);
                login.setPassword(password);
                login.clickLoginButton();
                count++;
            }
        } catch (Exception e) {

        }
    }

    @After
    public static void endScenario(Scenario sc) {
        if (sc.isFailed()) {
            System.out.println("Failed Scenario is :" + sc.getName());
            System.out.println("Failed tags are :" + sc.getSourceTagNames());
            byte[] screenshot = (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            sc.attach(screenshot, "image/png", "Error Image");
            customerViewDialog_header = new CustomerViewDialog_Header();
            customerviewDialog_appointmentsTab = new CustomerviewDialog_AppointmentsTab();
            createNewCustomer = new CreateNewCustomer();
            try {
                WebElement customerCard = FindElement.elementByAttribute(customerViewDialog_header.infoTabInDialog, FindElement.InputType.XPath);
                WebElement schedulingAppointment = FindElement.elementByAttribute(customerviewDialog_appointmentsTab.closeSchedulingNotice, FindElement.InputType.XPath);
                if (customerCard.isDisplayed()) {
                    customerViewDialog_header.clickCloseButton();
                } else if (schedulingAppointment.isDisplayed()) {
                    customerviewDialog_appointmentsTab.clickCloseSchedulingNoticeButton();
                }
            } catch (Exception e) {
            }
        }
    }

    @Before(order = 3)
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Before(order = 4)
    public void clickCloseSchedulingNoticeButton() {
        try {
            WebElement schedulingAppointment = FindElement.elementByAttribute(customerviewDialog_appointmentsTab.closeSchedulingNotice, FindElement.InputType.XPath);
            if (schedulingAppointment.isDisplayed()) {
                Utilities.waitUntileElementIsVisible(customerviewDialog_appointmentsTab.closeSchedulingNotice);
                Utilities.clickElement(customerviewDialog_appointmentsTab.closeSchedulingNotice, Utilities.ElementType.XPath);
                System.out.println("Schedule button closed");
            }
        } catch (Exception e) {
        }
    }
    @Given("I get scenario name")
    public static String scenarioName() {
        return scenario.getName();
    }

    @Given("I get tag name")
    public static Collection<String> tagName() {
        return scenario.getSourceTagNames();
    }
}
