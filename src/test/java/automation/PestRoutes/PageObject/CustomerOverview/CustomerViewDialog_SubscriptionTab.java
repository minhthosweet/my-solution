package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Data.*;

import static automation.PestRoutes.Utilities.GetWebDriver.*;
import static automation.PestRoutes.Utilities.Utilities.*;

import automation.PestRoutes.Utilities.Deprecated;
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
		Deprecated.clickElement(newSubscriptionButton);
	}

	public void clickNewSubscription() { click(newSubscription); }

	public void clickButton(String needButton) {
		Deprecated.waitVisible(needButton);
		Deprecated.clickElement(needButton);
	}

	public void clickActivateDeActivateButton() {
		Deprecated.waitVisible(ActivateDeactivateButton);
		Deprecated.clickElement(ActivateDeactivateButton);
	}

	public void clickFreezeSubscriptionButton() {
		Deprecated.waitVisible(freezeSubscriptionButton);
		Deprecated.clickElement(freezeSubscriptionButton);
	}

	public void clickCancelButton_CancenSubscriptionDialog() {
		Deprecated.clickElement(cancelButton_cancelSubscriptionDialog);
	}

	public void clickSubscription(String subscriptionID) {
		Deprecated.waitVisible("//li[@subscriptionid='" + subscriptionID + "']");
		Deprecated.clickElement("//li[@subscriptionid='" + subscriptionID + "']");
	}

	public void clickEditCustomInitialScheduleButton() {
		Deprecated.waitVisible(editCustomInitialScheduleButton);
		Deprecated.clickElement(editCustomInitialScheduleButton);
	}

	public void clickEditCustomRecurringScheduleButton() {
		Deprecated.waitVisible(editCustomRecurringScheduleButton);
		Deprecated.clickElement(editCustomRecurringScheduleButton);
	}

	public void clickRecurringSubTotalValue() {
		Deprecated.clickElement(recurringSubTotalValue);
	}

	public void clickSpecificDateButton_initialCustomSchedule() {
		Deprecated.waitVisible("//div[@scheduletype='3']//h4[text()='" + GetDate.getCurrentMonth() + "']/following-sibling::div[contains(text(),'Specific Date')]");
		Deprecated.clickElement("//div[@scheduletype='3']//h4[text()='" + GetDate.getCurrentMonth() + "']/following-sibling::div[contains(text(),'Specific Date')]");
	}

	public void clickDayOfTheWeekButton_recurringCustomSchedule() {
		Deprecated.waitVisible("//h4[text()='" + GetDate.getMonthsInFuture(1) + "']/following-sibling::div[text()='+ Day of Week']");
		Deprecated.clickElement("//h4[text()='" + GetDate.getMonthsInFuture(1) + "']/following-sibling::div[text()='+ Day of Week']");
	}

	public void clickSpecificDateButton_recurringCustomSchedule() {
		Deprecated.waitVisible("//h4[text()='" + GetDate.getCurrentMonth() + "']/following-sibling::div[text()='+ Day of Week']/preceding-sibling::div[text()='+ Specific Date']");
		Deprecated.clickElement("//h4[text()='" + GetDate.getCurrentMonth() + "']/following-sibling::div[text()='+ Day of Week']/preceding-sibling::div[text()='+ Specific Date']");
	}

	public void selectCurrentDateSpecificDate_recurringCustomSchedule(String needCurrentDate) {
		Deprecated.waitVisible("//h4[text()='" + GetDate.getCurrentMonth() + "']/parent::div//select//option[text()='" + needCurrentDate + "']");
		Deprecated.clickElement("//h4[text()='" + GetDate.getCurrentMonth() + "']/parent::div//select//option[text()='" + needCurrentDate + "']");
	}

	public void selectDayOfTheWeek(String needWeek, String needDay) {
		Deprecated.waitVisible("//h4[text()='" + GetDate.getMonthsInFuture(1) + "']/parent::div//select[@name='ordinal']/option");
		Deprecated.selectByText("//h4[text()='" + GetDate.getMonthsInFuture(1) + "']/parent::div//select[@name='ordinal']", needWeek);
		Deprecated.waitVisible("//h4[text()='" + GetDate.getMonthsInFuture(1) + "']/parent::div//select[@name='dayOfWeek']/option");
		Deprecated.selectByText("//h4[text()='" + GetDate.getMonthsInFuture(1) + "']/parent::div//select[@name='dayOfWeek']", needDay);
	}

	public void clickFinishEditingSchedule() {
		Deprecated.waitVisible(finishEditingCustomScheduleButton);
		Deprecated.clickElement(finishEditingCustomScheduleButton);
	}

	public void selectCurrentDate_CustomSchedule() {
		Deprecated.locate("//div[@scheduletype='3']//h4[text()='" + GetDate.getCurrentMonth() + "']/following-sibling::div//select").sendKeys(GetDate.getCurrentDate());
	}

	public void selectCancellationCategory(String needCategory) {
		Deprecated.waitVisible(cancellationCategoryDropdown_cancelSubscriptionDialog);
		Deprecated.selectByText(cancellationCategoryDropdown_cancelSubscriptionDialog, needCategory);
	}

	public void selectSalesRep(String needSalesRap) {
		Deprecated.locate("//h3[text()=  'Sales Info']/following-sibling::select[@name='creditTo']").sendKeys(needSalesRap);
	}

	public void selectSalesRep2(String needSalesRap) {
		Deprecated.selectByText(second_SalesRepDropdown, needSalesRap);
	}

	public void selectSalesRep3(String needSalesRap) {
		Deprecated.selectByText(third_SalesRepDropdown, needSalesRap);
	}

	public void selectSource(String needSource) {
		Deprecated.selectByText(sourceDropdown, needSource);
	}

	public void selectContractLength(String needContractLength) {
		Deprecated.selectByText(contractLengthDropdown, needContractLength);
	}

	public void selectSetRenewalDate(String needOption) {
		Deprecated.waitVisible(setRenewalDateDropdown);
		Deprecated.selectByText(setRenewalDateDropdown, needOption);
	}

	public void selectRenewalFrequency(String needOption) {
		Deprecated.waitVisible(renewalFrequencyDropdown);
		Deprecated.selectByText(renewalFrequencyDropdown, needOption);
	}

	public void selectBillingFrequency(String needBillingFrequency) {
		Deprecated.selectByText(billingFrequencyDropdown, needBillingFrequency);
	}

	public void selectServiceType(String needServiceType) throws Exception {
		if (!Deprecated.isPresent(serviceTypeDropdown)){
			Deprecated.waitVisible(serviceTypeDropdown);
		}
		Deprecated.selectByText(serviceTypeDropdown, needServiceType);
	}

	public void selectRecurringServiceType(String serviceType) {
		Utilities.selectByText(serviceTypeDropDown, serviceType);
	}

	public List<String> getRecurringServiceType() {
		return Deprecated.getAllSelectedOptionsFromDropDown(serviceTypeDropDown);
	}

	public void selectServiceFrequency(String needServiceFrequency) {
		Deprecated.selectByText(serviceFrequencyDropdown, needServiceFrequency);
	}

	public void selectServiceDuration(String needDuration) {
		Deprecated.selectByText(serviceDurationDropdown, needDuration);
	}

	public void selectCallAheadOption(String needOption) {
		Deprecated.selectByText(callAheadDropdown, needOption);
	}

	public void selectSeasonalOption(String needOption) {
		Deprecated.selectByText(seasonalDropdown, needOption);
	}

	public void selectAutoScheduleOption(String needOption) {
		Deprecated.selectByText(autoScheduleDropdown, needOption);
	}

	public void selectInitialFollowupOption(String needOption) {
		Deprecated.selectByText(initialFollowupDropdown, needOption);
	}

	public void selectRoutineRegionOption(String needOption) {
		Deprecated.selectByText(routineRegionDropdown, needOption);
	}

	public void selectPreferTechOption(String needOption) {
		Deprecated.selectByText(preferredTechDropdown, needOption);
	}

	public void selectPreferredDayOption(String needOption) {
		Deprecated.waitVisible(preferredDayDropdown);
		Deprecated.selectByText(preferredDayDropdown, needOption);
	}

	public void selectPrefferedTimeOption(String needOption) {
		Deprecated.selectByText(preferredTimeDropdown, needOption);
	}

	public void selectAdditionalItem_ToInitialInvoice(String needItem) {
		Deprecated.scrollToElementJS(initialInvoice_AddTicketItemButton);
		Deprecated.clickElement(initialInvoice_AddTicketItemButton);
		if (SystemUtils.IS_OS_LINUX) {
			Utilities.acceptAlert();
		}
		Deprecated.waitVisible("//span[text()=  '" + needItem + "']");
		delay(1000);
		Deprecated.clickElement("//span[text()=  '" + needItem + "']");
		try{
			Deprecated.waitVisible("//div[text()='"+needItem+"']");
		}catch (Exception e){
			Deprecated.clickElement(initialInvoice_AddTicketItemButton);
			if (SystemUtils.IS_OS_LINUX) {
				Utilities.acceptAlert();
			}
			Deprecated.waitVisible("//span[text()=  '" + needItem + "']");
			Deprecated.scrollToElementJS("//span[text()=  '" + needItem + "']");
			delay(1000);
			Deprecated.clickElement("//span[text()=  '" + needItem + "']");
		}
		Deprecated.waitVisible("//div[text()='"+needItem+"']");
	}

	public void selectAdditionalItem_ToRecurringInvoice(String needItem) throws InterruptedException {
		Thread.sleep(500);
		Deprecated.scrollToElementJS(recurringInvoice_AddTicketItemButton);
		Deprecated.waitVisible(recurringInvoice_AddTicketItemButton);
		Deprecated.clickElement(recurringInvoice_AddTicketItemButton);
		if (SystemUtils.IS_OS_LINUX) {
			Utilities.acceptAlert();
		}
		Deprecated.scrollToElementJS("//span[text()=  '" + needItem + "']");
		Deprecated.waitVisible("//span[text()=  '" + needItem + "']");
		Deprecated.clickElement("//span[text()=  '" + needItem + "']");
	}

	public void clearCustomDate() {
		Deprecated.waitVisible(customDateInputField);
		Deprecated.locate(customDateInputField).clear();
	}

	/*
	 * Setter methods
	 * Below methods insert values in the object input field
	 */

	public void setCancelSubscriptionNotes(String needNotes) {
		Deprecated.locate(cancelNotesInputField).sendKeys(needNotes);
	}

	public void setSoldDate(String needSoldDate) {
		Deprecated.locate(soldDateField).sendKeys(needSoldDate);
	}

	public void setExpDate(String needExpDate) {
		Deprecated.locate(expirationDateInputField).clear();
		Deprecated.locate(expirationDateInputField).sendKeys(needExpDate);
	}

	public void selectSubscriptionFlag(String needSubscriptionFlag) {
		Deprecated.waitVisible(subscriptionFlagDropdown);
		Deprecated.selectByText(subscriptionFlagDropdown, needSubscriptionFlag);
	}

	public void setInitialBillingDate(String needInitialBillingDate) {
		Deprecated.waitVisible(initialBillingDateInputField);
		Deprecated.locate(initialBillingDateInputField).clear();
		Deprecated.locate(initialBillingDateInputField).sendKeys(needInitialBillingDate);
	}

	public void setRenewalDate(String needDate) {
		Deprecated.locate(renewalDateField).sendKeys(Keys.CONTROL, "a");
		Deprecated.locate(renewalDateField).sendKeys(needDate);
	}

	public void set_PO_Number(String needPoNum) {
		Deprecated.locate(pO_NumberInputField).sendKeys(needPoNum);
	}

	public void setSubscriptionNotes(String needNotes) {
		Deprecated.locate(notesInputField).sendKeys(needNotes);
	}

	public void setAutoPayProfileDropdown() {
		Deprecated.waitVisible(autoPayProfileDropdown);
		Deprecated.selectByIndex(autoPayProfileDropdown, 1);

	}

	public void setMergeBillingAccountField(String needMergeCustomerName) {
		Deprecated.waitVisible(mergeBillingAccountField);
		Deprecated.locate(mergeBillingAccountField).sendKeys(needMergeCustomerName);
		Deprecated.locate(mergeBillingAccountField).sendKeys(Keys.ARROW_DOWN);
		try {
			Deprecated.waitVisible("//li[@role='presentation']//span[contains(text(),'"+needMergeCustomerName+"')]", 10);
			Deprecated.clickElement("//li[@role='presentation']//span[contains(text(),'"+needMergeCustomerName+"')]");
		} catch (Exception e) {
			Deprecated.locate(mergeBillingAccountField).sendKeys(Keys.ARROW_DOWN);
			Deprecated.clickElement("//li[@role='presentation']//span[contains(text(),'"+needMergeCustomerName+"')]");
		}
	}

	public void setNetBillingDays(String needBillingDays) {
		Deprecated.locate(netBillingDaysInputField).sendKeys(needBillingDays);
	}

	public void setCustomDate(String needCustomDate) {
		Deprecated.waitVisible(customDateInputField);
		Deprecated.locate(customDateInputField).sendKeys(needCustomDate);
		Deprecated.locate(customDateInputField).sendKeys(Keys.ENTER);
	}

	public void selectCustomDate(String customDate) {
		Deprecated.type(customDate, customDateField);
		Utilities.locate(customDateField).sendKeys(Keys.ENTER);
	}

	public void setInitialServiceQuote(String needAmount) {
		delay(1000);
		Deprecated.scrollToElementJS(initialQuoteInputField);
		driver.findElement(By.xpath(initialQuoteInputField)).sendKeys(Keys.CONTROL, "a");
		driver.findElement(By.xpath(initialQuoteInputField)).sendKeys(Keys.DELETE);
		driver.findElement(By.xpath(initialQuoteInputField)).sendKeys(needAmount);
	}

	public void setInitialServiceDiscount(String needAmount) throws InterruptedException {
		delay(1000);
		Deprecated.scrollToElementJS(initialTotalValue);
		Deprecated.waitVisible(initialTotalValue);
		Deprecated.locate(initialDiscountInputField).clear();
		Deprecated.highLight(initialDiscountInputField);
		if (SystemUtils.IS_OS_LINUX) {
			Thread.sleep(500);
			Deprecated.locate(initialQuoteInputField).sendKeys(Keys.DELETE);
			Thread.sleep(500);
		}
		Deprecated.locate(initialDiscountInputField).sendKeys(needAmount);
		Deprecated.clickElement(initialTotalValue);
	}

	public void setServiceQuote(String needService, String needAmount) throws InterruptedException {
		delay(1000);
		Deprecated.waitVisible("//div[text()= '" + needService + "']/parent::div//input[@name='serviceCharge']");
		Deprecated.highLight("//div[text()= '" + needService + "']/parent::div//input[@name='serviceCharge']");
		if (SystemUtils.IS_OS_LINUX) {
			Thread.sleep(500);
			Deprecated.locate("//div[text()= '" + needService + "']/parent::div//input[@name='serviceCharge']").sendKeys(Keys.DELETE);
			Thread.sleep(500);
		}
		Deprecated.locate("//div[text()= '" + needService + "']/parent::div//input[@name='serviceCharge']").sendKeys(needAmount);
	}

	public void setAdditionalItemAmount(String needItemName, String needAmount) {
		Deprecated.highLight("//h3[text()='Recurring Invoice Template']/following-sibling::div/div[text()='" + needItemName + "']/following-sibling::div/input");
		Deprecated.locate("//h3[text()='Recurring Invoice Template']/following-sibling::div/div[text()='" + needItemName + "']/following-sibling::div/input").sendKeys(needAmount);
	}

	public void setInitialInvoiceType(String initialInvoiceType) {
		Deprecated.scrollToElementJS(billingInitialInvoiceDropdown);
		Deprecated.selectByText(billingInitialInvoiceDropdown, initialInvoiceType);
	}

	public void selectInitialInvoice(String initialInvoice) {
		Deprecated.scrollToElementJS(initialInvoiceDropDown);
		Utilities.selectByText(initialInvoiceDropDown, initialInvoice);
	}

	public void setAmount_CustomSchedule() {
		Deprecated.locate("//div[@scheduletype='3']//h4[text()='" + GetDate.getCurrentMonth() + "']/following-sibling::div//input[@name='amount']").sendKeys(String.valueOf(GetData.generateRandomInteger(3)));
	}

	public void setSubscriptionExpirationDate(String strExpirationDate) {
		 Deprecated.type(strExpirationDate,fieldExpirationDate,"ENTER");
	}

	/*
	 * Getter methods
	 * Below methods get string value of given object
	 */

	public String getStatusText() {
		Deprecated.waitVisible(statusText);
		return Deprecated.getElementTextValue(statusText);
	}

	public String getRenewalDate() {
		Deprecated.waitVisible(renewalDateField);
		return Deprecated.getAttribute(renewalDateField, "value");
	}

	public String getUpcomingAppt(String chooseAppt) {
		Deprecated.waitVisible(chooseAppt);
		return Deprecated.getElementTextValue(chooseAppt);
	}

	public double getInitialService_NewTicketItemPrice(String needTicketItem) {
		WebElement elm = Deprecated.locate("//div[@id='initialTicket']//div[text() = '" + needTicketItem + "']/following-sibling::input");
		String val = elm.getAttribute("value");
		return Double.parseDouble(val);
	}

	public double getInitialSubTotal() {
		String elm = Deprecated.getElementTextValue(initialSubTotalValue);
		String newElm = elm.replace("$", "");
		return Double.parseDouble(newElm);
	}

	public double getInitialTax() {
		String elm = Deprecated.getElementTextValue(initialTaxValue);
		String newElm = elm.replace("$", "");
		return Double.parseDouble(newElm);
	}

	public double getInitialTotal() {
		String elm = Deprecated.getElementTextValue(initialTotalValue);
		String newElm = elm.replace("$", "");
		return Double.parseDouble(newElm);
	}

	public double getRecurringService_NewTicketItemPrice(String needTicketItem) {
		delay(3000);
		String ticketValue = Deprecated.getAttribute("//div[@id='recurringServices']//div[text() = '" + needTicketItem + "']/following-sibling::input", "value");
//		String val = ticketValue.substring(3);
//		return Double.parseDouble(val);
		return Double.parseDouble(ticketValue);
	}

	public String getCustomProductionValue() {
		if (SystemUtils.IS_OS_LINUX) {
			Utilities.acceptAlert();
		}
		WebElement elm = Deprecated.locate("//span[text()='Custom Production']/following-sibling::input[@name='productionValue']");
		return elm.getAttribute("value");
	}

	public double getRecurringSubTotal() {
		String elm = Deprecated.getElementTextValue(recurringSubTotalValue);
		String newElm = elm.replace("$", "");
		return Double.parseDouble(newElm);
	}

	public double getRecurringTax() {
		String elm = Deprecated.getElementTextValue(recurringTaxValue);
		String newElm = elm.replace("$", "");
		return Double.parseDouble(newElm);
	}

	public double getRecurringTotal() {
		String elm = Deprecated.getElementTextValue(recurringTotalValue);
		String newElm = elm.replace("$", "");
		return Double.parseDouble(newElm);
	}

	public double getContractValue(String needServiceType) {
		String subscriptionTitle = "//h3[contains (text(), '" + needServiceType + "')]";
		Deprecated.waitVisible(subscriptionTitle);
		String elm = Deprecated.getElementTextValue(contractValue);
		String newElm = elm.replaceAll("[^\\\\.0123456789]", "");
		return Double.parseDouble(newElm);
	}

	public String getSubscriptionID(String serviceType) {
		return Deprecated.getElementTextValue("//h3[contains(text(),'" + serviceType + "')]/span[not(contains(text(),'Contract'))]");

	}

	public String getInitialInvoiceValue() {
		return Deprecated.getElementTextValue(initialTotalValue);
	}

	public String getInitialInvoiceTotal (){
		return getText(initialInvoiceTotal);
	}

	public String getRecurringInvoiceValue() {
		return Deprecated.getElementTextValue(recurringTotalValue);
	}

	public String getBilling_initialBillingDate() {
		return Deprecated.getAttribute(initialBillingDateInputField, "value");
	}

	public String getCurrentDate() {
		return GetDate.currentDateWithZeroDelimiterOnDate("MM/dd/yyyy");
	}

	public String getInitialInvoiceAmountWithoutTax_CustomSchedule() {
		Deprecated.waitVisible(selectedCustomScheduleOption);
		clickEditCustomInitialScheduleButton();
		return Deprecated.getAttribute("//div[@scheduletype='3']//h4[text()='" + GetDate.getCurrentMonth() + "']/following-sibling::div//input[@name='amount']", "value");
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
				Alert alert = Utilities.getAlert();
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
		return GetDate.addYearstoCurrentYear("MM/dd/yy", addMonths);
	}

	public String getUpcomingAppointment_specificDay_everyYear(int appointmentNumber) {
		String[] datesResult = new String[7];
		int counter = 0;
		String inputDate = GetDate.currentDate("MM/dd/yy");
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
		return Deprecated.getElementTextValue(selectedBillingInitialInvoiceDropdown);
	}

	public String getSubscriptionExpirationDate() {
		return Utilities.locate(fieldExpirationDate).getAttribute("value");
	}

	public String getSubscriptionRenewalDate() { return Utilities.locate(fieldRenewalDate).getAttribute("value");
	}

	public void clickActiveSubscription(){ click(activeSubscription); }

	public void clickActivateDeactivateButton() {
		delay(3000);
		click(activateDeactivateButton);
	}

	public void clickFreezeSubscriptionButtonOnCancelSubscriptionDialog() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(freezeCancelSubscriptionButton));
		if(Deprecated.isVisible(cancellationCategoryDropdown_cancelSubscriptionDialog)){
			Deprecated.selectByIndex(cancellationCategoryDropdown_cancelSubscriptionDialog,0);
		}
		click(freezeCancelSubscriptionButton);
	}
	public void checkEigibleForConsolidation(){
		Utilities.delay(500);
		Utilities.checkBox(chkboxEligibleForConsolidation);
	}
}