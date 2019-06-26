package nl.hu.ipass.laptopshop.persistence;

import java.util.List;

public interface OpslagDao {
	public List<Opslag> getAll() throws Exception;
}
