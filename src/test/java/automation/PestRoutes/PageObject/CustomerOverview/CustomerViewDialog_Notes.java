package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;
import org.openqa.selenium.By;

import static automation.PestRoutes.Utilities.Utilities.*;

public class CustomerViewDialog_Notes extends BasePage {
    public String sentToText = "//div[@id='contactsPanelWrapper']//div[@sentTo]";
    public String messageSent = "//div[@id='contactsPanelWrapper']//li//div[1]";
    private By logInfoMessage = By.xpath("//div[@id='contactsPanelWrapper']//p");
    private By logInfoContactType = By.xpath("//div[@id='contactsPanelWrapper']//div[@name='contactTypeName']");
    private By logInfoDateAdded = By.xpath("//div[@id='contactsPanelWrapper']//div[@name='contactTypeName']/following-sibling::div");
    private By statusInfoProcessing = By.xpath("//div[@id='contactsPanelWrapper']//div[@status='processing']");

    public String getNotesLogMessage() {
        String message = getText(logInfoMessage);
        System.out.println("Custom Message: " + message);
        return message;
    }

    public String getNotesContactType() {
        String contactType = getText(logInfoContactType);
        System.out.println("Contact Type: " + contactType);
        return contactType;
    }

    public String getNotesDateAdded() {
        String dateAdded = getText(logInfoDateAdded);
        System.out.println("Contact Date Time: " + dateAdded);
        return dateAdded;
    }

    public String getStatusInfo() {
        String status = getText(statusInfoProcessing);
        System.out.println(status);
        return status;
    }

    public String getTimeFromStatus(String date, String AM_PM) {
        String status = getText(statusInfoProcessing);

        return status.substring(
                status.indexOf(date) + 9,
                status.indexOf(" " + AM_PM));
    }
}