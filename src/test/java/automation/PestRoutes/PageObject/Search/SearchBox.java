package automation.PestRoutes.PageObject.Search;

import automation.PestRoutes.Utilities.Deprecated;

public class SearchBox
{

    //Author : Adi
    public String autoCompleteSearch(String customerDetail){
        Deprecated.waitVisible("//li[@role='presentation']//span[text()='"+customerDetail+"']");
        return Deprecated.getElementTextValue("//li[@role='presentation']//span[text()='"+customerDetail+"']");
    }

    //Author : Adi
    public String containsInAutoCompleteSearch(String customerDetail){
        Deprecated.waitVisible("//li[@role='presentation']//span[contains(text(),'"+customerDetail+"')]");
        return Deprecated.getElementTextValue("//li[@role='presentation']//span[contains(text(),'"+customerDetail+"')]");
    }
}
