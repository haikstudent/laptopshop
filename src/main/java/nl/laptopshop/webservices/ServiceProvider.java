package nl.laptopshop.webservices;

public class ServiceProvider {
	private static ProductService ProductService = new ProductService();

	public static ProductService getProductService() {
		return ProductService;
	}
}