package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import static automation.PestRoutes.Utilities.Utilities.*;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class CustomerViewDialog_SubscriptionTab extends BasePage {

	CustomerViewDialog_InfoTab infoTab;
	CustomerViewDialog_Header customerDialogHeader;
	WebDriverWait wait = new WebDriverWait(driver, 5);

	//********************Objects in subscription tab********************
	//Status fields
	public String statusText = "//div[@id='subStatusBox']//div[@id='SubStatus']";
	private By subscriptionStatus = By.xpath("//div[@id='subStatusBox']//div[@id='SubStatus']");
	public String ActivateDeactivateButton = "//div[@id='subStatusBox']/span[@id='SubStatusAction']";
	private By activateDeactivateButton = By.xpath("//div[@id='subStatusBox']/span[@id='SubStatusAction']");
	public String contractValue = "//span[contains(text(),'Contract Value')]";
	public String cancellationCategoryDropdown_cancelSubscriptionDialog = "//select[@id='cancelSubCategory']";
	public String cancelNotesInputField = "//textarea[@id='cancelSubNotes']";
	public String freezeSubscriptionButton = "//span[text()='Freeze Subscription']";
	private By freezeCancelSubscriptionButton = By.xpath("//span[text()='Freeze Subscription']");
	public String cancelButton_cancelSubscriptionDialog = "//span[text()='Freeze Subscription']/parent::button/preceding-sibling::button[1]/span[text()='Cancel']";
	private By  chkboxEligibleForConsolidation = By.xpath("//*[@id='eligibleForConsolidation']");

	//***Sales Info/Billing Options objects***
	public String newSubscriptionButton = "//div[text()=  '+ New Subscription']";
	private By newSubscription = By.xpath("//div[text()=  '+ New Subscription']");
	public String first_SalesRepDropdown = "//h3[text()=  'Sales Info']/following-sibling::select[@name='creditTo']";
	public String second_SalesRepDropdown = "//h3[text()=  'Sales Info']/following-sibling::select[@name='creditTo3']";
	public String third_SalesRepDropdown = "//h3[text()=  'Sales Info']/following-sibling::select[@name='creditTo4']";
	public String sourceDropdown = "//h3[text()=  'Sales Info']/following-sibling::select[@name='sourceID']";
	public String soldDateField = "//input[@name='subDateAdded']";
	public String contractLengthDropdown = "//h3[text()=  'Sales Info']/following-sibling::select[@name='agreementLength']";
	public String expirationDateInputField = "//input[@name='expirationDate']";
	private By fieldExpirationDate = By.xpath("//input[@name='expirationDate']");
	public String renewalDateField = "//input[@name='renewalDate']";
	private By fieldRenewalDate = By.xpath("//input[@name='renewalDate']");
	public String setRenewalDateDropdown = "//select[@name='setRenewalDateOn']";
	public String renewalFrequencyDropdown = "//select[@name='renewalFrequency']";
	public String pO_NumberInputField = "//input[@name='poNumber']";
	public String notesInputField = "//textarea[@name='subNotes']";
	public String subscriptionFlagDropdown = "//select[@id='subscriptionGenericFlags']";
	public String billingAccountField = "//h3[text()='Billing Options']/following-sibling::input[@name='displayBillToAccountID']";
	public String proceedAndTransferButton = "//div/button/span[text()='Proceed and Transfer balances']";
	public String mergeBillingAccountField = "//h2[text()='Billing Account']/following-sibling::input";
	public String mergeBillingAccountSelectButton = "//button/span[text()='Select']";
	public String mergeBillingAccountCancelButton = "//button/span[text()='Select']/parent::button/preceding-sibling::button/span[text()='Cancel']";
	public String autoPayProfileDropdown = "//h3[text()='Billing Options']/following-sibling::select[@name='autoPayPaymentProfileID']";
	public String netBillingDaysInputField = "//input[@name='netBillingDaysDisplay']";
	public String billingFrequencyDropdown = "//select[@name='billingFrequency']";
	public String billingInitialInvoiceDropdown = "//select[@name='initialInvoice']";
	private By initialInvoiceDropDown = By.xpath("//select[@name='initialInvoice']");
	public String selectedBillingInitialInvoiceDropdown = "//select[@name='initialInvoice']/option[@selected]";
	public String initialBillingDateInputField = "//input[@name='initialBillingDate']";
	public String editCustomInitialScheduleButton = "//div[@id='editInitialCustomScheduleButton']";
	public String editCustomRecurringScheduleButton = "//div[@id='editCustomScheduleButton']";
	public String selectedCustomScheduleOption = "//select[@name='initialInvoice']/option[@selected='SELECTED' and text()='Custom Schedule']";

	//Billing Frequency DropDown objects
	public String billingFrequency_Renewal = "Renewal";

	//Billing Initial Invoice DropDown Objects
	public String billing_initialBillingDate = "On Initial Billing Date";
	//***Recurring Services objects***
	public String serviceTypeDropdown = "//select[@name='recurringServiceType']";
	private By serviceTypeDropDown = By.xpath("//select[@name='recurringServiceType']");
	public String serviceFrequencyDropdown = "//select[@name='frequency']";
	public String serviceDurationDropdown = "//select[@name='duration']";
	public String callAheadDropdown = "//select[@name='callAhead']";
	public String seasonalDropdown = "//select[@name='isSeasonal']";
	//***Schedule Options objects***
	public String autoScheduleDropdown = "//select[@name='autoSchedule']";
	public String initialFollowupDropdown = "//select[@name='followupDelay']";
	public String customDateInputField = "//input[@name='customDate']";
	private By customDateField = By.xpath("//input[@name='customDate']");
	public String routineRegionDropdown = "//select[@name='regionID']";
	public String preferredTechDropdown = "//select[@name='preferredTech']";
	public String preferredDayDropdown = "//select[@name='preferredDays']";
	public String preferredTimeDropdown = "//select[@name='hasPreferredTime']";
	//***Appointment times slots objects***
	public String firstUpcomingAppointment = "//div[@id='scheduleWrapper']/div[1]/h4";
	public String secondUpcomingAppointment = "//div[@id='scheduleWrapper']/div[2]/h4";
	public String thirdUpcomingAppointment = "//div[@id='scheduleWrapper']/div[3]/h4";
	public String fourthUpcomingAppointment = "//div[@id='scheduleWrapper']/div[4]/h4";
	public String fifthUpcomingAppointment = "//div[@id='scheduleWrapper']/div[5]/h4";
	public String sixthUpcomingAppointment = "//div[@id='scheduleWrapper']/div[6]/h4";
	public String seventhUpcomingAppointment = "//div[@id='scheduleWrapper']/div[7]/h4";
	public String eighthUpcomingAppointment = "//div[@id='scheduleWrapper']/div[8]/h4";

	//***Initial invoice template objects***
	public String initialInvoice_AddTicketItemButton = "//h3[text()='Initial Invoice Template']/following-sibling::div[text()='+ Add Ticket Item']";
	public String initialQuoteInputField = "//div[@id= 'subscriptionAccordion']//div[text()='Initial Quote']/following-sibling::input[@name='serviceCharge']";
	private By initialQuoteField = By.xpath("//div[@id= 'subscriptionAccordion']//div[text()='Initial Quote']/following-sibling::input[@name='serviceCharge']");
	public String initialDiscountInputField = "//div[text()=  'Initial Discount']/following-sibling::input[@value]";
	private By initialDiscountField = By.xpath("//div[text()=  'Initial Discount']/following-sibling::input[@value]");
	public String initialSubTotalValue = "//div[@id='initialTicket']//div[@class='ticketSummary']/div[2]";
	public String initialTaxValue = "//div[@id='initialTicket']//div[@class='ticketSummary']/div[4]";
	public String initialTotalValue = "//div[@id='initialTicket']//div[@class='ticketSummary']/div[6]";
	private By initialInvoiceTotal = By.xpath("//div[@id='initialTicket']//div[@class='ticketSummary']/div[@class='ticketTotal totalBoxValue']");
	//***Recurring invoice template objects***
	public String recurringInvoice_AddTicketItemButton = "//h3[text()='Recurring Invoice Template']/following-sibling::div[text()='+ Add Ticket Item']";
	public String standardProductionButton = "//div[@id='recurringServices']//span[text()='Standard Production']";
	public String customProductionButton = "//div[@id='recurringServices']//span[text()='Custom Production']";
	public String recurringSubTotalValue = "//div[@id='recurringServices']//div[@class='ticketSummary']/div[2]";
	public String recurringTaxValue = "//div[@id='recurringServices']//div[@class='ticketSummary']/div[4]";
	public String recurringTotalValue = "//div[@id='recurringServices']//div[@class='ticketSummary']/div[6]";

	//Custom Schedule Tab Objects
	public String finishEditingCustomScheduleButton = "//span[text()='Finished Editing Custom Schedule']";

	//Sales Info Objects
	public String salesRepDropdown = "//h3[text()=  'Sales Info']/following-sibling::select[@name='creditTo']";
	private By activeSubscription = By.xpath("//div[@id='subscriptionPanel']//li[contains(@class,'appleMenuActive')]");

	public final String SUBSCRIPTION_STATUS_ACTIVE = "Active";
	public final String SUBSCRIPTION_STATUS_FROZEN = "Frozen";

	//********************Functional methods by objects********************
	/*
	 * Click actions
	 * Below methods click or select objects
	 */
	public void clickNewSubscriptionButton() {
		Utilities.clickElement(newSubscriptionButton, ElementType.XPath);
		//Optimized For Encapsulation Below via clickNewSubscription() Using A Private Modifier With By Class
		//click(newSubscriptionButton); Optimize Later With A Private Modifier & By Class
	}

	public void clickNewSubscription() { click(newSubscription); }

	public void clickButton(String needButton) {
		Utilities.waitUntileElementIsVisible(needButton);
		Utilities.clickElement(needButton, ElementType.XPath);
	}

	public void clickActivateDeActivateButton() {
		Utilities.waitUntileElementIsVisible(ActivateDeactivateButton);
		Utilities.clickElement(ActivateDeactivateButton, ElementType.XPath);
	}

	public void clickFreezeSubscriptionButton() {
		Utilities.waitUntileElementIsVisible(freezeSubscriptionButton);
		Utilities.clickElement(freezeSubscriptionButton, ElementType.XPath);
	}

	public void clickCancelButton_CancenSubscriptionDialog() {
		Utilities.clickElement(cancelButton_cancelSubscriptionDialog, ElementType.XPath);
	}

	public void clickSubscription(String subscriptionID) {
		Utilities.waitUntileElementIsVisible("//li[@subscriptionid='" + subscriptionID + "']");
		Utilities.clickElement("//li[@subscriptionid='" + subscriptionID + "']", ElementType.XPath);
	}

	public void clickEditCustomInitialScheduleButton() {
		Utilities.waitUntileElementIsVisible(editCustomInitialScheduleButton);
		Utilities.clickElement(editCustomInitialScheduleButton, ElementType.XPath);
	}

	public void clickEditCustomRecurringScheduleButton() {
		Utilities.waitUntileElementIsVisible(editCustomRecurringScheduleButton);
		Utilities.clickElement(editCustomRecurringScheduleButton, ElementType.XPath);
	}

	public void clickRecurringSubTotalValue() {
		Utilities.clickElement(recurringSubTotalValue, ElementType.XPath);
	}

	public void clickSpecificDateButton_initialCustomSchedule() {
		Utilities.waitUntileElementIsVisible("//div[@scheduletype='3']//h4[text()='" + Utilities.getCurrentMonth() + "']/following-sibling::div[contains(text(),'Specific Date')]");
		Utilities.clickElement("//div[@scheduletype='3']//h4[text()='" + Utilities.getCurrentMonth() + "']/following-sibling::div[contains(text(),'Specific Date')]", ElementType.XPath);
	}

	public void clickDayOfTheWeekButton_recurringCustomSchedule() {
		Utilities.waitUntileElementIsVisible("//h4[text()='" + Utilities.getMonthsInFuture(1) + "']/following-sibling::div[text()='+ Day of Week']");
		Utilities.clickElement("//h4[text()='" + Utilities.getMonthsInFuture(1) + "']/following-sibling::div[text()='+ Day of Week']", ElementType.XPath);
	}

	public void clickSpecificDateButton_recurringCustomSchedule() {
		Utilities.waitUntileElementIsVisible("//h4[text()='" + Utilities.getCurrentMonth() + "']/following-sibling::div[text()='+ Day of Week']/preceding-sibling::div[text()='+ Specific Date']");
		Utilities.clickElement("//h4[text()='" + Utilities.getCurrentMonth() + "']/following-sibling::div[text()='+ Day of Week']/preceding-sibling::div[text()='+ Specific Date']", ElementType.XPath);
	}

	public void selectCurrentDateSpecificDate_recurringCustomSchedule(String needCurrentDate) {
		Utilities.waitUntileElementIsVisible("//h4[text()='" + Utilities.getCurrentMonth() + "']/parent::div//select//option[text()='" + needCurrentDate + "']");
		Utilities.clickElement("//h4[text()='" + Utilities.getCurrentMonth() + "']/parent::div//select//option[text()='" + needCurrentDate + "']", ElementType.XPath);
	}

	public void selectDayOfTheWeek(String needWeek, String needDay) {
		Utilities.waitUntileElementIsVisible("//h4[text()='" + Utilities.getMonthsInFuture(1) + "']/parent::div//select[@name='ordinal']/option");
		Utilities.selectValueFromDropDownByValue("//h4[text()='" + Utilities.getMonthsInFuture(1) + "']/parent::div//select[@name='ordinal']", needWeek);
		Utilities.waitUntileElementIsVisible("//h4[text()='" + Utilities.getMonthsInFuture(1) + "']/parent::div//select[@name='dayOfWeek']/option");
		Utilities.selectValueFromDropDownByValue("//h4[text()='" + Utilities.getMonthsInFuture(1) + "']/parent::div//select[@name='dayOfWeek']", needDay);
	}

	public void clickFinishEditingSchedule() {
		Utilities.waitUntileElementIsVisible(finishEditingCustomScheduleButton);
		Utilities.clickElement(finishEditingCustomScheduleButton, ElementType.XPath);
	}

	public void selectCurrentDate_CustomSchedule() {
		FindElement.elementByAttribute("//div[@scheduletype='3']//h4[text()='" + Utilities.getCurrentMonth() + "']/following-sibling::div//select", InputType.XPath).sendKeys(Utilities.getCurrentDate());
	}

	public void selectCancellationCategory(String needCategory) {
		Utilities.waitUntileElementIsVisible(cancellationCategoryDropdown_cancelSubscriptionDialog);
		Utilities.selectValueFromDropDownByValue(cancellationCategoryDropdown_cancelSubscriptionDialog, needCategory);
	}

	public void selectSalesRep(String needSalesRap) {
		FindElement.elementByAttribute("//h3[text()=  'Sales Info']/following-sibling::select[@name='creditTo']", InputType.XPath).sendKeys(needSalesRap);
	}

	public void selectSalesRep2(String needSalesRap) {
		Utilities.selectValueFromDropDownByValue(second_SalesRepDropdown, needSalesRap);
	}

	public void selectSalesRep3(String needSalesRap) {
		Utilities.selectValueFromDropDownByValue(third_SalesRepDropdown, needSalesRap);
	}

	public void selectSource(String needSource) {
		Utilities.selectValueFromDropDownByValue(sourceDropdown, needSource);
	}

	public void selectContractLength(String needContractLength) {
		Utilities.selectValueFromDropDownByValue(contractLengthDropdown, needContractLength);
	}

	public void selectSetRenewalDate(String needOption) {
		Utilities.waitUntileElementIsVisible(setRenewalDateDropdown);
		Utilities.selectValueFromDropDownByValue(setRenewalDateDropdown, needOption);
	}

	public void selectRenewalFrequency(String needOption) {
		Utilities.waitUntileElementIsVisible(renewalFrequencyDropdown);
		Utilities.selectValueFromDropDownByValue(renewalFrequencyDropdown, needOption);
	}

	public void selectBillingFrequency(String needBillingFrequency) {
		Utilities.selectValueFromDropDownByValue(billingFrequencyDropdown, needBillingFrequency);
	}

	public void selectServiceType(String needServiceType) throws Exception {
		if (!Utilities.isPresent(serviceTypeDropdown)){
			Utilities.waitUntileElementIsVisible(serviceTypeDropdown);
		}
		Utilities.selectValueFromDropDownByValue(serviceTypeDropdown, needServiceType);
		//Optimized For Encapsulation Below via selectRecurringServiceType() By Passing In A String & Using A Private Modifier With By Class
	}

	public void selectRecurringServiceType(String serviceType) {
		selectFromDropDown(serviceType, serviceTypeDropDown);
	}

	public List<String> getRecurringServiceType() {
		return getAllSelectedOptionsFromDropDown(serviceTypeDropDown);
	}

	public void selectServiceFrequency(String needServiceFrequency) {
		Utilities.selectValueFromDropDownByValue(serviceFrequencyDropdown, needServiceFrequency);
	}

	public void selectServiceDuration(String needDuration) {
		Utilities.selectValueFromDropDownByValue(serviceDurationDropdown, needDuration);
	}

	public void selectCallAheadOption(String needOption) {
		Utilities.selectValueFromDropDownByValue(callAheadDropdown, needOption);
	}

	public void selectSeasonalOption(String needOption) {
		Utilities.selectValueFromDropDownByValue(seasonalDropdown, needOption);
	}

	public void selectAutoScheduleOption(String needOption) {
		Utilities.selectValueFromDropDownByValue(autoScheduleDropdown, needOption);
	}

	public void selectInitialFollowupOption(String needOption) {
		Utilities.selectValueFromDropDownByValue(initialFollowupDropdown, needOption);
	}

	public void selectRoutineRegionOption(String needOption) {
		Utilities.selectValueFromDropDownByValue(routineRegionDropdown, needOption);
	}

	public void selectPreferTechOption(String needOption) {
		Utilities.selectValueFromDropDownByValue(preferredTechDropdown, needOption);
	}

	public void selectPreferredDayOption(String needOption) {
		Utilities.waitUntileElementIsVisible(preferredDayDropdown);
		Utilities.selectValueFromDropDownByValue(preferredDayDropdown, needOption);
	}

	public void selectPrefferedTimeOption(String needOption) {
		Utilities.selectValueFromDropDownByValue(preferredTimeDropdown, needOption);
	}

	public void selectAdditionalItem_ToInitialInvoice(String needItem) {
		Utilities.scrollToElementJS(initialInvoice_AddTicketItemButton);
		Utilities.clickElement(initialInvoice_AddTicketItemButton, ElementType.XPath);
		if (SystemUtils.IS_OS_LINUX) {
			Utilities.acceptAlert();
		}
		Utilities.waitUntileElementIsVisible("//span[text()=  '" + needItem + "']");
		//Utilities.scrollToElementJS("//span[text()=  '" + needItem + "']");
		delay(1000);
//		Utilities.waitUntileElementIsVisible("//span[text()=  '" + needItem + "']");
		Utilities.clickElement("//span[text()=  '" + needItem + "']", ElementType.XPath);
		try{
			Utilities.waitUntileElementIsVisible("//div[text()='"+needItem+"']");
		}catch (Exception e){
			Utilities.clickElement(initialInvoice_AddTicketItemButton, ElementType.XPath);
			if (SystemUtils.IS_OS_LINUX) {
				Utilities.acceptAlert();
			}
			Utilities.waitUntileElementIsVisible("//span[text()=  '" + needItem + "']");
			Utilities.scrollToElementJS("//span[text()=  '" + needItem + "']");
			delay(1000);
			Utilities.clickElement("//span[text()=  '" + needItem + "']", ElementType.XPath);
		}
		Utilities.waitUntileElementIsVisible("//div[text()='"+needItem+"']");
	}

	public void selectAdditionalItem_ToRecurringInvoice(String needItem) throws InterruptedException {
		Thread.sleep(500);
		Utilities.scrollToElementJS(recurringInvoice_AddTicketItemButton);
		Utilities.waitUntileElementIsVisible(recurringInvoice_AddTicketItemButton);
		Utilities.clickElement(recurringInvoice_AddTicketItemButton, ElementType.XPath);
		if (SystemUtils.IS_OS_LINUX) {
			Utilities.acceptAlert();
		}
		Utilities.scrollToElementJS("//span[text()=  '" + needItem + "']");
		Utilities.waitUntileElementIsVisible("//span[text()=  '" + needItem + "']");
		Utilities.clickElement("//span[text()=  '" + needItem + "']", ElementType.XPath);
	}

	public void clearCustomDate() {
		Utilities.waitUntileElementIsVisible(customDateInputField);
		FindElement.elementByAttribute(customDateInputField, InputType.XPath).clear();
	}

	/*
	 * Setter methods
	 * Below methods insert values in the object input field
	 */

	public void setCancelSubscriptionNotes(String needNotes) {
		FindElement.elementByAttribute(cancelNotesInputField, InputType.XPath).sendKeys(needNotes);
	}

	public void setSoldDate(String needSoldDate) {
		FindElement.elementByAttribute(soldDateField, InputType.XPath).sendKeys(needSoldDate);
	}

	public void setExpDate(String needExpDate) {
		FindElement.elementByAttribute(expirationDateInputField, InputType.XPath).clear();
		FindElement.elementByAttribute(expirationDateInputField, InputType.XPath).sendKeys(needExpDate);
	}

	public void selectSubscriptionFlag(String needSubscriptionFlag) {
		Utilities.waitUntileElementIsVisible(subscriptionFlagDropdown);
		Utilities.selectValueFromDropDownByValue(subscriptionFlagDropdown, needSubscriptionFlag);
	}

	public void setInitialBillingDate(String needInitialBillingDate) {
		Utilities.waitUntileElementIsVisible(initialBillingDateInputField);
		FindElement.elementByAttribute(initialBillingDateInputField, InputType.XPath).clear();
		FindElement.elementByAttribute(initialBillingDateInputField, InputType.XPath).sendKeys(needInitialBillingDate);
	}

	public void setRenewalDate(String needDate) {
		FindElement.elementByAttribute(renewalDateField, InputType.XPath).sendKeys(Keys.CONTROL, "a");
		FindElement.elementByAttribute(renewalDateField, InputType.XPath).sendKeys(needDate);
	}

	public void set_PO_Number(String needPoNum) {
		FindElement.elementByAttribute(pO_NumberInputField, InputType.XPath).sendKeys(needPoNum);
	}

	public void setSubscriptionNotes(String needNotes) {
		FindElement.elementByAttribute(notesInputField, InputType.XPath).sendKeys(needNotes);
	}

	public void setAutoPayProfileDropdown() {
		Utilities.waitUntileElementIsVisible(autoPayProfileDropdown);
		Utilities.selectValueFromDropDownByIndex(autoPayProfileDropdown, 1);

	}

	public void setMergeBillingAccountField(String needMergeCustomerName) throws InterruptedException {
		Utilities.waitUntileElementIsVisible(mergeBillingAccountField);
		FindElement.elementByAttribute(mergeBillingAccountField, InputType.XPath).sendKeys(needMergeCustomerName);
		FindElement.elementByAttribute(mergeBillingAccountField, InputType.XPath).sendKeys(Keys.ARROW_DOWN);
		try {
			Utilities.waitUntileElementIsVisible("//li[@role='presentation']//span[contains(text(),'"+needMergeCustomerName+"')]", 10);
			Utilities.clickElement("//li[@role='presentation']//span[contains(text(),'"+needMergeCustomerName+"')]", ElementType.XPath);
		} catch (Exception e) {
			FindElement.elementByAttribute(mergeBillingAccountField, InputType.XPath).sendKeys(Keys.ARROW_DOWN);
			Utilities.clickElement("//li[@role='presentation']//span[contains(text(),'"+needMergeCustomerName+"')]", ElementType.XPath);
		}
	}

	public void setNetBillingDays(String needBillingDays) {
		FindElement.elementByAttribute(netBillingDaysInputField, InputType.XPath).sendKeys(needBillingDays);
	}

	public void setCustomDate(String needCustomDate) {
		Utilities.waitUntileElementIsVisible(customDateInputField);
		FindElement.elementByAttribute(customDateInputField, InputType.XPath).sendKeys(needCustomDate);
		FindElement.elementByAttribute(customDateInputField, InputType.XPath).sendKeys(Keys.ENTER);
		//Optimized For Encapsulation Below via selectCustomDate() By Passing In A String & Using A Private Modifier With By Class
	}

	public void selectCustomDate(String customDate) {
		type(customDate, customDateField);
		find(customDateField).sendKeys(Keys.ENTER);
	}

	public void setInitialServiceQuote(String needAmount) throws InterruptedException {
		driver.findElement(By.xpath(initialQuoteInputField)).sendKeys(Keys.CONTROL, "a");
//		Utilities.highLight(initialQuoteInputField);
		driver.findElement(By.xpath(initialQuoteInputField)).sendKeys(Keys.DELETE);
//		if (SystemUtils.IS_OS_LINUX) {
//			Thread.sleep(500);
//			FindElement.elementByAttribute(initialQuoteInputField, InputType.XPath).sendKeys(Keys.DELETE);
//			Thread.sleep(1000);
//		}
		driver.findElement(By.xpath(initialQuoteInputField)).sendKeys(needAmount);
//		FindElement.elementByAttribute(initialQuoteInputField, InputType.XPath).sendKeys(needAmount);
		//Optimize Later via Below typeInitialQuote() By Passing In A String & Using A Private Modifier With By Class
	}

	public void setInitialServiceDiscount(String needAmount) throws InterruptedException {
		Utilities.scrollToElementJS(initialTotalValue);
		Utilities.waitUntileElementIsVisible(initialTotalValue);
		FindElement.elementByAttribute(initialDiscountInputField, InputType.XPath).clear();
		Utilities.highLight(initialDiscountInputField);
		if (SystemUtils.IS_OS_LINUX) {
			Thread.sleep(500);
			FindElement.elementByAttribute(initialQuoteInputField, InputType.XPath).sendKeys(Keys.DELETE);
			Thread.sleep(500);
		}
		FindElement.elementByAttribute(initialDiscountInputField, InputType.XPath).sendKeys(needAmount);
		Utilities.clickElement(initialTotalValue, ElementType.XPath);
		//Optimize Later via Below typeInitialDiscount() By Passing In A String & Using A Private Modifier With By Class
	}

	public void setServiceQuote(String needService, String needAmount) throws InterruptedException {
		Utilities.waitUntileElementIsVisible("//div[text()= '" + needService + "']/parent::div//input[@name='serviceCharge']");
		Utilities.highLight("//div[text()= '" + needService + "']/parent::div//input[@name='serviceCharge']");
		if (SystemUtils.IS_OS_LINUX) {
			Thread.sleep(500);
			FindElement.elementByAttribute("//div[text()= '" + needService + "']/parent::div//input[@name='serviceCharge']", InputType.XPath).sendKeys(Keys.DELETE);
			Thread.sleep(500);
		}
		FindElement.elementByAttribute("//div[text()= '" + needService + "']/parent::div//input[@name='serviceCharge']", InputType.XPath).sendKeys(needAmount);
	}

	public void setAdditionalItemAmount(String needItemName, String needAmount) {
		Utilities.highLight("//h3[text()='Recurring Invoice Template']/following-sibling::div/div[text()='" + needItemName + "']/following-sibling::div/input");
		FindElement.elementByAttribute("//h3[text()='Recurring Invoice Template']/following-sibling::div/div[text()='" + needItemName + "']/following-sibling::div/input", InputType.XPath).sendKeys(needAmount);
	}

	public void setInitialInvoiceType(String initialInvoiceType) {
		Utilities.selectValueFromDropDownByValue(billingInitialInvoiceDropdown, initialInvoiceType);
		//Optimized For Encapsulation Below via selectInitialInvoice() By Passing In A String & Using A Private Modifier With By Class
	}

	public void selectInitialInvoice(String initialInvoice) {
		selectFromDropDown(initialInvoice, initialInvoiceDropDown);
	}

	public void setAmount_CustomSchedule() {
		FindElement.elementByAttribute("//div[@scheduletype='3']//h4[text()='" + Utilities.getCurrentMonth() + "']/following-sibling::div//input[@name='amount']", InputType.XPath).sendKeys(String.valueOf(Utilities.generateRandomInteger(3)));
	}

	public void setSubscriptionExpirationDate(String strExpirationDate) {
		 type(strExpirationDate,fieldExpirationDate,"ENTER");
	}

	/*
	 * Getter methods
	 * Below methods get string value of given object
	 */

	public String getStatusText() {
		Utilities.waitUntileElementIsVisible(statusText);
		return Utilities.getElementTextValue(statusText, ElementType.XPath);
	}

	public String getRenewalDate() {
		Utilities.waitUntileElementIsVisible(renewalDateField);
		return Utilities.getAttributeValue(renewalDateField, "value");
	}

	public String getUpcomingAppt(String chooseAppt) {
		Utilities.waitUntileElementIsVisible(chooseAppt);
		return Utilities.getElementTextValue(chooseAppt, ElementType.XPath);
	}

	public double getInitialService_NewTicketItemPrice(String needTicketItem) {
		WebElement elm = FindElement.elementByAttribute("//div[@id='initialTicket']//div[text() = '" + needTicketItem + "']/following-sibling::input", InputType.XPath);
		String val = elm.getAttribute("value");
		return Double.parseDouble(val);
	}

	public double getInitialSubTotal() {
		String elm = Utilities.getElementTextValue(initialSubTotalValue, ElementType.XPath);
		String newElm = elm.replace("$", "");
		return Double.parseDouble(newElm);
	}

	public double getInitialTax() {
		String elm = Utilities.getElementTextValue(initialTaxValue, ElementType.XPath);
		String newElm = elm.replace("$", "");
		return Double.parseDouble(newElm);
	}

	public double getInitialTotal() {
		String elm = Utilities.getElementTextValue(initialTotalValue, ElementType.XPath);
		String newElm = elm.replace("$", "");
		return Double.parseDouble(newElm);
	}

	public double getRecurringService_NewTicketItemPrice(String needTicketItem) {
		String ticketValue = Utilities.getAttributeValue("//div[@id='recurringServices']//div[text() = '" + needTicketItem + "']/following-sibling::input", "value");
//		String val = ticketValue.substring(3);
//		return Double.parseDouble(val);
		return Double.parseDouble(ticketValue);
	}

	public String getCustomProductionValue() {
		if (SystemUtils.IS_OS_LINUX) {
			Utilities.acceptAlert();
		}
		WebElement elm = FindElement.elementByAttribute("//span[text()='Custom Production']/following-sibling::input[@name='productionValue']", InputType.XPath);
		return elm.getAttribute("value");
	}

	public double getRecurringSubTotal() {
		String elm = Utilities.getElementTextValue(recurringSubTotalValue, ElementType.XPath);
		String newElm = elm.replace("$", "");
		return Double.parseDouble(newElm);
	}

	public double getRecurringTax() {
		String elm = Utilities.getElementTextValue(recurringTaxValue, ElementType.XPath);
		String newElm = elm.replace("$", "");
		return Double.parseDouble(newElm);
	}

	public double getRecurringTotal() {
		String elm = Utilities.getElementTextValue(recurringTotalValue, ElementType.XPath);
		String newElm = elm.replace("$", "");
		return Double.parseDouble(newElm);
	}

	public double getContractValue(String needServiceType) {
		String subscriptionTitle = "//h3[contains (text(), '" + needServiceType + "')]";
		Utilities.waitUntileElementIsVisible(subscriptionTitle);
		String elm = Utilities.getElementTextValue(contractValue, ElementType.XPath);
		String newElm = elm.replaceAll("[^\\\\.0123456789]", "");
		return Double.parseDouble(newElm);
	}

	public String getSubscriptionID(String serviceType) {
		return Utilities.getElementTextValue("//h3[contains(text(),'" + serviceType + "')]/span[not(contains(text(),'Contract'))]", ElementType.XPath);

	}

	public String getInitialInvoiceValue() {
		return Utilities.getElementTextValue(initialTotalValue, ElementType.XPath);
		//Optimized For Encapsulation Below via getInitialInvoiceTotal() Using A Private Modifier With By Class
	}

	public String getInitialInvoiceTotal (){
		return getText(initialInvoiceTotal);
	}

	public String getRecurringInvoiceValue() {
		return Utilities.getElementTextValue(recurringTotalValue, ElementType.XPath);
	}

	public String getBilling_initialBillingDate() {
		return Utilities.getAttributeValue(initialBillingDateInputField, "value");
	}

	public String getCurrentDate() {
		return Utilities.currentDateWithZeroDelimiterOnDate("MM/dd/yyyy");
	}

	public String getInitialInvoiceAmountWithoutTax_CustomSchedule() {
		Utilities.waitUntileElementIsVisible(selectedCustomScheduleOption);
		clickEditCustomInitialScheduleButton();
		return Utilities.getAttributeValue("//div[@scheduletype='3']//h4[text()='" + Utilities.getCurrentMonth() + "']/following-sibling::div//input[@name='amount']", "value");
	}

	public String getInitialInvoiceTotalAmount_CustomerSchedule() throws InterruptedException {
		customerDialogHeader = new CustomerViewDialog_Header();
		infoTab = new CustomerViewDialog_InfoTab();
		String initialInvoiceAmountWithoutTax = getInitialInvoiceAmountWithoutTax_CustomSchedule();
		customerDialogHeader.navigateTo(customerDialogHeader.infoTabInDialog);
		return String.valueOf((double) Math.round((Double.parseDouble(initialInvoiceAmountWithoutTax) + (Double.parseDouble(infoTab.getTaxRate()) * Double.parseDouble(initialInvoiceAmountWithoutTax)) / 100) * 100) / 100);
	}

	 public String getSubscriptionStatus(){
		return getText(subscriptionStatus);
	 }//getSubscriptionStatus()

	public void customInitialBilling_alertCondition() {
		int i = 0;
		while (i++ < 5) {
			try {
				Alert alert = Utilities.alertPopUp();
				String actionAlert = Utilities.getAlertText();
				String expected = "Custom initial billing schedules cannot be empty. These changes have not been saved.";
				if (actionAlert.contains(expected)) {
					alert.accept();
				}
				break;
			} catch (NoAlertPresentException e) {
				System.out.print("Exception found: " + e);
			}
		}
	}

	public String getUpcomingAppointment_specificDate(String needDate, int addMonths) {
		return Utilities.addYearstoCurrentYear("MM/dd/yy", addMonths);
	}

	public String getUpcomingAppointment_specificDay_everyYear(int appointmentNumber) {
		String[] datesResult = new String[7];
		int counter = 0;
		String inputDate = Utilities.currentDate("MM/dd/yy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
		LocalDate date = LocalDate.parse(inputDate, formatter).plusMonths(1);

		do {
			date = date.withDayOfMonth(1);
			if (date.getDayOfWeek() != DayOfWeek.TUESDAY) {
				date = date.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
			}
			int firstTuesday = date.getDayOfMonth();
			int thirdTuesday = firstTuesday + 14;
			date = date.withDayOfMonth(thirdTuesday);
			datesResult[counter] = formatter.format(date);
			date = date.plusMonths(12);
			++counter;
		} while (counter != 7);
		return datesResult[appointmentNumber-1];
	}

	public String getServiceType() {
		return Utilities.getElementTextValue(selectedBillingInitialInvoiceDropdown, ElementType.XPath);
	}

	public String getSubscriptionExpirationDate() {
		return find(fieldExpirationDate).getAttribute("value");
	}

	public String getSubscriptionRenewalDate() { return find(fieldRenewalDate).getAttribute("value");
	}

	public void clickActiveSubscription(){ click(activeSubscription); }

	public void clickActivateDeactivateButton() {
		delay(3000);
		click(activateDeactivateButton);
	}

	public void clickFreezeSubscriptionButtonOnCancelSubscriptionDialog() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(freezeCancelSubscriptionButton));
		click(freezeCancelSubscriptionButton);
	}
	public void checkEigibleForConsolidation(){
		Utilities.delay(500);
		Utilities.checkBox(chkboxEligibleForConsolidation);
	}
}