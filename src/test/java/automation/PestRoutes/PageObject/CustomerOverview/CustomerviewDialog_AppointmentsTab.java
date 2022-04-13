package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.Data.*;
import automation.PestRoutes.Utilities.Legacy;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static automation.PestRoutes.Utilities.GetWebDriver.*;
import static automation.PestRoutes.Utilities.Utilities.*;

public class CustomerviewDialog_AppointmentsTab extends BasePage {

    // **********Tab's objects**********
    public String statusButton = "//div[@id='appointmentContainor']//div[@id='SubStatus']";
    private By subStatusButton = By.xpath("//div[@id='appointmentContainor']//div[@id='SubStatus']");
    public String unitNameObject = "//h3[text() = 'Unit Products']/parent::div//div[@class='left bold']";
    public String productNameObject = "//h3[text() = 'Products Used']/parent::div//div[@class='left bold']";
    public String areaUnitTreated = "//div[contains (text(), 'Treated Areas:')]/following-sibling::div[1]";
    public String pestsUnitTreated = "//div[contains (text(), 'Treated Pests:')]/following-sibling::div[1]";
    public String areaTreated = "//h3[text() = 'Products Used']/parent::div//div[@class='left'][1]";
    public String pestsTreated = "//h3[text() = 'Products Used']/parent::div//div[@class='left'][2]";
    public String addProductButton_InCompletingApptDialog = "//div[text()='+ add product']";
    public String applicationMethodDropdown_InCompletingApptDialog = "//div[@class='chemicalTicketContent']//select[@name = 'applicationMethod']";
    public String targetIssueDropdown_InCompletingApptDialog = "//select[@id='targetedPests0']";
    public String targetAreaDropdown_InCompletingApptDialog = "//select[@id='targetedLocations0']";
    public String cancelButton_InCompletingApptDialog = "//div[@id='completeAppointment']/following-sibling::div//span[text() = 'Cancel']";
    public String saveButton_InCompletingApptDialog = "//div[@id='completeAppointment']/following-sibling::div//span[text() = 'Save']";
    public String interiorSeviced = "//select[@name='servicedInterior']";
    public String saveAndCompleteButton_InCompletingApptDialog = "//span[text() = 'Save and Complete']";
    public String subscriptionType_schedulinTab = "//h3[contains(text(),'Scheduling')]/parent::div//child::select[@name='subscriptionID']";
    public String serviceNotes_Complete = "//textarea[@name='serviceNotes']";
    private By serviceNotesField = By.xpath("//div[@id='completeTicket']/textarea[@name='serviceNotes']");
    public String editButton = "//div[@id='appointmentContainor']//div[text()='Edit']";
    public String reScheduleButton = "//span[text()='Reschedule']";
    public String cancelAppointment = "//div[@id='completeButton']/following-sibling::div//div[text() = 'Cancel']";
    public String cancelNotesAppointment = "//textarea[@id='cancelReason']";
    public String confirmCancellation = "//span[text()='Confirm Cancelation']";
    private By confirmCancellationButton = By.xpath("//span[text()='Confirm Cancellation']");
    private By okButton = By.xpath("//div[@aria-describedby= 'fieldRoutesDialog']//span[text()='OK']");
    private By technicianName = By.xpath("//div[@id='ADCustomer']//div[@class='techName techNameAssignButton']");
    private By allTechnicians = By.xpath("//div[@id='editRouteTechs']//div[@class='techName']");
    private By appointmentID = By.xpath("//div[@id='appointmentContainor']//h3/span");

    // Subscription objects
    public String standAloneService_Scheduling = "Stand-Alone Service or Reservice";
    private By selectedSubscription = By.xpath("//div[@id='appointmentContainor']//option[@selected='SELECTED']");
    private By pendingAppointment = By.xpath("//div[@id='historyPanel']//span[text()='Pending']");

    // Unit Products
    public String unitName = "//div[@id='appointmentContainor']//div[@style = 'fonts-size:11px; float:left; margin-left:5px;']";

    // Structure Products Page
    private String structureName = "//div[@id='appointmentContainor']//div[contains (text(), 'Structure:')]";
    public String structureNameObject = "//h3[text() = 'Structure Inspected']/parent::div//div[@class='left bold']";
    public String areaStructureTreated = "//div[contains (text(), 'Target Areas:')]/following-sibling::div[1]";
    public String issuesStructureTreated = "//select[@name='saveAppointmentSubscription']//following-sibling::div";

    // Scheduling Notice Objects
    public String closeSchedulingNotice = "//div[@id='schedulingNotice']//span[text()='X']";

    // Appointment Info Objects
    public String scheduledBy = "//div[@id='appointmentContainor']//label[text()='Scheduled By']/following-sibling::div[1]";
    public String completedBy = "//div[@id='appointmentContainor']//label[text()='Scheduled By']/following-sibling::div[1]";
    public String technicianOnAppointment = "//select[@name='changeAppointmentTech']//option[@selected]";

    // **********Functions**********

    /*
     * Action Methods Below function will click or select objects from drop down
     */
    public void clickScheduledService(String needServiceName) {
        delay(3000);
        //Utilities.waitUntileElementIsVisible(
          //      "//span[text()='Pending']/parent::div/preceding-sibling::div[contains (text(), '" + needServiceName
            //            + "')]");
        Legacy.clickElement("//span[text()='Pending']/parent::div/preceding-sibling::div[contains (text(), '"
                + needServiceName + "')]");
    }

    public void clickPendingAppointment(String serviceType) {
        delay(2000);
        click(pendingAppointment);
    }

    public void clickScheduledStructuredService(String needStructureName) {
        Legacy.clickElement("//ul[@id='structuresMenuList']//span[text()='" + needStructureName + "']"
        );
    }

    public void clickSubScheduledStructuredService(String needStructureName, String needSubStructureName) {
        Legacy.waitVisible("//ul[@id='structuresMenuList']//span[text()='" + needStructureName + "']");
        Legacy.clickElement("//ul[@id='structuresMenuList']//span[text()='" + needStructureName + "']"
        );
        Legacy.waitVisible(
                "//ul[@id='structuresMenuList']//span[text() = '" + needSubStructureName + "']");
        Legacy.clickElement("//ul[@id='structuresMenuList']//span[text() = '" + needSubStructureName + "']"
        );
    }

    public void clickStatusButton() {
        Legacy.clickElement(statusButton);
    }

    public void clickAddProductButton_InCompletingApptDialog() {
        Legacy.waitVisible(addProductButton_InCompletingApptDialog);
        Legacy.clickElement(addProductButton_InCompletingApptDialog);
    }

    public void chooseProduct(String needProductName) {
        Legacy.clickElement("//span[text()='" + needProductName + "']");
    }

    public void chooseApplicationMethod(String needMethod) {
        Legacy.scrollToElement(applicationMethodDropdown_InCompletingApptDialog);
        Legacy.selectByText(applicationMethodDropdown_InCompletingApptDialog, needMethod);
    }

    public void chooseTargetIssue(String needOption) {
        Legacy.selectByText(targetIssueDropdown_InCompletingApptDialog, needOption);
    }

    public void chooseTargetArea(String needOption) {
        Legacy.selectByText(targetAreaDropdown_InCompletingApptDialog, needOption);
    }

    public void chooseInteriorServiced(String needOption) {
        Legacy.selectByText(interiorSeviced, needOption);
    }

    public void clickCancelButton() {
        Legacy.clickElement(cancelButton_InCompletingApptDialog);
    }

    public void clickSaveButton() {
        Legacy.clickElement(saveButton_InCompletingApptDialog);
    }

    public void clickSaveAndCompleteButton() {
        if (Legacy.isVisible(serviceNotes_Complete)) {
            Legacy.clickElement(saveAndCompleteButton_InCompletingApptDialog);
        }else {
            Legacy.clickElement(saveAndCompleteButton_InCompletingApptDialog);
        }
    }

    public void clickEditButton_AppointmentCard() {
        Legacy.waitVisible(editButton);
        Legacy.clickElement(editButton);
    }

    public void clickRescheduleButton() {
        Legacy.waitVisible(reScheduleButton);
        Legacy.clickElement(reScheduleButton);
    }

    public void clickCancelAppointmentButton() {
        delay(3000);
        Legacy.waitVisible(cancelAppointment);
        Legacy.clickElement(cancelAppointment);
    }

    public void clickOKButton() {
        click(okButton);
    }

    public void cancellationNotes() {
        Legacy.waitVisible(cancelNotesAppointment);
        Legacy.locate(cancelNotesAppointment).sendKeys(GetData.generateRandomString(10));
    }

    public void confirmCancellation() {
        Legacy.waitVisible(confirmCancellation);
        Legacy.clickElement(confirmCancellation);
    }

    public void clickUnitName() {
        Legacy.waitVisible(unitName);
        Legacy.clickElement(unitName);
    }

    @When("I close scheduling notice button")
    public void clickCloseSchedulingNoticeButton() {
        try {
            WebElement schedulingAppointment = Legacy.locate(closeSchedulingNotice);
            if (schedulingAppointment.isEnabled()) {
                Legacy.jsClickElement(closeSchedulingNotice);
            }
        } catch (Exception e) {
            System.out.println("Scheduling notice button not available");
        }
    }

    /*
     * Getter Methods Below methods get text value of an object
     */

    public String getUnitChemicalName() {
        Legacy.waitVisible(unitNameObject);
        return Legacy.getElementTextValue(unitNameObject);
    }

    public String getChemicalName() {
        Legacy.waitVisible(productNameObject);
        return Legacy.getElementTextValue(productNameObject);
    }

    public String getTreatedArea() {
        Legacy.waitVisible(areaTreated);
        return Legacy.getElementTextValue(areaTreated);
    }

    public String getTreatedPests() {
        Legacy.waitVisible(pestsTreated);
        return Legacy.getElementTextValue(pestsTreated);
    }

    /*
     * Getter Methods Below methods get text value of an Unit Product object
     */
    public String getUnitName() {
        Legacy.waitVisible(unitName);
        return Legacy.getElementTextValue(unitName);
    }

    public String getUnitAreaTreated() {
        Legacy.waitVisible(areaUnitTreated);
        return Legacy.getElementTextValue(areaUnitTreated);
    }

    public String getUnitPestsTreated() {
        Legacy.waitVisible(pestsUnitTreated);
        return Legacy.getElementTextValue(pestsUnitTreated);
    }

    // Structure Methods(Structure Products Object
    public String getStructureChemicalName() {
        Legacy.waitVisible(structureNameObject);
        return Legacy.getElementTextValue(structureNameObject);
    }

    public void clickStructureName() {
        Legacy.waitVisible(structureName);
        Legacy.clickElement(structureName);
    }

    public String getStructureAreaTreated() {
        Legacy.waitVisible(areaStructureTreated);
        return Legacy.getElementTextValue(areaStructureTreated);
    }

    public String getStructureIssuesTreated() {
        Legacy.waitVisible(issuesStructureTreated);
        return Legacy.getElementTextValue(issuesStructureTreated);
    }

    //Appointment Info details getter methods
    public String getScheduledBy_User() {
        Legacy.waitVisible(scheduledBy);
        String schedulerName = Legacy.getElementTextValue(scheduledBy);
        int index = schedulerName.indexOf(" on");
        schedulerName = schedulerName.substring(0, index);
        return schedulerName;
    }

    public String getCompletedBy_User() {
        Legacy.waitVisible(completedBy);
        String completedByName = Legacy.getElementTextValue(completedBy);
        int index = completedByName.indexOf(" on");
        completedByName = completedByName.substring(0, index);
        return completedByName;
    }

    public String getTechnicianOnAppointment() {
        Legacy.waitVisible(technicianOnAppointment);
        System.out.println("technicianName : " + Legacy.getElementTextValue(technicianOnAppointment));
        return Legacy.getElementTextValue(technicianOnAppointment);
    }

    public String getTechName () {
        return getText(technicianName);
    }

    public void setTechName(String techName) {
        click(technicianName);
        List<WebElement> listOfTechNames = driver.findElements(allTechnicians);

        for (WebElement technician : listOfTechNames) {
            if (technician.getText().equalsIgnoreCase(techName)) {
                technician.click();
            }
        }
    }

    public String getAppointmentID() {
        Legacy.waitVisible("//div[@id='appointmentContainor']//h3");
        return Legacy.getAttribute("//div[@id='appointmentContainor']//h3", "data-appointmentid");
    }

    public void changeAppointmentTech(String needTechnicianName) {
        Legacy.waitVisible("//select[@name='changeAppointmentTech']");
        Legacy.clickElement("//select[@name='changeAppointmentTech']");
        Legacy.clickElement("//select[@name='changeAppointmentTech']//option[text()='"+needTechnicianName+"']");
    }

    public void clickConfirmCancellationButton() {
        click(confirmCancellationButton);
    }

    public String getAppointmentsTabSubscription(){
        return getText(selectedSubscription);
    }

    public void typeServiceNotes(String serviceNotes) {
        type(serviceNotesField, serviceNotes);
    }

    public String getServiceNotes() {
        delay(1000);
        click(subStatusButton);
        String serviceNotes = getText(serviceNotesField);
        clickSaveAndCompleteButton();
        return serviceNotes;
    }

    public String getAppointmentTabID(){
        return getText(appointmentID).trim();
    }

    public void clickEditButton() {
        click(By.xpath(editButton));
    }

    public void clickReschedule() {
        click(By.xpath(reScheduleButton));
        delay(3000);
    }
}
