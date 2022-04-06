package automation.PestRoutes.Utilities.Element;

import org.openqa.selenium.*;

import static automation.PestRoutes.Utilities.Element.WebCheck.*;
import static automation.PestRoutes.Utilities.Element.WebLocator.*;
import static automation.PestRoutes.Utilities.GetWebDriver.*;

public class WebCommon {
    public static void click(By locator) {
        locate(locator).click();
    }

    public static void switchToIframe(String identifier) {
        driver.switchTo().frame(identifier);
    }

    public static int countElements(By locator) {
        return locateAll(locator).size();
    }

    public static String getText(By locator) {
        return locate(locator).getText();
    }

    public static String getAttribute(By locator, String attribute) {
        return locate(locator).getAttribute(attribute);
    }

}