package automation.PestRoutes.Utilities.Driver;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GetWebDriver {
	
	private static WebDriver driver;
	private static ThreadLocal<WebDriver> webDrivers = new ThreadLocal<WebDriver>();

	public static WebDriver getInstance() {
		if(driver == null) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--window-size=1366,768","--ignore-certificate-errors");
			WebDriverManager.chromedriver().setup();
			if(SystemUtils.IS_OS_MAC_OSX) {
				options.addArguments("--headless", "--disable-gpu");
				driver = new ChromeDriver(options);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} else if(SystemUtils.IS_OS_WINDOWS) {
				driver = new ChromeDriver(options);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			} else if(SystemUtils.IS_OS_LINUX) {
				options.addArguments("--headless", "--disable-gpu");
				driver = new ChromeDriver(options);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
		}
		return driver;
	}
	public static void quitCurrentDriver() {
		final WebDriver webDriver = webDrivers.get();
		if (webDriver != null) {
			webDriver.quit();
			webDrivers.remove();
		}
	}
	public static void reloadBrowser() throws InterruptedException {
		driver.manage().deleteAllCookies();
	}
}
