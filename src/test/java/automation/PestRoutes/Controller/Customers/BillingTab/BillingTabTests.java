package automation.PestRoutes.Controller.Customers.BillingTab;

import automation.PestRoutes.PageObject.CustomerOverview.*;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.PageObject.CustomerOverview.BillingPage;
import io.cucumber.java.en.And;
import org.testng.Assert;

public class BillingTabTests {
    CustomerViewDialog_Header customerCardHeader;
    BillingPage billingPage;

    @And("I set Autopay option to {string} for customer,{string} {string}")
    public void setAutopayOption(String strAutopayOption, String firstName, String lastName) throws Exception {
       try {
            if(Utilities.isTextPresent(firstName + " " + lastName )) {
                //Navigate to the Billing Tab
               customerCardHeader = new CustomerViewDialog_Header();
               billingPage = customerCardHeader.goToBillingTab();

                // Load Billing Information Screen
                billingPage.clickBillingIfoLink();

               //Set "AutoPay" option to (No Auto Pay)
               billingPage.selectAutoPayOption(strAutopayOption);

               // Click the "Save Billing Info" link
               billingPage.clickSaveBillingInfoButton();
           }
       }
       catch(Exception e){
           System.out.println("************setAutopayOption(): ERROR occurred. AutoPay option was not set!!!");
           e.printStackTrace();
           throw new Exception(e);
       }
    }// setAutopayOption()

}//BillingTabTests
