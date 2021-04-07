package automation.PestRoutes.Controller.Notes;

import java.util.ArrayList;
import java.util.List;

import automation.PestRoutes.Utilities.*;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.NotesTab.NotesPage;
import automation.PestRoutes.Utilities.FindElement.InputType;

public class NotesValidation extends AppData {
	
	Header mainHeader;
	CustomerViewDialog_Header customerCardHeader;
	NotesPage notes = new NotesPage();
	String notesMessage = Utilities.generateRandomString(8);
	String taskMessage = Utilities.generateRandomString(8);
	List list = new ArrayList<String>();
	@Test
	public void notesTabValidation() throws Exception {
		createNote();
		createTask();
		AssertException.assertFailure(list);
	}
	
	public void createNote() throws Exception {
		navigateToCustomerCard();
		navigateToNotesTab();
		addNotes();
		validateCreation
		("Validate notes creation", notesMessage, "General Notes", notes.notesDeleteButton);
	}
	
	public void createTask() throws InterruptedException {
		addTask();
		validateCreation
		("Validate tasks creation", taskMessage, "Tasks", notes.taskDeleteButton);
	}
	
	private void navigateToCustomerCard() throws Exception {
		mainHeader = new Header();
		mainHeader.searchCustomer_History(getData("customerName", generalData));
	}
	
	private void addNotes() {
		notes.clickButton(notes.addNotesButton);
		notes.selectTextFromDropdown(notes.notesTechVisibilityDropdown, "Visible to Tech");
		notes.selectTextFromDropdown(notes.notesCustomerVisibilityDropdown, "Visible to Customer");
		notes.setInputField(notes.notesInputField, notesMessage);
		notes.clickButton(notes.notesSaveButton);
	}
	
	private void addTask() {
		notes.clickButton(notes.addTaskButton);
		notes.selectTextFromDropdown(notes.taskStatusDropdown, "Completed");
		notes.setInputField(notes.taskInputField, taskMessage);
		notes.clickButton(notes.taskSaveButton);
	}
	
	private void validateCreation
	(String needStepName, String messageType, String needNoteType, String needDeleteButton) throws InterruptedException {
		notes.navigateToNoteType(needNoteType);
		FindElement.elementByAttribute(notes.searchField, InputType.XPath).sendKeys(Keys.CONTROL,"a");
		notes.setInputField(notes.searchField, messageType);
		String searchResult = notes.getText(notes.savedEntry);
		result(messageType, searchResult, needStepName, "Validate Notes Entries");
		notes.clickButton(notes.savedEntry);
		notes.clickButton(needDeleteButton);
		Utilities.acceptAlert();
	}
	
	private void navigateToNotesTab() throws InterruptedException {
		customerCardHeader = new CustomerViewDialog_Header();
		customerCardHeader.navigateTo(customerCardHeader.notesTabInDialog);
	}
	
	@SuppressWarnings("unchecked")
	private void result(String expected, String actual, String stepName, String testName) {
		if(AssertException.result(expected, actual, stepName).size()>0) {
			list.add(AssertException.result(expected, actual, stepName));
		}
		Reporter.status(stepName,expected, actual, testName);
	}

}
