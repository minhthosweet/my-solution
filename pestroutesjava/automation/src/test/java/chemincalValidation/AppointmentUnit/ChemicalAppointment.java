package chemincalValidation.AppointmentUnit;

import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.BaseClass;

public class ChemicalAppointment extends BaseClass {

	Header mainHeader;
	DateSelection dateSelection;

	@Test
	public void ChemicalValidation() throws Exception {
		// Login
		mainHeader = new Header();

		// Date Selection
		dateSelection = new DateSelection();
		dateSelection.DateSelected();
		dateSelection.Appointment();
	}
}


