package automation.PestRoutes.PageObject.Search;

import automation.PestRoutes.Utilities.Utilities;

public class SearchBox
{
    public String autoCompleteSearch(String customerDetail){
        Utilities.waitUntileElementIsVisible("//li[@role='presentation']//span[text()='"+customerDetail+"']");
        return Utilities.getElementTextValue("//li[@role='presentation']//span[text()='"+customerDetail+"']", Utilities.ElementType.XPath);
    }
}
