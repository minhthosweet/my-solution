package automation.PestRoutes.PageObject;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import static automation.PestRoutes.Utilities.Utilities.*;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Footer extends BasePage {

	public String history = "History";
	public String tasks = "Tasks";
	public String phone = "Phone";
	public String marketing = "Marketing";
	public String news = "News";
	public String guides = "Guides";
	public String helpWizard = "Help Wizard";
	public String clock = "Clock";
	public String mapCodeWizard = "Map Code Wizard";
	public String logout = "//div[@class='dockLogoutButton']";
	public String selectOffice = "//select[@id='officeSwitcher']";

	private By listOfCustomers = By.xpath("//tbody[@id='taskItems']//td[2]/p[text()]");

	public void logoutPortal() {
		Utilities.waitUntileElementIsVisible(logout);
		Utilities.clickElement(logout, ElementType.XPath);

	}
	@When("I navigate to {string} in footer")
	public void navigateTo(String needTab){
		Utilities.waitUntileElementIsVisible("//p[text() = '"+needTab+"']");
		Utilities.clickElement("//p[text() = '"+needTab+"']", ElementType.XPath);
	}

	public void mapCodeWizard(String mapCode) {
		Utilities.waitUntileElementIsVisible("//input[@notext='"+mapCodeWizard+"']");
		FindElement.elementByAttribute("//input[@notext='"+mapCodeWizard+"']", InputType.XPath).sendKeys(mapCode);
	}

	public String getCustomerFromList(String customerName) {
		List<WebElement> allCustomers = findElements(listOfCustomers);
		for (WebElement customer : allCustomers) {
			scrollToElementJS(customer);
			if(customer.getText().equalsIgnoreCase(customerName)) {
				System.out.println("Customer: (" + customer.getText() + ") Is Located In The List");
				return customer.getText();
			}
		}
		return "Customer Is Not Located In The List";
	}

	public void clickCustomerFromList(String customerName) {
		List<WebElement> allCustomers = findElements(listOfCustomers);
		for (WebElement customer : allCustomers) {
			scrollToElementJS(customer);
			if(customer.getText().equalsIgnoreCase(customerName)) {
				customer.click();
			}
		}
	}

	public String getTaskInformation(String customer) {
		WebElement task = find(By.xpath("//tbody[@id='taskItems']//td[2]/p[text()='"+ customer +"']//following::td[3]/p"));
		System.out.println("Task: " + task.getText());
		return task.getText();
	}

	public String getTaskCategory(String customer) {
		WebElement category = find(By.xpath("//tbody[@id='taskItems']//td[2]/p[text()='"+ customer +"']//following::td[6]/p"));
		System.out.println("Category: " + category.getText());
		return category.getText();
	}

	public String getAlertNotification(String customer) {
		WebElement notification = find(By.xpath("//tbody[@id='taskItems']//td[2]/p[text()='"+ customer +"']//following::td[3]"));
		System.out.println("Alert Notification: " + notification.getText());
		return notification.getText();
	}
}
