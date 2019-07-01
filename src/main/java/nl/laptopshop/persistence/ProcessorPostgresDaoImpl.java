package nl.laptopshop.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProcessorPostgresDaoImpl extends PostgresBaseDao implements ProcessorDao {
	private Connection conn;
	
	@Override
	public List<Processor> getAll() throws Exception {
		conn = super.getConnection();
		
		Statement stmt = conn.createStatement();
		String queryText = "select * from processor";
		List<Processor> allProcessors = new ArrayList<Processor>();
		ResultSet rs = stmt.executeQuery(queryText);
		Processor pr;
		while (rs.next()) {
			pr = new Processor(rs.getInt("prosessor_id"),
							 		rs.getString("naam"),
							 		rs.getInt("klokfrequentie"),
							 		rs.getInt("aantal_cores"));
			allProcessors.add(pr);
		}

		rs.close();
		stmt.close();
		conn.close();
		return allProcessors;
	}


}
