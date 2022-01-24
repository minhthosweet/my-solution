package automation.PestRoutes.Utilities;

import automation.PestRoutes.Utilities.Driver.GetWebDriver;
import automation.PestRoutes.Utilities.FindElement.InputType;
import io.cucumber.java.en.And;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Utilities {
	static WebDriver driver = GetWebDriver.getInstance();

	public static List list= new ArrayList<String>();

	public static String getAlertText() {
		return driver.switchTo().alert().getText();
	}
	
	public static void clickElementInIframe(String needXpath) {
		int size = driver.findElements(By.tagName("iframe")).size();
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

	public static void switchToIframeByXpath(String needElement) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		String elm = needElement;
		driver.switchTo().frame(elm);
	}

	public static String getInnerText(String needElement){
		WebElement elm = driver.findElement(By.xpath(needElement));
		return (String) ((JavascriptExecutor) driver).executeScript(
				"return jQuery(arguments[0]).text();", elm);
	}

	public static String getInnerText(By needElement){
		WebElement elm = driver.findElement(needElement);
		return (String) ((JavascriptExecutor) driver).executeScript(
				"return jQuery(arguments[0]).text();", elm);
	}

	public static void switchToIframeByIndex(int needIndex) {
		driver.switchTo().frame(needIndex);
	}

	public static void switchToNewWindowOpened() {
		for (String windowHandle : driver.getWindowHandles()) {
			driver.switchTo().window(windowHandle);
		}
	}

	public static String winHandleBefore = driver.getWindowHandle();

	public static void switchToOldWindowOpened(){
		driver.switchTo().window(winHandleBefore);
	}

	public static void switchBackToDom() {
		driver.switchTo().defaultContent();
	}

	public static void acceptAlert() {
		//IAlert alert = driver.switchTo().alert().accept();
			int i=0;
			while(i++<5)
			{
				try
				{
					driver.switchTo().alert().accept();
					break;
				}
				catch(Exception e)
				{
					delay(1000);
					continue;
				}
			}

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
		waitUntileElementIsVisible(needXpath, 7);
		Select dropdown = new Select(driver.findElement(By.xpath(needXpath)));
		dropdown.selectByVisibleText(needValue);
	}

	public static void selectValueFromDropDownByIndex(String needXpath, int needIndex) {
		waitUntileElementIsVisible(needXpath, 7);
		Select dropdown = new Select(driver.findElement(By.xpath(needXpath)));
		dropdown.selectByIndex(needIndex);
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

	public static void scrollToElementJS(String needXpath) {
		WebElement element = driver.findElement(By.xpath(needXpath));
		scrollToElementJS(element);
	}

	public static void scrollToElementJS(By needXpath) {
		WebElement element = driver.findElement(needXpath);
		scrollToElementJS(element);
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

	public static void scrollToBottomElementJS(String needXpath) {
		WebElement element = driver.findElement(By.xpath(needXpath));
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		delay(300);
	}

	public static String generateRandomString(int needLength) {
		return RandomStringUtils.random(needLength, true, true);
	}

	// Author: Aditya
	public static double generateRandomInteger(int needLength) {
		double m = (double) Math.pow(10, needLength - 1);
		m = m + new Random().nextInt((int) (9 * m)) +  new Random().nextDouble();
		double b = Math.round(m*100.0)/100.0;
		return b;
	}

	public static int generateRandomNumber(int needLength) {
		int m = (int)Math.pow(10, needLength - 1);
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
		if(date1.startsWith("0") && (needFormat.startsWith("d") || needFormat.startsWith("M"))){
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
		waitUntileElementIsVisible(needXpath, 7);
		return driver.findElement(By.xpath(needXpath)).getAttribute(needAttribute);
	}

	public static void waitUntileElementIsVisible(String needXpath, int needSecs) {
		WebDriverWait wait = new WebDriverWait(driver, needSecs);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(needXpath)));
	}

	public static void waitUntileElementIsVisible(By locator, int needSecs) {
		WebDriverWait wait = new WebDriverWait(driver, needSecs);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void waitUntileElementIsClickable(By locator, int needSecs) {
		WebDriverWait wait = new WebDriverWait(driver, needSecs);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void waitUntileElementIsVisible(String needXpath) {
		waitUntileElementIsVisible(needXpath, 50);
	}

	public static void waitUntileElementIsVisible(By locator) {
		waitUntileElementIsVisible(locator, 45);
	}

	public static boolean elementIsVisible(String needXpath) {
		if (driver.findElements(By.xpath(needXpath)).size() > 0) {
			return driver.findElement(By.xpath(needXpath)).isDisplayed();
		} else {
			return false;
		}
	}
	public static boolean elementIsVisible(By locator) {
		if (driver.findElements(locator).size() > 0) {
			return driver.findElement(locator).isDisplayed();
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

	public static void hoverElement(String needElementToHover, String needAttributeToClick) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(needElementToHover))).
				moveToElement(driver.findElement(By.xpath(needAttributeToClick))).click().perform();
	}

	private static WebElement locateElement(String needAttribute, ElementType Attribute_Type) {
		switch (Attribute_Type) {
			case ID:
				waitUntileElementIsVisible(needAttribute, 7);
				return driver.findElement(By.id(needAttribute));
			case ClassName:
				waitUntileElementIsVisible(needAttribute, 7);
				return driver.findElement(By.className(needAttribute));
			case LinkText:
				waitUntileElementIsVisible(needAttribute, 7);
				return driver.findElement(By.linkText(needAttribute));
			default:
				waitUntileElementIsVisible(needAttribute, 7);
				return driver.findElement(By.xpath(needAttribute));
		}
	}

	private static WebElement locateElements(String needAttribute, ElementType Attribute_Type) {
		List<WebElement> elements;
		switch (Attribute_Type) {
			case ID:
				waitUntileElementIsVisible(needAttribute, 7);
				elements = driver.findElements(By.id(needAttribute));
				break;
			case ClassName:
				waitUntileElementIsVisible(needAttribute, 7);
				elements = driver.findElements(By.className(needAttribute));
				break;
			case LinkText:
				waitUntileElementIsVisible(needAttribute, 7);
				elements = driver.findElements(By.linkText(needAttribute));
				break;
			default:
				waitUntileElementIsVisible(needAttribute, 7);
				elements = driver.findElements(By.xpath(needAttribute));
		}
		return elements.get(elements.size() - 1);

	}

	public static String getElementTextValue(String needAttribute, ElementType Attribute_Type) {
		for (int i = 0; i < 10; i++) {
			try {
				waitUntileElementIsVisible(needAttribute, 7);
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
			WebElement elm = FindElement.elementByAttribute(needAttribute, FindElement.InputType.XPath);
			try {
				if (elm.isDisplayed()) {
					waitUntileElementIsVisible(needAttribute, 7);
					scrollToElement(needAttribute);
					clickElement(needAttribute, Attribute_Type, false, false);
				}
			}
			catch(Exception e){
				System.out.println("Locator not visible");
				}
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
						waitUntileElementIsVisible(needAttribute, 7);
						attribute = locateElements(needAttribute, Attribute_Type);
					} else {
						waitUntileElementIsVisible(needAttribute, 7);
						attribute = locateElement(needAttribute, Attribute_Type);
					}
					waitUntileElementIsVisible(needAttribute, 7);
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
				Thread.sleep(1000);
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

	public static String getCurrentUrl(){
		String currentURL = driver.getCurrentUrl();
		return currentURL;
	}

	@And("I quit driver")
	public static void closeBrowser() {
		driver.quit();
	}

	public static void closeTab() {
		driver.close();
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

	public static void clearField(String needElement) {
		FindElement.elementByAttribute(needElement, InputType.XPath).clear();
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

	public static void clickAdvancedFilters()  {
		waitUntileElementIsClickable(By.xpath("//div[@id = 'advancedFilterToggleButton']"), 10);
		clickElement("//div[@id = 'advancedFilterToggleButton']", ElementType.XPath);
	}

	public static void doubleClick(String needXpath){
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.xpath(needXpath));
		actions.doubleClick(elementLocator).perform();
	}

	public static String transformName(String name) {
		String firstName = name.substring(0, name.indexOf(" "));
		String lastName = name.substring(name.indexOf(" ")).trim();
		String last_first = lastName + ", " + firstName;
		return last_first;
	}

	public static void refreshPage() {
		driver.navigate().refresh();
	}

	public static void dragCustomerCard (int x, int y)  {
		WebElement customerCard = driver.findElement(By.xpath("//body[@id='daysPage']//div[@aria-describedby='customerWindow']/div"));
		Actions act = new Actions(driver);
		act.dragAndDropBy(customerCard, x, y).perform();
		delay(3000);
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
  
	//F.White - 12/11/2021 : Added method readExcelFile()
	//Method readExcelFile(): Reads files from an excel spreadsheet and places it in a HaspMap<String, String>
	public static HashMap<String, String> readExcelFile(String filePathLocation, String fileName, String sheetName, int dataSetID) throws IOException {
		HashMap<String, String> fileDataMap =  new  HashMap<String, String>();
		String fileFullPathLocation = filePathLocation + File.separator + fileName;
		FileInputStream fis= null;

		try {
			File file = new File(fileFullPathLocation);
			fis = new FileInputStream(file);
			XSSFWorkbook workbookName = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbookName.getSheet(sheetName);

			//Load Data into a HashMap
			Row rowHeader = sheet.getRow(0);
			List<String> columnHeader = new ArrayList<String>();

			//Load The Header Row
			Iterator<Cell> cellIterator = rowHeader.cellIterator();
			while (cellIterator.hasNext())
			{
				columnHeader.add(cellIterator.next().getStringCellValue());
			}

			//Load the HashMap
			int rowCount = rowHeader.getLastCellNum();
			int columnCount = rowHeader.getLastCellNum();

			Row row = sheet.getRow(dataSetID);
			for (int j = 0; j < columnCount; j++) {
				Cell cell = row.getCell(j);
				fileDataMap.put(columnHeader.get(j), getCellValueAsString(cell));
			}

		}catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (fis != null)
			{
				fis.close();
			}
		}
		return fileDataMap;
	}//readExcelFile()

	//F.White - 12/11/2021: Added method readExcelFile(). It called by readExelFile()
	//Method getCellValueAsString(): Returns the cell value in a Hashmap as a string
	public static String getCellValueAsString(Cell cell) {
		String cellValue = null;
		switch (cell.getCellType()) {
			case NUMERIC:
				cellValue = String.valueOf(cell.getNumericCellValue());
				break;
			case STRING:
				cellValue = cell.getStringCellValue();
				break;
			case BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case FORMULA:
				cellValue = cell.getCellFormula();
			case BLANK:
				cellValue = "BLANK";
			default:
				cellValue = "DEFAULT";
		}
		return cellValue;
	}//getCellValueAsString()

	//F.White - 12/11/2021: Added method printTestData().
	//Method printTestData(): Prints the data in the HashMap
	public static String printTestData(HashMap<String, String> testData) {
		List<String> keys = new ArrayList<String>(testData.keySet());
		for (String key : keys) {
			System.out.println(key + ": " + testData.get(key));
		}
		return testData.toString();
	}//printTestData()

	public static boolean isTextPresent(String text){
		try{
			driver.findElement(By.xpath("//*[contains(text(),'" + text +"')]"));
			return true;
		}
		catch(Exception e){
			return false;
		}
	}//isTextPresent()

	public static boolean isChecked(String elemXPath) {
		WebElement elem = driver.findElement(By.xpath(elemXPath));
		return elem.isSelected();
	}//isChecked()

	public static boolean isChecked(By locator) {
		WebElement elem = driver.findElement(locator);
		return elem.isSelected();
	}//isChecked()


	public static void checkBox( By locator)
	{
		WebElement elemBox = driver.findElement(locator);
		if(!elemBox.isSelected())
		{
			System.out.println("checkBox(): Checked box...");
			elemBox.click();
		}
	}//checkBox

	public static void uncheckBox( By locator)
	{
		WebElement elemBox = driver.findElement(locator);
		if(elemBox.isSelected())
		{
			elemBox.click();
		}
	}//checkBox

	public static WebDriver loadIncognitoChromeBrowser(String strURL){
		//Set browser type to Chrome and chromedriver.exe path
		WebDriverManager.chromedriver().setup();

		// Configure "incognito" option and set parameters for new  Chrome browser driver
		ChromeOptions browserOptions= new ChromeOptions();
		browserOptions.addArguments("--incognito");

		//Set capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, browserOptions);
		browserOptions.merge(capabilities);

		//Load and execute the URL in the Incogito Browser
		WebDriver incognitoBrowser = new ChromeDriver(browserOptions);
		incognitoBrowser.get(strURL);
		incognitoBrowser.navigate().to(strURL);

		return incognitoBrowser;
	} //loadIncognitoChromeBrowser()

	public static void closeIncongitoBrowser(WebDriver incongitoBrowser ){
		if (incongitoBrowser != null) {
			incongitoBrowser.quit();
		}
	}//closeIncongitoBrowser()

	public static void selectFromDropDown(String value, By locator) {
		WebElement webElm = driver.findElement(locator);
		Select findDropDown = new Select(webElm);
		findDropDown.selectByVisibleText(value);
	}//selectFromDropDown()

	public static boolean isPresent(String XPath, int timeout) {
		for (int i = 0; i < timeout * 2; i++) {
			if (driver.findElements(By.xpath(XPath)).size() > 0) {
				return true;
			}
			delay(500);
		}
		return false;
	}

	public static boolean isPresent(String XPath) {
		return isPresent(XPath, 5);
	}

	public static void delay(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
