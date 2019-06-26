package nl.hu.ipass.laptopshop.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OpslagPostgresDaoImpl  extends PostgresBaseDao implements OpslagDao {
	private Connection conn;
	
	@Override
	public List<Opslag> getAll() throws Exception {
		conn = super.getConnection();
		
		Statement stmt = conn.createStatement();
		String queryText = "select * from opslag";
		List<Opslag> allOpslag = new ArrayList<Opslag>();
		ResultSet rs = stmt.executeQuery(queryText);
		Opslag op;
		while (rs.next()) {
			op = new Opslag(rs.getInt("opslag_id"),
							 		rs.getString("opslagtype"),
							 		rs.getInt("opslagcapiciteit"));
			allOpslag.add(op);
		}

		rs.close();
		stmt.close();
		conn.close();
		return allOpslag;
	}

}
