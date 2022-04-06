package automation.PestRoutes.PageObject.RoutePage;

import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
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
    public String blockDescriptionTextBox = "//div[@id='templateSpots']//div[text()='End']/parent::div/following-sibling::div[2]/div[text()='to']/preceding-sibling::input[2]";
    public String termOfService = "//a[text()='Terms of Service']";

    public void navigateToRouteTemplate() throws InterruptedException {
        header = new Header();
        adminMainPage = new AdminMainPage();
        header.navigateTo(header.adminTab);
        adminMainPage.navigateTo(adminMainPage.routeTemplates);
    }

    public void createNewRouteTemplate(String routeName) throws InterruptedException {
        Deprecated.waitVisible(selectAllBlock);
        Deprecated.scrollToElementJS(termOfService);
        Utilities.jsScrollToBottom();
        Deprecated.scrollToElementJS(newRouteTemplate_button);
        Deprecated.waitVisible(newRouteTemplate_button);
        Deprecated.clickElement(newRouteTemplate_button);
        Deprecated.waitVisible(newRouteTemplate_name);
        Deprecated.locate(newRouteTemplate_name).sendKeys(routeName);
        Deprecated.waitVisible(newRouteTemplate_save);
        Deprecated.clickElement(newRouteTemplate_save);
    }

    public void blockSpecificTimeSlot(String slotNumber) throws InterruptedException {
        Deprecated.waitVisible("//div[text()='Time Description']/parent::div/following-sibling::div[" + slotNumber + "]//input[@class='markBlocked']");
        Deprecated.clickElement("//div[text()='Time Description']/parent::div/following-sibling::div[" + slotNumber + "]//input[@class='markBlocked']");
    }

    public void clickRouteTempalate(String routeName) {
        Deprecated.waitVisible("//li[text()='" + routeName + "']");
        Deprecated.jsClickElement("//li[text()='" + routeName + "']");
    }

    public void deleteRoute() {
        Deprecated.waitVisible(selectAllBlock);
        Deprecated.scrollToElementJS(deleteCustomRoute);
        Deprecated.clickElement(deleteCustomRoute);
    }

    public String getRouteTemplateTextValue(String actualRouteTemplatename) {
        return Deprecated.getElementTextValue("//li[text()='" + actualRouteTemplatename + "']");
    }

    public String getTemplateNameTextValue() {
        return Deprecated.getAttribute("//label[text()='Template Name: ']/following-sibling::input", "value");
    }

    public String getDescriptionTextValue() {
        return Deprecated.getAttribute("//div[@id='templateSpots']//div[text()='End']/parent::div/following-sibling::div[1]/div[text()='to']/following-sibling::input[2]", "value");
    }

    public void setStartTime(String hour, String minute) throws Exception {
        Deprecated.waitVisible(clickStartHour);
        Deprecated.clickElement(clickStartHour);
        Deprecated.waitVisible("//td[@data-hour='" + hour + "']//a[text()='" + hour + "']");
        Deprecated.clickElement("//td[@data-hour='" + hour + "']//a[text()='" + hour + "']");
        Deprecated.waitVisible("//td[@data-minute='" + minute + "']//a[text()='" + minute + "']");
        Deprecated.clickElement("//td[@data-minute='" + minute + "']//a[text()='" + minute + "']");
    }

    public void setEndTime(String hour, String minute) {
        Deprecated.waitVisible(clickEndTime);
        Deprecated.clickElement(clickEndTime);
        Deprecated.waitVisible("//td[@data-hour='" + hour + "']//a[text()='" + hour + "']");
        Deprecated.clickElement("//td[@data-hour='" + hour + "']//a[text()='" + hour + "']");
        Deprecated.waitVisible("//td[@data-minute='" + minute + "']//a[text()='" + minute + "']");
        Deprecated.clickElement("//td[@data-minute='" + minute + "']//a[text()='" + minute + "']");
    }

    public void setInterval(String minutes) {
        Deprecated.waitVisible(clickInterval);
        Deprecated.clickElement(clickInterval);
        Deprecated.waitVisible("//select[@id='generate_interval']//option[text()='" + minutes + " minutes']");
        Deprecated.clickElement("//select[@id='generate_interval']//option[text()='" + minutes + " minutes']");
    }

    public void setDescription(String description) {
        Deprecated.waitVisible(fillDescription);
        Deprecated.locate(fillDescription).sendKeys(description);
    }

    public void clearDescription() {
        Deprecated.waitVisible(fillDescription);
        Deprecated.locate(fillDescription).clear();
    }

    public void clickGenerate() {
        Deprecated.waitVisible(clickGenerate);
        Deprecated.clickElement(clickGenerate);
    }

    public void clickClear() {
        Deprecated.waitVisible(clickClear);
        Deprecated.clickElement(clickClear);
    }

    public void clickFillDescription() {
        Deprecated.waitVisible(clickFillDescription);
        Deprecated.clickElement(clickFillDescription);
    }

    public void saveRoute() {
        Deprecated.waitVisible(saveTemplate);
        Deprecated.scrollToElementJS(saveTemplate);
        Deprecated.clickElement(saveTemplate);
    }

    public WebElement getDescription(String needText) {
        return Deprecated.locate("//li[(text()= '" + needText + "')]");
    }

    public void setBlockDescription(String blockText) {
        Deprecated.waitVisible(blockDescriptionTextBox);
        Deprecated.locate(blockDescriptionTextBox).sendKeys(blockText);
    }

    public void routeDelete_alertCondition() throws Exception {
        int i = 0;
        while (i++ < 5) {
            try {
                Alert alert = Utilities.getAlert();
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
                Alert alert = Utilities.getAlert();
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
                Alert alert = Utilities.getAlert();
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

    public void routeTemplateGenerateValidTimes_alertCondition() throws Exception {
        int i = 0;
        while (i++ < 5) {
            try {
                Alert alert = Utilities.getAlert();
                String actionAlert = Utilities.getAlertText();
                String expected = "Please";
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
