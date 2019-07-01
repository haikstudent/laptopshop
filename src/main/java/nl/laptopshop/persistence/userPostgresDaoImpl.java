package nl.laptopshop.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

public class userPostgresDaoImpl extends PostgresBaseDao implements UserDao {
	public String findRoleForUser(String username, String pass) throws NamingException {
		String result = null;
		try (Connection con = super.getConnection()) {
			PreparedStatement ps = con.prepareStatement
					("select * from useraccount where username =? and password = ?");
			ps.setString(1, username);
			ps.setString(2, pass)
			;
			ResultSet dbResultSet = ps.executeQuery();
			
			while (dbResultSet.next()) {
				result = dbResultSet.getString("role");
			}
		} catch (SQLException sqle) { sqle.printStackTrace(); }
		
		return result;
	}
}
