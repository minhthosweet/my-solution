package automation.PestRoutes.PageObject.CustomerPortal;

import org.openqa.selenium.By;

public class CustomerPortalHistoryTabPage extends CustomerPortalBasePage {

    private By viewDetailsButton = By.xpath("//div[@id='content']//button[text()='View Details']");
    private By serviceType = By.xpath("//div[@id='content']//h4");
    private By appointmentID = By.xpath("//div[@id='serviceHistory']//h4/a");

    public void clickViewDetailsButton(){
        click(viewDetailsButton);
    }

    public String getServiceType() {
        return getText(serviceType);
    }

    public String getAppointmentID(){
        return find(appointmentID).getAttribute("href");
    }
}
