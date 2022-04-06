package automation.PestRoutes.PageObject.CustomerPortal;

import automation.PestRoutes.Utilities.*;
import org.openqa.selenium.By;

public class CustomerPortalHistoryTabPage extends CustomerPortalBasePage {

    private By viewDetailsButton = By.xpath("//div[@id='content']//button[text()='View Details']");
    private By serviceType = By.xpath("//div[@id='content']//h4");
    private By appointmentID = By.xpath("//div[@id='serviceHistory']//h4/a");

    public void clickViewDetailsButton(){
        Utilities.click(viewDetailsButton);
    }

    public String getServiceType() {
        return Utilities.getText(serviceType);
    }

    public String getAppointmentID(){
        return Utilities.locate(appointmentID).getAttribute("href");
    }
}
