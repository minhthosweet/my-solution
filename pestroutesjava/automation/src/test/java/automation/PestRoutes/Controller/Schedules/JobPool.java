package automation.PestRoutes.Controller.Schedules;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.Utilities.AppData;
import io.cucumber.java.en.And;

public class JobPool extends AppData {

    Header header;
    SchedulingTab scheduleDay;

    @And("I navigate to the job pool tab")
    public void navigateToJobPool() {
        header = new Header();
        scheduleDay = new SchedulingTab();

        header.navigateTo(header.schedulingTab);
        scheduleDay.clickJobPool();

    }
}
