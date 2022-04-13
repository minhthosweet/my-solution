package automation.PestRoutes.Utilities.Element;

import org.apache.commons.lang3.*;
import org.openqa.selenium.*;

import static automation.PestRoutes.Utilities.Element.WebCheck.*;
import static automation.PestRoutes.Utilities.Element.WebLocator.*;

public class WebInput {
    public static void type(By locator, String text, boolean clear) {
        if (clear) {
            locate(locator).sendKeys(SystemUtils.IS_OS_MAC_OSX ? Keys.COMMAND : Keys.CONTROL, "a");
        }
        locate(locator).sendKeys(text);
    }

    public static void type(By locator, Keys key) {
        locate(locator).sendKeys(key);
    }

    public static void checkBox(By locator) {
        if (!isChecked(locator)) {
            WebCommon.click(locator);
        }
    }

    public static void uncheckBox(By locator) {
        if (isChecked(locator)) {
            WebCommon.click(locator);
        }
    }
}
