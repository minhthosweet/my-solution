package automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;

public class DivisionsObjects {

    private String clickNewDivision = "//div[text() = '+ Division']";
    private String divisionCode = "//input[@value ='' and @name='code']";
    private String divisionDescription = "//input[@value ='' and @name='description']";

    public void createNewDivisions(String needDivisionCode, String needDivisionDescription){
        clickNewDivision();
        setDivisionCode(needDivisionCode);
        setDivisionDescription(needDivisionDescription);
        CustomerSourcesObjects.clickSaveButton();

    }

    public void clickNewDivision() {
        Utilities.waitUntileElementIsVisible(clickNewDivision);
        Utilities.clickElement(clickNewDivision, Utilities.ElementType.XPath);
    }

    public void setDivisionCode(String needDivisionCode) {
        Utilities.waitUntileElementIsVisible(divisionCode);
        FindElement.elementByAttribute(divisionCode, FindElement.InputType.XPath).sendKeys(needDivisionCode);
    }

    public void setDivisionDescription(String needDivisionDescription) {
        Utilities.waitUntileElementIsVisible(divisionDescription);
        FindElement.elementByAttribute(divisionDescription, FindElement.InputType.XPath).sendKeys(needDivisionDescription);
    }
}
