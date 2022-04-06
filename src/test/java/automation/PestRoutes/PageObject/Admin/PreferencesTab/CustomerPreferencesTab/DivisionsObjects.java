package automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab;

import automation.PestRoutes.Utilities.Deprecated;

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
        Deprecated.waitVisible(clickNewDivision);
        Deprecated.clickElement(clickNewDivision);
    }

    public void setDivisionCode(String needDivisionCode) {
        Deprecated.waitVisible(divisionCode);
        Deprecated.locate(divisionCode).sendKeys(needDivisionCode);
    }

    public void setDivisionDescription(String needDivisionDescription) {
        Deprecated.waitVisible(divisionDescription);
        Deprecated.locate(divisionDescription).sendKeys(needDivisionDescription);
    }
}
