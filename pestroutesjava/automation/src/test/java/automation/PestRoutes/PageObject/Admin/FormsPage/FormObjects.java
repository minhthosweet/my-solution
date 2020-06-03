package automation.PestRoutes.PageObject.Admin.FormsPage;

import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class FormObjects {
	
	public String addFormButton = "//div[text() = '+ Form Template']";
	public String htmlButton = "html";
	public String previewButton = "preview";
	public String deleteButton = "delete";
	public String cancelButton = "cancel";
	public String saveButton = "save";
	
	//Form objects
	public String nameField = "//div[@id='preferenceHeader']/following-sibling::form//input[@name='description']";
	public String requiredApprovalDropdown = "//div[@id='preferenceHeader']/following-sibling::form//select[@name='requireApproval']";
	
	
	
	
	
	
	public void clickFormButton(String needFormName, String needButtonType) {
		Utilities.clickElement("//input[@value = "+needFormName+"]/following-sibling::div[text() = "+needButtonType+"]", ElementType.XPath);
	}

}
