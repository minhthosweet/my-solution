package automation.PestRoutes.PageObject;

import automation.PestRoutes.Utilities.Legacy;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;;

public class Header {
    public String newCustomerTab = "New Customer";
    public String calender = "//div[@id='guestNav']/div[2]/input";
    public String date = "//a[@class= 'ui-state-default ui-state-highlight']/parent::td/following-sibling::td[2]";
    public String schedulingTab = "Scheduling";
    public String customersTab = "Customers";
    public String billingTab = "Billing";
    public String reportingTab = "Reporting";
    public String salesTab = "Sales";
    public String adminTab = "Admin";
    public String SearchField = "//input[@id='customerSearch']";
    public String FirstSearchResult = "//ul[@id='ui-id-15']/li[1]";
    public String ACCESS_HISTORY = "//div[@id='toggleAccessHistory']";

    @When("I navigate to {string} in header")
    public void navigateTo(String chooseTabFromConst) {
        Legacy.waitVisible("//a[text() = '" + chooseTabFromConst + "']");
        Legacy.clickElement("//a[text() = '" + chooseTabFromConst + "']");
    }


    public void searchCustomer_History(String needCustomerFullName) {
        Legacy.waitVisible(ACCESS_HISTORY);
        Legacy.jsClickElement(ACCESS_HISTORY);
        Legacy.clickElement("//span[text()='" + needCustomerFullName + "']");
    }

    @When("I search the number {string} customer in History tab")
    public void searchCustomerInOrder(String customerNumber) {
        clickAccessHistory();
        Legacy.clickElement("//h3[text()='Customer Access History']/following-sibling::div//li[" + customerNumber + "]//span[" + customerNumber + "]");
    }

    //**Author Aarbi
    @And("I search customer with name {string}")
    public void searchCustomerWithName(String needCustomerName) {
        clickAccessHistory();
        String name = convertName(needCustomerName);
        if (!Legacy.isPresent("//span[text()='" + name + "']")){
            clickAccessHistory();
        } else {
            Legacy.clickElement("//span[text()='" + name + "']");
        }
    }

    public void clickAccessHistory() {
        Legacy.waitVisible(ACCESS_HISTORY);
        Legacy.jsClickElement(ACCESS_HISTORY);
    }

    public void searchCustomer_SearchField(String customerDetails) {
        Legacy.jsClickElement(SearchField);
        Legacy.locate(SearchField).sendKeys(customerDetails);
    }

    //**Author Aarbi
    public static String convertName(String name) {
        String firstName = name.substring(0, name.indexOf(" "));
        String lastName = name.substring(name.indexOf(" ") + 1);
        String cName = lastName + ", " + firstName;
        return cName;
    }
}