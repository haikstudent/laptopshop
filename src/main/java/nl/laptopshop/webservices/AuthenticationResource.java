package nl.laptopshop.webservices;

import java.security.Key;
import java.util.AbstractMap.SimpleEntry;
import java.util.Calendar;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.laptopshop.persistence.userPostgresDaoImpl;


@Path("/authentication")
public class AuthenticationResource {
	final static public Key key = MacProvider.generateKey();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("uname") String umail, @FormParam("upass") String pass) throws NamingException {
		try {
			userPostgresDaoImpl daoImpl = new userPostgresDaoImpl();
			String role = daoImpl.findRoleForUser(umail, pass);

			if (role == null) {
				throw new IllegalArgumentException("No user found!");
			}

			String token = createToken("username", role);

			SimpleEntry<String, String> JWT = new SimpleEntry<String, String>("JWT", token);
			return Response.ok(JWT).build();
		} catch (JwtException | IllegalArgumentException e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	private String createToken(String username, String role) {
		Calendar expiration = Calendar.getInstance();
		expiration.add(Calendar.MINUTE, 30);

		return Jwts.builder().setSubject(username).setExpiration(expiration.getTime()).claim("role", role)
				.signWith(SignatureAlgorithm.HS512, key).compact();
	}
}