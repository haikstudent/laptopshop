package nl.laptopshop.webservices;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import nl.laptopshop.persistence.*;;

public class ProductService {
	ProductPostgresDaoImpl ppd;
	Product pr;

	Grafischkaart gkrt;
	GrafischkaartPostgresDaoImpl gkrtImpl;

	Leverancier lv;
	LeverancierPostgresDaoImpl lvImpl;

	Opslag op;
	OpslagPostgresDaoImpl opImpl;

	Processor proc;
	ProcessorPostgresDaoImpl procImpl;

	public ProductService() {
		ppd = new ProductPostgresDaoImpl();
		gkrtImpl = new GrafischkaartPostgresDaoImpl();
		lvImpl = new LeverancierPostgresDaoImpl();
		opImpl = new OpslagPostgresDaoImpl();
		procImpl = new ProcessorPostgresDaoImpl();
	}

	public List<Product> getAllProducts() throws Exception {
		return ppd.getAll();
	}

	public List<Grafischkaart> getAllGkaarten() throws Exception {
		return gkrtImpl.getAll();
	}

	public List<Leverancier> getAllLeveranciers() throws Exception {
		return lvImpl.getAll();
	}

	public List<Opslag> getAllOpslag() throws Exception {
		return opImpl.getAll();
	}

	public List<Processor> getAllProcessors() throws Exception {
		return procImpl.getAll();
	}

	public boolean saveProduct(Product pr) throws Exception {
		return ppd.save(pr);
	}

	public boolean delProduct(int id) throws Exception {
		return ppd.delete(id);
	}

	public Product getProductByCode(int code) throws Exception {
		return ppd.getByCode(code);

	}

	public boolean update(Product pr) throws SQLException, NamingException {
		return ppd.update(pr);
	}

}
