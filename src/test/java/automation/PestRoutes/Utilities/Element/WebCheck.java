package automation.PestRoutes.Utilities.Element;

import org.openqa.selenium.*;

import static automation.PestRoutes.Utilities.Element.WebCommon.*;
import static automation.PestRoutes.Utilities.Element.WebLocator.*;

public class WebCheck {
    public static boolean isChecked(By locator) {
        return locate(locator).isSelected();
    }

    public static boolean isPresent(By locator) {
        return countElements(locator) > 0;
    }

    public static boolean isVisible(By locator) {
        return isPresent(locator) && locate(locator).isDisplayed();
    }
}
