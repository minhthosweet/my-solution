package automation.PestRoutes.PageObject.Scheduling;

import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;

import java.util.HashMap;
import java.util.Map;

public class JobPoolTab extends AppData {

    //Buttons
    public String advanceToggleButton = "//div[@id='cdContentWrapper']//div[@id='advancedFilterToggleButton']";
    public String refreshButton = "//div[@id='cdContentWrapper']//input[@type='submit']";
    public String SearchField = "//div[@id='cdContentWrapper']//input[@type='search']";
    public Map<String, String> filter_Types = new HashMap<String, String>();
    public String filterTypes(String key) {
        // filters
        filter_Types.put("IncludeCustomersWithSpecialRequests", "//div[@id='cdContentWrapper']//select[@name='filterSpecial']");
        filter_Types.put("showPrePlanned", "//div[@id='cdContentWrapper']//select[@name='includeRouted']");
        filter_Types.put("excludeOneTimeServices", "//div[@id='cdContentWrapper']//select[@name='oneTimes']");
        filter_Types.put("dueBetween", "//div[@id='cdContentWrapper']//input[@name='dueStart']");
        filter_Types.put("dueEnd", "//div[@id='cdContentWrapper']//input[@name='dueEnd']");
        filter_Types.put("followUpDueBetween", "//div[@id='cdContentWrapper']//input[@name='followUpDueStart']");
        filter_Types.put("followUpDueEnd", "//div[@id='cdContentWrapper']//input[@name='followUpDueEnd']");
        filter_Types.put("filterPotential", "//div[@id='cdContentWrapper']//select[@name='filterPotential']");
        filter_Types.put("filterFollowUp", "//div[@id='cdContentWrapper']//select[@name='filterFollowup']");
        filter_Types.put("pendingCancels", "//div[@id='cdContentWrapper']//select[@name='pendingCancels']");
        filter_Types.put("propertyType", "//div[@id='cdContentWrapper']//select[@name='propertyType']");
        filter_Types.put("filterBalances", "//div[@id='cdContentWrapper']//input[@name='filterBalances']");
        filter_Types.put("balanceAge", "//div[@id='cdContentWrapper']//input[@name='balanceAge']");
        filter_Types.put("mapPages", "//div[@id='cdContentWrapper']//input[@name='mapPages']");
        filter_Types.put("zipCodes", "//div[@id='cdContentWrapper']//input[@name='zipCodes']");
        filter_Types.put("includeServiceTypes", "//div[@id='cdContentWrapper']//select[@name='includeServiceTypes']");
        filter_Types.put("excludeServiceTypes", "//div[@id='cdContentWrapper']//select[@name='excludeServiceTypes']");
        filter_Types.put("hideAllFlags", "//div[@id='cdContentWrapper']//select[@name='showFlagsHideFlags']");
        filter_Types.put("cities", "//div[@id='cdContentWrapper']//select[@name='cities']");
        filter_Types.put("preferredTech", "//div[@id='cdContentWrapper']//select[@name='preferredTech']");
        filter_Types.put("preferredDays", "//div[@id='cdContentWrapper']//select[@name='preferredDays']");
        filter_Types.put("filterRegion", "//div[@id='cdContentWrapper']//select[@name='regionID']");
        filter_Types.put("measurement", "//div[@id='cdContentWrapper']//select[@name='unitOfMeasure']");
        filter_Types.put("serviceCategory", "//div[@id='cdContentWrapper']//select[@name='serviceCategories']");
        filter_Types.put("includeCustomerFlags", "//div[@id='cdContentWrapper']//select[@name='includeCustomerFlags']");
        filter_Types.put("excludeCustomerFlags", "//div[@id='cdContentWrapper']//select[@name='excludeCustomerFlags']");
        filter_Types.put("includeSubscriptionFlags", "//div[@id='cdContentWrapper']//select[@name='includeSubscriptionFlags']");
        filter_Types.put("excludeSubscriptionFlags", "//div[@id='cdContentWrapper']//select[@name='excludeSubscriptionFlags']");

        return filter_Types.get(key);
    }

    public void selectFilter(String needFilterType, String needValue) {
        Utilities.selectValueFromDropDownByValue(needFilterType, needValue);
    }

    public void clickRefreshButton() {
        Utilities.waitUntileElementIsVisible(refreshButton);
        Utilities.clickElement(refreshButton, Utilities.ElementType.XPath);
    }

    public void clickAdvanceFilterButton() {
        Utilities.waitUntileElementIsVisible(advanceToggleButton);
        Utilities.clickElement(advanceToggleButton, Utilities.ElementType.XPath);
    }

    public void searchCustomer_SearchField(String customerDetails){
        Utilities.jsClickElement(SearchField, Utilities.ElementType.XPath);
        FindElement.elementByAttribute(SearchField, FindElement.InputType.XPath).sendKeys(customerDetails);
    }

    public void setDate(String needFilterType) {
        String todaysDate = Utilities.currentDate("MM/dd/yy");
        Utilities.clearField(needFilterType);
        Utilities.highLight(needFilterType);
        FindElement.elementByAttribute(needFilterType, FindElement.InputType.XPath).sendKeys(todaysDate);
    }

    public void setInputFilter(String needFilterType, String needValue) {
        FindElement.elementByAttribute(needFilterType, FindElement.InputType.XPath).sendKeys(needValue);
    }

    public String getCustomerName(String chooseNameFromConst) {
        String elm = Utilities.getElementTextValue("//tr[@customername='"+chooseNameFromConst+"']//td/following-sibling::td[2]", Utilities.ElementType.XPath);
        return elm;
    }

    public String getCustomerPhone(String chooseNameFromConst) {
        String elm = Utilities.getElementTextValue("//tr[@customername='"+chooseNameFromConst+"']//td/following-sibling::td[6]", Utilities.ElementType.XPath);
        return elm;
    }

    public String getCustomerStreet(String chooseNameFromConst) {
        String elm = Utilities.getElementTextValue("//tr[@customername='"+chooseNameFromConst+"']//td/following-sibling::td[7]", Utilities.ElementType.XPath);
        return elm;
    }

    public String getCustomerCity(String chooseNameFromConst) {
        String elm = Utilities.getElementTextValue("//tr[@customername='"+chooseNameFromConst+"']//td/following-sibling::td[8]", Utilities.ElementType.XPath);
        return elm;
    }

    public String getCustomerZip(String chooseNameFromConst) {
        String elm = Utilities.getElementTextValue("//tr[@customername='"+chooseNameFromConst+"']//td/following-sibling::td[9]", Utilities.ElementType.XPath);
        return elm;
    }

    public String getCustomerRegion(String chooseNameFromConst) {
        String elm = Utilities.getElementTextValue("//tr[@customername='"+chooseNameFromConst+"']//td/following-sibling::td[12]", Utilities.ElementType.XPath);
        return elm;
    }

    public String getServiceType(String chooseNameFromConst) {
        String elm = Utilities.getElementTextValue("//tr[@customername='"+chooseNameFromConst+"']//td/following-sibling::td[13]", Utilities.ElementType.XPath);
        return elm;
    }

}




