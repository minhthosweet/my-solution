package automation.PestRoutes.Utilities;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.jcraft.jsch.Session;
import java.sql.Connection;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class DbBaseClass extends AppData{

//	private Connection connection;
	
    public static Connection conn = null;
    Session session= null;

	@BeforeClass

	public void setUp() throws Exception {
		
	    String dbuserName = getData("dbUserName", generalData);
	    String dbpassword = getData("dbPassword", generalData);
	    String url = "jdbc:mysql://localhost:5656/pestdemo?" + "user="+dbuserName+"&password="+dbpassword;
	    String driverName="com.mysql.cj.jdbc.Driver";
		
	    try{
	    	java.util.Properties config = new java.util.Properties(); 
	    	config.put("StrictHostKeyChecking", "no");
	    	
            Class.forName(driverName);
            System.out.println("Url"+url);
            conn = DriverManager.getConnection(url);
            
            System.out.println ("Database connection established");
            System.out.println("DONE");
	    }catch(Exception e){
	    	e.printStackTrace();}

	}

	@AfterClass
	public void tearDown() throws SQLException {
		if(conn != null && !conn.isClosed()){
    		System.out.println("Closing Database Connection");
    		conn.close();
    	}
    	if(session !=null && session.isConnected()){
    		System.out.println("Closing SSH Connection");
    		session.disconnect();
    	}
	}

}
