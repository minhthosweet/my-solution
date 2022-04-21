package automation.PestRoutes.PageObject.RoutePage;

import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Legacy;
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
        Legacy.waitVisible(selectAllBlock);
        Legacy.scrollToElementJS(termOfService);
        Utilities.jsScrollToBottom();
        Legacy.scrollToElementJS(newRouteTemplate_button);
        Legacy.waitVisible(newRouteTemplate_button);
        Legacy.clickElement(newRouteTemplate_button);
        Legacy.waitVisible(newRouteTemplate_name);
        Legacy.locate(newRouteTemplate_name).sendKeys(routeName);
        Legacy.waitVisible(newRouteTemplate_save);
        Legacy.clickElement(newRouteTemplate_save);
    }

    public void blockSpecificTimeSlot(String slotNumber) throws InterruptedException {
        Legacy.waitVisible("//div[text()='Time Description']/parent::div/following-sibling::div[" + slotNumber + "]//input[@class='markBlocked']");
        Legacy.clickElement("//div[text()='Time Description']/parent::div/following-sibling::div[" + slotNumber + "]//input[@class='markBlocked']");
    }

    public void clickRouteTempalate(String routeName) {
        Legacy.waitVisible("//li[text()='" + routeName + "']");
        Legacy.jsClickElement("//li[text()='" + routeName + "']");
    }

    public void deleteRoute() {
        Legacy.waitVisible(selectAllBlock);
        Legacy.scrollToElementJS(deleteCustomRoute);
        Legacy.clickElement(deleteCustomRoute);
    }

    public String getRouteTemplateTextValue(String actualRouteTemplatename) {
        return Legacy.getElementTextValue("//li[text()='" + actualRouteTemplatename + "']");
    }

    public String getTemplateNameTextValue() {
        return Legacy.getAttribute("//label[text()='Template Name: ']/following-sibling::input", "value");
    }

    public String getDescriptionTextValue() {
        return Legacy.getAttribute("//div[@id='templateSpots']//div[text()='End']/parent::div/following-sibling::div[1]/div[text()='to']/following-sibling::input[2]", "value");
    }

    public void setStartTime(String hour, String minute) throws Exception {
        Legacy.waitVisible(clickStartHour);
        Legacy.clickElement(clickStartHour);
        Legacy.waitVisible("//td[@data-hour='" + hour + "']//a[text()='" + hour + "']");
        Legacy.clickElement("//td[@data-hour='" + hour + "']//a[text()='" + hour + "']");
        Legacy.waitVisible("//td[@data-minute='" + minute + "']//a[text()='" + minute + "']");
        Legacy.clickElement("//td[@data-minute='" + minute + "']//a[text()='" + minute + "']");
    }

    public void setEndTime(String hour, String minute) {
        Legacy.waitVisible(clickEndTime);
        Legacy.clickElement(clickEndTime);
        Legacy.waitVisible("//td[@data-hour='" + hour + "']//a[text()='" + hour + "']");
        Legacy.clickElement("//td[@data-hour='" + hour + "']//a[text()='" + hour + "']");
        Legacy.waitVisible("//td[@data-minute='" + minute + "']//a[text()='" + minute + "']");
        Legacy.clickElement("//td[@data-minute='" + minute + "']//a[text()='" + minute + "']");
    }

    public void setInterval(String minutes) {
        Legacy.waitVisible(clickInterval);
        Legacy.clickElement(clickInterval);
        Legacy.waitVisible("//select[@id='generate_interval']//option[text()='" + minutes + " minutes']");
        Legacy.clickElement("//select[@id='generate_interval']//option[text()='" + minutes + " minutes']");
    }

    public void setDescription(String description) {
        Legacy.waitVisible(fillDescription);
        Legacy.locate(fillDescription).sendKeys(description);
    }

    public void clearDescription() {
        Legacy.waitVisible(fillDescription);
        Legacy.locate(fillDescription).clear();
    }

    public void clickGenerate() {
        Legacy.waitVisible(clickGenerate);
        Legacy.clickElement(clickGenerate);
    }

    public void clickClear() {
        Legacy.waitVisible(clickClear);
        Legacy.clickElement(clickClear);
    }

    public void clickFillDescription() {
        Legacy.waitVisible(clickFillDescription);
        Legacy.clickElement(clickFillDescription);
    }

    public void saveRoute() {
        Legacy.waitVisible(saveTemplate);
        Legacy.scrollToElementJS(saveTemplate);
        Legacy.clickElement(saveTemplate);
    }

    public WebElement getDescription(String needText) {
        return Legacy.locate("//li[(text()= '" + needText + "')]");
    }

    public void setBlockDescription(String blockText) {
        Legacy.waitVisible(blockDescriptionTextBox);
        Legacy.locate(blockDescriptionTextBox).sendKeys(blockText);
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
