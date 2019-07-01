package nl.laptopshop.persistence;

import javax.naming.NamingException;

public interface UserDao {
	public String findRoleForUser(String name, String pass) throws NamingException;
}
