package automation.PestRoutes.Utilities;

import automation.PestRoutes.Utilities.Element.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.List;

public class Utilities {
    // WebAction
    public static void hoverClick(By hover, By click) {
        WebAction.hoverClick(hover, click);
    }

    // WebAlert
    public static Alert getAlert() {
        return WebAlert.getAlert(1);
    }

    public static Alert getAlert(int timeout) {
        return WebAlert.getAlert(timeout);
    }

    public static String getAlertText(int timeout) {
        return WebAlert.getAlertText(timeout);
    }

    public static String getAlertText() {
        return WebAlert.getAlertText(1);
    }

    public static void acceptAlert(int timeout) {
        WebAlert.acceptAlert(timeout);
    }

    public static void acceptAlert() {
        WebAlert.acceptAlert(1);
    }

    public static void dismissAlert(int timeout) {
        WebAlert.dismissAlert(timeout);
    }

    public static void dismissAlert() {
        WebAlert.dismissAlert(1);
    }

    // WebCheck
    public static boolean isChecked(By locator) {
        return WebCheck.isChecked(locator);
    }

    public static boolean isPresent(By locator) {
        return WebCheck.isPresent(locator);
    }

    public static boolean isVisible(By locator) {
        return WebCheck.isVisible(locator);
    }

    // WebCommon
    public static void click(By locator) {
        WebCommon.click(locator);
    }

    public static int countElements(By locator) {
        return WebCommon.countElements(locator);
    }

    public static void switchToIframe(String identifier) {
        WebCommon.switchToIframe(identifier);
    }

    public static String getAttribute(By locator, String attribute) {
        return WebCommon.getAttribute(locator, attribute);
    }

    public static String getText(By locator) {
        return WebCommon.getText(locator);
    }

    // WebExecutor
    public static void jsClick(By locator) {
        WebExecutor.jsClick(locator);
    }

    public static String jsGetText(By locator) {
        return WebExecutor.jsGetText(locator);
    }

    public static void jsScrollTo(By locator) {
        WebExecutor.jsScrollTo(locator);
    }

    public static void jsScrollToBottom() {
        WebExecutor.jsScrollToBottom();
    }

    // WebInput
    public static void type(By locator, String text) {
        WebInput.type(locator, text, true);
    }

    public static void checkBox(By locator) {
        WebInput.checkBox(locator);
    }

    public static void uncheckBox(By locator) {
        WebInput.uncheckBox(locator);
    }

    // WebLocator
    public static WebElement locate(By locator) {
        return WebLocator.locate(locator);
    }

    public static List<WebElement> locateAll(By locator) {
        return WebLocator.locateAll(locator);
    }

    // WebSelect
    public static Select getSelect(By locator) {
        return WebSelect.getSelect(locator);
    }

    public static void selectByText(By locator, String text) {
        WebSelect.selectByText(locator, text);
    }

    public static String getFirstSelected(By locator) {
        return WebSelect.getFirstSelected(locator);
    }

    // WebWait
    public static void delay(int timeout) {
        WebWait.delay(timeout);
    }

    public static void waitVisible(By locator, int timeout) {
        WebWait.waitVisible(locator, timeout);
    }

    public static void waitVisible(By locator) {
        WebWait.waitVisible(locator, 30);
    }

    public static void waitClickable(By locator, int timeout) {
        WebWait.waitClickable(locator, timeout);
    }

    public static void waitPresent(By locator, int timeout) {
        WebWait.waitPresent(locator, timeout);
    }

    public static void waitRefreshed(By locator, int timeout) {
        WebWait.waitRefreshed(locator, timeout);
    }
}
