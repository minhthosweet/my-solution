package automation.PestRoutes.Utilities.Driver;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetWebDriver {
	
	private static WebDriver driver;

	public static WebDriver getInstance() {
		if(driver == null) {
			if(SystemUtils.IS_OS_MAC_OSX) {
				System.setProperty("webdriver.chrome.driver",
						"src/test/java/automation/PestRoutes/Utilities/Driver/chromedriver");
			} else if(SystemUtils.IS_OS_WINDOWS) {
				System.setProperty("webdriver.chrome.driver",
						"src/test/java/automation/PestRoutes/Utilities/Driver/chromedriver.exe");
			}

			driver = new ChromeDriver();
		}
		return driver;
	}
}
