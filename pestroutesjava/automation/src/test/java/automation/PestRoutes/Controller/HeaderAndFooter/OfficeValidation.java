package automation.PestRoutes.Controller.HeaderAndFooter;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.PageObject.Footer;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.Then;

public class OfficeValidation {
    Footer footer = new Footer();
    SchedulingTab schedulingTab;

    //**Author Aarbi
    @Then("I validate if sorted offices displayed")
    public void validateOfficesDisplayed(){
        schedulingTab = new SchedulingTab();
        String url = Utilities.getCurrentUrl();
        officeValidation();
        if (url.contains("calendar")){
            schedulingTab.clickScheduleDay();
            officeValidation();
        }

    }
    //**Author Aarbi
    public void officeValidation(){
        String[] offices = {"//option[text()='Switch to:  Rubens1']", "//option[text()='Switch to: Matthews Code SLinging Test 2']",
                "//option[text()='Switch to: TestPest']"};
        AssertException.validateFieldEnabled(offices);
    }

}
