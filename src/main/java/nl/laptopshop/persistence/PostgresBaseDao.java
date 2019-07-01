package nl.laptopshop.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PostgresBaseDao {
	private Connection conn; 
	
	
	public Connection getConnection() throws SQLException, NamingException{
		 Context initContext = new InitialContext();
         Context envContext = (Context) initContext.lookup("java:comp/env");
         DataSource ds = (DataSource) envContext.lookup("jdbc/PostgresDS");
         Connection conn = ds.getConnection();
		
	return conn;
	}
}
