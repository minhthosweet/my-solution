package automation.PestRoutes.Utilities.Driver;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GetWebDriver {
	
	private static WebDriver driver;
	private static ThreadLocal<WebDriver> webDrivers = new ThreadLocal<WebDriver>();

	public static WebDriver getInstance() {
		if(driver == null) {
			if(SystemUtils.IS_OS_MAC_OSX) {
				System.setProperty("webdriver.chrome.driver",
						"src/test/java/automation/PestRoutes/Utilities/Driver/chromedriver.mac");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} else if(SystemUtils.IS_OS_WINDOWS) {
				System.setProperty("webdriver.chrome.driver",
						"src/test/java/automation/PestRoutes/Utilities/Driver/chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} else if(SystemUtils.IS_OS_LINUX) {
				System.setProperty("webdriver.chrome.driver",
						"src/test/java/automation/PestRoutes/Utilities/Driver/chromedriver.linux");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
				driver = new ChromeDriver(options);
				driver.manage().window().maximize();
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
