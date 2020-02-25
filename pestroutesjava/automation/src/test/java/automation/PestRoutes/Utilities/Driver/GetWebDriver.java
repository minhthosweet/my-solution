package automation.PestRoutes.Utilities.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetWebDriver {
	
	private static WebDriver driver;

	public static WebDriver getInstance() {
		if(driver == null) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\AbdulAarbi\\Documents\\GitHub\\tests\\pestroutesjava\\automation\\src\\test\\java\\automation\\PestRoutes\\Utilities\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		return driver;
	}
}
