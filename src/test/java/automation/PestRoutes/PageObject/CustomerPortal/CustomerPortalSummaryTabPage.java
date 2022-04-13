package automation.PestRoutes.PageObject.CustomerPortal;

import automation.PestRoutes.Utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static automation.PestRoutes.Utilities.GetWebDriver.*;

public class CustomerPortalSummaryTabPage extends CustomerPortalBasePage {
    private By propertyDetailsSection = By.xpath("//div[@id='content']//h3[text()='Property Details']/..");
    private By shareTheLove = By.xpath("//div[@id='shareWrapper']/h3");
    private By serviceType = By.xpath("//div[@id='content']//h4");
    private By dateField = By.xpath("//div[text()='Date: ']/following-sibling::div");
    private By technicianField = By.xpath("//div[text()='Technician: ']/following-sibling::div");
    private By invoiceField = By.xpath("//div[text()='Invoice #: ']/following-sibling::div");
    private By totalField = By.xpath("//div[text()='Total: ']/following-sibling::div");
    private By balanceField = By.xpath("//div[text()='Balance: ']/following-sibling::div");
    private By mostRecentServiceSection = By.xpath("//h3[text()='Most Recent Service']/..");
    private By serviceNotificationLink = By.xpath("//a[text() ='Service Notification']");
    private By invoiceLink = By.xpath("//a[text() ='Invoice']");
    private By technicianReviewTextArea = By.xpath("//div[@id='content']//textarea[@name='feedback']");
    private By fiveStarRatings = By.xpath("//div[contains(@class,'star-rating')]");

    public String getPropertyDetails() {
        return Utilities.getText(propertyDetailsSection);
    }

    public String getShareTheLoveInformation() {
        return Utilities.getText(shareTheLove);
    }

    public String getServiceType() {
        return Utilities.getText(serviceType);
    }

    public int numberOfServiceTypes() {
        List<WebElement> numberOfServiceTypes = driver.findElements(serviceType);
        return numberOfServiceTypes.size();
    }

    public String getMostRecentServiceSection(){
        return Utilities.getText(mostRecentServiceSection);
    }

    public String getDate(){
        return Utilities.getText(dateField);
    }

    public String getTechnician(){
        return Utilities.getText(technicianField);
    }

    public String getInvoiceNumber(){
        return Utilities.getText(invoiceField);
    }

    public String getTotalAmount(){
        return Utilities.getText(totalField);
    }

    public String getBalanceAmount(){
        return Utilities.getText(balanceField);
    }

    public boolean isServiceNotificationLinkDisplayed(){
        if (Utilities.locate(serviceNotificationLink).isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean isInvoiceLinkDisplayed(){
        if (Utilities.locate(invoiceLink).isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean isTechnicianReviewTextAreaDisplayed(){
        if (Utilities.locate(technicianReviewTextArea).isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean isStarRatingsDisplayed(){
        if (Utilities.locate(fiveStarRatings).isDisplayed()) {
            return true;
        }
        return false;
    }

    public String getURL() {
        String url = driver.getCurrentUrl();
        System.out.println("Portal URL: " + url);
        return url;
    }
}