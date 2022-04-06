package automation.PestRoutes.Utilities.Element;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import static automation.PestRoutes.Utilities.Element.WebWait.*;

public class WebAlert {
    public static Alert getAlert(int timeout) {
        try {
            return explicitWait(timeout).until(ExpectedConditions.alertIsPresent());
        } catch (Exception e) {
            return null;
        }
    }

    public static String getAlertText(int timeout) {
        Alert alert = getAlert(timeout);
        if (alert != null) {
            return alert.getText();
        }
        return "";
    }

    public static void acceptAlert(int timeout) {
        Alert alert = getAlert(timeout);
        if (alert != null) {
            alert.accept();
        }
    }

    public static void dismissAlert(int timeout) {
        Alert alert = getAlert(timeout);
        if (alert != null) {
            alert.dismiss();
        }
    }
}
