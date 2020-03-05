package automation.PestRoutes.Utilities.Driver;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GetWebDriver {
	
	private static WebDriver driver;

	public static WebDriver getInstance() {
		if(driver == null) {
			if(SystemUtils.IS_OS_MAC_OSX) {
				System.setProperty("webdriver.chrome.driver",
						"src/test/java/automation/PestRoutes/Utilities/Driver/chromedriver");
				driver = new ChromeDriver();
			} else if(SystemUtils.IS_OS_WINDOWS) {
				System.setProperty("webdriver.chrome.driver",
						"src/test/java/automation/PestRoutes/Utilities/Driver/chromedriver.exe");
				driver = new ChromeDriver();
			} else if(SystemUtils.IS_OS_LINUX) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
				driver = new ChromeDriver(options);
			}
		}
		return driver;
	}
}
