package nl.laptopshop.webservices;

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

import nl.laptopshop.persistence.Grafischkaart;
import nl.laptopshop.persistence.Leverancier;
import nl.laptopshop.persistence.Opslag;
import nl.laptopshop.persistence.Processor;
import nl.laptopshop.persistence.Product;

@Path("/producten")
public class ProductResource {
//alle producten ophalen
	@GET
	@Path("/overzicht")
	@Produces("application/json")
	public String getProducts() throws Exception {
		ProductService service = ServiceProvider.getProductService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Product p : service.getAllProducts()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", p.getId());
			job.add("model", p.getModel());
			job.add("lnaam", p.getLeverancierNaam());
			job.add("gnaam", p.getGkaartNaam());
			job.add("onaam", p.getOpslagNaam());
			job.add("ocapaciteit", p.getOpslagcapaciteit());
			job.add("werkgeheugen", p.getWerkgeheugen());
			job.add("pnaam", p.getProcessorNaam());
			job.add("resolutie", p.getResolutie());
			job.add("prijs", p.getPrijs());

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

//	laadt de gegevens van 1 product voor de update
	@GET
	@Path("/laadattr")
	@RolesAllowed({"admin"})
	@Produces("application/json")
	public String getProductsAtrr() throws Exception {
		ProductService service = ServiceProvider.getProductService();
		JsonArrayBuilder jabLv = Json.createArrayBuilder();
		JsonArrayBuilder jabP = Json.createArrayBuilder();
		JsonArrayBuilder jabO = Json.createArrayBuilder();
		JsonArrayBuilder jabG = Json.createArrayBuilder();

		for (Opslag p : service.getAllOpslag()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", p.getOpslagId());
			job.add("naamE", p.getOpslagType());
			job.add("geheugen", p.getOpslagCapiciteit());

			jabO.add(job);
		}

		for (Leverancier p : service.getAllLeveranciers()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", p.getLeverancierId());
			job.add("naam", p.getNaam());

			jabLv.add(job);
		}

		for (Processor p : service.getAllProcessors()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", p.getProcessorId());
			job.add("naam", p.getNaam());

			jabP.add(job);
		}

		for (Grafischkaart p : service.getAllGkaarten()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", p.getGkaart_id());
			job.add("naamE", p.getNaam());
			job.add("geheugen", p.getWerkgeheugen());

			jabG.add(job);
		}

		JsonObjectBuilder js = Json.createObjectBuilder();
		js.add("Leverancier", jabLv.build());
		js.add("Processor", jabP.build());
		js.add("Gkaart", jabG.build());
		js.add("Opslag", jabO.build());
		return js.build().toString();

	}
	
	
//	nieuw product toevoegen
	@POST
	@Path("/new")
	@RolesAllowed({"admin"})
	@Produces("application/json")
	public Response create(
						   @FormParam("Model") String model,
						   @FormParam("Prijs") double prijs,
						   @FormParam("Resolutie") String resolutie,
						   @FormParam("Werkgeheugen") int werkgeheugen,
						   @FormParam("Leverancier") int leverancier,
						   @FormParam("Processor") int processor,
						   @FormParam("Gkaart") int gkaart,
						   @FormParam("Opslag") int opslag) throws Exception {
		ProductService service = ServiceProvider.getProductService();
		Product nProduct = new Product(resolutie,werkgeheugen, model, prijs, gkaart, processor,opslag,leverancier);
		
		if(service.saveProduct(nProduct)) {
			return Response.ok().build();
		} else { return Response.status(400).build();
		}
		
	}
	
//	verwijder een product
	@DELETE
	@Path("{code}")
	@RolesAllowed({"admin"})
	@Produces("application/json")
	public Response delete(@PathParam("code") int code) throws Exception {
		ProductService service = ServiceProvider.getProductService();
		
	    if(service.delProduct(code)) {
	    	return Response.ok().build();
	    	
	    }else
	    return Response.status(404).build();
		
	}
	
	
//	verkrijg de informatie van 1 product
	@GET
	@Path("{code}")
	@RolesAllowed({"admin"})
	@Produces("application/json")
	public String getProduct(@PathParam("code") int code) throws Exception {
		ProductService service = ServiceProvider.getProductService();
		Product p = service.getProductByCode(code);
			
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", p.getId());
			job.add("model", p.getModel());
			job.add("lid", p.getLeverancierId());
			job.add("gid", p.getGkaartId());
			job.add("oid", p.getOpslagId());
			job.add("werkgeheugen", p.getWerkgeheugen());
			job.add("pid", p.getProcessorId());
			job.add("resolutie", p.getResolutie());
			job.add("prijs", p.getPrijs());
			
		
		
		return job.build().toString();
	}
	
	
	
	
	@Path("{code}")
	@RolesAllowed({"admin"})
	@PUT
	@Produces("application/json")
	public Response update(@PathParam("code") int code,
														@FormParam("Model") String model,
														@FormParam("Prijs") double prijs,
														@FormParam("Resolutie") String resolutie,
														@FormParam("Werkgeheugen") int werkgeheugen,
														@FormParam("Leverancier") int leverancier,
														@FormParam("Processor") int processor,
														@FormParam("Gkaart") int gkaart,
														@FormParam("Opslag") int opslag) throws Exception {
		ProductService service = ServiceProvider.getProductService();
		
		Product pr = new Product(code,resolutie,werkgeheugen, model, prijs, gkaart, processor,opslag,leverancier);
		
	    if(service.update(pr)) {
	    	return Response.ok().build();
		} else {
			return Response.status(400).build();
		}
	}
	
	
	

}
