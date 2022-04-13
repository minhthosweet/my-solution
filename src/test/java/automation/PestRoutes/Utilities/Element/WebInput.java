package automation.PestRoutes.Utilities.Element;

import org.apache.commons.lang3.*;
import org.openqa.selenium.*;

import static automation.PestRoutes.Utilities.Element.WebCheck.*;
import static automation.PestRoutes.Utilities.Element.WebLocator.*;

public class WebInput {
    public static void type(By locator, String text, boolean clear) {
        if(clear) {
            Keys key;
            if (SystemUtils.IS_OS_MAC_OSX) {
                key = Keys.COMMAND;
            } else {
                key = Keys.CONTROL;
            }
            locate(locator).sendKeys(key, "a");
        }
        locate(locator).sendKeys(text);
        locate(locator).sendKeys(Keys.ENTER);
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
