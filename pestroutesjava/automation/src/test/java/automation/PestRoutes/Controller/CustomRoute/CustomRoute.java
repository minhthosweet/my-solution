package automation.PestRoutes.Controller.CustomRoute;

import automation.PestRoutes.Controller.Admin.Preferences.ServiceRelated.Service;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.PageObject.RoutePage.RouteTemplate;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class CustomRoute extends BaseClass {

    RouteTemplate routeTemplate = new RouteTemplate();
    Service service;
    ValidateRenewal validateRenewal;

    public List list = new ArrayList<String>();

    private String route_startHour = "5";
    private String route_startMinute = "30";
    private String route_endHour = "18";
    private String route_endMinute = "30";
    private String route_interval = "60";
    private String routeDescription = Utilities.generateRandomString(5);
    private String blockTimeSlotNumber = "3";
    private String blockTimeDescription = Utilities.generateRandomString(5);
    public String routeName = Utilities.generateRandomString(5);

    @Test
    @Then("I navigate to Route Templates")
    public void navigateToRouteTemplates() {
        routeTemplate.navigateToRouteTemplate();
    }

    @And("I have a route template")
    public void createRouteTemplate() throws Exception {
        service = new Service();
        validateRenewal = new ValidateRenewal();
        try {
            WebElement elm = routeTemplate.getDescription(routeName);
            if (elm.isDisplayed()) {
                routeTemplate.clickRouteTempalate(routeName);
                routeTemplate.saveRoute();
            }
        } catch (Exception e) {
            createRouteTemplates();
            generateTimeSlots(route_startHour, route_startMinute, route_endHour, route_endMinute, route_interval);
            blockTimeSlot(blockTimeSlotNumber, blockTimeDescription);
            validateIfFailureExist();
        }
    }

    @When("I create a route template")
    public void createRouteTemplates() throws Exception {
        routeTemplate.createNewRouteTemplate(routeName);
        routeTemplate.clickRouteTempalate(routeName);
        result(routeName, routeTemplate.getRouteTemplateTextValue(routeName), "Create Route",
                "Route Template Creation");
        result(routeName, routeTemplate.getTemplateNameTextValue(), "Create Route",
                "Route Template Creation");
    }

    @Then("I generate route from {string}:{string} to {string}:{string} with interval {string}")
    public void generateTimeSlots(String startHour, String startMinute, String endHour, String endMinute, String interval) throws Exception {
        routeTemplate.setStartTime(startHour, startMinute);
        routeTemplate.clickGenerate();
        routeTemplate.routeTemplateGenerateValidTimes_alertCondition();
        routeTemplate.setEndTime(endHour, endMinute);
        routeTemplate.setInterval(interval);
        routeTemplate.setDescription(routeDescription);
        routeTemplate.setStartTime(startHour, startMinute);
        routeTemplate.clickClear();
        routeTemplate.routeTemplateClear_alertCondition();
        routeTemplate.clickGenerate();
        routeTemplate.routeTemplateGenerate_alertCondition();
        routeTemplate.clickFillDescription();
        routeTemplate.clearDescription();
        routeTemplate.clickFillDescription();
        routeTemplate.saveRoute();
    }

    @And("I block route template slot number {string} with block description {string}")
    public void blockTimeSlot(String slotNumber, String blockDescription) throws InterruptedException {
        routeTemplate.navigateToRouteTemplate();
        routeTemplate.findRoute(routeName);
        routeTemplate.clickRouteTempalate(routeName);
        routeTemplate.blockSpecificTimeSlot(slotNumber);
        routeTemplate.setBlockDescription(blockDescription);
        routeTemplate.saveRoute();
    }

    @And("I delete the route template")
    public void deleteRouteTemplate() throws Exception {
        routeTemplate.clickRouteTempalate(routeName);
        routeTemplate.findRoute(routeName);
        routeTemplate.deleteRoute();
        routeTemplate.routeDelete_alertCondition();
        routeTemplate.navigateToRouteTemplate();
        routeTemplate.createNewRouteTemplate(routeName);
    }

    private void result(String expected, String actual, String stepName, String testName) {
        if (AssertException.result(expected, actual, stepName).size() > 0) {
            list.add(AssertException.result(expected, actual, stepName));
        }
        Reporter.status(stepName, expected, actual, testName);
    }

    public void validateIfFailureExist() {
        AssertException.assertFailure(list);
    }

}
