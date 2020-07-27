package automation.PestRoutes.Controller.StructureValidation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.Structure.StructuresTab;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;

public class Structures extends BaseClass {
    CreateNewCustomer createNewCustomer;
    CustomerViewDialog_Header dialog = new CustomerViewDialog_Header();
    StructuresTab structures = new StructuresTab();
    CustomerviewDialog_AppointmentsTab appointmentTab = new CustomerviewDialog_AppointmentsTab();
    List list = new ArrayList<String>();

    private String mainStructureName = Utilities.generateRandomString(5);
    private String product = "UP-STAR";
    private String applicationMethod = "Direct Spray";
    private String targetIssue = "Bat";
    private String subUnit = Utilities.generateRandomString(5);
    private String subSubUnit = Utilities.generateRandomString(5);

    @Test

    public void validateStructures() throws Exception {
        createStructureCustomer();
        createStructure();
        addChemicalMainStructureTab();
        addChemicalSubStructureTab();
        verifyChemicalinUnit();
        verifySubChemicalinUnit();
    }

    public void createStructureCustomer() throws Exception {
        createNewCustomer = new CreateNewCustomer();
        createNewCustomer.createCustomerWithStructure();
    }

    @And("I add structure and sub structures")
    public void createStructure() {
        dialog.NavigateTo(dialog.structuresTabInDialog);
        structures.setMainStructure(mainStructureName);
        structures.setSubStructure(subUnit);
        structures.setSubStructure(subSubUnit);
    }

    @And("I add chemicals to main structure")
    public void addChemicalMainStructureTab() throws InterruptedException, IOException {
    dialog.NavigateTo(dialog.appointmentsTabInDialog);
        appointmentTab.clickScheduledService(getData("quarterly", quarterlyPreferredDayData));
        appointmentTab.clickEditButton_AppointmentCard();
        structures.clickStructuresTabApt();
        structures.clickDetailsButtonMainStructure(mainStructureName);
        structures.clickAddProductMainStructure();
        appointmentTab.chooseProduct(product);
        appointmentTab.chooseApplicationMethod(applicationMethod);
        appointmentTab.chooseTargetIssue(targetIssue);
        appointmentTab.clickSaveButton();
    }

    @And("I add chemicals to substructures")
    public void addChemicalSubStructureTab() throws InterruptedException, IOException {
        appointmentTab.clickScheduledService(getData("quarterly", quarterlyPreferredDayData));
        appointmentTab.clickEditButton_AppointmentCard();
        structures.clickStructuresTabApt();
        structures.clickDetailsButtonSubStructure(subUnit);
        structures.clickAddProductSubStructure();
        appointmentTab.chooseProduct(product);
        appointmentTab.chooseApplicationMethod(applicationMethod);
        appointmentTab.chooseTargetIssue(targetIssue);
        appointmentTab.clickSaveButton();
    }

    @Then("I verify chemical in structure")
    public void verifyChemicalinUnit() throws IOException, Exception {
        createNewCustomer = new CreateNewCustomer();
        createNewCustomer.searchCustomer();
        dialog.NavigateTo(dialog.structuresTabInDialog);
        appointmentTab.clickScheduledStructuredService(mainStructureName);
        structures.clickProductsAptTab();
        structures.getChemicalNameStructure(product);
        appointmentTab.clickStructureName();
        String actualStructureArea = appointmentTab.getStructureAreaTreated();
        String actualStructureIssues = appointmentTab.getStructureIssuesTreated();
        String actualStructureProductUsed = appointmentTab.getStructureChemicalName();
        result(product, actualStructureArea, "Chemical for Structure", "Structure Validation");
        result(product, actualStructureProductUsed, "Product for Structure", "Structure Validation");
        result(targetIssue, actualStructureIssues, "Target issue for Structure", "Structure Validation");
    }

    @Then("I verify chemical in substructure")
    public void verifySubChemicalinUnit() throws IOException, Exception {
        createNewCustomer = new CreateNewCustomer();
        createNewCustomer.searchCustomer();
        dialog.NavigateTo(dialog.structuresTabInDialog);
        appointmentTab.clickSubScheduledStructuredService(mainStructureName, subUnit);
        structures.clickProductsAptTab();
        structures.getChemicalNameStructure(product);
        appointmentTab.clickStructureName();
        String actualStructureArea = appointmentTab.getStructureAreaTreated();
        String actualStructureIssues = appointmentTab.getStructureIssuesTreated();
        String actualStructureProductUsed = appointmentTab.getStructureChemicalName();
        result(product, actualStructureArea, "Chemical for Structure", "Structure Validation");
        result(product, actualStructureProductUsed, "Product for Structure", "Structure Validation");
        result(targetIssue, actualStructureIssues, "Target issue for Structure", "Structure Validation");
    }

    @SuppressWarnings("unchecked")
    private void result(String expected, String actual, String stepName, String testName) {
        if (AssertException.result(expected, actual, stepName).size() > 0) {
            list.add(AssertException.result(expected, actual, stepName));
        }
        Reporter.status(stepName, expected, actual, testName);
    }

}