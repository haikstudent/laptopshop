package nl.laptopshop.persistence;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

public interface ProductDao {
	public boolean save(Product product) throws SQLException, NamingException;
	public List<Product> getAll() throws Exception;
	public Product getByCode(int code) throws SQLException, NamingException;
	public boolean update(Product product) throws SQLException, NamingException;
	public boolean delete(int prId) throws SQLException, NamingException;
	
	
}
