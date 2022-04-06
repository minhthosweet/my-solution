package automation.PestRoutes.Controller.HeaderAndFooter;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.PageObject.Footer;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Report.AssertException;
import io.cucumber.java.en.Then;

public class OfficeValidation {
    Footer footer = new Footer();
    SchedulingTab schedulingTab;

    //**Author Aarbi
    @Then("I validate if sorted offices displayed")
    public void validateOfficesDisplayed(){
        schedulingTab = new SchedulingTab();
        String url = GetWebDriver.getCurrentUrl();
        officeValidation();
        if (url.contains("calendar")){
            schedulingTab.clickScheduleDay();
            officeValidation();
        }

    }
    //**Author Aarbi
    public void officeValidation(){
        String[] offices = {"//option[text()='Switch to:  Rubens1']", "//option[text()='Switch to: Matthews Code SLinging Test 2']",
                "//option[text()='Switch to: Test Office 1']"};
        AssertException.validateFieldEnabled(offices);
    }

}
