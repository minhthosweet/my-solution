package automation.PestRoutes.PageObject;

import automation.PestRoutes.Utilities.Deprecated;
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
        Deprecated.waitVisible("//a[text() = '" + chooseTabFromConst + "']");
        Deprecated.clickElement("//a[text() = '" + chooseTabFromConst + "']");
    }


    public void searchCustomer_History(String needCustomerFullName) {
        Deprecated.waitVisible(ACCESS_HISTORY);
        Deprecated.jsClickElement(ACCESS_HISTORY);
        Deprecated.clickElement("//span[text()='" + needCustomerFullName + "']");
    }

    @When("I search the number {string} customer in History tab")
    public void searchCustomerInOrder(String customerNumber) {
        clickAccessHistory();
        Deprecated.clickElement("//h3[text()='Customer Access History']/following-sibling::div//li[" + customerNumber + "]//span[" + customerNumber + "]");
    }

    //**Author Aarbi
    @And("I search customer with name {string}")
    public void searchCustomerWithName(String needCustomerName) {
        clickAccessHistory();
        String name = convertName(needCustomerName);
        if (!Deprecated.isPresent("//span[text()='" + name + "']")){
            clickAccessHistory();
        } else {
            Deprecated.clickElement("//span[text()='" + name + "']");
        }
    }

    public void clickAccessHistory() {
        Deprecated.waitVisible(ACCESS_HISTORY);
        Deprecated.jsClickElement(ACCESS_HISTORY);
    }

    public void searchCustomer_SearchField(String customerDetails) {
        Deprecated.jsClickElement(SearchField);
        Deprecated.locate(SearchField).sendKeys(customerDetails);
    }

    //**Author Aarbi
    public static String convertName(String name) {
        String firstName = name.substring(0, name.indexOf(" "));
        String lastName = name.substring(name.indexOf(" ") + 1);
        String cName = lastName + ", " + firstName;
        return cName;
    }
}