package automation.PestRoutes.PageObject.CustomerPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

import static automation.PestRoutes.Utilities.GetWebDriver.*;
import static automation.PestRoutes.Utilities.Utilities.*;

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
    private By facebookRecommendButton = By.xpath("//button[contains(@title,'Recommend')]");
    private By facebookShareButton = By.xpath("//a[contains(@href,'share')]");

    public String getPropertyDetails() {
        return getText(propertyDetailsSection);
    }

    public String getShareTheLoveInformation() {
        return getText(shareTheLove);
    }

    public String getServiceType() {
        return getText(serviceType);
    }

    public int numberOfServiceTypes() {
        List<WebElement> numberOfServiceTypes = driver.findElements(serviceType);
        return numberOfServiceTypes.size();
    }

    public String getMostRecentServiceSection(){
        return getText(mostRecentServiceSection);
    }

    public String getDate(){
        return getText(dateField);
    }

    public String getTechnician(){
        return getText(technicianField);
    }

    public String getInvoiceNumber(){
        return getText(invoiceField);
    }

    public String getTotalAmount(){
        return getText(totalField);
    }

    public String getBalanceAmount(){
        return getText(balanceField);
    }

    public boolean isServiceNotificationLinkDisplayed(){
        if (locate(serviceNotificationLink).isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean isInvoiceLinkDisplayed(){
        if (locate(invoiceLink).isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean isTechnicianReviewTextAreaDisplayed(){
        if (locate(technicianReviewTextArea).isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean isStarRatingsDisplayed(){
        if (locate(fiveStarRatings).isDisplayed()) {
            return true;
        }
        return false;
    }

    public String getURL() {
        String url = driver.getCurrentUrl();
        System.out.println("Portal URL: " + url);
        return url;
    }

    public String getFacebookButtonName(String button) {
        delay(2000);
        WebElement facebookFrame = locate(By.xpath("//iframe[@title='fb:like Facebook Social Plugin']"));
        driver.switchTo().frame(facebookFrame);
        switch(button) {
            case "Recommend":
                String facebookRecommendButtonName = getText(facebookRecommendButton);
                System.out.println("Facebook Button Name: " + facebookRecommendButtonName);
                return facebookRecommendButtonName;
            case "Share":
                String facebookShareButtonName = getText(facebookShareButton);
                System.out.println("Facebook Button Name: " + facebookShareButtonName);
                return facebookShareButtonName;
            default:
                return null;
        }
    }
}