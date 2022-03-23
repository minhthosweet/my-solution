package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import org.openqa.selenium.By;

public class CustomerViewDialog_Notes extends BasePage {
    public String sentToText = "//div[@id='contactsPanelWrapper']//div[@sentTo]";
    public String messageSent = "//div[@id='contactsPanelWrapper']//li//div[1]";
    private By logInfoMessage = By.xpath("//div[@id='contactsPanelWrapper']//p");
    private By logInfoContactType = By.xpath("//div[@id='contactsPanelWrapper']//div[@name='contactTypeName']");
    private By logInfoDateAdded = By.xpath("//div[@id='contactsPanelWrapper']//div[@name='contactTypeName']/following-sibling::div");

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
}