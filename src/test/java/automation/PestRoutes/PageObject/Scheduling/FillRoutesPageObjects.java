package automation.PestRoutes.PageObject.Scheduling;

import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Data.AppData;
import automation.PestRoutes.Utilities.Legacy;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FillRoutesPageObjects extends AppData {
    //--------------------------------------------------------
    // --  DEFINE PAGE OBJECTS
    //--------------------------------------------------------

    //-- FillRoutes Page Web Elements
    //--------------------------------------------------------

    //************** Jobs Available Section ********************
    //"Jobs Available" Section Title
    private String jobsAvailable_SectionTitle_IncludeAllRoutes = "//*[@id='fillRoutes']/h3[contains(text(),'Jobs Available')]";

    //Due Between
    private String inputDueBetweenStart = "//input[@name='dueStart']";
    private String inputDueBetweenEnd = "//input[@name='dueEnd']";

    // Follow-up Due Between
    private String inputFollowupDueBetweenStart = "//input[@name='followUpDueStart']";
    private String inputFollowupDueBetweenEnd = "//input[@name='followUpDueEnd']";

    // Refresh button - Jobs Available
    private String btnRefresh_JobsAvailable ="//*[@id='fillRoutes']//div[@class='buttonWrapper']/input[@value='Refresh']";

    //Unscheduled Stops Routing/Customers counts
    private String unscheduledStopsRoutingCustomerCounts = "//*[@id='fillExistingCustomersDue']/h3[contains(text(),'Routing')]";


    //************** Routes to Fill Section ********************
    //"Routes To Fill" Section Title
    private String routesToFill_SectionTitle_IncludeAllRoutes = "//*[@id='fillRoutes']//div//h3[contains(text(),'Routes to Fill')]";

    //Options:
    private String filledStatusOption_IncludeAllRoutes ="//*[@id='fillRoutes']//select[@name='filledStatus']/option[@value='0']";
    private String filledStatusOption_IncludeOnlyEmptyRoutes ="//*[@id='fillRoutes']//select[@name='filledStatus']/option[@value='1']";
    private String filledStatusOption_IncludeOnlyRoutesWithExistingAppts ="//*[@id='fillRoutes']//select[@name='filledStatus']/option[@value='2']";

    //Date Range
    private String inputDateRangeStart="//input[@name='fillRoutesStart']";
    private String inputDateRangeEnd="//input[@name='fillRoutesEnd']";

    // Refresh button - Routes To Fill
    private String btnRefresh_RoutesToFill ="//div[@class = 'right actions']//input[@value='Refresh']";

    //Fill Routes Button
    private String btnFillRoutes ="//*[@id='fillExistingRoutesButton']";


    //************** "Fill Routes with Available Jobs" Dialog ********************
    //Routes to be filled are displayed
    private String routesByDateGroups ="//*[@id='fillExistingRoutes']//h3[@class='aCenter clr']";

    //"Fill Routes with Available Jobs" Dialog Title
    private String dialogTitle ="//span[text()='Fill Routes with Available Jobs']/parent::div";

    //Max Time
    private String maxTimeLabel="//*[@id='fillRoutesDialog']//label[contains(text(),'Max Time')]";
    private String maxTimeInput="//*[@id='fillRoutesDialog']//label[contains(text(),'Max Time')]/following-sibling::input[@name='maxMinutes']";
    private String maxTimeDropDown="//*[@id='fillRoutesDialog']//label[contains(text(),'Max Time')]/following::select[@name='maxTimeControl']";

    //Max Stops
    private String maxStopsLabel="//*[@id='fillRoutesDialog']//label[contains(text(),'Max Stops')]";
    private String maxStopsInput="//*[@id='fillRoutesDialog']//label[contains(text(),'Max Stops')]/following::select[@name='maxTimeControl']";

    //Preferred Techs
    private String PreferredTechsLabel="//*[@id='fillRoutesDialog']//label[contains(text(),'Preferred Techs')]";
    private String PreferredTechsDropDown="//*[@id='fillRoutesDialog']//label[contains(text(),'Preferred Techs')]/following::select[@name='disregardTechAssignments']";

    //Max SF
    private String maxSFLabel="//*[@id='fillRoutesDialog']//label[contains(text(),'Max SF')]";
    private String maxSFInput="//*[@id='fillRoutesDialog']//label[contains(text(),'Max SF')]/following-sibling::input[@name='maxSF']";

    //Max LF
    private String maxLFLabel="//*[@id='fillRoutesDialog']//label[contains(text(),'Max LF')]";
    private String maxLFInput="//*[@id='fillRoutesDialog']//label[contains(text(),'Max LF')]/following-sibling::input[@name='maxLF']";


    //"Fill Routes with Available Jobs" button
    private String btnCancel ="//button/span[text()='Fill Routes with Available Jobs']/../../parent::div//button/span[text() ='Cancel']";
    private String btnFillRoutesWithAvailableJobs ="//button/span[contains(text(),'Fill Routes with Available Jobs')]";


    //************** "Fill Routes Review" Page  ********************
    //Page Title
    private String fillRoutesReviewPage ="//*[@id='fillRoutesReview']/h3[contains(text(),'Fill Routes Review')]";

    //"Routes have been successfully..." Message
    private String msgRoutesOptimizedReviewPg ="//*[@name='fillRoutesReviewMessage']";

    //Cancel Button
    private String btnCancel_FillRoutsReviewPg ="//*[@name='fillRoutesReviewMessage']/following-sibling::div[text()='Cancel']";

    //Save Button
    private String btnSave_FillRoutsReviewPg ="//*[@name='fillRoutesReviewMessage']/following-sibling::div[text()='Save']";

     //In Progress Banner
     private String inprogressBanner_FillRoutsReviewPg ="//*[@id='checkedOptimizationNotice']/h3[text()='Fill Routes in progress']";


    //--------------------------------------------------------
    // -- CONSTANTS & VARIABLES
    // --------------------------------------------------------
    public final String JOBS_AVAILABLE_SECTION = "Jobs Available";
    public final String ROUTES_TO_FILL_SECTION = "Routes To Fill";
    public final String DIALOG_HEADER = "Fill Routes with Available Jobs";
    public final String FILLROUTESREVIEW_PAGE_HEADER = "Fill Routes Review";
    public final String OPTIMIZEQUEUE_PHP_URL = "https://stagingdemo.pestroutes.com/resources/scripts/optimizeQueue.php";

    private WebDriver incognitoBrowser;

    //--------------------------------------------------------
    // -- METHODS
    //--------------------------------------------------------
    public void clickRefreshBtn(String strSectionTitle) {

        switch (strSectionTitle){
            case JOBS_AVAILABLE_SECTION:
                Legacy.isVisible(btnRefresh_JobsAvailable);
                Legacy.clickElement(btnRefresh_JobsAvailable);
                break;
            case ROUTES_TO_FILL_SECTION:
                Legacy.scrollToElement(routesToFill_SectionTitle_IncludeAllRoutes);
                Legacy.isVisible(btnRefresh_RoutesToFill);
                Legacy.clickElement(btnRefresh_RoutesToFill);
                break;
        }
     }//clickRefreshBtn()

    public void clickFillRoutesBtn() {
        Legacy.isVisible(btnFillRoutes);
        Legacy.clickElement(btnFillRoutes);
        Legacy.waitVisible(dialogTitle);
    }// public void clickFillRoutesBtn()

    public void addDueBetweenCriteria(String strStartDate, String strEndDate) throws InterruptedException {
        //Add a start date for "Due Between" range
        loadFilterValue(inputDueBetweenStart, strStartDate);

        //Add an end date for "Due Between" range
        loadFilterValue(inputDueBetweenEnd, strEndDate);

    }//addDueBetweenCriteria()

    public void addDateRangeCriteria(String strStartDate, String strEndDate) throws InterruptedException {
        //Scroll to the "Routes To Fill"
        Legacy.scrollToElementJS(routesToFill_SectionTitle_IncludeAllRoutes);

        //Add a start date for "Date Range"
        loadFilterValue(inputDateRangeStart, strStartDate);

        //Add an end date  for "Date Range"
        loadFilterValue(inputDateRangeEnd, strEndDate);
    }//addDateRangeCriteria()

    public boolean unscheduledStopsCheck() {
        boolean boolFlag;
        String splitToken = "/";
        String numberOnlyRegexp = "[^0-9]";
        String replacementToken ="";
        int routesCount;
        int customerCount;

        String strRouteCustomerCounts = Legacy.locate(unscheduledStopsRoutingCustomerCounts).getText();
        String[] routesCustomerArray= strRouteCustomerCounts.split(splitToken);
        String strRoutesCounts = routesCustomerArray[0].replaceAll(numberOnlyRegexp,replacementToken);
        String strCustomerCounts = routesCustomerArray[1].replaceAll(numberOnlyRegexp,replacementToken);

        //Check Routes Count
         if(!strRoutesCounts.trim().isEmpty())
             routesCount = Integer.parseInt(strRoutesCounts);
         else
             routesCount =0;

        //Check Customers Count
        if(!strCustomerCounts.trim().isEmpty())
            customerCount = Integer.parseInt(strCustomerCounts);
        else
            customerCount =0;

        boolFlag = (routesCount > 0) && (customerCount > 0);

        return boolFlag;
    }//unscheduledStopsCheck()

    public boolean routesToFillCheck() {
        // Verify there are displayed routes to be filled
        boolean  routeChkFlag = Legacy.isVisible(routesByDateGroups);
        return routeChkFlag;
    }//routesToFillCheck()

    public void fillAvailableRoutes(String strMaxTimes){
        // Check for dialog being displayed
        if(Legacy.isVisible(dialogTitle))
        {
            //Select Max Time option
           Legacy.selectByText(maxTimeDropDown, strMaxTimes);
            clickFillRoutesWithAvailableJobsBtn();
        }
    } //fillAvailableRoutes()

    public void executeOptimizeQueueScript() throws InterruptedException {
        //Allow the "In Progress Banner to load
        Legacy.waitVisible(inprogressBanner_FillRoutsReviewPg, 6);

        if(Legacy.isVisible(inprogressBanner_FillRoutsReviewPg)) {
            // Load an incognito browser and execute the optimizeQueue.php script
            incognitoBrowser = GetWebDriver.loadIncognitoChromeBrowser(OPTIMIZEQUEUE_PHP_URL);
         }
    }//executeOptimizeQueueScript()

    public void clickFillRoutesWithAvailableJobsBtn() {
        //Click the "Fill Routes with Available Jobs" button
        if( Legacy.isVisible(btnFillRoutesWithAvailableJobs))
            Legacy.clickElement(btnFillRoutesWithAvailableJobs);
    } //clickFillRoutesWithAvailableJobsBtn()

    public void loadFilterValue(String strFilterXpath, String strValue) throws InterruptedException{
        //Make sure the field(s) are displayed
         if (Legacy.isVisible(strFilterXpath)) {
             //Clear the Date Range startDate field and load new value
             Legacy.locate(strFilterXpath).clear();
             Legacy.locate(strFilterXpath).sendKeys(strValue);
             Legacy.locate(strFilterXpath).sendKeys(Keys.ENTER);
         }
         else
         {
             Assert.fail("Fill Routes' Landing Page DID NOT LOAD");
         }
    }// loadFilterValue()

    public void saveOptimizedRoutes() throws InterruptedException {
        //Allow time for the "Fill Routes Review" Page to display
        Legacy.waitVisible(fillRoutesReviewPage,3);

         //Click [Save] button to save optimized routes
        if( Legacy.isVisible(fillRoutesReviewPage)) {

            //Close incognito browser
            GetWebDriver.closeIncognitoBrowser(incognitoBrowser);

            //Click [Save] to save the optimized routes
            Legacy.clickElement(btnSave_FillRoutsReviewPg);

            //Accepts any windows alerts
            Utilities.acceptAlert();

            //Verify the "Save" process has completed and the "Fill Routes" landing page is displayed
            Legacy.waitVisible(jobsAvailable_SectionTitle_IncludeAllRoutes);
        }
        else {
            Assert.fail("Fill Routes' Review Page DID NOT LOAD");
        }
    }//saveOptimizedRoutes()
} //FillRoutesPageObjects
