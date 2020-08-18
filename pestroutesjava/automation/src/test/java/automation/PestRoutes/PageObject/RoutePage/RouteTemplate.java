package automation.PestRoutes.PageObject.RoutePage;

import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

public class RouteTemplate {

    Header header;
    AdminMainPage adminMainPage;

    public String newRouteTemplate_button = "//li[@id='newTemplateButton']";
    public String newRouteTemplate_name = "//input[@id='templateNameInput']";
    public String newRouteTemplate_save = "//label[text()='Template Name']/parent::div/following-sibling::div//span[text()='Save']";
    public String selectAllBlock = "//input[@id='blockSelectAll']";
    public String deleteCustomRoute = "//div[text()='Delete Template']";
    public String clickStartHour = "//input[@id='generate_start']";
    public String clickEndTime = "//input[@id='generate_end']";
    public String clickInterval = "//select[@id='generate_interval']";
    public String fillDescription = "//input[@id='fill_description']";
    public String clickGenerate = "//div[@id='generateTemplate']";
    public String clickFillDescription = "//div[text()='Fill Description']";
    public String clickClear = "//div[text()='Clear']";
    public String saveTemplate = "//div[@id='templateBuilderAction']//div[text()='Save Template']";

    public void navigateToRouteTemplate() {
        header = new Header();
        adminMainPage = new AdminMainPage();
        header.NavigateTo(header.adminTab);
        adminMainPage.navigateTo(adminMainPage.routeTemplates);
    }

    public void createNewRouteTemplate(String routeName) throws InterruptedException {
        Utilities.waitUntileElementIsVisible(selectAllBlock);
        Utilities.scrollToElementJS(newRouteTemplate_button);
        Utilities.waitUntileElementIsVisible(newRouteTemplate_button);
        Utilities.clickElement(newRouteTemplate_button, Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible(newRouteTemplate_name);
        FindElement.elementByAttribute(newRouteTemplate_name, FindElement.InputType.XPath).sendKeys(routeName);
        Utilities.waitUntileElementIsVisible(newRouteTemplate_save);
        Utilities.clickElement(newRouteTemplate_save, Utilities.ElementType.XPath);
    }

    public void blockSpecificTimeSlot(String slotNumber) throws InterruptedException {
        Utilities.waitUntileElementIsVisible("//div[text()='Time Description']/parent::div/following-sibling::div[" + slotNumber + "]//input[@class='markBlocked']");
        Utilities.clickElement("//div[text()='Time Description']/parent::div/following-sibling::div[" + slotNumber + "]//input[@class='markBlocked']", Utilities.ElementType.XPath);
    }

    public void findRoute(String routeName) throws InterruptedException {
        try {
            WebElement elm = FindElement.elementByAttribute("//li[text()='" + routeName + "']/following-sibling::li[2]", FindElement.InputType.XPath);
            Utilities.waitUntileElementIsVisible("//li[text()='" + routeName + "']/following-sibling::li[2]");
            Utilities.scrollToElementJS("//li[text()='" + routeName + "']/following-sibling::li[2]");
            Utilities.scrollToElement("//li[text()='" + routeName + "']/following-sibling::li[2]");

        } catch (Exception e) {
            WebElement elm = FindElement.elementByAttribute("//li[text()='" + routeName + "']/parent::div/following-sibling::li", FindElement.InputType.XPath);
            Utilities.waitUntileElementIsVisible("//li[text()='" + routeName + "']/parent::div/following-sibling::li");
            Utilities.scrollToElementJS("//li[text()='" + routeName + "']/parent::div/following-sibling::li");
            Utilities.scrollToElement("//li[text()='" + routeName + "']/parent::div/following-sibling::li");
        }
        Utilities.clickElement("//li[text()='" + routeName + "']", Utilities.ElementType.XPath);
    }

    public void deleteRoute() {
        Utilities.waitUntileElementIsVisible(selectAllBlock);
        Utilities.clickElement(deleteCustomRoute, Utilities.ElementType.XPath);

    }

    public String getRouteTemplateTextValue(String actualRouteTemplatename) {
        return Utilities.getElementTextValue("//li[text()='" + actualRouteTemplatename + "']", Utilities.ElementType.XPath);
    }

    public String getTemplateNameTextValue() {
        return Utilities.getAttributeValue("//label[text()='Template Name: ']/following-sibling::input", "value");
    }

    public void setStartTime(String hour, String minute) {
        Utilities.waitUntileElementIsVisible(clickStartHour);
        Utilities.clickElement(clickStartHour, Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible("//td[@data-hour='" + hour + "']//a[text()='" + hour + "']");
        Utilities.clickElement("//td[@data-hour='" + hour + "']//a[text()='" + hour + "']", Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible("//td[@data-minute='" + minute + "']//a[text()='" + minute + "']");
        Utilities.clickElement("//td[@data-minute='" + minute + "']//a[text()='" + minute + "']", Utilities.ElementType.XPath);
    }

    public void setEndTime(String hour, String minute) {
        Utilities.waitUntileElementIsVisible(clickEndTime);
        Utilities.clickElement(clickEndTime, Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible("//td[@data-hour='" + hour + "']//a[text()='" + hour + "']");
        Utilities.clickElement("//td[@data-hour='" + hour + "']//a[text()='" + hour + "']", Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible("//td[@data-minute='" + minute + "']//a[text()='" + minute + "']");
        Utilities.clickElement("//td[@data-minute='" + minute + "']//a[text()='" + minute + "']", Utilities.ElementType.XPath);
    }

    public void setInterval(String minutes) {
        Utilities.waitUntileElementIsVisible(clickInterval);
        Utilities.clickElement(clickInterval, Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible("//select[@id='generate_interval']//option[text()='" + minutes + " minutes']");
        Utilities.clickElement("//select[@id='generate_interval']//option[text()='" + minutes + " minutes']", Utilities.ElementType.XPath);
    }

    public void setDescription(String description) {
        Utilities.waitUntileElementIsVisible(fillDescription);
        FindElement.elementByAttribute(fillDescription, FindElement.InputType.XPath).sendKeys(description);
    }

    public void clickGenerate() {
        Utilities.waitUntileElementIsVisible(clickGenerate);
        Utilities.clickElement(clickGenerate, Utilities.ElementType.XPath);
    }

    public void clickClear() {
        Utilities.waitUntileElementIsVisible(clickClear);
        Utilities.clickElement(clickClear, Utilities.ElementType.XPath);
    }

    public void clickFillDescription() {
        Utilities.waitUntileElementIsVisible(clickFillDescription);
        Utilities.clickElement(clickFillDescription, Utilities.ElementType.XPath);
    }

    public void saveRoute() {
        Utilities.waitUntileElementIsVisible(saveTemplate);
        Utilities.clickElement(saveTemplate, Utilities.ElementType.XPath);
    }

    public void routeDelete_alertCondition() throws Exception {
        int i = 0;
        while (i++ < 5) {
            try {
                Alert alert = Utilities.alertPopUp();
                String actionAlert = Utilities.getAlertText();
                String expected = "Are you sure you want to delete this template?";
                if (actionAlert.contains(expected)) {
                    alert.accept();
                }
                break;
            } catch (NoAlertPresentException e) {
                Thread.sleep(500);
                continue;
            }
        }
    }

    public void routeTemplateGenerate_alertCondition() throws Exception {
        int i = 0;
        while (i++ < 5) {
            try {
                Alert alert = Utilities.alertPopUp();
                String actionAlert = Utilities.getAlertText();
                String expected = "This will remove any current spots on the template and replace them with new ones. Are you sure you want to continue?";
                if (actionAlert.contains(expected)) {
                    alert.accept();
                }
                break;
            } catch (NoAlertPresentException e) {
                Thread.sleep(500);
                continue;
            }
        }
    }

    public void routeTemplateClear_alertCondition() throws Exception {
        int i = 0;
        while (i++ < 5) {
            try {
                Alert alert = Utilities.alertPopUp();
                String actionAlert = Utilities.getAlertText();
                String expected = "Are you sure you want to clear the route?";
                if (actionAlert.contains(expected)) {
                    alert.accept();
                }
                break;
            } catch (NoAlertPresentException e) {
                Thread.sleep(500);
                continue;
            }
        }
    }
}
