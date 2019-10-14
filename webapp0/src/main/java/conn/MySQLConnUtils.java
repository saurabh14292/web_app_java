package conn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnUtils {
	public static Connection getMySQLConnection()
	        throws ClassNotFoundException, SQLException {
	    // Note: Change the connection parameters accordingly.
		 String hostName;
	    String dbName;
	    String userName;
	    String password;

	    Properties prop = new Properties();
	    try 
	    {
			File configDir = new File(System.getProperty("catalina.base"), "conf");
			File configFile = new File(configDir, "db.properties");
			InputStream stream = new FileInputStream(configFile);
			Properties props = new Properties();
			props.load(stream);
			prop.load(new FileInputStream(configFile));
	    } catch (IOException e) 
	    {
			e.printStackTrace();
	    }
	    
	    hostName = prop.getProperty("hostName");
 	    dbName = prop.getProperty("dbName");
 	    userName = prop.getProperty("userName");
 	    password = prop.getProperty("password");
	    
	    return getMySQLConnection(hostName, dbName, userName, password);
	}

	public static Connection getMySQLConnection(String hostName, String dbName,
	        String userName, String password) throws SQLException,
	        ClassNotFoundException {
	   
	    Class.forName("com.mysql.jdbc.Driver");
	 
	    // URL Connection for MySQL:
	    // Example: 
	    // jdbc:mysql://localhost:3306/simplehr
	    String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
	 
	    Connection conn = DriverManager.getConnection(connectionURL, userName,
	            password);
	    return conn;
	}
}
