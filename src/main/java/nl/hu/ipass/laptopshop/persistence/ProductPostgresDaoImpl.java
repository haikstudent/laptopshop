package nl.hu.ipass.laptopshop.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

public class ProductPostgresDaoImpl extends PostgresBaseDao implements ProductDao {
	private Connection conn;
	
	
	@Override
	public List<Product> getAll() throws Exception {
	conn = super.getConnection();
		
		Statement stmt = conn.createStatement();
		String queryText = "select product.*, grafischkaart.naam as gnaam, leverancier.naam as lnaam, opslag.opslagtype, processor.naam as pnaam \r\n" + 
				"from product\r\n" + 
				"inner join grafischkaart on product.gkaart_id = grafischkaart.gkaart_id\r\n" + 
				"inner join leverancier on product.leverancier_id = leverancier.leverancier_id\r\n" + 
				"inner join opslag on product.opslagid = opslag.opslag_id\r\n" + 
				"inner join processor on product.processor_id = processor.prosessor_id";
		List<Product> allProducts = new ArrayList<Product>();
		ResultSet rs = stmt.executeQuery(queryText);
		Product pr;
		while (rs.next()) {
			pr = new Product(rs.getInt("product_id"),
							 rs.getString("resolutie"),
							 rs.getInt("werkgeheugen"),
							 rs.getString("model"),
							 rs.getDouble("prijs"),
							 rs.getString("gnaam"),
							 rs.getString("pnaam"),
							 rs.getString("opslagtype"),
							 rs.getString("lnaam"));
			allProducts.add(pr);
		}

		rs.close();
		stmt.close();
		conn.close();
		return allProducts;
	}

	@Override
	public Product getByModel(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Product pr) throws SQLException, NamingException {
		conn = super.getConnection();
		PreparedStatement ps = conn.prepareStatement(
				"UPDATE product SET "
				+ "gkaart_id=?, "
				+ "processor_id=?, "
				+ "resolutie=?,"
				+ "werkgeheugen=?,"
				+ "oplsagid=?,"
				+ "model=?,"
				+ "leverancier_id=?,"
				+ "prijs=? "
				+ "WHERE product_id=?");
		ps.setInt(1, pr.getGkaartId());
		ps.setInt(2, pr.getProcessorId());
		ps.setString(3, pr.getResolutie());
		ps.setDouble(4, pr.getWerkgeheugen());
		ps.setInt(5, pr.getOpslagId());
		ps.setString(6, pr.getModel());
		ps.setInt(7, pr.getLeverancierId());
		ps.setDouble(8, pr.getPrijs());
		ps.setInt(9, pr.getId());

		try {
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return false;
	}

	@Override
	public boolean delete(int prId) throws SQLException, NamingException {
		conn = super.getConnection();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM product WHERE product_id=?");
		ps.setInt(1, prId);

		try {
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return false;
	}

	@Override
	public boolean save(Product pr) throws SQLException, NamingException {
		conn = super.getConnection();
		PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO product ("
				+ "gkaart_id, "
				+ "processor_id, "
				+ "resolutie,"
				+ "werkgeheugen,"
				+ "oplsagid,"
				+ "model,"
				+ "leverancier_id,"
				+ "prijs)"
				+ "VALUES(?,?,?,?,?,?,?,?)");
		ps.setInt(1, pr.getGkaartId());
		ps.setInt(2, pr.getProcessorId());
		ps.setString(3, pr.getResolutie());
		ps.setDouble(4, pr.getWerkgeheugen());
		ps.setInt(5, pr.getOpslagId());
		ps.setString(6, pr.getModel());
		ps.setInt(7, pr.getLeverancierId());
		ps.setDouble(8, pr.getPrijs());

		try {
			ps.executeQuery();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return false;
	}

}
