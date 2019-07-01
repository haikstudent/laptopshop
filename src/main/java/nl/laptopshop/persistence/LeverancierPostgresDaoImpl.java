package nl.laptopshop.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LeverancierPostgresDaoImpl extends PostgresBaseDao implements LeverancierDao{
	private Connection conn;
	
	@Override
	public List<Leverancier> getAll() throws Exception {
	conn = super.getConnection();
		
		Statement stmt = conn.createStatement();
		String queryText = "select * from leverancier";
		List<Leverancier> allLeverancier = new ArrayList<Leverancier>();
		ResultSet rs = stmt.executeQuery(queryText);
		Leverancier lv;
		while (rs.next()) {
			lv = new Leverancier(rs.getInt("leverancier_id"),
							 		rs.getString("naam"));
			allLeverancier.add(lv);
		}

		rs.close();
		stmt.close();
		conn.close();
		return allLeverancier;
	}
	
}
