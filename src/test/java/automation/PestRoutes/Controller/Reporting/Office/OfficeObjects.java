package automation.PestRoutes.Controller.Reporting.Office;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.ReportingPage.ReportingMainPage;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;

public class OfficeObjects {

    Header header;
    ReportingMainPage reportingMainPage;

    public void click(String needTab) {
        Utilities.scrollToElementJS("//li[text() = '"+needTab+"']");
        Utilities.clickElement("//li[text() = '"+needTab+"']", Utilities.ElementType.XPath);
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
