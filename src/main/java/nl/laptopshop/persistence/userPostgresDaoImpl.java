package nl.laptopshop.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

public class userPostgresDaoImpl extends PostgresBaseDao implements UserDao {
	public String findRoleForUser(String umail, String pass) throws NamingException {
		String result = null;
		try (Connection con = super.getConnection()) {
			PreparedStatement ps = con.prepareStatement
					("select gebruiker.voornaam, gebruiker.email,gebruiker.wachtwoord , role.rolnaam from gebruiker\n" + 
							"inner join role on gebruiker.rol_id = role.rol_id\n" + 
							"where email =? and wachtwoord =?");
			ps.setString(1, umail);
			ps.setString(2, pass);
			ResultSet dbResultSet = ps.executeQuery();
			
			while (dbResultSet.next()) {
				result = dbResultSet.getString("rolnaam");
			}
		} catch (SQLException sqle) { sqle.printStackTrace(); }
		
		return result;
	}
}
