package automation.PestRoutes.PageObject.Scheduling;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SingleRoutePageObjects extends BasePage {

    //--------------------------------------------------------
    // --  DEFINE PAGE OBJECTS
    //--------------------------------------------------------
    private WebDriver incognitoBrowser;

    private By inprogressBanner_SingleRoute = By.xpath("//*[@id='checkedOptimizationNotice']/h3[contains(text(),'Route Optimization in progress')]");
    private By finishedBannerMsg_SingleRoute = By.xpath("//*[@id='checkedOptimizationNotice']/h3[contains(text(),'Route Optimization has finished')]");
    private By seeResultsBannerMsg_SingleRoute =  By.xpath("//*[@id='checkedOptimizationNotice']//span[contains(text(),'Click here to see the results')]");

    public String lnkRouteActions = "//*[@id='routesView']//div[contains(text(),'Route Actions')] ";
    private By routeActions = By.xpath("//*[@id='routesView']//div[contains(text(),'Route Actions')] ");
    private By actionDeleteRoute = By.xpath("//*[@id='routeActions']//p[contains(text(),'Delete Route')]");
    private By actionOptimizeRoute = By.xpath("//*[@id='routeActions']//p[contains(text(),'Optimize Route')]");
    private By lblDeleteRouteDialog = By.xpath("//div[@class='ui-widget-overlay ui-front']//following-sibling::div//span[text()='Delete Route?']");
    private By btnDeleteRoute = By.xpath("//div[@class='ui-widget-overlay ui-front']//following-sibling::div//span[text()='Delete Route']");
    private By btnCancel = By.xpath("//div[@class='ui-widget-overlay ui-front']//following-sibling::div//span[text()='Cancel']");
    private By lblFailedOptimizationDialog = By.xpath("//div[@class='ui-widget-overlay ui-front']//following-sibling::div//span[text()='Failed Optimization']");
    private By  failedOptimizationMsg = By.xpath("//*[contains(text(),'Failed Optimization')]//following::div/div/p");

    private By btnClose = By.xpath("//div[@class='ui-widget-overlay ui-front']//following-sibling::div//span[text()='Close']");

    public String btnRefresh = "//*[@id='routesRefresh']";  //Advanced "Map View" Refresh button

    //--------------------------------------------------------
    // -- CONSTANTS & VARIABLES
    // --------------------------------------------------------
    public static String optimizationCompleteMsg = null;
    public static String optimizationFailedDetailMsg = null;
    public final String DELETE_DIALOG_HEADER = "Delete Route?";
    public final String OPTIMIZEQUEUE_PHP_URL = "https://stagingdemo.pestroutes.com/resources/scripts/optimizeQueue.php";

    //--------------------------------------------------------
    // --  METHODS: Setters, Getters, Click
    //--------------------------------------------------------
    public void clickRouteActions(){
        Utilities.click(routeActions);
    }//clickRouteActions()

    public void clickActions_DeleteRoute(){
        Deprecated.scrollToElementJS(routeActions);
        Utilities.click(routeActions);
        Utilities.click(actionDeleteRoute);
    }//clickActions_DeleteRoute()

    public void clickActions_OptimizeRoute(){
        Utilities.delay(20000);
        Deprecated.scrollToElementJS(routeActions);
        Utilities.click(routeActions);
        Utilities.isVisible(actionOptimizeRoute);
        Utilities.click(actionOptimizeRoute);
    }//clickActions_OptimizeRoute()

    public void clickDeleteRouteBtn(){
        Utilities.click(btnDeleteRoute);
    }//clickDeleteRouteBtn()

    public void clickCancelBtn(){
        Utilities.click(btnCancel);
    }//clickCancelBtn()

    public void clickSeeOptimizedResultsMsg(){
        Utilities.click(seeResultsBannerMsg_SingleRoute);
    }//clickSeeResultsMsg()

    public void clickClosBtn(){
        Utilities.click(btnClose);
    }//clickClosBtn()

    public String getFailedOptimizationMsg(){
         return Utilities.getText(failedOptimizationMsg);
    }//getFailedOptimizationMsg()

    public void clickRefreshBtn(){
        Utilities.click(By.xpath(btnRefresh));
    }//clickRefreshBtn()

    //--------------------------------------------------------
    // --  METHODS: Others
    //--------------------------------------------------------
    public void executeOptimizeQueueScript() {

          //Allow the "In Progress Banner to load
        Utilities.waitVisible(inprogressBanner_SingleRoute, 6);

        if(Utilities.isVisible(inprogressBanner_SingleRoute)) {
            // Load an incognito browser and execute the optimizeQueue.php script
            incognitoBrowser = GetWebDriver.loadIncognitoChromeBrowser(OPTIMIZEQUEUE_PHP_URL);
        }
    }//executeOptimizeQueueScript()


    public void closeIncognitoBrowse() {
        GetWebDriver.closeIncognitoBrowser(incognitoBrowser);
    }//loadIncognitoBrowse()

    public boolean isRouteOptimizationProcessComplete() {
       try {
           if (Utilities.locate(finishedBannerMsg_SingleRoute).isDisplayed()) {
               optimizationCompleteMsg = Utilities.getText(finishedBannerMsg_SingleRoute);
               return true;
           } else
               return false;
       }
       catch(Exception e){
           if(Utilities.locate(lblFailedOptimizationDialog).isDisplayed()) {
               optimizationCompleteMsg = Utilities.getText(lblFailedOptimizationDialog);
               optimizationFailedDetailMsg = getFailedOptimizationMsg();
               System.out.println("****** Failed Detail Message: "  + optimizationFailedDetailMsg);
               clickClosBtn();
           }
          return false;
        }
    }//isRouteOptimizationProcessComplete()

    public void deleteSingleRoute(String routeID, String routeGroupID){
       Utilities.waitVisible(((By.xpath("//*[@id='routesView']//div[@class='routes']/div[@groupid='" +
                routeGroupID + "' and @routeid='" + routeID + "']"))),3);
      if(Utilities.isVisible((By.xpath("//*[@id='routesView']//div[@class='routes']/div[@groupid='" +
                            routeGroupID + "' and @routeid='" + routeID + "']"))))
                clickActions_DeleteRoute();
     }//deleteSingleRoute()

}//SingleRoutePageObjects
