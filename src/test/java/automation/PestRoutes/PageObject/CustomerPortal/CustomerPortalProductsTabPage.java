package automation.PestRoutes.PageObject.CustomerPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.support.Color;

import static automation.PestRoutes.Utilities.Utilities.*;

public class CustomerPortalProductsTabPage extends CustomerPortalBasePage {

    private By tableHeaders = By.xpath("//table[@id='chemicalUsageReportTable']/thead/tr/th");

    public String getTableHeaderBackgroundColor() {
        String rgba_backgroundColor = locate(tableHeaders).getCssValue("background-color");
        String hex_backgroundColor = Color.fromString(rgba_backgroundColor).asHex();
        System.out.println("\n Table Header Background Color Is: " + hex_backgroundColor);
        System.out.println(" Background Should Not Be White: #FFFFFF");
        return rgba_backgroundColor;
    }
}