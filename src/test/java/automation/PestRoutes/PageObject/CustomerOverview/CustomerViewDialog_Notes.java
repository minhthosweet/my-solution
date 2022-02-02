package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import org.openqa.selenium.By;

public class CustomerViewDialog_Notes extends BasePage {
    public String sentToText = "//div[@id='contactsPanelWrapper']//div[@sentTo]";
    public String messageSent = "//div[@id='contactsPanelWrapper']//li//div[1]";
    private By logInfoNotes = By.xpath("//div[@id='contactsPanelWrapper']//div[@name='contactTypeName']");

    public String getNotesLogInfo() {
        String notes = getText(logInfoNotes);
        System.out.println("Log Info Notes: " + notes);
        return getText(logInfoNotes);
    }
}