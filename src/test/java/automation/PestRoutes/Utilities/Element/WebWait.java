package automation.PestRoutes.Utilities.Element;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import static automation.PestRoutes.Utilities.Element.WebLocator.*;
import static automation.PestRoutes.Utilities.GetWebDriver.*;

public class WebWait {
    public static WebDriverWait explicitWait(int timeout) {
        return new WebDriverWait(driver, timeout);
    }

    public static void delay(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (Exception e) {
        }
    }

    public static void waitVisible(By locator, int timeout) {
        explicitWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitClickable(By locator, int timeout) {
        explicitWait(timeout).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitPresent(By locator, int timeout) {
        explicitWait(timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitRefreshed(By locator, int timeout) {
        explicitWait(timeout).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(locate(locator))));
    }
}
