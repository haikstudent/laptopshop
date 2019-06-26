package nl.hu.ipass.laptopshop.webservices;

import java.util.List;

import nl.hu.ipass.laptopshop.persistence.Product;
import nl.hu.ipass.laptopshop.persistence.ProductPostgresDaoImpl;



public class ProductService {
	ProductPostgresDaoImpl ppd;
	Product pr;
	
	public ProductService() {
		ppd = new ProductPostgresDaoImpl();
	}
	
	
	public List<Product> getAllProducts() throws Exception {
		return ppd.getAll();
	}

}
