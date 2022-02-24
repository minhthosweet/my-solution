package automation.PestRoutes.PageObject.RoutePage;

import automation.PestRoutes.Controller.CustomRoute.CustomRoute;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.Controller.Schedules.ScheduleAppt;
import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import static automation.PestRoutes.Utilities.Utilities.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class RoutePage extends BasePage {
    public String addRoutesButton = "//li[@id= 'addRoutesButton']";
    public String jobPoolButton = "//p[text()= 'Job Pool']";
    public String remindersButton = "//p[text()= 'Reminders']";
    public String mapButton = "//p[text()= 'Map']";
    public String printButton = "//p[text()= 'Print']";
    public String viewButton = "//p[text()= 'Views']";
    public String groupName = "//select[@id='groupSelect']/following-sibling::div//h3[text()='TestRoutes']";
    public String addGroup = "//div[@id='addGroupButton']//h3[contains (text(), 'Add Group')]";
    public String groupTitle = "//form[@id='editGroupForm']//input[@name='title']";
    public String groupTemplate = "//form[@id='editGroupForm']//select[@name='templateID']";
    public String groupTemplateName = "//form[@id='editGroupForm']//option[contains (text(), 'TestRoutes')]";
    public String saveButton = "//span[text()='Edit Group']/parent::div/following-sibling::div//span[text()='Save']";
    public String deleteButton = "//span[text()='Delete']";
    public String deleteGroup = "//span[text()='Delete Group']";
    private By allFixedAppointments = By.xpath("//div[@id='routesView']//div[text()='Schedule Fixed Appointment']");
    private By newCustomerField = By.xpath("//input[@name='newCustomer']");
    private By existingCustomerField = By.xpath("//input[@name='customer']");
    private By newCustomerButton = By.xpath("//div[@id='newCustomerAppointmentButton']");
    private By routeActions = By.xpath("//div[@id='routesView']//div[text()='Route Actions']");

    CustomRoute customRoute;

    ScheduleAppt appt;
    RoutePage route;
    ValidateRenewal validateRenewal;

    public void clickButton(String chooseButton) {
        Utilities.waitUntileElementIsVisible(chooseButton);
        Utilities.scrollToElement(chooseButton);
        Utilities.clickElement(chooseButton, ElementType.XPath);
    }

    public void addRoutesByQuantity(String insertQuantity) {
        appt = new ScheduleAppt();
        validateRenewal = new ValidateRenewal();
        route = new RoutePage();
//        try {
//            Thread.sleep(1000);
//            int total = Utilities.getElementCount(appt.routes);
//            while (total > 3) {
//                deleteFirstRoute();
//                total--;
//                validateRenewal.navigateToSchedulingTab();
//                continue;
//            }
//        } catch (Exception e) {
//            System.out.println("Exception is " + e);
//        }
        route.clickButton(route.addRoutesButton);
        Utilities.waitUntileElementIsVisible("//p[text()= 'Add " + insertQuantity + " Route']");
        Utilities.scrollToElementJS("//p[text()= 'Add " + insertQuantity + " Route']");
        Utilities.clickElement("//p[text()= 'Add " + insertQuantity + " Route']", ElementType.XPath);
    }

    public void scheduleAppointment(String needRouteSlotNumber, String needTime) {
        Utilities.waitUntileElementIsVisible("//*[@id='schedulingNotice']");
        Utilities.scrollToElement(
                "//div[@class='routes']/div[" + needRouteSlotNumber + "]"
                        + "//div[text()='" + needTime + "']/following-sibling::div");
        Utilities.waitUntileElementIsVisible("//div[@class='routes']/div[" + needRouteSlotNumber + "]"
                + "//div[text()='" + needTime + "']/following-sibling::div");
        Utilities.jsClickElement("//div[@class='routes']/div[" + needRouteSlotNumber + "]" + "//div[text()='" + needTime
                + "']/following-sibling::div", ElementType.XPath);
    }


    @And ("I add a route group if not already existing")
    public void addGroupIfNotExisting() {
        route = new RoutePage();
        try {
            WebElement elm = FindElement.elementByAttribute("//h3[text() = 'TestRoutes']", InputType.XPath);
            if (elm.isDisplayed()) {
                deleteGroup();
                addGroup();
                route.addRoutesByQuantity("1");
            }
        } catch(Exception e) {
                addGroup();
            route.addRoutesByQuantity("1");
            }
        }

    public boolean isRouteGroupPresent(String routeGroupName) {
       return Utilities.isPresent("//h3[text() ='" + routeGroupName + "']");
    }

    @And("I add a route group")
    public void addGroup() {
        addGroup( "TestRoutes");
    }

    public void addGroup(String routeGroupName){
        Utilities.scrollToElementJS(addGroup);
        Utilities.clickElement(addGroup, ElementType.XPath);
        FindElement.elementByAttribute(groupTitle, InputType.XPath).sendKeys(routeGroupName);
        Utilities.waitUntileElementIsVisible(groupTemplate);
        Utilities.waitUntileElementIsVisible(saveButton);
        Utilities.clickElement(saveButton, ElementType.XPath);
    }//addGroup()

    @Then("I delete a routing group")
    public void deleteGroup () {
        deleteGroup("TestRoutes");
    }

    public void deleteGroup (String routeGroupName) {
        Header header = new Header();
        SchedulingTab scheduleDay = new SchedulingTab();

        try {
            header.navigateTo(header.schedulingTab);
            scheduleDay.addScheduleDateToProperties();
            scheduleDay.clickScheduleDay();

            WebElement elm = getDescription(routeGroupName);
            if (elm.isDisplayed()) {
                int elementCount = Utilities.getElementCount("//h3[text() = '" + routeGroupName + "']");
                for (int i = elementCount; i>0; i--) {
                    Utilities.waitUntileElementIsVisible("//h3[text() = '" + routeGroupName + "']");
                    Utilities.clickElement("//h3[text() = '" + routeGroupName + "']", ElementType.XPath);
                    Utilities.waitUntileElementIsVisible("//h3[text() = '" + routeGroupName + "']/following-sibling::div[@class = 'clickToEdit']");
                    Utilities.clickElement("//h3[text() = '" + routeGroupName +"']/following-sibling::div[@class = 'clickToEdit']", ElementType.XPath);
                    Utilities.waitUntileElementIsVisible("//div[@id = 'editGroupDialog']/following-sibling::div[1]//span[text()='Delete']");
                    Utilities.clickElement("//div[@id = 'editGroupDialog']/following-sibling::div[1]//span[text()='Delete']", ElementType.XPath);
                    Utilities.waitUntileElementIsVisible("//span[text()='Delete Group?']/ancestor::div//span[text()='Delete Group']");
                    Utilities.clickElement("//span[text()='Delete Group?']/ancestor::div//span[text()='Delete Group']", ElementType.XPath, false, true);
                    header.navigateTo(header.schedulingTab);
                    scheduleDay.addScheduleDateToProperties();
                    scheduleDay.clickScheduleDay();
                }
            }
        }
        catch (Exception e){}
    }


    public void deleteFirstRoute()  {
        Utilities.waitUntileElementIsVisible("//div[@class='routes']//div[@groupid][1]//div[text()='Route Actions']");
        Utilities.clickElement("//div[@class='routes']//div[@groupid][1]//div[text()='Route Actions']", ElementType.XPath);
        Utilities.waitUntileElementIsVisible("//div[@class='routes']//div[@groupid][1]//div[text()='Route Actions']//following-sibling::div//p[text()='Delete Route']");
        Utilities.clickElement("//div[@class='routes']//div[@groupid][1]//div[text()='Route Actions']//following-sibling::div//p[text()='Delete Route']", ElementType.XPath);
        Utilities.waitUntileElementIsVisible("//div[@class='ui-widget-overlay ui-front']//following-sibling::div//span[text()='Delete Route']");
        Utilities.clickElement("//div[@class='ui-widget-overlay ui-front']//following-sibling::div//span[text()='Delete Route']", ElementType.XPath);
    }

    public WebElement getDescription(String needText) {
        return FindElement.elementByAttribute("//h3[contains (text(), '"+needText+"')]", InputType.XPath);
    }

    public void selectAvailableAppointment() {
        List<WebElement> fixedAppointments = driver.findElements(allFixedAppointments);
        if (elementIsVisible(routeActions)) {
            for(WebElement availableAppointment : fixedAppointments) {
                if (!availableAppointment.isSelected()) {
                    scrollToElementJS(availableAppointment);
                    availableAppointment.click();
                    break;
                }
                else {
                    addRoutesByQuantity("1");
                    find(allFixedAppointments).click();
                }
            }
        } else {
            addRoutesByQuantity("1");
            find(allFixedAppointments).click();
        }
    }

    public void selectExistingCustomer(String customer) {
        type(customer, existingCustomerField);
        WebElement existingCustomer = find(By.xpath("//div[@aria-describedby='chooseCustomerDialog']//span[@class='left searchName' and contains(text(), '"+ customer +"')]"));
        existingCustomer.click();
    }
}