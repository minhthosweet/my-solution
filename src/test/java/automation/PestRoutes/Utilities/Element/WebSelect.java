package automation.PestRoutes.Utilities.Element;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import static automation.PestRoutes.Utilities.Element.WebLocator.*;


public class WebSelect {
    public static Select getSelect(By locator) {
        return new Select(locate(locator));
    }

    public static void selectByText(By locator, String text) {
        getSelect(locator).selectByVisibleText(text);
    }

    public static void selectByIndex(By locator, int index) {
        getSelect(locator).selectByIndex(index);
    }

    public static String getFirstSelected(By locator) {
        return getSelect(locator).getFirstSelectedOption().getText();
    }
}
