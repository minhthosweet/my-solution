package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CustomerviewDialog_AppointmentsTab extends BasePage {

    // **********Tab's objects**********
    public String statusButton = "//div[@id='appointmentContainor']//div[@id='SubStatus']";
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
    public String editButton = "//div[@id='appointmentContainor']//div[text()='Edit']";
    public String reScheduleButton = "//span[text()='Reschedule']";
    public String cancelAppointment = "//div[@id='completeButton']/following-sibling::div//div[text() = 'Cancel']";
    public String cancelNotesAppointment = "//textarea[@id='cancelReason']";
    public String confirmCancellation = "//span[text()='Confirm Cancelation']";
    //public String okButton = "//div[@aria-describedby= 'fieldRoutesDialog']//span[text()='OK']";
    private By okButton = By.xpath("//div[@aria-describedby= 'fieldRoutesDialog']//span[text()='OK']");
    private By technicianName = By.xpath("//div[@id='ADCustomer']//div[@class='techName techNameAssignButton']");
    private By allTechnicians = By.xpath("//div[@id='editRouteTechs']//div[@class='techName']");

    // Subscription objects
    public String createNewSubscription_Scheduling = "Create New Subscription";
    public String standAloneService_Scheduling = "Stand-Alone Service or Reservice";

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
        Utilities.waitUntileElementIsVisible(
                "//span[text()='Pending']/parent::div/preceding-sibling::div[contains (text(), '" + needServiceName
                        + "')]");
        Utilities.clickElement("//span[text()='Pending']/parent::div/preceding-sibling::div[contains (text(), '"
                + needServiceName + "')]", ElementType.XPath);
    }

    public void clickScheduledStructuredService(String needStructureName) {
        Utilities.clickElement("//ul[@id='structuresMenuList']//span[text()='" + needStructureName + "']",
                ElementType.XPath);
    }

    public void clickSubScheduledStructuredService(String needStructureName, String needSubStructureName) {
        Utilities.waitUntileElementIsVisible("//ul[@id='structuresMenuList']//span[text()='" + needStructureName + "']");
        Utilities.clickElement("//ul[@id='structuresMenuList']//span[text()='" + needStructureName + "']",
                ElementType.XPath);
        Utilities.waitUntileElementIsVisible(
                "//ul[@id='structuresMenuList']//span[text() = '" + needSubStructureName + "']");
        Utilities.clickElement("//ul[@id='structuresMenuList']//span[text() = '" + needSubStructureName + "']",
                ElementType.XPath);
    }

    public void clickStatusButton() {
        Utilities.clickElement(statusButton, ElementType.XPath);
    }

    public void clickAddProductButton_InCompletingApptDialog() {
        Utilities.waitUntileElementIsVisible(addProductButton_InCompletingApptDialog);
        Utilities.clickElement(addProductButton_InCompletingApptDialog, ElementType.XPath);
    }

    public void chooseProduct(String needProductName) {
        Utilities.clickElement("//span[text()='" + needProductName + "']", ElementType.XPath);
    }

    public void chooseApplicationMethod(String needMethod) {
        Utilities.scrollToElement(applicationMethodDropdown_InCompletingApptDialog);
        Utilities.selectValueFromDropDownByValue(applicationMethodDropdown_InCompletingApptDialog, needMethod);
    }

    public void chooseTargetIssue(String needOption) {
        Utilities.selectValueFromDropDownByValue(targetIssueDropdown_InCompletingApptDialog, needOption);
    }

    public void chooseTargetArea(String needOption) {
        Utilities.selectValueFromDropDownByValue(targetAreaDropdown_InCompletingApptDialog, needOption);
    }

    public void chooseInteriorServiced(String needOption) {
        Utilities.selectValueFromDropDownByValue(interiorSeviced, needOption);
    }

    public void clickCancelButton() {
        Utilities.clickElement(cancelButton_InCompletingApptDialog, ElementType.XPath);
    }

    public void clickSaveButton() {
        Utilities.clickElement(saveButton_InCompletingApptDialog, ElementType.XPath);
    }

    public void clickSaveAndCompleteButton() {
        Utilities.waitUntileElementIsVisible(serviceNotes_Complete);
        Utilities.clickElement(saveAndCompleteButton_InCompletingApptDialog, ElementType.XPath);
    }

    public void clickEditButton_AppointmentCard() {
        Utilities.waitUntileElementIsVisible(editButton);
        Utilities.clickElement(editButton, ElementType.XPath);
    }

    public void clickRescheduleButton() {
        Utilities.waitUntileElementIsVisible(reScheduleButton);
        Utilities.clickElement(reScheduleButton, ElementType.XPath);
    }

    public void clickCancelAppointmentButton() throws InterruptedException {
        Thread.sleep(3000);
        Utilities.waitUntileElementIsVisible(cancelAppointment);
        Utilities.clickElement(cancelAppointment, ElementType.XPath);
    }

    public void clickOKButton() {
        //Utilities.clickElement(okButton, Utilities.ElementType.XPath);
        click(okButton);
    }

    public void cancellationNotes() {
        Utilities.waitUntileElementIsVisible(cancelNotesAppointment);
        FindElement.elementByAttribute(cancelNotesAppointment, FindElement.InputType.XPath).sendKeys(Utilities.generateRandomString(10));
    }

    public void confirmCancellation() {
        Utilities.waitUntileElementIsVisible(confirmCancellation);
        Utilities.clickElement(confirmCancellation, ElementType.XPath);
    }

    public void clickUnitName() {
        Utilities.waitUntileElementIsVisible(unitName);
        Utilities.clickElement(unitName, ElementType.XPath);
    }

    @When("I close scheduling notice button")
    public void clickCloseSchedulingNoticeButton() {
        try {
            WebElement schedulingAppointment = FindElement.elementByAttribute(closeSchedulingNotice, FindElement.InputType.XPath);
            if (schedulingAppointment.isEnabled()) {
                Utilities.jsClickElement(closeSchedulingNotice, ElementType.XPath);
            }
        } catch (Exception e) {
            System.out.println("Scheduling notice button not available");
        }
    }

    /*
     * Getter Methods Below methods get text value of an object
     */

    public String getUnitChemicalName() {
        Utilities.waitUntileElementIsVisible(unitNameObject);
        return Utilities.getElementTextValue(unitNameObject, ElementType.XPath);
    }

    public String getChemicalName() {
        Utilities.waitUntileElementIsVisible(productNameObject);
        return Utilities.getElementTextValue(productNameObject, ElementType.XPath);
    }

    public String getTreatedArea() {
        Utilities.waitUntileElementIsVisible(areaTreated);
        return Utilities.getElementTextValue(areaTreated, ElementType.XPath);
    }

    public String getTreatedPests() {
        Utilities.waitUntileElementIsVisible(pestsTreated);
        return Utilities.getElementTextValue(pestsTreated, ElementType.XPath);
    }

    /*
     * Getter Methods Below methods get text value of an Unit Product object
     */
    public String getUnitName() {
        Utilities.waitUntileElementIsVisible(unitName);
        return Utilities.getElementTextValue(unitName, ElementType.XPath);
    }

    public String getUnitAreaTreated() {
        Utilities.waitUntileElementIsVisible(areaUnitTreated);
        return Utilities.getElementTextValue(areaUnitTreated, ElementType.XPath);
    }

    public String getUnitPestsTreated() {
        Utilities.waitUntileElementIsVisible(pestsUnitTreated);
        return Utilities.getElementTextValue(pestsUnitTreated, ElementType.XPath);
    }

    // Structure Methods(Structure Products Object
    public String getStructureChemicalName() {
        Utilities.waitUntileElementIsVisible(structureNameObject);
        return Utilities.getElementTextValue(structureNameObject, ElementType.XPath);
    }

    public void clickStructureName() {
        Utilities.waitUntileElementIsVisible(structureName);
        Utilities.clickElement(structureName, ElementType.XPath);
    }

    public String getStructureAreaTreated() {
        Utilities.waitUntileElementIsVisible(areaStructureTreated);
        return Utilities.getElementTextValue(areaStructureTreated, ElementType.XPath);
    }

    public String getStructureIssuesTreated() {
        Utilities.waitUntileElementIsVisible(issuesStructureTreated);
        return Utilities.getElementTextValue(issuesStructureTreated, ElementType.XPath);
    }

    //Appointment Info details getter methods
    public String getScheduledBy_User() {
        Utilities.waitUntileElementIsVisible(scheduledBy);
        String schedulerName = Utilities.getElementTextValue(scheduledBy, ElementType.XPath);
        int index = schedulerName.indexOf("on");
        schedulerName = schedulerName.substring(0, index);
        return schedulerName;
    }

    public String getCompletedBy_User() {
        Utilities.waitUntileElementIsVisible(completedBy);
        String completedByName = Utilities.getElementTextValue(completedBy, ElementType.XPath);
        int index = completedByName.indexOf("on");
        completedByName = completedByName.substring(0, index);
        return completedByName;
    }

    public String getTechnicianOnAppointment() {
        Utilities.waitUntileElementIsVisible(technicianOnAppointment);
        System.out.println("technicianName : " + Utilities.getElementTextValue(technicianOnAppointment, ElementType.XPath));
        return Utilities.getElementTextValue(technicianOnAppointment, ElementType.XPath);
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
        Utilities.waitUntileElementIsVisible("//div[@id='appointmentContainor']//h3");
        return Utilities.getAttributeValue("//div[@id='appointmentContainor']//h3", "data-appointmentid");
    }

    public void changeAppointmentTech(String needTechnicianName) {
        Utilities.waitUntileElementIsVisible("//select[@name='changeAppointmentTech']");
        Utilities.clickElement("//select[@name='changeAppointmentTech']", ElementType.XPath);
        Utilities.clickElement("//select[@name='changeAppointmentTech']//option[text()='"+needTechnicianName+"']", ElementType.XPath);
    }
}
