package automation.PestRoutes.Utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import automation.PestRoutes.Utilities.Driver.GetWebDriver;


public class Utilities {
	static WebDriver driver = GetWebDriver.getInstance();;

	public static String getAlertText() {
		return driver.switchTo().alert().getText();
	}
	public static void acceptAlert() {
		driver.switchTo().alert().accept();
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
	
	public static void scrollToElement(String needXpath) {
		WebElement element = driver.findElement(By.xpath(needXpath));
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}

	public static String generateRandomString(int needLength) {
		return RandomStringUtils.random(needLength, true, true);
	}
	
	public static double generateRandomInteger(int needLength) {
		double m = (double) Math.pow(10, needLength - 1);
	    return m + new Random().nextInt((int) (9 * m));
	}
	
	public static Date currentDate(String needFormat) {
		DateFormat dateFormat = new SimpleDateFormat(needFormat);
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return date;
	}
	
	public static int getElementCount(String needXpath) {
		return driver.findElements(By.xpath(needXpath)).size();
	}
	
	public static void waitUntileElementIsVisible(String needXpath) {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(needXpath)));
	}
	
	public static void javaScriptClick(String needID) {
		WebElement element = driver.findElement(By.id(needID));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public static String getElementTextValue(String needAttribute, ElementType Attribute_Type) {
		String attribute;
		switch (Attribute_Type) {
		case XPath:
			attribute = driver.findElement(By.xpath(needAttribute)).getText();
			break;
		case ID:
			attribute = driver.findElement(By.id(needAttribute)).getText();
			break;
		case ClassName:
			attribute = driver.findElement(By.className(needAttribute)).getText();
			break;
		case LinkText:
			attribute = driver.findElement(By.linkText(needAttribute)).getText();
			break;
		default:
			attribute = driver.findElement(By.xpath(needAttribute)).getText();
			break;
		}
		return attribute;
	}

	public static void clickElement(String needAttribute, ElementType Attribute_Type) {
		WebElement attribute;
		switch (Attribute_Type) {
		case XPath:
			attribute = driver.findElement(By.xpath(needAttribute));
			attribute.click();
			break;
		case ID:
			attribute = driver.findElement(By.id(needAttribute));
			attribute.click();
			break;
		case ClassName:
			attribute = driver.findElement(By.className(needAttribute));
			attribute.click();
			break;
		case LinkText:
			attribute = driver.findElement(By.linkText(needAttribute));
			attribute.click();
			break;
		default:
			attribute = driver.findElement(By.xpath(needAttribute));
			attribute.click();
			break;
		}
	}
	
	public static void navigateToUrl(String needURL) {
		driver.get(needURL);
	}
	
	public static void closeBrowser() {
		driver.close();
	}

	public enum ElementType {
		XPath, ID, ClassName, PartialLink, LinkText
	}
	
	public static int removeSpecialChars(String needAttribute) {
		String cases = getElementTextValue(needAttribute, ElementType.XPath);

		int result = Integer.parseInt(cases.replaceAll("[@ $,.]", ""));
				
		return result/100;
	}
}
