package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.Data.*;
import automation.PestRoutes.Utilities.Deprecated;
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
        Deprecated.clickElement("//span[text()='Pending']/parent::div/preceding-sibling::div[contains (text(), '"
                + needServiceName + "')]");
    }

    public void clickPendingAppointment(String serviceType) {
        delay(2000);
        click(pendingAppointment);
    }

    public void clickScheduledStructuredService(String needStructureName) {
        Deprecated.clickElement("//ul[@id='structuresMenuList']//span[text()='" + needStructureName + "']"
        );
    }

    public void clickSubScheduledStructuredService(String needStructureName, String needSubStructureName) {
        Deprecated.waitVisible("//ul[@id='structuresMenuList']//span[text()='" + needStructureName + "']");
        Deprecated.clickElement("//ul[@id='structuresMenuList']//span[text()='" + needStructureName + "']"
        );
        Deprecated.waitVisible(
                "//ul[@id='structuresMenuList']//span[text() = '" + needSubStructureName + "']");
        Deprecated.clickElement("//ul[@id='structuresMenuList']//span[text() = '" + needSubStructureName + "']"
        );
    }

    public void clickStatusButton() {
        Deprecated.clickElement(statusButton);
    }

    public void clickAddProductButton_InCompletingApptDialog() {
        Deprecated.waitVisible(addProductButton_InCompletingApptDialog);
        Deprecated.clickElement(addProductButton_InCompletingApptDialog);
    }

    public void chooseProduct(String needProductName) {
        Deprecated.clickElement("//span[text()='" + needProductName + "']");
    }

    public void chooseApplicationMethod(String needMethod) {
        Deprecated.scrollToElement(applicationMethodDropdown_InCompletingApptDialog);
        Deprecated.selectByText(applicationMethodDropdown_InCompletingApptDialog, needMethod);
    }

    public void chooseTargetIssue(String needOption) {
        Deprecated.selectByText(targetIssueDropdown_InCompletingApptDialog, needOption);
    }

    public void chooseTargetArea(String needOption) {
        Deprecated.selectByText(targetAreaDropdown_InCompletingApptDialog, needOption);
    }

    public void chooseInteriorServiced(String needOption) {
        Deprecated.selectByText(interiorSeviced, needOption);
    }

    public void clickCancelButton() {
        Deprecated.clickElement(cancelButton_InCompletingApptDialog);
    }

    public void clickSaveButton() {
        Deprecated.clickElement(saveButton_InCompletingApptDialog);
    }

    public void clickSaveAndCompleteButton() {
        if (Deprecated.isVisible(serviceNotes_Complete)) {
            Deprecated.clickElement(saveAndCompleteButton_InCompletingApptDialog);
        }else {
            Deprecated.clickElement(saveAndCompleteButton_InCompletingApptDialog);
        }
    }

    public void clickEditButton_AppointmentCard() {
        Deprecated.waitVisible(editButton);
        Deprecated.clickElement(editButton);
    }

    public void clickRescheduleButton() {
        Deprecated.waitVisible(reScheduleButton);
        Deprecated.clickElement(reScheduleButton);
    }

    public void clickCancelAppointmentButton() {
        delay(3000);
        Deprecated.waitVisible(cancelAppointment);
        Deprecated.clickElement(cancelAppointment);
    }

    public void clickOKButton() {
        click(okButton);
    }

    public void cancellationNotes() {
        Deprecated.waitVisible(cancelNotesAppointment);
        Deprecated.locate(cancelNotesAppointment).sendKeys(GetData.generateRandomString(10));
    }

    public void confirmCancellation() {
        Deprecated.waitVisible(confirmCancellation);
        Deprecated.clickElement(confirmCancellation);
    }

    public void clickUnitName() {
        Deprecated.waitVisible(unitName);
        Deprecated.clickElement(unitName);
    }

    @When("I close scheduling notice button")
    public void clickCloseSchedulingNoticeButton() {
        try {
            WebElement schedulingAppointment = Deprecated.locate(closeSchedulingNotice);
            if (schedulingAppointment.isEnabled()) {
                Deprecated.jsClickElement(closeSchedulingNotice);
            }
        } catch (Exception e) {
            System.out.println("Scheduling notice button not available");
        }
    }

    /*
     * Getter Methods Below methods get text value of an object
     */

    public String getUnitChemicalName() {
        Deprecated.waitVisible(unitNameObject);
        return Deprecated.getElementTextValue(unitNameObject);
    }

    public String getChemicalName() {
        Deprecated.waitVisible(productNameObject);
        return Deprecated.getElementTextValue(productNameObject);
    }

    public String getTreatedArea() {
        Deprecated.waitVisible(areaTreated);
        return Deprecated.getElementTextValue(areaTreated);
    }

    public String getTreatedPests() {
        Deprecated.waitVisible(pestsTreated);
        return Deprecated.getElementTextValue(pestsTreated);
    }

    /*
     * Getter Methods Below methods get text value of an Unit Product object
     */
    public String getUnitName() {
        Deprecated.waitVisible(unitName);
        return Deprecated.getElementTextValue(unitName);
    }

    public String getUnitAreaTreated() {
        Deprecated.waitVisible(areaUnitTreated);
        return Deprecated.getElementTextValue(areaUnitTreated);
    }

    public String getUnitPestsTreated() {
        Deprecated.waitVisible(pestsUnitTreated);
        return Deprecated.getElementTextValue(pestsUnitTreated);
    }

    // Structure Methods(Structure Products Object
    public String getStructureChemicalName() {
        Deprecated.waitVisible(structureNameObject);
        return Deprecated.getElementTextValue(structureNameObject);
    }

    public void clickStructureName() {
        Deprecated.waitVisible(structureName);
        Deprecated.clickElement(structureName);
    }

    public String getStructureAreaTreated() {
        Deprecated.waitVisible(areaStructureTreated);
        return Deprecated.getElementTextValue(areaStructureTreated);
    }

    public String getStructureIssuesTreated() {
        Deprecated.waitVisible(issuesStructureTreated);
        return Deprecated.getElementTextValue(issuesStructureTreated);
    }

    //Appointment Info details getter methods
    public String getScheduledBy_User() {
        Deprecated.waitVisible(scheduledBy);
        String schedulerName = Deprecated.getElementTextValue(scheduledBy);
        int index = schedulerName.indexOf(" on");
        schedulerName = schedulerName.substring(0, index);
        return schedulerName;
    }

    public String getCompletedBy_User() {
        Deprecated.waitVisible(completedBy);
        String completedByName = Deprecated.getElementTextValue(completedBy);
        int index = completedByName.indexOf(" on");
        completedByName = completedByName.substring(0, index);
        return completedByName;
    }

    public String getTechnicianOnAppointment() {
        Deprecated.waitVisible(technicianOnAppointment);
        System.out.println("technicianName : " + Deprecated.getElementTextValue(technicianOnAppointment));
        return Deprecated.getElementTextValue(technicianOnAppointment);
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
        Deprecated.waitVisible("//div[@id='appointmentContainor']//h3");
        return Deprecated.getAttribute("//div[@id='appointmentContainor']//h3", "data-appointmentid");
    }

    public void changeAppointmentTech(String needTechnicianName) {
        Deprecated.waitVisible("//select[@name='changeAppointmentTech']");
        Deprecated.clickElement("//select[@name='changeAppointmentTech']");
        Deprecated.clickElement("//select[@name='changeAppointmentTech']//option[text()='"+needTechnicianName+"']");
    }

    public void clickConfirmCancellationButton() {
        click(confirmCancellationButton);
    }

    public String getAppointmentsTabSubscription(){
        return getText(selectedSubscription);
    }

    public void typeServiceNotes(String serviceNotes) {
        Deprecated.type(serviceNotes, serviceNotesField);
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
