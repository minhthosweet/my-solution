package automation.PestRoutes.PageObject.RoutePage;

import automation.PestRoutes.Controller.CustomRoute.CustomRoute;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.Controller.Schedules.ScheduleAppt;
import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.Utilities.*;

import static automation.PestRoutes.Utilities.GetWebDriver.*;
import static automation.PestRoutes.Utilities.Utilities.*;

import automation.PestRoutes.Utilities.Legacy;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
    private By serviceTypeDropDown = By.xpath("//div[@id='overviewPanel']//select[@name='type']");

    CustomRoute customRoute;

    ScheduleAppt appt;
    RoutePage route;
    ValidateRenewal validateRenewal;

    public void clickButton(String chooseButton) {
        Legacy.waitVisible(chooseButton);
        Legacy.scrollToElement(chooseButton);
        Legacy.clickElement(chooseButton);
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
        Legacy.scrollToElementJS(route.addRoutesButton);
        route.clickButton(route.addRoutesButton);
        delay(3000);
        Legacy.waitVisible("//p[text()= 'Add " + insertQuantity + " Route']");
        Legacy.scrollToElementJS("//p[text()= 'Add " + insertQuantity + " Route']");
        Legacy.clickElement("//p[text()= 'Add " + insertQuantity + " Route']");
    }

    public String addRoutesByQuantity(String routeGroupName, String insertQuantity) {
        appt = new ScheduleAppt();
        validateRenewal = new ValidateRenewal();
        route = new RoutePage();

        Legacy.scrollToElementJS(route.addRoutesButton);
        route.clickButton(route.addRoutesButton);
        delay(3000);
        Legacy.waitVisible("//p[text()= 'Add " + insertQuantity + " Route']");
        Legacy.scrollToElementJS("//p[text()= 'Add " + insertQuantity + " Route']");
        Legacy.clickElement("//p[text()= 'Add " + insertQuantity + " Route']");
        return getRouteID(routeGroupName);
    }//addRoutesByQuantity()

    public void scheduleAppointment(String needRouteSlotNumber, String needTime) {
        Legacy.waitVisible("//*[@id='schedulingNotice']");
        Legacy.scrollToElement(
                "//div[@class='routes']/div[" + needRouteSlotNumber + "]"
                        + "//div[text()='" + needTime + "']/following-sibling::div");
        Legacy.waitVisible("//div[@class='routes']/div[" + needRouteSlotNumber + "]"
                + "//div[text()='" + needTime + "']/following-sibling::div");
        Legacy.jsClickElement("//div[@class='routes']/div[" + needRouteSlotNumber + "]" + "//div[text()='" + needTime
                + "']/following-sibling::div");
    }

    @And ("I add a route group if not already existing")
    public void addGroupIfNotExisting() {
        route = new RoutePage();
        try {
            WebElement elm = Legacy.locate("//h3[text() = 'TestRoutes']");
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
       return Legacy.isPresent("//h3[text() ='" + routeGroupName + "']");
    }

    @And("I add a route group")
    public String addGroup() {
        return addGroup( "TestRoutes", "TestRoutes");
    }

    public String addGroup(String routeGroupName, String grpTemplateName){
        Legacy.scrollToElementJS(addGroup);
        Legacy.clickElement(addGroup);
        Legacy.locate(groupTitle).sendKeys(routeGroupName);
        Legacy.waitVisible(groupTemplate);
        Utilities.selectByText(By.xpath(groupTemplate), grpTemplateName);
        Legacy.waitVisible(saveButton);
        Legacy.clickElement(saveButton);
        String groupID  = getGroupID(routeGroupName);
        return groupID;
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
                int elementCount = Legacy.countElements("//h3[text() = '" + routeGroupName + "']");
                for (int i = elementCount; i>0; i--) {
                    Legacy.waitVisible("//h3[text() = '" + routeGroupName + "']",2);
                    Legacy.clickElement("//h3[text() = '" + routeGroupName + "']");
                    Legacy.waitVisible("//h3[text() = '" + routeGroupName + "']/following-sibling::div[@class = 'clickToEdit']",2);
                    Legacy.clickElement("//h3[text() = '" + routeGroupName +"']/following-sibling::div[@class = 'clickToEdit']");
                    Legacy.waitVisible("//div[@id = 'editGroupDialog']/following-sibling::div[1]//span[text()='Delete']",2);
                    Legacy.clickElement("//div[@id = 'editGroupDialog']/following-sibling::div[1]//span[text()='Delete']");
                    Legacy.waitVisible("//span[text()='Delete Group?']/ancestor::div//span[text()='Delete Group']",2);
                    Legacy.clickElement("//span[text()='Delete Group?']/ancestor::div//span[text()='Delete Group']", false, true);
                    header.navigateTo(header.schedulingTab);
                    scheduleDay.addScheduleDateToProperties();
                    scheduleDay.clickScheduleDay();
                }
            }
        }
        catch (Exception e){}
    }


    public void deleteFirstRoute()  {
        Legacy.waitVisible("//div[@class='routes']//div[@groupid][1]//div[text()='Route Actions']");
        Legacy.clickElement("//div[@class='routes']//div[@groupid][1]//div[text()='Route Actions']");
        Legacy.waitVisible("//div[@class='routes']//div[@groupid][1]//div[text()='Route Actions']//following-sibling::div//p[text()='Delete Route']");
        Legacy.clickElement("//div[@class='routes']//div[@groupid][1]//div[text()='Route Actions']//following-sibling::div//p[text()='Delete Route']");
        Legacy.waitVisible("//div[@class='ui-widget-overlay ui-front']//following-sibling::div//span[text()='Delete Route']");
        Legacy.clickElement("//div[@class='ui-widget-overlay ui-front']//following-sibling::div//span[text()='Delete Route']");
    }

    public WebElement getDescription(String needText) {
        return Legacy.locate("//h3[contains (text(), '"+needText+"')]");
    }

    public void selectAvailableAppointment() {
        List<WebElement> fixedAppointments = driver.findElements(allFixedAppointments);
        if (isVisible(routeActions)) {
            for(WebElement availableAppointment : fixedAppointments) {
                if (!availableAppointment.isSelected()) {
                    Legacy.scrollToElementJS(availableAppointment);
                    availableAppointment.click();
                    break;
                }
                else {
                    addRoutesByQuantity("1");
                    Utilities.locate(allFixedAppointments).click();
                }
            }
        } else {
            addRoutesByQuantity("1");
            Utilities.locate(allFixedAppointments).click();
        }
        delay(2000);
        if(!isVisible(existingCustomerField)) {
            delay(2000);
            if (!isVisible(serviceTypeDropDown)) {
                addRoutesByQuantity("1");
                Utilities.locate(allFixedAppointments).click();
            }
        }
    }

    public void selectExistingCustomer(String customer) {
        if (isVisible(existingCustomerField)) {
            delay(1000);
            Legacy.type(customer, existingCustomerField);
            WebElement existingCustomer = Utilities.locate(By.xpath("//div[@aria-describedby='chooseCustomerDialog']//span[@class='left searchName' and contains(text(), '"+ customer +"')]"));
            existingCustomer.click();
        }
    }

    public String getGroupID(String routeGroupName){
        String groupID = Utilities.getAttribute(By.xpath("//div[ @title='" + routeGroupName + "']"),"groupid");
        return groupID;
    }//getGroupID()

    public String getRouteID(String routeGroupName){
        String groupID = getGroupID(routeGroupName);
        String routeID = Utilities.getAttribute(By.xpath("//*[@id='routesView']//div[@class='routes']/div[@groupid='" + groupID + "']"),"routeid");
        return routeID;
    }//getRouteID()


}