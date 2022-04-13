package automation.PestRoutes.PageObject.Search;

import automation.PestRoutes.Utilities.Legacy;

public class SearchBox
{

    //Author : Adi
    public String autoCompleteSearch(String customerDetail){
        Legacy.waitVisible("//li[@role='presentation']//span[text()='"+customerDetail+"']");
        return Legacy.getElementTextValue("//li[@role='presentation']//span[text()='"+customerDetail+"']");
    }

    //Author : Adi
    public String containsInAutoCompleteSearch(String customerDetail){
        Legacy.waitVisible("//li[@role='presentation']//span[contains(text(),'"+customerDetail+"')]");
        return Legacy.getElementTextValue("//li[@role='presentation']//span[contains(text(),'"+customerDetail+"')]");
    }
}
