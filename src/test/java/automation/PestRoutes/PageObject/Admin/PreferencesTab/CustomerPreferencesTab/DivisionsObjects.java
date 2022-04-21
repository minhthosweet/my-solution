package automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab;

import automation.PestRoutes.Utilities.Legacy;

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
        Legacy.waitVisible(clickNewDivision);
        Legacy.clickElement(clickNewDivision);
    }

    public void setDivisionCode(String needDivisionCode) {
        Legacy.waitVisible(divisionCode);
        Legacy.locate(divisionCode).sendKeys(needDivisionCode);
    }

    public void setDivisionDescription(String needDivisionDescription) {
        Legacy.waitVisible(divisionDescription);
        Legacy.locate(divisionDescription).sendKeys(needDivisionDescription);
    }
}
