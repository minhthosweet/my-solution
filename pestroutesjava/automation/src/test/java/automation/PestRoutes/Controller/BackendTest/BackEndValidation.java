package automation.PestRoutes.Controller.BackendTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import automation.PestRoutes.Utilities.DbBaseClass;

public class BackEndValidation extends DbBaseClass{
	
	private Connection connection;
	private static Statement statement;
	private static ResultSet result;
	
	/*
	 * SQL queries
	 */
	String validateChemical = "select * from employees limit 10";
	
	
	
	
	/*
	 * Validations
	 * Below methods get data and validate with front end
	 */
	@Test
	public void validateBackEnd() throws SQLException {
		statement = connection.createStatement();
		result = statement.executeQuery(validateChemical);
		System.out.println(result);
	}
	

}
