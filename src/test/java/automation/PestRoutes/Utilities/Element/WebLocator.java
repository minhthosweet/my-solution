package automation.PestRoutes.Utilities.Element;

import org.openqa.selenium.*;

import java.util.*;

import static automation.PestRoutes.Utilities.GetWebDriver.*;

public class WebLocator {
    public static WebElement locate(By locator) {
        return driver.findElement(locator);
    }

    public static List<WebElement> locateAll(By locator) {
        return driver.findElements(locator);
    }
}
