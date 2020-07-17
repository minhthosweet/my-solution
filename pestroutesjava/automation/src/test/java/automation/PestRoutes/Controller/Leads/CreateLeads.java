package automation.PestRoutes.Controller.Leads;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.Leads.LeadsPage;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;

public class CreateLeads extends BaseClass{
	
	CustomerViewDialog_Header header;
	String additialItem = "bed";
	
	String assignto = "   - Office ";
	String source = "Current Customer";
	String contractLength = "12 months";
	String reasonLost = "";
	String dateAssigned = "";
	String value = "1000";
	
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
	List list = new ArrayList<String>();
	
	@Test
	public void test() throws Exception {
		Header header = new Header();
		CustomerViewDialog_Header customerCart = new CustomerViewDialog_Header();
		header.Search_A_Customer(getData("customerName", generalData));
		customerCart.NavigateTo(customerCart.leadsTabInDialog);
		leadCreation();
		validateInvoices();
	}
	
	
	public void leadCreation() {
		header = new CustomerViewDialog_Header();
		leads.clickButton(leads.newButton);
		String[] dropdown = {leads.assignToDropdown,leads.sourceDropdown,
				leads.contractDropdown, leads.serviceTypeDropdown,
				leads.followUpDropdown, leads.frequencyDropdown, leads.seasonalDropdown, leads.billingDropdown};
		String[] dropdownValue = {assignto, source, contractLength, serviceType, followup, frequency, seasonal, billing};
		String[] inputField = {leads.dateAssignedInputField, leads.valueInputField, leads.customStartDateInputField,
				leads.initialQuoteInputField, leads.initialDiscountInputField, leads.recurringInvoiceInputField};
		String[] inputValue = {dateAssigned, value, customStartDate, initialQuote, initialDiscount, recurringServiceQuote};
		for(int i = 0; i < dropdown.length; i++) {
			leads.selectValueFromDropdown(dropdown[i], dropdownValue[i]);
		}
		for(int j = 0; j < inputField.length; j++) {
			leads.setInputField(inputField[j], inputValue[j]);
		}
		
		leads.selectAdditionalItem(leads.addInitialInvoiceTicketItemButton, additialItem);
		leads.selectAdditionalItem(leads.addRecurringInvoiceTicketItemButton, additialItem);
		header.ClickSaveButton();
		
	}
	
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
		
		String[] actualValue = {leads.getValueOfAnElement(leads.initialSubTotalValue),
				leads.getValueOfAnElement(leads.initialTotalValue),
				leads.getValueOfAnElement(leads.recurringSubTotalValue),
				leads.getValueOfAnElement(leads.recurringTotalValue)};
		double[] expectedValue = {initialSubTotal, initialTotal, recurringSubtotal, recurringTotal};
		String[] stepName = {"Initial Subtotal ", "Initial total ", "Recurring subtotal ", "Recurring total "};
		
		for(int i = 0; i < actualValue.length; i++) {
			System.out.println(actualValue[i]+" "+expectedValue[i]);
			result(Double.toString(expectedValue[i]), actualValue[i], stepName[i], "Leads validation");
		}
		
		AssertException.assertFailure(list);
	}
	
	@SuppressWarnings("unchecked")
	private void result(String expected, String actual, String stepName, String testName) {
		if(AssertException.result(expected, actual, stepName).size()>0) {
			list.add(AssertException.result(expected, actual, stepName));
		}
		Reporter.status(stepName,expected, actual, testName);
	}


}
