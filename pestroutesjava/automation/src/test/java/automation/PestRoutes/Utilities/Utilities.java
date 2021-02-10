package automation.PestRoutes.Utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import io.cucumber.java.en.And;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;
import org.apache.commons.lang3.time.DateUtils;

public class Utilities {
	static WebDriver driver = GetWebDriver.getInstance();;

	public static String getAlertText() {
		return driver.switchTo().alert().getText();
	}
	
	public static void clickElementInIframe(String needXpath) {
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println(size);
		for (int i = 0; i <= size; i++) {
			driver.switchTo().frame(i);
			Utilities.waitUntileElementIsVisible(needXpath);
			int elem = driver.findElements(By.xpath(needXpath)).size();
			System.out.println(elem);
			if (elem != 0) {
				driver.findElement(By.xpath(needXpath)).click();
				break;
			} else {
				driver.switchTo().defaultContent();
				continue;
			}
		}
	}

	public static void switchToIframeByXpath(WebElement needElement) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(needXpath)));
		WebElement elm = needElement;
		driver.switchTo().frame(elm);
	}
	
	public static void switchToIframeByIndex(int needIndex) {
		driver.switchTo().frame(needIndex);
	}
	
	public static void switchBackToDom() {
		driver.switchTo().defaultContent();
	}

	public static void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public static void acceptAlertLinux() {
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {

		}
	}

	public static Alert alertPopUp() {
		return driver.switchTo().alert();
	}
	
	public static void hitEnter(String needAttributeXpath) {
		driver.findElement(By.xpath(needAttributeXpath)).sendKeys(Keys.ENTER);
	}

	public static void selectValueFromDropDownByValue(String needXpath, String needValue) {
		Select dropdown = new Select(driver.findElement(By.xpath(needXpath)));
		dropdown.selectByVisibleText(needValue);
	}

	public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {

		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);

	}

	public static void search(String needXpath, String needText){
		FindElement.elementByAttribute(needXpath, InputType.XPath).sendKeys(needText);
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

	public static void scrollToElementJS(String needXpath) {
		for (int i = 0; i < 10; i++) {
			try {
				WebElement element = driver.findElement(By.xpath(needXpath));
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

	public static void scrollToBottomElementJS(String needXpath) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath(needXpath));
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(300);
	}

	public static String generateRandomString(int needLength) {
		return RandomStringUtils.random(needLength, true, true);
	}

	public static double generateRandomInteger(int needLength) {
		double m = (double) Math.pow(10, needLength - 1);
		return m + new Random().nextInt((int) (9 * m));
	}

	public static String currentDate(String needFormat) {
		DateFormat dateFormat = new SimpleDateFormat(needFormat);
		Date date = new Date();
		String date1 = dateFormat.format(date);
		return date1;
	}

	public static String currentDateWithZeroDelimiterOnDate(String needFormat) {
		DateFormat dateFormat = new SimpleDateFormat(needFormat);
		Date date = new Date();
		String date1 = dateFormat.format(date);
		if(date1.startsWith("0") && needFormat.startsWith("d")){
			date1 = date1.substring(1,date1.length());
			if(date1.charAt(4)=='0') {
				date1 = date1.substring(0,3) + date1.substring(4, date1.length());
			}
		}
		return date1;
	}

	public static int getElementCount(String needXpath) {
		return driver.findElements(By.xpath(needXpath)).size();
	}

	public static String getAttributeValue(String needXpath, String needAttribute) {
		waitUntileElementIsVisible(needXpath);
		return driver.findElement(By.xpath(needXpath)).getAttribute(needAttribute);
	}

	public static void waitUntileElementIsVisible(String needXpath) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(needXpath)));
	}

	public static boolean elementIsVisible(String needXpath) {
		if (driver.findElements(By.xpath(needXpath)).size() > 0) {
			return driver.findElement(By.xpath(needXpath)).isDisplayed();
		} else {
			return false;
		}
	}
	
	public static void sign(WebElement needAttribute) {
		Actions builder = new Actions(driver);
		Action drawAction = builder 
	              .click(needAttribute)
	              .moveToElement(needAttribute,8,8)
	              .clickAndHold(needAttribute)
	              .moveByOffset(60, 70)
	              .moveByOffset(-120,-120)
	              .moveByOffset(70, 80)
	              .moveByOffset(-80,-80)
	              .release(needAttribute)
	              .build();
	    drawAction.perform();
	}

	public static void javaScriptClick(String needID) {
		WebElement element = driver.findElement(By.id(needID));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	private static WebElement locateElement(String needAttribute, ElementType Attribute_Type) {
		switch (Attribute_Type) {
			case ID:
				return driver.findElement(By.id(needAttribute));
			case ClassName:
				return driver.findElement(By.className(needAttribute));
			case LinkText:
				return driver.findElement(By.linkText(needAttribute));
			default:
				return driver.findElement(By.xpath(needAttribute));
		}
	}

	private static WebElement locateElements(String needAttribute, ElementType Attribute_Type) {
		List<WebElement> elements;
		switch (Attribute_Type) {
			case ID:
				elements = driver.findElements(By.id(needAttribute));
				break;
			case ClassName:
				elements = driver.findElements(By.className(needAttribute));
				break;
			case LinkText:
				elements = driver.findElements(By.linkText(needAttribute));
				break;
			default:
				elements = driver.findElements(By.xpath(needAttribute));
		}
		return elements.get(elements.size() - 1);

	}

	public static String getElementTextValue(String needAttribute, ElementType Attribute_Type) {
		for (int i = 0; i < 10; i++) {
			try {
				WebElement attribute = locateElement(needAttribute, Attribute_Type);
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

	public static void clickElement(String needAttribute, ElementType Attribute_Type) {
		scrollToElement(needAttribute);
		clickElement(needAttribute, Attribute_Type, false, false);
	}

	public static void clickElement(String needAttribute, ElementType Attribute_Type, Boolean simple, Boolean order) {
		if (simple) {
			WebElement attribute = locateElement(needAttribute, Attribute_Type);
			attribute.click();
		} else {
			for (int i = 0; i < 10; i++) {
				try {
					WebElement attribute;
					if (order) {
						attribute = locateElements(needAttribute, Attribute_Type);
					} else {
						attribute = locateElement(needAttribute, Attribute_Type);
					}
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

	public static void jsClickElement(String needAttribute, ElementType Attribute_Type) {
		for (int i = 0; i < 10; i++) {
			try {
				WebElement attribute = locateElement(needAttribute, Attribute_Type);
				JavascriptExecutor executor = (JavascriptExecutor)driver;
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

	public static void navigateToUrl(String needURL) { driver.get(needURL);
	}

	@And("I quit driver")
	public static void closeBrowser() {
		driver.quit();
	}
	
	public static void highLight(String needElement) {
		if (SystemUtils.IS_OS_MAC_OSX) {
			WebElement ele = FindElement.elementByAttribute(needElement, InputType.XPath);
			Actions action = new Actions(driver);
			action.doubleClick(ele).build().perform();
		} else if (SystemUtils.IS_OS_WINDOWS) {
			FindElement.elementByAttribute(needElement, InputType.XPath).sendKeys(Keys.CONTROL, "a");
		}

	}

	public static int removeSpecialChars(String needAttribute) {
		String cases = getElementTextValue(needAttribute, ElementType.XPath);

		int result = Integer.parseInt(cases.replaceAll("[@ $,.]", ""));

		return result / 100;
	}
	
	public static String getCurrentMonth(){
		Calendar calendar = Calendar.getInstance();
		return new SimpleDateFormat("MMMMMMMMMMMMMM").format(calendar.getTime());
	}

	public static String addYearstoCurrentYear(String needFormat, int addNumberOfMonths){
		DateFormat dateFormat = new SimpleDateFormat(needFormat);
		Date date = new Date();
		date = DateUtils.addYears(date, addNumberOfMonths);
		String date1 = dateFormat.format(date);
		return date1;
	}

	public static String getCurrentDate(){
		Calendar calendar = Calendar.getInstance();
		String date = new SimpleDateFormat("dd").format(calendar.getTime());
		if(date.startsWith("0")){
			date = date.substring(1,date.length());
		}
		return date;
	}

	public static String getMonthsInFuture(int monthsInFuture){
		Calendar currentMonth = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
		currentMonth.add(Calendar.MONTH, monthsInFuture);
		return dateFormat.format(currentMonth.getTime());
	}

	public static String getLastDateOfTheMonth(){
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		Date lastDayOfMonth = calendar.getTime();
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(lastDayOfMonth);
	}
	
	public enum ElementType {
		XPath, ID, ClassName, PartialLink, LinkText
	}
}
