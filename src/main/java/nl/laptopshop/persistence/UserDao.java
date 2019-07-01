package nl.laptopshop.persistence;

import javax.naming.NamingException;

public interface UserDao {
	public String findRoleForUser(String umail, String pass) throws NamingException;
}
