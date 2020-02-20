package automation.PestRoutes.Utilities.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetWebDriver {
	
	private static WebDriver driver;

	public static WebDriver getInstance() {
		if(driver == null) {
			driver = new ChromeDriver();
		}
		return driver;
	}
}
