package automation.PestRoutes.Controller.Schedules;

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
import org.testng.annotations.Test;

public class customRoute extends BaseClass {

    RouteTemplate routeTemplate = new RouteTemplate();

    public List list = new ArrayList<String>();

    private String customRouteName = Utilities.generateRandomString(5);
    private String route_startHour = "8";
    private String route_startMinute = "10";
    private String route_endHour = "16";
    private String route_endMinute = "10";
    private String route_interval = "60";
    private String routeDescription = Utilities.generateRandomString(5);
    private String blockTimeSlotNumber = "2";

    @Test
    public void createRouteTemplate() throws Exception {
        createRouteTemplates(customRouteName);
        generateTimeSlots(route_startHour, route_startMinute, route_endHour, route_endMinute, route_interval);
        blockTimeSlot(customRouteName, blockTimeSlotNumber);
        validateIfFailureExist();
    }

    @When("I create a route with name {string}")
    public void createRouteTemplates(String routeName) throws Exception {
        routeTemplate.navigateToRouteTemplate();
        routeTemplate.createNewRouteTemplate(routeName);
        routeTemplate.findRoute(routeName);
        routeTemplate.deleteRoute();
        routeTemplate.routeDelete_alertCondition();
        routeTemplate.navigateToRouteTemplate();
        routeTemplate.createNewRouteTemplate(routeName);
        result(routeName, routeTemplate.getRouteTemplateTextValue(routeName), "Create Route",
                "Route Template Creation");
        result(routeName, routeTemplate.getTemplateNameTextValue(), "Create Route",
                "Route Template Creation");
    }

    @Then("I generate route from {string}:{string} to {string}:{string} with interval {string}")
    public void generateTimeSlots(String startHour, String startMinute, String endHour, String endMinute, String interval) throws Exception {
        routeTemplate.setStartTime(startHour, startMinute);
        routeTemplate.setEndTime(endHour, endMinute);
        routeTemplate.setInterval(interval);
        routeTemplate.setDescription(routeDescription);
        routeTemplate.clickClear();
        routeTemplate.routeTemplateClear_alertCondition();
        routeTemplate.clickGenerate();
        routeTemplate.routeTemplateGenerate_alertCondition();
        routeTemplate.clickFillDescription();
        routeTemplate.saveRoute();
    }

    @And("I block route template {string} slot number {string}")
    public void blockTimeSlot(String routeName, String slotNumber) throws InterruptedException {
        routeTemplate.navigateToRouteTemplate();
        routeTemplate.findRoute(routeName);
        routeTemplate.blockSpecificTimeSlot(slotNumber);
        routeTemplate.saveRoute();
    }

    public void validateGeneratedSlots() {

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
