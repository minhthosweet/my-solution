package automation.PestRoutes.PageObject.RoutePage;

import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.Controller.Schedules.ScheduleAppt;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import io.cucumber.java.en.And;

public class RoutePage {
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
        try {
            Thread.sleep(1000);
            int total = Utilities.getElementCount(appt.routes);
            while (total > 3) {
                deleteFirstRoute();
                total--;
                validateRenewal.navigateToSchedulingSameDayTab();
                continue;
            }
        } catch (Exception e) {
            System.out.println("Exception is " + e);
        }
        route.clickButton(route.addRoutesButton);
        Utilities.waitUntileElementIsVisible("//p[text()= 'Add " + insertQuantity + " Route']");
        Utilities.clickElement("//p[text()= 'Add " + insertQuantity + " Route']", ElementType.XPath);
    }

    public void scheduleAppointment(String needRouteSlotNumber, String needTime) throws InterruptedException {
        Utilities.scrollToElement(
                "//div[@class='routes']/div[" + needRouteSlotNumber + "]"
                        + "//div[text()='" + needTime + "']/following-sibling::div");
        Utilities.waitUntileElementIsVisible("//div[@class='routes']/div[" + needRouteSlotNumber + "]"
                + "//div[text()='" + needTime + "']/following-sibling::div");
        Thread.sleep(3000);
        Utilities.clickElement("//div[@class='routes']/div[" + needRouteSlotNumber + "]" + "//div[text()='" + needTime
                + "']/following-sibling::div", ElementType.XPath);
    }

    @And("I add a route group")
    public void addGroup() {
        String groupXpath = "//h3[text()= 'TestRoutes']/parent::div";
        String group = "groupButton";
        try {
            if (Utilities.getElementCount(groupName) == 0) {
                Utilities.clickElement(addGroup, ElementType.XPath);
                FindElement.elementByAttribute(groupTitle, InputType.XPath).sendKeys("TestRoutes");
                Utilities.waitUntileElementIsVisible(groupTemplate);
                Utilities.clickElement(groupTemplate, ElementType.XPath);
                Utilities.waitUntileElementIsVisible(groupTemplateName);
                Utilities.clickElement(groupTemplateName, ElementType.XPath);
                Utilities.waitUntileElementIsVisible(saveButton);
                Utilities.clickElement(saveButton, ElementType.XPath);
            } else if (Utilities.getAttributeValue(groupXpath, "class").equalsIgnoreCase(group)) {
                Utilities.clickElement(groupName, ElementType.XPath);
            }
        } catch (Exception e) {
            System.out.println("Exception is == " + e.getMessage());
        }

    }

    public void deleteFirstRoute() throws InterruptedException {
        Utilities.waitUntileElementIsVisible("//div[@class='routes']//div[@groupid][1]//div[text()='Route Actions']");
        Utilities.clickElement("//div[@class='routes']//div[@groupid][1]//div[text()='Route Actions']", ElementType.XPath);
        Utilities.waitUntileElementIsVisible("//div[@class='routes']//div[@groupid][1]//div[text()='Route Actions']//following-sibling::div//p[text()='Delete Route']");
        Utilities.clickElement("//div[@class='routes']//div[@groupid][1]//div[text()='Route Actions']//following-sibling::div//p[text()='Delete Route']", ElementType.XPath);
        Utilities.waitUntileElementIsVisible("//div[@class='ui-widget-overlay ui-front']//following-sibling::div//span[text()='Delete Route']");
        Utilities.clickElement("//div[@class='ui-widget-overlay ui-front']//following-sibling::div//span[text()='Delete Route']", ElementType.XPath);
    }

}