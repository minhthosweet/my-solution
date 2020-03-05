package automation.PestRoutes.Utilities;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.sql.Connection;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class DbBaseClass extends AppData{

//	private Connection connection;
	
    Connection conn = null;
    Session session= null;

	@BeforeClass

	public void setUp() throws Exception {
		int lport=5656;
		String dbName = getData("dbName", generalData);
	    String rhost=getData("dbHost", generalData);
	    String host=getData("sshHost", generalData);
	    int rport=3306;
	    String sshUser=getData("sshUserName", generalData);
	    String sshPassword=getData("sshPassword", generalData);
	    String dbuserName = getData("dbUserName", generalData);
	    String dbpassword = getData("dbPassword", generalData);
	    //String url = "jdbc:mysql://127.0.0.1:"+lport;
	    String url = "jdbc:mysql://127.0.0.1:5656/pestdemo?" + "user="+dbuserName+"&password="+dbpassword;
	    String driverName="com.mysql.cj.jdbc.Driver";
	    String command1="ls -ltr";
		
	    try{
	    	//Set StrictHostKeyChecking property to no to avoid UnknownHostKey issue
	    	java.util.Properties config = new java.util.Properties(); 
	    	config.put("StrictHostKeyChecking", "no");
	    	JSch jsch = new JSch();
	    	session=jsch.getSession(sshUser, host, 22);
	    	session.setPassword(sshPassword);
	    	session.setConfig(config);
	    	session.connect();
	    	Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand(command1);
	    	System.out.println("Connected");
	    	int assinged_port = session.setPortForwardingL(lport, rhost, rport);
	    	if (session.isConnected()) {
	    		System.out.println("connected");
	    	}
	        System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
	    	System.out.println("Port Forwarded");
	    	
	    	//mysql database connectivity
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
