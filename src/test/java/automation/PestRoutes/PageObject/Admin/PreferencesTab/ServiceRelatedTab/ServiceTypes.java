package automation.PestRoutes.PageObject.Admin.PreferencesTab.ServiceRelatedTab;

import automation.PestRoutes.Utilities.Legacy;
import org.openqa.selenium.WebElement;

public class ServiceTypes {
	
	//Service types objects
		public String searchInputField = "//input[@placeholder = 'Search...']";
		public String addNewServiceButton = "//div[text() = '+ Service Type']";
		public String services_DescriptionInputField = "//div[@id='preferenceHeader']/following-sibling::form//input[@placeholder='Description (Required)']";
		public String services_CategoryInputField = "//div[@id='preferenceHeader']/following-sibling::form//input[@placeholder='Category']";
		public String services_AbbreviationInputField = "//div[@id='preferenceHeader']/following-sibling::form//input[@placeholder='Abbreviation']";
		public String globalDropDown = "//div[@id='preferenceHeader']/following-sibling::form//select[@placeholder='Global']";
		public String visibilityDropDown = "//div[@id='preferenceHeader']/following-sibling::form//select[@placeholder='Visibility']";
		public String appointmentFrequencyDropDown = "//select[@name='frequency']";
		public String defaultFollowUpDelayDropDown = "//select[@name='defaultFollowupDelay']";
		public String defaultAppointmentLengthInputField = "//input[@name='defaultLength']";
		public String defaultContractLengthDropDown = "//select[@name='defaultContractLength']";
		public String minimumContractLengthDropDown = "//select[@name='minimumContractLength']";
		public String displayRenewalDateDropDown = "//select[@name='displayRenewalDate']";
		public String renewalFrequencyDropDown = "//select[@name='defaultRenewalFrequency']";
		public String setRenewalDateDropDown = "//select[@name='defaultSetRenewalDateOn']";
		public String defaultServiceChargeInputField = "//input[@name='defaultCharge']";
		public String serviceCommissionInputField = "//input[@name='serviceCommissionRate']";
		public String minimumInitialChargeInputField = "//input[@name='minimumInitialCharge']";
		public String recurring_OneTimeServiceDropDown = "//select[@name='regularService']";
		public String cancelButton = "//div[@id='preferenceHeader']/following-sibling::form//div[text()='cancel']";
		public String saveButton = "//div[@id='preferenceHeader']/following-sibling::form//div[text()='save']";
		
		public void clickEditButton(String needDescription) {
			Legacy.waitVisible("//div[contains (text(), '"+needDescription+"')]/ancestor::form//div[text()='edit']");
			Legacy.clickElement("//div[contains (text(), '"+needDescription+"')]/ancestor::form//div[text()='edit']");
		}
		public void clickAddServiceButton() {
			Legacy.waitVisible(addNewServiceButton);
			Legacy.clickElement(addNewServiceButton);
		}
		public void clickSave() {
			Legacy.clickElement(saveButton);
		}
		public void clickCancel() {
			Legacy.clickElement(cancelButton);
		}
		public void selectFromDropdown(String needXpath, String needValue) {
			Legacy.selectByText(needXpath, needValue);
		}
		
		/*
		 * Setter methods
		 */
		
		public void setSearch(String needText) {
			Legacy.waitVisible(searchInputField);
			Legacy.locate(searchInputField).sendKeys(needText);
		}
		public void setDescription(String needDescription) {
			Legacy.waitVisible(services_DescriptionInputField);
			Legacy.locate(services_DescriptionInputField).sendKeys(needDescription);
		}
		public void setCategory(String needCategory) {
			Legacy.waitVisible(services_CategoryInputField);
			Legacy.locate(services_CategoryInputField).sendKeys(needCategory);
		}
		public void setAbbreviation(String needAbbreviation) {
			Legacy.waitVisible(services_AbbreviationInputField);
			Legacy.locate(services_AbbreviationInputField).sendKeys(needAbbreviation);
		}
		public void setAppointLegnth(String needMinutes) {
			Legacy.locate(defaultAppointmentLengthInputField).sendKeys(needMinutes);
		}
		public void setServiceCharge(String needAmount) {
			Legacy.locate(defaultServiceChargeInputField).sendKeys(needAmount);
		}
		public void setServiceCommission(String needAmount) {
			Legacy.locate(serviceCommissionInputField).sendKeys(needAmount);
		}
		public void setMinInitialCharge(String needAmount) {
			Legacy.locate(minimumInitialChargeInputField).sendKeys(needAmount);
		}
		
		/*
		 * Getter methods
		 */
		
		public WebElement getDescription(String needText) {
			return Legacy.locate("//div[contains (text(), '"+needText+"')]");
		}

}
