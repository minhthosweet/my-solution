package automation.PestRoutes.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import automation.PestRoutes.Utilities.Driver.GetWebDriver;

public class FindElement {
	static WebDriver driver = GetWebDriver.getInstance();
	
	public static WebElement elementByAttribute(String needAttribute,
            InputType attributeType)
        {
            switch (attributeType)
            {
                case XPath:
                    Utilities.waitForElementIsVisible(needAttribute, 7);
                    return driver.findElement(By.xpath(needAttribute));
                case ID:
                    Utilities.waitForElementIsVisible(needAttribute, 7);
                    return driver.findElement(By.id(needAttribute));
                case ClassName:
                    Utilities.waitForElementIsVisible(needAttribute, 7);
                    return driver.findElement(By.name(needAttribute));
                case TagName:
                    Utilities.waitForElementIsVisible(needAttribute, 7);
                    return driver.findElement(By.tagName(needAttribute));
                case LinkText:
                    Utilities.waitForElementIsVisible(needAttribute, 7);
                    return driver.findElement(By.linkText(needAttribute));
                default:
                    Utilities.waitForElementIsVisible(needAttribute, 7);
                    return driver.findElement(By.xpath(needAttribute));

            }
        }

	
	 public enum InputType
	    {
	        XPath,
	        ID,
	        ClassName,
	        PartialLink,
	        TagName,
	        LinkText
	    }
	
}

