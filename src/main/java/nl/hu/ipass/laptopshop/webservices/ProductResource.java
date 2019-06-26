package nl.hu.ipass.laptopshop.webservices;

import java.sql.SQLException;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.ipass.laptopshop.persistence.Product;


@Path("/countries")
public class ProductResource {

	@GET
//	@Path("/overzicht")
	@Produces("application/json")
	public String getProducts() throws Exception {
		System.out.println("hahahahahah");
		ProductService service = ServiceProvider.getProductService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Product p : service.getAllProducts()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", p.getId());
			job.add("resolutie", p.getResolutie());
			job.add("werkgeheugen", p.getWerkgeheugen());
			job.add("model", p.getModel());
			job.add("prijs", p.getPrijs());
			job.add("gnaam", p.getGkaartNaam());
			job.add("pnaam", p.getProcessorNaam());
			job.add("onaam", p.getOpslagNaam());
			job.add("lnaam", p.getLeverancierNaam());
			
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	
//	@Path("/new")
//	@POST
//	@RolesAllowed({"admin"})
//	@Produces("application/json")
//	public Response create(
//						   @FormParam("code") String code,
//						   @FormParam("iso3") String iso3,
//						   @FormParam("name") String name,
//						   @FormParam("continent") String continent,
//						   @FormParam("r") String region,
//						   @FormParam("surface") int surface,
//						   @FormParam("population") int population,
//						   @FormParam("governmentform") String govform,
//						   @FormParam("capital") String capital) throws Exception {
//		ProductService service = ServiceProvider.getProductService();
//		Product nProduct= new Product(code, iso3, name, capital, continent, region, surface, population, govform);
		
//		if(service.save(nProduct)) {
//			return Response.ok().build();
//		} else { return Response.status(400).build();
//		}
//		return null;
//	}
	
	
	
	
	
}
