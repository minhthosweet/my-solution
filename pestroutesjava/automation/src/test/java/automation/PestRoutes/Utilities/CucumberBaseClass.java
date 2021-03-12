package automation.PestRoutes.Utilities;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.SignInPage.LoginPage;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CucumberBaseClass extends AppData {

    static WebDriver driver = GetWebDriver.getInstance();
    static LoginPage login;
    static CustomerViewDialog_Header customerViewDialog_header;
    static CreateNewCustomer createNewCustomer;
    static CustomerviewDialog_AppointmentsTab customerviewDialog_appointmentsTab;

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
    public static void endScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Error Image");
            customerViewDialog_header = new CustomerViewDialog_Header();
            customerviewDialog_appointmentsTab = new CustomerviewDialog_AppointmentsTab();
            createNewCustomer = new CreateNewCustomer();
            try {
                WebElement customerCard = FindElement.elementByAttribute(customerViewDialog_header.infoTabInDialog, FindElement.InputType.XPath);
                WebElement schedulingAppointment = FindElement.elementByAttribute(customerviewDialog_appointmentsTab.closeSchedulingNotice, FindElement.InputType.XPath);
                if (customerCard.isDisplayed()) {
                    customerViewDialog_header.clickCloseButton();
                }
                else if (schedulingAppointment.isDisplayed()){
                    customerviewDialog_appointmentsTab.clickCloseSchedulingNoticeButton();
                }
            }
            catch(Exception e){
            }
        }
    }
}
