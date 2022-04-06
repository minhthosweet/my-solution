package automation.PestRoutes.Utilities.Element;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;

import static automation.PestRoutes.Utilities.Element.WebLocator.*;
import static automation.PestRoutes.Utilities.GetWebDriver.*;

public class WebAction {
    static Actions actions = new Actions(driver);

    public static void scrollTo(By locator) {
        actions.moveToElement(locate(locator));
        actions.perform();
    }

    public static void doubleClick(By locator) {
        actions.doubleClick(locate(locator)).perform();
    }

    public static void hoverClick(By hover, By click) {
        actions.moveToElement(driver.findElement(hover)).moveToElement(driver.findElement(click)).click().perform();
    }
}
