package automation.PestRoutes.PageObject.CustomerOverview;

public class CustomerViewDialog_InvoicesTab {
	
	public String newInvoiceButton = "//li[text()='+ New Invoice']";
	public String dateInputField_NewInvDialog = "//form[@id='newInvoiceParams']/input[1]";
	public String amountInputField_NewInvDialog = "//form[@id='newInvoiceParams']/input[2]";
	public String serviceTypeDropdown_NewInvDialog = "//select[@name='serviceID']";
	public String createButton_NewInvDialog = "//span[text()='Create']";
	public String cancelButton_NewInvDialog = "//span[text()='Create']/parent::button/preceding-sibling::button/span";
	public String invoiceTitle = "//div[@id='monthClosed']/preceding-sibling::h3";

}
