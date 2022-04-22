package automation.PestRoutes.Controller.Leads;

import automation.PestRoutes.Utilities.Data.*;
import automation.PestRoutes.Utilities.Legacy;
import automation.PestRoutes.Utilities.Report.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.Leads.LeadsPage;

import java.io.IOException;

public class CreateLeads extends AppData {
	
	CustomerViewDialog_Header header;
	String additialItem = "bed";
	//String assignto = "Automation User - Office";
	//String source = "Customer Referral";
	String contractLength = "12 months";
	String reasonLost = "";
	String dateAssigned = "";
	public String lead_value = "1000";
	String serviceType = "Quarterly";
	String followup = "2 Months";
	String frequency = "Alternate Monthly";
	String seasonal = "Year-Round Service";
	String billing = "After Each Completed Service (Normal)";
	
	String customStartDate = "";
	
	String initialQuote = "100";
	String initialDiscount = "20";
	
	String recurringServiceQuote = "120";
	
	LeadsPage leads = new LeadsPage();

	@Test
	public void test() throws Exception {
		Header header = new Header();
		CustomerViewDialog_Header customerCart = new CustomerViewDialog_Header();
		header.searchCustomer_History(getData("customerName", generalData));
		customerCart.navigateTo(customerCart.leadsTabInDialog);
		leadCreation();
		validateInvoices();
	}

	@And("I create a new lead")
	public void leadCreation() throws InterruptedException, IOException {
		header = new CustomerViewDialog_Header();
		CustomerViewDialog_Header customerCart = new CustomerViewDialog_Header();
		customerCart.navigateTo(customerCart.leadsTabInDialog);
		leads.clickButton(leads.newButton);
		String[] dropdown = {leads.assignToDropdown,leads.sourceDropdown,
				leads.contractDropdown, leads.serviceTypeDropdown,
				leads.followUpDropdown, leads.frequencyDropdown, leads.seasonalDropdown, leads.billingDropdown};
		String[] dropdownValue = {getData("assignto", generalData), getData("source", generalData), contractLength, serviceType, followup, frequency, seasonal, billing};
		String[] inputField = {leads.dateAssignedInputField, leads.valueInputField, leads.customStartDateInputField,
				leads.initialQuoteInputField, leads.initialDiscountInputField, leads.recurringInvoiceInputField};
		String[] inputValue = {dateAssigned, lead_value, customStartDate, initialQuote, initialDiscount, recurringServiceQuote};
		for(int i = 0; i < dropdown.length; i++) {
			leads.selectValueFromDropdown(dropdown[i], dropdownValue[i]);
		}
		for(int j = 0; j < inputField.length; j++) {
			leads.setInputField(inputField[j], inputValue[j]);
		}
		
		leads.selectAdditionalItem(leads.addInitialInvoiceTicketItemButton, additialItem);
		leads.selectAdditionalItem(leads.addRecurringInvoiceTicketItemButton, additialItem);
		header.clickSaveButton();
		Legacy.waitVisible(leads.newButton);
		leads.clickButton(leads.addLeadsNotesButton);
		leads.setInputField(leads.notesInputField, getData("source", generalData));
		leads.clickButton(leads.saveNotesButton);
	}

	@Then("I validate lead creation invoices")
	public void validateInvoices() {
		String initialTax = (leads.getValueOfAnElement(leads.initialTaxValue)).replace("$", "");
		double initialItemValue = Double.parseDouble
				(leads.getValueOfAnElementByAttribute(leads.initialTicketItemValue));
		double initialSubTotal = (Double.parseDouble(initialQuote)+initialItemValue)-Double.parseDouble(initialDiscount);
		double initialTotal = initialSubTotal+Double.parseDouble(initialTax);
		
		String recurringTax = (leads.getValueOfAnElement(leads.recurringTaxValue)).replace("$", "");
		double recurringItemValue = Double.parseDouble
				(leads.getValueOfAnElementByAttribute(leads.recurringTicketItemValue));
		double recurringSubtotal = Double.parseDouble(recurringServiceQuote)+recurringItemValue;
		double recurringTotal = recurringSubtotal+Double.parseDouble(recurringTax);
		
		String[] actualValue = {leads.getValueOfAnElement(leads.initialSubTotalValue).replace("$", ""),
				leads.getValueOfAnElement(leads.initialTotalValue).replace("$", ""),
				leads.getValueOfAnElement(leads.recurringSubTotalValue).replace("$", ""),
				leads.getValueOfAnElement(leads.recurringTotalValue).replace("$", "")};
		double[] expectedValue = {initialSubTotal, initialTotal, recurringSubtotal, recurringTotal};
		String[] stepName = {"Initial Subtotal ", "Initial total ", "Recurring subtotal ", "Recurring total "};
		
		for(int i = 0; i < actualValue.length; i++) {
			System.out.println(actualValue[i]+" "+expectedValue[i]);
			result(Double.toString(expectedValue[i]), actualValue[i], stepName[i], "Leads validation");
		}
	}

	@Then("I convert a successful lead to subscription and I verify it in the subscriptions tab")
		public void convertLeadToSubscription() throws InterruptedException {
		header = new CustomerViewDialog_Header();
		CustomerViewDialog_Header customerCart = new CustomerViewDialog_Header();
		String subscriptionTitle= "//li[@subscriptionid]//div[text()='"+ serviceType +"']";
		Legacy.waitVisible(leads.newButton);
		leads.clickButton(leads.convertToLeadButton);
		customerCart.navigateTo(customerCart.subscriptionTabInDialog);
		Legacy.waitVisible(subscriptionTitle);
		String actualServiceTypeConverted = Legacy.getElementTextValue(subscriptionTitle);
		result(serviceType, actualServiceTypeConverted, "serviceType Validation", "");
	}
	
	@SuppressWarnings("unchecked")
	private void result(String expected, String actual, String stepName, String testName) {
		if(AssertException.result(expected, actual, stepName).size()>0) {
			AssertException.list.add(AssertException.result(expected, actual, stepName));
		}
		Reporter.status(stepName,expected, actual, testName);
	}

}
