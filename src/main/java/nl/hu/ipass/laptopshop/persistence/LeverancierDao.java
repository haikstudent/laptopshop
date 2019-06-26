package nl.hu.ipass.laptopshop.persistence;

import java.util.List;

public interface LeverancierDao {
	public List<Leverancier> getAll() throws Exception;
}
