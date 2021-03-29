package automation.PestRoutes.Controller.Reporting.Office;

import automation.PestRoutes.Utilities.Utilities;

public class OfficeObjects {

    public String billingByServiceType = "Billing by Service Type";

    public void click(String needTab) {
        Utilities.clickElement("//li[text() = '"+needTab+"']", Utilities.ElementType.XPath);
    }
}
