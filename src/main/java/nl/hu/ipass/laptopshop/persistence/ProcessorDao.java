package nl.hu.ipass.laptopshop.persistence;

import java.util.List;

public interface ProcessorDao {
	public List<Processor> getAll() throws Exception;
}
