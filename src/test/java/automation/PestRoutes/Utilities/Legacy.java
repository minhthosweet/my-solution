package automation.PestRoutes.Utilities;

import automation.PestRoutes.Utilities.Element.*;
import org.apache.commons.lang3.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.*;

import java.util.*;
import java.util.stream.*;

import static automation.PestRoutes.Utilities.GetWebDriver.*;

public class Legacy {
    // convoluted or dispensable
    public static boolean isTextPresent(String text) {
        try {
            driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]")).isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // isVisible(By.xpath("//*[contains(text(),'" + text + "')]"));

    public static void jsClickElement(String needAttribute) {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                WebElement attribute = locate(needAttribute);
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", attribute);
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    // waitPresent(By.xpath(needAttribute), 10);
    // waitRefreshed(By.xpath(needAttribute), 10);
    // jsClick(By.xpath(needAttribute));

    public static void jsClickElement(By locator) {
        Utilities.waitVisible(locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(locator));
    }

    public static void scrollToElement(By needXpath) {
        for (int i = 0; i < 10; i++) {
            try {
                WebElement element = driver.findElement(needXpath);
                Actions actions = new Actions(driver);
                actions.moveToElement(element);
                actions.perform();
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void scrollToElement(String needXpath) {
        for (int i = 0; i < 10; i++) {
            try {
                WebElement element = driver.findElement(By.xpath(needXpath));
                Actions actions = new Actions(driver);
                actions.moveToElement(element);
                actions.perform();
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void clickElement(String needAttribute) {
        WebElement elm = locate(needAttribute);
        try {
            if (elm.isDisplayed()) {
                scrollToElement(needAttribute);
                clickElement(needAttribute, false, false);
            }
        } catch (Exception e) {
            System.out.println("Locator not visible");
        }
    }

    public static void clickElement(String needAttribute, Boolean simple, Boolean order) {
        if (simple) {
            WebElement attribute = locate(needAttribute);
            attribute.click();
        } else {
            for (int i = 0; i < 10; i++) {
                try {
                    WebElement attribute;
                    if (order) {
                        waitVisible(needAttribute, 7);
                        List<WebElement> elements;
                        waitVisible(needAttribute, 7);
                        elements = driver.findElements(By.xpath(needAttribute));
                        attribute = elements.get(elements.size() - 1);
                    } else {
                        waitVisible(needAttribute, 7);
                        attribute = locate(needAttribute);
                    }
                    waitVisible(needAttribute, 7);
                    attribute.click();
                    break;
                } catch (Exception e) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public static void scrollToElementJS(WebElement element) {
        for (int i = 0; i < 10; i++) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -40)");
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void scrollToElementJS(String needXpath) {
        WebElement element = driver.findElement(By.xpath(needXpath));
        scrollToElementJS(element);
    }

    public static void scrollToElementJS(By needXpath) {
        WebElement element = driver.findElement(needXpath);
        scrollToElementJS(element);
    }

    public static String getElementTextValue(String needAttribute) {
        for (int i = 0; i < 10; i++) {
            try {
                waitVisible(needAttribute, 2);
                WebElement attribute = locate(needAttribute);
                return attribute.getText();
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return "";
    }

    public static List<String> getAllSelectedOptionsFromDropDown(By locator) {
        Select findDropDown = new Select(driver.findElement(locator));
        List<WebElement> allSelectedOptions = findDropDown.getAllSelectedOptions();
        return allSelectedOptions.stream().map(e->e.getText()).collect(Collectors.toList());
    }

    public static List<String> getOptionsFromDropDown(By locator) {
        Select findDropDown = new Select(driver.findElement(locator));
        List<WebElement> allOptions = findDropDown.getOptions();
        return allOptions.stream().map(e->e.getText()).collect(Collectors.toList());
    }
    // WebSelect.getSelect(locator).getOptions().stream().map(e->e.getText()).collect(Collectors.toList());

    public static void type (String text, WebElement elem) {
        elem.sendKeys(Keys.CONTROL, "a");
        elem.sendKeys(text);
        elem.sendKeys(Keys.ENTER);
    }

    public static void type (String text, By locator, String keyControl) {
        Utilities.click(locator);
        Utilities.locate(locator).sendKeys(Keys.CONTROL, "a");
        Utilities.locate(locator).sendKeys(text);
        Utilities.locate(locator).sendKeys(Keys.CONTROL, keyControl);
    }

    public static void highLight(String needElement) {
        if (SystemUtils.IS_OS_MAC_OSX) {
            WebElement ele = locate(needElement);
            Actions action = new Actions(driver);
            action.doubleClick(ele).build().perform();
        } else if (SystemUtils.IS_OS_WINDOWS) {
            locate(needElement).sendKeys(Keys.CONTROL, "a");
        }
    }

    public static void clearField(String needElement) {
        locate(needElement).clear();
    }

    // XPath use
    // WebAction
    public static void doubleClick(String XPath) {
        WebAction.doubleClick(By.xpath(XPath));
    }

    public static void hoverElement(String hover, String click) {
        WebAction.hoverClick(By.xpath(hover), By.xpath(click));
    }

    // WebCheck
    public static boolean isChecked(String XPath) {
        return WebCheck.isChecked(By.xpath(XPath));
    }

    public static boolean isPresent(String XPath) {
        return WebCheck.isPresent(By.xpath(XPath));
    }

    public static boolean isVisible(String XPath) {
        return WebCheck.isVisible(By.xpath(XPath));
    }

    // WebCommon
    public static int countElements(String XPath) {
        return WebCommon.countElements(By.xpath(XPath));
    }

    public static String getAttribute(String XPath, String attribute) {
        return WebCommon.getAttribute(By.xpath(XPath), attribute);
    }

    public static String jsGetText(String XPath) {
        return WebExecutor.jsGetText(By.xpath(XPath));
    }

    public static WebElement locate(String XPath) {
        return WebLocator.locate(By.xpath(XPath));
    }

    public static void selectByText(String XPath, String text) {
        WebSelect.selectByText(By.xpath(XPath), text);
    }

    public static void selectByIndex(String XPath, int index) {
        WebSelect.selectByIndex(By.xpath(XPath), index);
    }

    public static void waitVisible(String XPath, int timeout) {
        WebWait.waitVisible(By.xpath(XPath), timeout);
    }

    public static void waitVisible(String XPath) {
        WebWait.waitVisible(By.xpath(XPath), 30);
    }

    public static void click(String needObject){
        waitVisible(needObject);
        clickElement(needObject);
    }
}
