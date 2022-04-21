package automation.PestRoutes.PageObject;

import automation.PestRoutes.Utilities.*;

import automation.PestRoutes.Utilities.Legacy;
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
		Legacy.waitVisible(logout);
		Legacy.clickElement(logout);

	}
	@When("I navigate to {string} in footer")
	public void navigateTo(String needTab){
		Legacy.waitVisible("//p[text() = '"+needTab+"']");
		Legacy.clickElement("//p[text() = '"+needTab+"']");
	}

	public void mapCodeWizard(String mapCode) {
		Legacy.waitVisible("//input[@notext='"+mapCodeWizard+"']");
		Legacy.locate("//input[@notext='"+mapCodeWizard+"']").sendKeys(mapCode);
	}

	public String getCustomerFromList(String customerName) {
		List<WebElement> allCustomers = Utilities.locateAll(listOfCustomers);
		for (WebElement customer : allCustomers) {
			Legacy.scrollToElementJS(customer);
			if(customer.getText().equalsIgnoreCase(customerName)) {
				System.out.println("Customer: (" + customer.getText() + ") Is Located In The List");
				return customer.getText();
			}
		}
		return "Customer Is Not Located In The List";
	}

	public void clickCustomerFromList(String customerName) {
		List<WebElement> allCustomers = Utilities.locateAll(listOfCustomers);
		for (WebElement customer : allCustomers) {
			Legacy.scrollToElementJS(customer);
			if(customer.getText().equalsIgnoreCase(customerName)) {
				Utilities.delay(1000);
				customer.click();
				break;
			}
		}
	}

	public String getTaskInformation(String customer) {
		WebElement task = Utilities.locate(By.xpath("//tbody[@id='taskItems']//td[2]/p[text()='"+ customer +"']//following::td[3]/p"));
		System.out.println("Task: " + task.getText());
		return task.getText();
	}

	public String getTaskCategory(String customer) {
		WebElement category = Utilities.locate(By.xpath("//tbody[@id='taskItems']//td[2]/p[text()='"+ customer +"']//following::td[6]/p"));
		System.out.println("Category: " + category.getText());
		return category.getText();
	}

	public String getAlertNotification(String customer) {
		WebElement notification = Utilities.locate(By.xpath("//tbody[@id='taskItems']//td[2]/p[text()='"+ customer +"']//following::td[3]"));
		System.out.println("Alert Notification: " + notification.getText());
		return notification.getText();
	}
}
