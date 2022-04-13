package automation.PestRoutes.Controller.Reporting.Office;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.ReportingPage.ReportingMainPage;
import automation.PestRoutes.Utilities.Legacy;
import io.cucumber.java.en.And;

public class OfficeObjects {

    Header header;
    ReportingMainPage reportingMainPage;

    public void click(String needTab) {
        Legacy.scrollToElementJS("//li[text() = '"+needTab+"']");
        Legacy.clickElement("//li[text() = '"+needTab+"']");
    }

    //Author: Aditya
    @And("I navigate to Report of type {string}")
    public void navigateToReportType(String needReportType) {
        header = new Header();
        reportingMainPage = new ReportingMainPage();
        header.navigateTo(header.reportingTab);
        reportingMainPage.navigateTo(reportingMainPage.office);
        click(needReportType);
    }
}
