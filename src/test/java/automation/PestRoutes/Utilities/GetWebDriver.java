package automation.PestRoutes.Utilities;

import java.util.concurrent.TimeUnit;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.*;

public class GetWebDriver {
    public static WebDriver driver = getInstance();
    public static String winHandleBefore = driver.getWindowHandle();

    public static WebDriver getInstance() {
		if(driver == null) {
            WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--window-size=1366,768","--ignore-certificate-errors");
//            options.addArguments("--headless", "--disable-gpu");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		return driver;
	}

	public static void reloadBrowser() {
		driver.manage().deleteAllCookies();
	}

    public static void navigateToUrl(String needURL) {
        driver.get(needURL);
    }

    public static String getCurrentUrl(){
        String currentURL = driver.getCurrentUrl();
        return currentURL;
    }

    @And("I quit driver")
    public static void closeBrowser() {
        driver.quit();
    }

    public static void closeTab(){
        driver.close();
    }

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

        //Load and execute the URL in the Incognito Browser
        WebDriver incognitoBrowser = new ChromeDriver(browserOptions);
        incognitoBrowser.get(strURL);
        incognitoBrowser.navigate().to(strURL);

        return incognitoBrowser;
    }

    public static void closeIncognitoBrowser(WebDriver incognitoBrowser ){
        if (incognitoBrowser != null) {
            incognitoBrowser.quit();
        }
    }

    public static void switchToNewWindowOpened() {
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
    }

    public static void switchToOldWindowOpened() {
        driver.switchTo().window(winHandleBefore);
    }

    public static void switchBackToDom() {
        driver.switchTo().defaultContent();
    }

    public static void refreshPage() {
        driver.navigate().refresh();
    }
}

