package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.Utilities;
import org.openqa.selenium.By;

public class CustomerViewDialog_Properties extends BasePage {
    //-- Page Objects
    //---------------------------------------------------
    private By customercardPropertiesLink = By.xpath("//*[@id='customerTabs']/li[@name='linkedPropertiesTab']");
    private By lnkAProperty = By.xpath("//*[@id='propertySearch']");
    private By pageTitle = By.xpath("//*[@id='linkedProperties']/h3");


    //Strings and Constants
    //------------------------------------------------------
    public String  strPropertiesLink = "Properties";
    public  final String  pageTitleLinkedProperties =  "Linked Properties";

    //********************* Setters  *******************


    //********************* Getters  *******************
    public String getPageTitle(){
        return getText(pageTitle);
    }//getPageTitle()

    //******************* Other Method & Functions **************
    public void linkThisProperty(String strPropertyNameOrID){
       type(strPropertyNameOrID,lnkAProperty);
       Utilities.hoverElement(lnkAProperty,(By.xpath("//ul[contains(@id,'ui-id')] //li/a[contains(@id,'ui-id')]/span[contains(text(),'" + strPropertyNameOrID + "')]")));
    }//linkThisProperty()

    public void gotoPropertiesTab(){
        click(customercardPropertiesLink);
    }//gotoPropertiesTab()

}