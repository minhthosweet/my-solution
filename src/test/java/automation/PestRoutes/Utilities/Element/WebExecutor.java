package automation.PestRoutes.Utilities.Element;

import org.openqa.selenium.*;

import static automation.PestRoutes.Utilities.Element.WebLocator.*;
import static automation.PestRoutes.Utilities.GetWebDriver.*;

public class WebExecutor {
    public static JavascriptExecutor executor = (JavascriptExecutor) driver;

    public static void jsClick(By locator) {
        executor.executeScript("arguments[0].click();", locate(locator));
    }

    public static String jsGetText(By locator) {
        return (String) executor.executeScript("return jQuery(arguments[0]).text();", locate(locator));
    }

    public static void jsScrollTo(By locator) {
        executor.executeScript("arguments[0].scrollIntoView(false);", locate(locator));
    }

    public static void jsScrollToBottom() {
        executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
