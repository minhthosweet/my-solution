package automation.PestRoutes.PageObject.CustomerPortal;

import automation.PestRoutes.PageObject.BasePage;
import org.openqa.selenium.By;

public class CustomerPortalBasePage extends BasePage {

    private By welcomeBanner = By.xpath("//div[@id='welcomeBarInner']//following-sibling::div[contains(text(),' Welcome ')]");
    private By responsibleBalance = By.xpath("//h3[text()='Responsible Balance']/following::div[contains(text(),'$')]");

    public String getFirstNameFromWelcomeBanner(){
        return getText(welcomeBanner);
    }

    public String getResponsibleBalance(){
        return getText(responsibleBalance);
    }
}
