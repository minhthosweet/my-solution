package automation.PestRoutes.PageObject.NotesTab;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class NotesPage {
	//Main objects
	public String addNotesButton = "//div[text()='Add Note']";
	public String addTaskButton = "//div[text()='Add Task']";
	public String sendEmailButton = "//div[text()='Send Email']";
	public String sendSmsButton = "//div[text()='Send SMS']";
	public String sendVoiceButton = "//div[text()='Send Voice']";
	public String searchField = "//input[@id='customerContactsSearch']";
	public String savedEntry = "//div[@id='notesDetail']//li/div[1]";
	
	//Add notes objects
	public String notesTypeDropdown = "//select[@name='contactType']";
	public String notesTechVisibilityDropdown = "//select[@name='showTech']";
	public String notesCustomerVisibilityDropdown = "//select[@name='showCustomer']";
	public String notesInputField = "//div[@class='inputSection']//textarea[@name='notes']";
	public String notesSaveButton = "//div[@id='saveCustomerContactButton']";
	public String notesDeleteButton = "//div[@id='deleteCustomerContactButton']";
	public String notesCancelButton = "//div[@id='editCustomerContactButton']";
	public String notesFinishInWindowButton = "//div[@id='editCustomerContactButton'] /following-sibling::div[text()='Finish In Window']";
	
	//Add task objects
	public String taskStatusDropdown = "//form[@id='customerTaskForm']//select[@name='status']";
	public String taskAssignToDropdown = "//form[@id='customerTaskForm']//select[@name='assignedTo']";
	public String taskCategoryDropdown = "//form[@id='customerTaskForm']//select[@name='category']";
	public String taskInputField = "//form[@id='customerTaskForm']//textarea[@name='task']";
	public String taskCancelButton = "//form[@id='customerTaskForm']//div[@name='cancelEditTaskButton']";
	public String taskFinishInWindowButton = "//form[@id='customerTaskForm']//div[text()='Finish In Window']";
	public String taskDeleteButton = "//form[@id='customerTaskForm']//div[@name='deleteTaskButton']";
	public String taskSaveButton = "//form[@id='customerTaskForm']//div[@name='saveTaskButton']";
	
	//Send email objects
	public String emailSubjectInputField = "//input[@name='subject']";
	public String emailCategoryDropdown = "//select[@name='emailCategoryID']";
	public String emailBodyMessage = "//div[@role='application']//p";
	public String emailSendButton = "//form[@id='customerSingleEmailForm']//div[text()='Send']";
	public String emailCancelButton = "//form[@id='customerSingleEmailForm']//div[text()='Cancel']";
	public String emailSendInWindowButton = "//form[@id='customerSingleEmailForm']//div[text()='Finish In Window']";
	
	//Send sms objects
	public String smsInputField = "//textarea[@id='createSMS']";
	public String smsCharactersCount = "//div[@id='smsCharacterCount']";
	public String smsSendButton = "//form[@id='customerSingleSMSForm']//div[text()='Send']";
	public String smsCancelButton = "//form[@id='customerSingleSMSForm']//div[text()='Cancel']";
	public String smsSendInWindowButton = "//form[@id='customerSingleSMSForm']//div[text()='Finish In Window']";
	
	//Send voice objects
	public String voiceMessageDropdown = "//select[@name='voiceMessageID']";
	public String voiceSendButton = "//form[@id='customerSingleCallForm']//div[text()='Send']";
	public String voiceCancelButton = "//form[@id='customerSingleCallForm']//div[text()='Cancel']";
	public String voiceSendInWindowButton = "//form[@id='customerSingleCallForm']//div[text()='Finish In Window']";
	
	
	//Methods
	
	public void clickButton(String needButton) {
		Utilities.waitUntileElementIsVisible(needButton);
		Utilities.clickElement(needButton, ElementType.XPath);
	}
	
	public void setInputField(String needInputField, String needValue) {
		Utilities.waitUntileElementIsVisible(needInputField);
		FindElement.elementByAttribute(needInputField, InputType.XPath).sendKeys(needValue);
	}
	
	public void selectTextFromDropdown(String needDropdown, String needText) {
		Utilities.waitUntileElementIsVisible(needDropdown);
		Utilities.selectValueFromDropDownByValue(needDropdown, needText);
	}
	
	public String getText(String needAttribute) {
		Utilities.waitUntileElementIsVisible(needAttribute);
		return Utilities.getElementTextValue(needAttribute, ElementType.XPath);
	}
	
	public void navigateToNoteType(String needNoteType) {
		Utilities.clickElement("//li[text()='"+needNoteType+"']", ElementType.XPath);
	}

}
