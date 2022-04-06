package automation.PestRoutes.Controller.Customers.BillingTab;

import automation.PestRoutes.PageObject.CustomerOverview.*;
import automation.PestRoutes.PageObject.CustomerOverview.BillingPage;
import automation.PestRoutes.Utilities.Deprecated;
import io.cucumber.java.en.And;

public class BillingTabTests {
    CustomerViewDialog_Header customerCardHeader;
    BillingPage billingPage;
    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();

    @And("I set Autopay option to {string} for customer,{string} {string}")
    public void setAutopayOption(String strAutopayOption, String firstName, String lastName) throws Exception {
       try {
            if(Deprecated.isTextPresent(firstName + " " + lastName )) {
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

    @And("I Select Credit Card {string} For Auto Pay Using {string}, {string}, {string}, {string}")
    public void automateAddingCreditCardAutoPay(String creditCard, String gateway, String creditCardNumber, String expirationDate, String cvv) {
        billingPage = sameUser.goToBillingTab();
        billingPage.clickAddPaymentMethod();
        billingPage.clickCreditCardButton();
        billingPage.enterNewCardInformation(gateway, creditCardNumber, expirationDate, cvv);
        billingPage.clickBillingInfo();
        billingPage.selectAutoPayOption(creditCard);
    }

    @And("I Select Bank Account For Auto Pay")
    public void automateAddingBankAccountAutoPay() {
        billingPage = sameUser.goToBillingTab();
        billingPage.clickAddPaymentMethod();
        billingPage.clickBankAccountButton();
        billingPage.typeBankName("JP Morgan");
        billingPage.typeRoutingNumber();
        billingPage.typeAccountNumber();
        billingPage.clickSaveBankAccountButton();
        billingPage.clickBillingInfo();
        billingPage.selectAutoPay();
    }

}//BillingTabTests
