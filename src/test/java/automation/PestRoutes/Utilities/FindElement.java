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
            try{
                switch (attributeType)
                {
                    case XPath:
                        return driver.findElement(By.xpath(needAttribute));
                    case ID:
                        return driver.findElement(By.id(needAttribute));
                    case ClassName:
                        return driver.findElement(By.name(needAttribute));
                    case TagName:
                        return driver.findElement(By.tagName(needAttribute));
                    case LinkText:
                        return driver.findElement(By.linkText(needAttribute));
                    default:
                        return driver.findElement(By.xpath(needAttribute));

                }

            }catch (Exception e){
                System.out.println("Could not find element with => "+attributeType+ " " + needAttribute);
            }
            return driver.findElement(By.xpath(needAttribute));
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

