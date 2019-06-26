package nl.hu.ipass.laptopshop.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GrafischkaartPostgresDaoImpl extends PostgresBaseDao implements GrafischkaartDao{
	private Connection conn;
	
	@Override
	public List<Grafischkaart> getAll() throws Exception {
		conn = super.getConnection();
		
		Statement stmt = conn.createStatement();
		String queryText = "select * from grafischkaart";
		List<Grafischkaart> allKaarten = new ArrayList<Grafischkaart>();
		ResultSet rs = stmt.executeQuery(queryText);
		Grafischkaart pr;
		while (rs.next()) {
			pr = new Grafischkaart(rs.getInt("product_id"),
							 		rs.getString("resolutie"),
							 		rs.getInt("werkgeheugen"));
			allKaarten.add(pr);
		}

		rs.close();
		stmt.close();
		conn.close();
		return allKaarten;
	}

}
