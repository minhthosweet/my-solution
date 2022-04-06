package automation.PestRoutes.Controller.Schedules;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.OfficeSettingsObjects;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.RoutePage.RoutePage;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.PageObject.Scheduling.SingleRoutePageObjects;
import automation.PestRoutes.Utilities.Deprecated;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static automation.PestRoutes.Utilities.Report.AssertException.result;

public class SingleRoutesTests{
    Header header = new Header();
    SchedulingTab scheduler = new SchedulingTab();
    SingleRoutePageObjects singleRoutesPageObjs = new SingleRoutePageObjects();
    DashboardPage dashboardPg = new DashboardPage();
    AdminMainPage adminMainPage;
    ScheduleAppt scheduleAppointment;
    CustomerviewDialog_AppointmentsTab  appointmentsTab = new CustomerviewDialog_AppointmentsTab();

    SchedulingTab schedulingTab;
    CustomerViewDialog_SubscriptionTab subscriptionTab;
    CustomerViewDialog_Header customerCardHeader ;
    RoutePage route = new RoutePage();
    static String groupID = null;
    static String routeID = null;
    static String customerName_InESTzone =null;
    static String customer1_fullname = null;
    static String customer2_fullname = null;
    static String customer3_fullname = null;
    static String officePhysicalAddr_zip = "75010";

    @When("I navigate to the Scheduling Tab and loads today's schedule")
    public void navigateToFillRoutesTab() throws Exception {
        header.navigateTo(header.schedulingTab);
        scheduler.clickFillRoutesLink();
    }

    @Given("I enter the location of the company's Physical Location address to be in a specified time zone CST")
    public void setCompanyPhysicalAddress() {
      }//setCompanyPhysicalAddress()

    @Given("I create a route in a route group {string} with template type {string}")
    public void createNewRouteGroupAndRoute(String routeGrpName, String routeGrpTemplateName){

        //Today's Schedule
         schedulingTab = dashboardPg.goToSchedulingComponent();
         schedulingTab.addScheduleDateToProperties();
         schedulingTab.clickScheduleDay();

        if(route.isRouteGroupPresent(routeGrpName)) {
            route.deleteGroup(routeGrpName);
        }
        groupID = route.addGroup(routeGrpName, routeGrpTemplateName);
        routeID = route.addRoutesByQuantity(routeGrpName,"1");
    }//createNewRouteGroupAndRoute()

    @Then("I delete route group {string}")
    public void deleteRouteGroup(String routeGroupName) {
        if(route.isRouteGroupPresent(routeGroupName)) {
            route.deleteGroup(routeGroupName);
        }
    }

    @And("I create multiple customers and one with an address in a different time zone EST with zip {string}")
    public void  createMultipleCustomersWithAddressAndZipCode(String  zipCode) throws Exception {
        customerCardHeader  = new CustomerViewDialog_Header();
        CreateNewCustomer customer_InESTzone = new CreateNewCustomer();
        CreateNewCustomer customer1 = new CreateNewCustomer();
        CreateNewCustomer customer2= new CreateNewCustomer();
        CreateNewCustomer customer3 = new CreateNewCustomer();

        //Create customers
        customerName_InESTzone = customer_InESTzone.createCustomerWithNameEmailAddrStreetAddrPhNumZipCode(zipCode);
        customer1_fullname = customer1.createCustomerWithNameEmailAddrStreetAddrPhNumZipCode(officePhysicalAddr_zip);
        customer2_fullname = customer2.createCustomerWithNameEmailAddrStreetAddrPhNumZipCode(officePhysicalAddr_zip);
        customer3_fullname = customer3.createCustomerWithNameEmailAddrStreetAddrPhNumZipCode(officePhysicalAddr_zip);
        System.out.println("***** Test Customers: Cust In EST Zone: " + customerName_InESTzone + " Others: " + customer1_fullname +
                " | " + customer2_fullname  +  " | " + customer3_fullname  );
    }//createCustomerInESTimezone()

    @And("I create multiple customers for Route Optimization")
    public void  createMultipleCustomersForRouteOptimization() throws Exception {
        customerCardHeader  = new CustomerViewDialog_Header();
        CreateNewCustomer customer_InESTzone = new CreateNewCustomer();
        CreateNewCustomer customer1 = new CreateNewCustomer();
        CreateNewCustomer customer2= new CreateNewCustomer();
        CreateNewCustomer customer3 = new CreateNewCustomer();

        //Create customers
        customer1_fullname = customer1.createCustomerWithNameEmailAddrStreetAddrPhNumZipCode(officePhysicalAddr_zip);
        customer2_fullname = customer2.createCustomerWithNameEmailAddrStreetAddrPhNumZipCode(officePhysicalAddr_zip);
        customer3_fullname = customer3.createCustomerWithNameEmailAddrStreetAddrPhNumZipCode(officePhysicalAddr_zip);
        System.out.println("***** Test Customers: " + customer1_fullname + " | " + customer2_fullname  +  " | " + customer3_fullname  );
    }//createCustomerInESTimezone()

    @Given("I retrieve the zip code of the company's Physical Location address")
    public String retrieveCompanyPhysicalAddrZip() {
        adminMainPage = dashboardPg.goToAdminComponent();
        adminMainPage.clickPreferencesSubComponent();
        OfficeSettingsObjects officeSettings = new OfficeSettingsObjects();
        String zipCode = officeSettings.getPhysicalZipCode();
       officePhysicalAddr_zip = zipCode;
        return zipCode;
    }//retrieveCompanyPhysicalAddrZip()

    @And("I add a subscription and schedule an appointment for each customer")
    public void addSubscriptionAndScheduleServiceAppt() {
        if (customerName_InESTzone != null) {
            addCustomerSubscription(customerName_InESTzone, "Annual Automation Inspection");
            scheduleCustomerServiceAppointment(customerName_InESTzone, "Annual Automation Inspection");
        }

        if (customer1_fullname != null) {
            addCustomerSubscription(customer1_fullname, "Annual Automation Inspection");
            scheduleCustomerServiceAppointment(customer1_fullname, "Annual Automation Inspection");
        }

        if (customer2_fullname != null) {
            addCustomerSubscription(customer2_fullname, "Annual Automation Inspection");
            scheduleCustomerServiceAppointment(customer2_fullname, "Annual Automation Inspection");
        }

        if (customer3_fullname != null) {
            addCustomerSubscription(customer3_fullname, "Annual Automation Inspection");
            scheduleCustomerServiceAppointment(customer3_fullname, "Annual Automation Inspection");
        }
    }//addASubscriptionToEachCustomer()

    @And("I execute the Single Route Optimization Process")
    public void executeSingleRouteOptimizationProcess(){
        Deprecated.scrollToElementJS(singleRoutesPageObjs.lnkRouteActions);
        if(Deprecated.isTextPresent("Route Actions")) {
            singleRoutesPageObjs.clickActions_OptimizeRoute();
            Utilities.acceptAlert();
            singleRoutesPageObjs.executeOptimizeQueueScript();

            Utilities.delay(2000);
            if (singleRoutesPageObjs.isRouteOptimizationProcessComplete()) {
                 singleRoutesPageObjs.clickSeeOptimizedResultsMsg();
                if(Deprecated.isVisible(singleRoutesPageObjs.btnRefresh))
                    singleRoutesPageObjs.clickRefreshBtn();
                singleRoutesPageObjs.closeIncognitoBrowse();
            }
            else if(singleRoutesPageObjs.optimizationCompleteMsg.contains("Failed Optimization")){
                singleRoutesPageObjs.closeIncognitoBrowse();
            }
        }
    }//singleRoutesPageObjs()

    @Then("I delete route from route group {string}")
    public void deleteRouteFromRouteGroup(String routeGroupName) {
        schedulingTab = dashboardPg.goToSchedulingComponent();
        schedulingTab.addScheduleDateToProperties();
        schedulingTab.clickScheduleDay();

        String routeID = route.getRouteID(routeGroupName);
        String groupID = route.getGroupID(routeGroupName);
        System.out.println("Group ID: " + groupID + " | Route ID: " + routeID );
        singleRoutesPageObjs.deleteSingleRoute(routeID, groupID);
        singleRoutesPageObjs.clickDeleteRouteBtn();
    }//deleteRouteFromRouteGroup()


    // -- Methods: Other
    //--------------------------------------------------------
    public void scheduleCustomerServiceAppointment(String  customerName, String serviceType){
        customerCardHeader = new CustomerViewDialog_Header();
        scheduleAppointment = new ScheduleAppt();
        appointmentsTab = new CustomerviewDialog_AppointmentsTab();

        //Today's Schedule
        schedulingTab = dashboardPg.goToSchedulingComponent();
        schedulingTab.addScheduleDateToProperties();
        schedulingTab.clickScheduleDay();

        //Load Customer and Schedule The Appointment
        header.searchCustomerWithName(customerName);
        Deprecated.waitVisible(customerCardHeader.overviewPageTitle,10);

        subscriptionTab = customerCardHeader.goToSubscriptionTab();
        Utilities.delay(1000);
        scheduleAppointment.scheduleAppointmentOnRoute(serviceType);
    }//scheduleCustomerServiceAppointment()

    public void addCustomerSubscription(String customerName, String strServiceType) {
        customerCardHeader = new CustomerViewDialog_Header();
        header.searchCustomerWithName(customerName);
        Deprecated.waitVisible(customerCardHeader.overviewPageTitle,10);

        //Add Service Subscription
        subscriptionTab = customerCardHeader.goToSubscriptionTab();
        subscriptionTab.clickNewSubscription();
        subscriptionTab.selectRecurringServiceType(strServiceType);
        subscriptionTab.selectInitialInvoice("After Initial Completion");

        //Save the Subscription
        customerCardHeader.clickSaveButton();
    }//addCustomerSubscription()

    @Then("I validate the Single Route Optimization Process Completed Successfully")
    public void validateSingleRouteOptimizationProcessCompleted() {
    String optimizationResultsMsg = "";
        if(singleRoutesPageObjs.optimizationCompleteMsg.contains("Route Optimization has finished"))
            optimizationResultsMsg = "Route Optimization has finished!";
        else {
            optimizationResultsMsg = "Failed Optimization";
         }
        result("Route Optimization has finished!",optimizationResultsMsg,"Single Route Optimization Validation","Route Optimization Validation");
    }//validateSingleRouteOptimizationProcessCompleted()

    @Then("I remove customers used in Single Route Optimization Process")
    public void removeCustomersUsedInRouteOptimizationProcess() {
        CreateNewCustomer createCustomer = new CreateNewCustomer();
        customerCardHeader = new CustomerViewDialog_Header();

        //Remove Customers
        if(customerName_InESTzone != null)
            createCustomer.removeCustomer(customerName_InESTzone);

        if(customer1_fullname!= null)
            createCustomer.removeCustomer(customer1_fullname);

        if (customer2_fullname != null)
            createCustomer.removeCustomer(customer2_fullname);

        if (customer3_fullname != null)
            createCustomer.removeCustomer(customer3_fullname);
    }//removeCustomersUsedInRouteOptimizationProcess

    @Then("I validate the Single Route Optimization Process Failed with error message {string}")
    public void validateRouteOptimizationProcessFailed(String errorMessage) {
        String optimizationResultsMsg = "";
        if(singleRoutesPageObjs.optimizationCompleteMsg.contains("Failed Optimization"))
             optimizationResultsMsg =singleRoutesPageObjs.optimizationFailedDetailMsg;

        result("One or more routes failed due to conflicting time zones.",optimizationResultsMsg,"Single Route Optimization Validation","Route Optimization Validation");
    }//validateRouteOptimizationProcessFailed()
}//SingleRoutesTests
