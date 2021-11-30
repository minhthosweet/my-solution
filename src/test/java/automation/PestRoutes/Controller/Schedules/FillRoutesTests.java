package automation.PestRoutes.Controller.Schedules;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.PageObject.Scheduling.FillRoutesPageObjects;
import automation.PestRoutes.Utilities.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class FillRoutesTests  extends BaseClass{
    Header header = new Header();
    SchedulingTab scheduler = new SchedulingTab();
    FillRoutesPageObjects fillRoutesPageObjs = new FillRoutesPageObjects();
    boolean boolRoutesToFillFlag= false;
    boolean boolUnscheduledStopsFlag= false;

    @When("I navigate to the Scheduling Tab and access the Fill Routes sub Tab")
    public void navigateToFillRoutesTab() throws Exception {
        header.navigateTo(header.schedulingTab);
        scheduler.clickFillRoutesLink();
    }

    @And("I add filter values for \"Due Between\" {string} and {string} fields in \"Jobs Available\" section")
    public void addDueBetweenCriteria(String strStartDate, String strEndDate) throws Exception{
        fillRoutesPageObjs.addDueBetweenCriteria(strStartDate,strEndDate);
    }

    @And("I click the \"Refresh\" button in the \"Jobs Available\" section")
    public void clickRefreshBtnInJobsAvailableSection() throws Exception{
        fillRoutesPageObjs.clickRefreshBtn(fillRoutesPageObjs.JOBS_AVAILABLE_SECTION);
    }

    @Then("I validate unscheduled \"Routing\" count and \"Customers\" count greater than zero in \"Unscheduled Stops\" section")
    public boolean checkForUnscheduledStops() throws Exception {
        boolUnscheduledStopsFlag = fillRoutesPageObjs.unscheduledStopsCheck();
                return boolUnscheduledStopsFlag;
    }

   @And("I add filter values for \"Date Range\" {string} and {string} fields in \"Routes To Fill\" section")
    public void addDateRangeCriteria(String strStartDate, String strEndDate) throws Exception {
       fillRoutesPageObjs.addDateRangeCriteria(strStartDate, strEndDate);
    }

    @And("I click the \"Refresh\" button in the \"Routes To Fill\" section")
    public void clickRefreshBtnInRoutesToFillSection() throws Exception{
        fillRoutesPageObjs.clickRefreshBtn(fillRoutesPageObjs.ROUTES_TO_FILL_SECTION);
    }

    @Then ("I validate routes-by-date groups are displayed")
    public boolean checkForRoutesToFill() throws Exception {
        boolRoutesToFillFlag = fillRoutesPageObjs.routesToFillCheck();
        return boolRoutesToFillFlag;
    }

    @Then("I click the \"Fill Routes\" button to launch the \"Fill Routes with Available Jobs\" dialog")
    public void clickFillRoutesBtn() throws Exception{
         if ((boolUnscheduledStopsFlag) && (boolRoutesToFillFlag))
             fillRoutesPageObjs.clickFillRoutesBtn();
    }

    @Then("I fill available routes with available jobs by Max Time option {string}")
    public void fillAvailableRoutes(String strMaxTimeOption) throws Exception {
        fillRoutesPageObjs.fillAvailableRoutes(strMaxTimeOption);
    }

    @And("I execute the optimizeQueue script")
    public void executeOptimizeQueue() throws Exception{
       fillRoutesPageObjs.executeOptimizeQueueScript();
    }

    @Then("I validate \"Fill Routes Review\" Page displays and save optimized routes")
    public void saveOptimizedRoutes() throws Exception{
        fillRoutesPageObjs.saveOptimizedRoutes();
    }

} // FillRoutesTests
